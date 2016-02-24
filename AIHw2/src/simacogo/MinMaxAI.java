package simacogo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//constructor to set the AI level
public class MinMaxAI {
	int AILevel;

	public MinMaxAI(int level) {
		this.AILevel = level;
	}

	// The inital call to the min max algorithm
	public int minMaxCall(Board state) {
		Node root = new Node(state, null);// set up the root node
		ArrayList<Node> a = root.getChildren(); // get the roots children
		minMax(a, 1);// call min max on the children.
		int max = Integer.MIN_VALUE;// variable for the best outcome
		Node move = a.get(0);// set the desired move to a starting value
		for (Node n : a) {// loop over child nodes of the root
			if (n.score > max) {// see if the childs score is greater then the current max
				max = n.score;// set the winning node and max value
				move = n;
			}
		}
		return move.slot;// return the best node

	}

	// recursive call for min max
	public void minMax(ArrayList<Node> childNodes, int depth) {
		// if we are continuing down the tree
		if (depth < AILevel * 2) {
			// loop over nodes passed from previous call
			for (Node n : childNodes) {
				ArrayList<Node> a = n.getChildren();// get the children of each node
				minMax(a, depth + 1);// call min max on each child
				if (depth % 2 != 0) {// if a min selection
					int min = Integer.MAX_VALUE;
					for (Node n2 : a) {
						if (n2.score < min) {
							min = n2.score;// set nodes score to the minimum of it's children

						}
					}
					n.score = min;
				}
				if (depth % 2 == 0) {// if a max selection
					int max = Integer.MIN_VALUE;
					for (Node n2 : a) {
						if (n2.score > max)
							max = n2.score;
					}
					n.score = max;// set max to the maximum of it's children

				}
			}
		}

		// if base case is true
		else {
			for (Node n : childNodes) {
				n.getScore();// populate the scores of the lead nodes
			}
		}
	}
}
