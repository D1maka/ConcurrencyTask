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
		T value = data.get(startIndex);
		System.out.println("Value " + value + "removed from buffer");
		startIndex = (startIndex + 1) % data.size();
		notifyAll();
		return value;
	}

	public synchronized void add(T value) {
		while(isFull()) {
			try {
//				System.out.println(Thread.currentThread().toString() + " " + isFull());
//				for (T entry : data) {
//					System.out.print(" " + entry);
//				}
//				System.out.println();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		data.add(endIndex, value);
		System.out.println("Value " + value + "added to buffer");
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
		if (data.size() == 0) {
			return false;
		} else {
			return ((endIndex + 1) % data.size()) == startIndex;
		}
	}
}
