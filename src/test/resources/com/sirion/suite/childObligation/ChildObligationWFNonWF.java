package test.resources.com.sirion.suite.childObligation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class ChildObligationWFNonWF extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(child_obligation_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(child_obligation_suite_xls, this.getClass().getSimpleName());
  }

  @Test(dataProvider="getTestData") 
	public void ChildObligationWFNonWFTest(String COBTitle, String Archive,String WFTask1, String WFTask2, String cobComment,String WFTask3, String OnHold, String WFTask4, String Delete) throws InterruptedException, ClassNotFoundException, SQLException, AWTException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Contract Workflow");
				
		openBrowser();
	  	

		// Calling a method for login(at different platform) from TestBase.java file
		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
		WebDriverWait wait=new WebDriverWait(driver, 50);
		
		getObject("analytics_link").click();
		getObject("obligation_quick_link").click(); // IP Quick Link Clicking
		Thread.sleep(20000);
		

		getObject("cob_id_link").click();
		Thread.sleep(10000);
		/*driver.findElement(By.xpath("//a[contains(.,'"+COBTitle+"')]")).click();
		Thread.sleep(25000);*/
		
		 if(!Archive.equalsIgnoreCase(""))
			{
	    	 wait_in_report.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Archive')]")));
	    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Archive')]")));
	    	 	driver.findElement(By.xpath("//button[contains(.,'Archive')]")).click();
	    	 	wait_in_report.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Restore')]")));
	     	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Restore')]")));
	     	 	driver.findElement(By.xpath("//button[contains(.,'Restore')]")).click();
			}
	     
	     
	     
	     if(!OnHold.equalsIgnoreCase(""))
			{
	    	 wait_in_report.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'On Hold')]")));
	    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'On Hold')]")));
	    	 	driver.findElement(By.xpath("//button[contains(.,'On Hold')]")).click();
	    	 	wait_in_report.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Activate')]")));
	    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Activate')]")));
	    	 	driver.findElement(By.xpath("//button[contains(.,'Activate')]")).click();
	    	 	
			}
		
		if(!WFTask1.equalsIgnoreCase(""))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask1+"')]")));
			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask1+"')]")));
			driver.findElement(By.xpath("//button[contains(.,'"+WFTask1+"')]")).click();
			Thread.sleep(5000);
		}

		if(!WFTask2.equalsIgnoreCase(""))
		{
			WebElement we = driver.findElement(By.xpath("//button[contains(text(), '"+WFTask2+"')]"));
			wait.until(ExpectedConditions.visibilityOf(we));
			driver.findElement(By.xpath("//p[contains(.,'BASIC INFORMATION')]")).click();
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			
			if (!cobComment.equalsIgnoreCase("")) {
				getObject("cob_comment_box").click();
				getObject("cob_comment_box").sendKeys(cobComment);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//p[contains(.,'BASIC INFORMATION')]")).click();
				
				}
			
			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")));
			driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")).click();
			Thread.sleep(5000);
		}
		if(!WFTask3.equalsIgnoreCase(""))
		{
			WebElement we = driver.findElement(By.xpath("//button[contains(text(), '"+WFTask3+"')]"));
			wait.until(ExpectedConditions.visibilityOf(we));
			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
			driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")).click();
			Thread.sleep(5000);
		}
		
		
     if(!WFTask4.equalsIgnoreCase(""))
		{
			WebElement we = driver.findElement(By.xpath("//button[contains(text(), '"+WFTask4+"')]"));
			wait.until(ExpectedConditions.visibilityOf(we));
			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
			driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")).click();
			Thread.sleep(5000);
		}
     
     if(!Delete.equalsIgnoreCase(""))
		{
 	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Delete')]")));
 	 Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Delete')]")));
 	 driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();
 	 Thread.sleep(5000);

      if (driver.getPageSource().contains("Are you sure you would like to delete this entity?")) {
      	APP_LOGS.debug("Are you sure you would like to delete this entity?");

  		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Yes')]")));
  		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
  		Thread.sleep(5000);
  		
      	fail=false;
      	driver.get(CONFIG.getProperty("endUserURL"));
      	return;
      	}
		}
              
		fail = false; //this executes if assertion passes
	 }
			 
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(child_obligation_suite_xls, "Test Cases", TestUtil.getRowNum(child_obligation_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(child_obligation_suite_xls, "Test Cases", TestUtil.getRowNum(child_obligation_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(child_obligation_suite_xls, this.getClass().getSimpleName());
  }

}
