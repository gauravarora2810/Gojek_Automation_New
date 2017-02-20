package test.resources.com.sirion.suite.contractTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class ContractTemplateCreation extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(contract_template_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case"	+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(contract_template_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider = "getTestData")
	public void ContractTemplateCreationTest(String templateName, String templateTransactionType, String templateSuppliers, String templateCategory,
			String templateContractingEntity, String templateTermType, String templateUploadTemplate, String templateFunctions, String templateServices,
			String templateRegions, String templateCountries, String templateIndustryTypes, String templateAgreementTypes, String templateContractServices,
			String templateTCVMin, String templateTCVMax, String templateRiskTypes) throws InterruptedException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data set to NO " + count);
			}

		APP_LOGS.debug("Executing Contract Template Creation Test --- " + templateName);

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);
		
		getObject("cdr_quick_link").click();
		Thread.sleep(10000);
		
		new Actions(driver).moveToElement(driver.findElement(By.className("dropdown-toggle"))).clickAndHold().build().perform();
		
		driver.findElement(By.linkText("View Contract Templates")).click();
		Thread.sleep(10000);

		getObject("contract_template_listing_page_plus_icon").click();
		Thread.sleep(10000);

		if (!templateName.equalsIgnoreCase("")) {
			DateFormat dateFormat = new SimpleDateFormat("MMMddyyyyHHmmss");
			Date date = new Date();
			String date1= dateFormat.format(date);

			getObject("contract_template_create_page_name_textbox").clear();
			getObject("contract_template_create_page_name_textbox").sendKeys(templateName);
			getObject("contract_template_create_page_name_textbox").sendKeys(date1);
			}
		
		if (!templateTransactionType.equalsIgnoreCase("")) {
			new Select(getObject("contract_template_create_page_transaction_type_dropdown")).selectByVisibleText(templateTransactionType);
			}
		
		if (!templateSuppliers.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateSuppliers.split(";")) {
				new Select(getObject("contract_template_create_page_suppliers_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			}

		if (!templateCategory.equalsIgnoreCase("")) {
			new Select(getObject("contract_template_create_page_category_dropdown")).selectByVisibleText(templateCategory);
			}		
		
		if (!templateContractingEntity.equalsIgnoreCase("")) {
			new Select(getObject("contract_template_create_page_contracting_entity_dropdown")).selectByVisibleText(templateContractingEntity);
			}		
		
		if (!templateTermType.equalsIgnoreCase("")) {
			new Select(getObject("contract_template_create_page_term_type_dropdown")).selectByVisibleText(templateTermType);
			}		

		if (!templateFunctions.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateFunctions.split(";")) {
				new Select(getObject("contract_template_create_page_functions_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			if (!templateServices.equalsIgnoreCase("")) {
				for (String contractTemplateData : templateServices.split(";")) {
					new Select(getObject("contract_template_create_page_services_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
					}
				}
			}

		if (!templateRegions.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateRegions.split(";")) {
				new Select(getObject("contract_template_create_page_regions_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			if (!templateCountries.equalsIgnoreCase("")) {
				for (String contractTemplateData : templateCountries.split(";")) {
					new Select(getObject("contract_template_create_page_countries_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
					}
				}
			}
		
		if (!templateIndustryTypes.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateIndustryTypes.split(";")) {
				new Select(getObject("contract_template_create_page_industry_types_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			}

		if (!templateAgreementTypes.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateAgreementTypes.split(";")) {
				new Select(getObject("contract_template_create_page_agreement_types_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			}

		if (!templateContractServices.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateContractServices.split(";")) {
				new Select(getObject("contract_template_create_page_contract_services_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			}
		
		if (!templateTCVMin.equalsIgnoreCase("")) {
		    Double temp_template_double = Double.parseDouble(templateTCVMin);
		    int temp_template_int = temp_template_double.intValue();
		    String temp_template_string = Integer.toString(temp_template_int);

			getObject("contract_template_create_page_tcv_min_numeric_textbox").clear();
			getObject("contract_template_create_page_tcv_min_numeric_textbox").sendKeys(temp_template_string);
			}

		if (!templateTCVMax.equalsIgnoreCase("")) {
		    Double temp_template_double = Double.parseDouble(templateTCVMax);
		    int temp_template_int = temp_template_double.intValue();
		    String temp_template_string = Integer.toString(temp_template_int);

			getObject("contract_template_create_page_tcv_max_numeric_textbox").clear();
			getObject("contract_template_create_page_tcv_max_numeric_textbox").sendKeys(temp_template_string);
			}

		if (!templateRiskTypes.equalsIgnoreCase("")) {
			for (String contractTemplateData : templateRiskTypes.split(";")) {
				new Select(getObject("contract_template_create_page_risk_types_multi_dropdown")).selectByVisibleText(contractTemplateData.trim());
				}
			}		
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save Template')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save Template')]")).click();
		Thread.sleep(10000);

		if (templateName.equalsIgnoreCase("")) {
			fail = true;

			String contractTemplateAttribute = getObject("contract_template_create_page_name_textbox").getAttribute("class");
			String[] contractTemplateAttributeSplit = contractTemplateAttribute.split(" ");
			String contractTemplateAttributeSplitString = contractTemplateAttributeSplit[3];

			Assert.assertEquals(contractTemplateAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Template Name is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (templateName.length() > 512) {
			fail = true;
			String contractTemplateLengthError = getObject("contract_template_create_page_name_textbox_max_length_error").getText();

			Assert.assertEquals(contractTemplateLengthError, "Maximum characters allowed is 512");

			APP_LOGS.debug("Contract Template Name has more than 512 Characters");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (templateFunctions.equalsIgnoreCase("")) {
			fail = true;

			String contractTemplateAttribute = getObject("contract_template_create_page_functions_multi_dropdown").getAttribute("class");
			String[] contractTemplateAttributeSplit = contractTemplateAttribute.split(" ");
			String contractTemplateAttributeSplitString = contractTemplateAttributeSplit[3];

			Assert.assertEquals(contractTemplateAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Template Function is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (templateServices.equalsIgnoreCase("")) {
			fail = true;

			String contractTemplateAttribute = getObject("contract_template_create_page_services_multi_dropdown").getAttribute("class");
			String[] contractTemplateAttributeSplit = contractTemplateAttribute.split(" ");
			String contractTemplateAttributeSplitString = contractTemplateAttributeSplit[3];

			Assert.assertEquals(contractTemplateAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Template Service is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (templateRegions.equalsIgnoreCase("")) {
			fail = true;

			String contractTemplateAttribute = getObject("contract_template_create_page_regions_multi_dropdown").getAttribute("class");
			String[] contractTemplateAttributeSplit = contractTemplateAttribute.split(" ");
			String contractTemplateAttributeSplitString = contractTemplateAttributeSplit[3];

			Assert.assertEquals(contractTemplateAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Template Region is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (templateCountries.equalsIgnoreCase("")) {
			fail = true;

			String contractTemplateAttribute = getObject("contract_template_create_page_countries_multi_dropdown").getAttribute("class");
			String[] contractTemplateAttributeSplit = contractTemplateAttribute.split(" ");
			String contractTemplateAttributeSplitString = contractTemplateAttributeSplit[3];

			Assert.assertEquals(contractTemplateAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Template Countries is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (getObject("contract_template_contract_template_id_popup_link") != null) {
			String templateId = getObject("contract_template_contract_template_id_popup_link").getText();
			APP_LOGS.debug("Contract Template created successfully with Contract Template id "+templateId);

			getObject("contract_template_contract_template_id_popup_link").click();
			Thread.sleep(10000);
			
			driver.findElement(By.linkText("GENERAL")).click();

			String templateShowPageId = getObject("contract_template_show_page_id").getText();

			Assert.assertEquals(templateShowPageId, templateId);
			APP_LOGS.debug("Contract Template ID on show page has been verified");
			}
		
		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(contract_template_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(contract_template_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
			} else
				TestUtil.reportDataSetResult(contract_template_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
		
		skip = false;
		fail = true;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(contract_template_suite_xls, "Test Cases", TestUtil.getRowNum(contract_template_suite_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(contract_template_suite_xls, "Test Cases", TestUtil.getRowNum(contract_template_suite_xls, this.getClass().getSimpleName()), "FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(contract_template_suite_xls, this.getClass().getSimpleName());
		}
	}
