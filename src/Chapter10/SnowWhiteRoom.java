package Chapter10;

import java.awt.*; 
//import java.applet.AudioClip; 

public class SnowWhiteRoom extends Room { 

  public static final Color ROOM_COLOR = new Color(255, 218, 185);
  public static final Color PLAYER_COLOR = Color.white;

  public SnowWhiteRoom(int roomNumber) { 
    super(roomNumber);
  }

  public void enter(Maze maze) {
    maze.setCurrentRoom(this);
//    gong.play();
    System.out.println("Snow White entered");
  }

  @Override
  public void draw(Graphics g, int x, int y, int w, int h) {
	System.out.println("Draw Snow White");
    g.setColor(ROOM_COLOR); 
    g.fillRect(x, y, w, h); 
//    if (inroom) { 
//      g.setColor(PLAYER_COLOR);
//      g.fillOval(x + w / 2 - 5, y + h / 2 - 5, 10, 10); 
//    }
    loadImage(g, x, y, w, h);
  }


}
