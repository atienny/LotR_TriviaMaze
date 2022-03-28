import java.io.Serializable;

/**
 * Determine door state
 *
 */

public class Door implements Serializable {
    
    private static final long serialVersionUID = 9139275846084082952L;
    private boolean unlockedDoor = true;

    /**
     * Door is locked
     */
    public void lockedDoor() {
        this.unlockedDoor = false;
    }

    /**
     * Door is unlocked
     */
    public boolean isUnlocked() {
        return this.unlockedDoor;
    }
}