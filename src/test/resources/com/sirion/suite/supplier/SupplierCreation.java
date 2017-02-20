package test.resources.com.sirion.suite.supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
import testlink.api.java.client.TestLinkAPIException;

public class SupplierCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(supplier_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(supplier_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void SupplierCreationTest(String testCaseId, String supplierName, String supplierAlias, String supplierTier, String supplierAddress, String supplierShareId,
			String supplierEmail, String supplierTaxId, String supplierFunctions, String supplierServices, String supplierRegions, String supplierCountries,
			String supplierAdditionalFACV, String supplierAdditionalTCV, String supplierAdditionalACV, String supplierParent)
			throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}

		APP_LOGS.debug("Executing Test Case Supplier Creation with Name ---- "+supplierName);

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
		getObject("vh_quick_link").click();
		Thread.sleep(10000);
		
		new Select(getObject("vh_listing_page_display_entries_dropdown")).selectByIndex(4);
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'" +supplierParent+ "')]/preceding-sibling::td[1]/a")).click();
		Thread.sleep(10000);

		new Actions(driver).moveToElement(getObject("vh_show_page_plus_button")).build().perform();
		driver.findElement(By.linkText("Create Supplier")).click();
		
		// Supplier - Create Page - BASIC INFORMATION
		if (!supplierName.equalsIgnoreCase("")) {
			getObject("supplier_create_page_name_textbox").clear();
			getObject("supplier_create_page_name_textbox").sendKeys(supplierName);
			}

		if (!supplierAlias.equalsIgnoreCase("")) {
			getObject("supplier_create_page_alias_textbox").clear();
			getObject("supplier_create_page_alias_textbox").sendKeys(supplierAlias);
			}
		
		if (!supplierTier.equalsIgnoreCase("")) {
			for (String supData : supplierTier.split(";")) {
				new Select(getObject("supplier_create_page_tier_multi_dropdown")).selectByVisibleText(supData.trim());
				}
			}

		if (!supplierAddress.equalsIgnoreCase("")) {
			getObject("supplier_create_page_address_textarea").clear();
			getObject("supplier_create_page_address_textarea").sendKeys(supplierAddress);
			}

		if (!supplierShareId.equalsIgnoreCase("")) {
			getObject("supplier_create_page_share_id_textbox").clear();
			getObject("supplier_create_page_share_id_textbox").sendKeys(supplierShareId);
			}

		if (!supplierEmail.equalsIgnoreCase("")) {
			getObject("supplier_create_page_email_textbox").clear();
			getObject("supplier_create_page_email_textbox").sendKeys(supplierEmail);
			}

		if (!supplierTaxId.equalsIgnoreCase("")) {
			getObject("supplier_create_page_tax_id_textbox").clear();
			getObject("supplier_create_page_tax_id_textbox").sendKeys(supplierTaxId);
			}

		// Supplier - Create Page - FUNCTIONS
		if (!supplierFunctions.equalsIgnoreCase("")) {
			for (String supData : supplierFunctions.split(";")) {
				new Select(getObject("supplier_create_page_functions_multi_dropdown")).selectByVisibleText(supData.trim());
				}
			if (!supplierServices.equalsIgnoreCase("")) {
				for (String supData : supplierServices.split(";")) {
					new Select(getObject("supplier_create_page_services_multi_dropdown")).selectByVisibleText(supData.trim());
					}
				}
			}		
		
		// Supplier - Create Page - GEOGRAPHY
		if (!supplierRegions.equalsIgnoreCase("")) {
			for (String supData : supplierRegions.split(";")) {
				new Select(getObject("supplier_create_page_regions_multi_dropdown")).selectByVisibleText(supData.trim());
				}
			if (!supplierCountries.equalsIgnoreCase("")) {
				for (String supData : supplierCountries.split(";")) {
					new Select(getObject("supplier_create_page_countries_multi_dropdown")).selectByVisibleText(supData.trim());
					}
				}
			}
		
		// Supplier - Create Page - FINANCIAL INFORMATION
		if (!supplierAdditionalTCV.equalsIgnoreCase("")) {
			Double temp_supplierAdditionalTCV_double = Double.parseDouble(supplierAdditionalTCV);
			int temp_supplierAdditionalTCV_int = temp_supplierAdditionalTCV_double.intValue();
			String temp_supplierAdditionalTCV_string = Integer.toString(temp_supplierAdditionalTCV_int);
			
			getObject("supplier_create_page_additional_tcv_numerix_box").clear();
			getObject("supplier_create_page_additional_tcv_numerix_box").sendKeys(temp_supplierAdditionalTCV_string);
			}
		
		if (!supplierAdditionalACV.equalsIgnoreCase("")) {
			Double temp_supplierAdditionalACV_double = Double.parseDouble(supplierAdditionalACV);
			int temp_supplierAdditionalACV_int = temp_supplierAdditionalACV_double.intValue();
			String temp_supplierAdditionalACV_string = Integer.toString(temp_supplierAdditionalACV_int);
			
			getObject("supplier_create_page_additional_acv_numerix_box").clear();
			getObject("supplier_create_page_additional_acv_numerix_box").sendKeys(temp_supplierAdditionalACV_string);
			}
		
		if (!supplierAdditionalFACV.equalsIgnoreCase("")) {
			Double temp_supplierAdditionalFACV_double = Double.parseDouble(supplierAdditionalFACV);
			int temp_supplierAdditionalFACV_int = temp_supplierAdditionalFACV_double.intValue();
			String temp_supplierAdditionalFACV_string = Integer.toString(temp_supplierAdditionalFACV_int);
			
			getObject("supplier_create_page_additional_facv_numerix_box").clear();
			getObject("supplier_create_page_additional_facv_numerix_box").sendKeys(temp_supplierAdditionalFACV_string);
			}

		getObject("supplier_create_page_save_button").click();
		Thread.sleep(5000);

		if (supplierName.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("supplier_create_page_name_textbox").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Supplier Name is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (supplierFunctions.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("supplier_create_page_functions_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Supplier Functions is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (supplierServices.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("supplier_create_page_services_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Supplier Services is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (supplierRegions.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("supplier_create_page_regions_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Supplier Regions is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (supplierCountries.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("supplier_create_page_countries_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Supplier Countries is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (getObject("supplier_notification_popup_id") != null) {
			String supId = getObject("supplier_notification_popup_id").getText();
			APP_LOGS.debug("Supplier created successfully with Supplier ID "+supId);

			getObject("supplier_notification_popup_id").click();
			String supShowPage = getObject("supplier_show_page_id").getText();

			Assert.assertEquals(supShowPage, supId);
			APP_LOGS.debug("Supplier ID on show page has been verified");
			}

		if(driver.findElements(By.id("genericErrors")).size()!=0) {
			String errors_create_page = getObject("supplier_create_page_notification_errors_dialogue_box").getText();

			APP_LOGS.debug("Supplier already exists with Name -- " +supplierName+" -- or with Alias --- "+supplierAlias);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			fail=false;
/*
			if (!fail)
		        result= TestLinkAPIResults.TEST_PASSED;
		      else   
		         result= TestLinkAPIResults.TEST_FAILED;
		     TestlinkIntegration.updateTestLinkResult(testCaseId,"",result);
*/
		     driver.get(CONFIG.getProperty("endUserURL"));
		     return;
		     }
		
		fail = false;
/*
		if (!fail)
	        result= TestLinkAPIResults.TEST_PASSED;
	      else   
	         result= TestLinkAPIResults.TEST_FAILED;
	     TestlinkIntegration.updateTestLinkResult(testCaseId,"",result);
*/
		driver.get(CONFIG.getProperty("endUserURL"));
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
			TestUtil.reportDataSetResult(supplier_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(supplier_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(supplier_suite_xls, this.getClass().getSimpleName());
		}
	}
