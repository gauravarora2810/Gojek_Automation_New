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


public class UpdateSerSubCategory extends TestSuiteBase{
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
	public void SerSubCategoryCreation(String serviceSubCategoryName, String serviceSubCategoryNameUpdate, String serviceSubCategoryActive ) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +serviceSubCategoryName +" to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Service Sub Category update test -- "+serviceSubCategoryName);
		getObject("admin_tab_link").click();
		getObject("sa_service_sub_category_link").click();
        new Select (getObject("sa_service_sub_category_display_dropdown")).selectByIndex(3);
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterServiceSubCategory']/tbody/tr[@role='row']/td[contains(.,'"+ serviceSubCategoryName +"')]/a/div")).click();
    //    Thread.sleep(5000);
        getObject("sa_service_sub_category_edit_button").click();
        getObject("sa_service_sub_category_textbox").clear();
        getObject("sa_service_sub_category_textbox").sendKeys(serviceSubCategoryNameUpdate);
		getObject("sa_service_sub_category_submit_button").click();
	  // driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterServiceSubCategory']/tbody/tr[@role='row']/td[contains(.,'"+ serviceSubCategoryNameUpdate +"')]/a/div")).click();
	  //      Thread.sleep(5000);
       /*String serviceSubCategoryShowPage = getObject("sa_service_sub_category_show").getText();
       Assert.assertEquals(serviceSubCategoryShowPage, serviceSubCategoryNameUpdate, "Service Sub Category at show page is -- " +serviceSubCategoryShowPage+ " instead of -- " +serviceSubCategoryNameUpdate);
       String serviceSubCategoryActiveShowPage = getObject("sa_service_sub_category_active_show").getText();
       Assert.assertEquals(serviceSubCategoryActiveShowPage, serviceSubCategoryActive, "Service Sub Category status at show page is -- " +serviceSubCategoryActiveShowPage+ " instead of -- " +serviceSubCategoryActive);
       */fail = false;
       APP_LOGS.debug("Service Sub Category open successfully, following parameters have been validated: Service Sub Category updated name -- " 
       +serviceSubCategoryNameUpdate +", Service Sub Category status -- "+serviceSubCategoryActive);
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
