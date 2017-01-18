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

public class UpdateCreditnEarnback extends TestSuiteBase {
	
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
	@Test(groups = "ContractCreditnEarnback", dataProvider = "getTestData")
	  public void ContractCreditnEarnback(String ContractName,String CreditnEarnbackName, String CreditnEarnbackUpdateName,String CreditnEarnbackDescription, 
			  String CreditnEarnbackCurrency,String CreditnEarnbackInvoiceYear, String CreditnEarnbackInvoiceMonth, 
			  String CreditnEarnbackInvoiceDate, String CreditnEarnbackInvoiceNumber, String ContractCreditValue, String ActualCreditValue
			  , String ContractEarnbackValue, String ActualEarnbackValue) throws InterruptedException
			  {
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case of Updating Contract Credit and Earnback");
		    
		    openBrowser();
			driver.manage().window().maximize();
	  
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
	  
			getObject("contract_quick_link").click();
			Thread.sleep(12000);
			
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ContractName+"')]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(10000);
			
			getObject("co_creditnearnback_tab_contract_show_page").click();
		    Thread.sleep(10000);
		    driver.findElement(By.xpath("//*[@id='48']/tbody/tr[@role='row']/td[2][contains(.,'"+CreditnEarnbackName+"')]/preceding::td[1]/a")).click();
			
		    getObject("co_ceb_edit_button").click();
		    
		    getObject("co_creditnearnback_name_textbox").clear();
			
			getObject("co_creditnearnback_name_textbox").sendKeys(CreditnEarnbackUpdateName);
			
			getObject("co_creditnearnback_descrition_textarea").clear();
			
			getObject("co_creditnearnback_descrition_textarea").sendKeys(CreditnEarnbackDescription);
			
			if (!CreditnEarnbackCurrency.equalsIgnoreCase("")) 
			{
				new Select (driver.findElement(By.xpath(".//*[@id='elem_4007']/select"))).selectByVisibleText(CreditnEarnbackCurrency);
			}
			
			driver.findElement(By.name("invoiceDate")).click();
			
			Double temp_contractEffectiveYear_double = Double.parseDouble(CreditnEarnbackInvoiceYear);
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

		    Double temp_contractEffectiveMonth_double = Double.parseDouble(CreditnEarnbackInvoiceMonth);
		    int temp_contractEffectiveMonth_int = temp_contractEffectiveMonth_double.intValue();

		    int click = current_month - temp_contractEffectiveMonth_int;
		    System.out.println("click " + click);
		    for (; click >= 0; click = click - 1) {

		      System.out.println("value of j" + click);
		      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();

		    }

		    Double temp_contractEffectiveDate_double = Double.parseDouble(CreditnEarnbackInvoiceDate);
		    int temp_contractEffectiveDate_int = temp_contractEffectiveDate_double.intValue();
		    String contractEffectiveDate_string = Integer.toString(temp_contractEffectiveDate_int);
		    driver.findElement(By.linkText(contractEffectiveDate_string)).click();
			
		    getObject("co_creditnearnback_invoice_number_textbox").clear();
		    
		    getObject("co_creditnearnback_invoice_number_textbox").sendKeys(CreditnEarnbackInvoiceNumber);
		    
		    getObject("co_creditnearnback_contract_credit_value_textbox").clear();
			getObject("co_creditnearnback_contract_credit_value_textbox").sendKeys(convertDoubleToIntegerInStringForm(ContractCreditValue));
			getObject("co_creditnearnback_actual_credit__value_textbox").clear();
			getObject("co_creditnearnback_actual_credit__value_textbox").sendKeys(convertDoubleToIntegerInStringForm(ActualCreditValue));
			getObject("co_creditnearnback_contract_earnback_value_textbox").clear();
			getObject("co_creditnearnback_contract_earnback_value_textbox").sendKeys(convertDoubleToIntegerInStringForm(ContractEarnbackValue));
			getObject("co_creditnearnback_actual_earnback_value_textbox").clear();
			getObject("co_creditnearnback_actual_earnback_value_textbox").sendKeys(convertDoubleToIntegerInStringForm(ActualEarnbackValue));
	
			getObject("co_ceb_update_button").click();
			
			  
		    APP_LOGS.debug("Contract Credit & Earnback updated successfully");
		   
		    String CreditnEarnbackShowPageName = getObject("co_creditnearnback_name_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(CreditnEarnbackShowPageName, CreditnEarnbackName,"Credit & Earnback Name on show page is -- " +CreditnEarnbackShowPageName+ " instead of -- " +CreditnEarnbackName);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("credit and Earnback Name on show page is -- " +CreditnEarnbackShowPageName+ " instead of -- " +CreditnEarnbackName);
		    }
		    
		    String CreditnEarnbackdescription = getObject("co_creditnearnback_descrition_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(CreditnEarnbackdescription, CreditnEarnbackDescription,"Credit and Earnback description on show page is -- " +CreditnEarnbackdescription+ " instead of -- " +CreditnEarnbackDescription);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Credit and Earnback description on show page is -- " +CreditnEarnbackDescription+ " instead of -- " +CreditnEarnbackdescription);
		    }
		    
		    String CEBInvoiveNumberShowPage = getObject("co_creditnearnback_invoice_number_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(CEBInvoiveNumberShowPage, CreditnEarnbackInvoiceNumber,"Invoice Number on Credit & Earnback show page is -- " +CreditnEarnbackInvoiceNumber+ " instead of -- " +CEBInvoiveNumberShowPage);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Invoice Number on Credit & Earnback show page is -- " +CEBInvoiveNumberShowPage+ " instead of -- " +CreditnEarnbackInvoiceNumber);
		    }
		    
		    String CEBContractCreditValue = getObject("co_creditnearnback_contract_credit_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(CEBContractCreditValue, ContractCreditValue,"Contract Credit Value on show page is -- " +CEBContractCreditValue+ " instead of -- " +ContractCreditValue );
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Contract Credit Value show page is -- " +ContractCreditValue+ " instead of -- " +CEBContractCreditValue);
		    }
	
		    
		    String CEBActualCreditValue = getObject("co_creditnearnback_actual_credit_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(CEBActualCreditValue, ActualCreditValue,"Actual Credit Value on show page is -- " +CEBActualCreditValue+ " instead of -- " +ActualCreditValue );
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Actual Credit Value show page is -- " +ActualCreditValue+ " instead of -- " +CEBActualCreditValue);
		    }
			

		    String CEBContractEarnbackValue = getObject("co_creditnearnback_contract_earnback_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(CEBContractEarnbackValue, ContractEarnbackValue,"Earnback Credit Value on show page is -- " +CEBContractEarnbackValue+ " instead of -- " +ContractEarnbackValue );
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Earnback Credit Value show page is -- " +ContractEarnbackValue+ " instead of -- " +CEBContractEarnbackValue);
		    }			

		    String CEBActualEarnbackValue = getObject("co_creditnearnback_actual_earnback_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(CEBActualEarnbackValue, ActualEarnbackValue,"Actual Earnback Value on show page is -- " +CEBActualEarnbackValue+ " instead of -- " +ActualEarnbackValue );
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Actual Earnback Value show page is -- " +ActualEarnbackValue+ " instead of -- " +CEBActualEarnbackValue);
		    }				
		    
		    
		    fail = false;
	  
	  }
	  
	  public String convertDoubleToIntegerInStringForm(String data){
			data = Integer.valueOf((Double.valueOf(Double.parseDouble(data))).intValue()).toString();
			return data;
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
