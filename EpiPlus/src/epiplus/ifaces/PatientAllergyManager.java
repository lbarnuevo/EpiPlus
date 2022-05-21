package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Allergy;
import epiplus.pojos.Patient;
import epiplus.pojos.PatientAllergy;

public interface PatientAllergyManager {

	void assignPatientAllergy(PatientAllergy pa);
	void unassignPatientAllergy(PatientAllergy pa);
	public List<Allergy> getAllergiesOfPatient(Integer pId);
	public PatientAllergy getPatientAllergy(Patient p, Allergy all);
	

}
