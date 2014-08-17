package com.kuldeep.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Brute force.S
 * @author kuldeep
 *
 */
public class Solution {

	static BufferedReader in = new BufferedReader(new InputStreamReader(
	         System.in));
	static StringBuilder out = new StringBuilder();
	
	private static int almostSorted (int[] array) {
		
		int numberOfAlmostSortedIntervals = 0;
		for (int i = 0; i < array.length; i++) {
			int start = array[i];
			int maxSeen = start;
			for (int j = i; j < array.length; j++) {
				
				if (array[j] < start) {
					break;
				} else {
					if (array[j] >= maxSeen) {
						numberOfAlmostSortedIntervals++;
						maxSeen = array[j];
					} 
				}
			}
		}
		return numberOfAlmostSortedIntervals;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int numberOfInputs = in.nextInt();
		int[] array = new int[numberOfInputs];
		
		for (int i = 0; i < numberOfInputs; i++) {
			array[i] = in.nextInt();
		}
		
		int numberOfAlmostSortedIntervals = almostSorted(array);
		System.out.println(numberOfAlmostSortedIntervals);
	}

}
