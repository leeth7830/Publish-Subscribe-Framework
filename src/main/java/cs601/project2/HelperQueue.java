package cs601.project2;

import java.util.concurrent.TimeUnit;

/**
 * A blockingqueue that stores items from publishers for broker to take to deliver to subscribers
 * @author Tae Hyon Lee
 *
 * @param <T>
 */
public class HelperQueue<T> {
	private T[] items;
	private int start;
	private int end;
	private int size;

	
	public HelperQueue(int size) {
		this.items = (T[]) new Object[size];
		this.start = 0;
		this.end = -1;
		this.size = 0;

	}

	/**
	 * puts item in the queue unless it is full
	 * @param item
	 */
	public synchronized void put(T item) {
		while (size == items.length) {
			try {
				wait();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int next = (end+1)%items.length;
		items[next] = item;
		end = next;
		size++;
		if (size == 1){
			this.notifyAll();
		}
	}
	
	/**
	 * takes item from the queue and returns unless the queue is empty
	 * @return item
	 */
	public synchronized T take() {
		while (size == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		T item = items[start];
		start = (start+1)%items.length;
		size --;
		if (size == items.length-1) {
			this.notifyAll();
		}
		return item;
	}
	
	public synchronized T poll(int time) throws InterruptedException {
		if (size != 0) {
			Thread.sleep(time);
		}
		else {
			return null;
		}
		T item = items[start];
		return item;
	}
	
	public synchronized boolean isEmpty() {
		return size == 0;
	}
	
}
