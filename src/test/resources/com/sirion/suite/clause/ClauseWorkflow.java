package test.resources.com.sirion.suite.clause;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ClauseWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(clause_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(clause_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void ClauseWorkflowTest(String clauseName) throws InterruptedException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data set to NO " + count);
			}

		APP_LOGS.debug("Executing Clause Workflow Test --- ");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);
		
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(10000);
		
		getObject("cdr_quick_link").click();
		Thread.sleep(10000);
		
		new Actions(driver).moveToElement(driver.findElement(By.className("dropdown-toggle"))).clickAndHold().build().perform();
		
		driver.findElement(By.linkText("View Clause Library")).click();
		Thread.sleep(10000);

		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(".//*[@id='products']/div[1]"));
		action.moveToElement(element).build().perform();
		driver.findElement(By.xpath(".//*[@id='products']/div[1]/div[2]/a")).click();
		Thread.sleep(10000);

		driver.findElement(By.linkText("GENERAL")).click();
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(10000);
		
		DateFormat dateFormat = new SimpleDateFormat("MMMddyyyyHHmmss");
		Date date = new Date();
		String date1= dateFormat.format(date);

		getObject("clause_create_page_name_textbox").clear();
		getObject("clause_create_page_name_textbox").sendKeys(clauseName);
		getObject("clause_create_page_name_textbox").sendKeys(date1);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Clause')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Clause')]")).click();
		Thread.sleep(10000);

		if (getObject("clause_create_page_clause_id_popup_link") != null) {
			String clauseId = getObject("clause_create_page_clause_id_popup_link").getText();
			APP_LOGS.debug("Clause created successfully with Clause id "+clauseId);

			getObject("clause_create_page_clause_id_popup_link").click();
			Thread.sleep(10000);
			
			driver.findElement(By.linkText("GENERAL")).click();

			String clauseShowPage = getObject("clause_show_page_id").getText();

			Assert.assertEquals(clauseShowPage, clauseId);
			APP_LOGS.debug("Clause ID on show page has been verified");
			}
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review Clause')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Client Review Clause')]")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.linkText("GENERAL")).click();
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve Clause')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve Clause')]")).click();
		Thread.sleep(10000);

		driver.findElement(By.linkText("GENERAL")).click();
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish Clause')]")));
		driver.findElement(By.xpath("//button[contains(.,'Publish Clause')]")).click();
		Thread.sleep(10000);
		
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
			TestUtil.reportDataSetResult(clause_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(clause_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(clause_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
		
		skip = false;
		fail = true;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(clause_suite_xls, "Test Cases", TestUtil.getRowNum(clause_suite_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(clause_suite_xls, "Test Cases", TestUtil.getRowNum(clause_suite_xls, this.getClass().getSimpleName()), "FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(clause_suite_xls, this.getClass().getSimpleName());
		}
	}
