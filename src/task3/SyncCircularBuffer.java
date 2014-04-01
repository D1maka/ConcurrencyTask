package task3;

import java.util.ArrayList;

public class SyncCircularBuffer<T> implements MyBuffer<T> {
	private ArrayList<T> data;
	private int startIndex;
	private int endIndex;
	private int maxSize;

	public SyncCircularBuffer(int size) {
		data = new ArrayList<T>(size);
		maxSize = size;
		maxSize = size;
		startIndex = endIndex = 0;
	}

	public synchronized T get() {
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		T value = data.get(startIndex);
		startIndex = (startIndex + 1) % maxSize;
		notifyAll();
		return value;
	}

	public synchronized void add(T value) {
		while (isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		data.add(endIndex, value);
		endIndex = (endIndex + 1) % maxSize;
		if (endIndex == startIndex) {
			startIndex = (startIndex + 1) % maxSize;
		}
		notifyAll();
	}

	public synchronized boolean isEmpty() {
		return startIndex == endIndex;
	}

	public synchronized boolean isFull() {
		if (data.size() == 0) {
			return false;
		} else {
			return ((endIndex + 1) % maxSize) == startIndex;
		}
	}
}
