package sait.bms.problemdomain;

public class CookBook extends Book {
	private String publisher;
	private String diet;
	public CookBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CookBook(long isbn, String callNumber, int availableQty, int totalQty, String title, String publisher, String diet) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.publisher=publisher;
		this.diet=diet;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	@Override
	public String toString() {
		return "CookBook [publisher=" + publisher + ", diet=" + diet + ", getIsbn()=" + getIsbn() + ", getCallNumber()="
				+ getCallNumber() + ", getAvailableQty()=" + getAvailableQty() + ", getTotalQty()=" + getTotalQty()
				+ ", getTitle()=" + getTitle() + "]";
	}
	
}
