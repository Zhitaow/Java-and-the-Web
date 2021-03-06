package Chapter06;
/**
 * The Stack interface
 * @author Zhitao Wang
 * @version 1.0
 */
public interface MyStack {
		
	/**
	 * Push the item into the stack
	 * @post size() = size() + 1
	 * @param the item to be pushed
	 */
	public void push(Object obj);
	
	/**
	 * @post size() = size() - 1
	 * @return the top-most item in the stack
	 */
	public Object pop();
	
	/**
	 * @post size() = size()
	 * @return the top-most item in the stack
	 */
	public Object top();
	
	/**
	 * @return true if the stack is empty
	 */
	public boolean isEmpty();
	
	/**
	 * @return the total number of objects 
	 * in the stack
	 */
	public int size();

}
