package com.hacker.kuldeep;

import java.util.HashMap;

/**
 * There is an integer array d which does not contain more 
 * than two elements of the same value. How many distinct 
 * ascending triples (d[i] < d[j] < d[k], i < j < k) are present? 
 * 
 * @algorithm
 * Hold an element in array and take all the elements which are 
 * left to it and smaller. Similarly in right check for elements which 
 * are larger than this element.
 * 
 * @author kuldeep
 *
 */
public class AscendingTripletsFinder {

	/**
	 * The input array.
	 */
	private int[] array;
	
	/**
	 * Hashmap to keep 
	 */
	private HashMap<Integer, HashMap<Integer, Integer>> smallerNumbers;
	
	private HashMap<Integer, HashMap<Integer, Integer>> largerNumbers;
	
	public AscendingTripletsFinder(int[] array) {
		this.array = array;
	}
	
	/**
	 * Triplets in array whose middle element have
	 * to be array[midIndex]
	 * 
	 * @param midIndex index of middle element 
	 * of triplets to be found.
	 * @return 
	 */
	private int triplets(int midIndex) {
		
		HashMap<Integer, Integer> smaller = smallerNumbers.get(array[midIndex]);
		HashMap<Integer, Integer> larger = largerNumbers.get(array[midIndex]);
		
		int i;
		for (i = 0; i < midIndex; i++) {
			
			if (array[i] < array[midIndex] && !smaller.containsKey(array[i])) {
				smaller.put(array[i], 1);
			}
		}
		
		i++;
		for ( ; i < array.length; i++) {
			if (array[i] > array[midIndex] && !larger.containsKey(array[i])) {
				larger.put(array[i], 1);
			}
		}
		smallerNumbers.put(array[midIndex], smaller);
		largerNumbers.put(array[midIndex], larger);
		
		return smaller.size() * larger.size();
	}
	
	/**
	 * @return total number of triplets.
	 */
	public int triplets() {
		int totalTriplets = 0;
		for (int i = 0; i < array.length; i++) {
			totalTriplets += triplets(i);
		}
		return totalTriplets;
	}
}
