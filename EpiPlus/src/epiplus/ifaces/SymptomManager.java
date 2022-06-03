package epiplus.ifaces;

import java.util.*;

import epiplus.pojos.Symptom;

public interface SymptomManager {
	public void addSymptom(Symptom s);
	public void deleteSymptom(Symptom s);
	public Symptom getSymptomByName(String name);
<<<<<<< HEAD
	public List<Symptom> listsAllSymptoms();
=======
	Symptom getSymptomById(Integer sId);
>>>>>>> branch 'master' of https://github.com/lbarnuevo/EpiPlus
}
