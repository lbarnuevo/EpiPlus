//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import epiplus.pojos.*;

public class Auxiliar {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static int getPositiveInteger(BufferedReader reader) {
		boolean read = false;
		int N = -1;

		do {
			try {
				N = Integer.parseInt(reader.readLine());
				if (N >= 0) {
					read = true;
				} else {
					System.out.println("Error. Introduce a number bigger than zero:");
				}
			} catch (IOException ex) {
				System.out.println("Reading error");
			} catch (NumberFormatException ex) {
				System.out.println("Error. Introduce a number:");
			}
		} while (read == false);
		return N;
	}

	public static String getString(BufferedReader reader) {
		String leido = null;
		try {
			leido = reader.readLine();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return leido;
	}

	public static Float getPositiveFloat(BufferedReader reader) {
		boolean read = false;
		Float N = -1.0f;
		do {
			try {
				N = Float.parseFloat(reader.readLine());
				if (N >= 0.0) {
					read = true;
				}
			} catch (IOException ex) {
				System.out.println("Reading error");
			} catch (NumberFormatException ex) {
				System.out.println("Error. Introduce a number:");
			}
		} while (read == false);
		return N;
	}

	public static byte[] getPhoto(BufferedReader reader) {
		byte[] bytesBlob = null;
		try {
			System.out.print("Type the file name as it appears in folder /photos, including extension: ");
			String fileName = getString(reader);
			File photo = new File("./photos/" + fileName);
			InputStream streamBlob = new FileInputStream(photo);
			bytesBlob = new byte[streamBlob.available()];
			streamBlob.read(bytesBlob);
			streamBlob.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return bytesBlob;
	}

	public static String getLifeStyle(BufferedReader reader) {
		ListLifestyle();
		String lifestyle = null;
		boolean read = false;

		do {
			System.out.println("What lifestyle would you say you follow?\n\nIntroduce one of the previous options. ");
			lifestyle = getString(reader);
			if (lifestyle.equalsIgnoreCase("sedentary")) {
				read = true;
			} else if (lifestyle.equalsIgnoreCase("low")) {
				read = true;
			} else if (lifestyle.equalsIgnoreCase("medium")) {
				read = true;
			} else if (lifestyle.equalsIgnoreCase("high")) {
				read = true;
			}

		} while (read == false);

		return lifestyle;
	}

	public static String getDiet(BufferedReader reader) {
		ListDiets();
		String diet = null;
		boolean read = false;

		do {
			System.out.println("What diet would you say you follow? \n\nIntroduce one of the previous options.");
			diet = getString(reader);

			if (diet.equalsIgnoreCase("normal")) {
				read = true;
			} else if (diet.equalsIgnoreCase("mediterranean")) {
				read = true;
			} else if (diet.equalsIgnoreCase("high protein")) {
				read = true;
			} else if (diet.equalsIgnoreCase("high protein vegetarian")) {
				read = true;
			} else if (diet.equalsIgnoreCase("high protein vegan")) {
				read = true;
			} else if (diet.equalsIgnoreCase("gluten free")) {
				read = true;
			} else if (diet.equalsIgnoreCase("lactose free")) {
				read = true;
			} else if (diet.equalsIgnoreCase("dairy free")) {
				read = true;
			} else if (diet.equalsIgnoreCase("ketogenic")) {
				read = true;
			} else if (diet.equalsIgnoreCase("ketogenic vegetarian")) {
				read = true;
			} else if (diet.equalsIgnoreCase("ketogenic vegan")) {
				read = true;
			} else if (diet.equalsIgnoreCase("vegan")) {
				read = true;
			} else if (diet.equalsIgnoreCase("vegetarian")) {
				read = true;
			}

		} while (read == false);

		return diet;
	}

	public static Allergy getAllergy(BufferedReader reader) {
		ListAllergies();
		String allergyName = null;
		Allergy allergy = null;
		boolean read = false;

		do {
			System.out.println("Do you have any allergies? \n\nIntroduce one or more of the options above.");
			allergyName = getString(reader);

			if (allergyName.equalsIgnoreCase("egg")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("fish")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("animal milk")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("nuts")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("legums")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("soja")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("wheat")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("vegetables")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("fruits")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("high-histamine food")||allergyName.equalsIgnoreCase("high histamine food")) {
				read = true;
			}

		} while (read == false);
		allergy = new Allergy(allergyName);
		return allergy;
	}

	public static Boolean askConfirmation(BufferedReader reader) {
		boolean confir = false;
		String confirmation = getString(reader);

		while (true) {
			if ("Y".equalsIgnoreCase(confirmation)) {
				confir = true;
				break;
			} else if ("N".equalsIgnoreCase(confirmation)) {
				confir = false;
				break;
			} else {
				System.out.println("Please introduce Y/N: ");
				confirmation = getString(reader);
			}
		}
		return confir;
	}

	public static Doctor createDoctor(BufferedReader reader) throws IOException {
		System.out.println("Input doctor information");
		System.out.println("");

		System.out.println("Name: ");
		String name = getString(reader);

		System.out.println("Hospital name: ");
		String hospital = getString(reader);

		System.out.println("Email: ");
		String email = getString(reader);

		System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
		boolean confirmation = askConfirmation(reader);

		byte[] photo = null;
		if (confirmation == true) {
			photo = getPhoto(reader);
		}

		Doctor doc = new Doctor(name, email, hospital, photo);
		return doc;
	}

	public static Patient createPatient(BufferedReader reader) throws IOException {
		System.out.println("Input patient information");
		System.out.println("");

		System.out.println("Name: ");
		String name = getString(reader);

		System.out.println("Email: ");
		String email = getString(reader);

		System.out.println("Date of birth (dd-MM-yyyy): ");
		String dob = getString(reader);
		LocalDate birthday = LocalDate.parse(dob, formatter);

		System.out.println("Height (cm): ");
		Float height = getPositiveFloat(reader);

		System.out.println("Weight (kg): ");
		Float weight = getPositiveFloat(reader);

		System.out.println("Lifestyle: ");
		String lifestyle = getLifeStyle(reader);

		System.out.println("Diet: ");
		String diet = getDiet(reader);

		System.out.println("Exercise per week (how many hours): ");
		Integer exercise = getPositiveInteger(reader);

		System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
		boolean confirmation = askConfirmation(reader);

		byte[] photo = null;
		if (confirmation == true) {
			photo = getPhoto(reader);
		}
		Patient p = new Patient(name, email, Date.valueOf(birthday), height, weight, lifestyle, diet, exercise, photo);
		return p;
	}

	public static EmergencyContact createEmergencyContacts(BufferedReader reader, Patient patient) throws IOException {
		System.out.println("Input emergency contact");
		System.out.println("");

		System.out.println("Name: ");
		String name = getString(reader);

		System.out.println("Telephone number: ");
		Float number = getPositiveFloat(reader);

		EmergencyContact ec = new EmergencyContact(name, number, patient);
		return ec;
	}

	public static Medication createMedication(BufferedReader reader) throws IOException {
		System.out.println("\nInput medication information: ");
		System.out.println("");

		System.out.println("Input it's name: ");
		String name = getString(reader);

		Medication med = new Medication(name);
		return med;
	}

	public static PatientMedication createPMed(Patient patient, Medication med) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Input frequency: ");
		Integer freq = getPositiveInteger(reader);
		System.out.println("Input amount: ");
		Float amount = getPositiveFloat(reader);

		PatientMedication pmed = new PatientMedication(freq, amount, patient, med);
		reader.close();
		return pmed;
	}

	public static Episode createEpisode(BufferedReader reader) throws IOException {
		System.out.println("Input the episode's information: ");
		System.out.println("");

		System.out.println("Date of episode (dd-MM-yyyy): ");
		String doe = getString(reader);
		LocalDate depisode = LocalDate.parse(doe, formatter);

		System.out.println("Episode length: ");
		Float length = getPositiveFloat(reader);

		System.out.println("Add previous activity: ");
		String activity = getString(reader);

		System.out.println("Mood: ");
		String mood = getString(reader);

		System.out.println("Place: ");
		String place = getString(reader);

		System.out.println("Previous meal: ");
		String previous_meal = getString(reader);

		System.out.println("Did you had any injury?(Yes --> Y / No --> N): ");
		Boolean injuries = askConfirmation(reader);

		Episode ep = new Episode(Date.valueOf(depisode), length, activity, mood, place, previous_meal, injuries);
		return ep;
	}

	public static Symptom createSymptom(BufferedReader reader) throws IOException {
		System.out.println("Symptoms name: ");
		String name = getString(reader);

		Symptom symp = new Symptom(name);
		return symp;
	}

	public static EpisodeSymptom createSeverity(Episode ep, Symptom symp) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Input the severity of the symptom in a scale from 0 to 10: ");
		Integer sev = getPositiveInteger(reader);

		EpisodeSymptom epsymp = new EpisodeSymptom(ep, symp, sev);
		reader.close();
		return epsymp;
	}

	public static void ListDiets() {
		System.out.println("                  	DEFINITION OF DIETS                        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Normal: do not have any specified diet");
		System.out.println("Mediterranean: seafood, fish, vegetables, fruits, whole grains");
		System.out.println("High protein: high protein foods like meat or eggs");
		System.out.println("High protein vegetarian: high protein foods excluding meat");
		System.out.println("High protein vegan: high protein foods which fulfills vegan diet");
		System.out.println("Gluten free: diet which exclude gluten foods");
		System.out.println("Lactose free: diet which exclude lactose products");
		System.out.println("Dairy free: diet which exclude dairy products");
		System.out.println("Ketogenic: diet which is rich in fats and allow small amount of carbohydrates");
		System.out.println(
				"Ketogenic vegetarian: diet which is rich in fats and allow small amount of carbohydrates and fulfills vegetarian diet");
		System.out.println(
				"Ketogenic vegan: diet which is rich in fats and allow small amount of carbohydrates and fulfills vegan diet");
		System.out.println("Vegan: standard vegan diet");
		System.out.println("Vegetarian: standard vegetarian diet");

	}

	public static void ListLifestyle() {
		System.out.println("                  	DEFINITION OF LIFESTYLES                        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Sedentary: do not play any sports");
		System.out.println("Low: practice no impact sports (yoga, pilates...)");
		System.out.println("Medium: practice low impact sports (walking, swimmming...)");
		System.out.println("High: practice high impact sports ");
	}

	public static void ListAllergies() {
		System.out.println("               DEFINITION OF ALLERGIES AND INTOLERANCES               ");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Egg");
		System.out.println("Fish");
		System.out.println("Animal milk");
		System.out.println("Nuts (almonds, peanuts, seeds, walnuts, pistachios, hazelnuts, cashews...)");
		System.out.println("Legums (green peas, garbanzo peas...)");
		System.out.println("Soja");
		System.out.println("Wheat");
		System.out.println("Vegetables (tomatp, carrot, lettuce");
		System.out.println("Fruits (melon, pineapple, strawberry, kiwi, peach...");
		System.out.println("High-histamine food (wine, cheese, pickles, sardine...");

	}
}
