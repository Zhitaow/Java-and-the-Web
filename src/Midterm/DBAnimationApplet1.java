package Midterm;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class DBAnimationApplet1
    extends AnimationApplet1 {

  public void update(Graphics g) {
    if (doubleBuffered) {
 	
      paintFrame(offscreen);
      g.drawImage(im, 0, 0, this);
    } else {
      super.update(g);
    }
  }

  final public void paint(Graphics g) {
    paintFrame(g);
  }

  final public void init() {
	setSize(xSize, ySize);
    d = getSize();
    im = createImage(d.width, d.height);
    offscreen = im.getGraphics();
    initAnimator();
  } 
  
  protected void initAnimator() {}

  abstract protected void paintFrame(Graphics g);

  protected DBAnimationApplet1(boolean doubleBuffered) {
    this.doubleBuffered = doubleBuffered;
  }

  protected DBAnimationApplet1() {
    this.doubleBuffered = true;
  }

  protected boolean doubleBuffered;
  protected Dimension d;
  protected Image im;
  protected Graphics offscreen;

}
