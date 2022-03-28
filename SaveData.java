import java.io.Serializable;

public class SaveData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -341835787402514619L;
	
	public Room[][] maze;
	public int mazeSize;
	public int playerLocationRow;
	public int playerLocationColumn;
	public int endingRoomRow;
	public int endingRoomColumn;
	
}