package test.resources.com.sirion.suite.cr;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.*;
import test.resources.com.sirion.util.TestUtil;

public class ChangeRequestNonWorkflow extends TestSuiteBase  {
	
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
		  
		  APP_LOGS.debug(" Opened CR Listing from CR Quick Link");
	        String CrIdFromListing = getObject("cr_id_link").getText();
	        System.out.println("CR id from listing is "+CrIdFromListing);
	        APP_LOGS.debug("CR id from listing is "+CrIdFromListing);
	        getObject("cr_id_link").click();
	        
	        String CrIdFromShowPage=getObject("cr_id_text").getText();

			System.out.println("CR id from show page is "+CrIdFromShowPage);
			APP_LOGS.debug("CR id from show page is "+CrIdFromShowPage);
			
			
			Assert.assertEquals(CrIdFromShowPage, CrIdFromListing);
			
			//this executes if assertion passes
			fail = false; 
			Thread.sleep(5000);
			AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Edit')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
	    	Thread.sleep(5000);
	    	
	    	
	    	//Title
	    	 if (getObject("cr_title_textbox").isDisplayed()) {
	   		  getObject("cr_title_textbox").sendKeys(crTitle);  
	   		  } 
	    	 
	    	 //Description
	   		  if (getObject("cr_description_textarea").isDisplayed()) {
	   		  getObject("cr_description_textarea").sendKeys(crDescription); 
	   		  }
	   		  
	     	 // timezone
	   		  if ((getObject("cr_timezone_dropdown").isDisplayed())) {
	   			  new  Select(getObject("cr_timezone_dropdown"))
	   		  .selectByVisibleText(crTimezone); 
	   		  
	   		  try { if (driver .findElement(By.className("success-icon"))
	   		  .getText() .contains(
	   		  "Current Date is different for the selected Time Zone"))
	   		  driver.findElement(By.xpath(".//button[contains(.,'OK')]")) .click();
	   		  } catch (Exception e) {
	   		  
	   		  } }
	   		  
	   		  
	   		 // class dropdown field 
	   		  if (getObject("cr_class_dropdown").isDisplayed()) { new
	   		  Select(getObject("cr_class_dropdown")) .selectByVisibleText(crClass);
	   		 
	   		  }
	   		  
	   		  if (getObject("cr_type_dropdown").isDisplayed()) { new
	   		  Select(getObject("cr_type_dropdown")) .selectByVisibleText(crType); }
	   		  if (getObject("cr_priority").isDisplayed()) { new
	   		  Select(getObject("cr_priority")) .selectByVisibleText(crPriority); }
	   		  if (getObject("cr_responsibility").isDisplayed()) { new
	   		  Select(getObject("cr_responsibility"))
	   		  .selectByVisibleText(crResponsibility); } 
	   		  if
	   		  (getObject("cr_id").isDisplayed()) { getObject("cr_id").sendKeys(crId); }
	   		  if (getObject("cr_contractentity_dropdown").isDisplayed()) { new
	   		  Select(getObject("cr_contractentity_dropdown"))
	   		  .selectByVisibleText(crContractEntity); } 
	   		  if(getObject("cr_supaccess_checkbox").isDisplayed()) {
	   		  getObject("cr_supaccess_checkbox").click(); } if
	   		  (getObject("cr_tier_dropdown").isDisplayed()) { new
	   		  Select(getObject("cr_tier_dropdown")) .selectByVisibleText(crTier); }
	   		  if (getObject("cr_depentity_checkbox").isDisplayed()) {
	   		  getObject("cr_depentity_checkbox").click(); }
	   		  
	   		  Thread.sleep(5000);
	   		  
	   		  //Date picker
	   		 
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
	   		  
	   		  if (getObject("cr_original_tcv").isDisplayed()) {
	   		  getObject("cr_original_tcv").sendKeys(crOriginalTCV); }
	   		  
	   		  if (getObject("cr_curr_original_tcv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_original_tcv"))
	   		  .selectByVisibleText(crCurrOriginalTCV); } if
	   		  (getObject("cr_revised_tcv").isDisplayed()) {
	   		  getObject("cr_revised_tcv").sendKeys(crRevisedTCV); } if
	   		  (getObject("cr_curr_revised_tcv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_revised_tcv"))
	   		  .selectByVisibleText(crCurrRevisedTCV); } if
	   		  (getObject("cr_variance_tcv").isDisplayed()) {
	   		  getObject("cr_variance_tcv").sendKeys(crVarianceTCV); }
	   		  
	   		  if (getObject("cr_curr_variance_tcv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_variance_tcv"))
	   		  .selectByVisibleText(crCurrVarianceTCV); }
	   		  
	   		  
	   		  if (getObject("cr_original_acv").isDisplayed()) {
	   		  getObject("cr_original_acv").sendKeys(crOriginalACV); } if
	   		  (getObject("cr_curr_original_acv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_original_acv"))
	   		  .selectByVisibleText(crCurrOriginalACV); } if
	   		  (getObject("cr_revised_acv").isDisplayed()) {
	   		  getObject("cr_revised_acv").sendKeys(crRevisedACV); } if
	   		  (getObject("cr_curr_revised_acv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_revised_acv"))
	   		  .selectByVisibleText(crCurrRevisedACV); } if
	   		  (getObject("cr_variance_acv").isDisplayed()) {
	   		  getObject("cr_variance_acv").sendKeys(crVarianceACV); } if
	   		  (getObject("cr_curr_variance_acv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_variance_acv"))
	   		  .selectByVisibleText(crCurrVarianceACV); }
	   		  
	   		  if (getObject("cr_original_facv").isDisplayed()) {
	   		  getObject("cr_original_facv").sendKeys(crOriginalFACV); } if
	   		  (getObject("cr_curr_original_facv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_original_facv"))
	   		  .selectByVisibleText(crCurrOriginalFACV); } if
	   		  (getObject("cr_revised_facv").isDisplayed()) {
	   		  getObject("cr_revised_facv").sendKeys(crRevisedFACV); } if
	   		  (getObject("cr_curr_revised_facv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_revised_facv"))
	   		  .selectByVisibleText(crCurrRevisedFACV); } if
	   		  (getObject("cr_variance_facv").isDisplayed()) {
	   		  getObject("cr_variance_facv").sendKeys(crVarianceFACV); } if
	   		  (getObject("cr_curr_variance_facv").isDisplayed()) { new
	   		  Select(getObject("cr_curr_variance_facv"))
	   		  .selectByVisibleText(crCurrVarianceFACV); }
	   		  
	   		  
	   		  Assert.assertEquals(crTitle, getObject("cr_title_textbox").getText());
	   		  APP_LOGS.debug("Title is updating");
	   		  


	   		  	    	
	    	
		  
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
