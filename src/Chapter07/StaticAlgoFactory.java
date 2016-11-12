package Chapter07;

public class StaticAlgoFactory implements AlgorithmFactory {
	protected AlgorithmAnimator animator;
	
	public StaticAlgoFactory(AlgorithmAnimator animator) {
		this.animator = animator;
	}
	
	public SortAlgorithm makeSortAlgorithm(String algName) {
		if ("BubbleSort".equals(algName)) {
			return new BubbleSortAlgorithm(animator);
		} else if ("QuickSort".equals(algName))
			return new QuickSortAlgorithm(animator);
		else if ("MergeSort".equals(algName))
			return new MergeSortAlgorithm(animator);
		else if ("SelectionSort".equals(algName))
			return new SelectionSortAlgorithm(animator);
		else if ("HeapSort".equals(algName))
			return new HeapSortAlgorithm(animator);
		return new BubbleSortAlgorithm(animator);
	}
}
