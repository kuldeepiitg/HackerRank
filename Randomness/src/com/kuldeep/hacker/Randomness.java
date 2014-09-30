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
	private HashMap<String, Integer> map;

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
		this.map = new HashMap<String, Integer>();
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
				Integer count = map.get(subString);
				map.put(subString, count+1);
			} else {
				map.put(subString, 1);
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
	 * @param position position in string where character is to be removed.
	 * @return count of substrings that have been removed from map.
	 */
	private int removeCharacter(int position) {
		
		int totalRemoved = 0;
		for (int length = 1; length <= smallestLengthWithAllDistinctSubstrings; length++) {
			for (int start = (position - length + 1 > 0 ? position - length + 1 :  0); start <= position; start++) {
				int end;
				
				if (start + length <= originalString.length()) {
					end = start + length;
				} else {
					break;
				}
				
				String substring = originalString.substring(start, end);
				Integer count = map.get(substring);
				if (count == 1) {
					map.remove(substring);
					totalRemoved++;
				} else {
					map.put(substring, count - 1);
				}
			}
		}
		return totalRemoved;
	}
	
	/**
	 * 
	 * @param position
	 * @param character
	 * @return
	 */
	private int putCharacter(int position, char character) {
		originalString = substitute(originalString, character, position);
		int totalAdded = 0;
		boolean allStringsDistinct = true;
		for (int length = 1; length <= smallestLengthWithAllDistinctSubstrings; length++) {
			allStringsDistinct = true;
			for (int start = position - length + 1 > 0 ? position - length + 1 : 0; start <= position; start++) {
				
				int end;
				if (start + length <= originalString.length()) {
					end = start + length;
				} else {
					break;
				}
				
				String substring = originalString.substring(start, end);
				if (map.containsKey(substring)) {
					int count = map.get(substring);
					map.put(substring, count+1);
					allStringsDistinct = false;
				} else {
					map.put(substring, 1);
					totalAdded++;
				}
			}
		}
		if (!allStringsDistinct) {
			addOneMoreLayerInMap();
		}
		return totalAdded;
	}
	
	/**
	 * Add one more layer in map.
	 * @return total elements added
	 */
	private int addOneMoreLayerInMap() {
		smallestLengthWithAllDistinctSubstrings++;
		for (int start = 0; start <= originalString.length() - smallestLengthWithAllDistinctSubstrings; start++) {
			String substring = originalString.substring(start, start + smallestLengthWithAllDistinctSubstrings);
			map.put(substring, 1);
		}
		return originalString.length() - smallestLengthWithAllDistinctSubstrings + 1;
	}
	
	/**
	 * Replace a character with new.
	 * @param position
	 * @param character
	 * @return
	 */
	public int replaceCharacter(int position, char character) {
		distinctSubstringsCount -= removeCharacter(position);
		distinctSubstringsCount += putCharacter(position, character);
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
