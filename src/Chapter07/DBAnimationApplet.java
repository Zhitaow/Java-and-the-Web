package Chapter07;
import java.awt.*;
@SuppressWarnings("serial")
public abstract class DBAnimationApplet extends AnimationApplet {
	protected boolean doubleBuffered;
	protected Dimension d;
	protected Image im;
	protected Graphics offscreen;
	
	final public void update(Graphics g) {
		if (doubleBuffered) {
			paintFrame(offscreen);
			g.drawImage(im, 0, 0, this);
		} else {
			super.update(g);
		}
	}
	
	final public void paint(Graphics g) {
		paintFrame(g);
	}
	
	final public void init() {
		d = getSize();
		im = createImage(d.width, d.height);
		offscreen = im.getGraphics();
		initAnimator();
	}
	
	protected void initAnimator(){}
	
	abstract protected void paintFrame(Graphics g);
	
	protected DBAnimationApplet(boolean doubleBuffered) {
		this.doubleBuffered = doubleBuffered;
	}
	
	protected DBAnimationApplet() {
		this.doubleBuffered = true;
	}

}
