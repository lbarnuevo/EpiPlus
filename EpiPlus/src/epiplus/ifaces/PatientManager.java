package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.Patient;
import epiplus.pojos.Episode;
import epiplus.pojos.EmergencyContact;

public interface PatientManager {
	public void addPatient (Patient p);
	public List<Patient> searchPatientByName(String name);
	public Patient getPatientById(Integer pacId);
	public void updatePatient(String name, byte[] photo, Date bd, Float height, Float weight, 
			String lifestyle, Integer exerciseweek, String diet);
	public void updatePatient(Patient p);
	public void deletePatient(Patient p);
	//public void assignPatient(Doctor dId, Patient pId);
	//public void unassignPatient(Doctor dId, Patient pId);
	public void showEvolution(Patient p); //It is void or is other data type? /Yes it should be void, Ola
	public List<Patient> listsAllPatients();
}
