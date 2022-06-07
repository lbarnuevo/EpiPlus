package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import epiplus.ifaces.DoctorManager;
import epiplus.pojos.Allergy;
import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public class JDBCDoctorManager implements DoctorManager {

	private JDBCManager manager;

	public JDBCDoctorManager(JDBCManager m) {
		this.manager = m;
	}

	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctors (name, email, hospitalName, photo, role_id) VALUES (?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getEmail());
			prep.setString(3, d.getHospitalName());
			prep.setBytes(4, d.getPhoto());
			prep.setInt(5, d.getUser_id());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Doctor searchDoctorByEmail(String email) { 

		Doctor d = null;

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
				d = new Doctor(id, name, e, hospitalName, photo);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
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
			String sql = "SELECT * FROM doctors WHERE hospitalName LIKE ?";
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
	public void updateDoctor(Doctor doctor) {
		try {
			String sql = "UPDATE doctors" + " SET name=?," + " hospitalName=?," + " photo=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, doctor.getName());
			p.setString(2, doctor.getHospitalName());
			p.setBytes(3, doctor.getPhoto());
			p.executeUpdate();
			p.close();
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
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Patient> getPatientsOfDoctor(Integer DocId) {

		List<Patient> patientsList = new ArrayList<Patient>();

		try {
			String sql = "SELECT * FROM patients WHERE doctorId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, DocId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date bd = rs.getDate("birthday");
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				String lifestyle = rs.getString("lifestyle");
				String diet = rs.getString("diet"); // We need to change this if it is a new class
				Integer ex_per_week = rs.getInt("ex_per_week");
				byte[] photo = rs.getBytes("photo");
				Patient patient = new Patient(id, name, email, bd, height, weight, lifestyle, diet, ex_per_week, photo);
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
