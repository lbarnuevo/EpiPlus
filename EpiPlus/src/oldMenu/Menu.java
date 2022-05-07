package oldMenu;

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
	private static JDBCEmergencyContactManager emergencyManager;

	private static JDBCManager jdbcManager = new JDBCManager();;

	private static final Integer reiterative = -1;// variable to make a infinite loop

	public static void main(String[] args) {

		docManager = new JDBCDoctorManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);

		while (true) {
			startMenu();
			Integer optionsm = getPositiveInteger("\nSelect an option: ");

			switch (optionsm) {
			/*
			 * //BETTER TO CREATE A METHOD COMMON TO LOG IN AND USING THE ROLES CHOOSE WICH
			 * ONE ARE case 1: {// LOG IN AS PATIENT //TODO we have to create login methods
			 * Integer pId = loginpatient()..... patientChoice(/* pId ); break; } case 2:
			 * {// LOG IN AS DOCTOR // TODO we have to create login methods // Integer dId =
			 * logindoctor()..... doctorChoice(/* dId ); break; }
			 */

			case 3: {// REGISTER DOCTOR/PATIENT
				// TODO CREATE A METHOD TO REGISTER
				Integer optionregist = reiterative;

				while ((optionregist > 2) || (optionregist < 0)) {
					registerMenu();
					optionregist = getPositiveInteger("\nSelect an option: ");

					switch (optionregist) {
					case 1: {// REGISTER DOCTOR
						Doctor doc;
						System.out.println("\n\tREGISTER AS DOCTOR" + "\nDo you want to continue the process?");
						String register = getString(
								"Press B if you want to go back to the register menu, other key if you want to continue: ");

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
						String register = getString(
								"Press B if you want to go back to the register menu, other key if you want to continue: ");

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

	public static void connect() {
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
		System.out.println(" 1. Log in as a patient                                       ");
		System.out.println(" 2. Log in as a doctor                                        ");
		System.out.println(" 3. Register as patient/doctor                                ");
		System.out.println(" 0. EXIT THE PROGRMAM                                        ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void registerMenu() {
		System.out.println("                  REGISTER MENU                         ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1. Register as a doctor                           ");
		System.out.println(" 2. Register as a patient                                     ");
		System.out.println(" 0. GO BACK TO MAIN MENU                              ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void PMenu() {
		System.out.println("                  PATIENT MENU                        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1. Register an episode                                          ");
		System.out.println(" 2. Input new data on medication                              ");
		System.out.println(" 3. See user's information                                      ");
		System.out.println(" 4. Update user's information                                   ");
		System.out.println(" 5. Call my emergency contacts                                   ");
		System.out.println(" 6. See list of my medications                                   ");
		System.out.println(" 7. Show graphs on my evolution                               ");
		System.out.println(" 8. Search doctor                                             ");
		System.out.println(" 9. Show recipes                                               ");
		System.out.println(" 0. GO BACK TO MAIN MENU                                       ");
		System.out.println("---------------------------------------------------------------");
	}
	
	private static void MMenu() {
		System.out.println("                  MEDICATION MENU                         ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1. Change medication                           ");
		System.out.println(" 2. Add medication                                     ");
		System.out.println(" 3. Delete medication                                     ");//in the mock up we didn't have delete med so i added
		System.out.println(" 0. GO BACK TO PATIENT MENU                              ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void DMenu() {
		System.out.println("                  DOCTOR MENU                         ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1. See data on patient                                       ");
		System.out.println(" 2. See user's information                                      ");
		System.out.println(" 3. Update user's information 									");
		// CAN A DOCTOR DELETE A PATIENT? NO, RIGHT?
		System.out.println(" 0. GO BACK TO MAIN MENU              ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void patientChoice(Integer pId) {
		epManager = new JDBCEpisodeManager(jdbcManager);
		sympManager = new JDBCSymptomManager(jdbcManager);
		epsympManager = new JDBCEpisodeSymptomManager(jdbcManager);
		pmedManager = new JDBCPatientMedicationManager(jdbcManager);
		medManager = new JDBCMedicationManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);
		docManager = new JDBCDoctorManager(jdbcManager);		

		Patient patient = patientManager.getPatientById(pId);

		List<Medication> listMed;
		
		Medication med = null;
		PatientMedication pmed = null;

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
					epManager.addEpisode(ep);
					
					Symptom symptom = createSymptom();
					Symptom s2 = sympManager.getSymptomByName(symptom.getName()); //It check if the symptom exist on the database
					
					if(s2 == null) {
						sympManager.addSymptom(symptom);
						EpisodeSymptom epsymp = createSeverity(ep, symptom);
						epsympManager.assignEpisodeSymptom(epsymp);
					} else {
						EpisodeSymptom epsymp = createSeverity(ep, s2);
						epsympManager.assignEpisodeSymptom(epsymp);
					}
				}
				break;
			}

			case 2: {//INPUT NEW DATA ON MEDICATION
				System.out.println("\n\tCHANGE MEDICATION" + "\nDo you want to continue the process?");
				String register = getString(
						"Press B if you want to go back to the patient menu, other key if you want to continue: ");
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					listMed = pmedManager.getMedicationsOfPatient(pId);
					for (Medication m : listMed) {
						System.out.println("\n" + m);
					}
					
					//the following code should be in another function to understand the code better

					MMenu();
					System.out.println("Please, select the option: ");
					int option = getPositiveInteger();
					switch(option) {
					case 0:{
						break;
					}
					case 1:{//MAKE CHANGES
						System.out.println("Wich medication do you want to change? ");
						String namemed = getString();
						med = medManager.getMedicationByName(namemed);
						
						System.out.println("Input new frequency: ");
						Integer freq = getPositiveInteger();
						System.out.println("Input new amount: ");
				    	Float amount = getPositiveFloat();
						pmed = new PatientMedication(freq, amount, patient, med);

						pmedManager.updatePatientMedication(pmed);
						break;
					}
					case 2:{//ADD MED
						System.out.println("Add new medication: ");
						
						med = createMedication();
						
						Medication med2 = medManager.getMedicationByName(med.getName());//It search if there is a medication with that name, if it already exist in the database
						
						if(med2 == null) {//if it is null --> add the med previously created and all the patientmedications relations
							pmed = createPMed(patient, med);
							medManager.addMedication(med);
							pmedManager.assignPatientMedication(pmed);
						} else {//if it already exist create only the related thing to patientmedication
							pmed = createPMed(patient, med2);
							pmedManager.assignPatientMedication(pmed);
						}
						break;
					}
					case 3:{//DELETE
						System.out.println("Wich medication do you want to delete?");
						String namemed = getString();
						Medication deletemed = medManager.getMedicationByName(namemed);
						pmed = new PatientMedication(patient, deletemed); //Im not so sure
						pmedManager.unassignPatientMedication(pmed);
						break;
					}
					}
				}
				break;
			}

			case 3: {// SHOW PATIENT INFO
				// use the same function for patient and doctor
				System.out.println("\n" + patient);
				// USE toStringForPatients() TO SHOW DATA OF DOCTOR (no photo...)
				break;
			}
			case 4: {//UPDATE PATIENT INFO
				System.out.println("Press B if you want to go back to the patient menu, other key if you want to continue: ");
				String register = getString();
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					System.out.println("UPDATE USER INFO");
					System.out.println("\n" + patient);
					
					System.out.println("\nWhat data do you want to change?\n"
							+ "	0_Finish changes\n"
							+ "	1_name\n"
							+ "	2_age\n"
							+ "	3_height\n"
							+ "	4_weight\n"
							+ "	5_lifestyle\n"
							+ "	6_diet\n"
							+ "	7_ex_per_week\n"
							+ "	8_photo (comming soon)");//TODO --> i dont know how to do it
					Integer option = getPositiveInteger();
					while(true) {
						switch(option) {
						case 0:{
							patientManager.updatePatient(patient);
							return;
							}
						case 1:{
							String newname = getString();
							patient.setName(newname);
							break;
						}
						case 2:{
							Integer newage = getPositiveInteger();
							patient.setAge(newage);
							break;
						}
						case 3:{
							Float newheight = getPositiveFloat();
							patient.setHeight(newheight);
							break;
						}
						case 4:{
							Float newweight = getPositiveFloat();
							patient.setWeight(newweight);
							break;
						}
						case 5:{
							String newlifestyle = getString();
							patient.setLifestyle(newlifestyle);
							break;
						}
						case 6:{
							String newdiet = getString();
							patient.setDiet(newdiet);
							break;
							}
						case 7:{
							Integer newexweek = getPositiveInteger();
							patient.setEx_per_week(newexweek);
							break;
						}
						default:{
							System.out.println("Input correct option");
						}
						}
					}
				}
				break;
			}
			case 5:{//CALL EMERGENCY CONTACTS --> TODO --> how?
				break;
			}
			case 6:{//SEE LIST OF MEDS
				listMed = pmedManager.getMedicationsOfPatient(pId);
				for (Medication m : listMed) {
					System.out.println("\n" + m);
				}
				break;
			}
			case 7:{//SEE GRAPHS --> TODO --> but we also said that we should do it at the end of our project
				patientManager.showEvolution(patient);//not implemented!!
				break;
			}
			case 8:{//SEARCH DOCTOR --> TODO
				//1_search a doctor by name email or hospital 2a_add the doctor to the patient and the patient to the doctor  or 2b_see info doctor
				Doctor doctor = null;
				List<Doctor> listDoc;

				System.out.println("SEE DOCTORS INFO");
				while(true) {
					System.out.println("\n1_Search by name");
					System.out.println("\n2_Search by email");
					System.out.println("\n3_Search by hospital");
					System.out.println("\n0_Exit");
					Integer option = getPositiveInteger();
					switch(option) {
					case 0:{
						return;
					}
					/*TODO
					In patient we have an atribute that is Doctor but we dont do anythig with it. 
					We should: 
						a) added to the contructor as a variable that is originally null and change the data base adding that new variable
						b) deleted the vairable from patient*/
					
					case 1:{//NAME
						//PRINTS THE DOCTORS BY WITH THE SAME NAME
						String name = getString();
						listDoc = docManager.searchDoctorByName(name);
						for (Doctor d : listDoc) {
							System.out.println("\n" + d.toStringForPatients());
						}
						//ASK IF THE PATIENT WANT TO CHANGE THE DOCTOR
						System.out.println("\nDo you want to change your actual doctor? (N--> No)");
						String c = getString();
						if (c.equalsIgnoreCase("N")) {
							return;
						} else {
							
							/*PRINT THE DOCTOR ASOCIATED WITH THA PATIENT, ASK FOR THE ID OF THE NEW DOCTOR, ADD THE DOCTOR TO THE PATIENT 
							  & FINALLY ADD THIS PATIENT TO THE LIST OF DOCTORS OF THE DOCTOR SELECTED*/ 
							
							System.out.println("\nActual doctor: "+patient.getDoctor().toStringForPatients()
									+ "\nWich doctor do you want to add? (You only can have one doctor asociated)(Input the doctor Id)");
							Integer DId = getPositiveInteger();
							doctor = docManager.getDoctorById(DId);
							patient.setDoctor(doctor);
							doctor.addPatienttoList(patient);
							
						}
						break;
					}
					default:{
						System.out.println("Enter other number: ");
					}
					}
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

	// private static

	private static void doctorChoice(Integer dId) {

		epManager = new JDBCEpisodeManager(jdbcManager);
		epsympManager = new JDBCEpisodeSymptomManager(jdbcManager);
		pmedManager = new JDBCPatientMedicationManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);
		emergencyManager = new JDBCEmergencyContactManager(jdbcManager);
		List<Patient> pList = new ArrayList<Patient>();

		Integer pchoice = reiterative;

		while ((pchoice > 3) || (pchoice < 0)) {
			DMenu();
			
			pchoice = getPositiveInteger("\nSelect an option: ");
			switch (pchoice) {
			case 1: {// SEE DATA ON PATIENT
				System.out.println("\n\tSEE DATA ON PATIENT" + "\nDo you want to continue the process?");
				String register = getString(
						"Press B if you want to go back to the patient menu, other key if you want to continue: ");
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					pList = docManager.getPatientsOfDoctor(dId);
					
					
					System.out.println("\nChoose a patient to show their data:");
					for (Patient p : pList) {
						p.toStringForDoctors();
					}
					
					Integer pIdChosen = getPositiveInteger("\nWrite the number above their name: ");
					Patient p = patientManager.getPatientById(pIdChosen);
					p.toString();
					for (EmergencyContact c : emergencyManager.getEmergencyContactsOfPatient(pIdChosen)) {
						c.toString();
					}
					for (Episode e : epManager.getEpisodesOfPatient(pIdChosen)) {
						e.toString();
						for (Symptom s : epsympManager.getSymptomsOfEpisode(e.getId())) {
							s.toString();
						}
					}
					for (Medication m : pmedManager.getMedicationsOfPatient(pIdChosen)) {
						m.toString();
					}
					// TODO DEBERÍA VOLVER DESPUÉS A LA LISTA DE NOMBRES Y ID POR SI HAY REPETIDOS Y
					// EL ESCOGIDO NO INTERESABA
				}
				break;
			}
			
			case 2: {// SEE USER'S INFO
				System.out.println("\n\tSEE USER'S INFO" + "\nDo you want to continue the process?");
				String register = getString(
						"Press B if you want to go back to the patient menu, other key if you want to continue: ");
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					Doctor doctor = docManager.getDoctorById(dId);
					System.out.println("\nShowing user's information... \n");
					doctor.toString();
					pList = docManager.getPatientsOfDoctor( dId );
					for (Patient p : pList) {
						p.toStringForDoctors();
					}
				}
				break;
			}
			case 3:{// UPDATE USER'S INFO
				System.out.println("\n\tUPDATE USER'S INFO" + "\nDo you want to continue the process?");
				String register = getString(
						"Press B if you want to go back to the patient menu, other key if you want to continue: ");
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					Doctor doctor = docManager.getDoctorById(dId);
					while(true) {
					System.out.println("\nShowing user's information... \n");
					doctor.toString();
					String toChange= getString("\nWhich information (name, email...) would you like to change?: ");
					if (toChange.equalsIgnoreCase("name")) {
						String toChangeName= getString("\nInput new NAME: ");
						doctor.setName(toChangeName);
						docManager.updateDoctor(doctor);
					} else if (toChange.equalsIgnoreCase("email")) {
						String toChangeEmail= getString("\nInput new EMAIL: ");
						doctor.setEmail(toChangeEmail);
						docManager.updateDoctor(doctor);
					} else if (toChange.equalsIgnoreCase("hospitalName")) {
						String toChangeHospitalName= getString("\nInput new HOSPITAL'S NAME: ");
						doctor.setHospitalName(toChangeHospitalName);
						docManager.updateDoctor(doctor);
					} else if (toChange.equalsIgnoreCase("photo")) {
						// HOW DO WE IMPORT A PHOTO? FROM A DIRECTORY AS A STRING AND TO AN ARRAY OF BITS?
						String toChangePhoto= getString("\nInput new PHOTO: ");
						doctor.setPhoto(null);;
						docManager.updateDoctor(doctor);
				}
					String toContinue=getString("\nWould you like to make any more changes? (Y/N): ");
				if (!toContinue.equalsIgnoreCase("Y")) {
					break;
				}
			}
			break;
			}
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
