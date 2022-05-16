package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
			String sql = "INSERT INTO patients (name,age,email,height,weight,lifestyle,diet,ex_per_week,photo) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getEmail());
			prep.setDate(3, (java.sql.Date) p.getBirthday());
			prep.setFloat(4, p.getHeight());
			prep.setFloat(5, p.getWeight());
			prep.setString(6, p.getLifestyle());
			prep.setString(7, p.getDiet());
			prep.setInt(8, p.getEx_per_week());
			prep.setBytes(9, p.getPhoto());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void assignDoctor(Patient p, Doctor d) {
		try {
			String sql = "UPDATE patients SET doctorId=? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, d.getId());
			prep.setInt(2, p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void unassignDoctor(Patient p, Doctor d) {
		try {
			String sql = "UPDATE patients SET doctorId=NULL WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, p.getId());
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
				String e = rs.getString("email");
				Date bd = rs.getDate("birthday");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, n, e, bd, height, weight, lifestyle, diet, exercise, photo);
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
				String e = rs.getString("email");
				Date bd = rs.getDate("birthday");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				patient = new Patient(id, n, e, bd, height, weight, lifestyle, diet, exercise, photo);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public void updatePatient(Patient p) {
		try {
			String sql = "UPDATE patients" + " SET name=?" + " email=?" + " photo=?" + " birthday=?" + " height=?"
					+ " weight=?" + " lifestyle=?" + " ex_per_week=?" + " diet=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getEmail());
			ps.setBytes(3, p.getPhoto());
			ps.setDate(4, (java.sql.Date) p.getBirthday());
			ps.setFloat(5, p.getHeight());
			ps.setFloat(6, p.getWeight());
			ps.setString(7, p.getLifestyle());
			ps.setInt(8, p.getEx_per_week());
			ps.setString(9, p.getDiet());
			ps.executeUpdate();
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
		//We could show the data like this:
		//1. Shows all episodes in a month
		//2. Shows the number of episodes per month using a counter
		//3. Shows if there is an exercise or meal repeated in the collection
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
				String e = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet");
				Integer exercise = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, n, e, birthday, height, weight, lifestyle, diet, exercise, photo);
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
