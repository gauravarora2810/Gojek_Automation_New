package test.resources.com.sirion.suite.changeRequest;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class OpenCRFromListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  
  
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
	public void OpenCRFromListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case - Open CR From Listing");
        String CrIdFromListing = getObject("cr_id_link").getText();
        System.out.println("CR id from listing is "+CrIdFromListing);
        APP_LOGS.debug("CR id from listing is "+CrIdFromListing);
				
		getObject("cr_id_link").click();
		//
		Thread.sleep(5000);
		
		//String textFromCR= driver.findElement(By.xpath("//*[@id='mainForm']/ng-form/fieldset[1]/p")).getText();
		String CrIdFromShowPage=getObject("cr_id_text").getText();

		System.out.println("CR id from show page is "+CrIdFromShowPage);
		APP_LOGS.debug("CR id from show page is "+CrIdFromShowPage);
		
		//Assert.assertEquals(textFromCR, "BASIC INFORMATION");
		Assert.assertEquals(CrIdFromShowPage, CrIdFromListing);
		
		APP_LOGS.debug("CR show page open successfully with CR id " +CrIdFromShowPage);
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
