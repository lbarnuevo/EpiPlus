package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.SymptomManager;
import epiplus.pojos.Symptom;

public class JDBCSymptomManager implements SymptomManager{

	private JDBCManager manager;

	public JDBCSymptomManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void addSymptom(Symptom s) {
		try {
			String sql = "INSERT INTO symptoms (name) VALUES (?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, s.getName());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSymptom(Symptom s) {
		try {
			String sql = "DELETE FROM symtoms WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, s.getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Symptom getSymptomByName(String name) {

		Symptom symptom = null;

		try {
			String sql = "SELECT * FROM symptoms WHERE name LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				symptom = new Symptom(id, n);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptom;
	}
	

	@Override
	public Symptom getSymptomById(Integer sId ) {

		Symptom symptom = null;

		try {
			String sql = "SELECT * FROM symptoms WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, sId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				symptom = new Symptom(id, n);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptom;
	}
	
	
	@Override
	public List<Symptom> listsAllSymptoms() {
		
		List<Symptom> symptomsList = new ArrayList<Symptom>();

		try {
			String sql = "SELECT * FROM symptoms";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				String  name= r.getString("name");
				Symptom symptom = new Symptom(id,name);
				symptomsList.add(symptom);
			}
			r.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptomsList;
	}

}
