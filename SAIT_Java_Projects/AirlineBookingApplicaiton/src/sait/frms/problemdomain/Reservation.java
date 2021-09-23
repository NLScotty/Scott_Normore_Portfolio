package sait.frms.problemdomain;

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	//Constructors 
	//Place Holder Constructor: no use yet

	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost, boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}
	//getters
	public String getCode() {
		return code;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public String getAirline() {
		return airline;
	}
	public String getName() {
		return name;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public double getCost() {
		return cost;
	}
	public boolean isActive() {
		return active;
	}
	//setters
	public void setName(String name) {
		this.name = name;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	//to be edited
	
	@Override
	public String toString() {
		return "Reservation [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline + ", name=" + name
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}
}
