package Chapter10;

import java.awt.*; 
//import java.applet.AudioClip;

public class HarryPotterRoom extends Room { 

  public static final Color ROOM_COLOR = new Color(85, 107, 47);
  public static final Color PLAYER_COLOR = Color.black;
  
  public HarryPotterRoom(int roomNumber) { 
    super(roomNumber);
  }

  public void enter(Maze maze) {
    maze.setCurrentRoom(this);
//    gong.play();
  }

  public void draw(Graphics g, int x, int y, int w, int h) {
    g.setColor(ROOM_COLOR); 
    g.fillRect(x, y, w, h);
    System.out.println("Draw Harry Potter");
    loadImage(g, x, y, w, h);
//    if (inroom) { 
//      g.setColor(PLAYER_COLOR);
//      g.fillOval(x + w / 2 - 5, y + h / 2 - 5, 10, 10); 
//    }
  }

//  protected static AudioClip adapt = AudioUtility.getAudioClip("audio/adapt-or-die.au");

}
