import static org.junit.Assert.*;

import org.junit.Test;

public class MenuTest {

	@Test
	public void mazeSizeTooSmall() {
		assertTrue(Menu.invalidMazeSize(3));
	}
	
	@Test
	public void mazeSizeInbound() {
		assertFalse(Menu.invalidMazeSize(6));
	}
	
	@Test
	public void mazeSizeTooLarge() {
		assertTrue(Menu.invalidMazeSize(11));
	}

}
