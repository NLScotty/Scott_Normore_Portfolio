package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import sait.frms.manager.ReservationManager;
import sait.frms.manager.FlightManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the Reservations tab.
 * 
 */
public class ReservationsTab extends TabBase 
{
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * List of Reservations.
	 */
	private JList<Reservation> reservationsList;
	
	private DefaultListModel<Reservation> reservationsModel;
	
	/**
	 * Creates the components for Reservations tab.
	 */
	/**
	 * Creates the components for Reservations tab.
	 * 
	 * @param ReservationManager Instance of ReservationManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel,BorderLayout.SOUTH);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel,BorderLayout.EAST);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
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
		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
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
		
		JLabel title = new JLabel("Search", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		titlePanel.add(title);
		
		//Input Panel
		
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill=GridBagConstraints.HORIZONTAL;
		

		c.weightx=0.05;
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=1;
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(codeLabel,c);
		
		c.weightx=0.05;
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=1;
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(airlineLabel,c);
		
		c.weightx=0.05;
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=1;
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(nameLabel,c);

		c.weightx=0.95;
		c.gridx=1;
		c.gridy=0;
		c.gridwidth=400;
		JTextField codeTextField = new JTextField();
		inputPanel.add(codeTextField,c);
		
		c.weightx=0.95;
		c.gridx=1;
		c.gridy=1;
		c.gridwidth=400;
		JTextField airlineTextField = new JTextField();
		inputPanel.add(airlineTextField,c);
		
		c.weightx=0.95;
		c.gridx=1;
		c.gridy=2;
		c.gridwidth=400;
		JTextField nameTextField = new JTextField();
		inputPanel.add(nameTextField,c);
		
		//Button Panel
		
		buttonPanel.setLayout(new GridLayout(1,1));
		JButton findReservationButton = new JButton("Find Reservations");
		buttonPanel.add(findReservationButton);
		
		masterPanel.add(titlePanel, BorderLayout.NORTH);
		masterPanel.add(inputPanel, BorderLayout.CENTER);
		masterPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		class ReservationTabFindReservationsActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String code = codeTextField.getText();
				String airline = airlineTextField.getText();
				String name = nameTextField.getText();

				
				ArrayList<Reservation> matchingReservationList = reservationManager.findReservations(code,airline,name);
				
				reservationsModel.clear();
				for(Reservation reservation : matchingReservationList) {
					reservationsModel.addElement(reservation);
				}
				
			}
			
		}
		findReservationButton.addActionListener(new ReservationTabFindReservationsActionListener());
		
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
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(codeLabel,c);
		
		c.gridx=1;
		c.gridy=0;
		JTextField codeTextField = new JTextField(10);
		codeTextField.setEditable(false);
		inputPanel.add(codeTextField,c);
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=1;
		JLabel flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(flightLabel,c);
		
		c.gridx=1;
		c.gridy=1;
		JTextField flightTextField = new JTextField(10);
		flightTextField.setEditable(false);
		inputPanel.add(flightTextField,c);
		
		c.gridx=0;
		c.gridy=2;
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(airlineLabel,c);
		
		c.gridx=1;
		c.gridy=2;
		JTextField airlineTextField = new JTextField(10);
		airlineTextField.setEditable(false);
		inputPanel.add(airlineTextField,c);
		
		c.gridx=0;
		c.gridy=3;
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(costLabel,c);
		
		c.gridx=1;
		c.gridy=3;
		JTextField costTextField = new JTextField(10);
		costTextField.setEditable(false);
		inputPanel.add(costTextField,c);
		
		c.gridx=0;
		c.gridy=4;
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(nameLabel,c);

		c.gridx=1;
		c.gridy=4;
		JTextField nameTextField = new JTextField(10);
		inputPanel.add(nameTextField,c);
		
		c.gridx=0;
		c.gridy=5;
		JLabel citizenshipLabel = new JLabel("Citizenship:");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(citizenshipLabel,c);
		
		c.gridx=1;
		c.gridy=5;
		JTextField citizenshipTextField = new JTextField(10);
		inputPanel.add(citizenshipTextField,c);
		
		c.gridx=0;
		c.gridy=6;
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setHorizontalAlignment(JLabel.RIGHT);
		inputPanel.add(statusLabel,c);

		String[] statusOptions = {"Active","Inactive"};
		c.gridx=1;
		c.gridy=6;
		JComboBox statusComboBox = new JComboBox(statusOptions);
		inputPanel.add(statusComboBox,c);
		
		//Button Panel
		
		buttonPanel.setLayout(new GridLayout(1,1));
		JButton reserveButton = new JButton("Update");
		buttonPanel.add(reserveButton);
		
		
		masterPanel.add(titlePanel, BorderLayout.NORTH);
		masterPanel.add(inputPanel, BorderLayout.CENTER);
		masterPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		inputPanel.setPreferredSize(new Dimension(200, 200));
	
		class ReservationTabListActionListener implements ListSelectionListener{
			/**
			 * Called when user selects an item in the JList.
			 */
			@Override
			public void valueChanged(ListSelectionEvent ev) {
				try {
					Reservation selected = reservationsList.getSelectedValue();
				//Add other Values
					codeTextField.setText(selected.getCode());
					flightTextField.setText(selected.getFlightCode());
					airlineTextField.setText(selected.getAirline());
					costTextField.setText("$"+selected.getCost());
					nameTextField.setText(selected.getName());
					citizenshipTextField.setText(selected.getCitizenship());
					if(selected.isActive()) {
						statusComboBox.setSelectedItem("Active");
					}else {
						statusComboBox.setSelectedItem("Inactive");
					}
				}catch(NullPointerException ex) {
					//exists to stop crashing of program when it new list is selected.
				}
			}
		}
		
		reservationsList.addListSelectionListener(new ReservationTabListActionListener());
		
		return masterPanel;
	}
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
		}
		
	}
}