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

	private void connect() {
		em = Persistence.createEntityManagerFactory("epiplus-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		// Insert the roles needed only if they are not there already
		if (this.getRoles().isEmpty()) { 
			Role doctor = new Role("doctor"); 
			Role patient = new Role("patient"); 
			this.newRole(doctor); 
			this.newRole(patient);
		}
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
	
	private void newRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(int id) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE id = ?", Role.class);
		q.setParameter(1, id);
		return (Role) q.getSingleResult();
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
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
	
	@Override
	public Boolean checkEmail(String email) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ?", User.class);
			q.setParameter(1, email);
			User temp = (User)q.getSingleResult();
			if(temp.getEmail().equalsIgnoreCase(email)) {
				return true;
			}else {
				return false;
			}
		}catch(NoResultException nre) {
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteUser(String email) {
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ?", User.class);
		q.setParameter(1, email);
		User u = (User) q.getSingleResult();
		
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}
	
	@Override
	public void updatePassword(String mail, String newPass, String oldPass) {
		try {
 			MessageDigest md = MessageDigest.getInstance("MD5");
 			md.update(oldPass.getBytes());
 			byte[] hash = md.digest();
 			
 			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
 			q.setParameter(1, mail);
 			q.setParameter(2, hash);
 			
 			User u = (User) q.getSingleResult();
 			MessageDigest md2 = MessageDigest.getInstance("MD5");
 			md2.update(newPass.getBytes());
 			byte[] hash2 = md2.digest();

 			em.getTransaction().begin();
 			u.setPassword(hash2);
 			em.getTransaction().commit();
 			
 		}catch(Exception e) {
 			System.out.println("The current password is not correct"); 
 		}
	}
	
	@Override
	public void forgotPassword(String mail, String newpass) {
		try {
 			MessageDigest md = MessageDigest.getInstance("MD5");
 			md.update(newpass.getBytes());
 			byte[] hash = md.digest();
 			
 			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? ", User.class);
 			q.setParameter(1, mail);
 			q.setParameter(2, hash);
 			
 			User u = (User) q.getSingleResult();

 			em.getTransaction().begin();
 			u.setPassword(hash);
 			em.getTransaction().commit(); 
 		}catch(Exception e) {
 			System.out.println("The current password is not correct");
 		}
	}

}
