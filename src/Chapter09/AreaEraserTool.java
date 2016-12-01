package Chapter09;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import scribble3.ScribbleCanvas;
import draw1.TwoEndsShape;
import draw2.TwoEndsShapeTool;

public class AreaEraserTool extends TwoEndsShapeTool {

	private Color filledColor = Color.white;

	public AreaEraserTool(ScribbleCanvas canvas, String name,
			TwoEndsShape prototype) {
		super(canvas, name, prototype);
		// TODO Auto-generated constructor stub
	}
	
	public void setFilledColor(Color c) {
		filledColor  = c;
	}
	
	public Color getFilledColor() {
		return filledColor;
	}
	
	  public void endShape(Point p) {
		    canvas.mouseButtonDown = false; 
		    if (prototype != null) { 
		      try { 
				TwoEndsShape newShape = (TwoEndsShape) prototype.clone(); 
//				------ my edited line ----------
//				newShape.setColor(prototype.getColor());
				newShape.setColor(filledColor);
//		      --------------------------------
				newShape.setEnds(xStart, yStart, p.x, p.y); 
				canvas.addShape(newShape);
		      } catch (CloneNotSupportedException e) {}
		      Graphics g = canvas.getGraphics();
		      g.setPaintMode();
		      canvas.repaint();
		    }
	  }
}
