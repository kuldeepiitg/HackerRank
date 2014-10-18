package com.hackerrank.kuldeep;


/**
 * <b>Stock Maximize</b>	
 * 
 * Your algorithms have become so good at predicting 
 * the market that you now know what the share price 
 * of Wooden Orange Toothpicks Inc. (WOT) will be for 
 * the next N days.Each day, you can either buy one 
 * share of WOT, sell any number of shares of WOT that 
 * you own, or not make any transaction at all. What 
 * is the maximum profit you can obtain with an optimum 
 * trading strategy?
 * 
 * @algorithm (Linear time)
 * Start from last day to first and keep track of peak
 * price seen. If price at 'i'th day is less than peak 
 * seen price, then I will buy at this day and sell at 
 * peak price day. If it is higher then update peak price.
 * 
 * @author kuldeep
 */
public class Broker {
	
	/**
	 * @return maximum profit that can be earned.
	 */
	public long maximize(int[] priceList) {
		
		int maxSeenPrice = Integer.MIN_VALUE;
		long totalProfit = 0;
		for (int i = priceList.length - 1; i >= 0; i--) {
			if (priceList[i] < maxSeenPrice) {
				totalProfit += maxSeenPrice - priceList[i];
			} else {
				maxSeenPrice = priceList[i];
			}
		}
		return totalProfit;
	}
	
}
