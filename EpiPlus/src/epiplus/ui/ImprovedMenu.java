package epiplus.ui;

import java.io.*;
import java.sql.*;
import java.util.*;

import static epiplus.ui.Auxiliar.*;
import epiplus.ifaces.*;
import epiplus.jdbc.*;
import epiplus.pojos.*;

public class ImprovedMenu {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Connection c;
	private static JDBCManager jdbcManager = new JDBCManager();
	
	private static DoctorManager doctorManager = new JDBCDoctorManager(jdbcManager);
	private static PatientManager patientManager = new JDBCPatientManager(jdbcManager);
	
	//THESE VARIABLES ARE TEMPORARY
	private static List<Patient> userPatients = new LinkedList<Patient>();
	private static List<Doctor> userDoctors = new LinkedList<Doctor>();
	
	//private static EmergencyContactManager ecManager;
	//private static EpisodeManager episodeManager;
	//private static EpisodeSymptomManager esManager;
	//private static MedicationManager medicationManager;
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
		System.out.println("WELCOME TO EPI+ !!");
		connect();
		
		int choice;
		
		try {
			do {
				showMenu();
				System.out.println("Please choose an option: ");
				
				choice = getPositiveInteger("");
				switch(choice) {
					case 1: 
						//TODO loginPatient();
						System.out.println("Enter patient name: ");
						String p_name = reader.readLine();
						Patient p = searchPatient(p_name);
						patientMenu(p);
						break;
						
					case 2:
						//TODO loginDoctor();
						System.out.println("Enter doctor name: ");
						String d_name = reader.readLine();
						Doctor d = searchDoctor(d_name);
						doctorMenu(d);
						break;
						
					case 3: 
						registerMenu();
						break;
						
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
	
	/*private static void loginUser() {
		//TODO loginUser method --> method for both users 
		//when login in with the user, we show the menu for the type os user, so maybe we could add an attribute that consisted of role of user 
	}
	
	private static void registerUser() {
		//TODO register method 
	} */
	
	
	private static void showMenu() {
		System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.Log in as a patient                                       ");
	    System.out.println(" 2.Log in as a doctor                                        ");
	    System.out.println(" 3.Register as patient/doctor                                ");
	    System.out.println(" 0. EXIT THE PROGRMAM                                        ");
	    System.out.println("---------------------------------------------------------------");
	}
	
	private static void registerMenu() throws NumberFormatException, IOException {
		System.out.println("                  REGISTER MENU                         ");
	    System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.Register as a doctor                           ");
	    System.out.println(" 2.Register as a patient                                     ");
	    System.out.println(" 0.GO BACK TO MAIN MENU                              ");
	    System.out.println("---------------------------------------------------------------");
	    
	    do {
	    	int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
				case 1:
					registerDoctor();
					break;
				case 2:
					registerPatient();
					break;
				case 0:
					return;
				default:
					System.out.println("Please enter a valid option. ");
				}
	    } while(true); 
	}
	
	private static void registerDoctor() {
		Doctor doctor = createDoctor();
		doctorManager.addDoctor(doctor);
		userDoctors.add(doctor);
		System.out.println("\nYou have been successfully registered");
	}
	
	private static void registerPatient() {
		Patient patient = createPatient();
		patientManager.addPatient(patient);
		userPatients.add(patient);
		System.out.println("\nYou have been successfully registered");
	}
	
	
	
	public static Patient searchPatient(String name) throws Exception {
    	Patient p = null;
    	ListIterator<Patient> iterador= userPatients.listIterator();
        
    	while(iterador.hasNext()){
            Patient p2= iterador.next();
            if(p2.getName().equalsIgnoreCase(name)){
                p=p2;
            }
        }
        if(p == null){
            throw new Exception("No existe esa poblacion.");
        } else{
            return p;
        } 
    }
	
	public static Doctor searchDoctor(String name) throws Exception {
    	Doctor d = null;
    	ListIterator<Doctor> iterador= userDoctors.listIterator();
        
    	while(iterador.hasNext()){
            Doctor d2= iterador.next();
            if(d2.getName().equalsIgnoreCase(name)){
                d = d2;
            }
        }
        if(d == null){
            throw new Exception("No existe esa poblacion.");
        } else{
            return d;
        }
    }

	private static void patientMenu(Patient p) throws Exception{ //METHOD FOR LOGIN SUBSYSTEM
		//TODO implement methods before login subsystem
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
					p.toString();
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
	
	private static void doctorMenu(Doctor d) throws Exception{ //METHOD FOR LOGIN SUBSYSTEM
		//TODO implement methods before login subsystem
		
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
					Patient p = selectPatient(d.getPatients());
					p.toString();
					break;
				case 2:
					d.toString();
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

	
	private static Patient selectPatient(List<Patient> p) throws IOException{
		listPatients(p);
		System.out.println("Introduce the patients id: ");
		int id = getPositiveInteger("");
		
		Patient patient = searchPatient(p, id);
		return patient;
	}
	
	private static Patient searchPatient(List<Patient> patients, int id) {
		Patient p = null;
		ListIterator<Patient> iterator= patients.listIterator();
		
		while(iterator.hasNext()) {
			Patient p2 = iterator.next();
			if(p2.getId() == id) {
				p = p2;
				break;
			}
		}
		
		return p;
	}
	
	private static void listPatients(List<Patient> p) {
		Iterator<Patient> it = p.iterator();
		int counter = 0;
		while (it.hasNext()) {
			 Patient patient = it.next();
			 System.out.println("Id " + counter + ": " + patient.getName());
			 counter++;
		}
	}
	
	
}
