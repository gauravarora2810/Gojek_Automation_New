package test.resources.com.sirion.suite.clause;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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

public class ClauseCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(clause_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(clause_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void ClauseCreationTest(String testCaseId, String clauseName, String clauseHeaderLabel, String clauseCategory, String clauseSupplier, String clauseType,
			String clauseContractingEntity, String clauseTermType, String clauseFunctions, String clauseServices, String clauseContent, String clauseRiskType,
			String clauseIndustryType, String clauseCompanyPosition, String clauseAgreementType, String clauseTransactionType, String clauseContractServices,
			String clauseTCVMin, String clauseTCVMax, String clauseRegions, String clauseCountries) throws InterruptedException, TestLinkAPIException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data set to NO " + count);
			}

		APP_LOGS.debug("Executing Clause Creation Test --- " + clauseName);

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(5000);
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(5000);
		
		getObject("cdr_quick_link").click();
		Thread.sleep(10000);

		new Actions(driver).moveToElement(driver.findElement(By.className("dropdown-toggle"))).clickAndHold().build().perform();
		
		driver.findElement(By.linkText("View Clause Library")).click();
		Thread.sleep(10000);

		getObject("clause_listing_page_plus_button").click();
		Thread.sleep(5000);
		
		if (!clauseName.equalsIgnoreCase("")) {
			DateFormat dateFormat = new SimpleDateFormat("MMMddyyyyHHmmss");
			Date date = new Date();
			String date1= dateFormat.format(date);
				
			System.out.println("Current date and time is " +date1);
			getObject("clause_create_page_name_textbox").clear();
			getObject("clause_create_page_name_textbox").sendKeys(clauseName);
			getObject("clause_create_page_name_textbox").sendKeys(date1);
			}
		
		if (!clauseHeaderLabel.equalsIgnoreCase("")) {
			getObject("clause_create_page_header_label_textbox").clear();
			getObject("clause_create_page_header_label_textbox").sendKeys(clauseHeaderLabel);
			}
		
		if (!clauseCategory.equalsIgnoreCase("")) {
			new Select(getObject("clause_create_page_clause_category_dropdown")).selectByVisibleText(clauseCategory);
			}
		
		if (!clauseSupplier.equalsIgnoreCase("")) {
			for (String clauseData : clauseSupplier.split(";")) {
				new Select(getObject("clause_create_page_suppliers_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			}

		if (!clauseType.equalsIgnoreCase("")) {
			new Select(getObject("clause_create_page_clause_type_dropdown")).selectByVisibleText(clauseType);
			}
		
		if (!clauseContractingEntity.equalsIgnoreCase("")) {
			new Select(getObject("clause_create_page_contracting_entity_dropdown")).selectByVisibleText(clauseContractingEntity);
			}
		
		if (!clauseTermType.equalsIgnoreCase("")) {
			new Select(getObject("clause_create_page_term_type_dropdown")).selectByVisibleText(clauseTermType);
			}

		if (!clauseFunctions.equalsIgnoreCase("")) {
			for (String clauseData : clauseFunctions.split(";")) {
				new Select(getObject("clause_create_page_functions_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			if (!clauseServices.equalsIgnoreCase("")) {
				for (String clauseData : clauseServices.split(";")) {
					new Select(getObject("clause_create_page_services_multi_dropdown")).selectByVisibleText(clauseData.trim());
					}
				}
			}

		if (!clauseRiskType.equalsIgnoreCase("")) {
			for (String clauseData : clauseRiskType.split(";")) {
				new Select(getObject("clause_create_page_risk_types_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			}
		
		if (!clauseIndustryType.equalsIgnoreCase("")) {
			for (String clauseData : clauseIndustryType.split(";")) {
				new Select(getObject("clause_create_page_industry_types_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			}
		
		if (!clauseCompanyPosition.equalsIgnoreCase("")) {
			new Select(getObject("clause_create_page_company_position_dropdown")).selectByVisibleText(clauseCompanyPosition);
			}

		if (!clauseAgreementType.equalsIgnoreCase("")) {
			for (String clauseData : clauseAgreementType.split(";")) {
				new Select(getObject("clause_create_page_agreement_types_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			}
		
		if (!clauseTransactionType.equalsIgnoreCase("")) {
			new Select(getObject("clause_create_page_clause_transaction_type_dropdown")).selectByVisibleText(clauseTransactionType);
			}
		
		if (!clauseContractServices.equalsIgnoreCase("")) {
			for (String clauseData : clauseContractServices.split(";")) {
				new Select(getObject("clause_create_page_contract_services_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			}

		if (!clauseTCVMin.equalsIgnoreCase("")) {
		    Double temp_clauseTCVMin_double = Double.parseDouble(clauseTCVMin);
		    int temp_clauseTCVMin_int = temp_clauseTCVMin_double.intValue();
		    String temp_clauseTCVMin_string = Integer.toString(temp_clauseTCVMin_int);

			getObject("clause_create_page_tcv_min_numeric_textbox").clear();
			getObject("clause_create_page_tcv_min_numeric_textbox").sendKeys(temp_clauseTCVMin_string);
			}

		if (!clauseTCVMax.equalsIgnoreCase("")) {
		    Double temp_clauseTCVMax_double = Double.parseDouble(clauseTCVMax);
		    int temp_clauseTCVMax_int = temp_clauseTCVMax_double.intValue();
		    String temp_clauseTCVMax_string = Integer.toString(temp_clauseTCVMax_int);

			getObject("clause_create_page_tcv_max_numeric_textbox").clear();
			getObject("clause_create_page_tcv_max_numeric_textbox").sendKeys(temp_clauseTCVMax_string);
			}

		if (!clauseRegions.equalsIgnoreCase("")) {
			for (String clauseData : clauseRegions.split(";")) {
				new Select(getObject("clause_create_page_regions_multi_dropdown")).selectByVisibleText(clauseData.trim());
				}
			if (!clauseCountries.equalsIgnoreCase("")) {
				for (String clauseData : clauseCountries.split(";")) {
					new Select(getObject("clause_create_page_countries_multi_dropdown")).selectByVisibleText(clauseData.trim());
					}
				}
			}
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Clause')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Clause')]")).click();
		Thread.sleep(5000);

		if (clauseName.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_name_textbox").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Name is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (clauseName.length() > 512) {
			fail = true;
			String clauseLengthError = getObject("clause_create_page_name_textbox_max_length_error").getText();

			Assert.assertEquals(clauseLengthError, "Maximum characters allowed is 512");

			APP_LOGS.debug("Clause Name has more than 512 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (clauseHeaderLabel.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_header_label_textbox").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Header Label is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (clauseHeaderLabel.length() > 512) {
			fail = true;
			String clauseLengthError = getObject("clause_create_page_header_label_textbox_max_length_error").getText();
			System.out.println("clauseLengthError " + clauseLengthError);

			Assert.assertEquals(clauseLengthError, "Maximum characters allowed is 512");

			APP_LOGS.debug("Clause Header Label has more than 512 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (clauseCategory.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_clause_category_dropdown").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Category is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (clauseType.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_clause_type_dropdown").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Type is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (clauseFunctions.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_functions_multi_dropdown").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Functions is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (clauseServices.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_services_multi_dropdown").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Services is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (clauseRegions.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_regions_multi_dropdown").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Regions is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (clauseCountries.equalsIgnoreCase("")) {
			fail = true;

			String clauseAttribute = getObject("clause_create_page_countries_multi_dropdown").getAttribute("class");
			String[] clauseAttributeSplit = clauseAttribute.split(" ");
			String clauseAttributeSplitString = clauseAttributeSplit[3];

			Assert.assertEquals(clauseAttributeSplitString, "errorClass");

			APP_LOGS.debug("Clause Countries is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (getObject("clause_create_page_clause_id_popup_link") != null) {
			String clauseId = getObject("clause_create_page_clause_id_popup_link").getText();
			APP_LOGS.debug("Clause created successfully with Clause id "+clauseId);

			getObject("clause_create_page_clause_id_popup_link").click();
			Thread.sleep(5000);
			
			driver.findElement(By.linkText("GENERAL")).click();

			String clauseShowPage = getObject("clause_show_page_id").getText();

			Assert.assertEquals(clauseShowPage, clauseId);
			APP_LOGS.debug("Clause ID on show page has been verified");
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
			TestUtil.reportDataSetResult(clause_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(clause_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(clause_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
		
		skip = false;
		fail = true;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(clause_suite_xls, "Test Cases", TestUtil.getRowNum(clause_suite_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(clause_suite_xls, "Test Cases", TestUtil.getRowNum(clause_suite_xls, this.getClass().getSimpleName()), "FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(clause_suite_xls, this.getClass().getSimpleName());
		}
	}
