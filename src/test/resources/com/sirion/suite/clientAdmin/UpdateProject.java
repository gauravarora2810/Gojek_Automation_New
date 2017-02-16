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

public class UpdateProject extends TestSuiteBase {
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
	public void UpdateProjectTest (String projectName, String projectNameUpdate, String projectCurrencyUpdate, String projectStartDateUpdate, String projectEndDateUpdate,
			String projectTotalCostUpdate, String projectOriginalCostUpdate, String projectActiveUpdate) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +projectName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		
		APP_LOGS.debug("Executing Client Admin Project Creation Test -- "+projectNameUpdate);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Project")).click();

        new Select(getObject("ca_project_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterProject']/tbody/tr[@role='row']/td[contains(.,'"+ projectName +"')]/a/div")).click();
        
        getObject("ca_project_show_page_edit_button").click();
        
		getObject("ca_project_name_textbox").clear();
		getObject("ca_project_name_textbox").sendKeys(projectNameUpdate);
		Thread.sleep(2000);
		
		new Select(driver.findElement(By.name("currency.id"))).selectByVisibleText(projectCurrencyUpdate);
        
        if (!projectStartDateUpdate.equalsIgnoreCase("")) {
          driver.findElement(By.name("startDate")).click();
          String[] projectDate = projectStartDateUpdate.split("-");

          String projectMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
          while (!projectMonth.equalsIgnoreCase(projectDate[0])) {
        	  	driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
        	  	projectMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
          		}

          new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(projectDate[2]);

          driver.findElement(By.linkText(projectDate[1])).click();
          Thread.sleep(2000);
        	}

        if (!projectEndDateUpdate.equalsIgnoreCase("")) {
            driver.findElement(By.name("endDate")).click();
            String[] projectDate = projectEndDateUpdate.split("-");

            String projectMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
            while (!projectMonth.equalsIgnoreCase(projectDate[0])) {
          	  		driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
          	  		projectMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
            		}

            new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(projectDate[2]);

            driver.findElement(By.linkText(projectDate[1])).click();
          }

		getObject("ca_project_total_cost_numeric_box").clear();
		getObject("ca_project_total_cost_numeric_box").sendKeys(projectTotalCostUpdate);

		getObject("ca_project_original_cost_numeric_box").clear();
		getObject("ca_project_original_cost_numeric_box").sendKeys(projectOriginalCostUpdate);

		if(!projectActiveUpdate.equalsIgnoreCase("Yes")) {
			getObject("ca_project_active_checkbox").click();
			}
		
		getObject("ca_project_edit_page_update_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("ca_project_notification_popup_errors_dialogue_box").getText();
			
			getObject("ca_project_notification_popup_ok_button").click();
			
			getObject("ca_project_cancel_button").click();
			
			getObject("ca_project_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Project already exists with Name -- " +projectNameUpdate);
			APP_LOGS.debug("Errors: "+errors_create_page);

	        fail = false;
	        driver.get(CONFIG.getProperty("clientAdminURL"));
			return;
			}

        String entityTypeOrgPreferenceShowPage = getObject("ca_project_show_page_org_preference").getText();
        Assert.assertEquals(entityTypeOrgPreferenceShowPage, projectNameUpdate, "Project Org. Preference at show page is -- " +entityTypeOrgPreferenceShowPage+ " instead of -- " +projectNameUpdate);

        String entityTypeActiveShowPage = getObject("ca_project_show_page_active").getText();
        Assert.assertEquals(entityTypeActiveShowPage, projectActiveUpdate, "Project Active Status at show page is -- " +entityTypeActiveShowPage+ " instead of -- " +projectActiveUpdate);
        
        APP_LOGS.debug("Project updated successfully, and following parameters have been validated: Project Name -- " +projectNameUpdate +", Project Active Status -- "+projectActiveUpdate);
        
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