package cs601.project2;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Synchronous Broker that synchronously deliver items to subscribers from publisher
 * @author Tae Hyon Lee
 *
 * @param <T>
 */
public class SynchronousOrderedDispatchBroker<T> implements Broker {
	
	ArrayList<Subscriber> list = new ArrayList<Subscriber>();
	
	/**
	 * the same thread handles the delivery as well.
	 */
	public void publish(Object item) {
		for (Subscriber sub : list) {
			try {
				sub.onEvent(item);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void subscribe(Subscriber subscriber) {
		list.add(subscriber);		
	}

	public void shutdown() {
		
	}

}
