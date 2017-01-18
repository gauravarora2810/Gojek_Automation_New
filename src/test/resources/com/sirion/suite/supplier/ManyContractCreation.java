package test.resources.com.sirion.suite.supplier;

import java.util.Date;

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

public class ManyContractCreation extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(supplier_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(supplier_suite_xls, this.getClass().getSimpleName());
  }

  @Test(groups = "ContractCreation", dataProvider = "getTestData")
  public void ContractCreation(String contractName, String contractTitle, String contractAgreement, String contractBrief, String contractTimeZone,
      String contractContractingEntity, String contractGovernanceBody, String contractDeliveryCountries, String contractTier, String contractSupplierAccess, 
      String contractEffectiveDate,
      String contractEffectiveMonth, String contractEffectiveYear, String contractExpirationDate, String contractExpirationMonth,
      String contractExpirationYear, String contractFunctions, String contractServices, String contractRegions, String contractCountries,
      String contractStakeholder1, String contractStakeholder2, String contractStakeholder3, String contractStakeholder4, String contractStakeholder5, 
      String contractStakeholder6, String contractStakeholder7, String contractStakeholder8, String contractStakeholder9, String contractStakeholder10,
      String contractCurrencies, String contractReportingCurrency, String contractConversionType,
      String contractCurrencyConversionMatrix, String contractCurrencyConversionMatrixFromDate, String contractCurrencyConversionMatrixFromMonth,
      String contractCurrencyConversionMatrixFromYear, String contractCurrencyConversionMatrixToDate, String contractCurrencyConversionMatrixToMonth,
      String contractCurrencyConversionMatrixToYear, String additionalACV, String additionalTCV, String additionalFACV,
      String contractDocumentPath, 
      String viewerCheckbox, String searchCheckbox, String downloadCheckbox, String financialCheckbox, 
      String legalCheckbox, String supName, String contractDocType, String contractFunction, String contractService, String contractRegion, String contractCountry, 
      String contractACV, String contractAddACV, String contractAggACV, String contractTCV, String contractAddTCV, String contractAggTCV, String contractFACV,
      String contractAddFACV, String contractAggFACV) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Contract Creation with contract name --- " +contractName+ " under supplier --- "+supName);
    try{
      openBrowser();
      endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
      
      for(int i=1010; i<3000; i++)
      {
     
        try{
        String client_id= "SP0"+i;
        getObject("quick_search_textbox").sendKeys(client_id);
        getObject("quick_search_textbox").sendKeys(Keys.ENTER);
        Thread.sleep(10000);
     
      


    plus_button("supplier_plus_button_link"); // web element for plus button on supplier page
    
    getObject("msa_create_link").click(); // click msa create link
    Thread.sleep(1000);
    /*
     * //plus_button("supplier_plus_button_link"); // web element for plus button on supplier page Actions ac_plus_contract = new Actions(driver);
     * WebElement plus_contract = driver.findElement(By.xpath(".//*[@id='mainContainer']/div/div[2]")); WebElement
     * create_contract_link=driver.findElement(By.xpath(".//*[@id='drop']/ul/li[1]/label")); //Thread.sleep(1000); WebElement
     * msa_link=driver.findElement(By.xpath(".//*[@id='i_tblcontract_new']/a/div"));
     * 
     * //Thread.sleep(1000);
     * ac_plus_contract.moveToElement(plus_contract).moveToElement(create_contract_link).moveToElement(msa_link).click().build().perform();
     */

    // getObject("msa_create_link").click(); // click msa create link

    if (!contractName.equalsIgnoreCase("")) {
      getObject("co_name_textbox").sendKeys(contractName); // name
    }

      if (!contractTitle.equalsIgnoreCase("")) {
      getObject("co_title_textbox").sendKeys(contractTitle); // title
    }

    if (!contractAgreement.equalsIgnoreCase("")) {
      getObject("co_agreement_textbox").sendKeys(contractAgreement); // title
    }

    if (!contractBrief.equalsIgnoreCase("")) {
      getObject("co_brief_textarea").sendKeys(contractBrief); // brief
    }

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
      String[] contractDeliveryCountriesSplit=  contractDeliveryCountries.split(";");
      for (int i1=0; i1 < contractDeliveryCountriesSplit.length ;i1++){
        new Select(getObject("co_delcountry_multi")).selectByVisibleText(contractDeliveryCountriesSplit[i1]);
      }
     
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
      // new Select(datepicker_ui_year).selectByVisibleText("2015");
      // new Select(datepicker_ui_year).selectByIndex(1);
    }
    // Select year=new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select")));
    // year.selectByValue("2016");

    Date date = new Date();

    int current_month = date.getMonth();
    //System.out.println("current_month " + current_month);

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

    if (!contractFunctions.equalsIgnoreCase("")) {
      String[] contractFunctionsSplit=  contractFunctions.split(";");
      for (int i1=0; i1 < contractFunctionsSplit.length ;i1++){
        new Select(getObject("co_functions_multi")).selectByVisibleText(contractFunctionsSplit[i1]);
        //new Select(getObject("ca_sup_functions_multidropdown")).selectByVisibleText(supFunctions);
        }}
    
    if (!contractServices.equalsIgnoreCase("")) {
      String[] contractServicesSplit=  contractServices.split(";");
      for (int i1=0; i1 < contractServicesSplit.length ;i1++){
        new Select(getObject("co_services_multi")).selectByVisibleText(contractServicesSplit[i1]);
        //new Select(getObject("ca_sup_functions_multidropdown")).selectByVisibleText(supFunctions);
        }}
    
    if (!contractRegions.equalsIgnoreCase("")) {
      String[] contractRegionsSplit=  contractRegions.split(";");
      for (int i1=0; i1 < contractRegionsSplit.length ;i1++){
        new Select(getObject("co_regions_multi")).selectByVisibleText(contractRegionsSplit[i1]);
        //new Select(getObject("ca_sup_functions_multidropdown")).selectByVisibleText(supFunctions);
        }}
    
    if (!contractCountries.equalsIgnoreCase("")) {
      String[] contractCountriesSplit=  contractCountries.split(";");
      for (int i1=0; i1 < contractCountriesSplit.length ;i1++){
        new Select(getObject("co_countries_multi")).selectByVisibleText(contractCountriesSplit[i1]);
        //new Select(getObject("ca_sup_functions_multidropdown")).selectByVisibleText(supFunctions);
        }}
 
    
    
    if(!contractStakeholder1.equalsIgnoreCase("")){  
      driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label/span[2]/div/input")).clear();
      driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label/span[2]/div/input")).sendKeys(contractStakeholder1);
      Thread.sleep(3000);
      driver.findElement(By.xpath("//strong")).click();
    }  
       if(!contractStakeholder2.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[2]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[2]/span[2]/div/input")).sendKeys(contractStakeholder2);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder3.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[3]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[3]/span[2]/div/input")).sendKeys(contractStakeholder3);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        }  
       if(!contractStakeholder4.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[4]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[4]/span[2]/div/input")).sendKeys(contractStakeholder4);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder5.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[5]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[5]/span[2]/div/input")).sendKeys(contractStakeholder5);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder6.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[6]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[6]/span[2]/div/input")).sendKeys(contractStakeholder6);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder7.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[7]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[7]/span[2]/div/input")).sendKeys(contractStakeholder7);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder8.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[8]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[8]/span[2]/div/input")).sendKeys(contractStakeholder8);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder9.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[9]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[9]/span[2]/div/input")).sendKeys(contractStakeholder9);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
       if(!contractStakeholder10.equalsIgnoreCase("")){  
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[10]/span[2]/div/input")).clear();
         driver.findElement(By.xpath("//div[17]/div[2]/div/div/div/div/div/div/div/div[2]/div/div/form/div/ng-form/fieldset[5]/label[10]/span[2]/div/input")).sendKeys(contractStakeholder10);
         Thread.sleep(3000);
         driver.findElement(By.xpath("//strong")).click();
        } 
    
    
    if (!contractCurrencies.equalsIgnoreCase(""))  {
      String[] contractCurrenciesSplit=  contractCurrencies.split(";");
      for (int i1=0; i1 < contractCurrenciesSplit.length ;i1++){
        new Select(getObject("co_currency_multi")).selectByVisibleText(contractCurrenciesSplit[i1]);
        //new Select(getObject("ca_sup_functions_multidropdown")).selectByVisibleText(supFunctions);
        }}

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

    if (!contractCurrencyConversionMatrix.equalsIgnoreCase("")) {
      new Select(getObject("co_currencyconversion_dropdown")).selectByVisibleText(contractCurrencyConversionMatrix);
    }
    
/*    if (!contractTitle.equalsIgnoreCase("")) {
    getObject("co_title_textbox").sendKeys(contractTitle); // title
  }*/
    
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
   
    driver.findElement(By.xpath(".//*[@id='kk']/div/div/div[2]/div[1]/div/form/div[4]/ng-form/div/button[1]")).click();
    Thread.sleep(10000);
   // String contract_id = driver.findElement(By.xpath(".//*[@id='hrefElemId']")).getText();
    String contract_id = getObject("co_popup_id").getText();
    
    APP_LOGS.debug("Contract created successfully with Contract id "+contract_id);
    Thread.sleep(3000);
    
    //driver.findElement(By.xpath(".//*[@id='data-ng-app']/div[25]/div/div/div/div[3]/button")).click();
    getObject("co_popup_ok_button").click();
    Thread.sleep(8000);
    getObject("analytics_link").click();
    APP_LOGS.debug("Quick Search the created contract with Contract id "+contract_id);
      
    
    
    //driver.findElement(By.xpath(".//*[@id='h-analytics']/a")).click();
    getObject("analytics_link").click();
        }
        catch(Exception exception){
          System.out.println("skipping for id "+i+" due to exception");
          //ignore
        }
    }
    }
catch(Exception e){
  APP_LOGS.debug("Exception while creating supplier" +supName+ " -- " +e);
}
}
      

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(supplier_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(supplier_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(supplier_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = true;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(supplier_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(supplier_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(supplier_suite_xls, this.getClass().getSimpleName());
  }

}
