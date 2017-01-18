package test.resources.com.sirion.suite.wor;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class OpenWORFromListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(wor_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(wor_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void OpenWORFromListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case - Open WOR From Listing");
        String WorIdFromListing = getObject("wor_id_link").getText();
        System.out.println("WOR id from listing is "+WorIdFromListing);
		APP_LOGS.debug("WOR id from listing is "+WorIdFromListing);		
		getObject("wor_id_link").click();
		//
		Thread.sleep(5000);
		
		//String textFromCR= driver.findElement(By.xpath("//*[@id='mainForm']/ng-form/fieldset[1]/p")).getText();
		String WorIdFromShowPage=getObject("wor_id_text").getText();

		System.out.println("WOR id from show page is "+WorIdFromShowPage);
		APP_LOGS.debug("WOR id from show page is "+WorIdFromShowPage);
		//Assert.assertEquals(textFromCR, "BASIC INFORMATION");
		Assert.assertEquals(WorIdFromShowPage, WorIdFromListing);
		
		APP_LOGS.debug("WOR show page open successfully with WOR id " +WorIdFromShowPage);
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
