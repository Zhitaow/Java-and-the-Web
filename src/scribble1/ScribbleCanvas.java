package scribble1; 

//import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import javax.swing.*; 

@SuppressWarnings("serial")
public class ScribbleCanvas extends JPanel { 

  public ScribbleCanvas() { 
    listener = new ScribbleCanvasListener(this); 
    addMouseListener((MouseListener) listener); 
    addMouseMotionListener((MouseMotionListener) listener); 
  }

  protected EventListener listener;
  protected boolean mouseButtonDown = false; 
  protected int x, y; 

}

