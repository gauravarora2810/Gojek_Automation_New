package test.resources.com.sirion.suite.action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

@Listeners(ActionCreation.class)
public class ActionCreation extends TestSuiteBase implements ITestListener {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(action_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
			}
		runmodes = TestUtil.getDataSetRunmodes(action_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void ActionCreationTest (String actionTitle, String actionDescription, String actionType, String actionPriority, String actionResponsibility,
			String actionGovernanceBody, String actionDeliveryCountries, String actionTimeZone, String actionCurrencies, String actionSupplierAccess,
			String actionDependentEntity, String actionTier, String actionRequestedOnDate, String actionDueDate, String actionFinancialImpact
			) throws InterruptedException {
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data set to NO " + count);
			}

		APP_LOGS.debug("Executing Test Case Action Creation from Service Level");

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

		getObject("contract_quick_link").click();
		Thread.sleep(20000);

        driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(10000);

		plus_button("contracts_plus_button");
		driver.findElement(By.linkText("Create Action")).click();
		
		if (!actionTitle.equalsIgnoreCase("")) {
			getObject("ac_title_textbox").sendKeys(actionTitle);
			}

		if (!actionDescription.equalsIgnoreCase("")) {
			getObject("ac_description_textarea").sendKeys(actionDescription);
			}
		
		if (!actionType.equalsIgnoreCase("")) {
			new Select(getObject("ac_type_dropdown")).selectByVisibleText(actionType);
			}
		
		if (!actionPriority.equalsIgnoreCase("")) {
			new Select(getObject("ac_priority_dropdown")).selectByVisibleText(actionPriority);
			}
		
		if (!actionResponsibility.equalsIgnoreCase("")) {
			new Select(getObject("ac_responsibility_dropdown")).selectByVisibleText(actionResponsibility);
			}
		
		if (!actionGovernanceBody.equalsIgnoreCase("")) {
			new Select(getObject("ac_govbody_multi")).selectByVisibleText(actionGovernanceBody);
			}
		
		if (!actionDeliveryCountries.equalsIgnoreCase("")) {
			new Select(getObject("ac_delcountries_multi")).selectByVisibleText(actionDeliveryCountries);
			}

		if (!actionTimeZone.equalsIgnoreCase("")) {
			new Select(getObject("ac_timezone_dropdown")).selectByVisibleText(actionTimeZone);
			try {
	      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
	      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	      	} catch (Exception e) {
	      		
	      	}
			}
		Thread.sleep(5000);

		if (!actionCurrencies.equalsIgnoreCase("")) {
			try
			{
			new Select(getObject("ac_currency_dropdown")).selectByVisibleText(actionCurrencies);
			}
			catch(Exception e)
			{
				new Select(getObject("ac_currency_dropdown")).selectByIndex(1);
				}
			}

		if (actionSupplierAccess.equalsIgnoreCase("Yes")) {
			getObject("action_supplier_access_checkbox").isEnabled();
			}

		if (!actionDependentEntity.equalsIgnoreCase("Yes")) {
			getObject("ac_depentity_checkbox").click();
			}

		if (!actionTier.equalsIgnoreCase("")) {
			new Select(getObject("ac_tier_dropdown")).selectByVisibleText(actionTier);
			}
		Thread.sleep(5000);
		
        if (!actionRequestedOnDate.equalsIgnoreCase("")) {
            driver.findElement(By.name("requestedOn")).click();
            String[] acDate = actionRequestedOnDate.split("-");

            String acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
            while (!acMonth.equalsIgnoreCase(acDate[0])) {
                  driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
                  acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();                  
                  }

            new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(acDate[2]);

            driver.findElement(By.linkText(acDate[1])).click();
             }
        Thread.sleep(5000);
        
        
        if (!actionDueDate.equalsIgnoreCase("")) {
            driver.findElement(By.name("plannedCompletionDate")).click();
            String[] acDate = actionDueDate.split("-");

            String acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
            while (!acMonth.equalsIgnoreCase(acDate[0])) {
                  driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
                  acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
                  }

            new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(acDate[2]);

            driver.findElement(By.linkText(acDate[1])).click();
            }
		Thread.sleep(5000);

		getObject("ac_create_action").click();

		Thread.sleep(5000);
		String action_id = getObject("ac_popup_id").getText();

		APP_LOGS.debug("Action created successfully with Action ID "+ action_id);

		getObject("ac_popup_ok_button_from_ip").click();

		APP_LOGS.debug("Quick Search the created action with Action ID "+ action_id);

		getObject("quick_search_textbox").sendKeys(action_id);
		getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		
		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(action_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(action_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(action_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(action_suite_xls, "Test Cases",
					TestUtil.getRowNum(action_suite_xls, this.getClass()
							.getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(action_suite_xls, "Test Cases",
					TestUtil.getRowNum(action_suite_xls, this.getClass()
							.getSimpleName()), "FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(action_suite_xls, this.getClass()
				.getSimpleName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}