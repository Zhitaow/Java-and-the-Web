package MidtermAddition;


import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class BouncingBall3 extends java.applet.Applet {
  public BouncingBall3() {
    setLayout(new BorderLayout());
    canvas = new BouncingBallCanvas();
    add("Center", canvas); 
    animator = new Animator(canvas); 
    Dimension d; 
    
    controlPanel = new Panel(); 
    controlPanel.setLayout(new GridLayout(1,0));     
    Button startButton = new Button("start");
    controlPanel.add(startButton);
    Button stopButton = new Button("stop");
    controlPanel.add(stopButton);
    Choice choice = new Choice();
    choice.addItem("red");
    choice.addItem("green");
    choice.addItem("blue");
    controlPanel.add(choice); 
    add("South", controlPanel); 
    
    startButton.addActionListener(new ButtonHandler(new StartCommand())); 
    stopButton.addActionListener(new ButtonHandler(new StopCommand())); 
    choice.addItemListener(new ColorChoiceHandler());
     
  }
   
  public void init() {
    String att = getParameter("delay");
    if (att != null) { 
      int delay = Integer.parseInt(att);
      animator.setDelay(delay); 
    }
    canvas.initCanvas(); 
  }
  public void start() {
    animator.start(); 
  }
  public void stop() {
    animator.stop(); 
  }
   
  protected BouncingBallCanvas canvas;
  protected Animator animator; 
  protected Panel controlPanel; 
  
  protected class ButtonHandler implements ActionListener {  
    private Command cmd; 
      
    public ButtonHandler(Command cmd) {
      this.cmd = cmd;     
    }
       
    public void actionPerformed(ActionEvent event) { 
      if (cmd != null) 
        cmd.execute(); 
    }
  }
  protected class StartCommand implements Command {
    public void execute() {
      start(); 
    }
  }
  protected class StopCommand implements Command {
    public void execute() {
      stop(); 
    }
  }
  protected class ColorChoiceHandler implements ItemListener {
    public void itemStateChanged(ItemEvent event)  {    
      Choice choice = (Choice) event.getSource();  
      if (choice != null) {
        if ("red".equals(event.getItem()))
          canvas.setBallColor(Color.red); 
        else if ("green".equals(event.getItem()))
          canvas.setBallColor(Color.green); 
        else if ("blue".equals(event.getItem()))
          canvas.setBallColor(Color.blue); 
        canvas.repaint();
      }
    }
  }
   
}

