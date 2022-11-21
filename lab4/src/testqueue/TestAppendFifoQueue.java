package testqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<Integer> myIntQueue2;

	@BeforeEach
	void setUp() {
		myIntQueue = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown(){
		myIntQueue = null;
		myIntQueue2 = null;
	}

	@Test
	void testNewTestFile() {
		assertTrue(myIntQueue.isEmpty(), "Wrong result from empty of queue");
		assertEquals(0, myIntQueue.size(), "Wrong size of empty queue");
		assertTrue(myIntQueue2.isEmpty(), "Wrong result from empty of queue");
		assertEquals(0, myIntQueue2.size(), "Wrong size of empty queue");
	}
	@Test
	void twoEmpty() {
		myIntQueue.append(myIntQueue2);
		assertEquals(0,myIntQueue.size());
	}
	@Test
	void oneEmptyFromBehind() {
		int nbr = 5;
		for (int i = 1; i <= nbr; i++) {
			myIntQueue.offer(i);
		}
		myIntQueue.append(myIntQueue2);
		//size
		assertEquals(nbr,myIntQueue.size());
		
		//Order
		Iterator<Integer> itr = myIntQueue.iterator();
		assertTrue(itr.hasNext(), "Wrong result from hasNext");
		for (int i = 1; i <= nbr; i++) {
			assertTrue(itr.hasNext(), "Wrong result from hasNext");
			assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next");
		}
		assertFalse(itr.hasNext(), "Wrong result from hasNext");
		assertThrows(NoSuchElementException.class, () -> itr.next());
		
		
	}
	
	@Test
	void oneEmptyInTheFront() {
		int nbr = 5;
		for (int i = 1; i <= nbr; i++) {
			myIntQueue.offer(i);
		}
		myIntQueue2.append(myIntQueue);
		//Size
		assertEquals(nbr,myIntQueue2.size());
		//Order
		Iterator<Integer> itr = myIntQueue2.iterator();
		assertTrue(itr.hasNext(), "Wrong result from hasNext");
		for (int i = 1; i <= nbr; i++) {
			assertTrue(itr.hasNext(), "Wrong result from hasNext");
			assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next");
		}
		assertFalse(itr.hasNext(), "Wrong result from hasNext");
		assertThrows(NoSuchElementException.class, () -> itr.next());
	}
	@Test
	void doubleTrouble() {
		int nbr = 5;
		for (int i = 1; i <= nbr; i++) {
			myIntQueue.offer(i);
		}
		int nbr2 = 10;
		for (int i = 6; i <= nbr2; i++) {
			myIntQueue2.offer(i);
		}
		myIntQueue.append(myIntQueue2);
		//Size
		assertEquals(10,myIntQueue.size());
		//Order
		Iterator<Integer> itr = myIntQueue.iterator();
		assertTrue(itr.hasNext(), "Wrong result from hasNext");
		for (int i = 1; i <= nbr2; i++) {
			//System.out.print(i);
			assertTrue(itr.hasNext(), "Wrong result from hasNext");
			assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next: " + i);
		}
		assertFalse(itr.hasNext(), "Wrong result from hasNext");
		assertThrows(NoSuchElementException.class, () -> itr.next());
	}
	

}
