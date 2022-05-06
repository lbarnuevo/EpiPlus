package epiplus.jdbc;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import epiplus.ifaces.PatientAllergyManager;
import epiplus.pojos.PatientAllergy;

public class JDBCPatientAllergyManager implements PatientAllergyManager{

	private JDBCManager manager;

	public JDBCPatientAllergyManager(JDBCManager m) {
		this.manager = m;
	}
	
	
	@Override
	public void assignPatientAllergy(PatientAllergy pa) {
		try {
			String sql = "INSERT INTO patientallergy (patientId, allergyId)  VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pa.getPatientId());
			p.setInt(2, pa.getAllergyId());
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unassignPatientAllergy(PatientAllergy pa) {
		try {
			String sql = "DELETE FROM patientallergy WHERE patientId=? AND allergyId=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pa.getPatientId());
			p.setInt(2, pa.getAllergyId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
