package test.resources.com.sirion.suite.workOrderRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.DatePicker;
import test.resources.com.sirion.util.TestUtil;

public class WORCreation extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(wor_suite_xls, this.getClass()
				.getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}
		// load the run modes off the tests
		runmodes = TestUtil.getDataSetRunmodes(wor_suite_xls, this.getClass()
				.getSimpleName());
	}

	@Test(dataProvider = "getTestData")
	public void WorkOrderCreationTest( String worSupplier,String worSourceType,
			String worsourceName,String worName,String worTitle,
			String worBrief, String worPriority, String worType,
			String worBillingType, String worCurrency,
			String worContractingEntity, String worDeliveryCountries,
			String worTimezone, String worSupplierAccess, String worTier,
			String worEffectiveDateDate, String worEffectiveDateMonth,
			String worEffectiveDateYear, String worExpirationDateDate,
			String worExpirationDateMonth, String worExpirationDateYear,
			String worAdditionalTCV, String worAdditionalACV,
			String worAdditionalFACV) throws InterruptedException {
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "
					+ count);
		}

		APP_LOGS.debug("Executing Test Case WOR Creation");

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
		//Click WOR quick link
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("wor_quick_link"))).click();
		
		//Click on CR Global creation quick link
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("wor_plus_button_link"))).click();
		
		// Select value in supplier dropdown
				if (!worSupplier.equalsIgnoreCase("")) {
					new Select(getObject("wor_supplier_dropdown"))
							.selectByVisibleText(worSupplier);
				}

				// Select value in source type dropdown
				if (!worSourceType.equalsIgnoreCase("")) {
					new Select(getObject("wor_source_type_dropdown"))
							.selectByVisibleText(worSourceType);
				}

				// Select value in source Name dropdown
				if (!worsourceName.equalsIgnoreCase("")) {
					new Select(getObject("wor_source_name_dropdown"))
							.selectByVisibleText(worsourceName);
				}

				// Click on submit button
				getObject("wor_submit_button").click();
	
            //Enter value in name 
		if (!worName.equalsIgnoreCase("")) {
			getObject("wor_name_textbox").sendKeys(worName);
		}
		
		//Enter value in title
		if (!worTitle.equalsIgnoreCase("")) {
			getObject("wor_title_textbox").sendKeys(worTitle);
		}
		
		//Enter value in Brief
		if (!worBrief.equalsIgnoreCase("")) {
			getObject("wor_brief_textarea").sendKeys(worBrief); // name
		}
		
		//Select value in Priority
		if (!worPriority.equalsIgnoreCase("")) {
			new Select(getObject("wor_priority_dropdown"))
					.selectByVisibleText(worPriority);
		}
		
		//Select value in type field
		if (!worType.equalsIgnoreCase("")) {
			new Select(getObject("wor_type_dropdown"))
					.selectByVisibleText(worType);
		}
		
		//Select value in billing type		
		if (!worBillingType.equalsIgnoreCase("")) {
			new Select(getObject("wor_billing_type_dropdown"))
					.selectByVisibleText(worBillingType);
		}
		
		//Select value in currency
		if (!worCurrency.equalsIgnoreCase("")) {
			new Select(getObject("wor_currency_dropdown"))
					.selectByVisibleText(worCurrency);
		}
		
		//Select value in contracting entity
		if (!worContractingEntity.equalsIgnoreCase("")) {
			new Select(getObject("wor_contracting_entity_dropdown"))
					.selectByVisibleText(worContractingEntity);
		}
		
		//Select value in Delivered countries
		if (!worDeliveryCountries.equalsIgnoreCase("")) {
			new Select(getObject("wor_delcountry_multiselect"))
					.selectByVisibleText(worDeliveryCountries);
		}
		
		//Select value in timezone
		if (!worTimezone.equalsIgnoreCase("")) {
			new Select(getObject("wor_timezone_dropdown"))
					.selectByVisibleText(worTimezone);
			try {
				if (driver
						.findElement(By.className("success-icon"))
						.getText()
						.contains(
								"Current Date is different for the selected Time Zone"))
					driver.findElement(By.xpath(".//button[contains(.,'OK')]"))
							.click();
			} catch (Exception e) {

			}
		}
		
		//Click supplier access checkbox
		if (!worSupplierAccess.equalsIgnoreCase("Yes")) {
			getObject("wor_supplier_access_checkbox").click();
		}
		
		//Select value in tier 
		if (!worTier.equalsIgnoreCase("")) {
			new Select(getObject("wor_tier_dropdown"))
					.selectByVisibleText(worTier);
		}
		Thread.sleep(1000);

		// Date picker
				// Enter value in Effective Date field
				String wor_DateMonth = convertDoubleToIntegerInStringForm(worEffectiveDateMonth);
				int wor_dateMonth = Integer.parseInt(wor_DateMonth);
				String wor_DateYear = convertDoubleToIntegerInStringForm(worEffectiveDateYear);
				int wor_dateYear = Integer.parseInt(wor_DateYear);
				String wor_DateDate = convertDoubleToIntegerInStringForm(worEffectiveDateDate);
				Integer wor_dateDate = Integer.parseInt(wor_DateDate);
				wor_DateDate = wor_dateDate.toString();

				DatePicker dp_wor_Date_date = new DatePicker();
				dp_wor_Date_date.expDate = wor_DateDate;
				dp_wor_Date_date.expMonth = wor_dateMonth;
				dp_wor_Date_date.expYear = wor_dateYear;
				System.out.println("gaurav");
				dp_wor_Date_date.pickExpDate("effectiveDate");
				System.out.println("arora");
				
				// Enter value in Expiration Date field
				String wor_effective_DateMonth = convertDoubleToIntegerInStringForm(worExpirationDateDate);
				int wor_effective_dateMonth = Integer.parseInt(wor_effective_DateMonth);
				String wor_effective_DateYear = convertDoubleToIntegerInStringForm(worExpirationDateYear);
				int wor_effective_dateYear = Integer.parseInt(wor_effective_DateYear);
				String wor_effective_DateDate = convertDoubleToIntegerInStringForm(worExpirationDateMonth);
				Integer wor_effective_dateDate = Integer.parseInt(wor_effective_DateDate);
				wor_effective_DateDate = wor_effective_dateDate.toString();

				DatePicker dp_wor_effective_Date_date = new DatePicker();
				dp_wor_effective_Date_date.expDate = wor_effective_DateDate;
				dp_wor_effective_Date_date.expMonth = wor_effective_dateMonth;
				dp_wor_effective_Date_date.expYear = wor_effective_dateYear;
				
				dp_wor_effective_Date_date.pickExpDate("expirationDate");
				
				//Make additional TCV visible
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);",
				getObject("wor_additional_tcv_textbox"));

		//Enter value in TCV
		if (!worAdditionalTCV.equalsIgnoreCase("")) {
			getObject("wor_additional_tcv_textbox").sendKeys(worAdditionalTCV);
		}
		
		//Enter value in ACV
		if (!worAdditionalACV.equalsIgnoreCase("")) {
			getObject("wor_additional_acv_textbox").sendKeys(worAdditionalACV);
		}
		
		//Enter value in FACV
		if (!worAdditionalFACV.equalsIgnoreCase("")) {
			getObject("wor_additional_facv_textbox")
					.sendKeys(worAdditionalFACV);
		}

		//Click on submit button
		getObject("wor_submit_button").click();
		
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("wor_popup_id")));
		
		//Get Wor ID
		String wor_id = getObject("wor_popup_id").getText();
        APP_LOGS.debug("WOR created successfully with WOR id " + wor_id);

        //Click on OK button
		getObject("wor_popup_ok_button").click();

		APP_LOGS.debug("Quick Search the created action with WOR id " + wor_id);

		//Quick search WOR ID
		//String wor_id="WOR01013";
		getObject("quick_search_textbox").sendKeys(wor_id);
		getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("wor_show_page_id")));
		
		
		  //Get WOR ID 
		  String worIdFromShowPage = getObject("wor_show_page_id").getText();
		  
		  //Compare show page WOR ID with generated WOR ID
		  Assert.assertEquals(worIdFromShowPage, wor_id);
		  APP_LOGS.debug("WOR show page open successfully with WOR id " +
		  wor_id);
		 
		
		String WORNameShowPage = getObject("wor_show_page_name_textbox").getText();
		Assert.assertEquals(WORNameShowPage, worName);
		
		
		String WORTitleShowPage = getObject("wor_show_page_title_textbox").getText();
		
		Assert.assertEquals(WORTitleShowPage, worTitle);
		

		String WORBriefShowPage = getObject("wor_show_page_brief_textarea").getText();
		Assert.assertEquals(WORBriefShowPage, worBrief);
		

		String WORPriorityShowPage = getObject("wor_show_page_priority_dropdown").getText();
		Assert.assertEquals(WORPriorityShowPage, worPriority);
		

		String WORTypeShowPage = getObject("wor_show_page_type_multiselect").getText();
		worType += new String(" ");
		System.out.println(worType);
		Assert.assertEquals(WORTypeShowPage, worType);
		
		
		String WORBillingTypeShowPage = getObject("wor_show_page_billing_type_multiselect")
				.getText();
		worBillingType += new String(" ");
		System.out.println(worBillingType);
		Assert.assertEquals(WORBillingTypeShowPage, worBillingType);
		
		

		String WORContractEntityShowPage = getObject("wor_show_page_contracting_entity_dropdown")
				.getText();
		Assert.assertEquals(WORContractEntityShowPage, worContractingEntity);
				

			

		fail = false; // this executes if assertion passes

		
	}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(wor_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(wor_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(wor_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil
					.getRowNum(wor_suite_xls, this.getClass().getSimpleName()),
					"PASS");
		else
			TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil
					.getRowNum(wor_suite_xls, this.getClass().getSimpleName()),
					"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(wor_suite_xls, this.getClass().getSimpleName());
	}
	public String convertDoubleToIntegerInStringForm(String data) {
		data = Integer.valueOf(
				(Double.valueOf(Double.parseDouble(data))).intValue())
				.toString();
		return data;
	}
}
