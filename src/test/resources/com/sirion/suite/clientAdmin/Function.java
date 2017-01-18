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

public class Function extends TestSuiteBase {
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
	public void FunctionCreation (String function, String functionOp, String functionAlias, String functionActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +functionOp +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Function Creation Test -- "+functionOp);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Function")).click();
				
		getObject("ca_function_plus_button").click();
		
        new Select(getObject("ca_function_dropdown")).selectByVisibleText(function);
		
		getObject("ca_function_org_preference_textbox").clear();
		getObject("ca_function_org_preference_textbox").sendKeys(functionOp);
		
		getObject("ca_function_alias_textbox").clear();
		getObject("ca_function_alias_textbox").sendKeys(functionAlias);

		if(functionActive.equalsIgnoreCase("Yes")) {
			getObject("ca_function_active_checkbox").click();
			}
		
		getObject("ca_function_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_function_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_function_notification_popup_ok_button").click();
			
			getObject("ca_function_cancel_button").click();
			
			getObject("ca_function_confirmation_popup_yes_button").click();
			
			fail=false;
			APP_LOGS.debug("Function already exists with Name -- " +functionOp);
			APP_LOGS.debug("Errors: "+errors_create_page);

			return;
			}

        new Select(getObject("ca_function_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterContractType']/tbody/tr[@role='row']/td[contains(.,'"+ function +"')]/a/div")).click();
        
        String entityTypeShowPage = getObject("ca_function_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, function, "Function at show page is -- " +entityTypeShowPage+ " instead of -- " +function);

        String entityTypeOrgPreferenceShowPage = getObject("ca_function_org_preference_show_page_name").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, functionOp, "Function Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +functionOp);

        String entityTypeAliasShowPage = getObject("ca_function_alias_show_page_name").getText();
        Assert.assertEquals(entityTypeAliasShowPage, functionAlias, "Function Alias at show page is -- " +entityTypeAliasShowPage+ " instead of -- " +functionAlias);

        String entityTypeActiveShowPage = getObject("ca_function_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, functionActive, "Function Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +functionActive);
        
        APP_LOGS.debug("Function opened successfully, and following parameters have been validated: Function Name -- " +functionOp +", Function Active Status -- "+functionActive);
        
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