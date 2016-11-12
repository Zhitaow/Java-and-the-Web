package Chapter07;

public class QuickSortAlgorithm extends SortAlgorithm {
	
	public QuickSortAlgorithm(AlgorithmAnimator animator) {
		super(animator);
	}
	// add marker
	protected void qsort(int[] a, int[] marker, int lo0, int hi0) {
		int lo = lo0;
		int hi = hi0;
		int mid;
		pause();
		if (hi0 >lo0) {
			mid = a[(lo0 +hi0) / 2];
			while (lo <= hi) {
				while ((lo <hi0) && (a[lo] < mid)) {
					lo++;
				}
				while ((hi > lo0) && (a[hi] > mid)) {
					hi--;
				}
				if (lo <= hi) {
					swap(a, lo, hi);
					marker[0] = lo;
					marker[1] = hi;
					pause();
					lo++;
					hi--;
				}
			}
			if (lo0 < hi) {
				qsort(a, marker, lo0, hi);
			}
			if (lo < hi0) {
				qsort(a, marker, lo, hi0);
			}
		}
	}
	// add marker
	public void sort(int a[], int[] marker) {
		qsort(a, marker, 0, a.length - 1);
	}
}
