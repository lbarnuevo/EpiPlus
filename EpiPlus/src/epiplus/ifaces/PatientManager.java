package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.*;;

public interface PatientManager {
	public void addPatient (Patient p);
	public List<Patient> searchPatientByName(String name);
	public Patient searchPatientByEmail(String email);
	public Patient getPatientById(Integer pacId);
	public void updatePatient(Patient p);
	public void deletePatient(Patient p);
	public void assignDoctor(Patient p, Doctor d);
	public void unassignDoctor(Patient p, Doctor d);
	public void showEvolution(Patient p); 
	public List<Patient> listsAllPatients();
}
