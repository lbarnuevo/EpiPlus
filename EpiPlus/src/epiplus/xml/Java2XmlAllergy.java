package epiplus.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import epiplus.pojos.Allergy;


public class Java2XmlAllergy {
	
	
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	private static void printAllergies() {
		Query q1 = em.createNativeQuery("SELECT * FROM allergies", Allergy.class);
		List<Allergy> allergies = (List<Allergy>) q1.getResultList();
		// Print the departments
		for (Allergy allergy : allergies) {
			System.out.println(allergy);
		}
	}
	
	
	
	
			
			
			
	public static void main(String[] args) throws Exception {
		
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Allergy.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		
		
		
		
		// Choose his new department TO CHANGE
		printAllergies();
		
		System.out.print("Choose an allergy to turn into an XML file:");
		int allergy_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM allergies WHERE id = ?", Allergy.class);
		q2.setParameter(1, allergy_id);
		Allergy allergy = (Allergy) q2.getSingleResult();
		
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./xmls/Allergy.xml");
		
		marshaller.marshal(allergy, file);
		// Printout
		marshaller.marshal(allergy, System.out);

		
		
	}
	
	
	
	
	
	

}
