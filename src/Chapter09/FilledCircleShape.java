package Chapter09;

import java.awt.*; 

@SuppressWarnings("serial")
public class FilledCircleShape extends CircleShape {


	public void draw(Graphics g) {
		int x = Math.min(x1, x2); 
		int y = Math.min(y1, y2); 
	    int w = Math.abs(x1 - x2) + 1;     
	    if (color != null) {
	      g.setColor(color);
	    }
	    g.fillOval(x, y, w, w);
	}
} 