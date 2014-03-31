package task3;

public class Producer implements Runnable {
	
	private MyBuffer<Integer> buffer;
	private int maxWrites;
	
	public Producer(MyBuffer<Integer> buffer, int maxWrites) {
		this.buffer = buffer;
		this.maxWrites = maxWrites;
	}

	@Override
	public void run() {
		while (maxWrites > 0) {
			Integer value = (int) (Math.random() * 100);
			System.out.println(value + " produced");
			buffer.add(value);
			maxWrites--;
		}

	}
}
