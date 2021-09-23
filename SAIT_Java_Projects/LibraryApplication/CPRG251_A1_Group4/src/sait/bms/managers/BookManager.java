package sait.bms.managers;
import sait.bms.problemdomain.*;
import java.util.*;
import java.io.*;

public class BookManager{
	private final static String PATH ="CPRG251_A1_Group4\\res\\books.txt";
	private ArrayList<Book> books=new ArrayList<>();
	
	public void run() throws IOException{
		this.readFile();
		System.out.print(books.toString());
	}
	
	public void readFile() throws IOException {
		Scanner fileReader = new Scanner(new File(PATH));
		while(fileReader.hasNextLine()){
			String[] record = fileReader.nextLine().split(";");
			if(record[0].charAt(record[0].length()-1)=='0' || record[0].charAt(record[0].length()-1)=='1') {
				ChildrensBook book=new ChildrensBook(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5],record[6]);
				this.books.add(book);
			}
			else if(record[0].charAt(record[0].length()-1)=='2' || record[0].charAt(record[0].length()-1)=='3') {
				CookBook book=new CookBook(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5],record[6]);
				this.books.add(book);
			}
			else if(record[0].charAt(record[0].length()-1)=='4' || record[0].charAt(record[0].length()-1)=='7') {
				Paperback book=new Paperback(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5],Integer.parseInt(record[6]),record[7]);
				this.books.add(book);
			}
			else if(record[0].charAt(record[0].length()-1)=='8' || record[0].charAt(record[0].length()-1)=='9') {
				Periodical book=new Periodical(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5]);
				this.books.add(book);
			}
		}
		fileReader.close();
	}
}
