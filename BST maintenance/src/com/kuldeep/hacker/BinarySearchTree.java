package com.kuldeep.hacker;

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
public class BinarySearchTree {

	/**
	 * Sum of distance between every two node in tree.
	 */
	private long cummulativeInterNodeDistance;
	
	/**
	 * The root node.
	 */
	private Node root;
	
	/**
	 * Binary search tree node.
	 * 
	 * @author kuldeep
	 *
	 */
	private class Node {
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
		private int cummulativeDistanceOfLeftChildren;
		
		/**
		 * Sum of distance of every right children to this node.
		 */
		private int cummulativeDistanceOfRightChildren;

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
		}

		private int insert (Node node) {
			if (node.getValue() < value) {
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
				if (right == null) {
					right = node;
					cummulativeDistanceOfRightChildren =1;
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
		 * @return the countOfLeftChildren
		 */
		public int getCountOfLeftChildren() {
			return countOfLeftChildren;
		}



		/**
		 * @param countOfLeftChildren the countOfLeftChildren to set
		 */
		public void setCountOfLeftChildren(int countOfLeftChildren) {
			this.countOfLeftChildren = countOfLeftChildren;
		}



		/**
		 * @return the countOfRightChildren
		 */
		public int getCountOfRightChildren() {
			return countOfRightChildren;
		}



		/**
		 * @param countOfRightChildren the countOfRightChildren to set
		 */
		public void setCountOfRightChildren(int countOfRightChildren) {
			this.countOfRightChildren = countOfRightChildren;
		}



		/**
		 * @return the cummulativeDistanceOfLeftChildren
		 */
		public int getCummulativeDistanceOfLeftChildren() {
			return cummulativeDistanceOfLeftChildren;
		}



		/**
		 * @param cummulativeDistanceOfLeftChildren the cummulativeDistanceOfLeftChildren to set
		 */
		public void setCummulativeDistanceOfLeftChildren(
				int cummulativeDistanceOfLeftChildren) {
			this.cummulativeDistanceOfLeftChildren = cummulativeDistanceOfLeftChildren;
		}



		/**
		 * @return the cummulativeDistanceOfRightChildren
		 */
		public int getCummulativeDistanceOfRightChildren() {
			return cummulativeDistanceOfRightChildren;
		}



		/**
		 * @param cummulativeDistanceOfRightChildren the cummulativeDistanceOfRightChildren to set
		 */
		public void setCummulativeDistanceOfRightChildren(
				int cummulativeDistanceOfRightChildren) {
			this.cummulativeDistanceOfRightChildren = cummulativeDistanceOfRightChildren;
		}



		/**
		 * @return the left
		 */
		public Node getLeft() {
			return left;
		}



		/**
		 * @param left the left to set
		 */
		public void setLeft(Node left) {
			this.left = left;
		}



		/**
		 * @return the right
		 */
		public Node getRight() {
			return right;
		}



		/**
		 * @param right the right to set
		 */
		public void setRight(Node right) {
			this.right = right;
		}



		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}
}
