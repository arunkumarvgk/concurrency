package com.example.concurrency;

import java.io.IOException;
import java.util.regex.Pattern;

public class Test {

	public static String convertAntToRegexPattern(final String antPattern) {

		StringBuilder str = new StringBuilder(antPattern);

		int index = 0;
		while (index < str.length()) {
			if (str.charAt(index) == '.') {
				str.replace(index, index + 1, "\\.");
				index = index + 3;
			} else if (str.charAt(index) == '?') {
				str.replace(index, index + 1, ".");
				index = index + 3;
			} else if (str.charAt(index) == '*' && str.charAt(index + 1) == '*') {
				str.replace(index, index + 2, ".*");
				index = index + 2;
			} else if (str.charAt(index) == '*') {
				str.replace(index, index + 1, ".[^/]*");
				index = index + 6;
			} else {
				index++;
			}
		}
		return str.toString();
	}

	private static void m () {
		 StringBuilder str = new StringBuilder("Java Util Package");
	      System.out.println("string = " + str);
	    
	      // replace substring from index 5 to index 9
	      str.replace(0, 1, "Lang");
	   
	      // prints the StringBuilder after replacing
	      System.out.println("After replacing: " + str);
	}
	
	
	public static void main(String[] args) throws IOException {
		//m();
		String pattern = "/foo/bar/*.cbl";
		System.out.println(convertAntToRegexPattern(pattern));
		System.out.println(Pattern.matches(convertAntToRegexPattern(pattern), "/foo/bar/A.cbl"));
		System.out.println(Pattern.matches(convertAntToRegexPattern(pattern), "/foo/bar/baz/A.cbl"));
		
		
		System.out.println("=====================================================================");
		String pattern2 = "/foo/bar/**.cbl";
		System.out.println(convertAntToRegexPattern(pattern2));
		System.out.println(Pattern.matches(convertAntToRegexPattern(pattern2), "/foo/bar/A.cbl"));
		System.out.println(Pattern.matches(convertAntToRegexPattern(pattern2), "/foo/bar/baz/A.cbl"));
	}

}
