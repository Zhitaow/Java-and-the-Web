package scribble3; 

import java.awt.Color;

public abstract class AbstractTool implements Tool { 

  public String getName() {
    return name; 
  }

  protected AbstractTool(ScribbleCanvas canvas, String name) {
    this.canvas = canvas;
    this.name = name; 
  }
  
  protected ScribbleCanvas canvas; 
  protected String name;
  
  //--------- my added methods --------------
  public void setThickness(int thickness) {
	  this.thickness = thickness;
  }
  
  public int getThickness() {
	  return thickness;
  }
  
  public void setColor(Color color) {
	  this.color  = color;
  }
  
  public Color getColor() {
	  return color;
  }
  //---------------------------------------
  //----  my added fields -----------
  protected int thickness = 2;
  protected Color color = Color.black;
  //----------------------------------
}
