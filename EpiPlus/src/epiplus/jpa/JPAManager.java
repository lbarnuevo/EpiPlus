package epiplus.jpa;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAManager {
	
	public static void main (String[]args) throws IOException{

	EntityManager em= Persistence.createEntityManagerFactory("epiplus-provider").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON");
	em.getTransaction().commit();
}
}
