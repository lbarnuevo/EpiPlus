package epiplus.ui;

import java.io.*;
import java.util.*;

import static epiplus.ui.Auxiliar.*;
import epiplus.ifaces.*;
import epiplus.jdbc.*;
import epiplus.jpa.JPAUserManager;
import epiplus.pojos.*;

public class Menu {

	private static JDBCManager jdbcManager = new JDBCManager();

	private static DoctorManager doctorManager = new JDBCDoctorManager(jdbcManager);
	private static PatientManager patientManager = new JDBCPatientManager(jdbcManager);
	private static EmergencyContactManager ecManager = new JDBCEmergencyContactManager(jdbcManager);
	private static EpisodeManager episodeManager = new JDBCEpisodeManager(jdbcManager);
	private static EpisodeSymptomManager esManager = new JDBCEpisodeSymptomManager(jdbcManager);
	private static MedicationManager medicationManager = new JDBCMedicationManager(jdbcManager);
	private static PatientMedicationManager pmManager = new JDBCPatientMedicationManager(jdbcManager);
	private static SymptomManager symptomManager = new JDBCSymptomManager(jdbcManager);
	private static AllergyManager allergyManager = new JDBCAllergyManager(jdbcManager);
	private static PatientAllergyManager paManager = new JDBCPatientAllergyManager(jdbcManager);
	private static UserManager uManager = new JPAUserManager();

	public static void main(String[] args) {
		System.out.println("WELCOME TO EPI+ !!");

		int choice;

		try {
			do {
				showMenu();
				System.out.println("Please choose an option: ");

				choice = getPositiveInteger();
				switch (choice) {
				case 1:
					// TODO loginPatient();
					loginUser("patient");
					System.out.println("Enter patient name: ");
					String p_name = getString();
					Patient p = searchPatient(p_name);
					patientMenu(p);
					break;

				case 2:
					// TODO loginDoctor()
					Doctor d = searchDoctor(1);
					doctorMenu(d);
					break;

				case 3:
					registerMenu();
					break;

				case 0:
					jdbcManager.disconnect();
					System.exit(0);

				default:
					System.out.println("Please introduce a valid option. ");
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static User loginUser(String role) {
		System.out.println("Enter email:");
		String email = getString();
		System.out.println("Enter password:");
		String passwd = getString();
		User u = uManager.checkPassword(email, passwd);

		if (u != null) {
			if (u.getRole().getName().equals(role)) {
				System.out.println("Login as "+ role+ " ran successfully");
		} else {
			System.out.println("User not found in the database. Are you sure you want to login as a "+role+ "?");
		}
		}
		else {
			System.out.println("User not found in the database. Please, register.");
		}
		// ownerMenu(u.getId());
		return u;
	}
	
	// TODO loginUser method --> method for both users
	/*
	 * when login in with the user, we show the menu for the type of user, so maybe
	 * we could add an attribute that consisted of role of user }
	 * 
	 * Maybe when logged in as a patient, reminder of taking medication and notify
	 * how much is left.
	 */

	/* private static void registerUser() { //TODO register method } */

	private static void showMenu() {
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1.Log in as patient                                       ");
		System.out.println(" 2.Log in as doctor                                        ");
		System.out.println(" 3.Register as patient/doctor                                ");
		System.out.println(" 0. EXIT THE PROGRMAM                                        ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void showPatientMenu() {
		System.out.println("                  PATIENT MENU                        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1.Register episode                                          ");
		System.out.println(" 2.My medications                                            ");
		System.out.println(" 3.My episodes                                               ");
		System.out.println(" 4.Show graphs on my evolution NOT DONE                      ");
		// System.out.println(" 4.See my evolution ");
		System.out.println(" 5.Show recipes          NOT DONE YET                        ");
		System.out.println(" 6.Search doctor                                             ");
		System.out.println(" 7.See user information                                      ");
		System.out.println(" 8.Update user information                                   ");
		System.out.println(" 0. GO BACK TO MAIN MENU                                     ");
		System.out.println("---------------------------------------------------------------");
		//IT WOULD BE INTERESTING TO ADD AN OPTION TO CHANGE THE PASSWORD
	}
	
	/*
	private static void changePassword() throws Exception{
 		System.out.println("Please, introduce again your email address:");
 		String email = reader.readLine();
 		System.out.println("Now, please, introduce again your password:");
 		String oldPassword = reader.readLine();
 		System.out.println("Now, please, introduce your new password:");
 		String newPassword = reader.readLine();
 		System.out.println("Are you sure you want to change your password? (YES / NO)");
 		String sure = reader.readLine();
 		if(sure.equalsIgnoreCase("yes")) {
 			umanager.updateUserPassword(email, newPassword, oldPassword);
 		}
 	}
*/
	private static void showDoctorMenu() {
		System.out.println("                  DOCTOR MENU                         ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1.See data on patient                                       ");
		System.out.println(" 2.See user information                                      ");
		System.out.println(" 3.Update user information                                   ");
		System.out.println(" 0. GO BACK TO MAIN MENU                                     ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void showMedsMenu() {
		System.out.println("                  MEDICATION                         ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1.List all my medications                                       ");
		System.out.println(" 2.Input new medication                                       ");
		System.out.println(" 3.Make changes on my medication                              ");
		System.out.println(" 4.Delete medication                                          ");
		System.out.println(" 0. GO BACK TO PATIENT MENU                                   ");
		System.out.println("---------------------------------------------------------------");
	}

	private static void searchDoctorMenu() {
		System.out.println("                                          ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1.Search by name                                       ");
		System.out.println(" 2.Search by email                                      ");
		System.out.println(" 3.Search by hospital                                   ");
		System.out.println(" 0. GO BACK TO PATIENT MENU              ");
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
		} while (true);
	}

	private static void registerDoctor() {
		Doctor doctor = createDoctor();
		doctorManager.addDoctor(doctor);
		System.out.println("\nYou have been successfully registered");
	}

	private static void registerPatient() {
		Patient patient = createPatient();
		patientManager.addPatient(patient);
		System.out.println("\nYou have been successfully registered");
	}

	public static Patient searchPatient(String name) throws Exception {

		List<Patient> patients = patientManager.searchPatientByName(name);
		Patient p = selectPatient(patients);
		listPatients(patients);

		return p;
	}

	public static Doctor searchDoctor(int choice) throws Exception {
		List<Doctor> docs = null;
		Doctor d = null;

		switch (choice) {
		case 1:
			System.out.println("Introduce the doctor's name: ");
			docs = doctorManager.searchDoctorByName(getString());
			listDoctors(docs);

			do {
				System.out.println("Introduce the doctor�s id: ");
				d = doctorManager.getDoctorById(getPositiveInteger());
			} while (d == null);

			break;

		case 2:
			System.out.println("Introduce the email: ");
			d = doctorManager.searchDoctorByEmail(getString());
			if (d == null) {
				System.out.println("There is not a doctor with that id. ");
			} else {
				break;
			}

		case 3: // hospital
			System.out.println("Introduce the hospital�s name: ");
			docs = doctorManager.searchDoctorByHospital(getString());
			listDoctors(docs);

			do {
				System.out.println("Introduce the doctor�s id: ");
				d = doctorManager.getDoctorById(getPositiveInteger());
			} while (d == null);

			break;
		default:
			System.out.println("That option does not exist. ");
		}

		return d;
	}

	private static void patientMenu(Patient p) throws Exception { // METHOD FOR LOGIN SUBSYSTEM

		do {
			showPatientMenu();
			int choice = getPositiveInteger();

			switch (choice) {
			case 1:
				registerEpisode();
				break;
			case 2:
				medicationMenu(p);
				break;
			case 3:
				List<Episode> episodes = episodeManager.getEpisodesOfPatient(p.getId());
				listEpisodes(episodes);
				break;
			case 4:
				showEvolution(p);
				break;
			case 5:
				// TODO show recipes
				break;
			case 6:
				operationsOnDoctor(p);
				break;
			case 7:
				seeUserPatient(p);
				break;
			case 8:
				updateUserPatient(p);
				break;
			case 0:
				return;
			default:
				System.out.println("Please introduce a valid option. ");
			}
		} while (true);
	}

	private static void medicationMenu(Patient p) {
		do {
			showMedsMenu();
			int choice = getPositiveInteger();

			switch (choice) {
			case 1:
				List<Medication> pmeds = pmManager.getMedicationsOfPatient(p.getId());
				listMedications(pmeds);
				break;
			case 2:
				addMedication(p);
				break;
			case 3:
				updateMedication(p);
				break;
			case 4:
				deleteMedication(p);
				break;
			case 0:
				return;
			default:
				System.out.println("Please introduce a valid option. ");
			}
		} while (true);
	}

	private static void doctorMenu(Doctor d) throws Exception { // METHOD FOR LOGIN SUBSYSTEM
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
				System.out.println("Please introduce a valid option. ");
			}
		} while (true);
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

	private static void operationsOnDoctor(Patient p) throws Exception {
		do {
			searchDoctorMenu();
			Doctor d = searchDoctor(getPositiveInteger());

			System.out.println("---------------------------------------------------------------");
			System.out.println(" 1.Show my doctor's profile                                    ");
			System.out.println(" 2.Add as my doctor                                            ");
			System.out.println(" 3.Delete as my doctor                                         ");
			System.out.println(" 0. GO BACK TO PATIENT MENU                                    ");
			System.out.println("---------------------------------------------------------------");

			System.out.println("Introduce the option: ");
			int choice = getPositiveInteger();

			switch (choice) {
			case 1:
				(p.getDoctor()).toString();
				return;
			case 2:
				if (p.getDoctor() == null) {
					p.setDoctor(d);
					patientManager.assignDoctor(p, d);
					return;
				} else {
					System.out.println("You already have a doctor.");
					return;
				}
			case 3:
				if (p.getDoctor() == null) {
					System.out.println("You have not registered him as a doctor.");
					return;
				} else {
					p.setDoctor(null);
					patientManager.unassignDoctor(p, d);
					return;
				}
			case 0:
				return;
			default:
				System.out.println("Please introduce a valid option. ");
			}
		} while (true);
	}

	private static void seeUserPatient(Patient p) {
		System.out.println("Showing user's information...");
		p.toString();

		System.out.println("--- MY EMERGENCY CONTACTS ---");
		for (EmergencyContact c : ecManager.getEmergencyContactsOfPatient(p.getId())) {
			c.toString();
		}
	}

	private static void updateUserPatient(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			while (true) {
				System.out.println("Showing user's information... ");
				seeUserPatient(p);

				System.out.println(
						"Which information would you like to change? \n(you won't be able to change yor date of birth) ");
				String toChange = getString();

				if (toChange.equalsIgnoreCase("name")) {
					System.out.println("Input new NAME: ");
					String toChangeName = getString();
					p.setName(toChangeName);
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("height")) {
					System.out.println("Input new height: ");
					p.setHeight(getPositiveFloat());
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("weight")) {
					System.out.println("Input new weight: ");
					p.setWeight(getPositiveFloat());
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("lifestyle")) {
					System.out.println("Input new lifestyle: ");
					String new_lifestyle = getLifeStyle();
					p.setLifestyle(new_lifestyle);
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("diet")) {
					System.out.println("Input new diet: ");
					String new_diet = getDiet();
					p.setDiet(new_diet);
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("exercise")) {
					System.out.println("Input new amount of exercise per week: ");
					p.setEx_per_week(getPositiveInteger());
					patientManager.updatePatient(p);
				} // TODO change photo

				if (continueProccess() == false) {
					return;
				}
			}
		}
	}

	private static void seeUserDoctor(Doctor d) {
		System.out.println("Showing user's information...");
		d.toString();

		System.out.println("--- MY PATIENTS ---");
		List<Patient> pList = doctorManager.getPatientsOfDoctor(d.getId());
		for (Patient patient : pList) {
			patient.toStringForDoctors();
		}
	}

	private static void updateUserDoctor(Doctor d) {
		if (continueProccess() == false) {
			return;
		} else {
			while (true) {
				System.out.println("\nShowing user's information... \n");
				d.toString();
				System.out.println("Which information (name, email...) would you like to change?: ");
				String toChange = getString();

				if (toChange.equalsIgnoreCase("name")) {
					System.out.println("Input new name: ");
					String toChangeName = getString();
					d.setName(toChangeName);
					doctorManager.updateDoctor(d);
				} else if (toChange.equalsIgnoreCase("email")) {
					System.out.println("Input new email: ");
					String toChangeEmail = getString();
					d.setEmail(toChangeEmail);
					doctorManager.updateDoctor(d);
				} else if (toChange.equalsIgnoreCase("hospitalName")) {
					System.out.println("Input new hospital name: ");
					String toChangeHospitalName = getString();
					d.setHospitalName(toChangeHospitalName);
					doctorManager.updateDoctor(d);
				} // TODO change photo

				if (continueProccess() == false) {
					return;
				}
			}
		}
	}

	private static void registerEpisode() {
		if (continueProccess() == false) {
			return;
		} else {
			Episode ep = createEpisode();
			episodeManager.addEpisode(ep);

			Symptom symptom = createSymptom();
			Symptom s2 = symptomManager.getSymptomByName(symptom.getName());

			if (s2 == null) {
				symptomManager.addSymptom(symptom);
				EpisodeSymptom epsymp = createSeverity(ep, symptom);
				esManager.assignEpisodeSymptom(epsymp);
			} else {
				EpisodeSymptom epsymp = createSeverity(ep, s2);
				esManager.assignEpisodeSymptom(epsymp);
			}
		}
	}

	private static void addMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			Medication med = createMedication();
			Medication med2 = medicationManager.getMedicationByName(med.getName());

			if (med2 == null) {
				PatientMedication pm = createPMed(p, med);

				medicationManager.addMedication(med);
				pmManager.assignPatientMedication(pm);
			} else {
				medicationManager.deleteMedication(med); // because it has been created, in order to avoid duplicates,
															// we delete it
				PatientMedication pm = createPMed(p, med2);
				pmManager.assignPatientMedication(pm);
			}
		}
	}

	private static void showEvolution(Patient p) {
		// We could show the data like this:
		//PRECONDITION: 1 month of data and at least 1 episode recorded
		// 1. Shows all episodes in a month
		// 2. Shows the number of episodes per month using a counter
		// 3. Shows if there is an exercise or meal repeated in the collection ?????
		List<Episode> episodes = episodeManager.getEpisodesOfPatient(p.getId());
		int month = 1;
		int count = 0;
		for (Episode ep : episodes) {
			if (ep.getDoe().getMonth() == month) {
				count++;
				ep.toString();
				for (Symptom s : esManager.getSymptomsOfEpisode(ep.getId())) {
					s.toString();
				}
			} else {
				System.out.println("Number of episodes in month " + month + ": " + count);
				month++;
			}
		}
	}

	private static void updateMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			PatientMedication pmed = selectMedicationFromPatient(p);

			System.out.println("\nShowing medications information... \n");
			pmed.toString();
			System.out.println("Which information would you like to change?: ");
			String toChange = getString();

			if (toChange.equalsIgnoreCase("frequency")) {
				System.out.println("Input new frequency: ");
				pmed.setFrequency(getPositiveInteger());
				pmManager.unassignPatientMedication(pmed);
			} else if (toChange.equalsIgnoreCase("amount")) {
				System.out.println("Input new amount: ");
				pmed.setAmount(getPositiveFloat());
				pmManager.updatePatientMedication(pmed);
			}
		}
	}

	private static PatientMedication selectMedicationFromPatient(Patient p) {
		List<Medication> meds = pmManager.getMedicationsOfPatient(p.getId());
		listMedications(meds);

		Medication med = null;
		do {
			System.out.println("Input the name of the medication: ");
			String namemed = getString();
			med = medicationManager.getMedicationByName(namemed);
		} while (med == null);

		PatientMedication pm = pmManager.getPatientMedication(p, med);
		return pm;
	}

	private static void deleteMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			PatientMedication pm = selectMedicationFromPatient(p);
			pmManager.unassignPatientMedication(pm);
		}
	}

	private static void deleteAllergy(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			PatientAllergy pa = selectAllergyFromPatient(p);
			paManager.unassignPatientAllergy(pa);
		}
	}

	private static PatientAllergy selectAllergyFromPatient(Patient p) {
		List<Allergy> alls = paManager.getAllergiesOfPatient(p.getId());
		listAllergies(alls);

		Allergy all = null;
		do {
			System.out.println("INput the name of the allergy: ");
			String nameall = getString();
			all = allergyManager.getAllergyByName(nameall);
		} while (all == null);

		PatientAllergy pa = paManager.getPatientAllergy(p, all);

		return pa;
	}

	private static Patient selectPatient(List<Patient> p) throws Exception {
		listPatients(p);
		System.out.println("Introduce the patients id: ");
		Integer id = getPositiveInteger();

		Patient patient = patientManager.getPatientById(id);
		return patient;
	}

	private static void listPatients(List<Patient> p) {
		for (Patient pat : p) {
			pat.toStringForDoctors();
		}
	}

	private static void listDoctors(List<Doctor> docs) {
		for (Doctor d : docs) {
			d.toStringForPatients();
		}
	}

	private static void listMedications(List<Medication> meds) {
		for (Medication m : meds) {
			m.toString();
		}
	}

	private static void listEpisodes(List<Episode> episodes) {
		for (Episode e : episodes) {
			e.toString();
			for (Symptom s : esManager.getSymptomsOfEpisode(e.getId())) {
				s.toString();
			}
		}
	}

	private static void listAllergies(List<Allergy> allergies) {
		for (Allergy a : allergies) {
			a.toString();
		}
	}
}
