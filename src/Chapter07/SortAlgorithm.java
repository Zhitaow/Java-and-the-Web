package Chapter07;

public abstract class SortAlgorithm {
	private AlgorithmAnimator animator;
	// add marker
	abstract public void sort(int[] a, int marker[]);
	
	protected SortAlgorithm(AlgorithmAnimator animator) {
		this.animator = animator;
	}
	
	protected void pause() {
		if (animator != null) {
			animator.pause();
		}
	}
	
	protected static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
