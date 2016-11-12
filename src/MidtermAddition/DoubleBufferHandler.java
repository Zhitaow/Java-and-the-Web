package MidtermAddition;
import java.awt.*; 

public class DoubleBufferHandler {
	public DoubleBufferHandler(DoubleBufferedComponent comp) {
    this.comp = comp; 
  }

  final public void update(Graphics g) {
    if (im == null) {
      d = comp.getSize(); 
      im = comp.createImage(d.width, d.height);
      offscreen = im.getGraphics();
    }
    comp.paintFrame(offscreen); 
    g.drawImage(im, 0, 0, comp);
  }

  DoubleBufferedComponent comp; 

  /** The size of the viewing area */ 
  protected Dimension d;         
  
  /** The off-screen image */
  protected Image im;
  
  /** The off-screen graphics */
  protected Graphics offscreen;
   
}
