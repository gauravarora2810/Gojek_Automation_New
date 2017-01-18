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

public class SLSubCategory extends TestSuiteBase {
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
	public void SLSubCategoryCreation (String slCategory, String slSubCategory, String slSubCategoryOp, String slSubCategoryActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +slSubCategoryOp +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin SL Sub-Category Creation Test -- "+slSubCategoryOp);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("SL Category")).click();
		
        new Select(getObject("ca_sl_category_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterSlacategory']/tbody/tr[@role='row']/td[contains(.,'"+ slCategory +"')]/a/div")).click();

        driver.findElement(By.id("ui-id-3")).click();
				
		getObject("ca_sl_sub_category_plus_button").click();
		
        new Select(getObject("ca_sl_sub_category_dropdown")).selectByVisibleText(slSubCategory);
		
		getObject("ca_sl_sub_category_org_preference_textbox").clear();
		getObject("ca_sl_sub_category_org_preference_textbox").sendKeys(slSubCategoryOp);

		if(slSubCategoryActive.equalsIgnoreCase("Yes")) {
			getObject("ca_sl_sub_category_active_checkbox").click();
			}
		
		getObject("ca_sl_sub_category_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_sl_sub_category_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_sl_sub_category_notification_popup_ok_button").click();
			
			getObject("ca_sl_sub_category_cancel_button").click();
			
			getObject("ca_sl_sub_category_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("SL Sub-Category already exists with Name -- " +slSubCategoryOp);
			APP_LOGS.debug("Errors: "+errors_create_page);

			fail=false;
			getObject("ca_administration_tab_link").click();
			return;
			}
		
        driver.findElement(By.id("ui-id-3")).click();

        new Select(getObject("ca_sl_sub_category_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterSlasubCategory']/tbody/tr[@role='row']/td[contains(.,'"+ slSubCategory +"')]/a/div")).click();
        
        String entityTypeShowPage = getObject("ca_sl_sub_category_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, slSubCategory, "SL Sub-Category Name at show page is -- " +entityTypeShowPage+ " instead of -- " +slSubCategory);

        String entityTypeOrgPreferenceShowPage = getObject("ca_sl_sub_category_org_preference_show_page_name").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, slSubCategoryOp, "SL Sub-Category Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +slSubCategoryOp);

        String entityTypeActiveShowPage = getObject("ca_sl_sub_category_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, slSubCategoryActive, "SL Sub-Category Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +slSubCategoryActive);
        
        APP_LOGS.debug("SL Sub-Category opened successfully, and following parameters have been validated: SL Sub-Category Name -- " +slSubCategoryOp +", SL Sub-Category Active Status -- "+slSubCategoryActive);
        
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