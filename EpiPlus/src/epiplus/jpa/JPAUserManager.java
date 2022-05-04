package epiplus.jpa;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import epiplus.ifaces.UserManager;
import epiplus.pojos.Role;
import epiplus.pojos.User;

public class JPAUserManager implements UserManager {

	private EntityManager em;

	public JPAUserManager() {
		this.connect();
	}

	public void connect() {
		em = Persistence.createEntityManagerFactory("epiplus-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Insert the roles needed only if they are not there already
		/*
		 * if (this.getRoles().isEmpty()) { Role owner = new Role("owner"); Role vet =
		 * new Role("vet"); this.newRole(owner); this.newRole(vet); }
		 */
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public Role getRole(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}
}
