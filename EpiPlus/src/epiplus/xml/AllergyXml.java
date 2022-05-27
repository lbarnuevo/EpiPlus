package epiplus.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import epiplus.pojos.Allergy;

public class AllergyXml {

	
	
	
		
		
				
		public static void allergy2Xml(Allergy allergy) throws JAXBException {
			
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Allergy.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			
		
			// Use the Marshaller to marshal the Java object to a file
			File file = new File("./xmls/Allergy.xml");
			
			marshaller.marshal(allergy, file);
			
			
		}
		
		public static Allergy Xml2Allergy() throws JAXBException {

		


				// Create the JAXBContext
				JAXBContext jaxbContext = JAXBContext.newInstance(Allergy.class);
				// Get the unmarshaller
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

				// Use the Unmarshaller to unmarshal the XML document from a file
				File file = new File("./xmls/External-Allergy.xml");
				Allergy allergy = (Allergy) unmarshaller.unmarshal(file);
				
				return allergy;
				
		}	
		
		
		
		/**
		 * Simple transformation method. You can use it in your project.
		 * @param sourcePath - Absolute path to source xml file.
		 * @param xsltPath - Absolute path to xslt file.
		 * @param resultDir - Directory where you want to put resulting files.
		 */
		
		
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
