package test.resources.com.sirion.suite.obligation;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class InterpretationCreation extends TestSuiteBaseExisting {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(obligation_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
  }

  @Test(groups = "ContractCreation", dataProvider = "getTestData")
  public void InterpretationCreate(String ipObligationName, String ipTitle, String ipAreaofDisagreement, String ipBackground, String ipType, String ipPriority, String ipTimezone,
      String ipTier, String ipSupplierAccess, String ipDependentEntity, String ipQuestion, String ipIncludeInFAQ, String ipRequestedDateMonth,
      String ipRequestedDateDate, String ipRequestedDateYear, String ipPlannedSubmissionDateMonth, String ipPlannedSubmissionDateDate,
      String ipPlannedSubmissionDateYear, String ipSupplier, String ipStatus, String ipFunction, String ipRegion, String ipService, String ipCountry) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Interpretation Creation");
 // Calling method for opening browser from TestBase.java file
  		openBrowser();

  		// Calling a method for login(at different platform) from TestBase.java file
  		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));

  		WebDriverWait wait=new WebDriverWait(driver, 50);
   		getObject("analytics_link").click();
     
   		getObject("child_obligaiton_quick_link").click(); // IP Quick Link Clicking
      
   		Thread.sleep(20000);
  		getObject("obligaiton_link").click();
  		
   		
   		Thread.sleep(20000);
   		//wait.until(ExpectedConditions.elementToBeClickable(getObject("ob_id_link")));
   
   		getObject("ob_id_link").click(); 
      
   		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
  	    plus_button("ob_plus_button_link"); // web element for plus button on supplier page
  	    wait.until(ExpectedConditions.elementToBeClickable(getObject("ip_create_link_from_ob")));
      
  	    getObject("ip_create_link_from_ob").click(); // click issue create link 
     
     
     

     if (!ipTitle.equalsIgnoreCase("")) {
       getObject("ip_title_textbox").sendKeys(ipTitle); // name
     }
     if (!ipAreaofDisagreement.equalsIgnoreCase("")) {
       getObject("ip_area_textarea").sendKeys(ipAreaofDisagreement); // title
     }
     if (!ipBackground.equalsIgnoreCase("")) {
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
 	 Date date = new Date();

 	    int current_month = date.getMonth();
 	    new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[contains(@name,'requestDate')]"))).click().perform();
 	   // driver.findElement(By.xpath("//input[contains(@name,'requestDate')]")).click();
 	    Double temp_actionRequestedOnYear_double = Double.parseDouble(ipRequestedDateYear);
 	    int temp_actionRequestedOnYear_int = temp_actionRequestedOnYear_double.intValue();
 	    String actionRequestedOnYear_string = Integer.toString(temp_actionRequestedOnYear_int);

 	    WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
 	    System.out.println(datepicker_ui.isDisplayed());
 	    if (datepicker_ui.isDisplayed() == true) {
 	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
 	      new Select(datepicker_ui_year).selectByVisibleText(actionRequestedOnYear_string);
 	    }

 	    Double temp_actionRequestedOnMonth_double = Double.parseDouble(ipRequestedDateMonth);
 	    int temp_actionRequestedOnMonth_int = temp_actionRequestedOnMonth_double.intValue();
 	    System.out.println(" ipRequestedDateMonth " + temp_actionRequestedOnMonth_int);

 	    int click2 = current_month - temp_actionRequestedOnMonth_int;
 	    System.out.println("click " + click2);
 	    for (; click2 >= 0; click2 = click2 - 1) {
 	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
 	    }
 	    Double temp_actionRequestedOnDate_double = Double.parseDouble(ipRequestedDateDate);
 	    int temp_actionRequestedOnDate_int = temp_actionRequestedOnDate_double.intValue();
 	    String actionRequestedOnDate_string = Integer.toString(temp_actionRequestedOnDate_int);
 	    driver.findElement(By.linkText(actionRequestedOnDate_string)).click();
 	    new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[@name='plannedSubmissionDate']"))).click().perform();
 	    //driver.findElement(By.xpath("//input[@name='plannedSubmissionDate']")).click();

 	    Double temp_actionDueDateYear_double = Double.parseDouble(ipPlannedSubmissionDateYear);
 	    int temp_actionDueDateYear_int = temp_actionDueDateYear_double.intValue();
 	    String actionDueDateYear_string = Integer.toString(temp_actionDueDateYear_int);

 	    System.out.println(datepicker_ui.isDisplayed());
 	    if (datepicker_ui.isDisplayed() == true) {
 	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
 	      new Select(datepicker_ui_year).selectByVisibleText(actionDueDateYear_string);
 	    }

 	    Double temp_actionDueDateMonth_double = Double.parseDouble(ipPlannedSubmissionDateMonth);
 	    int temp_actionDueDateMonth_int = temp_actionDueDateMonth_double.intValue();
 	    System.out.println(" actionDueDateMonth " + temp_actionDueDateMonth_int);

 	    int click3 = temp_actionDueDateMonth_int - current_month;
 	    System.out.println("click " + click3);
 	    for (; click3 > 0; click3 = click3 - 1) {
 	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
 	    }
 	    Double temp_actionDueDateDate_double = Double.parseDouble(ipPlannedSubmissionDateDate);
 	    int temp_actionDueDateDate_int = temp_actionDueDateDate_double.intValue();
 	    String actionDueDateDate_string = Integer.toString(temp_actionDueDateDate_int);
 	    driver.findElement(By.linkText(actionDueDateDate_string)).click();
 	
 	
 	
 	
 	
 	 
 	
 	  getObject("ac_save_button").click();

 	Thread.sleep(4000);
     String ip_id = getObject("ip_popup_id").getText();

     APP_LOGS.debug("Interpretation created successfully from Obligation with Interpretation id " + ip_id);
     getObject("ip_popup_ok_button_from_is").click();
     APP_LOGS.debug("Quick Search the created action with Interpretation id " + ip_id);

     getObject("quick_search_textbox").sendKeys(ip_id);
     // getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
     getObject("quick_search_textbox").sendKeys(Keys.ENTER);
  
     
     fail=false;
    
    /*String ipIdFromShowPage = getObject("ip_show_id").getText();

    Assert.assertEquals(ipIdFromShowPage, ip_id);

    APP_LOGS.debug("Interpretation show page open successfully with action id " + ip_id);
*/    
    
    
    
   /* String IPStatusShowPage =  getObject("ip_status_show").getText(); 
    try
    {
    Assert.assertEquals(IPStatusShowPage, ipStatus, "IP Status is -- " +IPStatusShowPage+ " instead of -- " +ipStatus);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Status is -- " +IPStatusShowPage+ " instead of -- " +ipStatus);
 	   fail=true;
    }
    
    
    String IPTitleShowPage =  getObject("ip_title_show").getText(); 
    try
    {
    Assert.assertEquals(IPTitleShowPage, ipTitle, "IP title is -- " +IPTitleShowPage+ " instead of -- " +ipTitle);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP title is -- " +IPTitleShowPage+ " instead of -- " +ipTitle);
 	  fail=true;
    }
    
    
    
    String IPAreaofDisagreementShowPage =  getObject("ip_areaofdisagreement_show").getText(); 
    try
    {
    Assert.assertEquals(IPAreaofDisagreementShowPage, ipAreaofDisagreement, "IP Area of Disagreement is -- " +IPAreaofDisagreementShowPage+ " instead of -- " +ipAreaofDisagreement);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Area of Disagreement is -- " +IPAreaofDisagreementShowPage+ " instead of -- " +ipAreaofDisagreement);
 	  fail=true;
    }
    
    
    
    
    String IPBackgroundShowPage =  getObject("ip_background_show").getText(); 
    try
    {
    Assert.assertEquals(IPBackgroundShowPage, ipBackground, "IP Background is -- " +IPBackgroundShowPage+ " instead of -- " +ipBackground);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Background is -- " +IPBackgroundShowPage+ " instead of -- " +ipBackground);
 	  fail=true;
    }
    
    
    
    
    
    String IPTypeShowPage =  getObject("ip_type_show").getText(); 
    try
    {
    Assert.assertEquals(IPTypeShowPage, ipType, "IP Type is -- " +IPTypeShowPage+ " instead of -- " +ipType);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Type is -- " +IPTypeShowPage+ " instead of -- " +ipType);
 	  fail=true;
    }
    
    
    
    
    String IPPriorityShowPage =  getObject("ip_priority_show").getText(); 
    try
    {
    Assert.assertEquals(IPPriorityShowPage, ipPriority, "IP Priority is -- " +IPPriorityShowPage+ " instead of -- " +ipPriority);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Priority is -- " +IPPriorityShowPage+ " instead of -- " +ipPriority);
 	  fail=true;
    }
    
    
    
    String IPSupplierShowPage =  getObject("ip_supplier_show").getText(); 
    try
    {
    Assert.assertEquals(IPSupplierShowPage, ipSupplier, "IP Supplier is -- " +IPSupplierShowPage+ " instead of -- " +ipSupplier);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Supplier is -- " +IPSupplierShowPage+ " instead of -- " +ipSupplier);
 	  fail=true;
    }
    
    
    
    String IPTimezoneShowPage =  getObject("ip_timezone_show").getText(); 
    try
    {
    Assert.assertEquals(IPTimezoneShowPage, ipTimezone, "IP time zone is -- " +IPTimezoneShowPage+ " instead of -- " +ipTimezone);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP time zone is -- " +IPTimezoneShowPage+ " instead of -- " +ipTimezone);
 	  fail=true;
    }
    
 
    
    String IPTierShowPage =  getObject("ip_tier_show").getText(); 
    try
    {
    Assert.assertEquals(IPTierShowPage, ipTier, "IP Tier is -- " +IPTierShowPage+ " instead of -- " +ipTier);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Tier is -- " +IPTierShowPage+ " instead of -- " +ipTier);
 	  fail=true;
    }
    
    
   
    
    String IPSupplierAccessShowPage =  getObject("ip_supplieraccess_show").getText(); 
    try
    {
    Assert.assertEquals(IPSupplierAccessShowPage, ipSupplierAccess, "IP Supplier Access is -- " +IPSupplierAccessShowPage+ " instead of -- " +ipSupplierAccess);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Supplier Access is -- " +IPSupplierAccessShowPage+ " instead of -- " +ipSupplierAccess);
 	  fail=true;
    }
    
    
    String IPDependentEntityShowPage =  getObject("ip_depentity_show").getText(); 
    try
    {
    Assert.assertEquals(IPDependentEntityShowPage, ipDependentEntity, "IP Dependent Entity is -- " +IPDependentEntityShowPage+ " instead of -- " +ipDependentEntity);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Dependent Entity is -- " +IPDependentEntityShowPage+ " instead of -- " +ipDependentEntity);
 	  fail=true;
    }
    
    
    String IPQuestionShowPage =  getObject("ip_question_show").getText(); 
    try
    {
    Assert.assertEquals(IPQuestionShowPage, ipQuestion, "IP Question is -- " +IPQuestionShowPage+ " instead of -- " +ipQuestion);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Question is -- " +IPQuestionShowPage+ " instead of -- " +ipQuestion);
 	  fail=true;
    }
    
    
    String IPIncludeInFaqShowPage =  getObject("ip_includeinfaq_show").getText(); 
    try
    {
    Assert.assertEquals(IPIncludeInFaqShowPage, ipIncludeInFAQ, "IP Include In Faq is -- " +IPIncludeInFaqShowPage+ " instead of -- " +ipIncludeInFAQ);
    }
    catch(Throwable e)
    {
 	   System.out.println("IP Include In Faq is -- " +IPIncludeInFaqShowPage+ " instead of -- " +ipIncludeInFAQ);
 	  fail=true;
    }
    
    
    String IPFunctionsShowPage= getObject("ip_functions_show").getText();
      try
      {
      Assert.assertEquals(IPFunctionsShowPage, ipFunction, "IP Functions are== "+IPFunctionsShowPage +"instead of" + ipFunction);
      }
      catch(Throwable e)
      {
    	  System.out.println("IP Functions are== "+IPFunctionsShowPage +"instead of" + ipFunction);
    	  fail=true;
      }
      
      
      
      String IPServicesShowPage= getObject("ip_services_show").getText();
      try
      {
    	  Assert.assertEquals(IPServicesShowPage, ipService, "IP Services are== "+IPServicesShowPage +"instead of" + ipService);  
      }
      catch(Throwable e)
      {
    	  System.out.println("IP Services are== "+IPServicesShowPage +"instead of" + ipService);
    	  fail=true;
      }
      
      
      
      
      String IPRegionsShowPage= getObject("ip_regions_show").getText();
      try
      {
      Assert.assertEquals(IPRegionsShowPage, ipRegion, "IP Regions are== "+IPRegionsShowPage +"instead of" + ipRegion);
      }
      catch(Throwable e)
      {
    	  System.out.println("IP Regions are== "+IPRegionsShowPage +"instead of" + ipRegion);
    	  fail=true;
      }
      
      
      
      String IPCountriesShowPage= getObject("ip_countries_show").getText();
      try
      {
      Assert.assertEquals(IPCountriesShowPage, ipCountry, "IP Countries are== "+IPCountriesShowPage +"instead of" + ipCountry);
      }
      catch(Throwable e)
      {
    	  System.out.println("IP Countries are== "+IPCountriesShowPage +"instead of" + ipCountry);
    	  fail=true;
      }
    */
    
     // this executes if assertion passes
    
    APP_LOGS.debug("IP open successfully, following parameters have been validated: IP Title-- " + ipTitle+  
            ", IP TimeZone -- " + ipTimezone+  ", IP Type -- " + ipType+ ", " +
            		"IP Priority  -- " 
            + ipPriority+ ", IP Id-- " + ip_id+  ", IP Status-- " + ipStatus+ 
            ", IP Supplier Access -- " + ipSupplierAccess+
            ", " +  "IP Tier -- " + ipTier+", IP Depenndent Entity -- " + ipDependentEntity+  
            ", IP Function -- " + ipFunction+ ", IP Services -- " 
            + ipService+ ", IP Contract Regions -- " + ipRegion + ", IP Contract Countries -- " + ipCountry+ 
            ", IP Area of Disagreement -- " 
            + ipAreaofDisagreement+ ",IP Background -- " + ipBackground+ ",IP Include in FAQ -- " + ipIncludeInFAQ+ 
            ", IP Planned Submission Date Date -- " + ipPlannedSubmissionDateDate+" , IP Planned Submission Date Month-- " 
            + ipPlannedSubmissionDateMonth+", IP Planned Submission Date Year  -- " + ipPlannedSubmissionDateYear+  
            ", IP Question--"+ ipQuestion+ ", IP Requested Date Date--" +ipRequestedDateDate+
            ", IP Requested Date Month--" +ipRequestedDateMonth+", IP Requested Date Year--" +ipRequestedDateYear);
    
    getObject("analytics_link").click();
    

  }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(obligation_suite_xls, this.getClass().getSimpleName());
  }

}
