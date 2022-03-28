import java.io.Serializable;
import java.util.Scanner;

public class Menu implements Serializable {

	private static QuestionDatabase database;
	private static Scanner scanner;
	private static final long serialVersionUID = 1L;

	public Menu() {
		menuPrompt();
	}

	void menuPrompt() {
		int menuInput = 0;
		scanner = new Scanner(System.in);

		System.out.println("Welcome to Trivia Maze!");
		System.out.println("Press 1: Start a new game.");
		System.out.println("Press 2: Load a new game.");
		System.out.println("Press 3: Exit game.\n");

		menuInput = Integer.parseInt(scanner.nextLine());

		if (menuInput == 1) {
			System.out.println(
					"\"The board is set, the pieces are moving. \n"
					+ "We come to it at last, \n"
					+ "the great battle of our time.\\\"\n"
					+ "-Gandalf\n");
			newGame();			
		} else if (menuInput == 2) {
			
			try {
				MazeInterface loadedData = (MazeInterface) ResourceManager.load("trivia_maze.save");
				System.out.println("\"Oh, it's quite simple. \n"
						+ "If you are a friend, you speak the password, "
						+ "\nand the doors will open.\""
						+ "\n-Gandalf\n");
				
				loadedData.getData();
				Maze maze = new Maze(loadedData.getData().mazeSize,
						loadedData.getData().playerLocationRow,
						loadedData.getData().playerLocationColumn,
						loadedData.getData().endingRoomRow,
						loadedData.getData().endingRoomColumn);
				Game game = new Game(maze, database);
				game.currentGame();
				
			} catch (Exception e) {
				System.out.println("Game did not load: " + e.getMessage());
			}
		} else if (menuInput == 3) {
			System.out.println("Thank you for playing!");
		} else {
			System.out.println("Incorrect input. Please, try again.");
		}

		scanner.close();
	}

	private static void newGame() {
		Game game = new Game(new Maze(getMazeSize()), database);
		game.currentGame();
	}

	private static int getMazeSize() {
		System.out.print("Enter maze size: ");
		int size = 0;
		while (invalidMazeSize(size)) {
			try {
				size = Integer.parseInt(scanner.nextLine());
				if (invalidMazeSize(size)) {
					System.out.println("Input size must be between 4 and 10");
				}
			} catch (NumberFormatException e) {
				System.out.println("Input must be an Integer!");
			}
		}

		return size;
	}

	static boolean invalidMazeSize(int size) {
		return size < 4 || size > 10;
	}
}