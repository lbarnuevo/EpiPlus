package epiplus.ui;

import static epiplus.ui.Auxiliar.getIntegerBiggerThanCero;
import static epiplus.ui.Auxiliar.getString;

import java.util.ArrayList;
import java.util.List;

import static epiplus.ui.Auxiliar.askalldocinfo;
import static epiplus.ui.Auxiliar.askdocinfo;
import static epiplus.ui.Auxiliar.askallpatientinfo;
import static epiplus.ui.Auxiliar.askpatientinfo;
import static epiplus.ui.Auxiliar.askconfirmation;
import static epiplus.ui.Auxiliar.askepinfo;
import static epiplus.ui.Auxiliar.asksympinfo;
import static epiplus.ui.Auxiliar.askepsympinfo;

import epiplus.jdbc.JDBCManager;
import epiplus.jdbc.JDBCMedicationManager;
import epiplus.jdbc.JDBCDoctorManager;
import epiplus.jdbc.JDBCEpisodeManager;
import epiplus.jdbc.JDBCEpisodeSymptomManager;
import epiplus.jdbc.JDBCPatientManager;
import epiplus.jdbc.JDBCPatientMedicationManager;
import epiplus.jdbc.JDBCSymptomManager;

import epiplus.ifaces.DoctorManager;
import epiplus.ifaces.PatientManager;
import epiplus.ifaces.SymptomManager;
import epiplus.ifaces.EpisodeManager;
import epiplus.ifaces.EpisodeSymptomManager;
import epiplus.pojos.*;

public class Menu {

	private static JDBCDoctorManager docManager;
	private static JDBCPatientManager patientManager;
	private static JDBCSymptomManager sympManager;
	private static JDBCEpisodeManager epManager;
	private static JDBCEpisodeSymptomManager epsympManager;
	private static JDBCPatientMedicationManager pmedManager;
	private static JDBCMedicationManager medManager;

	private static JDBCManager jdbcManager;

	private static final Integer reiterative = -1;// variable to make a infinite loop

	private static void startMenu() {
		System.out.println("\n\tSTART MENU" + "\n0_Exit program" + "\n1_Log in as a patient" + "\n2_Log in as a doctor"
				+ "\n3_Register");
	}

	private static void registerMenu() {
		System.out.println(
				"\n\tREGISTER MENU" + "\n0_Go back" + "\n1_Register as a doctor" + "\n2_Register as a patient");
	}

	private static void PMenu() {
		System.out.println("\n\tPATIENT MENU" + "\n0_Go back" + "\n1_Register episodes"
				+ "\n2_Input new data on medication" + "\n3_See user's info" + "\n4_Update user's info"
				+ "\n5_Call emergency contacts" + "\n6_See list of medication"
				+ "\n7_Grahps on my evolution (Coming soon)" + "\n8_Search doctor" + "\n9_Recipes");
	}

	private static void DMenu() {
		System.out.println("\n\tDOCTOR MENU" + "\n0_Go back" + "\n1_See data on patient" + "\n2_See user's info"
				+ "\n3_Update user's info");
	}

	private static void patientChoice(/* Integer pId */) {
		epManager = new JDBCEpisodeManager(jdbcManager);
		sympManager = new JDBCSymptomManager(jdbcManager);
		epsympManager = new JDBCEpisodeSymptomManager(jdbcManager);
		pmedManager = new JDBCPatientMedicationManager(jdbcManager);
		medManager = new JDBCMedicationManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);

		Patient patient = patientManager.getPatientById(/* pId */);

		Integer pchoice = reiterative;
		while ((pchoice > 9) || (pchoice < 0)) {
			PMenu();
			pchoice = getIntegerBiggerThanCero("\nSelect an option: ");
			switch (pchoice) {
			case 1: {// REGISTER EPISODES
				System.out.println("\n\tREGISTER EPISODES" + "\nDo you want to continue the process?");
				String register = getString(
						"Press B if you want to go back to the patient menu, other key if you want to continue: ");
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					Episode ep = askepinfo();
					Symptom symptom = asksympinfo();
					epManager.addEpisode(ep);
					sympManager.addSymptom(symptom);

					EpisodeSymptom epsymp = askepsympinfo(ep, symptom);
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
					medManager.listsAllMedication();

					pmedManager.assignPatientMedication(/* pId */);
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

	private static void doctorChoice(/* Integer pId */) {

		List<Patient> pList = new ArrayList<Patient>();

		Integer pchoice = reiterative;
		while ((pchoice > 3) || (pchoice < 0)) {
			PMenu();
			pList = JDBCDoctorManager.getPatientsofDoctor(/*pId*/);
			pchoice = getIntegerBiggerThanCero("\nSelect an option: ");
			
			switch (pchoice) {
			case 1: {// SEE DATA ON PATIENT
				System.out.println("\n\tSEE DATA ON PATIENT" + "\nDo you want to continue the process?");
				String register = getString(
						"Press B if you want to go back to the patient menu, other key if you want to continue: ");
				if (register.equalsIgnoreCase("B")) {
					pchoice = reiterative;
				} else {
					System.out.println("\nChoose a patient to show their data: ");
		
					for (Patient p:pList) {
						System.out.println(p.getName());
						//from the name?
					}
					System.out.println("\nInput the symptom information: ");
					Symptom symptom = asksympinfo();
					EpisodeSymptom epsymp = askepsympinfo(ep, symptom);
					epManager.addEpisode(ep);
					sympManager.addSymptom(symptom);
					epsympManager.assignEpisodeSymptom(epsymp);

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

	public static void main(String[] args) {

		// JDBCManager jdbcManager = new JDBCManager(); --> as atribute of the class
		// because in patientchoice it is used
		docManager = new JDBCDoctorManager(jdbcManager);
		patientManager = new JDBCPatientManager(jdbcManager);
		/*
		 * epManager = new JDBCEpisodeManager(jdbcManager); sympManager = new
		 * JDBCSymptomManager(jdbcManager); change to the function patientchoice
		 */

		/*
		 * wrong menus!! add doctor/patient --> ask to user delete doctor/patient -->
		 * ask to user view doctor/patient --> ask to user who wants see list of
		 * patients/doctors show evolution patient
		 */

		/*
		 * Following his menu of dog hospital(see below): We do not have the database
		 * initialized and the managers declared
		 */

		System.out.println("\nWELCOME TO EPIPLUS!");

		while (true) {
			startMenu();
			Integer optionsm = getIntegerBiggerThanCero("\nSelect an option: ");

			switch (optionsm) {
			case 1: {// LOG IN AS PATIENT

				/*
				 * TODO we have to create login methods Integer pId = loginpatient().....
				 */

				patientChoice(/* pId */);
				break;
			}
			case 2: {// LOG IN AS DOCTOR

				// TODO we have to create login methods
				// Integer dId = logindoctor().....
				doctorChoice(/* dId */);
				break;
			}
			case 3: {// REGISTER DOCTOR/PATIENT
				Integer optionregist = reiterative;
				while ((optionregist > 2) || (optionregist < 0)) {
					registerMenu();
					optionregist = getIntegerBiggerThanCero("\nSelect an option: ");
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
							System.out.print("\nInput Doctor information:");
							System.out.println("\nDo you want to have a photo?? ");
							Boolean confirmation = askconfirmation();
							if (confirmation == true) {// with photo
								doc = askalldocinfo();// not complete, problem with photo
								docManager.addDoctor(doc);
							} else {// without photo
								doc = askdocinfo();
								docManager.addDoctor(doc);
							}
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
							System.out.print("\nInput Patient information:");
							System.out.println("\nDo you want to have a photo?? ");
							Boolean confirmation = askconfirmation();
							if (confirmation == true) {// with photo
								patient = askallpatientinfo();// not complete, problem with photo
								patientManager.addPatient(patient);
							} else {// without photo
								patient = askpatientinfo();
								patientManager.addPatient(patient);
							}
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
