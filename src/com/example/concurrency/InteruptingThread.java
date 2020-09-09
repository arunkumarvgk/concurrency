package com.example.concurrency;

public class InteruptingThread {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new BlockingThread());
		thread.setName("blocking thread");
		thread.start();

		Thread.sleep(10000);
	}

}

class BlockingThread implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+" >>>>");
		}
		
	}
	
}
