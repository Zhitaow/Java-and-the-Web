package Chapter05;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The MyShape class extends the Applet.
 * @author Zhitao Wang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MyShape extends Applet implements Runnable {
	// input
	String inputfileName = "shapes.txt";
	// use white space as the delimiter 
	String delimiter = "\\s+";
	// window's size
	int xSize = 600, ySize = 800;
	// canvas's size
	int canvasWidth = 600, canvasHeight = 600;
	// top left corner of canvas
	int[] canvasPosition = new int[] {0, 200};
	// buttons
	TextField pathInput;
	JTextPane textBox;
	JScrollPane scrollBox;
	Button loadBtn;
	// Use Shape array to store shape objects
	Shape[] shapeArray = null;

	private Image imageBuffer;
	private Graphics doubleG;
	Thread thread;
	@Override
	public void init() {
		setSize(xSize, ySize);
		setBackground(Color.gray);
		setLayout(null);
		// file path input
		pathInput = new TextField();
		pathInput.setBounds(25, 30, 300, 20);
		add(pathInput);
		pathInput.setColumns(20);

		// scrolling text box containing the shape information
		textBox = new JTextPane();
		textBox.setEditable(false);
		scrollBox = new JScrollPane(textBox);
		scrollBox.setBounds(25, 60, xSize - 40, 100);
		scrollBox.setBorder(null);
		add(scrollBox);
		//D:\eclipse_workspace\CS602\shapes.txt
		// load file button
		loadBtn = new Button("Load File");
		add(loadBtn);
		loadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputfileName = pathInput.getText();
				shapeArray = Ch05Solution01.readShapeFile(
						inputfileName, delimiter, canvasPosition, loadBtn);
				//repaint();
			}});
		loadBtn.setBounds(400, 30, 140, 20);
		//loadBtn.setBounds(350, 30, 240, 20);
	}
	
	@Override
	public void paint(Graphics g) {		
		try {
			if (shapeArray == null) {
				g.setColor(Color.blue);
				g.drawString("Please enter a file path below:", 
25, 20);
			} else if (shapeArray[0] == null) {
				g.setColor(Color.red);
				g.drawString("No shape object is loaded. Try again.",
 25, 20);
			}

			// draw the canvas area
			g.setColor(Color.white);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.9f));
			g2d.fillRect(0, 200, canvasWidth, canvasHeight);

			// draw validated shape instances one by one.
			String line = "";
			for (Shape shape : shapeArray) {
				if (shape != null) {
					g.setColor(Color.black);
					shape.draw(g);    	
					line = line + shape.toString() + 
							" has been drawn. \n";
					textBox.setText(line);
				}
			}
			textBox.setText(line);
		} catch (Exception e) {}
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
                //updateCalendar();
                //checkAlarm();
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
    
    
    
}
