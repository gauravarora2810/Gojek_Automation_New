package test.resources.com.sirion.suite.sirionAdmin;

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

public class Frequency extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void FrequencyCreation (String frequencyName, String frequencyType, String frequencyRepeats, String frequencyRepeatEvery, String frequencyRepeatOn,
			String frequencyWeeklyRepeatOnAdvancedDay, String frequencyAdvancedFirstDropdown, String frequencyAdvancedSecondDropdown,
			String frequencyAdvanceCreation, String frequencyBelowMonthly) throws InterruptedException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +frequencyName +" set to NO " +count);
			}
		
		openBrowser();
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword"));
		
		APP_LOGS.debug("Executing Sirion Admin Frequency Creation Test -- "+frequencyName);
		
		getObject("sirion_admin_administration_tab_link").click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Frequency")).click();
				
		getObject("sa_frequency_plus_button").click();
		
		getObject("sa_frequency_name_textbox").clear();
		getObject("sa_frequency_name_textbox").sendKeys(frequencyName);
		
		new Select(getObject("sa_frequency_type_dropdown")).selectByVisibleText(frequencyType);

		getObject("sa_frequency_recurrence_data_textarea").click();

		if (!frequencyRepeats.equalsIgnoreCase("")) {
			new Select(getObject("sa_frequency_recurrence_data_repeats_dropdown")).selectByVisibleText(frequencyRepeats);
			Thread.sleep(2000);
			
			if (!frequencyRepeats.equalsIgnoreCase("Repeat Once")) {
				Double temp_frequencyRepeatEvery_double = Double.parseDouble(frequencyRepeatEvery);
				int temp_frequencyRepeatEvery_int = temp_frequencyRepeatEvery_double.intValue();
				String temp_frequencyRepeatEvery_string = Integer.toString(temp_frequencyRepeatEvery_int);	

				if (!frequencyRepeatEvery.equalsIgnoreCase("")) {					
					if (frequencyRepeats.equalsIgnoreCase("Daily")) {
						getObject("sa_frequency_daily_repeat_every_numeric_box").clear();
						getObject("sa_frequency_daily_repeat_every_numeric_box").sendKeys(temp_frequencyRepeatEvery_string);
						}
					else if (frequencyRepeats.equalsIgnoreCase("Weekly")) {
						getObject("sa_frequency_weekly_repeat_every_numeric_box").clear();
						getObject("sa_frequency_weekly_repeat_every_numeric_box").sendKeys(temp_frequencyRepeatEvery_string);

						if(frequencyRepeatOn.equalsIgnoreCase("Advanced")) {
							getObject("sa_frequency_weekly_repeat_on_advanced_radio_button").click();
							String[] frequencyData = frequencyWeeklyRepeatOnAdvancedDay.split(";");
							for(int i=0; i<frequencyData.length; i++) {
								if(frequencyData[i].trim().equalsIgnoreCase("Sunday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_sunday_checkbox").click();
									}
								if(frequencyData[i].trim().equalsIgnoreCase("Monday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_monday_checkbox").click();
									}
								if(frequencyData[i].trim().equalsIgnoreCase("Tuesday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_tuesday_checkbox").click();
									}
								if(frequencyData[i].trim().equalsIgnoreCase("Wednesday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_wednesday_checkbox").click();
									}
								if(frequencyData[i].trim().equalsIgnoreCase("Thursday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_thursday_checkbox").click();
									}
								if(frequencyData[i].trim().equalsIgnoreCase("Friday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_friday_checkbox").click();
									}
								if(frequencyData[i].trim().equalsIgnoreCase("Saturday")) {
									getObject("sa_frequency_weekly_repeat_on_advanced_saturday_checkbox").click();
									}
								}
							}
						}
					else if (frequencyRepeats.equalsIgnoreCase("Monthly")) {
						getObject("sa_frequency_monthly_repeat_every_numeric_box").clear();
						getObject("sa_frequency_monthly_repeat_every_numeric_box").sendKeys(temp_frequencyRepeatEvery_string);
						
						if(frequencyRepeatOn.equalsIgnoreCase("Date")) {
							getObject("sa_frequency_monthly_repeat_on_date_radio_button").click();
							}

						if(frequencyRepeatOn.equalsIgnoreCase("Advanced")) {
							getObject("sa_frequency_monthly_repeat_on_advanced_radio_button").click();
							if (!frequencyAdvancedFirstDropdown.equalsIgnoreCase("")) {
								new Select(getObject("sa_frequency_monthly_repeat_on_advanced_first_dropdown")).selectByVisibleText(frequencyAdvancedFirstDropdown);
								}
							if (!frequencyAdvancedSecondDropdown.equalsIgnoreCase("")) {
								new Select(getObject("sa_frequency_monthly_repeat_on_advanced_second_dropdown")).selectByVisibleText(frequencyAdvancedSecondDropdown);
								}
							}
						}
					
					else if (frequencyRepeats.equalsIgnoreCase("Yearly")) {
						getObject("sa_frequency_yearly_repeat_every_numeric_box").clear();
						getObject("sa_frequency_yearly_repeat_every_numeric_box").sendKeys(temp_frequencyRepeatEvery_string);
						
						if(frequencyRepeatOn.equalsIgnoreCase("Date")) {
							getObject("sa_frequency_yearly_repeat_on_date_radio_button").click();
							}

						if(frequencyRepeatOn.equalsIgnoreCase("Advanced")) {
							getObject("sa_frequency_yearly_repeat_on_advanced_radio_button").click();
							if (!frequencyAdvancedFirstDropdown.equalsIgnoreCase("")) {
								new Select(getObject("sa_frequency_yearly_repeat_on_advanced_first_dropdown")).selectByVisibleText(frequencyAdvancedFirstDropdown);
								}
							if (!frequencyAdvancedSecondDropdown.equalsIgnoreCase("")) {
								new Select(getObject("sa_frequency_yearly_repeat_on_advanced_second_dropdown")).selectByVisibleText(frequencyAdvancedSecondDropdown);
								}
							}
						}
					Thread.sleep(2000);
					}
				}
			}
		
		getObject("sa_frequency_recurrence_data_save_button").click();
		Thread.sleep(2000);		
		
		if (!frequencyAdvanceCreation.equalsIgnoreCase("")) {
			Double temp_frequencyAdvanceCreation_double = Double.parseDouble(frequencyAdvanceCreation);
			int temp_frequencyAdvanceCreation_int = temp_frequencyAdvanceCreation_double.intValue();
			String temp_frequencyAdvanceCreation_string = Integer.toString(temp_frequencyAdvanceCreation_int);
			
			getObject("sa_frequency_advance_creation_numeric_box").clear();
			getObject("sa_frequency_advance_creation_numeric_box").sendKeys(temp_frequencyAdvanceCreation_string);
			}

		if (frequencyBelowMonthly.equalsIgnoreCase("Yes")) {
			getObject("sa_frequency_below_monthly_checkbox").click();
			}
		
		getObject("sa_frequency_save_button").click();
		Thread.sleep(5000);
		
		if(driver.findElements(By.id("errorDialog")).size()!=0) {
			String errors_create_page = getObject("sa_frequency_notification_popup_errors_dialogue_box").getText();
			
			getObject("sa_frequency_notification_popup_ok_button").click();
			
			getObject("sa_frequency_cancel_button").click();
			
			getObject("sa_frequency_confirmation_popup_yes_button").click();
			
			APP_LOGS.debug("Frequency already exists with Name -- " +frequencyName);
			APP_LOGS.debug("Errors: "+errors_create_page);
			
			fail=false;
			driver.get(CONFIG.getProperty("sirionAdminURL"));
			return;
			}

        new Select(getObject("sa_frequency_listing_page_display_dropdown")).selectByIndex(3);
        
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterFrequency']/tbody/tr[@role='row']/td[contains(.,'"+ frequencyName +"')]/a/div")).click();
        
        String entityTypeShowPage = getObject("sa_frequency_show_page_name").getText();
        Assert.assertEquals(entityTypeShowPage, frequencyName, "Frequency Name at show page is -- " +entityTypeShowPage+ " instead of -- " +frequencyName);
        
        APP_LOGS.debug("Frequency opened successfully, and following parameters have been validated: Frequency Name -- " +frequencyName);
        
		fail=false;
		driver.get(CONFIG.getProperty("sirionAdminURL"));
		}
	
	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(sirion_admin_suite_xls, this.getClass().getSimpleName());
		}
	}