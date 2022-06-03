package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Allergy;
import epiplus.pojos.Patient;
import epiplus.pojos.PatientAllergy;

public interface PatientAllergyManager {

	public void assignPatientAllergy(PatientAllergy pa);
	public void unassignPatientAllergy(PatientAllergy pa);
	public List<Allergy> getAllergiesOfPatient(Integer pId);
}
