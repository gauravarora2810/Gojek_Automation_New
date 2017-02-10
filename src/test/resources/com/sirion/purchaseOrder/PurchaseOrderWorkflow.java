package test.resources.com.sirion.purchaseOrder;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class PurchaseOrderWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	String numberOfEntries = null;
	String[] numberOfEntriesSplit = null;
	String numberOfContracts;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(purchaseorder_suite_xls, this.getClass().getSimpleName())) {
			
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");// reports

		}
		// load the runmodes off the tests
		
		runmodes = TestUtil.getDataSetRunmodes(purchaseorder_suite_xls, this.getClass().getSimpleName());
		System.out.println("at last chektestskip");
	}

	@Test(dataProvider ="getTestData")
		public void PurchaseOrderWorkflowTest(String poPONumber,String poName,
				String poRequisitionNumber,String poTrackingNumber) throws Exception {
			System.out.println("In @Test Annotation");
			// test the runmode of current dataset
			count++;
			
			if (!runmodes[count].equalsIgnoreCase("Y"))
			{
				skip = true;
				throw new SkipException("Runmode for test set data set to no " + count);
			}

		
		System.out.println("In middle contract Listing");
		APP_LOGS.debug("Executing Test Case PurchaseOrder Workflow");

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"),
				CONFIG.getProperty("endUserUsername"),
				CONFIG.getProperty("endUserPassword"));
		
		
		Thread.sleep(10000);
		// Click analytics
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		getObject("analytics_link").click();


		
		Thread.sleep(10000);	
		// Clicking the Purchase Order quick link
		getObject("po_quick_link").click();
		System.out.println("Clicked the purchase order quick link");
		Thread.sleep(10000);

		
		// Clicking the first record of the Purchase Order listing
		getObject("po_id_link").click();
		System.out.println("clicked the first record");
		Thread.sleep(10000);

		// Clicking the clone button
		WebElement element = getObject("po_clone_button");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		
		System.out.println("clicked the clone button");
		Thread.sleep(10000);
		
		if (!poPONumber.equalsIgnoreCase("")) 
		{
			getObject("po_number_textbox").clear();
			getObject("po_number_textbox").sendKeys(poPONumber); // name
			Thread.sleep(10000);
		}
		if (!poName.equalsIgnoreCase("")) 
		{
			getObject("po_name_textbox").clear();
			getObject("po_name_textbox").sendKeys(poName); // name
			Thread.sleep(10000);
		}
		
		//Assert.assertNotNull(getObject("po_create"));
		//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		getObject("po_create").click();
		//driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		System.out.println("clicked the submit button");
		Thread.sleep(10000);

		

		
		
		
	 	if (getObject("po_popup_id") != null) {

			String po_id = getObject("po_popup_id").getText();
			APP_LOGS.debug("PurchaseOrder Cloned successfully with id "+ po_id);

			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(10000);

			APP_LOGS.debug("Quick Search the created po with id "+ po_id);

			getObject("quick_search_textbox").sendKeys(po_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			Thread.sleep(10000);

			String acnIdFromShowPage = getObject("po_show_id").getText();
			System.out.println("PO Id " + acnIdFromShowPage);

		}

		Thread.sleep(10000);
		
		// workflow steps - submit button

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		System.out.println("clicked the submit button");
		Thread.sleep(10000);

		
		
		
		
		if (!poRequisitionNumber.equalsIgnoreCase("")) 
		{
			getObject("po_requisition_number").click();
			getObject("po_requisition_number").sendKeys(poRequisitionNumber); // name
			Thread.sleep(10000);
		}
		if (!poTrackingNumber.equalsIgnoreCase("")) 
		{
			getObject("po_tracking_number").click();
			getObject("po_tracking_number").sendKeys(poTrackingNumber); // name
			Thread.sleep(10000);
		}
		getObject("po_submit").click(); 
fail=false;
	}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(purchaseorder_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(purchaseorder_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(purchaseorder_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

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
