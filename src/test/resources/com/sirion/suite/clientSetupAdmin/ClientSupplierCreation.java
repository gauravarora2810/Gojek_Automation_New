package test.resources.com.sirion.suite.clientSetupAdmin;

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
import test.resources.com.sirion.util.TestlinkIntegration;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class ClientSupplierCreation extends TestSuiteBase {
	String result = null;
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(client_setup_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(client_setup_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void ClientSupplierCreationTest (String testCaseid, String clientName, String clientTimezone, String clientAlias, String clientSupplierType, String clientHomePage,
			String clientEnableHTML, String clientEnableDate, String clientActive) throws InterruptedException, TestLinkAPIException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +clientName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientSetupAdminLogin(CONFIG.getProperty("ClientSetupAdminURL"), CONFIG.getProperty("ClientSetupAdminUserName"), CONFIG.getProperty("ClientSetupAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Client Setup Admin Client/Supplier Creation Test -- "+clientName);
		
		getObject("csa_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Client/Supplier Creation")).click();
				
		getObject("csa_client_creation_plus_button").click();
		
		getObject("csa_client_creation_name_textbox").clear();
		getObject("csa_client_creation_name_textbox").sendKeys(clientName);
		
		new Select(getObject("csa_client_creation_timezone_dropdown")).selectByVisibleText(clientTimezone);
		
		getObject("csa_client_creation_alias_textbox").clear();
		getObject("csa_client_creation_alias_textbox").sendKeys(clientAlias);
		
		if (clientSupplierType.equalsIgnoreCase("Yes")) {
			getObject("csa_client_creation_supplier_type_checkbox").click();
			}
		
		new Select(getObject("csa_client_creation_home_page_view_dropdown")).selectByVisibleText(clientHomePage);

		if (clientEnableHTML.equalsIgnoreCase("Yes")) {
			getObject("csa_client_creation_enable_html_checkbox").click();
			}
		
		if (clientEnableDate.equalsIgnoreCase("Yes")) {
			getObject("csa_client_creation_enable_date_checkbox").click();
			}
		
		getObject("csa_client_creation_save_button").click();
		Thread.sleep(2000);
		
		getObject("csa_client_creation_save_submit_button").click();
		
		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("csa_client_creation_notification_popup_errors_dialogue_box").getText();
			
			APP_LOGS.debug("Client/Supplier Creation already exists with Name -- " +clientName+" -- or with Alias --- "+clientAlias);
			APP_LOGS.debug("Errors: "+errors_create_page);

			fail=false;
			
			if (!fail)
		        result= TestLinkAPIResults.TEST_PASSED;
		      else   
		         result= TestLinkAPIResults.TEST_FAILED;
		     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
		     
		     driver.get(CONFIG.getProperty("ClientSetupAdminURL"));
		     return;
		     }
		
		Thread.sleep(60000);
		if (getObject("csa_client_creation_popup_client_id") != null) {
			String clientId = getObject("csa_client_creation_popup_client_id").getText();
			APP_LOGS.debug("Client/Supplier created successfully with Client/Suppplier id "+clientId);

			getObject("csa_client_creation_popup_client_id").click();
			Thread.sleep(5000);

			String clientShowPage = getObject("csa_client_creation_show_page_name").getText();

			Assert.assertEquals(clientShowPage, clientName);
			APP_LOGS.debug("Client Name on show page has been verified");
			}

		fail=false;
		
		if (!fail)
	        result= TestLinkAPIResults.TEST_PASSED;
	      else   
	         result= TestLinkAPIResults.TEST_FAILED;
	     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
	     
	     driver.get(CONFIG.getProperty("ClientSetupAdminURL"));
	     }
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_setup_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_setup_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(client_setup_admin_suite_xls, this.getClass().getSimpleName());
		}
	}