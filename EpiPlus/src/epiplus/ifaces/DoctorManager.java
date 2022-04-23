package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.Doctor;
import epiplus.pojos.Patient;

public interface DoctorManager {
	public void addDoctor(Doctor d);
	public List<Doctor> searchDoctorByEmail(String email);
	public List<Doctor> searchDoctorByName(String name);
	public List<Doctor> searchDoctorByHospital(String hospital);
	public Doctor getDoctorById(Integer DocId);
	public List<Patient> getPatientsOfDoctors(Integer DocId);
	public void updateDoctor(String name, byte[] photo, String email, String hospital);
	public void updateDoctor(Doctor doctor);
	public void deleteDoctor(Doctor d);
	public List<Doctor> listsAllDoctors();
}
