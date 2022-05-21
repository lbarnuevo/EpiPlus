package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Allergy;
import epiplus.pojos.Patient;
import epiplus.pojos.PatientAllergy;

public interface PatientAllergyManager {

	void assignPatientAllergy(PatientAllergy pa);
	void unassignPatientAllergy(PatientAllergy pa);
	public List<Allergy> getAllergiesOfPatient(Integer pId);
<<<<<<< HEAD
	//public PatientAllergy getPatientAllergy(Patient p, Allergy all);
=======
	// public PatientAllergy getPatientAllergy(Patient p, Allergy all);
>>>>>>> branch 'master' of https://github.com/lbarnuevo/EpiPlus
	

}
