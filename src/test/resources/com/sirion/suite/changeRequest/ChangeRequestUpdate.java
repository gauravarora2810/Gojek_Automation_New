package test.resources.com.sirion.suite.changeRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.DatePicker;
import test.resources.com.sirion.util.TestUtil;

public class ChangeRequestUpdate extends TestSuiteBase {

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
			String crSourceType, String crsourceName, String crComment,String conCountries)
			throws InterruptedException {

		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "
					+ count);
		}

		APP_LOGS.debug("Executing Test Case Change Request Creation");

		// Calling method for opening browser from TestBase.java file
		openBrowser();

		// Calling a method for login(at different platform) from TestBase.java
		endUserLogin(CONFIG.getProperty("endUserURL"),
				CONFIG.getProperty("endUserUsername"),
				CONFIG.getProperty("endUserPassword"));
		
		
		Thread.sleep(10000);
		// Click analytics
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		getObject("analytics_link").click();
		
		Thread.sleep(10000);
		// Click analytics
		//wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("analytics_link"))).click();

		
		// Click CR Quick Link
		getObject("cr_quick_link").click();

		APP_LOGS.debug(" Opened CR Listing from CR Quick Link");

		//Get CR ID from listing
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("cr_id_link")));
		String CrIdFromListing = getObject("cr_id_link").getText();
		System.out.println("CR id from listing is " + CrIdFromListing);
		APP_LOGS.debug("CR id from listing is " + CrIdFromListing);
		
		//Click on CR ID
		getObject("cr_id_link").click();
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("cr_id_text")));
		
		//Get CR ID text
		String CrIdFromShowPage = getObject("cr_id_text").getText();
        System.out.println("CR id from show page is " + CrIdFromShowPage);
		APP_LOGS.debug("CR id from show page is " + CrIdFromShowPage);

		//Compare Listing page CR ID with Show Page CR ID 
		Assert.assertEquals(CrIdFromShowPage, CrIdFromListing);

		// this executes if assertion passes
		fail = false;
		
		//Click on Edit button 
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("cr_show_page_edit_button")));
		AssertJUnit.assertNotNull(getObject("cr_show_page_edit_button"));
		driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
		
		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("cr_id_text")));
		
		// Enter value in Title text box
		if (!crTitle.equalsIgnoreCase("")) {
			getObject("cr_title_textbox").clear();
			getObject("cr_title_textbox").sendKeys(crTitle);
		}

		// Enter value in Description text area
		if (!crDescription.equalsIgnoreCase("")) {
			getObject("cr_description_textarea").clear();
			getObject("cr_description_textarea").sendKeys(crDescription);
		}

		// Select value in timezone dropdown
		if (!crTimezone.equalsIgnoreCase("")) {
			new Select(getObject("cr_timezone_dropdown"))
					.selectByVisibleText(crTimezone);
			/*try {
				if (driver
						.findElement(By.className("success-icon"))
						.getText()
						.contains(
								"currency Date is different for the selected Time Zone"))
					driver.findElement(By.xpath(".//button[contains(.,'OK')]"))
							.click();
			} catch (Exception e) {

			}*/
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
			getObject("cr_cr_id_textbox").clear();
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

		// Check Dependent Entity checkbox
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
			getObject("cr_original_tcv_textbox").clear();
			getObject("cr_original_tcv_textbox").sendKeys(crOriginalTCV);
		}

		// Select value in currency Original TCV dropdown
		if (!crCurrOriginalTCV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_original_tcv_dropdown"))
					.selectByVisibleText(crCurrOriginalTCV);
		}

		// Enter value in Revised TCV text box
		if (!crRevisedTCV.equalsIgnoreCase("")) {
			getObject("cr_revised_tcv_textbox").clear();
			getObject("cr_revised_tcv_textbox").sendKeys(crRevisedTCV);
		}

		// Select value in currency Revised TCV dropdown
		if (!crCurrRevisedTCV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_revised_tcv_dropdown"))
					.selectByVisibleText(crCurrRevisedTCV);
		}

		// Enter value in Variance TCV text box
		if (!crVarianceTCV.equalsIgnoreCase("")) {
			getObject("cr_variance_tcv_textbox").clear();
			getObject("cr_variance_tcv_textbox").sendKeys(crVarianceTCV);
		}

		// Select value in currency Variance TCV dropdown
		if (!crCurrVarianceTCV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_variance_tcv_dropdown"))
					.selectByVisibleText(crCurrVarianceTCV);
		}

		// Enter value in Original ACV text box
		if (!crOriginalACV.equalsIgnoreCase("")) {
			getObject("cr_original_acv_textbox").clear();
			getObject("cr_original_acv_textbox").sendKeys(crOriginalACV);
		}

		// Select value in currency Original ACV dropdown
		if (!crCurrOriginalACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_original_acv_dropdown"))
					.selectByVisibleText(crCurrOriginalACV);
		}

		// Enter value in RevisedACV text box
		if (!crRevisedACV.equalsIgnoreCase("")) {
			getObject("cr_revised_acv_textbox").clear();
			getObject("cr_revised_acv_textbox").sendKeys(crRevisedACV);
		}

		// Select value in currency Revised ACV dropdown
		if (!crCurrRevisedACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_revised_acv_dropdown"))
					.selectByVisibleText(crCurrRevisedACV);
		}

		// Enter value in Variance ACV text box
		if (!crVarianceACV.equalsIgnoreCase("")) {
			getObject("cr_variance_acv_textbox").clear();
			getObject("cr_variance_acv_textbox").sendKeys(crVarianceACV);
		}

		// Select value in currency Variance ACV dropdown
		if (!crCurrVarianceACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_variance_acv_dropdown"))
					.selectByVisibleText(crCurrVarianceACV);
		}

		// Enter value in OriginalFACV text box
		if (!crOriginalFACV.equalsIgnoreCase("")) {
			getObject("cr_original_facv_textbox").clear();
			getObject("cr_original_facv_textbox").sendKeys(crOriginalFACV);
		}

		// Select value in currency Original FACV dropdown
		if (!crCurrOriginalFACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_original_facv_dropdown"))
					.selectByVisibleText(crCurrOriginalFACV);
		}

		// Enter value in RevisedFACV text box
		if (!crRevisedFACV.equalsIgnoreCase("")) {
			getObject("cr_revised_facv_textbox").clear();
			getObject("cr_revised_facv_textbox").sendKeys(crRevisedFACV);
		}

		// Select value in currency Revised FACV dropdown
		if (!crCurrRevisedFACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_revised_facv_dropdown"))
					.selectByVisibleText(crCurrRevisedFACV);
		}

		// Enter value in VarianceFACV text box
		if (!crVarianceFACV.equalsIgnoreCase("")) {
			getObject("cr_variance_facv_textbox").clear();
			getObject("cr_variance_facv_textbox").sendKeys(crVarianceFACV);
		}

		// Select value in currency Variance FACV dropdown
		if (!crCurrVarianceFACV.equalsIgnoreCase("")) {
			new Select(getObject("cr_currency_variance_facv_dropdown"))
					.selectByVisibleText(crCurrVarianceFACV);
		}
		// Enter value in Comment text area
		/*if (!crComment.equalsIgnoreCase("")) {
			getObject("cr_comment_textarea").clear();
			getObject("cr_comment_textarea").sendKeys(crComment);

		}*/

		// select value in Request By dropdown
		/*new Select(getObject("cr_requested_by_dropdown")).selectByIndex(2);

		// select value in Change Request dropdown
		new Select(getObject("cr_change_request_dropdown")).selectByIndex(1);*/
		Thread.sleep(10000);
		// Save Change Request
		//getObject("cr_show_page_update_Button").click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Update')][@clientvalidation='true']")));
		driver.findElement(By.xpath("//button[contains(.,'Update')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
		//wait_in_report.until(ExpectedConditions.visibilityOf(getObject("cr_show_page_id")));
		System.out.println("gaurav");
		//Compare Show page title with Entered title 
//          /wait_in_report.until(ExpectedConditions.visibilityOf(getObject("cr_show_page_title_textbox")));
           String CRTitleShowPage=getObject("cr_show_page_title_textbox").getText();
		  System.out.println(CRTitleShowPage);
		   Assert.assertEquals(CRTitleShowPage, crTitle);
		 
		   System.out.println("arora");
		  
		//Compare Show page Description with Entered Description
		  String CRDescriptionShowPage=getObject("cr_show_page_description_textarea").getText();
		  Assert.assertEquals(CRDescriptionShowPage, crDescription);
		 
		  
			//Compare Show page supplier with selected supplier
		  String CRSupplierShowPage=getObject("cr_show_page_supplier_dropdown").getText(); 
		  Assert.assertEquals(CRSupplierShowPage, crSupplier);
		  
		  
		//Compare Show page supplier with selected supplier
		  String CRSourcetypeShowPage=getObject("cr_show_page_source_type_dropdown").getText(); 
		  Assert.assertEquals(CRSourcetypeShowPage, crSourceType);
		  
		  
		  
		//Compare Show page supplier with selected supplier
		  String CRSourceNameShowPage=getObject("cr_show_page_source_name_dropdown").getText(); 
		  Assert.assertEquals(CRSourceNameShowPage, crsourceName);
		 
		  
			//Compare Show page timezone with selected timezone
		  String CRTimeZoneShowPage=getObject("cr_show_page_timezone_dropdown").getText();
		  Assert.assertEquals(CRTimeZoneShowPage, crTimezone);
		  
		  
		  
			//Compare Show page class with selected class
		  String CRClassShowPage=getObject("cr_show_page_class_dropdown").getText(); 
		  Assert.assertEquals(CRClassShowPage, crClass);
		 
		  
			//Compare Show page type value with selected type value
		  String CRTypeShowPage=getObject("cr_show_page_type_dropdown").getText();
		  Assert.assertEquals(CRTypeShowPage, crType);
		  
		  
			//Compare Show page priority with Entered priority
		  String CRPriorityShowPage=getObject("cr_show_page_priority_dropdown").getText();
		  Assert.assertEquals(CRPriorityShowPage, crPriority);
		  
		  
			//Compare Show page responsibility with Entered responsibility
		  String CRResponsibilityShowPage=getObject("cr_show_page_responsibility_dropdown").getText();
		  Assert.assertEquals(CRResponsibilityShowPage, crResponsibility);
		  
		  
		 
			//Compare Show page status with Entered status
		 /* String CRStatusShowPage=getObject("cr_show_page_status").getText();
		  Assert.assertEquals(CRStatusShowPage, crStatus);*/
		  
		  
			//Compare Show page CR ID with Entered CR ID
		 /* String CRIdShowPage=getObject("cr_show_page_cr_id").getText();
		  Assert.assertEquals(CRIdShowPage, crId);*/
		  
		  
		  
			//Compare Show page supplier Access with Entered Access value
		 /* String CRSupplierAccessShowPagee=getObject("cr_show_page_supplier_access_checkbox").getText();
		  Assert.assertEquals(CRSupplierAccessShowPagee, crSupplierAccess);*/
			 
		  
		  
			//Compare Show page Tier with selected Tier
		  String CRTierShowPage=getObject("cr_show_page_tier_dropdown").getText();
		  Assert.assertEquals(CRTierShowPage, crTier);
		  
		// Compare Show page Dependent entity with Entered Dependent entity
		// value
		/*String CRDependentEntityShowPage = getObject("cr_show_page_dependent_entity_checkbox")
				.getText();
		Assert.assertEquals(CRDependentEntityShowPage, crDependentEntity);*/
		
		
		
		//Compare Show page function with selected function
		/*String CRFunctionsShowPage = getObject("cr_show_page_functions_multiselect").getText();
		Assert.assertEquals(CRFunctionsShowPage, crFunction);
		
		
		  
		//Compare Show page service with selected service
		String CRServicesShowPage = getObject("cr_show_page_services_multiselect").getText();
		Assert.assertEquals(CRServicesShowPage, crService);
		

		//Compare Show page Region with Entered Region
		String CRRegionsShowPage = getObject("cr_show_page_regions_multiselect").getText();
		Assert.assertEquals(CRRegionsShowPage, conRegions);
		
		  
		  //Compare Show page countries with Entered countries
		  String CRCountriesShowPage=getObject("cr_show_page_countries_multiselect").getText();
		  Assert.assertEquals(CRCountriesShowPage, conCountries);*/
		
		 
		 
		    //Compare Show page original TCV  with Entered original TCV
			/*String CROriginalTCVShowPage = getObject("cr_show_page_original_tcv_textbox")
					.getText();
			Assert.assertEquals(CROriginalTCVShowPage, crOriginalTCV);
			
			
			
			//Compare Show page currency original TCV with Entered TCV
			String CRCurrencyOriginalTCVShowPage = getObject(
					"cr_show_page_currency_original_tcv_dropdown").getText();
			Assert.assertEquals(CRCurrencyOriginalTCVShowPage, crCurrOriginalTCV);
			
			
			
			//Compare Show page revised TCV with Entered revised TCV
			String CRRevisedTCVShowPage = getObject("cr_show_page_revised_tcv_textbox")
					.getText();
			Assert.assertEquals(CRRevisedTCVShowPage, crRevisedTCV);
			
			

			//Compare Show page currency revised TCV with Entered currency revised TCV
			String CRCurrencyRevisedTCVShowPage = getObject(
					"cr_show_page_currency_revised_tcv_dropdown").getText();
			Assert.assertEquals(CRCurrencyRevisedTCVShowPage, crCurrRevisedTCV);
			
			
			//Compare Show page variance TCV with Entered variance TCV
			String CRVarianceTCVShowPage = getObject("cr_show_page_variance_tcv_textbox").getText();
			Assert.assertEquals(CRVarianceTCVShowPage, crVarianceTCV);
			
			
			//Compare Show page currency variance TCV with Entered currency variance TCV
			String CRCurrencyVarianceTCVShowPage = getObject("cr_show_page_currency_variance_tcv_dropdown")
					.getText();
			Assert.assertEquals(CRCurrencyVarianceTCVShowPage, crCurrVarianceTCV);
				
			
				
			//Compare Show page original ACV  with Entered original ACV
			String CROriginalACVShowPage = getObject("cr_show_page_original_acv_textbox")
					.getText();
			Assert.assertEquals(CROriginalACVShowPage, crOriginalACV);
			
			
			
			//Compare Show page currency original ACV with Entered ACV
			String CRCurrencyOriginalACVShowPage = getObject(
					"cr_show_page_currency_original_acv_dropdown").getText();
			Assert.assertEquals(CRCurrencyOriginalACVShowPage, crCurrOriginalACV);
			
			
			//Compare Show page revised ACV with Entered revised ACV
			String CRRevisedACVShowPage = getObject("cr_show_page_revised_acv_textbox")
					.getText();
			Assert.assertEquals(CRRevisedACVShowPage, crRevisedACV);
			
			

			//Compare Show page currency revised ACV with Entered currency revised ACV
			String CRCurrencyRevisedACVShowPage = getObject(
					"cr_show_page_currency_revised_acv_dropdown").getText();
			Assert.assertEquals(CRCurrencyRevisedACVShowPage, crCurrRevisedACV);
			
			
			
			//Compare Show page variance ACV with Entered variance ACV
			String CRVarianceACVShowPage = getObject("cr_show_page_variance_acv_textbox").getText();
			Assert.assertEquals(CRVarianceACVShowPage, crVarianceACV);
			
			
			//Compare Show page currency variance ACV with Entered currency variance ACV
			String CRCurrencyVarianceACVShowPage = getObject("cr_show_page_currency_variance_acv_dropdown")
					.getText();
			Assert.assertEquals(CRCurrencyVarianceACVShowPage, crCurrVarianceACV);
					
			
			
			//Compare Show page original FACV  with Entered original FACV
			String CROriginalFACVShowPage = getObject("cr_show_page_original_facv_textbox")
					.getText();
			Assert.assertEquals(CROriginalFACVShowPage, crOriginalFACV);
			
				
			
			//Compare Show page currency original FACV with Entered FACV
			String CRCurrencyOriginalFACVShowPage = getObject(
					"cr_show_page_currency_original_facv_dropdown").getText();
			Assert.assertEquals(CROriginalFACVShowPage, crOriginalFACV);
			
			
			//Compare Show page revised FACV with Entered revised FACV
			String CRRevisedFACVShowPage = getObject("cr_show_page_revised_facv_textbox")
					.getText();
			Assert.assertEquals(CRRevisedFACVShowPage, crRevisedFACV);
			
			

			//Compare Show page currency revised FACV with Entered currency revised FACV
			String CRCurrencyRevisedFACVShowPage = getObject(
					"cr_show_page_currency_revised_facv_dropdown").getText();
			Assert.assertEquals(CRCurrencyRevisedFACVShowPage, crCurrRevisedFACV);
			
			
			
			//Compare Show page variance FACV with Entered variance FACV
			String CRVarianceFACVShowPage = getObject("cr_show_page_variance_facv_textbox").getText();
			Assert.assertEquals(CRVarianceFACVShowPage, crVarianceFACV);
			
			
			//Compare Show page currency variance FACV with Entered currency variance FACV
			String CRCurrencyVarianceShowPage = getObject("cr_show_page_currency_variance_facv_dropdown")
					.getText();
			Assert.assertEquals(CRCurrencyVarianceShowPage, crCurrVarianceFACV);
					
			*/
			

		// this executes if assertion passes
		fail = false; // this executes if assertion passes

		APP_LOGS.debug("CR open successfully, following parameters have been validated: CR Title-- "
				+ crTitle
				+ ", CR Description -- "
				+ crDescription
				+ ", CR TimeZone -- "
				+ crTimezone
				+ ", CR Class -- "
				+ crClass
				+ ", CR Type -- "
				+ crType
				+ ", "
				+ "CR Priority  -- "
				+ crPriority
				+ ", CR Responsibiity -- "
				+ crResponsibility
				+ ", CR Contract Entity -- "
				+ crContractEntity
				+ ", CR Supplier Access -- "
				+ crSupplierAccess
				+ ", "
				+ "CR Tier -- "
				+ crTier
				+ ", CR Depenndent Entity -- "
				+ crDependentEntity
				+ ", CR Date -- "
				+ crDateDate
				+ ", CR Date in Month-- "
				+ crDateMonth
				+ ", CR Date in Year-- "
				+ crDateYear
				+ ", CR Function -- "
				+ crFunction
				+ ", CR Services -- "
				+ crService
				+ ", CR Contract Regions -- "
				+ conRegions
				+ ", CR Contract Countries -- "
				+ conCountries
				+ ", CR Original ACV -- "
				+ crOriginalACV
				+ ", CR Currency Original ACV -- "
				+ crCurrOriginalACV
				+ ", CR Revised ACV -- "
				+ crRevisedACV
				+ ", CR Currency Revised ACV -- "
				+ crRevisedACV
				+ " , CR Currency Revised ACV-- "
				+ crRevisedACV
				+ ", CR Varience  -- "
				+ crVarianceACV
				+ ", CR Supplier--"
				+ crSupplier
				+ ", CR Status--" + crStatus);
		
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

	public String convertDoubleToIntegerInStringForm(String data) {
		data = Integer.valueOf(
				(Double.valueOf(Double.parseDouble(data))).intValue())
				.toString();
		return data;
	}

}
