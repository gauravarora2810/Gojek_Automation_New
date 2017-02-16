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

public class IndustryType extends TestSuiteBase {
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
	public void IndustryTypeCreation (String industryType, String industryTypeActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +industryType +" set to NO " +count);
			}
		
		openBrowser();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin Industry Type Creation Test -- "+industryType);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Industry Type")).click();
				
		getObject("sa_industry_type_plus_button").click();
		
		getObject("sa_industry_type_name_textbox").clear();
		getObject("sa_industry_type_name_textbox").sendKeys(industryType);
		
		getObject("sa_industry_type_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_industry_type_notification_popup_errors_dialogue_box").getText();
			
			getObject("sa_industry_type_notification_popup_ok_button").click();
			
			getObject("sa_industry_type_cancel_button").click();
			
			getObject("sa_industry_type_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Industry Type already exists with name -- " +industryType);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			fail=false;
			driver.get(CONFIG.getProperty("sirionAdminURL"));
			return;
			}

        new Select(getObject("sa_industry_type_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ industryType +"')]/div/a")).click();
        
        String industryTypeShowPage = getObject("sa_industry_type_show_page_name").getText();
        Assert.assertEquals(industryTypeShowPage, industryType, "Industry Type Name at show page is -- " +industryTypeShowPage+ " instead of -- " +industryType);
        
        String industryTypeActiveShowPage = getObject("sa_industry_type_show_page_active").getText();
        Assert.assertEquals(industryTypeActiveShowPage, industryTypeActive, "Industry Type Active Status at show page is -- " +industryTypeActiveShowPage+ " instead of -- " +industryTypeActive);
        
        APP_LOGS.debug("Industry Type opened successfully, and following parameters have been validated: Industry Type Name -- " +industryType +", Industry Type Active Status -- "+industryTypeActive);
        
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