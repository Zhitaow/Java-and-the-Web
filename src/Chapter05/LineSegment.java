package Chapter05;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * The LineSegment class implements the Shape interface.
 * @author Zhitao Wang
 * @version 1.0
 */
public class LineSegment implements Shape {
	private Point startPoint, endPoint;
	private String name;
	
	/**
	 * This constructs a line segment instance with parameters startPoint and endPoint.
	 * @param startPoint Point Class Object, specifying starting coordinate of the line.
	 * @param endPoint Point Class Object, specifying ending coordinate of the line.
	 */
	public LineSegment (Point startPoint, Point endPoint) {
		this.name = "LineSegment";
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	/**
	 * This method return the information of a line segment instance.
	 * @return Information of this line segment's name, start point and end point.
	 */
	@Override
	public String toString() {
		String out = name + ": start point (" + (startPoint.getXY())[0] +
				", " + (startPoint.getXY())[1] + "), end point (" +
				(endPoint.getXY())[0] + ", " + (endPoint.getXY())[1] + ")";
		return out;
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
	 * This method draws this line segment to screen.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int[] xy0 = startPoint.getXY();
		int[] xy1 = endPoint.getXY();
		g2d.draw(new Line2D.Double(xy0[0], xy0[1], xy1[0], xy1[1]));
	}

	/**
	 * This method prints warning message that area is not applicable to the line segment object.
	 * @return An integer 0, representing line segment does not have an area.
	 */
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		System.out.println("Line segment does not have an area!");
		return 0;
	}
	
	/** This method return the start point of the line
	 *  @return the start point of the line
	 */
	public Point getStartPoint() {
		return startPoint;
	}
	
	/** This method return the end point of the line
	 *  @return the end point of the line
	 */
	public Point getEndPoint() {
		return endPoint;
	}
}
