package Chapter10;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyMazeGame extends JApplet implements ActionListener {
	private JMenuBar menuBar;
	private JMenu mnOption;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmGameSettings;
	private JSeparator separator;
	private JMenu mnAbout;
	private JMenuItem mntmVersion;
	private JPanel contentPane;
	private Maze maze;
	private MazeGameCreator creator;
	private Maze.MazePanel mazePanel;
	private SettingDialog dialog;
	private JFrame frame;
	private String creatorType;
	private JTextField textField;
	private JMenu mntmGameHints;
	private JMenuItem mntmGameHintsOn;
	private JMenuItem mntmGameHintsOff;
	
	public MyMazeGame (JFrame frame) {
		this.frame = frame;
		init();
		setVisible(true);
		start();
	    frame.setContentPane(this); 
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void init() {
		// set container size
		setSize(600, 550);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout()); 
		menuBar = createMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH); 
		textField = new JTextField("Steps Remaining: ");
		nextGame(0, 0, 3, 3);
		contentPane.add(mazePanel,BorderLayout.CENTER);
		contentPane.add(textField, BorderLayout.SOUTH);
		add(contentPane);
	}
	
	protected JMenuBar createMenuBar() {
		menuBar = new JMenuBar();
		contentPane.add(menuBar);
		
		mnOption = new JMenu("Option");
		menuBar.add(mnOption);
		
		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener(this);
		mnOption.add(mntmNewGame);
		
		mntmGameSettings = new JMenuItem("Settings");
		mntmGameSettings.addActionListener(this);
		mnOption.add(mntmGameSettings);
		
		mntmGameHints = new JMenu("Hints");
		mnOption.add(mntmGameHints);
		mntmGameHintsOn = new JMenuItem("On");
		mntmGameHints.add(mntmGameHintsOn);
		mntmGameHintsOn.addActionListener(this);
		mntmGameHintsOff = new JMenuItem("Off");
		mntmGameHints.add(mntmGameHintsOff);
		mntmGameHintsOff.addActionListener(this);
		
		separator = new JSeparator();
		menuBar.add(separator);
		
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mntmVersion = new JMenuItem("Version");
		mntmVersion.addActionListener(this);
		mnAbout.add(mntmVersion);
	    
	    return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Version")) {
			JOptionPane.showMessageDialog(null, 
				    "Minion Maze Game version 1.1\nAuthor: Zhitao Wang 2016\n"
				    + "Maze Game version 1.0\n Author: Xiaoping Jia", "About", 
				    JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getActionCommand().equals("New Game")) {
			nextGame(0, 0, 3, 3);
			contentPane.add(mazePanel, BorderLayout.CENTER);
			validate();
		} else if (e.getActionCommand().equals("Settings")) {
			if (dialog == null) {
				dialog = new SettingDialog(this, contentPane, mazePanel, maze, creator);
			}
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			// update the contents in the list
			List<Object> list = dialog.getList();
			// update the maze game
			if (list != null && !list.isEmpty()){
				try {
					if (mazePanel != null) {
						contentPane.remove(mazePanel);
						System.out.println("remove mazePanel");
					}
					
					contentPane = (JPanel) list.get(1);
					mazePanel = (Maze.MazePanel) list.get(2);
					maze = (Maze) list.get(3);
					System.out.println(list.get(4));
					if (list.get(4) instanceof SnowWhiteMazeGameCreator) {
						creator = (SnowWhiteMazeGameCreator) list.get(4);
						creatorType  = "SnowWhite";
						System.out.println("Set SnowWhite");
					} else if (list.get(4) instanceof HarryPotterMazeGameCreator){
						creator = (HarryPotterMazeGameCreator) list.get(4);
						creatorType  = "HarryPotter";
						System.out.println("Set HarryPotter");
					} else if (list.get(4) instanceof MazeGameCreator) {
						creator = (MazeGameCreator) list.get(4);
						creatorType  = "Default";
						System.out.println("Set Default");
					}
					
					maze.setGame(this);
//					nextGame(0, 0, getCol(), getRow());
					setSize(mazePanel.getPreferredSize());
					frame.setContentPane(this); 
				    frame.pack();
					repaint();
					revalidate();
				} catch (Exception e2) {}
			}
			validate();
		} else if (e.getActionCommand().equals("On")) {
			/*
			 *  get current location of the minion
			 *  and plan the route to the destination
			 */
			Room room = maze.getCurrentRoom();
			int num = room.getRoomNumber();
			int col = num / creator.getCol();
			int row = num % creator.getCol() - 1;
			creator.solve(row, col, creator.getCol()-1, creator.getRow()-1);
			validate();
			repaint();
		} else if (e.getActionCommand().equals("Off")) {
			if (creator != null) {
				creator.hideSolution();
				repaint();
			}
		}
	}
	
	public int getCol() {
		if (creator != null) {
			return creator.getCol();
		}
		return 0;
	}
	
	public int getRow() {
		if (creator != null) {
			return creator.getRow();
		}
		return 0;
	}
	
	
	public void nextGame(int x0, int y0, int x1, int y1) {
		
		if (creator == null) {
			creatorType = "Default";
		}
		if (creatorType.equals("HarryPotter")) {
			creator = new HarryPotterMazeGameCreator();
			System.out.println("Create HarryPotter");
		} else if (creatorType.equals("SnowWhite")) {
			creator = new SnowWhiteMazeGameCreator();
			System.out.println("Create SnowWhite");
		} else if (creatorType.equals("Default")) {
			creator = new MazeGameCreator();
			System.out.println("Create Default");
		}
		
		maze = creator.createMaze(x0, y0, x1, y1);
		maze.setCurrentRoom(1);
		maze.setDestiRoom(maze.countRoom());
		if (mazePanel != null) {
			contentPane.remove(mazePanel);
		}
		mazePanel = new Maze.MazePanel(maze);
		contentPane.add(mazePanel, BorderLayout.CENTER);
		maze.setGame(this);
		updateStepMessage(maze.getRemain());
		repaint();
		revalidate();
		setSize(mazePanel.getPreferredSize());
		frame.repaint();
		frame.setContentPane(this); 
	    frame.pack();  
	}
	
	public void updateStepMessage(int nstep) {
		textField.setText("Total steps in remain: " + nstep);
	}
}
