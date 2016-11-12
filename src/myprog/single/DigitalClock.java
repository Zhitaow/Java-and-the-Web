package myprog.single;

/**
 *  This is a Digital clock applet to 
 *  a) Display the current year, month, date and day of week 
 *  b) Display 12 Hr format
 */

import java.awt.*;
import java.util.Calendar;



    public class DigitalClock extends java.applet.Applet implements Runnable{
        
        protected Thread clockThread = null;
        Font dateFont = new Font("Monospaced", Font.BOLD, 26);
        Font timeFont = new Font("Monospaced", Font.BOLD, 44);                
        protected Color color = Color.BLACK;
        private Image i;
        private Graphics doubleG;


        public void init(){

            this.setSize(350, 150); 
               setLayout(null);
               setBackground (color);  
	        	
           }
        
        public void start(){
            if(clockThread == null){
                clockThread = new Thread(this);
                clockThread.start();
            }
        }
        
        public void stop(){
            clockThread = null;
        }

        public void run() {
            while(Thread.currentThread() == clockThread){
                
                try{
                	repaint();
                    Thread.currentThread().sleep(20);
                }
                catch(InterruptedException e){}
            }
        }   
        
//        public void run() {
//        	repaint();
//        }
        
        @Override
        public void update(Graphics g) {
        	if (i == null) {
        		i = createImage(this.getSize().width, this.getSize().height);
        		doubleG = i.getGraphics();
        	}
        	doubleG.setColor(getBackground());
        	doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
        	
        	doubleG.setColor(getForeground());
        	paint(doubleG);
        	
        	g.drawImage(i,0,0,this);
        }
        
        @Override
        public void paint(Graphics g){            
        	       	
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);            
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);        
            
            String month = calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,getLocale());
            String dayw = calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT,getLocale());
            String AMPM = calendar.getDisplayName(Calendar.AM_PM,Calendar.SHORT,getLocale());
            
            g.setFont(dateFont);
            g.setColor (color);
            //setBackground(myColor);
            Image offImage;
            offImage = getImage(getCodeBase(), "IMG_1209.JPG");
            g.drawImage(offImage, 0,10, this);
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5 * 0.1f));
            g2d.fillRect(50 * 3, 20, 50, 50);
            
            g.drawString(dayw + "," + " " + month + " " + day + " " + year, 29, 40);
            g.setFont(timeFont);
            g.drawString(hour + ":" + minute/10 + minute%10 + ":" + second/10 + second%10 + " " + AMPM ,25,100);

        }    
        
        
        
    }
    
     