package cs601.project2;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Asynchronous Ordered Dispatch Broker that Asynchronously deliver to subscribers with the use of a helper thread.
 * @author Tae Hyon Lee
 *
 */
public class AsyncOrderedDispatchBroker<T> implements Broker {
	private ArrayList<Subscriber> list = new ArrayList<Subscriber>();
	private HelperQueue<AmazonData> queue;
	private Helper helper = new Helper();
	private boolean run = true;
	private int pollTime;
	
	public AsyncOrderedDispatchBroker (int size, int pollTime) {
		queue = new HelperQueue<AmazonData>(size);
		this.pollTime = pollTime;
		helper.start();
	}
	
	/**
	 * puts the item in blockingqueue for helper thread to process.
	 */
	public void publish(Object item) {
	
		queue.put((AmazonData) item); 
		
	}
	
	public void subscribe(Subscriber subscriber) {
		list.add(subscriber);		
	}
	
	/**
	 * shuts down graceful by first waiting until the blockingqueue is empty and stopping the helper thread's loop
	 */
	public void shutdown() {
		while (!queue.isEmpty())
		{
			try {
				Thread.sleep(pollTime);
			} catch (InterruptedException e) {
				System.out.println("interrupted during waiting for emptying queue");
			}
		}
		helper.finish();
	}
	
	/**
	 * Helper thread that delivers items from queue to subscribers.
	 * @author Tae Hyon Lee
	 *
	 */
	private class Helper extends Thread {
		
		private volatile boolean running = true;
		
		public void finish() {
			running = false;
		}
		
		/**
		 * delivers until running is finished.
		 */
		@Override
		public void run() {
			Object r;
			while(running){
				r =  queue.take();
				for (Subscriber sub : list) {
					try {
						sub.onEvent(r);
					} catch (IOException e) {
						System.out.println("Error while delivering to subscriber");
					}
				}
				
			}
		}
	}
}
