package test.resources.com.sirion.suite.cdr;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class CDRWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(cdr_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(cdr_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void CDRWorkflowTest () throws InterruptedException {

		count++;
        if(!runmodes[count].equalsIgnoreCase("Y")) {
            skip = true;
            throw new SkipException("Runmode for Test Data set to NO " + count);
            }
        
        APP_LOGS.debug("Executing Test Case CDR Workflow");

        openBrowser();
        endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
        
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(5000);
		
		getObject("cdr_quick_link").click();
		Thread.sleep(20000);

		driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(10000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(10000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save CDR')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save CDR')]")).click();
		Thread.sleep(10000);

		if (getObject("cdr_create_page_cdr_id_popup_link") != null) {
			String cdrId = getObject("cdr_create_page_cdr_id_popup_link").getText();
			APP_LOGS.debug("CDR created successfully with CDR id "+cdrId);

			getObject("cdr_create_page_cdr_id_popup_link").click();
			Thread.sleep(10000);

			String cdrShowPage = getObject("cdr_show_page_id").getText();

			Assert.assertEquals(cdrShowPage, cdrId);
			APP_LOGS.debug("CDR ID on show page has been verified");
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review CDR')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Client Review CDR')]")).click();
		Thread.sleep(20000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve CDR')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve CDR')]")).click();
		Thread.sleep(20000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish CDR')]")));
		driver.findElement(By.xpath("//button[contains(.,'Publish CDR')]")).click();
		Thread.sleep(20000);
		
		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(cdr_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if(fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(cdr_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(cdr_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
		
		skip = false;
		fail = true;
		}

	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(cdr_suite_xls, "Test Cases", TestUtil.getRowNum(cdr_suite_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(cdr_suite_xls, "Test Cases", TestUtil.getRowNum(cdr_suite_xls, this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(cdr_suite_xls, this.getClass().getSimpleName());
		}
	}