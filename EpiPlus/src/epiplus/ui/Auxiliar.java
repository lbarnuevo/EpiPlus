//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;
import java.util.Date;
import epiplus.pojos.*;

public class Auxiliar {

	//TODO @lucia do the list of diet, I think we also need to do it for mood and place(work, home...)
	//TODO @luciabarnuevo change in methods to parse the buffer (when marta finishes menu) 
	
	//Function to ask for an Integer to the user
    public static int getPositiveInteger(String txt){
        System.out.print(txt);
        boolean read = false;
        int N = -1;
        
        do {
        	try {
        		BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
                N = Integer.parseInt(consol.readLine());
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

	//Function to ask for a String to the user
    public static String getString(String txt){ //TODO discuss deleting this method 
        System.out.print(txt);
        String leido = null;
        try{
            BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
            leido = consol.readLine();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return leido;
    }
    
	//Function to ask for a positive Float to the user
    public static Float getPositiveFloat(String txt){
        System.out.print(txt);
        boolean read = false;
        Float N = -1.0f;
        do {
        	try{
                BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
                N = Float.parseFloat(consol.readLine());
                if(N>=0.0) { //this is done to make sure that N has the correct format
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
    
	//Function to ask for a Byte to the user
    public static byte[] getByte(String txt) {
    	System.out.print(txt);
    	byte[] bytesBlob = null;
    	
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	System.out.print("Type the file name as it appears in folder /photos, including extension: ");
    		String fileName = br.readLine();
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
    		lifestyle = getString("");
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
    		diet = getString("");
    		read = true;
    		//TODO add diets 
    		
    	} while(read == false);
 
    	return diet; 
    }
    
    //Ask for confirmation on something
    public static Boolean askConfirmation(){
        boolean confir = false;
        System.out.println("Would you like to continue?");
    	String confirmation = getString("(Yes --> Y / No --> N)");
        while (true) {
            if ("Y".equalsIgnoreCase(confirmation)) {
                confir = true;
                break;
            } else if ("N".equalsIgnoreCase(confirmation)) {
                confir = false;
                break;
            } else {
                confirmation = getString("Please introduce Y/N: ");
            }
        }
        return confir;
    }
    
    public static Doctor createDoctor(){
    	System.out.println("Input doctor information");
    	System.out.println("");
    	
    	System.out.println("Name: ");
    	String name = getString("");
    	
    	System.out.println("Hospital name: ");
    	String hospital = getString("");
    	
    	System.out.println("Email: ");
    	String email = getString("");
    	
    	System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
    	boolean confirmation = askConfirmation();
    	
    	byte[] photo = null;
    	if( confirmation == true) {
    		photo = getByte("Photo: ");//Need to change method to input bufferedreader  
    	}
    	
    	Doctor doc = new Doctor(name, email, hospital, photo);
		return doc;
    }
    
    public static Patient createPatient(){
    	System.out.println("Input patient information");
    	System.out.println("");
    	
    	System.out.println("Name: ");
    	String name = getString("");
    	
    	System.out.println("Age: ");
    	Integer age = getPositiveInteger("Age: "); //need to change method 
    	
    	System.out.println("Height: ");
    	Float height = getPositiveFloat("");
    	
    	System.out.println("Weight: ");
    	Float weight = getPositiveFloat("");
    	
    	System.out.println("Lifestyle: ");
    	String lifestyle = getLifeStyle();
    	
    	System.out.println("Diet: ");
    	String diet = getDiet();
    	
    	System.out.println("Exercise per week (how many hours): ");
    	Integer exercise = getPositiveInteger("");
    	
    	System.out.println("Do you want to add a photo? (Yes --> Y / No --> N)");
    	boolean confirmation = askConfirmation();
    	
    	byte[] photo = null;
    	if( confirmation == true) {
    		photo = getByte("Photo: ");//Need to change method to input bufferedreader 
    		Patient p = new Patient(name, age, height, weight, lifestyle, diet, exercise, photo);
    		return p;
    		
    	}
    	
    	Patient p = new Patient(name, age, height, weight, lifestyle, diet, exercise, photo);
		return p;
    }
    
    //episode info
    public static Episode createEpisode(){
		System.out.println("Input the episode's information: ");
		System.out.println("");
		
		System.out.println("Date of the episode: ");
		System.out.println("Day(d): ");
		Integer day = getPositiveInteger("");
		System.out.println("Month (m): ");
		Integer month = getPositiveInteger("");
		System.out.println("Year (yyyy): ");
		Integer year = getPositiveInteger("");
		Date doe = new Date(year, month, day);  
		
		System.out.println("Episode length: ");
		Float length = getPositiveFloat(""); 
		
		System.out.println("Add previous activity: ");
		String activity = getString("");//TODO add types of activity
		
		System.out.println("Mood: ");
		String mood = getString("");
		
		System.out.println("Place: ");
		String place = getString("");
		
		System.out.println("Previous meal: ");
		String previous_meal = getString("");
		
		System.out.println("Did you had any injury?(Yes --> Y / No --> N): ");
		Boolean injuries = askConfirmation();
		
		Episode ep = new Episode(doe, length, activity, mood, place, previous_meal, injuries);
		return ep;
    }
    
    //ask symptoms information
    public static Symptom createSymptom() { 
    	System.out.println("Symptoms name: ");
    	String name = getString("");
    	
    	Symptom symp = new Symptom(name);
    	return symp;
    }
    
    public static EpisodeSymptom createSeverity( Episode ep, Symptom symp) {    	
    	System.out.println("Input the severity of the symptom in a scale from 0 to 10: ");
    	Integer sev = getPositiveInteger("");
    	
    	EpisodeSymptom epsymp= new EpisodeSymptom(ep,symp,sev);
    	return epsymp;
    }
    
    
    public static void ListDiets() {
    	//TODO add diets
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
