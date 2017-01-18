package test.resources.com.sirion.suite.clientAdmin;
 
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.testng.annotations.Test;

@Test
public class Database_Password_Change_Query extends Createuser{
	
	private Scanner scanner;
	File file = new File(CONFIG.getProperty("Databasequeryfilepath"));
	Connection con = null;
	Statement stmt = null;
	Statement resultStmt = null;
	ResultSet rs = null;
	

	public void enduserpasswordchange(String query) throws ClassNotFoundException,SQLException, InterruptedException {
		
/*		Progress_Bar progressbar = new Progress_Bar();
		progressbar.bar();*/
		
		//String query ="Select id from app_user where login_id='"+login_Id+"'" ; 
		//String query = "Select id from app_user where login_id='tuborguser102'"; 
		 
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(CONFIG.getProperty("DatabaseURL"), CONFIG.getProperty("DatabaseUsername"), CONFIG.getProperty("DatabasePassword"));
		try{
		stmt = con.createStatement();
	    rs = stmt.executeQuery(query);
	      while (rs.next()) {
	       System.out.println(rs);
	       int id = rs.getInt("id");
	       ID = Integer.toString(id);
	       System.out.println("ÏD is"+ID);
	       ID_new=ID+";";
	       System.out.println("ÏD new is"+ID_new);
	      }
	       
	      
	       scanner = new Scanner(file);
	       while (scanner.hasNextLine()) {
	    	   
	          final String lineFromFile = scanner.nextLine();
	          
	          if(lineFromFile.contains("id = "+ID_new)) { 
	              // a match!
	        	  System.out.println(lineFromFile);
	              System.out.println("I found " +ID+ " in file " +file.getName());
	              ResultSet passwordchange = stmt.executeQuery(lineFromFile);
	              System.out.println(passwordchange);
	              
	              System.out.println("Password has been Changed for"+login_Id);
	          }
	       }
	       
	       
 
		} catch (Exception e) {
			System.err.println("Failed to Execute" + query + " The error is " + e.getMessage());
		}
		
		
		}
	
}