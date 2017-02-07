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

public class ObligationWorkflowExisting extends TestSuiteBaseExisting {
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

    if (!TestUtil.isTestCaseRunnable(obligation_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider = "getTestData")
	public void obligationworkflowtest(String obligationName) throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Contract Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("child_obligaiton_quick_link").click(); 
		Thread.sleep(15000);
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
		
		
		

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
		Thread.sleep(5000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
         driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")).click();
         Thread.sleep(5000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
         driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")).click();
         Thread.sleep(5000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
         driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")).click();
         Thread.sleep(5000); 
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
         driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")).click();
         Thread.sleep(5000); 
          Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
         driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
         Thread.sleep(5000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish')]")));
         driver.findElement(By.xpath("//button[contains(.,'Publish')]")).click();
         Thread.sleep(5000);
         
         fail = false; //this executes if assertion passes
 		getObject("analytics_link").click();
         
         /*String ob_status = getObject("ob_status_id").getText();
         
         Assert.assertEquals(ob_status, "Active");     */
         
              
		
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
