package maze; 

import java.awt.*;
import javax.swing.*;

public class MazePrototypeFactory extends MazeFactory { 

  public MazePrototypeFactory(Maze mazePrototype, 
			      Wall wallPrototype, 
			      Room roomPrototype, 
			      Door doorPrototype) { 
    this.mazePrototype = mazePrototype;
    this.wallPrototype = wallPrototype;
    this.roomPrototype = roomPrototype;
    this.doorPrototype = doorPrototype;
  }

  public Maze makeMaze() {
    try { 
      return (Maze) mazePrototype.clone(); 
    } catch (CloneNotSupportedException e) {
      System.err.println("CloneNotSupportedException: " + e.getMessage());
    }
    return null; 
  }

  public Wall makeWall() { 
    try { 
      return (Wall) wallPrototype.clone(); 
    } catch (CloneNotSupportedException e) {
      System.err.println("CloneNotSupportedException: " + e.getMessage());
    }
    return null; 
  }

  public Room makeRoom(int roomNumber) { 
    try { 
      Room room = (Room) roomPrototype.clone(); 
      room.setRoomNumber(roomNumber); 
      return room; 
    } catch (CloneNotSupportedException e) {
      System.err.println("CloneNotSupportedException: " + e.getMessage());
    }
    return null; 
  }

  public Door makeDoor(Room room1, Room room2) { 
    try { 
      Door door = (Door) doorPrototype.clone();
      door.setRooms(room1, room2); 
      return door;
    } catch (CloneNotSupportedException e) {
      System.err.println("CloneNotSupportedException: " + e.getMessage());
    }
    return null; 
  }

  protected Maze mazePrototype;
  protected Wall wallPrototype;
  protected Room roomPrototype; 
  protected Door doorPrototype;

  public static void main(String[] args) { 
    Maze maze; 
    MazePrototypeFactory factory = null;

    if (args.length > 0) { 
      if ("Harry".equals(args[0])) { 
	factory = new MazePrototypeFactory(new Maze(),
					   new HarryPotterWall(),
					   new HarryPotterRoom(0),
					   new HarryPotterDoor(null, null)); 
      } else if ("Snow".equals(args[0])) { 
	factory = new MazePrototypeFactory(new Maze(),
					   new SnowWhiteWall(),
					   new SnowWhiteRoom(0),
					   new SnowWhiteDoor(null, null)); 
      }
    }
    if (factory == null) { 
      factory = new MazePrototypeFactory(new Maze(),
					 new Wall(),
					 new Room(0),
					 new Door(null, null)); 
    }
    maze = MazeGameAbstractFactory.createMaze(factory); 
    maze.setCurrentRoom(1); 

    JFrame frame;    
    frame = new JFrame("Maze -- Prototype");
    frame.setContentPane(new Maze.MazePanel(maze)); 
    frame.pack();
    Dimension frameDim = frame.getSize(); 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - frameDim.width / 2, 
		      screenSize.height / 2 - frameDim.height / 2);    
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    frame.setVisible(true);
  }  

}
