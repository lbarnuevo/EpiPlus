package epiplus.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

		//??
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
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	//PRIVATE?
	public void newUser(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name = ?", Role.class);
		q.setParameter(1, name);
		return (Role) q.getSingleResult();
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		//returns an user if there is a match, null if there isn't
		return null;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
		q.setParameter(1, email);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			q.setParameter(2, digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			u = (User) q.getSingleResult();
		} catch (NoResultException e) {}
		return u;
	}
}
