package task3;

import java.util.ArrayList;

public class Runner {

	public static final int THREADS_NUMBER = 5;
	public static final int MAX_OPERATIONS = 4;
	public static final int BUFFER_SIZE = 7;
	
	public static void main(String[] args) {
		ConcurrentCircularBuffer<Integer> buffer = new ConcurrentCircularBuffer<>(BUFFER_SIZE);
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < THREADS_NUMBER; i++) {
			Thread producer = new Thread(new Producer(buffer, MAX_OPERATIONS));
			Thread consumer = new Thread(new Consumer(buffer, MAX_OPERATIONS));
			threads.add(producer);
			threads.add(consumer);
			producer.start();
			consumer.start();
		}
		
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
