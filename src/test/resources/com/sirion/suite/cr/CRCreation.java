package test.resources.com.sirion.suite.cr;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.DatePicker;
import test.resources.com.sirion.util.TestUtil;

public class CRCreation extends TestSuiteBase

{
	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(cr_suite_xls, this.getClass()
				.getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}

		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(cr_suite_xls, this.getClass()
				.getSimpleName());
	}

	@Test(dataProvider = "getTestData")
	public void CRCreate(String crTitle, String crDescription,
			String crTimezone, String crClass, String crType,
			String crPriority, String crResponsibility, String crId,
			String crContractEntity, String crSupplierAccess, String crTier,
			String crDependentEntity, String crDateMonth, String crDateDate,
			String crDateYear, String crEffectiveDateMonth,
			String crEffectiveDateDate, String crEffectiveDateYear,
			String crOriginalTCV, String crCurrOriginalTCV,
			String crRevisedTCV, String crCurrRevisedTCV, String crVarianceTCV,
			String crCurrVarianceTCV, String crOriginalACV,
			String crCurrOriginalACV, String crRevisedACV,
			String crCurrRevisedACV, String crVarianceACV,
			String crCurrVarianceACV, String crOriginalFACV,
			String crCurrOriginalFACV, String crRevisedFACV,
			String crCurrRevisedFACV, String crVarianceFACV,
			String crCurrVarianceFACV, String crSupplier, String crStatus,
			String crFunction, String crService, String conRegions,
			String crSourceType, String crsourceName,String crComment, String conCountries)
			throws InterruptedException {

		// test the runmode of currency dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "
					+ count);
		}

		APP_LOGS.debug("Executing Test Case Change Request Creation");

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

		// Click CR Quick Link
		getObject("cr_quick_link").click();
		

		// Click on CR Global creation quick link
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("cr_global_listing_plus_button"))).click();
		

		// Select value in supplier dropdown
		if (!crSupplier.equalsIgnoreCase("")) {
			new Select(getObject("cr_supplier_dropdown"))
					.selectByVisibleText(crSupplier);
		}

		// Select value in source type dropdown
		if (!crSourceType.equalsIgnoreCase("")) {
			new Select(getObject("cr_source_type_dropdown"))
					.selectByVisibleText(crSourceType);
		}

		// Select value in source Name dropdown
		if (!crsourceName.equalsIgnoreCase("")) {
			new Select(getObject("cr_source_name_dropdown"))
					.selectByVisibleText(crsourceName);
		}

		// Click on submit button
		getObject("cr_submit_global_button").click();

		//  Enter value in Title text box
		if (!crTitle.equalsIgnoreCase("")) {
			getObject("cr_title_textbox").sendKeys(crTitle);
		}

		// Enter value in Description text area
		if (!crDescription.equalsIgnoreCase("")) {
			getObject("cr_description_textarea").sendKeys(crDescription);
		}

		// Select value in timezone dropdown
		if (!crTimezone.equalsIgnoreCase("")) {
			new Select(getObject("cr_timezone_dropdown"))
					.selectByVisibleText(crTimezone);
			try {
				if (driver
						.findElement(By.className("success-icon"))
						.getText()
						.contains(
								"currency Date is different for the selected Time Zone"))
					driver.findElement(By.xpath(".//button[contains(.,'OK')]"))
							.click();
			} catch (Exception e) {

			}
		}

		// Select value in class dropdown 
		if (!crClass.equalsIgnoreCase("")) {
			new Select(getObject("cr_class_dropdown"))
					.selectByVisibleText(crClass);
		}

		// Select value in Type dropdown
		if (!crType.equalsIgnoreCase("")) {
			new Select(getObject("cr_type_dropdown"))
					.selectByVisibleText(crType);
		}

		// Select value in Priority
		if (!crPriority.equalsIgnoreCase("")) {
			new Select(getObject("cr_priority_dropdown"))
					.selectByVisibleText(crPriority);
		}

		// Select value in Responsibility
		if (!crResponsibility.equalsIgnoreCase("")) {
			new Select(getObject("cr_responsibility_dropdown"))
					.selectByVisibleText(crResponsibility);
		}

		// Enter value in CR ID text box
		if (!crId.equalsIgnoreCase("")) {
			getObject("cr_cr_id_textbox").sendKeys(crId);
		}


		// Check Supplier access checkbox
		if (!crSupplierAccess.equalsIgnoreCase("yes")) {
			getObject("cr_supplier_access_checkbox").click();
		}

		// Select value in tier dropdown
		if (!crTier.equalsIgnoreCase("")) {
			new Select(getObject("cr_tier_dropdown"))
					.selectByVisibleText(crTier);
		}
		
		// Check  Dependent Entity checkbox
		if (!crDependentEntity.equalsIgnoreCase("yes")) {
			getObject("cr_dependent_entity_checkbox").click();
		}

		

		// Date picker
		// Enter value in CR Date field
		String cr_DateMonth = convertDoubleToIntegerInStringForm(crDateMonth);
		int cr_dateMonth = Integer.parseInt(cr_DateMonth);
		String cr_DateYear = convertDoubleToIntegerInStringForm(crDateYear);
		int cr_dateYear = Integer.parseInt(cr_DateYear);
		String cr_DateDate = convertDoubleToIntegerInStringForm(crDateDate);
		Integer cr_dateDate = Integer.parseInt(cr_DateDate);
		cr_DateDate = cr_dateDate.toString();

		DatePicker dp_cr_Date_date = new DatePicker();
		dp_cr_Date_date.expDate = cr_DateDate;
		dp_cr_Date_date.expMonth = cr_dateMonth;
		dp_cr_Date_date.expYear = cr_dateYear;
		dp_cr_Date_date.pickExpDate("crDate");

		// Enter value in Effective Date field
		String cr_effective_DateMonth = convertDoubleToIntegerInStringForm(crEffectiveDateDate);
		int cr_effective_dateMonth = Integer.parseInt(cr_effective_DateMonth);
		String cr_effective_DateYear = convertDoubleToIntegerInStringForm(crEffectiveDateYear);
		int cr_effective_dateYear = Integer.parseInt(cr_effective_DateYear);
		String cr_effective_DateDate = convertDoubleToIntegerInStringForm(crEffectiveDateMonth);
		Integer cr_effective_dateDate = Integer.parseInt(cr_effective_DateDate);
		cr_effective_DateDate = cr_effective_dateDate.toString();

		DatePicker dp_cr_effective_Date_date = new DatePicker();
		dp_cr_effective_Date_date.expDate = cr_effective_DateDate;
		dp_cr_effective_Date_date.expMonth = cr_effective_dateMonth;
		dp_cr_effective_Date_date.expYear = cr_effective_dateYear;
		dp_cr_effective_Date_date.pickExpDate("effectiveDate");

		// Enter value in TCV text box
		if (!crOriginalTCV.equalsIgnoreCase("")) {
			getObject("cr_original_tcv_textbox").sendKeys(crOriginalTCV);
		}

		// Select value in currency Original TCV dropdown
		if (!crCurrOriginalTCV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_original_tcv_dropdown"))
					.selectByVisibleText(crCurrOriginalTCV);
		}

		// Enter value in Revised TCV text box
		if (!crRevisedTCV.equalsIgnoreCase("")) {
			getObject("cr_revised_tcv_textbox").sendKeys(crRevisedTCV);
		}

		// Select value in currency Revised TCV dropdown
		if (!crCurrRevisedTCV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_revised_tcv_dropdown"))
					.selectByVisibleText(crCurrRevisedTCV);
		}

		// Enter value in Variance TCV text box
		if (!crVarianceTCV.equalsIgnoreCase("")) {
			getObject("cr_variance_tcv_textbox").sendKeys(crVarianceTCV);
		}

		// Select value in currency Variance TCV dropdown
		if (!crCurrVarianceTCV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_variance_tcv_dropdown"))
					.selectByVisibleText(crCurrVarianceTCV);
		}

		// Enter value in Original ACV text box
		if (!crOriginalACV.equalsIgnoreCase("")) {
			getObject("cr_original_acv_textbox").sendKeys(crOriginalACV);
		}

		// Select value in currency Original ACV dropdown
		if (!crCurrOriginalACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_original_acv_dropdown"))
					.selectByVisibleText(crCurrOriginalACV);
		}

		// Enter value in RevisedACV text box
		if (!crRevisedACV.equalsIgnoreCase("")) {
			getObject("cr_revised_acv_textbox").sendKeys(crRevisedACV);
		}

		// Select value in currency Revised ACV dropdown
		if (!crCurrRevisedACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_revised_acv_dropdown"))
					.selectByVisibleText(crCurrRevisedACV);
		}

		// Enter value in Variance ACV text box
		if (!crVarianceACV.equalsIgnoreCase("")) {
			getObject("cr_variance_acv_textbox").sendKeys(crVarianceACV);
		}

		// Select value in currency Variance ACV dropdown
		if (!crCurrVarianceACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_variance_acv_dropdown"))
					.selectByVisibleText(crCurrVarianceACV);
		}

		// Enter value in OriginalFACV text box
		if (!crOriginalFACV.equalsIgnoreCase("")) {
			getObject("cr_original_facv_textbox").sendKeys(crOriginalFACV);
		}

		// Select value in currency Original FACV dropdown
		if (!crCurrOriginalFACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_original_facv_dropdown"))
					.selectByVisibleText(crCurrOriginalFACV);
		}

		// Enter value in RevisedFACV text box
		if (!crRevisedFACV.equalsIgnoreCase("")) {
			getObject("cr_revised_facv_textbox").sendKeys(crRevisedFACV);
		}

		// Select value in currency Revised FACV dropdown
		if (!crCurrRevisedFACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_revised_facv_dropdown"))
					.selectByVisibleText(crCurrRevisedFACV);
		}

		// Enter value in VarianceFACV text box
		if (!crVarianceFACV.equalsIgnoreCase("")) {
			getObject("cr_variance_facv_textbox").sendKeys(crVarianceFACV);
		}

		// Select value in currency Variance FACV dropdown
		if (!crCurrVarianceFACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_variance_facv_dropdown"))
					.selectByVisibleText(crCurrVarianceFACV);
		}
              //Enter value in Comment text area
		/*if (!crComment.equalsIgnoreCase("")) {
			getObject("cr_comment_textarea").sendKeys(crComment);
			
		}*/

		// select value in Request By dropdown
		//new Select(getObject("cr_requested_by_dropdown")).selectByIndex(2);

		// select value in Change Request dropdown
		//new Select(getObject("cr_change_request_dropdown")).selectByIndex(1);

		
		Thread.sleep(5000);
		System.out.println("gaurav");
		// Save Change Request
		
		getObject("cr_save_button").click();
		System.out.println("arora");
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("cr_popup_id")));
		
		//Click on OK button
		String cr_id = getObject("cr_popup_id").getText();
		APP_LOGS.debug("CR created successfully with CR id " + cr_id);
		getObject("cr_popup_ok_button_from_cr").click();
		
		
         //Quick Search of CR ID
		getObject("quick_search_textbox").sendKeys(cr_id);
		getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		APP_LOGS.debug("Quick Search the created CR with CR id " + cr_id);
		
		//Compare Show page CR ID with Entered Generated CR ID
		String crIdFromShowPage = getObject("cr_show_page_id").getText();
		AssertJUnit.assertEquals(crIdFromShowPage, cr_id);
		APP_LOGS.debug("CR show page open successfully with CR id " + cr_id);

		
		
			

		// this executes if assertion passes
		fail = false; // this executes if assertion passes

		
		
	}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(cr_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(cr_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(cr_suite_xls, this.getClass()
					.getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil
					.getRowNum(cr_suite_xls, this.getClass().getSimpleName()),
					"PASS");
		else
			TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil
					.getRowNum(cr_suite_xls, this.getClass().getSimpleName()),
					"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(cr_suite_xls, this.getClass().getSimpleName());
	}

	
}
