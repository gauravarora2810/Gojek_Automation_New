package test.resources.com.sirion.suite.calenderTodo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.resources.com.sirion.util.TestUtil;

public class TODOTaskDueDateContentControl extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(calendar_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(calendar_suite_xls, this.getClass().getSimpleName());
  }

  @Test(dataProvider = "getTestData")
  public void TODOTaskDueDateContentControlTest(String entityName, String roleGroup,String status1, String status2) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }
    
    openBrowser();
    driver.manage().window().maximize();
    
    clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
    APP_LOGS.debug("Executing Client Admin TODO Tasks content control test -- " +entityName);
    
    getObject("admin_tab_link").click();
    getObject("todo_tasks_due_date_link").click();
    
    //Select Action Role Group and Status 
    if (entityName.equalsIgnoreCase("Action")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_action_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_action_role_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_action_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO Tasks content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    //Select Action Parent Role Group and Status 
    if (entityName.equalsIgnoreCase("Action Parent")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_action_parent_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_action_parentrole_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_action_parentrole_status1_multiselect")).selectByVisibleText(status1Split[i]);
	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    
    if (entityName.equalsIgnoreCase("Change Request")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_change_request_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_change_request_role_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_change_request_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    //Select Change Request Parent Role Group and Status 
    if (entityName.equalsIgnoreCase("Change Request Parent")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_change_request_parent_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_change_request_parent_role_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_change_request_parent_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    
    if (entityName.equalsIgnoreCase("Child Obligation")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_child_obligation_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_child_obligation_role_status1_multiselect")).deselectAll();
		new Select(getObject("todo_tasks_due_date_child_obligation_role_status2_multiselect")).deselectAll();


    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_child_obligation_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	
    	if(!status2.equalsIgnoreCase("")){
        	String[] status2Split=  status2.split(";");
        	for (int i=0; i < status2Split.length;i++){
        	new Select(getObject("todo_tasks_due_date_child_obligation_role_status2_multiselect")).selectByVisibleText(status2Split[i]);
        	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}}
    
    //Select Child Obligation Parent Role Group and Status 
    if (entityName.equalsIgnoreCase("Child Obligation Parent")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_child_obligation_parent_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_child_obligation_parent_role_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_child_obligation_parent_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    
    if (entityName.equalsIgnoreCase("Child Service Level")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_child_service_level_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_child_service_level_role_status1_multiselect")).deselectAll();
		new Select(getObject("todo_tasks_due_date_child_service_level_role_status2_multiselect")).deselectAll();


    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_child_service_level_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	
    	if(!status2.equalsIgnoreCase("")){
        	String[] status2Split=  status2.split(";");
        	for (int i=0; i < status2Split.length;i++){
        	new Select(getObject("todo_tasks_due_date_child_service_level_role_status2_multiselect")).selectByVisibleText(status2Split[i]);
        	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}}
    
    //Select Child Service Level Parent Role Group and Status 
    if (entityName.equalsIgnoreCase("Child  Service Level Parent")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_child_service_level_parent_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_child_service_level_parent_role_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_child_service_level_parent_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    //Select Contract Draft Request Parent Role Group and Status
    if (entityName.equalsIgnoreCase("Contract Draft Request")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_contract_draft_request_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_contract_draft_request_role_status1_multiselect")).deselectAll();

    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_contract_draft_request_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}
    
    //Select Governance Body Meeting Role Group and Status 
    if (entityName.equalsIgnoreCase("Governance Body Meeting")) {    	
    	if(!roleGroup.equalsIgnoreCase("")){
    	new Select(getObject("todo_tasks_due_date_governance_body_meeting_role_group1_dropdown")).selectByVisibleText(roleGroup);
		new Select(getObject("todo_tasks_due_date_governance_body_meeting_role_status1_multiselect")).deselectAll();
		new Select(getObject("todo_tasks_due_date_governance_body_meeting_role_status2_multiselect")).deselectAll();


    	}
    	if(!status1.equalsIgnoreCase("")){
    	String[] status1Split=  status1.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_governance_body_meeting_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
    	
      }
	  if(!status2.equalsIgnoreCase("")){
    	String[] status2Split=  status2.split(";");
    	for (int i=0; i < status1Split.length;i++){
    	new Select(getObject("todo_tasks_due_date_governance_body_meeting_role_status2_multiselect")).selectByVisibleText(status2Split[i]);
    	}
    	driver.findElement(By.xpath("//input[@value='Save']")).click();
    	
    	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
    	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
    	      Thread.sleep(5000);
    	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
    	      fail=false;
    	  }
      }}}
    
      if (entityName.equalsIgnoreCase("Governance Body Meeting Parent")) {    	
        	if(!roleGroup.equalsIgnoreCase("")){
        	new Select(getObject("todo_tasks_due_date_governance_body_meeting_parent_role_group1_dropdown")).selectByVisibleText(roleGroup);
    		new Select(getObject("todo_tasks_due_date_governance_body_meeting_parent_role_status1_multiselect")).deselectAll();

        	}
        	if(!status1.equalsIgnoreCase("")){
        	String[] status1Split=  status1.split(";");
        	for (int i=0; i < status1Split.length;i++){
        	new Select(getObject("todo_tasks_due_date_governance_body_meeting_parent_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
        	}
        	driver.findElement(By.xpath("//input[@value='Save']")).click();
        	
        	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
        	      APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
        	      Thread.sleep(5000);
        	      getObject("todo_tasks_due_date_pop_up_ok_button").click();
        	      fail=false;
        	  }
          }}
      
      //Select Issue Role Group and Status 
      if (entityName.equalsIgnoreCase("Issue")) {    	
      	if(!roleGroup.equalsIgnoreCase("")){
      	new Select(getObject("todo_tasks_due_date_issue_role_group1_dropdown")).selectByVisibleText(roleGroup);
  		new Select(getObject("todo_tasks_due_date_issue_role_status1_multiselect")).deselectAll();

      	}
      	if(!status1.equalsIgnoreCase("")){
      	String[] status1Split=  status1.split(";");
      	for (int i=0; i < status1Split.length;i++){
      	new Select(getObject("todo_tasks_due_date_issue_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
      	}
      	driver.findElement(By.xpath("//input[@value='Save']")).click();
      	
      	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
            APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
            Thread.sleep(5000);
            getObject("todo_tasks_due_date_pop_up_ok_button").click();
            fail=false;
        }
        }}
      
      //Select Issue Parent Role Group and Status 
      if (entityName.equalsIgnoreCase("Issue Parent")) {    	
      	if(!roleGroup.equalsIgnoreCase("")){
      	new Select(getObject("todo_tasks_due_date_issue_parent_role_group1_dropdown")).selectByVisibleText(roleGroup);
  		new Select(getObject("todo_tasks_due_date_issue_parent_role_status1_multiselect")).deselectAll();

      	}
      	if(!status1.equalsIgnoreCase("")){
      	String[] status1Split=  status1.split(";");
      	for (int i=0; i < status1Split.length;i++){
      	new Select(getObject("todo_tasks_due_date_issue_parent_role_status1_multiselect")).selectByVisibleText(status1Split[i]);
      	}
      	driver.findElement(By.xpath("//input[@value='Save']")).click();
      	
      	if(driver.getPageSource().contains("To Do Parameters updated successfully")){
            APP_LOGS.debug("TODO content control set successfully for report -- "+entityName);
            Thread.sleep(5000);
            getObject("todo_tasks_due_date_pop_up_ok_button").click();
            fail=false;
        }
        }}
    
    getObject("admin_tab_link").click();
  }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(calendar_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(calendar_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(calendar_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = true;

  }

  @AfterTest
  public void reportTestResult() {
	    driver.findElement(By.xpath("//*[@id='gl-logout']/a/span")).click();

    if (isTestPass)
      TestUtil.reportDataSetResult(calendar_suite_xls, "Test Cases", TestUtil.getRowNum(calendar_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(calendar_suite_xls, "Test Cases", TestUtil.getRowNum(calendar_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(calendar_suite_xls, this.getClass().getSimpleName());
  }

}
