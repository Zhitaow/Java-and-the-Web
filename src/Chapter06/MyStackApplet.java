package Chapter06;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
/**
 * MyStackApplet allows user to 
 * 1. perform actions including push, pop, and peek. The applet can 
 *    show the current status in the stack, including the information
 *    of the size, maximum and minimum integer in the stack.
 * 2. convert the infix input to outfix expression. This is a simple
 *    application in calculator.
 * @author Zhitao Wang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MyStackApplet extends Applet {
    // window's size
    int xSize = 600, ySize = 450;
    // input field
    TextField input, inputInfix;
    // output field
    JTextPane textBox, status, maxBox, minBox, 
    	postfixBox, sizeBox;
    // title field
    JTextPane title1, title2, title3, title4, reminder1, reminder2;
    // scrolling pane hosting the stack
    JScrollPane scrollBox;
    // functional buttons
    Button pushBtn, popBtn, peekBtn, convertBtn;
    StackArray stack = new StackArray();
	public void init() {
//		JFrame f = new JFrame("Swing Paint Demo");
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //f.setLayout(null);
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        
        setSize(xSize, ySize);
        setBackground(Color.gray);
        setLayout(null);
        // set up the stack title
        title1 = new JTextPane();
        title1.setBackground(Color.gray);
        title1.setFont(new Font(Font.SANS_SERIF, 20, 30));
        title1.setText("Stack");
        title1.setBounds(60, 10, 100, 40);
        add(title1);
        
//        f.add(title1);
//        f.setLocation(60, 10);
//        f.setVisible(true);
        contentPane.add(title1);
//        f.setContentPane(contentPane);
//        f.setSize(xSize,ySize);
//        f.setBackground(Color.gray);
//        f.setVisible(true);
        
        // set up the status title
        title2 = new JTextPane();
        title2.setBackground(Color.gray);
        title2.setFont(new Font(Font.SANS_SERIF, 20, 30));
        title2.setText("Status");
        title2.setBounds(230, 10, 100, 40);
        add(title2);
        // set up the action title
        title2 = new JTextPane();
        title2.setBackground(Color.gray);
        title2.setFont(new Font(Font.SANS_SERIF, 20, 30));
        title2.setText("Action");
        title2.setBounds(420, 10, 100, 40);
        add(title2);
        // set up the title
        title1 = new JTextPane();
        title1.setBackground(Color.gray);
        title1.setFont(new Font(Font.SANS_SERIF, 20, 30));
        title1.setText("Stack");
        title1.setBounds(60, 10, 100, 40);
        add(title1);
        // text input
        input = new TextField();
        input.setBounds(400, 60, 140, 20);
        add(input);
        input.setColumns(20);
        // scrolling text box containing the stack information
        textBox = new JTextPane();
        textBox.setEditable(false);
        scrollBox = new JScrollPane(textBox);
        scrollBox.setBounds(25, 60, 150, 140);
        scrollBox.setBorder(null);
        add(scrollBox);
        // text box to show the action of push, pop and top
        status = new JTextPane();
        status.setBounds(200, 60, 150, 20);
        status.setEditable(false);
        add(status);
        // text box to show the maximum value in the stack
        maxBox = new JTextPane();
        maxBox.setBounds(200, 100, 150, 20);
        maxBox.setEditable(false);
        maxBox.setText("Empty");
        add(maxBox);
        // text box to show the minimum value in the stack
        minBox = new JTextPane();
        minBox.setBounds(200, 140, 150, 20);
        minBox.setEditable(false);
        minBox.setText("Empty");
        add(minBox);
        // text box to show the size in the stack
        sizeBox = new JTextPane();
        sizeBox.setBounds(200, 180, 150, 20);
        sizeBox.setEditable(false);
        sizeBox.setText("Stack size is " + stack.size());
        add(sizeBox);
        // set up push button
        pushBtn = new Button("Push");
        pushBtn.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
        	  String stringInput = input.getText();
        	  if (stringInput.length() > 0) {
        		  stack.push(stringInput);
        		  textBox.setText(stack.toString());
        		  status.setText(stringInput + " is pushed!");
        		  maxBox.setText(stack.getMax() + 
        				  " is stack's maximum");
        		  minBox.setText(stack.getMin() + 
        				  " is stack's minimum");
        		  sizeBox.setText("Stack size is " + stack.size());
        		  repaint();
        	  }
          }});
        pushBtn.setBounds(400, 100, 140, 20);
        add(pushBtn);
        // set up pop button
        popBtn = new Button("Pop");
        popBtn.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
        	  if (!stack.isEmpty()) {
        		  String s = stack.pop().toString();
        		  textBox.setText(stack.toString());
        		  status.setText(s + " is poped!");
        		  if (!stack.isEmpty()) {
	        		  maxBox.setText(stack.getMax() + 
	        				  " is stack's maximum");
	        		  minBox.setText(stack.getMin() + 
	        				  " is stack's minimum");
	        		  sizeBox.setText("Stack size is " 
	        				  + stack.size());
        		  } else {
            		  maxBox.setText("Empty");
            		  minBox.setText("Empty");
            		  sizeBox.setText("Stack size is " 
            				  + stack.size());
        		  }
        		  repaint();
        	  }
          }});
        popBtn.setBounds(400, 140, 140, 20);
        add(popBtn);
        
        // set up peek button
        peekBtn = new Button("Top");
        peekBtn.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
        	  if (!stack.isEmpty()) {
        		  String s = stack.top().toString();
        		  textBox.setText(stack.toString());
        		  status.setText(s + " is peeked!");
        		  repaint();
        	  }
          }});
        peekBtn.setBounds(400, 180, 140, 20);
        add(peekBtn);
        
        // set up some titles
        title4 = new JTextPane();
        title4.setBackground(Color.gray);
        title4.setForeground(Color.black);
        title4.setFont(new Font(Font.SANS_SERIF, 20, 30));
        title4.setText("Application: Reverse Polish Notation");
        title4.setBounds(40, 220, 600, 50);
        add(title4);
        
        reminder1 = new JTextPane();
        reminder1.setBackground(Color.gray);
        reminder1.setForeground(Color.blue);
        reminder1.setFont(new Font(Font.SANS_SERIF, 20, 15));
        reminder1.setText("Enter an infix expresson e.g. (5 + 3) * 12 / 3");
        reminder1.setBounds(25, 270, 300, 30);
        add(reminder1);

        reminder2 = new JTextPane();
        reminder2.setBackground(Color.gray);
        reminder2.setForeground(Color.blue);
        reminder2.setFont(new Font(Font.SANS_SERIF, 20, 15));
        reminder2.setText("Postfix expression:");
        reminder2.setBounds(25, 340, 300, 30);
        add(reminder2);
        // infix input
        inputInfix = new TextField();
        inputInfix.setBounds(25, 300, 300, 30);
        inputInfix.setFont(new Font(Font.SANS_SERIF, 20, 20));
        add(inputInfix);
        inputInfix.setColumns(20);
        // text box to show the postfix result
        postfixBox = new JTextPane();
        postfixBox.setBounds(25, 380, 300, 30);
        postfixBox.setFont(new Font(Font.SANS_SERIF, 20, 20));
        postfixBox.setEditable(false);
        add(postfixBox);
        // set up convert button
        convertBtn = new Button("Convert");
        convertBtn.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
        	  String stringInput = inputInfix.getText();
        	  if (stringInput.length() > 0) {
        	      InToPost theTrans = new InToPost(stringInput);
        	      String output = theTrans.doTrans();
        	      postfixBox.setText(output);
        		  repaint();
        	  }
          }});
        convertBtn.setBounds(400, 300, 140, 20);
        add(convertBtn);
	}
}
