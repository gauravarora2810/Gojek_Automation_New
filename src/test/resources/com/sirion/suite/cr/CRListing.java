package test.resources.com.sirion.suite.cr;

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

public class CRListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfCr;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(cr_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(cr_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void CRListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug(" Executing Test Case CR Listing");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("cr_quick_link").click(); 
		
		Thread.sleep(10000);
		numberOfEntries = getObject("cr_entries_text").getText();
		
		System.out.println("Number of  CR in listing page " +numberOfEntries);
		APP_LOGS.debug("Number of  CR in listing page " +numberOfEntries);
		numberOfEntriesSplit = numberOfEntries.split(" ");
		numberOfCr = numberOfEntriesSplit[4];
				
	    Connection con = getDbConnection();
		APP_LOGS.debug("Executing CR listing query");
		String countFromDb = queryResult(con, CONFIG.getProperty("cr_listing_query"));
		System.out.println("count from db " +countFromDb);
		Assert.assertEquals(numberOfCr, countFromDb);
		APP_LOGS.debug(" CR listing is displaying correct entries.");
		fail = false; //this executes if assertion passes
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil.getRowNum(cr_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil.getRowNum(cr_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(cr_suite_xls, this.getClass().getSimpleName());
  }

}
