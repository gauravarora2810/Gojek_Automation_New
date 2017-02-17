package test.resources.com.sirion.suite.contractTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ContractTemplateWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(contract_template_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(contract_template_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void ContractTemplateWorkflowTest(String templateName) throws InterruptedException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data set to NO " + count);
			}

		APP_LOGS.debug("Executing Contract Template Workflow Test --- ");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(10000);
		
		getObject("cdr_quick_link").click();
		Thread.sleep(10000);
		
		new Actions(driver).moveToElement(driver.findElement(By.className("dropdown-toggle"))).clickAndHold().build().perform();
		
		driver.findElement(By.linkText("View Contract Templates")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr/td[1]/a")).click();
		Thread.sleep(10000);

		driver.findElement(By.linkText("GENERAL")).click();
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(10000);
		
		if (!templateName.equalsIgnoreCase("")) {
			getObject("contract_template_create_page_name_textbox").sendKeys(templateName);
			}
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Template')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Template')]")).click();
		Thread.sleep(10000);
		
		if (getObject("contract_template_contract_template_id_popup_link") != null) {
			String templateId = getObject("contract_template_contract_template_id_popup_link").getText();
			APP_LOGS.debug("Contract Template created successfully with Contract Template id "+templateId);

			getObject("contract_template_contract_template_id_popup_link").click();
			Thread.sleep(10000);

			driver.findElement(By.linkText("GENERAL")).click();

			String templateShowPageId = getObject("contract_template_show_page_id").getText();

			Assert.assertEquals(templateShowPageId, templateId);
			APP_LOGS.debug("Contract Template ID on show page has been verified");
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review Template')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Client Review Template')]")).click();
		Thread.sleep(10000);

		driver.findElement(By.linkText("GENERAL")).click();
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve Template')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve Template')]")).click();
		Thread.sleep(10000);

		driver.findElement(By.linkText("GENERAL")).click();
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish Template')]")));
		driver.findElement(By.xpath("//button[contains(.,'Publish Template')]")).click();
		Thread.sleep(10000);		

		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(contract_template_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(contract_template_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(contract_template_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
		
		skip = false;
		fail = true;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(contract_template_suite_xls, "Test Cases", TestUtil.getRowNum(contract_template_suite_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(contract_template_suite_xls, "Test Cases", TestUtil.getRowNum(contract_template_suite_xls, this.getClass().getSimpleName()), "FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(contract_template_suite_xls, this.getClass().getSimpleName());
		}
	}
