package epiplus.ui;

import static epiplus.ui.Auxiliar.getPositiveInteger;
import static epiplus.ui.Auxiliar.getString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import static epiplus.ui.Auxiliar.*;
import epiplus.jdbc.*;
import epiplus.ifaces.*;
import epiplus.pojos.*;

public class Menu {

	private static Connection c;
	
	private static JDBCDoctorManager docManager;
	private static JDBCPatientManager patientManager;
	private static JDBCSymptomManager sympManager;
	private static JDBCEpisodeManager epManager;
	private static JDBCEpisodeSymptomManager epsympManager;
	private static JDBCPatientMedicationManager pmedManager;
	private static JDBCMedicationManager medManager;
	
	private static JDBCManager jdbcManager = new JDBCManager();
;
	
	private static final Integer reiterative = -1;// variable to make a infinite loop
			
	public static void main(String[] args) {
	
		docManager = new JDBCDoctorManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);
	
		while (true) {
			startMenu();
			Integer optionsm = getPositiveInteger("\nSelect an option: ");
	
			switch (optionsm) {
				/*//BETTER TO CREATE A METHOD COMMON TO LOG IN AND USING THE ROLES CHOOSE WICH ONE ARE
				 case 1: {// LOG IN AS PATIENT
					//TODO we have to create login methods Integer pId = loginpatient().....
					patientChoice(/* pId );
					break;
				}
				case 2: {// LOG IN AS DOCTOR
					// TODO we have to create login methods
					// Integer dId = logindoctor().....
					doctorChoice(/* dId );
					break;
				}*/
				
				case 3: {// REGISTER DOCTOR/PATIENT
					//TODO CREATE A METHOD TO REGISTER
					Integer optionregist = reiterative;
					
					while ((optionregist > 2) || (optionregist < 0)) {
						registerMenu();
						optionregist = getPositiveInteger("\nSelect an option: ");
						
						switch (optionregist) {
							case 1: {// REGISTER DOCTOR
								Doctor doc;
								System.out.println("\n\tREGISTER AS DOCTOR" + "\nDo you want to continue the process?");
								String register = getString("Press B if you want to go back to the register menu, other key if you want to continue: ");
								
								if (register.equalsIgnoreCase("B")) {
									optionregist = reiterative;
									// break;
								} else {
									doc = createDoctor();
									docManager.addDoctor(doc);							
									System.out.println("\nYou have been successfully registered");
								}
								
								break;
							}
							
							case 2: {// REGISTER PATIENT
								Patient patient;
								System.out.println("\n\tREGISTER AS PATIENT" + "\nDo you want to continue the process?");
								String register = getString("Press B if you want to go back to the register menu, other key if you want to continue: ");
								
								if (register.equalsIgnoreCase("B")) {
									optionregist = reiterative;
									// break;
								} else {							
									patient = createPatient();
									patientManager.addPatient(patient);							
									System.out.println("\nYou have been successfully registered");
								}
								
								break;
							}
							
							case 0: {
								
								break;
							}
							
							default: {
								System.out.println("\nPlease enter a correct number: ");
							}
						}
					}
					
					break;
				}
			
				case 0: {
					System.out.println("\nYou have exited the app");
					System.exit(0);
				}
			
				default: {
					System.out.println("\nPlease enter a correct number: ");
				}
			}
		}
	}
	
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
	
	private static void startMenu() {
		System.out.println("                  	WELCOME TO EPI+!                        ");
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
	
	private static void PMenu() {
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
	
	private static void DMenu() {
		System.out.println("                  DOCTOR MENU                         ");
	    System.out.println("---------------------------------------------------------------");
	    System.out.println(" 1.See data on patient                                       ");
	    System.out.println(" 2.See user information                                      ");
	    System.out.println(" 3.Update user information                                   ");
	    System.out.println(" 0. GO BACK TO MAIN MENU              ");
	    System.out.println("---------------------------------------------------------------");
	}
	
	private static void patientChoice(/* Integer pId */) {
		epManager = new JDBCEpisodeManager(jdbcManager);
		sympManager = new JDBCSymptomManager(jdbcManager);
		epsympManager = new JDBCEpisodeSymptomManager(jdbcManager);
		pmedManager = new JDBCPatientMedicationManager(jdbcManager);
		medManager = new JDBCMedicationManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);
	
		Patient patient = patientManager.getPatientById(/* pId */);
		
		List<Medication> listMed;
		
		Integer pchoice = reiterative;
		
		while ((pchoice > 9) || (pchoice < 0)) {
			PMenu();
			pchoice = getPositiveInteger("\nSelect an option: ");
			
			switch (pchoice) {
				case 1: {// REGISTER EPISODES
					System.out.println("\n\tREGISTER EPISODES" + "\nDo you want to continue the process?");
					String register = getString(
							"Press B if you want to go back to the patient menu, other key if you want to continue: ");
					if (register.equalsIgnoreCase("B")) {
						pchoice = reiterative;
					} else {
						Episode ep = createEpisode();
						Symptom symptom = createSymptom();
						epManager.addEpisode(ep);
						sympManager.addSymptom(symptom);
		
						EpisodeSymptom epsymp = createSeverity(ep, symptom);
						epsympManager.assignEpisodeSymptom(epsymp);
					}
					break;
				}
				
				case 2: {// TODO INPUT NEW DATA ON MEDICATION --> not done yet
					System.out.println("\n\tCHANGE MEDICATION" + "\nDo you want to continue the process?");
					String register = getString(
							"Press B if you want to go back to the patient menu, other key if you want to continue: ");
					if (register.equalsIgnoreCase("B")) {
						pchoice = reiterative;
					} else {
						// show list medication
						listMed = medManager.listsAllMedication();
						for(Medication m:listMed) {
							System.out.println("\n"+m);
						}
		
						//pmedManager.assignPatientMedication(/* pId */);
					}
				}
				
				case 3: {// SHOW PATIENT INFO
					// use the same function for patient and doctor
					System.out.println("\n" + patient);
					
				}
	
				case 0: {// GO BACK TO START MENU
					break;
				}
				
				default: {
					System.out.println("\nPlease enter a correct number: ");
				}
			}
		}
	}
	
	//private static 
	
	private static void doctorChoice(/* Integer dId */) {
		
		List<Patient> pList = new ArrayList<Patient>();
	
		Integer pchoice = reiterative;
		
		while ((pchoice > 3) || (pchoice < 0)) {
			PMenu();
			
			pchoice = getPositiveInteger("\nSelect an option: ");
			switch (pchoice) {
				case 1: {// SEE DATA ON PATIENT
					
					System.out.println("\n\tSEE DATA ON PATIENT" + "\nDo you want to continue the process?");
					String register = getString(
							"Press B if you want to go back to the patient menu, other key if you want to continue: ");
					if (register.equalsIgnoreCase("B")) {
						pchoice = reiterative;
					} else {
						pList = JDBCDoctorManager.getPatientsofDoctor(/*dId*/);
						
						System.out.println("\nChoose a patient to show their data:");
						for (Patient p:pList) {
							System.out.println(p.getId()+ "\n" + p.getName());
						}
						Integer pIdChosen= getPositiveInteger("\nWrite the number above their name: ");
						Patient p= patientManager.getPatientById(pIdChosen);	
						p.toString();
					}
					break;
				}
	
				case 0: {// GO BACK TO START MENU
					break;
				}
				
				default: {
					System.out.println("\nPlease enter a correct number: ");
				}
			}
		}
	}
}

//{
/*
 * package hospital.ui;
 * 
 * import java.io.BufferedReader; import java.io.InputStreamReader;
 * 
 * import hospital.ifaces.DogManager; import hospital.ifaces.OwnerManager;
 * import hospital.ifaces.VetManager; import hospital.jdbc.JDBCDogManager;
 * import hospital.jdbc.JDBCManager; import hospital.jdbc.JDBCOwnerManager;
 * import hospital.jdbc.JDBCVetManager; import hospital.pojos.Owner; import
 * hospital.pojos.Vet;
 */

/*
 * public class Menu {
 * 
 * private static BufferedReader reader = new BufferedReader(new
 * InputStreamReader(System.in)); private static DogManager dogManager; private
 * static OwnerManager ownerManager; private static VetManager vetManager;
 * 
 * public static void main(String[] args) {
 * System.out.println("Welcome to the Dog Hospital"); // Initialize database
 * JDBCManager jdbcManager = new JDBCManager(); dogManager = new
 * JDBCDogManager(jdbcManager); ownerManager = new
 * JDBCOwnerManager(jdbcManager); vetManager = new JDBCVetManager(jdbcManager);
 * // Menu loop try { do { System.out.println("Please choose an option:");
 * System.out.println("1. Choose an owner");
 * System.out.println("2. Create a new owner");
 * System.out.println("3. Choose a vet");
 * System.out.println("4. Create a new vet"); System.out.println("0. Exit"); int
 * choice = Integer.parseInt(reader.readLine()); switch (choice) { case 1:
 * chooseOwner(); break; case 2: createOwner(); break; case 3: chooseVet();
 * break; case 4: createVet(); break; case 0: // Close the connection with the
 * database jdbcManager.disconnect(); System.exit(0); default: break; }
 * 
 * } while (true); } catch (Exception e) { e.printStackTrace(); } }
 * 
 * public static void chooseOwner() throws Exception {
 * System.out.println("Please choose an owner, type its ID:");
 * System.out.println(ownerManager.listAllOwners()); Integer ownerId =
 * Integer.parseInt(reader.readLine()); ownerMenu(ownerId); }
 * 
 * public static void createOwner() throws Exception { // Ask for user data
 * System.out.println("Please type your data:"); System.out.println("Name:");
 * String name = reader.readLine(); System.out.println("Phone:"); Integer phone
 * = Integer.parseInt(reader.readLine()); System.out.println("Email:"); String
 * email = reader.readLine(); System.out.println("Card Number:"); Integer
 * cardNumber = Integer.parseInt(reader.readLine()); Owner o = new Owner(name,
 * phone, email, cardNumber); // Call ownerManager.createOwner(Owner o)
 * ownerManager.createOwner(o); // Go back }
 * 
 * public static void chooseVet() throws Exception {
 * System.out.println("Please choose a vet, type its ID:");
 * System.out.println(vetManager.listAllVets()); Integer vetId =
 * Integer.parseInt(reader.readLine()); vetMenu(vetId); }
 * 
 * public static void createVet() throws Exception { // Ask for vet data
 * System.out.println("Please type your data:"); System.out.println("Name:");
 * String name = reader.readLine(); System.out.println("Speciality:"); String
 * speciality = reader.readLine(); Vet v = new Vet(name, speciality); // Call
 * vetManager.createVet(Vet v) vetManager.createVet(v); // Go back }
 * 
 * public static void ownerMenu(Integer oID) throws Exception { // TODO List
 * options for: // 1. Leave at the hospital (create) a new dog // 2. List my
 * dogs // 1. Select one dog and, if cured, ask if I want to retrieve (remove)
 * them // 3. Change my data // 0. Go Back }
 * 
 * public static void vetMenu(Integer vID) throws Exception { // TODO List
 * options for: // 1. List all dogs (show which vets treat them) // 1. Select
 * one dog and treat them // 0. Go back // 2. List all dogs without any vets
 * treating them // 1. Select one dog and treat them // 0. Go back // 3. Check
 * on dogs I treat // 1. Cure dog // 2. Stop treating dog // 0. Go back // 0. Go
 * Back }
 */

//}
