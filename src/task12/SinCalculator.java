package task12;

public class SinCalculator extends Thread {

	public static volatile Thread currentThread;
	public static volatile boolean controlTaken = false;
	public static volatile double sinValue;
	public static volatile int executions;

	private int startValue;
	private int endValue;

	public SinCalculator(int startValue, int endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
	}

	@Override
	public void run() {
		Double result = new Double(0);
		for (int i = startValue; i < endValue; i++) {
			result += Math.sin(i);
		}
		while (controlTaken) {
			try {
				currentThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		controlTaken = true;
		currentThread = this;
		sinValue += result;
		executions++;

		controlTaken = false;
	}

}
