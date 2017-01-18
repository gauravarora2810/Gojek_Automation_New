package test.resources.com.sirion.suite.contract;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ContractSOWCloneExisting extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  static boolean fail = false;
  static boolean skip = false;
  static boolean isTestPass = true;

  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    
    runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
  }

  @SuppressWarnings("deprecation")
@Test(groups = "ContractClone", dataProvider = "getTestData")
  public void ContractCloning(String Contract, String contractName, String contractTitle, String contractAgreement, 
		  String contractBrief, String contractTimeZone, String contractContractingEntity, String contractGovernanceBody, 
		  String contractDeliveryCountries, String contractTier, String contractSupplierAccess, String contractEffectiveYear, 
		  String contractEffectiveMonth, String contractEffectiveDate, String contractExpirationYear, 
		  String contractExpirationMonth, String contractExpirationDate, String contractRCS, String contractLRegions, 
		  String contractLCountries, String contractCurrencies, String contractReportingCurrency, String contractConversionType, 
		  String contractCurrencyConversionMatrix, String contractCurrencyConversionMatrixFromDate, 
		  String contractCurrencyConversionMatrixFromMonth, String contractCurrencyConversionMatrixFromYear, 
		  String contractCurrencyConversionMatrixToDate, String contractCurrencyConversionMatrixToMonth, 
		  String contractCurrencyConversionMatrixToYear, String additionalACV, String additionalTCV, String additionalFACV, 
		  String contractDocumentPath, String viewerCheckbox, String searchCheckbox, String downloadCheckbox, 
		  String financialCheckbox, String legalCheckbox, String contractSupName, String contractDocType,String contractFunction,
		  String contractService, String contractRegion, String contractCountry, String contractTCV, String contractAddTCV, 
		  String contractACV, String contractAddACV, String contractFACV, String contractAddFACV) throws InterruptedException 
		  {
    
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug(" Executing Test Case of Contract Cloning");

    openBrowser();
	driver.manage().window().maximize();
	
	endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));

	getObject("contract_quick_link").click();
	
	new Select(driver.findElement(By.xpath(".//*[@id='cr_length']/label/select"))).selectByIndex(3);
	
	driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+Contract+"')]/preceding-sibling::td[1]/a")).click();
    
    Thread.sleep(10000);
    
    getObject("co_clone_button").click();

    if (!contractName.equalsIgnoreCase("")) {
    	getObject("co_name_textbox").clear();
      getObject("co_name_textbox").sendKeys(contractName); // name
    }

      if (!contractTitle.equalsIgnoreCase("")) {
      getObject("co_title_textbox").clear();
      getObject("co_title_textbox").sendKeys(contractTitle); // title
    }

    if (!contractAgreement.equalsIgnoreCase("")) {
    	getObject("co_agreement_textbox").clear();
      getObject("co_agreement_textbox").sendKeys(contractAgreement); // title
    }

    if (!contractBrief.equalsIgnoreCase("")) {
    	getObject("co_brief_textarea").clear();
      getObject("co_brief_textarea").sendKeys(contractBrief); // brief
    }

    getObject("extra_space_clone_co").click();
    if (!contractTimeZone.equalsIgnoreCase("")) {
      new Select(getObject("co_timezone_dropdown")).selectByVisibleText(contractTimeZone); // timezone
    }

    if (!contractContractingEntity.equalsIgnoreCase("")) {
      new Select(getObject("co_contracting_dropdown")).selectByVisibleText(contractContractingEntity);
    }

    if (!contractGovernanceBody.equalsIgnoreCase("")) {
      new Select(getObject("co_govbody_multi")).selectByVisibleText(contractGovernanceBody); // governance body
    }

    if (!contractDeliveryCountries.equalsIgnoreCase("")) {
      new Select(getObject("co_delcountry_multi")).selectByVisibleText(contractDeliveryCountries);
    }
    
    if (contractSupplierAccess.equalsIgnoreCase("yes")) {
      getObject("co_supplier_access_checkbox").click(); 
    }

    if (!contractTier.equalsIgnoreCase("")) {
      new Select(getObject("co_tier_dropdown")).selectByVisibleText(contractTier);
    }
    
    boolean contractrcs_tag=false;
    try{
			if(getObject("co_local_rcs_dropdown") != null){
			contractrcs_tag = getObject("co_local_rcs_dropdown").isEnabled();
    }
    }
    catch(Exception e)
    {
        contractrcs_tag=false;
    }

    if(!contractRCS.equalsIgnoreCase("")  &&  contractrcs_tag)
    {    
        new Select(getObject("co_local_rcs_dropdown")).selectByVisibleText(contractRCS);
    }
    
    boolean contractLregions_tag=false;
    try{
			if(getObject("co_local_region_multi") != null){
			contractLregions_tag = getObject("co_local_region_multi").isEnabled();
    }
    }
    catch(Exception e)
    {
        contractLregions_tag=false;
    }

    if(!contractLRegions.equalsIgnoreCase("")  &&  contractLregions_tag)
    {    
        new Select(getObject("co_local_region_multi")).deselectAll();
		  new Select(getObject("co_local_region_multi")).selectByVisibleText(contractLRegions);
    }
    
    boolean contractLcountries_tag=false;
    try{
			if(getObject("co_local_countries_multi") != null){
			contractLcountries_tag = getObject("co_local_countries_multi").isEnabled();
    }
    }
    catch(Exception e)
    {
        contractLcountries_tag=false;
    }

    if(!contractLCountries.equalsIgnoreCase("")  &&  contractLcountries_tag)
    {    
        new Select(getObject("co_local_countries_multi")).deselectAll();
		  new Select(getObject("co_local_countries_multi")).selectByVisibleText(contractLCountries);
    }


    if (!contractCurrencies.equalsIgnoreCase("")) {
      new Select(getObject("co_currency_multi")).selectByVisibleText(contractCurrencies);
    }
    
    if (!contractReportingCurrency.equalsIgnoreCase("")) {
      new Select(getObject("co_reportingcurrency_dropdown")).selectByVisibleText(contractReportingCurrency);
    }

    if (!contractConversionType.equalsIgnoreCase("")) {
      new Select(getObject("co_conversiontype_dropdown")).selectByVisibleText(contractConversionType);
    }

    driver.findElement(By.name("rateCardFromDate")).click();

    Double temp_contractCurrencyConversionMatrixFromYear_double = Double.parseDouble(contractCurrencyConversionMatrixFromYear);
    int temp_contractCurrencyConversionMatrixFromYear_int = temp_contractCurrencyConversionMatrixFromYear_double.intValue();
    String contractCurrencyConversionMatrixFromYear_string = Integer.toString(temp_contractCurrencyConversionMatrixFromYear_int);

    WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
    System.out.println(datepicker_ui.isDisplayed());
    if (datepicker_ui.isDisplayed() == true) {
      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
      new Select(datepicker_ui_year).selectByVisibleText(contractCurrencyConversionMatrixFromYear_string);
          }

    Double temp_contractCurrencyConversionMatrixFromMonth_double = Double.parseDouble(contractCurrencyConversionMatrixFromMonth);
    int temp_contractCurrencyConversionMatrixFromMonth_int = temp_contractCurrencyConversionMatrixFromMonth_double.intValue();
    System.out.println(" contractCurrencyConversionMatrixFromMonth " + temp_contractCurrencyConversionMatrixFromMonth_int);
    Date date = new Date();
    int current_month = date.getMonth();
    int click2 = current_month - temp_contractCurrencyConversionMatrixFromMonth_int;
    System.out.println("click " + click2);
    for (; click2 >= 0; click2 = click2 - 1) {
      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
    }
    Double temp_contractCurrencyConversionMatrixFromDate_double = Double.parseDouble(contractCurrencyConversionMatrixFromDate);
    int temp_contractCurrencyConversionMatrixFromDate_int = temp_contractCurrencyConversionMatrixFromDate_double.intValue();
    String contractCurrencyConversionMatrixFromDate_string = Integer.toString(temp_contractCurrencyConversionMatrixFromDate_int);
    driver.findElement(By.linkText(contractCurrencyConversionMatrixFromDate_string)).click(); // enter date

    driver.findElement(By.name("rateCardToDate")).click();
    Double temp_contractCurrencyConversionMatrixToYear_double = Double.parseDouble(contractCurrencyConversionMatrixToYear);
    int temp_contractCurrencyConversionMatrixToYear_int = temp_contractCurrencyConversionMatrixToYear_double.intValue();
    String contractCurrencyConversionMatrixToYear_string = Integer.toString(temp_contractCurrencyConversionMatrixToYear_int);

    System.out.println(datepicker_ui.isDisplayed());
    if (datepicker_ui.isDisplayed() == true) {
      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
      new Select(datepicker_ui_year).selectByVisibleText(contractCurrencyConversionMatrixToYear_string);
    }

    Double temp_contractCurrencyConversionMatrixToMonth_double = Double.parseDouble(contractCurrencyConversionMatrixToMonth);
    int temp_contractCurrencyConversionMatrixToMonth_int = temp_contractCurrencyConversionMatrixToMonth_double.intValue();
    System.out.println(" contractCurrencyConversionMatrixToMonth " + temp_contractCurrencyConversionMatrixToMonth_int);

    int click3 = temp_contractCurrencyConversionMatrixToMonth_int - current_month;
    System.out.println("click " + click3);
    for (; click3 >= 0; click3 = click3 - 1) {
      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
    }
    Double temp_contractCurrencyConversionMatrixToDate_double = Double.parseDouble(contractCurrencyConversionMatrixToDate);
    int temp_contractCurrencyConversionMatrixToDate_int = temp_contractCurrencyConversionMatrixToDate_double.intValue();
    String contractCurrencyConversionMatrixToDate_string = Integer.toString(temp_contractCurrencyConversionMatrixToDate_int);

    driver.findElement(By.linkText(contractCurrencyConversionMatrixToDate_string)).click(); // enter date

    if (!contractCurrencyConversionMatrix.equalsIgnoreCase("")) {
      new Select(getObject("co_currencyconversion_dropdown")).selectByVisibleText(contractCurrencyConversionMatrix);
    }
    if (!contractTitle.equalsIgnoreCase("")) {
    getObject("co_title_textbox").sendKeys(contractTitle); // title
    }
    if(!additionalTCV.equalsIgnoreCase("")){
    getObject("co_tcv_textbox").sendKeys(additionalTCV);
    }
    if(!additionalACV.equalsIgnoreCase("")){
    getObject("co_acv_textbox").sendKeys(additionalACV);
    }
    if (!additionalFACV.equalsIgnoreCase("")){
    getObject("co_facv_textbox").sendKeys(additionalFACV);
    }
    getObject("co_doc_upload_tab_clone_link").click();
    if (!contractDocumentPath.equalsIgnoreCase("")){
    getObject("co_doc_upload_browse_button").sendKeys(contractDocumentPath);
    }
    if(viewerCheckbox.equalsIgnoreCase("yes")){
    getObject("co_doc_upload_viewer_checkbox").click();
    }
    if(searchCheckbox.equalsIgnoreCase("yes")){
    getObject("co_doc_upload_search_checkbox").click();
    }
    if(downloadCheckbox.equalsIgnoreCase("yes")){
    getObject("co_doc_upload_download_checkbox").click();
    }
    if(financialCheckbox.equalsIgnoreCase("yes")){
    getObject("co_doc_upload_financial_checkbox").click();
    }
    if(legalCheckbox.equalsIgnoreCase("yes")){
    getObject("co_doc_upload_legal_checkbox").click();
    }
    
    getObject("co_general_tab_clone_link").click();
    getObject("co_clone_save_button").click();
    Thread.sleep(45000);
    String contract_id = getObject("co_popup_id").getText();
    APP_LOGS.debug(" Contract created successfully with Contract id "+contract_id);
    getObject("co_popup_ok_button").click();
    Thread.sleep(5000);
    
    APP_LOGS.debug(" Quick Search the created contract with Contract id "+contract_id);
    
    getObject("quick_search_textbox").sendKeys(contract_id);
    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
    String ContractIdFromShowPage = getObject("co_show_id").getText();
    Assert.assertEquals(ContractIdFromShowPage, contract_id);
    APP_LOGS.debug("Contract show page open successfully with contract id " + contract_id);
    fail = false;
    
    String ContractNameShowPage = getObject("co_name_show").getText();
    try
    {
 	   System.out.println(ContractNameShowPage);
 	   System.out.println(contractName);
    Assert.assertEquals(ContractNameShowPage, contractName, "Contract name is -- " +ContractNameShowPage+ " instead of -- " +contractName);
    }
    catch(Throwable e)
    {
 	   
    }
    String ContractTitleShowPage =  getObject("co_title_show").getText(); 
    try
    {
    Assert.assertEquals(ContractTitleShowPage, contractTitle, "Contract title is -- " +ContractTitleShowPage+ " instead of -- " +contractTitle);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract title is -- " +ContractTitleShowPage+ " instead of -- " +contractTitle);
    }
    String ContractSupplierShowPage = getObject("co_sup_show").getText();
    try
    {
 	   Assert.assertEquals(ContractSupplierShowPage, contractSupName, "Contract supplier is -- " +ContractSupplierShowPage+ " instead of -- " +contractSupName);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract supplier is -- " +ContractSupplierShowPage+ " instead of -- " +contractSupName);
    }
    String ContractAggNoShowPage = getObject("co_agreement_number_show").getText();
    try
    {
    Assert.assertEquals(ContractAggNoShowPage, contractAgreement, "Contract aggregate number is -- " +ContractAggNoShowPage+ " instead of -- " +contractAgreement);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract aggregate number is -- " +ContractAggNoShowPage+ " instead of -- " +contractAgreement);
    }
    String ContractBriefShowPage = getObject("co_brief_show").getText();
    try
    {
    Assert.assertEquals(ContractBriefShowPage, contractBrief, "Contract brief is -- " +ContractBriefShowPage+ " instead of -- " +contractBrief);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract brief is -- " +ContractBriefShowPage+ " instead of -- " +contractBrief);
    }
    String ContractTimezoneShowPage =getObject("co_timezone_show").getText();
    try
    {
    Assert.assertEquals(ContractTimezoneShowPage, contractTimeZone, "Contract timezone is -- " +ContractTimezoneShowPage+ " instead of -- " +contractTimeZone);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract timezone is -- " +ContractTimezoneShowPage+ " instead of -- " +contractTimeZone);
    }
    String ContractDocTypeShowPage = getObject("co_doc_type_show").getText();
    try
    {
 	   Assert.assertEquals(ContractDocTypeShowPage, contractDocType, "Contract document type is -- " +ContractDocTypeShowPage+ " instead of -- " +contractDocType);   
    }
    catch(Throwable e)
    {
 	   System.out.println( "Contract document type is -- " +ContractDocTypeShowPage+ " instead of -- " +contractDocType);
    }
    String ContractContractEntityShowPage =getObject("co_contracting_show").getText();
    try
    {
    Assert.assertEquals(ContractContractEntityShowPage, contractContractingEntity, "Contract contract entity is -- " +ContractContractEntityShowPage+ " instead of -- " +contractContractingEntity);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract contract entity is -- " +ContractContractEntityShowPage+ " instead of -- " +contractContractingEntity);
    }
    String ContractGovBodyShowPage = getObject("co_govbody_show").getText();
    try
    {
    Assert.assertEquals(ContractGovBodyShowPage, contractGovernanceBody, "Contract governance body is -- " +ContractGovBodyShowPage+ " instead of -- " +contractGovernanceBody);
    }
    catch(Throwable e)
    {
 	  System.out.println("Contract governance body is -- " +ContractGovBodyShowPage+ " instead of -- " +contractGovernanceBody);
    }
    String ContractDelCountryShowPage =getObject("co_delcountry_show").getText();
    try
    {
    Assert.assertEquals(ContractDelCountryShowPage, contractDeliveryCountries, "Contract delivery country is -- " +ContractDelCountryShowPage+ " instead of -- " +contractDeliveryCountries);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract delivery country is -- " +ContractDelCountryShowPage+ " instead of -- " +contractDeliveryCountries);
    }
    String ContractTierShowPage = getObject("co_tier_show").getText();
    try
    {
 	   Assert.assertEquals(ContractTierShowPage, contractTier, "Contract tier is -- " +ContractTierShowPage+ " instead of -- " +contractTier);   
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract tier is -- " +ContractTierShowPage+ " instead of -- " +contractTier);
    }
    String ContractFunctionShowPage = getObject("co_function_show").getText();
    try
    {
    Assert.assertEquals(ContractFunctionShowPage, contractFunction, "Contract function is -- " +ContractFunctionShowPage+ " instead of -- " +contractFunction);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract function is -- " +ContractFunctionShowPage+ " instead of -- " +contractFunction);
    }
    String ContractServiceShowPage =getObject("co_service_show").getText();
    try
    {
    Assert.assertEquals(ContractServiceShowPage, contractService, "Contract service is -- " +ContractServiceShowPage+ " instead of -- " +contractService);
    }
    catch(Throwable e )
    {
 	   System.out.println("Contract service is -- " +ContractServiceShowPage+ " instead of -- " +contractService);
    }
String contractRegionShowPage = getObject("co_local_region_show_page").getText();
    
    try
    {
    Assert.assertEquals(contractRegionShowPage, contractLRegions, "Contract Management Country is -- " +contractLRegions+ " instead of -- " +contractRegionShowPage);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract Local Country is -- " +contractRegionShowPage+ " instead of -- " +contractLRegions);
    }
    
    String ContractLCountryShowPage =getObject("co_l_countries_show").getText();
    
    try
    {
    Assert.assertEquals(ContractLCountryShowPage, contractLCountries, "Contract Local Country is -- " +ContractLCountryShowPage+ " instead of -- " +contractLCountries);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract Local Country is -- " +ContractLCountryShowPage+ " instead of -- " +contractLCountries);
    }

    String ContractCurrencyShowPage = getObject("co_currency_show").getText();
    try
    {
    Assert.assertEquals(ContractCurrencyShowPage, contractCurrencies, "Contract currency is -- " +ContractCurrencyShowPage+ " instead of -- " +contractCurrencies);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract currency is -- " +ContractCurrencyShowPage+ " instead of -- " +contractCurrencies);
    }
    String ContractReportingCurrencyShowPage = getObject("co_reportingcurrency_show").getText();
    try
    {
    Assert.assertEquals(ContractReportingCurrencyShowPage, contractReportingCurrency, "Contract reporting currency is -- " +ContractReportingCurrencyShowPage+ " instead of -- " +contractReportingCurrency);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract reporting currency is -- " +ContractReportingCurrencyShowPage+ " instead of -- " +contractReportingCurrency);
    }
    String ContractCurrencyConversionTypeShowPage = getObject("co_currency_conversion_type_show").getText();
    try
    {
    Assert.assertEquals(ContractCurrencyConversionTypeShowPage, contractConversionType, "Contract currency conversion type is -- " +ContractCurrencyConversionTypeShowPage+ " instead of -- " +contractConversionType);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract currency conversion type is -- " +ContractCurrencyConversionTypeShowPage+ " instead of -- " +contractConversionType);
    }
    String ContractConversionMatrixShowPage = getObject("co_coversion_matrix_show").getText();
    try
    {
    Assert.assertEquals(ContractConversionMatrixShowPage, contractCurrencyConversionMatrix, "Contract conversion matrix is -- " +ContractConversionMatrixShowPage+ " instead of -- " +contractCurrencyConversionMatrix);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract conversion matrix is -- " +ContractConversionMatrixShowPage+ " instead of -- " +contractCurrencyConversionMatrix);
    }
    String ContractTCVShowPage =getObject("co_tcv_show").getText();
    try
    {
 	   Assert.assertEquals(ContractTCVShowPage, contractTCV, "Contract TCV is -- " +ContractTCVShowPage+ " instead of -- " +contractTCV);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract TCV is -- " +ContractTCVShowPage+ " instead of -- " +contractTCV);
    }
    String ContractAddTCVShowPage = getObject("co_add_tcv_show").getText();
    try
    {
    Assert.assertEquals(ContractAddTCVShowPage, contractAddTCV, "Contract Additional TCV is -- " +ContractAddTCVShowPage+ " instead of -- " +contractAddTCV);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract Additional TCV is -- " +ContractAddTCVShowPage+ " instead of -- " +contractAddTCV);
    }
    String ContractACVShowPage =getObject("co_acv_show").getText();
    try
    {
 	   Assert.assertEquals(ContractACVShowPage, contractACV, "Contract ACV is -- " +ContractACVShowPage+ " instead of -- " +contractACV);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract ACV is -- " +ContractACVShowPage+ " instead of -- " +contractACV);
    }
    String ContractAddACVShowPage = getObject("co_add_acv_show").getText();
    try
    {
    Assert.assertEquals(ContractAddACVShowPage, contractAddACV, "Contract Additional ACV is -- " +ContractAddACVShowPage+ " instead of -- " +contractAddACV);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract Additional ACV is -- " +ContractAddACVShowPage+ " instead of -- " +contractAddACV);
    }
    String ContractFACVShowPage =getObject("co_facv_show").getText();
    try
    {
    Assert.assertEquals(ContractFACVShowPage, contractFACV, "Contract FACV is -- " +ContractFACVShowPage+ " instead of -- " +contractFACV);
    }
    catch(Throwable e )
    {
 	   System.out.println("Contract FACV is -- " +ContractFACVShowPage+ " instead of -- " +contractFACV);
    }
    String ContractAddFACVShowPage = getObject("co_add_facv_show").getText();
    try
    {
    Assert.assertEquals(ContractAddFACVShowPage, contractAddFACV, "Contract Additional FACV is -- " +ContractAddFACVShowPage+ " instead of -- " +contractAddFACV);
    }
    catch(Throwable e)
    {
 	   System.out.println("Contract Additional FACV is -- " +ContractAddFACVShowPage+ " instead of -- " +contractAddFACV);
    }
    
     
    fail = false;
    APP_LOGS.debug("Contract open successfully, following parameters have been validated: Contract id -- " + contract_id+ ", Contract name -- " + contractName+ 
                    ", Contract title -- " + contractTitle+ ", Contract supplier name -- " + contractSupName+ ", Contract aggrement number -- " + contractAgreement+ ", " +
                    		"Contract brief -- " 
                    + contractBrief+", Contract timezone -- " + contractTimeZone+ ", Contract document type -- " + contractDocType+ ", Contract contract entity -- " + contractContractingEntity+ 
                    ", Contract governance body -- " + contractGovernanceBody+
                    ", " +  "Contract delivery country -- " + contractDeliveryCountries+", Contract tier -- " + contractTier+ ", Contract function -- " + contractFunction+ 
                    ", Contract service -- " + contractService+ ", Contract region -- " + contractRegion+", Contract country -- " + contractCountry+ ", Contract currencies -- " 
                    + contractCurrencies+ ", Contract reporting currency -- " + contractReportingCurrency+ ", Contract currency conversion type -- " + contractConversionType+ 
                    ", Contract currency conversion matrix -- " 
                    + contractCurrencyConversionMatrix+ ", Contract TCV -- " + contractTCV+ ", Contract Additional TCV -- " + contractAddTCV+ 
                    ", Contract ACV -- " + contractACV+" , Contract Additional ACV -- " 
                    + contractAddACV+", Contract FACV  -- " + contractFACV); 
    
    plus_button("co_plus_button_link"); // web element for plus button on Contract Show page
    driver.findElement(By.linkText("Map Countries")).click();
    Thread.sleep(5000);
    getObject("con_mapped_countries_save_button").click();

    getObject("analytics_link").click();
      
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
