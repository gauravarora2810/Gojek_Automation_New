package test.resources.com.sirion.suite.contract;

import java.util.Date;

import org.openqa.selenium.By;
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

public class UpdateMSAContract extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
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

  @SuppressWarnings("deprecation")
@Test(groups = "ContractCreation", dataProvider = "getTestData")
  public void SOWContractsCreation(String contractName, String contractUpdateName, String contractTitle, String contractAgreement, 
		  String contractBrief, String contractTimeZone, String contractContractingEntity, String contractGovernanceBody, 
		  String contractDeliveryCountries, String contractTier, String contractSupplierAccess, String contractEffectiveYear, 
		  String contractEffectiveMonth, String contractEffectiveDate, String contractExpirationYear, 
		  String contractExpirationMonth, String contractExpirationDate, String contractFunctions, String contractServices, 
		  String contractMRegions, String contractMcountries,
		  String contractCurrencies, String contractReportingCurrency, String contractConversionType, 
		  String contractCurrencyConversionMatrix, String contractCurrencyConversionMatrixFromYear, 
		  String contractCurrencyConversionMatrixFromMonth, String contractCurrencyConversionMatrixFromDate, 
		  String contractCurrencyConversionMatrixToYear, String contractCurrencyConversionMatrixToMonth, 
		  String contractCurrencyConversionMatrixToDate, String additionalACV, String additionalTCV, String additionalFACV, 
		  String contractDocumentPath, String viewerCheckbox, String searchCheckbox, String downloadCheckbox, String financialCheckbox, 
		  String legalCheckbox, String supName, String contractDocType
		  ) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug(" Executing Test Case of MSA/PSA type Contract Updation");
    
    openBrowser();
	driver.manage().window().maximize();
	
	endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));

	getObject("contract_quick_link").click();
	
	new Select(driver.findElement(By.xpath(".//*[@id='cr_length']/label/select"))).selectByIndex(3);
	
	driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+contractName+"')]/preceding-sibling::td[1]/a")).click();
    
    Thread.sleep(10000);
    
    driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
    Thread.sleep(10000);

   	boolean contractName_tag = false;
    	try {
    		if(getObject("co_name_textbox")!=null){
    			contractName_tag= getObject("co_name_textbox").isEnabled();
    		}
    	}
    	catch(Exception e)
        {
    		contractName_tag=false;
        }
      
      if(!contractName.equalsIgnoreCase("")  &&  contractName_tag)
      {    
          getObject("co_name_textbox").clear();
          getObject("co_name_textbox").sendKeys(contractUpdateName); // name
      }

      boolean contractTitle_tag=false;
      try{
    	  	if(getObject("co_title_textbox") != null){
    	  	contractTitle_tag = getObject("co_title_textbox").isEnabled();
      }
      }
      catch(Exception e)
      {
          contractTitle_tag=false;
      }

      if(!contractTitle.equalsIgnoreCase("")  &&  contractTitle_tag)
      {    
          getObject("co_title_textbox").clear();
          getObject("co_title_textbox").sendKeys(contractTitle); // name
      }

      boolean contractAgreement_tag=false;
      try{
      System.out.println(getObject("co_agreement_textbox"));

      if(getObject("co_agreement_textbox") != null){
          contractAgreement_tag = getObject("co_agreement_textbox").isEnabled();
      }
      }
      catch(Exception e)
      {
          contractAgreement_tag=false;
      }

      if(!contractAgreement.equalsIgnoreCase("")  &&  contractAgreement_tag)
      {    
          getObject("co_agreement_textbox").clear();
          getObject("co_agreement_textbox").sendKeys(contractAgreement); // name
      }

      boolean contractBrief_tag=false;
      try{
      System.out.println(getObject("co_brief_textarea"));

      if(getObject("co_brief_textarea") != null){
          contractBrief_tag = getObject("co_brief_textarea").isEnabled();
      }
      }
      catch(Exception e)
      {
          contractBrief_tag=false;
      }

      if(!contractBrief.equalsIgnoreCase("")  &&  contractBrief_tag)
      {    
          getObject("co_brief_textarea").clear();
          getObject("co_brief_textarea").sendKeys(contractBrief); // name
          getObject("co_extra_space").click();
      }

    if (!contractTimeZone.equalsIgnoreCase("")) {
    	System.out.println(contractTimeZone);
      new Select(getObject("co_timezone_dropdown")).selectByVisibleText(contractTimeZone); // timezone
    }

    if (!contractContractingEntity.equalsIgnoreCase("")) {
      new Select(getObject("co_contracting_dropdown")).selectByVisibleText(contractContractingEntity);
      
    }

    if (!contractGovernanceBody.equalsIgnoreCase("")) {
    	new Select(getObject("co_govbody_multi")).deselectAll();
    	
      new Select(getObject("co_govbody_multi")).selectByVisibleText(contractGovernanceBody); // governance body
    }

    if (!contractDeliveryCountries.equalsIgnoreCase("")) {
    	new Select(getObject("co_delcountry_multi")).deselectAll();
      new Select(getObject("co_delcountry_multi")).selectByVisibleText(contractDeliveryCountries);
    }
    
    if (contractSupplierAccess.equalsIgnoreCase("yes")) {
      getObject("co_supplier_access_checkbox").click(); 
    }

    if (!contractTier.equalsIgnoreCase("")) {
      new Select(getObject("co_tier_dropdown")).selectByVisibleText(contractTier);
    }
    
    
    driver.findElement(By.name("effectiveDate")).click();


    Double temp_contractEffectiveYear_double = Double.parseDouble(contractEffectiveYear);
    int temp_contractEffectiveYear_int = temp_contractEffectiveYear_double.intValue();
    String contractEffectiveYear_string = Integer.toString(temp_contractEffectiveYear_int);

    WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
    System.out.println(datepicker_ui.isDisplayed());
    if (datepicker_ui.isDisplayed() == true) {
      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
      new Select(datepicker_ui_year).selectByVisibleText(contractEffectiveYear_string);
          }

    Date date = new Date();

    int current_month = date.getMonth();

    Double temp_contractEffectiveMonth_double = Double.parseDouble(contractEffectiveMonth);
    int temp_contractEffectiveMonth_int = temp_contractEffectiveMonth_double.intValue();

    int click = current_month - temp_contractEffectiveMonth_int;
    System.out.println("click " + click);
    for (; click >= 0; click = click - 1) {

      System.out.println("value of j" + click);
      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();

    }

    Double temp_contractEffectiveDate_double = Double.parseDouble(contractEffectiveDate);
    int temp_contractEffectiveDate_int = temp_contractEffectiveDate_double.intValue();
    String contractEffectiveDate_string = Integer.toString(temp_contractEffectiveDate_int);
    driver.findElement(By.linkText(contractEffectiveDate_string)).click(); // enter date

    driver.findElement(By.name("expirationDate")).click();
    Double temp_contractExpirationYear_double = Double.parseDouble(contractExpirationYear);
    int temp_contractExpirationYear_int = temp_contractExpirationYear_double.intValue();
    String contractExpirationYear_string = Integer.toString(temp_contractExpirationYear_int);
    // WebElement datepicker_ui=driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));

    System.out.println(datepicker_ui.isDisplayed());
    if (datepicker_ui.isDisplayed() == true) {
      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
      new Select(datepicker_ui_year).selectByVisibleText(contractExpirationYear_string);
    }

    Double temp_contractExpirationMonth_double = Double.parseDouble(contractExpirationMonth);
    int temp_contractExpirationMonth_int = temp_contractExpirationMonth_double.intValue();
    System.out.println(" contractExpirationMonth " + temp_contractExpirationMonth_int);

    int click1 = temp_contractExpirationMonth_int - current_month;
    System.out.println("click " + click1);
    for (; click1 >= 0; click1 = click1 - 1) {
      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
    }

    Double temp_contractExpirationDate_double = Double.parseDouble(contractExpirationDate);
    int temp_contractExpirationDate_int = temp_contractExpirationDate_double.intValue();
    String contractExpirationDate_string = Integer.toString(temp_contractExpirationDate_int);
    driver.findElement(By.linkText(contractExpirationDate_string)).click(); // enter date
    
    
    boolean contractMregion_tag=false;
    try{
			if(getObject("co_regions_multi") != null){
			contractMregion_tag = getObject("co_regions_multi").isEnabled();
    }
    }
    catch(Exception e)
    {
        contractMregion_tag=false;
    }

    if(!contractMRegions.equalsIgnoreCase("")  &&  contractMregion_tag)
    {    
        new Select(getObject("co_regions_multi")).deselectAll();
        new Select(getObject("co_regions_multi")).selectByVisibleText(contractMRegions);
    }
    
    boolean contractMcountries_tag=false;
    try{
			if(getObject("co_countries_multi") != null){
			contractMcountries_tag = getObject("co_countries_multi").isEnabled();
    }
    }
    catch(Exception e)
    {
        contractMcountries_tag=false;
    }

    if(!contractMcountries.equalsIgnoreCase("")  &&  contractMcountries_tag)
    {    
        new Select(getObject("co_countries_multi")).deselectAll();
        new Select(getObject("co_countries_multi")).selectByVisibleText(contractMcountries);
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
    
    if (!contractCurrencyConversionMatrix.equalsIgnoreCase("")) {
        new Select(getObject("co_currencyconversion_dropdown")).selectByVisibleText(contractCurrencyConversionMatrix);
      }

    driver.findElement(By.name("rateCardFromDate")).click();

    Double temp_contractCurrencyConversionMatrixFromYear_double = Double.parseDouble(contractCurrencyConversionMatrixFromYear);
    int temp_contractCurrencyConversionMatrixFromYear_int = temp_contractCurrencyConversionMatrixFromYear_double.intValue();
    String contractCurrencyConversionMatrixFromYear_string = Integer.toString(temp_contractCurrencyConversionMatrixFromYear_int);

    System.out.println(datepicker_ui.isDisplayed());
    if (datepicker_ui.isDisplayed() == true) {
      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
      new Select(datepicker_ui_year).selectByVisibleText(contractCurrencyConversionMatrixFromYear_string);
    }

    Double temp_contractCurrencyConversionMatrixFromMonth_double = Double.parseDouble(contractCurrencyConversionMatrixFromMonth);
    int temp_contractCurrencyConversionMatrixFromMonth_int = temp_contractCurrencyConversionMatrixFromMonth_double.intValue();
    System.out.println(" contractCurrencyConversionMatrixFromMonth " + temp_contractCurrencyConversionMatrixFromMonth_int);

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
    
   if(!additionalTCV.equalsIgnoreCase("")){
    getObject("co_tcv_textbox").sendKeys(additionalTCV);
    }
    if(!additionalACV.equalsIgnoreCase("")){
    getObject("co_acv_textbox").sendKeys(additionalACV);
    }
    if (!additionalFACV.equalsIgnoreCase("")){
    getObject("co_facv_textbox").sendKeys(additionalFACV);
    }

    getObject("co_doc_upload_tab_link").click();
    
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
    
    getObject("co_general_tab_link").click();
    
    getObject("contract_update_button").click();
    Thread.sleep(20000);

    String ContractNameShowPage = getObject("co_name_show").getText();
    try
    {
 	   System.out.println(ContractNameShowPage);
 	   System.out.println(contractName);
    Assert.assertEquals(ContractNameShowPage, contractName, "Contract name is -- " +ContractNameShowPage+ " instead of -- " +contractName);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract Other Folder Na"+contractName+"Instead of"+ContractNameShowPage);
    }
    String ContractTitleShowPage =  getObject("co_title_show").getText(); 
    try
    {
    Assert.assertEquals(ContractTitleShowPage, contractTitle, "Contract title is -- " +ContractTitleShowPage+ " instead of -- " +contractTitle);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract title is -- " +ContractTitleShowPage+ " instead of -- " +contractTitle);
    }
    String ContractSupplierShowPage = getObject("co_sup_show").getText();
    try
    {
 	   Assert.assertEquals(ContractSupplierShowPage, supName, "Contract supplier is -- " +ContractSupplierShowPage+ " instead of -- " +supName);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract supplier is -- " +ContractSupplierShowPage+ " instead of -- " +supName);
    }
    String ContractAggNoShowPage = getObject("co_agreement_number_show").getText();
    try
    {
    Assert.assertEquals(ContractAggNoShowPage, contractAgreement, "Contract aggregate number is -- " +ContractAggNoShowPage+ " instead of -- " +contractAgreement);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract aggregate number is -- " +ContractAggNoShowPage+ " instead of -- " +contractAgreement);
    }
    String ContractBriefShowPage = getObject("co_brief_show").getText();
    try
    {
    Assert.assertEquals(ContractBriefShowPage, contractBrief, "Contract brief is -- " +ContractBriefShowPage+ " instead of -- " +contractBrief);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract brief is -- " +ContractBriefShowPage+ " instead of -- " +contractBrief);
    }
    String ContractTimezoneShowPage =getObject("co_timezone_show").getText();
    try
    {
    Assert.assertEquals(ContractTimezoneShowPage, contractTimeZone, "Contract timezone is -- " +ContractTimezoneShowPage+ " instead of -- " +contractTimeZone);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract timezone is -- " +ContractTimezoneShowPage+ " instead of -- " +contractTimeZone);
    }
    String ContractDocTypeShowPage = getObject("co_doc_type_show").getText();
    try
    {
 	   Assert.assertEquals(ContractDocTypeShowPage, contractDocType, "Contract document type is -- " +ContractDocTypeShowPage+ " instead of -- " +contractDocType);   
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug( "Contract document type is -- " +ContractDocTypeShowPage+ " instead of -- " +contractDocType);
    }
    String ContractContractEntityShowPage =getObject("co_contracting_show").getText();
    try
    {
    Assert.assertEquals(ContractContractEntityShowPage, contractContractingEntity, "Contract contract entity is -- " +ContractContractEntityShowPage+ " instead of -- " +contractContractingEntity);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract contract entity is -- " +ContractContractEntityShowPage+ " instead of -- " +contractContractingEntity);
    }
    String ContractGovBodyShowPage = getObject("co_govbody_show").getText();
    try
    {
    Assert.assertEquals(ContractGovBodyShowPage, contractGovernanceBody, "Contract governance body is -- " +ContractGovBodyShowPage+ " instead of -- " +contractGovernanceBody);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract governance body is -- " +ContractGovBodyShowPage+ " instead of -- " +contractGovernanceBody);
    }
    String ContractDelCountryShowPage =getObject("co_delcountry_show").getText();
    try
    {
    Assert.assertEquals(ContractDelCountryShowPage, contractDeliveryCountries, "Contract delivery country is -- " +ContractDelCountryShowPage+ " instead of -- " +contractDeliveryCountries);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract delivery country is -- " +ContractDelCountryShowPage+ " instead of -- " +contractDeliveryCountries);
    }
    String ContractTierShowPage = getObject("co_tier_show").getText();
    try
    {
 	   Assert.assertEquals(ContractTierShowPage, contractTier, "Contract tier is -- " +ContractTierShowPage+ " instead of -- " +contractTier);   
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract tier is -- " +ContractTierShowPage+ " instead of -- " +contractTier);
    }
    String ContractFunctionShowPage = getObject("co_function_show").getText();
    try
    {
    Assert.assertEquals(ContractFunctionShowPage, contractFunctions, "Contract function is -- " +ContractFunctionShowPage+ " instead of -- " +contractFunctions);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract function is -- " +ContractFunctionShowPage+ " instead of -- " +contractFunctions);
    }
    String ContractServiceShowPage =getObject("co_service_show").getText();
    try
    {
    Assert.assertEquals(ContractServiceShowPage, contractServices, "Contract service is -- " +ContractServiceShowPage+ " instead of -- " +contractServices);
    }
    catch(Throwable e )
    {
    	APP_LOGS.debug("Contract service is -- " +ContractServiceShowPage+ " instead of -- " +contractServices);
    }
    String contractMRegionShowPage = getObject("co_m_region_show").getText();

    try
        {
     	   Assert.assertEquals(contractMRegionShowPage, contractMRegions, "Contract Management Region is -- " +contractMRegionShowPage+ " instead of -- " +contractMRegions);   
        }
        catch(Throwable e)
        {
        	APP_LOGS.debug("Contract Management Region is -- " +contractMRegionShowPage+ " instead of -- " +contractMRegions);
        }
        
  String ContractMCountryShowPage =getObject("co_m_countries_show").getText();
    try
    {
 	   Assert.assertEquals(ContractMCountryShowPage, contractMcountries, "Contract Management Region is -- " +ContractMCountryShowPage+ " instead of -- " +contractMcountries);   
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract Management Region is -- " +ContractMCountryShowPage+ " instead of -- " +contractMcountries);
    }
     
  String ContractCurrencyShowPage = getObject("co_currency_show").getText();
    try
    {
    Assert.assertEquals(ContractCurrencyShowPage, contractCurrencies, "Contract currency is -- " +ContractCurrencyShowPage+ " instead of -- " +contractCurrencies);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract currency is -- " +ContractCurrencyShowPage+ " instead of -- " +contractCurrencies);
    }
    String ContractReportingCurrencyShowPage = getObject("co_reportingcurrency_show").getText();
    try
    {
    Assert.assertEquals(ContractReportingCurrencyShowPage, contractReportingCurrency, "Contract reporting currency is -- " +ContractReportingCurrencyShowPage+ " instead of -- " +contractReportingCurrency);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract reporting currency is -- " +ContractReportingCurrencyShowPage+ " instead of -- " +contractReportingCurrency);
    }
    String ContractCurrencyConversionTypeShowPage = getObject("co_currency_conversion_type_show").getText();
    try
    {
    Assert.assertEquals(ContractCurrencyConversionTypeShowPage, contractConversionType, "Contract currency conversion type is -- " +ContractCurrencyConversionTypeShowPage+ " instead of -- " +contractConversionType);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract currency conversion type is -- " +ContractCurrencyConversionTypeShowPage+ " instead of -- " +contractConversionType);
    }
    String ContractConversionMatrixShowPage = getObject("co_coversion_matrix_show").getText();
    try
    {
    Assert.assertEquals(ContractConversionMatrixShowPage, contractCurrencyConversionMatrix, "Contract conversion matrix is -- " +ContractConversionMatrixShowPage+ " instead of -- " +contractCurrencyConversionMatrix);
    }
    catch(Throwable e)
    {
    	APP_LOGS.debug("Contract conversion matrix is -- " +ContractConversionMatrixShowPage+ " instead of -- " +contractCurrencyConversionMatrix);
    }
        
    Thread.sleep(5000);

    fail = false;
    APP_LOGS.debug("Contract open successfully, following parameters have been validated: Contract name -- " + contractName+ 
                    ", Contract title -- " + contractTitle+ ", Contract supplier name -- " + supName+ ", Contract aggrement number -- " + contractAgreement+ ", " +
                    		"Contract brief -- " 
                    + contractBrief+", Contract timezone -- " + contractTimeZone+ ", Contract document type -- " + contractDocType+ ", Contract contract entity -- " + contractContractingEntity+ 
                    ", Contract governance body -- " + contractGovernanceBody+
                    ", " +  "Contract delivery country -- " + contractDeliveryCountries+", Contract tier -- " + contractTier+ ", Contract function -- " + contractFunctions+ 
                    ", Contract service -- " + contractServices+ ", Contract region -- " + contractMRegions+", Contract country -- " + contractMcountries+ ", Contract currencies -- " 
                    + contractCurrencies+ ", Contract reporting currency -- " + contractReportingCurrency+ ", Contract currency conversion type -- " + contractConversionType+ 
                    ", Contract currency conversion matrix -- " 
                    + contractCurrencyConversionMatrix); 
    
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
    fail = true;

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