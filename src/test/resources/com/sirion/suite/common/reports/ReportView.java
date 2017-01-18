package test.resources.com.sirion.suite.common.reports;

import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ReportView extends TestSuiteBase {
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
  public void ReportContentControl(String entityName, String reportName, String reportNameUpdate, String tooltip, String xAxis, String yAxis) throws InterruptedException {
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
    getObject("ca_report_view_link").click();
    
    new Select(getObject("ca_report_view_select_entity_dropdown")).selectByVisibleText(entityName);
    new Select(getObject("ca_report_view_select_report_dropdown")).selectByVisibleText(reportName);
    Thread.sleep(10000);
    getObject("ca_report_view_select_report_name_textbox").clear();
    if(!reportNameUpdate.equalsIgnoreCase("")){
    getObject("ca_report_view_select_report_name_textbox").sendKeys(reportNameUpdate);
    }
    
    getObject("ca_report_view_select_tooltip_textarea").clear();
    if(!tooltip.equalsIgnoreCase("")){
    getObject("ca_report_view_select_tooltip_textarea").sendKeys(tooltip);
    }
    
    if(!xAxis.equalsIgnoreCase("")){
    getObject("ca_report_view_x_axis_textbox").sendKeys(xAxis);
    }
    
    if(!yAxis.equalsIgnoreCase("")){
    getObject("ca_report_view_y_axis_textbox").sendKeys(yAxis);
    }

    
    getObject("ca_report_view_proceed_button").click();
    
    
    if(driver.getPageSource().contains("Configuration successfully updated.")){
      APP_LOGS.debug("Reprort view set successfully for report -- "+reportName);
      getObject("ca_report_view_popup_button").click();
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
