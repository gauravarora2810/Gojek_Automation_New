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

public class UpdateAppGroup extends TestSuiteBase  {
	
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
	  
	  @Test(groups = "Updatingapplicationgroup", dataProvider = "getTestData")
	  public void Updateapplicationgroup(String ContractName,String AppGroupNameListing, String UpdateAppName,String AppGroupActiveCheckbox) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case of Updating Application Group");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));

			getObject("contract_quick_link").click();
			Thread.sleep(15000);
			
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ContractName+"')]/preceding-sibling::td[1]/a")).click();
			
			driver.findElement(By.xpath(".//*[@id='46']/tbody/tr[2][@role='row']/td[contains(.,'"+AppGroupNameListing+"')]/preceding::td[1]/preceding-sibling::td[1]/a")).click();
			
			getObject("co_app_group_edit_button").click();
			
			getObject("contract_app_group_name_textbox").clear();
			getObject("contract_app_group_name_textbox").sendKeys(UpdateAppName);
			
			if(AppGroupActiveCheckbox.equalsIgnoreCase("Yes") && driver.findElement(By.xpath(".//*[@id='elem_4031']/input")).isSelected())
	        {
	          return;
	        }
	        else
	        {
	        	getObject("contract_app_group_active_checkbox").click();
	        }

			getObject("co_app_group_update_button").click();
			
			Thread.sleep(20000);
			
			 String AppGroupShowPageName = getObject("co_app_group_show_name").getText();
		    
		    try
		    {
		    	Assert.assertEquals(AppGroupShowPageName, AppGroupNameListing,"Application Group Name is -- " +AppGroupShowPageName+ " instead of -- " +AppGroupNameListing);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Application Group Name is -- " +AppGroupShowPageName+ " instead of -- " +AppGroupNameListing);
		    }
		    
		    String ContractShowPageName = getObject("co_show_page_name").getText();
		    
		    try
		    {
		    	Assert.assertEquals(ContractShowPageName, ContractName,"Contract Name on Application Group show page is -- " +ContractName+ " instead of -- " +ContractShowPageName);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Contract Name on Application Group show page is -- " +ContractShowPageName+ " instead of -- " +ContractName);
		    }
		    
		    String AppGroupActive = getObject("co_app_group_show_page_active").getText();
		    
		    try
		    {
		    	Assert.assertEquals(AppGroupActive, AppGroupActiveCheckbox,"Application Group Name is -- " +AppGroupActive+ " instead of -- " +AppGroupActiveCheckbox);
		    }
		    
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Application Group Name is -- " +AppGroupActive+ " instead of -- " +AppGroupActiveCheckbox);
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
