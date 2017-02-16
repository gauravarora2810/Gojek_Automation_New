package test.resources.com.sirion.suite.sirionAdmin;

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

public class Service extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			}
		runmodes=TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void serviceCreation(String service, String serviceActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +service +" -- set to NO " +count);
			}
		
		openBrowser();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin Service Creation Test -- "+service);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		getObject("sa_service_link").click();
				
		getObject("sa_service_plus_button").click();
		
		getObject("sa_service_name_textbox").clear();
		getObject("sa_service_name_textbox").sendKeys(service);
		
		getObject("sa_service_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_service_notification_popup_errors_dialogue_box").getText();
			
			getObject("sa_service_notification_popup_ok_button").click();
			
			getObject("sa_service_cancel_button").click();
			
			getObject("sa_service_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Service already exists with Name -- " +service);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			fail=false;
			driver.get(CONFIG.getProperty("sirionAdminURL"));
			return;
			}

        new Select(getObject("sa_service_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterContractSubType']/tbody/tr[@role='row']/td[contains(.,'"+ service +"')]/a/div")).click();
        
        String entityTypeShowPage = getObject("sa_service_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, service, "Service Name at show page is -- " +entityTypeShowPage+ " instead of -- " +service);
        
        String entityTypeActiveShowPage = getObject("sa_service_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, serviceActive, "Service Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +serviceActive);
        
        APP_LOGS.debug("Service opened successfully, and following parameters have been validated: Service Name -- " +service +", Service Active Status -- "+serviceActive);
        
		fail=false;
		driver.get(CONFIG.getProperty("sirionAdminURL"));
		}
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	}