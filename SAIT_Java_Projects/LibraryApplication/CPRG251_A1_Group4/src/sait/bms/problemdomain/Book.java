package sait.bms.problemdomain;

public class Book {
	//Attributes
	
	private long isbn;
	private String callNumber;
	private int availableQty;
	private int totalQty;
	private String title;
	//Constructors
	public Book() {
	}
	public Book(long isbn, String callNumber, int availableQty, int totalQty, String title) {
		this.isbn = isbn;
		this.callNumber = callNumber;
		this.availableQty = availableQty;
		this.totalQty = totalQty;
		this.title = title;
	}
	//Getters and Setters
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", callNumber=" + callNumber + ", availableQty=" + availableQty + ", totalQty="
				+ totalQty + ", title=" + title + "]";
	}
	
}
