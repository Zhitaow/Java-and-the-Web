package Chapter09;

import java.awt.Graphics;

import draw1.TwoEndsShape;

@SuppressWarnings("serial")
public class CircleShape extends TwoEndsShape {

    public void draw(Graphics g) {
	    int x = Math.min(x1, x2); 
	    int y = Math.min(y1, y2); 
	    int w = Math.abs(x1 - x2) + 1;     
	    if (color != null) {
	      g.setColor(color);
	    }
	    g.drawOval(x, y, w, w);
    }

  public void drawOutline(Graphics g, int x1, int y1, int x2, int y2) {
    int x = Math.min(x1, x2); 
    int y = Math.min(y1, y2); 
    int w = Math.abs(x1 - x2) + 1;     
    g.drawOval(x, y, w, w);
  }
  
}