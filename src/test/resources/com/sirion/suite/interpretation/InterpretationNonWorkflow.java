package test.resources.com.sirion.suite.interpretation;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class InterpretationNonWorkflow extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfContracts;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(int_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(int_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void interpretationNonWorkflowTest() throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Interpretaion Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("int_quick_link").click(); 
		Thread.sleep(10000);
		getObject("int_id_link").click();
		Thread.sleep(10000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(20000);
        
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));
		driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
        
        
        
	    if(getObject("ip_popup_id")!=null){
			
	    	String ip_id = getObject("ip_popup_id").getText();
	    	APP_LOGS.debug("IP Cloned successfully with IP id "+ip_id);
		
	    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	Thread.sleep(10000);
		
	    	APP_LOGS.debug("Quick Search the created IP with IP id " + ip_id);
		
	    	getObject("quick_search_textbox").sendKeys(ip_id);
		
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	Thread.sleep(10000);
		
	    	String ipIdFromShowPage = getObject("ip_show_id").getText();
	    	System.out.println("IP Id " + ipIdFromShowPage);
	    	Thread.sleep(10000);
		}
		
	    Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Archive')]")));
		driver.findElement(By.xpath("//button[contains(.,'Archive')]")).click();
		Thread.sleep(5000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Restore')]")));
		driver.findElement(By.xpath("//button[contains(.,'Restore')]")).click();
		Thread.sleep(5000); 
         
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'On Hold')]")));
		driver.findElement(By.xpath("//button[contains(.,'On Hold')]")).click();
		Thread.sleep(5000); 
         
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Activate')]")));
		driver.findElement(By.xpath("//button[contains(.,'Activate')]")).click();
		Thread.sleep(5000); 
         
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Comment/Attachment')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Comment/Attachment')]")).click();
		Thread.sleep(5000); 
        
		if(driver.findElement(By.xpath("//*[contains(.,'Please add a comment or upload files')]")).getText().contains("Please add a comment or upload files"))
		{
			 		
	            	getObject("ip_Comment_text_area").clear();
	            	getObject("ip_Comment_text_area").sendKeys("Hello");         
	            	driver.findElement(By.xpath("//button[contains(.,'Save Comment/Attachment')]")).click();
		}
         
              
		fail = false; //this executes if assertion passes
		getObject("analytics_link").click();
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(int_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(int_suite_xls, "Test Cases", TestUtil.getRowNum(int_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(int_suite_xls, "Test Cases", TestUtil.getRowNum(int_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(int_suite_xls, this.getClass().getSimpleName());
  }

}
