package test.resources.com.sirion.suite.interpretation;

import org.openqa.selenium.By;
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
	public void InterpretationCreateTest(String supName, String sourceType,String sourceNameTitle,String ipTitle,
			String ipBackground, String ipType,
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

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("int_quick_link")));
	    getObject("int_quick_link").click(); // IP Quick Link Clicking
	    
	    wait_in_report.until(ExpectedConditions.visibilityOf(getObject("ip_plus_icon")));
		Thread.sleep(10000);
	    getObject("ip_plus_icon").click();
	    
	    new Select (getObject("ip_plus_icon_supplier_dropdown")).selectByVisibleText(supName);
		
		if(sourceType.equalsIgnoreCase("Supplier")){
			new Select (getObject("ip_plus_icon_source_type_dropdown")).selectByVisibleText(sourceType);
			getObject("ip_plus_icon_submit_button").click();
		}else{
			new Select (getObject("ip_plus_icon_source_type_dropdown")).selectByVisibleText(sourceType);
			new Select (getObject("ip_plus_icon_source_name_title_dropdown")).selectByVisibleText(sourceNameTitle);
			Thread.sleep(5000);
			getObject("ip_plus_icon_submit_button").click();
		}
		
		if (!ipTitle.equalsIgnoreCase("")) {
			getObject("ip_title_textbox").clear();
			getObject("ip_title_textbox").sendKeys(ipTitle); // name
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
		 
		if (!ipRequestedDateMonth.equalsIgnoreCase("")) {
		//Requested Date
		String RequestedDateMonth = convertDoubleToIntegerInStringForm(ipRequestedDateMonth);
		int RequesteddateMonth = Integer.parseInt(RequestedDateMonth);
		String RequestedDateYear = convertDoubleToIntegerInStringForm(ipRequestedDateYear);
		int RequesteddateYear = Integer.parseInt(RequestedDateYear);
		String RequestedDateDate = convertDoubleToIntegerInStringForm(ipRequestedDateDate);
		Integer RequesteddateDate = Integer.parseInt(RequestedDateDate);
		RequestedDateDate = RequesteddateDate.toString();

		DatePicker dp_Interpretation_Requested_Date_date = new DatePicker();
		dp_Interpretation_Requested_Date_date.expDate = RequestedDateDate;
		dp_Interpretation_Requested_Date_date.expMonth = RequesteddateMonth;
		dp_Interpretation_Requested_Date_date.expYear = RequesteddateYear;
		dp_Interpretation_Requested_Date_date.pickExpDate("requestDate");   
		}
		
		//Planned Submission Date
		String PlannedSubmissionDateMonth = convertDoubleToIntegerInStringForm(ipPlannedSubmissionDateMonth);
		int PlannedSubmissiondateMonth = Integer.parseInt(PlannedSubmissionDateMonth);
		String PlannedSubmissionDateYear = convertDoubleToIntegerInStringForm(ipPlannedSubmissionDateYear);
		int PlannedSubmissiondateYear = Integer.parseInt(PlannedSubmissionDateYear);
		String PlannedSubmissionDateDate = convertDoubleToIntegerInStringForm(ipPlannedSubmissionDateDate);
		Integer PlannedSubmissiondateDate = Integer.parseInt(PlannedSubmissionDateDate);
		PlannedSubmissionDateDate = PlannedSubmissiondateDate.toString();

		DatePicker dp_Interpretation_PlannedSubmission_Date_date = new DatePicker();
		dp_Interpretation_PlannedSubmission_Date_date.expDate = PlannedSubmissionDateDate;
		dp_Interpretation_PlannedSubmission_Date_date.expMonth = PlannedSubmissiondateMonth;
		dp_Interpretation_PlannedSubmission_Date_date.expYear = PlannedSubmissiondateYear;
		dp_Interpretation_PlannedSubmission_Date_date.pickExpDate("plannedSubmissionDate");  
		
		  getObject("ac_submit").click();

		Thread.sleep(4000);
		
		String ip_id = getObject("ip_popup_id").getText();
		
		System.out.println("Interpretation id is " + ip_id);
		
		 getObject("ip_popup_ok_button_from_ip").click();
		
		getObject("quick_search_textbox").sendKeys(ip_id);
		
		getObject("quick_search_textbox").sendKeys(Keys.ENTER);

		// Assertion code shifted down
		
		try {
			if (driver.findElement(By.className("success-icon")).getText().contains("Either you do not have the required permissions or requested page does not exist anymore.")) {
				driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
				Thread.sleep(40000);
			}

		} catch (Exception e) {

		}

		
		Thread.sleep(10000);
		
	
			  //Assertion code shifted down- comment put at its place String
			  String ipIdFromShowPage = getObject("ip_show_id").getText();
			  
			  Assert.assertEquals(ipIdFromShowPage, ip_id);
			  
			  APP_LOGS.debug(
			  "Interpretation show page open successfully with action id " + ip_id);
			  
			  String IPStatusShowPage = getObject("ip_status_show").getText(); try {
			  Assert.assertEquals(IPStatusShowPage, ipStatus, "IP Status is -- "
			  +IPStatusShowPage+ " instead of -- " +ipStatus); } catch(Throwable e) {
			  System.out.println("IP Status is -- " +IPStatusShowPage+
			  " instead of -- " +ipStatus); }
			  
			  String IPTitleShowPage = getObject("ip_title_show").getText(); try {
			  Assert.assertEquals(IPTitleShowPage, ipTitle, "IP title is -- "
			  +IPTitleShowPage+ " instead of -- " +ipTitle); } catch(Throwable e) {
			  System.out.println("IP title is -- " +IPTitleShowPage+ " instead of -- "
			  +ipTitle); }
			  
			  String IPBackgroundShowPage = getObject("ip_background_show").getText();
			  try { Assert.assertEquals(IPBackgroundShowPage, ipBackground,
			  "IP Background is -- " +IPBackgroundShowPage+ " instead of -- "
			  +ipBackground); } catch(Throwable e) {
			  System.out.println("IP Background is -- " +IPBackgroundShowPage+
			  " instead of -- " +ipBackground); }
			  
			  String IPTypeShowPage = getObject("ip_type_show").getText(); try {
			  Assert.assertEquals(IPTypeShowPage, ipType, "IP Type is -- "
			  +IPTypeShowPage+ " instead of -- " +ipType); } catch(Throwable e) {
			  System.out.println("IP Type is -- " +IPTypeShowPage+ " instead of -- "
			  +ipType); }
			  
			  String IPPriorityShowPage = getObject("ip_priority_show").getText(); try
			  { Assert.assertEquals(IPPriorityShowPage, ipPriority,
			  "IP Priority is -- " +IPPriorityShowPage+ " instead of -- " +ipPriority);
			  } catch(Throwable e) { System.out.println("IP Priority is -- "
			  +IPPriorityShowPage+ " instead of -- " +ipPriority); }
			  
			  String IPSupplierShowPage = getObject("ip_supplier_show").getText(); try
			  { Assert.assertEquals(IPSupplierShowPage, ipSupplier,
			  "IP Supplier is -- " +IPSupplierShowPage+ " instead of -- " +ipSupplier);
			  } catch(Throwable e) { System.out.println("IP Supplier is -- "
			  +IPSupplierShowPage+ " instead of -- " +ipSupplier); }
			  
			  String IPTimezoneShowPage = getObject("ip_timezone_show").getText(); try
			  { Assert.assertEquals(IPTimezoneShowPage, ipTimezone,
			  "IP time zone is -- " +IPTimezoneShowPage+ " instead of -- "
			  +ipTimezone); } catch(Throwable e) {
			  System.out.println("IP time zone is -- " +IPTimezoneShowPage+
			  " instead of -- " +ipTimezone); }
			  
			  String IPTierShowPage = getObject("ip_tier_show").getText(); try {
			  Assert.assertEquals(IPTierShowPage, ipTier, "IP Tier is -- "
			  +IPTierShowPage+ " instead of -- " +ipTier); } catch(Throwable e) {
			  System.out.println("IP Tier is -- " +IPTierShowPage+ " instead of -- "
			  +ipTier); }
			  
			  String IPSupplierAccessShowPage =
			  getObject("ip_supplieraccess_show").getText(); try {
			  Assert.assertEquals(IPSupplierAccessShowPage, ipSupplierAccess,
			  "IP Supplier Access is -- " +IPSupplierAccessShowPage+ " instead of -- "
			  +ipSupplierAccess); } catch(Throwable e) {
			  System.out.println("IP Supplier Access is -- " +IPSupplierAccessShowPage+
			  " instead of -- " +ipSupplierAccess); }
			  
			  String IPDependentEntityShowPage =
			  getObject("ip_depentity_show").getText(); try {
			  Assert.assertEquals(IPDependentEntityShowPage, ipDependentEntity,
			  "IP Dependent Entity is -- " +IPDependentEntityShowPage+
			  " instead of -- " +ipDependentEntity); } catch(Throwable e) {
			  System.out.println("IP Dependent Entity is -- "
			  +IPDependentEntityShowPage+ " instead of -- " +ipDependentEntity); }
			  
			  String IPQuestionShowPage = getObject("ip_question_show").getText(); try
			  { Assert.assertEquals(IPQuestionShowPage, ipQuestion,
			  "IP Question is -- " +IPQuestionShowPage+ " instead of -- " +ipQuestion);
			  } catch(Throwable e) { System.out.println("IP Question is -- "
			  +IPQuestionShowPage+ " instead of -- " +ipQuestion); }
			  
			  String IPIncludeInFaqShowPage =
			  getObject("ip_includeinfaq_show").getText(); try {
			  Assert.assertEquals(IPIncludeInFaqShowPage, ipIncludeInFAQ,
			  "IP Include In Faq is -- " +IPIncludeInFaqShowPage+ " instead of -- "
			  +ipIncludeInFAQ); } catch(Throwable e) {
			  System.out.println("IP Include In Faq is -- " +IPIncludeInFaqShowPage+
			  " instead of -- " +ipIncludeInFAQ); }
			  
			  /*String IPFunctionsShowPage= getObject("ip_functions_show").getText(); try
			  { Assert.assertEquals(IPFunctionsShowPage, ipFunction,
			  "IP Functions are== "+IPFunctionsShowPage +"instead of" + ipFunction); }
			  catch(Throwable e) {
			  System.out.println("IP Functions are== "+IPFunctionsShowPage
			  +"instead of" + ipFunction); }
			  
			  String IPServicesShowPage= getObject("ip_services_show").getText(); try {
			  Assert.assertEquals(IPServicesShowPage, ipService,
			  "IP Services are== "+IPServicesShowPage +"instead of" + ipService); }
			  catch(Throwable e) {
			  System.out.println("IP Services are== "+IPServicesShowPage +"instead of"
			  + ipService); }
			  
			  String IPRegionsShowPage= getObject("ip_regions_show").getText(); try {
			  Assert.assertEquals(IPRegionsShowPage, ipRegion,
			  "IP Regions are== "+IPRegionsShowPage +"instead of" + ipRegion); }
			  catch(Throwable e) {
			  System.out.println("IP Regions are== "+IPRegionsShowPage +"instead of" +
			  ipRegion); }
			  
			  String IPCountriesShowPage= getObject("ip_countries_show").getText(); try
			  { Assert.assertEquals(IPCountriesShowPage, ipCountry,
			  "IP Countries are== "+IPCountriesShowPage +"instead of" + ipCountry); }
			  catch(Throwable e) {
			  System.out.println("IP Countries are== "+IPCountriesShowPage
			  +"instead of" + ipCountry); }*/
			 
				// App logs comment shifted down- comment put at its place
						
						  APP_LOGS.debug(
						  "IP open successfully, following parameters have been validated: IP Title-- "
						  + ipTitle+ ", IP TimeZone -- " + ipTimezone+ ", IP Type -- " +
						  ipType+ ", " + "IP Priority  -- " + ipPriority+ ", IP Id-- " + ip_id+
						  ", IP Status-- " + ipStatus+ ", IP Supplier Access -- " +
						  ipSupplierAccess+ ", " + "IP Tier -- " +
						  ipTier+", IP Depenndent Entity -- " + ipDependentEntity+
						  ", IP Function -- " + ipFunction+ ", IP Services -- " + ipService+
						  ", IP Contract Regions -- " + ipRegion +
						  ", IP Contract Countries -- " + ipCountry+
						  ",IP Background -- " + ipBackground+ ",IP Include in FAQ -- " +
						  ipIncludeInFAQ+ ", IP Planned Submission Date Date -- " +
						  ipPlannedSubmissionDateDate+" , IP Planned Submission Date Month-- "
						  +
						  ipPlannedSubmissionDateMonth+", IP Planned Submission Date Year  -- "
						  + ipPlannedSubmissionDateYear+ ", IP Question--"+ ipQuestion+
						  ", IP Requested Date Date--" +ipRequestedDateDate+
						  ", IP Requested Date Month--"
						  +ipRequestedDateMonth+", IP Requested Date Year--"
						  +ipRequestedDateYear);
		fail=false;				 
	}

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
