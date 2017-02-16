package test.resources.com.sirion.suite.sirionUserAdmin;

import java.sql.SQLException;

import org.openqa.selenium.By;
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
import test.resources.com.sirion.util.TestlinkIntegration;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class ClientAdminCreation extends TestSuiteBase {
	String result = null;
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(user_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(user_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void ClientAdminCreationTest (String testCaseid, String clientFirstName, String clientLastName, String clientLoginId, String clientEmailId,
			String clientContactNo, String clientDesignation, String clientTimezone, String clientUniqueLoginId, String clientSupplier, String clientSupplierAlias,
			String clientSendEmail) throws InterruptedException, TestLinkAPIException, ClassNotFoundException, SQLException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +clientLoginId +" set to NO " +count);
			}

		openBrowser();
		
		sirionUserAdminLogin(CONFIG.getProperty("sirionuserAdminURL"), CONFIG.getProperty("sirionuserAdminUsername"), CONFIG.getProperty("sirionuserAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion User Admin Client/Supplier Admin Creation Test -- "+clientLoginId);
		
		getObject("sua_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("User Configuration")).click();
				
		getObject("sua_user_configuration_plus_button").click();
		
		getObject("sua_user_configuration_first_name_textbox").clear();
		getObject("sua_user_configuration_first_name_textbox").sendKeys(clientFirstName);
		
		getObject("sua_user_configuration_last_name_textbox").clear();
		getObject("sua_user_configuration_last_name_textbox").sendKeys(clientLastName);

		getObject("sua_user_configuration_login_textbox").clear();
		getObject("sua_user_configuration_login_textbox").sendKeys(clientLoginId);
		
		getObject("sua_user_configuration_email_textbox").clear();
		getObject("sua_user_configuration_email_textbox").sendKeys(clientEmailId);
		
		if (!clientContactNo.equalsIgnoreCase("")) {
			Double temp_clientContactNo_double = Double.parseDouble(clientContactNo);
			int temp_clientContactNo_int = temp_clientContactNo_double.intValue();
			String temp_clientContactNo_string = Integer.toString(temp_clientContactNo_int);		
			
			getObject("sua_user_configuration_contact_number_textbox").clear();
			getObject("sua_user_configuration_contact_number_textbox").sendKeys(temp_clientContactNo_string);
			}

		if (!clientDesignation.equalsIgnoreCase("")) {
			getObject("sua_user_configuration_designation_textbox").clear();
			getObject("sua_user_configuration_designation_textbox").sendKeys(clientDesignation);
			}

		new Select(getObject("sua_user_configuration_timezone_dropdown")).selectByVisibleText(clientTimezone);
		
		getObject("sua_user_configuration_unique_login_textbox").clear();
		getObject("sua_user_configuration_unique_login_textbox").sendKeys(clientUniqueLoginId);
		
		new Select(getObject("sua_user_configuration_select_client_dropdown")).selectByVisibleText(clientSupplier);
		
		if (clientSendEmail.equalsIgnoreCase("Yes")) {
			getObject("sua_user_configuration_send_email_checkbox").click();
			}
		
		getObject("sua_user_configuration_select_all_checkbox").click();
		
		getObject("sua_user_configuration_save_button").click();
		Thread.sleep(5000);

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sua_user_configuration_notification_popup_errors_dialogue_box").getText();
			
			APP_LOGS.debug("Client/Supplier Admin already exists with Login ID -- " +clientLoginId+" -- or with Unique Login ID --- "+clientUniqueLoginId+" -- or with Email --- "+clientEmailId);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			getObject("sua_user_configuration_notification_popup_ok_button").click();
			
			getObject("sua_user_configuration_cancel_button").click();
			
			getObject("sua_user_configuration_notification_popup_yes_button").click();

			fail=false;
			
			if (!fail)
		        result= TestLinkAPIResults.TEST_PASSED;
		      else   
		         result= TestLinkAPIResults.TEST_FAILED;
		     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
		     
		     driver.get(CONFIG.getProperty("sirionuserAdminURL"));
		     return;
		     }

		getObject("sua_user_configuration_activate_button").click();
		Thread.sleep(30000);
		
		if(driver.findElements(By.id("alertdialog")).size()!=0) {
			String errors_create_page = getObject("sua_user_configuration_notification_popup_alerts_dialogue_box").getText();
			
			APP_LOGS.debug("Client/Supplier Admin created successfully with Login ID -- " +clientLoginId);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			getObject("sua_user_configuration_notification_popup_ok_button").click();
			
			String clientShowPageLoginID = getObject("sua_user_configuration_show_page_login_id").getText();
			
			Assert.assertEquals(clientShowPageLoginID, clientLoginId);
			APP_LOGS.debug("Client Login ID on show page has been verified");
			
	    	Database_Password_Change_Query.clientUserPasswordChange("Select id from app_user where login_id='"+clientLoginId+"' and client_id in (select id from client  where alias ilike'"+clientSupplierAlias+"')" );
	    	
	    	Thread.sleep(10000);
	    	APP_LOGS.debug("Password changed successfully for Client Admin User --- " +clientFirstName +" " +clientLastName);
	    	System.out.println("Password changed successfully for Client Admin User --- " +clientFirstName +" " +clientLastName);
			}	

		fail=false;
		
		if (!fail)
	        result= TestLinkAPIResults.TEST_PASSED;
	      else   
	         result= TestLinkAPIResults.TEST_FAILED;
	     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
	     
	     driver.get(CONFIG.getProperty("sirionuserAdminURL"));
	     }
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(user_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(user_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(user_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(user_admin_suite_xls, "Test Cases", TestUtil.getRowNum(user_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(user_admin_suite_xls, "Test Cases", TestUtil.getRowNum(user_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(user_admin_suite_xls, this.getClass().getSimpleName());
		}
	}