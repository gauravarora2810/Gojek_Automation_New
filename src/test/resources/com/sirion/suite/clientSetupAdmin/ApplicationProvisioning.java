package test.resources.com.sirion.suite.clientSetupAdmin;

//import org.openqa.selenium.support.ui.Select;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ApplicationProvisioning extends TestSuiteBase
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
		
		if(!TestUtil.isTestCaseRunnable(client_setup_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(client_setup_admin_suite_xls, this.getClass().getSimpleName());
	}
		
	@Test (dataProvider="getTestData")
	public void ApplicationProvisioning(String clientName) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set "+clientName+"- to no " +count);
		}
		

		openBrowser();
		driver.manage().window().maximize();
		
		// creating ob category
		
		clientSetupAdminLogin(CONFIG.getProperty("ClientSetupAdminURL"), CONFIG.getProperty("ClientSetupAdminUserName"), CONFIG.getProperty("ClientSetupAdminPassword")); 
        APP_LOGS.debug("Executing Client Setup Admin, Application Provisioning test -- "+clientName );
		
        driver.findElement(By.xpath("//a[contains(.,'Application Provisioning')]")).click();
		driver.navigate().refresh();
        Select select_client= new Select(driver.findElement(By.xpath("//select[@id='allClients']")));
		List<WebElement> list_client=select_client.getOptions();
		System.out.println(list_client.toString());
		System.out.println("gaurav");
		select_client.selectByVisibleText(clientName);
        System.out.println(clientName); 
		//Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@value='Show']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='globaltoggleall']")).click();
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@value='Update']")));
		  
		driver.findElement(By.xpath("//input[@value='Update']")).click();
		//Thread.sleep(10000);
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
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
