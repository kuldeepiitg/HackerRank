package com.hacker.kuldeep;

import java.util.HashMap;
import java.util.Scanner;
public class Solution{
	
	/**
	 * 
	 * @param array
	 * @return
	 */
	private static int triplets (int [] array) {
		HashMap<Integer, HashMap<Integer, Integer>> smallerNumbers = 
				new HashMap<Integer, HashMap<Integer,Integer>>();
		HashMap<Integer, HashMap<Integer, Integer>> largerNumbers = 
				new HashMap<Integer, HashMap<Integer,Integer>>();
		

		int tripletsCount = 0;
		for (int midIndex = 0; midIndex < array.length; midIndex++) {
			HashMap<Integer, Integer> smaller = smallerNumbers.get(array[midIndex]);
			HashMap<Integer, Integer> larger = largerNumbers.get(array[midIndex]);
			if (smaller == null) {
				tripletsCount += firstLookUpForTriplets(array, midIndex, 
						smallerNumbers, largerNumbers);
			} else {
				tripletsCount += secondLookUpForTriplets(array, midIndex, smaller, larger);
			}
		}
		
		return tripletsCount;
	}
	
	/**
	 * 
	 * @param midIndex index of middle element 
	 * @return number of triplets for this element
	 */
	private static int firstLookUpForTriplets(int[] array, int midIndex,
			HashMap<Integer, HashMap<Integer, Integer>> smallerNumbers,
			HashMap<Integer, HashMap<Integer, Integer>> largerNumbers
			) {
		HashMap<Integer, Integer> smaller = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> larger = new HashMap<Integer, Integer>();
		
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
	 * 
	 * @param midIndex index of middle elements of a triplet.
	 * @param smaller hash containing smaller elements
	 * @param larger hash containing larger elements
	 * @return
	 */
	private static int secondLookUpForTriplets (int[] array, int midIndex, 
			HashMap<Integer, Integer> smaller,
			HashMap<Integer, Integer> larger) {
		
		int newlySeenSmaller = 0;
		int allLargeElements = 0;
		
		int i;
		for (i = 0; i < midIndex; i++) {
			if (array[i] < array[midIndex] && !smaller.containsKey(array[i])) {
				newlySeenSmaller++;
			}
		}
		
		i++;
		for ( ; i < array.length; i++) {
			if (array[i] > array[midIndex]) {
				allLargeElements++;
			}
		}
		return newlySeenSmaller * allLargeElements;
	}
	
    public static void main(String args[])
    {
        int[] arr;
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        arr=new int[n];
      
        for(int i=0;i<n;i++)
        {
            arr[i]=scn.nextInt();
        }
        int triplets= triplets(arr);
        // Write the code to compute the number of triplets as required
        System.out.println(triplets);
    }

}
