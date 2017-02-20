package test.resources.com.sirion.suite.childSL;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ChildSLWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(child_sl_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(child_sl_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider="getTestData") 
	public void ChildSLWorkflowTest() throws InterruptedException, ClassNotFoundException, SQLException {
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Child Service Level Workflow");

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

        getObject("service_levels_quick_link").click();
		Thread.sleep(10000);
        
        driver.findElement(By.linkText("View Child Service Levels")).click();
		Thread.sleep(10000);
        
        driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Start Computation')]")));
		driver.findElement(By.xpath("//button[contains(.,'Start Computation')]")).click();
		Thread.sleep(20000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve Computation')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve Computation')]")).click();
		Thread.sleep(20000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		Thread.sleep(20000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Review Complete')]")).click();
		Thread.sleep(20000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit For Customer Approval')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit For Customer Approval')]")).click();
		Thread.sleep(20000);

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
		Thread.sleep(20000);
		
    	String cslStatus = getObject("csl_show_page_status").getText();	        
        Assert.assertEquals(cslStatus, "Active");
        
        fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}
          
  @AfterMethod
  public void reportDataSetResult() {
	  if (skip)
		  TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
	  else if (fail) {
		  isTestPass = false;
		  TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		  }
	  else
		  TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
	  
	  skip = false;
	  fail = false;
	  }

  @AfterTest
  public void reportTestResult() {
	  if(isTestPass)
		  TestUtil.reportDataSetResult(child_sl_suite_xls, "Test Cases", TestUtil.getRowNum(child_sl_suite_xls, this.getClass().getSimpleName()), "PASS");
	  else
		  TestUtil.reportDataSetResult(child_sl_suite_xls, "Test Cases", TestUtil.getRowNum(child_sl_suite_xls, this.getClass().getSimpleName()), "FAIL");
	  }

  @DataProvider
  public Object[][] getTestData() {
	  return TestUtil.getData(child_sl_suite_xls, this.getClass().getSimpleName());
	  }
  }
