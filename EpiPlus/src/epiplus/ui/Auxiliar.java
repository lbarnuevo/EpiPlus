package epiplus.ui;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import epiplus.pojos.*;

public class Auxiliar {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
				ex.printStackTrace();
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
			System.out.println("\n\nWhat lifestyle would you say you follow?\nIntroduce one of the previous options. ");
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
			System.out.println("\n\nWhat diet would you say you follow?\nIntroduce one of the previous options.");
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
			System.out.println("\n\nDo you have any allergies? \nIntroduce one or more of the options above.");
			allergyName = getString(reader);

			if (allergyName.equalsIgnoreCase("chickpea")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("tahini")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("cumin")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("soybean")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("tofu")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("cinnamon")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("oat")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("lemon")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("olive")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("tomato")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("onion")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("red bell pepper")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("pepper")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("garlic")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("paprika")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("lettuce")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("cucumber")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("chili")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("egg")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("feta cheese")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("cow milk")) {
				read = true;
			} else if (allergyName.equalsIgnoreCase("baking soda")) {
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
		System.out.println("(Some ideas: temporary confusion, stiff muscles, lose of consciousness or awareness, "
				+ "staring spell, uncontrollable movement of legs and arms...)");
		String name = getString(reader);

		Symptom symp = new Symptom(name);
		return symp;
	}

	public static EpisodeSymptom createSeverity(Episode ep, Symptom symp) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Input the severity of the symptom in a scale from 0 to 10: ");
		Integer sev = getPositiveInteger(reader);

		EpisodeSymptom epsymp = new EpisodeSymptom(ep, symp, sev);
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
		System.out.println("--------------------------------------------------------------------");
		
		System.out.println("Sedentary: do not play any sports");
		System.out.println("Low: practice no impact sports (yoga, pilates...)");
		System.out.println("Medium: practice low impact sports (walking, swimmming...)");
		System.out.println("High: practice high impact sports ");
	}

	public static void ListAllergies() {
		System.out.println("               DEFINITION OF ALLERGIES AND INTOLERANCES               ");
		System.out.println("----------------------------------------------------------------------");
		
		System.out.println("LEGUMS/SEEDS/SPICES:");
		System.out.println("Chickpea");
		System.out.println("Tahini");
		System.out.println("Cumin");
		System.out.println("Soybean");
		System.out.println("Tofu");
		System.out.println("Cinnamon");
		
		System.out.println("CEREALS:");
		System.out.println("Oat");
		
		System.out.println("FRUITS:");
		System.out.println("Lemon");
		System.out.println("Olive");
		System.out.println("Tomato");
		
		System.out.println("VEGETABLES:");
		System.out.println("Onion");
		System.out.println("Red bell pepper");
		System.out.println("Pepper");
		System.out.println("Garlic");
		System.out.println("Paprika");
		System.out.println("Lettuce");
		System.out.println("Cucumber");
		System.out.println("Chili");
		
		System.out.println("ANIMAL PRODUCTS:");
		System.out.println("Egg");
		System.out.println("Feta cheese");		
		System.out.println("Cow milk");

		System.out.println("OTHERS:");
		System.out.println("Baking soda");
	}

	public static void showHummus() {
		System.out.println("\n       HUMMUS RECIPE        ");
		System.out.println("\nTake chickpeas and place it in a large bowl. Add plenty of water and soak overnight.");
		System.out.println("When ready, drain chickpeas and place them in a medium-sized heavy cooking pot. Cover with water by about 2 inches.");
		System.out.println("Bring to a boil, then reduce heat and simmer for up to 2 hours.");
		System.out.println("Cover the cooked chickpeas in hot water and add baking soda.");
		System.out.println("Leave for a few minutes.");
		System.out.println("Take a handful of chickpeas and rub under running water to remove the skins.");
		System.out.println("Let the chickpeas cool completely.");
		System.out.println("Puree the chickpeas in a food processor.");
		System.out.println("Add the rest and keep blending until its consistency is smooth.");
		System.out.println("Transfer to a serving bowl and garnish with for example parsley or paprika./n");
	}
	
	public static void showShakshuka() {
		System.out.println("\n       SHAKSHUKA RECIPE        ");
		System.out.println("\nHeat olive oil in a large saute pan on medium heat.");
		System.out.println("Add the chopped bell pepper and onion and cook for 5 minutes or until the onion becomes translucent.");
		System.out.println("Add garlic and spices and cook an additional minute");
		System.out.println("Pour the can of tomatoes and juice into the pan and break down the tomatoes using a large spoon.");
		System.out.println("Season with salt and pepper and bring the sauce to a simmer");
		System.out.println("Use your large spoon to make small wells in the sauce and crack the eggs into each well.");
		System.out.println("Cover the pan and cook for 5-8 minutes, or until the eggs are done to your liking");
		System.out.println("Optionally garnish with chopped cilantro and parsley./n");
	}
	
	public static void showFetaSalad() {
		System.out.println("\n       FETA SALAD RECIPE        ");
		System.out.println("\nChop half a lettuce into small pieces. Do the same with 2 tomatoes, 1 cucumber and a feta cheese");
		System.out.println("Mix everything together in a bowl");
		System.out.println("Use the spices you like for example: oregano, salt, pepper");
		System.out.println("Optionally you can add 1 table spoon of olive oil./n");
	}
	
	public static void showChineseNoodles() {
		System.out.println("\n       CHINESE NOODLES RECIPE        ");
		System.out.println("\nDice 2 onions and fry on oil you like, for example sesame oil.");
		System.out.println("Boil noodles.");
		System.out.println("Add soy sauce");
		System.out.println("Slice red bell pepper and other greens of your choice");
		System.out.println("Dice tofu and add to the veggies.");
		System.out.println("Add paprika.");
		System.out.println("Add noodles to the rest and fry for 2 minutes./n");
	}
	
	public static void showOatmeal() {
		System.out.println("\n       OATMEAL RECIPE        ");
		System.out.println("\nBoil oats in milk.");
		System.out.println("Add cinnamon.");
		System.out.println("You can add anything you want, nuts, goji berries, dates etc. It is recommended to add fresh fruit.");
		System.out.println("Mix everything and boil until oats are soft./n");
	}
	
}
