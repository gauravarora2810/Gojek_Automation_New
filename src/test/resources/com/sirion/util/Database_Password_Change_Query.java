package test.resources.com.sirion.util;
 
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.testng.annotations.Test;

import test.resources.com.sirion.suite.sirionUserAdmin.ClientAdminCreation;

@Test
public class Database_Password_Change_Query extends ClientAdminCreation{
	
	private Scanner scanner;
	File file = new File(CONFIG.getProperty("Databasequeryfilepath"));
	Connection con = null;
	Statement stmt = null;
	Statement resultStmt = null;
	ResultSet rs = null;
	

	public void clientAdminPasswordChange(String query) throws ClassNotFoundException,SQLException, InterruptedException {
		
		/*Progress_Bar progressbar = new Progress_Bar();
		progressbar.bar();*/
		System.out.println("gaurav Arora");
		 
		//Class.forName("com.mysql.jdbc.Driver");
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(CONFIG.getProperty("DatabaseURL"), CONFIG.getProperty("DatabaseUsername"), CONFIG.getProperty("DatabasePassword"));
		System.out.println("Gaurav Arora again");
		try{
		stmt = con.createStatement();
	    rs = stmt.executeQuery(query);
	      while (rs.next()) {
	       int id = rs.getInt("id");
	       ID_admin = Integer.toString(id);
	       System.out.println("ID_admin is"+ID_admin);
	       ID_admin_new=ID_admin+";";
	       System.out.println("ID_admin_new is"+ID_admin_new);
	      }
	       
	      System.out.println(ID_admin);
	       scanner = new Scanner(file);
	       while (scanner.hasNextLine()) {
	          final String lineFromFile = scanner.nextLine();
	          if(lineFromFile.contains("id = "+ID_admin_new)) { 
	              // a match!
	              System.out.println("I found " +ID_admin+ " in file " +file.getName());
	        	  APP_LOGS.debug("I found " +ID_admin+ " in file " +file.getName());
	              int passwordchange = stmt.executeUpdate(lineFromFile);
	              APP_LOGS.debug(passwordchange);
	              
	              APP_LOGS.debug("Password has been Changed for "+adminloginid);
	              System.out.println("Password has been Changed for "+adminloginid);
	          }
	       }
	       
	       
 
		} catch (Exception e) {
			APP_LOGS.debug("Failed to Execute" +" "+ query + " The error is " + e.getMessage());
		}
		
		
		}
	
}