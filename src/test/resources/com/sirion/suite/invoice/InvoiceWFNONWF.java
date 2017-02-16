package test.resources.com.sirion.suite.invoice;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class InvoiceWFNONWF extends TestSuiteBase {
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

    if (!TestUtil.isTestCaseRunnable(inv_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(inv_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider="getTestData") 
	public void InvoiceWFNONWFTest(String invTitle, String Archive, String Restore, String OnHold, String Activate, 
			String WFTask1, String WFTask2, String WFTask3, String WFTask4, String WFTask5, String WFTask6,
			String WFTask7,String WFTask8,String invActualPaymentDate,String invPaidAmount,String Delete) throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Invoice Workflow");
				
		/*openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));*/
		//getObject("invoice_quick_link").click(); 
		
		WebDriverWait wait=new WebDriverWait(driver, 50); 
		//driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
	    Thread.sleep(15000);
		      
		if(!Archive.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Archive')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Archive')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'Archive')]")).click();
    	 	Thread.sleep(5000);
		}
     
     if(!Restore.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Restore')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Restore')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'Restore')]")).click();
    	 	Thread.sleep(5000);
		}
     
     if(!OnHold.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'On Hold')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'On Hold')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'On Hold')]")).click();
    	 	Thread.sleep(5000);
		}
     
     if(!Activate.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Activate')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Activate')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'Activate')]")).click();
    	 	Thread.sleep(5000);
		}
     
     if(!WFTask1.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask1+"')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask1+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask1+"')]")).click();
    	 	Thread.sleep(8000);
		}
     
     if(!WFTask2.equalsIgnoreCase(""))
		{
    	 WebElement we = driver.findElement(By.xpath("//button[contains(text(), '"+WFTask2+"')]"));
    	 wait.until(ExpectedConditions.visibilityOf(we));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask2+"')]")).click();
    	 	Thread.sleep(15000);
		}
     
     if(!WFTask3.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask3+"')]")).click();
    	 	Thread.sleep(8000);
		}
     
     if(!WFTask4.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask4+"')]")).click();
    	 	Thread.sleep(8000);
		}
     
     if(!WFTask5.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
    	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")));
    	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask5+"')]")).click();
    	 	Thread.sleep(8000);
		}
     
     if(!WFTask6.equalsIgnoreCase(""))
		{
 	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask6+"')]")));
 	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask6+"')]")));
 	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask6+"')]")).click();
 	 	Thread.sleep(8000);
		}
     
     if(!WFTask7.equalsIgnoreCase(""))
		{
 	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask7+"')]")));
 	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask7+"')]")));
 	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask7+"')]")).click();
 	 	Thread.sleep(8000);
		}
     
     if(!WFTask8.equalsIgnoreCase(""))
		{
 	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'"+WFTask8+"')]")));
 	 	Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'"+WFTask8+"')]")));
 	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask8+"')]")).click();
 	 	Thread.sleep(15000);
 	 	 if (!invActualPaymentDate.equalsIgnoreCase("")) {
				driver.findElement(By.name("actualPaymentDate")).click();
				String[] invDate = invActualPaymentDate.split("-");

				String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
				while (!invMonth.equalsIgnoreCase(invDate[0])) {
					driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
					invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
					}

				new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);

				driver.findElement(By.linkText(invDate[1])).click();
				Thread.sleep(3000);
				}
 	 	 
 	 	 getObject("inv_paid_amount_textbox").sendKeys(invPaidAmount);
 	 	driver.findElement(By.xpath("//button[contains(.,'"+WFTask8+"')]")).click();
 	 	Thread.sleep(8000);
		}
		
     
     /*if(!Delete.equalsIgnoreCase(""))
		{
    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Delete')]")));
    	 Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Delete')]")));
    	 driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();
    	 Thread.sleep(10000);

         if (driver.getPageSource().contains("Are you sure you would like to delete this entity?")) {
         	APP_LOGS.debug("Are you sure you would like to delete this entity?");

     		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Yes')]")));
     		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
     		Thread.sleep(5000);
     		
         	fail=false;
         	driver.get(CONFIG.getProperty("endUserURL"));
         	return;
         	}
		}*/
              
		fail = false; //this executes if assertion passes
		driver.get(CONFIG.getProperty("endUserURL"));
			 }
			 
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(inv_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(inv_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(inv_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(inv_suite_xls, "Test Cases", TestUtil.getRowNum(inv_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(inv_suite_xls, "Test Cases", TestUtil.getRowNum(inv_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(inv_suite_xls, this.getClass().getSimpleName());
  }

}
