package Chapter09;

import java.awt.Color;

import draw2.FilledRectangleShape;
import scribble3.ScribbleCanvas;
import scribble3.ScribbleTool;
import junit.framework.TestCase;

public class DrawingPadTest extends TestCase {
	public DrawingPadTest(String name) {
		super(name);
	}
		  
	protected void setUp() throws Exception {
		super.setUp();
	}
		  
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	// test changing the color of the scribble eraser
	public void testStribbleEraserColor(){
		ScribbleCanvas canvas = new ScribbleCanvas();
		ScribbleTool myScribbleEraserTool = new ScribbleTool(canvas,
	    		"Scribble Eraser");
	    myScribbleEraserTool.setColor(Color.red);
	    assertEquals(myScribbleEraserTool.getColor(), Color.red);
    }
	
	// test changing the color of the area eraser
	public void testAreaEraserColor(){
		ScribbleCanvas canvas = new ScribbleCanvas();
		FilledRectangleShape myRectanlgeEraser = new FilledRectangleShape();
		AreaEraserTool areaEraserTool = new AreaEraserTool(canvas, 
	    		"Area Eraser", myRectanlgeEraser);
	    areaEraserTool.setFilledColor(Color.red);
	    assertEquals(areaEraserTool.getFilledColor(), Color.red);
    }
	
	// test changing the background color of canvas
	public void testBGColor(){
		ScribbleCanvas canvas = new ScribbleCanvas();
		canvas.setBGColor(Color.red);
	    assertEquals(canvas.getBGColor(), Color.red);
	}
	
	// test changing the thickness of my tool
	public void testToolThickness(){
		ScribbleCanvas canvas = new ScribbleCanvas();
		ScribbleTool tool = new ScribbleTool(canvas, "Just for test");
		tool.setThickness(10);
	    assertEquals(tool.getThickness(), 10);
	}
	
	// test one subclass of TwoEndsShape class
	public void testRoundRectangleShape(){
		RectangleRoundShape testRect =  new RectangleRoundShape();
		testRect.setEnds(10,20,30,40);
		assertEquals(testRect.getX1(), 10);
		assertEquals(testRect.getY1(), 20);
		assertEquals(testRect.getX2(), 30);
		assertEquals(testRect.getY2(), 40);        
    }
    
	public void testFilledRoundRectangleShape(){
		FilledRectangleRoundShape testFilledRect =  new FilledRectangleRoundShape();
		testFilledRect.setEnds(10,20,30,40);
		assertEquals(testFilledRect.getX1(), 10);
		assertEquals(testFilledRect.getY1(), 20);
		assertEquals(testFilledRect.getX2(), 30);
		assertEquals(testFilledRect.getY2(), 40);        
    }
	
	
}
