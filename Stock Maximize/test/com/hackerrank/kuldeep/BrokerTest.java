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
		Broker broker = new Broker(priceList);
		int profit = broker.maximize();
		assertEquals(3, profit);
	}

}
