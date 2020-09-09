package com.example.concurrency;

final class Inventory {

	private int items;
	
	public void incItems() {
		items++;
	}
	
	public void decItems() {
		items--;
	}
	
	public int getItemsCount() {
		return items;
	}
}

public class DataShatingBetweenThreads {
	
	public static void main(String[] args) throws InterruptedException {
		// When ever something created with new willl be in heap
		final Inventory inventory = new Inventory();
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
		incrementThread.join();
		decrementThread.start();
		
		
		decrementThread.join();
		
		System.out.println(inventory.getItemsCount());
	}

}
