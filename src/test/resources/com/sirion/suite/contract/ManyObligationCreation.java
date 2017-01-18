package test.resources.com.sirion.suite.contract;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ManyObligationCreation extends TestSuiteBase{
    String runmodes[]=null;
    static int count=-1;
    static boolean fail=false;
    static boolean skip=false;
    static boolean isTestPass=true;
    
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(contract_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
    }
    
    @SuppressWarnings("deprecation")
	@Test (dataProvider = "getTestData")
    public void ObligationCreationTest(
        String obTitle, String obDescription, String  obPerformanceType, String  obCategory, String obTimezone, 
        String obDeliveryCountries, String    obCurrency, String obSupplierAccess, String   obTier, String obPriority, String obPhase, String    obTriggered, 
        String    obReference, String    obClause, String   obPageNumber, String   obFrequencyType, String    obFrequency, String    obWeekType, 
        String obStartDateDate, String    obStartDateMonth, String   obStartDateYear, 
        String    obEndDateDate, String  obEndDateMonth, String obEndDateYear, 
        String  obIncludeStartDate, String obIncludeEndDate, 
        String   obPatternDateDate, String  obPatternDateMonth, String obPatternDateYear, 
        String  obEffectiveDateDate, String    obEffectiveDateMonth, String   obEffectiveDateYear, 
        String    obResponsibity, String obFinancialImpactApplicable, String    obCreditImpactApplicable       
            ) throws InterruptedException {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
        APP_LOGS.debug("Executing Test Case Obligation Creation");
        
        
        openBrowser();
        endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
        
        for(int i=1032; i<3000; i++){
          String co_id= "CO0"+i;
          getObject("quick_search_textbox").sendKeys(co_id);
          getObject("quick_search_textbox").sendKeys(Keys.ENTER);
          Thread.sleep(10000);

       

        plus_button("co_plus_button_link"); 
        getObject("ob_create_link").click();
        
        if(!obTitle.equalsIgnoreCase("")){    
        getObject("ob_title_textbox").sendKeys(obTitle); 
        }
        if(!obDescription.equalsIgnoreCase("")){    
        getObject("ob_desc_textarea").sendKeys(obDescription); 
        }
        if (!obPerformanceType.equalsIgnoreCase("")) {
          new Select(getObject("ob_perf_type_dropdown")).selectByVisibleText(obPerformanceType);
        }       
        if (!obCategory.equalsIgnoreCase("")) {
          new Select(getObject("ob_category_dropdown")).selectByVisibleText(obCategory);
        } 
        if (!obTimezone.equalsIgnoreCase("")) {
          new Select(getObject("ob_timezone_dropdown")).selectByVisibleText(obTimezone);
        } 
        if (!obDeliveryCountries.equalsIgnoreCase("")) {
          new Select(getObject("ob_delcountry_multi")).selectByVisibleText(obDeliveryCountries);
        } 
        if (!obCurrency.equalsIgnoreCase("")) {
          new Select(getObject("ob_currency_dropdown")).selectByVisibleText(obCurrency);
        }
        if (obSupplierAccess.equalsIgnoreCase("Yes")){
          getObject("ob_supplier_access_checkbox").click();
        }
        if (!obTier.equalsIgnoreCase("")) {
          new Select(getObject("ob_tier_dropdown")).selectByVisibleText(obTier);
        }
        if (!obPriority.equalsIgnoreCase("")) {
          new Select(getObject("ob_priority_dropdown")).selectByVisibleText(obPriority);
        }
        if (!obPhase.equalsIgnoreCase("")) {
          new Select(getObject("ob_phase_dropdown")).selectByVisibleText(obPhase);
        }
        if (obTriggered.equalsIgnoreCase("Yes")){
          getObject("ob_triggered_checkbox").click();
        }
        if (!obReference.equalsIgnoreCase("")) {
          new Select(getObject("ob_reference_dropdown")).selectByVisibleText(obReference);
        }
        if(!obClause.equalsIgnoreCase("")){    
        getObject("ob_clause_textbox").sendKeys(obClause); // name
        }
        if(!obPageNumber.equalsIgnoreCase("")){    
        getObject("ob_page_no_textbox").sendKeys(obPageNumber); // name
        }
        if (!obFrequencyType.equalsIgnoreCase("")) {
          new Select(getObject("ob_frequency_type_dropdown")).selectByVisibleText(obFrequencyType);
        }
        if (!obFrequency.equalsIgnoreCase("")) {
          new Select(getObject("ob_frequency_dropdown")).selectByVisibleText(obFrequency);
        }
        
        
        if (!obWeekType.equalsIgnoreCase("")) {
          new Select(getObject("ob_week_type_dropdown")).selectByVisibleText(obWeekType);
        }

       Date date = new Date();
        int current_month =  date.getMonth();
        
        if (obIncludeStartDate.equalsIgnoreCase("Yes")){
          getObject("ob_include_start_date_checkbox").click();
        }
        
        if (obIncludeEndDate.equalsIgnoreCase("Yes")){
          getObject("ob_include_end_date_checkbox").click();
        }
 
        if (!obResponsibity.equalsIgnoreCase("")) {
          new Select(getObject("ob_responsibility_dropdown")).selectByVisibleText(obResponsibity);
        }       
                driver.findElement(By.xpath(".//*[@id='kk']/div/div/div[2]/div/div/form/div[4]/ng-form/div/button[1]")).click();
                Thread.sleep(10000);
               
                String ob_id = getObject("ob_popup_id").getText();
                
                APP_LOGS.debug("Obligation created successfully with Obligation id " + ob_id);

                getObject("ob_popup_ok_button").click();

                APP_LOGS.debug("Quick Search the created action with Obligation id " + ob_id);

                getObject("analytics_link").click();
        } 
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(contract_suite_xls, this.getClass().getSimpleName()) ;
    }

}
