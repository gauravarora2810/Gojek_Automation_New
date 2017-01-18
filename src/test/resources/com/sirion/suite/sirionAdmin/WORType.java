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

public class WORType extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void WORTypeCreation (String worType, String worTypeActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +worType +" set to NO " +count);
			}
		
		openBrowser();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin WOR Type Creation Test -- "+worType);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("WOR Type")).click();
				
		getObject("sa_wor_type_plus_button").click();
		
		getObject("sa_wor_type_name_textbox").clear();
		getObject("sa_wor_type_name_textbox").sendKeys(worType);
		
		getObject("sa_wor_type_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_wor_type_notification_popup_errors_dialogue_box").getText();
			
			getObject("sa_wor_type_notification_popup_ok_button").click();
			
			getObject("sa_wor_type_cancel_button").click();
			
			getObject("sa_wor_type_confirmation_popup_yes_button").click();
			
			fail=false;
			APP_LOGS.debug("WOR Type already exists with Name -- " +worType);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			return;
			}

        new Select(getObject("sa_wor_type_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_WorType']/tbody/tr[@role='row']/td[contains(.,'"+ worType +"')]")).click();

        String entityTypeShowPage = getObject("sa_wor_type_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, worType, "WOR Type Name at show page is -- " +entityTypeShowPage+ " instead of -- " +worType);
        
        String entityTypeActiveShowPage = getObject("sa_wor_type_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, worTypeActive, "WOR Type Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +worTypeActive);
        
        APP_LOGS.debug("WOR Type opened successfully, and following parameters have been validated: WOR Type Name -- " +worType +", WOR Type Active Status -- "+worTypeActive);
        
        fail = false;        
		getObject("sirion_admin_administration_tab_link").click();
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