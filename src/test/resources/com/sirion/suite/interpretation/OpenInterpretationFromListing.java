package test.resources.com.sirion.suite.interpretation;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class OpenInterpretationFromListing extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(int_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(int_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void OpenInterpretationFromListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case - Open Interpretation From Listing");
		
        String IntIdFromListing = getObject("int_id_link").getText();
        
        System.out.println("Interpretation id from listing is "+IntIdFromListing);
		APP_LOGS.debug("Interpretation id from listing is "+IntIdFromListing);		
		getObject("int_id_link").click();
		//
		Thread.sleep(5000);
		
		//String textFromCR= driver.findElement(By.xpath("//*[@id='mainForm']/ng-form/fieldset[1]/p")).getText();
		String IntIdFromShowPage=getObject("int_id_text").getText();

		System.out.println("Interpretation id from show page is "+IntIdFromShowPage);
		APP_LOGS.debug("Interpretation id from show page is "+IntIdFromShowPage);
		//Assert.assertEquals(textFromCR, "BASIC INFORMATION");
		Assert.assertEquals(IntIdFromShowPage, IntIdFromListing);
		
		APP_LOGS.debug("Interpretation show page open successfully with Interpretaion id " +IntIdFromShowPage);
		fail = false; //this executes if assertion passes
		

			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(int_suite_xls, "Test Cases", TestUtil.getRowNum(int_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(int_suite_xls, "Test Cases", TestUtil.getRowNum(int_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(int_suite_xls, this.getClass().getSimpleName());
  }

}
