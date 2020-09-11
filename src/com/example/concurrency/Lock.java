package com.example.concurrency;

final class Inventory3 {

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	private int items;
	
	public synchronized void incItems() {
		synchronized (lock1) {
			items++;
		}
	}
	
	public synchronized void decItems() {
		synchronized (lock2) {
			items--;
		}
	}
	
	public int getItemsCount() {
		return items;
	}
}

public class Lock {
	
	public static void main(String[] args) throws InterruptedException {
		// When ever something created with new will be in heap
		final Inventory3 inventory = new Inventory3();
		final Thread incrementThread = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				inventory.incItems();
			}
		});
		final Thread decrementThread = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				inventory.decItems();
			}
		});
		

		incrementThread.start();
		decrementThread.start();
		
		incrementThread.join();
		decrementThread.join();
		
		System.out.println(inventory.getItemsCount());
	}

}
