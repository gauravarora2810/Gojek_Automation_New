package test.resources.com.sirion.suite.childObligation;

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

public class ChildObligationListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfChildObligations;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(child_obligation_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(child_obligation_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void ChildObligationListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug(" Executing Test Case Child Obligation Listing");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("child_obligaiton_quick_link").click(); 
		Thread.sleep(10000);
		numberOfEntries = getObject("child_obligation_entries_text").getText();
		
		System.out.println("this is " +numberOfEntries);
		numberOfEntriesSplit = numberOfEntries.split(" ");
		numberOfChildObligations = numberOfEntriesSplit[4];
		System.out.println(numberOfChildObligations);
		
	
		Connection con = getDbConnection();
		
		APP_LOGS.debug("Executing child obligation listing query");
		//String countFromDb = queryResult(con, "select count(*) from contract where client_id = '1011' and document_type_id != '76'");
		String countFromDb = queryResult(con, CONFIG.getProperty("child_obligation_listing_query"));
		
	
	   // System.out.println("count check " +countFromDb);
		Assert.assertEquals(numberOfChildObligations, countFromDb);

		fail = false; //this executes if assertion passes
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(child_obligation_suite_xls, "Test Cases", TestUtil.getRowNum(child_obligation_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(child_obligation_suite_xls, "Test Cases", TestUtil.getRowNum(child_obligation_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(child_obligation_suite_xls, this.getClass().getSimpleName());
  }

}
