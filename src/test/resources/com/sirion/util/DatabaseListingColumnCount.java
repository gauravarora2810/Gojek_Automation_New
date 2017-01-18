package test.resources.com.sirion.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import test.resources.com.sirion.suite.contract.ContractListing;

@Test
public class DatabaseListingColumnCount extends ContractListing{
    
    Connection con = null;
    Statement stmt = null;
    Statement resultStmt = null;
    ResultSet rs = null;
 
    public String DatabaseListing(String ListingEntity) throws ClassNotFoundException,SQLException, InterruptedException{
        int listingColumns =0;
        String docid = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(CONFIG.getProperty("DatabaseURL"), CONFIG.getProperty("DatabaseUsername"), CONFIG.getProperty("DatabasePassword"));
        try{
            
            Statement stmt = con.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("select count(*) from dynamic_column_metadata where list_id="+ListingEntity);
            while ( rs.next() ) {
              listingColumns = rs.getInt(1);
                docid = new Integer(listingColumns).toString();
                System.out.println("Total columns -- "+listingColumns);
            }
            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return docid;
    }
}
