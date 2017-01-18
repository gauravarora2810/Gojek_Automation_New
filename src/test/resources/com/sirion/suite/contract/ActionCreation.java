package test.resources.com.sirion.suite.contract;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.suite.cr.TestSuiteBase;
import test.resources.com.sirion.util.*;

public class ActionCreation extends TestSuiteBase {
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
  public void Actioncreation(String user,String actionTitle, String actionDescription, String actionType, String actionPriority, String actionResponsibility,
      String actionGovernanceBody, String actionDeliveryCountries, String actionTimeZone, String actionCurrencies, String actionSupplierAccess,
      String actionDependentEntity, String actionTier, String actionRequestedOnMonth, String actionRequestedOnDate, String actionRequestedOnYear,
      String actionDueDateMonth, String actionDueDateDate, String actionDueDateYear, String actionFinancialImpact,
      String actionSupName,String actionSourceName,String actionStatus,String actionSource ,String actionFunction, String actionService, String actionRegion, String actionCountry) throws InterruptedException, MalformedURLException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Action Creation");

	WebDriverWait wait=new WebDriverWait(driver, 50);
switch(user){
	
	case "user1": endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername1"),CONFIG.getProperty("endUserPassword"));
	break;
	case "user2": endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername2"),CONFIG.getProperty("endUserPassword"));
	break;	
	case "user3": endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername3"),CONFIG.getProperty("endUserPassword"));
	break;
	case "user4": endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername4"),CONFIG.getProperty("endUserPassword"));
	break;
	case "user5": endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername5"),CONFIG.getProperty("endUserPassword"));
	break;
	
	
	}
    
    getObject("analytics_link").click();
  
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rt-contracts']/a")));
	getObject("contract_quick_link").click(); 
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+actionSourceName+"')]/preceding-sibling::td[1]/a")).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
	
	Thread.sleep(5000);
    plus_button("is_plus_button_link"); 
    wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_create_link_from_is")));
    getObject("ac_create_link_from_is").click();   

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
    
    /*Requested On Date*/
    String RequestedOnMonth = convertDoubleToIntegerInStringForm(actionRequestedOnMonth);
	int RequestedonMonth = Integer.parseInt(RequestedOnMonth);
	String RequestedOnYear = convertDoubleToIntegerInStringForm(actionRequestedOnYear);
	int RequestedonYear = Integer.parseInt(RequestedOnYear);
	String RequestedOnDate = convertDoubleToIntegerInStringForm(actionRequestedOnDate);
	Integer RequestedonDate = Integer.parseInt(RequestedOnDate);
	RequestedOnDate = RequestedonDate.toString();

	DatePicker dp_Action_Requested_On_date = new DatePicker();
	dp_Action_Requested_On_date.expDate = RequestedOnDate;
	dp_Action_Requested_On_date.expMonth = RequestedonMonth;
	dp_Action_Requested_On_date.expYear = RequestedonYear;
	dp_Action_Requested_On_date.pickExpDate("requestedOn");
	
	/*Due Date*/
	String DueDateMonth = convertDoubleToIntegerInStringForm(actionDueDateMonth);
	int DuedateMonth = Integer.parseInt(DueDateMonth);
	String DueDateYear = convertDoubleToIntegerInStringForm(actionDueDateYear);
	int DuedateYear = Integer.parseInt(DueDateYear);
	String DueDateDate = convertDoubleToIntegerInStringForm(actionDueDateDate);
	Integer DuedateDate = Integer.parseInt(DueDateDate);
	DueDateDate = DuedateDate.toString();

	DatePicker dp_Action_Due_Date_date = new DatePicker();
	dp_Action_Due_Date_date.expDate = DueDateDate;
	dp_Action_Due_Date_date.expMonth = DuedateMonth;
	dp_Action_Due_Date_date.expYear = DuedateYear;
	dp_Action_Due_Date_date.pickExpDate("plannedCompletionDate");
	
	
    if (!actionFinancialImpact.equalsIgnoreCase("")) {
      getObject("ac_financial_impact_textbox").sendKeys(actionFinancialImpact);
    }

    getObject("ac_save_button").click();
    
    Thread.sleep(5000);
    String action_id = getObject("ac_popup_id").getText();
    
    APP_LOGS.debug("Action created successfully with Action id " + action_id);

    getObject("ac_popup_ok_button_from_ac").click();

    APP_LOGS.debug("Quick Search the created action with Action id " + action_id);

   getObject("quick_search_textbox").sendKeys(action_id);
   Thread.sleep(5000);
    
    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
    
    String actionIdFromShowPage = getObject("ac_show_id").getText();

    Assert.assertEquals(actionIdFromShowPage, action_id);

    APP_LOGS.debug("Action show page open successfully with Action Id: " + action_id);
        
    String ActionTitleShowPage= getObject("ac_title_show").getText();
    try
    {
    Assert.assertEquals(ActionTitleShowPage, actionTitle);
    APP_LOGS.debug("Action Title on show page is:"+ ActionTitleShowPage);
    }
    catch(Throwable e){
  	  System.out.println("Action Title is -- " +ActionTitleShowPage+ " instead of -- " +actionTitle);
    }
    String ActionDescriptionShowPage= getObject("ac_description_show").getText();
    try
    {
    Assert.assertEquals(ActionDescriptionShowPage, actionDescription);
    APP_LOGS.debug("Action Description on show page is:"+ ActionDescriptionShowPage);
    }
    catch(Throwable e){
  	  System.out.println("Action Desactioniption is--"+ActionDescriptionShowPage+"instead of" + actionDescription );
    }
    String ActionSupplierShowPage= getObject("ac_supplier_show").getText();
    try
    {
    Assert.assertEquals(ActionSupplierShowPage, actionSupName);
    APP_LOGS.debug("Action Supplier Name on show page is:"+ ActionSupplierShowPage);

    }
    catch(Throwable e) {
  	  System.out.println("Action Supplier Name is== "+ActionSupplierShowPage +"instead of" + actionSupName);
    }
    String ActionSourceShowPage= getObject("ac_source_show").getText();
    try {
    Assert.assertEquals(ActionSourceShowPage, actionSource);
    APP_LOGS.debug("Action Source Type on show page is:"+ ActionSourceShowPage);
    	}
    catch(Throwable e) {
  	  System.out.println("Action Source is== "+ActionSourceShowPage +"instead of" + actionSource);
    }
    String ActionSourceNameShowPage= getObject("ac_sourcename_show").getText();
    try {
    Assert.assertEquals(ActionSourceNameShowPage, actionSourceName);
    APP_LOGS.debug("Action Source Name or Title on show page is:"+ ActionSourceNameShowPage);

    }
    catch(Throwable e){
  	  System.out.println("Action Source Name is== "+ActionSourceShowPage +"instead of" + actionSourceName);
    }
    String ActionTypeShowPage= getObject("ac_type_show").getText();
    try {
    Assert.assertEquals(ActionTypeShowPage, actionType);
    APP_LOGS.debug("Action Type on show page is:"+ ActionTypeShowPage);
    	}
    catch(Throwable e){
  	  System.out.println("Action Type is== "+ActionTypeShowPage +"instead of" + actionType);
    }
    String ActionPriorityShowPage= getObject("ac_priority_show").getText();
    try
    {
    Assert.assertEquals(ActionPriorityShowPage, actionPriority);
    APP_LOGS.debug("Action Priority on show page is:"+ ActionPriorityShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Priority is== "+ActionPriorityShowPage +"instead of" + actionPriority);
    }
    String ActionResponsibilityShowPage= getObject("ac_responsibility_show").getText();
    try
    {
  	  Assert.assertEquals(ActionResponsibilityShowPage, actionResponsibility);
      APP_LOGS.debug("Action Responsibility on show page is:"+ ActionPriorityShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Responsibility is== "+ActionResponsibilityShowPage +"instead of" + actionResponsibility);
    }
    String ActionGovernanceBodyShowPage= getObject("ac_govbody_show").getText();
    try
    {
    Assert.assertEquals(ActionGovernanceBodyShowPage, actionGovernanceBody);
    APP_LOGS.debug("Governance Bodies on Action show page is:"+ ActionGovernanceBodyShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Governance Body is== "+ActionGovernanceBodyShowPage +"instead of" + actionGovernanceBody);
    }
    String ActionDeliveryCountriesShowPage= getObject("ac_delcountries_show").getText();
    try
    {
    Assert.assertEquals(ActionDeliveryCountriesShowPage, actionDeliveryCountries);
    APP_LOGS.debug("Delivery Countries on Action show page is:"+ ActionDeliveryCountriesShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Delivery Country is== "+ActionDeliveryCountriesShowPage +"instead of" + actionDeliveryCountries);
    }
    String ActionTimeZoneShowPage= getObject("ac_timezone_show").getText();
    try
    {
    Assert.assertEquals(ActionTimeZoneShowPage, actionTimeZone);
    APP_LOGS.debug("TimeZone at Action show page is:"+ ActionDeliveryCountriesShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action TimeZone is== "+ActionTimeZoneShowPage +"instead of" + actionTimeZone);
    }
    String ActionCurrencyShowPage= getObject("ac_currency_show").getText();
    try
    {
    Assert.assertEquals(ActionCurrencyShowPage, actionCurrencies);
    APP_LOGS.debug("Currency at Action show page is:"+ ActionCurrencyShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Currency is== "+ActionCurrencyShowPage +"instead of" + actionCurrencies);
    }
    String ActionSupplierAccessShowPage= getObject("ac_supplieraccess_show").getText();
    try
    {
    Assert.assertEquals(ActionSupplierAccessShowPage, actionSupplierAccess);
    APP_LOGS.debug("Supplier Access at Action show page is:"+ ActionSupplierAccessShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Supplier Access is== "+ActionSupplierAccessShowPage +"instead of" + actionSupplierAccess);
    }
    String ActionDependityEntityShowPage= getObject("ac_depentity_show").getText();
    try
    {
  	  Assert.assertEquals(ActionDependityEntityShowPage, actionDependentEntity);
      APP_LOGS.debug("Dependent Entity at Action show page is:"+ ActionDependityEntityShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Dependent Entity is== "+ActionDependityEntityShowPage +"instead of" + actionDependentEntity);
    }
    
    String ActionTierShowPage= getObject("ac_tier_show").getText();
    try
    {
    Assert.assertEquals(ActionTierShowPage, actionTier);
    APP_LOGS.debug("Tier at Action show page is:"+ ActionTierShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Tier is== "+ActionTierShowPage +"instead of" + actionTier);
    }
    /*String ActionFunctionsShowPage= getObject("ac_function_show").getText();
    try
    {
    Assert.assertEquals(ActionFunctionsShowPage, actionFunction);
    APP_LOGS.debug("Functions at Action show page are:"+ ActionFunctionsShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Functions are== "+ActionFunctionsShowPage +"instead of" + actionFunction);
    }
    String ActionServicesShowPage= getObject("ac_service_show").getText();
    try
    {
  	  Assert.assertEquals(ActionServicesShowPage, actionService);
      APP_LOGS.debug("Services at Action show page are:"+ ActionServicesShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Services are== "+ActionServicesShowPage +"instead of" + actionService);
    }
    String ActionRegionsShowPage= getObject("ac_region_show").getText();
    try
    {
    Assert.assertEquals(ActionRegionsShowPage, actionRegion);
    APP_LOGS.debug("Regions at Action show page are:"+ ActionRegionsShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Regions are== "+ActionRegionsShowPage +"instead of" + actionRegion);
    }
    String ActionCountriesShowPage= getObject("ac_country_show").getText();
    try
    {
    Assert.assertEquals(ActionCountriesShowPage, actionCountry);
    APP_LOGS.debug("Countries at Action show page are:"+ ActionRegionsShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Countries are== "+ActionCountriesShowPage +"instead of" + actionCountry);
    }
    String ActionFinancialImpactShowPage= getObject("ac_financialImpact_show").getText();
    try
    {
    Assert.assertEquals(ActionFinancialImpactShowPage, actionFinancialImpact);
    APP_LOGS.debug("Financial Impact value at Action show page are:"+ ActionFinancialImpactShowPage);

    }
    catch(Throwable e)
    {
  	  System.out.println("Action Financial Impact is== "+ActionFinancialImpactShowPage +"instead of" + actionFinancialImpact);
    }
*/
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
