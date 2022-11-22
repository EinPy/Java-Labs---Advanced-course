package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		size += 1;
		QueueNode<E> n = new QueueNode<E>(e);
		
		if (last == null) {
			n.next = n;
			last = n;
			return true;
		}
		n.next = last.next;
		last.next = n;
		last = n;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {	
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (last == null) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (last == null) {
			return null;
		}
		if (size == 1) {
			QueueNode<E> temp = last;
			last = null;
			size -= 1;
			return temp.element;
		}
		QueueNode<E> head = last.next;
		last.next = last.next.next;
		size -= 1;
		return head.element;
	}
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		//Check if adding non addable queue
		if (q == this) {
			throw new IllegalArgumentException();
		}
		if(q.last== null ) {
			return;
		}
		//Check if que that is adding to is null, and if so, make it the second que
		if (last == null) {
			last = q.last;
			size = q.size;
		}else {
			QueueNode<E> temp = q.last.next;
			q.last.next = last.next;
			last.next = temp;
			last = q.last;
			size += q.size;
			q.size = 0;
			q.last = null;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		int seen;
		
		private QueueIterator() {
			pos = last; // head of queue
			seen = 0;
		}
		
		public boolean hasNext() {
			if (pos != null && seen != size) {
				return true;
			}
			return false;
		}
		
		public E next() {
			if (pos == null || seen == size) {
				throw new NoSuchElementException();
			}
			pos = pos.next;
			seen += 1;
			return pos.element;
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
}
