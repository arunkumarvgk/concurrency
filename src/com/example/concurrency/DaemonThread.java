package com.example.concurrency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main thread shuts down and application closes but daemon continues to
 * execute.
 *
 */
public class DaemonThread {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new PrintNumbers());
		t.setDaemon(true);
		t.start();

		int i = 3;
		while (i-- != 0) {
			System.out.println("Main Thread " + i);
			Thread.sleep(1000);
		}
		System.out.println("Main Thread exiting... ");
	}
}

class PrintNumbers implements Runnable {

	@Override
	public void run() {
		try {
			write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Worker Thread exiting... ");
	}

	public void write() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("daemon.log")));

		for (int i = 0; i < 20; i++) {
			try {
				writer.write("Worker Thread " + i);
				System.out.println("Worker Thread " + i);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
