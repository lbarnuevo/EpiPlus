package epiplus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {

	private Connection c = null;

	public JDBCManager() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/epiplus.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			// Create tables
			this.createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Libraries not loaded");
		}
	}

	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

//------CREATE TABLES-------------
	public void createTables() { //TODO add user 
		// Create Tables
		try {
			Statement stmt = c.createStatement();
			
			// -----------------DOCTORS----------
			String sql = "CREATE TABLE doctors (" 
					+ "	id		INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name	TEXT NOT NULL," 
					+ "	email	TEXT NOT NULL," 
					+ "	hospitalName TEXT NOT NULL,"
					+ " photo BLOB " 
					+ ");";
			stmt.executeUpdate(sql);

			// -----------------PATIENTS----------
			sql = "CREATE TABLE patients (" 
					+ " id 			INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ " name		TEXT NOT NULL,"
					+ " email		TEXT NOT NULL,"
					+ " birthday 	DATE NOT NULL," 
					+ " height		REAL," // in cm
					+ " weight		REAL," // in kg
					+ " lifestyle 	TEXT CHECK('lifestyle' IN ('sedentary','low activity','medium ativity','high activity')),"
					+ " diet 		TEXT CHECK ('diet' IN ('normal','mediterranean', 'high protein','high protein vegetarian', 'high protein vegan', 'gluten free', 'lactose free', 'dairy free', 'ketogenic', 'ketogenic vegetarian', 'ketogenic vegan', 'vegan','vegetarian')),"
					+ " ex_per_week INTEGER,"
					+ " photo 		BLOB,"
					+ " doctorId INTEGER REFERENCES doctors(id) ON DELETE SET NULL" 
					+ ");";
			stmt.executeUpdate(sql);
			
			// -----------------EPISODES----------
			sql = "CREATE TABLE episodes (" 
					+ "	id	    	INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "	date		DATE NOT NULL,"
					+ "	length		REAL NOT NULL," // time in minutes
					+ "	activity	TEXT," // before attack
					+ "	mood	    TEXT," 
					+ "	place		TEXT," 
					+ "	previous_meal TEXT,"
					+ "	injuries 	BOOLEAN NOT NULL,"
					+ " patientId INTEGER NOT NULL REFERENCES patients(id) ON DELETE RESTRICT"
					+ ");";
			stmt.executeUpdate(sql);
			
			// -----------------SYMPTOMS----------
			sql= "CREATE TABLE symptoms (" 
					+ " id	    INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ " name	TEXT NOT NULL" 
					+ ");";
			stmt.executeUpdate(sql);		

			// -----------------EPISODE-SYMPTOMS----------
			sql = "CREATE TABLE episodesymptoms (" 
					+ "	episodeId 	INTEGER NOT NULL,"
					+ "	symptomId	INTEGER NOT NULL," 
					+ "	severity	INTEGER NOT NULL,"
					+ " FOREIGN KEY (episodeId) REFERENCES episodes(id) ON DELETE CASCADE,"
					+ " FOREIGN KEY (symptomId) REFERENCES symptoms(id) ON DELETE CASCADE,"
					+ " PRIMARY KEY (episodeId,symptomId)\r\n"
					+ ");";
			stmt.executeUpdate(sql);

			// -----------------MEDICATIONS----------
			sql = "CREATE TABLE medications (" 
					+ " id	    INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ " name	TEXT NOT NULL" 
					+ ");";
			stmt.executeUpdate(sql);
			
			// -----------------PATIENT-MEDICATION----------
			sql = "CREATE TABLE patientmedication ( " 
					+ " patientId 		INTEGER NOT NULL," 
					+ " medicationId 	INTEGER NOT NULL,"
					+ " frequency 		INTEGER NOT NULL," // times per day
					+ " amount 			REAL NOT NULL,"
					+ " FOREIGN KEY (patientId) REFERENCES patients(id) ON DELETE CASCADE,"
					+ " FOREIGN KEY (medicationId) REFERENCES medications(id) ON DELETE CASCADE,"
					+ " PRIMARY KEY (patientId,medicationId)\r\n"
					+ ");";
			stmt.executeUpdate(sql);

			// -----------------EMERGENCY CONTACT----------
			sql = "CREATE TABLE emergencycontacts (" 
					+ " id	 	INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name	TEXT NOT NULL," 
					+ " number	INTEGER NOT NULL," 
					+ " patientId INTEGER NOT NULL REFERENCES patients(id) ON DELETE CASCADE"
					+ ");";

			stmt.executeUpdate(sql);
			
			// -----------------ALLERGY----------
			sql = "CREATE TABLE allergies (" 
					+ "	id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name	TEXT NOT NULL"
					+ ");";

			stmt.executeUpdate(sql);

			// -----------------PATIENT-ALLERGY----------
			sql = "CREATE TABLE patientallergies ("
					+ " patientId 		INTEGER NOT NULL,"
					+ " allergyId		INTEGER NOT NULL,"
					+ "	FOREIGN KEY (patientId) REFERENCES patients(id) ON DELETE CASCADE,"
					+ "	FOREIGN KEY (allergyId) REFERENCES allergies(id) ON DELETE CASCADE,"
					+ " PRIMARY KEY (patientId,allergyId)\r\n"
					+ ");";
	
			stmt.executeUpdate(sql);



		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}

}
