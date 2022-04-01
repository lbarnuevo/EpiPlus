package epiplus.ifaces;

import java.util.*;
import epiplus.pojos.Symptom;

public interface SymptomsManager {
	public void addSymptom(Symptom s);
	public void deleteSymptom(Symptom s);
	public List<Symptom> listsAllSymptoms();
}
