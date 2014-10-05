package com.hacker.kuldeep;

import java.util.Arrays;
import java.util.HashMap;

/**
 * There is an integer array d which does not contain more 
 * than two elements of the same value. How many distinct 
 * ascending triples (d[i] < d[j] < d[k], i < j < k) are present? 
 * 
 * @algorithm
 * Hold an element in array and take all the elements which are 
 * left to it and smaller. Similarly in right check for elements which 
 * are larger than this element.
 * 
 * @author kuldeep
 *
 */
public class AscendingTripletsFinder {

	/**
	 * The input array.
	 */
	private int[] array;
	
	/**
	 * Hash map to contain triplets and counts.
	 */
	private HashMap<Triplet, Integer> map;
	
	public AscendingTripletsFinder(int[] array) {
		this.array = array;
		map = new HashMap<AscendingTripletsFinder.Triplet, Integer>();
	}
	
	/**
	 * Triplets in array whose middle element have
	 * to be array[midIndex]
	 * 
	 * @param midIndex index of middle element 
	 * of triplets to be found.
	 */
	private void putTriplets(int midIndex) {
		
		
	}
	
	/**
	 * The triplet
	 * 
	 * @author kuldeep
	 */
	private class Triplet {
		
		/**
		 * Triplets elements.
		 */
		int[] elements;

		public Triplet(int first, int second, int third) {
			super();
			this.elements = new int[]{first,second,third};
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + Arrays.hashCode(elements);
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Triplet other = (Triplet) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (!Arrays.equals(elements, other.elements))
				return false;
			return true;
		}

		private AscendingTripletsFinder getOuterType() {
			return AscendingTripletsFinder.this;
		}
	}
}
