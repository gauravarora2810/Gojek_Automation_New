package test.resources.com.sirion.suite.sirionAdmin;

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

public class TemplateCategory extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void TemplateCategoryCreation (String templateCategory, String templateCategoryActive) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +templateCategory +" set to NO " +count);
			}
		
		openBrowser();
		driver.manage().window().maximize();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin Template Category Creation Test -- "+templateCategory);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Template Category")).click();
		
		getObject("sa_template_category_plus_button").click();

		getObject("sa_template_category_name_textbox").clear();
		getObject("sa_template_category_name_textbox").sendKeys(templateCategory);
		
		getObject("sa_template_category_save_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_template_category_notification_popup_errors_dialogue_box").getText();
			
			getObject("sa_template_category_notification_popup_ok_button").click();
			
			getObject("sa_template_category_cancel_button").click();
			
			getObject("sa_template_category_confirmation_popup_yes_button").click();
			
			fail=false;
			APP_LOGS.debug("Template Category already exists with name -- " +templateCategory);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			return;
			}

        new Select(getObject("sa_template_category_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ templateCategory +"')]/div/a")).click();
        
        String templateCategoryShowPage = getObject("sa_template_category_show_page_name").getText();
        Assert.assertEquals(templateCategoryShowPage, templateCategory, "Template Category Name at show page is -- " +templateCategoryShowPage+ " instead of -- " +templateCategory);
        
        String templateCategoryActiveShowPage = getObject("sa_template_category_show_page_active").getText();
        Assert.assertEquals(templateCategoryActiveShowPage, templateCategoryActive, "Template Category Active status at show page is -- " +templateCategoryActiveShowPage+ " instead of -- " +templateCategoryActive);

        APP_LOGS.debug("Template Category opened successfully, and following parameters have been validated: Template Category Name -- " +templateCategory +", Template Category Active Status -- "+templateCategoryActive);

        fail = false;       
		getObject("sirion_admin_administration_tab_link").click();
		}
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	}