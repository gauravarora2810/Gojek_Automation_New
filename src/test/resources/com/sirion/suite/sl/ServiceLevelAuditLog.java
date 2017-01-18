package test.resources.com.sirion.suite.sl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ServiceLevelAuditLog extends TestSuiteBase


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
	        
	        if(!TestUtil.isTestCaseRunnable(sl_suite_xls,this.getClass().getSimpleName())){
	            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
	            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
	        }
	        // load the run modes off the tests
	        runmodes=TestUtil.getDataSetRunmodes(sl_suite_xls, this.getClass().getSimpleName());
	    }
	    
	    @Test (dataProvider = "getTestData")
	    public void slCommunicationTest(
	            String slContractName, String slTitle, String  slDescription, String   slid, String    slCategory, String  slSubCategory, String   slSL, String    slTimezone, 
	            String  slDeliveryCountries, String slCurrency, String  slSupplierAccess, String    slTier, String  slPriority, String  slReferences, 
	            String    slClause, String    slPageNumber, String    slApplicationGroup, String  slApplication, String   slMinMaxSelection, String   slUnitOfSlMeasurement, 
	            String   slMinMax, String    slExpected, String  slSignificantlyMinMax, String   slMeasurementWindow, 
	            String  slYtdStartDateDate, String  slYtdStartDateMonth, String slYtdStartDateYear, 
	            String  slCreditApplicable, String  slSubjectToContinousImprovement, String slFrequencyType, String slComputationFrequency, String  slFrequency, String slWeekType, 
	            String  slStartDateDate, String slStartDateMonth, String    slStartDateYear, 
	            String  slEndDateDate, String   slEndDateMonth, String  slEndDateYear, 
	            String  slPatternDateDate, String   slPatternDateMonth, String  slPatternDateYear, 
	            String  slEffectiveDateDate, String slEffectiveDateMonth, String    slEffectiveDateYear, 
	            String  slResponsibility, String  slFinancialImpactApplicable, String slCreditImpactApplicable, String slComment,
	            String slRequestedBy, String slChangeRequest, String slUploadedFile      
	            ) throws InterruptedException {
	        // test the runmode of current dataset
	        count++;
	        if(!runmodes[count].equalsIgnoreCase("Y")){
	            skip=true;
	            throw new SkipException("Runmode for test set data set to no "+count);
	        }
	        
	        APP_LOGS.debug("Executing Test Case Service Level Communication");
	        
	        
//	        if (!checkElementPresence("supplier_quick_link")) {
//	          fail = true;
//	          // return; // conditional
//	        }

	        
	        openBrowser();
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			getObject("analytics_link").click();
			getObject("contract_quick_link").click(); 
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ slContractName +"')]/preceding-sibling::td[1]/a")).click();
	        
	        //getObject("contract_id_link").click(); //click on contract id

	        plus_button("co_plus_button_link"); // web element for plus button on supplier page
	        getObject("sl_create_link").click(); // click Service Level create link
	        
	        if(!slTitle.equalsIgnoreCase("")){    
	        getObject("sl_title_textbox").sendKeys(slTitle); 
	        }
	        if(!slDescription.equalsIgnoreCase("")){    
	        getObject("sl_description_textarea").sendKeys(slDescription); // name
	        }
	        if(!slid.equalsIgnoreCase("")){    
	        getObject("sl_id_textbox").sendKeys(slid); // name
	        }
	        if (!slCategory.equalsIgnoreCase("")) {
	          new Select(getObject("sl_category_dropdown")).selectByVisibleText(slCategory);
	        }       
	        if (!slSubCategory.equalsIgnoreCase("")) {
	          new Select(getObject("sl_sub_category_dropdown")).selectByVisibleText(slSubCategory);
	        } 
	        if (!slSL.equalsIgnoreCase("")) {
	          new Select(getObject("sl_sl_dropdown")).selectByVisibleText(slSL);
	        } 
	        if (!slTimezone.equalsIgnoreCase("")) {
	          new Select(getObject("sl_timezone_dropdown")).selectByVisibleText(slTimezone);
	        
	          try {
	      		if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
	      			driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
	      	} catch (Exception e) {
	      		
	      	}
	        } 
	        if (!slDeliveryCountries.equalsIgnoreCase("")) {
	          new Select(getObject("sl_delcountry_multi")).selectByVisibleText(slDeliveryCountries);
	        }
	        if (!slCurrency.equalsIgnoreCase("")) {
	          new Select(getObject("sl_currency_dropdown")).selectByVisibleText(slCurrency);
	        }
	        if (slSupplierAccess.equalsIgnoreCase("Yes")){
	          getObject("sl_supplier_access_checkbox").click();
	        }
	        if (!slTier.equalsIgnoreCase("")) {
	          new Select(getObject("sl_tier_dropdown")).selectByVisibleText(slTier);
	        }
	        if (!slPriority.equalsIgnoreCase("")) {
	          new Select(getObject("sl_priority_dropdown")).selectByVisibleText(slPriority);
	        }
	        if (!slReferences.equalsIgnoreCase("")) {
	          new Select(getObject("sl_references_dropdown")).selectByVisibleText(slReferences);
	        }
	        if(!slClause.equalsIgnoreCase("")){    
	        getObject("sl_clause_textbox").sendKeys(slClause); 
	        }
	        if(!slPageNumber.equalsIgnoreCase("")){    
	        getObject("sl_page_number_textbox").sendKeys(slPageNumber); 
	        }
	        
	        if (!slApplicationGroup.equalsIgnoreCase("")) {
	          new Select(getObject("sl_application_group_dropdown")).selectByVisibleText(slApplicationGroup);
	        }
	        if (!slApplication.equalsIgnoreCase("")) {
	          new Select(getObject("sl_application_dropdown")).selectByVisibleText(slApplication);
	        }
	        
	        if (!slMinMaxSelection.equalsIgnoreCase("")) {
	          new Select(getObject("sl_min_max_selection_dropdown")).selectByVisibleText(slMinMaxSelection);
	        }
	        if(!slUnitOfSlMeasurement.equalsIgnoreCase("")){    
	        getObject("sl_unit_of_sl_measurement").sendKeys(slUnitOfSlMeasurement); 
	        }
	        if(!slMinMax.equalsIgnoreCase("")){    
	        getObject("sl_min_max_textbox").sendKeys(slMinMax); 
	        }
	        if(!slExpected.equalsIgnoreCase("")){    
	        getObject("sl_expected_textbox").sendKeys(slExpected); 
	        }
	        if(!slSignificantlyMinMax.equalsIgnoreCase("")){    
	        getObject("sl_sig_min_max_textbox").sendKeys(slSignificantlyMinMax); 
	        }
	        if (!slMeasurementWindow.equalsIgnoreCase("")) {
	          new Select(getObject("sl_measurement_window_dropdown")).selectByVisibleText(slMeasurementWindow);
	        }
	        
	                

	        Date date = new Date();
	        int current_month = date.getMonth();

	        
	        driver.findElement(By.name("ytdStartDate")).click();
	        Thread.sleep(3000);
	        Double temp_slYtdStartDateYear_double = Double.parseDouble(slYtdStartDateYear);
	        int temp_slYtdStartDateYear_int = temp_slYtdStartDateYear_double.intValue();
	        String slYtdStartDateYear_string = Integer.toString(temp_slYtdStartDateYear_int);

	        WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	        System.out.println(datepicker_ui.isDisplayed());
	        if (datepicker_ui.isDisplayed() == true) {
	          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	          new Select(datepicker_ui_year).selectByVisibleText(slYtdStartDateYear_string);
	        }

	        Double temp_slYtdStartDateMonth_double = Double.parseDouble(slYtdStartDateMonth);
	        int temp_slYtdStartDateMonth_int = temp_slYtdStartDateMonth_double.intValue();
	        System.out.println(" slYtdStartDateMonth " + temp_slYtdStartDateMonth_int);

	        int click_slYtdStartDateMonth = current_month - temp_slYtdStartDateMonth_int;
	        System.out.println("slYtdStartDateMonth " + slYtdStartDateMonth);
	        for (; click_slYtdStartDateMonth >= 0; click_slYtdStartDateMonth = click_slYtdStartDateMonth - 1) {
	          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	        }
	        Double temp_slYtdStartDateDate_double = Double.parseDouble(slYtdStartDateDate);
	        int temp_slYtdStartDateDate_int = temp_slYtdStartDateDate_double.intValue();
	        String slYtdStartDateDate_string = Integer.toString(temp_slYtdStartDateDate_int);
	        driver.findElement(By.linkText(slYtdStartDateDate_string)).click();

	        
	        if (slCreditApplicable.equalsIgnoreCase("Yes")){
	          getObject("sl_credit_applicable_checkbox").click();
	        }
	        if (slSubjectToContinousImprovement.equalsIgnoreCase("Yes")){
	          getObject("sl_subject_to_continous_improvement_checkbox").click();
	        }
	        if (!slFrequencyType.equalsIgnoreCase("")) {
	          new Select(getObject("sl_frequency_type_dropdown")).selectByVisibleText(slFrequencyType);
	        }
	        if (!slComputationFrequency.equalsIgnoreCase("")) {
	          new Select(getObject("sl_computation_frequency_dropdown")).selectByVisibleText(slComputationFrequency);
	        }
	        if (!slFrequency.equalsIgnoreCase("")) {
	          new Select(getObject("sl_frequency_dropdown")).selectByVisibleText(slFrequency);
	        }
	        if (!slWeekType.equalsIgnoreCase("")) {
	          new Select(getObject("sl_week_type_dropdown")).selectByVisibleText(slWeekType);
	        }
	        
	        driver.findElement(By.name("startDate")).click();
	        Thread.sleep(3000);
	        Double temp_slStartDateYear_double = Double.parseDouble(slStartDateYear);
	        int temp_slStartDateYear_int = temp_slStartDateYear_double.intValue();
	        String slStartDateYear_string = Integer.toString(temp_slStartDateYear_int);

	        WebElement datepicker_ui1 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	        System.out.println(datepicker_ui1.isDisplayed());
	        if (datepicker_ui1.isDisplayed() == true) {
	          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	          new Select(datepicker_ui_year).selectByVisibleText(slStartDateYear_string);
	        }

	        Double temp_slStartDateMonth_double = Double.parseDouble(slStartDateMonth);
	        int temp_slStartDateMonth_int = temp_slStartDateMonth_double.intValue();
	        System.out.println(" slStartDateMonth " + temp_slStartDateMonth_int);

	        int click_slStartDateMonth = current_month - temp_slStartDateMonth_int;
	        System.out.println("slStartDateMonth " + slStartDateMonth);
	        for (; click_slStartDateMonth >= 0; click_slStartDateMonth = click_slStartDateMonth - 1) {
	          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	        }
	        Double temp_slStartDateDate_double = Double.parseDouble(slStartDateDate);
	        int temp_slStartDateDate_int = temp_slStartDateDate_double.intValue();
	        String slStartDateDate_string = Integer.toString(temp_slStartDateDate_int);
	        driver.findElement(By.linkText(slStartDateDate_string)).click();
	        
	        
	        driver.findElement(By.name("expDate")).click();
	        Thread.sleep(3000);
	        Double temp_slEndDateYear_double = Double.parseDouble(slEndDateYear);
	        int temp_slEndDateYear_int = temp_slEndDateYear_double.intValue();
	        String slEndDateYear_string = Integer.toString(temp_slEndDateYear_int);

	        WebElement datepicker_ui11 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	        System.out.println(datepicker_ui11.isDisplayed());
	        if (datepicker_ui11.isDisplayed() == true) {
	          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	          new Select(datepicker_ui_year).selectByVisibleText(slEndDateYear_string);
	        }

	        Double temp_slEndDateMonth_double = Double.parseDouble(slEndDateMonth);
	        int temp_slEndDateMonth_int = temp_slEndDateMonth_double.intValue();
	        System.out.println(" slEndDateMonth " + temp_slEndDateMonth_int);

	        int click_slEndDateMonth = current_month - temp_slEndDateMonth_int;
	        System.out.println("slEndDateMonth " + slEndDateMonth);
	        for (; click_slEndDateMonth >= 0; click_slEndDateMonth = click_slEndDateMonth - 1) {
	          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	        }
	        Double temp_slEndDateDate_double = Double.parseDouble(slEndDateDate);
	        int temp_slEndDateDate_int = temp_slEndDateDate_double.intValue();
	        String slEndDateDate_string = Integer.toString(temp_slEndDateDate_int);
	        driver.findElement(By.linkText(slEndDateDate_string)).click();
	        
	        driver.findElement(By.name("patternDate")).click();
	        Thread.sleep(3000);
	        Double temp_slPatternDateYear_double = Double.parseDouble(slPatternDateYear);
	        int temp_slPatternDateYear_int = temp_slPatternDateYear_double.intValue();
	        String slPatternDateYear_string = Integer.toString(temp_slPatternDateYear_int);

	        WebElement datepicker_ui111 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	        System.out.println(datepicker_ui111.isDisplayed());
	        if (datepicker_ui111.isDisplayed() == true) {
	          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	          new Select(datepicker_ui_year).selectByVisibleText(slPatternDateYear_string);
	        }

	        Double temp_slPatternDateMonth_double = Double.parseDouble(slPatternDateMonth);
	        int temp_slPatternDateMonth_int = temp_slPatternDateMonth_double.intValue();
	        System.out.println(" slPatternDateMonth " + temp_slPatternDateMonth_int);

	        int click_slPatternDateMonth = current_month - temp_slPatternDateMonth_int;
	        System.out.println("slPatternDateMonth " + slPatternDateMonth);
	        for (; click_slPatternDateMonth >= 0; click_slPatternDateMonth = click_slPatternDateMonth - 1) {
	          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	        }
	        Double temp_slPatternDateDate_double = Double.parseDouble(slPatternDateDate);
	        int temp_slPatternDateDate_int = temp_slPatternDateDate_double.intValue();
	        String slPatternDateDate_string = Integer.toString(temp_slPatternDateDate_int);
	        driver.findElement(By.linkText(slPatternDateDate_string)).click();
	        
	        driver.findElement(By.name("effectiveDate")).click();
	        Thread.sleep(3000);
	        Double temp_slEffectiveDateYear_double = Double.parseDouble(slEffectiveDateYear);
	        int temp_slEffectiveDateYear_int = temp_slEffectiveDateYear_double.intValue();
	        String slEffectiveDateYear_string = Integer.toString(temp_slEffectiveDateYear_int);

	        WebElement datepicker_ui1111 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
	        System.out.println(datepicker_ui1111.isDisplayed());
	        if (datepicker_ui1111.isDisplayed() == true) {
	          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
	          new Select(datepicker_ui_year).selectByVisibleText(slEffectiveDateYear_string);
	        }

	        Double temp_slEffectiveDateMonth_double = Double.parseDouble(slEffectiveDateMonth);
	        int temp_slEffectiveDateMonth_int = temp_slEffectiveDateMonth_double.intValue();
	        System.out.println(" slEffectiveDateMonth " + temp_slEffectiveDateMonth_int);

	        int click_slEffectiveDateMonth = current_month - temp_slEffectiveDateMonth_int;
	        System.out.println("slEffectiveDateMonth " + slEffectiveDateMonth);
	        for (; click_slEffectiveDateMonth >= 0; click_slEffectiveDateMonth = click_slEffectiveDateMonth - 1) {
	          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
	        }
	        Double temp_slEffectiveDateDate_double = Double.parseDouble(slEffectiveDateDate);
	        int temp_slEffectiveDateDate_int = temp_slEffectiveDateDate_double.intValue();
	        String slEffectiveDateDate_string = Integer.toString(temp_slEffectiveDateDate_int);
	        driver.findElement(By.linkText(slEffectiveDateDate_string)).click();
	        
	         
	        if (!slResponsibility.equalsIgnoreCase("")) {
	          new Select(getObject("sl_responsibity_dropdown")).selectByVisibleText(slResponsibility);
	        }       
	        if (slFinancialImpactApplicable.equalsIgnoreCase("Yes")){
	          getObject("sl_financial_impact_applicable_checkbox").click();
	        }
	        if (slCreditImpactApplicable.equalsIgnoreCase("Yes")){
	          getObject("sl_credit_imapct_applicable_checkbox").click();
	        }
	        
	  
	        
	        // Communication Group Entry
	        
	        
	        if(!slComment.equalsIgnoreCase(""))
	        {
	        	driver.findElement(By.xpath(".//*[@id='elem_86']/textarea")).sendKeys(slComment);
	        }
	        
	        if(!slRequestedBy.equalsIgnoreCase(""))
	        {
	        	new Select(driver.findElement(By.xpath(".//*[@id='elem_88']/select"))).selectByVisibleText(slRequestedBy);
	        }
	        
	        if(!slChangeRequest.equalsIgnoreCase(""))
	        {
	        	new Select(driver.findElement(By.xpath(".//*[@id='elem_89']/select"))).selectByVisibleText(slChangeRequest);
	        }
	        
	        if(!slUploadedFile.equalsIgnoreCase(""))
	        {
	        	//driver.findElement(By.xpath("(//input[@name='documentFileData'])[2]")).clear();
	            driver.findElement(By.xpath("(//input[@name='documentFileData'])[2]")).sendKeys("C:\\Users\\gaurav.arora\\Desktop\\Master_Obligation_Salil (2) latest.xlsx");
	        	//WebElement file_input = driver.findElement(By.xpath(".//*[@id='elem_41_']/div[1]/button"));
	        	//file_input.sendKeys("C:\\Users\\gaurav.arora\\Desktop\\Master_Obligation_Salil (2) latest.xlsx");
	        }
	        
	        
	        
	               // driver.findElement(By.xpath("//*[@id='kk']/div/div/div[3]/div/div/form/div[4]/ng-form/div/button[1]")).click();
	        driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();        
	        
	        String sl_id = getObject("sl_popup_id").getText();
	                
	                APP_LOGS.debug("Service Level created successfully with Service Level id " + sl_id);

	                getObject("sl_popup_ok_button").click();

	                APP_LOGS.debug("Quick Search the created action with Service Level id " + sl_id);

	               getObject("quick_search_textbox").sendKeys(sl_id);
	                //getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
	                getObject("quick_search_textbox").sendKeys(Keys.ENTER);
//	                String slIdFromShowPage = getObject("sl_show_id").getText();
//
//	                Assert.assertEquals(slIdFromShowPage, sl_id);
//
//	                APP_LOGS.debug("Service Level show page open successfully with Service Level id " + sl_id);
	                
	                Thread.sleep(5000);
	                driver.findElement(By.xpath("//a[contains(.,'AUDIT LOG')]")).click();
	               
	               
//	                boolean present;
//	                try {
//	                   driver.findElement(By.id("logoutLink"));
//	                   present = true;
//	                } catch (NoSuchElementException e) {
//	                   present = false;
//	                }
	                
	                String requestedByName=driver.findElement(By.xpath(".//*[@id='230']/tbody/tr/td[2]")).getText();
	                   System.out.println(slRequestedBy);
	                
	              if(requestedByName.equalsIgnoreCase(slRequestedBy))
	                {
	                	System.out.println("Requested By Name: "+ requestedByName +" in Audit Log tab and "+ "Requested By Name that we have entered in communication section i.e " + slRequestedBy +" are same");
	                }
	              
//	              	String commentTrigger=driver.findElement(By.xpath(".//*[@id='230']/tbody/tr/td[6]")).getText();
//	              	System.out.println(commentTrigger);
//	              	if(commentTrigger.equalsIgnoreCase(slComment))
//	              	{
//	              		System.out.println("Comment Name: "+ commentTrigger +" in communication tab and "+ "Comment that we have entered in communication section i.e " + slComment +" are same");
//	              	}
	                
	               
	                
	                
	                
//	                String SLTtitleShowPage= getObject("sl_Title_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLTtitleShowPage, slTitle ,"SL Title is= " +SLTtitleShowPage +"instead of" + slTitle);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Title is== "+SLTtitleShowPage +"instead of" + slTitle);
//	                } 
//	                
//	                
//	            
//	                String SLDescriptionShowPage= getObject("sl_Description_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLDescriptionShowPage, slDescription ,"SL Description is= " +SLDescriptionShowPage +"instead of" + slDescription);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Description is== "+SLDescriptionShowPage +"instead of" + slDescription);
//	                } 
//	                
//	                String SLCategoryShowPage= getObject("sl_Category_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLCategoryShowPage, slCategory ,"SL Category is= " +SLCategoryShowPage +"instead of" + slCategory);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Category is== "+SLCategoryShowPage +"instead of" + slCategory);
//	                } 
//	                
//	              
//	                
//	                
//	                String SLSubCategoryShowPage= getObject("slSubCategory_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLSubCategoryShowPage, slSubCategory ,"SL Sub Category is= " +SLSubCategoryShowPage +"instead of" + slSubCategory);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Sub Category is== "+SLSubCategoryShowPage +"instead of" + slSubCategory);
//	                } 
//	                
//	                String SLSLShowPage= getObject("slSL_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLSLShowPage, slSL ,"SL SL is= " +SLSLShowPage +"instead of" + slSL);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL SL is== "+SLSLShowPage +"instead of" + slSL);
//	                } 
//	                
//	                
//	                String SLTimeZoneShowPage= getObject("slTimezone_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLTimeZoneShowPage, slTimezone ,"SL Timezone is= " +SLTimeZoneShowPage +"instead of" + slTimezone);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Timezone is== "+SLTimeZoneShowPage +"instead of" + slTimezone);
//	                } 
//	                
//
//	               
//	                
//	                String SLDeliveryCountriesShowPage= getObject("slDeliveryCountries_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLDeliveryCountriesShowPage, slDeliveryCountries ,"SL Delivery Countirs are= " +SLDeliveryCountriesShowPage +"instead of" + slDeliveryCountries);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Delivery Countirs are= "+SLDeliveryCountriesShowPage +"instead of" + slDeliveryCountries);
//	                } 
//	                
//	                String SLCurrencyShowPage= getObject("slCurrency_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLCurrencyShowPage, slCurrency ,"SL currencies are= " +SLCurrencyShowPage +"instead of" + slCurrency);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL currencies are= "+SLCurrencyShowPage +"instead of" + slCurrency);
//	                } 
//	                
//	                String SLSupplierAccessShowPage= getObject("slSupplierAccess_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLSupplierAccessShowPage, slSupplierAccess ,"SL Supplier Access is= " +SLSupplierAccessShowPage +"instead of" + slSupplierAccess);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Supplier Access is= "+SLSupplierAccessShowPage +"instead of" + slSupplierAccess);
//	                }
//	                
//	                String SLTierShowPage= getObject("slTier_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLTierShowPage, slTier ,"SL Tier is= " +SLTierShowPage +"instead of" + slTier);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Tier is= "+SLTierShowPage +"instead of" + slTier);
//	                }
//	                
//	                
//	                String SLPriorityShowPage= getObject("slPriority_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLPriorityShowPage, slPriority ,"SL Priority is= " +SLPriorityShowPage +"instead of" + slPriority);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Priority is= "+SLPriorityShowPage +"instead of" + slPriority);
//	                }
//	                
//	                
//	                String SLReferencesShowPage= getObject("slReferences_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLReferencesShowPage, slReferences ,"SL References is= " +SLReferencesShowPage +"instead of" + slReferences);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL References is= "+SLReferencesShowPage +"instead of" + slReferences);
//	                }
//	                
//	                String SLCaluseShowPage= getObject("slClause_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLCaluseShowPage, slClause ,"SL Clause is= " +SLCaluseShowPage +"instead of" + slClause);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Clause is= "+SLCaluseShowPage +"instead of" + slClause);
//	                }
//	                
//	                String SLPageNumberShowPage= getObject("slPageNumber_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLPageNumberShowPage, slPageNumber ,"SL Page Number is= " +SLPageNumberShowPage +"instead of" + slPageNumber);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Page Number is= "+SLPageNumberShowPage +"instead of" + slPageNumber);
//	                }
//	                
//	               
//	                
//	                
//	                String SLApplicationGroupShowPage= getObject("slApplicationGroup_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLApplicationGroupShowPage, slApplicationGroup ,"SL Application Group is= " +SLApplicationGroupShowPage +"instead of" + slApplicationGroup);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Application Group is= "+SLApplicationGroupShowPage +"instead of" + slApplicationGroup);
//	                }
//	                
//	                String SLApplicationShowPage= getObject("slApplication_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLApplicationShowPage, slApplication ,"SL Application  is= " +SLApplicationShowPage +"instead of" + slApplication);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Application  is= "+SLApplicationShowPage +"instead of" + slApplication);
//	                }
//	                
//	                String SLMinimumorMaximumSelectionShowPage= getObject("slMinimuMaximumSelection_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLMinimumorMaximumSelectionShowPage, slMinMaxSelection ,"SL Minimum or Maximum Selection is= " +SLMinimumorMaximumSelectionShowPage +"instead of" + slMinMaxSelection);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Minimum or Maximum Selection is= "+SLMinimumorMaximumSelectionShowPage +"instead of" + slMinMaxSelection);
//	                }
//	                
//	                
//	                String SLUnitofMeasurementShowPage= getObject("slUnitofSLMeasurement_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLUnitofMeasurementShowPage, slUnitOfSlMeasurement ,"SL  Unit Of Measurement is= " +SLUnitofMeasurementShowPage +"instead of" + slUnitOfSlMeasurement);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Unit Of Measurement  is= "+SLUnitofMeasurementShowPage +"instead of" + slUnitOfSlMeasurement);
//	                }
//	                
//	                
//	                String SLMinimumorMaximumShowPage= getObject("slMinimumorMaximum_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLMinimumorMaximumShowPage, slMinMax ,"SL Minimum or Maximum  is= " +SLMinimumorMaximumShowPage +"instead of" + slMinMax);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Minimum or Maximum  is= "+SLMinimumorMaximumShowPage +"instead of" + slMinMax);
//	                }
//	                
//	                String SLSignificantMinimumMaximumShowPage= getObject("slSignificant_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLSignificantMinimumMaximumShowPage, slSignificantlyMinMax ,"SL Minimum or Maximum  is= " +SLSignificantMinimumMaximumShowPage +"instead of" + slSignificantlyMinMax);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Minimum or Maximum  is= "+SLSignificantMinimumMaximumShowPage +"instead of" + slSignificantlyMinMax);
//	                }
//	                /*String slTitle, String  slDescription, String   slid, String    slCategory, String  slSubCategory, String   slSL, String    slTimezone, 
//	                String  slDeliveryCountries, String slCurrency, String  slSupplierAccess, String    slTier, String  slPriority, String  slReferences, 
//	                String    slClause, String    slPageNumber, String    slApplicationGroup, String  slApplication, String   slMinMaxSelection, String   slUnitOfSlMeasurement, 
//	                String   slMinMax, String    slExpected, String  slSignificantlyMinMax, String   slMeasurementWindow, 
//	                String  slYtdStartDateDate, String  slYtdStartDateMonth, String slYtdStartDateYear, 
//	                String  slCreditApplicable, String  slSubjectToContinousImprovement, String slFrequencyType, String slComputationFrequency, String  slFrequency, String slWeekType, 
//	                String  slStartDateDate, String slStartDateMonth, String    slStartDateYear, 
//	                String  slEndDateDate, String   slEndDateMonth, String  slEndDateYear, 
//	                String  slPatternDateDate, String   slPatternDateMonth, String  slPatternDateYear, 
//	                String  slEffectiveDateDate, String slEffectiveDateMonth, String    slEffectiveDateYear, 
//	                String  slResponsibity, String  slFinancialImpactApplicable, String slCreditImpactApplicable*/
//	                String SLUnitofMeasurementWindowShowPage= getObject("slUnitofSLMeasurement_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLUnitofMeasurementWindowShowPage, slMeasurementWindow ,"SL Unit of Measurement Window   is= " +SLUnitofMeasurementWindowShowPage +"instead of" + slMeasurementWindow);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Unit of Measurement Window is= "+SLUnitofMeasurementWindowShowPage +"instead of" + slMeasurementWindow);
//	                }
//	                
//	                String SLCreditApplicableShowPage= getObject("slCreditApplicable_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLCreditApplicableShowPage, slCreditApplicable ,"SL Credit Applicable is= " +SLCreditApplicableShowPage +"instead of" + slCreditApplicable);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Credit Applicable is= "+SLCreditApplicableShowPage +"instead of" + slCreditApplicable);
//	                }
//	                
//	                String SLSubjectedtoContinousImprovementShowPage= getObject("slSubjectedtoContinousImprovement_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLSubjectedtoContinousImprovementShowPage, slSubjectToContinousImprovement ,"SL Subjected to Contionous Improvement is= " +SLSubjectedtoContinousImprovementShowPage +"instead of" + slSubjectToContinousImprovement);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Subjected to Contionous Improvement is= "+SLSubjectedtoContinousImprovementShowPage +"instead of" + slSubjectToContinousImprovement);
//	                }
//	                
//	                String SLFrequencyTypeShowPage= getObject("slFrequencyType_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLFrequencyTypeShowPage, slFrequencyType ,"SL Frequency Type is= " +SLFrequencyTypeShowPage +"instead of" + slFrequencyType);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Frequency Type is= "+SLFrequencyTypeShowPage +"instead of" + slFrequencyType);
//	                }
//	                
//	                String SLComputationFrequencyShowPage= getObject("slComputationFrequency_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLComputationFrequencyShowPage, slComputationFrequency ,"SL Computation Frequency is= " +SLComputationFrequencyShowPage +"instead of" + slComputationFrequency);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Computation Frequency is= "+SLComputationFrequencyShowPage +"instead of" + slComputationFrequency);
//	                }
//	                
//	                String SLFrequencyShowPage= getObject("slFrequency_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLFrequencyShowPage, slFrequency ,"SL Frequency is= " +SLFrequencyShowPage +"instead of" + slFrequency);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Frequency is= "+SLFrequencyShowPage +"instead of" + slFrequency);
//	                } 
//	                
//	                
//	                String SLWeekTypeShowPage= getObject("slWeekType_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLWeekTypeShowPage, slWeekType,"SL Week Type is= " +SLWeekTypeShowPage +"instead of" + slWeekType);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Week Type is= "+SLWeekTypeShowPage +"instead of" + slWeekType);
//	                }
//	                
//	                String SLResponsibilityShowPage= getObject("slResponsibity_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLResponsibilityShowPage, slResponsibity,"SL Responsiblity is= " +SLResponsibilityShowPage +"instead of" + slResponsibity);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Responsiblity is= "+SLResponsibilityShowPage +"instead of" + slResponsibity);
//	                }
//	                
//	                String SLFinancialImpactShowPage= getObject("slFinancialImpactApplicable_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLFinancialImpactShowPage, slFinancialImpactApplicable,"SL Financial Impact is= " +SLFinancialImpactShowPage +"instead of" + slFinancialImpactApplicable);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Financial Impact is= "+SLFinancialImpactShowPage +"instead of" + slFinancialImpactApplicable);
//	                }
//	                
//	                String SLCreditImpactShowPage= getObject("slCreditImpactApplicable_show").getText();
//	                try
//	                {
//	                Assert.assertEquals(SLCreditImpactShowPage, slCreditImpactApplicable,"SL Credit Impact is= " +SLCreditImpactShowPage +"instead of" + slCreditImpactApplicable);
//	                }
//	                catch(Throwable e)
//	                {
//	              	  System.out.println("SL Credit Impact is= "+SLCreditImpactShowPage +"instead of" + slCreditImpactApplicable);
//	                }
//	                
//	                
	               
//
//	                // driver.findElement(By.xpath(".//*[@id='h-analytics']/a")).click();
	              getObject("analytics_link").click();
	              fail = false;  // this executes if assertion passes
//	                getObject("contract_quick_link").click();
	    }
	    
	    @AfterMethod
	    public void reportDataSetResult(){
	        if(skip)
	            TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
	        else if(fail){
	            isTestPass=false;
	            TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
	        }
	        else
	            TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
	        
	        skip=false;
	        fail=false;
	        

	    }
	    
	    @AfterTest
	    public void reportTestResult(){
	        if(isTestPass)
	            TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls,this.getClass().getSimpleName()), "PASS");
	        else
	            TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls,this.getClass().getSimpleName()), "FAIL");
	    
	    }
	    
	    @DataProvider
	    public Object[][] getTestData(){
	        return TestUtil.getData(sl_suite_xls, this.getClass().getSimpleName()) ;
	    }
	
	
	
	
	
	
	
}
