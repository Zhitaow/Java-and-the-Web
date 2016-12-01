package Chapter10;

import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Room implements MapSite { 
	
  protected int roomNumber = 0; 
  protected boolean inroom = false;
  protected MapSite[] sides = new MapSite[4]; 
  protected Point location = null;   
  protected static AudioClip gong = 
		  AudioUtility.getAudioClip(
				  ResourceLoader.class.getResource(
						  "/audio/enter.au"));
  
  public static final Color ROOM_COLOR = new Color(152, 251, 152);
  public static final Color PLAYER_COLOR = Color.red;
  public static Image image, imageDesti, imageBG, 
  	arrowDown, arrowUp, arrowLeft, arrowRight; 
  protected boolean goal = false;
  protected boolean hint = false;
  private Direction direction = null;
  
  @SuppressWarnings("unused")
  private Image readImage(String path) {
	URL url;
	try {
		url = new URL(path);
		try {
			Image image = ImageIO.read(url);
			return image; 
		} catch (IOException e) {}
	} catch (Exception e) {}
	return null;
	 
  }
  
  
  public Room(int roomNumber) { 
    this.roomNumber = roomNumber; 
  }

  public Object clone() throws CloneNotSupportedException { 
    Room room = (Room) super.clone();
    room.sides = new MapSite[4]; 
    for (int i = 0; i < 4; i++) {
      if (sides[i] != null) { 
    	  room.sides[i] = (MapSite) sides[i].clone(); 
      }
    }
    return room; 
  }
  
  public MapSite getSide(Direction dir) { 
    if (dir != null) { 
      return sides[dir.getOrdinal()]; 
    }
    return null; 
  }

  public void setSide(Direction dir, MapSite site) { 
    if (dir != null) { 
      sides[dir.getOrdinal()] = site; 
      if (site instanceof Door) { 
    	  Door door = (Door) site;
    	  if (dir == Direction.NORTH ||
    			  dir == Direction.SOUTH) {
    		  door.setOrientation(Orientation.HORIZONTAL); 
    	  } else { 
    		  door.setOrientation(Orientation.VERTICAL); 
    	  }
      }
    }
  }
  
  public void setRoomNumber(int roomNumber) { 
    this.roomNumber = roomNumber; 
  }

  public int getRoomNumber() { 
    return roomNumber; 
  }

  public Point getLocation() { 
    return location; 
  }

  public void setLocation(Point location) { 
    this.location = location;
  }

  public void enter(Maze maze) {
    maze.setCurrentRoom(this);
//    gong.setFramePosition(0);
//    gong.start();
    gong.play();
  }
  
  public boolean isInRoom() { 
    return inroom; 
  }

  public void setInRoom(boolean inroom) { 
    this.inroom = inroom;
  }

  public boolean isGoal() {
	  return goal;
  }
  
  public void setGoal(boolean goal) {
	  this.goal = goal;
  }
  
  public boolean isHint() {
	  return hint;
  }
  
  public void setHint(boolean hint) {
	  this.hint = hint;
  }
  
  public void setHintDir(Direction direction) {
	  this.direction = direction;
  }
  
  public void draw(Graphics g, int x, int y, int w, int h) {
    g.setColor(ROOM_COLOR); 
    g.fillRect(x, y, w, h);
    loadImage(g, x, y, w, h);
  }
  
  protected void loadImage(Graphics g, int x, int y, int w, int h) {
	    if (imageBG == null) {
	    	try {
	    		imageBG = 
	    			ImageIO.read(ResourceLoader.load("images/grass.jpg"));
	    	} catch (Exception e0) {System.out.println(e0);}
	    } else {
	    	g.drawImage(imageBG, x, y , x + w , y + h , 
	    			  0, 0, 120, 120, ROOM_COLOR, null);
	    }
	    if (image == null) {
	    	try {
//	    		image = readImage("http://harp.njit.edu/~zw56/Minion.jpg");
	    		image = 
	    			ImageIO.read(ResourceLoader.load("images/Minion.jpg"));
	    	} catch (Exception e1) {}
	    }
	    if (imageDesti == null) {
	    	try {
//	    		imageDesti = readImage("http://harp.njit.edu/~zw56/Minion_boss.jpg");
	    		imageDesti = 
	    			ImageIO.read(ResourceLoader.load("images/Minion_boss.jpg"));
	    	} catch (Exception e2) {}
	    }
	    if (inroom) {
	      if (image != null) {
	    	  g.drawImage(image, x, y , x + w , y + h , 
	    			  0, 0, 220, 220, ROOM_COLOR, null);
	      } else {
	          g.setColor(PLAYER_COLOR);
	          g.fillOval(x + w / 2 - 5, y + h / 2 - 5, 10, 10); 
	      }
	    }
	    if (goal) {
	        if (imageDesti != null) {
	        	g.drawImage(
	        			imageDesti, x, y , x + w , 
	        			y + h , 90, 0, 370, 280, ROOM_COLOR, null);
	        } else {
	            g.setColor(Color.black);
	            g.fillOval(x + w / 2 - 5, y + h / 2 - 5, 10, 10); 
	        }
	    }
	    if (hint && !inroom) {
//	    	System.out.println("Draw hint");
	        if (arrowUp == null || arrowDown ==null ||
	        		arrowLeft == null || arrowRight == null) {
	        	try {
	        		arrowUp = 
	        			ImageIO.read(ResourceLoader.load("images/arrow_north.png"));
	        		arrowDown = 
	        			ImageIO.read(ResourceLoader.load("images/arrow_south.png"));
	        		arrowLeft = 
	        			ImageIO.read(ResourceLoader.load("images/arrow_west.png"));
	        		arrowRight = 
	        			ImageIO.read(ResourceLoader.load("images/arrow_east.png"));
/*	        		arrowUp = readImage(
 * 						"http://harp.njit.edu/~zw56/image/arrow_north.png");
/	        		arrowDown = readImage(
 * 						"http://harp.njit.edu/~zw56/image/arrow_south.png");
/	        		arrowLeft = readImage(
 * 						"http://harp.njit.edu/~zw56/image/arrow_west.png");
/	        		arrowRight = readImage(
 * 						"http://harp.njit.edu/~zw56/image/arrow_east.png");        		
*/
	        	} catch (Exception e1) {System.out.println(e1);}
	        }
	        if (direction.equals(Direction.EAST)) {
	        	g.drawImage(arrowRight, x, y, x + w, y + h,
	        			-200, -200, 800, 800,
	        			null, null);
	        } else if (direction.equals(Direction.SOUTH)) {
	        	g.drawImage(arrowDown, x, y, x + w, y + h,
	        			-200, -200, 800, 800,
	        			null, null);
	        } else if (direction.equals(Direction.WEST)) {
	        	g.drawImage(arrowLeft, x, y, x + w, y + h,
	        			-200, -200, 800, 800,
	        			null, null);
	        } else if (direction.equals(Direction.NORTH)) {
	        	g.drawImage(arrowUp, x, y, x + w, y + h,
	        			-200, -200, 800, 800,
	        			null, null);
	        }
	        if (arrowUp == null) {
		        g.setColor(Color.green);
		        g.fillOval(x + w / 2 - 5, y + h / 2 - 5, 10, 10); 
	        }
	    }
  }
  
} 
