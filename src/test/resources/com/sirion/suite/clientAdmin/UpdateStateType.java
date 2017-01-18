package test.resources.com.sirion.suite.clientAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;


public class UpdateStateType extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(client_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(client_admin_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (dataProvider="getTestData")
	public void UpdateStateTypeTest( String stateTypeOp, String stateTypeUpdate) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +stateTypeOp +" to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
		APP_LOGS.debug("Executing Client Admin State type update test -- " +stateTypeOp);
		getObject("admin_tab_link").click();
		getObject("ca_state_type_link").click();
	
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ stateTypeOp +"')]/div/a")).click();
     //   Thread.sleep(5000);
	//	getObject("ca_state_type_edit_button").click();
        
        
		getObject("ca_state_type_org_perf_textbox").clear();
		getObject("ca_state_type_org_perf_textbox").sendKeys(stateTypeUpdate);
		getObject("ca_state_type_update_button").click();
		getObject("co_popup_ok_button").click();
        new Select (getObject("ca_state_type_display_dropdown")).selectByIndex(3);
   driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ stateTypeUpdate +"')]/div/a")).click(); 
   
   /*String stateTypeShowPage = getObject("ca_state_type_name_textbox").getText();
   
   Assert.assertEquals(stateTypeShowPage, stateTypeUpdate, "State type is --"+stateTypeShowPage+" instead of -- "+stateTypeUpdate);
*/
   fail=false;
   APP_LOGS.debug("State type show page open successfully, following parameters have been validated: State type name -- " +stateTypeUpdate);  
		getObject("admin_tab_link").click();
		
		
	}

	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
}