//THIS IS A CLASS FOR FUNCTIONS THAT CAN INTERACT WITH THE USER

package epiplus.ui;

import java.io.*;

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
    
	//Function to ask for an Float to the user
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
    
}
