package test.resources.com.sirion.suite.calenderTodo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class EndUserTodoApproval  extends TestSuiteBase {
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
	  public void EndUserTodoApprovalTest() throws InterruptedException {
	  
		// test the runmode of current dataset
		    count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }
		    
		    openBrowser();
		    		    
		    endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));
		    
		    Thread.sleep(10000);
		    
		    WebElement we = driver.findElement(By.xpath("//*[@id='today']/div"));
		    String[] classname = we.getAttribute("class").split(" ");
		    if (!classname[0].equalsIgnoreCase("emptyTodo")){
		    	
		    	getObject("end_user_todays_approvals_link").click();
		    	Thread.sleep(5000);
		    	getObject("end_user_todays_approval_popup_close_link").click();
		    	
		    	getObject("end_user_todays_tasks_link").click();
		    	Thread.sleep(5000);
		    	getObject("end_user_todays_task_popup_close_link").click();
		    	
		    }
		    APP_LOGS.debug("There is no data available under Today's TODO");
		    
		    
		    
		    getObject("end_user_next7days_tab").click();
		    WebElement week = driver.findElement(By.xpath("//*[@id='today']/div"));
		    String[] classname1 = week.getAttribute("class").split(" ");
		    if (!classname1[0].equalsIgnoreCase("emptyWeek")){
		    	
		    	/*getObject("end_user_todays_approvals_link").click();
		    	Thread.sleep(5000);
		    	getObject("end_user_todays_approval_popup_close_link").click();
		    	
		    	getObject("end_user_todays_tasks_link").click();
		    	Thread.sleep(5000);
		    	getObject("end_user_todays_task_popup_close_link").click();*/
		    	
		    }
		    
		    APP_LOGS.debug("There is no data available under Next 7 Days TODO");
		    
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
