package Chapter10;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import Chapter10.Maze.MazePanel;

@SuppressWarnings("serial")
public class SettingDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumberOfRow;
	private JTextField textField;
	private JTextField txtNumberOfColumn;
	private JTextField textField_1;
	private JTextField textField_2;
	private JRadioButton rdbtnClassic;
	private JTextField textField_4;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private MazeGameCreator creator;
	private Maze maze;
	private Maze.MazePanel mazePanel;
	private JRadioButton rdbtnHarryPotter;
	private JRadioButton rdbtnSnowWhite;
	private JPanel contentPane;
	private MyMazeGame myMazeGame;
	private List<Object> list = new ArrayList<>();
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			SettingDialog dialog = new SettingDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public SettingDialog(MyMazeGame myMazeGame, 
			JPanel contentPane, MazePanel mazePanel, 
			Maze maze, MazeGameCreator creator) {
		/* this is critical, do not execute line of code
		 * until the JDialog is closed.
		 */
		setModal(true);
		this.maze = maze;
		this.creator = creator;
		this.contentPane = contentPane;
		this.mazePanel = mazePanel;
		this.myMazeGame = myMazeGame;
		putList();	
		setBounds(100, 100, 393, 199);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(
				new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			txtNumberOfRow = new JTextField();
			txtNumberOfRow.setEditable(false);
			txtNumberOfRow.setBorder(null);
			txtNumberOfRow.setText("# of Row");
			contentPanel.add(txtNumberOfRow);
			txtNumberOfRow.setColumns(10);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setText(this.creator.getRow()+"");
			textField.setColumns(10);
		}
		
		{
			textField_4 = new JTextField();
			textField_4.setEditable(false);
			textField_4.setColumns(10);
			textField_4.setBorder(null);
			contentPanel.add(textField_4);
		}
		{
			txtNumberOfColumn = new JTextField();
			txtNumberOfColumn.setText("# of Column");
			txtNumberOfColumn.setEditable(false);
			txtNumberOfColumn.setColumns(10);
			txtNumberOfColumn.setBorder(null);
			contentPanel.add(txtNumberOfColumn);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setText(this.creator.getCol()+"");
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBorder(null);
			contentPanel.add(textField_2);
		}
		ButtonGroup fontFamilyGroup = new ButtonGroup();
		{
			textField_3 = new JTextField();
			textField_3.setEditable(false);
			textField_3.setColumns(10);
			textField_3.setBorder(null);
			contentPanel.add(textField_3);
		}
		{
			textField_5 = new JTextField();
			textField_5.setEditable(false);
			textField_5.setColumns(10);
			textField_5.setBorder(null);
			contentPanel.add(textField_5);
		}
		{
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setColumns(10);
			textField_6.setBorder(null);
			contentPanel.add(textField_6);
		}
		{
			rdbtnClassic = new JRadioButton("Classic");
			rdbtnClassic.setSelected(true);
			contentPanel.add(rdbtnClassic);
			fontFamilyGroup.add(rdbtnClassic);
		}
		{
			rdbtnHarryPotter = new JRadioButton("Harry Potter");
			contentPanel.add(rdbtnHarryPotter);
			fontFamilyGroup.add(rdbtnHarryPotter);
		}
		{
			rdbtnSnowWhite = new JRadioButton("Snow White");
			contentPanel.add(rdbtnSnowWhite);
			fontFamilyGroup.add(rdbtnSnowWhite);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("OK")) {
			try {
				int row = (int) Integer.parseInt(textField.getText());
				int col = (int) Integer.parseInt(textField_1.getText());
				if (rdbtnClassic.isSelected()) {
					creator = new MazeGameCreator();
					System.out.println("Setting set default");
				} else if (rdbtnHarryPotter.isSelected()) {
					creator = new HarryPotterMazeGameCreator();
					System.out.println("Setting set HarryPotter");
				} else if (rdbtnSnowWhite.isSelected()) {
					creator = new SnowWhiteMazeGameCreator();
					System.out.println("Setting set SnowWhite");
				}
				contentPane.remove(mazePanel);
				maze = creator.createMaze(0, 0, row, col);
				maze.setCurrentRoom(1);
				maze.setDestiRoom(maze.countRoom());
				mazePanel = new Maze.MazePanel(maze);
				contentPane.add(mazePanel, BorderLayout.CENTER);
				myMazeGame.validate();
				list = new ArrayList<Object>();
				putList();
				
			} catch (Exception e1) {System.out.println(e1);}
			setVisible(false);
		} else if (e.getActionCommand().equals("Cancel")) {
			setVisible(false);
		}
	}
	
	private void putList() {
		list.add(this.myMazeGame);
		list.add(this.contentPane);
		list.add(this.mazePanel);
		list.add(this.maze);
		list.add(this.creator);
	}
	
	public List<Object> getList() {
		return list;
	}
	
	public void updateList(MyMazeGame myMazeGame, JPanel contentPane, 
			MazePanel mazePanel, Maze maze, MazeGameCreator creator) {
		this.myMazeGame = myMazeGame;
		this.contentPane = contentPane;
		this.mazePanel = mazePanel;
		this.maze = maze;
		this.creator = creator;
	}

}
