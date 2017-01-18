package test.resources.com.sirion.suite.contract;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class InvoiceCreation extends TestSuiteBase {
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
  public void InvCreation(
   String invInvoiceNumber, String  invTitle, String invPoNumber, String invCurrency, String invTimezone, String invContractEntity, 
   String invAmount, String   invSupplierAccess, String   invTier, String invPeriodStartDateMonth, String invPeriodStartDateDate, 
   String invPeriodStartDateYear, String  invPeriodEndDateMonth, String   invPeriodEndDateDate, String invPeriodEndDateYear, String invCreditDays, 
   String invInvoiceDateMonth, String invInvoiceDateDate, String  invInvoiceDateYear, String  invInvoiceExpectedReceiptDateMonth, 
   String invInvoiceExpectedReceiptDateDate, String   invInvoiceExpectedReceiptDateYear, 
   String invInvoicePaymentDueDateMonth,  String invInvoicePaymentDueDateDate, String invInvoicePaymentDueDateYear,
   String invInvoicePaymentDateMonth,  String invInvoicePaymentDateDate, String invInvoicePaymentDateYear,
   String invInvoiceReceiptDueDateMonth, String invInvoiceReceiptDueDateDate, String invInvoiceReceiptDueDateYear, 
   String invAmountApproved, String invDiscrepancyAmount, String invNoOfLineItems, String invNoOfLineItemsWithDiscrepancy, 
   String invPaidAmount, String invResolvedDiscrepancy, String invDisputeAmount) throws InterruptedException {
 // test the runmode of current dataset
 count++;
 if (!runmodes[count].equalsIgnoreCase("Y")) {
   skip = true;
   throw new SkipException("Runmode for test set data set to no " + count);
 }

 APP_LOGS.debug(" Executing Test Case Invoice Creation");

//Calling method for opening browser from TestBase.java file
	openBrowser();


	// Calling a method for login(at different platform) from TestBase.java file
	endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));

	
	getObject("analytics_link").click();
getObject("contract_quick_link").click(); // IP Quick Link Clicking
//Thread.sleep(20000);
WebDriverWait wait=new WebDriverWait(driver, 50);
//wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_id_link")));
getObject("ac_id_link").click(); 
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
  plus_button("ac_plus_button_link"); // web element for plus button on supplier page
  wait.until(ExpectedConditions.elementToBeClickable(getObject("inv_create_link_from_contract")));
getObject("inv_create_link_from_contract").click(); // click issue create link 

//Thread.sleep(10000);
 

 if (!invInvoiceNumber.equalsIgnoreCase("")) {
   getObject("inv_inv_no_textbox").sendKeys(invInvoiceNumber); // name
 }

 if (!invTitle.equalsIgnoreCase("")) {
   getObject("inv_title_textbox").sendKeys(invTitle); // title
 }
 if (!invPoNumber.equalsIgnoreCase("")) {
   getObject("inv_po_number_textbox").sendKeys(invPoNumber);
 }
 if (!invCurrency.equalsIgnoreCase("")) {
   new Select(getObject("inv_currency_dropdown")).selectByVisibleText(invCurrency);
 }
 if (!invTimezone.equalsIgnoreCase("")) {
   new Select(getObject("inv_timezone_dropdown")).selectByVisibleText(invTimezone);
   try {
		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	} catch (Exception e) {
		
	}
 }
 if (!invContractEntity.equalsIgnoreCase("")) {
   getObject("inv_contract_entity_dropdown").sendKeys(invContractEntity);
 }
 if (!invAmount.equalsIgnoreCase("")) {
   getObject("inv_inv_amount_textbox").sendKeys(invAmount);
 }

 if (invSupplierAccess.equalsIgnoreCase("yes")) {
   getObject("inv_supplier_access_checkbox").click();
 }

 if (!invTier.equalsIgnoreCase("")) {
   new Select(getObject("inv_tier_dropdown")).selectByVisibleText(invTier);
 }
 
 //Thread.sleep(10000);
 Date date = new Date();

 int current_month = date.getMonth();

 driver.findElement(By.name("invoicePeriodFromDate")).click();
 Double temp_invPeriodStartDateYear_double = Double.parseDouble(invPeriodStartDateYear);
 int temp_invPeriodStartDateYear_int = temp_invPeriodStartDateYear_double.intValue();
 String invPeriodStartDateYear_string = Integer.toString(temp_invPeriodStartDateYear_int);

 WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
 System.out.println(datepicker_ui.isDisplayed());
 if (datepicker_ui.isDisplayed() == true) {
   WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
   new Select(datepicker_ui_year).selectByVisibleText(invPeriodStartDateYear_string);
 }

 Double temp_invPeriodStartDateMonth_double = Double.parseDouble(invPeriodStartDateMonth);
 int temp_invPeriodStartDateMonth_int = temp_invPeriodStartDateMonth_double.intValue();
 System.out.println(" invPeriodStartDateMonth " + temp_invPeriodStartDateMonth_int);

 int click2 = current_month - temp_invPeriodStartDateMonth_int;
 System.out.println("click " + click2);
 for (; click2 >= 0; click2 = click2 - 1) {
   driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
 }
 Double temp_invPeriodStartDateDate_double = Double.parseDouble(invPeriodStartDateDate);
 int temp_invPeriodStartDateDate_int = temp_invPeriodStartDateDate_double.intValue();
 String invPeriodStartDateDate_string = Integer.toString(temp_invPeriodStartDateDate_int);
 driver.findElement(By.linkText(invPeriodStartDateDate_string)).click();
 
 
 driver.findElement(By.name("invoicePeriodToDate")).click();

 Double temp_invPeriodEndDateYear_double = Double.parseDouble(invPeriodEndDateYear);
 int temp_invPeriodEndDateYear_int = temp_invPeriodEndDateYear_double.intValue();
 String invPeriodEndDateYear_string = Integer.toString(temp_invPeriodEndDateYear_int);

 System.out.println(datepicker_ui.isDisplayed());
 if (datepicker_ui.isDisplayed() == true) {
   WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
   new Select(datepicker_ui_year).selectByVisibleText(invPeriodEndDateYear_string);
 }

 Double temp_invPeriodEndDateMonth_double = Double.parseDouble(invPeriodEndDateMonth);
 int temp_invPeriodEndDateMonth_int = temp_invPeriodEndDateMonth_double.intValue();
 System.out.println(" invPeriodEndDateMonth " + temp_invPeriodEndDateMonth_int);

 int click_invPeriodEndDateMonth = temp_invPeriodEndDateMonth_int - current_month;
 System.out.println("click_invPeriodEndDateMonth " + click_invPeriodEndDateMonth);
 for (; click_invPeriodEndDateMonth >= 0; click_invPeriodEndDateMonth = click_invPeriodEndDateMonth - 1) {
   driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
 }
 Double temp_invPeriodEndDateDate_double = Double.parseDouble(invPeriodEndDateDate);
 int temp_invPeriodEndDateDate_int = temp_invPeriodEndDateDate_double.intValue();
 String invPeriodEndDateDate_string = Integer.toString(temp_invPeriodEndDateDate_int);
 driver.findElement(By.linkText(invPeriodEndDateDate_string)).click();
 
 
/* Double temp_invCreditDays_double = Double.parseDouble(invCreditDays);
 int temp_invCreditDays_int = temp_invCreditDays_double.intValue();
 String invCreditDays_string = Integer.toString(temp_invCreditDays_int);*/
 
  if (!invCreditDays.equalsIgnoreCase("")) {
   getObject("inv_credit_days").sendKeys(invCreditDays);
 }
  
  
  
  
 
  
  //Thread.sleep(10000);
  
  
  
  
  /*driver.findElement(By.name("invoiceDate")).click();

  Double temp_invInvoiceDateYear_double = Double.parseDouble(invInvoiceDateYear);
  int temp_invInvoiceDateYear_int = temp_invInvoiceDateYear_double.intValue();
  String invInvoiceDateYear_string = Integer.toString(temp_invInvoiceDateYear_int);

  System.out.println(datepicker_ui.isDisplayed());
  if (datepicker_ui.isDisplayed() == true) {
    WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
    new Select(datepicker_ui_year).selectByVisibleText(invInvoiceDateYear_string);
  }

  Double temp_invInvoiceDateMonth_double = Double.parseDouble(invInvoiceDateMonth);
  int temp_invInvoiceDateMonth_int = temp_invInvoiceDateMonth_double.intValue();
  System.out.println(" invInvoiceDateMonth " + temp_invInvoiceDateMonth_int);

  int click_invInvoiceDateMonth = temp_invInvoiceDateMonth_int - current_month;
  System.out.println("click " + click_invInvoiceDateMonth);
  for (; click_invInvoiceDateMonth >= 0; click_invInvoiceDateMonth = click_invInvoiceDateMonth - 1) {
    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
  }
  Double temp_invInvoiceDateDate_double = Double.parseDouble(invInvoiceDateDate);
  
  int temp_invInvoiceDateDate_int = temp_invInvoiceDateDate_double.intValue();
  String invInvoiceDateDate_string = Integer.toString(temp_invInvoiceDateDate_int);
  driver.findElement(By.linkText(invInvoiceDateDate_string)).click(); */
  

 /* driver.findElement(By.name("expectedReceiptDate")).click();

  Double temp_invInvoiceExpectedReceiptDateYear_double = Double.parseDouble(invInvoiceExpectedReceiptDateYear);
  int temp_invInvoiceExpectedReceiptDateYear_int = temp_invInvoiceExpectedReceiptDateYear_double.intValue();
  String invInvoiceExpectedReceiptDateYear_string = Integer.toString(temp_invInvoiceExpectedReceiptDateYear_int);

  System.out.println(datepicker_ui.isDisplayed());
  if (datepicker_ui.isDisplayed() == true) {
    WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
    new Select(datepicker_ui_year).selectByVisibleText(invInvoiceExpectedReceiptDateYear_string);
  }

  Double temp_invInvoiceExpectedReceiptDateMonth_double = Double.parseDouble(invInvoiceExpectedReceiptDateMonth);
  int temp_invInvoiceExpectedReceiptDateMonth_int = temp_invInvoiceExpectedReceiptDateMonth_double.intValue();
  System.out.println(" invInvoiceExpectedReceiptDateMonth " + temp_invInvoiceExpectedReceiptDateMonth_int);

  int click_invInvoiceExpectedReceiptDateMonth = temp_invInvoiceExpectedReceiptDateMonth_int - current_month;
  System.out.println("click " + click_invInvoiceExpectedReceiptDateMonth);
  for (; click_invInvoiceExpectedReceiptDateMonth >= 0; click_invInvoiceExpectedReceiptDateMonth = click_invInvoiceExpectedReceiptDateMonth - 1) {
    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
  }
  Double temp_invInvoiceExpectedReceiptDateDate_double = Double.parseDouble(invInvoiceExpectedReceiptDateDate);
  int temp_invInvoiceExpectedReceiptDateDate_int = temp_invInvoiceExpectedReceiptDateDate_double.intValue();
  String invInvoiceExpectedReceiptDateDate_string = Integer.toString(temp_invInvoiceExpectedReceiptDateDate_int);
  driver.findElement(By.linkText(invInvoiceExpectedReceiptDateDate_string)).click(); */ 
  
  
  
  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.name("paymentDueDate")));
  Thread.sleep(500); 
  driver.findElement(By.name("paymentDueDate")).click();

  Double temp_invInvoicePaymentDueDateYear_double = Double.parseDouble(invInvoicePaymentDueDateYear);
  int temp_invInvoicePaymentDueDateYear_int = temp_invInvoicePaymentDueDateYear_double.intValue();
  String invInvoicePaymentDueDateYear_string = Integer.toString(temp_invInvoicePaymentDueDateYear_int);

  System.out.println(datepicker_ui.isDisplayed());
  if (datepicker_ui.isDisplayed() == true) {
    WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
    new Select(datepicker_ui_year).selectByVisibleText(invInvoicePaymentDueDateYear_string);
  }

  Double temp_invInvoicePaymentDueDateMonth_double = Double.parseDouble(invInvoicePaymentDueDateMonth);
  int temp_invInvoicePaymentDueDateMonth_int = temp_invInvoicePaymentDueDateMonth_double.intValue();
  System.out.println(" invInvoicePaymentDueDateMonth " + temp_invInvoicePaymentDueDateMonth_int);

  int click_invInvoicePaymentDueDateMonth = temp_invInvoicePaymentDueDateMonth_int - current_month;
  System.out.println("click_invInvoicePaymentDueDateMonth " + click_invInvoicePaymentDueDateMonth);
  for (; click_invInvoicePaymentDueDateMonth >= 0; click_invInvoicePaymentDueDateMonth = click_invInvoicePaymentDueDateMonth - 1) {
    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
  }
  Double temp_invInvoicePaymentDueDateDate_double = Double.parseDouble(invInvoicePaymentDueDateDate);
  int temp_invInvoicePaymentDueDateDate_int = temp_invInvoicePaymentDueDateDate_double.intValue();
  String invInvoicePaymentDueDateDate_string = Integer.toString(temp_invInvoicePaymentDueDateDate_int);
  driver.findElement(By.linkText(invInvoicePaymentDueDateDate_string)).click();
  

  /*driver.findElement(By.name("actualPaymentDate")).click();

  Double temp_invInvoicePaymentDateYear_double = Double.parseDouble(invInvoicePaymentDateYear);
  int temp_invInvoicePaymentDateYear_int = temp_invInvoicePaymentDateYear_double.intValue();
  String invInvoicePaymentDateYear_string = Integer.toString(temp_invInvoicePaymentDateYear_int);

  System.out.println(datepicker_ui.isDisplayed());
  if (datepicker_ui.isDisplayed() == true) {
    WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
    new Select(datepicker_ui_year).selectByVisibleText(invInvoicePaymentDateYear_string);
  }

  Double temp_invInvoicePaymentDateMonth_double = Double.parseDouble(invInvoicePaymentDateMonth);
  int temp_invInvoicePaymentDateMonth_int = temp_invInvoicePaymentDateMonth_double.intValue();
  System.out.println(" invInvoicePaymentDateMonth " + temp_invInvoicePaymentDateMonth_int);

  int click_invInvoicePaymentDateMonth = temp_invInvoicePaymentDateMonth_int - current_month;
  System.out.println("click " + click_invInvoicePaymentDateMonth);
  for (; click_invInvoicePaymentDateMonth >= 0; click_invInvoicePaymentDateMonth = click_invInvoicePaymentDateMonth - 1) {
    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
  }
  Double temp_invInvoicePaymentDateDate_double = Double.parseDouble(invInvoicePaymentDateDate);
  int temp_invInvoicePaymentDateDate_int = temp_invInvoicePaymentDateDate_double.intValue();
  String invInvoicePaymentDateDate_string = Integer.toString(temp_invInvoicePaymentDateDate_int);
  driver.findElement(By.linkText(invInvoicePaymentDateDate_string)).click();
*/  
 
  driver.findElement(By.name("actualReceiptDate")).click();

  Double temp_invInvoiceReceiptDueDateYear_double = Double.parseDouble(invInvoiceReceiptDueDateYear);
  int temp_invInvoiceReceiptDueDateYear_int = temp_invInvoiceReceiptDueDateYear_double.intValue();
  String invInvoiceReceiptDueDateYear_string = Integer.toString(temp_invInvoiceReceiptDueDateYear_int);

  System.out.println(datepicker_ui.isDisplayed());
  if (datepicker_ui.isDisplayed() == true) {
    WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
    new Select(datepicker_ui_year).selectByVisibleText(invInvoiceReceiptDueDateYear_string);
  }

  Double temp_invInvoiceReceiptDueDateMonth_double = Double.parseDouble(invInvoiceReceiptDueDateMonth);
  int temp_invInvoiceReceiptDueDateMonth_int = temp_invInvoiceReceiptDueDateMonth_double.intValue();
  System.out.println(" invInvoiceReceiptDueDateMonth " + temp_invInvoiceReceiptDueDateMonth_int);

  int click_invInvoiceReceiptDueDateMonth = temp_invInvoiceReceiptDueDateMonth_int - current_month;
  System.out.println("click " + click_invInvoiceReceiptDueDateMonth);
  for (; click_invInvoiceReceiptDueDateMonth >= 0; click_invInvoiceReceiptDueDateMonth = click_invInvoiceReceiptDueDateMonth - 1) {
    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
  }
  Double temp_invInvoiceReceiptDueDateDate_double = Double.parseDouble(invInvoiceReceiptDueDateDate);
  int temp_invInvoiceReceiptDueDateDate_int = temp_invInvoiceReceiptDueDateDate_double.intValue();
  String invInvoiceReceiptDueDateDate_string = Integer.toString(temp_invInvoiceReceiptDueDateDate_int);
  driver.findElement(By.linkText(invInvoiceReceiptDueDateDate_string)).click();
  
  //Thread.sleep(10000);
  
  if (!invAmountApproved.equalsIgnoreCase("")) {
	  getObject("inv_amount_approved_textbox").clear();
    getObject("inv_amount_approved_textbox").sendKeys(invAmountApproved);
  }
 
  if (!invDiscrepancyAmount.equalsIgnoreCase("")) {
	  getObject("inv_discrepancy_amount_textbox").clear();
    getObject("inv_discrepancy_amount_textbox").sendKeys(invDiscrepancyAmount);
  } 
 
  if (!invNoOfLineItems.equalsIgnoreCase("")) {
    Double temp_invNoOfLineItems_double = Double.parseDouble(invNoOfLineItems);
    int temp_invNoOfLineItems_int = temp_invNoOfLineItems_double.intValue();
    String invNoOfLineItems_string = Integer.toString(temp_invNoOfLineItems_int);
    getObject("inv_no_of_line_items_textbox").sendKeys(invNoOfLineItems_string);
  } 
 
  if (!invNoOfLineItemsWithDiscrepancy.equalsIgnoreCase("")) {
	  getObject("inv_no_of_line_items_with_discrepancy_textbox").clear();  
    getObject("inv_no_of_line_items_with_discrepancy_textbox").sendKeys(invNoOfLineItemsWithDiscrepancy);
  } 
  
  if (!invPaidAmount.equalsIgnoreCase("")) {
	  getObject("inv_paid_amount_textbox").clear();
    getObject("inv_paid_amount_textbox").sendKeys(invPaidAmount);
  } 
  
  if (!invResolvedDiscrepancy.equalsIgnoreCase("")) {
	  getObject("inv_resolved_discrepancy_textbox").clear();
    getObject("inv_resolved_discrepancy_textbox").sendKeys(invResolvedDiscrepancy);
  } 
  

  getObject("inv_save_button").click();
 String inv_id = getObject("inv_popup_id").getText();

 APP_LOGS.debug("Invoice created successfully with Invoice id " + inv_id);

 getObject("inv_popup_ok_button").click();

 APP_LOGS.debug("Quick Search the created action with Invoice id " + inv_id);

   getObject("quick_search_textbox").sendKeys(inv_id);
 //getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
 getObject("quick_search_textbox").sendKeys(Keys.ENTER);
 /*String invIdFromShowPage = getObject("inv_show_id").getText();

 Assert.assertEquals(invIdFromShowPage, inv_id);

 APP_LOGS.debug("Invoice show page open successfully with invoice id " + inv_id);*/
 
 
 
 
 
 /*
 String InvoiceStatusShowPage= getObject("inv_status_show").getText();
 try
 {
	  
 
 Assert.assertEquals(InvoiceStatusShowPage,invStatus , "Invoice Status name is -- " +InvoiceStatusShowPage+ " instead of -- " +invStatus);
 
 }
 catch(Throwable e)
 {
	  
 System.out.println("Invoice Status name is -- " +InvoiceStatusShowPage+ " instead of -- " +invStatus);
 
 }
 
 
 String InvoiceReceiptStatusShowPage= getObject("inv_receiptstatus_show").getText();
 try
 {
 Assert.assertEquals(InvoiceReceiptStatusShowPage, invReceiptStatus, "Invoice Receipt Status is--"+InvoiceReceiptStatusShowPage+"instead of" + invReceiptStatus );
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Receipt Status  is--"+InvoiceReceiptStatusShowPage+"instead of" + invReceiptStatus );
 }
 
 
 String InvoicePaymentStatusShowPage= getObject("inv_paymentstatus_show").getText();
 try
 {
 Assert.assertEquals(InvoicePaymentStatusShowPage, invPaymentStatus, "Invoice Payment Status is--"+InvoicePaymentStatusShowPage+"instead of" + invPaymentStatus );
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Payment Status is--"+InvoicePaymentStatusShowPage+"instead of" + invPaymentStatus );
 }
 
 String InvoiceInvoiceNumberShowPage= getObject("inv_invoicenumber_show").getText();
 try
 {
 Assert.assertEquals(InvoiceInvoiceNumberShowPage, invInvoiceNumber, "Invoice Invoice Number is--"+InvoiceInvoiceNumberShowPage+"instead of" + invInvoiceNumber );
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Invoice Number is--"+InvoiceInvoiceNumberShowPage+"instead of" + invInvoiceNumber );
 }
 
 String InvoiceTitleShowPage= getObject("inv_title_show").getText();
 try
 {
 Assert.assertEquals(InvoiceTitleShowPage, invTitle, "Invoice Title is -- " +InvoiceTitleShowPage+ " instead of -- " +invTitle);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Title is -- " +InvoiceTitleShowPage+ " instead of -- " +invTitle);
 }
 
 String InvoicePONumberShowPage= getObject("inv_po_number_show").getText();
 try
 {
 Assert.assertEquals(InvoicePONumberShowPage, invPoNumber, "Invoice PO Number is--"+InvoicePONumberShowPage+"instead of" + invPoNumber );
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice PO Number is--"+InvoicePONumberShowPage+"instead of" + invPoNumber );
 }
 
 
 String InvoiceCurrencyShowPage= getObject("inv_currency_show").getText();
 try
 {
 Assert.assertEquals(InvoiceCurrencyShowPage, invCurrency, "Invoice Currency is== "+InvoiceCurrencyShowPage +"instead of" + invCurrency);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Currency is== "+InvoiceCurrencyShowPage +"instead of" + invCurrency);
 }
 
 String InvoiceSupplierShowPage= getObject("inv_supplier_show=").getText();
 try
 {
 Assert.assertEquals(InvoiceSupplierShowPage, invSupName, "Invoice Supplier Name is== "+InvoiceSupplierShowPage +"instead of" + invSupName);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Supplier Name is== "+InvoiceSupplierShowPage +"instead of" + invSupName);
 }
 
 
 String InvoiceContractShowPage= getObject("inv_contract_show=").getText();
 try
 {
 Assert.assertEquals(InvoiceContractShowPage, invConName, "Invoice Contract Name is== "+InvoiceContractShowPage +"instead of" + invConName);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Contract Name is== "+InvoiceContractShowPage +"instead of" + invConName);
 }
 
 
 String InvoiceTimeZoneShowPage= getObject("inv_timezone_show").getText();
 try
 {
 Assert.assertEquals(InvoiceTimeZoneShowPage, invTimezone, "Invoice TimeZone is== "+InvoiceTimeZoneShowPage +"instead of" + invTimezone);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice TimeZone is== "+InvoiceTimeZoneShowPage +"instead of" + invTimezone);
 }
 
 
 String InvoiceContractEntityShowPage= getObject("inv_contract_entity_show").getText();
 try
 {
 Assert.assertEquals(InvoiceContractEntityShowPage, invContractEntity, "Invoice Contract Entity is== "+InvoiceContractEntityShowPage +"instead of" + invContractEntity);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Contract Entity is== "+InvoiceContractEntityShowPage +"instead of" + invContractEntity);
 }
 
 
 String InvoiceInvoiceAmountShowPage= getObject("inv_inv_amount_show").getText();
 try
 {
 Assert.assertEquals(InvoiceInvoiceAmountShowPage, invAmount, "Invoice Amount is== "+InvoiceInvoiceAmountShowPage +"instead of" + invAmount);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Amount is== "+InvoiceInvoiceAmountShowPage +"instead of" + invAmount);
 }
 
 
 String InvoiceSupplierAccessShowPage= getObject("inv_supplier_access_show").getText();
 try
 {
 Assert.assertEquals(InvoiceSupplierAccessShowPage, invSupplierAccess, "Invoice Supplier Access is== "+InvoiceSupplierAccessShowPage +"instead of" + invSupplierAccess);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Supplier Access is== "+InvoiceSupplierAccessShowPage +"instead of" + invSupplierAccess);
 }
 
 
 String InvoiceTierShowPage= getObject("inv_tier_show").getText();
 try
 {
 Assert.assertEquals(InvoiceTierShowPage, invTier, "Invoice Tier is== "+InvoiceTierShowPage +"instead of" + invTier);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Tier is== "+InvoiceTierShowPage +"instead of" + invTier);
 }
 
 
 String InvoiceCreditPeriodShowPage= getObject("inv_credit_show").getText();
 try
 {
 Assert.assertEquals(InvoiceCreditPeriodShowPage, invCreditDays, "Invoice Credit Period is== "+InvoiceCreditPeriodShowPage +"instead of" + invCreditDays);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Credit Period is== "+InvoiceCreditPeriodShowPage +"instead of" + invCreditDays);
 }
 
 

 
 
 String InvoiceFunctionsShowPage= getObject("inv_sup_functions_show").getText();
 try
 {
 Assert.assertEquals(InvoiceFunctionsShowPage, supFunctions, "Invoice Functions are== "+InvoiceFunctionsShowPage +"instead of" + supFunctions);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Functions are== "+InvoiceFunctionsShowPage +"instead of" + supFunctions);
 }
 String InvoiceServicesShowPage= getObject("inv_sup_services_show").getText();
 try
 {
	  Assert.assertEquals(InvoiceServicesShowPage, supServices, "Invoice Services are== "+InvoiceServicesShowPage +"instead of" + supServices);  
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Services are== "+InvoiceServicesShowPage +"instead of" + supServices);
 }
 String InvoiceRegionsShowPage= getObject("inv_sup_regions_show").getText();
 try
 {
 Assert.assertEquals(InvoiceRegionsShowPage, supRegions, "Invoice Regions are== "+InvoiceRegionsShowPage +"instead of" + supRegions);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Regions are== "+InvoiceRegionsShowPage +"instead of" + supRegions);
 }
 String InvoiceCountriesShowPage= getObject("inv_sup_countries_show").getText();
 try
 {
 Assert.assertEquals(InvoiceCountriesShowPage, supCountries, "Invoice Countries are== "+InvoiceCountriesShowPage +"instead of" + supCountries);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Countries are== "+InvoiceCountriesShowPage +"instead of" + supCountries);
 }
 String InvoiceAmountApprovedShowPage= getObject("inv_amount_approved_show").getText();
 try
 {
 Assert.assertEquals(InvoiceAmountApprovedShowPage, invAmountApproved, "Invoice Amount Approved is== "+InvoiceAmountApprovedShowPage +"instead of" + invAmountApproved);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Amount Approved is== "+InvoiceAmountApprovedShowPage +"instead of" + invAmountApproved);
 }
 
 
 String InvoiceDiscrepencyAmountShowPage= getObject("inv_discrepancy_amount_show").getText();
 try
 {
 Assert.assertEquals(InvoiceDiscrepencyAmountShowPage, invDiscrepancyAmount, "Invoice Discrepency Amount is== "+InvoiceDiscrepencyAmountShowPage +"instead of" + invDiscrepancyAmount);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Discrepency Amount is== "+InvoiceDiscrepencyAmountShowPage +"instead of" + invDiscrepancyAmount);
 }
 
 
 String InvoiceNoofLineItemsShowPage= getObject("inv_no_of_line_items_show").getText();
 try
 {
 Assert.assertEquals(InvoiceNoofLineItemsShowPage, invNoOfLineItems, "Invoice No. of Line with is== "+InvoiceNoofLineItemsShowPage +"instead of" + invNoOfLineItems);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice No. of Line with is== "+InvoiceNoofLineItemsShowPage +"instead of" + invNoOfLineItems);
 }
 
 
 String InvoiceNoofLineItemswithDiscrepencyShowPage= getObject("inv_no_of_line_items_with_discrepancy_show").getText();
 try
 {
 Assert.assertEquals(InvoiceNoofLineItemswithDiscrepencyShowPage, invNoOfLineItemsWithDiscrepancy, "Invoice No. of Line with Discrepency is== "+InvoiceNoofLineItemswithDiscrepencyShowPage +"instead of" + invNoOfLineItemsWithDiscrepancy);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice No. of Line with Discrepency is== "+InvoiceNoofLineItemswithDiscrepencyShowPage +"instead of" + invNoOfLineItemsWithDiscrepancy);
 }
 
 String InvoicePaidAmountShowPage= getObject("inv_paid_amount_show").getText();
 try
 {
 Assert.assertEquals(InvoicePaidAmountShowPage, invPaidAmount, "Invoice paid amount is== "+InvoicePaidAmountShowPage +"instead of" + invPaidAmount);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice paid amount is== "+InvoicePaidAmountShowPage +"instead of" + invPaidAmount);
 }
 
 String InvoiceResolvedDiscrepencyAmountShowPage= getObject("inv_resolved_discrepancy_show").getText();
 try
 {
 Assert.assertEquals(InvoiceResolvedDiscrepencyAmountShowPage, invResolvedDiscrepancy, "Invoice Resolved Discrepency Amount is== "+InvoiceResolvedDiscrepencyAmountShowPage +"instead of" + invResolvedDiscrepancy);
 }
 catch(Throwable e)
 {
	  System.out.println("Invoice Resolved Discrepency Amount is== "+InvoiceResolvedDiscrepencyAmountShowPage +"instead of" + invResolvedDiscrepancy);
 }*/
 
 
 
 
 
 
 
 fail = false; // this executes if assertion passes

 getObject("analytics_link").click();
// getObject("supplier_quick_link").click();

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
