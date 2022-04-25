package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import epiplus.ifaces.EpisodeManager;
import epiplus.pojos.Episode;
import epiplus.pojos.Patient;

public class JDBCEpisodeManager implements EpisodeManager {

	private JDBCManager manager;

	public JDBCEpisodeManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addEpisode(Episode e) {
		try {
			String sql = "INSERT INTO episodes (doe,length,activity,mood,place,previous_meal,injuries) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setDate(1,e.getDoe()); //¿necesita un casting?
			prep.setFloat(2, e.getLength());
			prep.setString(3, e.getActivity());
			prep.setString(4, e.getMood());
			prep.setString(5, e.getMood());
			prep.setString(4, e.getPlace());
			prep.setBoolean(4, e.getInjuries());
			prep.executeUpdate();
			prep.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteEpisode(Episode e) {
		try {
			String sql = "DELETE FROM episodes WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, e.getId());
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
				Date doe = r.getDate("date");
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
		return null;
	}

}
