package test.resources.com.sirion.suite.common.reports;

import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ClientSetupReport extends TestSuiteBase {
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
  public void ReportContentControl(String clientName, String entityName, String reportName) throws InterruptedException {
    // test the runmode of current dataset
    count++;
    if (!runmodes[count].equalsIgnoreCase("Y")) {
      skip = true;
      throw new SkipException("Runmode for test set data set to no " + count);
    }
    
    openBrowser();
    driver.manage().window().maximize();
    
    clientSetupAdminLogin(CONFIG.getProperty("ClientSetupAdminURL"), CONFIG.getProperty("ClientSetupAdminUserName"), CONFIG.getProperty("ClientSetupAdminPassword")); 
    APP_LOGS.debug("Executing Client Setup Admin report configuration for -- " +reportName+ " under client " +clientName);
    
    getObject("admin_tab_link").click();
    getObject("csa_report_link").click();
    
    if(!clientName.equalsIgnoreCase("")){
    new Select(getObject("csa_report_client_dropdown")).selectByVisibleText(clientName);
    }
    
    if(!entityName.equalsIgnoreCase("")){
    new Select(getObject("csa_report_entity_dropdown")).selectByVisibleText(entityName);
    }
    
    if(!reportName.equalsIgnoreCase("")){
    new Select(getObject("csa_report_report_dropdown")).selectByVisibleText(reportName);
    }
    Thread.sleep(10000);
    getObject("csa_report_update_button").click();
    
    if(driver.getPageSource().contains("Configuration successfully updated.")){
      
      getObject("csa_popup_ok_button").click();
      APP_LOGS.debug("Client Setup Admin report configured successfully for -- " +reportName+ " under client " +clientName);
      fail=false;
      getObject("admin_tab_link").click();
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
