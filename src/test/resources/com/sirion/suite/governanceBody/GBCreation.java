package test.resources.com.sirion.suite.governanceBody;


import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class GBCreation extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;
  Date date;
  Date date1;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(governance_body_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(governance_body_suite_xls, this.getClass().getSimpleName());
  }

  /*----------------------initializing------------------------------*/
	  
  @Test(dataProvider = "getTestData")
  public void GBCreate(String supplier, String contract, String	gbTitle, String	gbDescription,
		  String gbGoal, String gbGBType, String	gbSupplier, String	gbContract, String gbFrequencyType,
		  String gbFrequency, String gbWeekType, String	gbStartDateDate, String	gbStartDateMonth, String gbStartDateYear,
		  String gbEndDateDate, String gbEndDateMonth, String gbEndDateYear, String gbIncludeStartDate, String	gbIncludeEndDate, String startTimeselect, String gbDuration,String gbLocation, String gbPatternDateYear, String gbPatternDateMonth,String gbPatternDateDate,String gbEffectiveDateYear,
String gbEffectiveDateMonth, String gbEffectiveDateDate, String gbFunctions, String gbServices, String gbRegions, String gbCountries, String gbTimeZone)throws InterruptedException {
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Governance Body Creation with Governance Body name --- " +gbTitle+ " under supplier --- "+gbSupplier);

    
	// Launch The Browser
	openBrowser();
	endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
	Thread.sleep(10000);

	driver.get(CONFIG.getProperty("endUserURL"));
	Thread.sleep(10000);
 
System.out.println("Hello contract");
    getObject("contract_quick_link").click();
    Thread.sleep(5000);// click on contract  quick link
   //driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ contract +"')]/preceding-sibling::td[1]/a")).click();
   
    getObject("gb_contract_listing_id").click();
//    driver.findElement(By.xpath("gb_contract_listing_id")).click();
   Thread.sleep(10000);

   wait_in_report.until(ExpectedConditions.elementToBeClickable(By
			.xpath("//*[@id='mainContainer']/div/div[2]/a")));
	System.out.println("Plus button is available now ");
	plus_button("contract_plus_button"); // web element for plus button on
										// contract page
	System.out.println("Clicked on the plus button");
	wait_in_report.until(ExpectedConditions
			.elementToBeClickable(getObject("gb_create_link_from_contract")));
	getObject("gb_create_link_from_contract").click(); // click issue create link
	System.out.println("clicked the create governance body");
	Thread.sleep(5000);
   
    if (!supplier.equalsIgnoreCase("")) {
        new Select(getObject("gb_supplier_select")).selectByVisibleText(supplier); //gb supplier
      }
    Thread.sleep(10000);
    if (!contract.equalsIgnoreCase("")) {
    	new Select(getObject("gb_contract_select")).selectByVisibleText(contract); // gb contract 
      }
    
    if (!contract.equalsIgnoreCase("")) {
        getObject("gb_submit_button").click();// gb submit
      }


    if (!gbTitle.equalsIgnoreCase("")) {
      getObject("gb_title_text").sendKeys(gbTitle); // gb Title
    }
    Thread.sleep(2000);
      if (!gbDescription.equalsIgnoreCase("")) {
      getObject("gb_description_text").sendKeys(gbDescription); // gb description
    }
      Thread.sleep(2000);
    if (!gbGoal.equalsIgnoreCase("")) {
      getObject("gb_goal_text").sendKeys(gbGoal); // gb Goal
    }
    Thread.sleep(2000);
    if (!gbGBType.equalsIgnoreCase("")) {
      new Select(getObject("gb_GBtype_select")).selectByVisibleText(gbGBType); // gb type
    }

    
    Thread.sleep(5000);
    if (startTimeselect.equalsIgnoreCase("")){
        new Select(getObject("gb_start_time")).selectByIndex(1); // gb start time
        }
    
    Thread.sleep(5000);
    if (gbDuration.equalsIgnoreCase("")){
        new Select(getObject("gb_duration")).selectByIndex(1); // gb Duration
        }
    
    Thread.sleep(5000);
    if (gbTimeZone.equalsIgnoreCase("")){
        new Select(getObject("gb_time_zone")).selectByIndex(1); // gb Time zone
        try {
      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
      	} catch (Exception e) {
      		
      	}    
    }
 /*  Thread.sleep(5000);
    if (!gbLocation.equalsIgnoreCase("")){
        getObject("gb_location_textarea").sendKeys(gbLocation); // gb Location
        }
    */
    Thread.sleep(2000);
    if (!gbFrequencyType.equalsIgnoreCase("")) {
      new Select(getObject("gb_frequency_type")).selectByVisibleText(gbFrequencyType); //  gb frequency type
    }
    Thread.sleep(2000);

    if (!gbFrequency.equalsIgnoreCase("")) {
      new Select(getObject("gb_frequency")).selectByVisibleText(gbFrequency); //gb frequency
    }
    Thread.sleep(2000);
    if (!gbWeekType.equalsIgnoreCase("")){
    new Select(getObject("gb_week_type")).selectByVisibleText(gbWeekType); // gb week type
    }

    /*------------------------------Start Date------------------------------------------------------*/
    driver.findElement(By.name("startDate")).click();


    Double temp_gbStartDateYear_double = Double.parseDouble(gbStartDateYear);
    int temp_gbStartDateYear_int = temp_gbStartDateYear_double.intValue();
    String gbStartDateYear_string = Integer.toString(temp_gbStartDateYear_int);

    WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
    System.out.println(datepicker_ui.isDisplayed());
    if (datepicker_ui.isDisplayed() == true) {
      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
      new Select(datepicker_ui_year).selectByVisibleText(gbStartDateYear_string);

    }

     date = new Date();

    int current_month = date.getMonth();

    Double temp_gbStartDateMonth_double = Double.parseDouble(gbStartDateMonth);
    int temp_gbStartDateMonth_int = temp_gbStartDateMonth_double.intValue();

    int click = current_month - temp_gbStartDateMonth_int;
    System.out.println("click " + click);
    for (; click >= 0; click = click - 1) {
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();

    }

    Double temp_gbStartDateDate_double = Double.parseDouble(gbStartDateDate);
    int temp_gbStartDateDate_int = temp_gbStartDateDate_double.intValue();
    String gbStartDateDate_string = Integer.toString(temp_gbStartDateDate_int);
    driver.findElement(By.linkText(gbStartDateDate_string)).click(); // enter date
    
    
    
    /*--------------------------End date--------------------------------*/
    
	 driver.findElement(By.name("expDate")).click();
	    Double temp_gbEndDateYear_double = Double.parseDouble(gbEndDateYear);
	    int temp_gbEndDateYear_int = temp_gbEndDateYear_double.intValue();
	    String gbEndDateYear_string = Integer.toString(temp_gbEndDateYear_int);
	    // WebElement datepicker_ui=driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));

	    System.out.println(datepicker_ui.isDisplayed());
	    if (datepicker_ui.isDisplayed() == true) {
	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	      new Select(datepicker_ui_year).selectByVisibleText(gbEndDateYear_string);
	    }

	    Double temp_gbEndDateMonth_double = Double.parseDouble(gbEndDateMonth);
	    int temp_gbEndDateMonth_int = temp_gbEndDateMonth_double.intValue();
	    System.out.println(" gbEndDateMonth " + temp_gbEndDateMonth_int);

	    int click1 = temp_gbEndDateMonth_int - current_month;
	    System.out.println("click " + click1);
	    for (; click1 >= 0; click1 = click1 - 1) {
	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    }

	    Double temp_gbEndDateDate_double = Double.parseDouble(gbEndDateDate);
	    int temp_gbEndDateDate_int = temp_gbEndDateDate_double.intValue();
	    String gbEndDateDate_string = Integer.toString(temp_gbEndDateDate_int);
	    driver.findElement(By.linkText(gbEndDateDate_string)).click(); // enter date
    
	    if (gbIncludeStartDate.equalsIgnoreCase("Yes")) {
	        getObject("gb_include_startdate").click(); 
	      }
	      if (gbIncludeStartDate.equalsIgnoreCase("Yes")) {
	    	  wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("gb_include_enddate")));
	    	  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", getObject("gb_include_enddate"));    
	    	  getObject("gb_include_enddate").click(); 
	    	  }

	 
	    
    /*Select dropdown = new Select(driver.findElement(By.name("startTime")));
	    dropdown.selectByValue("2");*/
   //  Thread.sleep(1000);
	/*---------------------------Select Start Time------------------------------*/      
	 /*  double temp_startTimeselect_double = Double.parseDouble(startTimeselect);
	   int a= (int)temp_startTimeselect_double;
       System.out.println("value of a is"+a);
	  new Select(getObject("gb_start_time")).selectByIndex(a);
       
	  if (!gbDuration.equalsIgnoreCase("")){
        new Select(getObject("gb_duration")).selectByVisibleText(gbDuration);
    }
    */
   /*-----------------------------Pattern Date-----------------------------*/
	   
	      
	      
	      
	      driver.findElement(By.name("patternDate")).click();
		    Double temp_gbPatternDateYear_double = Double.parseDouble(gbEndDateYear);
		    int temp_gbPatternDateYear_int = temp_gbPatternDateYear_double.intValue();
		    String gbPatternDateYear_string = Integer.toString(temp_gbPatternDateYear_int);
		    // WebElement datepicker_ui=driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));

		    System.out.println(datepicker_ui.isDisplayed());
		    if (datepicker_ui.isDisplayed() == true) {
		      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
		      new Select(datepicker_ui_year).selectByVisibleText(gbPatternDateYear_string);
		    }

		    Double temp_gbPatternDateMonth_double = Double.parseDouble(gbEndDateMonth);
		    int temp_gbPatternDateMonth_int = temp_gbPatternDateMonth_double.intValue();
		    System.out.println(" gbEndDateMonth " + temp_gbPatternDateMonth_int);

		    int clickp = temp_gbEndDateMonth_int - current_month;
		    System.out.println("click " + clickp);
		    for (; clickp >= 0; clickp = clickp - 1) {
		      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		    }

		    Double temp_gbPatternDateDate_double = Double.parseDouble(gbPatternDateDate);
		    int temp_gbPatternDateDate_int = temp_gbPatternDateDate_double.intValue();
		    String gbPatternDateDate_string = Integer.toString(temp_gbPatternDateDate_int);
		    System.out.println(gbPatternDateDate_string);
		    driver.findElement(By.linkText(gbPatternDateDate_string)).click(); // enter date
	    
		    
	      
	      
  /*-----------------------------pattern date end -------------------------*/
    
    
    /*-----------------------------Effective Date-----------------------------*/
	      driver.findElement(By.name("effectiveDate")).click();


	      Double temp_gbEffectiveDateYear_double = Double.parseDouble(gbEffectiveDateYear);
	      int temp_gbEffectiveDateYear_int = temp_gbEffectiveDateYear_double.intValue();
	      String gbEffectiveDateYear_string = Integer.toString(temp_gbEffectiveDateYear_int);

	      WebElement datepickerrr_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	      System.out.println(datepicker_ui.isDisplayed());
	      if (datepickerrr_ui.isDisplayed() == true) {
		        WebElement datepickerr_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
		        new Select(datepickerr_ui_year).selectByVisibleText(gbEffectiveDateYear_string);

		      }


	      Date date3 = new Date();

	      int current_month3 = date.getMonth();

	      Double temp_gbEffectiveDateMonth_double = Double.parseDouble(gbEffectiveDateMonth);
	      int temp_gbEffectiveDateMonth_int = temp_gbEffectiveDateMonth_double.intValue();

	      int click3 = current_month - temp_gbEffectiveDateMonth_int;
	      System.out.println("click " + click);
	      for (; click3 >= 0; click3 = click3 - 1) {
	  		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();

	      }
	      Double temp_gbEffectiveDateDate_double = Double.parseDouble(gbEffectiveDateDate);
	      int temp_gbEffectiveDateDate_int = temp_gbEffectiveDateDate_double.intValue();
	      String gbEffectiveDateDate_string = Integer.toString(temp_gbEffectiveDateDate_int);
	      driver.findElement(By.linkText(gbEffectiveDateDate_string)).click(); // enter date
  /*-----------------------------Effective date end -------------------------*/
	      
	 getObject("gb_create_button").click(); // click on submit button
      Thread.sleep(10000);
      
    String gb_id = getObject("gb_popup_id").getText();
    
    APP_LOGS.debug("Governance Body created successfully with GB id "+gb_id);
    Thread.sleep(10000);

    getObject("gb_popup_ok_button").click();
    Thread.sleep(10000);
    
    APP_LOGS.debug("Quick Search the created contract with GB id "+gb_id);
    
    getObject("quick_search_textbox").sendKeys(gb_id);
    Thread.sleep(5000);
    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
    Thread.sleep(5000);
    
    

   APP_LOGS.debug("GB show page open successfully with GB id " + gb_id);
     
  fail = false;
  APP_LOGS.debug("Governance Body open successfully, following parameters have been validated: Contract title -- " + gbTitle+ ", Governance Body Description  -- " + gbDescription+ ", Governance Body Goal -- " + gbGoal+ ", " +
                  		"Governance Body GBType -- " 
                  + gbGBType+", Governance Body Supplier Name-- " + gbSupplier+ ",Governance Body Contract Name -- " + gbContract); 
   
  
   getObject("analytics_link").click();
  
     }
  
  
  

 @AfterMethod
 public void reportDataSetResult() {
   if (skip)
     TestUtil.reportDataSetResult(governance_body_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
   else if (fail) {
     isTestPass = false;
     TestUtil.reportDataSetResult(governance_body_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
   } else
     TestUtil.reportDataSetResult(governance_body_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

   skip = false;
   fail = true;

 }

 @AfterTest
 public void reportTestResult() {
   if (isTestPass)
     TestUtil.reportDataSetResult(governance_body_suite_xls, "Test Cases", TestUtil.getRowNum(governance_body_suite_xls, this.getClass().getSimpleName()), "PASS");
   else
     TestUtil.reportDataSetResult(governance_body_suite_xls, "Test Cases", TestUtil.getRowNum(governance_body_suite_xls, this.getClass().getSimpleName()), "FAIL");

 }

 @DataProvider
 public Object[][] getTestData() {
   return TestUtil.getData(governance_body_suite_xls, this.getClass().getSimpleName());
 }

}
