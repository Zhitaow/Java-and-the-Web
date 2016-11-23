package scribble1; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

@SuppressWarnings("serial")
public class Scribble extends JFrame {

  public Scribble() {
    setTitle("Scribble Pad");
    canvas = new ScribbleCanvas(); 
    canvas.setBackground(Color.white); 
    getContentPane().setLayout(new BorderLayout()); 
    getContentPane().add(canvas, BorderLayout.CENTER);
    addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
	  System.exit(0);
	}
      }); 
  }

  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    int width = 600; 
    int height = 400; 
    JFrame frame = new Scribble();
    frame.setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width/2 - width/2,
		      screenSize.height/2 - height/2);
    frame.show();
  }

  protected ScribbleCanvas canvas; 

}
