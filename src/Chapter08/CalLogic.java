package Chapter08;
import java.awt.Color;
import java.math.BigInteger;
import javax.swing.JButton;
/**
 * The calculator logic
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
public class CalLogic {
	JButton btn; 
	//ScienceLayout calLayout;
	CalculatorLayout calLayout;
	StackArray stack;
	String label;
	// the current number in display panel
	String curNum;
	// priority of current operator: 0 for + and -; 1 for * and /; 2 for sqrt and other functional key
	String curOP;
	String isShift, isDeg, numMode;
	// the equation to be display into the history log
	String equation, result, logMessage;
	// previous trigger button type: 0 for a number, 1 for operator or function, 2 for others
	String lastInType;
	String[] numList = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	String[] opList = {"+", "\u2212", "\u00D7", "\u00f7"};
	String[] status;
	//public CalLogic (final JButton btn, final ScienceLayout calLayout, final StackArray stack, final String[] status) {
	public CalLogic (final JButton btn, final CalculatorLayout calLayout, final StackArray stack, final String[] status) {
		this.btn = btn;
		this.calLayout = calLayout;
		this.stack = stack;
		this.status = status;
		getStatus(status);
	}
	
	public void getStatus(String[] status) {	
		curNum = status[0];
		curOP = status[1];
		lastInType = status[2];
		equation = status[3];
		result = status[4];
		logMessage = status[5];
		isShift = status[6];
		isDeg = status[7];
		numMode = status[8];
	}
	
	public void setStatus() {
		status[0] = curNum;
		status[1] = curOP;
		status[2] = lastInType;
		status[3] = equation;
		status[4] = result;
		status[5] = logMessage;
		status[6] = isShift;
		status[7] = isDeg;
		status[8] = numMode;
	}
	
	public void update() {
		label = btn.getText();
		curNum = status[0];
		// pre-processing
		curNum = curNum.replace(" ", "");
		if (isEmpty()) {
			curNum = "0";
			toDisplay(curNum);
		}
		// the following check the responsible event
		// Degree or Radian
		if (label.equals("DEG")) {
			isDeg = "false";
			btn.setText("RAD");
		}
		
		if (label.equals("RAD")) {
			isDeg = "true";
			btn.setText("DEG");
		}
		// isShift
		if (label.equals("\u21E7")) {
			isShift = "true";
			btn.setText("\u21E9");
			JButton btn1 = calLayout.getBtn("sin");
			JButton btn2 = calLayout.getBtn("cos");
			JButton btn3 = calLayout.getBtn("tan");
			btn1.setText("sin\u207B\u00B9");
			btn2.setText("cos\u207B\u00B9");
			btn3.setText("tan\u207B\u00B9");
		}
		if (label.equals("\u21E9")) {
			isShift = "false";
			btn.setText("\u21E7");
			JButton btn1 = calLayout.getBtn("sin\u207B\u00B9");
			JButton btn2 = calLayout.getBtn("cos\u207B\u00B9");
			JButton btn3 = calLayout.getBtn("tan\u207B\u00B9");
			btn1.setText("sin");
			btn2.setText("cos");
			btn3.setText("tan");
		}
		
		// display mode "10" for decimal, "2" for binary, "16" for hexadecimal
		if (label.equals("DEC")) {
			numMode = "10";
		}
		if (label.equals("BIN")) {
			numMode = "2";
		}
		if (label.equals("HEX")) {
			numMode = "16";
		}

		// check number event
		for (String num:numList) {
			if (label.equals(num)) {
				if (isZero(curNum) || lastInType.equals("1")) {
					curNum = label;
				} else {
					append(label);
				}
				lastInType = "0";
			}
		}
		// check decimal
		if (label.equals(".")) {
			if (!curNum.contains(".")) {
				append(label);
			}
		}		
		// check +/-
		if (label.equals("\u00B1")) {
			if (!isZero(curNum)) {
				if (!curNum.contains("-")) {
					curNum = "-" + curNum;
				} else {
					curNum = curNum.replace("-", ""); 
				}
			}
		}
		// check CE
		if (label.equals("CE")) {
			curNum = "0";
			lastInType = "0";
		}
		// check C
		if (label.equals("C")) {
			curNum = "0";
			lastInType = "0";
			equation = "";
			logMessage = "";
			stack = new StackArray();
			System.out.println(stack.toString());
		}
		// check delete
		if (label.equals("DEL")) {
			if (curNum.length() <= 1) {
				curNum = "0";
			} else {
				curNum = curNum.substring(0, curNum.length()-1);
			}
		}
		// check PI
		if (label.equals("\u03C0")) {
			curNum = Math.PI + "";
			lastInType = "0";
		}
		
		// check recall
		if (label.equals("\u21E6")) {
			stack.pop();
			if (!stack.isEmpty()) {
				curNum = (String) stack.top();
			}
		}
		
		// check function key
		if (label.equals("%")) {
			curNum = mod(curNum);
		}
		// 1/x
		if (label.equals("1/x")) {
			curNum = inv(curNum);
		}
		// x^2
		if (label.equals("x\u00B2")) {
			curNum = pow(curNum, 2);
		}
		// x^3
		if (label.equals("x\u00B3")) {
			curNum = pow(curNum, 3);
		}
		
		// 10^x
		if (label.equals("10\u02E3")) {
			curNum = String.valueOf(Math.pow(10.0, 
					Double.parseDouble(curNum)));
		}
		
		// x^1/2
		if (label.equals("\u221Ax")) {
			curNum = sqrt(curNum);
		}
		// x^1/3
		if (label.equals("\u221Bx")) {
			curNum = cbrt(curNum);
		}
		
		// ln
		if (label.equals("ln")) {
			if (!isZero(curNum)) {
			curNum = String.valueOf(Math.log( 
					Double.parseDouble(curNum)));
			}
		}
		
		// log10
		if (label.equals("log")) {
			if (!isZero(curNum)) {
				curNum = String.valueOf(Math.log10( 
						Double.parseDouble(curNum)));
			}
		}
		// sine
		if (label.equals("sin")) {
			if (isDeg.equals("true")) {
				curNum = String.valueOf(Math.sin(
						Math.PI/180*
						Double.parseDouble(curNum)));
			} else {
				curNum = String.valueOf(Math.sin(
						Double.parseDouble(curNum)));
			}
		}
		// arc sine
		if (label.equals("sin\u207B\u00B9")) {
			if (isDeg.equals("true")) {
				curNum = String.valueOf(Math.asin(
						Double.parseDouble(curNum)) 
						* 180 / Math.PI);
			} else {
				curNum = String.valueOf(Math.asin(
						Double.parseDouble(curNum)));
			}
		}
		
		// cosine
		if (label.equals("cos")) {
			if (isDeg.equals("true")) {
				curNum = String.valueOf(Math.cos(
						Math.PI/180*
						Double.parseDouble(curNum)));
			} else {
				curNum = String.valueOf(Math.cos(
						Double.parseDouble(curNum)));
			}
		}
		// arc cosine
		if (label.equals("cos\u207B\u00B9")) {
			if (isDeg.equals("true")) {
				curNum = String.valueOf(Math.acos(
						Double.parseDouble(curNum)) 
						* 180 / Math.PI);
			} else {
				curNum = String.valueOf(Math.acos(
						Double.parseDouble(curNum)));
			}
		}
		
		// tangent
		if (label.equals("tan")) {
			double theta = Double.parseDouble(curNum);
			if (isDeg.equals("true")) {
				curNum = String.valueOf(Math.tan(
						Math.PI/180*
						theta));
			} else {
				curNum = String.valueOf(Math.tan(
						theta));
			}
		}
		// arc tangent
		if (label.equals("tan\u207B\u00B9")) {
			if (isDeg.equals("true")) {
				curNum = String.valueOf(Math.atan(
						Double.parseDouble(curNum)) 
						* 180 / Math.PI);
			} else {
				curNum = String.valueOf(Math.atan(
						Double.parseDouble(curNum)));
			}
		}
		// check operator
		for (String op:opList) {
			if (label.equals(op)) {
				// if current is the operator and before is a number 
				if (lastInType.equals("0")) {
					equation = equation + curNum;
					logMessage = logMessage + curNum;
					//----------------------------------
					curOP = findPriority(label);
					processText(curOP);
					//----------------------------------
					equation += label;
					logMessage += label;
					lastInType = "1";
				} else if (lastInType.equals("1")) {
					if (equation.length() > 1) {
						equation = equation.substring(0, equation.length()-1) + label;
					}
				}
			}
		}
		
		// check equal
		if (label.equals("=")) {
			if (lastInType.equals("0")) {
				equation += curNum;
				logMessage += curNum;
				curOP = "0";
				processText(curOP);
				logMessage = logMessage + label + curNum + "\n";
				// push the final result to stack
				stack.push(curNum);
				result = "";
				equation = "";
				lastInType = "0";
			}
		}
		toDisplay(curNum);
		calLayout.setLogText(logMessage);
		setStatus();
		updateBtnColor();
	}
	
	// display current result
	public void toDisplay(String curNum) {
		String output = "";
		Double number = Double.parseDouble(curNum);
		if (numMode.equals("2")) {
			output = Integer.toString(number.intValue(), 2);
		} else if (numMode.equals("16")) {
			output = Integer.toString(number.intValue(), 16);
		} else {
			output = curNum;
		}
		calLayout.setDisplayText(output + "   ");
	}
	
	// simple algorithm to process the calculation
	public void processText(String curOP) {
		int indexOfLastOP = findRightmostOP(equation);
		while (indexOfLastOP != -1) {
			String prevOP = findPriority(String.valueOf(equation.charAt(indexOfLastOP)));
			int order = comparePriority(prevOP, curOP);
			// if the priority of curOP is no higher than prevOP, process inputEQ, otherwise exit the loop
			if (order != -1) {
				int bidx = findRightmostOP(equation.substring(0, indexOfLastOP))+ 1;
				String substring1 = equation.substring(0, bidx);
				String substring2 = equation.substring(bidx);
				result = calculate(substring2);
				equation = substring1 + result;
				indexOfLastOP = findRightmostOP(equation);
				curNum = result;
			} else {
				break;
			}
		}
		// do nothing if there is no operator in the inputEQ
	}
	
	// calculate the equation a op b
	public String calculate(String equation) {
		String result = "", a, b, OP;
		boolean isDouble = false;
		int idx = findRightmostOP(equation);
		a = equation.substring(0,idx);
		b = equation.substring(idx+1);
		b.replace("\u2212", "-");
		System.out.println(a + ", " + b); 
		if (a.contains(".") || b.contains(".")) {
			isDouble = true;
		}
		OP = String.valueOf(equation.charAt(idx));
		
		if (OP.equals(opList[0])) {
			result = add(a, b, isDouble);
		} else if (OP.equals(opList[1])) {
			result = sub(a, b, isDouble);
		} else if (OP.equals(opList[2])) {
			result = mul(a, b, isDouble);
		} else {
			result = div(a, b, isDouble);
		}
		return result;
	}
	
	// addition a + b
	public String add(String a, String b, boolean isDouble) {
		String result = "Infinity";
		if (!a.equals("Infinity")) {
			if (!isDouble) {
				BigInteger temp1 = new BigInteger(a);
				BigInteger temp2 = new BigInteger(b);
				result = String.valueOf(temp1.add(temp2));
			} else {
				double temp = Double.parseDouble(a) + Double.parseDouble(b);
				result = Double.toString(temp);
			}
		}
		return result;
	}
	
	// subtraction a - b
	public String sub(String a, String b, boolean isDouble) {
		String result = "Infinity";
			if (!a.equals("Infinity")) {
			if (!isDouble) {
				BigInteger temp1 = new BigInteger(a);
				BigInteger temp2 = new BigInteger(b);
				result = String.valueOf(temp1.subtract(temp2));
			} else {
				double temp = Double.parseDouble(a) - Double.parseDouble(b);
				result = Double.toString(temp);
			}
		}
		return result;
	}
	
	// multiplication a * b
	public String mul(String a, String b, boolean isDouble) {
		String result = "";
		if (!a.equals("Infinity")) {
			if (!isDouble) {
				BigInteger temp1 = new BigInteger(a);
				BigInteger temp2 = new BigInteger(b);
				result = String.valueOf(temp1.multiply(temp2));
			} else {
				double temp = Double.parseDouble(a) * Double.parseDouble(b);
				result = Double.toString(temp);
			}
		}
		return result;
	}
	
	// divide a / b
	public String div(String a, String b, boolean isDouble) {
		String result = "Infinity";
		if (!a.equals("Infinity")) {
			if (!isDouble) {
				double temp = Double.parseDouble(a) / Double.parseDouble(b);
				if (temp == Math.floor(temp) && !Double.isInfinite(temp)) {
					result = Integer.toString((int) temp);
				} else {
					result = Double.toString(temp);
				}
	
			} else {
				double temp = Double.parseDouble(a) / Double.parseDouble(b);
				result = Double.toString(temp);
			}
		}
		return result;
	}
	
	public String mod(String a) {
		int temp = (int) Double.parseDouble(a) % 2;
		if (temp < 0) {
			temp += 2;
		}
		return String.valueOf(temp);
	}
	
	
	public String inv(String a) {
		double temp = Double.parseDouble(a);
		if (a.equals("0")) {
			return a;
		}
		return String.valueOf(1 / temp);
	}
	
	public String pow(String a, int exponent) {
		if (a.length() < 15) {
			if (isDouble(a)) {
				double temp = Double.parseDouble(a);
				return String.valueOf(temp*temp);
			}
			BigInteger number = new BigInteger(a);
			return String.valueOf(number.pow(exponent));
		}
		return a;
	}
	
	public String sqrt(String a) {
		if (a.length() < 15) {
			double temp = Double.parseDouble(a);
			return String.valueOf(Math.sqrt(temp));
		}
		return a;
	}
	
	public String cbrt(String a) {
		if (a.length() < 15) {
			double temp = Double.parseDouble(a);
			return String.valueOf(Math.cbrt(temp));
		}
		return a;
	}
	
	public String exp(String a) {
		if (a.length() < 15) {
			double temp = Double.parseDouble(a);
			return String.valueOf(Math.exp(temp));
		}
		return a;
	}
		
	// Compare the priority of two operators.
	// return 0 if OP1 and OP2 has the same priority
	// return 1 if OP1 has higher priority
	// return -1 if OP1 has lower priority
	public int comparePriority(String OP1, String OP2) {
		int order1 = Integer.parseInt(OP1);
		int order2 = Integer.parseInt(OP2);
		if (order1 == order2) {
			return 0;
		} else if (order1 > order2) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public String findPriority(String OP) {
		String outputOP = "";
		if (OP.equals(opList[0]) || OP.equals(opList[1])) {
			outputOP = "0"; 
		} else if (OP.equals(opList[2]) || OP.equals(opList[3])) {
			outputOP = "1";
		}
		// add other operator below
		return outputOP;
	}
	
	// return the index of the rightmost operator in the inputEQ, 
	// return -1 if inputEQ contains no operator
	public int findRightmostOP(String inputEQ) {
		int index = -1;
		if (inputEQ.length() > 0) {
			for (index = inputEQ.length() - 1; index >= 0; index--) {
				if (isOP(inputEQ.charAt(index))) {
					return index;
				}
			}
		}
		return index;
	}
	
	// check if inputChar is an operator
	public boolean isOP(char inputChar) {
		for (String op:opList) {
			if (String.valueOf(inputChar).equals(op)) {
				return true;
			}
		}
		return false;
	}	
		
	// append the label to the current number
	public void append(String label) {
		if (curNum.length() < 20) {
			curNum += label;
		}
	}
	
	// check if the display is empty
	public boolean isEmpty() {
		if (calLayout.getDisplayText().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// check if the input number is a double or integer
	public boolean isDouble(String inputNum) {
		if (inputNum.contains(".")) {
			return true;
		}
		return false;
	}
	
	//
	public boolean isInteger(double variable) {
		if ((variable == Math.floor(variable)) && !Double.isInfinite(variable)) {
			return true;
		}
		return false;
	}
	
	// check if the number is zero
	public boolean isZero(String inputNum) {
		if (!isDouble(inputNum) && inputNum.length() < 2) {
			int temp = Integer.parseInt(inputNum);
			if (temp ==0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void updateBtnColor() {
		try {
			JButton btn1 = calLayout.getBtn("DEC");
			JButton btn2 = calLayout.getBtn("BIN");
			JButton btn3 = calLayout.getBtn("HEX");
			btn1.setBackground(Color.LIGHT_GRAY);
			btn2.setBackground(Color.LIGHT_GRAY);
			btn3.setBackground(Color.LIGHT_GRAY);
			if (numMode.equals("10")) {
				btn1.setBackground(Color.gray);
			} else if (numMode.equals("2")) {
				btn2.setBackground(Color.gray);
			} else {
				btn3.setBackground(Color.gray);
			}
		} catch(Exception m) {}
	}
		
}
