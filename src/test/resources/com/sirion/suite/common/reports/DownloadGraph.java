package test.resources.com.sirion.suite.common.reports;

import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class DownloadGraph extends TestSuiteBase {
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
  public void ReportContentControl(String entityType, String reportName) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }
    
    openBrowser();
    driver.manage().window().maximize();
    
    openBrowser();
    endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
    Thread.sleep(15000);
    APP_LOGS.debug("Executing Test Case downloading graph - " +reportName);
    getObject("analytics_link").click(); 
    getObject("reports_link").click();
    
    new Select(getObject("report_entity_type_dropdown")).selectByVisibleText(entityType);
    Thread.sleep(10000);
    new Select(getObject("report_name_dropdown")).selectByVisibleText(reportName);
    Thread.sleep(15000);

    plus_button("report_download");
    Thread.sleep(5000);
    getObject("report_download_graph_with_data").click();
    Thread.sleep(15000);
    APP_LOGS.debug("Graph downloaded for report - " +reportName);
    
    getObject("analytics_link").click(); 
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
