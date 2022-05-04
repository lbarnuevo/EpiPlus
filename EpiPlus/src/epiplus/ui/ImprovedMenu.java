package epiplus.ui;

import java.io.*;
import java.sql.*;
import java.util.*;

import static epiplus.ui.Auxiliar.*;
import epiplus.ifaces.*;
import epiplus.jdbc.*;
import epiplus.pojos.*;

public class ImprovedMenu {
	private static Connection c;
	private static JDBCManager jdbcManager = new JDBCManager();
	
	private static DoctorManager doctorManager = new JDBCDoctorManager(jdbcManager);
	private static PatientManager patientManager = new JDBCPatientManager(jdbcManager);
	
	//THESE VARIABLES ARE TEMPORARY
	private static List<Patient> userPatients = new LinkedList<Patient>();
	private static List<Doctor> userDoctors = new LinkedList<Doctor>();
	
	private static EmergencyContactManager ecManager = new JDBCEmergencyContactManager(jdbcManager);
	private static EpisodeManager episodeManager = new JDBCEpisodeManager(jdbcManager);
	private static EpisodeSymptomManager esManager = new JDBCEpisodeSymptomManager(jdbcManager);
	//private static MedicationManager medicationManager = new JDBCMedicationManager(jdbcManager);
	//private static PatientMedicationManager pmManager = new JDBCPatientMedicationManager(jdbcManager);
	private static SymptomManager symptomManager = new JDBCSymptomManager(jdbcManager);
	
	public static void connect(){
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/EpiPlus(3).db");
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
				
				choice = getPositiveInteger();
				switch(choice) {
					case 1: 
						//TODO loginPatient();
						System.out.println("Enter patient name: ");
						String p_name = getString();
						Patient p = searchPatient(p_name);
						patientMenu(p);
						break;
						
					case 2:
						//TODO loginDoctor();
						System.out.println("Enter doctor name: ");
						String d_name = getString();
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
	
	private static void showPatientMenu() {
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
	}
	
	private static void showDoctorMenu() {
		System.out.println("                  DOCTOR MENU                         ");
	    System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.See data on patient                                       ");
	    System.out.println(" 2.See user information                                      ");
	    System.out.println(" 3.Update user information                                   ");
	    System.out.println(" 0. GO BACK TO MAIN MENU              ");
	    System.out.println("---------------------------------------------------------------");
	}
	
	private static boolean continueProccess() {
		System.out.println("Do you want to continue the process? (Yes -> Y || No -> N): ");
		return askConfirmation();
	}
	
	private static void registerMenu() throws NumberFormatException, IOException {
		System.out.println("                  REGISTER MENU                         ");
	    System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.Register as a doctor                           ");
	    System.out.println(" 2.Register as a patient                                     ");
	    System.out.println(" 0.GO BACK TO MAIN MENU                              ");
	    System.out.println("---------------------------------------------------------------");
	    
	    do {
	    	int choice = getPositiveInteger();
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
	
	
	//TEMPORARY METHODS 
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
            throw new Exception("There is not a patient with that id.");
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
            throw new Exception("There is not a doctor under that name registered.");
        } else{
            return d;
        }
    }

	private static void patientMenu(Patient p) throws Exception{ //METHOD FOR LOGIN SUBSYSTEM
		//TODO implement methods before login subsystem
		
		do {
			showPatientMenu();
			int choice = getPositiveInteger();
			
			switch (choice) {
				case 1:
					registerEpisode();
					break;
				case 2:
					//TODO input new data on medication
					break;
				case 3:
					seeUserPatient(p);
					break;
				case 4: 
					//TODO update user information
					break;
				case 5:
					//TODO Call emergency contacts //How can we call through the database?? I don´t think that is possible 
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
		//BEFORE LOGIN SUBSYSTEM CREATED
		do {			
			showDoctorMenu();
			int choice = getPositiveInteger();
			
			switch (choice) {
				case 1:
					seePatient(d);
					break;
				case 2:
					seeUserDoctor(d);
					break;
				case 3:
					updateUserDoctor(d); 
					break;
				case 0:
					return;
				default:
					break;
				}
		}
		while(true);
	}

	
	private static void seePatient(Doctor d) throws Exception {
		List<Patient> pList = new ArrayList<Patient>();
		
		if (continueProccess() == false) {
			return;
		} else {
			pList = doctorManager.getPatientsOfDoctor(d.getId());

			Patient p = selectPatient(pList);
			seeUserPatient(p);
		}
	}
	
	private static void seeUserPatient(Patient p) {
		p.toString();
		for (EmergencyContact c : ecManager.getEmergencyContactsOfPatient(p.getId())) {
			c.toString();
		}
		for (Episode e : episodeManager.getEpisodesOfPatient(p.getId())) {
			e.toString();
			for (Symptom s : esManager.getSymptomsOfEpisode(e.getId())) {
				s.toString();
			}
		}
		//for (Medication m : medicationManager.getMedicationsOfPatient(p.getId())) {
		//	m.toString();
		//}
		// TODO DEBERÍA VOLVER DESPUÉS A LA LISTA DE NOMBRES Y ID POR SI HAY REPETIDOS Y
		// EL ESCOGIDO NO INTERESABA
	}
	
	/* private static void updateUserPatient(Patient p) {
		if (continueProccess() == false) {
			return; 
		} else {
			while(true) {
				
				name, age, height, weight, lifestyle, diet, exercise, photo
				
				System.out.println("\nShowing user's information... \n");
				d.toString();
				String toChange= getString("\nWhich information (name, email...) would you like to change?: ");
				
				if (toChange.equalsIgnoreCase("name")) {
					String toChangeName= getString("\nInput new NAME: ");
					p.setName(toChangeName);
					patientManager.updatePatient(p); 
				} else if (toChange.equalsIgnoreCase("email")) {
					String toChangeEmail= getString("\nInput new EMAIL: ");
					d.setEmail(toChangeEmail);
					doctorManager.updateDoctor(d);
				} else if (toChange.equalsIgnoreCase("hospitalName")) {
					String toChangeHospitalName= getString("\nInput new HOSPITAL'S NAME: ");
					d.setHospitalName(toChangeHospitalName);
					doctorManager.updateDoctor(d);
				} //else if (toChange.equalsIgnoreCase("photo")) {
					// HOW DO WE IMPORT A PHOTO? FROM A DIRECTORY AS A STRING AND TO AN ARRAY OF BITS?
					//String toChangePhoto= getString("\nInput new PHOTO: ");
					//d.setPhoto(null);;
					//doctorManager.updateDoctor(d);
				//}
				
				if (askConfirmation() == false) {
					break;
				}
			}
		}
	} */
	
	private static void seeUserDoctor(Doctor d) {
		List<Patient> pList = new ArrayList<Patient>();
		
		if (continueProccess() == false) {
			return;
		} else {
			System.out.println("\nShowing user's information... \n");
			d.toString();
			
			System.out.println("--- MY PATIENTS ---");
			pList = doctorManager.getPatientsOfDoctor(d.getId());
			for (Patient patient : pList) {
				patient.toStringForDoctors();
			}
		}
	}
	
	private static void updateUserDoctor(Doctor d) {
		if (continueProccess() == false) {
			return;
		} else {
			System.out.println("\nShowing user's information... \n");
			d.toString();
			System.out.println("Which information (name, email...) would you like to change?: ");
			String toChange= getString();
			
			if (toChange.equalsIgnoreCase("name")) {
				System.out.println("Input new name: ");
				String toChangeName= getString();
				d.setName(toChangeName);
				doctorManager.updateDoctor(d);
			} else if (toChange.equalsIgnoreCase("email")) {
				System.out.println("Input new email: ");
				String toChangeEmail= getString();
				d.setEmail(toChangeEmail);
				doctorManager.updateDoctor(d);
			} else if (toChange.equalsIgnoreCase("hospitalName")) {
				System.out.println("Input new hospital name: ");
				String toChangeHospitalName= getString();
				d.setHospitalName(toChangeHospitalName);
				doctorManager.updateDoctor(d);
			} //else if (toChange.equalsIgnoreCase("photo")) {
				// HOW DO WE IMPORT A PHOTO? FROM A DIRECTORY AS A STRING AND TO AN ARRAY OF BITS?
				//String toChangePhoto= getString("\nInput new PHOTO: ");
				//d.setPhoto(null);;
				//doctorManager.updateDoctor(d);
			//}
		}
	}
	
	private static void registerEpisode() {
		if (continueProccess() == false) {
			return;
		} else {
			Episode ep = createEpisode();
			Symptom symptom = createSymptom();
			EpisodeSymptom epsymp = createSeverity(ep, symptom);
			
			episodeManager.addEpisode(ep);
			symptomManager.addSymptom(symptom);
			esManager.assignEpisodeSymptom(epsymp);
		}
	}
		
	private static Patient selectPatient(List<Patient> p) throws Exception{
		listPatients(p);
		System.out.println("Introduce the patients id: ");
		Integer id = getPositiveInteger();
		
		Patient patient = patientManager.getPatientById(id);
		return patient;
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
