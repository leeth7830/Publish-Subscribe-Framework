package cs601.project2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Subscriber one that reads items published by publisher and writes it to an output file.
 * @author TuckFae
 *
 * @param <T>
 */
public class SubscriberOne<T> implements Subscriber  {
	private String outputFileName; 
	private BufferedWriter writer;
	private Gson gson = new GsonBuilder().create();
	private int count; 
	private Object lock = new Object();
	
	public SubscriberOne(ConfigurationManager config, int number) throws IOException {
		outputFileName = config.getConfig().getOutputFileName()[number];
		File jsonFile = new File(outputFileName);
		writer = new BufferedWriter(new FileWriter(jsonFile));
	}
	
	/**
	 * writes to output json file
	 */
	public void onEvent(Object item) throws IOException {
		AmazonData data = (AmazonData) item;
		if (data.getUnixReviewTime() >= 1362268800 )
		{
			synchronized(lock) {
				gson.toJson(data, writer);
				writer.write("\n");
			}
			count ++;
		}		
	}
	
	//NOTE that this is only for the purpose of COUNTING which is not necessary
	public void print (){
		System.out.println(outputFileName + " " + count);
	}

}
