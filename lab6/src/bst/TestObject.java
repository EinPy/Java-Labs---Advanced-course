package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestObject {

	private BinarySearchTree<Integer> myIntTree;
	private BinarySearchTree<String> myStringTree;

	@BeforeEach
	void setUp() throws Exception {
		this.myIntTree = new BinarySearchTree<Integer>();
		this.myStringTree = new BinarySearchTree<String>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myIntTree.clear();
		myStringTree.clear();
	}

	@Test
	void testEmpty() {
		assertEquals(0, myIntTree.size(), "Check size of empty tree");
	}
	
	@Test
	void testAdd1() {
		myIntTree.add(5);
		assertEquals(5, myIntTree.root.element, "Is element added?");
	}
	@Test
	void testOrder() {
		myIntTree.add(5);
		myIntTree.add(3);
		myIntTree.add(8);
		assertEquals(5, myIntTree.root.element, "Is root added?");
		assertEquals(3, myIntTree.root.left.element, "Is root added?");
		assertEquals(8, myIntTree.root.right.element, "Is root added?");
		myIntTree.printTree();
	}
	@Test
	void testOrderString() {
		myStringTree.add("D");
		myStringTree.add("B");
		myStringTree.add("X");
		myStringTree.add("A");
		myStringTree.add("C");
		assertEquals("D", myStringTree.root.element, "Is root added?");
		assertEquals("B", myStringTree.root.left.element, "Is root added?");
		assertEquals("X", myStringTree.root.right.element, "Is root added?");
		assertEquals("A", myStringTree.root.left.left.element, "Root at depth 2");
		assertEquals("C", myStringTree.root.left.right.element, "Root at depth 2");
		myStringTree.printTree();
	}


}
