package Chapter07;

@SuppressWarnings("serial")
public class AnimationApplet extends java.applet.Applet implements java.lang.Runnable {
	protected Thread animationThread;
	protected int delay = 1; //100;
	
	@Override
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}
	
	@Override
	public void stop() {
		animationThread = null;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Thread.currentThread() == animationThread) {
			try {
				//repaint();
				Thread.currentThread().sleep(delay);
			} catch (InterruptedException e) {
				repaint();
				System.out.println(e);
			}
		}
	}
		
	final public void setDelay(int delay) {
		this.delay = delay;
	}
	
	final public int getDelay() {
		return delay;
	}
}
