package sait.frms.application;

import sait.frms.gui.MainWindow;

import java.io.IOException;

import sait.frms.gui.*;
import sait.frms.manager.*;
/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		FlightManager FManager= new FlightManager();
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
