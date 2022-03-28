import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

public class MazeTest {

Maze maze = new Maze(4);
	
	@Test(expected = IllegalArgumentException.class)
	public void mazeSizeTooSmall() {
		Maze maze = new Maze(2);
	}
	
	@Test
	public void mazeSizeWithinBounds() {
		Maze maze = new Maze(4);
	}
	
	@Test
    public void mazeToString() {
        String expected = "-----------------\n" +
                          "| P |   |   |   |\n" +
                          "-----------------\n" +
                          "|   |   |   |   |\n" +
                          "-----------------\n" +
                          "|   |   |   |   |\n" +
                          "-----------------\n" +
                          "|   |   |   |   |\n" +
                          "-----------------";
        assertEquals(expected, maze.toString());
    }
	
	@Test
    public void playerMoveNorthSuccess() throws NoSuchFieldException, IllegalAccessException {
        Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
        playerLocationRow.setAccessible(true);
        playerLocationRow.set(maze, 3);
        maze.moveNorth();
        String expected = "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "| P |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------";
        assertEquals(expected, maze.toString());
    }
	
	@Test
    public void playerMoveNorthFail() throws NoSuchFieldException, IllegalAccessException {
        maze.moveNorth();
        String expected = "-----------------\n" +
                		  "| P |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------";
        assertEquals(expected, maze.toString());
    }
	
	
	
	@Test
    public void playerMoveWestFail() throws NoSuchFieldException, IllegalAccessException {
        maze.moveWest();
        String expected = "-----------------\n" +
                		  "| P |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------";
        assertEquals(expected, maze.toString());
    }
	
	@Test
    public void playerMoveWestSuccess() throws NoSuchFieldException, IllegalAccessException {
        Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
        playerLocationRow.setAccessible(true);
        playerLocationRow.set(maze, 3);
        maze.moveWest();
        String expected = "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "| P |   |   |   |\n" +
      		  			  "-----------------";
        assertEquals(expected, maze.toString());
	}
	
	@Test
    public void playerMoveSouthFail() throws NoSuchFieldException, IllegalAccessException {
        maze.moveSouth();
        String expected = "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "| P |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------";
        assertEquals(expected, maze.toString());
    }
	
	@Test
    public void playerMoveSouthSuccess() throws NoSuchFieldException, IllegalAccessException {
        Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
        playerLocationRow.setAccessible(true);
        playerLocationRow.set(maze, 3);
        maze.moveSouth();
        String expected = "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "| P |   |   |   |\n" +
      		  			  "-----------------";
        assertEquals(expected, maze.toString());
	}
	
	@Test
    public void playerMoveEastFail() throws NoSuchFieldException, IllegalAccessException {
        maze.moveNorth();
        String expected = "-----------------\n" +
                		  "| P |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------\n" +
                		  "|   |   |   |   |\n" +
                		  "-----------------";
        assertEquals(expected, maze.toString());
    }
	
	@Test
	public void northDoorUnlockTrue() throws NoSuchFieldException, IllegalAccessException {
		Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
		playerLocationRow.setAccessible(true);
		playerLocationRow.set(maze, 1);
		
		assertTrue(maze.northDoorUnlock());
	}
	
	@Test
	public void northDoorUnlockFalse() {
		assertFalse(maze.northDoorUnlock());
	}
	
	@Test
	public void westDoorUnlockTrue() throws NoSuchFieldException, IllegalAccessException {
		Field playerLocationColumn = Maze.class.getDeclaredField("playerLocationColumn");
		playerLocationColumn.setAccessible(true);
		playerLocationColumn.set(maze, 2);
		
		assertTrue(maze.westDoorUnlock());
	}
	
	@Test
	public void westDoorUnlockFalse() {
		assertFalse(maze.westDoorUnlock());
	}
	
	@Test
	public void southDoorUnlockTrue() throws NoSuchFieldException, IllegalAccessException {
		Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
		playerLocationRow.setAccessible(true);
		playerLocationRow.set(maze, 1);
		
		assertTrue(maze.southDoorUnlock());
	}
	
	@Test
	public void southDoorUnlockFalse() throws NoSuchFieldException, IllegalAccessException {
		Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
		playerLocationRow.setAccessible(true);
		playerLocationRow.set(maze, 3);
		
		assertFalse(maze.southDoorUnlock());
	}
	
	@Test
	public void eastDoorUnlockTrue() throws NoSuchFieldException, IllegalAccessException {
		Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
		playerLocationRow.setAccessible(true);
		playerLocationRow.set(maze, 1);
		
		assertTrue(maze.eastDoorUnlock());
	}
	
	@Test
	public void eastDoorUnlockFalse() throws NoSuchFieldException, IllegalAccessException {
		Field playerLocationColumn = Maze.class.getDeclaredField("playerLocationColumn");
		playerLocationColumn.setAccessible(true);
		playerLocationColumn.set(maze, 3);
		
		assertFalse(maze.eastDoorUnlock());
	}
	
	@Test
    public void playerMoveEastSuccess() throws NoSuchFieldException, IllegalAccessException {
        Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
        playerLocationRow.setAccessible(true);
        playerLocationRow.set(maze, 3);
        maze.moveEast();
        String expected = "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   |   |   |   |\n" +
      		  			  "-----------------\n" +
      		  			  "|   | P |   |   |\n" +
      		  			  "-----------------";
        assertEquals(expected, maze.toString());
	}
	
	@Test
    public void endReachedSuccess() throws NoSuchFieldException, IllegalAccessException {
        Field playerLocationRow = Maze.class.getDeclaredField("playerLocationRow");
        Field playerLocationColumn = Maze.class.getDeclaredField("playerLocationColumn");
        playerLocationRow.setAccessible(true);
        playerLocationColumn.setAccessible(true);
        playerLocationRow.set(maze, 3);
        playerLocationColumn.set(maze, 3);
        
        assertTrue(maze.completionPossible());
    }
	
	@Test
	public void walkToMordorPossible() {
		assertTrue(maze.completionPossible());
	}
	
	@Test
	public void walkToMordorNotPossible() {
		assertFalse(maze.completionPossible());
	}

}
