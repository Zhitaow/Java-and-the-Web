package Chapter09;

import java.awt.Graphics;

import draw1.TwoEndsShape;

@SuppressWarnings("serial")
public class TriangleShape extends TwoEndsShape {
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
		    g.drawPolygon(xPoints, yPoints, 3);
		  }

		  public void drawOutline(Graphics g, int x1, int y1, int x2, int y2) {
		    int xMid = (x1 + x2) / 2;
		    int yTop = Math.min(y1, y2);
		    int yBot = Math.max(y1, y2);
		    int xLeft = Math.min(x1, x2);
		    int xRight = Math.max(x1, x2);
		    int[] xPoints = { xMid, xRight, xLeft };
		    int[] yPoints = { yTop, yBot, yBot};
		    g.drawPolygon(xPoints, yPoints, 3);
		  }

}
