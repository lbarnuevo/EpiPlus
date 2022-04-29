package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.EpisodeSymptomManager;
import epiplus.pojos.EpisodeSymptom;
import epiplus.pojos.Symptom;

public class JDBCEpisodeSymptomManager implements EpisodeSymptomManager {

	private JDBCManager manager;

	public JDBCEpisodeSymptomManager(JDBCManager m) {
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

	public List<Symptom> getSymptomsOfEpisode(Integer epId) {

		List<Symptom> symptomsList = new ArrayList<Symptom>();

		try {
			String sql = "SELECT * FROM symptoms AS s JOIN episodesymptoms AS es ON s.id=es.symptomId WHERE es.episodeId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, epId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Symptom symptom = new Symptom(id, name);
				symptomsList.add(symptom);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptomsList;
	}
}
