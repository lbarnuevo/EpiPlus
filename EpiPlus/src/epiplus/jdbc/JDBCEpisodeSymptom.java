package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import epiplus.ifaces.EpisodeSymptomManager;
import epiplus.pojos.EpisodeSymptom;

public class JDBCEpisodeSymptom implements EpisodeSymptomManager{

	private JDBCManager manager;

	public void JDBCEpisodeSymptomManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void assignEpisodeSymptom(EpisodeSymptom es) {
		try {
			String sql = "INSERT INTO episodesymptoms (episodeId, symptomId, severity) VALUES (?,?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, es.getEpisode().getId());
			p.setInt(2, es.getSymptom().getId());
			p.setInt(3, es.getSeverity());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unassignEpisodeSymptom(EpisodeSymptom es) {
		try {
			String sql = "DELETE FROM episodesymptoms WHERE episodeId=? AND symptomId=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, es.getEpisode().getId());
			p.setInt(2, es.getSymptom().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
