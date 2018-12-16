package cs601.project2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A broker that delivers published item asynchronously using a thread pool.
 * @author Tae Hyon Lee
 *
 * @param <T>
 */
public class AsyncUnorderedDispatchBroker<T> implements Broker {
	private ArrayList<Subscriber> list = new ArrayList<Subscriber>();
	private ExecutorService pool;

	public AsyncUnorderedDispatchBroker(int size) {
		pool = Executors.newFixedThreadPool(size);
	}
	
	/**
	 * adds new helper to threadpool to carry out the process
	 */
	public void publish(Object item) {
		pool.execute(new Helper(item));
	}
	
	public void subscribe(Subscriber subscriber) {
		list.add(subscriber);
	}
	
	/**
	 * tells pool to shut down and wait until the thread pool is completely done.
	 */
	public void shutdown() {
		pool.shutdown();
		while (!pool.isTerminated()) {
        }
		
	}
	
	/**
	 * helper thread that delivers items to subscribers.
	 * @author TuckFae
	 *
	 */
	private class Helper extends Thread {
		Object r;
		private Helper(Object item){
			r = item;
		}
		public void run() {
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
