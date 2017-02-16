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
import testlink.api.java.client.TestLinkAPIException;

public class ApplicationProvisioning extends TestSuiteBase {
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
	public void ApplicationProvisioningTest (String testCaseid, String clientName) throws InterruptedException, TestLinkAPIException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +clientName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientSetupAdminLogin(CONFIG.getProperty("ClientSetupAdminURL"), CONFIG.getProperty("ClientSetupAdminUserName"), CONFIG.getProperty("ClientSetupAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Client Setup Admin Application Provisioning Setup Test -- "+clientName);
		
		getObject("csa_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Application Provisioning")).click();
		
		new Select(getObject("csa_application_provisioning_select_client_dropdown")).selectByVisibleText(clientName);
		
		getObject("csa_application_provisioning_show_button").click();
		
		getObject("csa_application_provisioning_select_all_checkbox").click();
		
		getObject("csa_application_provisioning_update_button").click();
		Thread.sleep(2000);		
		
		if (getObject("csa_application_provisioning_notification_popup_box") != null) {
			String clientShowPage = getObject("csa_application_provisioning_notification_popup_box").getText();

			Assert.assertEquals(clientShowPage, "Updated Successfully!!");
			APP_LOGS.debug("Client Provisioning has done Successfully");
			
			getObject("csa_application_provisioning_notification_popup_ok_button").click();
			}

		fail=false;
		
/*		if (!fail)
	        result= TestLinkAPIResults.TEST_PASSED;
	      else   
	         result= TestLinkAPIResults.TEST_FAILED;
	     TestlinkIntegration.updateTestLinkResult(testCaseid,"",result);
*/	     
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