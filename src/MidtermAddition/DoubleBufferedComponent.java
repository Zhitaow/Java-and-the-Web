package MidtermAddition;


import java.awt.*; 

public interface DoubleBufferedComponent extends java.awt.image.ImageObserver {
  void paintFrame(Graphics g); 
  Dimension getSize(); 
  Image createImage(int width, int height); 
}
