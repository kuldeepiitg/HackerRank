import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Solution {

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		ArrayList<Integer> array = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			int number = in.nextInt();
			array.add(number);
		}

		Integer minBound = in.nextInt();
		Integer maxBound = in.nextInt();
		
		Collections.sort(array);
		
		Integer minDistance = null;
		Integer lastNumber = null;
		Integer toReturn = null;
		for (Integer number : array) {
			
			if (lastNumber == null) {
				lastNumber = number;
			} else {
				Integer mid = (number + lastNumber)/2;
				int newMinDistance = Math.min(number - mid, mid - lastNumber);
				
				if (mid >= minBound && mid <= maxBound) {
					if (minDistance == null || newMinDistance > minDistance) {
						minDistance = newMinDistance;
						 toReturn = mid;
					}
				}
				
				lastNumber = number;
			}
		}
		
		System.out.println(toReturn);
	}

}
