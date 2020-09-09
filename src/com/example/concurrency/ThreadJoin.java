package com.example.concurrency;

public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main Thread started");
		Thread thread = new WorkerThread();
		thread.setName("WorkedThread");
		
		thread.start();
		
		System.out.println("Main Thread waiting for "+ thread.getName()+" to complete");
		
		//This this joins with main thread
		thread.join();
		
		System.out.println("Main Thread completed....");

	}

}


class WorkerThread extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.println("Worker thread "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Worker Thread completed....");
	}
}
