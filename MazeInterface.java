public interface MazeInterface extends java.io.Serializable {
	
	public String toString();
    public void moveNorth();
    public void moveSouth();
    public void moveWest();
    public void moveEast();
    public void northDoorLock();
    public boolean northDoorUnlock();
    public void westDoorLock();
    public boolean westDoorUnlock();
    public void southDoorLock();
    public boolean southDoorUnlock();
    public void eastDoorLock();
    public boolean eastDoorUnlock();
    public boolean completionPossible();
    public boolean endReached();
    public SaveData getData();
}