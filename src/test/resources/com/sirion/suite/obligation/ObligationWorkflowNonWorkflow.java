package test.resources.com.sirion.suite.obligation;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class ObligationWorkflowNonWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;
	WebElement element;
	JavascriptExecutor executor;
	
	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(obligation_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider="getTestData") 
	public void ObligationNonWorkflowTest(String Obtitle, String Archive, String OnHold, 
			String WFTask1, String WFTask2, String WFTask3, String WFTask4, String WFTask5, String Delete) 
					throws InterruptedException, ClassNotFoundException, SQLException {
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Service Level Workflow");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		
		Thread.sleep(10000);
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("obligation_quick_link")));
		
        getObject("obligation_quick_link").click();
        APP_LOGS.debug("Click on Obligations Quick Link");
        Thread.sleep(5000);
        getObject("ob_link").click();
		Thread.sleep(5000);
		getObject("ob_id_link").click();
		Thread.sleep(10000);

		// Clicking the clone button
		  System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

			driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
		//getObject("ac_clone_button").click();
		System.out.println("clicked the clone button");
		Thread.sleep(10000);

		//clicking the create action button after cloning
		//Assert.assertNotNull(driver.findElement(By.xpath("ac_create_action")));
		  System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));
		   System.out.println("kumar");
			driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
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

			String obIdFromShowPage = getObject("ob_show_id").getText();
			System.out.println("OB Id " + obIdFromShowPage);

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
    	 WebElement we = driver.findElement(By.xpath("//button[contains(text(),'"+WFTask2+"')]"));
    	 wait_in_report.until(ExpectedConditions.visibilityOf(we));
    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")));
    	 //Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")).click();
    	 	
		}
     
     if(!WFTask3.equalsIgnoreCase(""))
		{
    	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")).click();
    	 	
		}
     
     if(!WFTask4.equalsIgnoreCase(""))
		{
    	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")).click();
		}	
    	 	
     System.out.println(WFTask5);
     if(!WFTask5.equalsIgnoreCase(""))
		{
 	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
 	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
 	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")).click();
		
		}
     if(!Delete.equalsIgnoreCase(""))
		{
    	 wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Delete')]")));
    	 Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Delete')]")));
    	 driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();
    	 Thread.sleep(5000);

         if (driver.getPageSource().contains("Are you sure you would like to delete this entity?")) {
         	APP_LOGS.debug("Are you sure you would like to delete this entity?");

     		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Yes')]")));
     		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
     		Thread.sleep(5000);
     		
         	fail=false;
         	}
		}
     		
     driver.get(CONFIG.getProperty("endUserURL"));
	    fail = false;
		
		}
    
          
  @AfterMethod
  public void reportDataSetResult() {
	  if (skip)
		  TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
	  else if (fail) {
		  isTestPass = false;
		  TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		  }
	  else
		  TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
	  
	  skip = false;
	  fail = false;
	  }

  @AfterTest
  public void reportTestResult() {
	  if(isTestPass)
		  TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()), "PASS");
	  else
		  TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()), "FAIL");
	  }

  @DataProvider
  public Object[][] getTestData() {
	  return TestUtil.getData(obligation_suite_xls, this.getClass().getSimpleName());
	  }
  }
