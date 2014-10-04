package com.hacker.kuldeep;


/**
 * Balance the given parenthesis in minimum number of swaps.
 * The input have equal number of left and right parenthesis.
 * 
 * @algorithm 
 * At any point of time, if you see more number of ')' than number 
 * of '(' then keep counting them and as soon as you get to look
 * at '(' print ')' as well.
 * 
 * e.g. 
 * 	original string : )((()))(
 * ) => ""
 * ( => "()"
 * ( => "()("
 * ( => "()(("
 * ) => "()(()"
 * ) => "()(())"
 * ) => "()(())"   because no '(' have been seen to pair with ')' so keep count of it.
 * ) => "()(())()" because number of ')' seen were more than '('
 * 
 * 
 * @author kuldeep
 */
public class BalanceInMinSwaps {

	/**
	 * Generate a balanced parenthesis 
	 * 
	 * @param unbalanced string containing unbalanced parentheses.
	 * @return balanced parentheses string.
	 */
	public static String balance(String unbalanced) {
		
		StringBuilder balanced = new StringBuilder();
		int surplusLeftParenthesis = 0;
		for (char character : unbalanced.toCharArray()) {
			if (character == '(') {
				balanced.append('(');
				if (surplusLeftParenthesis < 0) {
					balanced.append(')');
				}
				surplusLeftParenthesis++;
			} else if (character == ')') {
				if (surplusLeftParenthesis > 0) {
					balanced.append(')');
				}
				surplusLeftParenthesis--;
			}
		}
		if (surplusLeftParenthesis != 0) {
			throw new RuntimeException("Left and right parentheses are not equal in number");
		}
		return balanced.toString();
	}
}
