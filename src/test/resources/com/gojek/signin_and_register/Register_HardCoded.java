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

public class Register_HardCoded extends TestSuiteBase {
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
    
    @Test (dataProvider = "getTestData")
    public void registerTest() throws InterruptedException 
    {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
      System.out.println("gaurav");
        System.out.println("kumar"); 
        System.out.println("arora");
        	//   APP_LOGS.debug("Executing Test Case for Round Trip");
        	// Calling method to open browser
     		openBrowser();

     		// Calling a method for login(at different platform) from TestBase.java file
     		gojekSiteNavigation(CONFIG.getProperty("site_url"));
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_account_link")));
     		System.out.println("kumar");
     		getObject("user_account_link").click();;
     		System.out.println("arora");
     		
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_button")));
     		System.out.println("kumar");
     		getObject("register_button").click();;
     		System.out.println("arora");
     		
     		
     		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));

     		/*Set<String> obj= driver.getWindowHandles();
     		
     		for(String window_name:obj)
     		{
     			System.out.println(window_name);
     			//driver.switchTo().window(window_name);
     		}*/
     		
     		System.out.println(iframeElements);
     		
     		driver.switchTo().frame("modal_window");
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_email_address")));
     		System.out.println("kumar");
     		getObject("register_email_address").clear();
     		getObject("register_email_address").sendKeys("gauravarora2810@gmail.com");
     		System.out.println("arora");
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_send_updates")));
     		System.out.println("kumar");
     		getObject("register_send_updates").click();
     		System.out.println("arora");
     		

     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_create_account")));
     		System.out.println("kumar");
     		getObject("register_create_account").click();
     		System.out.println("arora");
     		
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_password")));
     		System.out.println("kumar");
     		getObject("register_password").clear();
     		getObject("register_password").sendKeys("admin1234");
     		System.out.println("arora");
     	
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_your_name_title")));
     		System.out.println("kumar");
     		Select select_name=new Select(getObject("register_your_name_title"));
     		select_name.selectByVisibleText("Mr");
     		System.out.println("arora");

     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_first_name")));
     		System.out.println("kumar");
     		getObject("register_first_name").clear();
     		getObject("register_first_name").sendKeys("Gaurav");
     		System.out.println("arora");
     	
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_last_name")));
     		System.out.println("kumar");
     		getObject("register_last_name").clear();
     		getObject("register_last_name").sendKeys("Arora");
     		System.out.println("arora");
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.visibilityOf(getObject("register_mobile_no")));
     		System.out.println("kumar");
     		getObject("register_mobile_no").clear();
     		getObject("register_mobile_no").sendKeys("8800628098");
     		System.out.println("arora");
     		
     		/*System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#mkt_sbpt"))));
     		System.out.println("kumar");
     		driver.findElement(By.cssSelector("#mkt_sbpt")).click();
     		//getObject("register_send_updates").click();
     		System.out.println("arora");*/
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_sign_up")));
     		System.out.println("kumar");
     		getObject("register_sign_up").click();
     		System.out.println("arora");
     		
     		
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_account_link")));
     		System.out.println("kumar");
     		getObject("user_account_link").click();;
     		System.out.println("arora");
     		
     		
     		
     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("user_sign_out")));
     		System.out.println("kumar");
     		getObject("user_sign_out").click();;
     		System.out.println("arora");
     		
     		
     		Thread.sleep(10000);
     		
     		
    	/*	System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_remember_password")));
     		System.out.println("kumar");
     		getObject("register_remember_password").click();
     		System.out.println("arora");*/
     		
/*     		System.out.println("gaurav");
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("register_signin")));
     		System.out.println("kumar");
     		getObject("register_signin").click();
     		System.out.println("arora");*/
     		
                
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
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(signin_and_register_suite_xls, this.getClass().getSimpleName()) ;
    }
   
}

