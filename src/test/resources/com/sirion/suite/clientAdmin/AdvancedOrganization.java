package test.resources.com.sirion.suite.clientAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class AdvancedOrganization extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(client_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(client_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void Clientedit(String clientName, String clientAddress, String clientApplyEncryption, String clientTimeZone, String clientIndustries, String clientAllowSameName,
			String clientEnableHTML, String clientBranding, String clientLanguage, String clientEnableDate, String clientFunctions, String clientServices,
			String clientRegions, String clientCountries, String clientCurrencies, String clientReportingCurrency, String clientConversionType,
			String clientConversionRatesApplicable, String clientConversionMatrixFromDate, String clientConversionMatrixToDate
			) throws Throwable {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +clientName +" set to NO " +count);
			}
		
		openBrowser();
		
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
		APP_LOGS.debug("Executing Client Admin Advanced Organization Update Test -- "+clientName);
		
		getObject("ca_administration_tab_link").click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Advanced Organization")).click();
		
		getObject("ca_advanced_organization_show_page_edit_button").click();
		
		if(!clientName.equalsIgnoreCase("")) {
			getObject("ca_advanced_organization_name_textbox").clear();
			getObject("ca_advanced_organization_name_textbox").sendKeys(clientName);
			}

		if(!clientAddress.equalsIgnoreCase("")) {
			getObject("ca_advanced_organization_address_textarea").clear();
			getObject("ca_advanced_organization_address_textarea").sendKeys(clientAddress);
			}
		
		if(clientApplyEncryption.equalsIgnoreCase("Yes")) {
			getObject("ca_advanced_organization_apply_encryption_checkbox").click();
			}
		
		if(!clientTimeZone.equalsIgnoreCase("")) {
			new Select(getObject("ca_advanced_organization_timezone_dropdown")).selectByVisibleText(clientTimeZone);
			}
		
		if(!clientIndustries.equalsIgnoreCase("")) {
			for(String clientData : clientIndustries.split(";")) {
				new Select(getObject("ca_advanced_organization_industries_multi_dropdown")).selectByVisibleText(clientData.trim());
				}
			}
		
		if(clientAllowSameName.equalsIgnoreCase("Yes")) {
			getObject("ca_advanced_organization_allow_same_name_checkbox").click();
			}
		
		if(clientEnableHTML.equalsIgnoreCase("Yes")) {
			getObject("ca_advanced_organization_enable_html_checkbox").click();
			}
		
		if(clientBranding.equalsIgnoreCase("Yes")) {
			getObject("ca_advanced_organization_organization_branding_checkbox").click();
			}
		
		if(!clientLanguage.equalsIgnoreCase("")) {
			new Select(getObject("ca_advanced_organization_default_language_dropdown")).selectByVisibleText(clientLanguage);
			}
		
		if(clientEnableDate.equalsIgnoreCase("Yes")) {
			getObject("ca_advanced_organization_enable_date_checkbox").click();
			}
		
		if(!clientFunctions.equalsIgnoreCase("")) {
			for(String clientData : clientFunctions.split(";")) {
				new Select(getObject("ca_advanced_organization_functions_multi_dropdown")).selectByVisibleText(clientData.trim());
				}			
			if(!clientServices.equalsIgnoreCase("")) {
				for(String clientData : clientServices.split(";")) {
					new Select(driver.findElement(By.name("contractSubTypes"))).selectByVisibleText("  "+clientData.trim());
					}
				}
			}
		
		if(!clientRegions.equalsIgnoreCase("")) {
			for(String clientData : clientRegions.split(";")) {
				new Select(getObject("ca_advanced_organization_regions_multi_dropdown")).selectByVisibleText(clientData.trim());
				}
			if(!clientCountries.equalsIgnoreCase("")) {
				for(String clientData : clientCountries.split(";")) {
					new Select(driver.findElement(By.name("countries"))).selectByVisibleText("  "+clientData.trim());
					}
				}
			}		
		
		if(!clientCurrencies.equalsIgnoreCase("")) {
			for(String clientData : clientCurrencies.split(";")) {
				new Select(getObject("ca_advanced_organization_currencies_multi_dropdown")).selectByVisibleText(clientData.trim());
				}
			if(!clientReportingCurrency.equalsIgnoreCase("")) {
				new Select(getObject("ca_advanced_organization_reporting_currency_dropdown")).selectByVisibleText(clientReportingCurrency);
				}
			}
		
		if(!clientConversionType.equalsIgnoreCase("")) {
			new Select(getObject("ca_advanced_organization_conversion_type_dropdown")).selectByVisibleText(clientConversionType);
			}
		
		if(!clientConversionRatesApplicable.equalsIgnoreCase("")) {
			new Select(getObject("ca_advanced_organization_conversion_rate_dropdown")).selectByVisibleText(clientConversionRatesApplicable);
			
		    if (!clientConversionMatrixFromDate.equalsIgnoreCase("")) {
		    	driver.findElement(By.id("c_com_sirionlabs_model_ratecard_startdate_id")).click();
		        String[] clientDate = clientConversionMatrixFromDate.split("-");

		        String clientMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
		        while (!clientMonth.equalsIgnoreCase(clientDate[0])) {
		          driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		          clientMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
		          }

		        new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(clientDate[2]);

		        driver.findElement(By.linkText(clientDate[1])).click();
		        }

		    if (!clientConversionMatrixToDate.equalsIgnoreCase("")) {
		    	driver.findElement(By.id("c_com_sirionlabs_model_ratecard_enddate_id")).click();
		        String[] clientDate = clientConversionMatrixToDate.split("-");

		        String clientMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
		        while (!clientMonth.equalsIgnoreCase(clientDate[0])) {
		          driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		          clientMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
		          }

		        new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(clientDate[2]);

		        driver.findElement(By.linkText(clientDate[1])).click();
		        }
		    }
		
		getObject("ca_advanced_organization_update_button").click();
		
		getObject("ca_advanced_organization_comments_submit_button").click();
		Thread.sleep(5000);
		
		
        fail = false;        
		getObject("ca_administration_tab_link").click();
		}
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName());
		}
	}