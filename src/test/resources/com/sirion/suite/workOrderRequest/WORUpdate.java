package test.resources.com.sirion.suite.workOrderRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.DatePicker;
import test.resources.com.sirion.util.TestUtil;

public class WORUpdate extends TestSuiteBase {
	String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=false;
    static boolean skip=false;
    static boolean isTestPass=true;
    
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(wor_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(wor_suite_xls, this.getClass().getSimpleName());
    }
    
    @Test (dataProvider = "getTestData")
    public void WORUpdateTest(
        String worName, String worTitle, String worBrief, String    worPriority, String worType, String worBillingType, String  worCurrency, String worContractingEntity, 
        String    worDeliveryCountries, String    worTimezone, String worSupplierAccess, String   worTier, 
        String worEffectiveDateDate, String    worEffectiveDateMonth, String   worEffectiveDateYear, 
        String    worExpirationDateDate, String   worExpirationDateMonth, String  worExpirationDateYear, 
        String  worAdditionalTCV, String    worAdditionalACV, String   worAdditionalFACV     
            ) throws InterruptedException {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
        APP_LOGS.debug("Executing Test Case WOR Creation");
     // Calling method to open browser
     		openBrowser();

     		// Calling a method for login(at different platform) from TestBase.java file
     		endUserLogin(CONFIG.getProperty("endUserURL"),
     				CONFIG.getProperty("endUserUsername"),
     				CONFIG.getProperty("endUserPassword"));

     		Thread.sleep(10000);
     		//Click on analytics
     		getObject("analytics_link").click();
     		
     		//Click WOR quick link
     		Thread.sleep(10000);
     		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("wor_quick_link"))).click();
     		APP_LOGS.debug(" Opened wor Listing from wor Quick Link");
     		
     		//Get WOR ID from listing
     		
     		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("wor_id_link")));
     		String worIdFromListing = getObject("wor_id_link").getText();
   	        
   	        System.out.println("wor id from listing is "+worIdFromListing);
   	        APP_LOGS.debug("wor id from listing is "+worIdFromListing);
   	        
   	        //Click on WOR ID
   	        getObject("wor_id_link").click();
   	     wait_in_report.until(ExpectedConditions.visibilityOf(getObject("wor_show_page_id")));
   	        String worIdFromShowPage=getObject("wor_show_page_id").getText();

   			System.out.println("wor id from show page is "+worIdFromShowPage);
   			APP_LOGS.debug("wor id from show page is "+worIdFromShowPage);
   			
   			
   			Assert.assertEquals(worIdFromShowPage, worIdFromListing);
   			
   			//this executes if assertion passes
   			fail = false; 
   			Thread.sleep(5000);
   			AssertJUnit.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Edit')]")));
   	    	driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
   	    	Thread.sleep(5000);
   	    	
     		 
     		
     		
                 //Enter value in name 
     		if (!worName.equalsIgnoreCase("")) {
     			getObject("wor_name_textbox").clear();
     			getObject("wor_name_textbox").sendKeys(worName);
     		}
     		
     		//Enter value in title
     		if (!worTitle.equalsIgnoreCase("")) {
     			getObject("wor_title_textbox").clear();
     			getObject("wor_title_textbox").sendKeys(worTitle);
     		}
     		
     		//Enter value in Brief
     		if (!worBrief.equalsIgnoreCase("")) {
     			getObject("wor_brief_textarea").clear();
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
     		
     	
     		     		
     		//Select value in contracting entity
     		if (!worContractingEntity.equalsIgnoreCase("")) {
     			new Select(getObject("wor_contracting_entity_dropdown"))
     					.selectByVisibleText(worContractingEntity);
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
     				dp_wor_Date_date.pickExpDate("effectiveDate");

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
     		getObject("wor_show_page_update_button").click();
     		
     	
     		
     		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("wor_show_page_id")));
     		
     		
     		  //Get WOR ID 
     		  worIdFromShowPage = getObject("wor_show_page_id").getText();
     		  
     		  //Compare show page WOR ID with generated WOR ID
     		  Assert.assertEquals(worIdFromShowPage, worIdFromListing);
     		  APP_LOGS.debug("WOR show page open successfully with WOR id " +
     				 worIdFromListing);
     		 
     		/*Thread.sleep(2000);
     		String WORNameShowPage = getObject("wor_show_page_name_textbox").getText();
     		System.out.println(WORNameShowPage+"v");
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
     		


     		String WORTimeZoneShowPage = getObject("wor_show_page_timezone_dropdown").getText();
     		Assert.assertEquals(WORTimeZoneShowPage, worTimezone);
     		

      		String WORAdditionalTCVShowPage = getObject("wor_show_page_additional_tcv_textbox")
     				.getText(); 
      		WORAdditionalTCVShowPage= WORAdditionalTCVShowPage.substring(0);
      		System.out.println(WORAdditionalTCVShowPage);
     		Assert.assertEquals(WORAdditionalTCVShowPage, worAdditionalTCV);
     		

     		String WORAdditionalACVShowPage = getObject("wor_show_page_additional_acv_show")
     				.getText();
     		Assert.assertEquals(WORAdditionalACVShowPage, worAdditionalACV);
     		

     		String WORAdditionalFACVShowPage = getObject("wor_show_page_additional_facv_show")
     				.getText();
     		Assert.assertEquals(WORAdditionalFACVShowPage, worAdditionalFACV);
     		*/
                
                fail = false; // this executes if assertion passes

                // driver.findElement(By.xpath(".//*[@id='h-analytics']/a")).click();
               // getObject("analytics_link").click();
               // getObject("contract_quick_link").click();
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil.getRowNum(wor_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil.getRowNum(wor_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(wor_suite_xls, this.getClass().getSimpleName()) ;
    }
    public String convertDoubleToIntegerInStringForm(String data) {
		data = Integer.valueOf(
				(Double.valueOf(Double.parseDouble(data))).intValue())
				.toString();
		return data;
	}
}

