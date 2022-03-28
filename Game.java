import java.util.Scanner;

public class Game {
    private MazeInterface maze;
    private QuestionDatabase questionDatabase;
    private Scanner scanner;
    
    public Game(MazeInterface maze, QuestionDatabase database) {
        this.maze = maze;
        this.questionDatabase = new QuestionDatabase();
        this.scanner = new Scanner(System.in);
    }

    
/**
 * Current game method
 * Database stuff    
 */
    public void currentGame() {
    	while (!endConditionMet()) {
            System.out.println(this.maze);
            movementPrompt();
            if(playerTurn(getInput())) {
                System.out.println("Press 'S': Save game.");
            	System.out.println("Press 'E': Exit game without saving.");
            	System.out.println("Press 'N': Start a new game.");
            	
            	String input = getInput();
            	boolean invalidInput = true;
            	while(invalidInput) {
	            	if (input.equals("s")) {
	            		try {
	            			ResourceManager.save(maze, "trivia_maze.save");
	            			System.out.println("Game is saved.\n");
	            			System.out.println("\"Don't adventures ever have an end? \n" 
	            								+ "I suppose not. Someone else always has to carry on the story\" \n"
	            								+ "-Bilbo");
	            		} catch (Exception e) {
	            			System.out.println("Game is not saved: " + e.getMessage());
	            		} 
	            		invalidInput = false;
	            	} else if (input.equals("e")) {
	            		System.out.println("Thank you for playing!\n");
	            		System.out.println("\"Though here at journey's end I lie in darkness buried deep, "
	            				+ "beyond all towers strong and high, beyond all mountains steep, "
	            				+ "above all shadows rides the sun and stars for ever dwell: "
	            				+ "I will not say the day is done, nor bid the stars farewell\" "
	            				+ "- Sam\"");
	            		invalidInput = false;
	            	} else if (input.equals("n")) {
	            		System.out.println("");
	            		new Menu();
	            	} else {
	            		System.out.println("Unrecognized input. Please, try again.");
	            		input = getInput();
	            	}
            	}    
            	return;
            }
    	}
    	
        System.out.println(this.maze);
    }

    
    /**
     * 
     * Character movement based on wasd
     */
    private boolean playerTurn(String userInput) {
        if (userInput.equals("w")) {
        	//this.maze.moveNorth();
        	characterTraversalNorth();
        }
        
        else if (userInput.equals("a")) {
        	//this.maze.moveWest();
        	characterTraversalWest();
        }
        
        else if (userInput.equals("s")) {
        	//this.maze.moveSouth();
        	characterTraversalSouth();
        }
        
        else if (userInput.equals("d")) {
        	//this.maze.moveEast();
        	characterTraversalEast();
        }

        else if (userInput.equals("x")) {
        	return true;
        }
        
        return false;
    }
   
    private boolean askQuestion() {
        int answer;
        Question question = questionDatabase.retriveRadnomQuestionFromDatabase();
        System.out.println(question.getTheQuestion());
        System.out.println(question.displayChoices());
        answer = Integer.parseInt(getInput());
        return checkAnswer(answer, question);
    }
    
    private boolean checkAnswer(int userAnswer, Question question) {
        return userAnswer == question.getAnswerNumber() || userAnswer == question.cheatCode();
    }    
    
    /**
     * Tells user what buttons which should be pressed.
     */
    private void movementPrompt() {
        System.out.println("Movement Prompt:\n" + 
                           "W) Move North\n" + 
                           "A) Move West\n" + 
                           "S) Move South\n" + 
                           "D) Move East\n" +
                           "X) Stop playing\n");
    }

    
    private void characterTraversalNorth() {
    	if (!this.maze.northDoorUnlock()) {
    		System.out.println("A band of Uruk-Hai block the Northern passage.");
    	} else if (askQuestion()) {
            System.out.println("Correct! The path opens.");
            this.maze.moveNorth();
        } else {
            System.out.println("Incorrect answer, a band of Uruk-Hai block your path.");
            this.maze.northDoorLock();
        }   	
    }

    private void characterTraversalWest() {
    	if (!this.maze.westDoorUnlock()) {
    		System.out.println("A band of Uruk-Hai block the Western passage.");
    	} else if (askQuestion()) {
            System.out.println("Correct! The path opens.");
            this.maze.moveWest();
        } else {
            System.out.println("Incorrect answer, a band of Uruk-Hai block your path.");
            this.maze.westDoorLock();
        }   	
    }
    
    private void characterTraversalSouth() {
    	if (!this.maze.southDoorUnlock()) {
    		System.out.println("A band of Uruk-Hai block the Southern passage.");
    	} else if (askQuestion()) {
            System.out.println("Correct! The path opens.");
            this.maze.moveSouth();
        } else {
            System.out.println("Incorrect answer, a band of Uruk-Hai block your path.");
            this.maze.southDoorLock();
        }
    }	
    
    private void characterTraversalEast() {
    	if (!this.maze.eastDoorUnlock()) {
    		System.out.println("A band of Uruk-Hai block the Eastern passage.");
    	} else if (askQuestion()) {
            System.out.println("Correct! The path opens");
            this.maze.moveEast();
        } else {
            System.out.println("Incorrect answer, a band of Uruk-Hai block your path.");
            this.maze.eastDoorLock();
        }   	
    }
    
    /**
     * 
     * Receive input from the user
     */
    String getInput() {
        String userInput;
        userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        return userInput;
    }

    /**
     * 
     * Determines whether the end condition is a win or a loss
     */
    private boolean endConditionMet() {
        boolean endGame = false;
        if (this.maze.endReached()) {
            System.out.println("You made Sauron cry and he went home to think about what he's done");
            endGame = true;
        }
        else if (!this.maze.completionPossible()) {
            System.out.println("The Fellowship has fallen. Sauron has returned to power. Middle Earth burns.");
            endGame = true;
        }
        return endGame;
    }

}