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
			c = DriverManager.getConnection("jdbc:sqlite:./db/Epiplus.db");
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
	private void createTables() {
		// Create Tables
		try {
			Statement stmt = c.createStatement();
			// -----------------DOCTORS----------
			String sql = "CREATE TABLE doctors (" + "	id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name	TEXT NOT NULL," + "	email	TEXT NOT NULL," + "	hospitalName TEXT NOT NULL,"
					+ " photo BLOB " + ");";
			stmt.executeUpdate(sql);

			// -----------EPISODES
			sql = "CREATE TABLE episodes (" + "	id	    INTEGER PRIMARY KEY AUTOINCREMENT," + "	date	DATE NOT NULL,"
					+ "	length	REAL NOT NULL," // time in minutes
					+ "	activity	TEXT," // before attack
					+ "	mood	    TEXT," + "	place	TEXT," + "	previous_meal TEXT,"
					+ "	injuries BOOLEAN NOT NULL,"
					+ "FOREIGN KEY(patientID) REFERENCES patients(id) ON DELETE RESTRICT" + ");";
			stmt.executeUpdate(sql);
			
			// ----------SYMPTOMS
			sql= "CREATE TABLE symptoms (" + "id	    INTEGER PRIMARY KEY AUTOINCREMENT," + " name	TEXT NOT NULL," + ");";
			stmt.executeUpdate(sql);		

			// ---------EPISODE-SYMPTOMS
			sql = "CREATE TABLE episodesymptoms(" + "	episodeId INTEGER NOT NULL,"
					+ "	symptomId	INTEGER NOT NULL," + "	severity	INTEGER NOT NULL,"
					+ " FOREIGN KEY (episodeId) REFERENCES episodes(id) ON DELETE RESTRICT,"
					+ " FOREIGN KEY (symptomId) REFERENCES symptoms(id) ON DELETE RESTRICT" + ");";
			stmt.executeUpdate(sql);

			// -------MEDICATIONS
			sql = "CREATE TABLE medications (" + "	id	 INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT,"
					+ "	name   TEXT NOT NULL" + ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE patientmedication( " + "patientId INTEGER NOT NULL," + "medicationId INTEGER NOT NULL, "
					+ "frequency INTEGER NOT NULL," // times per day
					+ "amount REAL NOT NULL,"
					+ " FOREIGN KEY (patientId) REFERENCES patients(id) ON DELETE RESTRICT,"
					+ " FOREIGN KEY (medicationId) REFERENCES medications(id) ON DELETE RESTRICT"
					+ ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE patients(" + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + "name TEXT NOT NULL,"
					+ "age INTEGER NOT NULL," + "height REAL NOT NULL," // in cm
					+ "weight REAL NOT NULL, " // in kg
					+ "lifestyle TEXT CHECK('lifestyle' IN ('sedentary','little activity','moderate ativity','high activity')) NOT NULL,"
					+ "diet TEXT CHECK ('diet' IN ('mediterranean', 'high protein', 'gluten free', 'lactose free', 'dairy free', 'ketogenic','highly caloric', 'vegan','vegetarian','intermitten fasting')) NOT NULL,"
					+ "ex_per_week INTEGER NOT NULL," + "doctorId INTEGER NOT NULL," + "photo BLOB,"
					+ "emergency_contact NUMERIC," + "FOREIGN KEY (doctorId) REFERENCES doctors(id) ON DELETE SET NULL"
					+ ");";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE emergencycontact (" + "id	 INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
					+ "name	TEXT NOT NULL," + "number	INTEGER NOT NULL);";

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}

}
