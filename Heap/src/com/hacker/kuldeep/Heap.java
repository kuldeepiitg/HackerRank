package com.hacker.kuldeep;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Generic heap.
 * @author kuldeep
 *
 * @param <E>
 */
public class Heap<E> {
	
	/**
	 * The heap array.
	 */
	private ArrayList<E> list;
	
	/**
	 * Comparator.
	 */
	private Comparator<E>comparator;

	public Heap(Comparator<E> comparator) {
		super();
		this.comparator = comparator;
		this.list = new ArrayList<E>();
	}
	
	/**
	 * Insert an element in heap.
	 * 
	 * @param element
	 */
	public void insert(E element) {
		if (list.isEmpty()) {
			list.add(element);
		}
		list.add(element);
		
		int lastIndex = list.size() - 1;
		
		while (lastIndex > 1 && comparator.compare(list.get(lastIndex), list.get(lastIndex/2)) > 0) {
			E upperValue = list.get(lastIndex/2);
			list.set(lastIndex/2, list.get(lastIndex));
			list.set(lastIndex, upperValue);
			lastIndex = lastIndex/2;
		}
	}
	
	/**
	 * @return next element from top of heap.
	 */
	public E next() {
		E toReturn = list.get(1);
		E lastElement = list.remove(list.size()-1);
		if (list.size() > 1) {
			list.set(1, lastElement);
		}
		
		int index = 1;
		int larger;
		if (2*index < list.size()) {
			if (2*index+1 < list.size()) {
				larger = comparator.compare(list.get(2 * index), list.get(2 * index + 1)) >= 0 ? 2 * index : 2 *index + 1;
			} else {
				larger =  2 *index;
			}
		} else {
			return toReturn;
		}
		
		while (comparator.compare(list.get(larger), list.get(index)) > 0) {
			E temp = list.get(larger);
			list.set(larger, list.get(index));
			list.set(index, temp);
			index = larger;
			if (2*index < list.size()) {
				if (2*index+1 < list.size()) {
					larger = comparator.compare(list.get(2 * index), list.get(2 * index + 1)) >= 0 ? 2 * index : 2 *index + 1;
				} else {
					larger =  2 *index;
				}
			} else {
				break;
			}
		}
		return toReturn;
	}
	
	/***
	 * @return true if heap have more elements to return.
	 */
	public boolean hasNext() {
		return list.size() > 1;
	}
	
}
