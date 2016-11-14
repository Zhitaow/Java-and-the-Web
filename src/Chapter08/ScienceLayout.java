package Chapter08;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * The scientific calculator layout
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
public class ScienceLayout implements CalculatorLayout {

	private JTextField logTextField;
	private JTextField dummyTextField;
	private JTextField calTextField;	
	private List<JButton> btnList = new ArrayList<JButton>();
	private JTextPane logTextPane;
	private JComboBox<String> displayModeCB;
	String[] displayMode = {"Scientific", "Simple"};
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
		gbl_btnPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_btnPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_btnPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_btnPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		btnPanel.setLayout(gbl_btnPanel);
		
		JButton btn00 = new JButton("sin");
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
		
		JButton btn02 = new JButton("\u221Bx");
		btn02.setPreferredSize(new Dimension(55, 36));
		btn02.setBorder(null);
		btn02.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn02 = new GridBagConstraints();
		gbc_btn02.fill = GridBagConstraints.VERTICAL;
		gbc_btn02.insets = new Insets(0, 0, 5, 5);
		gbc_btn02.gridx = 2;
		gbc_btn02.gridy = 0;
		btnPanel.add(btn02, gbc_btn02);
		
		JButton btn03 = new JButton("DEG");
		btn03.setBackground(Color.LIGHT_GRAY);
		btn03.setPreferredSize(new Dimension(55, 36));
		btn03.setBorder(null);
		GridBagConstraints gbc_btn03 = new GridBagConstraints();
		gbc_btn03.fill = GridBagConstraints.VERTICAL;
		gbc_btn03.insets = new Insets(0, 0, 5, 5);
		gbc_btn03.gridx = 3;
		gbc_btn03.gridy = 0;
		btnPanel.add(btn03, gbc_btn03);
		
		JButton btn04 = new JButton("BIN");
		btn04.setPreferredSize(new Dimension(55, 36));
		btn04.setBorder(null);
		btn04.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn04 = new GridBagConstraints();
		gbc_btn04.fill = GridBagConstraints.VERTICAL;
		gbc_btn04.insets = new Insets(0, 0, 5, 0);
		gbc_btn04.gridx = 4;
		gbc_btn04.gridy = 0;
		btnPanel.add(btn04, gbc_btn04);
		
		JButton btn10 = new JButton("cos");
		btn10.setPreferredSize(new Dimension(55, 36));
		btn10.setBorder(null);
		btn10.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn10 = new GridBagConstraints();
		gbc_btn10.fill = GridBagConstraints.VERTICAL;
		gbc_btn10.insets = new Insets(0, 0, 5, 5);
		gbc_btn10.gridx = 0;
		gbc_btn10.gridy = 1;
		btnPanel.add(btn10, gbc_btn10);
		
		JButton btn11 = new JButton("x\u00B2");
		btn11.setPreferredSize(new Dimension(55, 36));
		btn11.setBorder(null);
		btn11.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn11 = new GridBagConstraints();
		gbc_btn11.insets = new Insets(0, 0, 5, 5);
		gbc_btn11.gridx = 1;
		gbc_btn11.gridy = 1;
		btnPanel.add(btn11, gbc_btn11);
		
		JButton btn12 = new JButton("exp");
		btn12.setPreferredSize(new Dimension(55, 36));
		btn12.setBorder(null);
		btn12.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn12 = new GridBagConstraints();
		gbc_btn12.fill = GridBagConstraints.VERTICAL;
		gbc_btn12.insets = new Insets(0, 0, 5, 5);
		gbc_btn12.gridx = 2;
		gbc_btn12.gridy = 1;
		btnPanel.add(btn12, gbc_btn12);
		
		JButton btn13 = new JButton("ln");
		btn13.setPreferredSize(new Dimension(55, 36));
		btn13.setBorder(null);
		btn13.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn13 = new GridBagConstraints();
		gbc_btn13.fill = GridBagConstraints.VERTICAL;
		gbc_btn13.insets = new Insets(0, 0, 5, 5);
		gbc_btn13.gridx = 3;
		gbc_btn13.gridy = 1;
		btnPanel.add(btn13, gbc_btn13);
		
		JButton btn14 = new JButton("HEX");
		btn14.setPreferredSize(new Dimension(55, 36));
		btn14.setBorder(null);
		btn14.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn14 = new GridBagConstraints();
		gbc_btn14.fill = GridBagConstraints.VERTICAL;
		gbc_btn14.insets = new Insets(0, 0, 5, 0);
		gbc_btn14.gridx = 4;
		gbc_btn14.gridy = 1;
		btnPanel.add(btn14, gbc_btn14);
		
		JButton btn20 = new JButton("tan");
		btn20.setPreferredSize(new Dimension(55, 36));
		btn20.setBorder(null);
		btn20.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn20 = new GridBagConstraints();
		gbc_btn20.fill = GridBagConstraints.VERTICAL;
		gbc_btn20.insets = new Insets(0, 0, 5, 5);
		gbc_btn20.gridx = 0;
		gbc_btn20.gridy = 2;
		btnPanel.add(btn20, gbc_btn20);
		
		JButton btnCE = new JButton("CE");
		
		JButton btn21 = new JButton("x\u00B3");
		btn21.setPreferredSize(new Dimension(55, 36));
		btn21.setBorder(null);
		btn21.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn21 = new GridBagConstraints();
		gbc_btn21.insets = new Insets(0, 0, 5, 5);
		gbc_btn21.gridx = 1;
		gbc_btn21.gridy = 2;
		btnPanel.add(btn21, gbc_btn21);
		
		JButton btn22 = new JButton("10\u02E3");
		btn22.setPreferredSize(new Dimension(55, 36));
		btn22.setBorder(null);
		btn22.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn22 = new GridBagConstraints();
		gbc_btn22.fill = GridBagConstraints.VERTICAL;
		gbc_btn22.insets = new Insets(0, 0, 5, 5);
		gbc_btn22.gridx = 2;
		gbc_btn22.gridy = 2;
		btnPanel.add(btn22, gbc_btn22);
		
		JButton btn23 = new JButton("log");
		btn23.setPreferredSize(new Dimension(55, 36));
		btn23.setBorder(null);
		btn23.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn23 = new GridBagConstraints();
		gbc_btn23.fill = GridBagConstraints.VERTICAL;
		gbc_btn23.insets = new Insets(0, 0, 5, 5);
		gbc_btn23.gridx = 3;
		gbc_btn23.gridy = 2;
		btnPanel.add(btn23, gbc_btn23);
		
		JButton btn24 = new JButton("DEC");
		btn24.setPreferredSize(new Dimension(55, 36));
		btn24.setBorder(null);
		btn24.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btn24 = new GridBagConstraints();
		gbc_btn24.fill = GridBagConstraints.VERTICAL;
		gbc_btn24.insets = new Insets(0, 0, 5, 0);
		gbc_btn24.gridx = 4;
		gbc_btn24.gridy = 2;
		btnPanel.add(btn24, gbc_btn24);
		
		JButton btnShift = new JButton("\u21E7");
		btnShift.setPreferredSize(new Dimension(55, 36));
		btnShift.setBorder(null);
		btnShift.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnShift = new GridBagConstraints();
		gbc_btnShift.fill = GridBagConstraints.VERTICAL;
		gbc_btnShift.insets = new Insets(0, 0, 5, 5);
		gbc_btnShift.gridx = 0;
		gbc_btnShift.gridy = 3;
		btnPanel.add(btnShift, gbc_btnShift);
		btnCE.setPreferredSize(new Dimension(55, 36));
		btnCE.setBorder(null);
		btnCE.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnCE = new GridBagConstraints();
		gbc_btnCE.fill = GridBagConstraints.VERTICAL;
		gbc_btnCE.insets = new Insets(0, 0, 5, 5);
		gbc_btnCE.gridx = 1;
		gbc_btnCE.gridy = 3;
		btnPanel.add(btnCE, gbc_btnCE);
		
		JButton btnC = new JButton("C");
		btnC.setPreferredSize(new Dimension(55, 36));
		btnC.setBorder(null);
		btnC.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnC = new GridBagConstraints();
		gbc_btnC.fill = GridBagConstraints.VERTICAL;
		gbc_btnC.insets = new Insets(0, 0, 5, 5);
		gbc_btnC.gridx = 2;
		gbc_btnC.gridy = 3;
		btnPanel.add(btnC, gbc_btnC);
		
		JButton btnDel = new JButton("DEL");
		btnDel.setPreferredSize(new Dimension(55, 36));
		btnDel.setBorder(null);
		btnDel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnDel = new GridBagConstraints();
		gbc_btnDel.fill = GridBagConstraints.VERTICAL;
		gbc_btnDel.insets = new Insets(0, 0, 5, 5);
		gbc_btnDel.gridx = 3;
		gbc_btnDel.gridy = 3;
		btnPanel.add(btnDel, gbc_btnDel);
		
		JButton btnDiv = new JButton("\u00f7");
		btnDiv.setForeground(Color.WHITE);
		btnDiv.setPreferredSize(new Dimension(55, 36));
		btnDiv.setBorder(null);
		btnDiv.setBackground(Color.ORANGE);
		GridBagConstraints gbc_btnDiv = new GridBagConstraints();
		gbc_btnDiv.fill = GridBagConstraints.VERTICAL;
		gbc_btnDiv.insets = new Insets(0, 0, 5, 0);
		gbc_btnDiv.gridx = 4;
		gbc_btnDiv.gridy = 3;
		btnPanel.add(btnDiv, gbc_btnDiv);
		
		JButton btnMod = new JButton("%");
		btnMod.setPreferredSize(new Dimension(55, 36));
		btnMod.setBorder(null);
		btnMod.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnMod = new GridBagConstraints();
		gbc_btnMod.fill = GridBagConstraints.VERTICAL;
		gbc_btnMod.insets = new Insets(0, 0, 5, 5);
		gbc_btnMod.gridx = 0;
		gbc_btnMod.gridy = 4;
		btnPanel.add(btnMod, gbc_btnMod);
		
		JButton btnNum7 = new JButton("7");
		btnNum7.setPreferredSize(new Dimension(55, 36));
		btnNum7.setBorder(null);
		btnNum7.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum7 = new GridBagConstraints();
		gbc_btnNum7.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum7.gridx = 1;
		gbc_btnNum7.gridy = 4;
		btnPanel.add(btnNum7, gbc_btnNum7);
		
		JButton btnNum8 = new JButton("8");
		btnNum8.setPreferredSize(new Dimension(55, 36));
		btnNum8.setBorder(null);
		btnNum8.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum8 = new GridBagConstraints();
		gbc_btnNum8.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum8.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum8.gridx = 2;
		gbc_btnNum8.gridy = 4;
		btnPanel.add(btnNum8, gbc_btnNum8);
		
		JButton btnNum9 = new JButton("9");
		btnNum9.setPreferredSize(new Dimension(55, 36));
		btnNum9.setBorder(null);
		btnNum9.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum9 = new GridBagConstraints();
		gbc_btnNum9.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum9.gridx = 3;
		gbc_btnNum9.gridy = 4;
		btnPanel.add(btnNum9, gbc_btnNum9);
		
		JButton btnMul = new JButton("\u00D7");
		btnMul.setForeground(Color.WHITE);
		btnMul.setPreferredSize(new Dimension(55, 36));
		btnMul.setBorder(null);
		btnMul.setBackground(Color.ORANGE);
		GridBagConstraints gbc_btnMul = new GridBagConstraints();
		gbc_btnMul.fill = GridBagConstraints.VERTICAL;
		gbc_btnMul.insets = new Insets(0, 0, 5, 0);
		gbc_btnMul.gridx = 4;
		gbc_btnMul.gridy = 4;
		btnPanel.add(btnMul, gbc_btnMul);
		
		JButton btnPI = new JButton("\u03C0");
		btnPI.setPreferredSize(new Dimension(55, 36));
		btnPI.setBorder(null);
		btnPI.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnPI = new GridBagConstraints();
		gbc_btnPI.fill = GridBagConstraints.VERTICAL;
		gbc_btnPI.insets = new Insets(0, 0, 5, 5);
		gbc_btnPI.gridx = 0;
		gbc_btnPI.gridy = 5;
		btnPanel.add(btnPI, gbc_btnPI);
		
		JButton btnNum4 = new JButton("4");
		btnNum4.setPreferredSize(new Dimension(55, 36));
		btnNum4.setBorder(null);
		btnNum4.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum4 = new GridBagConstraints();
		gbc_btnNum4.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum4.gridx = 1;
		gbc_btnNum4.gridy = 5;
		btnPanel.add(btnNum4, gbc_btnNum4);
		
		JButton btnNum5 = new JButton("5");
		btnNum5.setPreferredSize(new Dimension(55, 36));
		btnNum5.setBorder(null);
		btnNum5.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum5 = new GridBagConstraints();
		gbc_btnNum5.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum5.gridx = 2;
		gbc_btnNum5.gridy = 5;
		btnPanel.add(btnNum5, gbc_btnNum5);
		
		JButton btnNum6 = new JButton("6");
		btnNum6.setPreferredSize(new Dimension(55, 36));
		btnNum6.setBorder(null);
		btnNum6.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum6 = new GridBagConstraints();
		gbc_btnNum6.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum6.gridx = 3;
		gbc_btnNum6.gridy = 5;
		btnPanel.add(btnNum6, gbc_btnNum6);
		
		JButton btnMin = new JButton("\u2212");
		btnMin.setForeground(Color.WHITE);
		btnMin.setPreferredSize(new Dimension(55, 36));
		btnMin.setBorder(null);
		btnMin.setBackground(Color.ORANGE);
		GridBagConstraints gbc_btnMin = new GridBagConstraints();
		gbc_btnMin.fill = GridBagConstraints.VERTICAL;
		gbc_btnMin.insets = new Insets(0, 0, 5, 0);
		gbc_btnMin.gridx = 4;
		gbc_btnMin.gridy = 5;
		btnPanel.add(btnMin, gbc_btnMin);
		
		JButton btnNeg = new JButton("\u00B1");
		btnNeg.setPreferredSize(new Dimension(55, 36));
		btnNeg.setBorder(null);
		btnNeg.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNeg = new GridBagConstraints();
		gbc_btnNeg.fill = GridBagConstraints.VERTICAL;
		gbc_btnNeg.insets = new Insets(0, 0, 5, 5);
		gbc_btnNeg.gridx = 0;
		gbc_btnNeg.gridy = 6;
		btnPanel.add(btnNeg, gbc_btnNeg);
		
		JButton btnNum1 = new JButton("1");
		btnNum1.setPreferredSize(new Dimension(55, 36));
		btnNum1.setBorder(null);
		btnNum1.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum1 = new GridBagConstraints();
		gbc_btnNum1.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum1.gridx = 1;
		gbc_btnNum1.gridy = 6;
		btnPanel.add(btnNum1, gbc_btnNum1);
		
		JButton btnNum2 = new JButton("2");
		btnNum2.setPreferredSize(new Dimension(55, 36));
		btnNum2.setBorder(null);
		btnNum2.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum2 = new GridBagConstraints();
		gbc_btnNum2.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum2.gridx = 2;
		gbc_btnNum2.gridy = 6;
		btnPanel.add(btnNum2, gbc_btnNum2);
		
		JButton btnNum3 = new JButton("3");
		btnNum3.setPreferredSize(new Dimension(55, 36));
		btnNum3.setBorder(null);
		btnNum3.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum3 = new GridBagConstraints();
		gbc_btnNum3.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNum3.gridx = 3;
		gbc_btnNum3.gridy = 6;
		btnPanel.add(btnNum3, gbc_btnNum3);
		
		JButton btnAdd = new JButton("+");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setPreferredSize(new Dimension(55, 36));
		btnAdd.setBorder(null);
		btnAdd.setBackground(Color.ORANGE);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.VERTICAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 6;
		btnPanel.add(btnAdd, gbc_btnAdd);
		
		JButton btnLBr = new JButton("\u21E6");
		btnLBr.setPreferredSize(new Dimension(55, 36));
		btnLBr.setBorder(null);
		btnLBr.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnLBr = new GridBagConstraints();
		gbc_btnLBr.fill = GridBagConstraints.VERTICAL;
		gbc_btnLBr.insets = new Insets(0, 0, 0, 5);
		gbc_btnLBr.gridx = 0;
		gbc_btnLBr.gridy = 7;
		btnPanel.add(btnLBr, gbc_btnLBr);
		
		JButton btnRBr = new JButton("1/x");
		btnRBr.setPreferredSize(new Dimension(55, 36));
		btnRBr.setBorder(null);
		btnRBr.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnRBr = new GridBagConstraints();
		gbc_btnRBr.fill = GridBagConstraints.VERTICAL;
		gbc_btnRBr.insets = new Insets(0, 0, 0, 5);
		gbc_btnRBr.gridx = 1;
		gbc_btnRBr.gridy = 7;
		btnPanel.add(btnRBr, gbc_btnRBr);
		
		JButton btnNum0 = new JButton("0");
		btnNum0.setPreferredSize(new Dimension(55, 36));
		btnNum0.setBorder(null);
		btnNum0.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnNum0 = new GridBagConstraints();
		gbc_btnNum0.fill = GridBagConstraints.VERTICAL;
		gbc_btnNum0.insets = new Insets(0, 0, 0, 5);
		gbc_btnNum0.gridx = 2;
		gbc_btnNum0.gridy = 7;
		btnPanel.add(btnNum0, gbc_btnNum0);
		
		JButton btnDot = new JButton(".");
		btnDot.setPreferredSize(new Dimension(55, 36));
		btnDot.setBorder(null);
		btnDot.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnDot = new GridBagConstraints();
		gbc_btnDot.fill = GridBagConstraints.VERTICAL;
		gbc_btnDot.insets = new Insets(0, 0, 0, 5);
		gbc_btnDot.gridx = 3;
		gbc_btnDot.gridy = 7;
		btnPanel.add(btnDot, gbc_btnDot);
		
		JButton btnEql = new JButton("=");
		btnEql.setForeground(Color.WHITE);
		btnEql.setPreferredSize(new Dimension(55, 36));
		btnEql.setBorder(null);
		btnEql.setBackground(Color.ORANGE);
		GridBagConstraints gbc_btnEql = new GridBagConstraints();
		gbc_btnEql.fill = GridBagConstraints.VERTICAL;
		gbc_btnEql.gridx = 4;
		gbc_btnEql.gridy = 7;
		btnPanel.add(btnEql, gbc_btnEql);
		// in the end, add all buttons into the list "btnList"
		collectBtn(btnPanel);
	}
	
}
