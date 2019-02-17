package launcher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import datamodel.McqQuestion;
import datamodel.OpenQuestion;
import datamodel.Question;
import datamodel.Topic;
import service.QuestionsService;
import utility.DatabaseConnection;

public class AppLauncher {
	static Scanner scanner = new Scanner(System.in);
	static QuestionsService questionsService = new QuestionsService();
	
	public static void main(String[] args) {
		try {
			DatabaseConnection.initialise();
			startQuizManager();
		}catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InputMismatchException inputMismatchException) {
			System.out.println("Invalid Input. Please re-enter.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}

	private static void startQuizManager() throws SQLException, InterruptedException, ClassNotFoundException {
		
		System.out.print("**********************************************");
		System.out.println("*******************************************");
		System.out.print("***************************");
		System.out.print("Welcome to Quiz Manager Application");
		System.out.println("***************************");
		System.out.print("**********************************************");
		System.out.println("*******************************************");
		
		int selectedTopicInputId = takeSelectedTopicInput();
		
		int selectedDifficultyLevel = takeSelectedDifficultyLevel();
		
		processAllQuestions(fetchQuestionsOnTopicAndDiffLevel(selectedTopicInputId, selectedDifficultyLevel));
		
		
		endQuizMessage();
	}

	private static void processAllQuestions(List<Question> fetchQuestionsOnTopicAndDiffLevel) {
		Collections.shuffle(fetchQuestionsOnTopicAndDiffLevel) ;
		int totalCorrectAnswer = 0;
		int totalMcqQuestion = 0;
		int totalOpenQuestion = 0;
		for(Question question : fetchQuestionsOnTopicAndDiffLevel) {
			String answerInput;
			if(question instanceof McqQuestion){
				McqQuestion mcqQuestion = (McqQuestion) question;
				System.out.println("\n" + mcqQuestion.getQuestionString());
				System.out.println("\n" + mcqQuestion.getMcqOptions());
				
				do {
					answerInput = scanner.next();
					if (!answerInput.equalsIgnoreCase("a") && !answerInput.equalsIgnoreCase("b")
							&& !answerInput.equalsIgnoreCase("c") && !answerInput.equalsIgnoreCase("d"))
						System.out.println("Invalid Input. Please re-enter.");
				} while (!answerInput.equalsIgnoreCase("a") && !answerInput.equalsIgnoreCase("b")
						&& !answerInput.equalsIgnoreCase("c") && !answerInput.equalsIgnoreCase("d"));
				if(answerInput.equalsIgnoreCase(mcqQuestion.getCorrectAnswer())) {
					totalCorrectAnswer++;
				}
				totalMcqQuestion++;
			}

			if(question instanceof OpenQuestion){
				OpenQuestion openQuestion = (OpenQuestion) question;
				System.out.println("\n" + openQuestion.getQuestionString());
				answerInput = scanner.next();
				totalOpenQuestion++;
			}
		}
		System.out.println("You have got " + totalCorrectAnswer/totalMcqQuestion * 100 + "% in MCQs");
	}

	private static void endQuizMessage() throws SQLException, InterruptedException, ClassNotFoundException {
		System.out.println("What would you like to do?");
		System.out.println("1. Exit\t\t2. Restart");
		int response;
		do {
			response = scanner.nextInt();
			if (response > 2 || response < 1)
				System.out.println("Invalid Input. Please re-enter.");
		} while (response > 2 || response < 1);
		if(response == 1) {
			System.out.println("*************************** Have a nice day!!! ***************************");
			System.exit(1);
		} else {
			startQuizManager();
		}		
	}

	private static int takeSelectedTopicInput() throws SQLException {
		int selectedTopicInput = 0;
		List<Topic> listOfTopics = fetchAllQuestionTopics();
		System.out.println("\nPlease select either of the below topics :\n");
		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		System.out.println("listOfTopics:"+listOfTopics.size());
		for(int i = 0 ; i < listOfTopics.size(); i++) {
			System.out.print("\t\t" + (i+1) + "." + listOfTopics.get(i).getTopicName());	
			hashMap.put((i+1), listOfTopics.get(i).getId());
		}
		
		do {
			selectedTopicInput = scanner.nextInt();
			if (selectedTopicInput > 6 || selectedTopicInput < 1)
				System.out.println("Invalid Input. Please re-enter.");
		} while (selectedTopicInput > 6 || selectedTopicInput < 1);
		
		return hashMap.get(selectedTopicInput);
	}
	
	private static int takeSelectedDifficultyLevel() {
		int selectedDifficultyLevel = 0;
		System.out.println("\nPlease select either of the below difficulty levels to start the quiz :\n");
		for(int i = 1 ; i < 4; i++) {
			System.out.print("\t" + i + ".Level " + i);	
		}
		
		do {
			selectedDifficultyLevel = scanner.nextInt();
			if (selectedDifficultyLevel > 3 || selectedDifficultyLevel < 1)
				System.out.println("Invalid Input. Please re-enter.");
		} while (selectedDifficultyLevel > 3 || selectedDifficultyLevel < 1);
		return selectedDifficultyLevel;
	}
	
	private static List<Topic> fetchAllQuestionTopics() throws SQLException {
		ResultSet resultSet = questionsService.fetchAllQuestionTopics();
		List<Topic> listOfTopics = new ArrayList<Topic>();
		for (int i = 1; resultSet.next(); i++) {
			Topic topic = new Topic();
			topic.setId(resultSet.getInt("id"));
			topic.setTopicName(resultSet.getString("topic"));
			listOfTopics.add(topic);
		}
		resultSet.close();
		return listOfTopics;
	}
	
	private static List<Question> fetchAllQuestionTypes() throws SQLException {
		ResultSet resultSet = questionsService.fetchQuestionTypes();
		List<Question> questionTypes = new ArrayList<Question>();
		for (int i = 1; resultSet.next(); i++) {
			Question question = new Question();
			question.setQuestionType(resultSet.getString("type"));
			questionTypes.add(question);
		}
		resultSet.close();
		return questionTypes;
	}
	
	private static List<Question> fetchQuestionsOnTopicAndDiffLevel(int selectedTopicInput, int selectedDifficultyLevel) throws SQLException {
		ResultSet mcqResultSet = questionsService.fetchMcqQuestion(selectedTopicInput, selectedDifficultyLevel);
		ResultSet openResultSet = questionsService.fetchOpenQuestion(selectedTopicInput, selectedDifficultyLevel);
		List<Question> questionsList = new ArrayList<Question>();
		for (; mcqResultSet.next() ;) {
			McqQuestion mcqQuestion = new McqQuestion();
			mcqQuestion.setQuestionString(mcqResultSet.getString("question"));
			mcqQuestion.setMcqOptions(mcqResultSet.getString("options"));
			mcqQuestion.setCorrectAnswer(mcqResultSet.getString("answer"));
			questionsList.add(mcqQuestion);
		}
		mcqResultSet.close();
		
		for (; openResultSet.next() ;) {
			OpenQuestion openQuestion = new OpenQuestion();
			openQuestion.setQuestionString(openResultSet.getString("question"));
			questionsList.add(openQuestion);
		}
		openResultSet.close();
		return questionsList;
	}

}