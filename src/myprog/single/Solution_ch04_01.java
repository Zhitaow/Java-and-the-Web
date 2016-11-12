package myprog.single;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Exercise 4.1 Calculate the factorial of an integer n using iterations.
 *  The input value is given as a command-line argument to the Java application.
 */

public class Solution_ch04_01 {
	public static void main(String[] args) {
		// create a scanner so we can read the command-line input
		Scanner scanner = new Scanner(System.in);

	    //  prompt for hints
	    System.out.print("Enter a positive integer to calculate its factorial: ");

	    try {
	    	// get the input as an integer
	    	int number = scanner.nextInt();
	    	if (number >= 0){	    	
		    	BigInteger result = factorial(number);
		    	// prompt for the result
		    	System.out.println(String.format("The factorial of %d is %d", number, result));
	    	} else {
	    		System.out.println(String.format("Input interger must be non-negative!"));
	    	}
	    }catch(InputMismatchException e){
	        //executes when this exception occurs
	        System.out.println("Input has to be an interger.");
	    }
	    scanner.close();
	}
	
	public static BigInteger factorial(int n) {
		BigInteger result = BigInteger.valueOf(1);
		// Base case
		if (n == 0) {
			return result;
		}
		// Calcualte n! for n >= 1
		for (int i = 1; i <= n; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}
}
