package Chapter09;

import java.awt.Graphics;

@SuppressWarnings("serial")
public class FilledTriangleShape extends TriangleShape {
	  public void draw(Graphics g) {
		    int xMid = (x1 + x2) / 2;
		    int yTop = Math.min(y1, y2);
		    int yBot = Math.max(y1, y2);
		    int xLeft = Math.min(x1, x2);
		    int xRight = Math.max(x1, x2);
		    if (color != null)
		      g.setColor(color);
		    int[] xPoints = {xMid, xRight, xLeft};
		    int[] yPoints = {yTop, yBot, yBot};
		    g.fillPolygon(xPoints, yPoints, 3);
		  }
}
