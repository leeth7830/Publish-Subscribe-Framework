package cs601.project2;

/**
 * Object file that stores config file details from configuration.json
 * @author Tae Hyon Lee
 *
 */
public class Config {
	private String[] fileName;
	private String[] outputFileName;
	private double blockingQueueSize;
	private double threadpoolSize;
	private double pollTime;
	
	public Config(String[] fileName, String[] outputFileName, double blockingQueueSize, double threadpoolSize, double pollTime) {
		this.setFileName(fileName);
		this.setOutputFileName(outputFileName);		
		this.blockingQueueSize = blockingQueueSize;
		this.threadpoolSize = threadpoolSize;
		this.pollTime = pollTime;
	}
	
	public String[] getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String[] outputFileName) {
		this.outputFileName = outputFileName;
	}
	public String[] getFileName() {
		return fileName;
	}
	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}
	public void setBlockingQueueSize(int blockingQueueSize) {
		this.blockingQueueSize = blockingQueueSize;
	}
	public int getBlockingQueueSize() {
		return (int) blockingQueueSize;
	}
	public void setThreadpoolSize(int threadpoolSize) {
		this.threadpoolSize = threadpoolSize;
	}
	public int getThreadpoolSize() {
		return (int) threadpoolSize;
	}
	public void setPollTime(int pollTime) {
		this.pollTime = pollTime;
	}
	public int getPollTime() {
		return (int) pollTime;
	}
}
