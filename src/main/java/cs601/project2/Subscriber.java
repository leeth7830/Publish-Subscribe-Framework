package cs601.project2;

import java.io.IOException;
/**
 * An interface for subscriber class
 * @author Tae Hyon lee
 *
 * @param <T>
 */
public interface Subscriber<T> {

	/**
	 * Called by the Broker when a new item
	 * has been published.
	 * @param item
	 * @throws IOException 
	 */
	public void onEvent(T item) throws IOException;
	
	//NOTE that this is only for the purpose of COUNTING which is not necessary
	public void print();
	
}