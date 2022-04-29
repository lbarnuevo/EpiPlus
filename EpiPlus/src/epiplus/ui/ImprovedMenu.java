package epiplus.ui;

import java.io.*;
import java.sql.*;
import static epiplus.ui.Auxiliar.*;
import epiplus.ifaces.*;
import epiplus.jdbc.*;
import epiplus.pojos.*;

public class ImprovedMenu {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Connection c;
	private static JDBCManager jdbcManager;
	
	private static DoctorManager doctorManager;
	//private static EmergencyContactManager ecManager;
	//private static EpisodeManager episodeManager;
	//private static EpisodeSymptomManager esManager;
	//private static MedicationManager medicationManager;
	private static PatientManager patientManager;
	//private static PatientMedicationManager pmManager;
	//private static SymptomManager symptomManager;
	
	public static void connect(){
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/epiplus.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			jdbcManager.createTables();
			
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("WELCOME TO EPI+");
		connect();
		
		jdbcManager = new JDBCManager();
		doctorManager = new JDBCDoctorManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);
		
		int choice;
		String register = null;
		Doctor doc = null;
		Patient p = null;
		
		try {
			do {
				System.out.println("Please choose an option: ");
				showMenu();
				
				choice = getPositiveInteger("");
				switch(choice) {
					case 1: 
						//TODO loginPatient();
						break;
						
					case 2:
						//TODO loginDoctor();
						break;
						
					case 3: //maybe we should create a method for registration? 
						/*
						registerMenu();
						System.out.println("Please choose an option: ");
						int option = getPositiveInteger("");
						
						switch(option) {
							case 1:
								System.out.println("\n\tREGISTER AS DOCTOR" + "\nDo you want to continue the process?");
								register = getString("Press B if you want to go back to the register menu, other key if you want to continue: ");
								
								if (register.equalsIgnoreCase("B")) {
									break;
								} else {
									doc = createDoctor();
									doctorManager.addDoctor(doc);							
									System.out.println("\nYou have been successfully registered");
								}
								break;
							
							case 2:
								System.out.println("\n\tREGISTER AS PATIENT" + "\nDo you want to continue the process?");
								register = getString("Press B if you want to go back to the register menu, other key if you want to continue: ");
								
								if (register.equalsIgnoreCase("B")) {
									break;
								} else {							
									p = createPatient();
									patientManager.addPatient(p);							
									System.out.println("\nYou have been successfully registered");
								}
								
								break;
							case 0: 
								break;
							default:
								System.out.println("Please introduce a valid option. ");
						}
						
						break;
						*/
					case 0: 
						c.close();
						jdbcManager.disconnect();
						System.exit(0);
						
					default:
						System.out.println("Please introduce a valid option. ");
				}
			} while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loginUser() {
		//TODO loginUser method --> method for both users 
		//when login in with the user, we show the menu for the type os user, so maybe we could add an attribute that consisted of role of user 
	}
	
	private static void registerUser() {
		//TODO registerUser method for both users
	}
	private static void showMenu() {
		System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.Log in as a patient                                       ");
	    System.out.println(" 2.Log in as a doctor                                        ");
	    System.out.println(" 3.Register as patient/doctor                                ");
	    System.out.println(" 0. EXIT THE PROGRMAM                                        ");
	    System.out.println("---------------------------------------------------------------");
	}
	
	private static void registerMenu() {
		System.out.println("                  REGISTER MENU                         ");
	    System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.Register as a doctor                           ");
	    System.out.println(" 2.Register as a patient                                     ");
	    System.out.println(" 0.GO BACK TO MAIN MENU                              ");
	    System.out.println("---------------------------------------------------------------");
	}
	
	private static void patientMenu() throws Exception{
		do {
			System.out.println("                  PATIENT MENU                        ");
		    System.out.println("---------------------------------------------------------------");
		    System.out.println(" 1.Register episode                                          ");
		    System.out.println(" 2.Input new data on medication                              ");
		    System.out.println(" 3.See user information                                      ");
		    System.out.println(" 4.Update user information                                   ");
		    System.out.println(" 5.Call emergency contacts                                   ");
		    System.out.println(" 6.See list of medications                                   ");
		    System.out.println(" 7.Show graphs on my evolution                               ");
		    System.out.println(" 8.Search doctor                                             ");
		    System.out.println(" 9.Show recipe                                               ");
		    System.out.println(" 0. GO BACK TO MAIN ME                                       ");
		    System.out.println("---------------------------------------------------------------");
		    
			int choice = Integer.parseInt(reader.readLine());
			
			switch (choice) {
				case 1:
					//TODO register episode
					break;
				case 2:
					//TODO input new data on medication
					break;
				case 3:
					//TODO see user information
					break;
				case 4: 
					//TODO update user information
					break;
				case 5:
					//TODO Call emergency contacts
					break;
				case 6: 
					//TODO see list of medication
					break;
				case 7: 
					//TODO show graphs on my evolution
					break;
				case 8: 
					//TODO search doctor
					break;
				case 9: 
					//TODO show recipes
					break; 
				case 0:
					return;
				default:
					break;
				}
		}
		while(true);
	}
	
	private static void doctorMenu() throws Exception{
		do {
			System.out.println("                  DOCTOR MENU                         ");
		    System.out.println("---------------------------------------------------------------");
		    System.out.println(" 1.See data on patient                                       ");
		    System.out.println(" 2.See user information                                      ");
		    System.out.println(" 3.Update user information                                   ");
		    System.out.println(" 0. GO BACK TO MAIN MENU              ");
		    System.out.println("---------------------------------------------------------------");
		    
			int choice = Integer.parseInt(reader.readLine());
			
			switch (choice) {
				case 1:
					//TODO see data on patient
					break;
				case 2:
					//TODO see user information
					break;
				case 3:
					//TODO update user information 
					break;
				case 0:
					return;
				default:
					break;
				}
		}
		while(true);
	}

}
