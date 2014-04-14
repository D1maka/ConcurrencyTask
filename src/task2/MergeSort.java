package task2;

public class MergeSort<T extends Comparable> {
	private T[] sortedArray;
	private T[] unsortedArray;

	public MergeSort(T[] array) {
		sortedArray = (T[]) new Comparable[array.length];
		unsortedArray = array;
	}
	
	public void sort() {
		sort(unsortedArray, 0, sortedArray.length - 1);
	}

	private void sort(T[] array, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}

		int midIndex = startIndex + (endIndex - startIndex) / 2;	
		Thread rightSorterThread = new Thread(new Sorter(array, midIndex + 1,
				endIndex));
		rightSorterThread.start();
		sort(array, startIndex, midIndex);
		try {
			rightSorterThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		merge(array, startIndex, midIndex, endIndex);
	}

	private void merge(T[] array, int startIndex, int midIndex, int endIndex) {
		int lowerIterator = startIndex;
		int higherIterator = midIndex + 1;
		
		for (int i = startIndex; i <= endIndex; i++) {
			sortedArray[i] = array[i];
		}
		
		for (int i = startIndex; i <= endIndex; i++) {
			//System.out.print(array[lowerIterator] + " " + array[higherIterator]);
			if (lowerIterator > midIndex) {
				array[i] = sortedArray[higherIterator++];
			} else if (higherIterator > endIndex) {
				array[i] = sortedArray[lowerIterator++];
			} else if (sortedArray[lowerIterator].compareTo(sortedArray[higherIterator]) < 0) {
				array[i] = sortedArray[lowerIterator++];
			} else {
				array[i] = sortedArray[higherIterator++];
			}
		}
	}

	private class Sorter implements Runnable {

		private T[] array;
		private int startIndex;
		private int endIndex;

		public Sorter(T[] array, int startIndex, int endIndex) {
			this.array = array;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			sort(array, startIndex, endIndex);
		}

	}
}
