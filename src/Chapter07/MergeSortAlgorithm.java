package Chapter07;

public class MergeSortAlgorithm extends SortAlgorithm {
	public void sort(int[] array, int[] marker) {
		if (array == null) {
			return;
		}
		int[] helper = new int[array.length];
		mergeSort(array, marker, helper, 0 , array.length - 1);
		marker[0] = array.length - 2;
		marker[1] = array.length - 1;
	}
	
	private void mergeSort(int[] array, int[] marker, int[] helper, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		mergeSort(array, marker, helper, left, mid);
		mergeSort(array, marker, helper, mid + 1, right);
		merge(array, marker, helper, left, mid, right);
	}
	
	private void merge(int[] array, int[] marker, int[] helper, int left, int mid, int right) {
		for (int i = left; i <= right; i++) {
			helper[i] = array[i];
		}
		int leftIndex = left;
		int rightIndex = mid + 1;
		marker[0] = left;
		marker[1] = right;
		while (leftIndex <= mid && rightIndex <= right) {
			if (helper[leftIndex] <= helper[rightIndex]) {
				array[left++] = helper[leftIndex++];
			} else {
				array[left++] = helper[rightIndex++];
			}
			pause();
		}
		while (leftIndex <= mid) {
			array[left++] = helper[leftIndex++];
		}
		//pause();
	}
	
	public MergeSortAlgorithm(AlgorithmAnimator animator) {
		super(animator);
	}
}


