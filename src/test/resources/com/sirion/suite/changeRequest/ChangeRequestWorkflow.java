package test.resources.com.sirion.suite.changeRequest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

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

		if (!TestUtil.isTestCaseRunnable(cr_suite_xls, this.getClass()
				.getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "
					+ this.getClass().getSimpleName() + " as runmode set to NO");
		}
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(cr_suite_xls, this.getClass()
				.getSimpleName());
	}

	@Test
	public void ChangerequestWorkflow() throws InterruptedException,
			ClassNotFoundException, SQLException {
		// test the run mode of current data set
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "
					+ count);
		}

		APP_LOGS.debug("Executing Test Case Invoice Workflow");

		// Opening Browser
		openBrowser();

		// Calling a method for login(at different platform) from TestBase.java file
		
		endUserLogin(CONFIG.getProperty("endUserURL"),
				CONFIG.getProperty("endUserUsername"),
				CONFIG.getProperty("endUserPassword"));

		/*// Click analytics link
		Thread.sleep(10000);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("analytics_link"));
//wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("analytics_link"))).click();
getObject("analytics_link").click();
		// Click on CR quick link
		wait_in_report.until(
				ExpectedConditions
						.elementToBeClickable(getObject("cr_quick_link"))).click();

		// Clicking Change request CR-ID
		wait_in_report.until(
				ExpectedConditions
						.elementToBeClickable(getObject("cr_id_link"))).click();

		// Click on Clone button
		wait_in_report.until(
				ExpectedConditions
						.elementToBeClickable(getObject("cr_show_page_clone_button"))).click();
	
		//wait_in_report.until(
			//	ExpectedConditions.elementToBeClickable(getObject("cr_save_button"))).click();
		Thread.sleep(10000);
		wait_in_report.until(
				ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")))).click();
		// Saving cloned CR
		Assert.assertNotNull(getObject("cr_save_button"));
		getObject("cr_save_button").click();

		wait_in_report.until(
				ExpectedConditions
						.elementToBeClickable(getObject("cr_popup_id")))
				.click();

		if (getObject("cr_popup_id") != null) {

			// Get CR id text
			String cr_id = getObject("cr_popup_id").getText();
			APP_LOGS.debug("Action Cloned successfully with Issue id " + cr_id);

			// Click OK on pop up
			AssertJUnit.assertNotNull(getObject("cr_popup_ok_button"));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();

			APP_LOGS.debug("Quick Search the created Action with Action id "
					+ cr_id);

			// search CR in Search box
			wait_in_report.until(ExpectedConditions
					.elementToBeClickable(getObject("quick_search_textbox")));
			getObject("quick_search_textbox").sendKeys(cr_id);
			getObject("quick_search_textbox").sendKeys(Keys.ENTER);

			// Get CR id value from show page
			wait_in_report.until(ExpectedConditions
					.visibilityOf(getObject("cr_show_page_id")));
			String crIdFromShowPage = getObject("cr_show_page_id").getText();
			System.out.println("Action Id " + crIdFromShowPage);

		}*/
		
		

		Thread.sleep(10000);
		// Click analytics
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		getObject("analytics_link").click();


		
		Thread.sleep(10000);
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("cr_quick_link")));
		
        getObject("cr_quick_link").click();
        APP_LOGS.debug("Click on Obligations Quick Link");
        
		Thread.sleep(10000);
		getObject("cr_id_link").click();
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
	    
	    if (getObject("cr_popup_id") != null) {

			String cr_id = getObject("cr_popup_id").getText();
			APP_LOGS.debug("CR Cloned successfully with Issue id "+ cr_id);

			//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(10000);

			APP_LOGS.debug("Quick Search the created CR with CR id "+ cr_id);

			getObject("quick_search_textbox").sendKeys(cr_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			Thread.sleep(10000);

			String crIdFromShowPage = getObject("cr_show_page_id").getText();
			System.out.println("CR Id " + crIdFromShowPage);

		}
        
        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Thread.sleep(10000);
		// Click on Submit button
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		

		
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); 
		
		
		// Click on Approve
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); 

		// Click on Approve
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); 

		// Click on Approve
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); 

		// Click on Approve

		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); 

		// Click on Approve
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); 
		// Click on Approve
		/*Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("cr_show_page_approve_button"));
		
		getObject("cr_show_page_approve_button").click(); */

		// this executes if assertion passes
		fail = false;
	}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(cr_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(cr_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(cr_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil
					.getRowNum(cr_suite_xls, this.getClass().getSimpleName()),
					"PASS");
		else
			TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil
					.getRowNum(cr_suite_xls, this.getClass().getSimpleName()),
					"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(cr_suite_xls, this.getClass().getSimpleName());
	}

}
