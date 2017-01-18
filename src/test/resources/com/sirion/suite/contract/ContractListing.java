package test.resources.com.sirion.suite.contract;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ContractListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfContracts;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void ContractListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Contract Listing");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("contract_quick_link").click(); 
		Thread.sleep(10000);
		numberOfEntries = getObject("contract_entries_text").getText();
		
		System.out.println("this is " +numberOfEntries);
		numberOfEntriesSplit = numberOfEntries.split(" ");
		numberOfContracts = numberOfEntriesSplit[4];
		System.out.println(numberOfContracts);
		
		//database connection code starts
/*		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.2.151:5432/kasauli_26032015", "postgres", "postgres");
		java.sql.Statement stmt =  con.createStatement();
		java.sql.ResultSet resultset =  stmt.executeQuery
				("select count(*) from contract where client_id = '1011' and document_type_id != '76'");
				
		System.out.println("result is - " +resultset);
	    int numcols = resultset.getMetaData().getColumnCount();
	    List <List <String> > result = new ArrayList<>();

	    while (resultset.next()) {
	        List <String> row = new ArrayList<>(numcols); // new list per row

	        for (int i=1; i<= numcols; i++) {  // don't skip the last column, use <=
	            row.add(resultset.getString(i));
	            System.out.print(resultset.getString(i) + "\t");
	        }
	        result.add(row); // add it to the result
	        System.out.print("\n");
	        System.out.println(result.get(0).get(0));
       	      
	    }*/
		//database connection code starts
		
		//String countFromDb= dbConnection(CONFIG.getProperty("DatabaseURL"), CONFIG.getProperty("DatabaseUsername"), CONFIG.getProperty("DatabasePassword"));
		
		
		Connection con = getDbConnection();
		
		APP_LOGS.debug("Executing contract listing query");
		//String countFromDb = queryResult(con, "select count(*) from contract where client_id = '1011' and document_type_id != '76'");
		String countFromDb = queryResult(con, CONFIG.getProperty("contract_listing_query"));
		
	
	   // System.out.println("count check " +countFromDb);
		Assert.assertEquals(numberOfContracts, countFromDb);

		fail = false; //this executes if assertion passes
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(contract_suite_xls, this.getClass().getSimpleName());
  }

}
