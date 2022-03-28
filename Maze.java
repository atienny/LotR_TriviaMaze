import java.io.Serializable;

public class Maze implements MazeInterface, Serializable {
    
    private static final long serialVersionUID = 892328020655688569L;
    private Room[][] maze;
    private int mazeSize, playerLocationRow, playerLocationColumn, endingRoomRow, endingRoomColumn;

    public Maze(int size) {
        this(size, 0, 0, size - 1, size - 1);
    }

    public Maze(int size, int playerLocationRow, int playerLocationColumn, int endingRoomRow, int endingRoomColumn) {
    	if (size < 4)
            throw new IllegalArgumentException("Size of maze must be at least 4");
        this.mazeSize = size;
        this.maze = new Room[size][size];
        initializeMaze();
        this.playerLocationRow = playerLocationRow;
        this.playerLocationColumn = playerLocationColumn;
        this.endingRoomRow = endingRoomRow;
        this.endingRoomColumn = endingRoomColumn;
    }

    private void initializeMaze() {
        
    	Door[][] doorsNorthSouth = new Door[this.mazeSize + 1][this.mazeSize];
        Door[][] doorsWestEast = new Door[this.mazeSize][this.mazeSize + 1];
        Door northDoor, southDoor, westDoor, eastDoor;
                
        for (int i = 0; i < doorsNorthSouth.length; i++) {
            for (int j = 0; j < doorsNorthSouth[0].length; j++) {
                doorsNorthSouth[i][j] = new Door();
            }
        }

        for (int i = 0; i < doorsWestEast.length; i++) {
            for (int j = 0; j < doorsWestEast[0].length; j++) {
                doorsWestEast[i][j] = new Door();
            }
        }
        
        for (int i = 0; i < this.mazeSize; i++) {
            for (int j = 0; j < this.mazeSize; j++) {
                northDoor = doorsNorthSouth[i][j];
                southDoor = doorsNorthSouth[i + 1][j];
                westDoor = doorsWestEast[i][j];
                eastDoor = doorsWestEast[i][j + 1];

                this.maze[i][j] = new Room(northDoor, southDoor, westDoor, eastDoor);
                if (j == 0)
                    this.maze[i][j].lockWestDoor();
                else if (j == this.mazeSize - 1)
                    this.maze[i][j].lockEastDoor();
                if (i == 0)
                    this.maze[i][j].lockNorthDoor();
                else if (i == this.mazeSize - 1)
                    this.maze[i][j].lockSouthDoor();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (4 * this.mazeSize) + 1; i++) {
            sb.append("-");
        }

        for (int i = 0; i < this.mazeSize; i++) {
            sb.append("\n|");
            for (int j = 0; j < this.mazeSize; j++) {
                if (this.playerLocationRow == i && this.playerLocationColumn == j)
                    sb.append(" P ");
                else
                    sb.append("   ");

                if (this.maze[i][j].eastDoorUnlocked())
                    sb.append("|");
                else 
                	sb.append("|");
            }
            sb.append("\n-");
            for (int j = 0; j < this.mazeSize; j++) {
                if (this.maze[i][j].southDoorUnlocked())
                    sb.append("----");
                else
                    sb.append("----");
            }
        }

        return sb.toString();
    }
    
    public void moveNorth() {
        if (this.maze[this.playerLocationRow][this.playerLocationColumn].northDoorUnlocked())
            this.playerLocationRow--;
        else
            System.out.println("A band of Uruk-Hai block the Northern passage.");
    }

    public void moveWest() {
        if (this.maze[this.playerLocationRow][this.playerLocationColumn].westDoorUnlocked())
            this.playerLocationColumn--;
        else
            System.out.println("A band of Uruk-Hai block the Western passage.");
    }
    
    public void moveSouth() {
        if (this.maze[this.playerLocationRow][this.playerLocationColumn].southDoorUnlocked())
            this.playerLocationRow++;
        else
            System.out.println("A band of Uruk-Hai block the Southern passage.");
    }

    public void moveEast() {
        if (this.maze[this.playerLocationRow][this.playerLocationColumn].eastDoorUnlocked())
            this.playerLocationColumn++;
        else
            System.out.println("A band of Uruk-Hai block the Eastern passage.");
    }
    
    public void northDoorLock() {
        this.maze[playerLocationRow][playerLocationColumn].lockNorthDoor();
    }

    public boolean northDoorUnlock() {
        return this.maze[playerLocationRow][playerLocationColumn].northDoorUnlocked();
    }
    
    public void westDoorLock() {
        this.maze[playerLocationRow][playerLocationColumn].lockWestDoor();
    }
    
	public boolean westDoorUnlock() {
		return this.maze[playerLocationRow][playerLocationColumn].westDoorUnlocked();
	}
    
    public void southDoorLock() {
        this.maze[playerLocationRow][playerLocationColumn].lockSouthDoor();
    }

	public boolean southDoorUnlock() {
		return this.maze[playerLocationRow][playerLocationColumn].southDoorUnlocked();
	}
    
    public void eastDoorLock() {
        this.maze[playerLocationRow][playerLocationColumn].lockEastDoor();
    }

	public boolean eastDoorUnlock() {
		return this.maze[playerLocationRow][playerLocationColumn].eastDoorUnlocked();
	}
    
    public boolean completionPossible() {
        boolean pathExists = walkToMordor(playerLocationRow, playerLocationColumn);
        unvisitedRooms();
        return pathExists;
    }

    private boolean walkToMordor(int row, int column) {
        if (row == this.endingRoomRow && column == this.endingRoomColumn)
            return true;
        if (this.maze[row][column].isVisited())
            return false;
        
        this.maze[row][column].setVisited(true);

        if (this.maze[row][column].northDoorUnlocked() && walkToMordor(row - 1, column))
            return true;
        if (this.maze[row][column].eastDoorUnlocked() && walkToMordor(row, column + 1))
            return true;
        if (this.maze[row][column].southDoorUnlocked() && walkToMordor(row + 1, column))
            return true;
        if (this.maze[row][column].westDoorUnlocked() && walkToMordor(row, column - 1))
            return true;

        return false;
    }
    
    private void unvisitedRooms() {
        for (int i = 0; i < this.mazeSize; i++) {
            for (int j = 0; j < this.mazeSize; j++) {
                this.maze[i][j].setVisited(false);
            }
        }
    }

    public boolean endReached() {
        return (this.playerLocationRow == this.endingRoomRow && this.playerLocationColumn == this.endingRoomColumn);
    }
    
    public SaveData getData() {
    	SaveData sd = new SaveData();
    	sd.maze = maze;
    	sd.mazeSize = maze.length;
    	sd.playerLocationRow = playerLocationRow;
    	sd.playerLocationColumn = playerLocationColumn;
    	sd.endingRoomRow = endingRoomRow;
    	sd.endingRoomColumn = endingRoomColumn;
    	return sd;
    }
}