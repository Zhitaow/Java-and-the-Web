package Chapter09; 

import java.awt.*; 
import java.awt.event.*;

import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import draw3.KeyboardDrawingCanvas;

@SuppressWarnings("serial")
public class FontDialog extends JDialog implements ActionListener { 
	private JTextField txtFontStyle;
	private JTextField txtFontSize;
	private JTextField txtFontStyle_1;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnDialog;
	private JRadioButton rdbtnDialoginput;
	private JRadioButton rdbtnPlain;
	private JRadioButton rdbtnBold;
	private JRadioButton rdbtnItalic;
	private JRadioButton rdbtnBoldItalic;
	private JTextField txtCurrentSize;
	private JSlider slider;
	private KeyboardDrawingCanvas keyboardDrawingCanvas;
	private String[] fontFamily = {"Serif", "Sans-serif", "Monospaced", "Dialog", "DialogInput"};
	private String[] fontStyle = {"plain", "bold", "Italic", "Bold Italic"};
	private int[] style = new int[] {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};
//	private JPanel contentPane;
	private JTextField textField_1;
	
	public FontDialog(KeyboardDrawingCanvas keyboardDrawingCanvas) { 
//		contentPane = new JPanel();
		this.keyboardDrawingCanvas = keyboardDrawingCanvas;
		getContentPane().setPreferredSize(new Dimension(450, 20));
		setPreferredSize(new Dimension(450, 350));
		setBounds(100, 100, 456, 249);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		txtFontStyle = new JTextField();
		txtFontStyle.setEditable(false);
		txtFontStyle.setPreferredSize(new Dimension(450, 20));
		txtFontStyle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtFontStyle.setHorizontalAlignment(SwingConstants.CENTER);
		txtFontStyle.setBackground(UIManager.getColor("Button.background"));
		txtFontStyle.setText("Font Family");
		GridBagConstraints gbc_txtFontStyle = new GridBagConstraints();
		gbc_txtFontStyle.insets = new Insets(0, 0, 5, 0);
		gbc_txtFontStyle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFontStyle.gridx = 0;
		gbc_txtFontStyle.gridy = 0;
		getContentPane().add(txtFontStyle, gbc_txtFontStyle);
		txtFontStyle.setColumns(10);
		
		panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 20));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rdbtnNewRadioButton = new JRadioButton(fontFamily[0]);
		rdbtnNewRadioButton.setActionCommand(fontFamily[0]);
		rdbtnNewRadioButton.addActionListener(this);
		panel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton(fontFamily[1]);
		rdbtnNewRadioButton_1.setActionCommand(fontFamily[1]);
		rdbtnNewRadioButton_1.addActionListener(this);
		panel.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton(fontFamily[2]);
		rdbtnNewRadioButton_2.setActionCommand(fontFamily[2]);
		rdbtnNewRadioButton_2.addActionListener(this);
		panel.add(rdbtnNewRadioButton_2);
		
		rdbtnDialog = new JRadioButton(fontFamily[3]);
		rdbtnDialog.setActionCommand(fontFamily[3]);
		rdbtnDialog.addActionListener(this);
		panel.add(rdbtnDialog);
		
		rdbtnDialoginput = new JRadioButton(fontFamily[4]);
		rdbtnDialoginput.setActionCommand(fontFamily[4]);
		rdbtnDialoginput.addActionListener(this);
		panel.add(rdbtnDialoginput);
		
		ButtonGroup fontFamilyGroup = new ButtonGroup();
		fontFamilyGroup.add(rdbtnNewRadioButton);
		fontFamilyGroup.add(rdbtnNewRadioButton_1);
		fontFamilyGroup.add(rdbtnNewRadioButton_2);
		fontFamilyGroup.add(rdbtnDialog);
		fontFamilyGroup.add(rdbtnDialoginput);
		
		txtFontSize = new JTextField();
		txtFontSize.setText("Font Style");
		txtFontSize.setPreferredSize(new Dimension(450, 20));
		txtFontSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtFontSize.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtFontSize.setEditable(false);
		txtFontSize.setColumns(10);
		txtFontSize.setBackground(SystemColor.menu);
		GridBagConstraints gbc_txtFontSize = new GridBagConstraints();
		gbc_txtFontSize.insets = new Insets(0, 0, 5, 0);
		gbc_txtFontSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFontSize.gridx = 0;
		gbc_txtFontSize.gridy = 2;
		getContentPane().add(txtFontSize, gbc_txtFontSize);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 5;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		txtCurrentSize = new JTextField();
		txtCurrentSize.setBorder(null);
		txtCurrentSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrentSize.setBackground(UIManager.getColor("Button.background"));
		txtCurrentSize.setText("Current Size: ");
		GridBagConstraints gbc_txtCurrentSize = new GridBagConstraints();
		gbc_txtCurrentSize.insets = new Insets(0, 0, 5, 0);
		gbc_txtCurrentSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCurrentSize.gridx = 0;
		gbc_txtCurrentSize.gridy = 0;
		panel_1.add(txtCurrentSize, gbc_txtCurrentSize);
		txtCurrentSize.setColumns(10);
		
		slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		slider.setMaximum(60);
		slider.setMinimum(10);
		slider.setValue(30);
		keyboardDrawingCanvas.setFontSize(30);
		slider.setMinorTickSpacing(1);
	    slider.setMajorTickSpacing(10);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    //---------------- add change listener --------------------------------
	    slider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	          keyboardDrawingCanvas.setFontSize(slider.getValue()); 
	          updateTextField();
//	          System.out.println("Value " + keyboardDrawingCanvas.getFontSize());
	        }
	      });
		//-----------------------------------------------------------------------
		gbc_slider.gridx = 0;
		gbc_slider.gridy = 1;
		panel_1.add(slider, gbc_slider);
		
		txtFontStyle_1 = new JTextField();
		txtFontStyle_1.setText("Font Size");
		txtFontStyle_1.setPreferredSize(new Dimension(450, 20));
		txtFontStyle_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtFontStyle_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtFontStyle_1.setEditable(false);
		txtFontStyle_1.setColumns(10);
		txtFontStyle_1.setBackground(SystemColor.menu);
		GridBagConstraints gbc_txtFontStyle_1 = new GridBagConstraints();
		gbc_txtFontStyle_1.insets = new Insets(0, 0, 5, 0);
		gbc_txtFontStyle_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFontStyle_1.gridx = 0;
		gbc_txtFontStyle_1.gridy = 4;
		getContentPane().add(txtFontStyle_1, gbc_txtFontStyle_1);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rdbtnPlain = new JRadioButton(fontStyle[0]);
		rdbtnPlain.setActionCommand(fontStyle[0]);
		rdbtnPlain.addActionListener(this);
		panel_2.add(rdbtnPlain);
		
		rdbtnBold = new JRadioButton(fontStyle[1]);
		rdbtnBold.setActionCommand(fontStyle[1]);
		rdbtnBold.addActionListener(this);
		panel_2.add(rdbtnBold);
		
		rdbtnItalic = new JRadioButton(fontStyle[2]);
		rdbtnItalic.setActionCommand(fontStyle[2]);
		rdbtnItalic.addActionListener(this);
		panel_2.add(rdbtnItalic);
		
		rdbtnBoldItalic = new JRadioButton(fontStyle[3]);
		rdbtnBoldItalic.setActionCommand(fontStyle[3]);
		rdbtnBoldItalic.addActionListener(this);
		panel_2.add(rdbtnBoldItalic);

		ButtonGroup fontStyleGroup = new ButtonGroup();
		fontStyleGroup.add(rdbtnPlain);
		fontStyleGroup.add(rdbtnBold);
		fontStyleGroup.add(rdbtnItalic);
		fontStyleGroup.add(rdbtnBoldItalic);
		
		// set initial state
		keyboardDrawingCanvas.setFontFamily(fontFamily[0]);
		rdbtnNewRadioButton.setSelected(true);
		keyboardDrawingCanvas.setFontStyle(style[0]);
		rdbtnPlain.setSelected(true);
		
		// set text field
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 6;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		updateTextField();
		
		pack();
		
  }

	public void updateTextField() {
		Font f = new Font(keyboardDrawingCanvas.getFontFamily(), 
				keyboardDrawingCanvas.getFontStyle(), keyboardDrawingCanvas.getFontSize());
		textField_1.setFont(f);
		textField_1.setText("Java is great!");
	}

	public void showDialog() { 
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = e.getActionCommand();
		for (String fontName:fontFamily) {
			if (name.equals(fontName)) {
				keyboardDrawingCanvas.setFontFamily(fontName);
				updateTextField();
				System.out.println(keyboardDrawingCanvas.getFontFamily());
			}
		}
		for (int i = 0; i < style.length; i++) {
			if (name.equals(fontStyle[i])) {
				keyboardDrawingCanvas.setFontStyle(style[i]);
				updateTextField();
				System.out.println(keyboardDrawingCanvas.getFontStyle());
			}
		}
		
	
	}
}
