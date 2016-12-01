package Chapter09;
import java.awt.*;
import draw1.RectangleShape;

public class FilledRectangleRoundShape extends RectangleShape {

    private static final long serialVersionUID = 1L;

    public void draw(Graphics g) {
      int x = Math.min(x1, x2); 
      int y = Math.min(y1, y2); 
      int w = Math.abs(x1 - x2) + 1; 
      int h = Math.abs(y1 - y2) + 1;
      int cw = (int)(0.5*w);
      int ch = (int)(0.5*h);
      if (color != null) {
        g.setColor(color);
      }
      g.fillRoundRect(x, y, w, h,cw,ch);
    }

    public void drawOutline(Graphics g, int x1, int y1, int x2, int y2) {
      int x = Math.min(x1, x2); 
      int y = Math.min(y1, y2); 
      int w = Math.abs(x1 - x2) + 1; 
      int h = Math.abs(y1 - y2) + 1;
      int cw = (int)(0.5*w);
      int ch = (int)(0.5*h);
      g.fillRoundRect(x, y, w, h,cw,ch);
    }
  
}