

package scribble3; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Point;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.*; 

@SuppressWarnings("serial")
public class ScribbleCanvas extends JPanel { 

  public ScribbleCanvas() { 
    // calling factory method 
    listener = makeCanvasListener(); 
    addMouseListener((MouseListener) listener); 
    addMouseMotionListener((MouseMotionListener) listener); 
  }

  public void setCurColor(Color curColor) { 
    this.curColor = curColor; 
  }

  public Color getCurColor() { 
    return curColor; 
  }


  public void addShape(Shape shape) {
    if (shape != null) { 
      shapes.add(shape); 
    }
  }
  
  // ------- my added codes --------
  @SuppressWarnings("rawtypes")
  public List getShape() {
	  return shapes;
  }
  
  public void undoShape() {
	  if (!shapes.isEmpty()) {
		  shapes.remove(shapes.size()-1);
	  }
  }
  
  // -------------------------------
  
  @SuppressWarnings("rawtypes")
  public void paint(Graphics g) {
    Dimension dim = getSize(); 
//    g.setColor(Color.white);
    //------- my modified line ---------------------
	g.setColor(bgColor);
	g.fillRect(0, 0, dim.width, dim.height); 
    if (imageBG != null) {
    	g.drawImage(imageBG, 0, 0, this.getSize().width, this.getSize().height, 0, 0, 
            1000, 1000, this);
    }
    //--------------------------------------------
    g.setColor(Color.white);
    if (shapes != null) { 
      Iterator iter = shapes.iterator(); 
      while (iter.hasNext()) { 
    	  Shape shape = (Shape) iter.next(); 
    	  if (shape != null) { 
    		  shape.draw(g); 
    	  }
      }
    }
  }
  // ---------- my added method --------------
  public void setBGColor(Color c) {
	  bgColor = c;
  }
  // ---------- my added method ---------------
  public Color getBGColor() {
	  return bgColor;
  }
  
  // ---------- my added method ---------------
  public void setBGImage(String inputImage) {
	  String[] postfixList = {".jpg", ".JPG", ".jpeg", ".JPEG",
			  ".png", ".PNG", ".bmp", ".BMP", ".gif", ".GIF"};
	  boolean isImage = false;
	  for (String postfix:postfixList) {
		  if (inputImage.contains(postfix)) {
			  isImage = true;
		  }
	  }
	  if (isImage) {
			  try {
				  InputStream is = new BufferedInputStream(new FileInputStream(inputImage));
				  imageBG = ImageIO.read(is);
		//		  g.drawImage(img, x, y, w, h, observer); 
				  
			  } catch (Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
	  } else {
		  JOptionPane.showMessageDialog(null, 
				  "Image File must be one of the following format: "
				  + ".jpg, .jpeg, .png, .bmp, .gif ");
	  }
  }

  public void newFile() { 
    shapes.clear();
    repaint();
  }

  @SuppressWarnings("rawtypes")
  public void openFile(String filename) {
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)); 
      shapes = (List) in.readObject();
      in.close(); 
      repaint();
    } catch (IOException e1) {
      System.out.println("Unable to open file: " + filename);
      // my added message
   	  JOptionPane.showMessageDialog(null, "Unable to open file: " + filename);
    } catch (ClassNotFoundException e2) {
      System.out.println(e2);
      JOptionPane.showMessageDialog(null, e2);
    }
  }

  public void saveFile(String filename) { 
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)); 
      out.writeObject(shapes); 
      out.close(); 
      // my added message
	  JOptionPane.showMessageDialog(null, "Save drawing to " + filename);
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      // my added message
      JOptionPane.showMessageDialog(null, "Unable to write file: " + filename);
      System.out.println("Unable to write file: " + filename); 
    }  
  }

  // factory method 
  protected EventListener makeCanvasListener() {
    return new ScribbleCanvasListener(this); 
  }

  // The list of shapes of the drawing 
  // The elements are instances of Stroke
  protected List<Shape> shapes = new ArrayList<Shape>(); 

  protected Color curColor = Color.black; 

  protected EventListener listener;

  public boolean mouseButtonDown = false; 
  public int x, y;
  
  //------------- my added field ------------------
  private Color bgColor = Color.white;
  private Image imageBG;
}