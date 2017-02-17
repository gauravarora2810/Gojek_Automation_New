package test.resources.com.sirion.suite.cdr;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CDRCreation extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;
	
	@BeforeTest
	public void checkTestSkip() {
		if (!TestUtil.isTestCaseRunnable(cdr_suite_xls, this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(cdr_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider = "getTestData")
	public void CDRCreationTest (String cdrTitle, String cdrClientContractingEntity, String cdrCounterPartyContractingEntity, String cdrSuppliers,
			String cdrCounterPartyAddress, String cdrPriority,	String cdrPaperType, String cdrTimezone, String cdrNatureOfTransaction,	String cdrAgreementType,
			String cdrIndustryTypes, String cdrFunctions, String cdrServices, String cdrTermType, String cdrBusinessUnits, String cdrBusinessLines, String cdrDealOverview,
			String cdrCurrency, String cdrACV, String cdrTCV, String cdrRegions, String cdrCountries, String cdrEffectiveDate, String cdrExpirationDate,
			String cdrReqSubmissionDate, String cdrExpCompletionDate, String cdrStartDate, String cdrCompletionDate) throws InterruptedException {

		count++;
        if(!runmodes[count].equalsIgnoreCase("Y")) {
            skip = true;
            throw new SkipException("Runmode for Test Data set to NO " + count);
            }
        
        APP_LOGS.debug("Executing Test Case CDR Creation");
        
        openBrowser();
        endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);
		wait_in_report.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='h-analytics']/a")));
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(10000);
		
		getObject("cdr_quick_link").click();
		Thread.sleep(20000);
       
        getObject("cdr_listing_page_plus_icon").click(); 
		Thread.sleep(10000);
       
        if (!cdrTitle.equalsIgnoreCase("")) {
        	getObject("cdr_create_page_title_textbox").clear();
        	getObject("cdr_create_page_title_textbox").sendKeys(cdrTitle);
        	}
        
        if (!cdrClientContractingEntity.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_client_contracting_entity_dropdown")).selectByVisibleText(cdrClientContractingEntity);
        	}
        
        if (!cdrCounterPartyContractingEntity.equalsIgnoreCase("")) {
        	getObject("cdr_create_page_counter_party_contracting_entity_textbox").clear();
        	getObject("cdr_create_page_counter_party_contracting_entity_textbox").sendKeys(cdrCounterPartyContractingEntity);
        	}
        
		if (!cdrSuppliers.equalsIgnoreCase("")) {
			for (String cdrData : cdrSuppliers.split(";")) {
				new Select(getObject("cdr_create_page_suppliers_multi_dropdown")).selectByVisibleText(cdrData.trim());
				}
			}
        
        if(!cdrCounterPartyAddress.equalsIgnoreCase("")) {
           	getObject("cdr_create_page_counter_party_address_textarea").clear();
            getObject("cdr_create_page_counter_party_address_textarea").sendKeys(cdrCounterPartyAddress);
        	}
        
        if(!cdrPriority.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_priority_dropdown")).selectByVisibleText(cdrPriority);
        	}
        
        if(!cdrPaperType.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_paper_type_dropdown")).selectByVisibleText(cdrPaperType);
        	}
        
        if(!cdrTimezone.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_timezone_dropdown")).selectByVisibleText(cdrTimezone);
        	}
        
        if(!cdrNatureOfTransaction.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_nature_of_transaction_dropdown")).selectByVisibleText(cdrNatureOfTransaction);
        	}
        
        if(!cdrAgreementType.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_agreement_type_dropdown")).selectByVisibleText(cdrAgreementType);
        	}
        
		if (!cdrIndustryTypes.equalsIgnoreCase("")) {
			for (String cdrData : cdrIndustryTypes.split(";")) {
				new Select(getObject("cdr_create_page_industry_types_multi_dropdown")).selectByVisibleText(cdrData.trim());
				}
			}

		if (!cdrFunctions.equalsIgnoreCase("")) {
			for (String cdrData : cdrFunctions.split(";")) {
				new Select(getObject("cdr_create_page_functions_multi_dropdown")).selectByVisibleText(cdrData.trim());
				}
			if (!cdrServices.equalsIgnoreCase("")) {
				for (String cdrData : cdrServices.split(";")) {
					new Select(getObject("cdr_create_page_services_multi_dropdown")).selectByVisibleText(cdrData.trim());
					}
				}
			}
        
        if(!cdrTermType.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_term_type_dropdown")).selectByVisibleText(cdrTermType);
        	}

		if (!cdrBusinessUnits.equalsIgnoreCase("")) {
			for (String cdrData : cdrBusinessUnits.split(";")) {
				new Select(getObject("cdr_create_page_business_units_multi_dropdown")).selectByVisibleText(cdrData.trim());
				}
			}

		if (!cdrBusinessLines.equalsIgnoreCase("")) {
			for (String cdrData : cdrBusinessLines.split(";")) {
				new Select(getObject("cdr_create_page_business_lines_multi_dropdown")).selectByVisibleText(cdrData.trim());
				}
			}

		if(!cdrDealOverview.equalsIgnoreCase("")) {
        	getObject("cdr_create_page_deal_overview_textarea").clear();
        	getObject("cdr_create_page_deal_overview_textarea").sendKeys(cdrDealOverview);
        	}

        if(!cdrCurrency.equalsIgnoreCase("")) {
        	new Select(getObject("cdr_create_page_currency_dropdown")).selectByVisibleText(cdrCurrency);
        	}        
        
        if(!cdrACV.equalsIgnoreCase("")) {
        	Double temp_cdrACV_double = Double.parseDouble(cdrACV);
            int temp_cdrACV_int = temp_cdrACV_double.intValue();
            String cdrACV_string = Integer.toString(temp_cdrACV_int);
            getObject("cdr_create_page_acv_numeric_textbox").clear();
            getObject("cdr_create_page_acv_numeric_textbox").sendKeys(cdrACV_string);
            }

        if(!cdrTCV.equalsIgnoreCase("")) {
        	Double temp_cdrTCV_double = Double.parseDouble(cdrTCV);
            int temp_cdrTCV_int = temp_cdrTCV_double.intValue();
            String cdrTCV_string = Integer.toString(temp_cdrTCV_int);
            getObject("cdr_create_page_tcv_numeric_textbox").clear();
            getObject("cdr_create_page_tcv_numeric_textbox").sendKeys(cdrTCV_string);
            }
       
		if (!cdrRegions.equalsIgnoreCase("")) {
			for (String cdrData : cdrRegions.split(";")) {
				new Select(getObject("cdr_create_page_regions_multi_dropdown")).selectByVisibleText(cdrData.trim());
				}
			if (!cdrCountries.equalsIgnoreCase("")) {
				for (String cdrData : cdrCountries.split(";")) {
					new Select(getObject("cdr_create_page_countries_multi_dropdown")).selectByVisibleText(cdrData.trim());
					}
				}
			}

		if (!cdrEffectiveDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_11017")).click();
//			driver.findElement(By.name("effectiveDate")).click();
			String[] cdrDate = cdrEffectiveDate.split("-");

			String cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!cdrMonth.equalsIgnoreCase(cdrDate[0])) {
				 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")));    
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(cdrDate[2]);

			driver.findElement(By.linkText(cdrDate[1])).click();
			}

		if (!cdrExpirationDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_11236")).click();
//			driver.findElement(By.name("expirationDate")).click();
			String[] cdrDate = cdrExpirationDate.split("-");

			String cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!cdrMonth.equalsIgnoreCase(cdrDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(cdrDate[2]);

			driver.findElement(By.linkText(cdrDate[1])).click();
			}
/*		
		if (!cdrReqSubmissionDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_11027")).click();
			String[] cdrDate = cdrReqSubmissionDate.split("-");

			String cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!cdrMonth.equalsIgnoreCase(cdrDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(cdrDate[2]);

			driver.findElement(By.linkText(cdrDate[1])).click();
			}
*/		
		if (!cdrExpCompletionDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_11239")).click();
			String[] cdrDate = cdrExpCompletionDate.split("-");

			String cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!cdrMonth.equalsIgnoreCase(cdrDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(cdrDate[2]);

			driver.findElement(By.linkText(cdrDate[1])).click();
			}

		if (!cdrStartDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_11237")).click();
			String[] cdrDate = cdrStartDate.split("-");

			String cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!cdrMonth.equalsIgnoreCase(cdrDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(cdrDate[2]);

			driver.findElement(By.linkText(cdrDate[1])).click();
			}
		
		if (!cdrCompletionDate.equalsIgnoreCase("")) {
			driver.findElement(By.id("elem_11028")).click();
			String[] cdrDate = cdrCompletionDate.split("-");

			String cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!cdrMonth.equalsIgnoreCase(cdrDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				cdrMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}
			
			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(cdrDate[2]);

			driver.findElement(By.linkText(cdrDate[1])).click();
			}
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save CDR')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save CDR')]")).click();
		Thread.sleep(10000);
		
		if (cdrTitle.equalsIgnoreCase("")) {
			fail = true;

			String cdrAttribute = getObject("cdr_create_page_name_textbox").getAttribute("class");
			String[] cdrAttributeSplit = cdrAttribute.split(" ");
			String cdrAttributeSplitString = cdrAttributeSplit[3];

			Assert.assertEquals(cdrAttributeSplitString, "errorClass");

			APP_LOGS.debug("CDR Title is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (cdrTimezone.equalsIgnoreCase("")) {
			fail = true;

			String cdrAttribute = getObject("cdr_create_page_name_textbox").getAttribute("class");
			String[] cdrAttributeSplit = cdrAttribute.split(" ");
			String cdrAttributeSplitString = cdrAttributeSplit[3];

			Assert.assertEquals(cdrAttributeSplitString, "errorClass");

			APP_LOGS.debug("CDR Timezone is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (cdrFunctions.equalsIgnoreCase("")) {
			fail = true;

			String cdrAttribute = getObject("cdr_create_page_name_textbox").getAttribute("class");
			String[] cdrAttributeSplit = cdrAttribute.split(" ");
			String cdrAttributeSplitString = cdrAttributeSplit[3];

			Assert.assertEquals(cdrAttributeSplitString, "errorClass");

			APP_LOGS.debug("CDR Timezone is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (cdrServices.equalsIgnoreCase("")) {
			fail = true;

			String cdrAttribute = getObject("cdr_create_page_name_textbox").getAttribute("class");
			String[] cdrAttributeSplit = cdrAttribute.split(" ");
			String cdrAttributeSplitString = cdrAttributeSplit[3];

			Assert.assertEquals(cdrAttributeSplitString, "errorClass");

			APP_LOGS.debug("CDR Timezone is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (cdrRegions.equalsIgnoreCase("")) {
			fail = true;

			String cdrAttribute = getObject("cdr_create_page_name_textbox").getAttribute("class");
			String[] cdrAttributeSplit = cdrAttribute.split(" ");
			String cdrAttributeSplitString = cdrAttributeSplit[3];

			Assert.assertEquals(cdrAttributeSplitString, "errorClass");

			APP_LOGS.debug("CDR Timezone is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}
		
		if (cdrCountries.equalsIgnoreCase("")) {
			fail = true;

			String cdrAttribute = getObject("cdr_create_page_name_textbox").getAttribute("class");
			String[] cdrAttributeSplit = cdrAttribute.split(" ");
			String cdrAttributeSplitString = cdrAttributeSplit[3];

			Assert.assertEquals(cdrAttributeSplitString, "errorClass");

			APP_LOGS.debug("CDR Timezone is Mandatory");

			fail = false;
			driver.get(CONFIG.getProperty("endUserURL"));
			return;
			}

		if (getObject("cdr_create_page_cdr_id_popup_link") != null) {
			String cdrId = getObject("cdr_create_page_cdr_id_popup_link").getText();
			APP_LOGS.debug("CDR created successfully with CDR id "+cdrId);

			getObject("cdr_create_page_cdr_id_popup_link").click();
			Thread.sleep(10000);

			String cdrShowPage = getObject("cdr_show_page_id").getText();

			Assert.assertEquals(cdrShowPage, cdrId);
			APP_LOGS.debug("CDR ID on show page has been verified");
			}
		
		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(cdr_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if(fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(cdr_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(cdr_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
		
		skip = false;
		fail = true;
		}

	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(cdr_suite_xls, "Test Cases", TestUtil.getRowNum(cdr_suite_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(cdr_suite_xls, "Test Cases", TestUtil.getRowNum(cdr_suite_xls, this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(cdr_suite_xls, this.getClass().getSimpleName());
		}
	}