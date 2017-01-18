package test.resources.com.sirion.suite.clientAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;


public class CustomGroup extends TestSuiteBase{
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
	public void CustomGroupCreation( String customGroupLabel, String customGroupOrder, String customGroupEntityType, String customGroupParentGroup
) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +customGroupLabel +" to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Custom Group Creation test -- "+customGroupLabel);
	      int customGroupOrder_integer=Double.valueOf(customGroupOrder).intValue();
	        String customGroupOrder_string = Integer.toString(customGroupOrder_integer);
		
		
		getObject("admin_tab_link").click();
		getObject("ca_custom_group_link").click();
		getObject("sa_custom_group_plus_button").click();
		getObject("sa_custom_group_label_textbox").sendKeys(customGroupLabel);
		getObject("sa_custom_group_group_order_textbox").sendKeys(customGroupOrder_string);
		new Select (getObject("sa_custom_group_entity_type_dropdown")).selectByVisibleText(customGroupEntityType);
		new Select (getObject("sa_custom_group_parent_group_dropdown")).selectByVisibleText(customGroupParentGroup);
		getObject("sa_custom_group_create_button").click();
		
        if(driver.getPageSource().contains("This label already exists") || driver.getPageSource().contains("This group order already exists")){
          getObject("sa_custom_group_popup_ok_button").click();
          APP_LOGS.debug("Custom Group already exists with either label -- "+customGroupLabel+ " or group order "+customGroupOrder_string+ 
              " for entity -- "+customGroupEntityType);
          getObject("admin_tab_link").click();
          fail=false;
      }
        getObject("sa_custom_group_popup_ok_button").click();
        
        new Select (getObject("sa_custom_group_display_dropdown")).selectByIndex(3);
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ customGroupLabel +"')]/div/a")).click();
        Thread.sleep(5000);
       String customGroupLabelShowPage = getObject("sa_custom_group_label_show").getText();
       Assert.assertEquals(customGroupLabelShowPage, customGroupLabel, "Custom Group label show page is -- " +customGroupLabelShowPage+ " instead of -- " +customGroupLabel);
       String customGroupOrderShowPage = getObject("sa_custom_group_order_show").getText();
       Assert.assertEquals(customGroupOrderShowPage, customGroupOrder_string, "Custom Group order at show page is -- " +customGroupOrderShowPage+ " instead of -- " +customGroupOrder_string);
       String customGroupEntityTypeShowPage = getObject("sa_custom_group_entity_type_show").getText();
       Assert.assertEquals(customGroupEntityTypeShowPage, customGroupEntityType, "Custom Group entity type at show page is -- " +customGroupEntityTypeShowPage+ 
           " instead of -- " +customGroupEntityType);
       String customGroupParentGroupShowPage = getObject("sa_custom_group_parent_type_show").getText();
       Assert.assertEquals(customGroupParentGroupShowPage, customGroupParentGroup, "Custom Group parent group show page is -- " +customGroupParentGroupShowPage+ 
           " instead of -- " +customGroupParentGroup);
       
       fail = false;
       APP_LOGS.debug("Custom group open successfully, following parameters have been validated: Custom group label -- " +customGroupLabel +
           ", Custom group order -- "+customGroupOrder_string+ " Custom group entity type -- " +customGroupEntityType+ " Custom group parent group -- "+customGroupParentGroup);
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
