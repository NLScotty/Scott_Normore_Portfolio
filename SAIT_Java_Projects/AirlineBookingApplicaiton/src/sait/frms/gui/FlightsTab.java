package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase 
{
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	RandomAccessFile reservationsFile;
	/**
	 * List of flights.
	 */
	
	
	
	private JList<Flight> flightsList;
	
	private DefaultListModel<Flight> flightsModel;
	
	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) throws IOException {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		this.reservationsFile = new RandomAccessFile("res/Reservations.bin", "rw");
		
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel,BorderLayout.SOUTH);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10,10,10,10));
		flightsModel = new DefaultListModel<>();
		flightsModel.addElement(new Flight("Pizza","Pizza","Pizza","Pizza","Pizza",12,21.5));
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		
		//flightsList.addListSelectionListener(new FlightsTabListActionListener());
		
		panel.add(scrollPane,BorderLayout.CENTER);
		
		return panel;
	}
	
	//create south panel
	private JPanel createSouthPanel() {
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		JPanel inputPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		//Title Panel
		
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		titlePanel.add(title);
		
		//Input Panel
		
		String[] toValues = {"AMS","ATL","CDG","DEL","DFW","DXB","FRA","HKG","HND","LHR","ORD","PEK","PVG","YEG","YOW","YUL","YVR","YWG","YYC","YYZ"} ;
		String[] fromValues = {"AMS","ATL","CDG","DEL","DFW","DXB","FRA","HKG","HND","LHR","ORD","PEK","PVG","YEG","YOW","YUL","YVR","YWG","YYC","YYZ"} ;
		String[] dayValues = {"Any","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill=GridBagConstraints.HORIZONTAL;
		

		c.weightx=0.05;
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=1;
		JLabel fromLabel = new JLabel("From:");
		fromLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(fromLabel,c);
		
		c.weightx=0.95;
		c.gridx=1;
		c.gridy=0;
		c.gridwidth=400;
		JComboBox fromBox = new JComboBox(fromValues);
		//fromBox.setPrototypeDisplayValue("                                                                                                                                                          ");
		fromBox.setPreferredSize(new Dimension(30,200));
		inputPanel.add(fromBox,c);
		
		c.weightx=0.05;
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=1;
		JLabel toLabel = new JLabel("To:");
		toLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(toLabel,c);
		
		c.weightx=0.95;
		c.gridx=1;
		c.gridy=1;
		c.gridwidth=400;
		JComboBox toBox = new JComboBox(toValues);
		inputPanel.add(toBox,c);
		
		c.weightx=0.05;
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=1;
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(dayLabel,c);
		
		c.weightx=0.95;
		c.gridx=1;
		c.gridy=2;
		c.gridwidth=400;
		JComboBox dayBox = new JComboBox(dayValues);
		inputPanel.add(dayBox,c);
		
		//Button Panel
		
		buttonPanel.setLayout(new GridLayout(1,1));
		JButton findFlightButton = new JButton("Find Flights");
		
		//nested class for action listener
		
		class FlightsTabFindFlightsActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {

				String toSelection = (String) toBox.getSelectedItem();
				String fromSelection = (String) fromBox.getSelectedItem();
				String daySelection = (String) dayBox.getSelectedItem();

				ArrayList<Flight> matchingFlightList = flightManager.findFlights(fromSelection, toSelection, daySelection);
				
				flightsModel.clear();
				
				for(Flight flight : matchingFlightList) {
					flightsModel.addElement(flight);
				}
			}
			
		}
		
		findFlightButton.addActionListener(new FlightsTabFindFlightsActionListener());
		buttonPanel.add(findFlightButton);
		
		
		masterPanel.add(titlePanel, BorderLayout.NORTH);
		masterPanel.add(inputPanel, BorderLayout.CENTER);
		masterPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		inputPanel.setPreferredSize(new Dimension(600, 120));
		
		
		
		return masterPanel;
	}
	
	
	//create East Panel
	private JPanel createEastPanel() {
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		JPanel inputPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		
		//Title Panel
		titlePanel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		titlePanel.add(title);
		
		//Input Panel
		
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		JLabel flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(flightLabel,c);
		
		c.gridx=1;
		c.gridy=0;
		JTextField flightTextField = new JTextField(10);
		flightTextField.setEditable(false);
		inputPanel.add(flightTextField,c);
		
		c.gridx=0;
		c.gridy=1;
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(airlineLabel,c);
		
		c.gridx=1;
		c.gridy=1;
		JTextField airlineTextField = new JTextField(10);
		airlineTextField.setEditable(false);
		inputPanel.add(airlineTextField,c);
		
		c.gridx=0;
		c.gridy=2;
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(dayLabel,c);
		
		c.gridx=1;
		c.gridy=2;
		JTextField dayTextField = new JTextField(10);
		dayTextField.setEditable(false);
		inputPanel.add(dayTextField,c);
		
		c.gridx=0;
		c.gridy=3;
		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(timeLabel,c);

		c.gridx=1;
		c.gridy=3;
		JTextField timeTextField = new JTextField(10);
		timeTextField.setEditable(false);
		inputPanel.add(timeTextField,c);
		
		c.gridx=0;
		c.gridy=4;
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(costLabel,c);
		
		c.gridx=1;
		c.gridy=4;
		JTextField costTextField = new JTextField(10);
		costTextField.setEditable(false);
		inputPanel.add(costTextField,c);
		
		c.gridx=0;
		c.gridy=5;
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(nameLabel,c);
		
		c.gridx=1;
		c.gridy=5;
		JTextField nameTextField = new JTextField(10);
		inputPanel.add(nameTextField,c);
		
		c.gridx=0;
		c.gridy=6;
		JLabel citizenshipLabel = new JLabel("Citizenship:");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(citizenshipLabel,c);
		
		c.gridx=1;
		c.gridy=6;
		JTextField citizenshipTextField = new JTextField(10);
		inputPanel.add(citizenshipTextField,c);
		
		//Button Panel
		
		class FlightsTabReserveButtonActionListener implements ActionListener{
			/*
			 * 	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
			 */
			@Override
			public void actionPerformed(ActionEvent e){
				//If feilds are empty, throw a null field exception
				Flight selected = flightsList.getSelectedValue();
				try {
					Reservation reserve = reservationManager.makeReservation(selected, nameTextField.getText(), citizenshipTextField.getText());
				}catch(IOException ex){
					System.out.println("Error Caught");
				}
			}
			
			
		}
		
		buttonPanel.setLayout(new GridLayout(1,1));
		JButton reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new FlightsTabReserveButtonActionListener());
		buttonPanel.add(reserveButton);
		
		
		masterPanel.add(titlePanel, BorderLayout.NORTH);
		masterPanel.add(inputPanel, BorderLayout.CENTER);
		masterPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		inputPanel.setPreferredSize(new Dimension(200, 200));
		
		class FlightsTabListActionListener implements ListSelectionListener{
			/**
			 * Called when user selects an item in the JList.
			 */
			@Override
			public void valueChanged(ListSelectionEvent ev) {
				try {
					Flight selected = flightsList.getSelectedValue();
				//Add other Values
					flightTextField.setText(selected.getCode());
					airlineTextField.setText(selected.getAirlineName());
					dayTextField.setText(selected.getWeekday());
					timeTextField.setText(selected.getTime());
					//maybe format decimal
					costTextField.setText("$"+selected.getCostPerSeat());
				}catch(NullPointerException ex) {
					//exists to stop crashing of program when it new list is selected.
				}
			}
		}
		
		flightsList.addListSelectionListener(new FlightsTabListActionListener());
		
		return masterPanel;
	}
	
	

	
	
}