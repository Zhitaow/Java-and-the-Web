package Chapter10;

public class SnowWhiteMazeGameCreator extends MazeGameCreator {

	
//  public SnowWhiteMazeGameCreator() {
//	  super();
//  }

  public Wall makeWall() { 
    return new SnowWhiteWall(); 
  }

  public Room makeRoom(int roomNumber) { 
    return new SnowWhiteRoom(roomNumber); 
  }

  public Door makeDoor(Room room1, Room room2) { 
    return new SnowWhiteDoor(room1, room2); 
  }

}
