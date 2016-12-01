package Chapter10;

public class MazeOpenCommand implements Command {
     Maze maze;

     public MazeOpenCommand(Maze maze) {
         this.maze = maze;
     }

     @Override
     public void execute(){
         if (maze.moves.size() > 2) {
             Command command = (Command) maze.moves.get(maze.moves.size()-2);
             if (command instanceof MazeOpenCommand) {
                 command = (Command) maze.moves.get(maze.moves.size()-3);
             }
             if (command instanceof MazeMoveCommand) {
                 Direction last = ((MazeMoveCommand) command).direction;
                 MapSite side = maze.getCurrentRoom().getSide(last);
                 	if (side instanceof Door && !((Door) side).isOpen()) {
                     ((Door) side).openDoor();
                 }
             }       
         }
     }
 }