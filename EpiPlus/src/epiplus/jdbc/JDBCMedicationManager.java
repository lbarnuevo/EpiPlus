package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.MedicationManager;
import epiplus.pojos.Medication;
import epiplus.pojos.Symptom;

public class JDBCMedicationManager implements MedicationManager{

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//¿Para qué?
	//@Override
	//public Medication searchMedicationByType(String name) {
		// TODO Auto-generated method stub
		//return null;
	//}

	@Override
	public void deleteMedication(Medication m) {
		try {
			String sql = "DELETE FROM medications WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, m.getId());
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
				String  name= r.getString("name");
				Medication medication = new Medication (id,name);
				medicationsList.add(medication);
			}
			r.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
