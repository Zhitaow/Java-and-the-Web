package Chapter07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class CSortDisplay implements SortDisplay {
	public int getArraySize(Dimension d) {
		return d.width / 3;
	}
	
	public void display(int[] a, Graphics g, Dimension d) {
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		double f = d.height / (double) a.length;
		double cf = 255.0 / (double) a.length;
		int x = d.width - 3;
		for (int i = a.length; --i >= 0; x -= 3) {
			g.setColor(new Color((int) (a[i] * cf / 1.5), 
					(int) (a[i] * cf), 0));
			g.fillRect(x, d.height - (int) (a[i] * f), 3, 
					(int) (a[i] * f));
		}
	}
	// 
	public void display(int[] a, int[] marker, Graphics g, Dimension d) {
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		double cf = 255.0 / (double) a.length;
		double xc = (double) d.width / 2.0;
		double yc = (double) d.height / 2.0;
		for (int i = 0; i < a.length; i ++) {
			if (i == marker[0]) {
				g.setColor(Color.red);
			} else if (i == marker[1]) {
				g.setColor(Color.blue); 
			} else if (i > marker[0] && i < marker[1]) {
				g.setColor(Color.orange);
			} else {
				g.setColor(new Color((int) (a[i] * cf / 1.5), 
						(int) (a[i] * cf), 0));
			}
			Graphics2D g2d = (Graphics2D) g;
			double hourDiv = (double) i / a.length;
			double[] xy = xyCoordinate(xc, yc, 1.5 * a[i], hourDiv);
			g2d.draw(new Line2D.Double(xc, yc, xy[0], xy[1]));
		}
	}
		
	// convert the portion of 360 degree to Cartesian coordinate
    private double[] xyCoordinate(double xc, double yc, double radius, double ndiv) {
        double[] xy = new double[6];
        double theta =  ndiv * Math.PI * 2.0;
        // convert angle to the corresponding x, y position in display
        xy[0] = xc + radius * Math.sin(theta);
        xy[1] = yc - radius * Math.cos(theta);
        return xy;
    }
}