package Chapter07;

public class BubbleSortAlgorithm extends SortAlgorithm {
	// add marker
	public void sort(int[] a, int[] marker) {
		for (int i = a.length; --i >= 0;) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j+1]) {
					swap(a, j, j+1);
					marker[0] = j;
					marker[1] = j+1;
				}
				pause();
			}
		}
	}
	
	public BubbleSortAlgorithm(AlgorithmAnimator animator) {
		super(animator);
	}
} 
