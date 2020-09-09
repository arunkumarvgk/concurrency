package com.example.concurrency.performance;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HandlingHttpRequests {

	private static final Path BOOK = Paths.get("resources/JavaScript book.txt");

	public static void main(String[] args) throws IOException {
		final String text = new String(Files.readAllBytes(BOOK));
		startServer(text);
	}

	private static void startServer(String text) throws IOException {
		final HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0 /* No of req to be queued */);
		server.createContext("/search", new WordCountHandler(text));
		final Executor executor = Executors.newFixedThreadPool(10);
		server.setExecutor(executor);
		server.start();
	}

}

class WordCountHandler implements HttpHandler {

	private String text;

	public WordCountHandler(final String text) {
		this.text = text;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		final String query = httpExchange.getRequestURI().getQuery();
		
		final String input[] = query.split("=");
		if ( ! input[0].equals("word")) {
			httpExchange.sendResponseHeaders(400, 0);
			return;
		}
		
		final long count = counWord(input[1]);
		final byte[] response = Long.toString(count).getBytes();
		httpExchange.sendResponseHeaders(200, response.length);
		final OutputStream os = httpExchange.getResponseBody();
		os.write(response);
		os.close();
	}

	private long counWord(final String word) {
		long count = 0;
		int index = 0;
		
		while (index >= 0) {
			index = text.indexOf(word, index);
			if (index >= 0) {
				count++;
				index++;
			}
		}
		return count;
	}

}
