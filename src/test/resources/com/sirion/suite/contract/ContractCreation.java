package test.resources.com.sirion.suite.contract;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ContractCreation extends TestSuiteBase {
	String result = null;
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	JavascriptExecutor executor;

	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case "+ this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
		}
	
  @Test(dataProvider = "getTestData")
  public void ContractCreationTest(String coName, String coTitle, String coAgreementNo, String coContractNo, String coBrief, String coTimezone, String coContractingEntity,
		  String coGovernanceBodies, String coDeliveryCountries, String coTier, String coSupplierAccess, String coTermType, String coAribaCWId, String coContractType,
		  String coCommentField, String coNoOfRenewals, String coContractPaper, String coVendorClassification,
		  String coEffectiveDate, String coExpirationDate, String coEffectiveDateOriginal, String coExpirationDateOriginal, String coExpirationNoticePeriod,
		  String coNoticeDate, String coNoticeLeadDays, String coNoticeLeadDate,
		  String coFunctions, String coServices, String coManagementRegions, String coManagementCountries,
		  String coRegionCountry, String coContractRegions, String coContractCountries,
		  String coCurrencies, String coReportingCurrency, String coConversionType, String coConversionMatrix, String coConversionMatrixFrom, String coConversionMatrixTo,
		  String coAdditionalTCV, String coAdditionalACV, String coAdditionalFACV,
		  String coDocumentName, String coViewer, String coSearch, String coDownload, String coFinancial, String coLegal,
		  String coDocumentType, String coParent, String coParentType) throws InterruptedException, MalformedURLException {

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for Test Data Set to NO -- "+ count);
			}
		APP_LOGS.debug("Executing Test Case Contract Creation with Title ---- "+ coTitle);
    
		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		// Click on Analytics
		wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("analytics_link")));
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(10000);
		
		if(coParentType.equalsIgnoreCase("Supplier")) {
			getObject("supplier_quick_link").click();
			Thread.sleep(10000);
		
			new Select(getObject("supplier_listing_page_display_entries_dropdown")).selectByIndex(4);
			Thread.sleep(10000);
			
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ coParent +"')]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);

			new Actions(driver).moveToElement(getObject("supplier_show_page_plus_button")).click().build().perform();
			}

		else if (coParentType.equalsIgnoreCase("Contract")) {
			getObject("contract_quick_link").click();
			Thread.sleep(10000);
		
			new Select(getObject("co_listing_page_display_entries_dropdown")).selectByIndex(4);
			Thread.sleep(2000);
		
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ coParent +"')]/preceding-sibling::td[1]/a")).click();
			Thread.sleep(20000);

			new Actions(driver).moveToElement(getObject("co_show_page_plus_button")).click().build().perform();
			}

		driver.findElement(By.linkText(coDocumentType)).click();
		Thread.sleep(5000);

		if (!coName.equalsIgnoreCase("")) {
			getObject("co_create_page_name_textbox").clear();
			getObject("co_create_page_name_textbox").sendKeys(coName);
			}
		
		if (!coTitle.equalsIgnoreCase("")) {
			getObject("co_create_page_title_textbox").clear();
			getObject("co_create_page_title_textbox").sendKeys(coTitle);
			}
		
		if (!coAgreementNo.equalsIgnoreCase("")) {
			getObject("co_create_page_agreement_no_textbox").clear();
			getObject("co_create_page_agreement_no_textbox").sendKeys(coAgreementNo);
			}
		
		if (!coContractNo.equalsIgnoreCase("")) {
			getObject("co_create_page_contract_no_textbox").clear();
			getObject("co_create_page_contract_no_textbox").sendKeys(coContractNo);
			
			DateFormat dateFormat = new SimpleDateFormat("MMMddyyyyHHmmss");
			Date date = new Date();
			String date1= dateFormat.format(date);
			
			System.out.println("Current date and time is " +date1);
			getObject("co_create_page_contract_no_textbox").sendKeys(date1);
			}
		
	/*	if (!coBrief.equalsIgnoreCase("")) {
			getObject("co_create_page_brief_textarea").clear();
			getObject("co_create_page_brief_textarea").sendKeys(coBrief);
			}*/
		System.out.println("On time zone");
		if (!coTimezone.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_timezone_dropdown")).selectByVisibleText(coTimezone);
			try {
	      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
	      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	      	} catch (Exception e) {
	      		
	      	}
			}
		
		if (!coContractingEntity.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_contracting_entity_dropdown")).selectByVisibleText(coContractingEntity);
			}
		
		if (!coGovernanceBodies.equalsIgnoreCase("")) {
			for (String coData : coGovernanceBodies.split(";")) {
				new Select(getObject("co_create_page_delivery_countries_multi_dropdown")).selectByVisibleText(coData.trim());
				}
			}	

		if (!coDeliveryCountries.equalsIgnoreCase("")) {
			for (String coData : coDeliveryCountries.split(";")) {
				new Select(getObject("co_create_page_delivery_countries_multi_dropdown")).selectByVisibleText(coData.trim());
				}
			}		

		if (!coTier.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_tier_dropdown")).selectByVisibleText(coTier);
			}

		if (!coTermType.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_term_type_dropdown")).selectByVisibleText(coTermType);
			}

		if (!coAribaCWId.equalsIgnoreCase("")) {
			getObject("co_create_page_ariba_cw_id_textbox").clear();
			getObject("co_create_page_ariba_cw_id_textbox").sendKeys(coAribaCWId);
			}

		if (!coContractType.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_contract_type_dropdown")).selectByVisibleText(coContractType);
			}

		if (!coCommentField.equalsIgnoreCase("")) {
			getObject("co_create_page_comment_field_textarea").clear();
			getObject("co_create_page_comment_field_textarea").sendKeys(coCommentField);
			}

		if (!coNoOfRenewals.equalsIgnoreCase("")) {
			Double temp_coNoOfRenewals_double = Double.parseDouble(coNoOfRenewals);
			int temp_coNoOfRenewals_int = temp_coNoOfRenewals_double.intValue();
			String temp_coNoOfRenewals_string = Integer.toString(temp_coNoOfRenewals_int);
			
			getObject("co_create_page_no_of_renewals_numeric_box").clear();
			getObject("co_create_page_no_of_renewals_numeric_box").sendKeys(temp_coNoOfRenewals_string);
			}

		if (!coContractPaper.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_contract_paper_dropdown")).selectByVisibleText(coContractPaper);
			}

		if (!coVendorClassification.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_vendor_classification_dropdown")).selectByVisibleText(coVendorClassification);
			}	
		
		if (!coEffectiveDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("effectiveDate")).click();
			String[] coDate = coEffectiveDate.split("-");

			String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!coMonth.equalsIgnoreCase(coDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

			driver.findElement(By.linkText(coDate[1])).click();
			}

		if (!coExpirationDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("expirationDate")).click();
			String[] coDate = coExpirationDate.split("-");

			String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!coMonth.equalsIgnoreCase(coDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

			driver.findElement(By.linkText(coDate[1])).click();
			}
/*		
		if (!coEffectiveDateOriginal.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_7011")).click();
			String[] coDate = coEffectiveDateOriginal.split("-");

			String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!coMonth.equalsIgnoreCase(coDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

			driver.findElement(By.linkText(coDate[1])).click();
			}

		if (!coExpirationDateOriginal.equalsIgnoreCase("")) {
			driver.findElement(By.name("expirationDateOriginal")).click();
			String[] coDate = coExpirationDateOriginal.split("-");

			String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!coMonth.equalsIgnoreCase(coDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

			driver.findElement(By.linkText(coDate[1])).click();
			}

		if (!coExpirationNoticePeriod.equalsIgnoreCase("")) {
			Double temp_coExpirationNoticePeriod_double = Double.parseDouble(coExpirationNoticePeriod);
			int temp_coExpirationNoticePeriod_int = temp_coExpirationNoticePeriod_double.intValue();
			String temp_coExpirationNoticePeriod_string = Integer.toString(temp_coExpirationNoticePeriod_int);
			
			getObject("co_create_page_expiration_notice_period_numeric_box").clear();
			getObject("co_create_page_expiration_notice_period_numeric_box").sendKeys(temp_coExpirationNoticePeriod_string);
			}		

		if (!coNoticeDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("noticeDate")).click();
			String[] coDate = coNoticeDate.split("-");

			String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!coMonth.equalsIgnoreCase(coDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

			driver.findElement(By.linkText(coDate[1])).click();
			}
		
		if (!coNoticeLeadDays.equalsIgnoreCase("")) {
			Double temp_coNoticeLeadDays_double = Double.parseDouble(coNoticeLeadDays);
			int temp_coNoticeLeadDays_int = temp_coNoticeLeadDays_double.intValue();
			String temp_coNoticeLeadDays_string = Integer.toString(temp_coNoticeLeadDays_int);
			
			getObject("co_create_page_notice_lead_numeric_box").clear();
			getObject("co_create_page_notice_lead_numeric_box").sendKeys(temp_coNoticeLeadDays_string);
			}		

		if (!coNoticeLeadDate.equalsIgnoreCase("")) {
			driver.findElement(By.name("noticeLeadDate")).click();
			String[] coDate = coNoticeLeadDate.split("-");

			String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!coMonth.equalsIgnoreCase(coDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

			driver.findElement(By.linkText(coDate[1])).click();
			}
*/		
		if (!coFunctions.equalsIgnoreCase("")) {
			for (String coData : coFunctions.split(";")) {
				new Select(getObject("co_create_page_functions_multi_dropdown")).selectByVisibleText(coData.trim());
				}
			if (!coServices.equalsIgnoreCase("")) {
				for (String coData : coServices.split(";")) {
					new Select(getObject("co_create_page_services_multi_dropdown")).selectByVisibleText(coData.trim());
					}
				}
			}		

		if (!coManagementRegions.equalsIgnoreCase("")) {
			for (String coData : coManagementRegions.split(";")) {
				new Select(getObject("co_create_page_management_regions_multi_dropdown")).selectByVisibleText(coData.trim());
				}
			if (!coManagementCountries.equalsIgnoreCase("")) {
				for (String coData : coManagementCountries.split(";")) {
					new Select(getObject("co_create_page_management_countries_multi_dropdown")).selectByVisibleText(coData.trim());
					}
				}
			}
		
		if (driver.findElements(By.id("elem_25")).size()>0) {
			if(!coRegionCountry.equalsIgnoreCase("")) {
				new Select(getObject("co_create_page_region_country_dropdown")).selectByVisibleText(coRegionCountry);
			if (!coContractRegions.equalsIgnoreCase("")) {
				for (String coData : coContractRegions.split(";")) {
					new Select(getObject("co_create_page_contract_regions_multi_dropdown")).selectByVisibleText(coData.trim());
					}
				if (!coContractCountries.equalsIgnoreCase("")) {
					for (String coData : coContractCountries.split(";")) {
						new Select(getObject("co_create_page_contract_countries_multi_dropdown")).selectByVisibleText(coData.trim());
						}
					}
				}
			}
		}
		
		if (!coCurrencies.equalsIgnoreCase("")) {
			for (String coData : coCurrencies.split(";")) {
				new Select(getObject("co_create_page_currencies_multi_dropdown")).selectByVisibleText(coData.trim());
				}

			if(!coReportingCurrency.equalsIgnoreCase("")) {
				new Select(getObject("co_create_page_reporting_currencies_dropdown")).selectByVisibleText(coReportingCurrency);
				}
			}

		if(!coConversionType.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_conversion_type_dropdown")).selectByVisibleText(coConversionType);
			}

		if(!coConversionMatrix.equalsIgnoreCase("")) {
			new Select(getObject("co_create_page_conversion_matrix_dropdown")).selectByVisibleText(coConversionMatrix);
			
			if (!coConversionMatrixFrom.equalsIgnoreCase("")) {
				driver.findElement(By.name("rateCardFromDate")).click();
				String[] coDate = coConversionMatrixFrom.split("-");

				String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				while (!coMonth.equalsIgnoreCase(coDate[0])) {
					driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
					coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
					}
				
				new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

				driver.findElement(By.linkText(coDate[1])).click();
				}

			if (!coConversionMatrixTo.equalsIgnoreCase("")) {
				driver.findElement(By.name("rateCardToDate")).click();
				String[] coDate = coConversionMatrixTo.split("-");

				String coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				while (!coMonth.equalsIgnoreCase(coDate[0])) {
					driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
					coMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
					}
				
				new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(coDate[2]);

				driver.findElement(By.linkText(coDate[1])).click();
				}
			}
		
		if (!coAdditionalTCV.equalsIgnoreCase("")) {
			Double temp_coAdditionalTCV_double = Double.parseDouble(coAdditionalTCV);
			int temp_coAdditionalTCV_int = temp_coAdditionalTCV_double.intValue();
			String temp_coAdditionalTCV_string = Integer.toString(temp_coAdditionalTCV_int);
			
			getObject("co_create_page_additional_tcv_numeric_box").clear();
			getObject("co_create_page_additional_tcv_numeric_box").sendKeys(temp_coAdditionalTCV_string);
			}
		
		if (!coAdditionalACV.equalsIgnoreCase("")) {
			Double temp_coAdditionalACV_double = Double.parseDouble(coAdditionalACV);
			int temp_coAdditionalACV_int = temp_coAdditionalACV_double.intValue();
			String temp_coAdditionalACV_string = Integer.toString(temp_coAdditionalACV_int);
			
			getObject("co_create_page_additional_acv_numeric_box").clear();
			getObject("co_create_page_additional_acv_numeric_box").sendKeys(temp_coAdditionalACV_string);
			}

		if (!coAdditionalFACV.equalsIgnoreCase("")) {
			Double temp_coAdditionalFACV_double = Double.parseDouble(coAdditionalFACV);
			int temp_coAdditionalFACV_int = temp_coAdditionalFACV_double.intValue();
			String temp_coAdditionalFACV_string = Integer.toString(temp_coAdditionalFACV_int);
			
			getObject("co_create_page_additional_facv_numeric_box").clear();
			getObject("co_create_page_additional_facv_numeric_box").sendKeys(temp_coAdditionalFACV_string);
			}

		getObject("co_create_page_save_button").click();
		Thread.sleep(10000);

		if (coName.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_name_textbox").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Name is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coTitle.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_title_textbox").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Title is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (coTimezone.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_timezone_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Timezone is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coContractingEntity.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_contracting_entity_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Contracting Entity is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coDeliveryCountries.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_delivery_countries_multi_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Delivery Countries is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coEffectiveDate.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = driver.findElement(By.className("effectiveDate")).getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[4];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Effective Date is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coExpirationDate.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = driver.findElement(By.className("expirationDate")).getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[4];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Expiration Date is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (driver.findElements(By.className("regionCountryStructure")).size()>0) {
			if (coRegionCountry.equalsIgnoreCase("")) {
				fail = true;

				String coAttribute = getObject("co_create_page_region_country_dropdown").getAttribute("class");
				String[] coAttributeSplit = coAttribute.split(" ");
				String coAttributeSplitString = coAttributeSplit[3];

				Assert.assertEquals(coAttributeSplitString, "errorClass");

				APP_LOGS.debug("Contract Region Country Structure is blank");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));
				return;
				}
			
			if (coContractRegions.equalsIgnoreCase("")) {
				fail = true;

				String coAttribute = getObject("co_create_page_contract_regions_multi_dropdown").getAttribute("class");
				String[] coAttributeSplit = coAttribute.split(" ");
				String coAttributeSplitString = coAttributeSplit[3];

				Assert.assertEquals(coAttributeSplitString, "errorClass");

				APP_LOGS.debug("Contract Contract Regions is blank");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));
				return;
				}
			
			if (coContractCountries.equalsIgnoreCase("")) {
				fail = true;

				String coAttribute = getObject("co_create_page_contract_countries_multi_dropdown").getAttribute("class");
				String[] coAttributeSplit = coAttribute.split(" ");
				String coAttributeSplitString = coAttributeSplit[3];

				Assert.assertEquals(coAttributeSplitString, "errorClass");

				APP_LOGS.debug("Contract Contract Countries is blank");

				fail = false;
				driver.get(CONFIG.getProperty("endUserURL"));
				return;
				}
			}

		if (coCurrencies.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_currencies_multi_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Currencies is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (coReportingCurrency.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_reporting_currencies_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Reporting Currency is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coConversionType.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_conversion_type_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Conversion Type is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coConversionMatrix.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = getObject("co_create_page_conversion_matrix_dropdown").getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[3];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Conversion Matrix is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (coConversionMatrixFrom.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = driver.findElement(By.className("rateCardFromDate")).getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[4];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Effective Date is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (coConversionMatrixTo.equalsIgnoreCase("")) {
			fail = true;

			String coAttribute = driver.findElement(By.className("rateCardToDate")).getAttribute("class");
			String[] coAttributeSplit = coAttribute.split(" ");
			String coAttributeSplitString = coAttributeSplit[4];

			Assert.assertEquals(coAttributeSplitString, "errorClass");

			APP_LOGS.debug("Contract Expiration Date is blank");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (getObject("co_notification_popup_id") != null) {
			String coId = getObject("co_notification_popup_id").getText();
			APP_LOGS.debug("Contract of Type --"+coDocumentType+" created successfully with Contract ID "+coId);			

			getObject("co_notification_popup_id").click();
			Thread.sleep(10000);
			
			if (coDocumentType.equalsIgnoreCase("SOW") || coDocumentType.equalsIgnoreCase("Work Order")) {
				new Actions(driver).moveToElement(getObject("co_show_page_plus_button")).click().build().perform();
				
				driver.findElement(By.linkText("Map Countries")).click();
				Thread.sleep(5000);
				
				getObject("co_map_countries_save_button").click();
				Thread.sleep(10000);
				}
			String coShowPage = getObject("co_show_page_id").getText();

			Assert.assertEquals(coShowPage, coId);
			APP_LOGS.debug("Contract ID on show page has been verified");
			}
		
		if(driver.findElements(By.id("genericErrors")).size()!=0) {
			String errors_create_page = getObject("co_create_page_notification_errors_dialogue_box").getText();

			APP_LOGS.debug("Contract already exists with Contract Number -- "+coContractNo);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			fail=false;
			driver.get(CONFIG.getProperty("endUserURL"));
		    return;
		    }

		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}

	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;
		}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()),"PASS");
		else
			TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()),"FAIL");
		}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(contract_suite_xls, this.getClass().getSimpleName());
		}
	}
