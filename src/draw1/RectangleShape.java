package draw1; 

import java.awt.*; 

@SuppressWarnings("serial")
public class RectangleShape extends TwoEndsShape {

  public void draw(Graphics g) {
    int x = Math.min(x1, x2); 
    int y = Math.min(y1, y2); 
    int w = Math.abs(x1 - x2) + 1; 
    int h = Math.abs(y1 - y2) + 1;     
    if (color != null) {
      g.setColor(color);
      System.out.println("not null "+ color);
    }
    System.out.println("null "+ color);
    g.drawRect(x, y, w, h);
  }

  public void drawOutline(Graphics g, int x1, int y1, int x2, int y2) {
    int x = Math.min(x1, x2); 
    int y = Math.min(y1, y2); 
    int w = Math.abs(x1 - x2) + 1; 
    int h = Math.abs(y1 - y2) + 1;   
//    System.out.println("null "+ color);
    g.drawRect(x, y, w, h);
  }

}
