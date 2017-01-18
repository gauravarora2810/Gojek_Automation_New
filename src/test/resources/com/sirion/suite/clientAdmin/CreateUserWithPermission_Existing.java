package test.resources.com.sirion.suite.clientAdmin;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class CreateUserWithPermission_Existing extends TestSuiteBase

{
                
                public WebElement element;
                boolean ClientUser;
                boolean ClientUserVisible;
                public static String login_Id;
                public static String ID;
                public static  String ID_new;
                public static String fileName = null;
                public static String format = "jpg";
                public static int i;
                String runmodes[]=null;
                static int count=-1;
                static boolean fail=false;
                static boolean skip=false;
                static boolean isTestPass=true;
                
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
                public void UserCreation( String euLoginID, String select_all, String global_permission,String search, String vendor_hierarchy,
                                          String contract, String ob, String cob, String issue, String sl,String csl, String action, 
                                          String invoice, String cr, String supplier, String ip, String wor, String gb, String clause, String cts, 
                                          String gbm,String ct,String cdr,String po)  throws InterruptedException, ClassNotFoundException, SQLException 
                
                {
                                // test the runmode of current dataset
                                count++;
                                if(!runmodes[count].equalsIgnoreCase("Y")){
                                                skip=true;
                                                throw new SkipException("Runmode for test set data set to No " +count);
                                }
                                
                                openBrowser();
                               // driver.manage().window().maximize();
                                System.out.println("gaurav");
                                clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword"));
                              //  APP_LOGS.debug("Executing Client Admin User Creation test -" +euFirstName + " " +euLastName );
//                                Thread.sleep(1000);
                                System.out.println("arora");
                                getObject("admin_tab_link").click();
                                Thread.sleep(1000);
                                getObject("sua_user_admin_tab_link").click();
                                
                                getObject("click_user").click();
                                
                                getObject("sua_search_textbox").clear();
                                
                                
                                getObject("sua_search_textbox").sendKeys(euLoginID);
                                
                               
                                driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_TblUser']/tbody/tr/td[2][contains(.,'"+euLoginID+"')]/preceding-sibling::td[1]/a")).click();
       
                                
                                Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@value='Edit']")));
        driver.findElement(By.xpath(".//*[@value='Edit']")).click();
        
        
        if(driver.findElement(By.xpath(".//*[@class='globaltoggleall']")).isSelected())
        {
        			if(select_all.equalsIgnoreCase("N"))
        	driver.findElement(By.xpath(".//*[@class='globaltoggleall']")).click();
        			
        }
        else
        {
        	if(select_all.equalsIgnoreCase("Y"))
            	driver.findElement(By.xpath(".//*[@class='globaltoggleall']")).click();
        	else
        	{
        		Thread.sleep(5000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//label[contains(.,'CONTRACT:')]/following-sibling::input[@class='toggleall']")));
        		if(driver.findElement(By.xpath(".//label[contains(.,'CONTRACT:')]/following-sibling::input[@class='toggleall']")).isSelected())
        		{
        			if (contract.equalsIgnoreCase("N"))
        			{
        				driver.findElement(By.xpath(".//label[contains(.,'CONTRACT:')]/following-sibling::input[@class='toggleall']")).click();
        			}
        		}
        		else
        		{
        			if (contract.equalsIgnoreCase("Y"))
        			{
        				driver.findElement(By.xpath(".//label[contains(.,'CONTRACT:')]/following-sibling::input[@class='toggleall']")).click();
        			}
        		}
        		
        	}
        
        }                        
                             //   getObject("eu_save_button").click();
        Thread.sleep(10000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='proceed']")));    
        driver.findElement(By.xpath(".//*[@id='proceed']")).click();                  
            driver.close();                 
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
                fail=false;
                

}

@AfterTest
public void reportTestResult(){
                if(isTestPass)
                                TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls,this.getClass().getSimpleName()), "PASS");
                else
                                TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(supplier_suite_xls,this.getClass().getSimpleName()), "FAIL");

}

@DataProvider
public Object[][] getTestData(){
                return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName()) ;
}
    
}
