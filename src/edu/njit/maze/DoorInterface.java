 /*
  * To change this template, choose Tools | Templates
  * and open the template in the editor.
  */
 package edu.njit.maze;
 
import java.awt.Graphics;

 /**
  *
  * @author Jason
  */
 public interface DoorInterface {
     public Object clone() throws CloneNotSupportedException;
     public boolean isOpen(); 
     public void setOpen(boolean open);
     public void openDoor();
     public void setRooms(Room room1, Room room2);
     public Orientation getOrientation();
     public void setOrientation(Orientation orientation);
     public Room otherSideFrom(Room room);
     public void enter(Maze maze);
     public void draw(Graphics g, int x, int y, int w, int h);
 }
 