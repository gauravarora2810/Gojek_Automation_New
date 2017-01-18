package test.resources.com.sirion.suite.supplier;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class SupplierNonWorkflow extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(supplier_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(supplier_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider = "getTestData")
	public void SupplierWorkflow(String supName) throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Supplier Workflow for supplier --- "+supName);
				
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("supplier_quick_link").click(); 
	//	getObject("supplier_id_link").click(); // click on supplier id 
		//Thread.sleep(20000);
		WebDriverWait wait=new WebDriverWait(driver, 50);
		//wait.until(ExpectedConditions.elementToBeClickable(getObject("supplier_id_link")));

		try
 		{
 			
 			getObject("supplier_id_link").click();
 			
 		}
 		catch(Exception e)
 		{
 			System.out.println(e);
 			getObject("supplier_id_link_2").click();
 			
 		}
		//driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ supName +"')]/preceding-sibling::td[1]/a")).click();
		Thread.sleep(10000);

         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Archive')]")));
         driver.findElement(By.xpath("//button[contains(.,'Archive')]")).click();
         Thread.sleep(10000);
         Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Restore')]")));
         driver.findElement(By.xpath("//button[contains(.,'Restore')]")).click();
         Thread.sleep(5000);
        /* String supplier_status = getObject("sp_status_id").getText();
         
         Assert.assertEquals(supplier_status, "Active"); */
         
         
		fail = false; //this executes if assertion passes
		getObject("analytics_link").click();
		//getObject("supplier_quick_link").click();
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(supplier_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(supplier_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(supplier_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(supplier_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(supplier_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(supplier_suite_xls, this.getClass().getSimpleName());
  }

}
