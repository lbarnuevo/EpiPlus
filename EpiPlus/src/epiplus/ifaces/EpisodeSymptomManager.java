package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Episode;
import epiplus.pojos.EpisodeSymptom;
import epiplus.pojos.Symptom;

public interface EpisodeSymptomManager {
	public void assignEpisodeSymptom(EpisodeSymptom es);
	public void unassignEpisodeSymptom(EpisodeSymptom es);
	public List<Symptom> getSymptomsOfEpisode(Integer epId);
	public EpisodeSymptom getEpisodeSymptom (Episode e, Symptom s);//TODO add to UML
	//List<EpisodeSymptom> listsAllESs();
}
