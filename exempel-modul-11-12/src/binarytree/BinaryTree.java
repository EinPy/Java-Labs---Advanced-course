package binarytree;
public class BinaryTree<E> {
	private Node<E> root;
	private int size;

	/** Skapar ett tomt träd. */
	public BinaryTree() {
		root = null;
	}

	/** Statiskt nästlad klass - beskriver en nod i trädet. */
	private static class Node<E> {
		private E data;
		private Node<E> left;
		private Node<E> right;

		public Node(E data) {
			this.data = data;
			left = right = null;
		}
	}

	/** Skriver ut trädet i preorder */
	public void print() {
		print(root);
	}

	/* Skriver ut det träd där n är rot i preorder. */
	private void print(Node<E> n) {
		if (n != null) {
			System.out.println(n.data);
			print(n.left);
			print(n.right);
		}
	}
	
	/** Returnerar en sträng som representerar trädet. */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		buildString(root, 0, sb);
		return sb.toString();
	}

	/*
	 * Lägger till de tecken som representerar det delträd där n är rot till sb.
	 * Delträdet indenteras med indent blanktecken.
	 */
	private void buildString(Node<E> n, int indent, StringBuilder sb) {
		for (int i = 0; i < indent; i++) {
			sb.append(' ');
		}
		if (n == null) {
			sb.append("null\n");
		} else {
			sb.append(n.data);
			sb.append('\n');
			buildString(n.left, indent + 2, sb);
			buildString(n.right, indent + 2, sb);
		}
	}
	
	/** Returnerar antal noder. */
	public int size() {
		return size;
	}
	
	/**
	 * Skapar ett binärt träd med innehållet data i roten och med leftTree som
	 * vänster subträd och rightTree som höger subträd.
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		size += 1;
		if (leftTree != null) {
			root.left = leftTree.root;
			size += leftTree.size();
		}
		if (rightTree != null) {
			root.right = rightTree.root;
			size += rightTree.size();
		}
	}

	public static void main(String[] args) {
		BinaryTree<String> empty = new BinaryTree<String>();
		// empty.print();
		System.out.println(empty);

		BinaryTree<String> tmp = new BinaryTree<String>("d", null, null);
		BinaryTree<String> left = new BinaryTree<String>("b", null, tmp);
		tmp = new BinaryTree<String>("e", null, null);
		BinaryTree<String> right = new BinaryTree<String>("c", tmp, null);
		BinaryTree<String> tree = new BinaryTree<String>("a", left, right);
		// tree.print();
		System.out.println(tree);
	}
}
