package test.resources.com.sirion.suite.issue;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class IssueUpdate extends TestSuiteBase{
    String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=true;
    static boolean skip=false;
    static boolean isTestPass=true;
    
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(issue_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(issue_suite_xls, this.getClass().getSimpleName());
    }
    
	@Test(dataProvider = "getTestData")
	public void IssueUpdateTest(String issueTitle, String issueDescription,
			String issueType, String issuePriority, String issueCurrencies,
			String issueFinancialImpact, String issueGovernanceBody,
			String issueDeliveryCountries, String issueTimeZone,
			String issueRestrictPublicAccess,
			String issueRestrictRequesterAccess, String issueSupplierAccess,
			String issueTier, String issueDependentEntity,
			String issueDateMonth, String issueDateDate, String issueDateYear,
			String issuePlannedCompletionDateMonth,
			String issuePlannedCompletionDateDate,
			String issuePlannedCompletionDateYear, String issueResponsibility,
			String issueStatus, String issueSupName, String issueSource,
			String issueSourceName, String issueFunction, String issueService,
			String issueRegion, String issueCountry, String issueComment,
			String issueActualDateDate, String issueActualDateMonth,
			String issueActualDateYear, String issueRequestedBy,
			String issueChangeRequest, String issueUploadedFile)
			throws InterruptedException {
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "+ count);
		}

		APP_LOGS.debug("Executing Test Case Issue Audit Log Creation");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getObject("analytics_link").click();
		getObject("issue_quick_link").click(); // Issue Quick Link Clicking
		Thread.sleep(10000);
		getObject("is_id_link").click(); // Issue ID Clicking

		Thread.sleep(5000);
		driver.findElement(By.xpath(".//button[contains(.,'Edit')]")).click(); // Issue Edit button

		// Title Text Box
		if (!issueTitle.equalsIgnoreCase("")) {
			getObject("is_title_textbox").clear();
			getObject("is_title_textbox").sendKeys(issueTitle); // name
		}

		// Description Text Area Box
		// if (!issueDescription.equalsIgnoreCase("")) {
		// getObject("is_description_textarea").clear();
		// getObject("is_description_textarea").sendKeys(issueDescription); //
		// title
		// }
		//

		// Issue Type drop down
		if (!issueType.equalsIgnoreCase("")) {
			new Select(getObject("is_type_dropdown")).selectByVisibleText(issueType);
		}

		// Issue Priority drop down
		if (!issuePriority.equalsIgnoreCase("")) {
			new Select(getObject("is_priority_dropdown")).selectByVisibleText(issuePriority);
		}

		// Issue Currency drop down
		if (!issueCurrencies.equalsIgnoreCase("")) {
			new Select(getObject("is_currency_dropdown")).selectByVisibleText(issueCurrencies);
		}

		// Financial Impact text box
		if (!issueFinancialImpact.equalsIgnoreCase("")) {
			getObject("is_financial_impact_textbox").clear();
			getObject("is_financial_impact_textbox").sendKeys(
					issueFinancialImpact);// governance body
		}

		/*
		 * if(getObject("is_govbody_multi").equals(null)){ if
		 * (!issueGovernanceBody.equalsIgnoreCase("")) { new Select
		 * (getObject("is_govbody_multi"
		 * )).selectByVisibleText(issueGovernanceBody); } }
		 */

		// Issue delivery countries multi select
		if (!issueDeliveryCountries.equalsIgnoreCase("")) {
			new Select(getObject("is_delcountries_multi")).selectByVisibleText(issueDeliveryCountries);
		}

		// Issue Time Zone drop down
		if (!issueTimeZone.equalsIgnoreCase("")) {
			new Select(getObject("is_timezone_dropdown")).selectByVisibleText(issueTimeZone);
			try {
				if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
					driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
			} catch (Exception e) {
				
			}
		}

        //   Issue Restricted Access Check box 
		if (issueRestrictPublicAccess.equalsIgnoreCase("yes")) {
			getObject("is_restrict_puclic_access_checkbox").click();
		}

		// Issue Restricted Required Accesss Checkbox
		if (issueRestrictRequesterAccess.equalsIgnoreCase("yes")) {
			getObject("is_restrict_req_access_checkbox").click();
		}

		/*
		 * Issue Supplier Access Checkbox
		 * if(getObject("is_supplier_access_checkbox").isEnabled()){
		 * if(issueSupplierAccess.equalsIgnoreCase("yes")) {
		 * getObject("is_supplier_access_checkbox").click(); } }
		 */

		// Issue Tier Drop down
		if (!issueTier.equalsIgnoreCase("")) {
			new Select(getObject("is_tier_dropdown")).selectByVisibleText(issueTier);
		}

		if (issueDependentEntity.equalsIgnoreCase("yes")) {
			getObject("is_depentity_checkbox").click();
		}
         
         Date date = new Date();
         WebElement datepicker_ui=null;
         int current_month = date.getDate();
    
     // Date Commented down
                         
		// Issue Responsibility dropdown
		if (!issueResponsibility.equalsIgnoreCase("")) {
			new Select(getObject("is_responsibility_dropdown")).selectByVisibleText(issueResponsibility);
		}

		// Comments and Attachments Section
		if (!issueComment.equalsIgnoreCase("")) {
			getObject("is_Comment_text_area").clear();
			getObject("is_Comment_text_area").sendKeys(issueComment);
		}

		if (!issueRequestedBy.equalsIgnoreCase("")) {
			new Select(getObject("is_requested_by_dropdown")).selectByVisibleText(issueRequestedBy);
		}

		if (!issueChangeRequest.equalsIgnoreCase("")) {
			new Select(getObject("is_change_request_dropdown")).selectByVisibleText(issueChangeRequest);
		}
            
            
            
		if (!issueActualDateYear.equalsIgnoreCase("") && !issueActualDateMonth.equalsIgnoreCase("") && !issueActualDateDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("actualDate")).click();
			Double temp_issueActualDateYear_double = Double.parseDouble(issueActualDateYear);
			int temp_issueActualDateYear_int = temp_issueActualDateYear_double.intValue();
			String issueActualDateYear_string = Integer.toString(temp_issueActualDateYear_int);

			WebElement datepicker_ui11 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
			System.out.println(datepicker_ui11.isDisplayed());
			if (datepicker_ui11.isDisplayed() == true) {
				WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
				new Select(datepicker_ui_year).selectByVisibleText(issueActualDateYear_string);
			}

			Double temp_issueActualDateMonth_double = Double.parseDouble(issueActualDateMonth);
			int temp_issueActualDateMonth_int = temp_issueActualDateMonth_double.intValue();
			System.out.println(" issueActualDateMonth "+ temp_issueActualDateMonth_int);

			int click_issueActualDateMonth = current_month - temp_issueActualDateMonth_int;
			System.out.println("click " + click_issueActualDateMonth);
			for (; click_issueActualDateMonth >= 0; click_issueActualDateMonth = click_issueActualDateMonth - 1) {
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
			}
			Double temp_issueActualDateDate_double = Double.parseDouble(issueActualDateDate);
			int temp_issueActualDateDate_int = temp_issueActualDateDate_double.intValue();
			String issueActualDateDate_string = Integer.toString(temp_issueActualDateDate_int);
			driver.findElement(By.linkText(issueActualDateDate_string)).click();

		}

		if (!issueUploadedFile.equalsIgnoreCase("")) {
			getObject("is_browse_button").sendKeys((System.getProperty("user.dir")+ "\\Communication Documents\\Issues\\" + issueUploadedFile));
		}

		Thread.sleep(60000);

		driver.findElement(By.xpath(".//button[contains(.,'Update')]")).click();

		Thread.sleep(5000);

		try {
			if (driver.findElement(By.className("success-icon")).getText().contains("Either you do not have the required permissions or requested page does not exist anymore.")) {
				driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
				Thread.sleep(40000);
			}

		} catch (Exception e) {

			String is_id = getObject("is_show_id").getText();

			System.out.println(is_id);

			getObject("quick_search_textbox").sendKeys(is_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);

			Thread.sleep(5000);

		}
		fail = false;
		getObject("analytics_link").click();
		

	}
    
    // Date Commented down so put here
    
    /* Issue Date 
                    Date date = new Date();
                    WebElement datepicker_ui=null;
                    int current_month = date.getDate();
                    System.out.println(current_month);
                    if(driver.findElement(By.name("issueDate")).isEnabled())
                    {
                    driver.findElement(By.name("issueDate")).click();
                    
                    Double temp_issueDateYear_double = Double.parseDouble(issueDateYear);
                    int temp_issueDateYear_int = temp_issueDateYear_double.intValue();
                    String issueDateYear_string = Integer.toString(temp_issueDateYear_int);

                     datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
                    System.out.println(datepicker_ui.isDisplayed());
                    if (datepicker_ui.isDisplayed() == true) {
                      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
                      new Select(datepicker_ui_year).selectByVisibleText(issueDateYear_string);
                    }

                    Double temp_issueDateMonth_double = Double.parseDouble(issueDateMonth);
                    int temp_issueDateMonth_int = temp_issueDateMonth_double.intValue();
                    System.out.println(" issueDateMonth " + temp_issueDateMonth_int);

                    int click2 = current_month - temp_issueDateMonth_int;
                    System.out.println("click " + click2);
                    for (; click2 >= 0; click2 = click2 - 1) {
                    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
                    }
                    Double temp_issueDateDate_double = Double.parseDouble(issueDateDate);
                    int temp_issueDateDate_int = temp_issueDateDate_double.intValue();
                    String issueDateDate_string = Integer.toString(temp_issueDateDate_int);
                    driver.findElement(By.linkText(issueDateDate_string)).click();
                    }
                    
                    if(driver.findElement(By.name("plannedCompletionDate")).isEnabled())
                    {
                    driver.findElement(By.name("plannedCompletionDate")).click();

                    
               // Issue Planned Completion date
                    Double temp_issuePlannedCompletionDateYear_double = Double.parseDouble(issuePlannedCompletionDateYear);
                    int temp_issuePlannedCompletionDateYear_int = temp_issuePlannedCompletionDateYear_double.intValue();
                    String issuePlannedCompletionDateYear_string = Integer.toString(temp_issuePlannedCompletionDateYear_int);

                    System.out.println(datepicker_ui.isDisplayed());
                    if (datepicker_ui.isDisplayed() == true) {
                      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
                      new Select(datepicker_ui_year).selectByVisibleText(issuePlannedCompletionDateYear_string);
                    }

                    Double temp_issuePlannedCompletionDateMonth_double = Double.parseDouble(issuePlannedCompletionDateMonth);
                    int temp_issuePlannedCompletionDateMonth_int = temp_issuePlannedCompletionDateMonth_double.intValue();
                    System.out.println(" issuePlannedCompletionDateMonth " + temp_issuePlannedCompletionDateMonth_int);

                    int click3 = temp_issuePlannedCompletionDateMonth_int - current_month;
                    System.out.println("click " + click3);
                    for (; click3 >= 0; click3 = click3 - 1) {
                      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
                    }
                    Double temp_issuePlannedCompletionDateDate_double = Double.parseDouble(issuePlannedCompletionDateDate);
                    int temp_issuePlannedCompletionDateDate_int = temp_issuePlannedCompletionDateDate_double.intValue();
                    String issuePlannedCompletionDateDate_string = Integer.toString(temp_issuePlannedCompletionDateDate_int);
                    driver.findElement(By.linkText(issuePlannedCompletionDateDate_string)).click();             
                    }*/
    
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(issue_suite_xls, this.getClass().getSimpleName()) ;
    }

}
