package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.EmergencyContactManager;
import epiplus.pojos.EmergencyContact;
import epiplus.pojos.Symptom;

public class JDBCEmergencyContactManager implements EmergencyContactManager{

	private JDBCManager manager;

	public JDBCEmergencyContactManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void addEmergencyContact(EmergencyContact c) {
		try {
			String sql = "INSERT INTO emergencycontact (name,number) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, c.getName());
			prep.setFloat(1, c.getNumber());
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
	public List<EmergencyContact> listsAllEmergencyContacts() {
		
		List<EmergencyContact> emergencyContactsList = new ArrayList<EmergencyContact>();

		try {
			String sql = "SELECT * FROM emergencycontact";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				String  name= r.getString("name");
				Float number= r.getFloat("number");
				EmergencyContact contact = new EmergencyContact(id,name, number);
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
