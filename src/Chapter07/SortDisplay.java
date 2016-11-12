package Chapter07;
import java.awt.*;

public interface SortDisplay {
	public int getArraySize(Dimension d);
	public void display(int[] a, Graphics g, Dimension d);
	//
	public void display(int[] a, int[] marker, Graphics g, Dimension d);
}
