package sait.bms.application;
import sait.bms.managers.BookManager;
import java.io.*;
public class AppDriver {
	public static void main (String [] args) throws IOException {
		BookManager manager = new BookManager();
		manager.run();
	}
}
