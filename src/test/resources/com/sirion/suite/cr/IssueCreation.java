package test.resources.com.sirion.suite.cr;

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
    static boolean fail=false;
    static boolean skip=false;
    static boolean isTestPass=true;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(cr_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the runmodes off the tests
        runmodes=TestUtil.getDataSetRunmodes(cr_suite_xls, this.getClass().getSimpleName());
    }
    
    @Test (groups = "ContractCreation", dataProvider = "getTestData")
    
    public void IssueCreate(
            String issueTitle, String issueDescription, String issueType, String issuePriority, String issueCurrencies,
            String issueFinancialImpact, String issueGovernanceBody, String issueDeliveryCountries, 
            String issueTimeZone, String issueRestrictPublicAccess, String issueRestrictRequesterAccess, String issueSupplierAccess, 
            String issueTier, String issueDependentEntity, 
            String issueDateMonth, String issueDateDate, String issueDateYear,
            String issuePlannedCompletionDateMonth, String issuePlannedCompletionDateDate, String issuePlannedCompletionDateYear,
            String issueResponsibility) throws InterruptedException
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

     		
      		
        
      		getObject("cr_quick_link").click(); // IP Quick Link Clicking
      		Thread.sleep(20000);
      		WebDriverWait wait=new WebDriverWait(driver, 50);
      		//wait.until(ExpectedConditions.elementToBeClickable(getObject("cr_id_link")));
      
      		getObject("cr_id_link").click(); 
         
      		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
     	    plus_button("cr_plus_button_link"); // web element for plus button on supplier page
     	    wait.until(ExpectedConditions.elementToBeClickable(getObject("is_create_link_from_cr")));
         
     	    getObject("is_create_link_from_cr").click(); // click issue create link 
     		
     		
     		
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
                        /*String issueIdFromShowPage = getObject("is_show_id").getText();

                        Assert.assertEquals(issueIdFromShowPage, issue_id);

                        APP_LOGS.debug("Issue show page open successfully with issue id " + issue_id);*/
                        fail = false; // this executes if assertion passes

                        getObject("analytics_link").click();
                       // getObject("supplier_quick_link").click();
                                
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(cr_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil.getRowNum(cr_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(cr_suite_xls, "Test Cases", TestUtil.getRowNum(cr_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(cr_suite_xls, this.getClass().getSimpleName()) ;
    }


}
