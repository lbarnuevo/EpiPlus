package epiplus.xml;


import java.io.File;




import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import epiplus.pojos.Patient;

public class PatientXml {

	
	
	
		
		//Create .xml file from java file
				
		public static void patient2Xml(Patient patient) throws JAXBException {
			
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			
		
			// Use the Marshaller to marshal the Java object to a file
			File file = new File("./xmls/External-Patient.xml");
			
			marshaller.marshal(patient, file);
			
			
		}
		
		//Create .java file from xml file
		
		public static Patient xml2Patient() throws JAXBException {

		


				// Create the JAXBContext
				JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
				// Get the unmarshaller
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

				// Use the Unmarshaller to unmarshal the XML document from a file
				File file = new File("./xmls/External-Patient.xml");
				Patient patient = (Patient) unmarshaller.unmarshal(file);
				
				return patient;
				
		}	
		
		
		
		/**
		 * Simple transformation method. You can use it in your project.
		 * @param sourcePath - Absolute path to source xml file.
		 * @param xsltPath - Absolute path to xslt file.
		 * @param resultDir - Directory where you want to put resulting files.
		 */
		
		//From .xslt file create .html file using .xml file
		
		public static void xslt2Html(String sourcePath, String xsltPath,String resultDir) {
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			try {
				Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
				transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//public static void main(String[] args) {
			//simpleTransform("./xmls/External-Allergy.xml", "./xmls/Allergy-Style.xslt", "./xmls/External-Allergy.html");

		//}
		
}
