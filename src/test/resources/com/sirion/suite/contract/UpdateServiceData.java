package test.resources.com.sirion.suite.contract;

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

public class UpdateServiceData extends TestSuiteBase {
	
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
	  
	  @Test(groups = "ServiceDataUpdation", dataProvider = "getTestData")
	  public void ServiceDataUpdation(String ContractName,String ServiceDataDisplayName,String ServiceDataUpdateName,String ServiceDataName, String ServiceDataCheckbox) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing the Test Case of Service Data Updation");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
	  
			getObject("contract_quick_link").click();
			Thread.sleep(15000);
			
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ContractName+"')]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);
			
			getObject("co_service_data_tab").click();
		    
		    driver.findElement(By.xpath("//*[@id='47']/tbody/tr[@role='row']/td[contains(.,'"+ServiceDataDisplayName+"')]/preceding::td[1]/preceding-sibling::td[1]/a")).click();
			 Thread.sleep(30000);
		    getObject("co_service_data_edit_button").click();
		    getObject("co_service_data_display_name_textbox").clear();
			getObject("co_service_data_display_name_textbox").sendKeys(ServiceDataUpdateName);
			new Select (driver.findElement(By.xpath(".//*[@id='elem_4039']/select"))).selectByVisibleText(ServiceDataName);
			
			if(ServiceDataCheckbox.equalsIgnoreCase("yes")){
			getObject("co_service_data_active_checkbox").click();
			}
			
			getObject("co_service_data_update_button").click();
			
			Thread.sleep(8000);
			
			String ServiceDataShowPageName = getObject("co_service_data_name_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(ServiceDataShowPageName, ServiceDataDisplayName,"Service Data Name on show page is -- " +ServiceDataShowPageName+ " instead of -- " +ServiceDataDisplayName);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Service Data Name on show page is -- " +ServiceDataShowPageName+ " instead of -- " +ServiceDataDisplayName);
		    }
		    
		    String ServiceDataActiveStatus = getObject("co_service_data_active_status_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(ServiceDataActiveStatus, ServiceDataCheckbox,"Service Data Active Status on show page is -- " +ServiceDataActiveStatus+ " instead of -- " +ServiceDataCheckbox);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Service Data Active Status on show page is -- " +ServiceDataActiveStatus+ " instead of -- " +ServiceDataCheckbox);
		    }
		    
		    String ContractNameShowPage = getObject("co_contract_name_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(ContractName, ContractNameShowPage,"Contract Name on show page is -- " +ContractName+ " instead of -- " +ContractNameShowPage);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Contract Name on show page is -- " +ContractNameShowPage+ " instead of -- " +ContractName);
		    }
		    
		    String ServiceNameShowPage = getObject("co_service_name_show_page").getText();
		    
		    try {
		    	Assert.assertEquals(ServiceDataUpdateName, ServiceNameShowPage,"Service Name on show page is -- " +ServiceDataUpdateName+ " instead of -- " +ServiceNameShowPage );
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Service Name on show page is -- " +ServiceNameShowPage+ " instead of -- " +ServiceDataUpdateName);
		    }
	
		    fail = false;
	  
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
