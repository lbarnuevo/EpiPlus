package epiplus.ui;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.*;

import static epiplus.ui.Auxiliar.*;
import epiplus.ifaces.*;
import epiplus.jdbc.*;
import epiplus.jpa.JPAUserManager;
import epiplus.pojos.*;
import epiplus.graphics.*;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static JDBCManager dbManager;
	private static UserManager userManager;
	private static DoctorManager doctorManager;
	private static PatientManager patientManager;
	
	private static EmergencyContactManager ecManager;
	private static EpisodeManager episodeManager;
	private static EpisodeSymptomManager esManager;
	private static MedicationManager medicationManager;
	private static PatientMedicationManager pmManager;
	private static SymptomManager symptomManager;
	private static AllergyManager allergyManager;
	private static PatientAllergyManager paManager;

	public static void main(String[] args) {
		dbManager = new JDBCManager();
		doctorManager = new JDBCDoctorManager(dbManager);
		patientManager = new JDBCPatientManager(dbManager);
		episodeManager = new JDBCEpisodeManager(dbManager);
		esManager = new JDBCEpisodeSymptomManager(dbManager);
		medicationManager = new JDBCMedicationManager(dbManager);
		pmManager = new JDBCPatientMedicationManager(dbManager);
		symptomManager = new JDBCSymptomManager(dbManager);
		allergyManager = new JDBCAllergyManager(dbManager);
		paManager = new JDBCPatientAllergyManager(dbManager);
		ecManager = new JDBCEmergencyContactManager(dbManager);
		
		//userManager = new JPAUserManager();
		try {
			do {
				System.out.println("                    WELCOME TO EPI+ !!                     ");
				System.out.println("---------------------------------------------------------------");
				System.out.println(" 1. Login as patient                                           ");
				System.out.println(" 2. log in as doctor (TEMPORARY) ");
				System.out.println(" 3. Register                                                   ");
				System.out.println(" 4. Generate XML                                               ");
				System.out.println(" 5. Generate HTML                                              ");
				System.out.println(" 6. I forgot my password                                       ");
				System.out.println(" 0. Exit the program                                           ");
				System.out.println("---------------------------------------------------------------");
				System.out.println("\n\nPlease chose one of the previous options: ");
				
				int choice = getPositiveInteger(reader);
				switch (choice) {
					case 1: 
						//login(); 
						System.out.println("Enter patient name: ");
						String p_name = getString(reader);
						Patient p = searchPatient(p_name);
						patientMenu(p);
						
						break; 
					case 2: 
						//register();
						
						System.out.println("Introduce the doctor's name: ");
						List<Doctor> docs = doctorManager.searchDoctorByName(getString(reader));
						listDoctors(docs);
						Doctor d = null; 
						do {
							System.out.println("Introduce the doctor�s id: ");
							d = doctorManager.getDoctorById(getPositiveInteger(reader));
						} while (d == null);
						doctorMenu(d);
						break;
					case 3: 
						//TODO generate xml
						registerMenu();
						
						break;
					case 4: 
						//TODO generate html
						break;
					case 6: 
						changePassword(1);
						break; 
					case 0:
						System.out.println("See you soon! :)");
						dbManager.disconnect();
						System.exit(0);
				}

			} while(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//TODO ask rodrigo why jpa doesnt work so we can delete these methods 
	public static Patient searchPatient(String name) throws Exception {

		List<Patient> patients = patientManager.searchPatientByName(name);
		Patient p = selectPatient(patients);
		return p;
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
	private static void registerDoctor() throws IOException{
		Doctor d = createDoctor(reader);
		doctorManager.addDoctor(d);
		int id = dbManager.getLastId();
		d.setId(id);
	}
	private static void registerPatient() throws IOException {
		Patient patient = createPatient(reader);
		patientManager.addPatient(patient);
		patient.setId(dbManager.getLastId());
		System.out.println("Adding allergies...");
		addAllergy(patient);

		System.out.println("\nYou have been successfully registered");
	}

	
	//THESE ARE THE ACTUAL METHODS THAT WILL BE USED 
	
	public static void login() {
		System.out.println("Email address: ");
		String email = getString(reader);
		
		System.out.println("Password: ");
		String passwd = getString(reader);
		
		User u = userManager.checkPassword(email, passwd);
		
		if(u == null) {
			System.out.println("Wrong email or password");
			return; 
		} else if (u.getRole().getName().equalsIgnoreCase("doctor")) {
			//TODO go to doctor menu 
		} else if (u.getRole().getName().equalsIgnoreCase("patient")) {
			//TODO go to patient menu 
		}
	}
	
	public static void register() throws Exception {
		System.out.println(userManager.getRoles());
		System.out.println("Please, input the chosen role ID: ");
		int id = getPositiveInteger(reader);
		Role role = userManager.getRole(id);
		
		String email; 
		
		switch(id) {
			case 1:
				System.out.println("----DOCTOR REGISTRATION----");
				System.out.println("");
				
				System.out.println("Please, write your email address: ");
				email = reader.readLine();
				
				if(userManager.checkEmail(email)) {
					System.out.println("There is already a doctor account with that email, please try to log in");
					return; 
					
				} else {
					System.out.println("Name: ");
					String name = getString(reader);

					System.out.println("Hospital name: ");
					String hospital = getString(reader);

					System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
					boolean confirmation = askConfirmation(reader);

					byte[] photo = null;
					if (confirmation == true) {
						photo = getPhoto(reader);
					}
					
					System.out.println("Please, write your password: ");
					String pass = getString(reader);

					Doctor doc = new Doctor(name, email, hospital, photo, id);
					doctorManager.addDoctor(doc);
					
					
					MessageDigest md1 = MessageDigest.getInstance("MD5");
					md1.update(pass.getBytes());
					byte[] digest = md1.digest();
					User user = new User(email, digest, role);
					userManager.newUser(user);
					
					doctorMenu(doc);
				}
				
			case 2: 
					System.out.println("-----PATIENT REGISTRATION-----");
					System.out.println("");
					
					System.out.println("Please, write your email address: ");
					email = getString(reader);
					
					if(userManager.checkEmail(email)) {
						System.out.println("There is already a patient account with that email, please try to log in");
						return; 
						
					} else {
						System.out.println("Name: ");
						String name = getString(reader);
				
						System.out.println("Date of birth (dd-MM-yyyy): ");
						LocalDate birthday = LocalDate.parse(getString(reader), formatter);
				
						System.out.println("Height (cm): ");
						Float height = getPositiveFloat(reader);
				
						System.out.println("Weight (kg): ");
						Float weight = getPositiveFloat(reader);
				
						System.out.println("Lifestyle: ");
						String lifestyle = getLifeStyle(reader);
				
						System.out.println("Diet: ");
						String diet = getDiet(reader);
				
						System.out.println("Exercise per week (hours per week): ");
						Integer exercise = getPositiveInteger(reader);
				
						System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
						boolean confirmation = askConfirmation(reader);
				
						byte[] photo = null;
						if (confirmation == true) {
							photo = getPhoto(reader);
						}
						
						System.out.println("Please, write your password: ");
						String pass = getString(reader);
	
						Patient p = new Patient(name, email, Date.valueOf(birthday), height, weight, lifestyle, diet, exercise, photo, id);
						patientManager.addPatient(p);
						
						System.out.println("Adding allergies...");
						addAllergy(p);
						
						MessageDigest md1 = MessageDigest.getInstance("MD5");
						md1.update(pass.getBytes());
						byte[] digest = md1.digest();
						
						User user = new User(email, digest, role);
						userManager.newUser(user);
						
						patientMenu(p);
					}	
		}
	} 
	
	private static void changePassword(int choice ) {
		String email; 
		switch(choice) {
			case 1: //Case when the user forgot their password
				System.out.println("Please, write your email address");
				email = getString(reader);
				System.out.println("Please, write your NEW password:");
				String password = getString(reader);
				userManager.forgotPassword(email, password);
				return; 
			case 2: //Case were the user wants tp change their password
				System.out.println("Please, introduce your email address:");
		 		email = getString(reader);
		 		System.out.println("Now, please, introduce your password:");
		 		String oldPassword = getString(reader);
		 		System.out.println("Now, please, introduce your new password:");
		 		String newPassword = getString(reader);
		 		
		 		if(continueProccess()) {
		 			userManager.updatePassword(email, newPassword, oldPassword);
		 			return; 
		 		}
		}
	}
	
	private static void deleteAccount() {
		//when you delete the account, also delete the doctor/patient from the database 
		//maybe we can do it with the role 
		System.out.println("Please, introduce again your email address:");
		String email = getString(reader);
		if(continueProccess()) {
			userManager.deleteUser(email);
		}
	}
	
	private static boolean continueProccess() {
		System.out.println("Do you want to continue the process? (Yes -> Y || No -> N): ");
		return askConfirmation(reader);
	}
	
	public static void doctorMenu(Doctor d) throws Exception {
		do {
			System.out.println("                     DOCTOR MENU                            ");
			System.out.println("---------------------------------------------------------------");
			System.out.println(" 1. See data of one of my patients                          ");
			System.out.println(" 2. See evolution of a patient                              ");
			System.out.println(" 3. See my profile                                          ");
			System.out.println(" 4. Edit my profile                                         ");
			System.out.println(" 5. Change my password                                      ");
			System.out.println(" 6. Delete my account                                       ");
			System.out.println(" 0. Log out                                                  ");
			System.out.println("---------------------------------------------------------------");
			
			int choice = getPositiveInteger(reader);

			switch (choice) {
			case 1:
				Patient p = selectPatient(d);
				System.out.println("\n" + p.toString());
				break;
			case 2:
				//TODO see evolution 
				break;
			case 3:
				seeUserDoctor(d); 
				break;
			case 4:
				updateUserDoctor(d);
				break; 
			case 5: 
				changePassword(2); //Do they have to login again? 
				break; 
			case 6: 
				deleteAccount();
				break; 
			case 0:
				System.out.println("~~Byee! ");
				return;
			default:
				System.out.println("Please introduce a valid option. ");
			}
		} while(true);
	}
	
	private static void patientMenu(Patient p) throws Exception { 

		do {
			System.out.println("                      PATIENT MENU                           ");
			System.out.println("---------------------------------------------------------------");
			System.out.println(" 1. Register a new episode                                    ");
			System.out.println(" 2. My medications                                            ");
			System.out.println(" 3. My episodes                                               ");
			System.out.println(" 4. See my evolution            NOT DONE                      ");
			System.out.println(" 5. Show recipes                  	                          ");
			System.out.println(" 6. Add emergency contacts          	                      ");
			System.out.println(" 7. Add a doctor                  	                          ");
			System.out.println(" 8. Delete my current doctor                   	              ");
			System.out.println(" 9. See my profile                   	                      ");
			System.out.println(" 10. Edit my profile              	                          ");
			System.out.println(" 11. Change my password                                       ");
			System.out.println(" 12. Delete my account                                        ");
			System.out.println(" 0. Log out                                                  ");
			System.out.println("---------------------------------------------------------------");
			
			int choice = getPositiveInteger(reader);

			switch (choice) {
			case 1:
				registerEpisode(p);
				break;
			case 2:
				Medications(p);
				break;
			case 3:
				listEpisodes(p); 
				// deleteEpisode(p);
				break;
			case 4:
				//TODO showEvolution(p);
				break;
			case 5:
				Recipes(p); 
				break;
			case 6:
				addEmergencyContact(p);
				break;
			case 7:
				assignDoctor(p); 
				break;
			case 8: 
				unassignDoctor(p);
				break; 
			case 9:
				seeUserPatient(p);
				break; 
			case 10: 
				updateUserPatient(p); 
				break; 
			case 11: 
				changePassword(2); //Do they have to login again?
				break; 
			case 12: 
				deleteAccount();
				break; 
			case 0:
				System.out.println("~~Byee");
				return;
			default:
				System.out.println("Please introduce a valid option. ");
			}
		} while (true);
	}
	
	private static void Medications(Patient p) throws Exception{
		do {
			System.out.println("               MY MEDICATIONS                      ");
			System.out.println("1. See my current medications                      ");
			System.out.println("2. Add a new medication        					   ");
			System.out.println("3. Modify frequency or amount of a medication      ");
			System.out.println("4. Delete one of my medications                    ");
			System.out.println("0. Go back                                         ");
			
			System.out.println("Please, introduce an option: ");
			int choice = getPositiveInteger(reader);
			switch(choice) {
				case 1: 
					listMedications(p);
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
					System.out.println("Please, introduce a valid option.");
			}
			
		} while(true);
	}
	
	private static void Recipes(Patient p) {
		do {
			System.out.println("~~~~What recipe do you want to see?~~~~");
			System.out.println("1. Hummus");
			System.out.println("2. Shakshuka");
			System.out.println("3. Salad");
			System.out.println("4. Chinese noodles");
			System.out.println("5. Oatmeal");
			System.out.println("0. Go back");
			
			System.out.println("Please, introduce an option: ");
			int choice = getPositiveInteger(reader);
			switch(choice) {
			case 1:
				showHummusRecipe(p.getDiet(), paManager.getAllergiesOfPatient(p.getId()));
				return;
			case 2: 
				showShakshukaRecipe(p.getDiet(), paManager.getAllergiesOfPatient(p.getId()));
				return;
			case 3: 
				showFetaRecipe(p.getDiet(), paManager.getAllergiesOfPatient(p.getId()));
				return; 
			case 4: 
				showNoodlesRecipe(p.getDiet(), paManager.getAllergiesOfPatient(p.getId()));
				return; 
			case 5: 
				showOatmealRecipe(p.getDiet(), paManager.getAllergiesOfPatient(p.getId()));
				return;
			case 0: 
				return; 
			default:
				System.out.println("That is not an option. ");
			}
		} while(true);
	}


	//TODO one unique seeuser method with roles 
	private static void seeUserPatient(Patient p) {
		System.out.println("Showing user's information...\n");
		System.out.println(p.toString());

		listEC(p);
		System.out.println("");
		listAllergy(p);
		System.out.println("");
		
		if(p.getDoctor() != null) {
			System.out.println("My doctor : " + p.getDoctor().toString());
		}

		if (p.getPhoto() != null) {
			ByteArrayInputStream blobIn = new ByteArrayInputStream(p.getPhoto());
			ImageWindow window = new ImageWindow();
			window.showBlob(blobIn);
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

	//TODO one unique updateuser methods with roles 
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
				} else if(toChange.equalsIgnoreCase("emergency contacts")) {
					System.out.println("Update emergency contacts...");
					updateEContacts(p);
				
					System.out.println("Delete emergency contact...");
					deleteEContact(p);	
				} else if(toChange.equalsIgnoreCase("allergy")) {
					System.out.println("Note: You won't be able to change any atributes from an allergy, onlt delete it");
					deleteAllergy(p);
				}
				// TODO change photo

				if (continueProccess() == false) {
					return;
				}
			}
		}
	}


	//Methods for working with patients from doctor
	private static Patient selectPatient(Doctor d) {
		listPatients(d);
		Patient p = null; 
		
		do {
			System.out.println("Introduce the patient's id: ");
			int id = getPositiveInteger(reader);
			p = patientManager.getPatientById(id);
		} while (p == null);

		return p;
	}
	
	private static void listPatients(Doctor d) {
		List<Patient> pts = doctorManager.getPatientsOfDoctor(d.getId());
		
		for(Patient p: pts) {
			System.out.println(p.toStringForDoctors());
		}
	}
	
	
	
	//Methods for working with episodes 
	private static void registerEpisode(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			try {
				Episode ep = createEpisode(reader);
				ep.setPatient(p);
				episodeManager.addEpisode(ep);
				ep.setId(dbManager.getLastId());
				
				Symptom smp = createSymptom(reader);
				Symptom s2 = symptomManager.getSymptomByName(smp.getName());
				
				if(s2 == null) {
					symptomManager.addSymptom(smp);
					smp.setId(dbManager.getLastId());
					smp.addEpisodes(ep);
					ep.addSymptom(smp);
					
					EpisodeSymptom epsp = createSeverity(ep, smp);
					esManager.assignEpisodeSymptom(epsp);
				} else {
					s2.addEpisodes(ep);
					ep.addSymptom(s2);
					
					EpisodeSymptom epsp = createSeverity(ep, s2);
					esManager.assignEpisodeSymptom(epsp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void deleteEpisode(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			boolean deleted = false;
			do {
				System.out.println("Introduce the episode's id: ");
				//TODO getEpisodeByID
				//then delete episode + delete the episodesymptom relationship between them, 
				//and remove episode form list of episodes in symptoms 
			} while(deleted == false);
		}
	}
	
	private static void listEpisodes(Patient p) {
		List<Episode> episodes = episodeManager.getEpisodesOfPatient(p.getId());
		
		for (Episode e : episodes) {
			System.out.println(e.toString());
			
			for (Symptom s : esManager.getSymptomsOfEpisode(e.getId())) {
				System.out.println(s.toString());
			}
			System.out.println("------------------------\n");
		}
	}


	//Methods for working with medications
	private static void addMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			try {
				Medication med = createMedication(reader);
				Medication med2 = medicationManager.getMedicationByName(med.getName());

				if (med2 == null) {
					medicationManager.addMedication(med);
					med.setId(dbManager.getLastId());
					med.addPatient(p);
					p.addMedication(med);
					
					PatientMedication pm = createPMed(p, med);
					pmManager.assignPatientMedication(pm);
					
				} else {
					med2.addPatient(p);
					p.addMedication(med2);
					
					PatientMedication pm = createPMed(p, med2);
					pmManager.assignPatientMedication(pm);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static PatientMedication getMedFromPatient(Patient p) {
		listMedications(p);
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
			PatientMedication pm = getMedFromPatient(p);
			Medication med = pm.getMedication();
			p.removeMedication(med);
			med.removePatient(p);
			
			pmManager.unassignPatientMedication(pm);
		}
	}
	
	private static void updateMedication(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			PatientMedication pmed = getMedFromPatient(p);

			System.out.println("\nShowing medications information... \n");
			System.out.println(pmed.toString());
			
			System.out.println("\nWhich information would you like to change?: ");
			String toChange = getString(reader);

			if (toChange.equalsIgnoreCase("frequency")) {
				System.out.println("Input new frequency: ");
				pmed.setFrequency(getPositiveInteger(reader));
				pmManager.updatePatientMedication(pmed);
			} else if (toChange.equalsIgnoreCase("amount")) {
				System.out.println("Input new amount: ");
				pmed.setAmount(getPositiveFloat(reader));
				pmManager.updatePatientMedication(pmed);
			}
		}
	}
	
	private static void listMedications(Patient p) {
		List<Medication> meds = pmManager.getMedicationsOfPatient(p.getId());
		
		for (Medication m : meds) {
			System.out.println(m.toString());
			//TODO also print the frequency and the amount 
			System.out.println("-----------------------------\n");
		}
	}
	
	
	//Methods for doing operations on doctors from a patients account
	private static Doctor searchDoctor() {
		do {
			System.out.println("		SEARCHING MENU			   ");
			System.out.println("1. Search by the doctor's name     ");
			System.out.println("2. Search by the doctor's email    ");
			System.out.println("3. Search by the hospital          "); //TODO consider deleting this 
			System.out.println("0. Go back                         ");
			
			System.out.println("\nPlease introduce the option: ");
			
			List<Doctor> docs = null;
			Doctor d = null; 
			
			int choice = getPositiveInteger(reader);
			switch(choice) {
				case 1: 
					System.out.println("Introduce the doctor's name: ");
					docs = doctorManager.searchDoctorByName(getString(reader));
					
					if(docs.isEmpty()) {
						System.out.println("There is not a doctor with that name");
						break;
					} else {
						listDoctors(docs);
						do {
							System.out.println("Introduce the doctor�s id: ");
							d = doctorManager.getDoctorById(getPositiveInteger(reader));
						} while (d == null);
						return d; 
					}
					
				case 2: 
					System.out.println("Introduce the doctor's email: ");
					d = doctorManager.searchDoctorByEmail(getString(reader));
	
					if(d == null) {
						System.out.println("There is not a doctor with that email");
						break;
					} else {
						return d; 
					}
					
				case 3: 
					System.out.println("Introduce the hospital's name: ");
					docs = doctorManager.searchDoctorByHospital(getString(reader));
					
					if(docs.isEmpty()) {
						System.out.println("There is no doctor's in that hospital. Check for spelling mistakes");
						break;
					} else {
						listDoctors(docs);
						do {
							System.out.println("Introduce the doctor�s id: ");
							d = doctorManager.getDoctorById(getPositiveInteger(reader));
						} while (d == null);
						return d; 
					} 
				case 0: 
					return null; 
				default: 
					System.out.println("That option does not exist.");
			}
		} while(true);
	}
	
	private static void assignDoctor(Patient p) {
		if (p.getDoctor() != null) {
			System.out.println("You already have a desiganted doctor, if you want to chenge it, you have to delete him as your doctor first");
			return; 
		} else {
			Doctor d = searchDoctor();
		
			if(d == null) {
				return; 
			} else {
				p.setDoctor(d);
				d.addPatient(p);
				
				patientManager.assignDoctor(p, d);
				return;
			}
		}
	}
	
	private static void unassignDoctor(Patient p) {
		if (p.getDoctor() == null) {
			System.out.println("You have not registered any doctor");
			return; 
		} else {
			patientManager.unassignDoctor(p, p.getDoctor());
			p.getDoctor().removePatient(p);
			p.setDoctor(null);
		}
	}
	
	private static void listDoctors(List<Doctor> docs) {
		for (Doctor d : docs) {
			System.out.println(d.toString());
		}
	}
	
	
	//Methods for emergency contacts 
	private static EmergencyContact selectEC(Patient p) {
		listEC(p);
		EmergencyContact ec = null; 
		
		do {
			System.out.println("Introduce the contacts id: ");
			int id = getPositiveInteger(reader);
			ec = ecManager.getECbyId(id);
		} while (ec == null);

		return ec;  
	}
	
	private static void listEC(Patient p) {
		List<EmergencyContact> ecs = ecManager.getEmergencyContactsOfPatient(p.getId());

		System.out.println("\n--- MY EMERGENCY CONTACTS ---");
		for(EmergencyContact e2 : ecs) {
			System.out.println(e2.toString());
			System.out.println("-----------------------------\n");
		}
	}
	
	private static void addEmergencyContact(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			try {
				EmergencyContact ec = createEmergencyContacts(reader, p);
				List<EmergencyContact> ecs = ecManager.getEmergencyContactsOfPatient(p.getId());
				
				for(EmergencyContact e2 : ecs) {
					if(e2.getNumber() == ec.getNumber()) {
						System.out.println("There is already an emergency contact with that number.");
						return; 
					}
				}
				
				ecManager.addEmergencyContact(ec);
				ec.setId(dbManager.getLastId());
				p.addEC(ec);
				
				return; 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void updateEContacts(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			EmergencyContact ec = selectEC(p);

			System.out.println("\nShowing emergency contact's information... \n");
			System.out.println(ec.toString());
			
			System.out.println("\nWhich information would you like to change?: ");
			String toChange = getString(reader);

			if (toChange.equalsIgnoreCase("name")) {
				System.out.println("Input new name: ");
				ec.setName(getString(reader));
				ecManager.updateEmergencyContact(ec);
			} else if (toChange.equalsIgnoreCase("number")) {
				System.out.println("Input new number: ");
				ec.setNumber(getPositiveFloat(reader));
				ecManager.updateEmergencyContact(ec);
			}
		}
	}
	
	private static void deleteEContact(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			EmergencyContact ec = selectEC(p);
			
			p.removeEC(ec);
			ecManager.deleteEmergencyContact(ec);
		}
	}
	
	
	//Methods for allergy
	private static void addAllergy(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			Allergy a = getAllergy(reader);
			Allergy a2 = allergyManager.getAllergyByName(a.getName());

			if (a2 == null) {
				allergyManager.addAllergy(a);
				a.setId(dbManager.getLastId());
				a.addPatient(p);
				p.addAllergy(a);
				
				PatientAllergy pa = new PatientAllergy(a, p);
				paManager.assignPatientAllergy(pa);
				
			} else {
				a2.addPatient(p);
				p.addAllergy(a2);
				
				PatientAllergy pa = new PatientAllergy(a2, p);
				paManager.assignPatientAllergy(pa);
			}
		}
	}
	
	private static void deleteAllergy(Patient p) {
		if (continueProccess() == false) {
			return;
		} else {
			PatientAllergy pa = getAllergyFromPatient(p);
			Allergy a = pa.getAllergy();
			p.removeAllergy(a);
			a.removePatient(p);
			
			paManager.unassignPatientAllergy(pa);
		}
	}
	
	private static PatientAllergy getAllergyFromPatient(Patient p) {
		listAllergy(p);
		Allergy a = null; 
		
		do {
			System.out.println("Input the allergy id: ");
			String name = getString(reader);
			a = allergyManager.getAllergyByName(name);
		} while (a == null);

		PatientAllergy pa = new PatientAllergy(a, p);
		return pa; 
	}
	
	private static void listAllergy(Patient p) {
		List<Allergy> allergies = paManager.getAllergiesOfPatient(p.getId());
		
		System.out.println("\n--- MY ALLERGIES ---");
		for(Allergy a: allergies) {
			System.out.println(a.toString());
			System.out.println("-----------------------------\n");
		}
	}
	
	
	//Methods for showing evolution //TODO show evolution
	/*private static void showEvolution(Patient p) {
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
	}*/
	
	
	//Methods for showing recipes 
	private static void showHummusRecipe(String diet, List<Allergy> allergies) {
		System.out.println("~~INGREDIENTS:");
		System.out.println("\n300g of chickpeas\n4 tablespoons of tahini \njuice from 1 lemon "
				+ "\n2 teaspoons of salt \n3 tablespoons of olive oil \n1.5 teaspoon of baking soda");

		String[] ingredientsHummus = new String[] { "chickpea", "tahini", "lemon", "salt", "olive", "baking soda" };

		for (Allergy a : allergies) {
			for (int i = 0; i < ingredientsHummus.length; i++) {
				if (a.getName() == ingredientsHummus[i]) {
					System.out.println("CAREFUL: This recipe contains " + ingredientsHummus[i] + ". "
							+ "Replace the ingredient or choose another recipe.");
				}
			}
		}

		if (diet == "ketogenic") {
			System.out.println("Note: for your ketogenic diet, you can add more olive oil.");
		}
		showHummus();
	}
	
	private static void showShakshukaRecipe(String diet, List<Allergy> allergies) {
		System.out.println("~~INGREDIENTS:");
		System.out.println("6 eggs \n1 red bell pepper \n1 onion \n1 garlic \n1 mashed tomatoes can "
				+ "\n2 teaspoons of paprika \n2 teaspoons of cumin \n1 teaspoon of salt \n1 teaspoon of pepper "
				+ "\n1 teaspoon of chili powder \n1 tablespoon of olive oil" );
				
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

		showShakshuka();
	}
	
	private static void showFetaRecipe(String diet, List<Allergy> allergies) {
		System.out.println("~~INGREDIENTS:");
		System.out.println("half of lettuce \n2 tomatoes \n1 cucumber \n200g of feta cheese");

		String[] ingredientsSalad = { "lettuce", "tomato", "cucumber", "feta" };

		for (Allergy a : allergies) {
			for (int i = 0; i < ingredientsSalad.length; i++) {
				if (a.getName() == ingredientsSalad[i]) {
					System.out.println("CAREFUL: This recipe contains " + ingredientsSalad[i] + ". "
							+ "Replace the ingredient or choose another recipe.");
				}
			}
		}

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

		showFetaSalad();
	}
	
	private static void showNoodlesRecipe(String diet, List<Allergy> allergies) {
		System.out.println("~~INGREDIENTS:");
		System.out.println("1 pack of noodles (of rice, wheat..., as preferred) \n1 big onion \n1 red bell pepper"
				+ " \n150g of tofu \n5 tablespoons of soy sauce \n2 table spoons of oil (olive or sunflower, as preferred) \n2 teaspoons of paprika");

		String[] ingredientsNoodles = { "onion", "red bell pepper", "tofu", "soybean", "paprika" };

		for (Allergy a : allergies) {
			for (int i = 0; i < ingredientsNoodles.length; i++) {
				if (a.getName() == ingredientsNoodles[i]) {
					System.out.println("CAREFUL: This recipe contains " + ingredientsNoodles[i] + ". "
							+ "Replace the ingredient or choose another recipe.");
				}
			}
		}

		if (diet == "high protein vegetarian" || diet == "high protein" || diet == "high protein vegan") {
			System.out.println("For your" + diet + " diet you can use 200g of tofu and less noodles.");
		}
		if (diet == "ketogenic" || diet == "ketogenic vegetarian" || diet == "ketogenic vegan") {
			System.out.println("For your " + diet + " diet you can use less noodles and more oil.");
		}
		if (diet == "gluten free") {
			System.out.println("For your " + diet + " diet remember to use gluten free noodles");
		}
		
		showChineseNoodles();
	}
	
	private static void showOatmealRecipe(String diet, List<Allergy> allergies) {
		System.out.println("~~INGREDIENTS:");
		System.out.println("1 cup of oats \n1 cup of milk \n1 teaspoon of cinnamon");

		String[] ingredientsOatmeal = { "oat", "cow milk", "cinnamon" };

		for (Allergy a : allergies) {
			for (int i = 0; i < ingredientsOatmeal.length; i++) {
				if (a.getName() == ingredientsOatmeal[i]) {
					System.out.println("CAREFUL: This recipe contains " + ingredientsOatmeal[i] + ". "
							+ "Replace the ingredient or choose another recipe.");
				}
			}
		}

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

		showOatmeal();
	}
}
