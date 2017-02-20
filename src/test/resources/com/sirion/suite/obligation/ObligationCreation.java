package test.resources.com.sirion.suite.obligation;

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

import test.resources.com.sirion.util.DatePicker;
import test.resources.com.sirion.util.TestUtil;
import testlink.api.java.client.TestLinkAPIException;

public class ObligationCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(obligation_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void ObligationCreationTest(String obTitle, String obDescription, String obPerformanceType, String obCategory,
			String obTimezone, String obDeliveryCountries, String obCurrency, String obSupplierAccess, String obTier,
			String obPriority, String obPhase,String obReferences, String obClause,
			String obPageNumber, String obFrequencyType, String obFrequency, String obWeekType,String obStartDateMonth,String obStartDateDate, String obStartDateYear,String obEndDateMonth,String obEndDateDate,
			String obEndDateYear,String obPatternDateMonth, String obPatternDateDate, String obPatternDateYear, String obEffectiveDateMonth, String	obEffectiveDateDate, String obEffectiveDateYear, 
			String obStartDate, String obEndDate,
			String obPatternDate, String obEffectiveDate, String obFunctions, String obServices, String obServiceCategory, String obManagementRegions,
			String obManagementCountries, String obContractRegions, String obContractCountries, String obStakeholder, String obResponsibility,
			String obFinancialImpactApplicable, String obFinancialImpactValue, String obFinancialImpactClause, String obImpactDays, String obImpactType,
			String obComments, String obActualDate, String obRequestedBy,
			String obChangeRequest, String obUploadFile, String obSupplier, String obSource, String obSourceName, String obGlobalCreation)
			throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}

		APP_LOGS.debug("Executing Test Case Obligation Creation with Title ---- "+ obTitle + " under Contract ---- " + obSourceName);

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
				// Condition Check for Global Creation
		if (obGlobalCreation.equalsIgnoreCase("Yes")) {
			wait_in_report.until(ExpectedConditions.visibilityOf(getObject("obligation_quick_link")));
			
			
			Thread.sleep(10000);
			// Click on Service Levels Quick Link
			getObject("obligation_quick_link").click();
			APP_LOGS.debug("Click on Obligations Quick Link");
			Thread.sleep(5000);
			
			getObject("ob_link").click();
			Thread.sleep(5000);
			// Click on Plus button for Global Creation Form
			wait_in_report.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Create Obligation']")));
			driver.findElement(By.xpath("//a[@title='Create Obligation']")).click();;
			APP_LOGS.debug("Click on Plus (+) button on Obligation Global Listing");

			// Select Supplier on Global Creation Form
			if (!obSupplier.equalsIgnoreCase("")) {
				new Select(getObject("ob_create_ob_supplier_dropdown")).selectByVisibleText(obSupplier);
				}

			// Select Source on Global Creation Form
			if (!obSource.equalsIgnoreCase("")) {
				new Select(getObject("ob_create_ob_source_dropdown")).selectByVisibleText(obSource);
				}

			// Select Source Name/Title on Global Creation Form
			if (!obSourceName.equalsIgnoreCase("")) {
				new Select(getObject("ob_create_ob_source_name_title_dropdown")).selectByVisibleText(obSourceName);
				}

			// Click on Submit of Global Creation Form
			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
			driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
			Thread.sleep(5000);

			// Conditional Check for Supplier
			if (obSupplier.equalsIgnoreCase("")) {
				fail = true;
				String obAttribute = getObject("ob_priority_dropdown").getAttribute("class");
				String[] obAttributeSplit = obAttribute.split(" ");
				String obAttributeSplitString = obAttributeSplit[3];

				Assert.assertEquals(obAttributeSplitString, "errorClass");

				APP_LOGS.debug("ob Supplier is blank on Global Creation Form");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));

				return;
				}

			// Conditional Check for Source or Source Name
			if (getObject("ob_create_ob_workflow_error") != null) {
				String obError = getObject("ob_create_ob_workflow_error").getText();
				System.out.println("obError: " + obError);

				Assert.assertEquals(obError, "Workflow configured incorrectly for Obligations.");

				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
				driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
				Thread.sleep(5000);

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));

				return;
				}
			}
		
		// Conditional Check for Contract Show Page Creation Form
		else if(!obGlobalCreation.equalsIgnoreCase("Yes")) {
			wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("contracts_quick_link")));

			// Click on Contracts Quick Link
			getObject("contracts_quick_link").click();
			APP_LOGS.debug("Click on Contracts Quick Link");
			wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("contracts_listing_page_display_dropdown")));

			new Select(getObject("contracts_listing_page_display_dropdown")).selectByIndex(4);
			Thread.sleep(5000);

			// Conditional Check for ob Parent Contract
			if (!obSourceName.equalsIgnoreCase("")) {
				driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'" +obSourceName+ "')]/preceding-sibling::td[1]/a")).click();
			} else {
				driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr/td[2]/a")).click();
				}
			Thread.sleep(5000);

			APP_LOGS.debug("Click on Contracts ID under Listing Page");

			// Click on Plus button to open the Create Entities List
			plus_button("contracts_plus_button");
			APP_LOGS.debug("Click on Plus (+) button on Contracts Show Page");

			// Click on Create Service Level to open Create Service Level Page
			driver.findElement(By.linkText("Create Obligations")).click();
			APP_LOGS.debug("Click on Create Service Level");
			}
		
		// Service Levels - Create Page - BASIC INFORMATION
		// Enter ob Title
		if (!obTitle.equalsIgnoreCase("")) {
			getObject("ob_title_textbox").sendKeys(obTitle);
			}

		// Enter ob Description
		if (!obDescription.equalsIgnoreCase("")) {
			getObject("ob_description_textarea").sendKeys(obDescription);
			driver.findElement(By.xpath("//p[contains(.,'BASIC INFORMATION')]")).click();
			}

		// Enter ob ID
		if (!obPerformanceType.equalsIgnoreCase("")) {
			getObject("ob_perf_type_dropdown").sendKeys(obPerformanceType);
			}

		// Select ob Category, ob Sub-Category and ob Item
		if (!obCategory.equalsIgnoreCase("")) {
			new Select(getObject("ob_category_dropdown")).selectByVisibleText(obCategory);
			}
		
		// Select ob Timezone
		if (!obTimezone.equalsIgnoreCase("")) {
			new Select(getObject("ob_timezone_dropdown")).selectByVisibleText(obTimezone);
			try {
	      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
	      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	      	} catch (Exception e) {
	      		
	      	}
			}

		// Select ob Delivery Countries
		if (!obDeliveryCountries.equalsIgnoreCase("")) {
			for (String obData : obDeliveryCountries.split(";")) {
				new Select(getObject("ob_delcountry_multi")).selectByVisibleText(obData.trim());
				}
			}

		// Select ob Currency
		if (!obCurrency.equalsIgnoreCase("")) {
			new Select(getObject("ob_currency_dropdown")).selectByVisibleText(obCurrency);
			}

		// Check Supplier Access
		if (obSupplierAccess.equalsIgnoreCase("Yes")) {
			getObject("ob_supplier_access_checkbox").click();
			}

		// Select ob Tier
		if (!obTier.equalsIgnoreCase("")) {
			new Select(getObject("ob_tier_dropdown")).selectByVisibleText(obTier);}
		
		// Service Levels - Create Page - OTHER INFORMATION
		// Select ob Priority
		if (!obPriority.equalsIgnoreCase("")) {
			new Select(getObject("ob_priority_dropdown")).selectByVisibleText(obPriority);
      		}
		
		
		
		if (!obPhase.equalsIgnoreCase("")) {
			new Select(getObject("ob_phase_dropdown")).selectByVisibleText(obPhase);
      		}
		// Select ob Reference, and Enter ob Page Number and ob Clause
		if (!obReferences.equalsIgnoreCase("")) {
			new Select(getObject("ob_reference_dropdown")).selectByVisibleText(obReferences);
			if (!obClause.equalsIgnoreCase("")) {
				getObject("ob_clause_textbox").sendKeys(obClause);
				}
			if (!obPageNumber.equalsIgnoreCase("")) {
				getObject("ob_page_no_textbox").sendKeys(obPageNumber);
				}

			// Check for ob Reference and ob Page Number
			String obAttribute = getObject("ob_page_no_textbox").getAttribute("maxvalue");
			if (obAttribute.equalsIgnoreCase("UNINDEXED")) {
				String obError = getObject("ob_references_dropdown_errors").getText();

				Assert.assertEquals(obError, "Document is yet to be indexed. Please try attaching this document after sometime.");

				APP_LOGS.debug("ob Reference Document is yet to be indexed. Please try attaching this document after sometime.");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));
				
				return;
				}
			
			if (Integer.valueOf(obPageNumber) > Integer.valueOf(obAttribute)) {
				String obError = getObject("ob_page_number_numeric_box_errors").getText();
				System.out.println("obError: " + obError);

				// Assert.assertEquals(obError, "Document is yet to be indexed. Please try attaching this document after sometime.");

				APP_LOGS.debug("ob Page Number has more value than Reference Document");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));

				return;
				}
			}
		
		
		// Select ob Frequency Type
		if (!obFrequencyType.equalsIgnoreCase("")) {
			new Select(getObject("ob_frequency_type_dropdown")).selectByVisibleText(obFrequencyType);
			}

		// Select ob Frequency
		if (!obFrequency.equalsIgnoreCase("")) {
			new Select(getObject("ob_frequency_dropdown")).selectByVisibleText(obFrequency);
			}
System.out.println("Gaurav");
		// Select ob week type
		if (!obWeekType.equalsIgnoreCase("")) {
			System.out.println("Arora");
			new Select(getObject("ob_week_type_dropdown")).selectByVisibleText(obWeekType);
			}
		System.out.println("Arora");
		/*
		// Select ob Start Date
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.name("startDate")));
		if (!obStartDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("startDate")).click();
			Thread.sleep(2000);
			String[] obDate = obStartDate.split("-");

			String obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!obMonth.equalsIgnoreCase(obDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				Thread.sleep(2000);
				obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(obDate[2]);
			Thread.sleep(2000);

			driver.findElement(By.linkText(obDate[1])).click();
			}

		// Select ob End Date
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.name("expDate")));
		if (!obEndDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("expDate")).click();
			String[] obDate = obEndDate.split("-");

			String obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!obMonth.equalsIgnoreCase(obDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(obDate[2]);
			driver.findElement(By.linkText(obDate[1])).click();
			}

		// Select ob Pattern Date
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.name("patternDate")));
		if (!obPatternDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("patternDate")).click();
			String[] obDate = obPatternDate.split("-");

			String obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!obMonth.equalsIgnoreCase(obDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(obDate[2]);

			driver.findElement(By.linkText(obDate[1])).click();
			}

		// Select ob Effective Date
	//	((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.name("effectiveDate")));
		if (!obEffectiveDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("effectiveDate")).click();
			String[] obDate = obEffectiveDate.split("-");

			String obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!obMonth.equalsIgnoreCase(obDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(obDate[2]);

			driver.findElement(By.linkText(obDate[1])).click();
			}
*/
		
		
		
		System.out.println("Naveen Date Code Start");
		
		
		
		// Date picker
				// Enter value in CR Date field
				String ob_DateMonth = convertDoubleToIntegerInStringForm(obStartDateMonth);
				int ob_dateMonth = Integer.parseInt(ob_DateMonth);
				String ob_DateYear = convertDoubleToIntegerInStringForm(obStartDateYear);
				int ob_dateYear = Integer.parseInt(ob_DateYear);
				String ob_DateDate = convertDoubleToIntegerInStringForm(obStartDateDate);
				Integer ob_dateDate = Integer.parseInt(ob_DateDate);
				ob_DateDate = ob_dateDate.toString();

				DatePicker dp_cr_Date_date = new DatePicker();
				dp_cr_Date_date.expDate = ob_DateDate;
				dp_cr_Date_date.expMonth = ob_dateMonth;
				dp_cr_Date_date.expYear = ob_dateYear;
				dp_cr_Date_date.pickExpDate("startDate");

				
				// Enter value in End Date field
				String ob_end_DateMonth = convertDoubleToIntegerInStringForm(obEndDateDate);
				int ob_end_dateMonth = Integer.parseInt(ob_end_DateMonth);
				String ob_end_DateYear = convertDoubleToIntegerInStringForm(obEndDateYear);
				int ob_end_dateYear = Integer.parseInt(ob_end_DateYear);
				String ob_end_DateDate = convertDoubleToIntegerInStringForm(obEndDateMonth);
				Integer ob_end_dateDate = Integer.parseInt(ob_end_DateDate);
				ob_end_DateDate = ob_end_dateDate.toString();

				DatePicker dp_ob_end_Date_date = new DatePicker();
				dp_ob_end_Date_date.expDate = ob_end_DateDate;
				dp_ob_end_Date_date.expMonth = ob_end_dateMonth;
				dp_ob_end_Date_date.expYear = ob_end_dateYear;
				dp_ob_end_Date_date.pickExpDate("expDate");
				
				
				// Enter value in patternDate Date field
				String ob_pattern_DateMonth = convertDoubleToIntegerInStringForm(obPatternDateDate);
				int ob_pattern_dateMonth = Integer.parseInt(ob_pattern_DateMonth);
				String ob_pattern_DateYear = convertDoubleToIntegerInStringForm(obPatternDateYear);
				int ob_pattern_dateYear = Integer.parseInt(ob_pattern_DateYear);
				String ob_pattern_DateDate = convertDoubleToIntegerInStringForm(obPatternDateMonth);
				Integer ob_pattern_dateDate = Integer.parseInt(ob_pattern_DateDate);
				ob_pattern_DateDate = ob_pattern_dateDate.toString();

				DatePicker dp_ob_pattern_Date_date = new DatePicker();
				dp_ob_pattern_Date_date.expDate = ob_pattern_DateDate;
				dp_ob_pattern_Date_date.expMonth = ob_pattern_dateMonth;
				dp_ob_pattern_Date_date.expYear = ob_pattern_dateYear;
				dp_ob_pattern_Date_date.pickExpDate("patternDate");
				
				
				// Enter value in Effective Date field
				String ob_effective_DateMonth = convertDoubleToIntegerInStringForm(obEffectiveDateDate);
				int ob_effective_dateMonth = Integer.parseInt(ob_effective_DateMonth);
				String ob_effective_DateYear = convertDoubleToIntegerInStringForm(obEffectiveDateYear);
				int ob_effective_dateYear = Integer.parseInt(ob_effective_DateYear);
				String ob_effective_DateDate = convertDoubleToIntegerInStringForm(obEffectiveDateMonth);
				Integer ob_effective_dateDate = Integer.parseInt(ob_effective_DateDate);
				ob_effective_DateDate = ob_effective_dateDate.toString();

				DatePicker dp_ob_effective_Date_date = new DatePicker();
				dp_ob_effective_Date_date.expDate = ob_effective_DateDate;
				dp_ob_effective_Date_date.expMonth = ob_effective_dateMonth;
				dp_ob_effective_Date_date.expYear = ob_effective_dateYear;
				dp_ob_effective_Date_date.pickExpDate("effectiveDate");
		
		
				
				
		
		
				System.out.println("Naveen Date Code End");
		
		
		
		
		
		
		// Select ob Functions and Services
		if (!obFunctions.equalsIgnoreCase("")) {
			for (String obData : obFunctions.split(";")) {
				new Select(getObject("ob_function_multi")).selectByVisibleText(obData.trim());
				}
			if (!obServices.equalsIgnoreCase("")) {
				for (String obData : obServices.split(";")) {
					new Select(getObject("ob_services_multi")).selectByVisibleText(obData.trim());
					}
				}
			}

		// Select ob Service Category
		if (!obServiceCategory.equalsIgnoreCase("")) {
			for (String obData : obServiceCategory.split(";")) {
				new Select(getObject("ob_services_category_multi")).selectByVisibleText(obData.trim());
				}
			}

		// Select ob Management Regions and Countries
		if (!obManagementRegions.equalsIgnoreCase("")) {
			for (String obData : obManagementRegions.split(";")) {
				new Select(getObject("ob_management_region_multi")).selectByVisibleText(obData.trim());
				}
			if (!obManagementCountries.equalsIgnoreCase("")) {
				for (String obData : obManagementCountries.split(";")) {
					new Select(getObject("ob_management_countries_multi")).selectByVisibleText(obData.trim());
					}
				}
			}
/*
		// Select ob Contract Regions and Countries
		if(getObject("ob_contract_regions_multi_dropdown").isDisplayed()) {
			if (!obContractRegions.equalsIgnoreCase("")) {
				for (String obData : obContractRegions.split(";")) {
					new Select(getObject("ob_contract_regions_multi_dropdown")).selectByVisibleText(obData.trim());
					}
				if (!obContractCountries.equalsIgnoreCase("")) {
					for (String obData : obContractCountries.split(";")) {
						new Select(getObject("ob_contract_countries_multi_dropdown")).selectByVisibleText(obData.trim());
						}
					}
				}
			}
*/
		// Select ob Responsibility
		if (!obResponsibility.equalsIgnoreCase("")) {
			new Select(getObject("ob_responsibility_dropdown")).selectByVisibleText(obResponsibility);
			}

		// Select ob Financial Impact Applicable Section
		if (obFinancialImpactApplicable.equalsIgnoreCase("Yes")) {
			getObject("ob_financial_impact_applicable_checkbox").click();

			if (!obFinancialImpactValue.equalsIgnoreCase("")) {
				getObject("ob_financial_impact_value_numeric_box").sendKeys(obFinancialImpactValue);
				}

			if (!obFinancialImpactClause.equalsIgnoreCase("")) {
				getObject("ob_financial_impact_clause_textarea").sendKeys(obFinancialImpactClause);
				}

			if (!obImpactDays.equalsIgnoreCase("")) {
				getObject("ob_impact_days_numeric_box").sendKeys(obImpactDays);
				}

			if (!obImpactType.equalsIgnoreCase("")) {
				for (String obData : obImpactType.split(";")) {
					new Select(getObject("ob_impact_type_multi_dropdown")).selectByVisibleText(obData.trim());
					}
				}
			}
		Thread.sleep(5000);
		System.out.println("before save button");
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));
		driver.findElement(By.xpath(".//button[contains(.,'Save')][@clientvalidation='true']")).click();
		System.out.println("after save button");
		//getObject("ob_savebutton").click();
		Thread.sleep(5000);

		if (obTitle.equalsIgnoreCase("")) {
			fail = true;

			String obAttribute = getObject("ob_title_textbox").getAttribute("class");
			String[] obAttributeSplit = obAttribute.split(" ");
			String obAttributeSplitString = obAttributeSplit[3];

			Assert.assertEquals(obAttributeSplitString, "errorClass");

			APP_LOGS.debug("ob Title is blank");

			fail = false;

			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (obTitle.length() > 512) {
			fail = true;
			String obLengthError = getObject("ob_title_textbox_errors").getText();
			System.out.println("obLengthError " + obLengthError);

			Assert.assertEquals(obLengthError, "Maximum characters allowed is 512");

			APP_LOGS.debug("ob Title has more than 512 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (obPriority.equalsIgnoreCase("")) {
			fail = true;

			String obAttribute = getObject("ob_priority_dropdown").getAttribute("class");
			String[] obAttributeSplit = obAttribute.split(" ");
			String obAttributeSplitString = obAttributeSplit[3];

			Assert.assertEquals(obAttributeSplitString, "errorClass");

			APP_LOGS.debug("ob Priority is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (obPageNumber.length() > 18) {
			fail = true;
			String obLengthError = getObject("ob_page_no_textbox").getText();
			System.out.println("obLengthError " + obLengthError);

			Assert.assertEquals(obLengthError, "Only integer values are allowed.");

			APP_LOGS.debug("ob Page Number has more than 18 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (getObject("ob_notification_popup_id") != null) {
			String ob_id = getObject("ob_notification_popup_id").getText();
			APP_LOGS.debug("Service Level created successfully with Service Level id "+ob_id);

			Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(5000);

			getObject("client_user_quick_search_box").sendKeys(ob_id);
			getObject("client_user_quick_search_box").sendKeys(Keys.ENTER);
			Thread.sleep(10000);

			String obIdFromShowPage = getObject("ob_show_id").getText();
			System.out.println("OB Id " + obIdFromShowPage);

			Assert.assertEquals(obIdFromShowPage, ob_id);
			APP_LOGS.debug("ob ID on show page has been verified");
			}
		driver.get(CONFIG.getProperty("endUserURL"));
		fail = false;
		
		}
	
	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(obligation_suite_xls, this.getClass().getSimpleName());
		}
	}
