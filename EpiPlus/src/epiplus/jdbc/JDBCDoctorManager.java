package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.DoctorManager;
import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public class JDBCDoctorManager implements DoctorManager {

	private JDBCManager manager;

	public JDBCDoctorManager(JDBCManager m) {
		this.manager = m;
	}

	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctors (name, email, hospitalName, photo) VALUES (?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getEmail());
			prep.setString(3, d.getHospitalName());
			prep.setBytes(4, d.getPhoto());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Doctor> searchDoctorByEmail(String email) {

		List<Doctor> doctorsList = new ArrayList<Doctor>();

		try {
			String sql = "SELECT * FROM doctors WHERE email LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String e = rs.getString("email");
				String hospitalName = rs.getString("hospitalName");
				byte[] photo = rs.getBytes("photo");
				Doctor doctor = new Doctor(id, name, e, hospitalName, photo);
				doctorsList.add(doctor);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorsList;
	}

	@Override
	public List<Doctor> searchDoctorByName(String name) {

		List<Doctor> doctorsList = new ArrayList<Doctor>();

		try {
			String sql = "SELECT * FROM doctors WHERE name LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String email = rs.getString("email");
				String hospitalName = rs.getString("hospitalName");
				byte[] photo = rs.getBytes("photo");
				Doctor doctor = new Doctor(id, n, email, hospitalName, photo);
				doctorsList.add(doctor);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorsList;
	}

	@Override
	public List<Doctor> searchDoctorByHospital(String hospital) {
		
		List<Doctor> doctorsList = new ArrayList<Doctor>();

		try {
			String sql = "SELECT * FROM doctors WHERE hospital LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, hospital);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String h = rs.getString("hospitalName");
				byte[] photo = rs.getBytes("photo");
				Doctor doctor = new Doctor(id, name, email, h, photo);
				doctorsList.add(doctor);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorsList;
	}

	@Override
	public Doctor getDoctorById(Integer DocId) {

		Doctor doctor = null;

		try {
			String sql = "SELECT * FROM doctors WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DocId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String h = rs.getString("hospitalName");
				byte[] photo = rs.getBytes("photo");
				doctor = new Doctor(id, name, email, h, photo);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public List<Patient> getPatientsOfDoctors(Integer DocId) {

		List<Patient> patientsList = new ArrayList<Patient>();

		try {
			String sql = "SELECT * FROM patients WHERE doctorId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DocId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Integer age = rs.getInt("age");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet"); // We need to change this if it is a new class
				Integer ex_per_week = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, name, age, height, weight, lifestyle, diet, ex_per_week, photo);
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
	public void updateDoctor(String name, byte[] photo, String email, String hospital) {

		try {
			String sql = "UPDATE doctors" + " SET name=?" + " photo=?" + " email=?" + " hospitalName=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, name);
			p.setBytes(2, photo);
			p.setString(3, email);
			p.setString(4, hospital);
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDoctor(Doctor d) {
		try {
			String sql = "UPDATE doctors" + " SET name=?" + " photo=?" + " email=?" + " hospitalName=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, d.getName());
			p.setBytes(2, d.getPhoto());
			p.setString(3, d.getEmail());
			p.setString(4, d.getHospitalName());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDoctor(Doctor d) {
		try {
			String sql = "DELETE FROM doctors WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, d.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Doctor> listsAllDoctors() {
		
		List<Doctor> doctorsList = new ArrayList<Doctor>();
		
		try {
			String sql = "SELECT * FROM doctors";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String h = rs.getString("hospitalName");
				byte[] photo = rs.getBytes("photo");
				Doctor doctor = new Doctor(id, name, email, h, photo);
				doctorsList.add(doctor);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorsList;
	}
}
