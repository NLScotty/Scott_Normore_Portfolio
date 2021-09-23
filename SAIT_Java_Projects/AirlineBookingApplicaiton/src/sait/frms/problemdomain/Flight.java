package sait.frms.problemdomain;

public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	
	//Constructor
	
	public Flight(String code, String fromCode, String toCode, String weekday, String time, int seats, double costPerSeat) {
		super();
		this.code=code;
		parseCode(this.code);
		this.from = fromCode;
		this.to = toCode;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	//Getters
	
	public String getCode() {
		return code;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}
	
	//Other Methods
	
	public void reserveSeat() {
		if(this.seats>0) {
			this.seats--;
		}
	}
	
	public boolean isDomestic() {
		return false;
	}
	//maybe if airline doesn't exist, do something.
	private void parseCode(String code) {
		this.airlineName=code.substring(0,2);
		
		if(this.airlineName.equals("OA")) {
			this.airlineName="Otto Airlines";
		}
		else if(this.airlineName.equals("CA")) {
			this.airlineName="Conned Air";
		}
		else if(this.airlineName.equals("TB")) {
			this.airlineName="Try a Bus Airways";
		}
		else if(this.airlineName.equals("VA")) {
			this.airlineName="Vertical Airways";
		}
	}
	@Override
	public String toString() {
		return code +", From: "+ from + ", To: " + to + ", Day: "+ weekday + ", Cost: " + costPerSeat;
	}
}
