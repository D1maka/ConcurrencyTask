package task3;

public class Consumer implements Runnable {
	
	private MyBuffer<Integer> buffer;
	private int maxReads;
	
	public Consumer(MyBuffer<Integer> buffer, int maxReads) {
		this.buffer = buffer;
		this.maxReads = maxReads;
	}

	@Override
	public void run() {
		while (maxReads > 0) {
			Integer value = buffer.get();
			System.out.println(value + " consumed");
			maxReads--;
		}

	}

}
