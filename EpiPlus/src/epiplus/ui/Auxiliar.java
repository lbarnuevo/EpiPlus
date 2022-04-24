//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;

import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public class Auxiliar {

	//Function to ask for an Integer to the user
    public static int getInteger(String txt){
        System.out.print(txt);
        int N=0;
        try{
            BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
            String leido = consol.readLine();
            N = Integer.parseInt(leido);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
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
        Double N=0.0;
        try{
            BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
            String leido = consol.readLine();
            N = Double.parseDouble(leido);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return N;
    }
    
	//Function to ask for a Float to the user
    public static Float getFloat(String txt){
        System.out.print(txt);
        Float N=0F;
        try{
            BufferedReader consol = new BufferedReader(new InputStreamReader(System.in));
            String leido = consol.readLine();
            N = Float.parseFloat(leido);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
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
                confirmation = getString("Por favor introduzca S/N: ");
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
    	Integer age = getInteger("\nAge: ");
		Float height = getFloat("\nHeight: ");
		Float weight = getFloat("\nWeight: ");
		String lifestyle = getString("\nLifestyle: ");
		String diet = getString("\nDiet: ");
		Integer exercise = getInteger("\nHow many times a week do you exercise? ");
    	byte[] photo = getByte("Photo: ");//Im not sure how to ask for the photo

		
		patient = new Patient();
		patient = new Patient(name, age, height, weight, lifestyle, diet, exercise, photo);
		return patient;
    }
    
    public static Patient askpatientinfo() {//Patient info - photo
    	Patient patient;
    	String name = getString("\nName: ");
    	Integer age = getInteger("\nAge: ");
		Float height = getFloat("\nHeight: ");
		Float weight = getFloat("\nWeight: ");
		String lifestyle = getString("\nLifestyle: ");
		String diet = getString("\nDiet: ");
		Integer exercise = getInteger("\nHow many times a week do you exercise? ");
		
		patient = new Patient();
		patient = new Patient(name, age, height, weight, lifestyle, diet, exercise);
		return patient;
    }
    
}
