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

public class RoleGroup extends TestSuiteBase {
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
	public void RoleGroupCreation (String roleGroupEntity, String roleGroupName, String roleGroupDisplayName, String roleGroupOrder, String defaultLoggedinUser,
			String multipleUser, String roleGroupActive, String includeInGridView, String stakeholderSubGroup, String roleGroupSelectAll) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +roleGroupName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Role Group Creation Test -- "+roleGroupName);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Role Group")).click();
				
		getObject("ca_role_group_plus_button").click();
		
		new Select(getObject("ca_role_group_entity_type_dropdown")).selectByVisibleText(roleGroupEntity);
		
		Select select_columns_to_report_list = new Select(getObject("ca_role_group_columns_listing_multi_dropdown"));
		List<WebElement> list_of_columns_to_report_list = select_columns_to_report_list.getOptions();
		for(WebElement options_of_columns_to_report_list:list_of_columns_to_report_list) {
			String value_of_columns_to_report_list=options_of_columns_to_report_list.getText();
			new Select(getObject("ca_role_group_columns_listing_multi_dropdown")).selectByVisibleText(value_of_columns_to_report_list);
			}

		Select select_columns_to_dashboards = new Select(getObject("ca_role_group_columns_dashboards_multi_dropdown"));
		List<WebElement> list_of__columns_to_dashboards = select_columns_to_dashboards.getOptions();
		for(WebElement options_of__columns_to_dashboards:list_of__columns_to_dashboards) {
			String value_of__columns_to_dashboards=options_of__columns_to_dashboards.getText();
			new Select(getObject("ca_role_group_columns_dashboards_multi_dropdown")).selectByVisibleText(value_of__columns_to_dashboards);
			}

		Select select_user_types = new Select(getObject("ca_role_group_user_types_multi_dropdown"));
		List<WebElement> list_of_user_types = select_user_types.getOptions();
		for(WebElement options_of_user_types:list_of_user_types) {
			String value_of_user_types=options_of_user_types.getText();
			new Select(getObject("ca_role_group_user_types_multi_dropdown")).selectByVisibleText(value_of_user_types);
			}
		
		getObject("ca_role_group_name_textbox").clear();
		getObject("ca_role_group_name_textbox").sendKeys(roleGroupName);

		getObject("ca_role_group_display_name_textbox").clear();
		getObject("ca_role_group_display_name_textbox").sendKeys(roleGroupDisplayName);
		
	    Double temp_roleGroupOrder_double = Double.parseDouble(roleGroupOrder);
	    int temp_roleGroupOrder_int = temp_roleGroupOrder_double.intValue();
	    String roleGroupOrder_string = Integer.toString(temp_roleGroupOrder_int);

		getObject("ca_role_group_order_numeric_box").sendKeys(roleGroupOrder_string);
		
		if(defaultLoggedinUser.equalsIgnoreCase("Yes")) {
			getObject("ca_role_group_default_loggedin_checkbox").click();
			}

		if(multipleUser.equalsIgnoreCase("Yes")) {
			getObject("ca_role_group_multi_user_checkbox").click();
			}

		if(roleGroupActive.equalsIgnoreCase("Yes")) {
			getObject("ca_role_group_active_checkbox").click();
			}
		
		if(roleGroupEntity.equalsIgnoreCase("Suppliers") || roleGroupEntity.equalsIgnoreCase("Clients")) {
			if(includeInGridView.equalsIgnoreCase("Yes")) {
				getObject("ca_role_group_include_grid_view_checkbox").click();
				}
			}
        
		new Select(getObject("ca_role_group_stakeholder_dropdown")).selectByVisibleText(stakeholderSubGroup);
		
		if(roleGroupSelectAll.equalsIgnoreCase("Yes")) {
			getObject("ca_role_group_select_all_checkbox").click();
			}		

		getObject("ca_role_group_save_button").click();

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