package epiplus.ifaces;

import java.util.Date;
import java.util.List;

import epiplus.pojos.Episode;

public interface EpisodeManager {
	public void addEpisode(Episode e);
	public void deleteEpisode(Episode e);
	public List<Episode> getEpisodesOfPatient(Integer pacId);
	public List<Episode> listsAllEpisodes();
}
