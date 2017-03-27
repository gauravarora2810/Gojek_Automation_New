package test.resources.com.gojek.suite.signin_and_register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class SignIn extends TestSuiteBase {
	static String runmodes[]=null;
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
    
    @Test (dataProvider = "SignIn Sheet" , groups={"Register and Login Functionality SignIn"})
    public  static void signInTest(String email_address, String password, String remember_me, String sign_in_button) throws SkipException, Exception 
    {
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
     		
     		
     		
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_sign_in")));
     		
     		getObject("user_sign_in").click();;
     		
     		
     		
     		driver.switchTo().frame("modal_window");
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("sign_in_user_name")));
     		
     		getObject("sign_in_user_name").clear();
     		getObject("sign_in_user_name").sendKeys(email_address);
     		
     		
     		
     		
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_signin_password")));
     		
     		getObject("register_signin_password").clear();
     		getObject("register_signin_password").sendKeys(password);
     		
     		
     		if(remember_me.equalsIgnoreCase("No"))
     		{
     		
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("sign_in_remember")));
     		
     		getObject("sign_in_remember").click();
     		
     		}
     		
     		
     		Thread.sleep(10000);
     		
     		if(sign_in_button.equalsIgnoreCase("Yes"))
     		{
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("sign_in_button")));
     		
     		getObject("sign_in_button").click();
     		
     		}
     		
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
    
    @DataProvider(name="SignIn Sheet")
    public Object[][] getTestData(){
        return TestUtil.getData(signin_and_register_suite_xls, this.getClass().getSimpleName()) ;
    }
   
}

