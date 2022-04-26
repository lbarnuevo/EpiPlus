//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;

import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public class Auxiliar {

	//TODO @lucia do the list of diet....
	//TODO @someone ask for a photo in patient and doctor
	
	//Function to ask for an Integer to the user
    public static int getIntegerBiggerThanCero(String txt){
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
        String leido = "";
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
    
	//Function to ask for a Float to the user
    public static Float getFloat(String txt){
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
    
	//Function to ask for a Byte to the user --> not correct
    public static Byte[] getByte(String txt) {
    	System.out.print(txt);
    	Byte[] info;// = 0;
        try{
            BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
            String leido = consol.readLine();
            info = Byte.parseByte(leido);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    	return info;
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
    	
    	doc = new Doctor();
    	doc = new Doctor(name, email, hospital, photo);
    	return doc;
    }
    
    public static Doctor askdocinfo() {//Doctor info - photo
    	Doctor doc;
    	String name = getString("\nName: ");
    	String hospital = getString("Hospital name: ");
    	String email = getString("Email: ");
    	
    	doc = new Doctor();
    	doc = new Doctor(name, email, hospital);
    	return doc;
	}
    
    public static Patient askallpatientinfo() {//Patient info + photo
    	Patient patient;
    	String name = getString("\nName: ");
    	Integer age = getIntegerBiggerThanCero("\nAge: ");
		Float height = getFloat("\nHeight: ");
		Float weight = getFloat("\nWeight: ");
		String lifestyle = getString("\nLifestyle: ");
		String diet = getString("\nDiet: ");
		Integer exercise = getIntegerBiggerThanCero("\nHow many times a week do you exercise? ");
    	byte[] photo = getByte("Photo: ");//Im not sure how to ask for the photo

		
		patient = new Patient();
		patient = new Patient(name, age, height, weight, lifestyle, diet, exercise, photo);
		return patient;
    }
    
    public static Patient askpatientinfo() {//Patient info - photo
    	Patient patient;
    	String name = getString("\nName: ");
    	Integer age = getIntegerBiggerThanCero("\nAge: ");
		Float height = getFloat("\nHeight: ");
		Float weight = getFloat("\nWeight: ");
		String lifestyle = getString("\nLifestyle: ");
		String diet = getString("\nDiet: ");
		Integer exercise = getIntegerBiggerThanCero("\nHow many times a week do you exercise? ");
		
		patient = new Patient();
		patient = new Patient(name, age, height, weight, lifestyle, diet, exercise);
		return patient;
    }
    
}
