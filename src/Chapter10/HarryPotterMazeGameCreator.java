package Chapter10; 

public class HarryPotterMazeGameCreator extends MazeGameCreator { 

  public HarryPotterMazeGameCreator() {
	super();
  }

  public Wall makeWall() { 
    return new HarryPotterWall(); 
  }

  public Room makeRoom(int roomNumber) { 
    return new HarryPotterRoom(roomNumber); 
  }

  public Door makeDoor(Room room1, Room room2) { 
    return new HarryPotterDoor(room1, room2); 
  }

}
