package com.yadav.kuldeep;

/**
 * Knapsack problem: Given 'n' objects each having value 'Vi' and weight 'Wi'.
 * Select objects such that you have max value in hand and total weight is less
 * than 'W'.
 * 
 * @author kuldeep
 */
public class Knapsack {

	/**
	 * Collection of objects.
	 */
	private Article[] objects;
	
	/**
	 * Maximum limit of weights that a chosen collection of object is allowed to have.
	 */
	private int weightLimit;
	
	/**
	 * Memorization table.
	 */
	private int[][] table;
	
	public Knapsack(Article[] objects, int weightLimit) {
		super();
		this.objects = objects;
		this.weightLimit = weightLimit;
		this.table = new int[objects.length][weightLimit+1];
	}

	/**
	 * @param maximumWeight maximum weight allowed
	 * @param lastElementIndex index of last object
	 * @return maximum value that can be achieved by selecting 
	 * 	elements keeping total weight less than maximum weight. 
	 */
	private int maximumValue(int maximumWeight, int lastElementIndex) {
		
		if(table[maximumWeight][lastElementIndex] < 0) {

			int value;
			if (maximumWeight <= 0) {
				value = 0;
			} else if (lastElementIndex == 0) {
				if (objects[0].weight <= maximumWeight) {
					value = objects[0].value;
				} else {
					value = 0;
				}
			} else {
				value = Math.max(maximumValue(maximumWeight - objects[lastElementIndex].weight, lastElementIndex -1)
						+ objects[lastElementIndex].value, maximumValue(maximumWeight, lastElementIndex -1));
			}
			table[maximumWeight][lastElementIndex] = value;
		}
		return table[maximumWeight][lastElementIndex];
	}
	
	/**
	 * @return maximum value possible to achieve keeping weight under limit.
	 */
	public int maximumValue() {
		return maximumValue(weightLimit, objects.length);
	}
	
	/**
	 * Object representation.
	 * 
	 * @author kuldeep
	 */
	public class Article {
		
		/**
		 * Value of object.
		 */
		private int value;
		
		/**
		 * Weight of object.
		 */
		private int weight;

		public void setValue(int value) {
			this.value = value;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
		
		/**
		 * @param weight
		 * @param value
		 */
		public void set(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
}
