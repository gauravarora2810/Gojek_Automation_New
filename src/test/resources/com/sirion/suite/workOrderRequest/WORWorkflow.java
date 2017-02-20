package test.resources.com.sirion.suite.workOrderRequest;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class WORWorkflow extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(wor_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(wor_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void worListing() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case WOR Workflow");
				
		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

		//Click WOR quick link
				wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("wor_quick_link"))).click();
		
		
				Thread.sleep(10000);
		
	    getObject("wor_id_link").click();
		
	    Thread.sleep(10000);
		   System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

			driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
		//getObject("ac_clone_button").click();
		System.out.println("clicked the clone button");
		Thread.sleep(10000);

		//clicking the create action button after cloning
		//Assert.assertNotNull(driver.findElement(By.xpath("ac_create_action")));
		  System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Submit')][@clientvalidation='true']")));
		   System.out.println("kumar");
			driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Submit')][@clientvalidation='true']")).click();
			   System.out.println("arora");
			Thread.sleep(10000);
		 //Add current user as stakeholder
/*		 String Stakeholder =driver.findElement(By.xpath(".//*[@id='elem_100014']/div[1]/div/input[1]")).getText();
		 System.out.println(Stakeholder);
		 String Username=driver.findElement(By.xpath(".//*[@id='headerNavbar']/div[2]/div[3]")).getText();
		 System.out.println(Username);
		 
		 if(!Stakeholder.equalsIgnoreCase(Username)){
			 
			 WebElement Stakeholder1=driver.findElement(By.xpath(".//*[@id='elem_100014']/div[1]/input[1]"));
			 Stakeholder1.sendKeys(Username);
		 }*/
		 
		 
		//driver.findElement(By.xpath("//button[contains(.,'Submit')][@clientvalidation='true']")).click();
		
        
        
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("wor_popup_id")));
	    if(getObject("wor_popup_id")!=null){
	    	
	    	String wor_id = getObject("wor_popup_id").getText();
	    	APP_LOGS.debug("wor Cloned successfully with WOR ID "+wor_id);
		
	    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	
		
	    	APP_LOGS.debug("Quick Search the created WOR with WOR id " + wor_id);
	    	wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("quick_search_textbox")));
	    	getObject("quick_search_textbox").sendKeys(wor_id);
		Thread.sleep(5000);
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	
	    	wait_in_report.until(ExpectedConditions.visibilityOf(getObject("wor_show_page_id")));
	    	String worIdFromShowPage = getObject("wor_show_page_id").getText();
	    	System.out.println("wor_id " + worIdFromShowPage);
	    	
		}
		Thread.sleep(10000);
	    //Click on Approve
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
	    wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'Approve')]"))));
		 Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
        
       //Click on Approve
         Thread.sleep(10000);
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'Approve')]"))));
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();

       //Click on TL5 Approve
         Thread.sleep(10000);
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'TL5 Approve')]")));
         wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'TL5 Approve')]"))));
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'TL5 Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'TL5 Approve')]")).click();
         
       //Click on Approve
         Thread.sleep(10000);
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'Approve')]"))));
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         
       //Click on Approve
         Thread.sleep(10000);
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'Approve')]"))));
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
        
       //Click on Upload Executed Project Plan
         Thread.sleep(10000);
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Upload Executed Project Plan')]")));
         wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'Upload Executed Project Plan')]"))));
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Upload Executed Project Plan')]")));
         driver.findElement(By.xpath("//button[contains(.,'Upload Executed Project Plan')]")).click();
         
       
              
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
