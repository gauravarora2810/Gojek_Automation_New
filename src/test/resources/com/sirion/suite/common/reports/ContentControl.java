package test.resources.com.sirion.suite.common.reports;

import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ContentControl extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(common_report_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(common_report_suite_xls, this.getClass().getSimpleName());
  }

  @Test(dataProvider = "getTestData")
  public void ReportContentControl(String reportName, String status, String parameter1, String parameter2, 
		  String parameter3) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }
    
    openBrowser();
    driver.manage().window().maximize();
    
    clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
    APP_LOGS.debug("Executing Client Admin Report content control test -- " +reportName);
    
    getObject("admin_tab_link").click();
    getObject("ca_report_content_control").click();
    
    getObject("ca_report_content_control_textbox").sendKeys(reportName);
    getObject("ca_report_content_control_update_link").click();
    
    String[] statusSplit=  status.split(";");
    for (int i=0; i < statusSplit.length ;i++){
      new Select(getObject("ca_report_content_control_status_multi")).selectByVisibleText(statusSplit[i]);
      Thread.sleep(5000);
      System.out.println("select status "+statusSplit[i]);
  }
    if(!parameter1.equalsIgnoreCase("")){
    String[] statusSplitParameter1=  parameter1.split(";");
    for (int i=0; i < statusSplitParameter1.length ;i++){
      new Select(getObject("ca_report_content_control_parameter1_multi")).selectByVisibleText(statusSplitParameter1[i]);
      Thread.sleep(5000);
      System.out.println("select status "+statusSplitParameter1[i]);
  }}
    
    if(!parameter2.equalsIgnoreCase("")){
    String[] statusSplitParameter2=  parameter2.split(";");
    for (int i=0; i < statusSplitParameter2.length ;i++){
      new Select(getObject("ca_report_content_control_parameter2_multi")).selectByVisibleText(statusSplitParameter2[i]);
      Thread.sleep(5000);
      System.out.println("select status "+statusSplitParameter2[i]);
  }}
    
    if(!parameter3.equalsIgnoreCase("")){
    String[] statusSplitParameter3=  parameter3.split(";");
    for (int i=0; i < statusSplitParameter3.length ;i++){
      new Select(getObject("ca_report_content_control_parameter3_multi")).selectByVisibleText(statusSplitParameter3[i]);
      Thread.sleep(5000);
      System.out.println("select status "+statusSplitParameter3[i]);
  }}
    
    getObject("ca_report_content_control_save_button").click();
    
    if(driver.getPageSource().contains("Report Parameters updated successfully")){
      APP_LOGS.debug("Reprort content control set successfully for report -- "+reportName);
      getObject("ca_report_content_control_popup_ok_button").click();
      fail=false;
  }
    getObject("admin_tab_link").click();
  }

  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(common_report_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(common_report_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(common_report_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = true;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(common_report_suite_xls, "Test Cases", TestUtil.getRowNum(common_report_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(common_report_suite_xls, "Test Cases", TestUtil.getRowNum(common_report_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(common_report_suite_xls, this.getClass().getSimpleName());
  }

}
