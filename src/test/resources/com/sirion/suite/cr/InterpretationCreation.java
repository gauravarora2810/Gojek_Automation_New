package test.resources.com.sirion.suite.cr;

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

    if (!TestUtil.isTestCaseRunnable(cr_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(cr_suite_xls, this.getClass().getSimpleName());
  }

  @Test(groups = "ContractCreation", dataProvider = "getTestData")
  public void InterpretationCreate(String ipTitle, String ipAreaofDisagreement, String ipBackground, String ipType, String ipPriority, String ipTimezone,
      String ipTier, String ipSupplierAccess, String ipDependentEntity, String ipQuestion, String ipIncludeInFAQ, String ipRequestedDateMonth,
      String ipRequestedDateDate, String ipRequestedDateYear, String ipPlannedSubmissionDateMonth, String ipPlannedSubmissionDateDate,
      String ipPlannedSubmissionDateYear) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Interpretation Creation from Action");

 // Calling method for opening browser from TestBase.java file
  		openBrowser();

  		// Calling a method for login(at different platform) from TestBase.java file
  		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));

  		WebDriverWait wait=new WebDriverWait(driver, 50);
   		getObject("analytics_link").click();
     
   		getObject("cr_quick_link").click(); // IP Quick Link Clicking
   		Thread.sleep(20000);
   		//wait.until(ExpectedConditions.elementToBeClickable(getObject("cr_id_link")));
   
   		getObject("cr_id_link").click(); 
      
   		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
  	    plus_button("cr_plus_button_link"); // web element for plus button on supplier page
  	    wait.until(ExpectedConditions.elementToBeClickable(getObject("ip_create_link_from_cr")));
      
  	    getObject("ip_create_link_from_cr").click(); // click issue create link 
     
     
     

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
     
    /*String ipIdFromShowPage = getObject("ip_show_id").getText();

    Assert.assertEquals(ipIdFromShowPage, ip_id);

    APP_LOGS.debug("Interpretation show page open successfully with action id " + ip_id);*/
    fail = false; // this executes if assertion passes
    getObject("analytics_link").click();
   

  }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil.getRowNum(cr_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil.getRowNum(cr_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(cr_suite_xls, this.getClass().getSimpleName());
  }

}
