package test.resources.com.sirion.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import test.resources.com.sirion.suite.contract.ContractListing;

@Test
public class Database_Query_DocID extends ContractListing{
	
	Connection con = null;
	Statement stmt = null;
	Statement resultStmt = null;
	ResultSet rs = null;
 
    public String Database_Query_DocId (String FileName) throws ClassNotFoundException,SQLException, InterruptedException{
        int contractdocid =0;
        String docid = null;
        
    	Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(CONFIG.getProperty("DatabaseURL"), CONFIG.getProperty("DatabaseUsername"), CONFIG.getProperty("DatabasePassword"));
		try{
            
            Statement stmt = con.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT id from contract_document where name like '"+FileName+"'");
            while ( rs.next() ) {
                contractdocid = rs.getInt(1);
                docid = new Integer(contractdocid).toString();
                System.out.println(contractdocid);
            }
            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		return docid;
    }
}