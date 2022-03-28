import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 532926960527351075L;
    private Door northDoor, southDoor, westDoor, eastDoor;
    private boolean isVisited = false;

    public Room(Door northDoor, Door southDoor, Door westDoor, Door eastDoor) {
        this.northDoor = northDoor;
        this.southDoor = southDoor;
        this.westDoor = westDoor;
        this.eastDoor = eastDoor;
    }

    public void lockNorthDoor() {
        this.northDoor.lockedDoor();
    }

    public void lockSouthDoor() {
        this.southDoor.lockedDoor();
    }

    public void lockWestDoor() {
        this.westDoor.lockedDoor();
    }

    public void lockEastDoor() {
        this.eastDoor.lockedDoor();
    }

    public boolean northDoorUnlocked() {
        return this.northDoor.isUnlocked();
    }

    public boolean southDoorUnlocked() {
        return this.southDoor.isUnlocked();
    }

    public boolean westDoorUnlocked() {
        return this.westDoor.isUnlocked();
    }

    public boolean eastDoorUnlocked() {
        return this.eastDoor.isUnlocked();
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited(boolean status) {
        this.isVisited = status;
    }
}