package test.resources.com.sirion.suite.cr;

import junit.framework.Assert;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import test.resources.com.sirion.util.*;

public class ChangeRequestCreation extends TestSuiteBase

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
			String crSourceType, String crsourceName, String conCountries)
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

		 // Click analytics
		getObject("analytics_link").click();

		// CR Quick Link Clicking
		  getObject("cr_quick_link").click(); 
		  
		  
		//CR Global creation quick link clicking
		  Thread.sleep(5000);
		  getObject("cr_plus_button_global_listing").click();
		  
		  
		// supplier dropdown 
		  if (!crSupplier.equalsIgnoreCase("")){ new Select
		  (getObject("cr_supplier_dropdown")).selectByVisibleText(crSupplier);
		  }
		  
		  
		// source type
		  if (!crSourceType.equalsIgnoreCase("")){ 
			  new  Select(getObject("cr_sourceType_dropdown")).selectByVisibleText(crSourceType); 
		  } 
		  
		  
		  //sourceName dropdown
		  if(!crsourceName.equalsIgnoreCase("")){ new Select
		  (getObject("cr_sourceName_dropdown"
		  )).selectByVisibleText(crsourceName);
		  }
		  
		// submit button
		  getObject("cr_submit_globalbutton").click();
		  
		  
		// Title text box
		  if (!crTitle.equalsIgnoreCase("")) {
		  getObject("cr_title_textbox").sendKeys(crTitle);  
		  } 
		  
		//Description  text area
		  if(!crDescription.equalsIgnoreCase("")) {
		  getObject("cr_description_textarea").sendKeys(crDescription); 
		  }
		  
		// timezone
		  if (!crTimezone.equalsIgnoreCase("")) { new
		  Select(getObject("cr_timezone_dropdown"))
		  .selectByVisibleText(crTimezone); 
		  try { 
			  if (driver .findElement(By.className("success-icon"))
		  .getText() .contains(
		  "Current Date is different for the selected Time Zone"))
		  driver.findElement(By.xpath(".//button[contains(.,'OK')]")) .click();
		  } catch (Exception e) {
		  
		  } }
		  
		// class dropdown field 
		  if (!crClass.equalsIgnoreCase("")) { new
		  Select(getObject("cr_class_dropdown")) .selectByVisibleText(crClass);
		  }
		  
		  //Type dropdown
		  if (!crType.equalsIgnoreCase("")) { 
			  new Select(getObject("cr_type_dropdown")) .selectByVisibleText(crType);
			  }
		  
		  //Prioprity
		  if (!crPriority.equalsIgnoreCase("")){
			  new Select(getObject("cr_priority")) .selectByVisibleText(crPriority);
			  }
		  
		  //Responsibility
		  if (!crResponsibility.equalsIgnoreCase("")) { new
		  Select(getObject("cr_responsibility"))
		  .selectByVisibleText(crResponsibility); } 
		  
		  //CR ID
		  if(!crId.equalsIgnoreCase("")) { 
			  getObject("cr_id").sendKeys(crId);
			  }
		  
		  //Contract Entity dropdown
		  if (!crContractEntity.equalsIgnoreCase("")) { new
		  Select(getObject("cr_contractentity_dropdown"))
		  .selectByVisibleText(crContractEntity); 
		  }
		  
		  //Supplier access checkbox
		  if(!crSupplierAccess.equalsIgnoreCase("yes")) {
		  getObject("cr_supaccess_checkbox").click();
		  }
		  
		  //tier dropdown
		  if(!crTier.equalsIgnoreCase("")) {
			  new Select(getObject("cr_tier_dropdown")) .selectByVisibleText(crTier); }
		  if (!crDependentEntity.equalsIgnoreCase("yes")) {
		  getObject("cr_depentity_checkbox").click(); }
		  
		  Thread.sleep(5000);
		  
		  
		                                                                                                                                                                                                                       
		//Date picker
		  //CR Date
		  String cr_DateMonth =
		  convertDoubleToIntegerInStringForm(crDateMonth); int cr_dateMonth =
		  Integer.parseInt(cr_DateMonth); String cr_DateYear =
		  convertDoubleToIntegerInStringForm(crDateYear); int cr_dateYear =
		  Integer.parseInt(cr_DateYear); String cr_DateDate =
		  convertDoubleToIntegerInStringForm(crDateDate); Integer cr_dateDate =
		  Integer.parseInt(cr_DateDate); cr_DateDate = cr_dateDate.toString();
		  
		  DatePicker dp_cr_Date_date = new DatePicker();
		  dp_cr_Date_date.expDate = cr_DateDate; dp_cr_Date_date.expMonth =
		  cr_dateMonth; dp_cr_Date_date.expYear = cr_dateYear;
		  dp_cr_Date_date.pickExpDate("crDate");
		 
		  //Effective Date
		 String cr_effective_DateMonth =
		 convertDoubleToIntegerInStringForm(crEffectiveDateDate); int
		  cr_effective_dateMonth = Integer.parseInt(cr_effective_DateMonth);
		 String cr_effective_DateYear =
		 convertDoubleToIntegerInStringForm(crEffectiveDateYear); int
		 cr_effective_dateYear = Integer.parseInt(cr_effective_DateYear);
		 String cr_effective_DateDate =
		 convertDoubleToIntegerInStringForm(crEffectiveDateMonth); Integer
		  cr_effective_dateDate = Integer.parseInt(cr_effective_DateDate);
		  cr_effective_DateDate = cr_effective_dateDate.toString();
		  
		  DatePicker dp_cr_effective_Date_date = new DatePicker();
		  dp_cr_effective_Date_date.expDate = cr_effective_DateDate;
		  dp_cr_effective_Date_date.expMonth = cr_effective_dateMonth;
		  dp_cr_effective_Date_date.expYear = cr_effective_dateYear;
		  dp_cr_effective_Date_date.pickExpDate("effectiveDate");
		  
		  //TCV 
		  if (!crOriginalTCV.equalsIgnoreCase("")) {
		  getObject("cr_original_tcv").sendKeys(crOriginalTCV); }
		  
		  //Current Original TCV
		  if (!crCurrOriginalTCV.equalsIgnoreCase("")) {
			  new Select(getObject("cr_curr_original_tcv"))
		  .selectByVisibleText(crCurrOriginalTCV); }
		  
		  //Revised TCV
		  if(!crRevisedTCV.equalsIgnoreCase("")) {
		  getObject("cr_revised_tcv").sendKeys(crRevisedTCV); 
		  }
		  
		  //Current Revised TCV
		  if(!crCurrRevisedTCV.equalsIgnoreCase("")) {
			  new Select(getObject("cr_curr_revised_tcv"))
		  .selectByVisibleText(crCurrRevisedTCV);
		  }
		  
		  //Variance TCV
		  if(!crVarianceTCV.equalsIgnoreCase("")) {
		  getObject("cr_variance_tcv").sendKeys(crVarianceTCV); }
		  
		  //Current Variance TCV
		  if (!crCurrVarianceTCV.equalsIgnoreCase("")) {
			  new Select(getObject("cr_curr_variance_tcv"))
		  .selectByVisibleText(crCurrVarianceTCV); }
		  
		  //Original ACV
		  if (!crOriginalACV.equalsIgnoreCase("")) {
		  getObject("cr_original_acv").sendKeys(crOriginalACV); }
		  
		  //Current Original ACV
		  if(!crCurrOriginalACV.equalsIgnoreCase("")) {
			  new Select(getObject("cr_curr_original_acv"))
		  .selectByVisibleText(crCurrOriginalACV); }
		  
		  //RevisedACV
		  if (!crRevisedACV.equalsIgnoreCase("")) {
		  getObject("cr_revised_acv").sendKeys(crRevisedACV); }
		  
		  //Current Revised ACV
		  if(!crCurrRevisedACV.equalsIgnoreCase("")) { 
			  new Select(getObject("cr_curr_revised_acv"))
		  .selectByVisibleText(crCurrRevisedACV); } 
		  
		  
		  //Variance ACV
		  if(!crVarianceACV.equalsIgnoreCase("")) {
		  getObject("cr_variance_acv").sendKeys(crVarianceACV); }
		  
		  
		  //Current Variance ACV
		  if(!crCurrVarianceACV.equalsIgnoreCase("")) { 
			  new Select(getObject("cr_curr_variance_acv"))
		  .selectByVisibleText(crCurrVarianceACV); }
		  
		  //OriginalFACV
		  if (!crOriginalFACV.equalsIgnoreCase("")) {
		  getObject("cr_original_facv").sendKeys(crOriginalFACV); } 
		  
		  //Current Original FACV
		  if(!crCurrOriginalFACV.equalsIgnoreCase("")) {
			  new Select(getObject("cr_curr_original_facv"))
		  .selectByVisibleText(crCurrOriginalFACV); } 
		  
		  
		  //RevisedFACV
		  if(!crRevisedFACV.equalsIgnoreCase("")) {
		  getObject("cr_revised_facv").sendKeys(crRevisedFACV); }
		  
		  //Current Revised FACV
		  if(!crCurrRevisedFACV.equalsIgnoreCase("")) {
			  new Select(getObject("cr_curr_revised_facv"))
		  .selectByVisibleText(crCurrRevisedFACV); } 
		  
		  //VarianceFACV
		  if (!crVarianceFACV.equalsIgnoreCase("")) {
		  getObject("cr_variance_facv").sendKeys(crVarianceFACV); } 
		  
		  //Current Variance FACV
		  if(!crCurrVarianceFACV.equalsIgnoreCase("")) { 
			  new Select(getObject("cr_curr_variance_facv"))
		  .selectByVisibleText(crCurrVarianceFACV); }
		  
		  
		  
		  getObject("cr_comment_textarea").sendKeys(
		  "Change request should not take more than 15 days");
		  
		//selecting value for Request by dropdown
		  new Select(getObject("cr_requestedby_dropdown")).selectByIndex(2);
		  
		//selecting change request from the dropdown
		  new Select(getObject("cr_changerequest_dropdown")).selectByIndex(1);
		  
		  Thread.sleep(5000);
		 
		// Saving Change request 
		  getObject("cr_submit_globalbutton").click(); 
		  
		  Thread.sleep(5000); String cr_id =
		  getObject("cr_popup_id").getText();
		  APP_LOGS.debug("CR created successfully with CR id " + cr_id);
		  getObject("cr_popup_ok_button_from_cr").click();
		  APP_LOGS.debug("Quick Search the created CR with CR id " + cr_id);
		  
		  getObject("quick_search_textbox").sendKeys(cr_id);
		  getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		  
		  String crIdFromShowPage = getObject("cr_show_id").getText();
		  
		  Assert.assertEquals(crIdFromShowPage, cr_id);
		  System.out.println(crIdFromShowPage);
		  APP_LOGS.debug("CR show page open successfully with CR id " + cr_id);
		 
		 
			
			

		
		
		
		
		/*
		 *  * String CRTitleShowPage=getObject("cr_title_show").getText(); try {
		 * Assert.assertEquals(CRTitleShowPage, crTitle, "CR Title is ="
		 * +CRTitleShowPage+ "instead of"+ crTitle); } catch(Throwable e) {
		 * System.out.println("CR Title is =" +CRTitleShowPage+ "instead of"+
		 * crTitle); } String
		 * CRDescriptionShowPage=getObject("cr_description_show").getText(); try
		 * { Assert.assertEquals(CRDescriptionShowPage, crDescription,
		 * "CR Description is =" +CRDescriptionShowPage+ "instead of"+
		 * crDescription); } catch(Throwable e) {
		 * System.out.println("CR Description is =" +CRDescriptionShowPage+
		 * "instead of"+ crDescription); } String
		 * CRSupplierShowPage=getObject("cr_supplier_show").getText(); try {
		 * Assert.assertEquals(CRSupplierShowPage, crSupplier,
		 * "CR Supplier is =" +CRSupplierShowPage+ "instead of"+ crSupplier); }
		 * catch(Throwable e) { System.out.println("CR Supplier is ="
		 * +CRSupplierShowPage+ "instead of"+ crSupplier); }
		 * 
		 * String CRTimeZoneShowPage=getObject("cr_timezone_show").getText();
		 * try { Assert.assertEquals(CRTimeZoneShowPage, crTimezone,
		 * "CR TimeZone is =" +CRTimeZoneShowPage+ "instead of"+ crTimezone); }
		 * catch(Throwable e) { System.out.println("CR TimeZone is ="
		 * +CRTimeZoneShowPage+ "instead of"+ crTimezone); } String
		 * CRClassShowPage=getObject("cr_class_show").getText(); try {
		 * Assert.assertEquals(CRClassShowPage, crClass, "CR Class is ="
		 * +CRClassShowPage+ "instead of"+ crClass); } catch(Throwable e) {
		 * System.out.println("CR Class is =" +CRClassShowPage+ "instead of"+
		 * crClass); } String
		 * CRTypeShowPage=getObject("cr_type_show").getText(); try {
		 * Assert.assertEquals(CRTypeShowPage, crType, "CR Type is ="
		 * +CRTypeShowPage+ "instead of"+ crType); } catch(Throwable e) {
		 * System.out.println("CR Type is =" +CRTypeShowPage+ "instead of"+
		 * crType); }
		 * 
		 * String CRPriorityShowPage=getObject("cr_priority_show").getText();
		 * try { Assert.assertEquals(CRPriorityShowPage, crPriority,
		 * "CR Priority is =" +CRPriorityShowPage+ "instead of"+ crPriority); }
		 * 
		 * catch(Throwable e) { System.out.println("CR Priority is ="
		 * +CRPriorityShowPage+ "instead of"+ crPriority); }
		 * 
		 * String
		 * CRResponsibilityShowPage=getObject("cr_responsibility_show").getText
		 * (); try { Assert.assertEquals(CRResponsibilityShowPage,
		 * crResponsibility, "CR Responsibility is =" +CRResponsibilityShowPage+
		 * "instead of"+ crResponsibility); } catch(Throwable e) {
		 * System.out.println("CR Responsibility is ="
		 * +CRResponsibilityShowPage+ "instead of"+ crResponsibility); } String
		 * CRStatusShowPage=getObject("cr_status_show").getText(); try {
		 * Assert.assertEquals(CRStatusShowPage, crStatus, "CR Status is ="
		 * +CRStatusShowPage+ "instead of"+ crStatus); } catch(Throwable e) {
		 * System.out.println("CR Status is =" +CRStatusShowPage+ "instead of"+
		 * crStatus); } String CRIdShowPage=getObject("cr_id_show").getText();
		 * try { Assert.assertEquals(CRIdShowPage, crId, "CR ID is ="
		 * +CRIdShowPage+ "instead of"+ crId); } catch(Throwable e) {
		 * System.out.println("CR ID is =" +CRIdShowPage+ "instead of"+ crId); }
		 * String
		 * CRSupplierAccessShowPagee=getObject("cr_supaccess_show").getText();
		 * try { Assert.assertEquals(CRSupplierAccessShowPagee,
		 * crSupplierAccess, "CR Suppplier Access is ="
		 * +CRSupplierAccessShowPagee+ "instead of"+ crSupplierAccess); }
		 * catch(Throwable e) { System.out.println("CR Suppplier Access is ="
		 * +CRSupplierAccessShowPagee+ "instead of"+ crSupplierAccess); } String
		 * CRTierShowPage=getObject("cr_tier_show").getText(); try {
		 * Assert.assertEquals(CRTierShowPage, crTier, "CR Tier is ="
		 * +CRTierShowPage+ "instead of"+ crTier); } catch(Throwable e) {
		 * System.out.println("CR Tier is =" +CRTierShowPage+ "instead of"+
		 * crTier); }
		 * 
		 * String
		 * CRDependentEntityShowPage=getObject("cr_depentity_show").getText();
		 * try { Assert.assertEquals(CRDependentEntityShowPage,
		 * crDependentEntity, "CR Dependent Entity is ="
		 * +CRDependentEntityShowPage+ "instead of"+ crDependentEntity); }
		 * catch(Throwable e) { System.out.println("CR Dependent Entity is ="
		 * +CRDependentEntityShowPage+ "instead of"+ crDependentEntity); }
		 * String CRFunctionsShowPage=getObject("cr_functions_show").getText();
		 * try { Assert.assertEquals(CRFunctionsShowPage, crFunction,
		 * "CR Function is =" +CRFunctionsShowPage+ "instead of"+ crFunction); }
		 * catch(Throwable e) { System.out.println("CR Function is ="
		 * +CRFunctionsShowPage+ "instead of"+ crFunction); } String
		 * CRServicesShowPage=getObject("cr_services_show").getText(); try {
		 * Assert.assertEquals(CRServicesShowPage, crService, "CR Service is ="
		 * +CRServicesShowPage+ "instead of"+ crService); } catch(Throwable e) {
		 * System.out.println("CR Service is =" +CRServicesShowPage+
		 * "instead of"+ crService); } String
		 * CRRegionsShowPage=getObject("cr_regions_show").getText(); try
		 * 
		 * { Assert.assertEquals(CRRegionsShowPage, conRegions, "CR Region is ="
		 * +CRRegionsShowPage+ "instead of"+ conRegions); } catch(Throwable e) {
		 * System.out.println("CR Region is =" +CRRegionsShowPage+ "instead of"+
		 * conRegions); }
		 * 
		 * 
		 * String CRCountriesShowPage=getObject("cr_countries_show").getText();
		 * try {
		 * 
		 * Assert.assertEquals(CRCountriesShowPage, conCountries,
		 * "CR Country is =" +CRCountriesShowPage+ "instead of"+ conCountries);
		 * } catch(Throwable e) { System.out.println("CR Country is ="
		 * +CRCountriesShowPage+ "instead of"+ conCountries); } String
		 * CROriginalACVShowPage=getObject("cr_original_acv_show").getText();
		 * try
		 * 
		 * { Assert.assertEquals(CROriginalACVShowPage, crOriginalACV,
		 * "CR Original ACV is =" +CROriginalACVShowPage+ "instead of"+
		 * crOriginalACV); } catch(Throwable e) {
		 * System.out.println("CR Original ACV is =" +CROriginalACVShowPage+
		 * "instead of"+ crOriginalACV); } String
		 * CRCurrencyOriginalACVShowPage=getObject
		 * ("cr_curr_original_acv_show").getText(); try {
		 * Assert.assertEquals(CRCurrencyOriginalACVShowPage, crCurrOriginalACV,
		 * "CR Currency Original ACV is =" +CRCurrencyOriginalACVShowPage+
		 * "instead of"+ crCurrOriginalACV); } catch(Throwable e) {
		 * System.out.println("CR Currency Original ACV is ="
		 * +CRCurrencyOriginalACVShowPage+ "instead of"+ crCurrOriginalACV); }
		 * String
		 * CRRevisedACVShowPage=getObject("cr_revised_acv_show").getText(); try
		 * { Assert.assertEquals(CRRevisedACVShowPage, crRevisedACV,
		 * "CR Revised ACV is =" +CRRevisedACVShowPage+ "instead of"+
		 * crRevisedACV); } catch(Throwable e) {
		 * System.out.println("CR Revised ACV is =" +CRRevisedACVShowPage+
		 * "instead of"+ crRevisedACV); } String
		 * CRCurrencyRevisedACVShowPage=getObject
		 * ("cr_curr_revised_acv_show").getText(); try {
		 * Assert.assertEquals(CRCurrencyRevisedACVShowPage, crCurrRevisedACV,
		 * "CR Currency Revised ACV is =" +CRCurrencyRevisedACVShowPage+
		 * "instead of"+ crCurrRevisedACV); } catch(Throwable e) {
		 * System.out.println("CR Currency Revised ACV is ="
		 * +CRCurrencyRevisedACVShowPage+ "instead of"+ crCurrRevisedACV); }
		 * String CRVarianceShowPage=getObject("cr_variance_show").getText();
		 * try { Assert.assertEquals(CRVarianceShowPage, crVariance,
		 * "CR Currency Revised ACV is =" +CRVarianceShowPage+ "instead of"+
		 * crVariance); } catch(Throwable e) {
		 * System.out.println("CR Currency Revised ACV is ="
		 * +CRVarianceShowPage+ "instead of"+ crVariance); } String
		 * CRCurrencyVarianceShowPage
		 * =getObject("cr_curr_variance_show").getText(); try {
		 * Assert.assertEquals(CRCurrencyVarianceShowPage, crCurrVariance,
		 * "CR Currency Revised ACV is =" +CRCurrencyVarianceShowPage+
		 * "instead of"+ crCurrVariance); } catch(Throwable e) {
		 * System.out.println("CR Currency Revised ACV is ="
		 * +CRCurrencyVarianceShowPage+ "instead of"+ crCurrVariance); }
		 */

		/*
		 * fail = false; // this executes if assertion passes
		 * 
		 * APP_LOGS.debug(
		 * "CR open successfully, following parameters have been validated: CR Title-- "
		 * + crTitle + ", CR Description -- " + crDescription +
		 * ", CR TimeZone -- " + crTimezone + ", CR Class -- " + crClass +
		 * ", CR Type -- " + crType + ", " + "CR Priority  -- " + crPriority +
		 * ", CR Responsibiity -- " + crResponsibility + ", CR Id-- " + cr_id +
		 * ", CR Contract Entity -- " + crContractEntity +
		 * ", CR Supplier Access -- " + crSupplierAccess + ", " + "CR Tier -- "
		 * + crTier + ", CR Depenndent Entity -- " + crDependentEntity +
		 * ", CR Date -- " + crDateDate + ", CR Date in Month-- " + crDateMonth
		 * + ", CR Date in Year-- " + crDateYear + ", CR Function -- " +
		 * crFunction + ", CR Services -- " + crService +
		 * ", CR Contract Regions -- " + conRegions +
		 * ", CR Contract Countries -- " + conCountries +
		 * ", CR Original ACV -- " + crOriginalACV +
		 * ", CR Currency Original ACV -- " + crCurrOriginalACV +
		 * ", CR Revised ACV -- " + crRevisedACV +
		 * ", CR Currency Revised ACV -- " + crRevisedACV +
		 * " , CR Currency Revised ACV-- " CRCurrencyRevisedACVShowPage +
		 * ", CR Varience  -- " + crVarianceACV + ", CR Supplier--" + crSupplier
		 * + ", CR Status--" + crStatus);
		 */
	
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
