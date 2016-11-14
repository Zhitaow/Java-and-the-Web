import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;

@SuppressWarnings("serial")
public class MyClock extends Applet implements Runnable {
    protected Thread thread;
    private Image imageBuffer, imageBG, imageClock;
    private Graphics doubleG;
    int year, day, hour, minute, second;
    int alarmHour, alarmMinute, alarmSecond;
    Calendar calendar;
    String month, weekday, AM_PM, alarmAMPM = "AM";
    // canvas's size
    int xSize = 600, ySize = 600;
    // center of the analog clock's hands
    double xc = 288.72d, yc = 410.0d;
    // initialize alarm status. Default is off.
    boolean peekAlarm = false, setAlarm = false, triggerAlarm = false;
    // status about whether to show in Roman numeric or not
    boolean showRoman = false;
    // initialize a button to activate the alarm
    Button b1 = null;
    // initialize a button to display Roman numeral format
    Button br = null;
    // initialize buttons for the increment and decrement of hour, minute, and second
    Button hh1 = null, hh2 = null, mm1 = null, mm2 = null, ss1 = null, ss2 = null, 
    ap1 = null, ap2 = null, alm = null;
    int fontSize = 36;
    // Roman numerals from 1-59; for zero use "0" instead
    final static String[] ROMAN = {"0", "I", "II", "III", "IV", "V",
	    "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
	    "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
	    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
	    "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
	    "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
	    "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV",
	    "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
	    "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII",
	    "LVIII", "LIX"};

    @Override
    public void init() {
        setSize(xSize, ySize);
        setBackground(Color.black);
        setLayout(null);
        b1 = activateAlarmButton("Alarm");
        br = switchRomanButton("Roman", true, new int[]{40, 80, 100, 50});
        // get background image
        imageBG = getImage(getCodeBase(), "golden-gate-bridge.jpg");
        // get the clock image
        imageClock = getImage(getCodeBase(), "AnalogClock.png");
    }
    
    @Override
    public void start() {
        thread = new Thread(this);
        thread.start();        
    }
    
    @SuppressWarnings("static-access")
    @Override
    public void run() {
        while (Thread.currentThread() == thread) {
            try {
                updateCalendar();
                checkAlarm();
                repaint();
                // refresh every 50 milliseconds
                thread.sleep(50);
    	    } catch (Exception e) {}
        }
    }
    
@Override
// draw a buffer image to address screen flashing issue.
public void update(Graphics g) {
    // credit to https://www.youtube.com/watch?v=WI_3rJmTPS4
        if (imageBuffer == null) {
            imageBuffer = createImage(this.getSize().width, this.getSize().height);
            doubleG = imageBuffer.getGraphics();
        }
        doubleG.setColor(getBackground());
        doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
        doubleG.setColor(getForeground());
        paint(doubleG);
        g.drawImage(imageBuffer, 0, 0, this);
    }
    
    @Override
    public void paint(Graphics g) {
        // draw background image
        g.drawImage(imageBG, 0, 0, this.getSize().width, this.getSize().height, 0, 0, 
          1000, 1000, this);
    	// draw opaque panel
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.fillRect(0, 60, this.getSize().width, 150);
        // draw current time
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
        g.drawString(weekday + ", " + month + " " + day + " " + year, 170, 120);
        if (showRoman) {
            g.drawString(AM_PM + " " + toRomanString(hour) + ":" + 
              toRomanString(minute) + ":" +toRomanString(second), 170, 180);
        } else {
            g.drawString(((hour == 0) ? 12 : hour) + ":" + minute / 10 + minute % 10 +
               ":" + second / 10 + second % 10 + " " + AM_PM, 200, 180);	
            }
        // print Alarm message if the alarm is triggered
        if (triggerAlarm) {
            if (second % 2 == 0) {
            	g.setColor(Color.red);
            	g.drawString("Alarm!", 60, 365);
            }
            showAlarm(g);
        }
        // When button "b1" is clicked, peek the alarm interface;
        if (peekAlarm == false) {
            showClock(g);
        } else {
            showAlarm(g);
        }
    }
    
    // update the current time
    public void updateCalendar() {
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);            
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);        
        month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, getLocale());
        weekday = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, 
          getLocale());
        AM_PM = calendar.getDisplayName(Calendar.AM_PM,Calendar.SHORT, getLocale());
    }
    // show the analog clock interface
    public void showClock (Graphics g) {
        // draw a clock
        g.drawImage(imageClock, 100, 220, 800, 920, 0, 0, 5000, 5000, this);
        // draw hour hand
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        double hourDiv = (double) hour/12.0 + minute/60.0/12.0;
        double[] xy = xyCoordinate(xc, yc, 120, hourDiv);
        g2d.draw(new Line2D.Double(xc, yc, xy[0], xy[1]));
        // draw minute hand
        g2d.setStroke(new BasicStroke(3));
        double minuteDiv = (double) minute/60.0 + second/3600.0;
        xy = xyCoordinate(xc, yc, 170, minuteDiv);
        g2d.draw(new Line2D.Double(xc, yc, xy[0], xy[1]));
        // draw second hand
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.red);
        xy = xyCoordinate(xc, yc, 170, second / 60.0);
        g2d.draw(new Line2D.Double(xc, yc, xy[0], xy[1]));
        
    }
    // show Alarm-set time
    public void showAlarm (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
        g2d.fillRect(0, 280, this.getSize().width, 150);
        g.setColor(Color.white);
        g.drawString(((alarmHour < 10) ? "0" + alarmHour : alarmHour) + ":" + 
          alarmMinute / 10 + alarmMinute % 10 +
            ":" + alarmSecond / 10 + alarmSecond % 10 + " " + alarmAMPM, 200, 365);
    }
    // convert the portion of 360 degree to Cartesian coordinate
    private double[] xyCoordinate(double xc, double yc, double radius, double ndiv) {
        double[] xy = new double[6];
        double theta =  ndiv * Math.PI * 2.0;
        // convert angle to the corresponding x, y position in display
        xy[0] = xc + radius * Math.sin(theta);
        xy[1] = yc - radius * Math.cos(theta);
        return xy;
    }
    // method to activate alarm button
    private Button activateAlarmButton(final String label) {
        final Button b = new Button(label);
        b.setFont(new Font("Helvetica", Font.PLAIN, 20));
        b.setForeground(Color.gray);
        add(b);
        b.setBackground(new Color(80,80,80));
        b.setBounds(480, 80, 100, 50);
        b.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (peekAlarm == false && label == "Alarm") {
              peekAlarm = true;
              b.setLabel("Clock");
    	       hh1 = setAlarmButton("hour", true, new int[]{205, 300, 30, 30});
    	       hh2 = setAlarmButton("hour", false, new int[]{205, 380, 30, 30});
    	       mm1 = setAlarmButton("minute", true, new int[]{255, 300, 30, 30});
    	       mm2 = setAlarmButton("minute", false, new int[]{255, 380, 30, 30});
    	       ss1 = setAlarmButton("second", true, new int[]{305, 300, 30, 30});
    	       ss2 = setAlarmButton("second", false, new int[]{305, 380, 30, 30});
    	       ap1 = setAlarmButton("AMPM", true, new int[]{355, 300, 30, 30});
    	       ap2 = setAlarmButton("AMPM", false, new int[]{355, 380, 30, 30});
    	       if (setAlarm == false) {
    	         alm = setAlarmButton("Set", false, new int[]{480, 330, 100, 50});
    	       } else {
    	         alm = setAlarmButton("Cancel", false, new int[]{480, 330, 100, 50});
    	       }
            } else {
                peekAlarm = false;
                b.setLabel("Alarm");
    	           try {
    	               remove(hh1);
    	               remove(hh2);
    	               remove(mm1);
    	               remove(mm2);
    	               remove(ss1);
    	               remove(ss2);
    	               remove(ap1);
    	               remove(ap2);
    	               remove(alm);
    	           } catch	(Exception m){};
    	      }
    	    }
        });
        return b;
    }
    // method to set alarm button "+", "-", "Set"
    private Button setAlarmButton(final String label, final boolean increment, final 
        int[] bound) {
        final Button b = new Button();
        add(b);
        b.setBackground(new Color(80,80,80));
        b.setBounds(bound[0], bound[1], bound[2], bound[3]);
        b.setForeground(Color.white);
        if (label == "Set") {
            b.setLabel("Set");
            b.setFont(new Font("Helvetica", Font.PLAIN, 20));
        } else if (label == "Cancel") {
            b.setLabel("Cancel");
            b.setFont(new Font("Helvetica", Font.PLAIN, 20));
        } else if (increment) {
            b.setLabel("+");
        } else {
            b.setLabel("-");
        }       
    	b.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
                switch (label) {
                    case "hour":
                    	if (increment == true) {
                    		alarmHour = (alarmHour + 1) % 12;
                    		
                    	} else {
                    		alarmHour = ((alarmHour - 1) % 12 >= 0) ? alarmHour 
                                 - 1 : 12 + alarmHour - 1;
                    	}
                    	break;
                    case "minute":
                    	if (increment == true) {
                    		alarmMinute = (alarmMinute + 1) % 60;
                    	} else {
                    		alarmMinute = ((alarmMinute - 1) % 60 >= 0) ? 
                                 alarmMinute - 1 : 60 + alarmMinute - 1;
                    	}
                    	break;
                    case "second":
                    	if (increment == true) {
                    		alarmSecond = (alarmSecond + 1) % 60;
                    	} else {
                    		alarmSecond = ((alarmSecond - 1) % 60 >= 0) ? 
                                 alarmSecond - 1 : 60 + alarmSecond - 1;
                    	}
                    	break;
                    case "AMPM":
                    	if (increment == true) {
                    		alarmAMPM = "PM";
                    	} else {
                    		alarmAMPM = "AM";
                    	}
                    	break;
                    case "Set":
                        if (setAlarm == false) {
                            setAlarm = true;
                            b.setLabel("Cancel");
                        } else {
                            setAlarm = false;
                            b.setLabel("Set");
                        }
                        break;
                    case "Cancel":
                        if (setAlarm == false) {
                            setAlarm = true;
                            b.setLabel("Cancel");
                        } else {
                            setAlarm = false;
                            b.setLabel("Set");
                        }
                        break;
                    default:
                        break;
                }
    		}
    	});
    	return b;
}

    // convert numbers to Roman letter
    public static String toRomanString(int num) {
    	return ROMAN[num];
    }

    // a switch button to show classical time (HH:MM:SS) or Roman numeral format
    private Button switchRomanButton(final String label, final boolean increment, 
     final int[] bound) {
        
    	final Button b = new Button();
        add(b);
        b.setBackground(new Color(80,80,80));
        b.setBounds(bound[0], bound[1], bound[2], bound[3]);
        b.setForeground(Color.gray);
        b.setFont(new Font("Helvetica", Font.PLAIN, 20));
        if (label == "Roman") {
            b.setLabel("Roman");
        }        
    	b.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
                switch (label) {
                    case "Roman":
                        if (showRoman == false) {
                            showRoman = true;
                            b.setLabel("Classic");
                        } else {
                        	showRoman = false;
                            b.setLabel("Roman");
                        }
                        break;
                    default:
                        break;
                }
    		}
        });
        return b;
    }
    // check if it's time to trigger an alarm
    private void checkAlarm() {
        if (setAlarm == true && alarmHour == hour && alarmMinute == minute && 
                alarmSecond == second && alarmAMPM == AM_PM) {
            triggerAlarm = true;
        } else if (setAlarm == false) {
        	triggerAlarm = false;
        }
    }
}
