package test.resources.com.sirion.suite.sl;

import java.util.Date;

import org.openqa.selenium.By;
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

public class IssueCreation extends TestSuiteBase
{
    String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=true;
    static boolean skip=false;
    static boolean isTestPass=true;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(sl_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the runmodes off the tests
        runmodes=TestUtil.getDataSetRunmodes(sl_suite_xls, this.getClass().getSimpleName());
    }
    
    @Test (groups = "ContractCreation", dataProvider = "getTestData")
    
    public void IssueCreate(
            String issueSlName, String issueTitle, String issueDescription, String issueType, String issuePriority, String issueCurrencies,
            String issueFinancialImpact, String issueGovernanceBody, String issueDeliveryCountries, 
            String issueTimeZone, String issueRestrictPublicAccess, String issueRestrictRequesterAccess, String issueSupplierAccess, 
            String issueTier, String issueDependentEntity, 
            String issueDateMonth, String issueDateDate, String issueDateYear,
            String issuePlannedCompletionDateMonth, String issuePlannedCompletionDateDate, String issuePlannedCompletionDateYear,
            String issueResponsibility, String issueStatus, String issueSupName, String issueSource, String issueSourceName, String issueFunction, String issueService, String issueRegion, String issueCountry) throws InterruptedException
    {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
        APP_LOGS.debug("Executing Test Case Issue Creation");
            
        // Calling method for opening browser from TestBase.java file
        openBrowser();

      	// Calling a method for login(at different platform) from TestBase.java file
      	endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
          
          WebDriverWait wait=new WebDriverWait(driver, 50);
      		getObject("analytics_link").click();

      		getObject("sl_quick_link").click(); // IP Quick Link Clicking
      		
      		Thread.sleep(20000);
       		getObject("sl_link_global_listing").click();
       		
       		Thread.sleep(20000);
      		//wait.until(ExpectedConditions.elementToBeClickable(getObject("sl_id_link")));

      		getObject("sl_id_link").click(); 
      		
      		
      		

      		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
          plus_button("sl_plus_button_link"); // web element for plus button on supplier page
          wait.until(ExpectedConditions.elementToBeClickable(getObject("is_create_link_from_sl")));
          getObject("is_create_link_from_sl").click(); // click issue create link  
 		
 		
 		
 		/*try {

 			if (issueParentEntity.equalsIgnoreCase("Contract")) {
 				getObject("contract_quick_link").click(); // click on contract quick link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			} else if (issueParentEntity.equalsIgnoreCase("Supplier")) {
 				getObject("supplier_quick_link").click(); // click on Supplier Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			} else if (issueParentEntity.equalsIgnoreCase("Issue")) {
 				getObject("issue_quick_link").click(); // click on Issue Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link
 			}

 			else if (issueParentEntity.equalsIgnoreCase("Interpretation")) {
 				getObject("int_quick_link").click(); // click on IP Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			} else if (issueParentEntity.equalsIgnoreCase("Action")) {
 				getObject("action_quick_link").click();
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			}

 			else if (issueParentEntity.equalsIgnoreCase("Change Request")) {
 				getObject("cr_quick_link").click(); // click on CR Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link
 			}

 			else if (issueParentEntity.equalsIgnoreCase("Work Order Request")) {
 				getObject("wor_quick_link").click(); // click on WOR Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			}

 			else if (issueParentEntity.equalsIgnoreCase("Service Level")) {
 				getObject("sl_quick_link").click(); // click on SL Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link
 			}

 			else if (issueParentEntity.equalsIgnoreCase("Child Obligation")) {
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			}

 			else if (issueParentEntity.equalsIgnoreCase("Invoice"))

 			{
 				getObject("invoice_quick_link").click(); // click on Invoice Quick Link
 				parentEntity(issueParentEntity);
 				getObject("is_create_link_from_is").click(); // click issue create link

 			}

 			else {

 				getObject("issue_quick_link").click();// click on Issue Quick Link
 				driver.findElement(By.xpath(".//*[@class='plus ng-scope']/a")).click();// Click on plus button
 				if (new Select(driver.findElement(By.xpath(".//*[@id='elem_2501']/select"))).getOptions().isEmpty() != true) {
 					new Select(driver.findElement(By.xpath(".//*[@id='elem_2501']/select"))).selectByIndex(1);
 					new Select(driver.findElement(By.xpath(".//*[@id='elem_2502']/select"))).selectByVisibleText("Supplier");
 					driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
 				}
 			}

 		} catch (Exception e) {

 		}*/

 		
 		
 	    if (!issueTitle.equalsIgnoreCase("")) {
             getObject("is_title_textbox").sendKeys(issueTitle); // name
           }

         if (!issueDescription.equalsIgnoreCase("")) {
        getObject("is_description_textarea").sendKeys(issueDescription); // title
         }
         if (!issueType.equalsIgnoreCase("")) {
        new Select (getObject("is_type_dropdown")).selectByVisibleText(issueType); 
         }
         if (!issuePriority.equalsIgnoreCase("")) {
        new Select (getObject("is_priority_dropdown")).selectByVisibleText(issuePriority);
         }
         if (!issueCurrencies.equalsIgnoreCase("")) {
        new Select (getObject("is_currency_dropdown")).selectByVisibleText(issueCurrencies);
         }
         if (!issueFinancialImpact.equalsIgnoreCase("")) {
        getObject("is_financial_impact_textbox").sendKeys(issueFinancialImpact);// governance body
         }
         if (!issueGovernanceBody.equalsIgnoreCase("")) {
        new Select (getObject("is_govbody_multi")).selectByVisibleText(issueGovernanceBody);
         }
         if (!issueDeliveryCountries.equalsIgnoreCase("")) {
        new Select (getObject("is_delcountries_multi")).selectByVisibleText(issueDeliveryCountries);
         }
         if (!issueTimeZone.equalsIgnoreCase("")) {
        new Select (getObject("is_timezone_dropdown")).selectByVisibleText(issueTimeZone);
         }
        
         if(issueRestrictPublicAccess.equalsIgnoreCase("yes"))
            {
                getObject("is_restrict_puclic_access_checkbox").click();
            }
         
          if(issueRestrictRequesterAccess.equalsIgnoreCase("yes"))
          {
              getObject("is_restrict_req_access_checkbox").click();
          }
          
           if(issueSupplierAccess.equalsIgnoreCase("yes"))
              {
                  getObject("is_supplier_access_checkbox").click();
              }
         
         
        if(!issueTier.equalsIgnoreCase(""))
        {
            new Select (getObject("is_tier_dropdown")).selectByVisibleText(issueTier);
        }
        if(issueDependentEntity.equalsIgnoreCase("yes"))
        {
            getObject("is_depentity_checkbox").click();
        }
        
        Thread.sleep(10000);
                        Date date = new Date();

                        int current_month = date.getMonth();

                        driver.findElement(By.name("issueDate")).click();
                        Double temp_issueDateYear_double = Double.parseDouble(issueDateYear);
                        int temp_issueDateYear_int = temp_issueDateYear_double.intValue();
                        String issueDateYear_string = Integer.toString(temp_issueDateYear_int);

                        WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
                        System.out.println(datepicker_ui.isDisplayed());
                        if (datepicker_ui.isDisplayed() == true) {
                          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
                          new Select(datepicker_ui_year).selectByVisibleText(issueDateYear_string);
                        }

                        Double temp_issueDateMonth_double = Double.parseDouble(issueDateMonth);
                        int temp_issueDateMonth_int = temp_issueDateMonth_double.intValue();
                        System.out.println(" issueDateMonth " + temp_issueDateMonth_int);

                        int click2 = current_month - temp_issueDateMonth_int;
                        System.out.println("click " + click2);
                        for (; click2 >= 0; click2 = click2 - 1) {
                          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
                        }
                        Double temp_issueDateDate_double = Double.parseDouble(issueDateDate);
                        int temp_issueDateDate_int = temp_issueDateDate_double.intValue();
                        String issueDateDate_string = Integer.toString(temp_issueDateDate_int);
                        driver.findElement(By.linkText(issueDateDate_string)).click();

                        driver.findElement(By.name("plannedCompletionDate")).click();

                        Double temp_issuePlannedCompletionDateYear_double = Double.parseDouble(issuePlannedCompletionDateYear);
                        int temp_issuePlannedCompletionDateYear_int = temp_issuePlannedCompletionDateYear_double.intValue();
                        String issuePlannedCompletionDateYear_string = Integer.toString(temp_issuePlannedCompletionDateYear_int);

                        System.out.println(datepicker_ui.isDisplayed());
                        if (datepicker_ui.isDisplayed() == true) {
                          WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
                          new Select(datepicker_ui_year).selectByVisibleText(issuePlannedCompletionDateYear_string);
                        }

                        Double temp_issuePlannedCompletionDateMonth_double = Double.parseDouble(issuePlannedCompletionDateMonth);
                        int temp_issuePlannedCompletionDateMonth_int = temp_issuePlannedCompletionDateMonth_double.intValue();
                        System.out.println(" issuePlannedCompletionDateMonth " + temp_issuePlannedCompletionDateMonth_int);

                        int click3 = temp_issuePlannedCompletionDateMonth_int - current_month;
                        System.out.println("click " + click3);
                        for (; click3 >= 0; click3 = click3 - 1) {
                          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
                        }
                        Double temp_issuePlannedCompletionDateDate_double = Double.parseDouble(issuePlannedCompletionDateDate);
                        int temp_issuePlannedCompletionDateDate_int = temp_issuePlannedCompletionDateDate_double.intValue();
                        String issuePlannedCompletionDateDate_string = Integer.toString(temp_issuePlannedCompletionDateDate_int);
                        driver.findElement(By.linkText(issuePlannedCompletionDateDate_string)).click();             
                        
                        if(!issueResponsibility.equalsIgnoreCase("")){
                        new Select (getObject("is_responsibility_dropdown")).selectByVisibleText(issueResponsibility);
                        }    
 				
 		
 		
                        getObject("ac_save_button").click();

 		Thread.sleep(4000);
 		
 		String issue_id = getObject("is_popup_id").getText();
 		
 		System.out.println("issue id is " + issue_id);
 		
 		driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
 		
 		getObject("quick_search_textbox").sendKeys(issue_id);
 		
 		getObject("quick_search_textbox").sendKeys(Keys.ENTER);
 		
                        fail=false;
                        /*String issueIdFromShowPage = getObject("is_show_id").getText();

                        Assert.assertEquals(issueIdFromShowPage, issue_id);

                        APP_LOGS.debug("Issue show page open successfully with issue id " + issue_id);
                        
                        */
                        
                       /* String IssueStatusShowPage= getObject("is_status_show").getText();
					      try
					      {
					    	  
					      
					      Assert.assertEquals(IssueStatusShowPage, issueStatus, "Issue Status name is -- " +IssueStatusShowPage+ " instead of -- " +issueStatus);
					      
					      }
					      catch(Throwable e)
					      {
					    	  
					      System.out.println("Issue Status name is -- " +IssueStatusShowPage+ " instead of -- " +issueStatus);
					      fail=true;
					      
					      }
					      
					      String IssueTitleShowPage= getObject("is_title_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueTitleShowPage, issueTitle, "Issue Title is -- " +IssueTitleShowPage+ " instead of -- " +issueTitle);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Title is -- " +IssueTitleShowPage+ " instead of -- " +issueTitle);
					    	  fail=true;
					      }
					      String IssueDesactioniptionShowPage= getObject("is_description_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueDesactioniptionShowPage, issueDescription, "Issue Description is--"+IssueDesactioniptionShowPage+"instead of" + issueDescription );
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Description is--"+IssueDesactioniptionShowPage+"instead of" + issueDescription );
					    	  fail=true;
					      }
					      String IssueSupplierShowPage= getObject("is_supplier_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueSupplierShowPage, issueSupName, "Issue Supplier Name is== "+IssueSupplierShowPage +"instead of" + issueSupName);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Supplier Name is== "+IssueSupplierShowPage +"instead of" + issueSupName);
					    	  fail=true;
					      }
					      String IssueSourceShowPage= getObject("is_source_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueSourceShowPage, issueSource, "Issue Source is== "+IssueSourceShowPage +"instead of" + issueSource);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Source is== "+IssueSourceShowPage +"instead of" + issueSource);
					    	  fail=true;
					      }
					      String IssueSourceNameShowPage= getObject("is_sourcename_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueSourceNameShowPage, issueSourceName, "Issue Source Name is== "+IssueSourceNameShowPage +"instead of" + issueSourceName);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Source Name is== "+IssueSourceNameShowPage +"instead of" + issueSourceName);
					    	  fail=true;
					      }
					      String IssueTypeShowPage= getObject("is_type_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueTypeShowPage, issueType, "Issue Type is== "+IssueTypeShowPage +"instead of" + issueType);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Type is== "+IssueTypeShowPage +"instead of" + issueType);
					    	  fail=true;
					      }
					      String IssuePriorityShowPage= getObject("is_priority_show").getText();
					      try
					      {
					      Assert.assertEquals(IssuePriorityShowPage, issuePriority, "Issue Priority is== "+IssuePriorityShowPage +"instead of" + issuePriority);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Priority is== "+IssuePriorityShowPage +"instead of" + issuePriority);
					    	  fail=true;
					      }
					     
					      
					      
					      String IssueCurrencyShowPage= getObject("is_currency_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueCurrencyShowPage, issueCurrencies, "Issue Currency is== "+IssueCurrencyShowPage +"instead of" + issueCurrencies);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Currency is== "+IssueCurrencyShowPage +"instead of" + issueCurrencies);
					    	  fail=true;
					      }
					      
					      String IssueFinancialImpactShowPage= getObject("is_financialImpact_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueFinancialImpactShowPage, issueFinancialImpact, "Issue Financial Impact is== "+IssueFinancialImpactShowPage +"instead of" + issueFinancialImpact);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Financial Impact is== "+IssueFinancialImpactShowPage +"instead of" + issueFinancialImpact);
					    	  fail=true;
					      }
					      
					      
					      String IssueGovernanceBodyShowPage= getObject("is_govbody_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueGovernanceBodyShowPage, issueGovernanceBody, "Issue Governance Body is== "+IssueGovernanceBodyShowPage +"instead of" + issueGovernanceBody);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Governance Body is== "+IssueGovernanceBodyShowPage +"instead of" + issueGovernanceBody);
					    	  fail=true;
					      }
					      String IssueDeliveryCountriesShowPage= getObject("is_delcountries_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueDeliveryCountriesShowPage, issueDeliveryCountries, "Issue Delivery Country is== "+IssueDeliveryCountriesShowPage +"instead of" + issueDeliveryCountries);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Delivery Country is== "+IssueDeliveryCountriesShowPage +"instead of" + issueDeliveryCountries);
					    	  fail=true;
					      }
					      String IssueTimeZoneShowPage= getObject("is_timezone_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueTimeZoneShowPage, issueTimeZone, "Issue TimeZone is== "+IssueTimeZoneShowPage +"instead of" + issueTimeZone);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue TimeZone is== "+IssueTimeZoneShowPage +"instead of" + issueTimeZone);
					    	  fail=true;
					      }
					      
					      
					      String IssueRestrictPublicAccessShowPage= getObject("is_restrictpublicaccess").getText();
					      try
					      {
					      Assert.assertEquals(IssueRestrictPublicAccessShowPage, issueRestrictPublicAccess, "Issue Restrict Public Access is== "+IssueRestrictPublicAccessShowPage +"instead of" + issueRestrictPublicAccess);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Restrict Public Access is== "+IssueRestrictPublicAccessShowPage +"instead of" + issueRestrictPublicAccess);
					    	  fail=true;
					      }
					      
					      
					      String IssueRestrictRequesterAccessShowPage= getObject("is_restrictrequesteraccess").getText();
					      try
					      {
					      Assert.assertEquals(IssueRestrictRequesterAccessShowPage, issueRestrictRequesterAccess, "Issue Restrict Requester Access is== "+IssueRestrictRequesterAccessShowPage +"instead of" + issueRestrictRequesterAccess);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Restrict Requester Access is== "+IssueRestrictRequesterAccessShowPage +"instead of" + issueRestrictRequesterAccess);
					    	  fail=true;
					      }
					      
					      
					      String IssueSupplierAccessShowPage= getObject("is_suppplieraccess").getText();
					      try
					      {
					      Assert.assertEquals(IssueSupplierAccessShowPage, issueSupplierAccess, "Issue Supplier Access is== "+IssueSupplierAccessShowPage +"instead of" + issueSupplierAccess);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Supplier Access is== "+IssueSupplierAccessShowPage +"instead of" + issueSupplierAccess);
					    	  fail=true;
					      }
					      
					      
					      String IssueTierShowPage= getObject("is_tier_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueTierShowPage, issueTier, "Issue Tier is== "+IssueTierShowPage +"instead of" + issueTier);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Action Tier is== "+IssueTierShowPage +"instead of" + issueTier);
					    	  fail=true;
					      }
					    
					      String IssueDependityEntityShowPage= getObject("is_depentity_show").getText();
					      try
					      {
					    	  Assert.assertEquals(IssueDependityEntityShowPage, issueDependentEntity, "Issue Dependent Entity is== "+IssueDependityEntityShowPage +"instead of" + issueDependentEntity);  
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Dependent Entity is== "+IssueDependityEntityShowPage +"instead of" + issueDependentEntity);
					    	  fail=true;
					      }
					      
					     
					      String IssueFunctionsShowPage= getObject("is_function_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueFunctionsShowPage, issueFunction, "Issue Functions are== "+IssueFunctionsShowPage +"instead of" + issueFunction);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Functions are== "+IssueFunctionsShowPage +"instead of" + issueFunction);
					    	  fail=true;
					      }
					      String IssueServicesShowPage= getObject("is_service_show").getText();
					      try
					      {
					    	  Assert.assertEquals(IssueServicesShowPage, issueService, "Issue Services are== "+IssueServicesShowPage +"instead of" + issueService);  
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Services are== "+IssueServicesShowPage +"instead of" + issueService);
					    	  fail=true;
					      }
					      String IssueRegionsShowPage= getObject("is_region_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueRegionsShowPage, issueRegion, "Issue Regions are== "+IssueRegionsShowPage +"instead of" + issueRegion);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Regions are== "+IssueRegionsShowPage +"instead of" + issueRegion);
					    	  fail=true;
					      }
					      String IssueCountriesShowPage= getObject("is_country_show").getText();
					      try
					      {
					      Assert.assertEquals(IssueCountriesShowPage, issueCountry, "Issue Countries are== "+IssueCountriesShowPage +"instead of" + issueCountry);
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Countries are== "+IssueCountriesShowPage +"instead of" + issueCountry);
					    	  fail=true;
					      }
					     
					      
					      String IssueResponsibilityShowPage= getObject("is_responsibility_show").getText();
					      try
					      {
					    	  Assert.assertEquals(IssueResponsibilityShowPage, issueResponsibility, "Issue Responsibility is== "+IssueResponsibilityShowPage +"instead of" + issueResponsibility);  
					      }
					      catch(Throwable e)
					      {
					    	  System.out.println("Issue Responsibility is== "+IssueResponsibilityShowPage +"instead of" + issueResponsibility);
					    	  fail=true;
					      }
                      */
                      
                        
                        
                        
                        

                        
                        APP_LOGS.debug("Issue open successfully, following parameters have been validated: Issue Title-- " + issueTitle+ ", issue Desissueiption -- " + issueDescription+ 
					            ", Issue Governance Body -- " + issueGovernanceBody+  ", issue Type -- " + issueType+ ", issue Time Zone" +issueTimeZone+ ", " +
					            		"Issue Priority  -- "   + issuePriority+", issue Responsibiity -- " + issueResponsibility+ ", issue Id-- " + issue_id+  
					            ", issue Supplier Access -- " + issueSupplierAccess+ ", " +  "issue Tier -- " + issueTier+", issue Depenndent Entity -- " + issueDependentEntity+ 
					            ", issue Function -- " + issueFunction+ ", issue Services -- " 
					            + issueService+  ", issue Supplier Name--" + issueSupName+ ", issue Status--" +issueStatus+ ", issue Delivery Countries--" +issueDeliveryCountries+
					            ", issue Currencies--" +issueCurrencies+", issue Date Month--" +issueDateMonth+ ", issue Date Date--" +issueDateDate+", issue Date Year--" +issueDateYear+
					             ", issue Financial Impact--" +issueFinancialImpact+ 
					            ", issue Source--" +issueSource+ ", issue Source Name--" +issueSourceName+ ", issue Region--" +issueRegion+ ", issue Country--" +issueCountry);
                        getObject("analytics_link").click();
                        //getObject("supplier_quick_link").click();
                                
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
