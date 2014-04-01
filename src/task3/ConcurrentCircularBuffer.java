package task3;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCircularBuffer<T> implements MyBuffer<T> {

	private ArrayList<T> data;
	private int startIndex;
	private int endIndex;
	private int maxSize;
	private Lock lock;
	private Condition isEmptyCondition;
	private Condition isFullCondition;

	public ConcurrentCircularBuffer(int size) {
		data = new ArrayList<T>(size);
		startIndex = endIndex = 0;
		maxSize = size;
		lock = new ReentrantLock();
		isEmptyCondition = lock.newCondition();
		isFullCondition = lock.newCondition();
	}

	@Override
	public void add(T value) {
		lock.lock();
		try {
			while (isFull()) {
				try {
					isFullCondition.await();
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
			isEmptyCondition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	@Override
	public T get() {
		lock.lock();
		try {
			while (isEmpty()) {
				try {
					isEmptyCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			T value = data.get(startIndex);
			startIndex = (startIndex + 1) % maxSize;
			isFullCondition.signalAll();
			return value;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean isFull() {
		lock.lock();
		try {
			if (data.size() == 0) {
				return false;
			} else {
				return ((endIndex + 1) % maxSize) == startIndex;
			}
		} finally {
			lock.unlock();
		}

	}

	@Override
	public boolean isEmpty() {
		lock.lock();
		try {
			return startIndex == endIndex;
		} finally {
			lock.unlock();
		}
	}

}
