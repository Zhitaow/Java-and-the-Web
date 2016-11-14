package Chapter08b;
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
public class ZipperLayout {
	
	private List<JButton> btnList = new ArrayList<JButton>();
	private List<JRadioButton> rbtnList = new ArrayList<JRadioButton>();

	private JTextField txtOutput;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnSetPath;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField OutputFileTextField;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField txtInputFilePath;
	private JButton btnBrowseFile0;
	private JTextField inputFileTextField0;
	private JTextField txtFileCompressor;
	private JTextField textField_4;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField txtFileDecompressor;
	private JTextField inputFileTextField1;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JButton btnCompress0;
	private JTextField textField_8;
	private JTextField textField_17;
	private JButton btnBrowseFile1;
	private JButton btnDecompress;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JRadioButton rbZIP;
	private JRadioButton rbGZIP;
	private JTextField textField_2;
	private JScrollPane scrollPane;
	private JTextPane logTextPane;
	
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
	
	public void collectRbtn(JPanel contentPane) {
		Component[] components =  contentPane.getComponents();
		System.out.println(components.length);
		for (Component component:components) {
			if (component.getClass().equals(JRadioButton.class)) {
				rbtnList.add((JRadioButton) component);
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
	
	public List<JRadioButton> getRbtn() {
		return rbtnList;
	}
	
	// set the text into the input file path
	public void setInputFilePath0(String message) {
		inputFileTextField0.setText(message);
	}
	// get text from the input file path
	public String getInputFilePath0() {
		return inputFileTextField0.getText();
	}
	
	// set the text into the input file path
	public void setInputFilePath1(String message) {
		inputFileTextField1.setText(message);
	}
	// get text from the input file path
	public String getInputFilePath1() {
		return inputFileTextField1.getText();
	}
	
	// set the text into the output file path
	public void setOutputFilePath(String message) {
		OutputFileTextField.setText(message);
	}
	// get text from the output file path
	public String getOutputFilePath() {
		return OutputFileTextField.getText();
	}
	
	// set the text into the history log
	public void setLogText(String message) {
		logTextPane.setText(message);
	}
	
	public void setLayout(JPanel contentPane) {
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(120, 400));
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setEnabled(false);
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		txtOutput = new JTextField();
		txtOutput.setHorizontalAlignment(SwingConstants.RIGHT);
		txtOutput.setEditable(false);
		txtOutput.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtOutput.setPreferredSize(new Dimension(100, 30));
		txtOutput.setSize(new Dimension(100, 30));
		txtOutput.setBorder(null);
		txtOutput.setOpaque(false);
		txtOutput.setText("Output File Path  ");
		GridBagConstraints gbc_txtOutput = new GridBagConstraints();
		gbc_txtOutput.insets = new Insets(0, 0, 5, 0);
		gbc_txtOutput.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOutput.gridx = 0;
		gbc_txtOutput.gridy = 1;
		panel.add(txtOutput, gbc_txtOutput);
		txtOutput.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 0;
		gbc_textField_4.gridy = 2;
		panel.add(textField_4, gbc_textField_4);
		
		textField_18 = new JTextField();
		textField_18.setEnabled(false);
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBorder(null);
		GridBagConstraints gbc_textField_18 = new GridBagConstraints();
		gbc_textField_18.insets = new Insets(0, 0, 5, 0);
		gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_18.gridx = 0;
		gbc_textField_18.gridy = 3;
		panel.add(textField_18, gbc_textField_18);
		
		txtInputFilePath = new JTextField();
		txtInputFilePath.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInputFilePath.setText("Input File  ");
		txtInputFilePath.setSize(new Dimension(100, 30));
		txtInputFilePath.setPreferredSize(new Dimension(100, 30));
		txtInputFilePath.setOpaque(false);
		txtInputFilePath.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtInputFilePath.setEditable(false);
		txtInputFilePath.setColumns(10);
		txtInputFilePath.setBorder(null);
		GridBagConstraints gbc_txtInputFilePath = new GridBagConstraints();
		gbc_txtInputFilePath.insets = new Insets(0, 0, 5, 0);
		gbc_txtInputFilePath.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInputFilePath.gridx = 0;
		gbc_txtInputFilePath.gridy = 4;
		panel.add(txtInputFilePath, gbc_txtInputFilePath);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBorder(null);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 0;
		gbc_textField_7.gridy = 5;
		panel.add(textField_7, gbc_textField_7);
		
		textField_15 = new JTextField();
		textField_15.setEnabled(false);
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBorder(null);
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.insets = new Insets(0, 0, 5, 0);
		gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_15.gridx = 0;
		gbc_textField_15.gridy = 6;
		panel.add(textField_15, gbc_textField_15);
		
		textField_12 = new JTextField();
		textField_12.setEnabled(false);
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBorder(null);
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 0);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 0;
		gbc_textField_12.gridy = 7;
		panel.add(textField_12, gbc_textField_12);
		
		textField_17 = new JTextField();
		textField_17.setEnabled(false);
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBorder(null);
		GridBagConstraints gbc_textField_17 = new GridBagConstraints();
		gbc_textField_17.insets = new Insets(0, 0, 5, 0);
		gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_17.gridx = 0;
		gbc_textField_17.gridy = 8;
		panel.add(textField_17, gbc_textField_17);
		
		textField_9 = new JTextField();
		textField_9.setText("Input File  ");
		textField_9.setSize(new Dimension(100, 30));
		textField_9.setPreferredSize(new Dimension(100, 30));
		textField_9.setOpaque(false);
		textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_9.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBorder(null);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 0;
		gbc_textField_9.gridy = 9;
		panel.add(textField_9, gbc_textField_9);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(100, 400));
		panel_1.setSize(new Dimension(100, 400));
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 0;
		panel_1.add(textField_1, gbc_textField_1);
		
		btnSetPath = new JButton("Set Path");
		btnSetPath.setActionCommand("Set Path");
		btnSetPath.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		btnSetPath.setPreferredSize(new Dimension(80, 20));
		GridBagConstraints gbc_btnSetPath = new GridBagConstraints();
		gbc_btnSetPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetPath.insets = new Insets(0, 0, 5, 0);
		gbc_btnSetPath.gridx = 0;
		gbc_btnSetPath.gridy = 1;
		panel_1.add(btnSetPath, gbc_btnSetPath);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 0;
		gbc_textField_5.gridy = 2;
		panel_1.add(textField_5, gbc_textField_5);
		
		textField_20 = new JTextField();
		textField_20.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textField_20.setEnabled(false);
		textField_20.setEditable(false);
		textField_20.setColumns(10);
		textField_20.setBorder(null);
		GridBagConstraints gbc_textField_20 = new GridBagConstraints();
		gbc_textField_20.insets = new Insets(0, 0, 5, 0);
		gbc_textField_20.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_20.gridx = 0;
		gbc_textField_20.gridy = 3;
		panel_1.add(textField_20, gbc_textField_20);
		
		btnBrowseFile0 = new JButton("Browse");
		btnBrowseFile0.setActionCommand("browseCompress");
		btnBrowseFile0.setPreferredSize(new Dimension(80, 20));
		btnBrowseFile0.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_btnBrowseFile0 = new GridBagConstraints();
		gbc_btnBrowseFile0.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrowseFile0.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowseFile0.gridx = 0;
		gbc_btnBrowseFile0.gridy = 4;
		panel_1.add(btnBrowseFile0, gbc_btnBrowseFile0);
		
		btnCompress0 = new JButton("Compress");
		btnCompress0.setActionCommand("Compress");
		btnCompress0.setPreferredSize(new Dimension(79, 20));
		btnCompress0.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_btnCompress0 = new GridBagConstraints();
		gbc_btnCompress0.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCompress0.insets = new Insets(0, 0, 5, 0);
		gbc_btnCompress0.gridx = 0;
		gbc_btnCompress0.gridy = 5;
		panel_1.add(btnCompress0, gbc_btnCompress0);
		
		textField_13 = new JTextField();
		textField_13.setEnabled(false);
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBorder(null);
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 5, 0);
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.gridx = 0;
		gbc_textField_13.gridy = 6;
		panel_1.add(textField_13, gbc_textField_13);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_16.setEnabled(false);
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBorder(null);
		GridBagConstraints gbc_textField_16 = new GridBagConstraints();
		gbc_textField_16.insets = new Insets(0, 0, 5, 0);
		gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_16.gridx = 0;
		gbc_textField_16.gridy = 7;
		panel_1.add(textField_16, gbc_textField_16);
		
		btnBrowseFile1 = new JButton("Browse");
		btnBrowseFile1.setActionCommand("browseDecompress");
		btnBrowseFile1.setPreferredSize(new Dimension(80, 20));
		btnBrowseFile1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_btnBrowseFile1 = new GridBagConstraints();
		gbc_btnBrowseFile1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrowseFile1.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowseFile1.gridx = 0;
		gbc_btnBrowseFile1.gridy = 8;
		panel_1.add(btnBrowseFile1, gbc_btnBrowseFile1);
		
		btnDecompress = new JButton("Decompress");
		btnDecompress.setActionCommand("Decompress");
		btnDecompress.setPreferredSize(new Dimension(79, 20));
		btnDecompress.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_btnDecompress = new GridBagConstraints();
		gbc_btnDecompress.insets = new Insets(0, 0, 5, 0);
		gbc_btnDecompress.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDecompress.gridx = 0;
		gbc_btnDecompress.gridy = 9;
		panel_1.add(btnDecompress, gbc_btnDecompress);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 10;
		panel_1.add(textField_2, gbc_textField_2);
		
		rbZIP = new JRadioButton("ZIP");
		rbZIP.setActionCommand("ZIP");
		GridBagConstraints gbc_rbZIP = new GridBagConstraints();
		gbc_rbZIP.anchor = GridBagConstraints.WEST;
		gbc_rbZIP.insets = new Insets(0, 0, 5, 0);
		gbc_rbZIP.gridx = 0;
		gbc_rbZIP.gridy = 11;
		panel_1.add(rbZIP, gbc_rbZIP);
		
		rbGZIP = new JRadioButton("GZIP");
		rbGZIP.setActionCommand("GZIP");
		GridBagConstraints gbc_rbGZIP = new GridBagConstraints();
		gbc_rbGZIP.anchor = GridBagConstraints.WEST;
		gbc_rbGZIP.gridx = 0;
		gbc_rbGZIP.gridy = 12;
		panel_1.add(rbGZIP, gbc_rbGZIP);
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(280, 400));
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 0;
		panel_2.add(textField_3, gbc_textField_3);
		
		OutputFileTextField = new JTextField();
		OutputFileTextField.setPreferredSize(new Dimension(6, 25));
		OutputFileTextField.setEditable(false);
		OutputFileTextField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_OutputFileTextField = new GridBagConstraints();
		gbc_OutputFileTextField.insets = new Insets(0, 0, 5, 0);
		gbc_OutputFileTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_OutputFileTextField.gridx = 0;
		gbc_OutputFileTextField.gridy = 1;
		panel_2.add(OutputFileTextField, gbc_OutputFileTextField);
		OutputFileTextField.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setEnabled(false);
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBorder(null);
		GridBagConstraints gbc_textField_19 = new GridBagConstraints();
		gbc_textField_19.insets = new Insets(0, 0, 5, 0);
		gbc_textField_19.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_19.gridx = 0;
		gbc_textField_19.gridy = 2;
		panel_2.add(textField_19, gbc_textField_19);
		
		txtFileCompressor = new JTextField();
		txtFileCompressor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtFileCompressor.setDisabledTextColor(Color.BLUE);
		txtFileCompressor.setSelectedTextColor(Color.BLACK);
		txtFileCompressor.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileCompressor.setText("File Compressor");
		txtFileCompressor.setEnabled(false);
		txtFileCompressor.setEditable(false);
		txtFileCompressor.setColumns(10);
		txtFileCompressor.setBorder(null);
		GridBagConstraints gbc_txtFileCompressor = new GridBagConstraints();
		gbc_txtFileCompressor.insets = new Insets(0, 0, 5, 0);
		gbc_txtFileCompressor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFileCompressor.gridx = 0;
		gbc_txtFileCompressor.gridy = 3;
		panel_2.add(txtFileCompressor, gbc_txtFileCompressor);
		
		inputFileTextField0 = new JTextField();
		inputFileTextField0.setPreferredSize(new Dimension(6, 25));
		inputFileTextField0.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		inputFileTextField0.setColumns(10);
		GridBagConstraints gbc_inputFileTextField0 = new GridBagConstraints();
		gbc_inputFileTextField0.insets = new Insets(0, 0, 5, 0);
		gbc_inputFileTextField0.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputFileTextField0.gridx = 0;
		gbc_inputFileTextField0.gridy = 4;
		panel_2.add(inputFileTextField0, gbc_inputFileTextField0);
		
		textField_14 = new JTextField();
		textField_14.setEnabled(false);
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBorder(null);
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.insets = new Insets(0, 0, 5, 0);
		gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_14.gridx = 0;
		gbc_textField_14.gridy = 5;
		panel_2.add(textField_14, gbc_textField_14);
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBorder(null);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 0);
		gbc_textField_11.fill = GridBagConstraints.BOTH;
		gbc_textField_11.gridx = 0;
		gbc_textField_11.gridy = 6;
		panel_2.add(textField_11, gbc_textField_11);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBorder(null);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 0;
		gbc_textField_8.gridy = 7;
		panel_2.add(textField_8, gbc_textField_8);
		
		txtFileDecompressor = new JTextField();
		txtFileDecompressor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtFileDecompressor.setText("File Decompressor");
		txtFileDecompressor.setSelectedTextColor(Color.BLACK);
		txtFileDecompressor.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileDecompressor.setEnabled(false);
		txtFileDecompressor.setEditable(false);
		txtFileDecompressor.setDisabledTextColor(Color.BLUE);
		txtFileDecompressor.setColumns(10);
		txtFileDecompressor.setBorder(null);
		GridBagConstraints gbc_txtFileDecompressor = new GridBagConstraints();
		gbc_txtFileDecompressor.insets = new Insets(0, 0, 5, 0);
		gbc_txtFileDecompressor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFileDecompressor.gridx = 0;
		gbc_txtFileDecompressor.gridy = 8;
		panel_2.add(txtFileDecompressor, gbc_txtFileDecompressor);
		
		inputFileTextField1 = new JTextField();
		inputFileTextField1.setPreferredSize(new Dimension(6, 25));
		inputFileTextField1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		inputFileTextField1.setColumns(10);
		GridBagConstraints gbc_inputFileTextField1 = new GridBagConstraints();
		gbc_inputFileTextField1.insets = new Insets(0, 0, 5, 0);
		gbc_inputFileTextField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputFileTextField1.gridx = 0;
		gbc_inputFileTextField1.gridy = 9;
		panel_2.add(inputFileTextField1, gbc_inputFileTextField1);
		
		
		logTextPane = new JTextPane();
		logTextPane.setBorder(null);
		logTextPane.setEditable(false);
		logTextPane.setFont(new Font("Times New Roman", Font.BOLD, 15));
		logTextPane.setForeground(Color.gray);
		logTextPane.setBackground(UIManager.getColor("Button.background"));
		logTextPane.setPreferredSize(new Dimension(250, 400));
		logTextPane.setSize(new Dimension(250, 400));
		//logScrollPane.setColumnHeaderView(logTextPane);
		
		scrollPane = new JScrollPane(logTextPane);
		scrollPane.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 10;
		panel_2.add(scrollPane, gbc_scrollPane);
		
		// in the end, add all buttons into the list "btnList"
		collectBtn(panel_1);
		collectRbtn(panel_1);
	}
	
	
	
	
}