package test.resources.com.sirion.suite.sirionAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
//import net.sf.saxon.sort.GroupStartingIterator;


public class Frequency extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (dataProvider="getTestData")
	public void FrequencyCreation( String frequencyName, String frequencyType, String frequencyRepeats, String frequencyRepeatsEvery, String frequencyRepeatOn,
	   String frequencyWeeklyRepeatOnAdvancedDay, String frequencyAdvancedFirstDropdown, String frequencyAdvancedSecondDropdown, 
	   String advanceCreation, String belowMonthly, String frequencyActive) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));
		
		int advanceCreation_integer=Double.valueOf(advanceCreation).intValue();
		String advanceCreation_string = Integer.toString(advanceCreation_integer);
		int repeatsEvery_integer=Double.valueOf(frequencyRepeatsEvery).intValue();
	    String repeatsEvery_string = Integer.toString(repeatsEvery_integer);
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Frequency Creation test -- "+frequencyName);
		getObject("admin_tab_link").click();
		getObject("sa_frequency_link").click();
		getObject("sa_frequency_plus_button").click();
		getObject("sa_frequency_name_textbox").sendKeys(frequencyName);
		new Select (getObject("sa_frequency_type_dropdown")).selectByVisibleText(frequencyType);
		
	getObject("sa_frequency_rec_data_textarea").click();
	//Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@class='risavebutton']")).click();
	 /* if (!frequencyRepeats.equalsIgnoreCase("") ) {
	    new Select(getObject("sa_frequency_type_repeats_dropdown")).selectByVisibleText(frequencyRepeats);      
	    }
	  if (!repeatsEvery_string.equalsIgnoreCase("") && !frequencyRepeats.equalsIgnoreCase("Repeat Once") && !frequencyRepeats.equalsIgnoreCase("Weekly")
	      && !frequencyRepeats.equalsIgnoreCase("Monthly") && !frequencyRepeats.equalsIgnoreCase("Yearly")) {
	          getObject("sa_frequency_type_repeats_every_daily_textbox").clear();
	           getObject("sa_frequency_type_repeats_every_daily_textbox").sendKeys(repeatsEvery_string);	    
	    	        }
	  
	    if (frequencyRepeats.equalsIgnoreCase("Weekly")) {
          getObject("sa_frequency_type_repeats_every_weekly_textbox").clear();
           getObject("sa_frequency_type_repeats_every_weekly_textbox").sendKeys(repeatsEvery_string);
                } 
	  
	   if (frequencyRepeats.equalsIgnoreCase("Repeat Once")) {
      getObject("sa_frequency_type_repeats_popup_save_button").click();
               }
	  
       if (frequencyRepeats.equalsIgnoreCase("Monthly")) {
         getObject("sa_frequency_type_repeats_every_monthly_textbox").clear();
          getObject("sa_frequency_type_repeats_every_monthly_textbox").sendKeys(repeatsEvery_string);
               } 
       
       if (frequencyRepeats.equalsIgnoreCase("Yearly")) {
         getObject("sa_frequency_type_repeats_every_yearly_textbox").clear();
          getObject("sa_frequency_type_repeats_every_yearly_textbox").sendKeys(repeatsEvery_string);
               }
	  
	   if (frequencyRepeatOn.equalsIgnoreCase("Weekly Advanced")) {
         getObject("sa_frequency_type_repeats_on_weekly_advanced_radio").click();
          
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Sunday")){
           getObject("sa_frequency_type_repeats_on_sunday_checkbox").click();
         }
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Monday")){
           getObject("sa_frequency_type_repeats_on_monday_checkbox").click();
         }
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Tueday")){
           getObject("sa_frequency_type_repeats_on_tueday_checkbox").click();
         }
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Wednesday")){
           getObject("sa_frequency_type_repeats_on_wednesday_checkbox").click();
         }
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Thursday")){
           getObject("sa_frequency_type_repeats_on_thursday_checkbox").click();
         }
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Friday")){
           getObject("sa_frequency_type_repeats_on_friday_checkbox").click();
         }
         if (frequencyWeeklyRepeatOnAdvancedDay.equalsIgnoreCase("Saturday")){
           getObject("sa_frequency_type_repeats_on_saturday_checkbox").click();
         }
       }
	   
       if (frequencyRepeatOn.equalsIgnoreCase("Monthly Date")) {         
         getObject("sa_frequency_type_repeats_on_monthly_date_radio").click();
       }
       
       if (frequencyRepeatOn.equalsIgnoreCase("Monthly Advanced")) {         
         getObject("sa_frequency_type_repeats_on_monthly_advanced_radio").click();
         
         if (!frequencyAdvancedFirstDropdown.equalsIgnoreCase("")) {
           new Select(getObject("sa_frequency_type_repeats_on_monthly_advanced_first_dropdown")).selectByVisibleText(frequencyAdvancedFirstDropdown);
         }
        
         if (!frequencyAdvancedSecondDropdown.equalsIgnoreCase("")) {
           new Select(getObject("sa_frequency_type_repeats_on_monthly_advanced_second_dropdown")).selectByVisibleText(frequencyAdvancedSecondDropdown);
         }
       }
       
       if (frequencyRepeatOn.equalsIgnoreCase("Yearly Advanced")) {
         getObject("sa_frequency_type_repeats_on_yearly_advanced_radio").click();
         
         if (!frequencyAdvancedFirstDropdown.equalsIgnoreCase("")) {
           new Select(getObject("sa_frequency_type_repeats_on_yearly_advanced_first_dropdown")).selectByVisibleText(frequencyAdvancedFirstDropdown);
         }
        
         if (!frequencyAdvancedSecondDropdown.equalsIgnoreCase("")) {
           new Select(getObject("sa_frequency_type_repeats_on_yearly_advanced_second_dropdown")).selectByVisibleText(frequencyAdvancedSecondDropdown);
         }
                 
             }
       
       
      if (frequencyRepeatOn.equalsIgnoreCase("Yearly Date")) {         
         getObject("sa_frequency_type_repeats_on_yearly_date_radio").click();
       } 
      
      
       getObject("sa_frequency_type_repeats_popup_save_button").click();

	        
		//driver.findElement(By.xpath("html/body/div[14]/form/div[3]/div/div[5]/input")).click();
		//getObject("freq_rec_data_textarea").sendKeys(recurrenceData);*/
		//Thread.sleep(2000);
		getObject("sa_frequency_adv_creation_textbox").sendKeys(advanceCreation_string);
		
		if(belowMonthly.equalsIgnoreCase("Yes"))
		{
		getObject("sa_frequency_below_monthly_checkbox").click();
		}
		
		Thread.sleep(5000);
		getObject("sa_frequency_submit_button").click();
		/*new Select (getObject("sa_frequency_display_dropdown")).selectByIndex(3);
		
		
			
	      driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterFrequency']/tbody/tr[@role='row']/td[contains(.,'"+ frequencyName +"')]/a/div")).click();
	      Thread.sleep(5000);
	      String frequencyNameShowPage = getObject("sa_frequency_name_show").getText();
	      Assert.assertEquals(frequencyNameShowPage, frequencyName, "Frequency name at show page is -- " +frequencyNameShowPage+ " instead of -- " +frequencyName);
	      String frequencyAdvanceCreationShowPage = getObject("sa_frequency_advance_creation_show").getText();
	      Assert.assertEquals(frequencyAdvanceCreationShowPage, advanceCreation_string, "Frequency advance creation at show page is -- " +frequencyAdvanceCreationShowPage+ 
	          " instead of -- " +advanceCreation_string);
	      String frequencyActiveShowPage = getObject("sa_frequency_active_show").getText();
	      Assert.assertEquals(frequencyActiveShowPage, frequencyActive, "Frequency status at show page is -- " +frequencyActiveShowPage+ " instead of -- " +frequencyActive);
	      String frequencyBelowMonthlyShowPage = getObject("sa_frequency_below_monthly_show").getText();
	      Assert.assertEquals(frequencyBelowMonthlyShowPage, belowMonthly, "Frequency duration at show page is -- " +frequencyBelowMonthlyShowPage+ " instead of -- " +belowMonthly);
	      String frequencyTypeShowPage = getObject("sa_frequency_type_show").getText();
          Assert.assertEquals(frequencyTypeShowPage, frequencyType, "Frequency type at show page is -- " +frequencyTypeShowPage+ " instead of -- " +frequencyType);*/
          fail=false;
         /* APP_LOGS.debug("Sirion Admin Frequency created successfully with frequency name -- "+frequencyName+" advance creation -- "+advanceCreation_string+
              " status -- "+frequencyActive+" duration -- "+belowMonthly+ " type -- "+frequencyType);
         */ 
          getObject("admin_tab_link").click();
	}	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(sirion_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
}
