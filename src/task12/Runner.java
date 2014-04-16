package task12;

import java.util.ArrayList;
import java.util.Calendar;;


public class Runner {
	
	public static final int THREADS_NUMBER = 3;
	public static final int N = 200;

	public static void main(String[] args) {
		double sum = 0;
		long startTime = Calendar.getInstance().getTimeInMillis();
		int segment = (2 * N + 1) / THREADS_NUMBER;
		int startValue = -N;
		int additional = (2 * N) % THREADS_NUMBER;
		
		int endValue = startValue + segment;
		ArrayList<SinCalculator> threads = new ArrayList<SinCalculator>();

		for (int i = 0; i < THREADS_NUMBER; i++) {
			threads.add(new task12.SinCalculator(startValue, endValue));
			startValue = endValue;
			if (additional > 0) {
				endValue = startValue + segment + 1;
				additional--;
			} else {
				endValue = startValue + segment;
			}
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < threads.size(); i++) {
			sum += threads.get(i).getResult();
		}
		startTime = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Sin value: " + sum);
		System.out.println("Algo time: " + startTime);
	}

}
