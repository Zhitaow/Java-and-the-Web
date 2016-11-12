//package midterm2014;
//
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.JButton;
//
//public class BouncingBall2 extends DBAnimationApplet implements MouseListener {
//
//    protected int x, y;
//    int startx,starty,endx,endy;
//    protected static int score;
//    protected int dx = 0, dy = 0;
//    protected int radius = 20;
//    protected Color color = Color.orange;
//    protected Label scoreLabel;
//    protected Button pick;
//    protected Button reset;
//    
//    public BouncingBall2() {
//      super(true);  // double buffering
//    }
//    
//    protected void initAnimator() {
//      
//      
//      addMouseListener(this);
//      score = 0;
//      String att = getParameter("delay");
//      if (att != null)
//        setDelay(Integer.parseInt(att));
//      x = d.width * 3/ 4 ;
//      y = d.height - radius;
//      scoreLabel = new Label ("Score: "+"0");
//      add(scoreLabel);
//      pick = new Button("Pick");
//      add(pick);
//      pick.addActionListener(new ActionListener() {
//        
//        @Override
//        public void actionPerformed(ActionEvent e) {
//          // TODO Auto-generated method stub
//          x = d.width * 3/ 4 ;
//          y = d.height - radius; 
//          dx = 0;
//          dy = 0;
//        }
//      });
//      reset = new Button("Reset");
//      add(reset); 
//      reset.addActionListener(new ActionListener() {
//        
//        @Override
//        public void actionPerformed(ActionEvent e) {
//          // TODO Auto-generated method stub
//          x = d.width * 3/ 4 ;
//          y = d.height - radius;
//          
//          dx = 0;
//          dy = 0;
//          scoreLabel.setText("Score: "+ "0");
//          
//        }
//      });
//      
//    }
//    
//
//    protected void paintFrame(Graphics g) {
//      g.setColor(Color.white);
//      g.fillRect(0,0,d.width,d.height);
//      if (x < radius || x > d.width - radius) {
//        dx = -dx;    
//      }
//
//      if (y < radius) {
//        dy  =  -dy;
//        y += dy+98/100;
//      }
//      
//      if(y > d.height - radius){
//        
//        dy = -dy;
//        y += dy;
//        dx = 0;
//        dy = 0;
//
//      }
//    
//      dy+=3;
//      x += dx; y += dy;
//    
//    
//    
//      if (y>70 && y<80 && dy>0 && x<55 && x>0){
//        score++;
//        scoreLabel.setText("Score: "+Integer.toString(score));
//      } 
//    
//      g.setColor(Color.orange);
//      g.fillOval(x - radius, y - radius, radius*2 , radius*2);
//      g.setColor(Color.red);
//      g.fillRect(0, 70, 70, 5);
//      g.drawLine(0, 75, 10, 100);
//      g.drawLine(60,100, 70, 75);
//    
//    }
//
//    public void mouseDown(MouseEvent e){
//            
//    }
//    public void mouseClicked(MouseEvent e) {
//      // TODO Auto-generated method stub
//      startx=e.getX();
//        endy=e.getY();
//    }
//
//    public void mouseEntered(MouseEvent e) {
//      // TODO Auto-generated method stub
//      
//    }
//
//    public void mouseExited(MouseEvent e) {
//      // TODO Auto-generated method stub
//      
//    }
//
//    public void mouseUp(MouseEvent e) {
//      // TODO Auto-generated method stub
//      
//    }
//
//    public void mousePressed(MouseEvent e) {
//      // TODO Auto-generated method stub
//      
//    }
//
//    public void mouseReleased(MouseEvent e) {
//      // TODO Auto-generated method stub
//      flag=true;
//      String att = getParameter("delay");
//        if (att != null)
//            setDelay(Integer.parseInt(att));
//
//      this.start();
//      endx=e.getX();
//      endy=e.getY();
//      dx=(startx-endx)/15;
//      dy=(starty-endy)/11;
//      System.out.println(dx+" "+dy);
//      x = d.width * 3/ 4 ;
//      y = d.height - radius;
//    }
//
//}
