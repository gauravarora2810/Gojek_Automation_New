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

		// Click analytics link
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
		/*// Saving cloned CR
		Assert.assertNotNull(getObject("cr_save_button"));
		getObject("cr_save_button").click();*/

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
