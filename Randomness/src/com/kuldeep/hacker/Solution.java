package com.kuldeep.hacker;

import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	
	/**
	 * Original String where modifications are to be made.
	 */
	private static String originalString;
	
	/**
	 * Map to store counts of instances of substrings.
	 */
	private static HashMap<String, Integer> map;

	/**
	 * Smallest length for which all substrings of original string are distinct.
	 */
	private static int smallestLengthWithAllDistinctSubstrings;
	
	/**
	 * Count of distinct substrings.
	 */
	private static long distinctSubstringsCount;
	
	/**
	 * Fill the map.
	 */
	private static void initializeMap() {
		
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
	private static int initializeMap(int length) {
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
	public static long countDistinctSubstrings() {
		
		long totalDistinctSubstrings = map.entrySet().size();
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
	private static String substitute(String original, char character, int index) {
		return original.substring(0, index) + character + original.substring(index + 1);
	}
	
	/**
	 * @param position position in string where character is to be removed.
	 * @return count of substrings that have been removed from map.
	 */
	private static int removeCharacter(int position) {
		
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
	private static int putCharacter(int position, char character) {
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
	private static int addOneMoreLayerInMap() {
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
	public static long replaceCharacter(int position, char character) {
		distinctSubstringsCount -= removeCharacter(position);
		distinctSubstringsCount += putCharacter(position, character);
		return distinctSubstringsCount;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int lengthOfInputString = in.nextInt();
		int numberOfTests = in.nextInt();
		
		originalString = in.next();
		if (originalString.length() != lengthOfInputString) {
			throw new RuntimeException("Length of input string is not correct");
		}
		
		map = new HashMap<String,Integer> ();
		smallestLengthWithAllDistinctSubstrings = 0;
		initializeMap();
		distinctSubstringsCount = countDistinctSubstrings();
		
		for (int i = 0; i < numberOfTests; i++) {
			int position = in.nextInt();
			char newCharacter = in.next().charAt(0);
			long newCount = replaceCharacter(position - 1, newCharacter);
			System.out.println(newCount);
		}
		
	}

}
