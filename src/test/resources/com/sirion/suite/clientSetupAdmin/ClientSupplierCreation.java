package test.resources.com.sirion.suite.clientSetupAdmin;

//import org.openqa.selenium.support.ui.Select;
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

public class ClientSupplierCreation extends TestSuiteBase
		{
			
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		System.out.println("Check for jar new");
		if(!TestUtil.isTestCaseRunnable(client_setup_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(client_setup_admin_suite_xls, this.getClass().getSimpleName());
	}
		
	@Test (dataProvider="getTestData")
	public void ClientSupplierCreate(String clientName, String clientAlias, String supplierType, String home_page_view) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set "+clientName+"- to no " +count);
		}
		

		openBrowser();
		//driver.manage().window().maximize();
		
		// creating ob category
		
		clientSetupAdminLogin(CONFIG.getProperty("ClientSetupAdminURL"), CONFIG.getProperty("ClientSetupAdminUserName"), CONFIG.getProperty("ClientSetupAdminPassword")); 
        APP_LOGS.debug("Executing Client Setup Admin, Client/Supplier Creation test -- "+clientName );
		getObject("csa_client_supplier_creation_link").click();
		getObject("csa_client_supplier_plus_button").click();
		getObject("csa_client_supplier_name").sendKeys(clientName);
		new Select(getObject("csa_client_supplier_timezone_dropdown")).selectByIndex(20);
		getObject("csa_client_supplier_alias").sendKeys(clientAlias);
		if(supplierType.equalsIgnoreCase("Yes"))
		{
		getObject("csa_client_supplier_supplier_type_checkbox").click();
		}
		
		WebElement dropdown = driver.findElement(By.xpath(".//*[@id='_homePageView.id_id']"));
		Select select = new Select(dropdown);  
		select.selectByVisibleText(home_page_view);
		
		getObject("csa_client_supplier_save_button").click();
		getObject("csa_client_supplier_submit_button").click();
		
		getObject("sa_cr_type_popup_ok_button").click();
		//Thread.sleep(10000);
		getObject("admin_tab_link").click();
	}
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_setup_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_setup_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_setup_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(client_setup_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
			}
