package test.resources.com.sirion.suite.invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.resources.com.sirion.util.TestUtil;

public class InvoiceUpdate extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(inv_suite_xls, this.getClass()
				.getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(inv_suite_xls, this.getClass()
				.getSimpleName());
	}

	@Test(groups = "InvoiceCreation", dataProvider = "getTestData")
	public void InvoiceupdateTest(String invInvoiceNumber, 
			String  invTitle, String invPoNumber, String invCurrency, String invTimezone, String invContractEntity, 
			   String invAmount, String   invSupplierAccess, String   invTier, String invPeriodStartDate, 
			   String  invPeriodEndDate, String invCreditDays, String  invInvoiceExpectedReceiptDate, 
			   String invInvoicePaymentDueDate,String invAmountApproved, String invDiscrepancyAmount, 
			   String invNoOfLineItems, String invNoOfLineItemsWithDiscrepancy,String invDisputeAmount) throws InterruptedException {

		
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "+ count);
		}

		APP_LOGS.debug("Executing Test Case Interpretation Creation from Action");

		// Calling method for opening browser from TestBase.java file
		//openBrowser();

		// Calling a method for login(at different platform) from TestBase.java file
		//endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
		
		/*WebDriverWait wait=new WebDriverWait(driver, 500);
		Thread.sleep(10000);
	    wait.until(ExpectedConditions.visibilityOf(getObject("int_quick_link")));
	    getObject("invoice_quick_link").click(); // IP Quick Link Clicking
	    Thread.sleep(8000);
	    driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();*/
	    Thread.sleep(15000);
	    driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
	    Thread.sleep(10000);
		 if (!invInvoiceNumber.equalsIgnoreCase("")) {
			 getObject("inv_inv_no_textbox").clear();
			   getObject("inv_inv_no_textbox").sendKeys(invInvoiceNumber); // name
			 }

			 if (!invTitle.equalsIgnoreCase("")) {
				 getObject("inv_title_textbox").clear();
			   getObject("inv_title_textbox").sendKeys(invTitle); // title
			 }
			 if (!invPoNumber.equalsIgnoreCase("")) {
				 getObject("inv_po_number_textbox").clear();
			   getObject("inv_po_number_textbox").sendKeys(invPoNumber);
			 }
			 if (!invCurrency.equalsIgnoreCase("")) {
			   new Select(getObject("inv_currency_dropdown")).selectByVisibleText(invCurrency);
			 }
			 if (!invTimezone.equalsIgnoreCase("")) {
			   new Select(getObject("inv_timezone_dropdown")).selectByVisibleText(invTimezone);
			   try {
					if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
						driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
				} catch (Exception e) {
					
				}
			 }
			 if (!invContractEntity.equalsIgnoreCase("")) {
				 new Select(getObject("inv_contract_entity_dropdown")).selectByVisibleText(invContractEntity);
			 }
			 if (!invAmount.equalsIgnoreCase("")) {
				 getObject("inv_inv_amount_textbox").clear();
			   getObject("inv_inv_amount_textbox").sendKeys(invAmount);
			 }

			 if (invSupplierAccess.equalsIgnoreCase("yes")) {
			   getObject("inv_supplier_access_checkbox").click();
			 }

			 if (!invTier.equalsIgnoreCase("")) {
			   new Select(getObject("inv_tier_dropdown")).selectByVisibleText(invTier);
			 }
			 
			 if (!invPeriodStartDate.equalsIgnoreCase("")) {
					driver.findElement(By.name("invoicePeriodFromDate")).click();
					String[] invDate = invPeriodStartDate.split("-");

					String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
					while (!invMonth.equalsIgnoreCase(invDate[0])) {
						driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
						invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
						}

					new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);

					driver.findElement(By.linkText(invDate[1])).click();
					Thread.sleep(3000);
					}
			 
			 if (!invPeriodEndDate.equalsIgnoreCase("")) {
					driver.findElement(By.name("invoicePeriodToDate")).click();
					String[] invDate = invPeriodEndDate.split("-");

					String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
					while (!invMonth.equalsIgnoreCase(invDate[0])) {
						driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
						invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
						}

					new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);

					driver.findElement(By.linkText(invDate[1])).click();
					Thread.sleep(3000);
					}
			 
			  
			 if (!invCreditDays.equalsIgnoreCase("")) {
				 getObject("inv_credit_days").clear();
			   getObject("inv_credit_days").sendKeys(invCreditDays);
			 }
			  
			 if (!invInvoiceExpectedReceiptDate.equalsIgnoreCase("")) {
					driver.findElement(By.name("expectedReceiptDate")).click();
					String[] invDate = invInvoiceExpectedReceiptDate.split("-");

					String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
					while (!invMonth.equalsIgnoreCase(invDate[0])) {
						driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
						invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
						}

					new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);

					driver.findElement(By.linkText(invDate[1])).click();
					Thread.sleep(3000);
					}
			  
			 if (!invInvoicePaymentDueDate.equalsIgnoreCase("")) {
					driver.findElement(By.id("elem_617")).click();
											  //paymentDueDate
					String[] invDate = invInvoicePaymentDueDate.split("-");

					String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
					while (!invMonth.equalsIgnoreCase(invDate[0])) {
						driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
						invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
						}

					new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);

					driver.findElement(By.linkText(invDate[1])).click();
					Thread.sleep(3000);
					}
			 
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
			  
			  getObject("inv_update_page_update_button").click();
			
			  Thread.sleep(8000);
			  
			  String invID = driver.findElement(By.xpath("//*[@id='elem_656']/span")).getText();
			  
			  APP_LOGS.debug("Invoice updated successfully with invoice id " + invID);
			 
			 
			 
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
			 
			 String InvoiceSupplierShowPage= getObject("inv_supplier_show").getText();
			 APP_LOGS.debug("Invoice Supplier Name is== "+InvoiceSupplierShowPage);
			 
			 String InvoiceContractShowPage= getObject("inv_contract_show").getText();
			 
			 APP_LOGS.debug("Invoice Contract Name is== "+InvoiceContractShowPage);
			 
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
			 
			 fail = false; // this executes if assertion passes

			 }

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(inv_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(inv_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(inv_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(inv_suite_xls, "Test Cases", TestUtil.getRowNum(inv_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(inv_suite_xls, "Test Cases", TestUtil.getRowNum(inv_suite_xls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(inv_suite_xls, this.getClass().getSimpleName());
	}

}
