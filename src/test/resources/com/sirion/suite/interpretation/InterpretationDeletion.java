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

public class InterpretationDeletion extends TestSuiteBaseExisting {
	  String runmodes[] = null;
	  static int count = -1;
	  static boolean fail = true;
	  static boolean skip = false;
	  static boolean isTestPass = true;

	  String numberOfEntries = null;
	  String[] numberOfEntriesSplit = null;
	  String numberOfContracts;
  
  // Run Mode of Test Case in a Suite
  @BeforeTest
  public void checkTestSkip() {
	  if (!TestUtil.isTestCaseRunnable(int_suite_xls, this.getClass().getSimpleName())) {
		  APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
		  throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
	  }
    
	  // Load the Run Modes off the Tests
    runmodes = TestUtil.getDataSetRunmodes(int_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
  public void InterpretationDeletionTest() throws InterruptedException, ClassNotFoundException, SQLException {
	
	  // Test the Run Mode of Current Data Set
	  count++;
			if(!runmodes[count].equalsIgnoreCase("Y")){
				skip=true;
				throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Interpretation Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(5000);
		
		getObject("analytics_link").click();
		Thread.sleep(5000);
		
		getObject("int_quick_link").click();
		Thread.sleep(10000);
		
		getObject("ip_id_link").click(); // Interpretation ID Clicking
        Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(20000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));
		driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
		
	    if(getObject("ip_popup_id")!=null){
			
	    	String ip_id = getObject("ip_popup_id").getText();
	    	APP_LOGS.debug("IP Cloned successfully with Interpretation id "+ip_id);
		
	    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	Thread.sleep(5000);
		
	    	APP_LOGS.debug("Quick Search the created Interpretation with Interpretation id " + ip_id);
		
	    	getObject("quick_search_textbox").sendKeys(ip_id);
		
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	Thread.sleep(5000);
		
	    	String iplevelIdFromShowPage = getObject("ip_show_id").getText();
	    	System.out.println("IP Id " + iplevelIdFromShowPage);
		
		}
	    
	    String ipcurrenturl = driver.getCurrentUrl();
	    System.out.println("Currrent URL "+ipcurrenturl);
	    
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Delete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();
		Thread.sleep(5000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Yes')]")));
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		Thread.sleep(10000);
				
		driver.get(ipcurrenturl);
		
		if(driver.findElement(By.xpath("//*[contains(.,'either deleted by')]")).getText().contains("user don't have permission to access this entity")){
    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
    	Thread.sleep(5000);			
		}
		                  
		fail = false;
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
