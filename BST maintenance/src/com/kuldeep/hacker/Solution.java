package com.kuldeep.hacker;

import java.util.Scanner;

/**
 * Consider a binary search tree T which is initially empty. 
 * Also, consider the first N positive integers {1, 2, 3, 4, 5, ....., N} 
 * and its permutation P {a1, a2, ..., aN}. If we start adding these 
 * numbers to the binary search tree T, starting from a1, continuing 
 * with a2, ... (and so on) ..., ending with aN. After every addition we 
 * ask you to output the sum of distances between every pair of T's nodes.
 * 
 * https://www.hackerrank.com/challenges/bst-maintenance
 * 
 * @algorithm
 * Have sum of cumulative distance of left children and right children, 
 * count of left and right children with every node. Maintain total cumulative 
 * distance for whole tree.
 * Let us suppose a node arrive which is to be inserted to the right of root node, 
 * so when the node is kept at root then distance to all left children to this node 
 * will be equal to what root have cumulative distance. When I will move to the 
 * right then Increment in total distance will be n+1 where n is number of children 
 * in left of root. Similarly if node have to be moved to left.
 * Repeat it for every node at every node.
 * 
 * @author kuldeep
 *
 */
public class Solution {

	/**
	 * Sum of distance between every two node in tree.
	 */
	private static long cummulativeInterNodeDistance;
	
	/**
	 * The root node.
	 */
	private static Node root;
	
	/**
	 * Total nodes seen by element to be inserted.
	 */
	private static int nodesCount;
	
	/**
	 * Insert value in BST.
	 * @param value
	 * @return cumulativeInterNodeDistance
	 */
	public static long insert(int value) {
		nodesCount = 0;
		Node node = new Node(value);
		if (root == null) {
			root = node;
		} else {
			root.insert(node);
		}
		return cummulativeInterNodeDistance;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int numberOfInputs = in.nextInt();
		
		for (int i = 0; i < numberOfInputs; i++) {
			int value = in.nextInt();
			long output = insert(value);
			System.out.println(output);
		}
	}

	/**
	 * Binary search tree node.
	 * 
	 * @author kuldeep
	 *
	 */
	private static class Node {
		/**
		 * Face value of node.
		 */
		private int value;
		
		/**
		 * Number of  children to this node on left.
		 */
		private int countOfLeftChildren;
		
		/**
		 * Number of children to this node on right.
		 */
		private int countOfRightChildren;
		
		/**
		 * Sum of distance of every left children to this node.
		 */
		private long cummulativeDistanceOfLeftChildren;
		
		/**
		 * Sum of distance of every right children to this node.
		 */
		private long cummulativeDistanceOfRightChildren;

		/**
		 * Node in left.
		 */
		private Node left;
		
		/**
		 * Node in right.
		 */
		private Node right;
		
		public Node(int value) {
			super();
			this.value = value;
			this.countOfLeftChildren = 0;
			this.countOfRightChildren = 0;
			this.cummulativeDistanceOfLeftChildren = 0;
			this.cummulativeDistanceOfRightChildren = 0;
		}

		/**
		 * Insert a node in the node.
		 * @param node node to be inserted.
		 * @return depth of inserted node from this node.
		 */
		private int insert (Node node) {
			if (node.getValue() < value) {
				
				nodesCount += countOfRightChildren + 1;
				cummulativeInterNodeDistance += (cummulativeDistanceOfRightChildren + nodesCount);
				if (left == null) {
					left = node;
					cummulativeDistanceOfLeftChildren = 1;
					countOfLeftChildren = 1;
					return 1;
				} else {
					int depthOfNewNode = left.insert(node) + 1;
					countOfLeftChildren++;
					cummulativeDistanceOfLeftChildren += depthOfNewNode;
					return depthOfNewNode;
				}
			} else if (node.getValue() > value) {
				
				nodesCount += countOfLeftChildren + 1;
				cummulativeInterNodeDistance += (cummulativeDistanceOfLeftChildren + nodesCount);
				if (right == null) {
					right = node;
					cummulativeDistanceOfRightChildren = 1;
					countOfRightChildren = 1;
					return 1;
				} else {
					int depthOfNewNode = right.insert(node) + 1;
					countOfRightChildren++;
					cummulativeDistanceOfRightChildren += depthOfNewNode;
					return depthOfNewNode;
				}
			} else {
				throw new RuntimeException("node already exists.");
			}
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}
}
