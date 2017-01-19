package test.resources.com.sirion.suite.clientAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class QuickLinkContentControl extends TestSuiteBase{
                String runmodes[]=null;
                static int count=-1;
                //static boolean pass=false;
                static boolean fail=true;
                static boolean skip=false;
                static boolean isTestPass=true;
                
                // Runmode of test case in a suite
                @BeforeTest
                public void checkTestSkip(){
                                
                                if(!TestUtil.isTestCaseRunnable(client_admin_suite_xls,this.getClass().getSimpleName())){
                                                APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
                                                throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
                                }
                                // load the runmodes off the tests
                                runmodes=TestUtil.getDataSetRunmodes(client_admin_suite_xls, this.getClass().getSimpleName());
                }
                
                @Test (dataProvider="getTestData")
                public void ContentControlSetup(String entityName, 
                		String status, String supplierSuffix, String parameter1, String parameter2,
                		String parameter3) throws InterruptedException 
                
                {
                                // test the runmode of current dataset
                                count++;
                                if(!runmodes[count].equalsIgnoreCase("Y")){
                                                skip=true;
                                                throw new SkipException("Runmode for test set data set to no " +count);
                                }
                                
                                
                                openBrowser();
                        		driver.manage().window().maximize();
                        		
                        		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
                        		
                        	    getObject("admin_tab_link").click();
                        	    getObject("ca_quick_link_content_control_link").click(); 
                        	    
                        	    getObject("ca_quick_link_content_control_textbox").sendKeys(entityName);
                        	    getObject("ca_quick_link_content_control_update_link").click();
                        	    
                        	    // Select multiple quick link status
                        	    String[] statusSplit=  status.split(";");
                        	    for (int i=0; i < statusSplit.length ;i++){
                        	      new Select(getObject("ca_report_content_control_status_multi")).selectByVisibleText(statusSplit[i]);
                        	      Thread.sleep(5000);
                        	      System.out.println("select status "+statusSplit[i]);
                        	  }
                        	    
                        	    if (supplierSuffix.equalsIgnoreCase("Yes")) {
                        			getObject("ca_quick_link_content_control_supplier_suffix_checkbox").click();
                        	    }
                                
                        	    getObject("ca_quick_link_content_control_save_button").click();
                        	    
                        	    if(driver.getPageSource().contains("List Parameters updated successfully")){
                        	      APP_LOGS.debug("List content control set successfully for list -- "+entityName);
                        	      getObject("ca_quick_link_content_control_popup_ok_button").click();
                        	      fail=false;
                        	  }
                        	    getObject("admin_tab_link").click();

                                                	
                                                	
                               
}
                @AfterMethod
                public void reportDataSetResult(){
                                if(skip)
                                                TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
                                else if(fail){
                                                isTestPass=false;
                                                TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
                                }
                                else
                                                TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
                                
                                skip=false;
                                fail=true;

                }
                
                @AfterTest
                public void reportTestResult(){
                                if(isTestPass)
                                                TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
                                else
                                                TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
                }
                
                @DataProvider
                public Object[][] getTestData(){
                                return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName()) ;
                }
}
