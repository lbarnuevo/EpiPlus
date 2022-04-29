package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.PatientMedicationManager;
import epiplus.pojos.Medication;
import epiplus.pojos.PatientMedication;
import epiplus.pojos.Symptom;

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
	
	public List<Medication> getMedicationsOfPacient(Integer pacId) {

		List<Medication> medicationsList = new ArrayList<Medication>();

		try {
			String sql = "SELECT * FROM medications AS m JOIN patientmedication AS pm ON m.id=pm.medicationId WHERE pm.patientId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pacId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String  name= rs.getString("name");
				Medication medication = new Medication (id,name);
				medicationsList.add(medication);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicationsList;
	}
}
