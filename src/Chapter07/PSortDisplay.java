package Chapter07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class PSortDisplay implements SortDisplay {
	public int getArraySize(Dimension d) {
		return d.height / 2;
	}
	
	public void display(int[] a, Graphics g, Dimension d) {
		double f = d.width / (double) a.length;
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		int y = d.height - 1;
		g.setColor(Color.black);
		y = d.height - 1;
		for (int i = a.length; --i >= 0; y -=2) {
			g.drawLine(0, y, (int) (a[i] * f), y);
		}
	}
	
	//
	public void display(int[] a, int[] marker, Graphics g, Dimension d) {
		double f = d.width / (double) a.length;
		double cf = 255.0 / (double) a.length;
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		int y = d.height - 3;
		y = d.height - 1;
		for (int i = a.length; --i >= 0; y -=2) {
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
			g.drawLine(d.width / 2, y, d.width / 2 + 
					(int) (a[i] * f) / 2, y);
			g.drawLine(d.width / 2, y, d.width / 2 - 
					(int) (a[i] * f) / 2, y);
		}
	}
}