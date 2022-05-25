package epiplus.ifaces;

import java.util.List;

import epiplus.pojos.Episode;

public interface EpisodeManager {
	public void addEpisode(Episode e);
	public Episode getEpisode(Integer eId);
	public void deleteEpisode(Episode e);
	public List<Episode> getEpisodesOfPatient(Integer pacId);
	public List<Episode> listsAllEpisodes();
}
