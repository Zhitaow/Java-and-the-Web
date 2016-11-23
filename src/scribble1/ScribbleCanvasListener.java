

package scribble1; 

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
    canvas.x = p.x; 
    canvas.y = p.y;       
  } 

  public void mouseReleased(MouseEvent e) {
    canvas.mouseButtonDown = false;       
  }    

  public void mouseDragged(MouseEvent e) {
    Point p = e.getPoint(); 
    if (canvas.mouseButtonDown) {
      canvas.getGraphics().drawLine(canvas.x, canvas.y, p.x, p.y); 
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

