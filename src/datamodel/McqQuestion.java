package datamodel;

public class McqQuestion extends Question {
	private String questionString;
	private String mcqOptions;
	private String correctAnswer;
	private String diffLevel;
	private int topicId;
	
	/**
	 * @return the questionString
	 */
	public String getQuestionString() {
		return questionString;
	}
	/**
	 * @param questionString the questionString to set
	 */
	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}
	/**
	 * @return the mcqOptions
	 */
	public String getMcqOptions() {
		return mcqOptions;
	}
	/**
	 * @param mcqOptions the mcqOptions to set
	 */
	public void setMcqOptions(String mcqOptions) {
		this.mcqOptions = mcqOptions;
	}
	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	/**
	 * @return the diffLevel
	 */
	public String getDiffLevel() {
		return diffLevel;
	}
	/**
	 * @param diffLevel the diffLevel to set
	 */
	public void setDiffLevel(String diffLevel) {
		this.diffLevel = diffLevel;
	}
	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
}
