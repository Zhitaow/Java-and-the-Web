package draw2; 

import java.awt.*; 
import draw1.*;

@SuppressWarnings("serial")
public class FilledRectangleShape extends RectangleShape {

  public FilledRectangleShape() {
	  super();
  }
  public FilledRectangleShape(Color c) {
	  super();
	  color = c;
	  this.setColor(c);
  }

  public void draw(Graphics g) {
    int x = Math.min(x1, x2); 
    int y = Math.min(y1, y2); 
    int w = Math.abs(x1 - x2) + 1; 
    int h = Math.abs(y1 - y2) + 1;
    if (color != null) {
      g.setColor(color);
    }
    g.fillRect(x, y, w, h);
  }
}
