package task3;

import java.util.ArrayList;

public class CircularBuffer<T> {
	private ArrayList<T> data;
	private int startIndex;
	private int endIndex;

	public CircularBuffer(int size) {
		data = new ArrayList<T>(size);
		startIndex = endIndex = 0;
	}

	public T get() {
		T elem = data.get(startIndex);
		startIndex = (startIndex + 1) % data.size();
		return elem;
	}

	public void add(T value) {
		data.add(endIndex, value);
		
	}
	
	public boolean isEmpty() {
		return startIndex == endIndex;
	}
	
	public boolean isFull() {
		int newLastIndex = (endIndex + 1) % data.size();
		return newLastIndex == startIndex; 
	}
}
