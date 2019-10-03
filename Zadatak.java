package proba;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zadatak {
	private static final Logger log = LoggerFactory.getLogger(Zadatak.class);

	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:mem:~/test";
	static final String fileName = "AKDTEST20191004001.csv";

	// Database credentials
	static final String USER = "admin";
	static final String PASS = "pass";

	public static void main(String[] args) {
		Connection conn = null;
		Statement task = null;

		List<Podaci> sviPodaci = new ArrayList<Podaci>();
		sviPodaci = new Podaci().loadFromCSV(fileName);

		try {
			Class.forName(JDBC_DRIVER);
			log.info("Making connection with params:\nURL: {}\nUSER: {}\nPASS: {} ", DB_URL, USER, PASS);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			task = conn.createStatement();
			String sql = "CREATE TABLE   PODACI " + "(id INTEGER not NULL, " + " ime VARCHAR(30), "
					+ " prezime VARCHAR(30), " + " spol VARCHAR(1), " + " datumRodenja VARCHAR(10), "
					+ " mjesto VARCHAR(20), " + " drzava VARCHAR(20), " + " PRIMARY KEY ( id ))";
			task.executeUpdate(sql);

			log.info("Created table PODACI");

			task.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			log.error("SQL Exception, description: {}", se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			log.error("General exception, description: {}", e.getMessage());
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (task != null)
					task.close();
				log.info("Closed connection with DB");
			} catch (SQLException se2) {

				log.error("SQL Exception, description: {}", se2.getMessage());
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {

				log.error("SQL Exception, description: {}", se.getMessage());
				se.printStackTrace();
			} // end finally try
		} // end try

		log.info("Ending the program");

	}
}