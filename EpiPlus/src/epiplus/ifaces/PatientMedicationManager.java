package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Medication;
import epiplus.pojos.Patient;
import epiplus.pojos.PatientMedication;

public interface PatientMedicationManager {
	public void assignPatientMedication(PatientMedication pm);
	public void unassignPatientMedication(PatientMedication pm);
	public void updatePatientMedication (PatientMedication pm);
	public List<Medication> getMedicationsOfPatient(Integer pacId);
}
