package test.resources.com.sirion.suite.issue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class IssueCommunication extends TestSuiteBase{
    String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=true;
    static boolean skip=false;
    static boolean isTestPass=true;
    
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(issue_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(issue_suite_xls, this.getClass().getSimpleName());
    }
    
    @Test (dataProvider = "getTestData")
    public void IssueCommunicationTest() throws InterruptedException {
            // test the runmode of current dataset
            count++;
            if(!runmodes[count].equalsIgnoreCase("Y")){
                skip=true;
                throw new SkipException("Runmode for test set data set to no "+count);
            }
            
            APP_LOGS.debug("Executing Test Case Issue Audit Log Creation");
            
            openBrowser();
    		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    		getObject("analytics_link").click();
    		getObject("issue_quick_link").click(); // Issue Quick Link Clicking
    		Thread.sleep(10000);
    		getObject("is_id_link").click(); // Issue ID Clicking
            Thread.sleep(5000);
     
            driver.findElement(By.xpath(".//a[contains(.,'COMMUNICATION')]")).click();
         
            Thread.sleep(10000);
            
            APP_LOGS.debug("Communication Verification of Issue is done");
            
            getObject("analytics_link").click();
            
            fail = false;
                 

            }
                    
    
    
    
    
                    
    				                   
            
    
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(issue_suite_xls, this.getClass().getSimpleName()) ;
    }

}
