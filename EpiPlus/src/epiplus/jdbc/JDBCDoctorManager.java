package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Doctor> searchDoctorByEmail(String email) {
		
		List<Doctor> doctorsList= new ArrayList<Doctor>();
		
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
				Doctor doctor= new Doctor (id,name,e,hospitalName,photo);
			doctorsList.add(doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorsList;
	}

	@Override
	public List<Doctor> searchDoctorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> searchDoctorByHospital(String hospital) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getDoctorById(Integer DocId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getPatientsOfDoctors(Integer DocId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateDoctor(String name, byte[] photo, String email, String hospital) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDoctor(Doctor d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Doctor> listsAllDoctors() {
		// TODO Auto-generated method stub
		return null;
	}

}
