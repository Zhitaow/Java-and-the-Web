package Chapter05;

import java.awt.Graphics;

/**
 * The Rectangle class implements the Shape interface.
 * @author Zhitao Wang
 * @version 1.0
 */
public class Rectangle implements Shape {
	private Point position;
	private int width, height;
	private String name;
	
	/**
	 * This constructs a rectangle instance with parameters position, width and height.
	 * @param position Point Class Object, specifying the top-left corner of the rectangle.
	 * @param width Integer type, specifying the width (in pixel unit) of the rectangle.
	 * @param height Integer type, specifying the height (in pixel unit) of the rectangle.
	 * @exception IllegalArgumentException if width or height is non-positive.
	 */
	public Rectangle (Point position, int width, int height) {
		if (width <= 0 ||  height <= 0) {
			throw new IllegalArgumentException("Width and Height must be positive value!"); 
		} else {
			this.position = position;
			this.width = width;
			this.height = height;
			name = "Rectangle";
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
	 * This method return the information of a rectangle instance.
	 * @return Information of this rectangle's name, position, width and height.
	 */
	@Override
	public String toString() {
		String out = name + ": position (" + (position.getXY())[0] +
				", " + (position.getXY())[1] + "), width " +
				width + ", height " + height;
		return out;
	}
	
	/**
	 * This method draws this rectangle to screen.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		// This pseudo-code represents drawing a rectangle.
		int[] xy = position.getXY();
		g.drawRect(xy[0], xy[1], width, height);
	}

	/**
	 * This method calculates the area of this instance of rectangle.
	 * @return The area of this rectangle.
	 */
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return width * height;
	}
	
	/** This method return the corner of the rectangle
	 *  @return corner of the rectangle
	 */
	public Point getPosition() {
		return position;
	}
	
	/** This method return the sides of the rectangle
	 *  @return the sides of the rectangle
	 */
	public int[] getSides() {
		return new int[] {width, height};
	}
	
}
