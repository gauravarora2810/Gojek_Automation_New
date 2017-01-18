package test.resources.com.sirion.suite.issue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class IssueAuditLog extends TestSuiteBase{
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
    public void IssueAuditLogTest() throws Exception {
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
		Thread.sleep(20000);
		getObject("is_id_link").click(); // Issue ID Clicking
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//a[contains(.,'AUDIT LOG')]")).click();
        Thread.sleep(10000);
  
        // Audit Log commented down	 
         
         
        APP_LOGS.debug("AuditLog Verification of Issue is done");
         
		getObject("analytics_link").click();
		fail = false;
        
    }
    
	// Audit Log commented down
	/*
	 * Verification of Requested By Column in AuditLog
	 * 
	 * String
	 * requestedByName=getObject("is_requested_by_audilog_text").getText();
	 * System.out.println(issueRequestedBy);
	 * 
	 * if(requestedByName.equalsIgnoreCase(issueRequestedBy)) {
	 * System.out.println("Issue Requested by Name: "+ requestedByName
	 * +" in AuditLog tab and "+
	 * "Name that we have given in comments section i.e " + issueRequestedBy
	 * +" are same"); }
	 * 
	 * if(!issueRequestedBy.equalsIgnoreCase("")){
	 * if(requestedByName.equalsIgnoreCase(is_user_performed)){
	 * System.out.println("Issue Requested by Name: "+ requestedByName
	 * +" in AuditLog tab and "+
	 * "Name that we have given in comments section i.e " + is_user_performed
	 * +" are same"); } }
	 * 
	 * // Verification of Completed By Column in AuditLog
	 * 
	 * String
	 * completedByName=getObject("is_completed_by_auditlog_text").getText();
	 * System.out.println(completedByName);
	 * 
	 * if(completedByName.equalsIgnoreCase(is_user_performed)) {
	 * System.out.println("Issue Completed by Name: "+ completedByName
	 * +" in AuditLog tab and "+
	 * "Name that we have given in comments section i.e " + is_user_performed
	 * +" are same"); }
	 * 
	 * // Verification of Comments Column in AuditLog
	 * 
	 * String comments_made=getObject("is_comment_auditlog_text").getText();
	 * System.out.println(comments_made);
	 * 
	 * if(!issueComment.equalsIgnoreCase("")){
	 * if(comments_made.equalsIgnoreCase("Yes")){
	 * System.out.println("Issue Commented by Name "
	 * +is_user_performed+" and is added in the AuditLog"); } }
	 * if(issueComment.equalsIgnoreCase("")){
	 * if(comments_made.equalsIgnoreCase("No")){
	 * System.out.println("Issue is not Commented by Name "+is_user_performed);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * // Verification of Documents Column in AuditLog
	 * 
	 * String documents_added=getObject("is_document_auditlog_text").getText();
	 * System.out.println(documents_added);
	 * 
	 * if(!issueComment.equalsIgnoreCase("")){
	 * if(documents_added.equalsIgnoreCase("Yes")){
	 * System.out.println("Issue documents uploaded by Name "
	 * +is_user_performed+" and is added in the AuditLog"); } }
	 * if(issueComment.equalsIgnoreCase("")){
	 * if(documents_added.equalsIgnoreCase("No")){
	 * System.out.println("Issue document is not uploaded by Name "
	 * +is_user_performed);
	 * 
	 * }
	 */
    
    
    
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
