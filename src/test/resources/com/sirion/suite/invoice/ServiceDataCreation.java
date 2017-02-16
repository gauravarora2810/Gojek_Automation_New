package test.resources.com.sirion.suite.invoice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import test.resources.com.sirion.util.TestUtil;

public class ServiceDataCreation extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	static Actions action_action;
	static WebElement panel_button;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(inv_suite_xls, this.getClass()
				.getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case"
					+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(inv_suite_xls, this.getClass()
				.getSimpleName());
	}
	
	
	
	@Test(groups = "ServiceDataCreation", dataProvider = "getTestData")
	public void ServiceDataCreationTest(String contractSupName,String contractName, String contractTitle, 
			String contractAgreement,String contractCurrencyConversionMatrix,
			String contractCurrencyConversionMatrixFromDate,String contractCurrencyConversionMatrixToDate,
			String FileName)throws InterruptedException, AWTException, IOException {

		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "+ count);
		}

		APP_LOGS.debug("Executing Test Case Interpretation Creation from Action");

		openBrowser();

		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
		//WebDriverWait wait=new WebDriverWait(driver, 50);
		Thread.sleep(15000);
		getObject("contract_quick_link").click();
		  Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[1]/td[1]/a")).click(); 
	    Thread.sleep(25000);
	   //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='kk']/div/div/div/div[2]/div[1]/div/form/div[2]/ng-form/div/button[5]")));
	   driver.findElement(By.xpath("//*[@id='kk']/div/div/div/div[2]/div[1]/div/form/div[2]/ng-form/div/button[5]")).click();
	   Thread.sleep(10000);
	   
	    if (!contractName.equalsIgnoreCase("")) {
	    	getObject("co_name_textbox").clear();
	    	getObject("co_name_textbox").sendKeys(contractName+randomAlphaNumeric(5)); // name
	    }

	      if (!contractTitle.equalsIgnoreCase("")) {
	    	  getObject("co_title_textbox").clear();
	    	  getObject("co_title_textbox").sendKeys(contractTitle+randomAlphaNumeric(5)); // title
	    }

	    if (!contractAgreement.equalsIgnoreCase("")) {
	    	fail= true;
	    	getObject("co_agreement_textbox").clear();
	      getObject("co_agreement_textbox").sendKeys(convertDoubleToIntegerInStringForm(contractAgreement)); // title
			}

	    	getObject("co_contract_number_textbox").clear();
	        getObject("co_contract_number_textbox").sendKeys(randomAlphaNumeric(5));
	    
	    if (!contractCurrencyConversionMatrix.equalsIgnoreCase("")) {
	        new Select(getObject("co_currencyconversion_dropdown")).selectByVisibleText(contractCurrencyConversionMatrix);
	      }
	    
	    if (!contractCurrencyConversionMatrixFromDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_30")).click();
			String[] invDate = contractCurrencyConversionMatrixFromDate.split("-");
			String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!invMonth.equalsIgnoreCase(invDate[0])) {
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);
			driver.findElement(By.linkText(invDate[1])).click();
			Thread.sleep(3000);
			}
	    
	    if (!contractCurrencyConversionMatrixToDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_31")).click();
			String[] invDate = contractCurrencyConversionMatrixToDate.split("-");
			String invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!invMonth.equalsIgnoreCase(invDate[0])) {
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				invMonth = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(invDate[2]);
			driver.findElement(By.linkText(invDate[1])).click();
			Thread.sleep(3000);
			}
	    
	    driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
	    Thread.sleep(15000);
	    getObject("co_popup_id").click();
	    Thread.sleep(15000);
	       driver.findElement(By.xpath("//a[contains(.,'SERVICE DATA')]")).click();
	       Thread.sleep(5000);
	      // (System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//WOR Suite.xlsx")
	       System.out.println(System.getProperty("user.dir") +FileName);
	     // StringSelection stringSelection = new StringSelection(System.getProperty("user.dir") +"//src//test//resources//com//sirion//xls//Service Data Creation.xlsx");
	     // Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	      
	      action_action=new Actions(driver);
	      panel_button=driver.findElement(By.xpath("html/body/div[4]/div[1]/ng-view/div/div/div[3]/div[2]/div/div[3]/div/div/div/div/div/div[2]/div[5]/div/form/div/div/p/span[3]/button"));
	      action_action.moveToElement(panel_button).clickAndHold(panel_button).build().perform();
	      getObject("service_data_bulk_upload_link").click();
	      
	     String str=new String(System.getProperty("user.dir") +"\\src\\test\\resources\\com\\sirion\\xls\\Service Data Creation.xlsx");
	     System.out.println("str is "+ str);
	     StringSelection stringSelection = new StringSelection(str);
	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
	     // driver.findElement(By.xpath("//button[contains(.,'Browse')]")).sendKeys(System.getProperty("user.dir")+"//src//test//resources//com//sirion//xls//Service Data Creation.xlsx");
	      //	System.getProperty("user.dir") +"//src//test//resources//com//sirion//xls//Service Data Creation.xlsx"
	      Thread.sleep(5000);
	      Robot robot = new Robot();
	      
	      robot.keyPress(KeyEvent.VK_CONTROL);
	      robot.keyPress(KeyEvent.VK_V); 
	    
	      robot.keyPress(KeyEvent.VK_ENTER);
	      robot.keyRelease(KeyEvent.VK_ENTER);
	    
	      driver.navigate().refresh();
	      Thread.sleep(15000);
	       driver.findElement(By.xpath("//a[contains(.,'SERVICE DATA')]")).click();
	   /* 
	    Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
	    	       .withTimeout(30, TimeUnit.MINUTES)
	    	       .pollingEvery(1, TimeUnit.MINUTES)
	    	       .ignoring(NoSuchElementException.class);

	    	   wait1.until(new Function<WebDriver, WebElement>() {
	    	     public WebElement apply(WebDriver driver) {
	    	    	 driver.navigate().refresh();
	    	    	 driver.findElement(By.xpath("//a[contains(.,'SERVICE DATA')]")).click();
	    		       try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    	       return driver.findElement(By.xpath("//*[@id='table_47']/tbody/tr[1]/td[2]/a"));
	    	     }
	    	   });*/
          
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
			TestUtil.reportDataSetResult(inv_suite_xls, "Test Cases", TestUtil.getRowNum(inv_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(inv_suite_xls, "Test Cases", TestUtil.getRowNum(inv_suite_xls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(inv_suite_xls, this.getClass().getSimpleName());
	}

}