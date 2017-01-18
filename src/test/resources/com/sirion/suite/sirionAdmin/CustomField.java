package test.resources.com.sirion.suite.sirionAdmin;

import java.util.List;
//import net.sf.saxon.sort.GroupStartingIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;


public class CustomField extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(sirion_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(sirion_admin_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (dataProvider="getTestData")
	public void CustomFieldCreation( String customFieldName, String customFieldLabel, String customFieldOrder, String customFieldEntityType,
	    String customFieldLayoutGroup, String customFieldHtmlType, String customFieldActive, String customFieldValidationType, String customFieldMessage,
	    String customFieldParameter
) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +customFieldName +" to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Custom Field Creation test -- "+customFieldName);
	      int customFieldOrder_integer=Double.valueOf(customFieldOrder).intValue();
	      String customFieldOrder_string = Integer.toString(customFieldOrder_integer);
	      int customFieldParameter_integer=Double.valueOf(customFieldParameter).intValue();
	      String customFieldParameter_string = Integer.toString(customFieldParameter_integer);
		
		getObject("admin_tab_link").click();
		getObject("sa_custom_field_link").click();
		getObject("sa_custom_field_plus_button").click();
		getObject("sa_custom_field_name_textbox").sendKeys(customFieldName);
		getObject("sa_custom_field_label_textbox").sendKeys(customFieldLabel);
		getObject("sa_custom_field_field_order_textbox").sendKeys(customFieldOrder_string);
		getObject("sa_custom_field_entity_type_dropdown").sendKeys(customFieldEntityType);
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='tabs-inner-sec']/div[2]/table/tbody/tr[2]/td[3]/label")).click();
		
	      Select select_columns_to_report_list=new Select(driver.findElement(By.id("_listReportId_id")));
	        List<WebElement> list_of_columns_to_report_list=select_columns_to_report_list.getOptions();
	        //int index_of_columns_to_report_list=0;
	        for(WebElement options_of_columns_to_report_list:list_of_columns_to_report_list)
	        {
	            String value_of_columns_to_report_list=options_of_columns_to_report_list.getAttribute("title");
	            select_columns_to_report_list.selectByVisibleText(value_of_columns_to_report_list);
	            //index_of_columns_to_report_list++;
	        }
	        
	         Select select_columns_to_report_list1=new Select(driver.findElement(By.id("_filterListReportIds_id")));
	            List<WebElement> list_of_columns_to_report_list1=select_columns_to_report_list1.getOptions();
	            //int index_of_columns_to_report_list=0;
	            for(WebElement options_of_columns_to_report_list:list_of_columns_to_report_list1)
	            {
	                String value_of_columns_to_report_list=options_of_columns_to_report_list.getAttribute("title");
	                select_columns_to_report_list1.selectByVisibleText(value_of_columns_to_report_list);
	                //index_of_columns_to_report_list++;
	            }    
	        
	    new Select (getObject("sa_custom_field_layout_group_dropdown")).selectByVisibleText(customFieldLayoutGroup);
		new Select (getObject("sa_custom_field_html_type_dropdown")).selectByVisibleText(customFieldHtmlType);
		if(customFieldActive.equalsIgnoreCase("Yes")){
		  getObject("sa_custom_field_active_checkbox").click();
		}
		new Select (getObject("sa_custom_field_validation_type_checkbox")).selectByVisibleText(customFieldValidationType);
		getObject("sa_custom_field_message_checkbox").sendKeys(customFieldMessage);
		getObject("sa_custom_field_parameter_checkbox").sendKeys(customFieldParameter_string);
			
    	getObject("sa_custom_field_create_button").click();
    	Thread.sleep(3000);
		
        if(driver.getPageSource().contains("This name already exists") || driver.getPageSource().contains("This label already exists")
            || driver.getPageSource().contains("This field order already exists")){
          getObject("sa_custom_group_popup_ok_button").click();
          APP_LOGS.debug("Custom Field already exists with either label -- "+customFieldLabel+ " or group order "+customFieldOrder_string+ 
              " for entity -- "+customFieldEntityType);
          getObject("admin_tab_link").click();
          fail=false;
      }
      //  getObject("sa_custom_field_popup_ok_button").click();
        
        new Select (getObject("sa_custom_field_display_dropdown")).selectByIndex(3);
        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ customFieldLabel +"')]/div/a")).click();
        Thread.sleep(5000);
       String customFieldNameShowPage = getObject("sa_custom_field_name_show").getText();
       Assert.assertEquals(customFieldNameShowPage, customFieldName, "Custom Field name show page is -- " +customFieldNameShowPage+ " instead of -- " +customFieldName);
       String customFieldLabelShowPage = getObject("sa_custom_field_label_show").getText();
       Assert.assertEquals(customFieldLabelShowPage, customFieldLabel, "Custom Field label show page is -- " +customFieldLabelShowPage+ " instead of -- " +customFieldLabel);
       String customFieldOrderShowPage = getObject("sa_custom_field_order_show").getText();
       Assert.assertEquals(customFieldOrderShowPage, customFieldOrder_string, "Custom Field order at show page is -- " +customFieldOrderShowPage+ " instead of -- " +customFieldOrder_string);
       String customFieldEntityTypeShowPage = getObject("sa_custom_field_entity_type_show").getText();
       Assert.assertEquals(customFieldEntityTypeShowPage, customFieldEntityType, "Custom Field entity type at show page is -- " +customFieldEntityTypeShowPage+ 
           " instead of -- " +customFieldEntityType);
       String customFieldLayoutGroupShowPage = getObject("sa_custom_field_layout_group_show").getText();
       Assert.assertEquals(customFieldLayoutGroupShowPage, customFieldLayoutGroup, "Custom Field layout group show page is -- " +customFieldLayoutGroupShowPage+ 
           " instead of -- " +customFieldLayoutGroup);
       
       String customFieldHtmlTypeShowPage = getObject("sa_custom_field_html_type_show").getText();
       Assert.assertEquals(customFieldHtmlTypeShowPage, customFieldHtmlType, "Custom Field HTML Type show page is -- " +customFieldHtmlType+ 
           " instead of -- " +customFieldName);
       String customFieldActiveShowPage = getObject("sa_custom_field_active_show").getText();
       Assert.assertEquals(customFieldActiveShowPage, customFieldActive, "Custom Field active show page is -- " +customFieldActiveShowPage+ " instead of -- " +customFieldActive);
       String customFieldValidationTypeShowPage = getObject("sa_custom_field_validation_type_show").getText();
       Assert.assertEquals(customFieldValidationTypeShowPage, customFieldValidationType, "Custom Field validation type show page is -- " +customFieldValidationTypeShowPage+ 
           " instead of -- " +customFieldValidationType);
       String customFieldValidationTypeMessageShowPage = getObject("sa_custom_field_validation_message_show").getText();
       Assert.assertEquals(customFieldValidationTypeMessageShowPage, customFieldMessage, "Custom Field validation message show page is -- " 
       +customFieldValidationTypeMessageShowPage+ " instead of -- " +customFieldMessage);
       String customFieldParameterShowPage = getObject("sa_custom_field_validation_parameter_show").getText();
       Assert.assertEquals(customFieldParameterShowPage, customFieldParameter_string, "Custom Field validation parameter show page is -- " +customFieldParameterShowPage+ 
           " instead of -- " +customFieldParameter_string);
              
       fail = false;
       APP_LOGS.debug("Custom Field open successfully, following parameters have been validated: Custom Field name -- " +customFieldNameShowPage +
           ", Custom Field label -- "+customFieldLabelShowPage+ " Custom Field order -- " +customFieldOrderShowPage+ 
           " Custom Field entity type -- "+customFieldEntityTypeShowPage+" Custom Field layout group -- "+customFieldLayoutGroupShowPage+" Custom Field HTML type -- "
           +customFieldHtmlTypeShowPage+ " Custom Field active -- "+customFieldActiveShowPage+ " Custom field validation type -- "+customFieldValidationTypeShowPage+
           " Custom field validation message -- "+customFieldValidationTypeMessageShowPage+ " Custom field parameter -- "+customFieldParameterShowPage);
       
		getObject("admin_tab_link").click();
		
		
	}	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(sirion_admin_suite_xls, "Test Cases", TestUtil.getRowNum(sirion_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(sirion_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
}
