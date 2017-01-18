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

public class ContractPaper extends TestSuiteBase {
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
	public void ContractPaperCreation (String contractPaper, String contractPaperOp, String contractPaperActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +contractPaperOp +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Contract Paper Creation Test -- "+contractPaperOp);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Contract Paper")).click();
				
		getObject("ca_contract_paper_plus_button").click();
		
        new Select(getObject("ca_contract_paper_dropdown")).selectByVisibleText(contractPaper);
		
		getObject("ca_contract_paper_org_preference_textbox").clear();
		getObject("ca_contract_paper_org_preference_textbox").sendKeys(contractPaperOp);

		if(contractPaperActive.equalsIgnoreCase("Yes")) {
			getObject("ca_contract_paper_active_checkbox").click();
			}
		
		getObject("ca_contract_paper_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_contract_paper_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_contract_paper_notification_popup_ok_button").click();
			
			getObject("ca_contract_paper_cancel_button").click();
			
			getObject("ca_contract_paper_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Contract Paper already exists with Name -- " +contractPaperOp);
			APP_LOGS.debug("Errors: "+errors_create_page);

	        fail = false;        
			getObject("ca_administration_tab_link").click();
			return;
			}

        new Select(getObject("ca_contract_paper_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_ContractPaper']/tbody/tr[@role='row']/td[contains(.,'"+ contractPaper +"')]/a/div")).click();
        
        String entityTypeShowPage = getObject("ca_contract_paper_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, contractPaper, "Contract Paper Name at show page is -- " +entityTypeShowPage+ " instead of -- " +contractPaper);

        String entityTypeOrgPreferenceShowPage = getObject("ca_contract_paper_org_preference_show_page_name").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, contractPaperOp, "Contract Paper Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +contractPaperOp);

        String entityTypeActiveShowPage = getObject("ca_contract_paper_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, contractPaperActive, "Contract Paper Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +contractPaperActive);
        
        APP_LOGS.debug("Contract Paper opened successfully, and following parameters have been validated: Contract Paper Name -- " +contractPaperOp +", Contract Paper Active Status -- "+contractPaperActive);
        
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