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

public class Region extends TestSuiteBase {
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
	public void RegionCreation (String geography, String regionCurrency, String regionTimezone, String regionName, String regionActive, String regionCountries,
			String regionAlias) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +regionName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Region Creation Test -- "+regionName);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Geography")).click();
		
        new Select(getObject("ca_geography_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_RegionCountryStructure']/tbody/tr[@role='row']/td[contains(.,'"+ geography +"')]/a/div")).click();

        driver.findElement(By.id("ui-id-3")).click();

		getObject("ca_region_plus_button").click();

        new Select(getObject("ca_region_currency_dropdown")).selectByVisibleText(regionCurrency);
        		
        new Select(getObject("ca_region_timezone_dropdown")).selectByVisibleText(regionTimezone);

        getObject("ca_region_name_textbox").clear();
		getObject("ca_region_name_textbox").sendKeys(regionName);
		
		if(regionActive.equalsIgnoreCase("Yes")) {
			getObject("ca_region_active_checkbox").click();
			}

		if(!regionCountries.equalsIgnoreCase("")) {
			for(String regionData : regionCountries.split(";")) {
		        new Select(getObject("ca_region_countries_multi_dropdown")).selectByVisibleText(regionData.trim());
		        }
			}
		
        getObject("ca_region_alias_textbox").clear();
		getObject("ca_region_alias_textbox").sendKeys(regionAlias);
		
		getObject("ca_region_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_region_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_region_notification_popup_ok_button").click();
			
			getObject("ca_region_cancel_button").click();
			
			getObject("ca_region_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Region already exists with Name -- " +regionName);
			APP_LOGS.debug("Errors: "+errors_create_page);

	        fail = false;
	        driver.get(CONFIG.getProperty("clientAdminURL"));
			return;
			}
		
        driver.findElement(By.id("ui-id-3")).click();

        new Select(getObject("ca_region_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterRegion']/tbody/tr[@role='row']/td[contains(.,'"+ regionName +"')]/a/div")).click();
        
        String entityNameShowPage = getObject("ca_region_show_page_name").getText();
        Assert.assertEquals(entityNameShowPage, regionName, "Region Name at show page is -- " +entityNameShowPage+ " instead of -- " +regionName);

        String entityCurrencyShowPage = getObject("ca_region_show_page_currency").getText();
        Assert.assertEquals(entityCurrencyShowPage, regionCurrency, "Region Currency at show page is -- " +entityCurrencyShowPage+ " instead of -- " +regionCurrency);

        String entityTimezoneShowPage = getObject("ca_region_show_page_timezone").getText();
        Assert.assertEquals(entityTimezoneShowPage, regionTimezone, "Region Timezone at show page is -- " +entityTimezoneShowPage+ " instead of -- " +regionTimezone);

        String entityTypeActiveShowPage = getObject("ca_region_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, regionActive, "Region Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +regionActive);
        
        APP_LOGS.debug("Region opened successfully, and following parameters have been validated: Region Name -- " +regionName +", Region Active Status -- "+regionActive);
        
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