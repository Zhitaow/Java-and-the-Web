package Chapter10;

import java.util.ArrayList;
import java.util.List;

/*
 *  Build a Maze game. 
 *
 *  This design uses Factory Methods design pattern. 
 *
 *  Compare this design with the one using Abstract Factory design pattern:
 *    MazeGameAbstractFactory   
 *
 */
public class MazeGameCreator { 

  private int[][] visited, visitedSolution;
//  private List<Room> solRooms = new ArrayList<>();
  private List<Node> solution = new ArrayList<>();
  private Maze maze = makeMaze();
  private int nrow;
  private int ncol;
  private boolean isSolved = false;
  
  
  // This method must not be static
  public Maze bruteForceCreateMaze() { 
	Maze maze = makeMaze();
    return maze;
  } 
  
  /* initialize the maze game with all 
     rooms being enclosed by walls
  */ 
  private void initMaze(int nrow, int ncol) {
	  this.nrow = nrow;
	  this.ncol = ncol;
      visited = new int[nrow][ncol];
      int curRmNum = 1;
      for (int i = 0; i < nrow; i++) {
    	  for (int j = 0; j < ncol; j++) {
    		  visited[i][j] = 0;
    		  maze.addRoom(makeRoom(curRmNum));
    		  curRmNum++;
    	  }
      }
  }
  
  public int getRow() {
	  return nrow;
  }
  
  public int getCol() {
	  return ncol;
  }
  public Maze createMaze(int x, int y, int nrow, int ncol) {
	  initMaze(nrow, ncol);
	  visited[y][x] = 1;
	  buildMazeDFS(x, y, nrow, ncol);
	  solve(x,y, nrow-1, ncol-1);
	  System.out.println("solution size: " + solution.size());
	  /*
	   *  give additional 10% of total number of steps 
	   *   solved by route planning algorithm 
	   */
	  maze.setRemain(solution.size() + 2 + (int) (solution.size()*0.1));
	  hideSolution();
	  System.out.println("Remaining step: " + maze.getRemain());
	  return maze;
  }
  
  private boolean isDeadend(int x, int y, int nrow, int ncol) {
	  if (x+1 < ncol && visited[y][x+1] == 0) {
		  return false;
	  }
	  if (x-1 > 0 && visited[y][x-1] == 0) {
		  return false;
	  }
	  if (y+1 < nrow && visited[y+1][x] == 0) {
		  return false;
	  }
	  if (y-1 > 0 && visited[y-1][x] == 0) {
		  return false;
	  }
	  return true;
  }
  
  private void buildMazeDFS(int x, int y, int nrow, int ncol) {
	  if (isDeadend(x, y, nrow, ncol)) {
		  return;
	  }
	  while(!isDeadend(x, y, nrow, ncol)) {
		  int ranNum = (int)(Math.random() * 4); 
		  if (ranNum == 0 && x+1 < ncol && visited[y][x+1] == 0) {
			  helper(x, y, x+1, y, nrow, ncol);
			  buildMazeDFS(x+1, y, nrow, ncol);
		  } else if (ranNum == 1 && x-1 > 0 && visited[y][x-1] == 0) {
			  helper(x, y, x-1, y, nrow, ncol);
			  buildMazeDFS(x-1, y, nrow, ncol);
		  } else if (ranNum == 2 && y+1 < nrow && visited[y+1][x] == 0) {
			  helper(x, y, x, y+1, nrow, ncol);
			  buildMazeDFS(x, y+1, nrow, ncol);
		  } else if (ranNum == 3 && y-1 > 0 && visited[y-1][x] == 0) {
			  helper(x, y, x, y-1, nrow, ncol);
			  buildMazeDFS(x, y-1, nrow, ncol);
		  }
	  }
	  
  }
  
  private void helper(int x0, int y0, int x1, int y1, int nrow, int ncol) {
	  // set as visited
	  visited[y1][x1] = 1;
	  // build door
	  Room room1 = maze.findRoom(1+ y0 * ncol + x0);
	  Room room2 = maze.findRoom(1+ y1 * ncol + x1);
	  Door door = makeDoor(room1, room2);
	  // evenly set the chance of the door is lock or unlocked
	  int ranNum = (int)(Math.random() * 2); 
	  if (ranNum == 0) {
		  door.setOpen(false);
	  } else {
		  door.setOpen(true);
	  }
	  if (x0 - x1 == 1) {
		  room2.setSide(Direction.EAST, door);
		  room1.setSide(Direction.WEST, door);
	  } else if (x1 - x0 == 1) {
		  room1.setSide(Direction.EAST, door);
		  room2.setSide(Direction.WEST, door);
	  } else if (y0 - y1 == 1) {
		  room2.setSide(Direction.SOUTH, door);
		  room1.setSide(Direction.NORTH, door);
	  } else if (y1 - y0 == 1) {
		  room1.setSide(Direction.SOUTH, door);
		  room2.setSide(Direction.NORTH, door);
	  }
  }

  
  public void solve(int x0, int y0, int x1, int y1) {
	  visitedSolution = new int[nrow][ncol];
	  for (int x = 0; x < ncol; x++) {
		  for (int y = 0; y < nrow; y++) {
			  visitedSolution[y][x] = 0;
		  }
	  }
	  isSolved = false;
	  System.out.println(ncol + ", " + nrow); 
	  if (x0 >= 0 && y0 >= 0 && x1 < ncol && y1 < nrow) {
		  solveMaze(x0, y0, x1, y1);
	  }
	  
	  for (Node sol:solution) {
		  sol.getRoom().setHint(true);
		  sol.getRoom().setHintDir(sol.getDir());
	  }
	  System.out.println("Maze has been solved.");
  }
  
  private void solveMaze(int x0, int y0, int x1, int y1) {
	  // base case
	  if (x0 == x1 && y0 == y1) {
		  isSolved = true;
		  // remove the first room
		  solution.remove(0);
		  return;
	  } else if (x0 >= ncol || y0 >= nrow || x0 < 0 || y0 < 0) {
		  return;
	  }

	  // recursive rule
	  Room room = maze.findRoom(1+ y0 * ncol + x0);
	  // add current room as temporary solution, probe at four directions
	  Node node = null;
	  visitedSolution[y0][x0] = 1;
	  /*
	   * Determine whether to traverse in all four directions
	   */
	  if (!isSolved && x0+1 < ncol && visitedSolution[y0][x0+1] == 0 
			  && room.getSide(Direction.EAST) instanceof Door) {
		  node = new Node(room, Direction.EAST);
		  solution.add(node);
		  solveMaze(x0+1, y0, x1, y1);
	  }
	  if (!isSolved && y0+1 < nrow && visitedSolution[y0+1][x0] == 0
			  && room.getSide(Direction.SOUTH) instanceof Door) {
		  node = new Node(room, Direction.SOUTH);
		  solution.add(node);
		  solveMaze(x0, y0+1, x1, y1);
	  }
	  if (!isSolved && x0-1 >= 0 && visitedSolution[y0][x0-1] == 0
			  && room.getSide(Direction.WEST) instanceof Door) {
		  node = new Node(room, Direction.WEST);
		  solution.add(node);
		  solveMaze(x0-1, y0, x1, y1);
	  }
	  if (!isSolved && y0-1 >= 0 && visitedSolution[y0-1][x0] == 0
			  && room.getSide(Direction.NORTH) instanceof Door) {
		  node = new Node(room, Direction.NORTH);
		  solution.add(node);
		  solveMaze(x0, y0-1, x1, y1);
	  }
	  /*
	   *  if current room is a dead-end, 
	   *  remove last current room from the solution
	   */
	  if (!isSolved) {
		  solution.remove(solution.size()-1);
	  }
	  
  }
  
  public void hideSolution() {
	  if (solution != null) {
		  for (Node node: solution) {
			  node.getRoom().setHint(false);
		  }
	  }
  }
  
  /*
   *  create an inner class of Node object, 
   *  containing the room and the flow direction of the solution
   */
  public class Node {
	  private Room room;
	  private Direction direction;
	  public Node(Room room, Direction direction) {
		  this.room = room;
		  this.direction = direction;
	  }
	  
	  public Room getRoom() {
		  return room;
	  }
	  
	  public Direction getDir() {
		  return direction;
	  }
	  
  }
  
  public Maze makeMaze() {
    return new Maze(); 
  }

  public Wall makeWall() { 
    return new Wall(); 
  }
  
  private Room makeRoom(int roomNum){               
      Room room = new Room(roomNum);
      // initialize all walls as present
      room.setSide(Direction.EAST, makeWall());
      room.setSide(Direction.WEST, makeWall());
      room.setSide(Direction.NORTH, makeWall());
      room.setSide(Direction.SOUTH, makeWall());
      return room;
  }

  public Door makeDoor(Room room1, Room room2) { 
    return new Door(room1, room2); 
  }

}
