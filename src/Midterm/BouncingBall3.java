package Midterm;


import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Janani Swaminathan
 * @version 1.0
 * @since 10/27/2012
 * @param color
 *            Sets the color of the ball
 * @param radius
 *            Sets the radius of the ball
 * @param x
 *            , y indicates the position of axis / coordinates
 * @param image
 *            Image name
 * @param offscreen
 *            Refers to the Graphics
 * @param d
 *            sets the dimension of the application area
 * @param Cir1X
 *            , Cir1Y indicates the position of axis / coordinates for object
 *            Circle1
 * @param Cir1X1
 *            , Cir1Y1 indicates the position of axis / coordinates for object
 *            Circle2
 */

public class BouncingBall3 extends DBAnimationApplet1 {
	protected int x, y;
	protected int dx = -2, dy = -4;
	protected int radius = 10;
	protected int CirRad1 = 30;
	protected int Cir1X, Cir1Y;
	protected Color color = Color.BLUE;
	protected boolean FlagDrain = false;
	protected boolean FlagPlayed = false;
	protected int CirRad11 = 30;
	protected int Cir1X1, Cir1Y1;

	protected boolean BallOut = false;
	static final int SPEED_MIN = 0;
	static final int SPEED_MAX = 10;
	static final int SPEED_INIT = 1;
	protected MediaTracker mt = new MediaTracker(this);
	protected Image background;
	protected URL base;

	protected AudioClip BouncingSound;

	protected AudioClip DrainSound;
	protected AudioClip InjectSound;

	protected JSlider speedctl = new JSlider(JSlider.HORIZONTAL, SPEED_MIN,
			SPEED_MAX, SPEED_INIT);
	protected Panel controlPanel1;
	protected Panel controlPanel2;
	protected Panel controlPanel3;
	protected Label label1;
	protected String shape = "Circle";
	protected Choice Speedlist;
	protected Choice ShapeChoice;
	protected Button injectButton = new Button("Inject");
	protected Button stopButton = new Button("Stop");
	protected Button exitButton = new Button("Exit");
	protected Button drainButton = new Button("Drain");
	protected Button startButton = new Button("Continue");

	/**
	 * Constructor method calling the super class
	 */
	public BouncingBall3() {
		super(true);
	}

	/**
	 * Method to draw the panel and all the elements of the panel This method
	 * also defines the size of the Applet layout
	 */

	protected void initAnimator() {
		String att = getParameter("delay");
		if (att != null)
			setDelay(Integer.parseInt(att));

		setLayout(new FlowLayout());
		controlPanel1 = new Panel();
		controlPanel2 = new Panel();
		controlPanel3 = new Panel();

		label1 = new Label("Choose the speed of the ball");
		add(controlPanel1);
		add(controlPanel2);
		add(controlPanel3);
		ShapeChoice = new Choice();
		ShapeChoice.addItem("Circle");
		ShapeChoice.addItem("Square");
		Speedlist = new Choice();
		Speedlist.addItem("1");
		Speedlist.addItem("2");
		Speedlist.addItem("3");
		Speedlist.addItem("4");
		Speedlist.addItem("5");
		Speedlist.addItem("6");
		Speedlist.addItem("7");
		Speedlist.addItem("8");
		Speedlist.addItem("9");
		Speedlist.addItem("10");

		controlPanel1.add(injectButton);

		controlPanel2.add(label1);
		controlPanel2.add(Speedlist);

		Speedlist.addItemListener(new SpeedlistHandler());

		controlPanel1.setLayout(new GridLayout(1, 3));
		controlPanel2.setLayout(new GridLayout(1, 2));
		speedctl.addChangeListener(new SpeedListener());
		injectButton.addActionListener(new ButtonListener(ButtonListener.INJECT));

		radius = 10;
		x = d.width * 2 / 3;
		y = d.height - radius;

		base = getCodeBase();
		background = getImage(base, "bg.jpg");
		mt.addImage(background, 1);

	}

/**
 * Method to redraw the position of the ball based on its position and 
 * to reposition based on the reflection of the wall or while dropping into the hole 
 */
	protected void paintFrame(Graphics g) {
		// g.setColor(Color.white);
		// g.fillRect(0,0,d.width,d.height);
		g.drawImage(background, 0, 0, this);
		g.setColor(Color.blue);
		g.fillRect(0, 0, d.height, 3);
		g.fillRect(0, 0, 3, d.height);
		g.fillRect(d.width - 3, 0, 3, d.height);
		g.fillRect(0, d.height - 3, d.width, 3);
		Cir1X = d.width / 3;
		Cir1Y = d.height / 3;
		g.setColor(Color.black);

		Cir1X1 = d.width / 5;
		Cir1Y1 = d.height / 5;
		g.setColor(Color.green);

		g.fillOval(Cir1X - CirRad1, Cir1Y - CirRad1, CirRad1 * 2, CirRad1 * 2);

		g.setColor(Color.white);
		g.fillOval(Cir1X1 - CirRad11, Cir1Y1 - CirRad11, CirRad11 * 2,
				CirRad11 * 2);

		if (FlagDrain == false) {
			g.setColor(Color.white);
			g.fillRect(d.width / 2 - 75, d.height - 4, 150, 4);
			if ((x > (d.width / 2 - 75) && x < (d.width / 2 + 75))
					&& (y > d.height - radius)) {
				BallOut = true;
				if (!FlagPlayed) {
					DrainSound.play();
					FlagPlayed = true;
				}
			}

		}

		g.fillRect(d.width / 2 - 75, d.height - 4, 150, 4);
		g.setColor(color);
		if (BallOut == false) {
			if (((x - Cir1X) * (x - Cir1X) + (y - Cir1Y) * (y - Cir1Y)) <= ((CirRad1 + radius) * (CirRad1 + radius))) {
				int angle_flag1, angle_flag2;
				angle_flag1 = (int) (d.width / 3 - 0.7 * CirRad1);
				angle_flag2 = (int) (d.width / 3 + 0.7 * CirRad1);
				if (x > angle_flag2) {
					dx = -dx;
					x += radius / 2;
				} else if (x < angle_flag1) {
					dx = -dx;
					x -= radius / 2;
				} else if (y > Cir1Y) {
					dy = -dy;
					y += radius / 2;
				} else {
					dy = -dy;
					y -= radius / 2;
				}
				// BouncingSound.play();

			}

			if (((x - Cir1X1) * (x - Cir1X1) + (y - Cir1Y1) * (y - Cir1Y1)) <= ((CirRad11 + radius) * (CirRad11 + radius))) {
				int angle_flag1, angle_flag2;
				angle_flag1 = (int) (d.width / 5 - 0.7 * CirRad1);
				angle_flag2 = (int) (d.width / 5 + 0.7 * CirRad1);
				if (x > angle_flag2) {
					dx = -dx;
					x += radius / 2;
				} else if (x < angle_flag1) {
					dx = -dx;
					x -= radius / 2;
				} else if (y > Cir1Y) {
					dy = -dy;
					y += radius / 2;
				} else {
					dy = -dy;
					y -= radius / 2;
				}
				// BouncingSound.play();

			}
			if (x < radius || x > d.width - radius) {
				dx = -dx;
				// BouncingSound.play();
			}
			if (y < radius || y > d.height - radius) {
				dy = -dy;
				// BouncingSound.play();
			}
			x += dx;
			y += dy;

			g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		}
	}

	/** 
	 * Method the inject back the ball to the palying area
	 */
	public void inject() {
		if (BallOut) {
			radius = 10;
			x = d.width * 2 / 3;
			y = d.height - radius;
			dx = -2;
			dy = -4;

			FlagPlayed = false;
			BallOut = false;
			speedctl.setValue(SPEED_INIT);
			InjectSound.play();
		}
	}

	public void exit() {
		this.setVisible(false);
	}

/**
 * 	
 * @author Janani Swaminathan
 *
 */
	public class ButtonListener implements ActionListener {
		static final int STOP = 1;
		static final int INJECT = 2;
		static final int EXIT = 3;
		static final int CONTINUE = 4;
		static final int DRAIN = 5;

		protected int cmd;

		public ButtonListener(int cmd) {
			this.cmd = cmd;
		}

		public void actionPerformed(ActionEvent e) {
			switch (cmd) {
			case STOP:
				stop();
				break;
			case INJECT:
				inject();
				break;
			case EXIT:
				exit();
				break;
			case CONTINUE:
				start();
				break;
			case DRAIN:
				FlagDrain = !FlagDrain;
				break;
			}
		}
	}
/**
 * 
 * @author Janani Swaminathan
 *
 */
	protected class SpeedlistHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			Choice choice = (Choice) event.getSource();
			if (choice != null) {
				int speed1 = choice.getSelectedIndex() + 1;

				if (dx < 0) {
					dx = -speed1 - 1;
				} else {
					dx = speed1 + 1;
				}
				if (dy < 0) {
					dy = -speed1 / 2 - 1;
				} else {
					dy = speed1 / 2 + 1;

				}
			}
		}
	}

	protected class SpeedListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting()) {
				int speed = (int) source.getValue();
				if (dx < 0) {
					dx = -speed - 1;
				} else {
					dx = speed + 1;
				}
				if (dy < 0) {
					dy = -speed / 2 - 1;
				} else {
					dy = speed / 2 + 1;

				}
			}
		}

	}
}
