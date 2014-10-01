package com.kuldeep.hacker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Junit test for {@link BinarySearchTree}.
 * 
 * @author kuldeep
 */
public class BinarySearchTreeTest {

	@Test
	public void test() {
		BinarySearchTree tree = new BinarySearchTree();
		int[] input = new int[]{4, 7, 3, 1, 8, 2, 6, 5};
		int[] output = new int[]{0,	1,4,10,20,35,52,76};
		for (int i = 0; i < input.length; i++) {
			long actual = tree.insert(input[i]);
			assertEquals(output[i], actual);
		}
	}
}
