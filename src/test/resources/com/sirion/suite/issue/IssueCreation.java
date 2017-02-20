package test.resources.com.sirion.suite.issue;

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

public class IssueCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(issue_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(issue_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void IssueCreationTest(String testCaseId, String issueTitle, String issueDescription, String issueType, String issuePriority, String issueCurrency,
			String issueFinancialValue, String issueDeliveryCountries, String issueTimezone, String issueRestrictPublicAccess, String issueRestrictRequesterAccess,
			String issueSupplierAccess, String issueTier, String issueDependentEntity, String issueGBMeeting, String issueIssueDate, String issuePlannedDate,
			String issueFunctions, String issueServices, String issueServiceCategory, String issueManagementRegions, String issueManagementCountries,
			String issueContractRegions, String issueContractCountries, String issueResponsibility, String issueSupplier, String issueSourceType, String issueSourceName)
					throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}

		APP_LOGS.debug("Executing Test Case Issue Creation with Title ---- "+issueTitle);

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
		getObject("issues_quick_link").click();
		Thread.sleep(20000);

		getObject("issues_listing_page_plus_button").click();
		Thread.sleep(10000);

		if(!issueSupplier.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_supplier_dropdown")).selectByVisibleText(issueSupplier);
			}
		
		if(!issueSourceType.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_source_type_dropdown")).selectByVisibleText(issueSourceType);
			if(!issueSourceType.equalsIgnoreCase("Supplier")) {
				if(!issueSourceName.equalsIgnoreCase("")) {
				new Select(getObject("issues_create_page_source_name_dropdown")).selectByVisibleText(issueSourceName);
				}
			}
		}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Submit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		Thread.sleep(10000);

		// Issue - Create Page - BASIC INFORMATION
		if (!issueTitle.equalsIgnoreCase("")) {
			getObject("issues_create_page_title_textbox").clear();
			getObject("issues_create_page_title_textbox").sendKeys(issueTitle);
			}

		if (!issueDescription.equalsIgnoreCase("")) {
			getObject("issues_create_page_description_textarea").clear();
			getObject("issues_create_page_description_textarea").sendKeys(issueDescription);
			}
		
		if(!issueType.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_issue_type_dropdown")).selectByVisibleText(issueType);
			}
		
		if(!issuePriority.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_priority_dropdown")).selectByVisibleText(issuePriority);
			}

		if(!issueCurrency.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_currency_dropdown")).selectByVisibleText(issueCurrency);
			}
		
		if (!issueFinancialValue.equalsIgnoreCase("")) {
			Double temp_issueFinancialValue_double = Double.parseDouble(issueFinancialValue);
			int temp_issueFinancialValue_int = temp_issueFinancialValue_double.intValue();
			String temp_issueFinancialValue_string = Integer.toString(temp_issueFinancialValue_int);
			
			getObject("issues_create_page_financial_impact_numeric_box").clear();
			getObject("issues_create_page_financial_impact_numeric_box").sendKeys(temp_issueFinancialValue_string);
			}
		
		if (!issueDeliveryCountries.equalsIgnoreCase("")) {
			for (String issueData : issueDeliveryCountries.split(";")) {
				new Select(getObject("issues_create_page_delivery_countries_multi_dropdown")).selectByVisibleText(issueData.trim());
				}
			}
		
		if(!issueTimezone.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_timezone_dropdown")).selectByVisibleText(issueTimezone);
			try {
	      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
	      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	      	} catch (Exception e) {
	      		
	      	}
			}

		if(issueRestrictPublicAccess.equalsIgnoreCase("Yes")) {
			if(getObject("issues_create_page_restrict_public_access_checkbox").isEnabled()) {
				if(getObject("issues_create_page_restrict_public_access_checkbox").isSelected()) {
					getObject("issues_create_page_restrict_public_access_checkbox").click();
					}
				}
			}

		if(issueRestrictRequesterAccess.equalsIgnoreCase("Yes")) {
			if(getObject("issues_create_page_restrict_requester_access_checkbox").isEnabled()) {
				if(getObject("issues_create_page_restrict_requester_access_checkbox").isSelected()) {
					getObject("issues_create_page_restrict_requester_access_checkbox").click();
					}
				}
			}

		if(issueSupplierAccess.equalsIgnoreCase("Yes")) {
			if(getObject("issues_create_page_supplier_access_checkbox").isEnabled()) {
				if(getObject("issues_create_page_supplier_access_checkbox").isSelected()) {
					getObject("issues_create_page_supplier_access_checkbox").click();
					}
				}
			}

		if(!issueTier.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_tier_dropdown")).selectByVisibleText(issueTier);
			}
		
		if(issueDependentEntity.equalsIgnoreCase("Yes")) {
			if(getObject("issues_create_page_dependent_entity_checkbox").isEnabled()) {
				if(getObject("issues_create_page_dependent_entity_checkbox").isSelected()) {
					getObject("issues_create_page_dependent_entity_checkbox").click();
					}
				}
			}

		if(!issueGBMeeting.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_gb_meeting_dropdown")).selectByVisibleText(issueGBMeeting);
			}

		if (!issueIssueDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_118")).click();
			String[] issueDate = issueIssueDate.split("-");

			String issueMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!issueMonth.equalsIgnoreCase(issueDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				issueMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(issueDate[2]);

			driver.findElement(By.linkText(issueDate[1])).click();
			}

		if (!issuePlannedDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_119")).click();
			String[] issueDate = issuePlannedDate.split("-");

			String issueMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!issueMonth.equalsIgnoreCase(issueDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				issueMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(issueDate[2]);

			driver.findElement(By.linkText(issueDate[1])).click();
			}

		// Issue - Create Page - FUNCTIONS
		if (!issueFunctions.equalsIgnoreCase("")) {
			for (String issueData : issueFunctions.split(";")) {
				new Select(getObject("issues_create_page_functions_multi_dropdown")).selectByVisibleText(issueData.trim());
				}
			if (!issueServices.equalsIgnoreCase("")) {
				for (String issueData : issueServices.split(";")) {
					new Select(getObject("issues_create_page_services_multi_dropdown")).selectByVisibleText(issueData.trim());
					}
				}
			}

		if (!issueServiceCategory.equalsIgnoreCase("")) {
			for (String issueData : issueServiceCategory.split(";")) {
				new Select(getObject("issues_create_page_service_category_multi_dropdown")).selectByVisibleText(issueData.trim());
				}
			}
		
		// Issue - Create Page - GEOGRAPHY
		if (!issueManagementRegions.equalsIgnoreCase("")) {
			for (String issueData : issueManagementRegions.split(";")) {
				new Select(getObject("issues_create_page_management_regions_multi_dropdown")).selectByVisibleText(issueData.trim());
				}
			if (!issueManagementCountries.equalsIgnoreCase("")) {
				for (String issueData : issueManagementCountries.split(";")) {
					new Select(getObject("issues_create_page_management_countries_multi_dropdown")).selectByVisibleText(issueData.trim());
					}
				}
			}
		
		if(!issueResponsibility.equalsIgnoreCase("")) {
			new Select(getObject("issues_create_page_responsibility_dropdown")).selectByVisibleText(issueResponsibility);
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Issue')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Issue')]")).click();
		Thread.sleep(10000);

		if (issueTitle.equalsIgnoreCase("")) {
			fail = true;

			String issueAttribute = getObject("issues_create_page_title_textbox").getAttribute("class");
			String[] issueAttributeSplit = issueAttribute.split(" ");
			String issueAttributeSplitString = issueAttributeSplit[3];

			Assert.assertEquals(issueAttributeSplitString, "errorClass");

			APP_LOGS.debug("Issue Title is Mandatory");

			fail = false;
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
		
		if (getObject("issues_create_page_issue_id_popup_link") != null) {
			String issueId = getObject("issues_create_page_issue_id_popup_link").getText();
			APP_LOGS.debug("Issue created successfully with Issue ID "+issueId);

			getObject("issues_create_page_issue_id_popup_link").click();
			Thread.sleep(10000);
			String issueShowPageId = getObject("issues_show_page_issue_id").getText();

			Assert.assertEquals(issueShowPageId, issueId);
			APP_LOGS.debug("Issue ID on show page has been verified");
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
			TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(issue_suite_xls, this.getClass().getSimpleName());
		}
	}
