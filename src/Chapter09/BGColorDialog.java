package Chapter09;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import scribble3.*;
@SuppressWarnings("serial")
public class BGColorDialog extends ColorDialog {

	private ScribbleCanvas canvas;
	private ScribbleTool myEraserTool;
	private AreaEraserTool areaEraserTool;
	public BGColorDialog(JFrame owner, String title, ScribbleCanvas canvas, ScribbleTool myEraserTool, AreaEraserTool areaEraserTool) {
		super(owner, title);
		this.canvas = canvas;
		this.myEraserTool = myEraserTool;
		this.areaEraserTool = areaEraserTool;
		// TODO Auto-generated constructor stub
	}

	  @SuppressWarnings("static-access")
	  public void actionPerformed(ActionEvent e) { 
	    Object source = e.getSource();
	    if (source == okButton) {
	      canvas.newFile();
	      canvas.setBGColor(colorPanel.getColor());
	      myEraserTool.setColor(colorPanel.getColor());
	      areaEraserTool.setFilledColor(colorPanel.getColor());
	      canvas.repaint();
	    } else if (source == moreColorButton) { 
	      Color selectedColor = chooser.showDialog(BGColorDialog.this, 
						       "Choose color", 
						       color); 
	      if (selectedColor != null) {
	    	  canvas.newFile();
	    	  myEraserTool.setColor(selectedColor);
	    	  areaEraserTool.setFilledColor(selectedColor);
	    	  canvas.setBGColor(selectedColor);
	    	  canvas.repaint();
	      }
	      return;
	    }
	    setVisible(false); 
	  }
}
