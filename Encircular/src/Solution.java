import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	/*
	 * Complete the function below.
	 */

	static String doesCircleExist(String commands) {

		int initialX = 0;
		int initialY = 0;
		
		int x = 0;
		int y = 0;
		String direction = "north";
		
		for (int i = 0; i < commands.length(); i++) {
			
			if (direction.equals("north")) {
				if (commands.charAt(i) == 'G') {
					y++;
				} else if (commands.charAt(i) == 'L') {
					direction = "west";
				} else if (commands.charAt(i) == 'R') {
					direction = "east";
				} else {
					System.out.println("Wrong command");
				}
			} else if (direction.equals("east")) {
				if (commands.charAt(i) == 'G') {
					x++;
				} else if (commands.charAt(i) == 'L') {
					direction = "north";
				} else if (commands.charAt(i) == 'R') {
					direction = "south";
				} else {
					System.out.println("Wrong command");
				}
			} else if (direction.equals("south")) {
				if (commands.charAt(i) == 'G') {
					y--;
				} else if (commands.charAt(i) == 'L') {
					direction = "east";
				} else if (commands.charAt(i) == 'R') {
					direction = "west";
				} else {
					System.out.println("Wrong command");
				}
			} else if (direction.equals("west")) {
				if (commands.charAt(i) == 'G') {
					x--;
				} else if (commands.charAt(i) == 'L') {
					direction = "south";
				} else if (commands.charAt(i) == 'R') {
					direction = "north";
				} else {
					System.out.println("Wrong command");
				}
			}
		}
		
		if (direction.equals("north") && (((x-initialX)*(x-initialX) + (y-initialY)*(y-initialY)) > 0)) {
			return "NO";
		} else {
			return "YES";
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		final String fileName = System.getenv("OUTPUT_PATH");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		String res;
		String _commands;
		try {
			_commands = in.nextLine();
		} catch (Exception e) {
			_commands = null;
		}

		res = doesCircleExist(_commands);
		bw.write(res);
		bw.newLine();

		bw.close();
	}
}