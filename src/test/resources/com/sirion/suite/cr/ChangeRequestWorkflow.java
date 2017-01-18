package test.resources.com.sirion.suite.cr;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ChangeRequestWorkflow extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(cr_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(cr_suite_xls, this.getClass().getSimpleName());
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
		
		APP_LOGS.debug("Executing Test Case Invoice Workflow");
				
		openBrowser();
	  	

		// Calling a method for login(at different platform) from TestBase.java file
		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
	
		
		getObject("analytics_link").click();
 getObject("cr_quick_link").click(); // IP Quick Link Clicking

 
 Thread.sleep(20000);
 
 getObject("cr_id_link").click(); 
		
		
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(20000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
        
        
        
	    if(getObject("cr_popup_id")!=null){
			
	    	String cr_id = getObject("cr_popup_id").getText();
	    	APP_LOGS.debug("Action Cloned successfully with Issue id "+cr_id);
		
	    	AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	Thread.sleep(5000);
		
	    	APP_LOGS.debug("Quick Search the created Action with Action id " + cr_id);
		
	    	getObject("quick_search_textbox").sendKeys(cr_id);
		
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	Thread.sleep(5000);
		
	    	String crIdFromShowPage = getObject("cr_show_id").getText();
	    	System.out.println("Action Id " + crIdFromShowPage);
		
		}
		Thread.sleep(10000);
		
		AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		Thread.sleep(5000);
         AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         
         
       //this executes if assertion passes 
		fail = false; 
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
