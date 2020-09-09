package com.example.concurrency;

public class ThreadCreation {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			System.out.println("Inside thread " + Thread.currentThread().getName());
			System.out.println("Priority "+Thread.currentThread().getPriority());
		});
		
		System.out.println("Inside thread 1 " + Thread.currentThread().getName());
		
		thread.setName("My Thread");
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		
		
		System.out.println("Inside thread 2 " + Thread.currentThread().getName());

		Thread.sleep(1000);
	}
}
