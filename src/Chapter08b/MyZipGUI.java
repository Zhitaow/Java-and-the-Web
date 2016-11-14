package Chapter08b;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * The calculator UI
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/13/2016
 */

@SuppressWarnings("serial")
public class MyZipGUI extends JApplet implements ActionListener {
	private ZipperLayout zipLayout;
	private JPanel contentPane;
	private List<JButton> btnList;
	private JFileChooser[] choosers = new JFileChooser[3];
	private List<JRadioButton> rbtnList;
	private String inputFile = "";
	private String outputFile = "";
	private Zipper zipper;
	private Unzipper unzipper;
	// zipMode: 0 means ZIP, 1 means GZIP
	private int zipMode = 0;
	public void init() {
		// set container size
		setSize(600, 350);
		// by default use scientific calculator layout 
		zipLayout = new ZipperLayout();
		// set up the layout, and add to to container, 
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(600, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		zipLayout.setLayout(contentPane);
		btnList = zipLayout.getBtn();
		rbtnList = zipLayout.getRbtn();
		for (JButton btn:btnList) {
			btn.addActionListener(this);
		}
		for (JRadioButton btn:rbtnList) {
			btn.addActionListener(this);
		}
		rbtnList.get(0).setSelected(true);
		System.out.println(btnList.size());
		add(contentPane);
		for (int i = 0; i < choosers.length; i++) {
			choosers[i] = new JFileChooser();
	        choosers[i].setCurrentDirectory(new java.io.File("."));
	        choosers[i].setDialogTitle("Browse for the file to compress");
		}
        choosers[0].setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        choosers[1].setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        choosers[2].setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// repaint the container
		validate(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Set Path")) {
			if (choosers[0].showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        	zipLayout.setOutputFilePath(choosers[0].getSelectedFile().getAbsolutePath());
				outputFile = zipLayout.getOutputFilePath();
			}
		} else if (e.getActionCommand().equals("browseCompress")) {
			if (choosers[1].showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        	zipLayout.setInputFilePath0(choosers[1].getSelectedFile().getAbsolutePath());
	        	inputFile = zipLayout.getInputFilePath0();
			}
		} else if (e.getActionCommand().equals("Compress")) {
			inputFile = zipLayout.getInputFilePath0();
			if (zipMode == 0) {
				// use ZIP
				zipper = new Zipper();
				zipper.zip(inputFile, outputFile, zipLayout);
			} else {
				// use GZIP
				zipper = new Zipper();
				zipper.gz(inputFile, outputFile, zipLayout);
			}
		} else if (e.getActionCommand().equals("browseDecompress")) {
			if (choosers[2].showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				choosers[2].setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        	zipLayout.setInputFilePath1(choosers[2].getSelectedFile().getAbsolutePath());
	        	inputFile = zipLayout.getInputFilePath1();
			}
		} else if (e.getActionCommand().equals("Decompress")) {
			inputFile = zipLayout.getInputFilePath1();
			if (zipMode == 0) {
				unzipper = new Unzipper();
				unzipper.unzip(inputFile, outputFile, zipLayout);
			} else {
				unzipper = new Unzipper();
				unzipper.ungz(inputFile, outputFile, zipLayout);
			}
		} else if (e.getActionCommand().equals("ZIP")) {
			rbtnList.get(1).setSelected(false);
			zipMode = 0;
		} else if (e.getActionCommand().equals("GZIP")) {
			rbtnList.get(0).setSelected(false);
			zipMode = 1;
		}
	}
	
}
