package com.yadav.kuldeep;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yadav.kuldeep.Knapsack.Article;

/**
 * Junit test for {@link Knapsack}.
 * 
 * @author kuldeep
 */
public class KnapsackTest {

	@Test
	public void testMaximumValue() {
		
		Article[] objects = new Article[5];
		objects[0].set(12, 4);
		objects[1].set(1, 2);
		objects[2].set(4, 10);
		objects[3].set(1, 1);
		objects[4].set(2, 2);
		
		Knapsack knapsack = new Knapsack(objects, 15);
		int value = knapsack.maximumValue();
		System.out.println(value);
	}

}
