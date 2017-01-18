package test.resources.com.sirion.suite.clientAdmin;

import java.util.List;
//import net.sf.saxon.sort.GroupStartingIterator;



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


public class CustomField extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	static String Option_Name_ar[]=null;
	static String Option_FieldOrder_ar[]=null;
	static String Option_Active_ar[]=null;
	
	
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
	public void CustomFieldCreation( String customFieldName, String customFieldLabel, String customFieldOrder, String customFieldEntityType,
	    String customFieldLayoutGroup, String customFieldHtmlType, String customFieldActive, String customFieldValidationType, String customFieldMessage,
	    String customFieldParameter, String Option_Name, String Option_FieldOrder, String Option_Active) throws InterruptedException 
	
	{
		int j=0;
		int k=0;
		int l=0;
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +customFieldName +" to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Custom Field Creation test -- "+customFieldName);
		int customFieldOrder_integer=Double.valueOf(customFieldOrder).intValue();
	      String customFieldOrder_string = Integer.toString(customFieldOrder_integer);
	     
	    
	    
	   
	    
	   
		
		getObject("admin_tab_link").click();
		getObject("ca_custom_field_link").click();
		getObject("sa_custom_field_plus_button").click();
		 if (!customFieldName.equalsIgnoreCase("")) {
		getObject("sa_custom_field_name_textbox").sendKeys(customFieldName);
		 }
		 if (!customFieldLabel.equalsIgnoreCase("")) {
		getObject("sa_custom_field_label_textbox").sendKeys(customFieldLabel);
		 }
		 if (!customFieldOrder.equalsIgnoreCase("")) {
		getObject("sa_custom_field_field_order_textbox").sendKeys(customFieldOrder_string);
		 }
		 if (!customFieldEntityType.equalsIgnoreCase("")) {
		getObject("sa_custom_field_entity_type_dropdown").sendKeys(customFieldEntityType);
		 }
		Thread.sleep(5000);
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
	            if (!customFieldLayoutGroup.equalsIgnoreCase("")) {   
	    new Select (getObject("sa_custom_field_layout_group_dropdown")).selectByVisibleText(customFieldLayoutGroup);
	            }
	            if (!customFieldHtmlType.equalsIgnoreCase("")) {
		new Select (getObject("sa_custom_field_html_type_dropdown")).selectByVisibleText(customFieldHtmlType);
	            }
	            
		if(customFieldActive.equalsIgnoreCase("Yes")){
		  getObject("sa_custom_field_active_checkbox").click();
		}
		
		if (!customFieldValidationType.equalsIgnoreCase("")) {
		new Select (getObject("sa_custom_field_validation_type_checkbox")).selectByVisibleText(customFieldValidationType);
		}
		
		if (!customFieldMessage.equalsIgnoreCase("")) {
		getObject("sa_custom_field_message_checkbox").sendKeys(customFieldMessage);
		}
		
		if (!customFieldParameter.equalsIgnoreCase("")) {
			 int customFieldParameter_integer=Double.valueOf(customFieldParameter).intValue();
		      String customFieldParameter_string = Integer.toString(customFieldParameter_integer);
		getObject("sa_custom_field_parameter_checkbox").sendKeys(customFieldParameter_string);
		}
		
		if (!Option_Name.equalsIgnoreCase("")) 
		{
		String Option_Name_ar[]=Option_Name.split(";");
		System.out.println(Option_Name_ar[0]);
		System.out.println(Option_Name_ar[1]);
		for(int i=0; i<Option_Name_ar.length;i++)
		{
		
			
		if (!Option_Name.equalsIgnoreCase("")) 
		{
			
			//String Option_Name_ar[]=Option_Name.split(",");
			while(j<Option_Name_ar.length)
			{
				
				driver.findElement(By.xpath("//input[@name='dynamicFieldOptionValues["+j+"].name']")).sendKeys(Option_Name_ar[j]);
				j++;
				break;
			}
			
		}
		
		
		if (!Option_FieldOrder.equalsIgnoreCase("")) 
		{
		
			String Option_FieldOrder_ar[]=Option_FieldOrder.split(";");
			while(k<Option_FieldOrder_ar.length)
			{
				driver.findElement(By.xpath("//input[@name='dynamicFieldOptionValues["+k+"].orderSeq']")).sendKeys(Option_FieldOrder_ar[k]);
				
				k++;
				break;
			}
			
		}
		
		if (!Option_Active.equalsIgnoreCase("")) 
		{
		
			String Option_Active_ar[]=Option_Active.split(";");
			while(l<Option_Active_ar.length)
			{
				if(Option_Active_ar[l].equalsIgnoreCase("Yes"))
				driver.findElement(By.xpath("//input[@name='dynamicFieldOptionValues["+l+"].active']")).click();
				l++;
				driver.findElement(By.xpath("//input[@id='addoption']")).click();
				break;
			}
			
		}
		
		}
	
		driver.findElement(By.xpath("//input[@id='o_"+l+"']")).click();
	}
    	getObject("sa_custom_field_create_button").click();
    	Thread.sleep(3000);
		/*try
		{
        if(driver.getPageSource().contains("This name already exists") || driver.getPageSource().contains("This label already exists")
            || driver.getPageSource().contains("This field order already exists")){
          getObject("sa_custom_group_popup_ok_button").click();
          APP_LOGS.debug("Custom Field already exists with either label -- "+customFieldLabel+ " or group order "+customFieldOrder+ 
              " for entity -- "+customFieldEntityType);
          getObject("admin_tab_link").click();
          
      }
		}*/
		/*catch(Exception e)
		{*/
			System.out.println("Field was not already existing");
			new Select (getObject("sa_custom_field_display_dropdown")).selectByIndex(3);
	        driver.findElement(By.xpath(".//*[@id='l_com_sirionlabs_model_MasterGroup']/tbody/tr[@role='row']/td[contains(.,'"+ customFieldLabel +"')]/div/a")).click();
	        Thread.sleep(5000);
	        getObject("admin_tab_link").click();
	        
		/*}*/
		
		
      //  getObject("sa_custom_field_popup_ok_button").click();  
       /*String customFieldNameShowPage = getObject("sa_custom_field_name_show").getText();
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
           " instead of -- " +customFieldParameter_string);*/
              
       fail = false;
       /*APP_LOGS.debug("Custom Field open successfully, following parameters have been validated: Custom Field name -- " +customFieldNameShowPage +
           ", Custom Field label -- "+customFieldLabelShowPage+ " Custom Field order -- " +customFieldOrderShowPage+ 
           " Custom Field entity type -- "+customFieldEntityTypeShowPage+" Custom Field layout group -- "+customFieldLayoutGroupShowPage+" Custom Field HTML type -- "
           +customFieldHtmlTypeShowPage+ " Custom Field active -- "+customFieldActiveShowPage+ " Custom field validation type -- "+customFieldValidationTypeShowPage+
           " Custom field validation message -- "+customFieldValidationTypeMessageShowPage+ " Custom field parameter -- "+customFieldParameterShowPage);*/
       
		
		
		
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
