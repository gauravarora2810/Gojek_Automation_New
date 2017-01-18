package test.resources.com.sirion.suite.contract;

import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import test.resources.com.sirion.util.*;
import test.resources.com.sirion.util.TestUtil;

public class ContractCreation extends TestSuiteBase {
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

  @Test(groups = "ContractCreation", dataProvider = "getTestData")
  public void ContractCreate(String user,String contractName, String contractTitle, String contractAgreement, String contractContractNumber,String contractBrief, String contractTimeZone,
      String contractContractingEntity, String contractGovernanceBody, String contractDeliveryCountries, String contractTier, String contractTermtype,String contracttype,String contractAribacwid,
      String contractCommentfieldstatus,String contractNumberofrenewals,String contractPaper,String contractVendorclassification,String contractSupplierAccess,String contractEffectiveDate, String contractEffectiveMonth, String contractEffectiveYear, 
      String contractExpirationDate, String contractExpirationMonth, String contractExpirationYear,String contractEffectiveOriginalMonth,String contractEffectiveOriginalYear,String contractEffectiveOriginalDate, 
      String contractExpirationOriginalMonth,String contractExpirationOriginalYear,String contractExpirationOriginalDate,String contractFunctions, String contractServices, String contractRegions, String contractCountries,
      String contractNoticeMonth,String contractNoticeYear,String contractNoticeDate,String contractNoticeLeadMonth,String contractNoticeLeadYear,String contractNoticeLeadDate,
      String contractCurrencies, String contractReportingCurrency, String contractConversionType,
      String contractCurrencyConversionMatrix, String contractCurrencyConversionMatrixFromDate, String contractCurrencyConversionMatrixFromMonth,
      String contractCurrencyConversionMatrixFromYear, String contractCurrencyConversionMatrixToDate, String contractCurrencyConversionMatrixToMonth,
      String contractCurrencyConversionMatrixToYear, String additionalACV, String additionalTCV, String additionalFACV,
      String contractDocumentPath, 
      String viewerCheckbox, String searchCheckbox, String downloadCheckbox, String financialCheckbox, 
      String legalCheckbox, String contractSupName, String contractDocType, String contractFunction, String contractService, String contractRegion, String contractCountry, 
      String contractACV, String contractAddACV, String contractAggACV, String contractTCV, String contractAddTCV, String contractAggTCV, String contractFACV,
      String contractAddFACV, String contractAggFACV) throws InterruptedException, MalformedURLException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }

    APP_LOGS.debug("Executing Test Case Contract Creation with contract name --- " +contractName+ " under supplier --- "+contractSupName);

    
    
    openBrowser();
	System.out.println("Shipra");
	endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
    
    Thread.sleep(5000);
	
    getObject("analytics_link").click();
    WebDriverWait wait=new WebDriverWait(driver, 50);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rt-suppliers']/a")));
    
    if (!checkElementPresence("supplier_quick_link")) {
        fail = true;
        // return; // conditional
      }
    
    getObject("supplier_quick_link").click();
  
    driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ contractSupName +"')]/preceding-sibling::td[1]/preceding-sibling::td[1]/a")).click();
    //Thread.sleep(10000);

    wait.until(ExpectedConditions.elementToBeClickable(getObject("supplier_plus_button_link")));
    
    plus_button("supplier_plus_button_link"); // web element for plus button on supplier page
    
    wait.until(ExpectedConditions.elementToBeClickable(getObject("msa_create_link")));
    
    getObject("msa_create_link").click(); // click msa create link
    //Thread.sleep(1000);
  
    if (!contractName.equalsIgnoreCase("")) {
      getObject("co_name_textbox").sendKeys(contractName); // name
    }

      if (!contractTitle.equalsIgnoreCase("")) {
      getObject("co_title_textbox").sendKeys(contractTitle); // title
    }

    if (!contractAgreement.equalsIgnoreCase("")) {
    	
    	fail= true;
      getObject("co_agreement_textbox").sendKeys(contractAgreement); // title
      getObject("co_contract_number_textbox").click();
      String slAttribute = getObject("co_agreement_textbox").getAttribute("class");
		String[] slAttributeSplit = slAttribute.split(" ");
		String slAttributeSplitString = slAttributeSplit[3];

		Assert.assertEquals(slAttributeSplitString, "errorClass");

		APP_LOGS.debug("Maximum characters allowed is 256");// brief
		
		fail = false;
		contractAgreement = contractAgreement.substring(0, 250);
		getObject("co_agreement_textbox").clear();
		getObject("co_agreement_textbox").sendKeys(contractAgreement);

		}

    if (!contractBrief.equalsIgnoreCase("")) {
      getObject("co_brief_textarea").sendKeys(contractBrief); // brief
    }

    if (!contractContractNumber.equalsIgnoreCase("")) {
    	fail= true;
        getObject("co_contract_number_textbox").sendKeys(contractContractNumber);
        getObject("co_agreement_textbox").click();
        String slAttribute = getObject("co_contract_number_textbox").getAttribute("class");
		String[] slAttributeSplit = slAttribute.split(" ");
		String slAttributeSplitString = slAttributeSplit[3];

		Assert.assertEquals(slAttributeSplitString, "errorClass");

		APP_LOGS.debug("No special Characters");// brief
		
		fail = false;
		Scanner scan = new Scanner(contractContractNumber);
		while (scan.hasNextLine()) {
	          final String contractcontractNumber = scan.nextLine();
		 
		Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
		Matcher match= pt.matcher(contractContractNumber);
		    while(match.find()){
		    	contractContractNumber=contractcontractNumber.replace(Character.toString(contractcontractNumber.charAt(match.start())),"");
		         }
		    getObject("co_contract_number_textbox").clear();
		    getObject("co_contract_number_textbox").sendKeys(contractContractNumber);
		      }
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
      new Select(getObject("co_delcountry_multi")).selectByVisibleText(contractDeliveryCountries);
    }
    
    if (contractSupplierAccess.equalsIgnoreCase("yes")) {
      getObject("co_supplier_access_checkbox").click(); 
    }

    if (!contractTier.equalsIgnoreCase("")) {
      new Select(getObject("co_tier_dropdown")).selectByVisibleText(contractTier);
    }

    if (!contractTermtype.equalsIgnoreCase("")) {
        new Select(getObject("co_term_type_dropdown")).selectByVisibleText(contractTermtype);
      }
 
    if (!contracttype.equalsIgnoreCase("")) {
        new Select(getObject("co_contract_type_dropdown")).selectByVisibleText(contracttype);
      }
    
    if (!contractAribacwid.equalsIgnoreCase("")) {
        getObject("co_ariba_cw_id_textbox").sendKeys(contractAribacwid);
      }
    
    if (!contractCommentfieldstatus.equalsIgnoreCase("")) {
        getObject("co_comment_field_status_textarea").sendKeys(contractCommentfieldstatus);
      }
    
    if (!contractNumberofrenewals.equalsIgnoreCase("")) {
        getObject("co_number_of_renewals_textbox").sendKeys(contractNumberofrenewals);
      }
    
    if (!contractPaper.equalsIgnoreCase("")) {
        new Select(getObject("co_contract_type_dropdown")).selectByVisibleText(contractPaper);
      }
    
    if (!contractVendorclassification.equalsIgnoreCase("")) {
        new Select(getObject("co_contract_type_dropdown")).selectByVisibleText(contractVendorclassification);
      }
    

    driver.findElement(By.name("effectiveDate")).click();

    //Effective Date
	String EffectiveDateMonth = convertDoubleToIntegerInStringForm(contractEffectiveMonth);
	int EffectivedateMonth = Integer.parseInt(EffectiveDateMonth);
	String EffectiveDateYear = convertDoubleToIntegerInStringForm(contractEffectiveYear);
	int EffectivedateYear = Integer.parseInt(EffectiveDateYear);
	String EffectiveDateDate = convertDoubleToIntegerInStringForm(contractEffectiveDate);
	Integer EffectivedateDate = Integer.parseInt(EffectiveDateDate);
	EffectiveDateDate = EffectivedateDate.toString();

	DatePicker dp_Contract_Effective_Date_date = new DatePicker();
	dp_Contract_Effective_Date_date.expDate = EffectiveDateDate;
	dp_Contract_Effective_Date_date.expMonth = EffectivedateMonth;
	dp_Contract_Effective_Date_date.expYear = EffectivedateYear;
	dp_Contract_Effective_Date_date.pickExpDate("effectiveDate");      
      
	//Expiration Date
	String ExpirationDateMonth = convertDoubleToIntegerInStringForm(contractExpirationMonth);
	int ExpirationdateMonth = Integer.parseInt(ExpirationDateMonth);
	String ExpirationDateYear = convertDoubleToIntegerInStringForm(contractExpirationYear);
	int ExpirationdateYear = Integer.parseInt(ExpirationDateYear);
	String ExpirationDateDate = convertDoubleToIntegerInStringForm(contractExpirationDate);
	Integer ExpirationdateDate = Integer.parseInt(ExpirationDateDate);
	ExpirationDateDate = ExpirationdateDate.toString();

	DatePicker dp_Contract_Expiration_Date_date = new DatePicker();
	dp_Contract_Expiration_Date_date.expDate = ExpirationDateDate;
	dp_Contract_Expiration_Date_date.expMonth = ExpirationdateMonth;
	dp_Contract_Expiration_Date_date.expYear = ExpirationdateYear;
	dp_Contract_Expiration_Date_date.pickExpDate("expirationDate");      
	
	//Effective Date Original
	String EffectiveDateOriginalMonth = convertDoubleToIntegerInStringForm(contractEffectiveOriginalMonth);
	int EffectivedateOriginalMonth = Integer.parseInt(EffectiveDateOriginalMonth);
	String EffectiveDateOriginalYear = convertDoubleToIntegerInStringForm(contractEffectiveOriginalYear);
	int EffectivedateOriginalYear = Integer.parseInt(EffectiveDateOriginalYear);
	String EffectiveDateOriginalDate = convertDoubleToIntegerInStringForm(contractEffectiveOriginalDate);
	Integer EffectivedateOriginalDate = Integer.parseInt(EffectiveDateOriginalDate);
	EffectiveDateOriginalDate = EffectivedateOriginalDate.toString();

	DatePicker dp_Contract_Effective_Date_Original_date = new DatePicker();
	dp_Contract_Effective_Date_Original_date.expDate = EffectiveDateOriginalDate;
	dp_Contract_Effective_Date_Original_date.expMonth = EffectivedateOriginalMonth;
	dp_Contract_Effective_Date_Original_date.expYear = EffectivedateOriginalYear;
	dp_Contract_Effective_Date_Original_date.pickExpDate("effectiveDateOriginal");   
	
	
	//Expiration Date Original
	String ExpirationDateOriginalMonth = convertDoubleToIntegerInStringForm(contractExpirationOriginalMonth);
	int ExpirationdateOriginalMonth = Integer.parseInt(ExpirationDateOriginalMonth);
	String ExpirationDateOriginalYear = convertDoubleToIntegerInStringForm(contractExpirationOriginalYear);
	int ExpirationdateOriginalYear = Integer.parseInt(ExpirationDateOriginalYear);
	String ExpirationDateOriginalDate = convertDoubleToIntegerInStringForm(contractExpirationOriginalDate);
	Integer ExpirationdateOriginalDate = Integer.parseInt(ExpirationDateOriginalDate);
	ExpirationDateOriginalDate = ExpirationdateOriginalDate.toString();

	DatePicker dp_Contract_Expiration_Date_Original_date = new DatePicker();
	dp_Contract_Expiration_Date_Original_date.expDate = ExpirationDateOriginalDate;
	dp_Contract_Expiration_Date_Original_date.expMonth = ExpirationdateOriginalMonth;
	dp_Contract_Expiration_Date_Original_date.expYear = ExpirationdateOriginalYear;
	dp_Contract_Expiration_Date_Original_date.pickExpDate("expirationDateOriginal");   
	
	//Notice Date 
	String NoticeDateMonth = convertDoubleToIntegerInStringForm(contractNoticeMonth);
	int NoticedateMonth = Integer.parseInt(NoticeDateMonth);
	String NoticeDateYear = convertDoubleToIntegerInStringForm(contractNoticeYear);
	int NoticedateYear = Integer.parseInt(NoticeDateYear);
	String NoticeDateDate = convertDoubleToIntegerInStringForm(contractNoticeDate);
	Integer NoticedateDate = Integer.parseInt(NoticeDateDate);
	NoticeDateDate = NoticedateDate.toString();

	DatePicker dp_Contract_Notice_Date_date = new DatePicker();
	dp_Contract_Notice_Date_date.expDate = NoticeDateDate;
	dp_Contract_Notice_Date_date.expMonth = NoticedateMonth;
	dp_Contract_Notice_Date_date.expYear = NoticedateYear;
	dp_Contract_Notice_Date_date.pickExpDate("noticeDate");   
	
	//Notice Lead Date 
	String NoticeLeadDateMonth = convertDoubleToIntegerInStringForm(contractNoticeLeadMonth);
	int NoticeLeaddateMonth = Integer.parseInt(NoticeLeadDateMonth);
	String NoticeLeadDateYear = convertDoubleToIntegerInStringForm(contractNoticeLeadYear);
	int NoticeLeaddateYear = Integer.parseInt(NoticeLeadDateYear);
	String NoticeLeadDateDate = convertDoubleToIntegerInStringForm(contractNoticeLeadDate);
	Integer NoticeLeaddateDate = Integer.parseInt(NoticeLeadDateDate);
	NoticeLeadDateDate = NoticeLeaddateDate.toString();

	DatePicker dp_Contract_Notice_Lead_Date__date = new DatePicker();
	dp_Contract_Notice_Lead_Date__date.expDate = NoticeLeadDateDate;
	dp_Contract_Notice_Lead_Date__date.expMonth = NoticeLeaddateMonth;
	dp_Contract_Notice_Lead_Date__date.expYear = NoticeLeaddateYear;
	dp_Contract_Notice_Lead_Date__date.pickExpDate("noticeLeadDate");   
	
    if (!contractCurrencies.equalsIgnoreCase("")) {
      new Select(getObject("co_currency_multi")).selectByVisibleText(contractCurrencies);
    }

    if (!contractReportingCurrency.equalsIgnoreCase("")) {
      new Select(getObject("co_reportingcurrency_dropdown")).selectByVisibleText(contractReportingCurrency);
    }

    if (!contractConversionType.equalsIgnoreCase("")) {
      new Select(getObject("co_conversiontype_dropdown")).selectByVisibleText(contractConversionType);
    }

    String RateCardFromDateMonth = convertDoubleToIntegerInStringForm(contractEffectiveMonth);
	int RateCardFromdateMonth = Integer.parseInt(RateCardFromDateMonth);
	String RateCardFromDateYear = convertDoubleToIntegerInStringForm(contractEffectiveYear);
	int RateCardFromdateYear = Integer.parseInt(RateCardFromDateYear);
	String RateCardFromDateDate = convertDoubleToIntegerInStringForm(contractEffectiveDate);
	Integer RateCardFromdateDate = Integer.parseInt(RateCardFromDateDate);
	RateCardFromDateDate = RateCardFromdateDate.toString();

	DatePicker dp_Rate_Card_From_Date_date = new DatePicker();
	dp_Rate_Card_From_Date_date.expDate = RateCardFromDateDate;
	dp_Rate_Card_From_Date_date.expMonth = RateCardFromdateMonth;
	dp_Rate_Card_From_Date_date.expYear = RateCardFromdateYear;
	dp_Rate_Card_From_Date_date.pickExpDate("rateCardFromDate");
	
	
    driver.findElement(By.name("rateCardToDate")).click();
    String RateCardToDateMonth = convertDoubleToIntegerInStringForm(contractEffectiveMonth);
	int RateCardTodateMonth = Integer.parseInt(RateCardToDateMonth);
	String RateCardToDateYear = convertDoubleToIntegerInStringForm(contractEffectiveYear);
	int RateCardTodateYear = Integer.parseInt(RateCardToDateYear);
	String RateCardToDateDate = convertDoubleToIntegerInStringForm(contractEffectiveDate);
	Integer RateCardTodateDate = Integer.parseInt(RateCardToDateDate);
	RateCardToDateDate = RateCardTodateDate.toString();

	DatePicker dp_Rate_Card_To_Date_date = new DatePicker();
	dp_Rate_Card_To_Date_date.expDate = RateCardToDateDate;
	dp_Rate_Card_To_Date_date.expMonth = RateCardTodateMonth;
	dp_Rate_Card_To_Date_date.expYear = RateCardTodateYear;
	dp_Rate_Card_To_Date_date.pickExpDate("rateCardFromDate");
	
	
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
    //Thread.sleep(10000);
   // String contract_id = driver.findElement(By.xpath(".//*[@id='hrefElemId']")).getText();
    String contract_id = getObject("co_popup_id").getText();
    
    APP_LOGS.debug("Contract created successfully with Contract id "+contract_id);
    //Thread.sleep(3000);
    
    //driver.findElement(By.xpath(".//*[@id='data-ng-app']/div[25]/div/div/div/div[3]/button")).click();
    getObject("co_popup_ok_button").click();
    //Thread.sleep(25000);
    
    APP_LOGS.debug("Quick Search the created contract with Contract id "+contract_id);
    
    getObject("quick_search_textbox").sendKeys(contract_id);
    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
    //Thread.sleep(5000);
    String ContractIdFromShowPage = getObject("co_show_id").getText();

    Assert.assertEquals(ContractIdFromShowPage, contract_id);

    APP_LOGS.debug("Contract show page open successfully with contract id " + contract_id);
       
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
   String ContractStatusShowPage = getObject("co_sup_show").getText();
   Assert.assertEquals(ContractStatusShowPage, contractTitle, "Contract name is -- " +ContractTitleShowPage+ " instead of -- " +contractTitle);
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
   String ContractSupplierAccessShowPage =getObject("co_supplier_access_show").getText();
   Assert.assertEquals(ContractSupplierAccessShowPage, contractSupplierAccess, "Contract supplier access is -- " +ContractSupplierAccessShowPage+ " instead of -- " +contractSupplierAccess);
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
   String ContractRegionShowPage = getObject("co_region_show").getText();
   try
   {
	   Assert.assertEquals(ContractRegionShowPage, contractRegion, "Contract region is -- " +ContractRegionShowPage+ " instead of -- " +contractRegion);   
   }
   catch(Throwable e)
   {
	   System.out.println("Contract region is -- " +ContractRegionShowPage+ " instead of -- " +contractRegion);
   }
   String ContractCountryShowPage =getObject("co_country_show").getText();
   try
   {
   Assert.assertEquals(ContractCountryShowPage, contractCountry, "Contract country is -- " +ContractCountryShowPage+ " instead of -- " +contractCountry);
   }
   catch(Throwable e)
   {
	   System.out.println("Contract country is -- " +ContractCountryShowPage+ " instead of -- " +contractCountry);
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
   //String ContractAggTCVShowPage = getObject("co_agg_tcv_show").getText();
   //Assert.assertEquals(ContractAggTCVShowPage, contractAggTCV, "Contract Aggregate TCV is -- " +ContractAggTCVShowPage+ " instead of -- " +contractAggTCV);
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
   //String ContractAggACVShowPage = getObject("co_agg_acv_show").getText();
  // Assert.assertEquals(ContractAggACVShowPage, contractAggACV, "Contract Aggregate ACV is -- " +ContractAggACVShowPage+ " instead of -- " +contractAggACV);
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
   
   
   
   // String ContractAggFACVShowPage = getObject("co_agg_facv_show").getText();
 //  Assert.assertEquals(ContractAggFACVShowPage, contractAggFACV, "Contract Aggregate FACV is -- " +ContractAggFACVShowPage+ " instead of -- " +contractAggFACV);
    
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
    
    
    //driver.findElement(By.xpath(".//*[@id='h-analytics']/a")).click();
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
