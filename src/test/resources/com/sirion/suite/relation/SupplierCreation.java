package test.resources.com.sirion.suite.relation;

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


public class SupplierCreation extends TestSuiteBase{
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
	public void Suppliercreation(String supName, String supAlias, String supTier, String supAddress, String supFunctions, String supServices, 
	    String supRegions, String supCountries, String supStakeholder,String supaddditionalTCV,  String supaddditionalACV, String supaddditionalFACV, String relName) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +supName +" to no " +count);
		}
		
		 // Calling method for opening browser from TestBase.java file
	 	openBrowser();

	 	// Calling a method for login(at different platform) from TestBase.java file
	 	endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
	     
	     WebDriverWait wait=new WebDriverWait(driver, 50);
	 		getObject("analytics_link").click();

	 		getObject("vh_quick_link").click(); // IP Quick Link Clicking
	 		Thread.sleep(20000);
	 		//wait.until(ExpectedConditions.elementToBeClickable(getObject("is_id_link")));

	 		getObject("is_id_link").click(); 

	 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
	     plus_button("is_plus_button_link"); // web element for plus button on supplier page
	     wait.until(ExpectedConditions.elementToBeClickable(getObject("sup_create_link_from_vh")));
	     getObject("sup_create_link_from_vh").click(); // click issue create link  
	     
	     
		
		if(!supName.equalsIgnoreCase(""))
		{
		getObject("ca_sup_name_textbox").clear();
		getObject("ca_sup_name_textbox").sendKeys(supName);
		}
		if(!supAlias.equalsIgnoreCase(""))
		{
		getObject("ca_sup_alias_textbox").clear();
		getObject("ca_sup_alias_textbox").sendKeys(supAlias);
		}
		if(!supTier.equalsIgnoreCase(""))
		{
			Thread.sleep(5000);
		new Select(getObject("ca_sup_tier_dropdown")).selectByVisibleText(supTier);
		}
		if(!supAddress.equalsIgnoreCase(""))
		{
		getObject("ca_sup_address_textarea").clear();
		getObject("ca_sup_address_textarea").sendKeys(supAddress);
		}
		
		if (!supFunctions.equalsIgnoreCase("")) {
			Thread.sleep(5000);
			new Select(getObject("ca_sup_functions_multidropdown")).selectByVisibleText(supFunctions);
			}
			if (!supServices.equalsIgnoreCase("")) {
				Thread.sleep(5000);
			  new Select(driver.findElement(By.name("services"))).selectByVisibleText(supServices);
			}
			if (!supRegions.equalsIgnoreCase("")) {
				Thread.sleep(5000);
			new Select(getObject("ca_sup_regions_multidropdown")).selectByVisibleText(supRegions);
			}
			if (!supCountries.equalsIgnoreCase("")) {
				Thread.sleep(5000);
				new Select(getObject("ca_sup_countries_multidropdown")).selectByVisibleText(supCountries);
			}
		   // driver.findElement(By.xpath("//span[2]/div/input")).clear();
		   // driver.findElement(By.xpath("//span[2]/div/input")).sendKeys(supStakeholder);
		   // Thread.sleep(3000);
		    //driver.findElement(By.xpath("//span[2]/div/ul/li/a")).click();
		if(!supaddditionalFACV.equalsIgnoreCase(""))
			{
		getObject("ca_sup_FACV_textbox").clear();
		getObject("ca_sup_FACV_textbox").sendKeys(supaddditionalFACV);
			}
		if(!supaddditionalTCV.equalsIgnoreCase(""))
		{
		getObject("ca_sup_TCV_textbox").clear();
		getObject("ca_sup_TCV_textbox").sendKeys(supaddditionalTCV);
		}
		if(!supaddditionalACV.equalsIgnoreCase(""))
		{
		getObject("ca_sup_ACV_textbox").clear();
		getObject("ca_sup_ACV_textbox").sendKeys(supaddditionalACV);
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
		
		    fail = false; //this executes if assertion passes
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
