package edu.njit.maze;

 import java.applet.Applet;
 
 

@SuppressWarnings("serial")
public class MazeApplet extends Applet{

   public MazeApplet () {
     
   }
   
   public void init(){
     Maze maze;
     
 
     int rows = getSizeParameter("rows");
     int cols = getSizeParameter("cols");   
 
     MazeGameCreator creator = new MazeGameCreator(rows, cols);
     maze = creator.createMaze();
     maze.setCurrentRoom(1);
     maze.addToComponent(this);
   }
   
   private Integer getSizeParameter(String param){
     String value = getParameter(param);
     if (value == null || value.isEmpty()) value = "4";    
     return Integer.parseInt(value);    
   }
 }
 