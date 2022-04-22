package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public class JDBCDoctorManager {

	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctors (name, email, hospitalName, photo) VALUES (?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getBreed());
			prep.setString(3, d.getCoat());
			prep.setDate(4, d.getDob());
			prep.setBoolean(5, d.getCured());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Doctor> searchDoctorByEmail(String email);

	public List<Doctor> searchDoctorByName(String name);

	public List<Doctor> searchDoctorByHospital(String hospital);

	public Doctor getDoctorById(Integer DocId);

	public List<Patient> getPatientsOfDoctors(Integer DocId);

	public void UpdateDoctor(String name, byte[] photo, String email, String hospital);

	public void deleteDoctor(Doctor d);

	public List<Doctor> listsAllDoctors();
}
