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
	private HashMap<String, HashMap<StartEndPair, Integer>> map;

	/**
	 * Smallest length for which all substrings of original string are distinct.
	 */
	private int smallestLengthWithAllDistinctSubstrings;
	
	/**
	 * Count of distinct substrings.
	 */
	private int distinctSubstringsCount;
	
	public Randomness(String originalString) {
		super();
		this.originalString = originalString;
		this.map = new HashMap<String, HashMap<StartEndPair, Integer>>();
		this.smallestLengthWithAllDistinctSubstrings = 0;
		initializeMap();
		this.distinctSubstringsCount = countDistinctSubstrings();
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
	 * Generate all substring of specified length.
	 * @param length length of substrings.
	 * @return 
	 */
	private int initializeMap(int length) {
		int totalDistinctSubstrings = 0;
		for (int i = 0; i <= originalString.length() - length; i++) {
			String subString = originalString.substring(i, i + length);
			if (map.containsKey(subString)) {
				HashMap<StartEndPair, Integer> pairMap = (HashMap<StartEndPair, Integer>) map.get(subString);
				pairMap.put(new StartEndPair(i, i+length), 1);
				map.put(subString, pairMap);
			} else {
				HashMap<StartEndPair, Integer> values = new HashMap<Randomness.StartEndPair, Integer>();
				values.put(new StartEndPair(i, i+length), 1);
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
	 * @param original original string
	 * @param character new character to put
	 * @param index position where substitution is to be made
	 * @return newly formed string
	 */
	private String substitute(String original, char character, int index) {
		return original.substring(0, index) + character + original.substring(index + 1);
	}
	
	/**
	 * TODO: 
	 * @param position replace character at specified position be new character.
	 * @param newCharacter new character to be substituted.
	 * @return the count of distinct substrings.
	 */
	public int replaceCharacter (int position, char newCharacter) {
		
		String substitutedString = substitute(originalString, newCharacter, position);
		
		for (int length = 1; length <= originalString.length(); length++) {
			
			int maximumPossibleCount = length;
			int totalDistinctSubstringsCount = 0;
			for (int start = position - length + 1; start <= position; start++) {
				int end = start+length;
				if (start >= 0 && end <= originalString.length()) {
					String originalSubString = originalString.substring(start, end);
					String newSubString = substitutedString.substring(start, end);
					StartEndPair pair = new StartEndPair(start, end);
					HashMap<StartEndPair, Integer> values = map.get(originalSubString);
					if (values.containsKey(pair)) {
						values.remove(pair);
						if (values.size() == 0) {
							map.remove(originalSubString);
							distinctSubstringsCount--;
						}
					}
					
					if (map.containsKey(newSubString)) {
						values = map.get(newSubString);
						values.put(pair, 1);
					} else {
						values = new HashMap<Randomness.StartEndPair, Integer>();
						values.put(new StartEndPair(start, end), 1);
						map.put(newSubString, values);
						distinctSubstringsCount++;
						totalDistinctSubstringsCount++;
					}
				} else {
					maximumPossibleCount--;
				}
			}
			if (totalDistinctSubstringsCount == maximumPossibleCount && totalDistinctSubstringsCount > 1) {
				smallestLengthWithAllDistinctSubstrings = length;
				break;
			}
		}
		originalString = substitutedString;
		return distinctSubstringsCount;
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

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + end;
			result = prime * result + start;
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StartEndPair other = (StartEndPair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (end != other.end)
				return false;
			if (start != other.start)
				return false;
			return true;
		}

		private Randomness getOuterType() {
			return Randomness.this;
		}
	}
}
