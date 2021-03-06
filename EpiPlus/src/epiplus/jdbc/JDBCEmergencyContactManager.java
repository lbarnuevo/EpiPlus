package epiplus.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.EmergencyContactManager;
import epiplus.pojos.EmergencyContact;
import epiplus.pojos.Patient;

public class JDBCEmergencyContactManager implements EmergencyContactManager {

	private JDBCManager manager;

	public JDBCEmergencyContactManager(JDBCManager m) {
		this.manager = m;
	} 
	
	@Override
	public void addEmergencyContact(EmergencyContact c) {
		try {
			String sql = "INSERT INTO emergencycontacts (name,number,patientId) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, c.getName());
			prep.setString(2, c.getNumber());
			prep.setInt(3, c.getPatient().getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmergencyContact(EmergencyContact c) {
		try {
			String sql = "UPDATE emergencycontacts" + " SET name=?," + " number=? WHERE id=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getNumber());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteEmergencyContact(EmergencyContact c) {
		try {
			String sql = "DELETE FROM emergencycontacts WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, c.getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public EmergencyContact getECbyId(Integer id) {
		EmergencyContact ec = null; 

		try {
			String sql = "SELECT * FROM emergencycontacts WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer eid = rs.getInt("id");
				String n = rs.getString("name");
				String number = rs.getString("number");
				ec = new EmergencyContact(eid, n, number);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ec;
	}
	
	@Override
	public List<EmergencyContact> getEmergencyContactsOfPatient(Integer pacId) {

		List<EmergencyContact> contactsList = new ArrayList<EmergencyContact>();

		try {
			String sql = "SELECT * FROM emergencycontacts WHERE patientId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pacId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				EmergencyContact emergencyContact = new EmergencyContact(id, name, number);
				contactsList.add(emergencyContact);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactsList;
	}
	
	@Override
	public List<EmergencyContact> listsAllEmergencyContacts() {

		List<EmergencyContact> emergencyContactsList = new ArrayList<EmergencyContact>();

		try {
			String sql = "SELECT * FROM emergencycontacts";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				String name = r.getString("name");
				String number = r.getString("number");
				EmergencyContact contact = new EmergencyContact(id, name, number);
				emergencyContactsList.add(contact);
			}
			r.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emergencyContactsList;
	}
	
}
