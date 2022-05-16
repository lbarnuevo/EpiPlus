package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.EmergencyContactManager;
import epiplus.pojos.EmergencyContact;

public class JDBCEmergencyContactManager implements EmergencyContactManager {

	private JDBCManager manager;

	public JDBCEmergencyContactManager(JDBCManager m) {
		this.manager = m;
	}

	//TODO assign patient 
	
	@Override
	public void addEmergencyContact(EmergencyContact c) {
		try {
			String sql = "INSERT INTO emergencycontact (name,number,patientId) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, c.getName());
			prep.setFloat(2, c.getNumber());
			prep.setInt(3, c.getPatient().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmergencyContact(EmergencyContact c) {
		try {
			String sql = "DELETE FROM emergencycontact WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, c.getId());
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<EmergencyContact> getEmergencyContactsOfPatient(Integer pacId) {

		List<EmergencyContact> contactsList = new ArrayList<EmergencyContact>();

		try {
			String sql = "SELECT * FROM emergencycontact WHERE patientId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pacId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Float number = rs.getFloat("number");
				EmergencyContact emergencyContact = new EmergencyContact(id, name, number);
				contactsList.add(emergencyContact);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return null;//this function should return a patirnt so why is it returning a
		// null? --> I'm(Marta) going to change it
		return contactsList;
	}

	@Override
	public void updateEmergencyContact(EmergencyContact c) {
		try {
			String sql = "UPDATE emergencycontacts" + " SET name=?" + " number=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setFloat(2, c.getNumber());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EmergencyContact> listsAllEmergencyContacts() {

		List<EmergencyContact> emergencyContactsList = new ArrayList<EmergencyContact>();

		try {
			String sql = "SELECT * FROM emergencycontact";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				String name = r.getString("name");
				Float number = r.getFloat("number");
				EmergencyContact contact = new EmergencyContact(id, name, number);
				emergencyContactsList.add(contact);
			}
			r.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
