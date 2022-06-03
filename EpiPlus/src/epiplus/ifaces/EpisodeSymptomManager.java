package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Episode;
import epiplus.pojos.EpisodeSymptom;
import epiplus.pojos.Symptom;

public interface EpisodeSymptomManager {
	public void assignEpisodeSymptom(EpisodeSymptom es);
	public void unassignEpisodeSymptom(EpisodeSymptom es);
	public EpisodeSymptom getEpisodeSymptom (Episode e, Symptom s);
	public List<Symptom> getSymptomsOfEpisode(Integer epId);
	//List<EpisodeSymptom> listsAllESs(); 
}
