package scribble2; 

import java.awt.*;
import java.awt.event.*;

public class ScribbleCanvasListener 
    implements MouseListener, MouseMotionListener {

  public ScribbleCanvasListener(ScribbleCanvas canvas) {
    this.canvas = canvas; 
  }

  public void mousePressed(MouseEvent e) {
    Point p = e.getPoint(); 
    canvas.mouseButtonDown = true;
    canvas.startStroke(p);    
    canvas.x = p.x; 
    canvas.y = p.y;    
  } 

  public void mouseReleased(MouseEvent e) {
    Point p = e.getPoint(); 
    canvas.endStroke(p);       
    canvas.mouseButtonDown = false;       
  }    

  public void mouseDragged(MouseEvent e) {
    Point p = e.getPoint(); 
    if (canvas.mouseButtonDown) {
      canvas.addPointToStroke(p);
      Graphics g = canvas.getGraphics();
      g.setColor(canvas.getCurColor());
      g.drawLine(canvas.x, canvas.y, p.x, p.y); 
      canvas.x = p.x; 
      canvas.y = p.y; 
    }       
  }

  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}  
  public void mouseExited(MouseEvent e) {}
  public void mouseMoved(MouseEvent e) {}     

  protected ScribbleCanvas canvas; 

}

