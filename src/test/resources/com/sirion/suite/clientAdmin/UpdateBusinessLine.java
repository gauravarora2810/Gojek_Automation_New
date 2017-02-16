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

public class UpdateBusinessLine extends TestSuiteBase {
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
	public void UpdateBusinessLineTest (String businessLine, String businessLineOpUpdate, String businessLineActiveUpdate) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +businessLineOpUpdate +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Business Line Update Test -- "+businessLineOpUpdate);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Business Line")).click();

        new Select(getObject("ca_business_line_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ businessLine +"')]/div/a")).click();
        
        getObject("ca_business_line_show_page_edit_button").click();
        
		getObject("ca_business_line_org_preference_textbox").clear();
		getObject("ca_business_line_org_preference_textbox").sendKeys(businessLineOpUpdate);

		if(!businessLineActiveUpdate.equalsIgnoreCase("Yes")) {
			getObject("ca_business_line_active_checkbox").click();
			}
		
		getObject("ca_business_line_edit_page_update_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_business_line_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_business_line_notification_popup_ok_button").click();
			
			getObject("ca_business_line_cancel_button").click();
			
			getObject("ca_business_line_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Business Line already exists with Name -- " +businessLineOpUpdate);
			APP_LOGS.debug("Errors: "+errors_create_page);

	        fail = false;
	        driver.get(CONFIG.getProperty("clientAdminURL"));
			return;
			}

        String entityTypeShowPage = getObject("ca_business_line_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, businessLine, "Business Line Name at show page is -- " +entityTypeShowPage+ " instead of -- " +businessLine);

        String entityTypeOrgPreferenceShowPage = getObject("ca_business_line_org_preference_show_page_name").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, businessLineOpUpdate, "Business Line Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +businessLineOpUpdate);

        String entityTypeActiveShowPage = getObject("ca_business_line_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, businessLineActiveUpdate, "Business Line Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +businessLineActiveUpdate);
        
        APP_LOGS.debug("Business Line updated successfully, and following parameters have been validated: Business Line Name -- " +businessLineOpUpdate +", Business Line Active Status -- "+businessLineActiveUpdate);
        
        fail = false;
        driver.get(CONFIG.getProperty("clientAdminURL"));
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