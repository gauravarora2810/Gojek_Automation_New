package test.resources.com.sirion.suite.sirionAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class CompanyPosition extends TestSuiteBase {
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
	public void CompanyPositionCreation (String companyPosition, String companyPositionActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +companyPosition +" set to NO " +count);
			}
		
		openBrowser();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin Company Position Creation Test -- "+companyPosition);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Company Position")).click();
		
		getObject("sa_company_position_plus_button").click();

		getObject("sa_company_position_name_textbox").clear();
		getObject("sa_company_position_name_textbox").sendKeys(companyPosition);
		
		getObject("sa_company_position_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_company_position_notification_popup_errors_dialogue_box").getText();
			
			getObject("sa_company_position_notification_popup_ok_button").click();
			
			getObject("sa_company_position_cancel_button").click();
			
			getObject("sa_company_position_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Company Position already exists with name -- " +companyPosition);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			fail=false;
			driver.get(CONFIG.getProperty("sirionAdminURL"));
			return;
			}

        new Select(getObject("sa_company_position_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ companyPosition +"')]/div/a")).click();
        
        String companyPositionShowPage = getObject("sa_company_position_show_page_name").getText();
        Assert.assertEquals(companyPositionShowPage, companyPosition, "Company Position Name at show page is -- " +companyPositionShowPage+ " instead of -- " +companyPosition);
        
        String companyPositionActiveShowPage = getObject("sa_company_position_show_page_active").getText();
        Assert.assertEquals(companyPositionActiveShowPage, companyPositionActive, "Company Position Active status at show page is -- " +companyPositionActiveShowPage+ " instead of -- " +companyPositionActive);

        APP_LOGS.debug("Company Position opened successfully, and following parameters have been validated: Company Position Name -- " +companyPosition +", Company Position Active Status -- "+companyPositionActive);

		fail=false;
		driver.get(CONFIG.getProperty("sirionAdminURL"));
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