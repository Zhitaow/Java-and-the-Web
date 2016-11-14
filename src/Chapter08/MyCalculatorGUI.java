package Chapter08;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * The calculator UI
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
@SuppressWarnings("serial")
public class MyCalculatorGUI extends JApplet implements ActionListener {
	private JPanel contentPane;
	JPanel container;
	CalculatorLayout calLayout;
	StackArray stack = new StackArray();
	String[] status = new String[10];
	BtnFactory btnFactory = new BtnFactory();
	JComboBox<String> calculatorCB;
	String calDisplay; // = "Simple";
	public void init() {
		// set container size
		setSize(600, 450);
		// by default use scientific calculator layout 
		calLayout = new ScienceLayout();
		// set up the layout, and add to to container, 
		// and add button, combo-box listener
		setup();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		calDisplay = (String) calculatorCB.getSelectedItem();
		// if one type of display mode is selected, 
		// create the corresponding layout
		if (calDisplay.equals("Simple")) {
			calLayout = new SimpleLayout();
			System.out.println(calDisplay);
		} else {
			calLayout = new ScienceLayout();
			System.out.println(calDisplay);
		}
		setup();
	}

	public void setup() {
		try {
			// if a new calculator layout is selected, 
			//remove previous content from the container
			remove(contentPane);
		} catch (Exception e) {
			
		}
		// create a new content pane hosting the calculator
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(600, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		calLayout.setLayout(contentPane);
		// add action listener
		calculatorCB = calLayout.getJComboBox();
		calculatorCB.addActionListener(this);
		// display components
		add(contentPane);
		calLayout.setDisplayText("0" + "   ");
		status[0] = "0";
		status[1] = "";
		status[2] = "";
		status[3] = "";
		status[4] = "";
		status[5] = "";
		status[6] = "false";
		status[7] = "true";
		status[8] = "10";
		// register button listeners
		btnFactory.register(calLayout, stack, status);
		// repaint the container
		validate(); 
	}
	
}
