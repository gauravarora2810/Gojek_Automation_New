package test.resources.com.sirion.purchaseOrder;

import java.util.Date;

import org.openqa.selenium.By;
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


public class PurchaseOrderCreation extends TestSuiteBase  {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	
	// Runmode of test case in a suite
		@BeforeTest
		public void checkTestSkip() {

			if (!TestUtil.isTestCaseRunnable(purchaseorder_suite_xls, this.getClass()
					.getSimpleName())) {
				APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
				throw new SkipException("Skipping Test Case"+ this.getClass().getSimpleName() + " as runmode set to NO");// reports
			}
			System.out.println("outside condition of method check test skip");
			// load the runmodes off the tests
			runmodes = TestUtil.getDataSetRunmodes(purchaseorder_suite_xls, this
					.getClass().getSimpleName());
			System.out.println("gaurav arora");
	}
		
		@Test(dataProvider = "getTestData")
		public void PurchaseOrderTest(String poPONumber,String poName,
				String poDescription,String poServiceSubCategory,
				String poTimeZone,String poCurrency,String poActive,
				String poTier,String poStartDate,String poEndDate,String poPOTotal,String poSupplier,String poContract) throws InterruptedException {
			System.out.println("In @Test Annotation");
			// test the runmode of current dataset
			count++;
			
			if (!runmodes[count].equalsIgnoreCase("Y"))
			{
				skip = true;
				throw new SkipException("Runmode for test set data set to no " + count);
			}

			APP_LOGS.debug("Executing Test Case Action Creation from Service Level");

			// Calling method for opening browser from TestBase.java file
			openBrowser();

			// Calling a method for login(at different platform) from TestBase.java
			// file
			endUserLogin(CONFIG.getProperty("endUserURL"),
					CONFIG.getProperty("endUserUsername"),
					CONFIG.getProperty("endUserPassword"));
			Thread.sleep(10000);
			wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
			getObject("analytics_link").click();
			System.out.println("Clicked the analytics link");
			Thread.sleep(8000);
			
			if (!poSupplier.equalsIgnoreCase(""))
			{
			getObject("supplier_quick_link").click();
			System.out.println("Clicked the suppliers quick link");
			Thread.sleep(10000);
			
			getObject("supplier_id_link").click();
			System.out.println("Clicked the first record in the supplier listing page");
			}
			else
			{
				getObject("contract_quick_link").click();
				System.out.println("Clicked the contracts quick link");
				Thread.sleep(10000);

				
				
				// clicking the first record in the contract listing page
				getObject("contract_id_link").click();
				System.out.println("clicked the first record in the contract listing page");
			}
			
			
			
		
			
			
			wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mainContainer']/div/div[2]/a")));
			System.out.println("Plus button is available now ");
			plus_button("supplier_plus_button_link"); 
			System.out.println("Clicked on the plus button");
			
			wait_in_report.until(ExpectedConditions.visibilityOf(getObject("create_purchase_order")));
			
			//clicking the create purchase order from the drop down
			getObject("create_purchase_order").click();
			
			if (!poPONumber.equalsIgnoreCase("")) {
				getObject("po_number_textbox").sendKeys(poPONumber); // number
			}
			
			if (!poName.equalsIgnoreCase("")) {
				getObject("po_name_textbox").sendKeys(poName); // Title
			}

			if (!poDescription.equalsIgnoreCase("")) {
				getObject("po_description_textarea").sendKeys(poDescription); // title
			}
			

			if (!poTimeZone.equalsIgnoreCase("")) {
				new Select(getObject("po_time_zone_dropdown")).selectByVisibleText(poTimeZone);
			}
			System.out.println("Timezone Selected");
			Thread.sleep(4000);

			if (!poCurrency.equalsIgnoreCase("")) {
				new Select(getObject("po_currency_dropdown")).selectByVisibleText(poCurrency);
			}
			System.out.println("Currency selected");

			
			if (!poActive.equalsIgnoreCase("yes")) {
				getObject("po_active_checkbox").click();
			}

			if (!poTier.equalsIgnoreCase("")) {
				new Select(getObject("po_tier_dropdown")).selectByVisibleText(poTier);
				System.out.println("Tier selected");
			}
			Thread.sleep(5000);
			
			if (!poPOTotal.equalsIgnoreCase("")){
				getObject("po_total_textbox").sendKeys(poPOTotal);
			}
			
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
			*/
				
			getObject("po_create_button").click();
			Thread.sleep(5000);
			String po_id = getObject("po_popup_id").getText();

			APP_LOGS.debug("PO created successfully with id "+ po_id);

			getObject("po_popup_ok_button_from_sl").click();

			APP_LOGS.debug("Quick Search the created po with id "+ po_id);

			getObject("quick_search_textbox").sendKeys(po_id);
			
			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			
			Thread.sleep(7000);
			
			WebElement element = getObject("po_ponumber_id_show");
			String strng = element.getText();
			System.out.println(strng);
			Assert.assertEquals(poPONumber, strng);
			System.out.println("string matched");
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
			return TestUtil.getData(purchaseorder_suite_xls, this.getClass()
					.getSimpleName());
		}
		}
