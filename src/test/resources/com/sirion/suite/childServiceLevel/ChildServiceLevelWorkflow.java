package test.resources.com.sirion.suite.childServiceLevel;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ChildServiceLevelWorkflow extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(child_sl_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(child_sl_suite_xls, this.getClass().getSimpleName());
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
		
		APP_LOGS.debug("Executing Test Case Contract Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("sl_quick_link").click(); 
		Thread.sleep(15000);
	    getObject("csl_id_link").click(); //click on obligation id
		Thread.sleep(10000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		Thread.sleep(5000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         String cob_status = getObject("cob_status_id").getText();
         
         Assert.assertEquals(cob_status, "Approved");     
         
              
		fail = false; //this executes if assertion passes
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(child_sl_suite_xls, "Test Cases", TestUtil.getRowNum(child_sl_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(child_sl_suite_xls, "Test Cases", TestUtil.getRowNum(child_sl_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(child_sl_suite_xls, this.getClass().getSimpleName());
  }

}
