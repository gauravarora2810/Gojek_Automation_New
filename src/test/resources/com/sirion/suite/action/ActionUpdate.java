package test.resources.com.sirion.suite.action;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ActionUpdate extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;
	WebElement element;
	JavascriptExecutor executor;
	String numberOfEntries = null;
	String[] numberOfEntriesSplit = null;
	String numberOfContracts;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(action_suite_xls, this.getClass().getSimpleName())) {
			System.out.println("Runmide set to no");
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case "
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports

		}
		// load the runmodes off the tests
		System.out.println("Runmide set to y");
		runmodes = TestUtil.getDataSetRunmodes(action_suite_xls, this
				.getClass().getSimpleName());
	}

	@Test(dataProvider = "getTestData")
	public void ContractListing(String actionTitle,
			String actionDescription, String actionType, String actionPriority,
			String actionResponsibility, String actionGovernanceBody,
			String actionDeliveryCountries, String actionTimeZone,
			String actionCurrencies, String actionTier,
			String actionRequestedOnDate, String actionDueDate,
			String actionFinancialImpact)
			throws InterruptedException, ClassNotFoundException, SQLException {
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "
					+ count);
		}

		APP_LOGS.debug("Executing Test Case Action Workflow");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"),
				CONFIG.getProperty("endUserUsername"),
				CONFIG.getProperty("endUserPassword"));
		Thread.sleep(5000);
		// String s1= driver.findElement(By.xpath("end_user_name")).getText();

/*		String s1 = getObject("end_user_name").getText();*/
		// Clicking the Action quick link
		getObject("action_quick_link").click();
		System.out.println("Clicked the actions quick link");
		Thread.sleep(7000);

		// wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_id_link")));
		// Clicking the first record of the action listing
		getObject("ac_id_link").click();
		System.out.println("clicked the first record");
		Thread.sleep(7000);

		// Clicking the clone button
		 element = getObject("ac_clone_button");
		 executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		//getObject("ac_clone_button").click();
		System.out.println("clicked the clone button");
		Thread.sleep(7000);

		//clicking the create action button after cloning
		//Assert.assertNotNull(driver.findElement(By.xpath("ac_create_action")));
		 element = getObject("ac_create_action");
		 executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		

		if (getObject("ac_popup_id") != null) {

			String action_id = getObject("ac_popup_id").getText();
			APP_LOGS.debug("Action Cloned successfully with Issue id "+ action_id);

			//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(10000);

			APP_LOGS.debug("Quick Search the created Action with Action id "+ action_id);

			getObject("quick_search_textbox").sendKeys(action_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			Thread.sleep(10000);

			String acnIdFromShowPage = getObject("ac_show_id").getText();
			System.out.println("Action Id " + acnIdFromShowPage);

		}

		Thread.sleep(10000);
		WebElement element2 = getObject("ac_edit");
		 executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element2);
		
		//driver.findElement(By.xpath("ac_edit")).click();
		Thread.sleep(7000);
		
		if (!actionTitle.equalsIgnoreCase("")) {
			getObject("ac_title_textbox").clear();
			getObject("ac_title_textbox").sendKeys(actionTitle); // name
		}

		if (!actionDescription.equalsIgnoreCase("")) {
			getObject("ac_description_textarea").clear();
			getObject("ac_description_textarea").sendKeys(actionDescription); // title
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
			
			new Select(getObject("ac_govbody_multi")).selectByVisibleText(actionGovernanceBody);// governance
																// body
		}
		if (!actionDeliveryCountries.equalsIgnoreCase("")) {
			new Select(getObject("ac_delcountries_multi")).selectByVisibleText(actionDeliveryCountries);
		}

		if (!actionTimeZone.equalsIgnoreCase("")) {
			new Select(getObject("ac_timezone_dropdown")).selectByVisibleText(actionTimeZone);
		}
		System.out.println("Timezone Selected");
		Thread.sleep(4000);

		if (!actionCurrencies.equalsIgnoreCase("")) {
			new Select(getObject("ac_currency_dropdown")).selectByVisibleText(actionCurrencies);
		}
		System.out.println("Currency selected");

	

		if (!actionTier.equalsIgnoreCase("")) {
			new Select(getObject("ac_tier_dropdown")).selectByVisibleText(actionTier);
			System.out.println("Tier selected");
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
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Update')]")));
		driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();
		Thread.sleep(10000); 
		
		WebElement element3 = getObject("ac_title_text");
		String strng = element3.getText();
		System.out.println(strng);
		Assert.assertEquals(actionTitle, strng);
		System.out.println("string matched");
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
		return TestUtil.getData(action_suite_xls, this.getClass().getSimpleName());
	}

}
