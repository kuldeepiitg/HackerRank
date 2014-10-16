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
 * @algorithm (Dynamic Programming)
 * On beginning of 'n'th day, you might have 'i' 
 * shares in hand, where i can vary from 0 to 'n-1'.
 * 	1) If you don't buy any share then neither the number 
 * of shares in hand will change, nor the profit 
 * earned.
 * 	2) If you buy a share then number of share will 
 * increase by one and profit earned will dip by price 
 * of share at that day. 
 * 	3) Sale is crucial. If you have 'i' numbers of shares 
 * in hand then you can sell 'j' shares where 'j' 
 * is at maximum 'i'.  
 * 
 * @author kuldeep
 */
public class Broker {
	
	/**
	 * List of prices in sequence of days.
	 */
	private int[] priceList;
	
	public Broker(int[] priceList) {
		super();
		this.priceList = priceList;
	}

	/**
	 * @return maximize profit with 
	 * having all number of shares in hand.
	 */
	private int[] maximize(int day, int[] lastDaysBusiness) {
		
		int[] todaysBusiness = new int[day+1];
		
		for(int i = 0; i < lastDaysBusiness.length; i++) {
			for (int j = 0; j <= i; j++) {
				int countOfSharesAfterSale = i - j;
				int todaysProfit = j * priceList[day - 1];
				if (lastDaysBusiness[i] + todaysProfit > lastDaysBusiness[countOfSharesAfterSale]) {
					todaysBusiness[countOfSharesAfterSale] = lastDaysBusiness[i] + todaysProfit;
				} else {
					todaysBusiness[countOfSharesAfterSale] = lastDaysBusiness[i-j];
				}
			}
		}
		if (day > 0) {
			todaysBusiness[day] = lastDaysBusiness[day-1] - priceList[day - 1];
		} else {
			todaysBusiness[day] = 0;
		}
		return todaysBusiness;
	}
	
	/**
	 * @return maximum profit that can be earned.
	 */
	public int maximize() {
		int[] business = new int[0];
		for (int i = 0; i < priceList.length + 1; i++) {
			business = maximize(i, business);
		}
		return business[0];
	}
}
