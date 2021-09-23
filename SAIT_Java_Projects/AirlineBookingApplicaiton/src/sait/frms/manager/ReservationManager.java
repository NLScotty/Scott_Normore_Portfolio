package sait.frms.manager;

import java.util.ArrayList;
import java.util.Random;

import java.io.*;
import sait.frms.problemdomain.*;

public class ReservationManager {
	public ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	private RandomAccessFile raf; 
	private final long RECORD_SIZE=181;

	public ReservationManager() throws IOException{
		this.raf = new RandomAccessFile("res/Reservations.bin", "rw");
		this.populateFromBinary();
		// TODO Auto-generated constructor stub
	}
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException {
		String randomCode= generateReservationCode(flight);
		Reservation reservation=new Reservation(randomCode, flight.getCode(),flight.getAirlineName(),name,citizenship,flight.getCostPerSeat(),true);
		this.writeToBinary(reservation);
		return reservation;
	}
	
	private void writeToBinary(Reservation reservation) throws IOException {
		this.raf.writeUTF(reservation.getCode());
		this.raf.writeUTF(reservation.getFlightCode());
		this.raf.writeUTF(String.format("%-50s", reservation.getAirline()));
		this.raf.writeUTF(String.format("%-50s", reservation.getName()));
		this.raf.writeUTF(String.format("%-50s", reservation.getCitizenship()));
		this.raf.writeDouble(reservation.getCost());
		this.raf.writeBoolean(reservation.isActive());
		
	}
	//tweaking
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		//Check if atleast one input is not null. Else, throw Exception (Optional)
		
		ArrayList<Reservation> matchingReservations = new ArrayList<Reservation>();
		for(int i=0;i<this.reservations.size();i++) {
			if((this.reservations.get(i).getCode().equals(code) || code.equals("")) && (this.reservations.get(i).getAirline().equals(airline)|| airline.equals(""))  && (this.reservations.get(i).getName().contains(name) || name.equals(""))  ) {
				matchingReservations.add(reservations.get(i));
			}
		}
		return matchingReservations;
	}
	public Reservation findReservationByCode(String code){
		for(int i=0;i<this.reservations.size();i++) {
			if(this.reservations.get(i).getCode().equals(code)) {
				return this.reservations.get(i);
			}
		}
		return null;
	}
	/**
	public void persist() {
		
	}
	*/
	private int getAvaliableSeats(Flight flight){
		return flight.getSeats();
	}
	private String generateReservationCode(Flight flight){
		String code="";
		if(flight.isDomestic()==true){
			code=code+"D";
		}else{
			code=code+"I";
		}
		int randomNumber= new Random().nextInt(9000) + 1000;
		return code+randomNumber;
	}
	
	private void populateFromBinary() throws IOException{
		for (long position = 0; position < this.raf.length(); position += RECORD_SIZE) {
			this.raf.seek(position);
			Reservation r = new Reservation(this.raf.readUTF(),this.raf.readUTF(),this.raf.readUTF().trim(),this.raf.readUTF().trim(),this.raf.readUTF().trim(),this.raf.readDouble(),this.raf.readBoolean());
			if (r.isActive())
				this.reservations.add(r);
		}
	}
}
