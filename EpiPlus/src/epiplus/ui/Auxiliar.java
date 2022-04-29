//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;
import java.util.Date;

import epiplus.pojos.Doctor;
import epiplus.pojos.Episode;
import epiplus.pojos.EpisodeSymptom;
import epiplus.pojos.Patient;
import epiplus.pojos.Symptom;

public class Auxiliar {

	//TODO @lucia do the list of diet, I think we also need to do it for mood and place(work, home...)
	//TODO @someone ask for a photo in patient and doctor
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
    public static String getString(String txt){
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
    
	//Function to ask for an Double to the user --> We use Doubles??
    public static Double getDouble(String txt){
        System.out.print(txt);
        boolean read = false;
        Double N=0.0;
        
        do {
        	try{
                BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
                N = Double.parseDouble(consol.readLine());
                if(N>0.0) { //this is done to make sure that N has the correct format
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
    	String fileName = null;
    	byte[] bytesBlob = null;
        try{
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
    
    //Ask for confirmation on something
    public static Boolean askconfirmation(){
        boolean confir = false;
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
    
    public static Doctor askalldocinfo() {//Doctor info + photo
    	Doctor doc;
    	String name = getString("\nName: ");
    	String hospital = getString("Hospital name: ");
    	String email = getString("Email: ");
    	byte[] photo = getByte("Photo: ");//Im not sure how to ask for the photo
    	
    	//doc = new Doctor();
    	doc = new Doctor(name, email, hospital, photo);
    	return doc;
    }
    
    public static Doctor askdocinfo() {//Doctor info - photo
    	Doctor doc;
    	String name = getString("\nName: ");
    	String hospital = getString("Hospital name: ");
    	String email = getString("Email: ");
    	
    	//doc = new Doctor();
    	doc = new Doctor(name, email, hospital);
    	return doc;
	}
    
    public static Patient askallpatientinfo() {//Patient info + photo
    	Patient patient;
    	String name = getString("\nName: ");
    	Integer age = getPositiveInteger("Age: ");
		Float height = getPositiveFloat("Height: ");
		Float weight = getPositiveFloat("Weight: ");
		String lifestyle = getString("Lifestyle: ");
		String diet = getString("Diet: ");
		Integer exercise = getPositiveInteger("How many times a week do you exercise? ");
    	byte[] photo = getByte("Photo: ");//Im not sure how to ask for the photo

		
		//patient = new Patient();
		patient = new Patient(name, age, height, weight, lifestyle, diet, exercise, photo);
		return patient;
    }
    
    public static Patient askpatientinfo() {//Patient info - photo
    	Patient patient;
    	String name = getString("\nName: ");
    	Integer age = getPositiveInteger("Age: ");
		Float height = getPositiveFloat("Height: ");
		Float weight = getPositiveFloat("Weight: ");
		String lifestyle = getString("Lifestyle: ");
		String diet = getString("Diet: ");
		Integer exercise = getPositiveInteger("How many times a week do you exercise? ");
		
		//patient = new Patient();
		patient = new Patient(name, age, height, weight, lifestyle, diet, exercise);
		return patient;
    }
    
    //episode info
    public static Episode askepinfo() {
    	Episode ep;
		Date doe;
		System.out.println("\nInput the episode's information: ");
		Float length = getPositiveFloat("\nLength: "); 
		String activity = getString("Activity: ");//TODO add types of activity
		String mood = getString("Mood: ");
		String place = getString("Place: ");
		String previous_meal = getString("Previous meal: ");
		System.out.println("Did you had any injury?");
		Boolean injuries = askconfirmation();
		System.out.println("When did it occur?");
		Integer day = getPositiveInteger("\nDay: ");
		Integer month = getPositiveInteger("\nMonth: ");
		Integer year = getPositiveInteger("\nYear: ");
		doe = new Date(year, month, day);
		
		//ep = new Episode();
		ep = new Episode(doe, length, activity, mood, place, previous_meal, injuries);
		
		return ep;
    }
    
    //ask symptoms information
    public static Symptom asksympinfo() {
    	Symptom symp;
    	System.out.println("\nInput the symptom's information: ");
    	String name = getString("\nName: ");
    	symp = new Symptom(name);
    	return symp;
    }
    
    public static EpisodeSymptom askepsympinfo( Episode ep, Symptom symp) {
    	EpisodeSymptom epsymp;
    	Integer sev = getPositiveInteger("\nInput the severity of the symptom in a scale from 0 to 10: ");
    	epsymp= new EpisodeSymptom(ep,symp,sev);
    	return epsymp;
    }
    
    
    
    
    
}
