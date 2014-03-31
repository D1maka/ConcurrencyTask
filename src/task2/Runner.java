package task2;


public class Runner {
	
	public static final int N = 7;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] integerArray = new Integer[N];
		
		for (int i = 0; i < integerArray.length; i++) {
			integerArray[i] = (int)(Math.random() * 100);
		}
		
		for (Integer integer : integerArray) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		MergeSort<Integer> mergeSorter = new MergeSort<Integer>(integerArray);
		mergeSorter.sort();
		
		for (Integer integer : integerArray) {
			System.out.print(integer + " ");
		}
		
	}

}
