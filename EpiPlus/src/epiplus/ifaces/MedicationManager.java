package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.Medication;

public interface MedicationManager {
	public void addMedication(Medication m);
	public void deleteMedication(Medication m);
	public Medication getMedicationByName(String name);
	public List<Medication> listsAllMedication();
	Medication getMedicationById(Integer mId);
}
