package MidtermAddition;

import java.awt.*;

public class Animator implements Runnable {  
  public Animator(Component comp) {
    this.comp = comp; 
  } 
  
  final public void setDelay(int delay) {
    this.delay = delay; 
  }
  
  final public int getDelay() {
    return delay; 
  }
  public void start() {
    animationThread = new Thread(this);
    animationThread.start();
  }
    
  public void stop() {
    animationThread = null; 
  }
  
  public void run() {
    while (Thread.currentThread() == animationThread) {
      try { 
        Thread.sleep(delay); 
      } catch (InterruptedException e){}     
      comp.repaint();
    }
  }
  
  protected Component comp; 
  /** The interval between two consecutive frames 
      in milliseconds */
  protected int delay = 100;     
  /** The animation thread */ 
  protected Thread animationThread;
  
}