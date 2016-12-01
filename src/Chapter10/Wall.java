package Chapter10;

import java.applet.AudioClip;
import java.awt.*;

public class Wall implements MapSite { 

  public static final Color WALL_COLOR = Color.orange;

  public Object clone() throws CloneNotSupportedException { 
    return super.clone();
  }

  public void enter(Maze maze) {
    hurts.play();
  }

  public void draw(Graphics g, int x, int y, int w, int h) {
    g.setColor(WALL_COLOR); 
    g.fillRect(x, y, w, h); 
  }
  
  protected static AudioClip hurts = 
		  AudioUtility.getAudioClip(
				  ResourceLoader.class.getResource(
						  "/audio/minion_tadaa.au")); 
}
