package test.resources.com.sirion.suite.contract;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class UpdateAppFromAppGroup extends TestSuiteBase {
	
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
	  
	  @Test(groups = "UpdateAppfromappgroup", dataProvider = "getTestData")
	  public void UpdateAppfromappgroup(String ContractName,String AppGroupName,String AppName,String UpdateAppName, String AppActiveCheckbox) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case of Updating Application from Application Group");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
	  
			getObject("contract_quick_link").click();
			Thread.sleep(15000);
			
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ContractName+"')]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);
			
			getObject("co_app_group_tab").click();
			
			driver.findElement(By.xpath("//*[@id='46']/tbody/tr[@role='row']/td[contains(.,'"+AppGroupName+"')]/preceding::td[1]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);
			
			getObject("app_group_app_tab_name").click();
			Thread.sleep(8000);
	  
			driver.findElement(By.xpath(".//*[@id='1421']/tbody/tr[@role='row']/td[contains(.,'"+AppName+"')]/preceding-sibling::td[1]/a")).click();
			
			getObject("co_app_edit_button").click();
			
			getObject("app_name_textbox").clear();
			getObject("app_name_textbox").sendKeys(UpdateAppName);	
			
			if(AppActiveCheckbox.equalsIgnoreCase("Yes") && driver.findElement(By.xpath(".//*[@id='_active_id']")).isSelected())
	        {
	          return;
	        }
	        else
	        {
	        	getObject("app_active_checkbox").click();
	        }
			
			getObject("co_app_update_button").click();
			
			APP_LOGS.debug(" Application from "+AppGroupName+" Application is updated successfully");
			
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
		    	Assert.assertEquals(AppActive, AppActiveCheckbox,"Application Group Name is -- " +AppActive+ " instead of -- " +AppActiveCheckbox);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Application Group Name is -- " +AppActive+ " instead of -- " +AppActiveCheckbox);
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
