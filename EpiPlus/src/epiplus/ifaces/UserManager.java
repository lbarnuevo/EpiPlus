package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Role;
import epiplus.pojos.User;

public interface UserManager {

	public void disconnect();
	public void newUser(User u);
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String email, String passwd); // returns an user if there is a match, null if there isn't

	public Boolean checkEmail(String email);
	public void deleteUser(String mail);
	public void updatePassword(String mail, String newPass, String oldPass);
	public void forgotPassword(String mail, String newpass);
}
