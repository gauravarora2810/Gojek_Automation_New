package test.resources.com.sirion.suite.issue;

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

public class IssueAuditLogOptimized extends TestSuiteBase{
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
        
        if(!TestUtil.isTestCaseRunnable(issue_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(issue_suite_xls, this.getClass().getSimpleName());
    }
    
    @Test (dataProvider = "getTestData")
    public void IssueAuditLogTest(
        String issueTitle, String issueDescription, String  issueType, String issuePriority,  
        String issueCurrencies, String issueFinancialImpact, String issueGovernanceBody,
        String issueDeliveryCountries,  String issueTimeZone, String issueRestrictPublicAccess, 
        String issueRestrictRequesterAccess, String issueSupplierAccess,
        String issueTier, String issueDependentEntity, String 	issueDateMonth,
        String issueDateDate, String issueDateYear, String issuePlannedCompletionDateMonth,
        String issuePlannedCompletionDateDate, String issuePlannedCompletionDateYear,
        String issueResponsibility, String issueStatus, String issueSupName,
        String issueSource, String issueSourceName, String issueFunction,
        String issueService, String issueRegion, String issueCountry, String issueComment,
        String issueActualDateDate, String issueActualDateMonth,
        String issueActualDateYear, String issueRequestedBy,
        String issueChangeRequest, String issueUploadedFile
            ) throws InterruptedException {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
        APP_LOGS.debug("Executing Test Case Issue Audit Log Creation");
        
        openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getObject("analytics_link").click();
		getObject("issue_quick_link").click(); // Issue Quick Link Clicking
		Thread.sleep(10000);
		getObject("is_id_link").click(); // Issue ID Clicking
		
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//button[contains(.,'Edit')]")).click(); // Issue Edit button
        
       
        
        
        // Comments and Attachments Section
        
        if(!issueComment.equalsIgnoreCase("")){
        	getObject("is_Comment_text_area").clear();
        	getObject("is_Comment_text_area").sendKeys(issueComment);
        }
         
        if(!issueRequestedBy.equalsIgnoreCase("")){  	
             	new Select(getObject("is_requested_by_dropdown")).selectByVisibleText(issueRequestedBy);
        }
        
        if(!issueChangeRequest.equalsIgnoreCase(""))
        {
        	new Select(getObject("is_change_request_dropdown")).selectByVisibleText(issueChangeRequest);
        }
        
        
        Date date = new Date();
        WebElement datepicker_ui=null;
        int current_month = date.getDate();
        if(!issueActualDateYear.equalsIgnoreCase("") && !issueActualDateMonth.equalsIgnoreCase("") && !issueActualDateDate.equalsIgnoreCase("")){
            driver.findElement(By.name("actualDate")).click();
            Double temp_issueActualDateYear_double = Double.parseDouble(issueActualDateYear);
            int temp_issueActualDateYear_int = temp_issueActualDateYear_double.intValue();
            String issueActualDateYear_string = Integer.toString(temp_issueActualDateYear_int);

            WebElement datepicker_ui11 = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
            System.out.println(datepicker_ui11.isDisplayed());
            if (datepicker_ui11.isDisplayed() == true) {
              WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
              new Select(datepicker_ui_year).selectByVisibleText(issueActualDateYear_string);
            }

            Double temp_issueActualDateMonth_double = Double.parseDouble(issueActualDateMonth);
            int temp_issueActualDateMonth_int = temp_issueActualDateMonth_double.intValue();
            System.out.println(" issueActualDateMonth " + temp_issueActualDateMonth_int);

            int click_issueActualDateMonth = current_month - temp_issueActualDateMonth_int;
            System.out.println("click " + click_issueActualDateMonth);
            for (; click_issueActualDateMonth >= 0; click_issueActualDateMonth = click_issueActualDateMonth - 1) {
              driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
            }
            Double temp_issueActualDateDate_double = Double.parseDouble(issueActualDateDate);
            int temp_issueActualDateDate_int = temp_issueActualDateDate_double.intValue();
            String issueActualDateDate_string = Integer.toString(temp_issueActualDateDate_int);
            driver.findElement(By.linkText(issueActualDateDate_string)).click();
        	
        }
        

/*        if(!issueUploadedFile.equalsIgnoreCase(""))
        {
            getObject("is_upload_file_browse").sendKeys("C:\\Users\\gaurav.arora\\Desktop\\Master_Obligation_Salil (2) latest.xlsx");
        }
*/       

        driver.findElement(By.xpath(".//button[contains(.,'Update')]")).click();
       
        Thread.sleep(5000);
        
        try
        {
        if(driver.findElement(By.className("success-icon")).getText().contains("Either you do not have the required permissions or requested page does not exist anymore.")){
        driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
        Thread.sleep(40000);
        }
       
               
        }
        catch(Exception e)
        {
        	
        	 String is_id = getObject("is_show_id").getText();
             System.out.println(is_id);
             
             /*String is_user_performed = getObject("is_user_name_text").getText();
             System.out.println(is_user_performed);
*/
             
             APP_LOGS.debug("Issue updated successfully with Issue id " + is_id);

             APP_LOGS.debug("Quick Search the updated issue with Issue id " + is_id);

             getObject("quick_search_textbox").sendKeys(is_id);
             
             getObject("quick_search_textbox").sendKeys(Keys.ENTER);
             
             Thread.sleep(5000);
    
             driver.findElement(By.xpath(".//a[contains(.,'AUDIT LOG')]")).click();
             
             Thread.sleep(10000);

             

        }
                /*// Verification of Requested By Column in AuditLog
              
                String requestedByName=getObject("is_requested_by_audilog_text").getText();
                   System.out.println(issueRequestedBy);
                
              if(requestedByName.equalsIgnoreCase(issueRequestedBy))
                {
                	System.out.println("Issue Requested by Name: "+ requestedByName +" in AuditLog tab and "+ "Name that we have given in comments section i.e " + issueRequestedBy +" are same");
                }
              
              if(!issueRequestedBy.equalsIgnoreCase("")){
            	  if(requestedByName.equalsIgnoreCase(is_user_performed)){
              	System.out.println("Issue Requested by Name: "+ requestedByName +" in AuditLog tab and "+ "Name that we have given in comments section i.e " + is_user_performed +" are same");
            	  } 
              }

              // Verification of Completed By Column in AuditLog

              String completedByName=getObject("is_completed_by_auditlog_text").getText();
              System.out.println(completedByName);
           
        if(completedByName.equalsIgnoreCase(is_user_performed))
           {
           	System.out.println("Issue Completed by Name: "+ completedByName +" in AuditLog tab and "+ "Name that we have given in comments section i.e " + is_user_performed +" are same");
           }

        // Verification of Comments Column in AuditLog   
        
         String comments_made=getObject("is_comment_auditlog_text").getText();
         System.out.println(comments_made);      
         
         if(!issueComment.equalsIgnoreCase("")){
         	if(comments_made.equalsIgnoreCase("Yes")){
         		System.out.println("Issue Commented by Name "+is_user_performed+" and is added in the AuditLog");
         	}
         }
         if(issueComment.equalsIgnoreCase("")){
        	 if(comments_made.equalsIgnoreCase("No")){
          		System.out.println("Issue is not Commented by Name "+is_user_performed);
        		 
        	 }      
        	 
         }

         // Verification of Documents Column in AuditLog
         
         String documents_added=getObject("is_document_auditlog_text").getText();
         System.out.println(documents_added);      
         
         if(!issueComment.equalsIgnoreCase("")){
         	if(documents_added.equalsIgnoreCase("Yes")){
         		System.out.println("Issue documents uploaded by Name "+is_user_performed+" and is added in the AuditLog");
         	}
         }
         if(issueComment.equalsIgnoreCase("")){
        	 if(documents_added.equalsIgnoreCase("No")){
          		System.out.println("Issue document is not uploaded by Name "+is_user_performed);
        		 
        	 }      
        	 
         }*/
         
         APP_LOGS.debug("AuditLog Verification of Issue is done");
         
		getObject("analytics_link").click();
		fail = false;
        
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(issue_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(issue_suite_xls, "Test Cases", TestUtil.getRowNum(issue_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData(){
        return TestUtil.getData(issue_suite_xls, this.getClass().getSimpleName()) ;
    }

}
