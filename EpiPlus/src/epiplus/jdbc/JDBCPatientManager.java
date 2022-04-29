package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.PatientManager;
import epiplus.pojos.EmergencyContact;
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
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, n, age, height, weight, lifestyle, diet, exercise, photo);
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

		Patient patient = null;

		try {
			String sql = "SELECT * FROM patients WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pacId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				Integer age = rs.getInt("age");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				patient = new Patient(id, n, age, height, weight, lifestyle, diet, exercise, photo);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}
	
	@Override
	public List<EmergencyContact> getEmergencyContactsOfPatient(Integer pacId) {

		List<EmergencyContact> contactsList= new ArrayList<EmergencyContact>();

		try {
			String sql = "SELECT * FROM emergencycontact WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pacId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				Integer age = rs.getInt("age");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				patient = new Patient(id, n, age, height, weight, lifestyle, diet, exercise, photo);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return null;//this function should return a patirnt so why is it returning a null? --> I'm(Marta) going to change it
		return ;
	}
	

	@Override
	public void updatePatient(String name, byte[] photo, Integer age, Float height, Float weight, String lifestyle,
			Integer exerciseweek, String diet) {
		try {
			String sql = "UPDATE patients" + " SET name=?" + " photo=?" + " age=?" + " height=?" + " weight=?"
					+ " lifestyle=?" + " ex_per_week=?" + " diet=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, name);
			p.setBytes(2, photo);
			p.setInt(3, age);
			p.setFloat(4, height);
			p.setFloat(5, weight);
			p.setString(6, lifestyle);
			p.setInt(7, exerciseweek);
			p.setString(8, diet);
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePatient(Patient p) {
		try {
			String sql = "DELETE FROM patients WHERE id=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showEvolution(Patient p) {
		//Hay que pensar en cómo hacer esto
		//Implementar funciones para representar gráficas sobre los datos de episodes de un paciente
	}

	@Override
	public List<Patient> listsAllPatients() {

		List<Patient> patientsList = new ArrayList<Patient>();

		try {
			String sql = "SELECT * FROM patients";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				Integer age = rs.getInt("age");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, n, age, height, weight, lifestyle, diet, exercise, photo);
				patientsList.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
