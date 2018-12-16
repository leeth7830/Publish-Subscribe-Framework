package cs601.project2;

import java.io.IOException;
import java.util.Set;
/**
 * Command line to run: java -cp project2.jar cs601.project2.TestApplication argument
 * ex: java -cp project2.jar cs601.project2.TestApplication Sync || AsyncOrdered || AysncUnordered
 * Main class that interacts with and takes in arguments and inputs from the user
 * @author Tae Hyon Lee
 *
 */
public class TestApplication {
	public static void main(String[] args) throws IOException {
		
		ConfigurationManager config = new ConfigurationManager(args);
		Broker<AmazonData> broker = config.getBroker();
		Publisher pubOne = new PublisherOne(broker, config, 0);
		Publisher pubTwo = new PublisherTwo(broker, config, 1);
		Subscriber<AmazonData> subOne = new SubscriberOne(config, 0);
		Subscriber<AmazonData> subTwo = new SubscriberTwo(config, 1);
		broker.subscribe(subOne);
		broker.subscribe(subTwo);
		Thread t1 = new Thread(pubOne);
		Thread t2 = new Thread(pubTwo);
		
		long tStart = System.currentTimeMillis();
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		long tMiddle = System.currentTimeMillis();
		System.out.println(tMiddle - tStart);
		broker.shutdown();
		subOne.print();
		subTwo.print();
		long tEnd = System.currentTimeMillis();
		System.out.println(tEnd - tStart);
	}
	
}
