package test.resources.com.sirion.suite.sl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class ServiceLevelCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(sl_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(sl_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void ServiceLevelCreationTest(String testCase, String Result, String slTitle, String slDescription, String slSLID, String slSLCategory,
			String slSLSubCategory, String slSLItem, String slTimezone, String slDeliveryCountries, String slCurrency, String slSupplierAccess, String slTier,
			String slComputationCalculationData, String slDataCalculationData, String slDataCriteria, String slPriority, String slReferences, String slClause,
			String slPageNumber, String slApplicationGroup, String slApplication, String slMinimumMaximumSelection, String slUnitofMeasurement,
			String slMinimumMaximumValue, String slExpected, String slSignificantlyMinMax, String slMeasurementWindow, String slYTDStartDate,
			String slCreditApplicable, String slCreditApplicableDate, String slCreditClause, String slCreditOfInvoice, String slSLCreditCategory,
			String slSLCreditSubCategory, String slSLCreditLineItem, String slCreditFrequency, String slEarnbackApplicableDate, String slEarnbackClause,
			String slEarnbackCategory, String slEarnbackSubCategory, String slEarnbackLineItem, String slEarnbackFrequency, String slSubjectTo,
			String slContinuousImprovementClause, String slFrequencyType, String slComputationFrequency, String slFrequency, String slStartDate, String slEndDate,
			String slPatternDate, String slEffectiveDate, String slFunctions, String slServices, String slServiceCategory, String slManagementRegions,
			String slManagementCountries, String slContractRegions, String slContractCountries, String slStakeholder, String slResponsibility,
			String slFinancialImpactApplicable, String slFinancialImpactValue, String slFinancialImpactClause, String slImpactDays, String slImpactType,
			String slCreditImpactApplicable, String slCreditImpactValue, String slCreditImpactClause, String slComments, String slActualDate, String slRequestedBy,
			String slChangeRequest, String slUploadFile, String slSupplier, String slSource, String slSourceName, String slGlobalCreation)
			throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}

		APP_LOGS.debug("Executing Test Case Service Level Creation with Title ---- "+ slTitle + " under Contract ---- " + slSourceName);

		// Launch The Browser
		openBrowser();

		// Login as Client user
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("client_user_analytics_tab_link")));

		APP_LOGS.debug("Go to " + CONFIG.getProperty("endUserURL"));
		APP_LOGS.debug("Login as Client User ("+ CONFIG.getProperty("endUserUsername") + "/"+ CONFIG.getProperty("endUserPassword") + ")");

		// Click on Analytics
		getObject("client_user_analytics_tab_link").click();

		// Condition Check for Global Creation
		if (slGlobalCreation.equalsIgnoreCase("Yes")) {
			wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("service_levels_quick_link")));
			
			// Click on Service Levels Quick Link
			getObject("service_levels_quick_link").click();
			APP_LOGS.debug("Click on Service Level Quick Link");
			Thread.sleep(5000);

			// Click on Plus button for Global Creation Form
			getObject("sl_listing_page_plus_button").click();
			APP_LOGS.debug("Click on Plus (+) button on Service Levels Global Listing");

			// Select Supplier on Global Creation Form
			if (!slSupplier.equalsIgnoreCase("")) {
				new Select(getObject("sl_create_sl_supplier_dropdown")).selectByVisibleText(slSupplier);
				}

			// Select Source on Global Creation Form
			if (!slSource.equalsIgnoreCase("")) {
				new Select(getObject("sl_create_sl_source_dropdown")).selectByVisibleText(slSource);
				}

			// Select Source Name/Title on Global Creation Form
			if (!slSourceName.equalsIgnoreCase("")) {
				new Select(getObject("sl_create_sl_source_name_title_dropdown")).selectByVisibleText(slSourceName);
				}

			// Click on Submit of Global Creation Form
			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
			driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
			Thread.sleep(5000);

			// Conditional Check for Supplier
			if (slSupplier.equalsIgnoreCase("")) {
				fail = true;
				String slAttribute = getObject("sl_priority_dropdown").getAttribute("class");
				String[] slAttributeSplit = slAttribute.split(" ");
				String slAttributeSplitString = slAttributeSplit[3];

				Assert.assertEquals(slAttributeSplitString, "errorClass");

				APP_LOGS.debug("SL Supplier is blank on Global Creation Form");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));

				return;
				}

			// Conditional Check for Source or Source Name
			if (getObject("sl_create_sl_workflow_error") != null) {
				String slError = getObject("sl_create_sl_workflow_error").getText();
				System.out.println("slError: " + slError);

				Assert.assertEquals(slError, "Workflow configured incorrectly for Service Levels.");

				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
				driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
				Thread.sleep(5000);

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));

				return;
				}
			}
		
		// Conditional Check for Contract Show Page Creation Form
		if (!slGlobalCreation.equalsIgnoreCase("Yes")) {
			wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("contracts_quick_link")));

			// Click on Contracts Quick Link
			getObject("contracts_quick_link").click();
			APP_LOGS.debug("Click on Contracts Quick Link");
			wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("contracts_listing_page_display_dropdown")));

			new Select(getObject("contracts_listing_page_display_dropdown")).selectByIndex(4);
			Thread.sleep(5000);

			// Conditional Check for SL Parent Contract
			if (!slSourceName.equalsIgnoreCase("")) {
				driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'" +slSourceName+ "')]/preceding-sibling::td[1]/a")).click();
			} else {
				driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr/td[2]/a")).click();
				}
			Thread.sleep(5000);

			APP_LOGS.debug("Click on Contracts ID under Listing Page");

			// Click on Plus button to open the Create Entities List
			plus_button("contracts_plus_button");
			APP_LOGS.debug("Click on Plus (+) button on Contracts Show Page");

			// Click on Create Service Level to open Create Service Level Page
			driver.findElement(By.linkText("Create Service Level")).click();
			APP_LOGS.debug("Click on Create Service Level");
			}
		
		// Service Levels - Create Page - BASIC INFORMATION
		// Enter SL Title
		if (!slTitle.equalsIgnoreCase("")) {
			getObject("sl_title_textbox").sendKeys(slTitle);
			}

		// Enter SL Description
		if (!slDescription.equalsIgnoreCase("")) {
			getObject("sl_description_textarea").sendKeys(slDescription);
			}

		// Enter SL ID
		if (!slSLID.equalsIgnoreCase("")) {
			getObject("sl_sl_id_textbox").sendKeys(slSLID);
			}

		// Select SL Category, SL Sub-Category and SL Item
		if (!slSLCategory.equalsIgnoreCase("")) {
			new Select(getObject("sl_sl_category_dropdown")).selectByVisibleText(slSLCategory);
			if (!slSLSubCategory.equalsIgnoreCase("")) {
				new Select(getObject("sl_sl_sub_category_dropdown")).selectByVisibleText(slSLSubCategory);
				}
			if (!slSLItem.equalsIgnoreCase("")) {
				new Select(getObject("sl_sl_item_dropdown")).selectByVisibleText(slSLItem);
				}
			}
		
		// Select SL Timezone
		if (!slTimezone.equalsIgnoreCase("")) {
			new Select(getObject("sl_timezone_dropdown")).selectByVisibleText(slTimezone);
			}

		// Select SL Delivery Countries
		if (!slDeliveryCountries.equalsIgnoreCase("")) {
			for (String slData : slDeliveryCountries.split(";")) {
				new Select(getObject("sl_delivery_countries_multi_dropdown")).selectByVisibleText(slData.trim());
				}
			}

		// Select SL Currency
		if (!slCurrency.equalsIgnoreCase("")) {
			new Select(getObject("sl_currency_dropdown")).selectByVisibleText(slCurrency);
			}

		// Check Supplier Access
		if (slSupplierAccess.equalsIgnoreCase("Yes")) {
			getObject("sl_currency_dropdown").click();
			}

		// Select SL Tier
		if (!slTier.equalsIgnoreCase("")) {
			new Select(getObject("sl_tier_dropdown")).selectByVisibleText(slTier);}

		// Enter SL Performance Computation Calculation Data
		if (!slComputationCalculationData.equalsIgnoreCase("")) {
			getObject("sl_performance_computation_calculation_textarea").sendKeys(slComputationCalculationData);
			}

		// Enter SL Performance Data Calculation Data
		if (!slDataCalculationData.equalsIgnoreCase("")) {
			getObject("sl_performance_data_calculation_textarea").sendKeys(slDataCalculationData);
			}

		// Enter SL Unique Criteria
		if (!slDataCriteria.equalsIgnoreCase("")) {
			getObject("sl_unique_criteria_textarea").sendKeys(slDataCriteria);
			}
		
		// Service Levels - Create Page - OTHER INFORMATION
		// Select SL Priority
		if (!slPriority.equalsIgnoreCase("")) {
			new Select(getObject("sl_priority_dropdown")).selectByVisibleText(slPriority);
      		}

		// Select SL Reference, and Enter SL Page Number and SL Clause
		if (!slReferences.equalsIgnoreCase("")) {
			new Select(getObject("sl_references_dropdown")).selectByVisibleText(slReferences);
			if (!slClause.equalsIgnoreCase("")) {
				getObject("sl_clause_textbox").sendKeys(slClause);
				}
			if (!slPageNumber.equalsIgnoreCase("")) {
				getObject("sl_page_number_numeric_box").sendKeys(slPageNumber);
				}

			// Check for SL Reference and SL Page Number
			String slAttribute = getObject("sl_page_number_numeric_box").getAttribute("maxvalue");
			if (slAttribute.equalsIgnoreCase("UNINDEXED")) {
				String slError = getObject("sl_references_dropdown_errors").getText();

				Assert.assertEquals(slError, "Document is yet to be indexed. Please try attaching this document after sometime.");

				APP_LOGS.debug("SL Reference Document is yet to be indexed. Please try attaching this document after sometime.");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));
				
				return;
				}
			
			if (Integer.valueOf(slPageNumber) > Integer.valueOf(slAttribute)) {
				String slError = getObject("sl_page_number_numeric_box_errors").getText();
				System.out.println("slError: " + slError);

				// Assert.assertEquals(slError, "Document is yet to be indexed. Please try attaching this document after sometime.");

				APP_LOGS.debug("SL Page Number has more value than Reference Document");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));

				return;
				}
			}
		
		// Select Application Group and Application
		if (!slApplicationGroup.equalsIgnoreCase("")) {
			for (String slData : slApplicationGroup.split(";")) {
				new Select(getObject("sl_application_group_multi_dropdown")).selectByVisibleText(slData.trim());
				}
			if (!slApplication.equalsIgnoreCase("")) {
				for (String slData : slApplication.split(";")) {
					new Select(getObject("sl_application_multi_dropdown")).selectByVisibleText(slData.trim());
					}
				}
			}

		// Select Minimum or Maximum
		if (!slMinimumMaximumSelection.equalsIgnoreCase("")) {
			new Select(getObject("sl_minimum_maximum_dropdown")).selectByVisibleText(slMinimumMaximumSelection);
			}

		// Select Unit of Measurement
		if (!slUnitofMeasurement.equalsIgnoreCase("")) {
			new Select(getObject("sl_unit_of_sl_measurement_dropdown")).selectByVisibleText(slUnitofMeasurement);
			}

		// Enter Minimum or Maximum Value
		if (!slMinimumMaximumValue.equalsIgnoreCase("")) {
			getObject("sl_minimum_maximum_numeric_box").sendKeys(slMinimumMaximumValue);
			}

		// Enter Expected Value
		if (!slExpected.equalsIgnoreCase("")) {
			getObject("sl_expected_numeric_box").sendKeys(slExpected);
			}

		// Enter Significantly Minimum or Maximum Value
		if (!slSignificantlyMinMax.equalsIgnoreCase("")) {
			getObject("sl_significantly_min_max_numeric_box").sendKeys(slSignificantlyMinMax);
			}

		// Select Measurement Window
		if (!slMeasurementWindow.equalsIgnoreCase("")) {
			new Select(getObject("sl_measurement_window_dropdown")).selectByVisibleText(slMeasurementWindow);
			}

		// Select YTD Start Date
		if (!slYTDStartDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("ytdStartDate")).click();
			String[] slDate = slYTDStartDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}

		// SL Credit Applicable Section
		if (slCreditApplicable.equalsIgnoreCase("Yes")) {
			getObject("sl_credit_applicable_checkbox").click();

			if (!slCreditApplicableDate.equalsIgnoreCase("")) {
				driver.findElement(By.name("creditApplicableDate")).click();
				String[] slDate = slCreditApplicableDate.split("-");

				String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				while (!slMonth.equalsIgnoreCase(slDate[0])) {
					driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
					slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
					}
				
				new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

				driver.findElement(By.linkText(slDate[1])).click();
				}
			
			if (!slCreditClause.equalsIgnoreCase("")) {
				getObject("sl_credit_clause_textarea").sendKeys(slCreditClause);
				}

			if (!slCreditOfInvoice.equalsIgnoreCase("")) {
				getObject("sl_credit_percentage_of_invoice_numeric_box").sendKeys(slCreditOfInvoice);
				}

			if (!slSLCreditCategory.equalsIgnoreCase("")) {
				getObject("sl_sl_credit_percentage_category_numeric_box").sendKeys(slSLCreditCategory);
				}

			if (!slSLCreditSubCategory.equalsIgnoreCase("")) {
				getObject("sl_sl_credit_percentage_sub_category_numeric_box").sendKeys(slSLCreditSubCategory);
				}

			if (!slSLCreditLineItem.equalsIgnoreCase("")) {
				getObject("sl_sl_credit_percentage_line_item_numeric_box").sendKeys(slSLCreditLineItem);
				}

			if (!slCreditFrequency.equalsIgnoreCase("")) {
				new Select(getObject("sl_credit_frequency_dropdown")).selectByVisibleText(slCreditFrequency);
				}

			if (!slEarnbackApplicableDate.equalsIgnoreCase("")) {
				driver.findElement(By.name("earnbackApplicableDate")).click();
				String[] slDate = slEarnbackApplicableDate.split("-");

				String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				while (!slMonth.equalsIgnoreCase(slDate[0])) {
					driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
					slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
					}

				new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

				driver.findElement(By.linkText(slDate[1])).click();
				}

			if (!slEarnbackClause.equalsIgnoreCase("")) {
				getObject("sl_earnback_clause_textarea").sendKeys(slEarnbackClause);
				}

			if (!slEarnbackCategory.equalsIgnoreCase("")) {
				getObject("sl_earnback_percentage_category_numeric_box").sendKeys(slEarnbackCategory);
				}

			if (!slEarnbackSubCategory.equalsIgnoreCase("")) {
				getObject("sl_earnback_percentage_sub_category_numeric_box").sendKeys(slEarnbackSubCategory);
				}

			if (!slEarnbackLineItem.equalsIgnoreCase("")) {
				getObject("sl_earnback_percentage_line_item_numeric_box").sendKeys(slEarnbackLineItem);
				}

			if (!slEarnbackFrequency.equalsIgnoreCase("")) {
				new Select(getObject("sl_earnback_frequency_dropdown")).selectByVisibleText(slEarnbackFrequency);
				}
			}

		// SL Subject To Continuous Improvement Section
		if (slSubjectTo.equalsIgnoreCase("Yes")) {
			getObject("sl_subject_to_continuous_improvement_checkbox").click();
			if (!slContinuousImprovementClause.equalsIgnoreCase("")) {
				getObject("sl_continuous_improvement_clause_textbox").sendKeys(slContinuousImprovementClause);
				}
			}

		// Select SL Frequency Type
		if (!slFrequencyType.equalsIgnoreCase("")) {
			new Select(getObject("sl_frequency_type_dropdown")).selectByVisibleText(slFrequencyType);
			if (!slComputationFrequency.equalsIgnoreCase("")) {
				new Select(getObject("sl_computation_frequency_dropdown")).selectByVisibleText(slComputationFrequency);
				}
			}

		// Select SL Frequency
		if (!slFrequency.equalsIgnoreCase("")) {
			new Select(getObject("sl_frequency_dropdown")).selectByVisibleText(slFrequency);
			}

		// Select SL Start Date
		if (!slStartDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("startDate")).click();
			String[] slDate = slStartDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}

		// Select SL End Date
		if (!slEndDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("expDate")).click();
			String[] slDate = slEndDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}

		// Select SL Pattern Date
		if (!slPatternDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("patternDate")).click();
			String[] slDate = slPatternDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}

		// Select SL Effective Date
		if (!slEffectiveDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("effectiveDate")).click();
			String[] slDate = slEffectiveDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}

		// Select SL Functions and Services
		if (!slFunctions.equalsIgnoreCase("")) {
			for (String slData : slFunctions.split(";")) {
				new Select(getObject("sl_functions_multi_dropdown")).selectByVisibleText(slData.trim());
				}
			if (!slServices.equalsIgnoreCase("")) {
				for (String slData : slServices.split(";")) {
					new Select(getObject("sl_services_multi_dropdown")).selectByVisibleText(slData.trim());
					}
				}
			}

		// Select SL Service Category
		if (!slServiceCategory.equalsIgnoreCase("")) {
			for (String slData : slServiceCategory.split(";")) {
				new Select(getObject("sl_service_category_multi_dropdown")).selectByVisibleText(slData.trim());
				}
			}

		// Select SL Management Regions and Countries
		if (!slManagementRegions.equalsIgnoreCase("")) {
			for (String slData : slManagementRegions.split(";")) {
				new Select(getObject("sl_management_regions_multi_dropdown")).selectByVisibleText(slData.trim());
				}
			if (!slManagementCountries.equalsIgnoreCase("")) {
				for (String slData : slManagementCountries.split(";")) {
					new Select(getObject("sl_management_countries_multi_dropdown")).selectByVisibleText(slData.trim());
					}
				}
			}

		// Select SL Contract Regions and Countries
		if(getObject("sl_contract_regions_multi_dropdown").isDisplayed()) {
			if (!slContractRegions.equalsIgnoreCase("")) {
				for (String slData : slContractRegions.split(";")) {
					new Select(getObject("sl_contract_regions_multi_dropdown")).selectByVisibleText(slData.trim());
					}
				if (!slContractCountries.equalsIgnoreCase("")) {
					for (String slData : slContractCountries.split(";")) {
						new Select(getObject("sl_contract_countries_multi_dropdown")).selectByVisibleText(slData.trim());
						}
					}
				}
			}

		// Select SL Responsibility
		if (!slResponsibility.equalsIgnoreCase("")) {
			new Select(getObject("sl_responsibility_dropdown")).selectByVisibleText(slResponsibility);
			}

		// Select SL Financial Impact Applicable Section
		if (slFinancialImpactApplicable.equalsIgnoreCase("Yes")) {
			getObject("sl_financial_impact_applicable_checkbox").click();

			if (!slFinancialImpactValue.equalsIgnoreCase("")) {
				getObject("sl_financial_impact_value_numeric_box").sendKeys(slFinancialImpactValue);
				}

			if (!slFinancialImpactClause.equalsIgnoreCase("")) {
				getObject("sl_financial_impact_clause_textarea").sendKeys(slFinancialImpactClause);
				}

			if (!slImpactDays.equalsIgnoreCase("")) {
				getObject("sl_impact_days_numeric_box").sendKeys(slImpactDays);
				}

			if (!slImpactType.equalsIgnoreCase("")) {
				for (String slData : slImpactType.split(";")) {
					new Select(getObject("sl_impact_type_multi_dropdown")).selectByVisibleText(slData.trim());
					}
				}
			}

		// Select SL Credit Impact Applicable Section
		if (slCreditImpactApplicable.equalsIgnoreCase("Yes")) {
			getObject("sl_credit_impact_applicable_checkbox").click();

			if (!slCreditImpactValue.equalsIgnoreCase("")) {
				getObject("sl_credit_impact_value_numeric_box").sendKeys(slCreditImpactValue);
			}

			if (!slCreditImpactClause.equalsIgnoreCase("")) {
				getObject("sl_credit_impact_clause_textarea").sendKeys(slCreditImpactClause);
				}
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		Thread.sleep(5000);

		if (slTitle.equalsIgnoreCase("")) {
			fail = true;

			String slAttribute = getObject("sl_title_textbox").getAttribute("class");
			String[] slAttributeSplit = slAttribute.split(" ");
			String slAttributeSplitString = slAttributeSplit[3];

			Assert.assertEquals(slAttributeSplitString, "errorClass");

			APP_LOGS.debug("SL Title is blank");

			fail = false;

			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (slTitle.length() > 512) {
			fail = true;
			String slLengthError = getObject("sl_title_textbox_errors").getText();
			System.out.println("slLengthError " + slLengthError);

			Assert.assertEquals(slLengthError, "Maximum characters allowed is 512");

			APP_LOGS.debug("SL Title has more than 512 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (slSLID.equalsIgnoreCase("")) {
			fail = true;

			String slAttribute = getObject("sl_sl_id_textbox").getAttribute("class");
			String[] slAttributeSplit = slAttribute.split(" ");
			String slAttributeSplitString = slAttributeSplit[3];

			Assert.assertEquals(slAttributeSplitString, "errorClass");

			APP_LOGS.debug("SL SL ID is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (slPriority.equalsIgnoreCase("")) {
			fail = true;

			String slAttribute = getObject("sl_priority_dropdown").getAttribute("class");
			String[] slAttributeSplit = slAttribute.split(" ");
			String slAttributeSplitString = slAttributeSplit[3];

			Assert.assertEquals(slAttributeSplitString, "errorClass");

			APP_LOGS.debug("SL Priority is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (slPageNumber.length() > 18) {
			fail = true;
			String slLengthError = getObject("sl_page_number_numeric_box_errors").getText();
			System.out.println("slLengthError " + slLengthError);

			Assert.assertEquals(slLengthError, "Only integer values are allowed.");

			APP_LOGS.debug("SL Page Number has more than 18 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (getObject("sl_notification_popup_id") != null) {
			String sl_id = getObject("sl_notification_popup_id").getText();
			APP_LOGS.debug("Service Level created successfully with Service Level id "+sl_id);

			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(5000);

			getObject("client_user_quick_search_box").sendKeys(sl_id);
			getObject("client_user_quick_search_box").sendKeys(Keys.ENTER);
			Thread.sleep(5000);

			String slShowPage = getObject("sl_show_page_id_textbox").getText();

			Assert.assertEquals(slShowPage, sl_id);
			APP_LOGS.debug("SL ID on show page has been verified");
			}

		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}
	
	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(sl_suite_xls, this.getClass().getSimpleName());
		}
	}
