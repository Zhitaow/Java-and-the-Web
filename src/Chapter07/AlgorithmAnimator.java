package Chapter07;

@SuppressWarnings("serial")
public abstract class AlgorithmAnimator extends DBAnimationApplet {
	
	// hook method
	abstract protected void algorithm();
	
	// the template method
	public void run() {
		algorithm();
	}

	final protected void pause() {
		if (Thread.currentThread() == animationThread) {
			try {
				Thread.sleep(delay);
				repaint();
			} catch (InterruptedException e) {
				repaint();
				System.out.println(e);
			}
		}
	}
}
