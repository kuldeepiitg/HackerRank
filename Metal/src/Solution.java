
public class Solution {

	

    static int maxProfit(int cost_per_cut, int metal_price, int[] lengths) {

    	int maxLength = 0;
    	for (int length : lengths) {
    		if (length > maxLength) {
    			maxLength = length;
    		}
    	}
    	
    	int maxProfit = 0;
    	
    	for (int i = 1; i < maxLength; i++) {
    		
    		int sumOfLengths = 0;
        	int sumOfCutCounts = 0;
        	int sumOfCutWastes = 0;
    		
    		for (int length : lengths) {
    			
        		sumOfLengths += length;
        		
        		if (length % i == 0) {
        			sumOfCutCounts += (length/i - 1);
        		} else {
        			sumOfCutCounts += (length/i);
        		}
        		
        		sumOfCutWastes += (length%i);
        	}
    		
    		int profit = sumOfLengths*metal_price - sumOfCutCounts*cost_per_cut - sumOfCutWastes*metal_price;
    		
    		if (profit > maxProfit) {
    			maxProfit  = profit;
    		}
    	}
    	
    	return maxProfit;
    }


	
}
