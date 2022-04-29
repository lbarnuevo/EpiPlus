package epiplus.jdbc;
		
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

import epiplus.ifaces.AllergyManager;
import epiplus.ifaces.EpisodeManager;
import epiplus.pojos.Allergy;
import epiplus.pojos.Episode;
	import epiplus.pojos.Patient;

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
				
				prep.setString(1,a.getName());
				
				prep.executeUpdate();
				prep.close();
				
				
				}catch (Exception ex) {
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
			
			
			}catch (Exception ex) {
				ex.printStackTrace();
			}
				
		
		}
		
		@Override 
		public List <Allergy> listAllAllergies(){
			
			List <Allergy> allergiesList = new ArrayList <Allergy>();
			
			try {
				String sql = "SELECT * FROM allergies";
				
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				ResultSet r = prep.executeQuery();

				while (r.next()) {
					Integer id = r.getInt("id");
					String name = r.getString("name");
					Allergy allergy = new Allergy(id,name);
					allergiesList.add(allergy);
				
					}
			r.close();
			prep.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
			
			
			
		}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

