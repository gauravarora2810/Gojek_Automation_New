package test.resources.com.sirion.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.testng.annotations.Test;

import test.resources.com.sirion.base.TestBase;

@Test
public class Database_Password_Change_Query extends TestBase {	
	private static Scanner scanner;
	static File file = new File(CONFIG.getProperty("Databasequeryfilepath"));	
	static Connection con = null;
	static Statement stmt = null;
	static Statement resultStmt = null;
	static ResultSet rs = null;
	static String clientShowPageLoginID = null;

	public static void clientUserPasswordChange(String query) throws ClassNotFoundException, SQLException, InterruptedException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(CONFIG.getProperty("DatabaseURL"),	CONFIG.getProperty("DatabaseUsername"),	CONFIG.getProperty("DatabasePassword"));
		APP_LOGS.debug("Connected to Database " + CONFIG.getProperty("DatabaseURL"));

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				clientShowPageLoginID = Integer.toString(id);
				}
			
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				final String lineFromFile = scanner.nextLine();
				if (lineFromFile.contains("id = "+clientShowPageLoginID+";")) {
					APP_LOGS.debug("We found " + clientShowPageLoginID + " in file "+ file.getName());
					stmt.executeUpdate(lineFromFile);
					System.out.println("Password has been Changed for "+ clientShowPageLoginID);
					}
				}
			} catch (Exception e) {
				APP_LOGS.debug("Failed to Execute" + " " + query + " The error is "+ e.getMessage());
				}
		}
	}