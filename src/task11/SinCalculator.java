package task11;

import java.util.concurrent.Callable;

public class SinCalculator implements Callable<Double> {

	private int startValue;
	private int endValue;

	public SinCalculator(int startValue, int endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
	}

	@Override
	public Double call() throws Exception {
		Double result = new Double(0);
		for (int i = startValue; i <= endValue; i++) {
			result += Math.sin(i);
		}
		return result;
	}

}
