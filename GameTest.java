import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {

	private static QuestionDatabase database;
	
	@BeforeClass
    public static void beforeClass() throws SQLException {
        database = new QuestionDatabase();
        database.intializeDatabase();
        
    }
	
	@Test
    public void checkAnswerUsingCheatTrue() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Game game = new Game(new Maze(4), database);
        Method sut = Game.class.getDeclaredMethod("checkAnswer", String.class, Question.class, int.class);
        sut.setAccessible(true);

        boolean result = (boolean) sut.invoke(game, 9, new Question("question", "prompt1", "prompt2", "prompt3", "prompt4", 9));

        assertTrue(result);
    }
	
//	private class MockTriviaGame extends Game {
//        private String userAnswer;
//
//        private MockTriviaGame(String userAnswer) {
//            super(new Maze(4), new QuestionDatabase());
//            this.userAnswer = userAnswer;
//        }
//
//        String getInput() {
//            return this.userAnswer;
//        }
//    
//	}

}
