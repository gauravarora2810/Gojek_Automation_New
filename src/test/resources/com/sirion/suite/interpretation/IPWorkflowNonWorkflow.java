package test.resources.com.sirion.suite.interpretation;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class IPWorkflowNonWorkflow extends TestSuiteBase {
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
System.out.println("In check test skip function");
    if (!TestUtil.isTestCaseRunnable(int_suite_xls, this.getClass().getSimpleName())) {
     System.out.println("In if condition");
    	APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    System.out.println("outside if condition");
    runmodes = TestUtil.getDataSetRunmodes(int_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider="getTestData")
	public void interpretationNonWorkflowTest(String ipName,String Archive, String OnHold, String WFTask1, 
			String WFTask2, String WFTask3, String WFTask4, String WFTask5, String Delete) throws InterruptedException, ClassNotFoundException, SQLException
			 {
	  System.out.println("In main function");
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Interpretaion Workflow");
		System.out.println("before open browser");		
		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("int_quick_link")));
		Thread.sleep(10000);
		getObject("int_quick_link").click(); 
		Thread.sleep(5000);
		getObject("ob_id_link").click();
		Thread.sleep(10000);

		// Clicking the clone button
		  System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

			driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
		//getObject("ac_clone_button").click();
		System.out.println("clicked the clone button");
		driver.navigate().refresh();
		
		Thread.sleep(10000);

		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

			driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
		//clicking the create action button after cloning
		//Assert.assertNotNull(driver.findElement(By.xpath("ac_create_action")));
		  System.out.println("gaurav");
		  
		  Thread.sleep(10000);
		  wait_in_report.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Submit')][@clientvalidation='true']"))));
		  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Submit')][@clientvalidation='true']")));
		   driver.findElement(By.xpath(".//*[@id='angularPopUpHtml']//button[contains(.,'Submit')][@clientvalidation='true']")).click();
			   System.out.println("arora");
			Thread.sleep(10000);

		
	  /*  if(getObject("ob_popup_id")!=null) {
	    	String ob_id = getObject("ob_popup_id").getText();
	    	APP_LOGS.debug("Service Level cloned successfully with Service Level id "+ob_id);
	    	
	    	getObject("ob_popup_id").click();
	    	Thread.sleep(5000);
	    	}*/
	    
	    if (getObject("ob_popup_id") != null) {

			String ob_id = getObject("ob_popup_id").getText();
			APP_LOGS.debug("Action Cloned successfully with Issue id "+ ob_id);

			//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(10000);

			APP_LOGS.debug("Quick Search the created OB with OB id "+ ob_id);

			getObject("quick_search_textbox").sendKeys(ob_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			Thread.sleep(10000);

			String ipIdFromShowPage = getObject("ip_show_id").getText();
			System.out.println("OB Id " + ipIdFromShowPage);

		}
        

         
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
     
    
     
     /*if(!WFTask1.equalsIgnoreCase(""))
		{
    	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask1+"')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask1+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask1+"')]")).click();
    	 	
		}*/
     
     if(!WFTask2.equalsIgnoreCase(""))
		{
    	 Thread.sleep(10000);
    	 WebElement we = driver.findElement(By.xpath("//button[contains(text(),'"+WFTask2+"')]"));
    	 wait_in_report.until(ExpectedConditions.visibilityOf(we));
    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")));
    	 //Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")).click();
    	 	
		}
     
     if(!WFTask3.equalsIgnoreCase(""))
		{
    	 Thread.sleep(10000);
    	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")).click();
    	 	
		}
     
     if(!WFTask4.equalsIgnoreCase(""))
		{
    	 Thread.sleep(10000);
    	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")).click();
    	 	driver.findElement(By.xpath(".//*[@id='elem_86']/textarea")).clear();
    	 	driver.findElement(By.xpath(".//*[@id='elem_86']/textarea")).sendKeys("Comment added");
    	 	Thread.sleep(10000);
    	 	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")).click();
		}	
    	 	
     System.out.println(WFTask5);
    /* if(!WFTask5.equalsIgnoreCase(""))
		{
    	 Thread.sleep(10000);
 	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
 	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
 	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")).click();
 	 	driver.findElement(By.xpath(".//*[@id='elem_86']/textarea")).clear();
 	 	driver.findElement(By.xpath(".//*[@id='elem_86']/textarea")).sendKeys("Comment added");
 	 	Thread.sleep(10000);
 	 	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")).click();
		
		}*/
     
     if(!Delete.equalsIgnoreCase(""))
		{
    	 Thread.sleep(10000);	 
 	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Delete')]")));
 	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Delete')]")));
 	 driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();
 	 Thread.sleep(10000);

      if (driver.getPageSource().contains("Are you sure you would like to delete this entity?")) {
      	APP_LOGS.debug("Are you sure you would like to delete this entity?");

  		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Yes')]")));
  		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
  		Thread.sleep(10000);
  		
      	
      	}
		}
     		
     driver.get(CONFIG.getProperty("endUserURL"));
	    fail = false;
		
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
