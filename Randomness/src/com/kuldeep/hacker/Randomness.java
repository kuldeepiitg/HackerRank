package com.kuldeep.hacker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * You're given a string S of N characters. It's known that the string consists of lowercase latin letters. 
 * The string is generated randomly. That means that every symbol is chosen randomly and independently
 * from others from the set {'a', 'b', ..., 'z'}. All the letters has equal probability to appear.
 * You're given Q queries on this string. Each query is of the form P C, where P is an integer 
 * between 1 and N (both inclusive) and C is a character from the set {'a', 'b', ..., 'z'}. 
 * Both P and C were chosen at random and independently from other queries.
 * When you have a query of the form P C you have to change the Pth symbol of S to C. 
 * After every change we ask you to output the number of distinct nonempty sub-strings of S.
 * 
 * 
 * https://www.hackerrank.com/challenges/randomness
 * 
 * @author kuldeep
 * 
 * @algorithm 
 * 			If all the substrings of length x are distinct then substrings of length x+1 must also be distinct.
 * 	So go on making sets of substrings of length of 1,2,3 ... x such that x is smallest number and set of substrings 
 * of length x are distinct. Calculate how many distinct substrings have been generated for set S1, S2, S3 ... Sx cumulatively.
 * And all sets with larger size substrings will have no duplicates so number of substrings in such set of length 'm' is
 * |Sm| = [n-(m-1)][{n-(m-1)}-1]/2
 * 			= [n-m+1][n-m]/2,
 * 						where n is length of original string.
 */
public class Randomness {

	/**
	 * Original String where modifications are to be made.
	 */
	private String originalString;
	
	/**
	 * Map to store counts of instances of substrings.
	 */
	private Map<String, List<StartEndPair>> map = new HashMap<String, List<StartEndPair>>();

	/**
	 * Smallest length for which all substrings of original string are distinct.
	 */
	private int smallestLengthWithAllDistinctSubstrings;
	
	public Randomness(String originalString) {
		super();
		this.originalString = originalString;
		this.map = new HashMap<String, List<StartEndPair>>();
		this.smallestLengthWithAllDistinctSubstrings = 0;
		initializeMap();
	}

	/**
	 * Fill the map.
	 */
	private void initializeMap() {
		
		for (int length = 1; length <= originalString.length(); length++) {
			int countDistinctSubstrings = initializeMap(length);
			int maxPossibleCount = originalString.length() - length + 1;
			if (countDistinctSubstrings == maxPossibleCount) {
				smallestLengthWithAllDistinctSubstrings = length;
				return;
			}
		}
		smallestLengthWithAllDistinctSubstrings = originalString.length();
	}
	
	/**
	 * @param length
	 * @return max number of possible substrings.
	 */
	private int maxSubstringsCount(int length) {
		return (originalString.length() - length + 1) * (originalString.length() - length) / 2;
	}
	
	/**
	 * Generate all substring of specified length.
	 * @param length length of substrings.
	 * @return 
	 */
	private int initializeMap(int length) {
		int totalDistinctSubstrings = 0;
		for (int i = 0; i <= originalString.length() - length; i++) {
			String subString = originalString.substring(i, i + length);
			if (map.containsKey(subString)) {
				List<StartEndPair> pairList = map.get(subString);
				pairList.add(new StartEndPair(i, i+length));
				map.put(subString, pairList);
			} else {
				List<StartEndPair> values = new ArrayList<Randomness.StartEndPair>();
				values.add(new StartEndPair(i, i+length));
				map.put(subString, values);
				totalDistinctSubstrings++;
			}
		}
		return totalDistinctSubstrings;
	}
	
	/**
	 * @return the count of distinct substrings.
	 */
	public int countDistinctSubstrings() {
		
		int totalDistinctSubstrings = map.entrySet().size();
		for (int length = smallestLengthWithAllDistinctSubstrings + 1; length <= originalString.length(); length++) {
			totalDistinctSubstrings += originalString.length() - length + 1;
		}
		return totalDistinctSubstrings;
	}
	
	/**
	 * Start and End position of a substring in original string.
	 * @author kuldeep
	 */
	private class StartEndPair {
		/**
		 * The start position.
		 */
		private int start;
		
		/**
		 * The end position.
		 */
		private int end;

		public StartEndPair(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		/**
		 * @return the start
		 */
		public int getStart() {
			return start;
		}

		/**
		 * @return the end
		 */
		public int getEnd() {
			return end;
		}
	}
}
