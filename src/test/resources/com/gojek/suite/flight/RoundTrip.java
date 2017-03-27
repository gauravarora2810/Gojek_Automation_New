package test.resources.com.gojek.suite.flight;

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
import test.resources.com.gojek.suite.signin_and_register.SignIn;
import test.resources.com.gojek.util.DatePicker;
import test.resources.com.gojek.util.TestUtil;

public class RoundTrip extends TestSuiteBase {
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
        
        if(!TestUtil.isTestCaseRunnable(flight_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(flight_suite_xls, this.getClass().getSimpleName());
    }
    
    
  
    
    
    @Test (dataProvider = "Round trip Sheet" , groups={"Flight Booking RoundTrip"})
    public void roundTripTest(String flight_from, String flight_to, String flight_depart_date, String flight_return_date, String adults_count, String children_count, String infants_count ) throws InterruptedException 
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

     		
     		
     		
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("flight_type_roundtrip")));
     		
     		getObject("flight_type_roundtrip").click();
     		
     		Thread.sleep(2000);
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("flight_from")));
     		getObject("flight_from").clear();
     		getObject("flight_from").sendKeys(flight_from);
               
     		Thread.sleep(2000);
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("flight_to")));
     		getObject("flight_to").clear();
     		getObject("flight_to").sendKeys(flight_to);
     		
     		Thread.sleep(2000);
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("flight_depart_date")));
     		
     		getObject("flight_depart_date").click();
     		
     		
     		
     		 
 		    	
 		        String[] departDate = flight_depart_date.split("-");

 		        String departMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
 		        while (!departMonth.equalsIgnoreCase(departDate[0])) {
 		          driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
 		         departMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
 		          }

 		       // new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(departDate[2]);

 		        driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]//a[contains(.,'"+departDate[1]+"')]")).click();
 		      
     		
     		
     		
 		       Thread.sleep(2000);
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("flight_return_date")));
     		
     		//getObject("flight_return_date").click();
     		
     		
     		
		        String[] returnDate = flight_return_date.split("-");

		        String returnMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/div/span[1]")).getText();
		        while (!returnMonth.equalsIgnoreCase(returnDate[0])) {
		          //driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		          returnMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/div/span[1]")).getText();
		          }

		     //   new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(returnDate[2]);

		        driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]//a[contains(.,'"+returnDate[1]+"')]")).click();
		      
     		
		       
	           
		        
		        Thread.sleep(2000);
		        System.out.println("adult count is "+adults_count);
		        Double adult_count_double=Double.parseDouble(adults_count);
		        
		        System.out.println("adult count double value is "+adult_count_double);
		        
		        int adult_count_integer=adult_count_double.intValue();
		        System.out.println("adult count Integer value is "+adult_count_integer);
		        String adult_count_string=Integer.toString(adult_count_integer);
		        System.out.println("adult count String value is "+adult_count_string);
     		fluent_wait.until(ExpectedConditions.elementToBeClickable(getObject("flight_boarded_adults_count")));
     		
     		Select select_adults_count=new Select(getObject("flight_boarded_adults_count"));
     		select_adults_count.selectByVisibleText(adult_count_string);
     		
     		Thread.sleep(10000);
     		
     		
     		getObject("flight_search").click();
     		
     		Thread.sleep(15000);
     		
            fail = false; // this executes if assertion passes

               
    }

    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(flight_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(flight_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(flight_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(flight_suite_xls, "Test Cases", TestUtil.getRowNum(flight_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(flight_suite_xls, "Test Cases", TestUtil.getRowNum(flight_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider(name="Round trip Sheet")
    public Object[][] getTestData(){
        return TestUtil.getData(flight_suite_xls, this.getClass().getSimpleName()) ;
    }
   
}

