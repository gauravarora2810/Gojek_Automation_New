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

public class Geography extends TestSuiteBase {
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
	public void GeographyCreation (String geographyType, String geographyName, String geographyCurrency, String geographyTimezone, String geographyActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +geographyName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Geography Creation Test -- "+geographyName);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Geography")).click();

		getObject("ca_geography_plus_button").click();

        new Select(getObject("ca_geography_type_dropdown")).selectByVisibleText(geographyType);
        		
		getObject("ca_geography_name_textbox").clear();
		getObject("ca_geography_name_textbox").sendKeys(geographyName);
		
        new Select(getObject("ca_geography_currency_dropdown")).selectByVisibleText(geographyCurrency);

        new Select(getObject("ca_geography_timezone_dropdown")).selectByVisibleText(geographyTimezone);
        
        if(geographyType.equalsIgnoreCase("LOCAL")) {
        	if(geographyActive.equalsIgnoreCase("Yes")) {
        		getObject("ca_geography_active_checkbox").click();
        		}
        	}
		
		getObject("ca_geography_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_geography_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_geography_notification_popup_ok_button").click();
			
			getObject("ca_geography_cancel_button").click();
			
			getObject("ca_geography_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Geography already exists with Name -- " +geographyName);
			APP_LOGS.debug("Errors: "+errors_create_page);

	        fail = false;
	        driver.get(CONFIG.getProperty("clientAdminURL"));
			return;
			}

        new Select(getObject("ca_geography_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_RegionCountryStructure']/tbody/tr[@role='row']/td[contains(.,'"+ geographyName +"')]/a/div")).click();
        
        String entityTypeShowPage = getObject("ca_geography_show_page_type").getText();
        Assert.assertEquals(entityTypeShowPage, geographyType, "Geography Type at show page is -- " +entityTypeShowPage+ " instead of -- " +geographyType);

        String entityNameShowPage = getObject("ca_geography_show_page_name").getText();
        Assert.assertEquals(entityNameShowPage, geographyName, "Geography Name at show page is -- " +entityNameShowPage+ " instead of -- " +geographyName);

        String entityCurrencyShowPage = getObject("ca_geography_show_page_currency").getText();
        Assert.assertEquals(entityCurrencyShowPage, geographyCurrency, "Geography Currency at show page is -- " +entityCurrencyShowPage+ " instead of -- " +geographyCurrency);

        String entityTimezoneShowPage = getObject("ca_geography_show_page_timezone").getText();
        Assert.assertEquals(entityTimezoneShowPage, geographyTimezone, "Geography Timezone at show page is -- " +entityTimezoneShowPage+ " instead of -- " +geographyTimezone);

        String entityTypeActiveShowPage = getObject("ca_geography_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, geographyActive, "Geography Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +geographyActive);
        
        APP_LOGS.debug("Geography opened successfully, and following parameters have been validated: Geography Name -- " +geographyName +", Geography Active Status -- "+geographyActive);
        
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