package epiplus.ifaces;

import epiplus.pojos.PatientAllergy;

public interface PatientAllergyManager {

	void assignPatientAllergy(PatientAllergy pa);

	void unassignPatientAllergy(PatientAllergy pa);

}
