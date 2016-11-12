package Chapter07;
import java.awt.*;
public class VSortDisplay implements SortDisplay {
	public int getArraySize(Dimension d) {
		return d.width / 2;
	}
	
	public void display(int[] a, Graphics g, Dimension d) {
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		int x = d.width - 1;
		double f = d.height / (double) a.length;
		g.setColor(Color.black);
		for (int i = a.length; --i >= 0; x -= 2) {
			g.drawLine(x, 0, x, (int) (a[i] * f));
		}
	}
	// my version
	public void display(int[] a, int[] marker, Graphics g, Dimension d) {
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		int x = d.width - 3;
		double f = d.height / (double) a.length;
		for (int i = a.length; --i >= 0; x -= 2) {
			if (i == marker[0]) {
				g.setColor(Color.red);
			} else if (i == marker[1]) {
				g.setColor(Color.blue); 
			} else if (i > marker[0] && i < marker[1]) {
				g.setColor(Color.orange);
			} else {
				g.setColor(Color.black);
			}
			g.drawLine(x, 0, x, (int) (a[i] * f));
		}
	}
}
