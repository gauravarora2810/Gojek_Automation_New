package test.resources.com.sirion.suite.wor;

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

public class WORListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = false;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfwor;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(wor_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(wor_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void ActionListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case WOR Listing");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("wor_quick_link").click(); 
		Thread.sleep(10000);
		numberOfEntries = getObject("wor_entries_text").getText();
		System.out.println("Number of  WOR in listing page " +numberOfEntries);
		APP_LOGS.debug("Number of  WOR in listing page " +numberOfEntries);
		
		numberOfEntriesSplit = numberOfEntries.split(" ");
		numberOfwor = numberOfEntriesSplit[4];
		System.out.println(numberOfwor);

		Connection con = getDbConnection();
		
		APP_LOGS.debug("Executin WOR listing query");
		
		String countFromDb = queryResult(con, CONFIG.getProperty("wor_listing_query"));
		Assert.assertEquals(numberOfwor, countFromDb);
	    APP_LOGS.debug("WOR listing is displaying correct entries.");
	    System.out.println("WOR listing is displaying correct entries.");
	    fail = false; //this executes if assertion passes
			 }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil.getRowNum(wor_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil.getRowNum(wor_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(wor_suite_xls, this.getClass().getSimpleName());
  }

}
