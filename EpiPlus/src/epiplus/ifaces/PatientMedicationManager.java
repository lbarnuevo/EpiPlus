package epiplus.ifaces;

import epiplus.pojos.PatientMedication;

public interface PatientMedicationManager {
	public void assignPatientMedication(PatientMedication pm);
	public void unassignPatientMedication(PatientMedication pm);
}
