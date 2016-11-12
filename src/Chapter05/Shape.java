package Chapter05;

import java.awt.Graphics;

/**
 * The Shape interface describes general methods for classes which implement it.
 * @author Zhitao Wang
 * @version 1.0
 */
public interface Shape {
	/**
	 * This method returns information of this instance.
	 * @return the information of this instance.
	 */
    public String toString();
	/**
	 * This method returns the name field of the object.
	 * @return name of this object
	 */
    public String getName();
	/**
	 * This method draws an instance of object to screen.
	 */
    public void draw(Graphics g);
	/**
	 * This method returns the area of an instance of object.
	 * @return the area of this instance.
	 */
    public double getArea();
}

