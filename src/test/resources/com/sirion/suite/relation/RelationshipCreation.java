package test.resources.com.sirion.suite.relation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;


public class RelationshipCreation extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	WebDriverWait wait;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(relation_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(relation_suite_xls, this.getClass().getSimpleName());
	}
		
	
	@Test (dataProvider="getTestData")
	public void Relationshipcreation( String relName, String relAlias, String relAddress, String relFunctions, String relServices, String relRegions, String relCountries, String reladdditionalTCV,  String reladdditionalACV, String reladdditionalFACV) throws Throwable 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +relName +" to no " +count);
		}
		
		 // Calling method for opening browser from TestBase.java file
	 	openBrowser();

	 	// Calling a method for login(at different platform) from TestBase.java file
	 	endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
	     
	     WebDriverWait wait=new WebDriverWait(driver, 50);
	 		getObject("analytics_link").click();

	 		getObject("vh_quick_link").click(); // IP Quick Link Clicking
	 		Thread.sleep(15000);
		
	    WebElement plus_relationship = getObject("vh_plus_button_global_listing");
		Actions ac_plus_relationship=new Actions(driver);
		ac_plus_relationship.moveToElement(plus_relationship).click().build().perform();
		
	

		      
		    
		      
		
		
		
		if(!relName.equalsIgnoreCase(""))
		{
		getObject("ca_rel_name_textbox").clear();
		getObject("ca_rel_name_textbox").sendKeys(relName);
		}
		if(!relAlias.equalsIgnoreCase(""))
		{
		getObject("ca_rel_alias_textbox").clear();
		getObject("ca_rel_alias_textbox").sendKeys(relAlias);
		}
		if(!relAddress.equalsIgnoreCase(""))
		{
		getObject("ca_rel_address_textarea").clear();
		getObject("ca_rel_address_textarea").sendKeys(relAddress);
		}
		Thread.sleep(5000);
		
		
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("ca_rel_regions_multidropdown"));
		
		
		driver.findElement(By.xpath("//span[contains(.,'Functions* : ')]")).click();
		
			if (!relFunctions.equalsIgnoreCase("")) {
			Thread.sleep(5000);
			new Select(getObject("ca_rel_functions_multidropdown")).selectByVisibleText(relFunctions);
			}
			if (!relServices.equalsIgnoreCase("")) {
				Thread.sleep(5000);
			new Select(getObject("ca_rel_services_multidropdown")).selectByVisibleText(relServices);
			}
			
		
			if (!relRegions.equalsIgnoreCase("")) {
				Thread.sleep(5000);
			new Select(getObject("ca_rel_regions_multidropdown")).selectByVisibleText(relRegions);
			}
			if (!relCountries.equalsIgnoreCase("")) {
				Thread.sleep(5000);
			new Select(getObject("ca_rel_countries_multidropdown")).selectByVisibleText(relCountries);
			}	
			
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("ca_rel_TCV_textbox"));	
			
			if(!reladdditionalTCV.equalsIgnoreCase(""))
			{
		getObject("ca_rel_TCV_textbox").clear();
		getObject("ca_rel_TCV_textbox").sendKeys(reladdditionalTCV);
			}
		
		if(!reladdditionalACV.equalsIgnoreCase(""))
		{
		getObject("ca_rel_ACV_textbox").clear();
		getObject("ca_rel_ACV_textbox").sendKeys(reladdditionalACV);
		}
		
		if(!reladdditionalFACV.equalsIgnoreCase(""))
		{
		getObject("ca_rel_FACV_textbox").clear();
		getObject("ca_rel_FACV_textbox").sendKeys(reladdditionalFACV);
		}
		
		 getObject("ac_save_button").click();
		
		
		
		 Thread.sleep(10000);
		   // String contract_id = driver.findElement(By.xpath(".//*[@id='hrefElemId']")).getText();
		    String contract_id = getObject("co_popup_id").getText();
		    
		    APP_LOGS.debug("Supplier created successfully with Contract id "+contract_id);
		    Thread.sleep(3000);
		    
		    //driver.findElement(By.xpath(".//*[@id='data-ng-app']/div[25]/div/div/div/div[3]/button")).click();
		    getObject("co_popup_ok_button").click();
		    Thread.sleep(8000);
		    
		    APP_LOGS.debug("Quick Search the created contract with Contract id "+contract_id);
		    
		    getObject("quick_search_textbox").sendKeys(contract_id);
		    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		    
		    
		    Thread.sleep(5000);
		    fail=false;
		    getObject("analytics_link").click();
		
		}
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(relation_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(relation_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(relation_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
	
	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(relation_suite_xls, "Test Cases", TestUtil.getRowNum(relation_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(relation_suite_xls, "Test Cases", TestUtil.getRowNum(relation_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(relation_suite_xls, this.getClass().getSimpleName()) ;
	}
	
}