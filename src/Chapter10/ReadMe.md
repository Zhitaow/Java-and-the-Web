# The Minion Maze Game
## Use Case

### Scope: 

the maze game application with enhanced features

### Major success scenario:
  1. Open and run the runnable jar file “MinionMazeGame.jar”
  2. The application will initialize a 3-by-3-matrix maze.
  3. Use “up”, “down”, “left”, “right” to control the character, and use “enter” to open the locked door.
  4. The goal is to move the “Minion” to the “Boss” (destination) within the step limit in each round.
  5. Once the player wins this round, the maze will initialize a new game with increased difficulty level (more rooms created).
  6. If the player fails to reach the destination within the step limit, the player will lose this round. A new game (at this level) will   initialize.
  
### Other Scenario: 
  If images cannot be loaded, draws colored circles instead.
  
### Enhanced Functionality:
  1. This maze game uses depth-first-search algorithm to randomly create, and solve the maze.
  2. User can choose any number of row and column (n by m rooms)
  3. User can change the display mode among the classic, Snow-White and Harry-Potter.
  4. User can get routing hints from the current room to destination.
  5. Add counts of step, and clearance mode making the game more fun and challenging.
