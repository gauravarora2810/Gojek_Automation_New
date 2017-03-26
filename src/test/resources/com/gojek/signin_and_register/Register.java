package test.resources.com.gojek.signin_and_register;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.gojek.base.TestBase;
import test.resources.com.gojek.util.DatePicker;
import test.resources.com.gojek.util.TestUtil;

public class Register extends TestSuiteBase {
	String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=false;
    static boolean skip=false;
    static boolean isTestPass=true;
    
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(signin_and_register_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(signin_and_register_suite_xls, this.getClass().getSimpleName());
    }
    
    @Test (dataProvider = "Register Sheet", groups={"Register and Login Functionality Register"})
    public void registerTest(String email_address, String send_updates, String password, String name_title, 
    String first_name, String last_name, String mobile_no, String sign_up_button) throws InterruptedException 
    {
    	System.out.println("Register class");
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
      
         
        
        	//   APP_LOGS.debug("Executing Test Case for Round Trip");
        	// Calling method to open browser
     		openBrowser();

     		// Calling a method for login(at different platform) from TestBase.java file
     		gojekSiteNavigation(CONFIG.getProperty("site_url"));
     		
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_account_link")));
     		
     		getObject("user_account_link").click();;
     		
     		
     		
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_button")));
     		
     		getObject("register_button").click();;
     		
     		
     		
     		driver.switchTo().frame("modal_window");
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_email_address")));
     		
     		getObject("register_email_address").clear();
     		getObject("register_email_address").sendKeys(email_address);
     		
     		
     		if(send_updates.equalsIgnoreCase("No"))
     		{
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_send_updates")));
     		
     		getObject("register_send_updates").click();
     		
     		}
     		

     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_create_account")));
     		
     		getObject("register_create_account").click();
     		
     		
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_signin_password")));
     		
     		getObject("register_signin_password").clear();
     		getObject("register_signin_password").sendKeys(password);
     		
     
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_your_name_title")));
     		
     		Select select_name=new Select(getObject("register_your_name_title"));
     		select_name.selectByVisibleText(name_title);
     		

     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_first_name")));
     		
     		getObject("register_first_name").clear();
     		getObject("register_first_name").sendKeys(first_name);
     		
     	
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_last_name")));
     		
     		getObject("register_last_name").clear();
     		getObject("register_last_name").sendKeys(last_name);
     		
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_mobile_no")));
     		
     		getObject("register_mobile_no").clear();
     		double mobile_no_double = Double.parseDouble(mobile_no);
     		
     		String mobile_no_string_digits=String.format ("%.0f",mobile_no_double);
     		System.out.println(mobile_no_string_digits);
     		getObject("register_mobile_no").sendKeys(mobile_no_string_digits);
     		
     		
     		/*
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#mkt_sbpt"))));
     		
     		driver.findElement(By.cssSelector("#mkt_sbpt")).click();
     		//getObject("register_send_updates").click();
     		*/
     		
     		
     		if(sign_up_button.equalsIgnoreCase("Yes"))
     		{
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_sign_up")));
     		
     		getObject("register_sign_up").click();
     		
     		}
     		
     		
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_account_link")));
     		
     		getObject("user_account_link").click();;
     		
     		
     		
     		Thread.sleep(10000);
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_sign_out")));
     		
     		getObject("user_sign_out").click();;
     		
     		
     		
     		
     		
     		
    	/*	
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_remember_password")));
     		
     		getObject("register_remember_password").click();
     		*/
     		
/*     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_signin")));
     		
     		getObject("register_signin").click();
     		*/
     		
     		Thread.sleep(10000);
            fail = false; // this executes if assertion passes

            
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(signin_and_register_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(signin_and_register_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(signin_and_register_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(signin_and_register_suite_xls, "Test Cases", TestUtil.getRowNum(signin_and_register_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(signin_and_register_suite_xls, "Test Cases", TestUtil.getRowNum(signin_and_register_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider(name="Register Sheet")
    public Object[][] getTestData(){
        return TestUtil.getData(signin_and_register_suite_xls, this.getClass().getSimpleName()) ;
    }
   
}

