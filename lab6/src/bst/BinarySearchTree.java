package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> ccomparator;
  
  	public static void main(String[] args) {
  		BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();
  		BSTVisualizer driver = new BSTVisualizer("MY TREES", 800, 800);
  		driver.drawTree(test);
  		test.add(5);
  		test.add(3);
  		test.add(8);
  		test.add(1);
  		test.add(4);
  		driver.drawTree(test);
  		BinarySearchTree<Integer> slanted = new BinarySearchTree<Integer>();
  		for (int i = 1; i < 40; i++) {
  			slanted.add(i);
  		}
  		driver.drawTree(slanted);
  		slanted.rebuild();
  		driver.drawTree(slanted);
  	}
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
		this.ccomparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.root = null;
		this.size = 0;
		this.ccomparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			BinaryNode<E> n = new BinaryNode<E> (x);
			root = n;
			size ++;
			return true;
		}else {
			boolean isAdded = addR(root, x);
			if (isAdded) {
				size ++;
			}
			return isAdded;
		}
	}
	
	public boolean addR(BinaryNode<E> n, E x) {
		int comp = ccomparator.compare(x, n.element);
		if (comp == 0) {
			return false;
		} else {
			if(comp < 0) {
				if (n.left == null) {
					BinaryNode<E> add = new BinaryNode<E>(x);
					n.left = add;
					return true;
				}else {
					return addR(n.left,  x);
				}
			}else {
				if (n.right == null) {
					BinaryNode<E> add = new BinaryNode<E>(x);
					n.right = add;
					return true;
				}else {
					return addR(n.right, x);
				}
			}

		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		if (root == null) {
			return 0;
		}
		return heightRec(root, 1);
	}
	
	private int heightRec(BinaryNode<E> n, int depth) {
		//ugly
		if (n.left == null && n.right == null) {
			return depth;
		}else if (n.left != null && n.right == null) {
			return heightRec(n.left, depth + 1);
		}else if (n.right != null && n.left == null) {
			return heightRec(n.right, depth + 1);
		}
		return Math.max(heightRec(n.left, depth + 1), heightRec(n.right, depth + 1));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);
	}
	private void print(BinaryNode<E> n) {
		if (n != null) {
			print(n.left);
			System.out.println(n.element);
			print(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		if (size < 3) {
			return;
		}
		ArrayList<E> values = new ArrayList<E>();
		toArray(root, values);
		root = buildTree(values, 0, values.size() - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	
	}
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) {
			return null;
		}else if (first == last) {
			return  new BinaryNode<E> (sorted.get(first));
		}
		int mid = first + (last - first) / 2;
		BinaryNode<E> n = new BinaryNode<E> (sorted.get(mid));
		n.left = buildTree(sorted, first, mid);
		n.right = buildTree(sorted, mid+1, last);
		return n;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
