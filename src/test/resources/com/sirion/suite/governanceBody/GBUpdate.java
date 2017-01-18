package test.resources.com.sirion.suite.governanceBody;





import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class GBUpdate extends TestSuiteBase {
	  String runmodes[] = null;
	  static int count = -1;
	  // static boolean pass=false;
	  static boolean fail = true;
	  static boolean skip = false;
	  static boolean isTestPass = true;

	  // Runmode of test case in a suite
	  @BeforeTest
	  public void checkTestSkip() {

	    if (!TestUtil.isTestCaseRunnable(governance_body_suite_xls, this.getClass().getSimpleName())) {
	      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
	      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
	    }
	    // load the runmodes off the tests
	    runmodes = TestUtil.getDataSetRunmodes(governance_body_suite_xls, this.getClass().getSimpleName());
	  }
	  @Test(groups = "OFUpdation", dataProvider = "getTestData")
	  public void OFUpdation (String gbTitle,String gbDescription) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case GB Updation");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));

			getObject("gb_quick_link").click();
			Thread.sleep(5000);
	        getObject("gb_listing_link").click();
	        Thread.sleep(5000);
	        getObject("gb_id_link").click();
	        
	        Thread.sleep(9000);
	        
	       /* String GBNameShowPage = getObject("gb_title_text").getText();
	        Thread.sleep(5000);*/
	        getObject("gb_edit_button").click();
	        Thread.sleep(5000);		    
	        
	        /*---Update GB Title--*/
	        getObject("gb_title_text").clear();
	        Thread.sleep(5000);
		    getObject("gb_title_text").sendKeys(gbTitle);
		    
		    /*---Update GB Description--*/
		    getObject("gb_description_text").clear();
		    Thread.sleep(5000);
		    getObject("gb_description_text").sendKeys(gbDescription);

		    
		   
		    getObject("gb_update_button").click();	  
		  
		/*        String GBName = getObject("gb_title_text").getText();
		  try
		    {
		 	   Assert.assertEquals(GBNameShowPage, GBName, "Governance body updated name is -- " +GBNameShowPage+ " instead of -- " +GBName);
		    }
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Contract Other Folder name is -- " +GBName+ " instead of -- " +GBNameShowPage);
		    }*/
	  }
		  
		  @AfterMethod
		  public void reportDataSetResult() {
		    if (skip)
		      TestUtil.reportDataSetResult(governance_body_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		    else if (fail) {
		      isTestPass = false;
		      TestUtil.reportDataSetResult(governance_body_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		    } else
		      TestUtil.reportDataSetResult(governance_body_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		    skip = false;
		    fail = false;

		  }

		  @AfterTest
		  public void reportTestResult() {
		    if (isTestPass)
		      TestUtil.reportDataSetResult(governance_body_suite_xls, "Test Cases", TestUtil.getRowNum(governance_body_suite_xls, this.getClass().getSimpleName()), "PASS");
		    else
		      TestUtil.reportDataSetResult(governance_body_suite_xls, "Test Cases", TestUtil.getRowNum(governance_body_suite_xls, this.getClass().getSimpleName()), "FAIL");

		  }

		  @DataProvider
		  public Object[][] getTestData() {
		    return TestUtil.getData(governance_body_suite_xls, this.getClass().getSimpleName());
		  }  
}
