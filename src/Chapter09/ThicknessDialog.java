package Chapter09;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import scribble3.ScribbleCanvas;
//import scribble3.ScribbleTool;
import scribble3.ScribbleTool;


@SuppressWarnings("serial")
public class ThicknessDialog extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField txtCurrentLineThickness;
	private JButton btnOK;
	private ScribbleTool myEraserTool;
	private int[] thickValue;

	private ScribbleCanvas canvas;
	public ThicknessDialog(int[] thickValue, ScribbleTool myEraserTool, ScribbleCanvas canvas) {
		this.myEraserTool = myEraserTool;
		this.thickValue = thickValue;
		this.canvas = canvas;
		this.myEraserTool.setColor(this.canvas.getBGColor());//(this.canvas.getBGColor());
		getContentPane().setPreferredSize(new Dimension(450, 20));
		setPreferredSize(new Dimension(450, 350));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtCurrentLineThickness = new JTextField();
		txtCurrentLineThickness.setEditable(false);
		txtCurrentLineThickness.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrentLineThickness.setText("Current Line Thickness");
		GridBagConstraints gbc_txtCurrentLineThickness = new GridBagConstraints();
		gbc_txtCurrentLineThickness.insets = new Insets(0, 0, 5, 0);
		gbc_txtCurrentLineThickness.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCurrentLineThickness.gridx = 0;
		gbc_txtCurrentLineThickness.gridy = 0;
		contentPane.add(txtCurrentLineThickness, gbc_txtCurrentLineThickness);
		txtCurrentLineThickness.setColumns(10);
		
		JSlider slider = new JSlider();
		slider.setMaximum(20);
		slider.setMinimum(2);
		slider.setValue(thickValue[0]);
		slider.setMinorTickSpacing(2);
	    slider.setMajorTickSpacing(5);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	  //---------------- add change listener --------------------------------
	    slider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	          thickValue[0] = slider.getValue();
//	          myEraser.setStroke(thickValue[0]);
//	          System.out.println(thickValue[0]);
	        }
	      });
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridx = 0;
		gbc_slider.gridy = 1;
		contentPane.add(slider, gbc_slider);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 2;
		contentPane.add(btnOK, gbc_btnOk);
		
	}
	
	public void showDialog() {
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		myEraserTool.setThickness(thickValue[0]);
		myEraserTool.setColor(this.canvas.getBGColor());
		setVisible(false);
	}
}
