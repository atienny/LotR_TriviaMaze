import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTest {

	Room room = new Room(new Door(), new Door(), new Door(), new Door());
	
	@Test
	public void roomCreate() {
		assertTrue(room.northDoorUnlocked());
		assertTrue(room.westDoorUnlocked());
		assertTrue(room.southDoorUnlocked());
		assertTrue(room.eastDoorUnlocked());
	}
	
	@Test
	public void lockNorthDoor() {
		room.lockNorthDoor();
        assertFalse(room.northDoorUnlocked());
	}
	
	@Test
	public void lockWestDoor() {
		room.lockWestDoor();
        assertFalse(room.westDoorUnlocked());
	}
	
	@Test
	public void lockSouthDoor() {
		room.lockSouthDoor();
        assertFalse(room.southDoorUnlocked());
	}
	
	@Test
	public void lockEastDoor() {
		room.lockEastDoor();
        assertFalse(room.eastDoorUnlocked());
	}
	
	@Test
	public void isVistedTrue() {
		room.setVisited(true);
		assertTrue(room.isVisited());
	}
	
	@Test
	public void isVistedFalse() {
		room.setVisited(false);
		assertFalse(room.isVisited());
	}

}
