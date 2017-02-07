package test.resources.com.sirion.suite.obligation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
import testlink.api.java.client.TestLinkAPIException;

public class ObligationUpdation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	WebElement element;
	JavascriptExecutor executor;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(obligation_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void ServiceLevelUpdateTest(String obTitle, String obDescription, String obPerformanceType, String obCategory,
			String obTimezone, String obDeliveryCountries, String obCurrency, String obSupplierAccess, String obTier,
			String obPriority, String obPhase,String obReferences, String obClause,
			String obPageNumber, String obFrequencyType, String obFrequency, String obWeekType,String obStartDate, String obEndDate,
			String obPatternDate, String obEffectiveDate, String obFunctions, String obServices, String obServiceCategory, String obManagementRegions,
			String obManagementCountries, String obContractRegions, String obContractCountries, String obStakeholder, String obResponsibility,
			String obFinancialImpactApplicable, String obFinancialImpactValue, String obFinancialImpactClause, String obImpactDays, String obImpactType,
			String obComments, String obActualDate, String obRequestedBy,
			String obChangeRequest, String obUploadFile, String obSupplier, String obSource, String obSourceName)
			throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}

		APP_LOGS.debug("Executing Test Case Service Level Update with Title ---- "+ obTitle + " under Contract ---- " + obSourceName);

		// Launch The Browser
		openBrowser();

		// Login as Client user
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
	
		
		
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("client_user_analytics_tab_link")));
		Thread.sleep(10000);
		// Click on Analytics
		getObject("client_user_analytics_tab_link").click();
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("obligation_quick_link")));
		
		// Click on Obligations Quick Link
		getObject("obligation_quick_link").click();
		APP_LOGS.debug("Click on Obligations Quick Link");
		Thread.sleep(10000);
		
		getObject("ob_link").click();
		Thread.sleep(10000);
		
		
		getObject("ob_id_link").click();
		Thread.sleep(10000);

		// Clicking the clone button
		  System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Clone')]")));

			driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();		
		//getObject("ac_clone_button").click();
		System.out.println("clicked the clone button");
		Thread.sleep(10000);

		//clicking the create action button after cloning
		//Assert.assertNotNull(driver.findElement(By.xpath("ac_create_action")));
		  System.out.println("gaurav");
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")));

			driver.findElement(By.xpath("//button[contains(.,'Save')][@clientvalidation='true']")).click();
		Thread.sleep(10000);
		
	  /*  if(getObject("ob_popup_id")!=null) {
	    	String ob_id = getObject("ob_popup_id").getText();
	    	APP_LOGS.debug("Service Level cloned successfully with Service Level id "+ob_id);
	    	
	    	getObject("ob_popup_id").click();
	    	Thread.sleep(5000);
	    	}*/
	    
	    if (getObject("ob_popup_id") != null) {

			String ob_id = getObject("ob_popup_id").getText();
			APP_LOGS.debug("Action Cloned successfully with Issue id "+ ob_id);

			//Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
			Thread.sleep(10000);

			APP_LOGS.debug("Quick Search the created OB with OB id "+ ob_id);

			getObject("quick_search_textbox").sendKeys(ob_id);

			getObject("quick_search_textbox").sendKeys(Keys.ENTER);
			Thread.sleep(10000);

			String obIdFromShowPage = getObject("ob_show_id").getText();
			System.out.println("OB Id " + obIdFromShowPage);

		}
	    
	    Thread.sleep(20000);
	    
	    System.out.println("gaurav");
	   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Edit')]")));

		driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
		Thread.sleep(10000);
		System.out.println("clicked on edit");
		
		
		
		// Obligations - Create Page - BASIC INFORMATION
		// Enter DNO Title
		if (!obTitle.equalsIgnoreCase("")) {
			getObject("ob_title_textbox").clear();
			getObject("ob_title_textbox").sendKeys(obTitle);
			}

		// Enter DNO Description
		if (!obDescription.equalsIgnoreCase("")) {
			getObject("ob_description_textarea").clear();
			getObject("ob_description_textarea").sendKeys(obDescription);
			driver.findElement(By.xpath("//p[contains(.,'BASIC INFORMATION')]")).click();
			}

		// Select DNO Performance Type
				if (!obPerformanceType.equalsIgnoreCase("")) {
					new Select (getObject("ob_perf_type_dropdown")).selectByVisibleText(obPerformanceType);
					}

				// Select DNO Category
				if (!obCategory.equalsIgnoreCase("")) {
					new Select(getObject("ob_category_dropdown")).selectByVisibleText(obCategory);
					}
		
		// Select DNO Timezone
		if (!obTimezone.equalsIgnoreCase("")) {
			new Select(getObject("ob_timezone_dropdown")).selectByVisibleText(obTimezone);
			}

		// Select DNO Delivery Countries
		if (!obDeliveryCountries.equalsIgnoreCase("")) {
			new Select(getObject("ob_delcountry_multi")).deselectAll();
			for (String obData : obDeliveryCountries.split(";")) {
				new Select(getObject("ob_delcountry_multi")).selectByVisibleText(obData.trim());
				}
			}

		// Select DNO Currency
		if (!obCurrency.equalsIgnoreCase("")) {
			new Select(getObject("ob_currency_dropdown")).selectByVisibleText(obCurrency);
			}

		// Check Supplier Access
		if (obSupplierAccess.equalsIgnoreCase("Yes")) {
			if (getObject("ob_supplier_access_checkbox").isEnabled()) {
				if (!getObject("ob_supplier_access_checkbox").isSelected()) {
					getObject("ob_supplier_access_checkbox").click();
					}
				}
			}

		// Select DNO Tier
		if (!obTier.equalsIgnoreCase("")) {
			new Select(getObject("ob_tier_dropdown")).selectByVisibleText(obTier);}

				
		// Obligations - Create Page - OTHER INFORMATION
		// Select DNO Priority
		if (!obPriority.equalsIgnoreCase("")) {
			new Select(getObject("ob_priority_dropdown")).selectByVisibleText(obPriority);
      		}

		// Select DNO Reference, and Enter DNO Page Number and DNO Clause
		if (!obReferences.equalsIgnoreCase("")) {
			new Select(getObject("ob_reference_dropdown")).selectByVisibleText(obReferences);
			if (!obClause.equalsIgnoreCase("")) {
				getObject("ob_clause_textbox").clear();
				getObject("ob_clause_textbox").sendKeys(obClause);
				}
			if (!obPageNumber.equalsIgnoreCase("")) {
				getObject("ob_page_no_textbox").clear();
				getObject("ob_page_no_textbox").sendKeys(obPageNumber);
				}

			// Check for DNO Reference and DNO Page Number
			String obAttribute = getObject("ob_page_no_textbox").getAttribute("maxvalue");
			if (obAttribute.equalsIgnoreCase("UNINDEXED")) {
				String obError = getObject("ob_references_dropdown_errors").getText();

				Assert.assertEquals(obError, "Document is yet to be indexed. Please try attaching this document after sometime.");

				APP_LOGS.debug("DNO Reference Document is yet to be indexed. Please try attaching this document after sometime.");

				fail = false;
				
				}
			
			if (Integer.valueOf(obPageNumber) > Integer.valueOf(obAttribute)) {
				String obError = getObject("ob_page_number_numeric_box_errors").getText();
				System.out.println("slError: " + obError);

				// Assert.assertEquals(slError, "Document is yet to be indexed. Please try attaching this document after sometime.");

				APP_LOGS.debug("DNO Page Number has more value than Reference Document");

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

				// Select ob Start Date
				if (!obStartDate.equalsIgnoreCase("")) {
					driver.findElement(By.name("startDate")).click();
					String[] obDate = obStartDate.split("-");

					String obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
					while (!obMonth.equalsIgnoreCase(obDate[0])) {
						driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
						obMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
						}

					new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(obDate[2]);

					driver.findElement(By.linkText(obDate[1])).click();
					}

				// Select ob End Date
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
				/*if (!obPatternDate.equalsIgnoreCase("")) {
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
					}*/

		// Select DNO Functions and Services
		if (!obFunctions.equalsIgnoreCase("")) {
			new Select(getObject("ob_function_multi")).deselectAll();
			for (String obData : obFunctions.split(";")) {
				new Select(getObject("ob_function_multi")).selectByVisibleText(obData.trim());
				}
			if (!obServices.equalsIgnoreCase("")) {
				for (String obData : obServices.split(";")) {
					new Select(getObject("ob_services_multi")).selectByVisibleText(obData.trim());
					}
				}
			}

		// Select DNO Service Category
		if (!obServiceCategory.equalsIgnoreCase("")) {
			new Select(getObject("ob_services_category_multi")).deselectAll();
			for (String obData : obServiceCategory.split(";")) {
				new Select(getObject("ob_services_category_multi")).selectByVisibleText(obData.trim());
				}
			}

		// Select DNO Management Regions and Countries
		if (!obManagementRegions.equalsIgnoreCase("")) {
			new Select(getObject("ob_management_region_multi")).deselectAll();
			for (String obData : obManagementRegions.split(";")) {
				new Select(getObject("ob_management_region_multi")).selectByVisibleText(obData.trim());
				}
			if (!obManagementCountries.equalsIgnoreCase("")) {
				for (String obData : obManagementCountries.split(";")) {
					new Select(getObject("ob_management_countries_multi")).selectByVisibleText(obData.trim());
					}
				}
			}

		// Select DNO Contract Regions and Countries
		/*if(getObject("sl_contract_regions_multi_dropdown").isEnabled()) {
			new Select(getObject("sl_contract_regions_multi_dropdown")).deselectAll();
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
				
		/*Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Update')]")));
		driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();
		Thread.sleep(10000);*/

		if (obTitle.equalsIgnoreCase("")) {
			fail = true;

			String obAttribute = getObject("ob_title_textbox").getAttribute("class");
			String[] obAttributeSplit = obAttribute.split(" ");
			String obAttributeSplitString = obAttributeSplit[3];

			Assert.assertEquals(obAttributeSplitString, "errorClass");

			APP_LOGS.debug("DNO Title is blank");

			fail = false;

			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (obTitle.length() > 512) {
			fail = true;
			String obLengthError = getObject("ob_title_textbox_errors").getText();
			System.out.println("obLengthError " + obLengthError);

			Assert.assertEquals(obLengthError, "Maximum characters allowed is 512");

			APP_LOGS.debug("DNO Title has more than 512 Characters");

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

			APP_LOGS.debug("DNO Priority is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		if (obPageNumber.length() > 18) {
			fail = true;
			String obLengthError = getObject("ob_page_no_textbox").getText();
			System.out.println("obLengthError " + obLengthError);

			Assert.assertEquals(obLengthError, "Only integer values are allowed.");

			APP_LOGS.debug("DNO Page Number has more than 18 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));

			return;
			}

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Update')][@clientvalidation='true']")));
		driver.findElement(By.xpath("//button[contains(.,'Update')][@clientvalidation='true']")).click();
			Thread.sleep(5000);
			System.out.println("clicked on update");
		
		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
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
