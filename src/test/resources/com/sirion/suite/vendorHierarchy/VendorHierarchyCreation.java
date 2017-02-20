package test.resources.com.sirion.suite.vendorHierarchy;

import org.openqa.selenium.By;
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

public class VendorHierarchyCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(vendor_hierarchy_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(vendor_hierarchy_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void VendorHierarchyCreationTest(String testCaseId, String vhName, String vhAlias, String vhAddress, String vhFunctions, String vhServices, String vhServiceCategory,
			String vhRegions, String vhCountries, String vhAdditionalTCV, String vhAdditionalACV, String vhAdditionalFACV)
			throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}

		APP_LOGS.debug("Executing Test Case Vendor Hierarchy Creation with Name ---- "+vhName);

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
		getObject("vh_quick_link").click();
		Thread.sleep(20000);

		getObject("vh_listing_page_plus_button").click();
		
		// Vendor Hierarchy - Create Page - BASIC INFORMATION
		if (!vhName.equalsIgnoreCase("")) {
			getObject("vh_create_page_name_textbox").clear();
			getObject("vh_create_page_name_textbox").sendKeys(vhName);
			}

		if (!vhAlias.equalsIgnoreCase("")) {
			getObject("vh_create_page_alias_textbox").clear();
			getObject("vh_create_page_alias_textbox").sendKeys(vhAlias);
			}

		if (!vhAddress.equalsIgnoreCase("")) {
			getObject("vh_create_page_address_textarea").clear();
			getObject("vh_create_page_address_textarea").sendKeys(vhAddress);
			}

		// Vendor Hierarchy - Create Page - FUNCTIONS
		if (!vhFunctions.equalsIgnoreCase("")) {
			for (String vhData : vhFunctions.split(";")) {
				new Select(getObject("vh_create_page_functions_multi_dropdown")).selectByVisibleText(vhData.trim());
				}
			if (!vhServices.equalsIgnoreCase("")) {
				for (String vhData : vhServices.split(";")) {
					new Select(getObject("vh_create_page_services_multi_dropdown")).selectByVisibleText(vhData.trim());
					}
				}
			}
		
		if (!vhServiceCategory.equalsIgnoreCase("")) {
			for (String vhData : vhServiceCategory.split(";")) {
				new Select(getObject("vh_create_page_service_category_multi_dropdown")).selectByVisibleText(vhData.trim());
				}
			}
		
		// Vendor Hierarchy - Create Page - GEOGRAPHY
		if (!vhRegions.equalsIgnoreCase("")) {
			for (String vhData : vhRegions.split(";")) {
				new Select(getObject("vh_create_page_regions_multi_dropdown")).selectByVisibleText(vhData.trim());
				}
			if (!vhCountries.equalsIgnoreCase("")) {
				for (String vhData : vhCountries.split(";")) {
					new Select(getObject("vh_create_page_countries_multi_dropdown")).selectByVisibleText(vhData.trim());
					}
				}
			}
		
		// Vendor Hierarchy - Create Page - FINANCIAL INFORMATION
		if (!vhAdditionalTCV.equalsIgnoreCase("")) {
			Double temp_vhAdditionalTCV_double = Double.parseDouble(vhAdditionalTCV);
			int temp_vhAdditionalTCV_int = temp_vhAdditionalTCV_double.intValue();
			String temp_vhAdditionalTCV_string = Integer.toString(temp_vhAdditionalTCV_int);
			
			getObject("vh_create_page_additional_tcv_numerix_box").clear();
			getObject("vh_create_page_additional_tcv_numerix_box").sendKeys(temp_vhAdditionalTCV_string);
			}
		
		if (!vhAdditionalACV.equalsIgnoreCase("")) {
			Double temp_vhAdditionalACV_double = Double.parseDouble(vhAdditionalACV);
			int temp_vhAdditionalACV_int = temp_vhAdditionalACV_double.intValue();
			String temp_vhAdditionalACV_string = Integer.toString(temp_vhAdditionalACV_int);
			
			getObject("vh_create_page_additional_acv_numerix_box").clear();
			getObject("vh_create_page_additional_acv_numerix_box").sendKeys(temp_vhAdditionalACV_string);
			}
		
		if (!vhAdditionalFACV.equalsIgnoreCase("")) {
			Double temp_vhAdditionalFACV_double = Double.parseDouble(vhAdditionalFACV);
			int temp_vhAdditionalFACV_int = temp_vhAdditionalFACV_double.intValue();
			String temp_vhAdditionalFACV_string = Integer.toString(temp_vhAdditionalFACV_int);
			
			getObject("vh_create_page_additional_facv_numerix_box").clear();
			getObject("vh_create_page_additional_facv_numerix_box").sendKeys(temp_vhAdditionalFACV_string);
			}

		getObject("vh_create_page_save_button").click();
		Thread.sleep(10000);

		if (vhName.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("vh_create_page_name_textbox").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Vendor Hierarchy Name is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (vhFunctions.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("vh_create_page_functions_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Vendor Hierarchy Functions is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (vhServices.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("vh_create_page_services_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Vendor Hierarchy Services is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (vhRegions.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("vh_create_page_regions_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Vendor Hierarchy Regions is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (vhCountries.equalsIgnoreCase("")) {
			fail = true;

			String vhAttribute = getObject("vh_create_page_countries_multi_dropdown").getAttribute("class");
			String[] vhAttributeSplit = vhAttribute.split(" ");
			String vhAttributeSplitString = vhAttributeSplit[3];

			Assert.assertEquals(vhAttributeSplitString, "errorClass");

			APP_LOGS.debug("Vendor Hierarchy Countries is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (getObject("vh_notification_popup_id") != null) {
			String vhId = getObject("vh_notification_popup_id").getText();
			APP_LOGS.debug("Vendor Hierarchy created successfully with Vendor Hierarchy ID "+vhId);

			getObject("vh_notification_popup_id").click();
			Thread.sleep(10000);
			String vhShowPage = getObject("vh_show_page_id").getText();

			Assert.assertEquals(vhShowPage, vhId);
			APP_LOGS.debug("Vendor Hierarchy ID on show page has been verified");
			}

		if(driver.findElements(By.id("genericErrors")).size()!=0) {
			String errors_create_page = getObject("vh_create_page_notification_errors_dialogue_box").getText();

			APP_LOGS.debug("Vendor Hierarchy already exists with Name -- " +vhName+" -- or with Alias --- "+vhAlias);
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
			TestUtil.reportDataSetResult(vendor_hierarchy_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(vendor_hierarchy_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(vendor_hierarchy_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(vendor_hierarchy_suite_xls, "Test Cases", TestUtil.getRowNum(vendor_hierarchy_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(vendor_hierarchy_suite_xls, "Test Cases", TestUtil.getRowNum(vendor_hierarchy_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(vendor_hierarchy_suite_xls, this.getClass().getSimpleName());
		}
	}
