package myprog.single;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TransparentRectangles extends JPanel {

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    for (int i = 1; i <= 10; i++) {
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i * 0.1f));
      g2d.fillRect(50 * i, 20, 50, 50);
    }
  }

  public static void main(String[] args) {

    JFrame frame = new JFrame("Transparent Rectangles");
    frame.add(new TransparentRectangles());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 100);
    frame.setVisible(true);
  }
}

   