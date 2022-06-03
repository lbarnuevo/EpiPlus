package epiplus.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import epiplus.ifaces.*;
import epiplus.pojos.*;

public class JDBCAllergyManager implements AllergyManager {

	private JDBCManager manager;

	public JDBCAllergyManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addAllergy(Allergy a) {
		try {

			String sql = "INSERT INTO allergies (name) VALUES (?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setString(1, a.getName());

			prep.executeUpdate();
			prep.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteAllergy(Allergy a) {

		try {
			String sql = "DELETE FROM allergies WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);

			prep.setInt(1, a.getId());

			prep.executeUpdate();
			prep.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public List<Allergy> listAllAllergies() {

		List<Allergy> allergiesList = new ArrayList<Allergy>();

		try {
			String sql = "SELECT * FROM allergies";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet r = prep.executeQuery();

			while (r.next()) {
				Integer id = r.getInt("id");
				String name = r.getString("name");
				Allergy allergy = new Allergy(id, name);
				allergiesList.add(allergy);

			}
			r.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Allergy getAllergyByName(String name) {

		Allergy allergy = null;

		try {
			String sql = "SELECT * FROM allergies WHERE name LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				allergy = new Allergy(id, n);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allergy;

	}
	
	@Override
	public Allergy getAllergyById(Integer intId) {

		Allergy allergy = null;

		try {
			String sql = "SELECT * FROM allergies WHERE id LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, intId);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				allergy = new Allergy(id, n);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allergy;

	}
	
}
