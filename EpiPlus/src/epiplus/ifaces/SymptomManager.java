package epiplus.ifaces;

import java.util.*;

import epiplus.pojos.Symptom;

public interface SymptomManager {
	public void addSymptom(Symptom s);
	public void deleteSymptom(Symptom s);
	public List<Symptom> listsAllSymptoms();
	public Symptom getSymptomByName(String name);
	Symptom getSymptomById(Integer sId);
}
