package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.EpisodeManager;
import epiplus.pojos.Episode;

public class JDBCEpisodeManager implements EpisodeManager {

	private JDBCManager manager;

	public JDBCEpisodeManager(JDBCManager m) {
		this.manager = m;
	} 
	
	@Override
	public void addEpisode(Episode e) {
		try {
			String sql = "INSERT INTO episodes (doe,length,activity,mood,place,previous_meal,injuries, patientID) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1,e.getDoe()); 
			prep.setFloat(2, e.getLength());
			prep.setString(3, e.getActivity());
			prep.setString(4, e.getMood());
			prep.setString(5, e.getPlace());
			prep.setString(6, e.getPrevious_meal());
			prep.setBoolean(7, e.getInjuries());
			prep.setInt(8, e.getPatient().getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Episode getEpisode(Integer eId) {
		
		Episode episode=null;
		
		try {
			String sql = "SELECT * FROM episodes WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, eId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Date doe = rs.getDate("doe");
				Float length = rs.getFloat("length");
				String  activity= rs.getString("activity");
				String mood = rs.getString("mood");
				String place = rs.getString("place");
				String meals = rs.getString("previous_meal");
				Boolean injuries = rs.getBoolean("injuries");
				episode = new Episode(id,doe,length,activity,mood,place,meals,injuries);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return episode;
	}
	
	@Override
	public void deleteEpisode(Episode e) {
		try {
			String sql = "DELETE FROM episodes WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, e.getId());
			p.executeUpdate();
			p.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Episode> getEpisodesOfPatient(Integer pacId) {

		List<Episode> episodesList = new ArrayList<Episode>();
	

		try {
			String sql = "SELECT * FROM episodes WHERE patientId LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pacId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Date doe = rs.getDate("doe");
				Float length = rs.getFloat("length");
				String  activity= rs.getString("activity");
				String mood = rs.getString("mood");
				String place = rs.getString("place");
				String meals = rs.getString("previous_meal");
				Boolean injuries = rs.getBoolean("injuries");
				Episode episode = new Episode(id,doe,length,activity,mood,place,meals,injuries);
				episodesList.add(episode);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return episodesList;
	}
	
	@Override
	public List<Episode> listsAllEpisodes() {
		
		List<Episode> episodesList = new ArrayList<Episode>();
		

		try {
			String sql = "SELECT * FROM episodes";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				Date doe = r.getDate("doe");
				Float length = r.getFloat("length");
				String  activity= r.getString("activity");
				String mood = r.getString("mood");
				String place = r.getString("place");
				String meals = r.getString("previous_meal");
				Boolean injuries = r.getBoolean("injuries");
				Episode episode = new Episode(id,doe,length,activity,mood,place,meals,injuries);
				episodesList.add(episode);
			}
			r.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return episodesList;
	}
}
