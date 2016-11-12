package Chapter05;

import java.awt.Graphics;

/**
 * The Circle class implements the Shape interface.
 * @author Zhitao Wang
 * @version 1.0
 */
public class Circle implements Shape {
	private Point center;
	private int radius;
	private String name;
	/**
	 * This constructs a Circle instance with parameters center, radius.
	 * @param center Point Class Object, specifying the center of the circle
	 * @param radius Integer type, specifying the radius of the circle
	 * @exception IllegalArgumentException if radius is non-positive.
	 */
	public Circle (Point center, int radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException("Radius must be greater than zero!"); 
		} else {
			this.center = center;
			this.radius = radius;
			this.name = "Circle";
		}
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
	 * This method return the information of a circle instance.
	 * @return Information of this circle's name, center and radius.
	 */
	@Override
	public String toString() {
		String out = name + ": center (" + (center.getXY())[0] +
			", " + (center.getXY())[1] + "), radius " + radius;
		return out;
	}
	/**
	 * This method draws this circle to screen.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		// This pseudo-code represents drawing a circle.
		//Graphics2D g2d = (Graphics2D) g;
		int[] xy = center.getXY();
		g.drawOval(xy[0], xy[1], radius, radius);
	}
	/**
	 * This method calculates the area of this instance of circle.
	 * @return The area of this circle.
	 */
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return Math.PI * radius * radius;
	}
	
	/**
	 * This method return the center of circle
	 * @return A point object of the center 
	 */
	public Point getCenter() {
		return center;
	}
	
	/**
	 * This method return the radius of circle
	 * @return the radius of circle
	 */
	public int getRadius() {
		return radius;
	}

}
