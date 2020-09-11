package com.example.concurrency.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockDemo {

	public static void main(String[] args) throws InterruptedException {
		final Helper1 helper = new Helper1();
		Thread t1 = new Thread(()-> {
			int i = 0;
			while (i++ != 10) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				helper.access();
			}
		});
		
		Thread t2 = new Thread(()-> {
			int i = 0;
			while (i++ != 5) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				helper.access();
			}
		});
		
		t1.start();
		t2.start();
		// Main thread will only wait for t2 to complete
		t2.join();
		System.out.println("Finished..........");
	}

}

final class Helper1 {
	private final Lock lockObject = new ReentrantLock();
	
	public void access() {
		lockObject.lock();
		try {
			System.out.println("Thread :: "+Thread.currentThread().getName());
		} finally {
			// Has to be unlocked otherwise t2 in main will not get access
			
			// Always unlock in finally, because if there is exception in the logic between
			// lock and unlock, unlock  will not be executed
			lockObject.unlock();
		}
	}
}
