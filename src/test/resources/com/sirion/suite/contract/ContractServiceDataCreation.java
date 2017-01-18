package test.resources.com.sirion.suite.contract;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class ContractServiceDataCreation extends TestSuiteBase {
	
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
	  
	  @Test(groups = "ContractServiceData", dataProvider = "getTestData")
	  public void ContractServiceData(String ContractName,String ServiceDataDisplayName,String ServiceName, String ServiceDataCheckbox) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case Contract Creation");
		    
		 // Calling method for opening browser from TestBase.java file
 			openBrowser();

 			// Calling a method for login(at different platform) from TestBase.java file
 			endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
 		    
 		    WebDriverWait wait=new WebDriverWait(driver, 50);
 				getObject("analytics_link").click();

 				getObject("contract_quick_link").click(); // IP Quick Link Clicking
 				Thread.sleep(20000);
 				//wait.until(ExpectedConditions.elementToBeClickable(getObject("is_id_link")));

 				getObject("is_id_link").click(); 

 				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
 		    plus_button("is_plus_button_link"); // web element for plus button on supplier page
 		    wait.until(ExpectedConditions.elementToBeClickable(getObject("sd_create_link_from_contract")));
 		    getObject("sd_create_link_from_contract").click(); // click issue create link  
	
			Thread.sleep(10000);
			getObject("contract_app_group_name_textbox").clear();
			getObject("contract_app_group_name_textbox").sendKeys(ServiceDataDisplayName);
			
			new Select (driver.findElement(By.xpath("//select[@name='service']"))).selectByVisibleText(ServiceName);
			
			if(ServiceDataCheckbox.equalsIgnoreCase("yes")){
			getObject("contract_app_group_active_checkbox").click();
			}
			
			getObject("ac_save_button").click();
			
			Thread.sleep(8000);
			String service_data_show_id = getObject("is_popup_id").getText();
			
			APP_LOGS.debug(" Service Data"+ServiceDataDisplayName+" is created successfully with Service Data id "+service_data_show_id);
			Thread.sleep(5000);
		    getObject("is_popup_ok_button").click();
		    Thread.sleep(3000);
		    APP_LOGS.debug(" Quick Search the created contract with Contract id "+service_data_show_id);
		    
		    getObject("quick_search_textbox").sendKeys(service_data_show_id);
		    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		    Thread.sleep(8000);
		    
		    /*getObject("co_service_data_tab").click();
		    
		    driver.findElement(By.xpath("//*[@id='47']/tbody/tr[@role='row']/td[contains(.,'"+ServiceDataDisplayName+"')]/preceding::td[1]/preceding-sibling::td[1]/a")).click();
			  */
		    APP_LOGS.debug("Service Data show page open successfully with id " + service_data_show_id);
		    /*String ServiceDataIdShowPage = getObject("co_service_data_id_show_page").getText();
		    try
		    {
		    	Assert.assertEquals(ServiceDataIdShowPage, service_data_show_id,"Service Data ID is -- " +ServiceDataIdShowPage+ " instead of -- " +service_data_show_id);
		    }
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Service Data ID is -- " +ServiceDataIdShowPage+ " instead of -- " +service_data_show_id);
		    }

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
		    	Assert.assertEquals(ServiceName, ServiceNameShowPage,"Service Name on show page is -- " +ServiceName+ " instead of -- " +ServiceNameShowPage );
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Service Name on show page is -- " +ServiceNameShowPage+ " instead of -- " +ServiceName);
		    }*/
	
		    fail = false;
		    getObject("analytics_link").click();
            /*APP_LOGS.debug("Contract service data open successfully, following parameters have been validated: Service data name -- " + ServiceDataDisplayName+ ", " +
                "Service data status -- " + ServiceDataActiveStatus);*/
	  
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
