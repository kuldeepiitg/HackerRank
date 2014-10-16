package com.hackerrank.kuldeep;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Junit test for {@link Broker}.
 * 
 * @author kuldeep
 */
public class BrokerTest {

	@Test
	public void testMaximize() {
		
		int[] priceList = new int[] {1, 3, 1, 2};
		Broker broker = new Broker();
		int profit = broker.maximize(priceList);
		assertEquals(3, profit);
		
		
		
	}

}
