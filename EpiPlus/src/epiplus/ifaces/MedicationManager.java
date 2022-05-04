package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.Medication;

public interface MedicationManager {
	public void addMedication(Medication m);
	//public Medication searchMedicationByType(String name); �Para qu�?
	public void deleteMedication(Medication m);
	public List<Medication> searchMedicationByName(String name);
	public List<Medication> listsAllMedication();
}
