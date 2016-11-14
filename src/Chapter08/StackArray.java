package Chapter08;
import java.util.LinkedList;
/**
 * The StackArray
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
public class StackArray implements MyStack, Cloneable {
	// an array used to store objects in the stack
	private Object[] stack;
	// the current size of MyStack's instance
	private int size;
	// a linkedlist stack to keep the maximum integer in the "stack"
	private LinkedList<Integer> maxStack = new LinkedList<Integer>();
	// a linkedlist stack to keep the minimum integer in the "stack"
	private LinkedList<Integer> minStack = new LinkedList<Integer>();
	
	public StackArray() {
		size = 0;
		stack = new Object[10];
	}
	
	public StackArray(int size) {
		this.size = size;
		stack = new Object[size];
	}
	
	@Override
	public void empty() {
		size = 0;
		stack = new Object[10];	
	}
	
	@Override
	public void push(Object obj) {
		// TODO Auto-generated method stub
		// corner case: if obj is null, no need to push.
		if (obj == null) {
			return;
		}
		// double the array size if the stack is full
		if (isFull()) {
			int newSize = stack.length * 2;
			System.out.println("Double the array length from " 
					+ stack.length + " to " + newSize);
			Object[] newStack = new Object[newSize];
			// copy old items to the new array
			for (int i = 0; i < size; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
		stack[size++] = obj;
		updateMinMax("Push");
	}

	
	public void updateMinMax(String action) {		
		if (top() instanceof Integer || top() instanceof String) {
			try {
				Integer top;
				if (top() instanceof Integer) {
					top = (Integer) top();
				} else {
					top = Integer.parseInt((String) top());
				}
			
				if (action.equals("Push")) {
					if (maxStack.peekFirst() == null || 
							maxStack.peekFirst() <= top) {
						maxStack.addFirst(top);
					}
					if (minStack.peekFirst() == null || 
							minStack.peekFirst() >= top) {
						minStack.addFirst(top);
					}
				}
				if (action.equals("Pop")) {
					if (maxStack.peekFirst() == top) {
						maxStack.removeFirst();
					}
					if (minStack.peek() == top) {
						minStack.removeFirst();
					}
				}
			} catch (Exception e) {}
		}
	}
	
	public Integer getMin() {
		return minStack.peekFirst();
	}
	
	public Integer getMax() {
		return maxStack.peekFirst();
	}
	
	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		updateMinMax("Pop");
		size--;
		return stack[size];
	}

	@Override
	public Object top() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		return stack[size - 1];
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isFull() {
		if (size == stack.length) {
			return true;
		}
		return false;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MyStack)) {
			return false;
		}
		MyStack another = (MyStack) obj;
		/* check each of the objects in the two Stack
		 * if they are the same
		 */
		if (this.size != another.size()) {
			return false;
		}
		for (int i = 0; i < this.size; i++) {
			if (this.stack[i] != another.peekAtIndex(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Object peekAtIndex(int index) {
		// TODO Auto-generated method stub
		// if stack is empty or the index is out of bound, return null
		if (isEmpty() || index >= stack.length) {
			return null;
		}
		return stack[index];
	}
		
	@Override
	public StackArray clone() throws CloneNotSupportedException{
		StackArray clone = (StackArray) super.clone();
		return clone;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
//		sb.append("\n------------ Top ------------\n");
		if (isEmpty()) {
			sb.append("Empty\n");
		}
		for (int i = 0; i < size; i++) {
			sb.append(stack[i].toString() + " \n");
//			if (i > 0) {
//				sb.append("------------------------------\n");
//			} else {
//				sb.append("---------- Bottom ---------\n");
//			}
		}
		return sb.toString();
	}
}
