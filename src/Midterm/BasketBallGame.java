package Midterm;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class BasketBallGame extends javax.swing.JApplet implements ActionListener {
	int xSize = 600;
	int ySize = 400;
	BouncingBall2 game;
	String message;
	JTextArea textArea;
	JPanel container;
	public void init() {
		game = new BouncingBall2();
		game.setSize(xSize, ySize / 2);
		setSize(xSize, ySize);
		container = new JPanel();
		container.setLayout(new GridLayout(1,1));
		container.add(game);
		
		//
		textArea = new JTextArea();
		updateText();
		textArea.setText(message);
		textArea.setBackground(Color.lightGray);
		container.add(textArea);
		textArea.setEditable(false);
		Button launchBtn = new Button("Start");
		add(launchBtn);
		
		Button stopBtn = new Button("Stop");
		add(stopBtn);
		add(container);
		game.init();
		game.start();
		game.launch();
		game.stopGame();
	}
	
	
	private void updateText() {
		message = "The Basketball Game";
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
