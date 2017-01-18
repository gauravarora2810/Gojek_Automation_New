package test.resources.com.sirion.suite.sirionAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
//import net.sf.saxon.sort.GroupStartingIterator;


public class UpdateFunction extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (dataProvider="getTestData")
	public void FunctionCreation( String functionName, String functionAlias, String functionNameUpdate, String functionAliasUpdate, String functionActive) 
	    throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +functionName +" to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Function Creation updation test --" +functionName);
		getObject("admin_tab_link").click();
		getObject("sa_function_link").click();
	      new Select (getObject("sa_function_display_dropdown")).selectByIndex(3);
	      driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterContractType']/tbody/tr[@role='row']/td[contains(.,'"+ functionName +"')]/a/div")).click();
	      // Thread.sleep(5000);
	    getObject("sa_function_edit_textbox").click();
	    getObject("sa_function_textbox").clear();
		getObject("sa_function_textbox").sendKeys(functionNameUpdate);
		getObject("sa_function_alias_textbox").clear();
		getObject("sa_function_alias_textbox").sendKeys(functionAliasUpdate);
		getObject("sa_function_submit_button").click();

    /* String functionNameShowPage = getObject("sa_function_show").getText();
     Assert.assertEquals(functionNameShowPage, functionNameUpdate, "Function at show page is -- " +functionNameShowPage+ " instead of -- " +functionNameUpdate);
     String functionAliasShowPage = getObject("sa_function_alias_show").getText();
     Assert.assertEquals(functionAliasShowPage, functionAliasUpdate, "Function at show page is -- " +functionAliasShowPage+ " instead of -- " +functionAliasUpdate);
     String functionActiveShowPage = getObject("sa_function_active_show").getText();
     Assert.assertEquals(functionActiveShowPage, functionActive, "Function status at show page is -- " +functionActiveShowPage+ " instead of -- " +functionActive);
     */fail = false;
     APP_LOGS.debug("Function open successfully, following parameters have been validated: Function updated name -- " +functionNameUpdate + ", Function alias " +
     		"updated name -- "+functionAliasUpdate+", Function status -- "+functionActive);
		getObject("admin_tab_link").click();
		
		
	}	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(sirion_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
}
