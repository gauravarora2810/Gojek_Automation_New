package test.resources.com.sirion.suite.po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class POCreation extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(po_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(po_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void PurchaseOrderTest(String poPONumber, String poName, String poDescription, String poServiceSubCategory, String poTimeZone, String poCurrency,
			String poActive, String poTier, String poStartDate, String poEndDate, String poPOTotal, String poSupplier, String poContract)
			throws InterruptedException {
		
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data set to NO "+ count);
			}

		APP_LOGS.debug("Executing Test Case PO Creation with Name --- "+poName);

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

		if (!poSupplier.equalsIgnoreCase("")) {
			getObject("supplier_quick_link").click();
			Thread.sleep(10000);

			getObject("supplier_id_link").click();
			} else {
				getObject("contract_quick_link").click();
				Thread.sleep(10000);
				getObject("contract_id_link").click();
				}

		wait_in_report.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mainContainer']/div/div[2]/a")));
		plus_button("supplier_plus_button_link");

		wait_in_report.until(ExpectedConditions.visibilityOf(getObject("create_purchase_order")));

		getObject("create_purchase_order").click();

		if (!poPONumber.equalsIgnoreCase("")) {
			getObject("po_number_textbox").clear();
			getObject("po_number_textbox").sendKeys(poPONumber);

			DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
			Date date = new Date();
			String date1 = dateFormat.format(date);

			getObject("po_number_textbox").sendKeys(date1);
			}

		if (!poName.equalsIgnoreCase("")) {
			getObject("po_name_textbox").clear();
			getObject("po_name_textbox").sendKeys(poName);

			DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
			Date date = new Date();
			String date1 = dateFormat.format(date);

			getObject("po_name_textbox").sendKeys(date1);
			}

		if (!poDescription.equalsIgnoreCase("")) {
			getObject("po_description_textarea").sendKeys(poDescription);
			}

		if (!poTimeZone.equalsIgnoreCase("")) {
			new Select(getObject("po_time_zone_dropdown")).selectByVisibleText(poTimeZone);
			try {
	      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
	      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	      	} catch (Exception e) {
	      		
	      	}	
		}
		Thread.sleep(5000);

		if (!poCurrency.equalsIgnoreCase("")) {
			new Select(getObject("po_currency_dropdown")).selectByVisibleText(poCurrency);
			}

		if (!poActive.equalsIgnoreCase("yes")) {
			getObject("po_active_checkbox").click();
		}

		if (!poTier.equalsIgnoreCase("")) {
			new Select(getObject("po_tier_dropdown")).selectByVisibleText(poTier);
			}
		Thread.sleep(5000);

		if (!poPOTotal.equalsIgnoreCase("")) {
			getObject("po_total_textbox").sendKeys(poPOTotal);
			}

		/*
		 * if (!poStartDate.equalsIgnoreCase("")) {
		 * driver.findElement(By.name("requestedOn")).click(); String[] acDate =
		 * poStartDate.split("-");
		 * 
		 * String acMonth =
		 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span"
		 * )).getText(); while (!acMonth.equalsIgnoreCase(acDate[0])) {
		 * driver.findElement
		 * (By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
		 * acMonth =
		 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span"
		 * )).getText(); }
		 * 
		 * new Select(driver.findElement(By.xpath(
		 * ".//*[@id='ui-datepicker-div']/div/div/select"
		 * ))).selectByVisibleText(acDate[2]);
		 * 
		 * driver.findElement(By.linkText(acDate[1])).click(); }
		 * Thread.sleep(5000);
		 * 
		 * 
		 * if (!poEndDate.equalsIgnoreCase("")) {
		 * driver.findElement(By.name("plannedCompletionDate")).click();
		 * String[] acDate = poEndDate.split("-");
		 * 
		 * String acMonth =
		 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span"
		 * )).getText(); while (!acMonth.equalsIgnoreCase(acDate[0])) {
		 * driver.findElement
		 * (By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		 * acMonth =
		 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span"
		 * )).getText(); }
		 * 
		 * new Select(driver.findElement(By.xpath(
		 * ".//*[@id='ui-datepicker-div']/div/div/select"
		 * ))).selectByVisibleText(acDate[2]);
		 * 
		 * driver.findElement(By.linkText(acDate[1])).click(); }
		 * Thread.sleep(5000);
		 */

		getObject("po_create_button").click();
		Thread.sleep(10000);

		String po_id = getObject("po_popup_id").getText();

		APP_LOGS.debug("PO created successfully with id " + po_id);

		getObject("po_popup_ok_button_from_sl").click();

		APP_LOGS.debug("Quick Search the created po with id " + po_id);

		getObject("quick_search_textbox").sendKeys(po_id);

		getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		Thread.sleep(7000);

		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(po_suite_xls, "Test Cases", TestUtil
					.getRowNum(po_suite_xls, this.getClass().getSimpleName()),
					"PASS");
		else
			TestUtil.reportDataSetResult(po_suite_xls, "Test Cases", TestUtil
					.getRowNum(po_suite_xls, this.getClass().getSimpleName()),
					"FAIL");

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(po_suite_xls, this.getClass().getSimpleName());
	}
}
