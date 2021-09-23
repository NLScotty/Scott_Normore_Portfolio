package sait.bms.problemdomain;

public class ChildrensBook extends Book {
	private String authors;
	private String format;
	
	public ChildrensBook() {
		super();
	}
	public ChildrensBook(long isbn, String callNumber, int availableQty, int totalQty, String title,String authors, String format) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.authors=authors;
		this.format=format;
	}
	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	@Override
	public String toString() {
		return "ChildrensBook [authors=" + authors + ", format=" + format + ", getIsbn()=" + getIsbn()
				+ ", getCallNumber()=" + getCallNumber() + ", getAvailableQty()=" + getAvailableQty()
				+ ", getTotalQty()=" + getTotalQty() + ", getTitle()=" + getTitle() + "]";
	}
	
	
}
