package Midterm;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class BouncingBall2 extends DBAnimationApplet1 {
		double x, y;
		double dx = 0, dy = 0;
		int radius = 20;
		Color color = Color.green;
		double PAngle = 360 - 45;
		double power = 10, vMax = 40;
		boolean drawAim = true;
		boolean setPower = false;
		boolean startGame = false;
	    double initX =  500;
	    double initY =  380;
	    int aimDistance = 100;
	    int score = 0;
	    Button launchBtn, stopBtn, addBtn, minBtn;
	    Image imageBG, ballBG;
	
	  public BouncingBall2() {
	    super(true);  // double buffering
	  }
	
	  protected void initAnimator() {
		imageBG = getImage(getCodeBase(), "background.png");
		ballBG = getImage(getCodeBase(), "ball.jpg");
		setDelay(50);
	    x = initX;
	    y = initY - radius;
	    shoot();
		launchBtn = new Button("Start");
		launch();
		add(launchBtn);
		
		stopBtn = new Button("Stop");
		stopGame();
		add(stopBtn);
		
		addBtn = new Button("+");
		aim(addBtn);
		add(addBtn);
		
		minBtn = new Button("-");
		aim(minBtn);
		add(minBtn);
		
	  }
	  	  
	  protected void paintFrame(Graphics g) {
	    Dimension d = getSize();
	    g.fillRect(0,0,d.width,d.height);
	    g.drawImage(imageBG, 0, 0, this.getSize().width, this.getSize().height, 0, 0, 
	            400, 400, this);
	    g.drawImage(ballBG, (int) x -radius, (int) y -radius, 
	    		(int) x + radius, (int) y  + radius, 0, 0, 
	            400, 400, this);
	    if (startGame) {
	    	
		    if (x < radius || x > d.width - radius) {
		    	dx = -dx;
		    }

		    if (y < radius) {
		    	dy = -dy;
		    } 
		    
//		    if (dy < -0.1) {
//		    	dy *= 0.9;
//		    } else {
//		    	dy = 8;
//		    }
		    
		    if (dy > 0 && y > d.height - radius) {
		    	dy = 0;
		    	dx = 0;
		    } 

		    x += dx; 
		    y += dy;
		    
	        //System.out.println(dy);

		    // check if it scored
		    if (y > d.height / 3 - 10 && y < d.height / 3 + 10 
		    		&& dy > 0 && x < 120 && x > 0){
		          score++;
		     } 
		    
		    if (Math.abs(dx) < 0.001 && Math.abs(dy) < 0.001) {
				drawAim = true;
			}
	    }
	    
	    //System.out.println(dy);
		// draw a horizontal line
	    Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.green);
		g2.drawLine(0, d.height / 3, 120, d.height / 3);
		g.drawString("Score:" + score, 2 * d.width / 3, 20);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
	    // draw aim assist line
		if (drawAim) {
		    double[] xy = xyCoordinate(x, y, aimDistance, PAngle/ 360);
		    g2.setStroke(new BasicStroke(10));
		    g2.setColor(Color.red);
		    g2.drawLine(40, 40, (int) (40 + power), 40); 
		    
		    g2.setStroke(new BasicStroke(2));
		    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT,
		    		BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		    g2.setStroke(dashed);
		    g2.drawLine((int) x, (int) y, (int) xy[0], (int) xy[1]);
		}
		g.setColor(Color.white);
	  }

	void aim(final Button btn) {	
		btn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (btn.getLabel().equals("+")){
    				PAngle += 1;
    			} else {
    				PAngle -= 1;
    			}
    		}
		});
	}
	
	// mouse press event
	public void shoot() {
		addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			setPower = true;
			if (power <= vMax) {
				power += power;
			}
		}
	  });
	}
	
    private double[] xyCoordinate(double xc, double yc, double radius, double ndiv) {
        double[] xy = new double[6];
        double theta =  ndiv * Math.PI * 2.0;
        // convert angle to the corresponding x, y position in display
        xy[0] = xc + radius * Math.sin(theta);
        xy[1] = yc - radius * Math.cos(theta);
        return xy;
    }
   
	void launch() {	
		launchBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (!startGame) {
	    			startGame = true;
	    			drawAim = false;
	    	    	dx = power * Math.sin(PAngle * Math.PI / 180);
	    			dy = power * - Math.cos(PAngle * Math.PI / 180);
    			} else {
    				if (drawAim) {
	    				power = 10;
	    				drawAim = false;
	    				dx = power * Math.sin(PAngle * Math.PI / 180);
		    			dy = power * - Math.cos(PAngle * Math.PI / 180);
    				}
    			}
    			
    		}
		});
	}
	
	void stopGame() {	
		stopBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			startGame = false;
    			drawAim = true;
    			setPower = false;
    			dx = 0;
    			dy = 0;
    			x = initX;
    		    y = initY - radius;
    			PAngle = 360 - 45;
    			power = 10;
    		    score = 0;
    		}
		});
	}
  
}
