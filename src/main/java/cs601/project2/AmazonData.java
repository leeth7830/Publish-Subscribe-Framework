package cs601.project2;
/**
 * Amazon Data object to store parsed Amazon review data
 * @author Tae Hyon Lee
 *
 */
public class AmazonData {
	private String reviewerID;
	private String asin;
	private String reviewerName;
	private int[] helpful;
	private String reviewText;
	private double overall;
	private String summary;
	private long unixReviewTime;
	private String reviewTime;
	
	public AmazonData(String reviewerID, String asin, String reviewerName, int[] helpful, String reviewText, double overall, String summary, long unixReviewTime, String reviewTime) {
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewerName = reviewerName;
		this.helpful = helpful;
		this.reviewText = reviewText;
		this.overall = overall;
		this.summary = summary;
		this.unixReviewTime = unixReviewTime;
		this.reviewTime = reviewTime;
	}
	
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	public String getReviewrName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewrName) {
		this.reviewerName = reviewrName;
	}
	public int[] getHelpful() {
		return helpful;
	}
	public void setHelpful(int[] helpful) {
		this.helpful = helpful;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public double getOverall() {
		return overall;
	}
	public void setOverall(double overall) {
		this.overall = overall;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public long getUnixReviewTime() {
		return unixReviewTime;
	}
	public void setUnixReviewTime(long unixReviewTime) {
		this.unixReviewTime = unixReviewTime;
	}
	public String getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}
	
}
