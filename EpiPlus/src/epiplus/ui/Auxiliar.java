//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;

import epiplus.pojos.Doctor;

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
	//Function to ask for a Byte to the user
    public static Byte getByte(String txt) {
    	System.out.print(txt);
    	Byte info = 0;
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
    
    public static Doctor askdocinfo() {
    	//TO DO 
    	//the photo is always necessary? or it can be optional?
    	//because in the constructors of doctor and in the function to addoctor of the jbdc of doctor is always necessary
    	//so we would need to create that
    	//in the meantime im gonna do as required
    	Doctor doc;
    	String name = getString("\nName: ");
    	String hospital = getString("Hospital name: ");
    	String email = getString("Email: ");
    	byte photo = getByte("Photo: ");
    	
    	doc = new Doctor();
    	doc = new 
    	
    }
    
    
}
