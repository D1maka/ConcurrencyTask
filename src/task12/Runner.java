package task12;

import java.util.ArrayList;
import java.util.Calendar;;


public class Runner {
	
	public static final int THREADS_NUMBER = 5;
	public static final int N = 200;

	public static void main(String[] args) {
		long startTime = Calendar.getInstance().getTimeInMillis();
		int segment = (2 * N + 1) / THREADS_NUMBER;
		int startValue = -N;
		int endValue = startValue + segment;
		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < THREADS_NUMBER; i++) {
			threads.add(new task12.SinCalculator(startValue, endValue - 1));
			startValue = endValue;
			endValue = startValue + segment;
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startTime = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Sin value: " + task12.SinCalculator.sinValue);
		System.out.println("Executions: " + task12.SinCalculator.executions);
		System.out.println("Algo time: " + startTime);
	}

}
