package test.resources.com.sirion.suite.clientAdmin;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.Database_Password_Change_Query;
import test.resources.com.sirion.util.TestUtil;
import testlink.api.java.client.TestLinkAPIException;

public class ClientUserCreation extends TestSuiteBase {
	String result = null;
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
	public void ClientUserCreationTest (String testCaseid, String userFirstName, String userLastName, String userLoginId, String userEmailId,
			String userContactNo, String userDesignation, String userTimezone, String userUniqueLoginId, String userDefaultTier, String userLegalDocument,
			String userFinancialDocument, String userBusinessCase, String userPasswordExpires, String userType, String userLanguage, String userSendEmail,
			String userClientAlias) throws InterruptedException, TestLinkAPIException, ClassNotFoundException, SQLException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +userLoginId +" set to NO " +count);
			}

		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin User Creation Test -- "+userLoginId);
		
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("ca_administration_tab_link")));
		getObject("ca_user_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("User Configuration")).click();
				
		getObject("ca_user_configuration_plus_button").click();
		
		getObject("ca_user_configuration_first_name_textbox").clear();
		getObject("ca_user_configuration_first_name_textbox").sendKeys(userFirstName);
		
		getObject("ca_user_configuration_last_name_textbox").clear();
		getObject("ca_user_configuration_last_name_textbox").sendKeys(userLastName);

		getObject("ca_user_configuration_login_textbox").clear();
		getObject("ca_user_configuration_login_textbox").sendKeys(userLoginId);
		
		getObject("ca_user_configuration_email_textbox").clear();
		getObject("ca_user_configuration_email_textbox").sendKeys(userEmailId);
		
		if (!userContactNo.equalsIgnoreCase("")) {			
			Double temp_userContactNo_double = Double.parseDouble(userContactNo);
			int temp_userContactNo_int = temp_userContactNo_double.intValue();
			String temp_userContactNo_string = Integer.toString(temp_userContactNo_int);		
			
			getObject("ca_user_configuration_contact_number_numeric_box").clear();
			getObject("ca_user_configuration_contact_number_numeric_box").sendKeys(temp_userContactNo_string);
			}

		if (!userDesignation.equalsIgnoreCase("")) {
			getObject("ca_user_configuration_designation_textbox").clear();
			getObject("ca_user_configuration_designation_textbox").sendKeys(userDesignation);
			}

		new Select(getObject("ca_user_configuration_timezone_dropdown")).selectByVisibleText(userTimezone);
		
		getObject("ca_user_configuration_unique_login_textbox").clear();
		getObject("ca_user_configuration_unique_login_textbox").sendKeys(userUniqueLoginId);
		
		new Select(getObject("ca_user_configuration_default_tier_dropdown")).selectByVisibleText(userDefaultTier);
		
		if (userLegalDocument.equalsIgnoreCase("Yes")) {
			getObject("ca_user_configuration_legal_document_checkbox").click();
			}

		if (userFinancialDocument.equalsIgnoreCase("Yes")) {
			getObject("ca_user_configuration_financial_document_checkbox").click();
			}

		if (userBusinessCase.equalsIgnoreCase("Yes")) {
			getObject("ca_user_configuration_business_case_checkbox").click();
			}

		if (userPasswordExpires.equalsIgnoreCase("Yes")) {
			getObject("ca_user_configuration_password_never_expires_checkbox").click();
			}
		
		new Select(getObject("ca_user_configuration_user_type_dropdown")).selectByVisibleText(userType);

		new Select(getObject("ca_user_configuration_language_dropdown")).selectByVisibleText(userLanguage);

		getObject("ca_user_configuration_send_email_checkbox").click();
		
		driver.findElement(By.className("globaltoggleall")).click();
		
		driver.findElement(By.id("9683_read")).click();
		
		driver.findElement(By.id("9683_write")).click();

		getObject("ca_user_configuration_save_button").click();
		Thread.sleep(10000);
		
		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_user_configuration_notification_popup_errors_dialogue_box").getText();
			
			APP_LOGS.debug("Client/Supplier User already exists with Login ID -- " +userLoginId+" -- or with Unique Login ID --- "+userUniqueLoginId+" -- or with Email --- "+userEmailId);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			getObject("ca_user_configuration_notification_popup_ok_button").click();
			
//			getObject("ca_user_configuration_cancel_button").click();

//			getObject("ca_user_configuration_notification_popup_yes_button").click();

			fail=false;
/*			
			if (!fail)
		        result= TestLinkAPIResults.TEST_PASSED;
		      else   
		         result= TestLinkAPIResults.TEST_FAILED;
		     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
*/		     
		     driver.get(CONFIG.getProperty("clientAdminURL"));
		     return;
		     }

		getObject("ca_user_configuration_activate_button").click();
		Thread.sleep(30000);

		if(driver.findElements(By.id("alertdialog")).size()!=0) {
			String errors_create_page = getObject("ca_user_configuration_notification_popup_alerts_dialogue_box").getText();
			System.out.println("errors_create_page: "+errors_create_page);
			
			APP_LOGS.debug("Client/Supplier User created successfully with Login ID -- " +userLoginId);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			getObject("ca_user_configuration_notification_popup_ok_button").click();
			
			String clientShowPageLoginID = getObject("ca_user_configuration_show_page_login_id").getText();
			
			Assert.assertEquals(clientShowPageLoginID, userLoginId);
			APP_LOGS.debug("Client User Login ID on show page has been verified");
			
	    	Database_Password_Change_Query.clientUserPasswordChange("Select id from app_user where login_id='"+userLoginId+"' and client_id in (select id from client  where alias ilike'"+userClientAlias+"')" );
	    	
	    	Thread.sleep(10000);
	    	APP_LOGS.debug("Password changed successfully for Client End User --- " +userFirstName +" " +userLastName);
	    	System.out.println("Password changed successfully for Client End User --- " +userFirstName +" " +userLastName);
			}	

		fail=false;
/*		
		if (!fail)
	        result= TestLinkAPIResults.TEST_PASSED;
	      else   
	         result= TestLinkAPIResults.TEST_FAILED;
	     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
*/	     
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