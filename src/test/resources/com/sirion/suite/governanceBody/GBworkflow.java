package test.resources.com.sirion.suite.governanceBody;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class GBworkflow extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(governance_body_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(governance_body_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider = "getTestData")
	public void ContractListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Governance Body Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		
		Thread.sleep(10000);
		// Click analytics
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		getObject("analytics_link").click();
		
		Thread.sleep(10000);
		getObject("gb_quick_link").click(); 
		Thread.sleep(10000);
		getObject("gb_listing_link").click(); 
		Thread.sleep(10000);
		
		//wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_id_link")));
		getObject("gb_id_link").click();
		
			 
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(10000);
        
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Save')][@clientvalidation='true']")));
		driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
	    if(getObject("gb_popup_id")!=null){
			
	    	String gb_id = getObject("gb_popup_id").getText();
	    	APP_LOGS.debug("Governance Body Cloned successfully with GB id "+gb_id);
		
	    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	Thread.sleep(10000);
		
	    	APP_LOGS.debug("Quick Search the created Governance Body with GB id " + gb_id);
		
	    	getObject("quick_search_textbox").sendKeys(gb_id);
	    	
	    	Thread.sleep(5000);
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	Thread.sleep(10000);
		
	    	
	    	/*---Send for Internal review-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_sendforinternalreview_button"));
	    	getObject("gb_sendforinternalreview_button").click();
	    	
	    	Thread.sleep(10000);
	    	/*---Internal review complete-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_internalreviewcomplete_button"));
	    	getObject("gb_internalreviewcomplete_button").click();
	    	Thread.sleep(10000);

	    	/*---Send for client review-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_sendforclientreview_button"));
	    	getObject("gb_sendforclientreview_button").click();
	    	
	    	Thread.sleep(10000);
	    	/*---approve-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_approve_button"));
	    	getObject("gb_approve_button").click();
	    	
	    	Thread.sleep(10000);
	    	/*---publish-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_publish_button"));
	    	getObject("gb_publish_button").click();
	    	
	    	Thread.sleep(10000);

	    	/*---archive-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_archive_button"));
	    	getObject("gb_archive_button").click();
	    	Thread.sleep(10000);

	    	/*---restore-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_restore_button"));
	    	getObject("gb_restore_button").click();
	    	Thread.sleep(10000);

	    	/*---onhold-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_onhold_button"));
	    	getObject("gb_onhold_button").click();
	    	Thread.sleep(10000);
	    	

	    	/*---activate-----*/
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("gb_activate_button"));
	    	getObject("gb_activate_button").click();
	    	
	    	Thread.sleep(5000);
	    
		
	    	String gbIdFromShowPage = getObject("gb_show_id").getText();
	    	System.out.println("Governance Body " + gbIdFromShowPage);
		
		}
	    
	    fail=false;
			 }
		/*Thread.sleep(10000);
		driver.findElement(By.xpath("//textarea[@name='processAreaImpacted']")).clear();
		
		driver.findElement(By.xpath("//textarea[@name='processAreaImpacted']")).sendKeys("Process Area Impacted");
		driver.findElement(By.xpath("//textarea[@name='actionTaken']")).clear();
		driver.findElement(By.xpath("//textarea[@name='actionTaken']")).sendKeys("Action Taken");
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		Thread.sleep(10000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(10000);
         String ob_status = getObject("ac_status_id").getText();
         
         Assert.assertEquals(ob_status, "Approved");     
         
              
		fail = false; //this executes if assertion passes
			 }
          */
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
