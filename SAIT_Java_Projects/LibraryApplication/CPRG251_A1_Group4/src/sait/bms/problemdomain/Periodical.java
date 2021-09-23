package sait.bms.problemdomain;

public class Periodical extends Book {
	private String frequency;

	public Periodical(long isbn, String callNumber, int availableQty, int totalQty, String title, String frequency) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.frequency = frequency;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "Periodical [frequency=" + frequency + ", getIsbn()=" + getIsbn() + ", getCallNumber()="
				+ getCallNumber() + ", getAvailableQty()=" + getAvailableQty() + ", getTotalQty()=" + getTotalQty()
				+ ", getTitle()=" + getTitle() + "]";
	}
	
	
}
