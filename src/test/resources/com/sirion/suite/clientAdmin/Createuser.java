package test.resources.com.sirion.suite.clientAdmin;

import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class Createuser extends TestSuiteBase

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
                public void UserCreation( String euFirstName, String euLastName, String euLoginID, String euEmailID, String euClientSupplierAlias,String euContractNumber, String euDesignation,
                                          String euTimezone, String euUniqueLoginID, String euDefaultTier, String euLegalDocument, String euFinancialDocument,
                                          String euBusinessCase, String euPasswordNeverExpires, String euType, String eusendEmail, String euSelectAll, String euSystemReadAccess, String euSystemWriteAccess) 
                                              throws InterruptedException, ClassNotFoundException, SQLException 
                
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
                                APP_LOGS.debug("Executing Client Admin User Creation test -" +euFirstName + " " +euLastName );
//                                Thread.sleep(1000);
                                System.out.println("arora");
                                getObject("admin_tab_link").click();
                                Thread.sleep(1000);
                                getObject("sua_user_admin_tab_link").click();
                                
                                getObject("click_user").click();
                                
                                WebElement plus_user = getObject("click_user_plus_icon");
                                Actions ac_plus_user=new Actions(driver);
                                ac_plus_user.moveToElement(plus_user).build().perform();
                                plus_user.click();
                                //Thread.sleep(10000);
                                if (!euFirstName.equalsIgnoreCase("")) {             
                                getObject("eu_first_name_textbox").sendKeys(euFirstName);
                                }
                                
                                if (!euLastName.equalsIgnoreCase("")) {  
                                getObject("eu_last_name_textbox").sendKeys(euLastName);
                                
                                if (!euLoginID.equalsIgnoreCase("")) { 
                                getObject("eu_login_id_textbox").sendKeys(euLoginID);
                                login_Id = euLoginID ;
                                }
                                
                                if (!euEmailID.equalsIgnoreCase("")) {
                                getObject("eu_email_id_textbox").sendKeys(euEmailID);
                                }
                                
                    if (!euContractNumber.equalsIgnoreCase("")) {
                        getObject("eu_contact_number_textbox").sendKeys(euContractNumber);
                        }
                      
                    if (!euDesignation.equalsIgnoreCase("")) {
                        getObject("eu_designation_textbox").sendKeys(euDesignation);
                        }
                                
                    if (!euTimezone.equalsIgnoreCase("")) {
                    	///Thread.sleep(3000);
                      new Select(getObject("eu_timezone_dropdown")).selectByVisibleText(euTimezone);
                      
                    }
                    
                      if (!euUniqueLoginID.equalsIgnoreCase("")) {
                                getObject("eu_unique_login_id_textbox").sendKeys(euUniqueLoginID);
                      }                        
                                
                    if (!euDefaultTier.equalsIgnoreCase("")) {
                    	//Thread.sleep(3000);
                          new Select(getObject("eu_default_tier_dropdown")).selectByVisibleText(euDefaultTier);
                        } 
                                
        if (!euLegalDocument.equalsIgnoreCase("")) {
       getObject("eu_legal_access_checkbox").click();
         } 
        
        if (!euFinancialDocument.equalsIgnoreCase("")) {
      getObject("eu_financial_access_checkbox").click();
        } 
        
        if (!euBusinessCase.equalsIgnoreCase("")) {
      getObject("eu_business_access_checkbox").click();
        } 
        
        if (!euPasswordNeverExpires.equalsIgnoreCase("")) {
      getObject("eu_password_never_expire_checkbox").click();
        } 
        
        if (!euType.equalsIgnoreCase("")) {
        	//Thread.sleep(3000);
          new Select(getObject("eu_type_dropdown")).selectByVisibleText(euType);
        } 
        
        getObject("sua_send_email").click();
                                
        if (!euSelectAll.equalsIgnoreCase("")) {
      getObject("eu_select_all_checkbox").click();
        }
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("eu_system_read_access_checkbox"));
        if (!euSystemReadAccess.equalsIgnoreCase("")) {
            getObject("eu_system_read_access_checkbox").click();
              }
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getObject("eu_system_write_access_checkbox"));
        if (!euSystemWriteAccess.equalsIgnoreCase("")) {
      getObject("eu_system_write_access_checkbox").click();
        } 
                                
                                getObject("eu_save_button").click();
                              
                               //Thread.sleep(1000);
                                getObject("eu_activation_button").click();
                                //Thread.sleep(1000);
                                getObject("eu_popupok_button").click();
                                getObject("sua_user_admin_tab_link").click();
                                                                
                                getObject("click_user").click();
                                
                                
                                getObject("sua_search_textbox").click();
                                
                                
                                getObject("sua_search_textbox").sendKeys(login_Id);
                                
                                if(driver.getPageSource().contains(login_Id))
                                  {
                                    System.out.println("This username is available"+"   "+login_Id);
                                  }
                                else
                                  {
                                    System.out.println(login_Id+"   "+"User is not available");
                                  }
                
                Database_Password_Change_Query database = new Database_Password_Change_Query();
                //database.enduserpasswordchange("Select id from app_user where login_id='"+login_Id+"'");
                database.enduserpasswordchange("Select id from app_user where login_id='"+login_Id+"' and client_id in (select id from client  where alias ilike'"+euClientSupplierAlias+"')");
                getObject("admin_tab_link").click();
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
