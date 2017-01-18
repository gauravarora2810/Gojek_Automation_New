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
                public void QuicklinkcontentControl() throws InterruptedException 
                
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
                        		Thread.sleep(5000);
                                driver.findElement(By.xpath("//a[contains(.,'Quick Link Content Controls')]")).click();    
                                
                                for (int i=1;i<=10;i++){
                                                try
                                                {
                                                if (driver.findElement(By.xpath("//*[@id='l_com_sirionlabs_model_MasterGroup_paginate']/span/a["+i+"]")).isDisplayed()){
                                                driver.findElement(By.xpath("//*[@id='l_com_sirionlabs_model_MasterGroup_paginate']/span/a["+i+"]")).click();
                                                
                                                WebElement totalitems = driver.findElement(By.cssSelector("#l_com_sirionlabs_model_MasterGroup>tbody"));
                                                List <WebElement> totaloptions = totalitems.findElements(By.tagName("tr"));
                                                
                                                for (int j=1; j<totaloptions.size();j++){
                                                                
                                                                driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr["+j+"]/td[2]/div/a")).click();
                                                                Select contentcontrol = new Select (driver.findElement(By.xpath("//*[@id='_params[0].statusList_id']")));
                                                              // WebElement contentcontrol2_element=driver.findElement(By.xpath("//*[@id='_params[1].statusList_id']"));
                                                                //Select contentcontrol2 = new Select (driver.findElement(By.xpath("//*[@id='_params[1].statusList_id']")));
                                                                List <WebElement> elementcount = contentcontrol.getOptions();
                                                                int contentcontroloptions = elementcount.size();
                                                                
                                                                for(int k = 0; k<contentcontroloptions; k++){
                                                                                
                                                                             
                                                                	contentcontrol.selectByIndex(k);
                                                                }
                                                                try
                                                                {
                                                                	WebElement contentcontrol2_element=driver.findElement(By.xpath("//*[@id='_params[1].statusList_id']"));
                                                                    Select contentcontrol2 = new Select (driver.findElement(By.xpath("//*[@id='_params[1].statusList_id']")));
                                                                    List <WebElement> elementcount2 = contentcontrol2.getOptions();
                                                                    int contentcontroloptions2 = elementcount2.size();
                                                                	
                                                                if(contentcontrol2_element.isDisplayed())
                                                                		{
                                                                		for(int l = 0; l<contentcontroloptions2; l++)
                                                                		{
                                                                    	contentcontrol2.selectByIndex(l);
                                                                		}
                                                                		}
                                                                }
                                                                catch(Exception e)
                                                                {
                                                                	
                                                                }
                                                                driver.findElement(By.xpath("//input[@value='Save']")).click();
                                                                driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
                                                                driver.findElement(By.xpath("//*[@id='h-adminstration']/a")).click();
                                                                driver.findElement(By.xpath("//a[contains(.,'Quick Link Content Controls')]")).click();
                                                                driver.findElement(By.xpath("//*[@id='l_com_sirionlabs_model_MasterGroup_paginate']/span/a["+i+"]")).click();
                                                }
                                }
                                                else{
                                                    
                                                    driver.findElement(By.xpath("//*[@id='h-adminstration']/a")).click();
                                    }
                                                }
                                                catch(Exception e)
                                                {
                                                	
                                                } 	
                                                	
                                                	
                                }
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
