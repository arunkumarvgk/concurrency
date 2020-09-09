package com.example.concurrency;

import java.math.BigInteger;

public class InterruptingLongestComputationThread {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new LongComputation(new BigInteger("20000000"), new BigInteger("200000")));
		t.start();

		int i = 10;
		while (i-- != 0) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		t.interrupt();
	}
}

class LongComputation implements Runnable {

	BigInteger base;
	BigInteger pow;

	public LongComputation(BigInteger base, BigInteger pow) {
		this.base = base;
		this.pow = pow;
	}

	private BigInteger pow(BigInteger base, BigInteger pow) {
		BigInteger result = BigInteger.ONE;

		for (BigInteger i = BigInteger.ZERO; i.compareTo(pow) != 0; i = i.add(BigInteger.ONE)) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Thread is externally interuppted");
				return BigInteger.ONE;
			}
			result = result.multiply(base);
		}

		return result;
	}

	@Override
	public void run() {
		System.out.println(pow(base, pow)+" .. ");
	}

}
