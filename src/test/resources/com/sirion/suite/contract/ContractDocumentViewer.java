package test.resources.com.sirion.suite.contract;

import java.awt.AWTException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.Database_Query_DocID;
import test.resources.com.sirion.util.TestUtil;


public class ContractDocumentViewer extends TestSuiteBase{
	
	String runmodes[] = null;
	  static int count = -1;
	  // static boolean pass=false;
	  static boolean fail = true;
	  static boolean skip = false;
	  static boolean isTestPass = true;
	  
	  public static String truncateText(String str, int maxLen, int lenFromEnd) {
		    int len;
		    if (str != null && (len = str.length()) > maxLen) {
		      return str.substring(0, maxLen - lenFromEnd - 3) + "..." +
		          str.substring(len - lenFromEnd, len);
		    }
		    return str;
		  }

	  // Runmode of test case in a suite
	  @BeforeTest
	  public void checkTestSkip() {

	    if (!TestUtil.isTestCaseRunnable(contract_suite_xls, this.getClass().getSimpleName())) {
	      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
	      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
	    }
	    // load the runmodes off the tests
	    runmodes = TestUtil.getDataSetRunmodes(contract_suite_xls, this.getClass().getSimpleName());
	  }

	  
	  @Test(groups = "ContractCreation", dataProvider = "getTestData")
	  public void DocViewer (String parentContractId, String contractFileName) throws InterruptedException, AWTException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case Contract Document Viewer");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));

			
			getObject("quick_search_textbox").sendKeys(parentContractId);
		    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
		    
		    Thread.sleep(10000);
			
			
			Database_Query_DocID docid = new Database_Query_DocID();
		    String contractdocid =null ;
		    try {
				 contractdocid =  docid.Database_Query_DocId(contractFileName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    driver.findElement(By.xpath(".//*[@id='doc_"+contractdocid+"']")).click();
		        driver.findElement(By.xpath(".//*[@id='"+contractdocid+"']/span")).click();
		        
		       // Thread.sleep(10000); 
		    
		        /*driver.findElement(By.xpath(".//*[@id='doc_"+contractdocid+"']")).click();
		        getObject("doc_download_link").click();
		        Thread.sleep(10000); 
		        Actions action = new Actions(driver);
		        action.sendKeys(Keys.ESCAPE);*/

	  }
	  
	  @AfterMethod
	  public void reportDataSetResult() {
	    if (skip)
	      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
	    else if (fail) {
	      isTestPass = false;
	      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
	    } else
	      TestUtil.reportDataSetResult(contract_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

	    skip = false;
	    fail = true;

	  }

	  @AfterTest
	  public void reportTestResult() {
	    if (isTestPass)
	      TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()), "PASS");
	    else
	      TestUtil.reportDataSetResult(contract_suite_xls, "Test Cases", TestUtil.getRowNum(contract_suite_xls, this.getClass().getSimpleName()), "FAIL");

	  }

	  @DataProvider
	  public Object[][] getTestData() {
	    return TestUtil.getData(contract_suite_xls, this.getClass().getSimpleName());
	  }
	  
	  
}
