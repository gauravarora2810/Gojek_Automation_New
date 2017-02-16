package test.resources.com.sirion.suite.childSL;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ChildSLUpdate extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(child_sl_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(child_sl_suite_xls, this.getClass().getSimpleName());
		}

	@Test(dataProvider="getTestData") 
	public void ChildSLWorkflowTest(String cslSupplierNumerator, String cslActualNumerator, String cslSupplierDenominator, String cslActualDenominator,
			String cslSupplierCalculation, String cslActualPerformance, String cslDiscrepancy, String cslDiscrepancyResolutionStatus,
			String cslFinalNumerator, String cslFinalDenominator, String cslFinalPerformance) throws InterruptedException, ClassNotFoundException, SQLException {
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Child Service Level Workflow");

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(5000);

		// Click on Analytics
		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(5000);

        getObject("service_levels_quick_link").click();
		Thread.sleep(10000);
        
        driver.findElement(By.linkText("View Child Service Levels")).click();
		Thread.sleep(10000);
        
        driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Edit')]")));
		driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
		Thread.sleep(10000);

		if(!cslSupplierNumerator.equalsIgnoreCase("")) {
			getObject("csl_edit_page_supplier_numerator_numeric_box").clear();
			getObject("csl_edit_page_supplier_numerator_numeric_box").sendKeys(cslSupplierNumerator);
			}

		if(!cslActualNumerator.equalsIgnoreCase("")) {
			getObject("csl_edit_page_actual_numerator_numeric_box").clear();
			getObject("csl_edit_page_actual_numerator_numeric_box").sendKeys(cslActualNumerator);
			}

		if(!cslSupplierDenominator.equalsIgnoreCase("")) {
			getObject("csl_edit_page_supplier_denominator_numeric_box").clear();
			getObject("csl_edit_page_supplier_denominator_numeric_box").sendKeys(cslSupplierDenominator);
			}

		if(!cslActualDenominator.equalsIgnoreCase("")) {
			getObject("csl_edit_page_actual_denominator_numeric_box").clear();
			getObject("csl_edit_page_actual_denominator_numeric_box").sendKeys(cslActualDenominator);
			}

		if(!cslSupplierCalculation.equalsIgnoreCase("")) {
			getObject("csl_edit_page_supplier_calculation_numeric_box").clear();
			getObject("csl_edit_page_supplier_calculation_numeric_box").sendKeys(cslSupplierCalculation);
			}

		if(!cslActualPerformance.equalsIgnoreCase("")) {
			getObject("csl_edit_page_actual_performance_numeric_box").clear();
			getObject("csl_edit_page_actual_performance_numeric_box").sendKeys(cslActualPerformance);
			}

		if(!cslDiscrepancy.equalsIgnoreCase("")) {
			getObject("csl_edit_page_discrepancy_textarea").clear();
			getObject("csl_edit_page_discrepancy_textarea").sendKeys(cslDiscrepancy);
			}

		if(!cslDiscrepancyResolutionStatus.equalsIgnoreCase("")) {
			getObject("csl_edit_page_discrepancy_resolution_status_textarea").clear();
			getObject("csl_edit_page_discrepancy_resolution_status_textarea").sendKeys(cslDiscrepancyResolutionStatus);
			}

		if(!cslFinalNumerator.equalsIgnoreCase("")) {
			getObject("csl_edit_page_final_numerator_numeric_box").clear();
			getObject("csl_edit_page_final_numerator_numeric_box").sendKeys(cslFinalNumerator);
			}

		if(!cslFinalDenominator.equalsIgnoreCase("")) {
			getObject("csl_edit_page_final_denominator_numeric_box").clear();
			getObject("csl_edit_page_final_denominator_numeric_box").sendKeys(cslFinalDenominator);
			}

		if(!cslFinalPerformance.equalsIgnoreCase("")) {
			getObject("csl_edit_page_final_performance_numeric_box").clear();
			getObject("csl_edit_page_final_performance_numeric_box").sendKeys(cslFinalPerformance);
			}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Update')]")));
		driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();
		Thread.sleep(10000);
        
        fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
		}
          
  @AfterMethod
  public void reportDataSetResult() {
	  if (skip)
		  TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
	  else if (fail) {
		  isTestPass = false;
		  TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		  }
	  else
		  TestUtil.reportDataSetResult(child_sl_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
	  
	  skip = false;
	  fail = false;
	  }

  @AfterTest
  public void reportTestResult() {
	  if(isTestPass)
		  TestUtil.reportDataSetResult(child_sl_suite_xls, "Test Cases", TestUtil.getRowNum(child_sl_suite_xls, this.getClass().getSimpleName()), "PASS");
	  else
		  TestUtil.reportDataSetResult(child_sl_suite_xls, "Test Cases", TestUtil.getRowNum(child_sl_suite_xls, this.getClass().getSimpleName()), "FAIL");
	  }

  @DataProvider
  public Object[][] getTestData() {
	  return TestUtil.getData(child_sl_suite_xls, this.getClass().getSimpleName());
	  }
  }
