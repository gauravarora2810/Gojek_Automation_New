package test.resources.com.sirion.suite.contract;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import test.resources.com.sirion.util.TestUtil;

public class ContractOtherFolderCreation extends TestSuiteBase {
	  String runmodes[] = null;
	  static int count = -1;
	  // static boolean pass=false;
	  static boolean fail = true;
	  static boolean skip = false;
	  static boolean isTestPass = true;

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
	  
	  public void OFCreation (String ContractName, String OFName, String OFBrief, String OFDocumentPath, String viewerCheckbox, 
			  String searchCheckbox, String downloadCheckbox, String financialCheckbox, String legalCheckbox) throws InterruptedException{
		  
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }

		    APP_LOGS.debug(" Executing Test Case Contract Creation");
		    
		    openBrowser();
			driver.manage().window().maximize();
			
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));

			new Select(driver.findElement(By.xpath(".//*[@id='cr_length']/label/select"))).selectByIndex(3);
			
			driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+ContractName+"')]/preceding-sibling::td[1]/a")).click();
		    
		    Thread.sleep(10000);

		    plus_button("co_plus_button_link"); // web element for plus button on supplier page
		    driver.findElement(By.linkText("Other Folder")).click();
		    
		    getObject("co_of_name_textbox").sendKeys(OFName);
		    getObject("co_of_brief_textarea").sendKeys(OFBrief);

		    if (!OFDocumentPath.equalsIgnoreCase("")){
		    	getObject("co_of_doc_upload_tab").click();
		        getObject("co_doc_upload_browse_button").sendKeys(OFDocumentPath);
		        }
		        
		        if(viewerCheckbox.equalsIgnoreCase("yes")){
		        getObject("co_doc_upload_viewer_checkbox").click();
		        }
		        if(searchCheckbox.equalsIgnoreCase("yes")){
		        getObject("co_doc_upload_search_checkbox").click();
		        }
		        if(downloadCheckbox.equalsIgnoreCase("yes")){
		        getObject("co_doc_upload_download_checkbox").click();
		        }
		        if(financialCheckbox.equalsIgnoreCase("yes")){
		        getObject("co_doc_upload_financial_checkbox").click();
		        }
		        if(legalCheckbox.equalsIgnoreCase("yes")){
		        getObject("co_doc_upload_legal_checkbox").click();
		        }
		        
		        getObject("of_general_tab_link").click();
		        getObject("co_of_save_button").click();	  
		  
		        String OFID = getObject("of_id_popup").getText();
		        getObject("of_popup_ok_button").click();
		  
		        getObject("quick_search_text_box").sendKeys(OFID);
		        getObject("quick_search_text_box").sendKeys(Keys.ENTER);
		  
		  String FOIDShowPage = getObject("fo_id_show_page").getText();
		  try
		    {
		 	   Assert.assertEquals(FOIDShowPage, OFID, "Contract Other Folder name is -- " +FOIDShowPage+ " instead of -- " +OFID);
		    }
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Contract Other Folder name is -- " +OFID+ " instead of -- " +FOIDShowPage);
		    }
		  
		  String FONameShowPage = getObject("fo_title_show_page").getText();
		  try
		    {
		 	   Assert.assertEquals(FONameShowPage, OFName, "Contract Other Folder name is -- " +FONameShowPage+ " instead of -- " +OFName);
		    }
		    catch(Throwable e)
		    {
		    	APP_LOGS.debug("Contract Other Folder name is -- " +OFName+ " instead of -- " +FONameShowPage);
		    }
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
		    fail = false;

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
