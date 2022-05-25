package epiplus.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.PatientAllergyManager;
import epiplus.pojos.Allergy;
import epiplus.pojos.Patient;
import epiplus.pojos.PatientAllergy;


public class JDBCPatientAllergyManager implements PatientAllergyManager{

	private JDBCManager manager;

	public JDBCPatientAllergyManager(JDBCManager m) {
		this.manager = m;
	}
	
	
	@Override
	public void assignPatientAllergy(PatientAllergy pa) {
		try {
			String sql = "INSERT INTO patientallergies (patientId, allergyId)  VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pa.getPatient().getId());
			p.setInt(2, pa.getAllergy().getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unassignPatientAllergy(PatientAllergy pa) {
		try {
			String sql = "DELETE FROM patientallergies WHERE patientId=? AND allergyId=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pa.getPatient().getId());
			p.setInt(2, pa.getAllergy().getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Allergy> getAllergiesOfPatient(Integer pId) {
		
		List<Allergy> allergies = new ArrayList<Allergy>();
		
		try {
			String sql = "SELECT * FROM allergies AS a JOIN patientallergies AS pa ON a.id=pa.allergyId WHERE pa.patientId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pId);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Allergy allergy = new Allergy(id, name);
				allergies.add(allergy);
			}
			rs.close();
			prep.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return allergies;
	}	
}
