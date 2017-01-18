package test.resources.com.sirion.suite.interpretation;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class InterpretationUpdate extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(int_suite_xls, this.getClass()
				.getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(int_suite_xls, this.getClass()
				.getSimpleName());
	}

	@Test(groups = "ContractCreation", dataProvider = "getTestData")
	public void InterpretationUpdateTest(String ipTitle,
			String ipAreaofDisagreement, String ipBackground, String ipType,
			String ipPriority, String ipTimezone, String ipTier,
			String ipSupplierAccess, String ipDependentEntity,
			String ipQuestion, String ipIncludeInFAQ,
			String ipRequestedDateMonth, String ipRequestedDateDate,
			String ipRequestedDateYear, String ipPlannedSubmissionDateMonth,
			String ipPlannedSubmissionDateDate,
			String ipPlannedSubmissionDateYear, String ipSupplier,
			String ipStatus, String ipFunction, String ipRegion,
			String ipService, String ipCountry, String ipComment,
			String ipActualDateDate, String ipActualDateMonth,
			String ipActualDateYear, String ipRequestedBy,
			String ipChangeRequest, String ipUploadedFile)
			throws InterruptedException {

		System.out.println("entered in IP Create method");
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "
					+ count);
		}

		APP_LOGS.debug("Executing Test Case Interpretation Creation from Action");

		// Calling method for opening browser from TestBase.java file
		openBrowser();

		// Calling a method for login(at different platform) from TestBase.java
		// file
		endUserLogin(CONFIG.getProperty("endUserURL"),
				CONFIG.getProperty("endUserUsername"),
				CONFIG.getProperty("endUserPassword"));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 50);	
		getObject("analytics_link").click();
		getObject("int_quick_link").click(); // Interpretation Quick Link
		Thread.sleep(20000);
		//wait.until(ExpectedConditions.elementToBeClickable(getObject("int_id_link")));										// Clicking
		getObject("int_id_link").click(); // Interpretation ID Clicking
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//button[contains(.,'Edit')]"))));	
		
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//button[contains(.,'Edit')]")).click(); 

		
		if (!ipTitle.equalsIgnoreCase("")) {
			getObject("ip_title_textbox").clear();
			getObject("ip_title_textbox").sendKeys(ipTitle);
		}

		if (!ipAreaofDisagreement.equalsIgnoreCase("")) {
			getObject("ip_area_textarea").clear();
			getObject("ip_area_textarea").sendKeys(ipAreaofDisagreement);
		}

		if (!ipBackground.equalsIgnoreCase("")) {
			getObject("ip_background_textarea").clear();
			getObject("ip_background_textarea").sendKeys(ipBackground);
		}

		if (!ipType.equalsIgnoreCase("")) {

			new Select(getObject("ip_type_dropdown"))
					.selectByVisibleText(ipType);
		}
		if (!ipPriority.equalsIgnoreCase("")) {
			new Select(getObject("ip_priority_dropdown"))
					.selectByVisibleText(ipPriority);
		}

		if (!ipTimezone.equalsIgnoreCase("")) {
			new Select(getObject("ip_timezone_dropdown")).selectByVisibleText(ipTimezone);

			try {
				if (driver.findElement(By.className("success-icon")).getText().contains(
								"Current Date is different for the selected Time Zone"))
					driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
			} catch (Exception e) {

			}
		}

		if (!ipTier.equalsIgnoreCase("")) {
			new Select(getObject("ip_tier_dropdown")).selectByVisibleText(ipTier);
		}

		if (ipSupplierAccess.equalsIgnoreCase("yes")) {
			getObject("ip_supplieraccess_checkbox").click();
		}

		if (ipDependentEntity.equalsIgnoreCase("yes")) {
			getObject("ip_depentity_dropdown").click();
		}

		if (!ipQuestion.equalsIgnoreCase("")) {
			getObject("ip_question_textarea").clear();
			getObject("ip_question_textarea").sendKeys(ipQuestion);
		}

		if (ipIncludeInFAQ.equalsIgnoreCase("yes")) {
			getObject("ip_includeinfaq_checkbox").click();
		}
		
		Thread.sleep(10000);
		
		Date date = new Date();

	    int current_month = date.getMonth();
	    new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[contains(@name,'requestDate')]"))).click().perform();
	   // driver.findElement(By.xpath("//input[contains(@name,'requestDate')]")).click();
	    Double temp_actionRequestedOnYear_double = Double.parseDouble(ipRequestedDateYear);
	    int temp_actionRequestedOnYear_int = temp_actionRequestedOnYear_double.intValue();
	    String actionRequestedOnYear_string = Integer.toString(temp_actionRequestedOnYear_int);

	    WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	    System.out.println(datepicker_ui.isDisplayed());
	    if (datepicker_ui.isDisplayed() == true) {
	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	      new Select(datepicker_ui_year).selectByVisibleText(actionRequestedOnYear_string);
	    }

	    Double temp_actionRequestedOnMonth_double = Double.parseDouble(ipRequestedDateMonth);
	    int temp_actionRequestedOnMonth_int = temp_actionRequestedOnMonth_double.intValue();
	    System.out.println(" ipRequestedDateMonth " + temp_actionRequestedOnMonth_int);

	    int click2 = current_month - temp_actionRequestedOnMonth_int;
	    System.out.println("click " + click2);
	    for (; click2 >= 0; click2 = click2 - 1) {
	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	    }
	    Double temp_actionRequestedOnDate_double = Double.parseDouble(ipRequestedDateDate);
	    int temp_actionRequestedOnDate_int = temp_actionRequestedOnDate_double.intValue();
	    String actionRequestedOnDate_string = Integer.toString(temp_actionRequestedOnDate_int);
	    driver.findElement(By.linkText(actionRequestedOnDate_string)).click();
	    new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[@name='plannedSubmissionDate']"))).click().perform();
	    //driver.findElement(By.xpath("//input[@name='plannedSubmissionDate']")).click();

	    Double temp_actionDueDateYear_double = Double.parseDouble(ipPlannedSubmissionDateYear);
	    int temp_actionDueDateYear_int = temp_actionDueDateYear_double.intValue();
	    String actionDueDateYear_string = Integer.toString(temp_actionDueDateYear_int);

	    System.out.println(datepicker_ui.isDisplayed());
	    if (datepicker_ui.isDisplayed() == true) {
	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	      new Select(datepicker_ui_year).selectByVisibleText(actionDueDateYear_string);
	    }

	    Double temp_actionDueDateMonth_double = Double.parseDouble(ipPlannedSubmissionDateMonth);
	    int temp_actionDueDateMonth_int = temp_actionDueDateMonth_double.intValue();
	    System.out.println(" actionDueDateMonth " + temp_actionDueDateMonth_int);

	    int click3 = temp_actionDueDateMonth_int - current_month;
	    System.out.println("click " + click3);
	    for (; click3 > 0; click3 = click3 - 1) {
	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    }
	    Double temp_actionDueDateDate_double = Double.parseDouble(ipPlannedSubmissionDateDate);
	    int temp_actionDueDateDate_int = temp_actionDueDateDate_double.intValue();
	    String actionDueDateDate_string = Integer.toString(temp_actionDueDateDate_int);
	    driver.findElement(By.linkText(actionDueDateDate_string)).click();

		if (!ipComment.equalsIgnoreCase("")) {
			getObject("ip_Comment_text_area").clear();
			getObject("ip_Comment_text_area").sendKeys(ipComment);
		}

		if (!ipRequestedBy.equalsIgnoreCase("")) {
			new Select(getObject("ip_requested_by_dropdown")).selectByVisibleText(ipRequestedBy);
		}

		if (!ipChangeRequest.equalsIgnoreCase("")) {
			new Select(getObject("ip_change_request_dropdown")).selectByVisibleText(ipChangeRequest);
		}

		/*if (!ipActualDateYear.equalsIgnoreCase("") && !ipActualDateMonth.equalsIgnoreCase("") && !ipActualDateDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("actualDate")).click();
			Double temp_ipActualDateYear_double = Double.parseDouble(ipActualDateYear);
			int temp_ipActualDateYear_int = temp_ipActualDateYear_double.intValue();
			String ipActualDateYear_string = Integer.toString(temp_ipActualDateYear_int);

			WebElement datepicker_ui11 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
			System.out.println(datepicker_ui11.isDisplayed());
			if (datepicker_ui11.isDisplayed() == true) {
				WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
				new Select(datepicker_ui_year).selectByVisibleText(ipActualDateYear_string);
			}

			Double temp_ipActualDateMonth_double = Double.parseDouble(ipActualDateMonth);
			int temp_ipActualDateMonth_int = temp_ipActualDateMonth_double.intValue();
			System.out.println(" ipActualDateMonth "+ temp_ipActualDateMonth_int);

			int click_ipActualDateMonth = current_month- temp_ipActualDateMonth_int;
			System.out.println("click " + click_ipActualDateMonth);
			for (; click_ipActualDateMonth >= 0; click_ipActualDateMonth = click_ipActualDateMonth - 1) {
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
			}
			Double temp_ipActualDateDate_double = Double.parseDouble(ipActualDateDate);
			int temp_ipActualDateDate_int = temp_ipActualDateDate_double.intValue();
			String ipActualDateDate_string = Integer.toString(temp_ipActualDateDate_int);
			driver.findElement(By.linkText(ipActualDateDate_string)).click();

		}*/

		if (!ipUploadedFile.equalsIgnoreCase("")) {
			getObject("ip_browse_button").sendKeys((System.getProperty("user.dir")+ "\\Communication Documents\\Issues\\" + ipUploadedFile));
		}

		

		driver.findElement(By.xpath(".//button[contains(.,'Update')]")).click();

		Thread.sleep(5000);

		try {
			if (driver.findElement(By.className("success-icon")).getText().contains("Either you do not have the required permissions or requested page does not exist anymore.")) {
				driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
				Thread.sleep(40000);
			}

		} catch (Exception e) {

			String ip_id = getObject("ip_show_id").getText();
			
			System.out.println(ip_id);

			getObject("quick_search_textbox").sendKeys(ip_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);

			Thread.sleep(5000);

		}

		try {
			if (driver.findElement(By.className("success-icon")).getText().contains("Either you do not have the required permissions or requested page does not exist anymore.")) {
				driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
				Thread.sleep(40000);
			}

		} catch (Exception e) {

		}

		fail = false; // this executes if assertion passes

		// App Log Commented down
		
		getObject("analytics_link").click();

	}

	

	
	// App log commented down and placed here
	/*
	 * APP_LOGS.debug(
	 * "IP open successfully, following parameters have been validated: IP Title-- "
	 * + ipTitle + ", IP TimeZone -- " + ipTimezone + ", IP Type -- " + ipType +
	 * ", " + "IP Priority  -- " + ipPriority + ", IP Id-- " + ", IP Status-- "
	 * + ipStatus + ", IP Supplier Access -- " + ipSupplierAccess + ", " +
	 * "IP Tier -- " + ipTier + ", IP Depenndent Entity -- " + ipDependentEntity
	 * + ", IP Function -- " + ipFunction + ", IP Services -- " + ipService +
	 * ", IP Contract Regions -- " + ipRegion + ", IP Contract Countries -- " +
	 * ipCountry + ", IP Area of Disagreement -- " + ipAreaofDisagreement +
	 * ",IP Background -- " + ipBackground + ",IP Include in FAQ -- " +
	 * ipIncludeInFAQ + ", IP Planned Submission Date Date -- " +
	 * ipPlannedSubmissionDateDate + " , IP Planned Submission Date Month-- " +
	 * ipPlannedSubmissionDateMonth + ", IP Planned Submission Date Year  -- " +
	 * ipPlannedSubmissionDateYear + ", IP Question--" + ipQuestion +
	 * ", IP Requested Date Date--" + ipRequestedDateDate +
	 * ", IP Requested Date Month--" + ipRequestedDateMonth +
	 * ", IP Requested Date Year--" + ipRequestedDateYear);
	 */
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(int_suite_xls, "Test Cases", TestUtil.getRowNum(int_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(int_suite_xls, "Test Cases", TestUtil.getRowNum(int_suite_xls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(int_suite_xls, this.getClass().getSimpleName());
	}

}
