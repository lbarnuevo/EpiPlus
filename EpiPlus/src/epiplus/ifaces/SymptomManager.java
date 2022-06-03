package epiplus.ifaces;

import java.util.*;

import epiplus.pojos.Symptom;

public interface SymptomManager {
	public void addSymptom(Symptom s);
	public void deleteSymptom(Symptom s);
	public Symptom getSymptomByName(String name);
	public Symptom getSymptomById(Integer sId);
	public List<Symptom> listsAllSymptoms();
}

