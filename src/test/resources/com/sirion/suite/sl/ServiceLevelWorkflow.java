package test.resources.com.sirion.suite.sl;

import java.sql.SQLException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class ServiceLevelWorkflow extends TestSuiteBase {
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
	public void ServiceLevelWorkflowTest(
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

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
        wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("client_user_analytics_tab_link")));

        getObject("client_user_analytics_tab_link").click();
        wait_in_report.until(ExpectedConditions.elementToBeClickable(getObject("service_levels_quick_link")));

        getObject("service_levels_quick_link").click();
        APP_LOGS.debug("Click on Service Level Quick Link");
        
        driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
        Thread.sleep(5000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Clone')]")));
		driver.findElement(By.xpath("//button[contains(.,'Clone')]")).click();
		Thread.sleep(5000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Save')]")));
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		Thread.sleep(5000);
		
	    if(getObject("sl_notification_popup_id")!=null) {
	    	String sl_id = getObject("sl_notification_popup_id").getText();
	    	APP_LOGS.debug("Service Level cloned successfully with Service Level id "+sl_id);
	    	
	    	getObject("sl_notification_popup_id").click();
	    	Thread.sleep(5000);
	    	}

		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
		Thread.sleep(5000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
		Thread.sleep(5000);
		        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")).click();
		Thread.sleep(5000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")).click();
		Thread.sleep(5000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
		driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")).click();
		Thread.sleep(5000);
		
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
		driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")).click();
		Thread.sleep(5000);
        
		Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
		driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
		Thread.sleep(5000);
		
	    if((getObject("sl_show_page_sl_category_dropdown_errors"))!=null) {
	    	String slError = getObject("sl_show_page_sl_category_dropdown_errors").getText();	        
	        Assert.assertEquals(slError, "Please enter SL Category.");

	        APP_LOGS.debug("SL Category is Mandatory");
	        
	        new Select(getObject("sl_sl_category_dropdown")).selectByVisibleText(slSLCategory);
	        }  
	    
	    if((getObject("sl_show_page_sl_sub_category_dropdown_errors"))!=null) {
	    	String slError = getObject("sl_show_page_sl_sub_category_dropdown_errors").getText();	        
	        Assert.assertEquals(slError, "Please enter SL Sub-Category.");

	        APP_LOGS.debug("SL Sub-Category is Mandatory");
	        
	        new Select(getObject("sl_sl_sub_category_dropdown")).selectByVisibleText(slSLSubCategory);
	        }  
	    
	    if((getObject("sl_show_page_sl_item_dropdown_errors"))!=null) {
	    	String slError = getObject("sl_show_page_sl_item_dropdown_errors").getText();	        
	        Assert.assertEquals(slError, "Please enter SL Item.");

	        APP_LOGS.debug("SL Item is Mandatory");
	        
	        new Select(getObject("sl_sl_item_dropdown")).selectByVisibleText(slSLItem);
	        }  
	    
	    if(driver.findElement(By.xpath(".//*[@id='elem_216']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_216']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Minimum/Maximum?'");

	        APP_LOGS.debug("SL Minimum/Maximum? is Mandatory");
	        
	        new Select(getObject("sl_minimum_maximum_dropdown")).selectByVisibleText(slMinimumMaximumSelection);
	        }  
	    
	    if(driver.findElement(By.xpath(".//*[@id='elem_217']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_217']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Unit of SL Measurement'");

	        APP_LOGS.debug("SL Unit of SL Measurement is Mandatory");
	        
	        new Select(getObject("sl_unit_of_sl_measurement_dropdown")).selectByVisibleText(slUnitofMeasurement);
	        }  
	    
	    if(driver.findElement(By.xpath(".//*[@id='elem_218']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_218']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Minimum/Maximum'");

	        APP_LOGS.debug("SL Minimum/Maximum is Mandatory");
	        
	        getObject("sl_minimum_maximum_numeric_box").sendKeys(slSLItem);
	        }  

	    if(driver.findElement(By.xpath(".//*[@id='elem_219']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_219']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Expected'");

	        APP_LOGS.debug("SL Expected is Mandatory");
	        
	        getObject("sl_expected_numeric_box").sendKeys(slExpected);
	        }  
	    
	    if(driver.findElement(By.xpath(".//*[@id='elem_220']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_220']/span")).getText();	        
	        Assert.assertEquals(slError, "Please Enter 'Significantly Minimum/Maximum'");

	        APP_LOGS.debug("SL Significantly Minimum/Maximum is Mandatory");
	        
	        getObject("sl_significantly_min_max_numeric_box").sendKeys(slSignificantlyMinMax);
	        }  
	    
	    if(driver.findElement(By.xpath(".//*[@id='elem_221']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_221']/span")).getText();	        
	        Assert.assertEquals(slError, "Please enter Measurement Window.");

	        APP_LOGS.debug("SL Measurement Window is Mandatory");
	        
	        new Select(getObject("sl_measurement_window_dropdown")).selectByVisibleText(slMeasurementWindow);
	        }  
/*
	    if(driver.findElement(By.xpath(".//*[@id='elem_240']/span"))!=null) {
	    	String slError = driver.findElement(By.xpath(".//*[@id='elem_240']/span")).getText();	        
	        Assert.assertEquals(slError, "Please enter Frequency.");

	        APP_LOGS.debug("SL Computation Frequency is Mandatory");
	        
	        new Select(getObject("sl_measurement_window_dropdown")).selectByVisibleText(slMeasurementWindow);
	        } */
        
		
/*		if(getObject("sl_publish_contract_error").isDisplayed()) {
			if(getObject("sl_publish_contract_error").getText().contains("Please publish contract")) {
				getObject("sl_contract_link").click();
				Thread.sleep(10000);
				
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")));
				driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
				Thread.sleep(10000);
				
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")));
				driver.findElement(By.xpath("//button[contains(.,'Peer Review Complete')]")).click();
				Thread.sleep(10000); 
		        
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")));
				driver.findElement(By.xpath("//button[contains(.,'Send For Internal Review')]")).click();
				Thread.sleep(10000); 
						         
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")));
				driver.findElement(By.xpath("//button[contains(.,'Internal Review Complete')]")).click();
				Thread.sleep(10000); 
				 	        
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")));
				driver.findElement(By.xpath("//button[contains(.,'Send For Client Review')]")).click();
				Thread.sleep(20000); 
							
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Approve')]")));
				driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();
				Thread.sleep(20000);
				
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish')]")));
				driver.findElement(By.xpath("//button[contains(.,'Publish')]")).click();
				Thread.sleep(10000);
				
				getObject("analytics_link").click();
				Thread.sleep(10000);
				
				getObject("child_service_levels_quick_link").click();
			    Thread.sleep(20000);
			        
			    getObject("service_levels_link").click();
			    Thread.sleep(20000);
			    
				driver.findElement(By.xpath(".//*[@id='cr']/tbody/tr[1]/td[1]/a")).click();
		        Thread.sleep(10000);
		        
				Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'Publish')]")));
				driver.findElement(By.xpath("//button[contains(.,'Publish')]")).click();
				Thread.sleep(10000);
				}
			}
				         
		String sl_status = getObject("sl_status_show").getText();         
		Assert.assertEquals(sl_status, "Active");     
           
*/		fail = false;
		driver.get(CONFIG.getProperty("endUserURL"));
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
