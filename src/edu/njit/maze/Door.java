package edu.njit.maze; 

 import java.awt.*; 
 import java.applet.AudioClip; 
 
 public class Door implements MapSite, DoorInterface { 
 
   public Door(Room room1, Room room2) { 
     this.room1 = room1;
     this.room2 = room2;
   }
 
   @Override
   public Object clone() throws CloneNotSupportedException { 
     return super.clone();
   }
 
   @Override
   public boolean isOpen() { 
     return open; 
   }
 
   @Override
   public void setOpen(boolean open) { 
     this.open = open;     
   }
 
   public void openDoor(){
     if(!open){
         openDoor.play();
         open = true;
     }      
   }
   
   @Override
   public void setRooms(Room room1, Room room2) { 
     this.room1 = room1;
     this.room2 = room2;
   }
 
   @Override
   public Orientation getOrientation() { 
     return orientation;
   }
 
   @Override
   public void setOrientation(Orientation orientation) { 
     this.orientation = orientation;
   }
 
   @Override
   public Room otherSideFrom(Room room) { 
     if (room != null) { 
       if (room == room1) {
         return room2; 
       } else if (room == room2) {
         return room1; 
       }
     }
     return null; 
   }
 
   @Override
   public void enter(Maze maze) {
     if (open) { 
       Room otherRoom = otherSideFrom(maze.getCurrentRoom());
       if (otherRoom != null) { 
         otherRoom.enter(maze); 
       }
     } else { 
       closedDoor.play();
     }
   }
 
   @Override
   public void draw(Graphics g, int x, int y, int w, int h) {
     g.setColor(Wall.WALL_COLOR); 
     g.fillRect(x, y, w, h); 
     if (orientation == Orientation.VERTICAL) { 
       y += 2 * w; h -= 4 * w;
     } else { 
       x += 2 * h; w -= 4 * h; 
     }
     if (open) { 
       g.setColor(Room.ROOM_COLOR);
       g.fillRect(x, y, w, h); 
     } else { 
       g.setColor(Color.red);
       g.fillRect(x, y, w, h); 
       g.setColor(Color.black);
       g.drawRect(x, y, w, h); 
     }    
   }
 
   protected Room room1;
   protected Room room2;
   protected boolean open; 
   protected Orientation orientation; 
   
   protected static AudioClip openDoor = AudioUtility.getAudioClip("opendoor.wav"); 
   protected static AudioClip closedDoor = AudioUtility.getAudioClip("knockdoor.au"); 
   
 }
 