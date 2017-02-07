package test.resources.com.sirion.suite.obligation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;


public class ObligationUpdationExisting extends TestSuiteBaseExisting


{

	
	String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=true;
    static boolean skip=false;
    static boolean isTestPass=true;
    
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(obligation_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
    }
    @Test (dataProvider = "getTestData")
    public void ObligationUpdationTest(
       String obligationNameforUpdate, String obTitle, String obDescription, String  obPerformanceType, String  obCategory, String obTimezone, 
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
        
        APP_LOGS.debug("Executing Test Case Obligation Updation");
       
        Thread.sleep(10000);
       
        
        openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
        
        getObject("analytics_link").click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getObject("child_obligaiton_quick_link").click(); 
		Thread.sleep(20000);
        getObject("obligaiton_link").click();
        
        Thread.sleep(20000);
		//driver.findElement(By.xpath("(//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ obligationNameforUpdate +"')]/preceding-sibling::td[1]/a)[1]")).click();
        WebDriverWait wait=new WebDriverWait(driver, 50);
       // wait.until(ExpectedConditions.elementToBeClickable(getObject("ob_id_link")));

 		getObject("ob_id_link").click(); 
        
//        if (!checkElementPresence("supplier_quick_link")) {
//          fail = true;
//          // return; // conditional
//        }

       // getObject("ob_id_link").click(); 
        Thread.sleep(10000);
        //plus_button("ob_plus_button_link"); // web element for plus button on supplier page
//        plus_button("ob_plus_button_link"); // web element for plus button on obligation page
//        Thread.sleep(4000);
//        getObject("ac_create_link_from_ob").click(); // click Action create link 
        
        //driver.findElement(By.xpath("//button[contains(.,'Send For Peer Review')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Edit')]")).click();
        //driver.findElement(By.xpath("//*[@id='kk']/div/div/div[2]/div[1]/div/form/div[4]/ng-form/div/button[4]")).click();
       /// driver.findElement(By.tagName(name))
        boolean obTitle_tag=false;
        try{
        //System.out.println(getObject("ob_title_textbox"));
        
        if(getObject("ob_title_textbox") != null){
        	obTitle_tag = getObject("ob_title_textbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obTitle_tag=false;
        }
        //if(!obTitle.equalsIgnoreCase("")  &&  @FindBy(id = "myElementID").isDisplayed());
        System.out.println("not inserted into if condition");
        if(!obTitle.equalsIgnoreCase("")  &&  obTitle_tag)
        {    
        	getObject("ob_title_textbox").clear();
        	getObject("ob_title_textbox").sendKeys(obTitle); // name
        }
        
        System.out.println("not inserted into if condition");
        
        
        boolean obDescription_tag=false;
        try{
        //System.out.println(getObject("ob_desc_textarea"));
        
        if(getObject("ob_desc_textarea") != null){
        	obDescription_tag = getObject("ob_desc_textarea").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obDescription_tag=false;
        }
        
        if(!obDescription.equalsIgnoreCase("") && obDescription_tag){    
        	getObject("ob_desc_textarea").clear(); 
        	getObject("ob_desc_textarea").sendKeys(obDescription); // name
        }
        
        boolean obPerformanceType_tag=false;
        try{
        //System.out.println(getObject("ob_perf_type_dropdown"));
        
        if(getObject("ob_perf_type_dropdown") != null){
        	obPerformanceType_tag = getObject("ob_perf_type_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obPerformanceType_tag=false;
        }
        if (!obPerformanceType.equalsIgnoreCase("") && obPerformanceType_tag) {
          new Select(getObject("ob_perf_type_dropdown")).selectByVisibleText(obPerformanceType);
        }  
        
        boolean obCategory_tag=false;
        try{
        //System.out.println(getObject("ob_category_dropdown"));
        
        if(getObject("ob_category_dropdown") != null){
        	obCategory_tag = getObject("ob_category_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obCategory_tag=false;
        }
        
        
        if (!obCategory.equalsIgnoreCase("") && obCategory_tag) {
          new Select(getObject("ob_category_dropdown")).selectByVisibleText(obCategory);
        } 
        
        
        boolean obTimezone_tag=false;
        try{
       // System.out.println(getObject("ob_timezone_dropdown"));
        
        if(getObject("ob_timezone_dropdown") != null){
        	obTimezone_tag = getObject("ob_timezone_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obTimezone_tag=false;
        }
        if (!obTimezone.equalsIgnoreCase("") && obTimezone_tag) {
          new Select(getObject("ob_timezone_dropdown")).selectByVisibleText(obTimezone);
        } 
        
        
        boolean obDeliveryCountries_tag=false;
        try{
        //System.out.println(getObject("ob_delcountry_multi"));
        
        if(getObject("ob_delcountry_multi") != null){
        	obDeliveryCountries_tag = getObject("ob_delcountry_multi").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obDeliveryCountries_tag=false;
        }
        if (!obDeliveryCountries.equalsIgnoreCase("") && obDeliveryCountries_tag) {
          new Select(getObject("ob_delcountry_multi")).selectByVisibleText(obDeliveryCountries);
        } 
        
        boolean obCurrency_tag=false;
        try{
        //System.out.println(getObject("ob_currency_dropdown"));
        
        if(getObject("ob_currency_dropdown") != null){
        	obCurrency_tag = getObject("ob_currency_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obCurrency_tag=false;
        }
       
        if (!obCurrency.equalsIgnoreCase("") && obCurrency_tag) {
          new Select(getObject("ob_currency_dropdown")).selectByVisibleText(obCurrency);
        }
        
        boolean obSupplierAccess_tag=false;
        try{
        //System.out.println(getObject("ob_supplier_access_checkbox"));
        
        if(getObject("ob_supplier_access_checkbox") != null){
        	obSupplierAccess_tag = getObject("ob_supplier_access_checkbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obSupplierAccess_tag=false;
        }
       
        if (obSupplierAccess.equalsIgnoreCase("Yes") && obSupplierAccess_tag){
          getObject("ob_supplier_access_checkbox").click();
        }
        
        boolean obTier_tag=false;
        try{
        //System.out.println(getObject("ob_tier_dropdown"));
        
        if(getObject("ob_tier_dropdown") != null){
        	obTier_tag = getObject("ob_tier_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obTier_tag=false;
        }
        
        if (!obTier.equalsIgnoreCase("") && obTier_tag) {
          new Select(getObject("ob_tier_dropdown")).selectByVisibleText(obTier);
        }
        
        boolean obPriority_tag=false;
        try{
       // System.out.println(getObject("ob_priority_dropdown"));
        
        if(getObject("ob_priority_dropdown") != null){
        	obPriority_tag = getObject("ob_priority_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obPriority_tag=false;
        }
        
        if (!obPriority.equalsIgnoreCase("") && obPriority_tag) {
          new Select(getObject("ob_priority_dropdown")).selectByVisibleText(obPriority);
        }
        
        
        boolean obPhase_tag=false;
        try{
        //System.out.println(getObject("ob_phase_dropdown"));
        
        if(getObject("ob_phase_dropdown") != null){
        	obPhase_tag = getObject("ob_phase_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obPhase_tag=false;
        }
        
        if (!obPhase.equalsIgnoreCase("") && obPhase_tag) {
          new Select(getObject("ob_phase_dropdown")).selectByVisibleText(obPhase);
        }
        
        boolean obTriggered_tag=false;
        try{
        //System.out.println(getObject("ob_triggered_checkbox"));
        
        if(getObject("ob_triggered_checkbox") != null){
        	obTriggered_tag = getObject("ob_triggered_checkbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obTriggered_tag=false;
        }
        
        if (obTriggered.equalsIgnoreCase("Yes") && obTriggered_tag){
          getObject("ob_triggered_checkbox").click();
        }
        
        boolean obReference_tag=false;
        try{
        //System.out.println(getObject("ob_reference_dropdown"));
        
        if(getObject("ob_reference_dropdown") != null){
        	obReference_tag = getObject("ob_reference_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obReference_tag=false;
        }
        
        
        if (!obReference.equalsIgnoreCase("") && obReference_tag) {
          new Select(getObject("ob_reference_dropdown")).selectByVisibleText(obReference);
        }
        
        boolean obClause_tag=false;
        try{
        //System.out.println(getObject("ob_clause_textbox"));
        
        if(getObject("ob_clause_textbox") != null){
        	obClause_tag = getObject("ob_clause_textbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obClause_tag=false;
        }
        
        if(!obClause.equalsIgnoreCase("") && obClause_tag){    
        getObject("ob_clause_textbox").clear();
        getObject("ob_clause_textbox").sendKeys(obClause); // name
        }
        
        boolean obPageNumber_tag=false;
        try{
        //System.out.println(getObject("ob_page_no_textbox"));
        
        if(getObject("ob_page_no_textbox") != null){
        	obPageNumber_tag = getObject("ob_page_no_textbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obPageNumber_tag=false;
        }
        
        if(!obPageNumber.equalsIgnoreCase("") && obPageNumber_tag){    
        	getObject("ob_page_no_textbox").clear();
        	getObject("ob_page_no_textbox").sendKeys(obPageNumber); // name
        }
        
        boolean obFrequencyType_tag=false;
        try{
        //System.out.println(getObject("ob_frequency_type_dropdown"));
        
        if(getObject("ob_frequency_type_dropdown") != null){
        	obFrequencyType_tag = getObject("ob_frequency_type_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obFrequencyType_tag=false;
        }
        
        
        if (!obFrequencyType.equalsIgnoreCase("") && obFrequencyType_tag) {
          new Select(getObject("ob_frequency_type_dropdown")).selectByVisibleText(obFrequencyType);
        }
        
        
        boolean obFrequency_tag=false;
        try{
        //System.out.println(getObject("ob_frequency_dropdown"));
        
        if(getObject("ob_frequency_dropdown") != null){
        	obFrequency_tag = getObject("ob_frequency_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obFrequency_tag=false;
        }
        
        
        if (!obFrequency.equalsIgnoreCase("") && obFrequency_tag) {
          new Select(getObject("ob_frequency_dropdown")).selectByVisibleText(obFrequency);
        }
        
        
        boolean obWeekType_tag=false;
        try{
        //System.out.println(getObject("ob_week_type_dropdown"));
        
        if(getObject("ob_week_type_dropdown") != null){
        	obWeekType_tag = getObject("ob_week_type_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obWeekType_tag=false;
        }
        
        
        if (!obWeekType.equalsIgnoreCase("") && obWeekType_tag) {
          new Select(getObject("ob_week_type_dropdown")).selectByVisibleText(obWeekType);
        }

/*        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 250)"); //y value '250' can be altered
*/        
      //  driver.findElement(By.xpath("//*[@id='kk']/div/div/div[3]/div/div/form/div[4]/ng-form/div/button[1]")).click();
        
//        Date date = new Date();
//        int current_month = date.getMonth();
//
//        
        /*driver.findElement(By.name("startDate")).click();
        Thread.sleep(3000);
        Double temp_obStartDateYear_double = Double.parseDouble(obStartDateYear);
        int temp_obStartDateYear_int = temp_obStartDateYear_double.intValue();
        String obStartDateYear_string = Integer.toString(temp_obStartDateYear_int);

        WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
        System.out.println(datepicker_ui.isDisplayed());
        if (datepicker_ui.isDisplayed() == true) {
          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
          new Select(datepicker_ui_year).selectByVisibleText(obStartDateYear_string);
        }

        Double temp_obStartDateMonth_double = Double.parseDouble(obStartDateMonth);
        int temp_obStartDateMonth_int = temp_obStartDateMonth_double.intValue();
        System.out.println(" obStartDateMonth " + temp_obStartDateMonth_int);

        int click2 = current_month - temp_obStartDateMonth_int;
        System.out.println("click " + click2);
        for (; click2 >= 0; click2 = click2 - 1) {
          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
        }
        Double temp_obStartDateDate_double = Double.parseDouble(obStartDateDate);
        int temp_obStartDateDate_int = temp_obStartDateDate_double.intValue();
        String obStartDateDate_string = Integer.toString(temp_obStartDateDate_int);
        driver.findElement(By.linkText(obStartDateDate_string)).click();
        

        driver.findElement(By.name("expDate")).click();
        Double temp_obEndDateYear_double = Double.parseDouble(obEndDateYear);
        int temp_obEndDateYear_int = temp_obEndDateYear_double.intValue();
        String obEndDateYear_string = Integer.toString(temp_obEndDateYear_int);

        WebElement datepicker_ui1 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
        System.out.println(datepicker_ui1.isDisplayed());
        if (datepicker_ui1.isDisplayed() == true) {
          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
          new Select(datepicker_ui_year).selectByVisibleText(obEndDateYear_string);
        }

        Double temp_obEndDateMonth_double = Double.parseDouble(obEndDateMonth);
        int temp_obEndDateMonth_int = temp_obEndDateMonth_double.intValue();
        System.out.println(" obEndDateMonth " + temp_obEndDateMonth_int);

        int click_obEndDateMonth = current_month - temp_obEndDateMonth_int;
        System.out.println("click " + click_obEndDateMonth);
        for (; click_obEndDateMonth >= 0; click_obEndDateMonth = click_obEndDateMonth - 1) {
          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
        }
        Double temp_obEndDateDate_double = Double.parseDouble(obEndDateDate);
        int temp_obEndDateDate_int = temp_obEndDateDate_double.intValue();
        String obEndDateDate_string = Integer.toString(temp_obEndDateDate_int);
        driver.findElement(By.linkText(obEndDateDate_string)).click();
        
        if (obIncludeStartDate.equalsIgnoreCase("Yes")){
          getObject("ob_include_start_date_checkbox").click();
        }
        
        if (obIncludeEndDate.equalsIgnoreCase("Yes")){
          getObject("ob_include_end_date_checkbox").click();
        }

        
        driver.findElement(By.name("patternDate")).click();
        Double temp_obPatternDateYear_double = Double.parseDouble(obPatternDateYear);
        int temp_obPatternDateYear_int = temp_obPatternDateYear_double.intValue();
        String obPatternDateYear_string = Integer.toString(temp_obPatternDateYear_int);

        WebElement datepicker_ui11 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
        System.out.println(datepicker_ui11.isDisplayed());
        if (datepicker_ui11.isDisplayed() == true) {
          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
          new Select(datepicker_ui_year).selectByVisibleText(obPatternDateYear_string);
        }

        Double temp_obPatternDateMonth_double = Double.parseDouble(obPatternDateMonth);
        int temp_obPatternDateMonth_int = temp_obPatternDateMonth_double.intValue();
        System.out.println(" obPatternDateMonth " + temp_obPatternDateMonth_int);

        int click_obPatternDateMonth = current_month - temp_obPatternDateMonth_int;
        System.out.println("click " + click_obPatternDateMonth);
        for (; click_obPatternDateMonth >= 0; click_obPatternDateMonth = click_obPatternDateMonth - 1) {
          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
        }
        Double temp_obPatternDateDate_double = Double.parseDouble(obPatternDateDate);
        int temp_obPatternDateDate_int = temp_obPatternDateDate_double.intValue();
        String obPatternDateDate_string = Integer.toString(temp_obPatternDateDate_int);
        driver.findElement(By.linkText(obPatternDateDate_string)).click();

        
        driver.findElement(By.name("effectiveDate")).click();
        Double temp_obEffectiveDateYear_double = Double.parseDouble(obEffectiveDateYear);
        int temp_obEffectiveDateYear_int = temp_obEffectiveDateYear_double.intValue();
        String obEffectiveDateYear_string = Integer.toString(temp_obEffectiveDateYear_int);

        WebElement datepicker_ui111 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
        System.out.println(datepicker_ui111.isDisplayed());
        if (datepicker_ui111.isDisplayed() == true) {
          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
          new Select(datepicker_ui_year).selectByVisibleText(obEffectiveDateYear_string);
        }

        Double temp_obEffectiveDateMonth_double = Double.parseDouble(obEffectiveDateMonth);
        int temp_obEffectiveDateMonth_int = temp_obEffectiveDateMonth_double.intValue();
        System.out.println(" obEffectiveDateMonth " + temp_obEffectiveDateMonth_int);

        int click_obEffectiveDateMonth = current_month - temp_obEffectiveDateMonth_int;
        System.out.println("click " + click_obEffectiveDateMonth);
        for (; click_obEffectiveDateMonth >= 0; click_obEffectiveDateMonth = click_obEffectiveDateMonth - 1) {
          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
        }
        Double temp_obEffectiveDateDate_double = Double.parseDouble(obEffectiveDateDate);
        int temp_obEffectiveDateDate_int = temp_obEffectiveDateDate_double.intValue();
        String obEffectiveDateDate_string = Integer.toString(temp_obEffectiveDateDate_int);
        driver.findElement(By.linkText(obEffectiveDateDate_string)).click();
*/       
        
        boolean obResponsibity_tag=false;
        try{
        //System.out.println(getObject("ob_responsibility_dropdown"));
        
        if(getObject("ob_responsibility_dropdown") != null){
        	obResponsibity_tag = getObject("ob_responsibility_dropdown").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obResponsibity_tag=false;
        }
        
        if (!obResponsibity.equalsIgnoreCase("") && obResponsibity_tag) {
          new Select(getObject("ob_responsibility_dropdown")).selectByVisibleText(obResponsibity);
        }
        
        boolean obFinancialImpactApplicable_tag=false;
        try{
        //System.out.println(getObject("ob_financial_imapct_applicable_checkbox"));
        
        if(getObject("ob_financial_imapct_applicable_checkbox") != null){
        	obFinancialImpactApplicable_tag = getObject("ob_financial_imapct_applicable_checkbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obFinancialImpactApplicable_tag=false;
        }
       
        if (obFinancialImpactApplicable.equalsIgnoreCase("Yes") && obFinancialImpactApplicable_tag){
          getObject("ob_financial_imapct_applicable_checkbox").click();
        }
        
        
        boolean obCreditImpactApplicable_tag=false;
        try{
       // System.out.println(getObject("ob_credit_imapct_applicable_checkbox"));
        
        if(getObject("ob_credit_imapct_applicable_checkbox") != null){
        	obCreditImpactApplicable_tag = getObject("ob_credit_imapct_applicable_checkbox").isEnabled();
        }
        }
        catch(Exception e)
        {
        	obCreditImpactApplicable_tag=false;
        }
        
        
        if (obCreditImpactApplicable.equalsIgnoreCase("Yes") && obCreditImpactApplicable_tag){
          getObject("ob_credit_imapct_applicable_checkbox").click();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        

//      driver.findElement(By.name("startDate")).click();
//      //WebElement fromDate_datePicker=driver.findElement(By.xpath(".//*[@id='dp1430214695387']"));
//       Thread.sleep(3000);
//      //wait.until(ExpectedConditions.visibilityOf(fromDate_datePicker));     
//      //System.out.println("Hello 1");
//      //System.out.println(fromDate_datePicker.isDisplayed());
//          //      fromDate_datePicker.click();
//              //  System.out.println("Hello 1");
//                  WebElement datepicker_ui=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
//                  System.out.println(datepicker_ui.isDisplayed());
//                  if (datepicker_ui.isDisplayed()==true)
//                  {
//                  WebElement datepicker_ui_year=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"));
//                  new Select(datepicker_ui_year).selectByIndex(1);
//                  }
//          //Select year=new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select")));
//          //  year.selectByValue("2016");
//              driver.findElement(By.linkText("3")).click();
//          
//              new Select (getObject("ob_priority")).selectByVisibleText(obligationPriority); // priority
//              
////                Double dobligationPhase = Double.parseDouble(obligationPhase);
////                int idobligationPhase = dobligationPhase.intValue();
//               
//              getObject("ob_phase").sendKeys(obligationPhase); // Phase 
//              getObject("ob_pageno").sendKeys(obligationPageNumber);  //Page number
//              
//              
//              new Select (getObject("ob_frequencyType")).selectByVisibleText(obligationFrequencyType); // Frequency type
//              new Select (getObject("ob_frequency")).selectByVisibleText(obligationFrequency); // Frequency 
//              new Select (getObject("ob_weektype")).selectByVisibleText(obligationWeekType); // week type 
//              
//              if (!(obligationIncludeStartDate=="")){
//              getObject("ob_includeStartDate").click(); // Inculde start date check box
//              }
        //      getObject("ob_enddate").click();
                
//           Thread.sleep(10000);
//           driver.findElement(By.name("expDate")).click();
//           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//          
                //WebElement fromDate_datePicker=driver.findElement(By.xpath("//*[@id='dp1430831314551']"));
                
            //  wait.until(ExpectedConditions.visibilityOf(fromDate_datePicker));       
            //  System.out.println("Hello 1");
            //  System.out.println(fromDate_datePicker.isDisplayed());
                //          fromDate_datePicker.click();
                //          System.out.println("Hello 1");
//                          WebElement datepicker_ui2=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']"));
//                          System.out.println(datepicker_ui2.isDisplayed());
//                          if (datepicker_ui2.isDisplayed()==true)
//                          {
//                          WebElement datepicker_ui_year=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select"));
//                          new Select(datepicker_ui_year).selectByIndex(1);
//                          }
//                  Select year=new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select")));
//                      year.selectByValue("2016");
//                      driver.findElement(By.linkText("2")).click();           
            
                
                //WebDriverWait wait = new WebDriverWait(driver, 60); 
                //wait.until(ExpectedConditions.visibilityOf(getObject("ob_savebutton")));
                
                //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                
                //*[@id='elem_332']/input
                
                
//            if (!(obligationFinancialImapctCheckBox=="")){
//              getObject("ob_financialImapct").click(); // Inculde start date check box
//              }
//              
//              getObject("ob_financialImpactClause").sendKeys(obligationFinancialImpactClause);
//              getObject("ob_financialImpactValue").sendKeys(obligationFinancialImpactValue);
//              getObject("ob_financialImpactDays").sendKeys(obligationFinancialImpactDays);
//              new Select (getObject("ob_financialImpactType")).selectByVisibleText(obligationFinancialImpactType);
//              
//              Thread.sleep(5000); 
               // getObject("ob_savebutton").click();
              //.sleep(2000);
        		
        
        driver.findElement(By.xpath("//button[contains(.,'Update')]")).click();
               
//                String ob_id = getObject("ob_popup_id").getText();
//                
//                APP_LOGS.debug("Obligation updated successfully with Obligation id " + ob_id);
//
//                getObject("ob_popup_ok_button").click();
//
//                APP_LOGS.debug("Quick Search the created action with Obligation id " + ob_id);
//                getObject("quick_search_textbox").clear();
//                getObject("quick_search_textbox").sendKeys(ob_id);
//                //getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
//                getObject("quick_search_textbox").sendKeys(Keys.ENTER);
//                String obIdFromShowPage = getObject("ob_show_id").getText();
//
//                Assert.assertEquals(obIdFromShowPage, ob_id);
//
//                APP_LOGS.debug("Obligation show page open successfully with obligation id " + ob_id);
                
                
//                String OBTitleShowPage= getObject("ob_title_show").getText();
//                try
//                {
//                Assert.assertEquals(OBTitleShowPage, obTitle ,"OB Title is= " +OBTitleShowPage +"instead of" + obTitle);
//                System.out.println("OB Title is= "+OBTitleShowPage + " same as " + obTitle);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Title is= "+OBTitleShowPage +"instead of" + obTitle);
//                } 
//                
//                String OBDescriptionShowPage= getObject("ob_description_show").getText();
//                try
//                {
//                Assert.assertEquals(OBDescriptionShowPage, obDescription ,"OB Description is= " +OBDescriptionShowPage +"instead of" + obDescription);
//                System.out.println("OB Description is= "+OBDescriptionShowPage +" same as " + obDescription);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Description is= "+OBDescriptionShowPage +"instead of" + obDescription);
//                } 
//                
//                
//                String OBPerformanceTypeShowPage= getObject("ob_performancetype_show").getText();
//                try
//                {
//                Assert.assertEquals(OBPerformanceTypeShowPage, obPerformanceType ,"OB Performance Type is= " +OBPerformanceTypeShowPage +"instead of" + obPerformanceType);
//                System.out.println("OB Performance Type is= "+OBPerformanceTypeShowPage +" same as " + obPerformanceType);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Performance Type is= "+OBPerformanceTypeShowPage +"instead of" + obPerformanceType);
//                }
//                
//                String OBCategoryShowPage= getObject("ob_category_show").getText();
//                try
//                {
//                Assert.assertEquals(OBCategoryShowPage, obCategory ,"OB Category is= " +OBCategoryShowPage +"instead of" + obCategory);
//                System.out.println("OB Catefory is= "+OBCategoryShowPage +" same as " + obCategory);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Catefory is= "+OBCategoryShowPage +"instead of" + obCategory);
//                }
//                
//                
//                String OBTimeZoneShowPage= getObject("ob_timezone_show").getText();
//                try
//                {
//                Assert.assertEquals(OBTimeZoneShowPage, obTimezone ,"OB Time Zone is= " +OBTimeZoneShowPage +"instead of" + obTimezone);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Time Zone is= "+OBTimeZoneShowPage +"instead of" + obTimezone);
//                }
//                
//                String OBDeliveryCountriesShowPage= getObject("ob_delcountry_show").getText();
//                try
//                {
//                Assert.assertEquals(OBDeliveryCountriesShowPage, obDeliveryCountries ,"OB Delivery Countries are= " +OBDeliveryCountriesShowPage +"instead of" + obDeliveryCountries);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Delivery Countries are= "+OBDeliveryCountriesShowPage +"instead of" + obDeliveryCountries);
//                }
//                
//                
//                
//                String OBCurrencyShowPage= getObject("ob_currency_show").getText();
//                try
//                {
//                Assert.assertEquals(OBCurrencyShowPage, obCurrency ,"OB Currency is= " +OBCurrencyShowPage +"instead of" + obCurrency);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Currency is= "+OBCurrencyShowPage +"instead of" + obCurrency);
//                }
//                
//                
//                String OBSupplierAccessShowPage= getObject("ob_supplieraccess_show").getText();
//                try
//                {
//                Assert.assertEquals(OBSupplierAccessShowPage, obSupplierAccess ,"OB Supplier Access is= " +OBSupplierAccessShowPage +"instead of" + obSupplierAccess);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Supplier Access is= "+OBSupplierAccessShowPage +"instead of" + obSupplierAccess);
//                }
//                
//                
//                
//                String OBTierShowPage= getObject("ob_tier_show").getText();
//                try
//                {
//                Assert.assertEquals(OBTierShowPage, obTier ,"OB Tier is= " +OBTierShowPage +"instead of" + obTier);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Tier is= "+OBTierShowPage +"instead of" + obTier);
//                }
//                
//                
//                String OBPriorityShowPage= getObject("ob_priority_show").getText();
//                try
//                {
//                Assert.assertEquals(OBPriorityShowPage, obPriority ,"OB Priority is= " +OBPriorityShowPage +"instead of" + obPriority);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Priority is= "+OBPriorityShowPage +"instead of" + obPriority);
//                }
//                
//                
//                
//                String OBPhaseShowPage= getObject("ob_phase_show").getText();
//                try
//                {
//                Assert.assertEquals(OBPhaseShowPage, obPhase ,"OB Phase is= " +OBPhaseShowPage +"instead of" + obPhase);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Phase is= "+OBPhaseShowPage +"instead of" + obPhase);
//                }
//                
//                
//                
//                String OBTrigggeredShowPage= getObject("ob_triggered_show").getText();
//                try
//                {
//                Assert.assertEquals(OBTrigggeredShowPage, obTriggered ,"OB Triggered is= " +OBTrigggeredShowPage +"instead of" + obTriggered);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Triggered is= "+OBTrigggeredShowPage +"instead of" + obTriggered);
//                }
//                
//                
//                
//                String OBReferenceshowPage= getObject("ob_references_show").getText();
//                try
//                {
//                Assert.assertEquals(OBReferenceshowPage, obReference,"OB Reference is= " +OBReferenceshowPage +"instead of" + obReference);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Reference is= "+OBReferenceshowPage +"instead of" + obReference);
//                }
//                
//                
//                String OBClauseShowPage= getObject("ob_clause_show").getText();
//                try
//                {
//                Assert.assertEquals(OBClauseShowPage, obClause ,"OB Clause is= " +OBClauseShowPage +"instead of" + obClause);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Clause is= "+OBClauseShowPage +"instead of" + obClause);
//                }
//                
//                
//                String OBPageNumberShowPage= getObject("ob_pagenumber_show").getText();
//                try
//                {
//                Assert.assertEquals(OBPageNumberShowPage, obPageNumber ,"OB Page Number is= " +OBPageNumberShowPage +"instead of" + obPageNumber);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Page Number is= "+OBPageNumberShowPage +"instead of" + obPageNumber);
//                }
//                
//                String OBFrequencyTypeShowPage= getObject("ob_frequency_type_show").getText();
//                try
//                {
//                Assert.assertEquals(OBFrequencyTypeShowPage, obFrequencyType ,"OB Frequency Type is= " +OBFrequencyTypeShowPage +"instead of" + obFrequencyType);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Frequency Type is= "+OBFrequencyTypeShowPage +"instead of" + obFrequencyType);
//                }
//                
//                String OBFrequencyShowPage= getObject("ob_frequency_show").getText();
//                try
//                {
//                Assert.assertEquals(OBFrequencyShowPage, obFrequency ,"OB Frequency is= " +OBFrequencyShowPage +"instead of" + obFrequency);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Frequency is= "+OBFrequencyShowPage +"instead of" + obFrequency);
//                }
//                
//               
//                String OBWeekTypeShowPage= getObject("ob_frequency_show").getText();
//                try
//                {
//                Assert.assertEquals(OBFrequencyShowPage, obFrequency ,"OB Frequency is= " +OBFrequencyShowPage +"instead of" + obFrequency);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Frequency is= "+OBFrequencyShowPage +"instead of" + obFrequency);
//                }
//                
//                String OBResponsibilityShowPage= getObject("ob_frequency_show").getText();
//                try
//                {
//                Assert.assertEquals(OBFrequencyShowPage, obFrequency ,"OB Frequency is= " +OBFrequencyShowPage +"instead of" + obFrequency);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Frequency is= "+OBFrequencyShowPage +"instead of" + obFrequency);
//                }
//                
//                
//                
//                String OBCreditImpactApplicableShowPage= getObject("ob_creditimpact_show").getText();
//                try
//                {
//                Assert.assertEquals(OBCreditImpactApplicableShowPage, obCreditImpactApplicable ,"OB Credit Impact Applicable is= " +OBCreditImpactApplicableShowPage +"instead of" + obCreditImpactApplicable);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Credit Impact Applicable is= "+OBCreditImpactApplicableShowPage +"instead of" + obCreditImpactApplicable);
//                }
//                
//                String OBFinancialImpactShowPage= getObject("ob_financialimpact_show").getText();
//                try
//                {
//                Assert.assertEquals(OBFinancialImpactShowPage, obFinancialImpactApplicable ,"OB Financial Impact Applicable is= " +OBFinancialImpactShowPage +"instead of" + obFinancialImpactApplicable);
//                }
//                catch(Throwable e)
//                {
//              	  System.out.println("OB Financial Impact Applicable is= "+OBFinancialImpactShowPage +"instead of" + obFinancialImpactApplicable);
//                }
//                
//                
//              
//                
                fail = false; // this executes if assertion passes
//
//                // driver.findElement(By.xpath(".//*[@id='h-analytics']/a")).click();
//                getObject("analytics_link").click();
//                getObject("contract_quick_link").click();
        
        

                // need to put the above code in obligation workflow after publish task because in update page assertion gives problem i.e. 
              // On update page some fields become non editable and some remains editable thus we are moving all the assertion from update file and even also from create file 
              // to only workflow file [However it is still unknown that after publish all fields become non editable]
                               
        
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(obligation_suite_xls, this.getClass().getSimpleName()) ;
    }

	
	
	
}
