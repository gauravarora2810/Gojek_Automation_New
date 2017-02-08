package test.resources.com.sirion.suite.interpretation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.DatePicker;
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
			openBrowser();

				endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
				
				Thread.sleep(10000);
			    wait_in_report.until(ExpectedConditions.visibilityOf(getObject("int_quick_link")));
			    getObject("int_quick_link").click(); // IP Quick Link Clicking

			   // driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ipTitle+"')]/preceding-sibling::td[1]/a")).click();
			    
			    
			    Thread.sleep(10000);
				getObject("ip_id_link").click();
				Thread.sleep(10000);

				// Clicking the clone button
				  System.out.println("gaurav");
				   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

					driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
				//getObject("ac_clone_button").click();
				System.out.println("clicked the clone button");
				driver.navigate().refresh();
				
				Thread.sleep(10000);

				 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

					driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
				//clicking the create action button after cloning
				//Assert.assertNotNull(driver.findElement(By.xpath("ac_create_action")));
				  System.out.println("gaurav");
				  
				  Thread.sleep(10000);
				   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//button[contains(.,'Submit')][@clientvalidation='true']")));
				   driver.findElement(By.xpath(".//button[contains(.,'Submit')][@clientvalidation='true']")).click();
					   System.out.println("arora");
					Thread.sleep(10000);

				
			  /*  if(getObject("ob_popup_id")!=null) {
			    	String ob_id = getObject("ob_popup_id").getText();
			    	APP_LOGS.debug("Service Level cloned successfully with Service Level id "+ob_id);
			    	
			    	getObject("ob_popup_id").click();
			    	Thread.sleep(5000);
			    	}*/
			    
			    if (getObject("ob_popup_id") != null) {

					String ob_id = getObject("ob_popup_id").getText();
					APP_LOGS.debug("Action Cloned successfully with Issue id "+ ob_id);

					//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(10000);

					APP_LOGS.debug("Quick Search the created OB with OB id "+ ob_id);

					getObject("quick_search_textbox").sendKeys(ob_id);

					getObject("quick_search_textbox").sendKeys(Keys.ENTER);
					Thread.sleep(10000);

					String ipIdFromShowPage = getObject("ip_show_id").getText();
					System.out.println("OB Id " + ipIdFromShowPage);

				}
		        
			    
			    
		Thread.sleep(10000);
		getObject("ip_edit_button").click(); 

		if (!ipTitle.equalsIgnoreCase("")) {
			getObject("ip_title_textbox").clear();
			getObject("ip_title_textbox").sendKeys(ipTitle);
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
		
		if (!ipPlannedSubmissionDateMonth.equalsIgnoreCase("")){
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
		}
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

		/*//Actual Date
				String ActualDateMonth = convertDoubleToIntegerInStringForm(ipActualDateMonth);
				int ActualdateMonth = Integer.parseInt(ActualDateMonth);
				String ActualDateYear = convertDoubleToIntegerInStringForm(ipActualDateYear);
				int ActualdateYear = Integer.parseInt(ActualDateYear);
				String ActualDateDate = convertDoubleToIntegerInStringForm(ipActualDateDate);
				Integer ActualdateDate = Integer.parseInt(ActualDateDate);
				ActualDateDate = ActualdateDate.toString();

				DatePicker dp_Interpretation_Actual_Date_date = new DatePicker();
				dp_Interpretation_Actual_Date_date.expDate = ActualDateDate;
				dp_Interpretation_Actual_Date_date.expMonth = ActualdateMonth;
				dp_Interpretation_Actual_Date_date.expYear = ActualdateYear;
				dp_Interpretation_Actual_Date_date.pickExpDate("actualDate");  
*/
		/*if (!ipUploadedFile.equalsIgnoreCase("")) {
			getObject("ip_browse_button").sendKeys((System.getProperty("user.dir")+ "\\Communication Documents\\Issues\\" + ipUploadedFile));
		}*/

		
Thread.sleep(10000);
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//button[contains(.,'Update')]")));
driver.findElement(By.xpath(".//button[contains(.,'Update')]")).click();
	  
		

		
		fail = false; // this executes if assertion passes

		// App Log Commented down
		Thread.sleep(10000);
		getObject("analytics_link").click();

	// App log commented down and placed here
	
	  APP_LOGS.debug(
	  "IP open successfully, following parameters have been validated: IP Title-- "
	  + ipTitle + ", IP TimeZone -- " + ipTimezone + ", IP Type -- " + ipType +
	  ", " + "IP Priority  -- " + ipPriority + ", IP Id-- " + ", IP Status-- "
	  + ipStatus + ", IP Supplier Access -- " + ipSupplierAccess + ", " +
	  "IP Tier -- " + ipTier + ", IP Depenndent Entity -- " + ipDependentEntity
	  + ", IP Function -- " + ipFunction + ", IP Services -- " + ipService +
	  ", IP Contract Regions -- " + ipRegion + ", IP Contract Countries -- " +
	  ipCountry + ", IP Area of Disagreement -- " + ipAreaofDisagreement +
	  ",IP Background -- " + ipBackground + ",IP Include in FAQ -- " +
	  ipIncludeInFAQ + ", IP Planned Submission Date Date -- " +
	  ipPlannedSubmissionDateDate + " , IP Planned Submission Date Month-- " +
	  ipPlannedSubmissionDateMonth + ", IP Planned Submission Date Year  -- " +
	  ipPlannedSubmissionDateYear + ", IP Question--" + ipQuestion +
	  ", IP Requested Date Date--" + ipRequestedDateDate +
	  ", IP Requested Date Month--" + ipRequestedDateMonth +
	  ", IP Requested Date Year--" + ipRequestedDateYear);
	 
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
