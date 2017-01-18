package test.resources.com.sirion.suite.contract;

import java.sql.SQLException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ContractWorkflow extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfContracts;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider = "getTestData")
	public void contractWorkflow() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		//APP_LOGS.debug("Executing Test Case Contract Workflow with contract title --- "+conTitle);
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("contract_quick_link").click(); 
		//Thread.sleep(20000);
		WebDriverWait wait=new WebDriverWait(driver, 50); 
		//wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_id_link")));
		getObject("ac_id_link").click();
		
		//Thread.sleep(10000);
        
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(10000);
        
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  getObject("co_currencyconversion_dropdown"));
		      new Select(getObject("co_currencyconversion_dropdown")).selectByIndex(1);
		
		
		        Thread.sleep(10000);
		        Date date = new Date();
			    int current_month = date.getMonth();
			    
		driver.findElement(By.name("rateCardFromDate")).click();

	    Double temp_contractCurrencyConversionMatrixFromYear_double = Double.parseDouble("2016");
	    int temp_contractCurrencyConversionMatrixFromYear_int = temp_contractCurrencyConversionMatrixFromYear_double.intValue();
	    String contractCurrencyConversionMatrixFromYear_string = Integer.toString(temp_contractCurrencyConversionMatrixFromYear_int);

	    WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	    System.out.println(datepicker_ui.isDisplayed());
	    if (datepicker_ui.isDisplayed() == true) {
	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	      new Select(datepicker_ui_year).selectByVisibleText(contractCurrencyConversionMatrixFromYear_string);
	          }

	    Double temp_contractCurrencyConversionMatrixFromMonth_double = Double.parseDouble("8");
	    int temp_contractCurrencyConversionMatrixFromMonth_int = temp_contractCurrencyConversionMatrixFromMonth_double.intValue();
	    System.out.println(" contractCurrencyConversionMatrixFromMonth " + temp_contractCurrencyConversionMatrixFromMonth_int);
	    
	    int click2 = current_month - temp_contractCurrencyConversionMatrixFromMonth_int;
	    System.out.println("click " + click2);
	    for (; click2 >= 0; click2 = click2 - 1) {
	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	    }
	    Double temp_contractCurrencyConversionMatrixFromDate_double = Double.parseDouble("1");
	    int temp_contractCurrencyConversionMatrixFromDate_int = temp_contractCurrencyConversionMatrixFromDate_double.intValue();
	    String contractCurrencyConversionMatrixFromDate_string = Integer.toString(temp_contractCurrencyConversionMatrixFromDate_int);
	    driver.findElement(By.linkText(contractCurrencyConversionMatrixFromDate_string)).click(); // enter date

	    driver.findElement(By.name("rateCardToDate")).click();
	    Double temp_contractCurrencyConversionMatrixToYear_double = Double.parseDouble("2016");
	    int temp_contractCurrencyConversionMatrixToYear_int = temp_contractCurrencyConversionMatrixToYear_double.intValue();
	    String contractCurrencyConversionMatrixToYear_string = Integer.toString(temp_contractCurrencyConversionMatrixToYear_int);

	    System.out.println(datepicker_ui.isDisplayed());
	    if (datepicker_ui.isDisplayed() == true) {
	      WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	      new Select(datepicker_ui_year).selectByVisibleText(contractCurrencyConversionMatrixToYear_string);
	    }

	    Double temp_contractCurrencyConversionMatrixToMonth_double = Double.parseDouble("8");
	    int temp_contractCurrencyConversionMatrixToMonth_int = temp_contractCurrencyConversionMatrixToMonth_double.intValue();
	    System.out.println(" contractCurrencyConversionMatrixToMonth " + temp_contractCurrencyConversionMatrixToMonth_int);

	    int click3 = temp_contractCurrencyConversionMatrixToMonth_int - current_month;
	    System.out.println("click " + click3);
	    for (; click3 >= 0; click3 = click3 - 1) {
	      driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    }
	    Double temp_contractCurrencyConversionMatrixToDate_double = Double.parseDouble("31");
	    int temp_contractCurrencyConversionMatrixToDate_int = temp_contractCurrencyConversionMatrixToDate_double.intValue();
	    String contractCurrencyConversionMatrixToDate_string = Integer.toString(temp_contractCurrencyConversionMatrixToDate_int);

	    driver.findElement(By.linkText(contractCurrencyConversionMatrixToDate_string)).click(); // enter date

	    
		Thread.sleep(10000);
	
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
        
        
        
	    if(getObject("ac_popup_id")!=null){
			
	    	String action_id = getObject("ac_popup_id").getText();
	    	APP_LOGS.debug("Action Cloned successfully with Issue id "+action_id);
		
	    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	Thread.sleep(10000);
		
	    	APP_LOGS.debug("Quick Search the created Action with Action id " + action_id);
		
	    	getObject("quick_search_textbox").sendKeys(action_id);
		
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	Thread.sleep(10000);
		
	    	String acnIdFromShowPage = getObject("con_show_id").getText();
	    	System.out.println("Action Id " + acnIdFromShowPage);
		
		}
		    Thread.sleep(10000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
	//	if(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")) != null)
		driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
		Thread.sleep(10000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
         driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")).click();
         Thread.sleep(10000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
         driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")).click();
         Thread.sleep(10000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
         driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")).click();
         Thread.sleep(10000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
         driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")).click();
         Thread.sleep(10000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(10000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish')]")));
         driver.findElement(By.xpath("//button[contains(.,'Publish')]")).click();
         Thread.sleep(10000);
         /*String contract_status = getObject("co_status_id").getText();
         
         Assert.assertEquals(contract_status, "Active");     
         */
              
		fail = false; //this executes if assertion passes
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
