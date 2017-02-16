package test.resources.com.sirion.suite.contract;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ContractWorkflow extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	JavascriptExecutor executor;
	static  WebDriverWait wait;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
		}
	
  @Test(dataProvider = "getTestData")
  public void ContractsWorkflowTest(String coContractNo, String coConversionMatrix, String coConversionMatrixFrom, String coConversionMatrixTo
		  ) throws InterruptedException, MalformedURLException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}
		APP_LOGS.debug("Executing Test Case Contract Workflow with Contract Number --- "+coContractNo);

		wait = new WebDriverWait(driver, 60);
		
		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Analytics"))));

		// Click on Analytics
		driver.findElement(By.linkText("Analytics")).click();
		wait.until(ExpectedConditions.elementToBeClickable(getObject("contract_quick_link")));
		
		getObject("contract_quick_link").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a"))));

		driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Approve')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(.,'Publish')]"))));

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish')]")));
		driver.findElement(By.xpath("//button[contains(.,'Publish')]")).click();
		Thread.sleep(20000);

		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(contract_suite_xls, this.getClass().getSimpleName());
		}
	}
