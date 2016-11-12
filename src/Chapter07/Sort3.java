package Chapter07;
import java.awt.*;
@SuppressWarnings("serial")
public class Sort3 extends Sort2 {
	protected SortDisplay theDisplay;
	protected SortDisplayFactory displayFactory;
	//
	protected String att = "horizontal";
	
	protected void initAnimator() {
		//String att = getParameter("dis");
		displayFactory = new StaticSortDisplayFactory();
		theDisplay = displayFactory.makeSortDisplay(att);
		super.initAnimator();
	}
	
	protected void scramble() {
		int n = theDisplay.getArraySize(getSize());
		arr = new int[n];
		for (int i = arr.length; --i >= 0;) {
			arr[i] = i;
		}
		for (int i = arr.length; --i >= 0;) {
			int j = (int) (i * Math.random());
			SortAlgorithm.swap(arr, i, j);
		}
	}
	
	protected void paintFrame(Graphics g) {
		//theDisplay.display(arr, g, getSize());
		theDisplay.display(arr, marker, g, getSize());
	}
	
	protected void setDisplay(String att) {
		this.att = att;
	}
}
