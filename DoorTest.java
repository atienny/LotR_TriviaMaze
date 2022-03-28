import static org.junit.Assert.*;

import org.junit.Test;

public class DoorTest {

	Door door = new Door();
	
	@Test
	public void doorTestUnlock() {
		assertTrue(door.isUnlocked());
	}

	@Test
	public void doorTestLock() {
		door.lockedDoor();
		assertFalse(door.isUnlocked());
	}

}