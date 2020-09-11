package com.example.concurrency;

public class DeadLock {

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
			// Lock track 2 for train to move
			synchronized (track2) {
				System.out.println("Locked track 2 by thread " + Thread.currentThread().getName());

				// Lock track 1 as train passing through track2
				synchronized (track1) {
					System.out.println("Locked track 1 by thread " + Thread.currentThread().getName());
					// Passing takes 1 second
					Thread.sleep(1000);
				}
			}
		}
	}

	public static class TrainA extends Thread {

		private final Intersection intersection;
		
		public TrainA(final Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			try {
				intersection.takeFirstTrack();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class TrainB extends Thread {

		private final Intersection intersection;
		
		public TrainB(final Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			try {
				intersection.takeSecondTrack();;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final Intersection intersection = new Intersection();
		final Thread thread1 =  new Thread(new TrainA(intersection));
		final Thread thread2 =  new Thread(new TrainB(intersection));
		
		
		thread1.setName("Thread A");
		thread2.setName("Thread B");
		
		
		thread1.start();
		//Thread.sleep(2000);
		thread2.start();

		
	}

}
