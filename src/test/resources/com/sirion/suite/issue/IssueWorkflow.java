package test.resources.com.sirion.suite.issue;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class IssueWorkflow extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(issue_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(issue_suite_xls, this.getClass().getSimpleName());
  }

  @Test 
	public void IssueWorkflowTest() throws InterruptedException, ClassNotFoundException, SQLException, Exception
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Issue Workflow");
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("issue_quick_link").click(); 
		Thread.sleep(20000);
		WebDriverWait wait=new WebDriverWait(driver, 50);
		//wait.until(ExpectedConditions.elementToBeClickable(getObject("is_id_link")));
	    getObject("is_id_link").click();
		Thread.sleep(10000);
		
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(20000);
        
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));
		driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
        
        
        
	    if(getObject("is_popup_id")!=null){
			
	    	String ip_id = getObject("ip_popup_id").getText();
	    	APP_LOGS.debug("Issue Cloned successfully with Issue id "+ip_id);
		
	    	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
	    	driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
	    	Thread.sleep(10000);
		
	    	APP_LOGS.debug("Quick Search the created Issue with Issue id " + ip_id);
		
	    	getObject("quick_search_textbox").sendKeys(ip_id);
		
	    	getObject("quick_search_textbox").sendKeys(Keys.ENTER);
	    	Thread.sleep(10000);
		
	    	String ipIdFromShowPage = getObject("is_show_id").getText();
	    	System.out.println("Issue Id " + ipIdFromShowPage);
	    	Thread.sleep(10000);
		}
		
	    
	    driver.findElement(By.xpath("//*[@id='elem_128']/textarea")).clear();
		driver.findElement(By.xpath("//*[@id='elem_128']/textarea")).sendKeys("Process Area Impacted");
		driver.findElement(By.xpath("//*[@id='elem_129']/textarea")).clear();
	    driver.findElement(By.xpath("//*[@id='elem_129']/textarea")).sendKeys("Action Taken");
	    driver.findElement(By.xpath("//*[@id='elem_130']/textarea")).clear();
		driver.findElement(By.xpath("//*[@id='elem_130']/textarea")).sendKeys("Resolution Remarks");
		
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		Thread.sleep(10000);
		
		
		
        Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Close')]")));
        driver.findElement(By.xpath("//button[contains(.,'Close')]")).click();
        Thread.sleep(10000);
        
        Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
        driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
        Thread.sleep(10000);
          
         
              
		fail = false; //this executes if assertion passes
		getObject("analytics_link").click();
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(issue_suite_xls, this.getClass().getSimpleName());
  }

}
