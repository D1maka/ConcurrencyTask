package task4;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class BigIntegerGenerator {

	private AtomicReference<BigInteger> currentReference;
	
	public BigIntegerGenerator() {
		currentReference = new AtomicReference<BigInteger>(BigInteger.ONE);
	}
	
	public BigInteger next() {
		for(;;) {
			BigInteger currentBigInteger = currentReference.get();
			BigInteger newBigInteger = currentBigInteger.add(currentBigInteger);
			if (currentReference.compareAndSet(currentBigInteger, newBigInteger)) {
				return currentReference.get();
			}
		}
	}

}
