package Chapter07;

public class SelectionSortAlgorithm extends SortAlgorithm {
	public void sort(int[] array, int[] marker) {
		if (array == null || array.length ==0) {
			return;
		}
		int i, j, tmp, idx_tmp;
		for (i = 0; i < array.length - 1; i++) {
			idx_tmp = i;
			tmp = array[i];
			for (j = i + 1; j < array.length; j++) {
				if (array[idx_tmp] > array[j]) {
					idx_tmp = j;
				}
				marker[0] = i;
				marker[1] = j;
				pause();
			}
			array[i] = array[idx_tmp];
			array[idx_tmp] = tmp;
		}
	}
	
	
	public SelectionSortAlgorithm(AlgorithmAnimator animator) {
		super(animator);
	}
}
