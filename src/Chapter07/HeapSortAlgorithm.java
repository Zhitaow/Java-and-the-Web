package Chapter07;

import java.util.PriorityQueue;

public class HeapSortAlgorithm extends SortAlgorithm {
	
	public void sort(int[] array, int[] marker) {
	
		if (array == null || array.length == 0) {
			return;
		}
		PriorityQueue<Integer> minHeap = 
				new PriorityQueue<Integer>(array.length);
		// heapify
		for (int i = 0; i < array.length; i++) {
			minHeap.offer(array[i]);
			marker[0] = 0;
			marker[1] = i;
			pause();
		}
		
		for (int i = 0; i < array.length; i++) {
			array[i] = minHeap.poll();
			marker[0] = i;
			marker[1] = i;
			pause();
		}
		
		
	}
	
	public HeapSortAlgorithm(AlgorithmAnimator animator) {
		super(animator);
	}
}
