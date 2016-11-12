package Chapter07;

import java.awt.*;

@SuppressWarnings("serial")
public class Sort2 extends AlgorithmAnimator {
	protected SortAlgorithm theAlgorithm;
	protected AlgorithmFactory algorithmFactory;
	protected int arr[];
	protected String algName = "BubbleSort";
	// 
	protected int[] marker = {0, 0};
	
	protected void initAnimator() {
//		algName = "BubbleSort";
		//String at = getParameter("alg");
//		if (at != null) {
//			algName = at;
//		}
		algorithmFactory = new StaticAlgoFactory(this);
		theAlgorithm = algorithmFactory.makeSortAlgorithm(algName);
		setDelay(20);
		scramble();
	}
	
	protected void algorithm() {
		if (theAlgorithm != null) {
			//theAlgorithm.sort(arr);
			theAlgorithm.sort(arr, marker);
		}
	}
	
	protected void scramble() {
		arr = new int[getSize().height / 2];
		for (int i = arr.length; --i >= 0;) {
			arr[i] = i;
		}
		for (int i = arr.length; --i >= 0;) {
			int j = (int) (i * Math.random());
			SortAlgorithm.swap(arr, i, j);
		}
	}
	
	protected void paintFrame(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.black);
		int y = d.height - 1;
		double f = d.width / (double) arr.length;
		for (int i = arr.length; --i >= 0; y -= 2) {
			g.drawLine(0, y, (int)(arr[i] * f), y);
		}
	}
	
	// set Algorithm name
	protected void setAlgorithm(String algName) {
		this.algName = algName;
	}
}
