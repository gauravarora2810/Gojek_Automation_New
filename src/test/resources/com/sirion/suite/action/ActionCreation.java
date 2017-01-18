package test.resources.com.sirion.suite.action;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ActionCreation extends TestSuiteBase implements ITestListener{
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = false;
  static boolean skip = false;
  static boolean isTestPass = true;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(action_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(action_suite_xls, this.getClass().getSimpleName());
  }

  @Test(dataProvider="getTestData")
  public void ActionCreation(String actionTitle, String actionDescription, String actionType, String actionPriority, String actionResponsibility,
      String actionGovernanceBody, String actionDeliveryCountries, String actionTimeZone, String actionCurrencies, String actionSupplierAccess,
      String actionDependentEntity, String actionTier, String actionRequestedOnMonth, String actionRequestedOnDate, String actionRequestedOnYear,
      String actionDueDateMonth, String actionDueDateDate, String actionDueDateYear, String actionFinancialImpact) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Action Creation from Service Level");

 // Calling method for opening browser from TestBase.java file
  		openBrowser();
  	

  		// Calling a method for login(at different platform) from TestBase.java file
  		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
  	
  		
  		getObject("analytics_link").click();
     getObject("action_quick_link").click(); // IP Quick Link Clicking
     Thread.sleep(20000);
     WebDriverWait wait=new WebDriverWait(driver, 50);
     //wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_id_link")));
     getObject("ac_id_link").click(); 
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
 	    plus_button("ac_plus_button_link"); // web element for plus button on supplier page
 	    wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_create_link_from_ac")));
     getObject("ac_create_link_from_ac").click(); // click issue create link 

    
    
    
   
    
    
     if (!actionTitle.equalsIgnoreCase("")) {
         getObject("ac_title_textbox").sendKeys(actionTitle); // name
       }

      if (!actionDescription.equalsIgnoreCase("")) {
         getObject("ac_description_textarea").sendKeys(actionDescription); // title
       }
       if (!actionType.equalsIgnoreCase("")) {
         new Select(getObject("ac_type_dropdown")).selectByVisibleText(actionType);
       }
       if (!actionPriority.equalsIgnoreCase("")) {
         new Select(getObject("ac_priority_dropdown")).selectByVisibleText(actionPriority);
       }
       if (!actionResponsibility.equalsIgnoreCase("")) {
         new Select(getObject("ac_responsibility_dropdown")).selectByVisibleText(actionResponsibility);
       }
       if (!actionGovernanceBody.equalsIgnoreCase("")) {
         new Select(getObject("ac_govbody_multi")).selectByVisibleText(actionGovernanceBody);// governance body
       }
       if (!actionDeliveryCountries.equalsIgnoreCase("")) {
         new Select(getObject("ac_delcountries_multi")).selectByVisibleText(actionDeliveryCountries);
       }

       if (!actionTimeZone.equalsIgnoreCase("")) {
         new Select(getObject("ac_timezone_dropdown")).selectByVisibleText(actionTimeZone);
       }

       if (!actionCurrencies.equalsIgnoreCase("")) {
         new Select(getObject("ac_currency_dropdown")).selectByVisibleText(actionCurrencies);
       }

       if (actionSupplierAccess.equalsIgnoreCase("yes")) {
         getObject("action_supplier_access_checkbox").click();
       }

      

       if (actionDependentEntity.equalsIgnoreCase("yes")) {
         getObject("ac_depentity_checkbox").click();
       }

       if (!actionTier.equalsIgnoreCase("")) {
         new Select(getObject("ac_tier_dropdown")).selectByVisibleText(actionTier);
       }
       Thread.sleep(10000);
       Date date = new Date();

       int current_month = date.getMonth();

       driver.findElement(By.name("requestedOn")).click();
       Double temp_actionRequestedOnYear_double = Double.parseDouble(actionRequestedOnYear);
       int temp_actionRequestedOnYear_int = temp_actionRequestedOnYear_double.intValue();
       String actionRequestedOnYear_string = Integer.toString(temp_actionRequestedOnYear_int);

       WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
       System.out.println(datepicker_ui.isDisplayed());
       if (datepicker_ui.isDisplayed() == true) {
         WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
         new Select(datepicker_ui_year).selectByVisibleText(actionRequestedOnYear_string);
       }

       Double temp_actionRequestedOnMonth_double = Double.parseDouble(actionRequestedOnMonth);
       int temp_actionRequestedOnMonth_int = temp_actionRequestedOnMonth_double.intValue();
       System.out.println(" actionRequestedOnMonth " + temp_actionRequestedOnMonth_int);

       int click2 = current_month - temp_actionRequestedOnMonth_int;
       System.out.println("click " + click2);
       for (; click2 >= 0; click2 = click2 - 1) {
         driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
       }
       Double temp_actionRequestedOnDate_double = Double.parseDouble(actionRequestedOnDate);
       int temp_actionRequestedOnDate_int = temp_actionRequestedOnDate_double.intValue();
       String actionRequestedOnDate_string = Integer.toString(temp_actionRequestedOnDate_int);
       driver.findElement(By.linkText(actionRequestedOnDate_string)).click();

       driver.findElement(By.name("plannedCompletionDate")).click();

       Double temp_actionDueDateYear_double = Double.parseDouble(actionDueDateYear);
       int temp_actionDueDateYear_int = temp_actionDueDateYear_double.intValue();
       String actionDueDateYear_string = Integer.toString(temp_actionDueDateYear_int);

       System.out.println(datepicker_ui.isDisplayed());
       if (datepicker_ui.isDisplayed() == true) {
         WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
         new Select(datepicker_ui_year).selectByVisibleText(actionDueDateYear_string);
       }

       Double temp_actionDueDateMonth_double = Double.parseDouble(actionDueDateMonth);
       int temp_actionDueDateMonth_int = temp_actionDueDateMonth_double.intValue();
       System.out.println(" actionDueDateMonth " + temp_actionDueDateMonth_int);

       int click3 = temp_actionDueDateMonth_int - current_month;
       System.out.println("click " + click3);
       for (; click3 > 0; click3 = click3 - 1) {
         driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
       }
       Double temp_actionDueDateDate_double = Double.parseDouble(actionDueDateDate);
       int temp_actionDueDateDate_int = temp_actionDueDateDate_double.intValue();
       String actionDueDateDate_string = Integer.toString(temp_actionDueDateDate_int);
       driver.findElement(By.linkText(actionDueDateDate_string)).click();

       if (!actionFinancialImpact.equalsIgnoreCase("")) {
         getObject("ac_financial_impact_textbox").sendKeys(actionFinancialImpact);
       }

       getObject("ac_save_button").click();
       
       Thread.sleep(5000);
       String action_id = getObject("ac_popup_id").getText();
       
       APP_LOGS.debug("Action created successfully with Action id " + action_id);

       getObject("ac_popup_ok_button_from_ip").click();

       APP_LOGS.debug("Quick Search the created action with Action id " + action_id);

      getObject("quick_search_textbox").sendKeys(action_id);
       //getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
       getObject("quick_search_textbox").sendKeys(Keys.ENTER);
    	
       

  }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(action_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(action_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(action_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(action_suite_xls, "Test Cases", TestUtil.getRowNum(action_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(action_suite_xls, "Test Cases", TestUtil.getRowNum(action_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(action_suite_xls, this.getClass().getSimpleName());
  }

@Override
public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStart(ITestContext context) {
	// TODO Auto-generated method stub
	
}

@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	
}




}
