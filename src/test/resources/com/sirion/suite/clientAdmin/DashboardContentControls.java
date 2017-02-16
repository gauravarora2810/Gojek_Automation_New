package test.resources.com.sirion.suite.clientAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class DashboardContentControls extends TestSuiteBase {
	String result=null;
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
	public void DashboardContentControlsTest (String chartName, String status, String StatusVolumeTrend, String StatusAgingTrend, String chartDefault,
			String chartAging, String chartVolumeTrend, String chartAgingTrend) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +chartName +" set to NO " +count);
			}
		
		openBrowser();		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Common Reports Content Controls Test -- "+chartName);

		getObject("ca_administration_tab_link").click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Report Content Controls")).click();

		getObject("ca_report_content_controls_search_textbox").sendKeys(chartName);

		getObject("ca_report_content_controls_search_update_button").click();
		
		Select statusDropdown = new Select(getObject("ca_report_content_controls_status_multi_dropdown"));
		List<WebElement> statusList = statusDropdown.getOptions();
		for(WebElement statusListElement : statusList) {
			String statusOption = statusListElement.getText();
			new Select(getObject("ca_report_content_controls_status_multi_dropdown")).selectByVisibleText(statusOption);
			}
		
		Thread.sleep(5000);
		getObject("ca_report_content_controls_save_button").click();
		
		if(driver.findElements(By.id("_params[1].statusList_id")).size()!=0) {
			Select PerformanceStatusDropdown = new Select(getObject("ca_report_content_controls_performance_status_multi_dropdown"));
			List<WebElement> PerformanceStatusList = PerformanceStatusDropdown.getOptions();
			for(WebElement PerformanceStatusListElement : PerformanceStatusList) {
				String PerformanceStatusOption = PerformanceStatusListElement.getText();
				new Select(getObject("ca_report_content_controls_performance_status_multi_dropdown")).selectByVisibleText(PerformanceStatusOption);
				}
			}

		if(driver.findElements(By.id("alertdialog")).size()!=0) {
			String errors_create_page = getObject("ca_action_type_notification_popup_alert_dialogue_box").getText();
			System.out.println("errors_create_page: "+errors_create_page);
			
			getObject("ca_quick_link_content_controls_notification_popup_ok_button").click();

			APP_LOGS.debug("Quick Link Content Controls Configured Successfully of -- " +chartName);
			APP_LOGS.debug("Errors: "+errors_create_page);
			}
		
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