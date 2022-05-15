package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Role;
import epiplus.pojos.User;

public interface UserManager {

	public void disconnect();
	public void newUser(User u);
	public Role getRole(String name);
	public List<Role> getRoles();
	public User checkPassword(String email, String passwd); // returns an user if there is a match, null if there isn't
}
