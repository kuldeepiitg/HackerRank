import java.util.Scanner;

public class Solution {

	/**
	 * @return maximum profit that can be earned.
	 */
	private static long maximize(int[] priceList) {
		
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
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int totalTestCases = scanner.nextInt();
		
		for (int i = 0; i < totalTestCases; i++) {
			int totalElements = scanner.nextInt();
			int[] priceList = new int[totalElements];
			for (int j = 0; j < totalElements; j++) {
				priceList[j] = scanner.nextInt();
			}
			long profit =  maximize(priceList);
			System.out.println(profit);
		}
	}
}
