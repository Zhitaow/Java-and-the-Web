//package Chapter08;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * The simple calculator layout
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
public class SimpleLayout implements CalculatorLayout {

	private JTextField logTextField;
	private JTextField dummyTextField;
	private JTextField calTextField;	
	private List<JButton> btnList = new ArrayList<JButton>();
	private JTextPane logTextPane;
	private JComboBox<String> displayModeCB;
	String[] displayMode = {"Simple", "Scientific"};
	// add all button objects to the list from the contentPane
	public void collectBtn(JPanel contentPane) {
		Component[] components =  contentPane.getComponents();
		System.out.println(components.length);
		for (Component component:components) {
			if (component.getClass().equals(JButton.class)) {
				btnList.add((JButton) component);
				
			}
		}
	}
	
	// find the button with the label in the list
	public JButton getBtn(String label) {
		for (JButton btn:btnList) {
			if (btn.getText().equals(label)) {
				return btn;
			}
		}
		System.out.println("Button '" + label + "' not found!");
		return null;
	}
	
	public List<JButton> getBtn() {
		return btnList;
	}
	
	// set the text into the display
	public void setDisplayText(String message) {
		calTextField.setText(message);
	}
	// get text from the display
	public String getDisplayText() {
		return calTextField.getText();
	}
	
	// set the text into the history log
	public void setLogText(String message) {
		logTextPane.setText(message);
	}
	// get text from the display
	public String getLogText() {
		return logTextPane.getText();
	}
	
	public String getSelectedItem() {
		return (String) displayModeCB.getSelectedItem();
	}
	
	public JComboBox<String> getJComboBox() {
		return displayModeCB;
	}
	
	// set up the layout of scientific calculator
	public void setLayout(JPanel contentPane) {
		JPanel rightPanel = new JPanel();
		rightPanel.setSize(new Dimension(250, 400));
		rightPanel.setPreferredSize(new Dimension(250, 400));
		contentPane.add(rightPanel, BorderLayout.EAST);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[]{0, 0};
		gbl_rightPanel.rowHeights = new int[]{0, 0, 0};
		gbl_rightPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		rightPanel.setLayout(gbl_rightPanel);
		
		logTextField = new JTextField();
		logTextField.setBorder(null);
		logTextField.setEditable(false);
		logTextField.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		logTextField.setBackground(UIManager.getColor("Button.background"));
		logTextField.setSize(new Dimension(250, 28));
		logTextField.setPreferredSize(new Dimension(250, 30));
		logTextField.setText("History Log");
		GridBagConstraints gbc_logTextField = new GridBagConstraints();
		gbc_logTextField.insets = new Insets(0, 0, 5, 0);
		gbc_logTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_logTextField.gridx = 0;
		gbc_logTextField.gridy = 0;
		rightPanel.add(logTextField, gbc_logTextField);
		logTextField.setColumns(10);
				
		logTextPane = new JTextPane();
		logTextPane.setBorder(null);
		logTextPane.setEditable(false);
		logTextPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		logTextPane.setForeground(Color.gray);
		logTextPane.setBackground(UIManager.getColor("Button.background"));
		logTextPane.setPreferredSize(new Dimension(250, 400));
		logTextPane.setSize(new Dimension(250, 400));
		//logScrollPane.setColumnHeaderView(logTextPane);
		
		JScrollPane logScrollPane = new JScrollPane(logTextPane);
		logScrollPane.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_logScrollPane = new GridBagConstraints();
		gbc_logScrollPane.fill = GridBagConstraints.BOTH;
		gbc_logScrollPane.gridx = 0;
		gbc_logScrollPane.gridy = 1;
		rightPanel.add(logScrollPane, gbc_logScrollPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(350, 400));
		contentPane.add(leftPanel, BorderLayout.CENTER);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel displayModePanel = new JPanel();
		displayModePanel.setPreferredSize(new Dimension(350, 30));
		displayModePanel.setSize(new Dimension(350, 30));
		leftPanel.add(displayModePanel, BorderLayout.NORTH);
		GridBagLayout gbl_displayModePanel = new GridBagLayout();
		gbl_displayModePanel.columnWidths = new int[]{0, 0, 0};
		gbl_displayModePanel.rowHeights = new int[]{0, 0};
		gbl_displayModePanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_displayModePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		displayModePanel.setLayout(gbl_displayModePanel);
		displayModeCB = new JComboBox<>(displayMode);
		displayModeCB.setPreferredSize(new Dimension(50, 30));
		GridBagConstraints gbc_displayModeCB = new GridBagConstraints();
		gbc_displayModeCB.insets = new Insets(0, 0, 0, 5);
		gbc_displayModeCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_displayModeCB.gridx = 0;
		gbc_displayModeCB.gridy = 0;
		displayModePanel.add(displayModeCB, gbc_displayModeCB);
		
		dummyTextField = new JTextField();
		dummyTextField.setBackground(UIManager.getColor("Button.background"));
		dummyTextField.setPreferredSize(new Dimension(250, 30));
		dummyTextField.setVisible(false);
		GridBagConstraints gbc_dummyTextField = new GridBagConstraints();
		gbc_dummyTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dummyTextField.gridx = 1;
		gbc_dummyTextField.gridy = 0;
		displayModePanel.add(dummyTextField, gbc_dummyTextField);
		dummyTextField.setColumns(10);
		
		JPanel calPanel = new JPanel();
		leftPanel.add(calPanel, BorderLayout.CENTER);
		GridBagLayout gbl_calPanel = new GridBagLayout();
		gbl_calPanel.columnWidths = new int[]{0, 0};
		gbl_calPanel.rowHeights = new int[]{0, 0, 0};
		gbl_calPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_calPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		calPanel.setLayout(gbl_calPanel);
		
		calTextField = new JTextField();
		calTextField.setBorder(null);
		calTextField.setEditable(false);
		calTextField.setPreferredSize(new Dimension(350, 80));
		calTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		calTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_calTextField = new GridBagConstraints();
		gbc_calTextField.insets = new Insets(0, 0, 5, 0);
		gbc_calTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_calTextField.gridx = 0;
		gbc_calTextField.gridy = 0;
		calPanel.add(calTextField, gbc_calTextField);
		calTextField.setColumns(10);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(null);
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.VERTICAL;
		gbc_btnPanel.gridx = 0;
		gbc_btnPanel.gridy = 1;
		calPanel.add(btnPanel, gbc_btnPanel);
		GridBagLayout gbl_btnPanel = new GridBagLayout();
		gbl_btnPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_btnPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_btnPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_btnPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		btnPanel.setLayout(gbl_btnPanel);
		
		JButton btn00 = new JButton("\u21E6");
		btn00.setPreferredSize(new Dimension(55, 36));
		btn00.setBorder(null);
		btn00.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn00 = new GridBagConstraints();
		gbc_btn00.fill = GridBagConstraints.VERTICAL;
		gbc_btn00.insets = new Insets(0, 0, 5, 5);
		gbc_btn00.gridx = 0;
		gbc_btn00.gridy = 0;
		btnPanel.add(btn00, gbc_btn00);
		
		JButton btn01 = new JButton("\u221Ax");
		btn01.setPreferredSize(new Dimension(55, 36));
		btn01.setBorder(null);
		btn01.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn01 = new GridBagConstraints();
		gbc_btn01.insets = new Insets(0, 0, 5, 5);
		gbc_btn01.gridx = 1;
		gbc_btn01.gridy = 0;
		btnPanel.add(btn01, gbc_btn01);
		
		JButton btn02 = new JButton("x\u00B2");
		btn02.setPreferredSize(new Dimension(55, 36));
		btn02.setBorder(null);
		btn02.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn02 = new GridBagConstraints();
		gbc_btn02.fill = GridBagConstraints.VERTICAL;
		gbc_btn02.insets = new Insets(0, 0, 5, 5);
		gbc_btn02.gridx = 2;
		gbc_btn02.gridy = 0;
		btnPanel.add(btn02, gbc_btn02);
		
		JButton btn03 = new JButton("1/x");
		btn03.setBackground(Color.LIGHT_GRAY);
		btn03.setPreferredSize(new Dimension(55, 36));
		btn03.setBorder(null);
		GridBagConstraints gbc_btn03 = new GridBagConstraints();
		gbc_btn03.fill = GridBagConstraints.VERTICAL;
		gbc_btn03.insets = new Insets(0, 0, 5, 5);
		gbc_btn03.gridx = 3;
		gbc_btn03.gridy = 0;
		btnPanel.add(btn03, gbc_btn03);
		
		JButton btn10 = new JButton("CE");
		btn10.setPreferredSize(new Dimension(55, 36));
		btn10.setBorder(null);
		btn10.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn10 = new GridBagConstraints();
		gbc_btn10.fill = GridBagConstraints.VERTICAL;
		gbc_btn10.insets = new Insets(0, 0, 5, 5);
		gbc_btn10.gridx = 0;
		gbc_btn10.gridy = 1;
		btnPanel.add(btn10, gbc_btn10);
		
		JButton btn11 = new JButton("C");
		btn11.setPreferredSize(new Dimension(55, 36));
		btn11.setBorder(null);
		btn11.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn11 = new GridBagConstraints();
		gbc_btn11.insets = new Insets(0, 0, 5, 5);
		gbc_btn11.gridx = 1;
		gbc_btn11.gridy = 1;
		btnPanel.add(btn11, gbc_btn11);
		
		JButton btn12 = new JButton("DEL");
		btn12.setPreferredSize(new Dimension(55, 36));
		btn12.setBorder(null);
		btn12.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn12 = new GridBagConstraints();
		gbc_btn12.fill = GridBagConstraints.VERTICAL;
		gbc_btn12.insets = new Insets(0, 0, 5, 5);
		gbc_btn12.gridx = 2;
		gbc_btn12.gridy = 1;
		btnPanel.add(btn12, gbc_btn12);
		
		JButton btn13 = new JButton("\u00f7");
		btn13.setPreferredSize(new Dimension(55, 36));
		btn13.setBorder(null);
		btn13.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn13 = new GridBagConstraints();
		gbc_btn13.fill = GridBagConstraints.VERTICAL;
		gbc_btn13.insets = new Insets(0, 0, 5, 5);
		gbc_btn13.gridx = 3;
		gbc_btn13.gridy = 1;
		btnPanel.add(btn13, gbc_btn13);
				
		JButton btn20 = new JButton("7");
		btn20.setPreferredSize(new Dimension(55, 36));
		btn20.setBorder(null);
		btn20.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn20 = new GridBagConstraints();
		gbc_btn20.fill = GridBagConstraints.VERTICAL;
		gbc_btn20.insets = new Insets(0, 0, 5, 5);
		gbc_btn20.gridx = 0;
		gbc_btn20.gridy = 2;
		btnPanel.add(btn20, gbc_btn20);
		
		JButton btn21 = new JButton("8");
		btn21.setPreferredSize(new Dimension(55, 36));
		btn21.setBorder(null);
		btn21.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn21 = new GridBagConstraints();
		gbc_btn21.insets = new Insets(0, 0, 5, 5);
		gbc_btn21.gridx = 1;
		gbc_btn21.gridy = 2;
		btnPanel.add(btn21, gbc_btn21);
		
		JButton btn22 = new JButton("9");
		btn22.setPreferredSize(new Dimension(55, 36));
		btn22.setBorder(null);
		btn22.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn22 = new GridBagConstraints();
		gbc_btn22.fill = GridBagConstraints.VERTICAL;
		gbc_btn22.insets = new Insets(0, 0, 5, 5);
		gbc_btn22.gridx = 2;
		gbc_btn22.gridy = 2;
		btnPanel.add(btn22, gbc_btn22);
		
		JButton btn23 = new JButton("\u00D7");
		btn23.setPreferredSize(new Dimension(55, 36));
		btn23.setBorder(null);
		btn23.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn23 = new GridBagConstraints();
		gbc_btn23.fill = GridBagConstraints.VERTICAL;
		gbc_btn23.insets = new Insets(0, 0, 5, 5);
		gbc_btn23.gridx = 3;
		gbc_btn23.gridy = 2;
		btnPanel.add(btn23, gbc_btn23);
		
		JButton btn30 = new JButton("4");
		btn30.setPreferredSize(new Dimension(55, 36));
		btn30.setBorder(null);
		btn30.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn30 = new GridBagConstraints();
		gbc_btn30.fill = GridBagConstraints.VERTICAL;
		gbc_btn30.insets = new Insets(0, 0, 5, 5);
		gbc_btn30.gridx = 0;
		gbc_btn30.gridy = 3;
		btnPanel.add(btn30, gbc_btn30);
		
		JButton btn31 = new JButton("5");
		btn31.setPreferredSize(new Dimension(55, 36));
		btn31.setBorder(null);
		btn31.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn31 = new GridBagConstraints();
		gbc_btn31.fill = GridBagConstraints.VERTICAL;
		gbc_btn31.insets = new Insets(0, 0, 5, 5);
		gbc_btn31.gridx = 1;
		gbc_btn31.gridy = 3;
		btnPanel.add(btn31, gbc_btn31);
		
		JButton btn32 = new JButton("6");
		btn32.setPreferredSize(new Dimension(55, 36));
		btn32.setBorder(null);
		btn32.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn32 = new GridBagConstraints();
		gbc_btn32.fill = GridBagConstraints.VERTICAL;
		gbc_btn32.insets = new Insets(0, 0, 5, 5);
		gbc_btn32.gridx = 2;
		gbc_btn32.gridy = 3;
		btnPanel.add(btn32, gbc_btn32);
		
		JButton btn33 = new JButton("\u2212");
		btn33.setPreferredSize(new Dimension(55, 36));
		btn33.setBorder(null);
		btn33.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn33 = new GridBagConstraints();
		gbc_btn33.fill = GridBagConstraints.VERTICAL;
		gbc_btn33.insets = new Insets(0, 0, 5, 5);
		gbc_btn33.gridx = 3;
		gbc_btn33.gridy = 3;
		btnPanel.add(btn33, gbc_btn33);
		
		JButton btn40 = new JButton("1");
		btn40.setPreferredSize(new Dimension(55, 36));
		btn40.setBorder(null);
		btn40.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn40 = new GridBagConstraints();
		gbc_btn40.fill = GridBagConstraints.VERTICAL;
		gbc_btn40.insets = new Insets(0, 0, 5, 5);
		gbc_btn40.gridx = 0;
		gbc_btn40.gridy = 4;
		btnPanel.add(btn40, gbc_btn40);
		
		JButton btn41 = new JButton("2");
		btn41.setPreferredSize(new Dimension(55, 36));
		btn41.setBorder(null);
		btn41.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn41 = new GridBagConstraints();
		gbc_btn41.fill = GridBagConstraints.VERTICAL;
		gbc_btn41.insets = new Insets(0, 0, 5, 5);
		gbc_btn41.gridx = 1;
		gbc_btn41.gridy = 4;
		btnPanel.add(btn41, gbc_btn41);
	
		JButton btn42 = new JButton("3");
		btn42.setPreferredSize(new Dimension(55, 36));
		btn42.setBorder(null);
		btn42.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn42 = new GridBagConstraints();
		gbc_btn42.fill = GridBagConstraints.VERTICAL;
		gbc_btn42.insets = new Insets(0, 0, 5, 5);
		gbc_btn42.gridx = 2;
		gbc_btn42.gridy = 4;
		btnPanel.add(btn42, gbc_btn42);
		
		JButton btn43 = new JButton("+");
		btn43.setPreferredSize(new Dimension(55, 36));
		btn43.setBorder(null);
		btn43.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn43 = new GridBagConstraints();
		gbc_btn43.fill = GridBagConstraints.VERTICAL;
		gbc_btn43.insets = new Insets(0, 0, 5, 5);
		gbc_btn43.gridx = 3;
		gbc_btn43.gridy = 4;
		btnPanel.add(btn43, gbc_btn43);
		
		JButton btn50 = new JButton("\u00B1");
		btn50.setPreferredSize(new Dimension(55, 36));
		btn50.setBorder(null);
		btn50.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn50 = new GridBagConstraints();
		gbc_btn50.fill = GridBagConstraints.VERTICAL;
		gbc_btn50.insets = new Insets(0, 0, 5, 5);
		gbc_btn50.gridx = 0;
		gbc_btn50.gridy = 5;
		btnPanel.add(btn50, gbc_btn50);
		
		JButton btn51 = new JButton("0");
		btn51.setPreferredSize(new Dimension(55, 36));
		btn51.setBorder(null);
		btn51.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn51 = new GridBagConstraints();
		gbc_btn51.fill = GridBagConstraints.VERTICAL;
		gbc_btn51.insets = new Insets(0, 0, 5, 5);
		gbc_btn51.gridx = 1;
		gbc_btn51.gridy = 5;
		btnPanel.add(btn51, gbc_btn51);
		
		JButton btn52 = new JButton(".");
		btn52.setPreferredSize(new Dimension(55, 36));
		btn52.setBorder(null);
		btn52.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn52 = new GridBagConstraints();
		gbc_btn52.fill = GridBagConstraints.VERTICAL;
		gbc_btn52.insets = new Insets(0, 0, 5, 5);
		gbc_btn52.gridx = 2;
		gbc_btn52.gridy = 5;
		btnPanel.add(btn52, gbc_btn52);
		
		JButton btn53 = new JButton("=");
		btn53.setForeground(Color.WHITE);
		btn53.setPreferredSize(new Dimension(55, 36));
		btn53.setBorder(null);
		btn53.setBackground(Color.ORANGE);
		GridBagConstraints gbc_btn53 = new GridBagConstraints();
		gbc_btn53.fill = GridBagConstraints.VERTICAL;
		gbc_btn53.gridx = 3;
		gbc_btn53.gridy = 5;
		btnPanel.add(btn53, gbc_btn53);
		// in the end, add all buttons into the list "btnList"
		collectBtn(btnPanel);
	}
	
}