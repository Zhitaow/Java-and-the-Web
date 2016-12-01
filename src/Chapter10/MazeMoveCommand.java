package Chapter10;

public class MazeMoveCommand implements UndoableCommand { 

  public MazeMoveCommand(Maze maze, Direction direction) { 
    this.maze = maze;
    this.direction = direction; 
  }

  public void execute() {
    maze.move(direction);
    maze.checkDesti();
  }

  public void undo() {
    maze.move(direction.opposite()); 
  }

  protected Maze maze; 
  protected Direction direction; 

}
