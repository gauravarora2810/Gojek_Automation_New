package test.resources.com.sirion.suite.childObligation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ChildDNOUpdate extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(child_obligation_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(child_obligation_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider="getTestData") 
	public void ChildSLWorkflowTest(String COBTitle,String cobComment,String cobActualDate,String cobRequestedBy, String cobChangeRequest) throws InterruptedException, ClassNotFoundException, SQLException, AWTException {
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Child Service Level Workflow");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		
		
        wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("obligation_quick_link")));
        Thread.sleep(10000);
        getObject("obligation_quick_link").click();
        APP_LOGS.debug("Click on Obligation Quick Link");
        
        Thread.sleep(15000);
        
        driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(15000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Edit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
		Thread.sleep(15000);
		
		
		
		
	
		
		
		
		
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Update')]")));
		driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();
		Thread.sleep(10000);
        
        fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}
          
  @AfterMethod
  public void reportDataSetResult() {
	  if (skip)
		  TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
	  else if (fail) {
		  isTestPass = false;
		  TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		  }
	  else
		  TestUtil.reportDataSetResult(child_obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
	  
	  skip = false;
	  fail = false;
	  }

  @AfterTest
  public void reportTestResult() {
	  if(isTestPass)
		  TestUtil.reportDataSetResult(child_obligation_suite_xls, "Test Cases", TestUtil.getRowNum(child_obligation_suite_xls, this.getClass().getSimpleName()), "PASS");
	  else
		  TestUtil.reportDataSetResult(child_obligation_suite_xls, "Test Cases", TestUtil.getRowNum(child_obligation_suite_xls, this.getClass().getSimpleName()), "FAIL");
	  }

  @DataProvider
  public Object[][] getTestData() {
	  return TestUtil.getData(child_obligation_suite_xls, this.getClass().getSimpleName());
	  }
  }
