package com.example.concurrency;

final class Inventory2 {

	private int items;
	
	public synchronized void incItems() {
		items++;
	}
	
	public synchronized void decItems() {
		items--;
	}
	
	public int getItemsCount() {
		return items;
	}
}

public class Monitor {
	
	public static void main(String[] args) throws InterruptedException {
		// When ever something created with new will be in heap
		final Inventory2 inventory = new Inventory2();
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
