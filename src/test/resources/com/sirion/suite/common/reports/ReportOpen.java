package test.resources.com.sirion.suite.common.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
import testlink.api.java.client.TestLinkAPIException;

public class ReportOpen extends TestSuiteBase {
	String result = null;
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	static WebDriverWait wait;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(common_report_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(common_report_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void ReportOpenTest (String testCaseId, String entityName, String entityReportName, String entityReportShowPage
			) throws InterruptedException, TestLinkAPIException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +entityName +" set to NO " +count);
			}
		
		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

		wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Reports"))));

		driver.findElement(By.linkText("Reports")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("allLists1"))));

		new Select(driver.findElement(By.id("allLists1"))).selectByVisibleText(entityName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("allLists2"))));

		new Select(driver.findElement(By.id("allLists2"))).selectByVisibleText(entityReportName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("downloadXls"))));

		String reportNameShowPage = driver.findElement(By.id("downloadXls")).getText();
	    Assert.assertEquals(reportNameShowPage, entityReportShowPage, "Report Name is -- " +reportNameShowPage+ " instead of -- "+entityReportShowPage);

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
		if(skip)
			TestUtil.reportDataSetResult(common_report_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(common_report_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(common_report_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(common_report_suite_xls, "Test Cases", TestUtil.getRowNum(common_report_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(common_report_suite_xls, "Test Cases", TestUtil.getRowNum(common_report_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(common_report_suite_xls, this.getClass().getSimpleName());
		}
	}