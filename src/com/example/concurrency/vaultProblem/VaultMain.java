package com.example.concurrency.vaultProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultMain {

	public static void main(String[] args) {
		final Random random = new Random();
		
		final Vault vault = new Vault();
		vault.setPassword(random.nextInt(MAX_PASSWORD));
		
		List<Thread> list = new ArrayList<>();
		
		list.add(new AscendingHackerThread(vault));
		list.add(new DesendingHackerThread(vault));
		list.add(new PoliceThread());
		
		list.forEach(t -> t.start());
	}
	private static final int MAX_PASSWORD = 9999;

	private static class Vault {
		private int password;

		public void setPassword(final int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(final int password) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return this.password == password;
		}
	}

	private static abstract class HackerThread extends Thread {
		protected Vault vault;

		public HackerThread(final Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY);
		}

		@Override
		public void start() {
			System.out.println("Starting thread " + this.getName());
			super.start();
		}
	}

	private static class AscendingHackerThread extends HackerThread {
		public AscendingHackerThread(final Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int i = 0; i < MAX_PASSWORD; i++) {
				if (vault.isCorrectPassword(i)) {
					System.out.println("Guessed by " + this.getName());
					System.exit(0);
				}
			}
		}
	}

	private static class DesendingHackerThread extends HackerThread {
		public DesendingHackerThread(final Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for (int i = MAX_PASSWORD; i >= 0; i--) {
				if (vault.isCorrectPassword(i)) {
					System.out.println("Guessed by " + this.getName());
					System.exit(0);
				}
			}
		}
	}

	private static class PoliceThread extends Thread {
		@Override
		public void start() {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.exit(0);
		}
	}
}
