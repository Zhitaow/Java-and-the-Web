package midterm2014;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class BouncingBallMidterm extends DBAnimationApplet {
		double x, y;
		double dx = -10, dy = -10;
		int radius = 20;
		Color color = Color.green;
		double PAngle = 360 - 45;
		double power = 10;
		boolean drawAim = true;
		boolean setPower = false;
		boolean startGame = false;
	    double initX =  500;
	    double initY =  400;
	    int aimDistance = 400;
	    int score = 0;
	    Button launchBtn, stopBtn, addBtn, minBtn;
	    Image imageBG, ballBG;
	
	  public BouncingBallMidterm() {
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
		      if (y < radius || y > d.height - radius) {
		        dy  =  -dy;
		      }
		      x += dx; y += dy;
		    // check if it scored
		    if (y > d.height / 3 - 10 && y < d.height / 3 + 10 
		    		&& dy > 0 && x < 120 && x > 0){
		          score++;
		     } 
	    }
//	    g.setColor(color);
//	    g.fillOval((int) (x) - radius, (int)(y) - radius, radius * 2, radius * 2);

		// draw a horizontal line
	    Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.green);
		g2.drawLine(0, d.height / 3, 120, d.height / 3);
		g.drawString("Score:" + score, 2 * d.width / 3, 20);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
	    // draw aim assist line
		if (drawAim) {
		    double[] xy = xyCoordinate(initX, initY, aimDistance, PAngle/ 360);
		    g2.setStroke(new BasicStroke(10));
		    g2.setColor(Color.red);
		    g2.drawLine(40, 40, (int) (40 + power), 40); 
		    
		    g2.setStroke(new BasicStroke(2));
		    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT,
		    		BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		    g2.setStroke(dashed);
		    g2.drawLine((int) initX, (int) initY, (int) xy[0], (int) xy[1]);
		}
		g.setColor(Color.white);
	  }
	  
	
	void aim(Button btn) {	
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
			power += power;
		
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
    			startGame = true;
    			drawAim = false;
//    	    	System.out.println(Math.cos(PAngle));
//    	    	System.out.println(Math.cos(dx));
//    	    	System.out.println(Math.cos(dy));
    	    	dx = power * - Math.sin(PAngle);
    			dy = power * - Math.cos(PAngle);
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
    			dx = -10;
    			dy = -10;
    			x = initX;
    		    y = initY - radius;
    			PAngle = 360 - 45;
    			power = 0;
    		    score = 0;
    			
    		}
		});
	}
  
}

