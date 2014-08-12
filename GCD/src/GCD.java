import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GCD {

	private static int gcd(int first, int second) {
		if (first > second) {
			int temp = second;
			second = first;
			first = temp;
		}

		if (first == 0) {
			return second;
		}

		return gcd(first, second % first);
	}

	private static boolean checkIfSetHaveGCDOne(List<Integer> list) {

		if (list.size() == 1) {
			if (list.get(0) == 1) {
				return true;
			} else {
				return false;
			}
		}
		Iterator<Integer> iter = list.iterator();

		int netGCD = gcd(iter.next(), iter.next());
		while (iter.hasNext()) {
			if (netGCD == 1) {
				return true;
			}
			netGCD = gcd(iter.next(), netGCD);
		}

		if (netGCD == 1) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int totalTests = in.nextInt();
		for (int i = 0; i < totalTests; i++) {
			List<Integer> list = new ArrayList<Integer>();

			int numberOfItems = in.nextInt();
			for (int j = 0; j < numberOfItems; j++) {
				list.add(in.nextInt());
			}
			
			if (checkIfSetHaveGCDOne(list)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}
}
