package test.resources.com.sirion.purchaseOrder;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



import test.resources.com.sirion.util.TestUtil;

//@Listeners(PurchaseOrderCreation.class)
public class PurchaseOrderUpdate extends TestSuiteBase  {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	WebDriverWait wait;
	
	// Runmode of test case in a suite
		@BeforeTest
		public void checkTestSkip() {

			if (!TestUtil.isTestCaseRunnable(purchaseorder_suite_xls, this.getClass()
					.getSimpleName())) {
				APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case"+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
			}
			System.out.println("In Purchase Order Update file - function- CheckTestSkip()");
			// load the runmodes off the tests
			runmodes = TestUtil.getDataSetRunmodes(purchaseorder_suite_xls, this
					.getClass().getSimpleName());
	}
		
		@Test(dataProvider = "getTestData")
		public void PurchaseOrderTest(String poPONumber,String poName,String poDescription, String poServiceSubCategory,
				String poTimeZone,String poCurrency,String poActive,String poTier,String poStartDate, String poEndDate, String poPOTotal, 
				String poSupplier, String poContract
				) throws InterruptedException {
			
			System.out.println("In Purchase Order Update file - function- @Test-PurchaseOrderTest()");
			// test the runmode of current dataset
			count++;
			
			if (!runmodes[count].equalsIgnoreCase("Y"))
			{
				skip = true;
				throw new SkipException("Runmode for test set data set to no " + count);
			}

			APP_LOGS.debug("Executing Test Case PurchaseOrder Update");

			
			// Calling method for opening browser from TestBase.java file
			openBrowser();

			// Calling a method for login(at different platform) from TestBase.java
			// file
			endUserLogin(CONFIG.getProperty("endUserURL"),
					CONFIG.getProperty("endUserUsername"),
					CONFIG.getProperty("endUserPassword"));

			/*Thread.sleep(4000);
			getObject("analytics_link").click();*/
			
			Thread.sleep(10000);
			
			//Clicking the purchase order quick link
			getObject("po_quick_link").click();
			
			Thread.sleep(10000);
			
			//clicking the first record in the listing page
			getObject("po_id_link").click();
			
			Thread.sleep(10000);
			
			//clicking the clone button
			WebElement element = getObject("po_clone_button");
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			
			
			System.out.println("clicked the clone button");
			Thread.sleep(10000);
			
			if (!poPONumber.equalsIgnoreCase("")) {
				getObject("po_number_textbox").clear();
				getObject("po_number_textbox").sendKeys(poPONumber); // number
			}
			
			if (!poName.equalsIgnoreCase("")) {
				getObject("po_name_textbox").clear();
				getObject("po_name_textbox").sendKeys(poName); // Title
			}
			
			Thread.sleep(10000);
			getObject("po_create_clone").click();
			
			String po_id = getObject("po_popup_id").getText();

			APP_LOGS.debug("PO created successfully with id "+ po_id);

			getObject("po_popup_ok_button_from_sl").click();

			APP_LOGS.debug("Quick Search the created po with id "+ po_id);

			getObject("quick_search_textbox").sendKeys(po_id);
			
			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			
			Thread.sleep(10000);
			
			wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("po_edit_button")));
			getObject("po_edit_button").click();
			Thread.sleep(10000);
			
	
			
			if (!poDescription.equalsIgnoreCase("")) {
				getObject("po_description_textarea").clear();
				getObject("po_description_textarea").sendKeys(poDescription); // title
			}
			

			if (!poTimeZone.equalsIgnoreCase("")) {
			
				new Select(getObject("po_time_zone_dropdown")).selectByVisibleText(poTimeZone);
			}
			
			Thread.sleep(10000);

			if (!poCurrency.equalsIgnoreCase("")) {
				
				new Select(getObject("po_currency_dropdown")).selectByVisibleText(poCurrency);
			}
			

			
			if (!poActive.equalsIgnoreCase("yes")) {
				getObject("po_active_checkbox").click();
			}

			if (!poTier.equalsIgnoreCase("po_tier_dropdown")) {
				new Select(getObject("po_tier_dropdown")).selectByVisibleText(poTier);
			
			}
			Thread.sleep(10000);
			
			if (!poPOTotal.equalsIgnoreCase("")){
				getObject("po_total_textbox").clear();
				getObject("po_total_textbox").sendKeys(poPOTotal);
			}
			
			getObject("po_update_button").click();
			Thread.sleep(10000);
			
		
			
	      /*  if (!poStartDate.equalsIgnoreCase("")) {
	            driver.findElement(By.name("requestedOn")).click();
	            String[] acDate = poStartDate.split("-");

	            String acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
	            while (!acMonth.equalsIgnoreCase(acDate[0])) {
	                  driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	                  acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();                  
	                  }

	            new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(acDate[2]);

	            driver.findElement(By.linkText(acDate[1])).click();
	             }
	        Thread.sleep(5000);
	        
	        
	        if (!poEndDate.equalsIgnoreCase("")) {
	            driver.findElement(By.name("plannedCompletionDate")).click();
	            String[] acDate = poEndDate.split("-");

	            String acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
	            while (!acMonth.equalsIgnoreCase(acDate[0])) {
	                  driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
	                  acMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
	                  }

	            new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(acDate[2]);

	            driver.findElement(By.linkText(acDate[1])).click();
	            }
			Thread.sleep(5000);
			
				
			getObject("po_create_button").click();
			Thread.sleep(5000);
						
		*/	
			fail=false;
		}
		
		
		@AfterTest
		public void reportTestResult() {
			if (isTestPass)
				TestUtil.reportDataSetResult(purchaseorder_suite_xls, "Test Cases",
						TestUtil.getRowNum(purchaseorder_suite_xls, this.getClass()
								.getSimpleName()), "PASS");
			else
				TestUtil.reportDataSetResult(purchaseorder_suite_xls, "Test Cases",
						TestUtil.getRowNum(purchaseorder_suite_xls, this.getClass()
								.getSimpleName()), "FAIL");

		}

		@DataProvider
		public Object[][] getTestData() {
			return TestUtil.getData(purchaseorder_suite_xls, this.getClass().getSimpleName());
		}
		

		}

