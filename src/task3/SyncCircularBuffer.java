package task3;

import java.util.ArrayList;

public class SyncCircularBuffer<T> implements MyBuffer<T> {
	private ArrayList<T> data;
	private int startIndex;
	private int endIndex;

	public SyncCircularBuffer(int size) {
		data = new ArrayList<T>(size);
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
		T elem = data.get(startIndex);
		startIndex = (startIndex + 1) % data.size();
		notifyAll();
		return elem;
	}

	public synchronized void add(T value) {
		while(isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		data.add(endIndex, value);
		endIndex = (endIndex + 1) % data.size();
		if (endIndex == startIndex) {
			startIndex = (startIndex + 1) % data.size();
		}
		notifyAll();
	}

	public synchronized boolean isEmpty() {
		return startIndex == endIndex;
	}

	public synchronized boolean isFull() {
		return ((endIndex + 1) % data.size()) == startIndex;
	}
}
