package Chapter07;

import java.awt.*;

public class HSortDisplay implements SortDisplay {
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
	
	// my version
	public void display(int[] a, int[] marker, Graphics g, Dimension d) {
		double f = d.width / (double) a.length;
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
				g.setColor(Color.black);
			}
			g.drawLine(0, y, (int) (a[i] * f), y);
		}
	}
}
