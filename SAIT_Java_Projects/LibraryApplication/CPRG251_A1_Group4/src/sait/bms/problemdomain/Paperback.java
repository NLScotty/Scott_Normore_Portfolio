package sait.bms.problemdomain;

public class Paperback extends Book {
	private String authors;
	private int year;
	private String genre;
	//Constructors
	public Paperback() {
		super();
	}
	public Paperback(long isbn, String callNumber, int availableQty, int totalQty, String title, String authors,
			int year, String genre) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.authors = authors;
		this.year = year;
		this.genre = genre;
	}
	//Getters and Setters
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Paperback [authors=" + authors + ", year=" + year + ", genre=" + genre + ", getIsbn()=" + getIsbn()
				+ ", getCallNumber()=" + getCallNumber() + ", getAvailableQty()=" + getAvailableQty()
				+ ", getTotalQty()=" + getTotalQty() + ", getTitle()=" + getTitle() + "]";
	}
	
}
