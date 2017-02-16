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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import test.resources.com.sirion.util.TestUtil;

public class Forecastsheetupload extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

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
	public void ForecastsheetuploadTest(String FileName)throws InterruptedException, AWTException, IOException {

		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no "+ count);
		}

		APP_LOGS.debug("Executing Test Case Interpretation Creation from Action");

		/*openBrowser();

		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
		//WebDriverWait wait=new WebDriverWait(driver, 50);
		Thread.sleep(10000);
		
		getObject("contract_quick_link").click();
		  
	    driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[3]/td[1]/a")).click(); */
	    Thread.sleep(20000);
	    driver.findElement(By.xpath("//a[contains(.,'FORECAST')]")).click();
	    Thread.sleep(5000);
	    
	  //  StringSelection stringSelection = new StringSelection(System.getProperty("user.dir") +"//src//test//resources//com//sirion//xls//Actuals.xlsx");
	    //  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	   
	     
	      
	      driver.findElement(By.xpath("//input[@value='Upload Forecast']")).click();
	     
	      
	      String str=new String(System.getProperty("user.dir") +"\\src\\test\\resources\\com\\sirion\\xls\\Service Data Creation.xlsx");
		  System.out.println("str is "+ str);
		  StringSelection stringSelection = new StringSelection(str);
		  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
	      
	      Thread.sleep(5000);
	      Robot robot = new Robot();
	      
	      robot.keyPress(KeyEvent.VK_CONTROL);
	      robot.keyPress(KeyEvent.VK_V); 
	    
	      robot.keyPress(KeyEvent.VK_ENTER);
	      robot.keyRelease(KeyEvent.VK_ENTER);
	    
	      driver.navigate().refresh();
	      Thread.sleep(15000);
	       driver.findElement(By.xpath("//a[contains(.,'FORECAST')]")).click();
	       
	       Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
	    	       .withTimeout(30, TimeUnit.MINUTES)
	    	       .pollingEvery(1, TimeUnit.MINUTES)
	    	       .ignoring(NoSuchElementException.class);

	    	   wait1.until(new Function<WebDriver, WebElement>() {
	    	     public WebElement apply(WebDriver driver) {
	    	    	 driver.navigate().refresh();
	    	    	 driver.findElement(By.xpath("//a[contains(.,'FORECAST')]")).click();
	    		       try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    	       return driver.findElement(By.xpath("//td[contains(./text(),'Monthly')]"));
	    	     }
	    	   });
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