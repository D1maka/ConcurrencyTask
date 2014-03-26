package task11;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Runner {

	public static final int THREADS_NUMBER = 10;
	public static final int N = 2000;

	public static void main(String[] args) {
		long startTime = Calendar.getInstance().getTimeInMillis();
		Double sum = new Double(0);
		Set<Callable<Double>> sins = new HashSet<Callable<Double>>();
		ExecutorService executorService = Executors
				.newFixedThreadPool(THREADS_NUMBER);

		int segment = (2 * N + 1) % THREADS_NUMBER;
		int startValue = -N;
		int endValue = startValue + segment;

		for (int i = 0; i < THREADS_NUMBER; i++) {
			sins.add(new SinCalculator(startValue, endValue));
			startValue = endValue + 1;
			endValue = startValue + segment;
		}

		try {
			List<Future<Double>> results = executorService.invokeAll(sins);
			for (Future<Double> res : results) {
				sum += res.get();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
		
		startTime = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Sum of sins: " + sum.toString());
		System.out.println("Algo time: " + startTime);
	}

}
