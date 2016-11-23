package scribble3; 

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Stroke extends Shape { 

  public Stroke() {} 
  
  public Stroke(Color color) {
    super(color); 
  } 

  public Stroke(Color color, int thickness) {
	    super(color);
	    this.thickness = thickness;
  }
  
  @SuppressWarnings("unchecked")
  public void addPoint(Point p) {
    if (p != null) { 
      points.add(p); 
    }
  }

  @SuppressWarnings("rawtypes")
  public List getPoints() { 
    return points; 
  }

  @SuppressWarnings("rawtypes")
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    Point prev = null; 
    Iterator iter = points.iterator(); 
    while (iter.hasNext()) { 
      Point cur = (Point) iter.next(); 
      if (prev != null) {
    
//	g.drawLine(prev.x, prev.y, cur.x, cur.y); 
      // -------- my added ---------
    	  Graphics2D g2 = (Graphics2D) g;
    	  g2.setStroke(new BasicStroke(thickness));
    	  g2.drawLine(prev.x, prev.y, cur.x, cur.y);
//    	  g.fillOval(prev.x, prev.y, thickness, thickness);
      }
      prev = cur; 
    }
  }

  // The list of points on the stroke
  // elements are instances of java.awt.Point 
  @SuppressWarnings("rawtypes")
  protected List points = new ArrayList();
  //protected int thickness;
}
