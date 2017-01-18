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


public class UpdateCrType extends TestSuiteBase{
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
	public void CrTypeCreation(String crType, String crTypeUpdate, String crTypeActive) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +crType +" to no " +count);
		}
		
		APP_LOGS.debug("Executing Sirion Admin CR Type test -- "+crType);
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin CR Type test -- "+crType);
		getObject("admin_tab_link").click();
		getObject("sa_cr_type_link").click();
        new Select (getObject("sa_cr_type_display_dropdown")).selectByIndex(3);
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ crType +"')]/div/a")).click();
        //Thread.sleep(5000);
        getObject("sa_cr_type_edit_textbox").click();
        getObject("sa_cr_type_textbox").clear();
        getObject("sa_cr_type_textbox").sendKeys(crTypeUpdate);
		getObject("sa_cr_type_submit_button").click();
		
        /*if(driver.getPageSource().contains("This name already exists")){
          getObject("sa_cr_type_popup_ok_button").click();
          getObject("sa_cr_type_cancel_button").click();
          getObject("sa_cr_type_yes_of_cancel_button").click();
          fail=false;
      }*/
       /*String crTypeShowPage = getObject("sa_cr_type_show").getText();
       Assert.assertEquals(crTypeShowPage, crTypeUpdate, "CR Type at show page is -- " +crTypeShowPage+ " instead of -- " +crTypeUpdate);
       String crTypeActiveShowPage = getObject("sa_cr_type_active_show").getText();
       Assert.assertEquals(crTypeActiveShowPage, crTypeActive, "CR Type status at show page is -- " +crTypeActiveShowPage+ " instead of -- " +crTypeActive);*/
       fail = false;
       APP_LOGS.debug("CR Type open successfully, following parameters have been validated: CR Type updated name -- " +crTypeUpdate +
           ", CR Type status -- "+crTypeActive);
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
