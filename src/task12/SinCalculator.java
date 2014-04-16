package task12;

public class SinCalculator extends Thread {

	private double result;

	private int startValue;
	private int endValue;

	public SinCalculator(int startValue, int endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
	}

	@Override
	public void run() {
		for (int i = startValue; i <= endValue; i++) {
			result += Math.sin(i);
		}
	}

	public double getResult() {
		return result;
	}
}
