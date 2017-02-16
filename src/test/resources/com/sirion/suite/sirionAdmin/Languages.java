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

public class Languages extends TestSuiteBase {
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
	public void LanguagesCreation (String languagesName) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +languagesName +" set to NO " +count);
			}
		
		openBrowser();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin Languages Creation Test -- "+languagesName);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Languages")).click();
				
		getObject("sa_languages_plus_button").click();
		
		getObject("sa_languages_name_textbox").clear();
		getObject("sa_languages_name_textbox").sendKeys(languagesName);
		
		String projectPath = System.getProperty("user.dir");
		
		getObject("sa_languages_upload_file_button").sendKeys(projectPath+"\\DataPackTemplate.xls");
		Thread.sleep(10000);
		
		getObject("sa_languages_update_button").click();

		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_languages_notification_popup_errors_dialogue_box").getText();
			
			Assert.assertEquals(errors_create_page, "Select A Unique Language Name");

			getObject("sa_languages_notification_popup_ok_button").click();
			
			APP_LOGS.debug("Languages already exists with Name -- " +languagesName);
			APP_LOGS.debug("Errors: "+errors_create_page);

			fail=false;
			driver.get(CONFIG.getProperty("sirionAdminURL"));
			return;
			}

		Thread.sleep(10000);
        if (driver.getPageSource().contains("Language created successfully")) {
        	APP_LOGS.debug("Language created successfully");

    		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
    		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
    		Thread.sleep(5000);
    		
            new Select(getObject("sa_languages_listing_page_display_dropdown")).selectByIndex(3);
            
    		driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'" +languagesName+ "')]/preceding-sibling::td[1]/a")).click();

            String entityTypeShowPage = getObject("sa_languages_show_page_name").getText();
            Assert.assertEquals(entityTypeShowPage, languagesName, "Languages Name at show page is -- " +entityTypeShowPage+ " instead of -- " +languagesName);
            
            APP_LOGS.debug("Languages opened successfully, and following parameters have been validated: Languages Name -- " +languagesName);
        	} 
        
		fail=false;
		driver.get(CONFIG.getProperty("sirionAdminURL"));
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