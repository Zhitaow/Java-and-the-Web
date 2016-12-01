/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 package edu.njit.maze;
 
 import java.applet.AudioClip;
 
 /**
  *
  * @author Jason
  */
 public class MazeOpenDoorCommand implements Command {
     Maze maze = null;
     
     public MazeOpenDoorCommand(Maze maze){
         this.maze = maze;
     }
 
     
     @Override
     public void execute(){
         // Check we have moved at least once before trying to open the door.
         if (maze.moves.size()>2){
             // Check if the last command was a move to a door
             Command cmd = (Command)maze.moves.get(maze.moves.size()-2);
             // If the last attempt was to unlock then we need to go back one command further.
             if (cmd instanceof MazeOpenDoorCommand){
                 cmd = (Command)maze.moves.get(maze.moves.size()-3);
             }
             if (cmd instanceof MazeMoveCommand){
                 Direction last = ((MazeMoveCommand)cmd).direction;
                 MapSite side = maze.getCurrentRoom().getSide(last);
                 // Is the instance a locked door?
                 if (side instanceof LockedDoor && ((LockedDoor)side).isLocked()){
                     // sound?
                     if(maze.keys > 0){
                         ((LockedDoor)side).setUnlock();
                         maze.keys--;
                     } else {
                         lockedDing.play();
                     }
                 } else if(side instanceof Door && !((Door)side).isOpen()){
                     ((Door)side).openDoor();
                 }
             }       
         }
     }
     protected static AudioClip lockedDing = AudioUtility.getAudioClip("locked.au"); 
 }
 