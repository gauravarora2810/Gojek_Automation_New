package test.resources.com.sirion.suite.contract;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class ActionCreationExisting extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = false;
  static boolean skip = false;
  static boolean isTestPass = true;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
  }

  @Test(groups = "ActionCreation", dataProvider = "getTestData")
  public void ActionCreation(String actionTitle, String actionDescription, String actionType, String actionPriority, String actionResponsibility,
      String actionGovernanceBody, String actionDeliveryCountries, String actionTimeZone, String actionCurrencies, String actionSupplierAccess,
      String actionDependentEntity, String actionTier, String actionRequestedOnMonth, String actionRequestedOnDate, String actionRequestedOnYear,
      String actionDueDateMonth, String actionDueDateDate, String actionDueDateYear, String actionFinancialImpact,
      String actionSupName,String actionSourceName,String actionStatus,String actionSource ,String actionFunction, String actionService, String actionRegion, String actionCountry) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Action Creation");

	// Calling method for opening browser from TestBase.java file
	openBrowser();

	// Calling a method for login(at different platform) from TestBase.java file
	endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
    
    WebDriverWait wait=new WebDriverWait(driver, 50);
		getObject("analytics_link").click();

		getObject("contract_quick_link").click(); // IP Quick Link Clicking
		//Thread.sleep(20000);
		//wait.until(ExpectedConditions.elementToBeClickable(getObject("is_id_link")));

		getObject("is_id_link").click(); 

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
    plus_button("is_plus_button_link"); // web element for plus button on supplier page
    wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_create_link_from_is")));
    getObject("ac_create_link_from_is").click(); // click issue create link  

    

    
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

    if (!actionCurrencies.equalsIgnoreCase("")) {
      new Select(getObject("ac_currency_dropdown")).selectByVisibleText(actionCurrencies);
    }

    if (actionDependentEntity.equalsIgnoreCase("yes")) {
      getObject("ac_depentity_checkbox").click();
    }

    if (!actionTier.equalsIgnoreCase("")) {
      new Select(getObject("ac_tier_dropdown")).selectByVisibleText(actionTier);
    }

   // Thread.sleep(10000);
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
    
    //Thread.sleep(5000);
    String action_id = getObject("ac_popup_id").getText();
    
    APP_LOGS.debug("Action created successfully with Action id " + action_id);

    getObject("ac_popup_ok_button_from_ac").click();

    APP_LOGS.debug("Quick Search the created action with Action id " + action_id);

   getObject("quick_search_textbox").sendKeys(action_id);
    //getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
    /*String actionIdFromShowPage = getObject("ac_show_id").getText();

    Assert.assertEquals(actionIdFromShowPage, action_id);

    APP_LOGS.debug("Action show page open successfully with action id " + action_id);*/
    
    
    
    
    
    /*String ActionStatusShowPage= getObject("ac_status_show").getText();
    try
    {
  	  
    
    Assert.assertEquals(ActionStatusShowPage, actionStatus, "Action Status name is -- " +ActionStatusShowPage+ " instead of -- " +actionStatus);
    
    }
    catch(Throwable e)
    {
  	  
    System.out.println("Action Status name is -- " +ActionStatusShowPage+ " instead of -- " +actionStatus);
    
    }
    
    String ActionTitleShowPage= getObject("ac_title_show").getText();
    try
    {
    Assert.assertEquals(ActionTitleShowPage, actionTitle, "Action Title is -- " +ActionTitleShowPage+ " instead of -- " +actionTitle);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Title is -- " +ActionTitleShowPage+ " instead of -- " +actionTitle);
    }
    String ActionDescriptionShowPage= getObject("ac_description_show").getText();
    try
    {
    Assert.assertEquals(ActionDescriptionShowPage, actionDescription, "Action Desactioniption is--"+ActionDescriptionShowPage+"instead of" + actionDescription );
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Desactioniption is--"+ActionDescriptionShowPage+"instead of" + actionDescription );
    }
    String ActionSupplierShowPage= getObject("ac_supplier_show").getText();
    try
    {
    Assert.assertEquals(ActionSupplierShowPage, actionSupName, "Action Supplier Name is== "+ActionSupplierShowPage +"instead of" + actionSupName);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Supplier Name is== "+ActionSupplierShowPage +"instead of" + actionSupName);
    }
    String ActionSourceShowPage= getObject("ac_source_show").getText();
    try
    {
    Assert.assertEquals(ActionSourceShowPage, actionSource, "Action Source is== "+ActionSourceShowPage +"instead of" + actionSource);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Source is== "+ActionSourceShowPage +"instead of" + actionSource);
    }
    String ActionSourceNameShowPage= getObject("ac_sourcename_show").getText();
    try
    {
    Assert.assertEquals(ActionSourceNameShowPage, actionSourceName, "Action Source Name is== "+ActionSourceShowPage +"instead of" + actionSourceName);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Source Name is== "+ActionSourceShowPage +"instead of" + actionSourceName);
    }
    String ActionTypeShowPage= getObject("ac_type_show").getText();
    try
    {
    Assert.assertEquals(ActionTypeShowPage, actionType, "Action Type is== "+ActionTypeShowPage +"instead of" + actionType);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Type is== "+ActionTypeShowPage +"instead of" + actionType);
    }
    String ActionPriorityShowPage= getObject("ac_priority_show").getText();
    try
    {
    Assert.assertEquals(ActionPriorityShowPage, actionPriority, "Action Priority is== "+ActionPriorityShowPage +"instead of" + actionPriority);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Priority is== "+ActionPriorityShowPage +"instead of" + actionPriority);
    }
    String ActionResponsibilityShowPage= getObject("ac_responsibility_show").getText();
    try
    {
  	  Assert.assertEquals(ActionResponsibilityShowPage, actionResponsibility, "Action Responsibility is== "+ActionResponsibilityShowPage +"instead of" + actionResponsibility);  
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Responsibility is== "+ActionResponsibilityShowPage +"instead of" + actionResponsibility);
    }
    String ActionGovernanceBodyShowPage= getObject("ac_govbody_show").getText();
    try
    {
    Assert.assertEquals(ActionGovernanceBodyShowPage, actionGovernanceBody, "Action Governance Body is== "+ActionGovernanceBodyShowPage +"instead of" + actionGovernanceBody);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Governance Body is== "+ActionGovernanceBodyShowPage +"instead of" + actionGovernanceBody);
    }
    String ActionDeliveryCountriesShowPage= getObject("ac_delcountries_show").getText();
    try
    {
    Assert.assertEquals(ActionDeliveryCountriesShowPage, actionDeliveryCountries, "Action Delivery Country is== "+ActionDeliveryCountriesShowPage +"instead of" + actionDeliveryCountries);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Delivery Country is== "+ActionDeliveryCountriesShowPage +"instead of" + actionDeliveryCountries);
    }
    String ActionTimeZoneShowPage= getObject("ac_timezone_show").getText();
    try
    {
    Assert.assertEquals(ActionTimeZoneShowPage, actionTimeZone, "Action TimeZone is== "+ActionTimeZoneShowPage +"instead of" + actionTimeZone);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action TimeZone is== "+ActionTimeZoneShowPage +"instead of" + actionTimeZone);
    }
    String ActionCurrencyShowPage= getObject("ac_currency_show").getText();
    try
    {
    Assert.assertEquals(ActionCurrencyShowPage, actionCurrencies, "Action Currency is== "+ActionCurrencyShowPage +"instead of" + actionCurrencies);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Currency is== "+ActionCurrencyShowPage +"instead of" + actionCurrencies);
    }
    String ActionSupplierAccessShowPage= getObject("ac_supplieraccess_show").getText();
    try
    {
    Assert.assertEquals(ActionSupplierAccessShowPage, actionSupplierAccess, "Action Supplier Access is== "+ActionSupplierAccessShowPage +"instead of" + actionSupplierAccess);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Supplier Access is== "+ActionSupplierAccessShowPage +"instead of" + actionSupplierAccess);
    }
    String ActionDependityEntityShowPage= getObject("ac_depentity_show").getText();
    try
    {
  	  Assert.assertEquals(ActionDependityEntityShowPage, actionSupplierAccess, "Action Dependent Entity is== "+ActionSupplierAccessShowPage +"instead of" + actionSupplierAccess);  
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Dependent Entity is== "+ActionSupplierAccessShowPage +"instead of" + actionSupplierAccess);
    }
    
    String ActionTierShowPage= getObject("ac_tier_show").getText();
    try
    {
    Assert.assertEquals(ActionTierShowPage, actionTier, "Action Tier is== "+ActionTierShowPage +"instead of" + actionTier);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Tier is== "+ActionTierShowPage +"instead of" + actionTier);
    }
    String ActionFunctionsShowPage= getObject("ac_function_show").getText();
    try
    {
    Assert.assertEquals(ActionFunctionsShowPage, actionFunction, "Action Functions are== "+ActionFunctionsShowPage +"instead of" + actionFunction);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Functions are== "+ActionFunctionsShowPage +"instead of" + actionFunction);
    }
    String ActionServicesShowPage= getObject("ac_service_show").getText();
    try
    {
  	  Assert.assertEquals(ActionServicesShowPage, actionService, "Action Services are== "+ActionServicesShowPage +"instead of" + actionService);  
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Services are== "+ActionServicesShowPage +"instead of" + actionService);
    }
    String ActionRegionsShowPage= getObject("ac_region_show").getText();
    try
    {
    Assert.assertEquals(ActionRegionsShowPage, actionRegion, "Action Regions are== "+ActionRegionsShowPage +"instead of" + actionRegion);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Regions are== "+ActionRegionsShowPage +"instead of" + actionRegion);
    }
    String ActionCountriesShowPage= getObject("ac_country_show").getText();
    try
    {
    Assert.assertEquals(ActionCountriesShowPage, actionCountry, "Action Countries are== "+ActionCountriesShowPage +"instead of" + actionCountry);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Countries are== "+ActionCountriesShowPage +"instead of" + actionCountry);
    }
    String ActionFinancialImpactShowPage= getObject("ac_financialImpact_show").getText();
    try
    {
    Assert.assertEquals(ActionFinancialImpactShowPage, actionFinancialImpact, "Action Financial Impact is== "+ActionFinancialImpactShowPage +"instead of" + actionFinancialImpact);
    }
    catch(Throwable e)
    {
  	  System.out.println("Action Financial Impact is== "+ActionFinancialImpactShowPage +"instead of" + actionFinancialImpact);
    }*/
    
    
    
    
    
    
    
    fail = false; // this executes if assertion passes

    APP_LOGS.debug("Action open successfully, following parameters have been validated: Action Title-- " + actionTitle+ ", Action Desactioniption -- " + actionDescription+ 
            ", Action Governance Body -- " + actionGovernanceBody+  ", Action Type -- " + actionType+ ", Action Time Zone" +actionTimeZone+ ", " +
            		"Action Priority  -- "   + actionPriority+", Action Responsibiity -- " + actionResponsibility+ ", Action Id-- " + action_id+  
            ", Action Supplier Access -- " + actionSupplierAccess+ ", " +  "Action Tier -- " + actionTier+", Action Depenndent Entity -- " + actionDependentEntity+ 
            ", Action Function -- " + actionFunction+ ", Action Services -- " 
            + actionService+  ", Action Supplier Name--" + actionSupName+ ", Action Status--" +actionStatus+ ", Action Delivery Countries--" +actionDeliveryCountries+
            ", Action Currencies--" +actionCurrencies+", Action Requested on Month--" +actionRequestedOnMonth+ ", Action Requested on Date--" +actionRequestedOnDate+", Action Requested on Year--" +actionRequestedOnYear+
            ", Action Due Date--" +actionDueDateDate+ ", Action Due Date Month--" +actionDueDateMonth+ ", Action Due Date Year--" +actionDueDateYear+ ", Action Financial Impact--" +actionFinancialImpact+ 
            ", Action Source--" +actionSource+ ", Action Source Name--" +actionSourceName+ ", Action Region--" +actionRegion+ ", Action Country--" +actionCountry);
    
    
    getObject("analytics_link").click();
   /// getObject("supplier_quick_link").click();

  }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(contract_suite_xls, this.getClass().getSimpleName());
  }

}
