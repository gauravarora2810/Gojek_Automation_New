package test.resources.com.sirion.suite.sl;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class SLWorkflow extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static boolean fail = true;
	static boolean skip = false;
	static boolean isTestPass = true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(sl_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(sl_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider="getTestData") 
	public void SLWorkflowTest(
			String slSLCategory, String slSLSubCategory, String slSLItem,
			String slMinimumMaximumSelection, String slUnitofMeasurement, String slMinimumMaximumValue, String slExpected, String slSignificantlyMinMax, String slMeasurementWindow,
			String slFrequencyType, String slComputationFrequency, String slFrequency, String slStartDate, String slEndDate, String slPatternDate, String slEffectiveDate,
			String slStakeholder, String slComments, String slActualDate, String slRequestedBy, String slChangeRequest, String slUploadFile
			) throws InterruptedException, ClassNotFoundException, SQLException {
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Set Data Set to NO "+count);
			}
		
		APP_LOGS.debug("Executing Test Case of Service Level Workflow");

		// Launch The Browser
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		Thread.sleep(10000);

		driver.get(CONFIG.getProperty("endUserURL"));
		Thread.sleep(10000);

        getObject("service_levels_quick_link").click();
		Thread.sleep(10000);
        
        driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(20000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save SL')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save SL')]")).click();
		Thread.sleep(10000);
		
	    if(getObject("sl_notification_popup_id")!=null) {
	    	String sl_id = getObject("sl_notification_popup_id").getText();
	    	APP_LOGS.debug("Service Level cloned successfully with Service Level id "+sl_id);
	    	
	    	getObject("sl_notification_popup_id").click();
			Thread.sleep(10000);
	    	}
	    
	    
	    Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
		Thread.sleep(10000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")).click();
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")).click();
		Thread.sleep(10000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")).click();
		Thread.sleep(10000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")).click();
		Thread.sleep(10000);
      /*  
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
		Thread.sleep(10000);*/
/*		
		// Select SL Category
	    if((getObject("sl_show_page_sl_category_dropdown_errors"))!=null) {
	    	String slError = getObject("sl_show_page_sl_category_dropdown_errors").getText();	        
	        Assert.assertEquals(slError, "Please enter SL Category.");

	        APP_LOGS.debug("SL Category is Mandatory");
	        
	        new Select(getObject("sl_sl_category_dropdown")).selectByVisibleText(slSLCategory);
	        }  
	    
	    // Select SL Sub-Category
	    if((getObject("sl_show_page_sl_sub_category_dropdown_errors"))!=null) {
	    	String slError = getObject("sl_show_page_sl_sub_category_dropdown_errors").getText();	        
	        Assert.assertEquals(slError, "Please enter SL Sub-category.");

	        APP_LOGS.debug("SL Sub-Category is Mandatory");
	        
	        new Select(getObject("sl_sl_sub_category_dropdown")).selectByVisibleText(slSLSubCategory);
	        }  
	    
	    // Select SL Item
	    if((getObject("sl_show_page_sl_item_dropdown_errors"))!=null) {
	    	String slError = getObject("sl_show_page_sl_item_dropdown_errors").getText();	        
	        Assert.assertEquals(slError, "Please enter SL Item.");

	        APP_LOGS.debug("SL Item is Mandatory");
	        
	        new Select(getObject("sl_sl_item_dropdown")).selectByVisibleText(slSLItem);
	        }  
	    
	    // Select SL Minimum/Maximum
	    if(driver.findElement(By.xpath(".//*[@id='elem_216']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_216']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Minimum/Maximum?'");

	        APP_LOGS.debug("SL Minimum/Maximum? is Mandatory");
	        
	        new Select(getObject("sl_minimum_maximum_dropdown")).selectByVisibleText(slMinimumMaximumSelection);
	        }  
	    
	    // Select SL Unit of Measurement
	    if(driver.findElement(By.xpath(".//*[@id='elem_217']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_217']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Unit of SL Measurement'");

	        APP_LOGS.debug("SL Unit of SL Measurement is Mandatory");
	        
	        new Select(getObject("sl_unit_of_sl_measurement_dropdown")).selectByVisibleText(slUnitofMeasurement);
	        }  
	    
	    // Enter SL Minimum/Maximum
	    if(driver.findElement(By.xpath(".//*[@id='elem_218']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_218']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Minimum/Maximum'");

	        APP_LOGS.debug("SL Minimum/Maximum is Mandatory");
	        
	        getObject("sl_minimum_maximum_numeric_box").sendKeys(slSLItem);
	        }  

	    // Enter SL Expected
	    if(driver.findElement(By.xpath(".//*[@id='elem_219']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_219']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Expected'");

	        APP_LOGS.debug("SL Expected is Mandatory");
	        
	        getObject("sl_expected_numeric_box").sendKeys(slExpected);
	        }  
	    
	    // Enter SL Significantly Minimum/maximum
	    if(driver.findElement(By.xpath(".//*[@id='elem_220']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_220']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Significantly Minimum/Maximum'");

	        APP_LOGS.debug("SL Significantly Minimum/Maximum is Mandatory");
	        
	        getObject("sl_significantly_min_max_numeric_box").sendKeys(slSignificantlyMinMax);
	        }  
	    
	    // Select SL Measurement Window
	    if(driver.findElement(By.xpath(".//*[@id='elem_221']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_221']/span")).getText();	        
	        Assert.assertEquals(slError, "Please enter Measurement Window.");

	        APP_LOGS.debug("SL Measurement Window is Mandatory");
	        
	        new Select(getObject("sl_measurement_window_dropdown")).selectByVisibleText(slMeasurementWindow);
	        }  
	    
	    // Select SL Computation Frequency and Frequency Type
	    if(driver.findElement(By.xpath(".//*[@id='elem_240']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_240']/span")).getText();	        
	        Assert.assertEquals(slError, "Please enter Frequency.");

	        APP_LOGS.debug("SL Computation Frequency is Mandatory");
	        
			if (!slFrequencyType.equalsIgnoreCase("")) {
				new Select(getObject("sl_frequency_type_dropdown")).selectByVisibleText(slFrequencyType);
				}
			new Select(getObject("sl_computation_frequency_dropdown")).selectByVisibleText(slComputationFrequency);
			}  

	    // Select SL Computation Frequency and Frequency
	    if(driver.findElement(By.xpath(".//*[@id='elem_241']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_241']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Frequency'");

	        APP_LOGS.debug("SL Frequency is Mandatory");
	        
	        new Select(getObject("sl_frequency_dropdown")).selectByVisibleText(slFrequency);
			}  
	    
	    // Select SL Start Date
	    if(driver.findElement(By.xpath(".//*[@id='elem_243']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_243']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Start Date'");

	        APP_LOGS.debug("SL Start Date is Mandatory");
	        
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
	    if(driver.findElement(By.xpath(".//*[@id='elem_244']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_244']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'End Date'");

	        APP_LOGS.debug("SL End Date is Mandatory");
	        
			driver.findElement(By.name("expDate")).click();
			String[] slDate = slStartDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}  
	    
	    // Select SL Pattern Date
	    if(driver.findElement(By.xpath(".//*[@id='elem_295']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_295']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Pattern Date'");

	        APP_LOGS.debug("SL Pattern Date is Mandatory");
	        
			driver.findElement(By.name("patternDate")).click();
			String[] slDate = slStartDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}  
	    
	    // Select SL Effective Date
	    if(driver.findElement(By.xpath(".//*[@id='elem_296']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_296']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Effective Date'");

	        APP_LOGS.debug("SL Effective Date is Mandatory");
	        
			driver.findElement(By.name("effectiveDate")).click();
			String[] slDate = slStartDate.split("-");

			String slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
			while (!slMonth.equalsIgnoreCase(slDate[0])) {
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				slMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span")).getText();
				}

			new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"))).selectByVisibleText(slDate[2]);

			driver.findElement(By.linkText(slDate[1])).click();
			}
	    
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
		Thread.sleep(10000);
*/
		/*Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish')]")));
		driver.findElement(By.xpath("//button[contains(.,'Publish')]")).click();
		Thread.sleep(10000);*/
	    
		fail = false;
		
		}

  @AfterMethod
  public void reportDataSetResult() {
	  if (skip)
		  TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
	  else if (fail) {
		  isTestPass = false;
		  TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		  }
	  else
		  TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");
	  
	  skip = false;
	  fail = false;
	  }

  @AfterTest
  public void reportTestResult() {
	  if(isTestPass)
		  TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls, this.getClass().getSimpleName()), "PASS");
	  else
		  TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls, this.getClass().getSimpleName()), "FAIL");
	  }

  @DataProvider
  public Object[][] getTestData() {
	  return TestUtil.getData(sl_suite_xls, this.getClass().getSimpleName());
	  }
  }
