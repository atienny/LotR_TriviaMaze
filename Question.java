//Structure for a single question
//intentionally exhaustive to provide ease of access at higher levels
public class Question {
	private String question;
	private String promptChoice1;
	private String promptChoice2;
	private String promptChoice3;
	private String promptChoice4;
	private int cheatCode = 9;
	private int answerNumber;
	private String questionDifficulty;

	 Question(String theQuestion, String prompt1, String prompt2, String prompt3, String prompt4, int answer) {
		question = theQuestion;
		promptChoice1 = prompt1;
		promptChoice2 = prompt2;
		promptChoice3 = prompt3;
		promptChoice4 = prompt4;
		answerNumber = answer;
	}

	public String getTheQuestion() {
		return question;
	}

	public String getPromptChoice1() {
		return promptChoice1;
	}

	public String getPromptChoice2() {
		return promptChoice2;
	}

	public String getPromptChoice3() {
		return promptChoice3;
	}

	public String getPromptChoice4() {
		return promptChoice4;
	}
	
	public int cheatCode() {
		return cheatCode;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}

	public String difficulty() {
		return questionDifficulty;
	}
	public String displayChoices() {
		StringBuilder str =new StringBuilder(promptChoice1+"\n"+promptChoice2+"\n"+promptChoice3+"\n"+promptChoice4);
		return str.toString();
	}
	
	
}