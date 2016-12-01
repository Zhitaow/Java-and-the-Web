 /*
  * To change this template, choose Tools | Templates
  * and open the template in the editor.
  */
 
 package edu.njit.maze;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Random;
 
 /**
  *
  */
 public class MazeGameCreator {
     private int rows = 4;
     private int cols = 4;
     private Integer[][] cells; 
     private Random r = new Random();   
     // Number of availible doors and keys.
     private int keys = 4;
     private int locks = 4;
     
     public MazeGameCreator() {};
     
     public MazeGameCreator(Integer cols, Integer rows){
         this.cols = cols;
         this.rows = rows;
         int k = cols * rows / 12;
         keys = k;
         locks = k;
     }
     
     public Maze createMaze() {
         Maze maze = makeMaze();
         cells = new Integer[rows][cols];
         // Fill cells and use to figure out the path.
         int r = 1;
         for(int i = 0; i < rows; i++){
             for (int j = 0; j < cols; j++){
                 cells[i][j] = 0;
                 maze.addRoom(makeRoom(r++));
             }
         }
         // Set the ending room.
         maze.findRoom(r - 1).setEnd();
         carvePassageFrom(0, 0, cells, maze);                                
         return maze;
     }
     
  
     
     public Maze makeMaze() {
         return new Maze(); 
     }
      
      public Wall makeWall() { 
          return new Wall(); 
      }
  
  //    public Room makeRoom(int roomNumber) { 
  //        return new Room(roomNumber); 
  //    }
  
      public Door makeDoor(Room room1, Room room2) { 
          return new Door(room1, room2); 
      }    
      
      private void carvePassageFrom(int currentX, int currentY, Integer[][] grid, Maze maze){
          //Direction[] directions = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
          List<Direction> directions = new ArrayList<>();
          directions.add(Direction.EAST);
          directions.add(Direction.NORTH);
          directions.add(Direction.SOUTH);
          directions.add(Direction.WEST);
          
          while (!directions.isEmpty()){
          //for (Direction dir : directions){
              // We need to grab a random direction here.
              int rand = r.nextInt(directions.size());
              Direction dir = directions.remove(rand);
              // Find the directions
              int nextX = currentX + directionX.get(dir);
              int nextY = currentY + directionY.get(dir);
              
              // If the next position is outside of the grid or been visited already we can skip to the next item
              if (isOutOfBounds(nextX, nextY, grid) || grid[nextX][nextY] != 0){
                  continue;
              }
                                 
              // Find the current room that we are working on.
              int rn = (currentY * grid.length) + currentX;            
              Room cr = maze.findRoom(rn + 1);
              
              // Get the next room so we can create doors.
              rn = (nextY * grid.length) + nextX;            
              Room nr = maze.findRoom(rn + 1);
             
             // Create a door between the rooms
             DoorInterface door = new Door(cr,nr);
             boolean locked = false;
             
             // Some doors are locked, others should be open
             if(r.nextInt(1000) < 400 && locks > 0 && keys < locks){
                 door = new LockedDoor(cr,nr);
                 locked = true;
                 locks--;
             } else if(r.nextInt(100) > 50){
                 door.setOpen(true);
             }  
             
             // sprinkle keys around rooms.
             if(cr.roomNumber > 1 && !locked && keys > 0 && r.nextInt(1000) < 350){   
                 cr.setKey(true);
                 keys--;
             }
             
             cr.setSide(dir, (MapSite)door);
             nr.setSide(dir.opposite(), (MapSite)door);
             
             // Readd the rooms to the grid
             maze.setRoom(cr);
             maze.setRoom(nr);
             
             // Store the room number so we can use it to find the next and previous rooms from the maze
             grid[currentX][currentY] = dir.getOrdinal();
             grid[nextX][nextY] = dir.opposite().getOrdinal();
             
             carvePassageFrom(nextX, nextY, grid, maze);
         }
         
     }
     
     private static final Map<Direction, Integer> directionX;
     static  
     {
         directionX = new HashMap<>();
         directionX.put(Direction.NORTH, 0);
         directionX.put(Direction.SOUTH, 0);
         directionX.put(Direction.EAST, 1);
         directionX.put(Direction.WEST, -1);        
     }
 
     private static final Map<Direction, Integer> directionY;
     static  
     {
         directionY = new HashMap<>();
         directionY.put(Direction.NORTH, -1);
         directionY.put(Direction.SOUTH, 1);
         directionY.put(Direction.EAST, 0);
         directionY.put(Direction.WEST, 0);        
     }      
     
     private boolean isOutOfBounds(int x, int y, Integer[][] grid){
         if (x < 0 || x > grid.length - 1){
             return true;
         }
         if (y < 0 || y > grid[0].length - 1){
             return true;
         }
         return false;
     }
     
     
     private Room makeRoom(int roomNumber){               
         Room room = new Room(roomNumber);
         room.setSide(Direction.NORTH, new Wall());
         room.setSide(Direction.SOUTH, new Wall());
         room.setSide(Direction.EAST, new Wall());
         room.setSide(Direction.WEST, new Wall());                                    
 
         return room;
     }
 }
 