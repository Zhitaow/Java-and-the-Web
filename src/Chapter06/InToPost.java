package Chapter06;
/**
 * reverse polish notation: 
 * 1. Create a stack
   2. For each character t in the expression
   - If t is an operand, append it to the output
   - Else if t is ')',then pop from the stack till '(' is encountered and append 
     it to the output. do not append '(' to the output
   - If t is an operator or '('
        -- If t has higher precedence than the top of the stack, then push t 
           on to the stack.
        -- If t has lower precedence than top of the stack, then keep popping 
           from the stack and appending to the output until either stack is 
           empty or a lower priority operator is encountered

    After the input is over, keep popping and appending to the output until the
    stack is empty.
 * @author Zhitao Wang
 * @version 1.0
 * {@link https://www.tutorialspoint.com/javaexamples/data_intopost.htm}
 */
public class InToPost {
   private MyStack theStack;
   private String input;
   private String output = "";
   /**
    * The constructor of an IntoPost instance
    * @param in String as an input
    */
   public InToPost(String in) {
      input = in;
      theStack = new StackArray();
   }
   /**
    * Translate the infix to post fix expression
    * @return a string of postfix notation
    */
   public String doTrans() {
      for (int j = 0; j < input.length(); j++) {
         char ch = input.charAt(j);
         switch (ch) {
            case '+': 
            case '-':
            gotOper(ch, 1); 
            break; 
            case '*': 
            case '/':
            gotOper(ch, 2); 
            break; 
            case '(': 
            theStack.push(ch);
            break;
            case ')': 
            gotParen(ch); 
            break;
//            case ' ':
//            break;
            default: 
            output = output + ch; 
            break;
         }
      }
      while (!theStack.isEmpty()) {
         output = output + theStack.pop();
      }
      System.out.println(output);
      return output; 
   }
   
   public void gotOper(char opThis, int prec1) {
	  
      while (!theStack.isEmpty()) {
    	 char opTop = (char) theStack.pop();
         
         if (opTop == '(') {
            theStack.push(opTop);
            break;
         }
         else {
            int prec2;
            if (opTop == '+' || opTop == '-')
            prec2 = 1;
            else
            prec2 = 2;
            if (prec2 < prec1) { 
               theStack.push(opTop);
               break;
            }
		    else
            output = output + opTop;
         }
      }
      theStack.push(opThis);
   }
   public void gotParen(char ch){ 
      while (!theStack.isEmpty()) {
         char chx = (char) theStack.pop();
         if (chx == '(') 
         break; 
         else
         output = output + chx; 
      }
   }
}



