package Chapter09;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import scribble3.ScribbleTool;
import draw2.FilledRectangleShape;
import draw2.TwoEndsShapeTool;


@SuppressWarnings("serial")
public class DrawingPad extends draw3.DrawingPad {
	private int[] thickValue = new int[] {2};
	private ScribbleTool myScribbleEraserTool;
	private FilledRectangleShape myRectanlgeEraser;
	private AreaEraserTool areaEraserTool;

	public DrawingPad(String title) {
	    super(title); 
	    JMenu optionMenu = menuBar.getMenu(2);
	    // remove all font optionMenu from draw3.DrawingPad
	    for (int i = 3; i > 0; i--) {
	    	optionMenu.remove(optionMenu.getItem(i));
	    }
	    // replace with new font menu
	    JMenuItem fontMenu = new JMenuItem("Font");
	    optionMenu.add(fontMenu);
	    fontMenu.addActionListener(new FontListener());
	    JMenuItem thickMenu = new JMenuItem("Thickness");
	    thickMenu.addActionListener(new ThicknessListener());
	    optionMenu.add(thickMenu);
	    // add edit  
	    //JMenu menu = createToolMenu(toolListener); 
	    JMenu editMenu = new JMenu("Edit");
	    menuBar.add(editMenu, 3);
	    JMenuItem bgColorMenu = new JMenuItem("background color");
	    bgColorMenu.addActionListener(new BGColorListener());
	    editMenu.add(bgColorMenu);
	    JMenuItem bgImageMenu = new JMenuItem("background image");
	    bgImageMenu.addActionListener(new BGImageListener());
	    editMenu.add(bgImageMenu);

	    JMenuItem undoMenu = new JMenuItem("Undo");
	    undoMenu.addActionListener(new undoListener());
	    editMenu.add(undoMenu);
	    
//	    shapes = canvas.getShape();
	}
	
	protected void initTools() { 
		super.initTools();
		canvas.setCurColor(Color.black);
		toolkit.addTool(new TwoEndsShapeTool(canvas, 
				"Circle", new CircleShape())); 
	    toolkit.addTool(new TwoEndsShapeTool(canvas, 
	    		"Filled Circle", new FilledCircleShape()));
	    toolkit.addTool(new TwoEndsShapeTool(canvas, 
	    		"Round Rect.", new RectangleRoundShape()));
	    toolkit.addTool(new TwoEndsShapeTool(canvas, 
	    		"Filled Round Rect.", new FilledRectangleRoundShape()));
	    toolkit.addTool(new TwoEndsShapeTool(canvas, 
	    		"Triangle", new TriangleShape()));
	    toolkit.addTool(new TwoEndsShapeTool(canvas, 
	    		"Filled Triangle", new FilledTriangleShape()));
	    
	    myScribbleEraserTool = new ScribbleTool(canvas,
	    		"Scribble Eraser");
	    myScribbleEraserTool.setColor(canvas.getBGColor());
	    toolkit.addTool(myScribbleEraserTool); 
	    myRectanlgeEraser = new FilledRectangleShape();
	    areaEraserTool = new AreaEraserTool(canvas, 
	    		"Area Eraser", myRectanlgeEraser);
	    areaEraserTool.setFilledColor(canvas.getBGColor());
	    toolkit.addTool(areaEraserTool);
	    myRectanlgeEraser.setColor(canvas.getBGColor());
	    repaint();
	}
	
	class undoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.undoShape();
			repaint();
		}
		
	}
	
	class BGColorListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dialog.showDialog();
		}
		protected BGColorDialog dialog = 
				new BGColorDialog(DrawingPad.this, 
						"Choose color", canvas, 
						myScribbleEraserTool, areaEraserTool);
	}
	
	
	class BGImageListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser BGchooser = new JFileChooser();
	        BGchooser.setCurrentDirectory(new java.io.File("."));
	        BGchooser.setDialogTitle(
	        		"Browse for loading background image");
	        BGchooser.setFileSelectionMode(
	        		JFileChooser.FILES_AND_DIRECTORIES);
	        if (BGchooser.showOpenDialog(null) == 
	        		JFileChooser.APPROVE_OPTION) {
	        	String BGfile = 
	        			BGchooser.getSelectedFile().getAbsolutePath();
	        	System.out.println(BGfile);
	        	canvas.setBGImage(BGfile);
	        	repaint();
			}
		}
	}
	
	class FontListener implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			dialog.showDialog();			
		}
		protected FontDialog dialog = 
				new FontDialog(keyboardDrawingCanvas);
	}
	
	class ThicknessListener implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			dialog.showDialog();
			repaint();
		}
		protected ThicknessDialog dialog = 
				new ThicknessDialog(thickValue, 
						myScribbleEraserTool, canvas);
	}
	
	public static void main(String[] args) {
		JFrame frame = 
				new Chapter09.DrawingPad("Drawing Pad");
		frame.setSize(width, height);
		Dimension screenSize = 
				Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2 - width/2,
				screenSize.height/2 - height/2);
		frame.setVisible(true);
	}
}
