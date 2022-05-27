package epiplus.xml;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import epiplus.pojos.Allergy;
import epiplus.pojos.Patient;
import sample.db.pojos.Report;
import sample.db.pojos.Employee;

public class Xml2JavaAllergy {

	private static final String PERSISTENCE_PROVIDER = "epiplus-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Allergy.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./xmls/External-Allergy.xml");
		Allergy allergy = (Allergy) unmarshaller.unmarshal(file);

		
	}
	
}