//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;
import java.util.Date;
import epiplus.pojos.*;

public class Auxiliar {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	//TODO @lucia do the list of diet, I think we also need to do it for mood and place(work, home...) 
	
    public static int getPositiveInteger(){
        boolean read = false;
        int N = -1;
        
        do {
        	try {
                N = Integer.parseInt(reader.readLine());
                if(N>=0) {
                	read = true;
                } else {
                	System.out.println("Error. Introduce a number bigger than cero:");
                }
        	} catch(IOException ex) {        		
                System.out.println("Reading error");
            } catch (NumberFormatException ex) { 
            	System.out.println("Error. Introduce a number:");
            }
        } while(read == false);
        return N;
    }

    public static String getString(){
        String leido = null;
        try{
            leido = reader.readLine();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return leido;
    }
    
    public static Float getPositiveFloat(){
        boolean read = false;
        Float N = -1.0f;
        do {
        	try{
                N = Float.parseFloat(reader.readLine());
                if(N>=0.0) {
                	read = true;
                }
            } catch(IOException ex){
                System.out.println("Reading error");
            } catch (NumberFormatException ex) { 
            	System.out.println("Error. Introduce a number:");
            }
        } while(read == false);
        return N;
    }
    
    public static byte[] getByte() {
    	byte[] bytesBlob = null;
        try{
        	System.out.print("Type the file name as it appears in folder /photos, including extension: ");
    		String fileName = getString();
            File photo = new File("./photos/" + fileName);
			InputStream streamBlob = new FileInputStream(photo);
			bytesBlob = new byte[streamBlob.available()];
			streamBlob.read(bytesBlob);
			streamBlob.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    	return bytesBlob;
    }
    
    public static String getLifeStyle() { 
    	ListLifestyle();
    	String lifestyle = null; 
    	boolean read = false; 
    	
    	do {
    		System.out.println("What life style would you say you follow? \nIntroduce one of the previous options. ");
    		lifestyle = getString();
    		if(lifestyle.equalsIgnoreCase("sedentary")) {
    			read = true;
    		} else if(lifestyle.equalsIgnoreCase("low")) {
    			read = true;
    		} else if(lifestyle.equalsIgnoreCase("medium")) {
    			read = true;
    		} else if(lifestyle.equalsIgnoreCase("high")) {
    			read = true;
    		}
    		
    	} while(read == false);
 
    	return lifestyle; 
    }
    
    public static String getDiet() {
    	ListDiets();
    	String diet = null; 
    	boolean read = false; 
    	System.out.println("What diet would you say you follow? (FOR THE MOMENT IS NULL ");
    	do {
    		diet = getString();
    		read = true;
    		//TODO add diets 
    		
    	} while(read == false);
 
    	return diet; 
    }
    
    public static Boolean askConfirmation(){
        boolean confir = false;
    	String confirmation = getString();

        while (true) {
            if ("Y".equalsIgnoreCase(confirmation)) {
                confir = true;
                break;
            } else if ("N".equalsIgnoreCase(confirmation)) {
                confir = false;
                break;
            } else {
            	System.out.println("Please introduce Y/N: ");
                confirmation = getString();
            }
        }
        return confir;
    }
    
    public static Doctor createDoctor(){
    	System.out.println("Input doctor information");
    	System.out.println("");
    	
    	System.out.println("Name: ");
    	String name = getString();
    	
    	System.out.println("Hospital name: ");
    	String hospital = getString();
    	
    	System.out.println("Email: ");
    	String email = getString();
    	
    	System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
    	boolean confirmation = askConfirmation();
    	
    	byte[] photo = null;
    	if( confirmation == true) {
    		photo = getByte(); 
    	}
    	
    	Doctor doc = new Doctor(name, email, hospital, photo);
		return doc;
    }
    
    public static Patient createPatient(){    	
    	System.out.println("Input patient information");
    	System.out.println("");
    	
    	System.out.println("Name: ");
    	String name = getString();
    	
    	System.out.println("Age: ");
    	Integer age = getPositiveInteger(); 
    	
    	System.out.println("Height: ");
    	Float height = getPositiveFloat();
    	
    	System.out.println("Weight: ");
    	Float weight = getPositiveFloat();
    	
    	System.out.println("Lifestyle: ");
    	String lifestyle = getLifeStyle();
    	
    	System.out.println("Diet: ");
    	String diet = getDiet();
    	
    	System.out.println("Exercise per week (how many hours): ");
    	Integer exercise = getPositiveInteger();
    	
    	System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
    	boolean confirmation = askConfirmation();
    	
    	byte[] photo = null;
    	if( confirmation == true) {
    		photo = getByte();
    	}
    	
    	Patient p = new Patient(name, age, height, weight, lifestyle, diet, exercise, photo);
		return p;
    }
    
    public static Medication createMedication() {
    //public static PatientMedication createallMedStaff() {
    	System.out.println("\nInput medication information: ");
		System.out.println("");
		
		System.out.println("Input it's name: ");
		String name = getString();
		
		Medication med = new Medication(name);
		return med;
    }
    
    public static PatientMedication createPMed(Patient patient, Medication med) {
    	System.out.println("Input frequency: ");
    	Integer freq = getPositiveInteger();
    	System.out.println("Input amount: ");
    	Float amount = getPositiveFloat();
	
    	PatientMedication pmed = new PatientMedication(freq, amount, patient, med);
    	return pmed;    	
    }
    
    
    public static Episode createEpisode(){
    	System.out.println("Input the episode's information: ");
		System.out.println("");
		
		System.out.println("Date of the episode: ");
		System.out.println("Day(d): ");
		Integer day = getPositiveInteger();
		System.out.println("Month (m): ");
		Integer month = getPositiveInteger();
		System.out.println("Year (yyyy): ");
		Integer year = getPositiveInteger();
		Date doe = new Date(year, month, day);  
		
		System.out.println("Episode length: ");
		Float length = getPositiveFloat(); 
		
		System.out.println("Add previous activity: ");
		String activity = getString();//TODO add types of activity
		
		System.out.println("Mood: ");
		String mood = getString();
		
		System.out.println("Place: ");
		String place = getString();
		
		System.out.println("Previous meal: ");
		String previous_meal = getString();
		
		System.out.println("Did you had any injury?(Yes --> Y / No --> N): ");
		Boolean injuries = askConfirmation();
		
		Episode ep = new Episode(doe, length, activity, mood, place, previous_meal, injuries);
		return ep;
    }
    
    public static Symptom createSymptom() { 
    	System.out.println("Symptoms name: ");
    	String name = getString();
    	
    	Symptom symp = new Symptom(name);
    	return symp;
    }
    
    public static EpisodeSymptom createSeverity( Episode ep, Symptom symp) {    	
    	System.out.println("Input the severity of the symptom in a scale from 0 to 10: ");
    	Integer sev = getPositiveInteger();
    	
    	EpisodeSymptom epsymp= new EpisodeSymptom(ep,symp,sev);
    	return epsymp;
    }
    
    
    public static void ListDiets() {
    	//TODO add diets
    	System.out.println("                  	DEFINITION OF DIETS                        ");
        System.out.println("---------------------------------------------------------------");
    	System.out.println("Normal: do not have any specified diet");
    	System.out.println("Mediterranean: seafood, fish, vegetables, fruits, whole grains");
    	System.out.println("High protein: high protein foods like meat or eggs");
    	System.out.println("High protein vegetarian: high protein foods excluding meat");
    	System.out.println("Mediterranean: seafood, fish, vegetables, fruits, whole grains");
    	System.out.println("High protein: high protein foods like meat or eggs");
    	System.out.println("High protein vegetarian: high protein foods excluding meat");
    	System.out.println("High protein vegan: high protein foods which fulfills vegan diet");
    	System.out.println("Gluten free: diet which exclude gluten foods");
    	System.out.println("Lactose free: diet which exclude lactose products");
    	System.out.println("High protein vegan: high protein foods which fulfills vegan diet");
    	System.out.println("Gluten free: diet which exclude gluten foods");
    	System.out.println("Lactose free: diet which exclude lactose foods");
    	System.out.println("Dairy free: diet which exclude dairy products");
    	System.out.println("Ketogenic: diet which is rich in fats and allow small amount of carbohydrates");
    	System.out.println("Ketogenic vegetarian: diet which is rich in fats and allow small amount of carbohydrates and fulfills vegetarian diet");    	
    	System.out.println("Ketogenic vegan: diet which is rich in fats and allow small amount of carbohydrates and fulfills vegan diet");
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
}




