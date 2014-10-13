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
		
		int[] array = new int[]{1, 1, 2, 2, 3, 4};
		
		AscendingTripletsFinder tripletsFinder = new AscendingTripletsFinder(array);
		int count = tripletsFinder.triplets();
		assertEquals(4, count);
	}

}
