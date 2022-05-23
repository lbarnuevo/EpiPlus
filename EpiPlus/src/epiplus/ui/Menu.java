package epiplus.ui;

import java.io.*;
import java.util.*;

import static epiplus.ui.Auxiliar.*;
import epiplus.ifaces.*;
import epiplus.jdbc.*;
import epiplus.jpa.JPAUserManager;
import epiplus.pojos.*;
import epiplus.graphics.*;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
	// private static UserManager uManager = new JPAUserManager();

	public static void main(String[] args) {
		System.out.println("WELCOME TO EPI+ !!");

		int choice;

		try {
			do {
				showMenu();
				System.out.println("Please choose an option: ");

				choice = getPositiveInteger(reader);
				switch (choice) {
				case 1:
					// TODO loginPatient();
					// loginUser("patient");
					System.out.println("Enter patient name: ");
					String p_name = getString(reader);
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

	/*
	 * public static User loginUser(String role) {
	 * System.out.println("Enter email:"); String email = getString(reader);
	 * System.out.println("Enter password:"); String passwd = getString(reader);
	 * User u = uManager.checkPassword(email, passwd);
	 * 
	 * if (u != null) { if (u.getRole().getName().equals(role)) {
	 * System.out.println("Login as "+ role+ " ran successfully"); } else {
	 * System.out.
	 * println("User not found in the database. Are you sure you want to login as a "
	 * +role+ "?"); } } else {
	 * System.out.println("User not found in the database. Please, register."); } //
	 * ownerMenu(u.getId()); return u; }
	 */

	// TODO loginUser method --> method for both users
	/*
	 * when login in with the user, we show the menu for the type of user, so maybe
	 * we could add an attribute that consisted of role of user }
	 * 
	 * Maybe when logged in as a patient, reminder of taking medication (show the
	 * frequency) and notify how much is left.
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
		System.out.println(" 0. LOG OUT                                                  ");
		System.out.println("---------------------------------------------------------------");
		// IT WOULD BE INTERESTING TO ADD AN OPTION TO CHANGE THE PASSWORD
	}

	// TODO good idea
	/*
	 * private static void changePassword() throws Exception{
	 * System.out.println("Please, introduce again your email address:"); String
	 * email = reader.readLine();
	 * System.out.println("Now, please, introduce again your password:"); String
	 * oldPassword = reader.readLine();
	 * System.out.println("Now, please, introduce your new password:"); String
	 * newPassword = reader.readLine(); System.out.
	 * println("Are you sure you want to change your password? (YES / NO)"); String
	 * sure = reader.readLine(); if(sure.equalsIgnoreCase("yes")) {
	 * umanager.updateUserPassword(email, newPassword, oldPassword); } }
	 */

	private static void showDoctorMenu() {
		System.out.println("                  DOCTOR MENU                         ");
		System.out.println("---------------------------------------------------------------");
		System.out.println(" 1.See data on patient                                       ");
		// + See evolution on patient
		System.out.println(" 2.See user information                                      ");
		System.out.println(" 3.Update user information                                   ");
		System.out.println(" 0. LOG OUT                                                  ");
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
		return askConfirmation(reader);
	}

	private static void registerMenu() throws NumberFormatException, IOException {
		do {
			System.out.println("                  REGISTER MENU                         ");
			System.out.println("---------------------------------------------------------------");
			System.out.println(" 1.Register as a doctor                           ");
			System.out.println(" 2.Register as a patient                                     ");
			System.out.println(" 0.GO BACK TO MAIN MENU                              ");
			System.out.println("---------------------------------------------------------------");

			int choice = getPositiveInteger(reader);
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

	private static void registerDoctor() throws IOException {
		Doctor doctor = createDoctor(reader);
		doctorManager.addDoctor(doctor);
		System.out.println("\nYou have been successfully registered");
	}

	private static void registerPatient() throws IOException {
		Patient patient = createPatient(reader);
		patientManager.addPatient(patient);

		/*
		 * List<EmergencyContact> listContacts = null; >>>>>>> branch 'master' of
		 * https://github.com/lbarnuevo/EpiPlus int stop1 = 1; while (stop1 != 0) {
		 * EmergencyContact contact = createEmergencyContacts(reader, patient);
		 * ecManager.addEmergencyContact(contact); // CREO QUE HAY UNA FUNCIÓN
		 * ESPECÍFICA CREADA PARA ESTO: System.out.
		 * println("Emergency contact added. Press '0' if finished, other key if you want to continue."
		 * ); stop1 = getPositiveInteger(reader); } int stop2 = 1; while (stop2 != 0) {
		 * addAllergy(patient); // CREO QUE HAY UNA FUNCIÓN ESPECÍFICA CREADA PARA ESTO:
		 * System.out.
		 * println("Allergy added. Press '0' if finished, other key if you want to continue."
		 * ); stop2 = getPositiveInteger(reader); }
		 */

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
			docs = doctorManager.searchDoctorByName(getString(reader));
			listDoctors(docs);

			do {
				System.out.println("Introduce the doctor´s id: ");
				d = doctorManager.getDoctorById(getPositiveInteger(reader));
			} while (d == null);

			break;

		case 2:
			System.out.println("Introduce the email: ");
			d = doctorManager.searchDoctorByEmail(getString(reader));
			if (d == null) {
				System.out.println("There is not a doctor with that id. ");
			} else {
				break;
			}

		case 3: // hospital
			System.out.println("Introduce the hospital´s name: ");
			docs = doctorManager.searchDoctorByHospital(getString(reader));
			listDoctors(docs);

			do {
				System.out.println("Introduce the doctor´s id: ");
				d = doctorManager.getDoctorById(getPositiveInteger(reader));
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
			int choice = getPositiveInteger(reader);

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
				//chooseRecipe(p);
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
			int choice = getPositiveInteger(reader);

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
			int choice = getPositiveInteger(reader);

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
			Doctor d = searchDoctor(getPositiveInteger(reader));

			System.out.println("---------------------------------------------------------------");
			System.out.println(" 1.Show my doctor's profile                                    ");
			System.out.println(" 2.Add as my doctor                                            ");
			System.out.println(" 3.Delete as my doctor                                         ");
			System.out.println(" 0. GO BACK TO PATIENT MENU                                    ");
			System.out.println("---------------------------------------------------------------");

			System.out.println("Introduce the option: ");
			int choice = getPositiveInteger(reader);

			switch (choice) {
			case 1:
				System.out.println((p.getDoctor()).toString());
				if (p.getDoctor().getPhoto() != null) {
					ByteArrayInputStream blobIn = new ByteArrayInputStream(p.getDoctor().getPhoto());
					ImageWindow window = new ImageWindow();
					window.showBlob(blobIn);
				}
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
		System.out.println(p.toString());

		System.out.println("--- MY EMERGENCY CONTACTS ---");
		for (EmergencyContact c : ecManager.getEmergencyContactsOfPatient(p.getId())) {
			System.out.println(c.toString());
		}

		if (p.getPhoto() != null) {
			ByteArrayInputStream blobIn = new ByteArrayInputStream(p.getPhoto());
			ImageWindow window = new ImageWindow();
			window.showBlob(blobIn);
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
						"Which information would you like to change? \n(you won't be able to change yor date of birth or email) ");
				String toChange = getString(reader);

				if (toChange.equalsIgnoreCase("name")) {
					System.out.println("Input new NAME: ");
					String toChangeName = getString(reader);
					p.setName(toChangeName);
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("height")) {
					System.out.println("Input new height: ");
					p.setHeight(getPositiveFloat(reader));
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("weight")) {
					System.out.println("Input new weight: ");
					p.setWeight(getPositiveFloat(reader));
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("lifestyle")) {
					System.out.println("Input new lifestyle: ");
					String new_lifestyle = getLifeStyle(reader);
					p.setLifestyle(new_lifestyle);
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("diet")) {
					System.out.println("Input new diet: ");
					String new_diet = getDiet(reader);
					p.setDiet(new_diet);
					patientManager.updatePatient(p);
				} else if (toChange.equalsIgnoreCase("exercise")) {
					System.out.println("Input new amount of exercise per week: ");
					p.setEx_per_week(getPositiveInteger(reader));
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
		System.out.println(d.toString());

		if (d.getPhoto() != null) {
			ByteArrayInputStream blobIn = new ByteArrayInputStream(d.getPhoto());
			ImageWindow window = new ImageWindow();
			window.showBlob(blobIn);
		}

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
				System.out.println(d.toString());
				System.out.println("\nWhich information would you like to change? (you cannot change your email): ");
				String toChange = getString(reader);

				if (toChange.equalsIgnoreCase("name")) {
					System.out.println("Input new name: ");
					String toChangeName = getString(reader);
					d.setName(toChangeName);
					doctorManager.updateDoctor(d);
				} else if (toChange.equalsIgnoreCase("hospitalName")) {
					System.out.println("Input new hospital name: ");
					String toChangeHospitalName = getString(reader);
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
			try {
				Episode ep = createEpisode(reader);
				episodeManager.addEpisode(ep);

				Symptom symptom = createSymptom(reader);
				Symptom s2 = symptomManager.getSymptomByName(symptom.getName());

				if (s2 == null) {
					symptomManager.addSymptom(symptom);
					EpisodeSymptom epsymp = createSeverity(ep, symptom);
					esManager.assignEpisodeSymptom(epsymp);
				} else {
					EpisodeSymptom epsymp = createSeverity(ep, s2);
					esManager.assignEpisodeSymptom(epsymp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void addMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			try {
				Medication med = createMedication(reader);
				Medication med2 = medicationManager.getMedicationByName(med.getName());

				if (med2 == null) {
					PatientMedication pm = createPMed(p, med);

					medicationManager.addMedication(med);
					pmManager.assignPatientMedication(pm);
				} else {
					PatientMedication pm = createPMed(p, med2);
					pmManager.assignPatientMedication(pm);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void addAllergy(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			// try {
			Allergy a = getAllergy(reader);
			Allergy a2 = allergyManager.getAllergyByName(a.getName());

			if (a2 == null) {
				PatientAllergy pa = new PatientAllergy(a, p);

				allergyManager.addAllergy(a);
				paManager.assignPatientAllergy(pa);
			} else {
				PatientAllergy pa = new PatientAllergy(a2, p);
				paManager.assignPatientAllergy(pa);
			}
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}

	private static void showEvolution(Patient p) {
		// We could show the data like this:
		// PRECONDITION: 1 month of data and at least 1 episode recorded
		// 1. Shows all episodes in a month
		// 2. Shows the number of episodes per month using a counter
		// 3. Shows if there is an exercise or meal repeated in the collection ?????
		List<Episode> episodes = episodeManager.getEpisodesOfPatient(p.getId());
		int month = 1;
		int count = 0;
		for (Episode ep : episodes) {
			if (ep.getDoe().getMonth() == month) {
				count++;
				System.out.println(ep.toString());
				for (Symptom s : esManager.getSymptomsOfEpisode(ep.getId())) {
					System.out.println(s.toString());
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
			System.out.println(pmed.toString());
			System.out.println("Which information would you like to change?: ");
			String toChange = getString(reader);

			if (toChange.equalsIgnoreCase("frequency")) {
				System.out.println("Input new frequency: ");
				pmed.setFrequency(getPositiveInteger(reader));
				pmManager.unassignPatientMedication(pmed);
			} else if (toChange.equalsIgnoreCase("amount")) {
				System.out.println("Input new amount: ");
				pmed.setAmount(getPositiveFloat(reader));
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
			String namemed = getString(reader);
			med = medicationManager.getMedicationByName(namemed);
		} while (med == null);

		PatientMedication pm = new PatientMedication(p, med);
		return pm;
	}

	private static void deleteMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			List<Medication> meds = pmManager.getMedicationsOfPatient(p.getId());
			listMedications(meds);

			Medication med = null;
			do {
				System.out.println("Input the name of the medication: ");
				String namemed = getString(reader);
				med = medicationManager.getMedicationByName(namemed);
			} while (med == null);

			PatientMedication pm = new PatientMedication(p, med);
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
			System.out.println("Input the name of the allergy: ");
			String nameall = getString(reader);
			all = allergyManager.getAllergyByName(nameall);
		} while (all == null);

		PatientAllergy pa = new PatientAllergy(all, p);

		return pa;
	}

	private static Patient selectPatient(List<Patient> p) throws Exception {
		listPatients(p);
		System.out.println("Introduce the patients id: ");
		Integer id = getPositiveInteger(reader);

		Patient patient = patientManager.getPatientById(id);
		return patient;
	}

	private static void listPatients(List<Patient> p) {
		for (Patient pat : p) {
			System.out.println(pat.toStringForDoctors());
		}
	}

	private static void listDoctors(List<Doctor> docs) {
		for (Doctor d : docs) {
			System.out.println(d.toString());
		}
	}

	private static void listMedications(List<Medication> meds) {
		for (Medication m : meds) {
			System.out.println(m.toString());
		}
	}

	private static void listEpisodes(List<Episode> episodes) {
		for (Episode e : episodes) {
			System.out.println(e.toString());

			for (Symptom s : esManager.getSymptomsOfEpisode(e.getId())) {
				System.out.println(s.toString());
			}
		}
	}

	private static void listAllergies(List<Allergy> allergies) {
		for (Allergy a : allergies) {
			System.out.println(a.toString());
		}
	}

	private static void showRecipe(Integer choice, String diet, List<Allergy> allergies) {

		switch (choice) {

		case 1: // Hummus for every diet, changes in low and high fat (olive oil)
			String[] ingredientsHummus = new String[] { "chickpea", "tahini", "lemon", "salt", "olive", "baking soda" };

			for (Allergy a : allergies) {
				for (int i = 0; i < ingredientsHummus.length; i++) {
					if (a.getName() == ingredientsHummus[i]) {
						System.out.println("CAREFUL: This recipe contains " + ingredientsHummus[i] + ". "
								+ "Replace the ingredient or choose another recipe.");
					}
				}
			}

			System.out.println("\nHummus recipe:");
			System.out.println(
					"\nNeeded ingredients: 300g of chickpeas, 4 tablespoons of tahini, juice from 1 lemon, 2 teaspoons of salt, 3 tablespoons of olive oil, 1.5 teaspoon of baking soda/n");

			if (diet == "ketogenic") {
				System.out.println("For your ketogenic diet, you can add more olive oil.");
			}

			System.out
					.println("\nTake chickpeas and place it in a large bowl. Add plenty of water and soak overnight.");
			System.out.println(
					"When ready, drain chickpeas and place them in a medium-sized heavy cooking pot. Cover with water by about 2 inches.");
			System.out.println("Bring to a boil, then reduce heat and simmer for up to 2 hours.");
			System.out.println("Cover the cooked chickpeas in hot water and add baking soda.");
			System.out.println("Leave for a few minutes.");
			System.out.println("Take a handful of chickpeas and rub under running water to remove the skins.");
			System.out.println("Let the chickpeas cool completely.");
			System.out.println("Puree the chickpeas in a food processor.");
			System.out.println("Add the rest and keep blending until its consistency is smooth.");
			System.out.println("Transfer to a serving bowl and garnish with for example parsley or paprika./n");

		case 2: // Shakshuka, only replace with oil and eggs
			String[] ingredientsShakshuka = { "olive", "onion", "red bell pepper", "garlic", "paprika", "cumin",
					"chili", "tomato", "egg", "salt", "pepper" };

			for (Allergy a : allergies) {
				for (int i = 0; i < ingredientsShakshuka.length; i++) {
					if (a.getName() == ingredientsShakshuka[i]) {
						System.out.println("CAREFUL: This recipe contains " + ingredientsShakshuka[i] + ". "
								+ "Replace the ingredient or choose another recipe.");
					}
				}
			}

			System.out.println("\nShakshuka recipe:");
			System.out.println(
					"\nNeeded ingredients: 6 eggs, 1 red bell pepper, 1 onion, 1 garlic, 1 mashed tomatoes can, 2 teaspoons of paprika, 2 teaspoons of cumin, 1 teaspoon of salt, 1 teaspoon of pepper, 1 teaspoon of chili powder, 1 tablespoon of olive oil /n");

			if (diet == "vegan") {
				System.out.println("For your " + diet + " diet, replace eggs with 200g of tofu.");
			}
			if (diet == "high protein vegan") {
				System.out.println("For your" + diet
						+ " diet replace eggs with 300g of tofu. You also can use less than a whole can of tomatoes. ");
			}
			if (diet == "ketogenic" || diet == "ketogenic vegetarian") {
				System.out.println("For your " + diet + " diet you can use less tomatoes and more olive oil.");
			}
			if (diet == "ketogenic vegan") {
				System.out.println(
						"For your " + diet + " diet you can replace eggs with 150g of tofu and use more olive oil.");
			}
			if (diet == "high protein vegetarian") {
				System.out.println(
						"For your " + diet + " diet you can use less than a whole can of tomatoes and use 8 eggs.");
			}
			if (diet == "high protein") {
				System.out.println("For your " + diet + " diet you can use 8 eggs.");
			}

			System.out.println("\nHeat olive oil in a large saute pan on medium heat.");
			System.out.println(
					"Add the chopped bell pepper and onion and cook for 5 minutes or until the onion becomes translucent.");
			System.out.println("Add garlic and spices and cook an additional minute");
			System.out.println(
					"Pour the can of tomatoes and juice into the pan and break down the tomatoes using a large spoon.");
			System.out.println("Season with salt and pepper and bring the sauce to a simmer");
			System.out.println(
					"Use your large spoon to make small wells in the sauce and crack the eggs into each well.");
			System.out.println("Cover the pan and cook for 5-8 minutes, or until the eggs are done to your liking");
			System.out.println("Optionally garnish with chopped cilantro and parsley./n");

		case 3: // Feta salad, only replace feta
			String[] ingredientsSalad = { "lettuce", "tomato", "cucumber", "feta" };

			for (Allergy a : allergies) {
				for (int i = 0; i < ingredientsSalad.length; i++) {
					if (a.getName() == ingredientsSalad[i]) {
						System.out.println("CAREFUL: This recipe contains " + ingredientsSalad[i] + ". "
								+ "Replace the ingredient or choose another recipe.");
					}
				}
			}

			System.out.println("Feta salad recipe:");
			System.out.println("Needed ingredients: half of lettuce, 2 tomatoes, 1 cucumber, 200g of feta cheese/n");

			if (diet == "vegan") {
				System.out.println("For your " + diet + " diet, replace feta with 200g of pickled tofu or vegan feta.");
			}
			if (diet == "high protein vegan") {
				System.out.println("For your" + diet
						+ " diet replace feta with 300g of pickled tofu or vegan tofu. You also can use less lettuce. ");
			}
			if (diet == "ketogenic" || diet == "ketogenic vegetarian") {
				System.out.println(
						"For your " + diet + " diet you can use less vegetables, more feta cheese and add olive oil.");
			}
			if (diet == "ketogenic vegan") {
				System.out.println("For your " + diet
						+ " diet you can replace feta cheese with pickled tofu or vegan feta. You also can use less vegetables and add olive oil.");
			}
			if (diet == "high protein vegetarian" || diet == "high protein") {
				System.out.println("For your " + diet + " diet you can use 300g of feta cheese and less vegetables.");
			}
			if (diet == "lactose free") {
				System.out.println("For your " + diet
						+ " diet you can use lactose free feta cheese. If you can't find one, you can use pickled tofu or vegan feta cheese.");
			}
			if (diet == "dairy free") {
				System.out.println("For your " + diet + " diet you can use pickled tofu or vegan feta cheese.");
			}

			System.out.println(
					"\nChop half a lettuce into small pieces. Do the same with 2 tomatoes, 1 cucumber and a feta cheese");
			System.out.println("Mix everything together in a bowl");
			System.out.println("Use the spices you like for example: oregano, salt, pepper");
			System.out.println("Optionally you can add 1 table spoon of olive oil./n");

		case 4:
			// Chinese noodles
			String[] ingredientsNoodles = { "onion", "red bell pepper", "tofu", "soybean", "paprika" };

			for (Allergy a : allergies) {
				for (int i = 0; i < ingredientsNoodles.length; i++) {
					if (a.getName() == ingredientsNoodles[i]) {
						System.out.println("CAREFUL: This recipe contains " + ingredientsNoodles[i] + ". "
								+ "Replace the ingredient or choose another recipe.");
					}
				}
			}

			System.out.println("\nChinese noodles recipe:");
			System.out.println(
					"\nNeeded ingredients: 1 pack of noodles (of rice, wheat..., as preferred), 1 big onion, 1 red bell pepper, 150g of tofu, 5 tablespoons of soy sauce, 2 table spoons of oil (olive or sunflower, as preferred), 2 teaspoons of paprika");

			if (diet == "high protein vegetarian" || diet == "high protein" || diet == "high protein vegan") {
				System.out.println("For your" + diet + " diet you can use 200g of tofu and less noodles.");
			}
			if (diet == "ketogenic" || diet == "ketogenic vegetarian" || diet == "ketogenic vegan") {
				System.out.println("For your " + diet + " diet you can use less noodles and more oil.");
			}
			if (diet == "gluten free") {
				System.out.println("For your " + diet + " diet remember to use gluten free noodles");
			}

			System.out.println("\nDice 2 onions and fry on oil you like, for example sesame oil.");
			System.out.println("Boil noodles.");
			System.out.println("Add soy sauce");
			System.out.println("Slice red bell pepper and other greens of your choice");
			System.out.println("Dice tofu and add to the veggies.");
			System.out.println("Add paprika.");
			System.out.println("Add noodles to the rest and fry for 2 minutes./n");

		case 5:
			// Oatmeal, replace milk, not for keto, high protein
			String[] ingredientsOatmeal = { "oat", "cow milk", "cinnamon" };

			for (Allergy a : allergies) {
				for (int i = 0; i < ingredientsOatmeal.length; i++) {
					if (a.getName() == ingredientsOatmeal[i]) {
						System.out.println("CAREFUL: This recipe contains " + ingredientsOatmeal[i] + ". "
								+ "Replace the ingredient or choose another recipe.");
					}
				}
			}

			System.out.println("\nOatmeal recipe:");
			System.out.println("\nNeeded ingredients: 1 cup of oats, 1 cup of milk, 1 teaspoon of cinnamon");

			if (diet == "high protein vegetarian" || diet == "high protein") {
				System.out.println(
						"For your" + diet + " diet you can add protein powder or tofu or use high protein milk.");
			}
			if (diet == "high protein vegan") {
				System.out.println("For your" + diet
						+ " diet replace cow milk with plant-based milk. To add proteins you can use high protein plant-based milk or add tofu or vegan protein powder.");
			}
			if (diet == "ketogenic" || diet == "ketogenic vegetarian") {
				System.out.println("For your" + diet
						+ " diet you can use coconut milk or high fat milk. Use only 1/3 cup of oats. Add lots of nuts or chia seeds to make it more ketogenic.");
			}
			if (diet == "dairy free" || diet == "vegan") {
				System.out.println("For your" + diet + " replace cow milk with plant based milk.");
			}
			if (diet == "lactose free") {
				System.out.println("For your" + diet + " replace normal cow milk with lactose free milk or vegan milk");
			}
			if (diet == "ketogenic vegan") {
				System.out.println("For your" + diet
						+ " replace cow milk with plant based milk. You can add many nuts or chia seeds to make it more ketogenic.");
			}

			System.out.println("\nBoil oats in milk.");
			System.out.println("Add cinnamon.");
			System.out.println(
					"You can add anything you want, nuts, goji berries, dates etc. It is recommended to add fresh fruit.");
			System.out.println("Mix everything and boil until oats are soft./n");

		default:
			System.out.println("Wrong number, do you want to back to the menu?");
			System.out.println("Type yes or no: ");
			String choice2 = getString(reader);
			if (choice2 == "yes") {
				showMenu();
			} else if (choice2 == "no") {
			}
		}
	}

	// Patient p has already logged in, connection with the database done
	private static void chooseRecipe(Patient p) {

		System.out.println("What recipe do you want to see?");
		System.out.println("1. Hummus");
		System.out.println("2. Shakshuka");
		System.out.println("3. Salad");
		System.out.println("4. Chinese noodles");
		System.out.println("5. Oatmeal");

		System.out.println("Input the number of the recipe: ");
		Integer choice = getPositiveInteger(reader);

		List<Allergy> allergies = paManager.getAllergiesOfPatient(p.getId());
		String diet = p.getDiet();

		showRecipe(choice, diet, allergies);
	}
}
