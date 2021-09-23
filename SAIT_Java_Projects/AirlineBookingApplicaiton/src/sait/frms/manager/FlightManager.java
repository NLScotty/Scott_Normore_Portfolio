package sait.frms.manager;

import java.util.*;
import sait.frms.problemdomain.*;
import java.io.*;

public class FlightManager {
	private final String FLIGHTS_PATH="res/flights.csv";
	private final String AIRPORTS_PATH="res/flights.csv";
	public final static String WEEKDAY_ANY="ANY";
	public final static String WEEKDAY_SUNDAY="SUNDAY";
	public final static String WEEKDAY_MONDAY="MONDAY";
	public final static String WEEKDAY_TUESDAY="TUESDAY";
	public final static String WEEKDAY_WEDNESDAY="WEDNESDAY";
	public final static String WEEKDAY_THURSDAY="THURSDAY";
	public final static String WEEKDAY_FRIDAY="FRIDAY";
	public final static String WEEKDAY_SATURDAY="SATURDAY";

	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	
	//to be modified
	public FlightManager() throws IOException {
		super();
		this.airports = new ArrayList<String>();
		
		//add either here or in main/driver a try catch that deals with file not found exception.
		populateFlights();
		populateAirports();

	}
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	public ArrayList<String> getAirports() {
		return airports;
	}
	
	public String findAirportByCode(String code) {
		return "";
	}
	public Flight findFlightByCode(String code) {
		for(int i=0;i<this.flights.size();i++) {
			if(flights.get(i).getCode().equals(code)) {
				return flights.get(i);
			}
		}
		return null;
	}
	
	//needs to be changed so that on any, will select all to and from.
	public ArrayList<Flight> findFlights(String from, String to, String weekday){ 
		ArrayList<Flight> matchingFlights = new ArrayList<Flight>();
		for(int i=0;i<this.flights.size();i++) {
			if(weekday.equals("Any")) {
				if(flights.get(i).getFrom().equals(from) && flights.get(i).getTo().equals(to)) {
					matchingFlights.add(flights.get(i));
				}
			}
			else if(flights.get(i).getFrom().equals(from) && flights.get(i).getTo().equals(to)  && flights.get(i).getWeekday().equals(weekday) ) {
				matchingFlights.add(flights.get(i));
			}
		}
		return matchingFlights;
	}
	//Maybe for loading csv
	private void populateFlights() throws FileNotFoundException {
		Scanner fileReader=new Scanner(new File(FLIGHTS_PATH));
		this.flights = new ArrayList<Flight>();
		while(fileReader.hasNextLine()) {
			String[] values = fileReader.nextLine().split(",");	
			flights.add(new Flight(values[0],values[1],values[2],values[3],values[4],Integer.parseInt(values[5]),Double.parseDouble(values[6])));
		}
		
	}
	//Maybe for loading csv
	private void populateAirports() throws FileNotFoundException {
		Scanner fileReader=new Scanner(new File(AIRPORTS_PATH));
		while(fileReader.hasNextLine()) {
			airports.add(fileReader.nextLine());
		}
		fileReader.close();
	}
	
}
