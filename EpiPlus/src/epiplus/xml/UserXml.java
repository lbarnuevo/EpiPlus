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

import epiplus.pojos.User;

public class UserXml {

	
	
	
		
		//Create .xml file from java file
				
		public static void user2Xml(User user) throws JAXBException {
			
			
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			
		
			// Use the Marshaller to marshal the Java object to a file
			File file = new File("./xmls/External-User.xml");
			
			marshaller.marshal(user, file);
			
			
		}
		
		//Create .java file from xml file
		
		public static User xml2User() throws JAXBException {

		


				// Create the JAXBContext
				JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
				// Get the unmarshaller
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

				// Use the Unmarshaller to unmarshal the XML document from a file
				File file = new File("./xmls/External-User.xml");
				User user = (User) unmarshaller.unmarshal(file);
				
				return user;
				
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
