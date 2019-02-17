package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import datamodel.McqQuestion;
import utility.DatabaseConnection;
import utility.QuizUtils;

public class QuestionsService {
	private Scanner scanner = new Scanner(System.in);
	
	public void processMcqQuestions() throws SQLException, InterruptedException {
		ResultSet mcqsResult = fetchMcqsQuestions();
		List<McqQuestion> mcqQuesList = mapResultToMcqs(mcqsResult);
		mcqsResult.close();
		int totalMcqs = mcqQuesList.size();
		List<Boolean> answerList = new ArrayList<Boolean>();
		for (int i = 0; i < totalMcqs; i++) {
			McqQuestion mcqQuestion = (McqQuestion) mcqQuesList.get(i);
			System.out.println("\n" + (i+1) + ". " + mcqQuestion.getQuestionString());
			System.out.println(mcqQuestion.getMcqOptions());
			String mcqAnswer = mcqQuestion.getCorrectAnswer();
			String answerInput;

			do {
				answerInput = scanner.next();
				if (!answerInput.equalsIgnoreCase("a") && !answerInput.equalsIgnoreCase("b")
						&& !answerInput.equalsIgnoreCase("c") && !answerInput.equalsIgnoreCase("d"))
					System.out.println("Invalid Input. Please re-enter.");
			} while (!answerInput.equalsIgnoreCase("a") && !answerInput.equalsIgnoreCase("b")
					&& !answerInput.equalsIgnoreCase("c") && !answerInput.equalsIgnoreCase("d"));
			
			answerList.add(mcqAnswer.equalsIgnoreCase(answerInput));
		}
		//answerList.forEach(System.out::println);
		float percent = (Float.valueOf(answerList.stream().filter(s -> s.equals(true)).count()) / Float.valueOf(totalMcqs)) * 100;
		System.out.println("You got " + percent + "% correct in MCQs");
	}
	
	private List<McqQuestion> mapResultToMcqs(ResultSet mcqsResult) throws SQLException {
		List<McqQuestion> listOfMcqQues = new ArrayList<McqQuestion>();
		while (mcqsResult.next()) {
			McqQuestion mcqQuestion = new McqQuestion();
			mcqQuestion.setQuestionString(mcqsResult.getString("question").toString());
			mcqQuestion.setMcqOptions(mcqsResult.getString("options").toString());
			mcqQuestion.setCorrectAnswer(mcqsResult.getString("answer").toString());
			mcqQuestion.setTopicId(mcqsResult.getInt("topic_id"));
			listOfMcqQues.add(mcqQuestion);
		}
		return listOfMcqQues;
	}

	public void processOpenQuestions() throws SQLException, InterruptedException {
		ResultSet openResult = fetchOpenQuestions();
		List<HashMap<String, String>> openQuestionsList = QuizUtils.resultSetToArrayList(openResult);
		openResult.close();
		Map<Integer, String> answerList = new HashMap<Integer, String>();
		for (int i = 0; i < openQuestionsList.size(); i++) {
			HashMap<String, String> openMap = (HashMap<String, String>) openQuestionsList.get(i);
			String answerInput;
			for (Map.Entry<String, String> entry : openMap.entrySet()) {
				System.out.println("\n" + (i+1) + ". " + entry.getValue());
			}
			answerInput = scanner.next();
			answerList.put(i+1, answerInput);
		}
	}

	public ResultSet fetchQuestionTypes() {
		ResultSet res = null;
		try {
			Statement state = DatabaseConnection.getConnection().createStatement();
			res = state.executeQuery("select type from question_types");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ResultSet fetchMcqsQuestions() {
		ResultSet res = null;
		try {
			Statement state = DatabaseConnection.getConnection().createStatement();
			res = state.executeQuery("select question,options,answer,diff_level,topic_id from mcq_questions");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ResultSet fetchOpenQuestions() {
		ResultSet res = null;
		try {
			Statement state = DatabaseConnection.getConnection().createStatement();
			res = state.executeQuery("select question from open_questions");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet fetchAllQuestionTopics() {
		ResultSet res = null;
		try {
			Statement state = DatabaseConnection.getConnection().createStatement();
			res = state.executeQuery("select id,topic from topics");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet fetchMcqQuestion(int selectedTopicInput, int selectedDifficultyLevel) {
		ResultSet res = null;
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("select id, question, options, answer from mcq_questions where topic_id=? and diff_level=?");
			preparedStatement.setInt(1, selectedTopicInput);
			preparedStatement.setString(2, "Level" + selectedDifficultyLevel);
			res = preparedStatement.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet fetchOpenQuestion(int selectedTopicInput, int selectedDifficultyLevel) {
		ResultSet res = null;
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("select id, question from open_questions where topic_id=? and diff_level=?");
			preparedStatement.setInt(1, selectedTopicInput);
			preparedStatement.setString(2, "Level" + selectedDifficultyLevel);
			res = preparedStatement.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
