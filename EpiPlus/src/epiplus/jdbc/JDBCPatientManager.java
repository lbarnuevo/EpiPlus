package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.PatientManager;
import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public class JDBCPatientManager implements PatientManager {

	private JDBCManager manager;

	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void addPatient(Patient p) {
		try {
			String sql = "INSERT INTO patients (name,age,height,weight,lifestyle,diet,ex_per_week,photo) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setInt(2, p.getAge());
			prep.setFloat(3, p.getHeight());
			prep.setFloat(4, p.getWeight());
			prep.setString(5, p.getLifestyle());
			prep.setString(5, p.getDiet());
			prep.setInt(4, p.getEx_per_week());
			prep.setBytes(4, p.getPhoto());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		
		List<Patient> patientsList = new ArrayList<Patient>();

		try {
			String sql = "SELECT * FROM patients WHERE name LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				Integer age = rs.getInt("age");
				Float height = rs.getFloat("height");
				Float weight= rs.getFloat("weight");
				String lifestyle= rs.getString("lifestyle");
				String diet= rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, n, age,height,weight,lifestyle,diet,exercise,photo);
				patientsList.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientsList;
	}

	@Override
	public Patient getPatientById(Integer pacId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePatient(String name, byte[] photo, Integer age, Float height, Float weight, String lifetyle,
			Integer exerciseweek, String diet, List<Integer> SOS_contacts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePatient(Patient p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignPatient(Doctor dId, Patient pId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignPatient(Doctor dId, Patient pId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showEvolution(Patient p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Patient> listsAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

}
