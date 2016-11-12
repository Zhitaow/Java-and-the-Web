package Chapter05;

import java.awt.Graphics;

/**
 * The Triangle class implements the Shape interface.
 * @author Zhitao Wang
 * @version 1.0
 */
public class Triangle implements Shape {
	private Point position1, position2, position3;
	private String name;
	
	/**
	 * This constructs a triangle instance with parameters position, sideLength1, 
	 * sideLength2 and the angle.
	 * @param position1 Point Class Object, specifying the corner of the triangle.
	 * @param position2 Point Class Object, specifying the corner of the triangle.
	 * @param position3 Point Class Object, specifying the corner of the triangle.
	 */
	public Triangle (Point pos1, Point pos2, Point pos3) {
		this.position1 = pos1;
		this.position2 = pos2;
		this.position3 = pos3;
		this.name = "Triangle";
	}
	
	/**
	 * This method returns the name field of the object.
	 * @return Name of this object.
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * This method return the information of a triangle instance.
	 * @return Information of this triangle's name, position, the two side length and angle.
	 */
	@Override
	public String toString() {
		String out = name + ": position1 (" + (position1.getXY())[0] +
			", " + (position1.getXY())[1] + "), position2 (" + 
			(position2.getXY())[0] + ", " + (position2.getXY())[1] +
			"), position3 ( " + (position3.getXY())[0] + ", " + 
			(position3.getXY())[1] + ")";
		return out;
	}
	
	/**
	 * This method draws this triangle to screen.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		int[] xy1 = position1.getXY();
		int[] xy2 = position2.getXY();
		int[] xy3 = position3.getXY();
		int[] xPoints = new int[] {xy1[0], xy2[0], xy3[0]};
		int[] yPoints = new int[] {xy1[1], xy2[1], xy3[1]};
		int nPoints = xPoints.length;
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	/**
	 * This method calculates the area of this instance of triangle.
	 * @return The area of this triangle.
	 */
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		int[] xy1 = position1.getXY();
		int[] xy2 = position2.getXY();
		int[] xy3 = position3.getXY();
		double area = 1/2.*Math.abs(xy1[0]*xy2[1]+
				xy2[0]*xy3[1]+xy3[0]*xy1[1]-
				xy1[0]*xy3[1]-xy2[0]*xy1[1] -xy3[0]*xy2[1]);
		return area;
	}

	/** This method return the corners of the triangle
	 *  @return the corners of the triangle
	 */
	public Point[] getPosition() {
		Point[] position = new Point[] {position1, position2, position3};
		return position;
	}
}

