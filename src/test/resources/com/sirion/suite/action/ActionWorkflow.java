package test.resources.com.sirion.suite.action;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ActionWorkflow extends TestSuiteBase {
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
	String s1;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(action_suite_xls, this.getClass().getSimpleName())) {
			
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case "
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports

		}
		// load the runmodes off the tests
		
		runmodes = TestUtil.getDataSetRunmodes(action_suite_xls, this
				.getClass().getSimpleName());
	}

	@Test(dataProvider = "getTestData")
	public void ContractListing(String actionTaken)
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
		
		 s1 = getObject("end_user_name").getText();
		 System.out.println("end user name "+s1);
		// Clicking the Action quick link
		Thread.sleep(5000);
		 getObject("action_quick_link").click();
		System.out.println("Clicked the actions quick link");
		Thread.sleep(7000);

		
		// Clicking the first record of the action listing
		getObject("ac_id_link").click();
		System.out.println("clicked the first record");
		Thread.sleep(7000);

		// Clicking the clone button
		 element = getObject("ac_clone_button");
		 executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		getObject("ac_clone_button").click();
		System.out.println("clicked the clone button");
		Thread.sleep(10000);

		/*String s = getObject("ac_stakeholder").getText();*/

		
		/*if (!s.equals(s1)) {
			//driver.findElement(By.xpath("//input[@class='ui-icon ui-icon-close']")).click();
			driver.findElement(By.xpath("//div[@class='ng-pristine ng-valid']/input")).click();
			driver.findElement(By.xpath("//div[@class='ng-pristine ng-valid']/input")).sendKeys(s1);
			//driver.findElement(By.xpath(".//*[@id='ui-id-49']")).click();
			Thread.sleep(5000);
		}*/

		int no_of_rolegroup=driver.findElement(By.xpath(".//fieldset[p[contains(.,'STAKEHOLDERS')]]")).findElements(By.tagName("label")).size();
		System.out.println(no_of_rolegroup);
	//	.//fieldset[p[contains(.,'STAKEHOLDERS')]]/label[1]/span/div//input[@type='text'][1]
	//	int no_of_input_box_each_rolegroup=driver.findElement(By.xpath(".//fieldset[p[contains(.,'STAKEHOLDERS')]]/label[1]/span/div")).findElements(By.tagName("input[@type='text']")).size();
		
		for(int i=1; i<=no_of_rolegroup;i++)
		{
			System.out.println("gaurav");
			if(driver.findElement(By.xpath(".//fieldset[p[contains(.,'STAKEHOLDERS')]]/label["+i+"]/span/div/input[@type='text']")).isEnabled())
			{
			driver.findElement(By.xpath(".//fieldset[p[contains(.,'STAKEHOLDERS')]]/label["+i+"]/span/div/input[@type='text']")).sendKeys(s1);
			Thread.sleep(2000);
			System.out.println("check");
			driver.findElement(By.xpath(".//fieldset[p[contains(.,'STAKEHOLDERS')]]/label["+i+"]/span/div/input[@type='text']")).sendKeys(Keys.DOWN,Keys.RETURN);
			//driver.findElement(By.xpath(".//li[@class='ui-menu-item']/a[contains(.,'"+s1+"')]")).sendKeys(Keys.DOWN);
			//driver.findElement(By.xpath(".//li[@class='ui-menu-item']/a[contains(.,'"+s1+"')]")).sendKeys(Keys.RETURN);
			}
			System.out.println("check again");
		}
		Thread.sleep(5000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Create')]")));
		driver.findElement(By.xpath("//button[contains(.,'Create')]")).click();
		Thread.sleep(5000);
		
		/*Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Create Action')]")));
		driver.findElement(By.xpath("//button[contains(.,'Create Action')]")).click();
		Thread.sleep(5000);*/

		if (getObject("ac_popup_id") != null) {

			String action_id = getObject("ac_popup_id").getText();
			APP_LOGS.debug("Action Cloned successfully with Issue id "+ action_id);

			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
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
		
				// workflow steps - submit button

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		System.out.println("clicked the submit button");
		Thread.sleep(7000);

		if (!actionTaken.equalsIgnoreCase("")) 
		{
			
			getObject("ac_action_taken").clear();
			getObject("ac_action_taken").sendKeys(actionTaken); // name
			
		}
		System.out.println("gaurav arora");
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("ac_submit"));
		
		getObject("ac_submit").click(); 

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
