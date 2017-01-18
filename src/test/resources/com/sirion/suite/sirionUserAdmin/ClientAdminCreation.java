package test.resources.com.sirion.suite.sirionUserAdmin;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.Database_Password_Change_Query;
import test.resources.com.sirion.util.TestUtil;


public class ClientAdminCreation extends test.resources.com.sirion.suite.sirionUserAdmin.TestSuiteBase
{
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	public static String adminloginid=null;
	public static String ID_admin;
	public static String ID_admin_new;
	
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(user_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(user_admin_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (dataProvider="getTestData")
	public void CreateClientAdmin( String firstName, String lastName, String loginId, String emailId, String contactNo, 
								String clientSupplier, String clientSupplierAlias, String designation, String timeZone, String uniqueLoginId)
										throws InterruptedException, ClassNotFoundException, SQLException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to No " +count);
		}
	
		openBrowser();
		driver.manage().window().maximize();
		
		sirionUserAdminLogin(CONFIG.getProperty("sirionuserAdminURL"), CONFIG.getProperty("sirionuserAdminUsername"), CONFIG.getProperty("sirionuserAdminPassword"));
		APP_LOGS.debug("Executing User Admin Creation test for user --- " +firstName +" " +lastName);
			getObject("sua_user_link").click();		    
		    getObject("sua_users_plus_button").click();		    
		    getObject("sua_users_first_name").clear();
		    getObject("sua_users_first_name").sendKeys(firstName);
		    
		    getObject("sua_users_last_name").clear();
		    getObject("sua_users_last_name").sendKeys(lastName);
		    
		    adminloginid=loginId;
		    getObject("sua_users_login_id").clear();
		    getObject("sua_users_login_id").sendKeys(loginId);
		    
		    getObject("sua_users_email_id").clear();
		    getObject("sua_users_email_id").sendKeys(emailId);
		    
		    getObject("sua_users_unique_id").clear();
		    getObject("sua_users_unique_id").sendKeys(uniqueLoginId);
		    
/*		    Select selectclient = new Select (getObject("clientdropdown"));
            selectclient.selectByVisibleText(clientSupplier);*/
		    
		    new Select(getObject("sua_users_client_supplier_dropdown")).selectByVisibleText(clientSupplier);            
            getObject("sua_users_select_all_checkbox").click();            
            getObject("sua_users_save_button").click();            
            getObject("sua_users_activate_button").click();            
            getObject("sua_users_confirm_button").click();   
            
            getObject("sua_user_admin_tab_link").click();   
            
            APP_LOGS.debug("Changing password for Client Admin user --- " +firstName +" " +lastName);
            getObject("sua_user_link").click();
            
            getObject("sua_search_textbox").click();	
    		
    		
    		getObject("sua_search_textbox").sendKeys(adminloginid);
    		
  //  		FullScreenCapture newuser = new FullScreenCapture();
    		
  //  		newuser.screenshot(adminloginid);
    		
    		if(driver.getPageSource().contains(adminloginid))
    		  {
    		    APP_LOGS.debug("This username is available "+"   "+adminloginid);
    		  }
    		else
    		  {
    			APP_LOGS.debug(adminloginid+"   "+" User is not available");
    		  }
    			
    		System.out.println("admin login id is = "+adminloginid);
    	//Thread.sleep(5000);	
    	//getObject("sua_logout").click();
    	Thread.sleep(5000);
    	Database_Password_Change_Query database = new Database_Password_Change_Query();
    	database.clientAdminPasswordChange("Select id from app_user where login_id='"+adminloginid+"' and client_id in (select id from client  where alias ilike'"+clientSupplierAlias+"')" );
    	
    	Thread.sleep(10000);
    	APP_LOGS.debug("Password changed successfully for Client Admin user --- " +firstName +" " +lastName);
    	System.out.println("Password changed successfully for Client Admin user --- " +firstName +" " +lastName);
    	
    	driver.findElement(By.xpath(".//*[@id='user-adminstration']/a")).click();
    	System.out.println("Password changed successfully for Client Admin user latest --- " +firstName +" " +lastName);
    	
    	//getObject("sua_logout").click();
    	//ClientAdminlogin login = new ClientAdminlogin();
    	//login.userlogin();
		    
	  }
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(user_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(user_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(user_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
		

	}

	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(user_admin_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(user_admin_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls,this.getClass().getSimpleName()), "FAIL");

	}

	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(user_admin_suite_xls, this.getClass().getSimpleName()) ;
	}

}
