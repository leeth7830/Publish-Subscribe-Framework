package cs601.project2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
/**
 * Publisher that reads the json file specified in the config file and send to broker to deliver.
 * @author Tae Hyon Lee
 *
 */
public class PublisherTwo implements Publisher{
	
	private Broker<AmazonData> broker;
	private String fileName;
	
	public PublisherTwo(Broker<AmazonData> broker, ConfigurationManager config, int number) {
		this.broker = broker;
		this.fileName = config.getConfig().getFileName()[number];
	}

	public void run() {
		read();
	}
	
	/**
	 * reads the json file specified.
	 */
	public void read() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			Gson gson = new Gson();
			String currentLine;
			int count = 0;
			while ((currentLine = bufferedReader.readLine()) != null) {
				count++;
				try {
					AmazonData data = gson.fromJson(currentLine, AmazonData.class);
					broker.publish(data);
				}
				catch (JsonSyntaxException e) {
					System.out.println("BAD DATA at row " + (count + 1) + " in the json file");
				} catch (JsonIOException e) {
					System.out.println("Error while attempting to read JSON FILE");
					System.exit(0);
				}
			}
			System.out.println(fileName + " " + count);
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("JSON File does not exist.");
		} catch (IOException e) {
			System.out.println("Error while attempting to read JSON FILE");
		}
	}
}
