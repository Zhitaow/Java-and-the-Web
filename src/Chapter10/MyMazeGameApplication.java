package Chapter10;

import javax.swing.JFrame;

public class MyMazeGameApplication {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	    JFrame frame;
	    frame = new JFrame("The Minion Maze Game");
		MyMazeGame dummy = new MyMazeGame(frame);
	}
}
