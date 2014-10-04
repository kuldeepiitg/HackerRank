package com.hacker.kuldeep;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Junit test for {@link BalanceInMinSwaps}.
 * 
 * @author kuldeep
 */
public class BalanceInMinSwapsTest {

	@Test
	public void testBalance() {
		String balanced = BalanceInMinSwaps.balance(")(((())))(");
		assertEquals("()((()))()", balanced);
	}

}
