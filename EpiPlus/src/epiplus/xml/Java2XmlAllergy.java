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
		

	
	
	
			
			
			
	public  void allergy2xml() throws Exception {
		
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Allergy.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		
	
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./xmls/Allergy.xml");
		
		marshaller.marshal(allergy, file);
		
		
	}
	
	
	
	
	
	

}
