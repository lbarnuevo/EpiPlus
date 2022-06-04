package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.MedicationManager;
import epiplus.pojos.Medication;

public class JDBCMedicationManager implements MedicationManager {

	private JDBCManager manager;

	public JDBCMedicationManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addMedication(Medication m) {
		try {
			String sql = "INSERT INTO medications (name) VALUES (?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, m.getName());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMedication(Medication m) {
		try {
			String sql = "DELETE FROM medications WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, m.getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Medication getMedicationByName(String name) {

		Medication medication = null;

		try {
			String sql = "SELECT * FROM medications WHERE name LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				medication = new Medication(id, n);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medication;
	}
	
	@Override
	public Medication getMedicationById(Integer mId) {

		Medication medication = null;

		try {
			String sql = "SELECT * FROM medications WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, mId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				medication = new Medication(id, n);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medication;
	}
	
	@Override
	public List<Medication> listsAllMedication() {

		List<Medication> medicationsList = new ArrayList<Medication>();

		try {
			String sql = "SELECT * FROM medications";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				String name = r.getString("name");
				Medication medication = new Medication(id, name);
				medicationsList.add(medication);
			}
			r.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicationsList;
	}
}
