package Chapter06;

import junit.framework.*;
/**
 * Test case for MyStack. This will apply sanity check on
 * the basic stack's function, including push, pop, peek, 
 * isEmpty, and keeping track of the maximum and minimum, etc.
 * @author Zhitao Wang
 */
public class MyTest extends TestCase {
	
	public MyTest(String Name) {
		super(Name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPush() {
		StackArray stack = new StackArray();
		assertTrue(stack.size() == 0);
		assertTrue(stack.top() == null);
		stack.push(null);
		assertTrue("null is not allowed to added to stack.", 
				stack.size() == 0);
		Integer[] numbers = new Integer[] {1, 2, 3, 4, 5, 6, 7};
		System.out.println("No Element: " + stack.top() + 
				" Size: " + stack.size());
		System.out.println("----- Test Push Elements -------");
		int i = 0;
		for (i = 0; i < numbers.length; i++) {
			stack.push(numbers[i]);
			System.out.println("Push Element: " + stack.top() + 
					" Increase Size to: " + stack.size());
			assertTrue("Size increases incorrectly.", 
					stack.size() == i+1);
			assertTrue("Top element updates incorrectly.", 
					stack.top() == numbers[i]);
		}
		System.out.println("------- Complete Test Push Elements -------");
	}
	
	public void testPop() {
		StackArray stack = new StackArray();
		Integer[] numbers = new Integer[] {0, 2, 3, 4, 5, 6, 7};
		int i = 0;
		for (i = 0; i < numbers.length; i++) {
			stack.push(numbers[i]);
		}
		System.out.println("------- Test Pop Elements -------");
		while(!stack.isEmpty()) {
			assertTrue("Size decreases incorrectly.", 
					stack.size() == i);
			assertTrue("Poped element not matched!", 
					stack.pop() == numbers[--i]);
			System.out.println("Pop Element: " + 
					stack.top() + " Decrease Size to: " + 
					stack.size());
			assertTrue(stack.size() == i);
		}
		System.out.println("------- Complete Test Pop Elements -------");
	}
	
	public void testMinMax() {
		StackArray stack = new StackArray();
		stack.push(null);
		assertTrue(stack.getMax() == null);
		assertTrue(stack.getMin() == null);
		Integer[] numbers = 
				new Integer[] {1, 2, 3, 4, 10, 1, 0, 5, 6, 7};
		Integer[] max = 
				new Integer[] {1, 2, 3, 4, 10, 10, 10, 10, 10, 10};
		Integer[] min = 
				new Integer[] {1, 1, 1, 1, 1, 1, 0, 0, 0, 0};
		// test the maximum integer while pushing integers into the stack
		System.out.println("------ "
				+ "Test Push Maximum/Minimum------");
		for (int i = 0; i < numbers.length; i++) {
			stack.push(numbers[i]);
			assertTrue(stack.getMax() == max[i]);
			assertTrue(stack.getMin() == min[i]);
		}
		System.out.println("---- "
				+ "Complete Test Push Maximum/Minimum ----");
		System.out.println("Maxium Integer in the stack: " + 
				stack.getMax());
		System.out.println("Minimum Integer in the stack: " + 
				stack.getMin());
		// test the maximum integer while popping integers out of the stack
		System.out.println("------ "
				+ "Test Pop Maximum/Minimum ------");
		int i = stack.size() - 1;
		while(!stack.isEmpty()) {
			assertTrue("Maximum integer update incorrect!", 
					stack.getMax() == max[i]);
			assertTrue("Minimum integer update incorrect!",
					stack.getMin() == min[i]);
			stack.pop();
			i--;
			System.out.println("Pop Element: " + stack.top() + 
					" , Maxium in the stack: " + stack.getMax() + 
					", Minimum Integer in the stack: " 
					+ stack.getMin());
		}
		System.out.println("---- "
				+ "Complete Test Pop Maximum/Minimum ----");
	}

	public void testIsEmpty() {
		StackArray stack = new StackArray();
		assertTrue("Stack is not emtpy!", stack.size() == 0);
		assertTrue(stack.top() == null);
		assertTrue(stack.pop() == null);
	}
	
	public void testCloneEquals() throws CloneNotSupportedException {
		System.out.println("------- Test Clone -------");
		StackArray stack = new StackArray();
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		stack.push(new Integer(3));
		stack.pop();
		StackArray clone = stack.clone();
		assertTrue("Clone is dereferenced to the 'stack' object!",
				stack.hashCode() != clone.hashCode());
		assertTrue(stack.size() == clone.size());
		assertTrue("Elements in each stack are not all identical!",
				stack.equals(clone));
		System.out.println("------- Complete Test Clone -------");
	}
	
	public static Test suite() {
		return new TestSuite(MyTest.class);
	}
}