package test.resources.com.sirion.suite.issue;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class IssueWorkflow extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	JavascriptExecutor executor;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(issue_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(issue_suite_xls, this.getClass().getSimpleName());
		}
	
  @Test(dataProvider = "getTestData")
  public void IssueWorkflowTest() throws InterruptedException, MalformedURLException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}
		APP_LOGS.debug("Executing Test Case Issues Workflow");
    
		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		// Click on Analytics
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("analytics_link")));
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(10000);
		
		getObject("issues_quick_link").click();
		Thread.sleep(20000);

		driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr/td[1]/a")).click();
		Thread.sleep(10000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(20000);		

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Issue')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Issue')]")).click();
		Thread.sleep(10000);

		if (getObject("issues_create_page_issue_id_popup_link") != null) {
			String issueId = getObject("issues_create_page_issue_id_popup_link").getText();
			APP_LOGS.debug("Issue created successfully with Issue ID "+issueId);

			getObject("issues_create_page_issue_id_popup_link").click();
			Thread.sleep(10000);
			String issueShowPageId = getObject("issues_show_page_issue_id").getText();

			Assert.assertEquals(issueShowPageId, issueId);
			APP_LOGS.debug("Issue ID on show page has been verified");
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Acknowledge Issue')]")));
		driver.findElement(By.xpath("//button[contains(.,'Acknowledge Issue')]")).click();
		Thread.sleep(20000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Assign Issue')]")));
		driver.findElement(By.xpath("//button[contains(.,'Assign Issue')]")).click();
		Thread.sleep(20000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit Issue')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit Issue')]")).click();
		Thread.sleep(20000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve Issue')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve Issue')]")).click();
		Thread.sleep(20000);

		fail = false;
/*
		if (!fail)
	        result= TestLinkAPIResults.TEST_PASSED;
	      else   
	         result= TestLinkAPIResults.TEST_FAILED;
	     TestlinkIntegration.updateTestLinkResult(testCaseId,"",result);
*/
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(issue_suite_xls, this.getClass().getSimpleName());
		}
	}
