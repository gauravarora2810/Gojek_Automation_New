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


public class UpdateFrequency extends TestSuiteBase{
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
	public void FrequencyCreation( String frequencyName, String frequencyNameUpdate, String frequencyType, String frequencyTypeUpdate, String frequencyRepeats, 
	    String frequencyRepeatsEvery, String frequencyRepeatOn,  String frequencyWeeklyRepeatOnAdvancedDay, String frequencyAdvancedFirstDropdown, 
	    String frequencyAdvancedSecondDropdown, 
	   String advanceCreation, String advanceCreationUpdate, String belowMonthly, String belowMonthlyUpdate, String frequencyActive) throws InterruptedException 
	
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
		
		int advanceCreation_integer=Double.valueOf(advanceCreationUpdate).intValue();
		String advanceCreation_string = Integer.toString(advanceCreation_integer);
		int repeatsEvery_integer=Double.valueOf(frequencyRepeatsEvery).intValue();
	    String repeatsEvery_string = Integer.toString(repeatsEvery_integer);
		
		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Frequency updation test -- "+frequencyName);
		getObject("admin_tab_link").click();
		getObject("sa_frequency_link").click();
		new Select (getObject("sa_frequency_display_dropdown")).selectByIndex(3);
	      driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterFrequency']/tbody/tr[@role='row']/td[contains(.,'"+ frequencyName +"')]/a/div")).click();
	      Thread.sleep(5000);
	       
           getObject("sa_frequency_edit_button").click();
           Thread.sleep(2000);
           getObject("sa_frequency_name_edit_textbox").clear();
           getObject("sa_frequency_name_edit_textbox").sendKeys(frequencyNameUpdate);
           getObject("sa_frequency_advance_creation_edit_textbox").clear();
           getObject("sa_frequency_advance_creation_edit_textbox").sendKeys(advanceCreation_string);

    if (belowMonthlyUpdate.equalsIgnoreCase("Yes")) {
      getObject("sa_frequency_below_monthly_checkbox").click();
    }
    new Select(getObject("sa_frequency_type_dropdown")).selectByVisibleText(frequencyTypeUpdate);

    getObject("sa_frequency_name_update_button").click();
    

	      /*String frequencyNameShowPage = getObject("sa_frequency_name_show").getText();
	      Assert.assertEquals(frequencyNameShowPage, frequencyNameUpdate, "Frequency name at show page is -- " +frequencyNameShowPage+ " instead of -- " +frequencyNameUpdate);
	      String frequencyAdvanceCreationShowPage = getObject("sa_frequency_advance_creation_show").getText();
	      Assert.assertEquals(frequencyAdvanceCreationShowPage, advanceCreation_string, "Frequency advance creation at show page is -- " +frequencyAdvanceCreationShowPage+ 
	          " instead of -- " +advanceCreation_string);
	      String frequencyActiveShowPage = getObject("sa_frequency_active_show").getText();
	      Assert.assertEquals(frequencyActiveShowPage, frequencyActive, "Frequency status at show page is -- " +frequencyActiveShowPage+ " instead of -- " +frequencyActive);
	      String frequencyBelowMonthlyShowPage = getObject("sa_frequency_below_monthly_show").getText();
	      Assert.assertEquals(frequencyBelowMonthlyShowPage, belowMonthlyUpdate, "Frequency duration at show page is -- " +frequencyBelowMonthlyShowPage+ " instead of -- " +belowMonthlyUpdate);
	      String frequencyTypeShowPage = getObject("sa_frequency_type_show").getText();
          Assert.assertEquals(frequencyTypeShowPage, frequencyTypeUpdate, "Frequency type at show page is -- " +frequencyTypeShowPage+ " instead of -- " +frequencyTypeUpdate);*/

          fail=false;
          /*APP_LOGS.debug("Sirion Admin Frequency updated successfully with frequency name -- "+frequencyNameUpdate+" advance creation -- "+advanceCreation_string+
              " status -- "+frequencyActive+" duration -- "+belowMonthlyUpdate+ " type -- "+frequencyType);*/
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
