package com.hacker.kuldeep;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import com.hacker.kuldeep.Heap;

public class HeapTest {

	@Test
	public void testInsert() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap heap = new Heap<Integer>(comparator);
		
		for (int i = 0; i < 10; i++) {
			heap.insert(i);
		}
		
		while (heap.hasNext()) {
			System.out.println(heap.next());
		}
		
	}

}
