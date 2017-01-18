package test.resources.com.sirion.suite.interpretation;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class InterpretationCreation extends TestSuiteBase {
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

	@Test(groups = "InterpretationCreation", dataProvider = "getTestData")
	public void InterpretationCreateTest(String ipParentEntity, String ipTitle,
			String ipAreaofDisagreement, String ipBackground, String ipType,
			String ipPriority, String ipTimezone, String ipTier,
			String ipSupplierAccess, String ipDependentEntity,
			String ipQuestion, String ipIncludeInFAQ,
			String ipRequestedDateMonth, String ipRequestedDateDate,
			String ipRequestedDateYear, String ipPlannedSubmissionDateMonth,
			String ipPlannedSubmissionDateDate,
			String ipPlannedSubmissionDateYear, String ipSupplier,
			String ipStatus, String ipFunction, String ipRegion,
			String ipService, String ipCountry) throws InterruptedException {

		
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "+ count);
		}

		APP_LOGS.debug("Executing Test Case Interpretation Creation from Action");

		// Calling method for opening browser from TestBase.java file
		openBrowser();
		

		// Calling a method for login(at different platform) from TestBase.java file
		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));

		// Creating an Interpretation from parent entity 
		/*try {

			if (ipParentEntity.equalsIgnoreCase("Contract")) {
				// Click on Contract Quick Link
				getObject("contract_quick_link").click(); // click on contract Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else if (ipParentEntity.equalsIgnoreCase("Supplier")) {
				getObject("supplier_quick_link").click(); // click on Supplier Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link

			} else if (ipParentEntity.equalsIgnoreCase("Issue")) {
				getObject("issue_quick_link").click(); // click on Issue Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link 
				
			}else if (ipParentEntity.equalsIgnoreCase("Interpretation")) {
				getObject("int_quick_link").click(); // click on IP Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
				
			} else if (ipParentEntity.equalsIgnoreCase("Action")) {

				getObject("action_quick_link").click(); // click on Action Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else if (ipParentEntity.equalsIgnoreCase("Change Request")) {
				getObject("cr_quick_link").click(); // click on CR Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else if (ipParentEntity.equalsIgnoreCase("Work Order Request")) {
				getObject("wor_quick_link").click(); // click on WOR Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else if (ipParentEntity.equalsIgnoreCase("Service Level")) {
				getObject("sl_quick_link").click(); // click on SL Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else if (ipParentEntity.equalsIgnoreCase("Child Obligation")) {
				getObject("child_obligaiton_quick_link").click(); // click on COB Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else if (ipParentEntity.equalsIgnoreCase("Invoice"))

			{
				getObject("invoice_quick_link").click(); // click on Invoice Quick Link
				parentEntity(ipParentEntity);
				getObject("ip_create_link").click(); // click Interpretation create link
			}

			else {

				getObject("int_quick_link").click();// click on Issue Quick Link
				driver.findElement(By.xpath(".//*[@class='plus ng-scope']/a")).click();// Click on plus button
				if (new Select(driver.findElement(By.xpath(".//*[@id='elem_2501']/select"))).getOptions().isEmpty() != true) {
					new Select(driver.findElement(By.xpath(".//*[@id='elem_2501']/select"))).selectByIndex(1);
					new Select(driver.findElement(By.xpath(".//*[@id='elem_2502']/select"))).selectByVisibleText("Supplier");
					driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
				}
			}

		} 
		catch (Exception e) 
		{
			
		}
		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(getObject("ip_id_link")));
*/
		WebDriverWait wait=new WebDriverWait(driver, 50);
		getObject("analytics_link").click();
	    getObject("int_quick_link").click(); // IP Quick Link Clicking
	    Thread.sleep(20000);
	    //wait.until(ExpectedConditions.elementToBeClickable(getObject("ip_id_link")));
	    getObject("ip_id_link").click(); 
	   // Thread.sleep(20000);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
	    plus_button("ip_plus_button_link"); // web element for plus button on supplier page
	    wait.until(ExpectedConditions.elementToBeClickable(getObject("ip_create_link_from_ip")));
	    getObject("ip_create_link_from_ip").click(); // click issue create link   
		
		
		
		if (!ipTitle.equalsIgnoreCase("")) {
			getObject("ip_title_textbox").clear();
			getObject("ip_title_textbox").sendKeys(ipTitle); // name
		}
		
		if (!ipAreaofDisagreement.equalsIgnoreCase("")) {
			getObject("ip_area_textarea").clear();
			getObject("ip_area_textarea").sendKeys(ipAreaofDisagreement); // title
		}
		
		if (!ipBackground.equalsIgnoreCase("")) {
			getObject("ip_background_textarea").clear();
			getObject("ip_background_textarea").sendKeys(ipBackground);
		}
		
		if (!ipType.equalsIgnoreCase("")) {

			new Select(getObject("ip_type_dropdown")).selectByVisibleText(ipType);
		}
		
		if (!ipPriority.equalsIgnoreCase("")) {
			new Select(getObject("ip_priority_dropdown")).selectByVisibleText(ipPriority);
		}
		
		if (!ipTimezone.equalsIgnoreCase("")) {
			new Select(getObject("ip_timezone_dropdown")).selectByVisibleText(ipTimezone);
			try {
				if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
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
		
		
		
		
		
		 
		
		  getObject("ac_save_button").click();

		Thread.sleep(4000);
		
		String ip_id = getObject("ip_popup_id").getText();
		
		System.out.println("Interpretation id is " + ip_id);
		
		 getObject("ip_popup_ok_button_from_ip").click();
		
		getObject("quick_search_textbox").sendKeys(ip_id);
		
		getObject("quick_search_textbox").sendKeys(Keys.ENTER);

		// Assertion code shifted down
		
		try {
			if (driver.findElement(By.className("success-icon")).getText().contains("Either you do not have the required permissions or requested page does not exist anymore.")) {
				driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
				Thread.sleep(40000);
			}

		} catch (Exception e) {

		}

		fail = false; // this executes if assertion passes

		getObject("analytics_link").click();
		
	}
	
	
	
	
	
	/*
	 * Date code shifted down - comment placed on its place Date date = new
	 * Date(); int current_month = date.getMonth();
	 * 
	 * driver.findElement(By.name("requestDate")).click(); Double
	 * temp_ipRequestedDateYear_double =
	 * Double.parseDouble(ipRequestedDateYear); int temp_ipRequestedDateYear_int
	 * = temp_ipRequestedDateYear_double.intValue(); String
	 * ipRequestedDateYear_string =
	 * Integer.toString(temp_ipRequestedDateYear_int);
	 * 
	 * WebElement datepicker_ui =
	 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	 * System.out.println(datepicker_ui.isDisplayed()); if
	 * (datepicker_ui.isDisplayed() == true) { WebElement datepicker_ui_year =
	 * driver.findElement(By.xpath(
	 * ".//*[@id='ui-datepicker-div']/div/div/select")); new
	 * Select(datepicker_ui_year
	 * ).selectByVisibleText(ipRequestedDateYear_string); }
	 * 
	 * Double temp_ipRequestedDateMonth_double =
	 * Double.parseDouble(ipRequestedDateMonth); int
	 * temp_ipRequestedDateMonth_int =
	 * temp_ipRequestedDateMonth_double.intValue();
	 * System.out.println(" ipRequestedDateMonth " +
	 * temp_ipRequestedDateMonth_int);
	 * 
	 * int click_ipRequestedDateMonth = current_month -
	 * temp_ipRequestedDateMonth_int; System.out.println("click " +
	 * click_ipRequestedDateMonth); for (; click_ipRequestedDateMonth >= 0;
	 * click_ipRequestedDateMonth = click_ipRequestedDateMonth - 1) { driver.
	 * findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span"
	 * )).click(); } Double temp_ipRequestedDateDate_double =
	 * Double.parseDouble(ipRequestedDateDate); int temp_ipRequestedDateDate_int
	 * = temp_ipRequestedDateDate_double.intValue(); String
	 * ipRequestedDateDate_string =
	 * Integer.toString(temp_ipRequestedDateDate_int);
	 * driver.findElement(By.linkText(ipRequestedDateDate_string)).click();
	 * 
	 * driver.findElement(By.name("plannedSubmissionDate")).click();
	 * 
	 * Double temp_ipPlannedSubmissionDateYear_double =
	 * Double.parseDouble(ipPlannedSubmissionDateYear); int
	 * temp_ipPlannedSubmissionDateYear_int =
	 * temp_ipPlannedSubmissionDateYear_double.intValue(); String
	 * ipPlannedSubmissionDateYear_string =
	 * Integer.toString(temp_ipPlannedSubmissionDateYear_int);
	 * 
	 * System.out.println(datepicker_ui.isDisplayed()); if
	 * (datepicker_ui.isDisplayed() == true) { WebElement datepicker_ui_year =
	 * driver.findElement(By.xpath(
	 * ".//*[@id='ui-datepicker-div']/div/div/select")); new
	 * Select(datepicker_ui_year
	 * ).selectByVisibleText(ipPlannedSubmissionDateYear_string); }
	 * 
	 * Double temp_ipPlannedSubmissionDateMonth_double =
	 * Double.parseDouble(ipPlannedSubmissionDateMonth); int
	 * temp_ipPlannedSubmissionDateMonth_int =
	 * temp_ipPlannedSubmissionDateMonth_double.intValue();
	 * System.out.println(" ipPlannedSubmissionDateMonth " +
	 * temp_ipPlannedSubmissionDateMonth_int);
	 * 
	 * int click_ipPlannedSubmissionDateMonth =
	 * temp_ipPlannedSubmissionDateMonth_int - current_month;
	 * System.out.println("click " + click_ipPlannedSubmissionDateMonth); for (;
	 * click_ipPlannedSubmissionDateMonth > 0;
	 * click_ipPlannedSubmissionDateMonth = click_ipPlannedSubmissionDateMonth -
	 * 1) { driver.findElement(By.xpath(
	 * "//*[@id='ui-datepicker-div']/div/a[2]/span")).click(); } Double
	 * temp_ipPlannedSubmissionDateDate_double =
	 * Double.parseDouble(ipPlannedSubmissionDateDate); int
	 * temp_ipPlannedSubmissionDateDate_int =
	 * temp_ipPlannedSubmissionDateDate_double.intValue(); String
	 * ipPlannedSubmissionDateDate_string =
	 * Integer.toString(temp_ipPlannedSubmissionDateDate_int);
	 * driver.findElement
	 * (By.linkText(ipPlannedSubmissionDateDate_string)).click();
	 */

	/*
	 * Assertion code shifted down- comment put at its place String
	 * ipIdFromShowPage = getObject("ip_show_id").getText();
	 * 
	 * Assert.assertEquals(ipIdFromShowPage, ip_id);
	 * 
	 * APP_LOGS.debug(
	 * "Interpretation show page open successfully with action id " + ip_id);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String IPStatusShowPage = getObject("ip_status_show").getText(); try {
	 * Assert.assertEquals(IPStatusShowPage, ipStatus, "IP Status is -- "
	 * +IPStatusShowPage+ " instead of -- " +ipStatus); } catch(Throwable e) {
	 * System.out.println("IP Status is -- " +IPStatusShowPage+
	 * " instead of -- " +ipStatus); }
	 * 
	 * 
	 * String IPTitleShowPage = getObject("ip_title_show").getText(); try {
	 * Assert.assertEquals(IPTitleShowPage, ipTitle, "IP title is -- "
	 * +IPTitleShowPage+ " instead of -- " +ipTitle); } catch(Throwable e) {
	 * System.out.println("IP title is -- " +IPTitleShowPage+ " instead of -- "
	 * +ipTitle); }
	 * 
	 * 
	 * 
	 * String IPAreaofDisagreementShowPage =
	 * getObject("ip_areaofdisagreement_show").getText(); try {
	 * Assert.assertEquals(IPAreaofDisagreementShowPage, ipAreaofDisagreement,
	 * "IP Area of Disagreement is -- " +IPAreaofDisagreementShowPage+
	 * " instead of -- " +ipAreaofDisagreement); } catch(Throwable e) {
	 * System.out.println("IP Area of Disagreement is -- "
	 * +IPAreaofDisagreementShowPage+ " instead of -- " +ipAreaofDisagreement);
	 * }
	 * 
	 * 
	 * 
	 * 
	 * String IPBackgroundShowPage = getObject("ip_background_show").getText();
	 * try { Assert.assertEquals(IPBackgroundShowPage, ipBackground,
	 * "IP Background is -- " +IPBackgroundShowPage+ " instead of -- "
	 * +ipBackground); } catch(Throwable e) {
	 * System.out.println("IP Background is -- " +IPBackgroundShowPage+
	 * " instead of -- " +ipBackground); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String IPTypeShowPage = getObject("ip_type_show").getText(); try {
	 * Assert.assertEquals(IPTypeShowPage, ipType, "IP Type is -- "
	 * +IPTypeShowPage+ " instead of -- " +ipType); } catch(Throwable e) {
	 * System.out.println("IP Type is -- " +IPTypeShowPage+ " instead of -- "
	 * +ipType); }
	 * 
	 * 
	 * 
	 * 
	 * String IPPriorityShowPage = getObject("ip_priority_show").getText(); try
	 * { Assert.assertEquals(IPPriorityShowPage, ipPriority,
	 * "IP Priority is -- " +IPPriorityShowPage+ " instead of -- " +ipPriority);
	 * } catch(Throwable e) { System.out.println("IP Priority is -- "
	 * +IPPriorityShowPage+ " instead of -- " +ipPriority); }
	 * 
	 * 
	 * 
	 * String IPSupplierShowPage = getObject("ip_supplier_show").getText(); try
	 * { Assert.assertEquals(IPSupplierShowPage, ipSupplier,
	 * "IP Supplier is -- " +IPSupplierShowPage+ " instead of -- " +ipSupplier);
	 * } catch(Throwable e) { System.out.println("IP Supplier is -- "
	 * +IPSupplierShowPage+ " instead of -- " +ipSupplier); }
	 * 
	 * 
	 * 
	 * String IPTimezoneShowPage = getObject("ip_timezone_show").getText(); try
	 * { Assert.assertEquals(IPTimezoneShowPage, ipTimezone,
	 * "IP time zone is -- " +IPTimezoneShowPage+ " instead of -- "
	 * +ipTimezone); } catch(Throwable e) {
	 * System.out.println("IP time zone is -- " +IPTimezoneShowPage+
	 * " instead of -- " +ipTimezone); }
	 * 
	 * 
	 * 
	 * String IPTierShowPage = getObject("ip_tier_show").getText(); try {
	 * Assert.assertEquals(IPTierShowPage, ipTier, "IP Tier is -- "
	 * +IPTierShowPage+ " instead of -- " +ipTier); } catch(Throwable e) {
	 * System.out.println("IP Tier is -- " +IPTierShowPage+ " instead of -- "
	 * +ipTier); }
	 * 
	 * 
	 * 
	 * 
	 * String IPSupplierAccessShowPage =
	 * getObject("ip_supplieraccess_show").getText(); try {
	 * Assert.assertEquals(IPSupplierAccessShowPage, ipSupplierAccess,
	 * "IP Supplier Access is -- " +IPSupplierAccessShowPage+ " instead of -- "
	 * +ipSupplierAccess); } catch(Throwable e) {
	 * System.out.println("IP Supplier Access is -- " +IPSupplierAccessShowPage+
	 * " instead of -- " +ipSupplierAccess); }
	 * 
	 * 
	 * String IPDependentEntityShowPage =
	 * getObject("ip_depentity_show").getText(); try {
	 * Assert.assertEquals(IPDependentEntityShowPage, ipDependentEntity,
	 * "IP Dependent Entity is -- " +IPDependentEntityShowPage+
	 * " instead of -- " +ipDependentEntity); } catch(Throwable e) {
	 * System.out.println("IP Dependent Entity is -- "
	 * +IPDependentEntityShowPage+ " instead of -- " +ipDependentEntity); }
	 * 
	 * 
	 * String IPQuestionShowPage = getObject("ip_question_show").getText(); try
	 * { Assert.assertEquals(IPQuestionShowPage, ipQuestion,
	 * "IP Question is -- " +IPQuestionShowPage+ " instead of -- " +ipQuestion);
	 * } catch(Throwable e) { System.out.println("IP Question is -- "
	 * +IPQuestionShowPage+ " instead of -- " +ipQuestion); }
	 * 
	 * 
	 * String IPIncludeInFaqShowPage =
	 * getObject("ip_includeinfaq_show").getText(); try {
	 * Assert.assertEquals(IPIncludeInFaqShowPage, ipIncludeInFAQ,
	 * "IP Include In Faq is -- " +IPIncludeInFaqShowPage+ " instead of -- "
	 * +ipIncludeInFAQ); } catch(Throwable e) {
	 * System.out.println("IP Include In Faq is -- " +IPIncludeInFaqShowPage+
	 * " instead of -- " +ipIncludeInFAQ); }
	 * 
	 * 
	 * String IPFunctionsShowPage= getObject("ip_functions_show").getText(); try
	 * { Assert.assertEquals(IPFunctionsShowPage, ipFunction,
	 * "IP Functions are== "+IPFunctionsShowPage +"instead of" + ipFunction); }
	 * catch(Throwable e) {
	 * System.out.println("IP Functions are== "+IPFunctionsShowPage
	 * +"instead of" + ipFunction); }
	 * 
	 * 
	 * 
	 * String IPServicesShowPage= getObject("ip_services_show").getText(); try {
	 * Assert.assertEquals(IPServicesShowPage, ipService,
	 * "IP Services are== "+IPServicesShowPage +"instead of" + ipService); }
	 * catch(Throwable e) {
	 * System.out.println("IP Services are== "+IPServicesShowPage +"instead of"
	 * + ipService); }
	 * 
	 * 
	 * 
	 * 
	 * String IPRegionsShowPage= getObject("ip_regions_show").getText(); try {
	 * Assert.assertEquals(IPRegionsShowPage, ipRegion,
	 * "IP Regions are== "+IPRegionsShowPage +"instead of" + ipRegion); }
	 * catch(Throwable e) {
	 * System.out.println("IP Regions are== "+IPRegionsShowPage +"instead of" +
	 * ipRegion); }
	 * 
	 * 
	 * 
	 * String IPCountriesShowPage= getObject("ip_countries_show").getText(); try
	 * { Assert.assertEquals(IPCountriesShowPage, ipCountry,
	 * "IP Countries are== "+IPCountriesShowPage +"instead of" + ipCountry); }
	 * catch(Throwable e) {
	 * System.out.println("IP Countries are== "+IPCountriesShowPage
	 * +"instead of" + ipCountry); }
	 */
		
		// App logs comment shifted down- comment put at its place
				/*
				 * APP_LOGS.debug(
				 * "IP open successfully, following parameters have been validated: IP Title-- "
				 * + ipTitle+ ", IP TimeZone -- " + ipTimezone+ ", IP Type -- " +
				 * ipType+ ", " + "IP Priority  -- " + ipPriority+ ", IP Id-- " + ip_id+
				 * ", IP Status-- " + ipStatus+ ", IP Supplier Access -- " +
				 * ipSupplierAccess+ ", " + "IP Tier -- " +
				 * ipTier+", IP Depenndent Entity -- " + ipDependentEntity+
				 * ", IP Function -- " + ipFunction+ ", IP Services -- " + ipService+
				 * ", IP Contract Regions -- " + ipRegion +
				 * ", IP Contract Countries -- " + ipCountry+
				 * ", IP Area of Disagreement -- " + ipAreaofDisagreement+
				 * ",IP Background -- " + ipBackground+ ",IP Include in FAQ -- " +
				 * ipIncludeInFAQ+ ", IP Planned Submission Date Date -- " +
				 * ipPlannedSubmissionDateDate+" , IP Planned Submission Date Month-- "
				 * +
				 * ipPlannedSubmissionDateMonth+", IP Planned Submission Date Year  -- "
				 * + ipPlannedSubmissionDateYear+ ", IP Question--"+ ipQuestion+
				 * ", IP Requested Date Date--" +ipRequestedDateDate+
				 * ", IP Requested Date Month--"
				 * +ipRequestedDateMonth+", IP Requested Date Year--"
				 * +ipRequestedDateYear);
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
