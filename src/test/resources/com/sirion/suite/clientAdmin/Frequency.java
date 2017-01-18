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

public class Frequency extends TestSuiteBase {
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
	public void FrequencyCreation (String frequency, String frequencyOp, String frequencyActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +frequencyOp +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Frequency Creation Test -- "+frequencyOp);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Frequency")).click();
				
		getObject("ca_frequency_plus_button").click();
		
        new Select(getObject("ca_frequency_frequency_dropdown")).selectByVisibleText(frequency);
		
		getObject("ca_frequency_org_preference_textbox").clear();
		getObject("ca_frequency_org_preference_textbox").sendKeys(frequencyOp);

		if(frequencyActive.equalsIgnoreCase("Yes")) {
			getObject("ca_frequency_active_checkbox").click();
			}
		
		getObject("ca_frequency_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_frequency_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_frequency_notification_popup_ok_button").click();
			
			getObject("ca_frequency_cancel_button").click();
			
			getObject("ca_frequency_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Frequency already exists with Name -- " +frequencyOp);
			APP_LOGS.debug("Errors: "+errors_create_page);

	        fail = false;        
			getObject("ca_administration_tab_link").click();
			return;
			}

        new Select(getObject("ca_frequency_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterFrequency']/tbody/tr[@role='row']/td[contains(.,'"+ frequency +"')]/a/div")).click();
        
        String entityTypeOrgPreferenceShowPage = getObject("ca_frequency_show_page_org_preference").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, frequencyOp, "Frequency Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +frequencyOp);

        String entityTypeActiveShowPage = getObject("ca_frequency_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, frequencyActive, "Frequency Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +frequencyActive);
        
        APP_LOGS.debug("Frequency opened successfully, and following parameters have been validated: Frequency Name -- " +frequencyOp +", Frequency Active Status -- "+frequencyActive);
        
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