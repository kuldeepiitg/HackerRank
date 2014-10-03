package com.hacker.kuldeep;

import java.util.Comparator;
import java.util.Scanner;

import org.junit.Test;

public class test {

	@Test
	public void testInsert() {
		Scanner in = new Scanner(System.in);
		Comparator<Integer> comparator = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		};
		Heap<Integer> heap = new Heap<Integer>(comparator);
		
		for (int i = 1; i <= 20; i++) {
			heap.insert(in.nextInt());
		}
		
		while (heap.hasNext()) {
			System.out.println(heap.next());
		}
		
	}

}
