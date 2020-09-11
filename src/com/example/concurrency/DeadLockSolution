package com.example.concurrency;

/**
 * Solution is to lock objects in the same order, this way there cannot be a dead lock
 *
 */
public class DeadLockSolution {

	public static class Intersection {
		private final Object track1 = new Object();
		private final Object track2 = new Object();

		public void takeFirstTrack() throws InterruptedException {
			// Lock track 1 for train to move
			synchronized (track1) {
				System.out.println("Locked track 1 by thread " + Thread.currentThread().getName());

				// Lock track 2 as train passing through track2
				synchronized (track2) {
					System.out.println("Locked track 2 by thread " + Thread.currentThread().getName());
					// Passing takes 1 second
					Thread.sleep(1000);
				}
			}
		}

		public void takeSecondTrack() throws InterruptedException {
			// Lock track 1 for train to move
			synchronized (track1) {
				System.out.println("Locked track 1 by thread " + Thread.currentThread().getName());

				// Lock track 2 as train passing through track2
				synchronized (track2) {
					System.out.println("Locked track 2 by thread " + Thread.currentThread().getName());
					// Passing takes 1 second
					Thread.sleep(1000);
				}
			}
		}
	}

	
	public static void main(String[] args) throws InterruptedException {
		final Intersection intersection = new Intersection();
		final Thread thread1 =  new Thread(()-> {
			try {
				intersection.takeFirstTrack();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		final Thread thread2 =  new Thread(()-> {
			try {
				intersection.takeSecondTrack();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		thread1.setName("Thread A");
		thread2.setName("Thread B");
		
		
		thread1.start();
		thread2.start();

		
	}

}
