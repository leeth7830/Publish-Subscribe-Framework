package cs601.project2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

/**
 * Configuration Manager that reads the config file for file names, blocking queue and threadpool sizes, and poll Time.
 * @author Tae Hyon Lee
 *
 */
public class ConfigurationManager {
	
	private String[] config;
	private Config data;
	
	public ConfigurationManager(String[] config){
		this.config = config;
		readConfig();
	}
	
	/**
	 * reads config file
	 */
	public void readConfig() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("configuration.json")));
			Gson gson = new Gson();
			String currentLine = bufferedReader.readLine();
			data = gson.fromJson(currentLine, Config.class);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Config file is not found");
		} catch (IOException e) {
			System.out.println("COULD NOT READ CONFIGURATION LINE");
		}
	}
	
	public Config getConfig() {
		return data;
	}
	
	/**
	 * returns broker depends on what the user specified.
	 * @return broker
	 */
	public Broker getBroker() {
		if (config.length > 0) {
			if (config[0].equals("Sync")) {
				return new SynchronousOrderedDispatchBroker();
			}
			else if (config[0].equals("AsyncOrdered")) {
				return new AsyncOrderedDispatchBroker(data.getBlockingQueueSize(), data.getPollTime());
			}
			else if (config[0].equals("AsyncUnordered")) {
				return new AsyncUnorderedDispatchBroker(data.getThreadpoolSize());
			}
			else {
				System.out.println("Invalid argument");
				throw new IllegalArgumentException();
			}
		}
		throw new IllegalArgumentException();
	}
}
