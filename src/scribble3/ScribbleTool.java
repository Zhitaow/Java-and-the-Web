package scribble3; 

import java.awt.*;

public class ScribbleTool extends AbstractTool { 

  public ScribbleTool(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  public void startShape(Point p) {
    curStroke = new Stroke(color, thickness);//(canvas.getCurColor()); 
    curStroke.addPoint(p); 
  }

  public void addPointToShape(Point p) {
    if (curStroke != null) { 
      curStroke.addPoint(p); 
      Graphics g = canvas.getGraphics();
      g.setColor(canvas.getCurColor());
//      g.setColor(Color.white);
//      g.fillOval(canvas.x, canvas.y, thickness, thickness);
      Graphics2D g2 = (Graphics2D) g;
	  g2.setStroke(new BasicStroke(thickness));
	  g2.setColor(color);
	  g2.drawLine(canvas.x, canvas.y, p.x, p.y);
//      g.drawLine(canvas.x, canvas.y, p.x, p.y); 
    }
  }

  public void endShape(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p); 
      canvas.addShape(curStroke); 
      curStroke = null; 
    }
  }

  protected Stroke curStroke = null; 

}
