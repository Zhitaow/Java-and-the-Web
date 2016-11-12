package Chapter05;
/**
 * @author Zhitao Wang
 * @version 1.0
 */
public class Point {
	private int x, y;
	/**
	 * This constructs a point instance with parameters x and y.
	 * @param x the x coordinate of the point.
	 * @param y the y coordinate of the point.
	 * @exception IllegalArgumentException if x or y is negative.
	 */
	public Point (int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x and y must be positive value in the canvas!"); 
		} else {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * This method return the coordinates of the Point instance.
	 * @return An integer array with x and y components.
	 */
	public int[] getXY() {
		return new int[]{x, y};
	}
}

