package maze; 

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class UniversalMazeFactory extends MazeFactory {

  public static MazeFactory getInstance() { 
    if (theInstance == null) { 
      if (usePrototype) { 
	switch (theme) { 
	case HARRY_PORTER_THEME: 
	  theInstance = new MazePrototypeFactory(new Maze(),
						 new HarryPotterWall(),
						 new HarryPotterRoom(0),
						 new HarryPotterDoor(null, null));
	  break; 
	case SNOW_WHITE_THEME: 
	  theInstance = new MazePrototypeFactory(new Maze(),
						 new SnowWhiteWall(),
						 new SnowWhiteRoom(0),
						 new SnowWhiteDoor(null, null));
	  break; 
	default:
	  theInstance = new MazePrototypeFactory(new Maze(),
						 new Wall(),
						 new Room(0),
						 new Door(null, null));
	  break; 
	}
      } else { 
	switch (theme) { 
	case HARRY_PORTER_THEME: 
	  theInstance = new HarryPotterMazeFactory();
	  break; 
	case SNOW_WHITE_THEME: 
	  theInstance = new SnowWhiteMazeFactory();
	  break; 
	default:
	  theInstance = new UniversalMazeFactory();
	  break; 
	}
      }
    }
    return theInstance; 
  }

  protected UniversalMazeFactory() {}

  private static MazeFactory theInstance = null; 

  private static final int SIMPLE_THEME = 0; 
  private static final int HARRY_PORTER_THEME = 1; 
  private static final int SNOW_WHITE_THEME = 2; 
  private static boolean usePrototype = true; 
  private static int theme = SIMPLE_THEME; 

  static {
    Properties configProperties = new Properties();
    try {
      configProperties.load(new FileInputStream("maze.properties")); 
    } catch (IOException e) {}    
    String value;
    value = System.getProperty("maze.theme"); 
    if (value == null) { 
      value = configProperties.getProperty("maze.theme"); 
    }
    if (value != null) { 
      if ("Harry".equals(value)) { 
	theme = HARRY_PORTER_THEME; 
      } else if ("Snow".equals(value)) {
	theme = SNOW_WHITE_THEME;
      }
    }
    
    value = System.getProperty("maze.prototype"); 
    if (value == null) { 
      value = configProperties.getProperty("maze.prototype"); 
    }
    if (value != null) { 
      usePrototype = Boolean.getBoolean(value);
    }
  }


  public static void main(String[] args) { 
    MazeFactory factory = UniversalMazeFactory.getInstance();
    Maze maze = MazeGameAbstractFactory.createMaze(factory); 
    maze.setCurrentRoom(1); 

    JFrame frame;    
    frame = new JFrame("Maze -- Universal Factory");
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
