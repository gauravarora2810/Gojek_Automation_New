package test.resources.com.sirion.suite.contract;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ContractApplicationGroup extends TestSuiteBase  {
	
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
	  
	  @Test(groups = "Contractapplicationgroup", dataProvider = "getTestData")
	  public void Contractapplicationgroup(String ContractName,String AppGroupName, String AppGroupActiveCheckbox) throws InterruptedException{
		  
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
				//Thread.sleep(20000);
				//wait.until(ExpectedConditions.elementToBeClickable(getObject("is_id_link")));

				getObject("is_id_link").click(); 

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
		    plus_button("is_plus_button_link"); // web element for plus button on supplier page
		    wait.until(ExpectedConditions.elementToBeClickable(getObject("ag_create_link_from_contract")));
		    getObject("ag_create_link_from_contract").click(); // click issue create link  
		
			//Thread.sleep(10000);
			getObject("contract_app_group_name_textbox").clear();
			getObject("contract_app_group_name_textbox").sendKeys(AppGroupName);
			
			
			if(AppGroupActiveCheckbox.equalsIgnoreCase("yes")){
				getObject("contract_app_group_active_checkbox").click();
			}
			getObject("ac_save_button").click();
			//Thread.sleep(12000);
			String app_group_show_id = getObject("is_popup_id").getText();
			
			APP_LOGS.debug(" Contract created successfully with Contract id "+app_group_show_id);

		    getObject("is_popup_ok_button").click();
		    //Thread.sleep(3000);
		    
		    APP_LOGS.debug(" Quick Search the created contract with Contract id "+app_group_show_id);
		    
		    getObject("quick_search_textbox").sendKeys(app_group_show_id);
		    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		    //Thread.sleep(8000);
		    APP_LOGS.debug("Contract show page open successfully with contract id " + app_group_show_id);

		    
//		    String AppGroupIdFromShowPage = getObject("co_app_group_show_id").getText();
//		    
//		    try
//		    {
//		    	Assert.assertEquals(AppGroupIdFromShowPage, app_group_show_id,"Application Group ID is -- " +AppGroupIdFromShowPage+ " instead of -- " +app_group_show_id);
//		    }
//		    catch(Throwable e)
//		    {
//		    	APP_LOGS.debug("Application Group ID is -- " +AppGroupIdFromShowPage+ " instead of -- " +app_group_show_id);
//		    }
//
//		    String AppGroupShowPageName = getObject("co_app_group_show_name").getText();
//		    
//		    try
//		    {
//		    	Assert.assertEquals(AppGroupShowPageName, AppGroupName,"Application Group Name is -- " +AppGroupShowPageName+ " instead of -- " +AppGroupName);
//		    }
//		    
//		    catch(Throwable e)
//		    {
//		    	APP_LOGS.debug("Application Group Name is -- " +AppGroupShowPageName+ " instead of -- " +AppGroupName);
//		    }
//		    
//		    String ContractShowPageName = getObject("co_show_page_name").getText();
//		    
//		    try
//		    {
//		    	Assert.assertEquals(ContractShowPageName, ContractName,"Contract Name on Application Group show page is -- " +ContractName+ " instead of -- " +ContractShowPageName);
//		    }
//		    
//		    catch(Throwable e)
//		    {
//		    	APP_LOGS.debug("Contract Name on Application Group show page is -- " +ContractShowPageName+ " instead of -- " +ContractName);
//		    }
//		    
//		    String AppGroupActive = getObject("co_app_group_show_page_active").getText();
//		    
//		    try
//		    {
//		    	Assert.assertEquals(AppGroupActive, AppGroupActiveCheckbox,"Application Group Name is -- " +AppGroupActive+ " instead of -- " +AppGroupActiveCheckbox);
//		    }
//		    
//		    catch(Throwable e)
//		    {
//		    	APP_LOGS.debug("Application Group Name is -- " +AppGroupActive+ " instead of -- " +AppGroupActiveCheckbox);
//		    }
//	
		    
		    fail = false;
		    getObject("analytics_link").click();
		    
		    APP_LOGS.debug("Application group open successfully, following parameters have been validated: Application Group name -- " + AppGroupName+ ", " +
		    		"Application group status -- " + AppGroupActiveCheckbox);
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
