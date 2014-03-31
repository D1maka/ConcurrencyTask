package task4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Runner {
	public static final int THREADS_AMMOUNT = 200;

	public static void main(String[] args) {
		List<BigInteger> integerList = Collections
				.synchronizedList(new ArrayList<BigInteger>());
		BigIntegerGenerator bigIntegerGenerator = new BigIntegerGenerator();

		for (int i = 0; i < THREADS_AMMOUNT; i++) {
			Thread thread = new Thread(new IntGetter(bigIntegerGenerator,
					integerList));
			thread.start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collections.sort(integerList);
		for (BigInteger bigInteger : integerList) {
			System.out.println(bigInteger);
		}
	}

}

class IntGetter implements Runnable {
	private BigIntegerGenerator generator;
	private List<BigInteger> list;

	public IntGetter(BigIntegerGenerator gen, List<BigInteger> list) {
		generator = gen;
		this.list = list;
	}

	@Override
	public void run() {
		BigInteger newInteger = generator.next();
		list.add(newInteger);
	}

}
