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

		// Print the report
		System.out.println("Allergy:");
		
		System.out.println("Id: " + allergy.getId());
		System.out.println("Name: " + allergy.getName());
		
		
		List<Patient> patients = allergy.getPatients();
		for (Patient pat : patients) {
			System.out.println("Patient: " + pat.getName());
		}

		// Store the report in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		for (Patient patient : patients) {
			em.persist(patient);
		}
		em.persist(allergy);
		
		// End transaction
		tx1.commit();
	}
	
}