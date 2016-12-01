 package edu.njit.maze; 
 
 import java.applet.AudioClip; 
 import java.awt.Color;
 import java.awt.Graphics;
 
 public class LockedDoor extends Door { 
 
     /**
      * Constructor used to create a "Locked" door that can only be unlocked with a key.
      * @param room1 Room on one side of door
      * @param room2 Room on other side of door
      */
     public LockedDoor(Room room1, Room room2) {
         super(room1,room2);
         this.locked = true;
     }
   
     /**
      * Returns if the door has been unlocked
      * @return is Door Locked?
      */
     public boolean isLocked() {
         return locked;
     }
   
     /**
      * Unlock the door. We only support unlocking.
      */
     public void setUnlock() {        
         this.locked = false;
         unlockDing.play();
     }
   
     @Override
     public void enter(Maze maze) {
         if (!this.locked) {
             super.enter(maze);
         } else {
             lockedDing.play();
         }
     }
   
     /** 
      * Method handles drawing the Locked / Unlocked door
      * @param g Standard Graphics
      * @param x x coordinate
      * @param y y coordinate
      * @param w width of applet
      * @param h hight of applet
      */
     @Override
     public void draw(Graphics g, int x, int y, int w, int h) {   
         if (locked) {
             g.setColor(Wall.WALL_COLOR); 
             g.fillRect(x, y, w, h); 
 
             if (orientation == Orientation.VERTICAL) { 
                 y += 2 * w; h -= 4 * w;
             } else { 
                 x += 2 * h; w -= 4 * h; 
             }            
             
             g.setColor(Color.YELLOW);
             g.fillRect(x, y, w, h); 
             g.setColor(Color.black);
             g.drawRect(x, y, w, h);             
         } else {
             super.draw(g, x, y, w, h);
         }
     }
     
     protected boolean locked;  
     protected static AudioClip unlockDing = AudioUtility.getAudioClip("unlockdoor.wav"); 
     protected static AudioClip lockedDing = AudioUtility.getAudioClip("locked.au"); 
 }
 