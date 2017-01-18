package test.resources.com.sirion.suite.clientAdmin;

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

public class UpdateOBPerformanceType extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(client_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(client_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void UpdateOBPerformanceTypeTest (String obPerformanceType, String obPerformanceTypeOpUpdate, String obPerformanceTypeActiveUpdate) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +obPerformanceTypeOpUpdate +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin OB Performance Type Update Test -- "+obPerformanceTypeOpUpdate);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("OB Performance Type")).click();

        new Select(getObject("ca_ob_performance_type_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterPerformanceType']/tbody/tr[@role='row']/td[contains(.,'"+ obPerformanceType +"')]/a/div")).click();

        getObject("ca_ob_performance_type_show_page_edit_button").click();
        Thread.sleep(2000);
		
		getObject("ca_ob_performance_type_edit_page_org_preference_textbox").clear();
		getObject("ca_ob_performance_type_edit_page_org_preference_textbox").sendKeys(obPerformanceTypeOpUpdate);

		if(!obPerformanceTypeActiveUpdate.equalsIgnoreCase("Yes")) {
			getObject("ca_ob_performance_type_active_checkbox").click();
			}
		
		getObject("ca_ob_performance_type_edit_page_update_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_ob_performance_type_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_ob_performance_type_notification_popup_ok_button").click();
			
			getObject("ca_ob_performance_type_cancel_button").click();
			
			getObject("ca_ob_performance_type_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("OB Performance Type already exists with Name -- " +obPerformanceTypeOpUpdate);
			APP_LOGS.debug("Errors: "+errors_create_page);

			fail=false;
			getObject("ca_administration_tab_link").click();
			return;
			}

        String entityTypeShowPage = getObject("ca_ob_performance_type_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, obPerformanceType, "OB Performance Type Name at show page is -- " +entityTypeShowPage+ " instead of -- " +obPerformanceType);

        String entityTypeOrgPreferenceShowPage = getObject("ca_ob_performance_type_org_preference_show_page_name").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, obPerformanceTypeOpUpdate, "OB Performance Type Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +obPerformanceTypeOpUpdate);

        String entityTypeActiveShowPage = getObject("ca_ob_performance_type_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, obPerformanceTypeActiveUpdate, "OB Performance Type Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +obPerformanceTypeActiveUpdate);
        
        APP_LOGS.debug("OB Performance Type Updated successfully, and following parameters have been validated: OB Performance Type Name -- " +obPerformanceType +", OB Performance Type Active Status -- "+obPerformanceTypeActiveUpdate);
        
        fail = false;        
		getObject("ca_administration_tab_link").click();
		}
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName());
		}
	}