package com.kuldeep.hacker;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		
		String original = "Hello World";
		String copy = original.substring(0,3) + "z" + original.substring(4);
		System.out.println(original);
		System.out.println(copy);
		
	}

}
