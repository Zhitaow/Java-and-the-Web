package Midterm;

import java.awt.*;
@SuppressWarnings("serial")
public class AnimationApplet1 extends java.applet.Applet implements java.lang.Runnable {
  
  int xSize = 600, ySize = 400;
//  boolean setPower = false;

  public void start() {
    animationThread = new Thread(this);
    animationThread.start();
  }

  public void stop() {
    animationThread = null;
  }

  @SuppressWarnings("static-access")
public void run() {
    while (Thread.currentThread() == animationThread) {
      try {
        Thread.currentThread().sleep(delay);
      } catch (InterruptedException e) {}
      repaint();
    }
  }

  final public void setDelay(int delay) {
    this.delay = delay;
  }

  final public int getDelay() {
    return delay;
  }

  protected Thread animationThread;
  protected int delay = 100;
}



