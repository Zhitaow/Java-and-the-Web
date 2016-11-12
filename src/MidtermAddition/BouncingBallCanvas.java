package MidtermAddition;

import java.awt.*;

@SuppressWarnings("serial")
public class BouncingBallCanvas extends Canvas implements DoubleBufferedComponent {

  public BouncingBallCanvas() {
    dbhandler = new DoubleBufferHandler(this); 
  }

  public void initCanvas() {
    d = getSize(); 
    x = d.width * 2 / 3 ;
    y = d.height - radius;
  }

  public void update(Graphics g) {
    dbhandler.update(g); 
  } 

  public void paint(Graphics g) {
    update(g); 
  } 

  public void paintFrame(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(0, 0, d.width, d.height); 
    if (x < radius || x > d.width - radius)
      dx = -dx; 
    if (y < radius || y > d.height - radius)
      dy = -dy; 
    x += dx; y += dy; 
    g.setColor(ballcolor);
    g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
  } 

  public void setBallColor(Color c) {
    ballcolor = c; 
  }

  public void setBallPosition(int x, int y) {
    this.x = x; this.y = y; 
  }

  protected int x, y, dx = -2, dy = -4, radius = 20; 
  protected Color ballcolor = Color.red;
  protected Dimension d;  
  protected DoubleBufferHandler dbhandler; 
}
