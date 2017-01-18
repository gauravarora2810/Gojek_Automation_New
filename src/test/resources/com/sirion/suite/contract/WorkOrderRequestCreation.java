package test.resources.com.sirion.suite.contract;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class WorkOrderRequestCreation extends TestSuiteBase{
    String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
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
    
    @Test (dataProvider = "getTestData")
    public void SLCreationTest(
        String worName, String worTitle, String worBrief, String    worPriority, String worType, String worBillingType, String  worCurrency, String worContractingEntity, 
        String    worDeliveryCountries, String    worTimezone, String worSupplierAccess, String   worTier, 
        String worEffectiveDateDate, String    worEffectiveDateMonth, String   worEffectiveDateYear, 
        String    worExpirationDateDate, String   worExpirationDateMonth, String  worExpirationDateYear, 
        String  worAdditionalTCV, String    worAdditionalACV, String    worAdditionalFACV       
            ) throws InterruptedException {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
        APP_LOGS.debug("Executing Test Case WOR Creation");
      //Calling method for opening browser from TestBase.java file
      	openBrowser();


      	// Calling a method for login(at different platform) from TestBase.java file
endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));

      	
      	getObject("analytics_link").click();
      getObject("contract_quick_link").click(); // IP Quick Link Clicking
      //Thread.sleep(20000);
      WebDriverWait wait=new WebDriverWait(driver, 50);
     // wait.until(ExpectedConditions.elementToBeClickable(getObject("ac_id_link")));
      getObject("ac_id_link").click(); 
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
        plus_button("ac_plus_button_link"); // web element for plus button on supplier page
        wait.until(ExpectedConditions.elementToBeClickable(getObject("wor_create_link_from_contract")));
      getObject("wor_create_link_from_contract").click(); // click issue create link 
        
        if(!worName.equalsIgnoreCase("")){    
        getObject("wor_name_textbox").sendKeys(worName); 
        }
        if(!worTitle.equalsIgnoreCase("")){    
        getObject("wor_title_textbox").sendKeys(worTitle); 
        }
        if(!worBrief.equalsIgnoreCase("")){    
        getObject("wor_brief_textarea").sendKeys(worBrief); // name
        }
        if (!worPriority.equalsIgnoreCase("")) {
          new Select(getObject("wor_priority_dropdown")).selectByVisibleText(worPriority);
        }       
        if (!worType.equalsIgnoreCase("")) {
          new Select(getObject("wor_type_dropdown")).selectByVisibleText(worType);
        } 
        if (!worBillingType.equalsIgnoreCase("")) {
          new Select(getObject("wor_billing_type_dropdown")).selectByVisibleText(worBillingType);
        } 
        if (!worCurrency.equalsIgnoreCase("")) {
          new Select(getObject("wor_currency_dropdown")).selectByVisibleText(worCurrency);
        } 
        if (!worContractingEntity.equalsIgnoreCase("")) {
          new Select(getObject("wor_contracting_entity_dropdown")).selectByVisibleText(worContractingEntity);
        }
        if (!worDeliveryCountries.equalsIgnoreCase("")) {
          new Select(getObject("wor_delcountry_multi")).selectByVisibleText(worDeliveryCountries);
        }
        if (!worTimezone.equalsIgnoreCase("")) {
          new Select(getObject("wor_timezone_dropdown")).selectByVisibleText(worTimezone);
          try {
				if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
					driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
			} catch (Exception e) {
				
			}
        }
        if (worSupplierAccess.equalsIgnoreCase("Yes")){
          getObject("wor_supplier_access_checkbox").click();
        }
        if (!worTier.equalsIgnoreCase("")) {
          new Select(getObject("wor_tier_dropdown")).selectByVisibleText(worTier);
        }
        
        Thread.sleep(10000);
        Date date = new Date();
        int current_month = date.getMonth();

        
        driver.findElement(By.name("effectiveDate")).click();
        //Thread.sleep(3000);
        Double temp_worEffectiveDateYear_double = Double.parseDouble(worEffectiveDateYear);
        int temp_worEffectiveDateYear_int = temp_worEffectiveDateYear_double.intValue();
        String worEffectiveDateYear_string = Integer.toString(temp_worEffectiveDateYear_int);

        WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
        System.out.println(datepicker_ui.isDisplayed());
        if (datepicker_ui.isDisplayed() == true) {
          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
          new Select(datepicker_ui_year).selectByVisibleText(worEffectiveDateYear_string);
        }

        Double temp_worEffectiveDateMonth_double = Double.parseDouble(worEffectiveDateMonth);
        int temp_worEffectiveDateMonth_int = temp_worEffectiveDateMonth_double.intValue();
        System.out.println(" worEffectiveDateMonth " + temp_worEffectiveDateMonth_int);

        int click_worEffectiveDateMonth = current_month - temp_worEffectiveDateMonth_int;
        System.out.println("worEffectiveDateMonth " + worEffectiveDateMonth);
        for (; click_worEffectiveDateMonth >= 0; click_worEffectiveDateMonth = click_worEffectiveDateMonth - 1) {
          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
        }
        Double temp_worEffectiveDateDate_double = Double.parseDouble(worEffectiveDateDate);
        int temp_worEffectiveDateDate_int = temp_worEffectiveDateDate_double.intValue();
        String worEffectiveDateDate_string = Integer.toString(temp_worEffectiveDateDate_int);
        driver.findElement(By.linkText(worEffectiveDateDate_string)).click();

        
        driver.findElement(By.name("expirationDate")).click();
        
        Double temp_worExpirationDateYear_double = Double.parseDouble(worExpirationDateYear);
        int temp_worExpirationDateYear_int = temp_worExpirationDateYear_double.intValue();
        String worExpirationDateYear_string = Integer.toString(temp_worExpirationDateYear_int);

        WebElement datepicker_ui1 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
        System.out.println(datepicker_ui1.isDisplayed());
        if (datepicker_ui1.isDisplayed() == true) {
          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
          new Select(datepicker_ui_year).selectByVisibleText(worExpirationDateYear_string);
        }

        Double temp_worExpirationDateMonth_double = Double.parseDouble(worExpirationDateMonth);
        int temp_worExpirationDateMonth_int = temp_worExpirationDateMonth_double.intValue();
        System.out.println(" worExpirationDateMonth " + temp_worExpirationDateMonth_int);

        int click_worExpirationDateMonth = current_month - temp_worExpirationDateMonth_int;
        System.out.println("worExpirationDateMonth " + worExpirationDateMonth);
        for (; click_worExpirationDateMonth >= 0; click_worExpirationDateMonth = click_worExpirationDateMonth - 1) {
          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
        }
        Double temp_worExpirationDateDate_double = Double.parseDouble(worExpirationDateDate);
        int temp_worExpirationDateDate_int = temp_worExpirationDateDate_double.intValue();
        String worExpirationDateDate_string = Integer.toString(temp_worExpirationDateDate_int);
        driver.findElement(By.linkText(worExpirationDateDate_string)).click();
        
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  getObject("wor_additional_tcv_textbox"));
       
        
        if(!worAdditionalTCV.equalsIgnoreCase("")){    
        getObject("wor_additional_tcv_textbox").sendKeys(worAdditionalTCV); 
        }
        if(!worAdditionalACV.equalsIgnoreCase("")){    
        getObject("wor_additional_acv_textbox").sendKeys(worAdditionalACV); 
        }
        if(!worAdditionalFACV.equalsIgnoreCase("")){    
        getObject("wor_additional_facv_textbox").sendKeys(worAdditionalFACV); 
        }
        
       
        getObject("ac_save_button").click();
                String wor_id = getObject("sl_popup_id").getText();
                
                APP_LOGS.debug("WOR created successfully with WOR id " + wor_id);

                getObject("sl_popup_ok_button").click();

                APP_LOGS.debug("Quick Search the created action with WOR id " + wor_id);

               getObject("quick_search_textbox").sendKeys(wor_id);
                //getObject("quick_search_textbox").sendKeys("AC01001"); // created for testing
                getObject("quick_search_textbox").sendKeys(Keys.ENTER);
                /*String worIdFromShowPage = getObject("sl_show_id").getText();

                Assert.assertEquals(worIdFromShowPage, wor_id);

                APP_LOGS.debug("WOR show page open successfully with WOR id " + wor_id);*/
                
                
              
               /* 
                String WORNameShowPage= getObject("wor_Name_textbox").getText();
                try
                {
                Assert.assertEquals(WORNameShowPage, worName, "WOR Name is== "+WORNameShowPage +"instead of" + worName);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Name is== "+WORNameShowPage +"instead of" + worName);
                }
                
                
                
                String WORTitleShowPage= getObject("wor_Title_show").getText();
                try
                {
                Assert.assertEquals(WORTitleShowPage, worTitle, "WOR Title is== "+WORTitleShowPage +"instead of" + worTitle);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Title is== "+WORTitleShowPage +"instead of" + worTitle);
                }
                
                
                
                String WORBriefShowPage= getObject("wor_Brief_show").getText();
                try
                {
                Assert.assertEquals(WORBriefShowPage, worBrief, "WOR Brief is== "+WORBriefShowPage +"instead of" + worBrief);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Brief is== "+WORBriefShowPage +"instead of" + worBrief);
                }
                
                
                
                String WORPriorityShowPage= getObject("wor_Priority_show").getText();
                try
                {
                Assert.assertEquals(WORPriorityShowPage, worPriority, "WOR Priority is== "+WORPriorityShowPage +"instead of" + worPriority);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Priority is== "+WORPriorityShowPage +"instead of" + worPriority);
                }
                
                String WORTypeShowPage= getObject("wor_Type_show").getText();
                try
                {
                Assert.assertEquals(WORTypeShowPage, worType, "WOR Type is== "+WORTypeShowPage +"instead of" + worType);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Type is== "+WORTypeShowPage +"instead of" + worType);
                }
                
                
                
                
                String WORBillingTypeShowPage= getObject("wor_BillingType_show").getText();
                try
                {
                Assert.assertEquals(WORBillingTypeShowPage, worBillingType, "WOR Billing Type is== "+WORBillingTypeShowPage +"instead of" + worBillingType);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Billing Type is== "+WORBillingTypeShowPage +"instead of" + worBillingType);
                }
                
                String WORCurrencyShowPage= getObject("wor_Currency_show").getText();
                try
                {
                Assert.assertEquals(WORCurrencyShowPage, worCurrency, "WOR Currency is== "+WORCurrencyShowPage +"instead of" + worCurrency);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Currency is== "+WORCurrencyShowPage +"instead of" + worCurrency);
                }
                

                
                String WORContractEntityShowPage= getObject("wor_ContractEntity_show").getText();
                try
                {
                Assert.assertEquals(WORContractEntityShowPage, worContractingEntity, "WOR Contract Entity is== "+WORContractEntityShowPage +"instead of" + worContractingEntity);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Contract Entity is== "+WORContractEntityShowPage +"instead of" + worContractingEntity);
                }
                
                
                
                String WORDeliveryContriesShowPage= getObject("wor_DeliveryCountries_show").getText();
                try
                {
                Assert.assertEquals(WORDeliveryContriesShowPage, worDeliveryCountries, "WOR Delivery Countries is== "+WORDeliveryContriesShowPage +"instead of" + worDeliveryCountries);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Delivery Countries is== "+WORDeliveryContriesShowPage +"instead of" + worDeliveryCountries);
                }
                
               
                
                
                String WORTimeZoneShowPage= getObject("wor_Timezone_show").getText();
                try
                {
                Assert.assertEquals(WORTimeZoneShowPage, worTimezone, "WOR Time Zone is= "+WORTimeZoneShowPage +"instead of" + worTimezone);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Time Zone is== "+WORTimeZoneShowPage +"instead of" + worTimezone);
                } 
                
                
                String WORSupplierAccessShowPage= getObject("wor_SupplierAccess_show").getText();
                try
                {
                Assert.assertEquals(WORSupplierAccessShowPage, worSupplierAccess ,"WOR Supplier Access is= " +WORSupplierAccessShowPage +"instead of" + worSupplierAccess);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Supplier Access is== "+WORSupplierAccessShowPage +"instead of" + worSupplierAccess);
                } 
                
                
                
                String worName, String worTitle, String worBrief, String    worPriority, String worType, String worBillingType, String  worCurrency, String worContractingEntity, 
                String    worDeliveryCountries, String    worTimezone, String worSupplierAccess, String   worTier, 
                String worEffectiveDateDate, String    worEffectiveDateMonth, String   worEffectiveDateYear, 
                String    worExpirationDateDate, String   worExpirationDateMonth, String  worExpirationDateYear, 
                String  worAdditionalTCV, String    worAdditionalACV, String    worAdditionalFACV 
                
                
                
                String WORTierShowPage= getObject("wor_Tier_show").getText();
                try
                {
                Assert.assertEquals(WORTierShowPage, worTier ,"WOR Tier is= " +WORTierShowPage +"instead of" + worTier);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Tier is== "+WORTierShowPage +"instead of" + worTier);
                } 
                
                
                String WORAdditionalTCVShowPage= getObject("wor_AdditionalTCV_show").getText();
                try
                {
                Assert.assertEquals(WORAdditionalTCVShowPage, worAdditionalTCV ,"WOR Additional TCV is= " +WORAdditionalTCVShowPage +"instead of" + worAdditionalTCV);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Additional TCV is== "+WORAdditionalTCVShowPage +"instead of" + worAdditionalTCV);
                } 
                
                
                String WORAdditionalACVShowPage= getObject("wor_AdditionalACV_show").getText();
                try
                {
                Assert.assertEquals(WORAdditionalACVShowPage, worAdditionalACV ,"WOR Additional ACV is= " +WORAdditionalACVShowPage +"instead of" + worAdditionalACV);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Additional ACV is== "+WORAdditionalACVShowPage +"instead of" + worAdditionalACV);
                } 
                
                
                
                String WORAdditionalFACVShowPage= getObject("wor_AdditionalFACV_show").getText();
                try
                {
                Assert.assertEquals(WORAdditionalFACVShowPage, worAdditionalFACV ,"WOR Additional FACV is= " +WORAdditionalFACVShowPage +"instead of" + worAdditionalFACV);
                }
                catch(Throwable e)
                {
              	  System.out.println("WOR Additional FACV is== "+WORAdditionalFACVShowPage +"instead of" + worAdditionalFACV);
                } 
                */
                
                fail = false; // this executes if assertion passes

                // driver.findElement(By.xpath(".//*[@id='h-analytics']/a")).click();
                getObject("analytics_link").click();
               // getObject("contract_quick_link").click();
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
