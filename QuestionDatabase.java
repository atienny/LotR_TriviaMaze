import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.sqlite.SQLiteDataSource;
//Structure to hold 
public class QuestionDatabase {
	private SQLiteDataSource dataSource = null;
	private Connection connectionToDatabase = null;
	private Statement sql_Statement = null;
	private List<Integer> questionsThatWereAlreadyUsedList;

	public QuestionDatabase() {
		questionsThatWereAlreadyUsedList = new ArrayList<Integer>();
		intializeDatabase();
	}

	public void intializeDatabase() {

		try {
			dataSource = new SQLiteDataSource();
			dataSource.setUrl("jdbc:sqlite:LOTR_Questions.db");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Opened database successfully");

	}

	public void clearUsedQuestionsList() {
		questionsThatWereAlreadyUsedList = new ArrayList<Integer>();
	}

	// for now send output to console but may need to return record instead for more
	// abstraction.
	// Think about how the other pieces are asking for the questions. Are they just
	// pressing a single thing or building the question display in any way
	public Question retriveRadnomQuestionFromDatabase() {
		// sql lite commands
		String queryForQuestion = "SELECT * FROM LOTR_Questions  WHERE Question_ID=?";
		String queryForDataBaseSize = "SELECT COUNT(*) AS total FROM LOTR_Questions";
		// placeholder question, if seen something didnt load proper
		StringBuilder question = new StringBuilder("something is wrong here");

		// connect to the sqlLite DB
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(queryForQuestion);
				Statement dbCountStatement = conn.createStatement()) {

			ResultSet countSet = dbCountStatement.executeQuery(queryForDataBaseSize);
			int tableSize = countSet.getInt("total");
			// choose a random question
			Random rando = new Random();
			int randomlyChoseQuestionNumber = rando.nextInt(tableSize - 1) + 1;
			// make sure the random question hasn't been used yet
			while (questionsThatWereAlreadyUsedList.contains(randomlyChoseQuestionNumber)) {
				rando.nextInt();
				randomlyChoseQuestionNumber = rando.nextInt(tableSize - 1) + 1;
			}
			// update query for the random variable
			stmt.setInt(1, randomlyChoseQuestionNumber);
			ResultSet foundQuestionSet = stmt.executeQuery();
			// add it to the used question list
			questionsThatWereAlreadyUsedList.add(randomlyChoseQuestionNumber);
			String choseQuestion = foundQuestionSet.getString("The_Question");
			String questionChoice1 = foundQuestionSet.getString("Question_choice_1");
			String questionChoice2 = foundQuestionSet.getString("Question_choice_2");
			String questionChoice3 = foundQuestionSet.getString("Question_choice_3");
			String questionChoice4 = foundQuestionSet.getString("Question_choice_4");
			int answer = foundQuestionSet.getInt("Answer");
			Question questionOBJ = new Question(choseQuestion, questionChoice1, questionChoice2, questionChoice3,
					questionChoice4, answer);
			return questionOBJ;

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return null;
	}
}