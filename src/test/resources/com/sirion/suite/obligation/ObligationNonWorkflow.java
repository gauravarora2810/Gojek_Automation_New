package test.resources.com.sirion.suite.obligation;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ObligationNonWorkflow extends TestSuiteBase {
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
	  if (!TestUtil.isTestCaseRunnable(obligation_suite_xls, this.getClass().getSimpleName())) {
		  APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
		  throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
	  }
    
	  // Load the Run Modes off the Tests
    runmodes = TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider="getTestData")
  public void IssueNonWorkflowTest(String obligationName) throws InterruptedException, ClassNotFoundException, SQLException {
	
	  // Test the Run Mode of Current Data Set
	  count++;
			if(!runmodes[count].equalsIgnoreCase("Y")){
				skip=true;
				throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Service Level Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(5000);
		
		getObject("analytics_link").click();
		Thread.sleep(5000);
		
		getObject("child_obligaiton_quick_link").click();
		Thread.sleep(10000);
		WebDriverWait wait=new WebDriverWait(driver, 50);
		Thread.sleep(20000);
  		getObject("obligaiton_link").click();
  		
  		Thread.sleep(20000);
 		//wait.until(ExpectedConditions.elementToBeClickable(getObject("ob_id_link")));

 		getObject("ob_id_link").click(); 
 		
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(20000);
        
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));
		driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
        
        
        
		 if(getObject("ob_popup_id")!=null){
				
		    	String ob_id = getObject("ob_popup_id").getText();
		    	APP_LOGS.debug("Issue Cloned successfully with Issue id "+ob_id);
			
		    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
		    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		    	Thread.sleep(10000);
			
		    	APP_LOGS.debug("Quick Search the created Issue with Issue id " + ob_id);
			
		    	getObject("quick_search_textbox").sendKeys(ob_id);
			
		    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		    	Thread.sleep(10000);
			
		    	String obIdFromShowPage = getObject("ob_show_id").getText();
		    	System.out.println("OB Id " + obIdFromShowPage);
		    	Thread.sleep(10000);
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Archive')]")));
		driver.findElement(By.xpath("//button[contains(.,'Archive')]")).click();
		Thread.sleep(10000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Restore')]")));
		driver.findElement(By.xpath("//button[contains(.,'Restore')]")).click();
		Thread.sleep(10000); 
         
		/*Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'On Hold')]")));
		driver.findElement(By.xpath("//button[contains(.,'On Hold')]")).click();
		Thread.sleep(5000); 
         
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Activate')]")));
		driver.findElement(By.xpath("//button[contains(.,'Activate')]")).click();
		Thread.sleep(5000); */
         
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Comment/Attachment')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Comment/Attachment')]")).click();
		Thread.sleep(10000); 
        
		if(driver.findElement(By.xpath("//*[contains(.,'Please add a comment or upload files')]")).getText().contains("Please add a comment or upload files"))
		{
			 		
	            	getObject("is_Comment_text_area").clear();
	            	getObject("is_Comment_text_area").sendKeys("Hello");	         
	            	driver.findElement(By.xpath("//button[contains(.,'Save Comment/Attachment')]")).click();
		}
		
		fail = false;
		getObject("analytics_link").click();
			
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;
  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()), "FAIL");
  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(obligation_suite_xls, this.getClass().getSimpleName());
  }
}
