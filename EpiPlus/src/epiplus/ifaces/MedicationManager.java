package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.Medication;

public interface MedicationManager {
	public void addMedication(Medication m);
	public Medication searchMedicationByType(String name);
	public void deleteMedication(Medication m);
	public List<Medication> listsAllMedication();
}
