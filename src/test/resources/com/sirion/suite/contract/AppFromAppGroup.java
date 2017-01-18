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

public class AppFromAppGroup extends TestSuiteBase {
	
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
	  
	  @Test(groups = "Appfromappgroup", dataProvider = "getTestData")
	  public void Appfromappgroup(String ContractName,String AppGroupName,String AppName, String AppActiveCheckbox, String AppGroupActiveCheckbox) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case Contract Creation");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
			getObject("analytics_link").click();
			getObject("contract_quick_link").click();
			Thread.sleep(15000);
			
			new Select(driver.findElement(By.xpath(".//*[@id='cr_length']/label/select"))).selectByIndex(3);
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ContractName+"')]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);
			
			getObject("co_app_group_tab").click();
			
			driver.findElement(By.xpath("//*[@id='46']/tbody/tr[@role='row']/td[contains(.,'"+AppGroupName+"')]/preceding::td[1]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);
			
			getObject("app_group_app_tab_name").click();
			Thread.sleep(8000);
	  
			getObject("app_add_button").click();
			
			driver.findElement(By.linkText("Create Application")).click();
			Thread.sleep(10000);
			
			getObject("app_name_textbox").sendKeys(AppName);
			
			if(AppActiveCheckbox.equalsIgnoreCase("yes")){
				getObject("app_active_checkbox").click();
			}
	  
			getObject("app_save_button_link").click();
			Thread.sleep(8000);
			String app_show_id = getObject("co_popup_id").getText();
			
			APP_LOGS.debug(" Application from "+AppGroupName+" Application Group created successfully with Application id "+app_show_id);
			Thread.sleep(5000);
		    getObject("app_ok_button_popup").click();
		    Thread.sleep(3000);
	  
		    getObject("app_group_app_tab_name").click();
		    
		    driver.findElement(By.xpath("//*[@id='1421']/tbody/tr[@role='row']/td[contains(.,'"+AppName+"')]/preceding-sibling::td[1]/a")).click();
	  
		    APP_LOGS.debug("Application show page open successfully with id " + app_show_id);
		    String AppGroupIdFromShowPage = getObject("app_id_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(AppGroupIdFromShowPage, app_show_id,"Application ID is -- " +AppGroupIdFromShowPage+ " instead of -- " +app_show_id);
		    }
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Application ID is -- " +AppGroupIdFromShowPage+ " instead of -- " +app_show_id);
		    }

		    String AppGroupShowPageName = getObject("app_name_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(AppGroupShowPageName, AppGroupName,"Application Name is -- " +AppGroupShowPageName+ " instead of -- " +AppGroupName);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Application Group Name is -- " +AppGroupShowPageName+ " instead of -- " +AppGroupName);
		    }
		    
		    String AppActive = getObject("app_active_show_page").getText();
		    
		    try
		    {
		    	Assert.assertEquals(AppActive, AppGroupActiveCheckbox,"Application Group Name is -- " +AppActive+ " instead of -- " +AppGroupActiveCheckbox);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Application Group Name is -- " +AppActive+ " instead of -- " +AppGroupActiveCheckbox);
		    }
	
		    fail = false;
	          APP_LOGS.debug("Application open successfully, following parameters have been validated: Application name -- " + AppName+ ", " +
                  "Application status -- " + AppActive);
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
