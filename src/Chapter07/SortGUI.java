package Chapter07;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SortGUI extends javax.swing.JApplet implements ActionListener {
	int xSize = 600, ySize = 400;
	JComboBox<String> panelCB, sortModeCB, displayModeCB;
	JButton clickBtn = null;
	JPanel controlPanel, container;
	JTextArea textArea;
	Sort3[] panels = new Sort3[2];
	String strSortMode, strSortDisplayMode, strPanelName;
	String[] sortMode = {"BubbleSort", "QuickSort", "MergeSort", 
			"SelectionSort", "HeapSort"};
	String[] displayMode = {"horizontal", "vertical", "bottom", 
			"bar", "line", "circular", "pyramid"};
	String[] selectPanel = {"Panel 1", "Panel 2"};
	String message;
	String[] subString = {sortMode[0], sortMode[1]};
	public void init() {	
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new Sort3();
			panels[i].setSize(300, 300);
			panels[i].setAlgorithm(sortMode[i]);
			panels[i].setDisplay(displayMode[i]);
		}
		setSize(xSize, ySize);
		// Display in JFrame
		JFrame frame = new JFrame("Zhitao's Algorithm GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = new JPanel();
		container.setLayout(new GridLayout(1,4));
		for (Sort3 panel:panels) {
			container.add(panel);
		}
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel,
				BoxLayout.Y_AXIS));		
		textArea = new JTextArea();
		updateText();
		textArea.setText(message);
		textArea.setBackground(Color.lightGray);
		controlPanel.add(textArea);
		textArea.setEditable(false);
		// switch panel
		panelCB = new JComboBox<>(selectPanel);
		panelCB.addActionListener(this);
		controlPanel.add(panelCB);
		// switch sorting algorithm
		sortModeCB = new JComboBox<>(sortMode);
		sortModeCB.addActionListener(this);
		controlPanel.add(sortModeCB);
		// switch display strategy
		displayModeCB = new JComboBox<>(displayMode);
		displayModeCB.addActionListener(this);
		controlPanel.add(displayModeCB);
		// start button
		clickBtn = new JButton("Start");
		clickAction();
		controlPanel.add(clickBtn);
		// add all components and display
		container.add(controlPanel);
		add(container);
		frame.setSize(xSize,ySize);
		frame.add(container);
		frame.setVisible(true);
		// initialize all algorithms in the panels
		for (Sort3 panel:panels) {
			panel.init();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		strPanelName = (String) panelCB.getSelectedItem();
		int panelIdx = 0;
		if (strPanelName.equals(selectPanel[0])) {
			panelIdx = 0;
			subString[0] = 
					(String) sortModeCB.getSelectedItem();
		} else if (strPanelName.equals(selectPanel[1])) {
			panelIdx = 1;
			subString[1] = 
					(String) sortModeCB.getSelectedItem();
		} else {
			panelIdx = 0;
			subString[0] = 
					(String) sortModeCB.getSelectedItem();
		}
		strSortMode = (String) sortModeCB.getSelectedItem();
		strSortDisplayMode = 
				(String) displayModeCB.getSelectedItem();
		panels[panelIdx].setAlgorithm(strSortMode);
		panels[panelIdx].setDisplay(strSortDisplayMode);
		updateText();
		textArea.setText(message);
	}
	
	private void clickAction() {	
		clickBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			String label = clickBtn.getText();
    			if (label.equals("Start")) {
    				clickBtn.setText("Stop");
    				for (Sort3 panel : panels) {
    					panel.scramble();
    					panel.init();
    					panel.start();
    				}
    			} else {
    				clickBtn.setText("Start");
    				for (Sort3 panel : panels) {
    					panel.stop();
    				}
    			}
    		}
		});
	}
	
	private void updateText() {
		String[] subStr  = {"", ""};
		for (int i = 0; i < subString.length; i ++) {
			if (subString[i].equals("BubbleSort")) {
				subStr[i] = " Worst-case O(n^2) \n";
			} else if (subString[i].equals("QuickSort")) {
				subStr[i] = 
					" Worst-case O(n^2)\n  On average O(nlog(n))";
			} else if (subString[i].equals("MergeSort")) {
				subStr[i] = " Worst-case O(nlog(n)) \n";
			} else if (subString[i].equals("SelectionSort")) {
				subStr[i] = " Worst-case O(n^2) \n";
			} else if (subString[i].equals("HeapSort")) {
				subStr[i] = " Worst-case O(nlog(n)) \n";
			}
		}
		
		message = "A simple sort GUI: \n"
				+ "  1. Select the Panel; \n"
				+ "  2. Select the algorithm;\n"
				+ "  3. Select the display strategy.\n"
				+ "\n" + "Current left panel: " 
				+ subString[0] + "\n "
				+ subStr[0] + "\n" 
				+ "Current right panel: " 
						+ subString[1] + "\n "
						+ subStr[1];
	}
}
