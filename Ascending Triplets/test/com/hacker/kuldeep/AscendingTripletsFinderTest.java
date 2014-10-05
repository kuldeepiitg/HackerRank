package com.hacker.kuldeep;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

/**
 * Junit test for {@link AscendingTripletsFinder}.
 * @author kuldeep
 *
 */
public class AscendingTripletsFinderTest {

	@Test
	public void testTriplets() {
		
		Scanner in = new Scanner(System.in);
		int numberOfInputs = in.nextInt();
		
		int[] array = new int[numberOfInputs];
		
		for (int i = 0; i < numberOfInputs; i++) {
			array[i] = in.nextInt();
		}
		
		AscendingTripletsFinder tripletsFinder = new AscendingTripletsFinder(array);
		int count = tripletsFinder.triplets();
		System.out.println(count);
	}

}
