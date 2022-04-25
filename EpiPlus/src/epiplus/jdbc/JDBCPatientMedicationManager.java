package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import epiplus.ifaces.PatientMedicationManager;
import epiplus.pojos.PatientMedication;

public class JDBCPatientMedicationManager implements PatientMedicationManager{

	private JDBCManager manager;

	public JDBCPatientMedicationManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void assignPatientMedication(PatientMedication pm) {
		try {
			String sql = "INSERT INTO patientmedication (patientId, medicationId, frequency, amount) VALUES (?,?,?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pm.getPatient().getId());
			p.setInt(2, pm.getMedication().getId());
			p.setInt(3, pm.getFrequency());
			p.setFloat (4,pm.getAmount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unassignPatientMedication(PatientMedication pm) {
		try {
			String sql = "DELETE FROM patientmedication WHERE patientId=? AND medicationId=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pm.getPatient().getId());
			p.setInt(2, pm.getMedication().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
