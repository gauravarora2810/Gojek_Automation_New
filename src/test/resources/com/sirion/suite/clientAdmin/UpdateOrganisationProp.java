package test.resources.com.sirion.suite.clientAdmin;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;


public class UpdateOrganisationProp extends TestSuiteBase{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=false;
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
	public void OrgPropUpdation( String email_username,String email_password,String email_folderToRead,
								 String email_mail_store_protocol,String email_mail_imap_host,String entity_date_format,
								 String entity_timestamp_format,String todo_dueDate_timestamp_format,
								 String volumeTrend_month_format,String excel_date_format,String slaPerformance_date_format,
								 String email_date_format,String systemBccEmail,String bulkDefaultEmail,String systemEmail,
								 String fromEmail,String userActivityEmailRecipient,String email_mail_imap_port,
								 String smtp_username,String smtp_password,String smtp_host,String smtp_port,
								 String mail_smtp_auth,String mail_smtp_connectiontimeout,String mail_smtp_timeout,
								 String mail_smtp_starttls_enable,String mail_smtp_quitwait) 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +email_username +" to no " +count);
		}
		
		APP_LOGS.debug("Executing Client Admin Action Type test -- " +email_username);
		
		openBrowser();
		driver.manage().window().maximize();
		
		// Updating Action Type
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
		getObject("admin_tab_link").click();
		getObject("ca_organisation_properties_link").click();
		getObject("ca_email_username_textarea").clear();
		getObject("ca_email_username_textarea").sendKeys(email_username);
		getObject("ca_email_password_tesxtarea").clear();
		getObject("ca_email_password_tesxtarea").sendKeys(email_password);
		getObject("ca_email_foldertoread_textarea").clear();
		getObject("ca_email_foldertoread_textarea").sendKeys(email_folderToRead);
		getObject("ca_email_mail_store_protocol").clear();
		getObject("ca_email_mail_store_protocol").sendKeys(email_mail_store_protocol);
		getObject("ca_email_mail_imap_host").clear();
		getObject("ca_email_mail_imap_host").sendKeys(email_mail_imap_host);
		getObject("ca_entity_date_format_textarea").clear();
		getObject("ca_entity_date_format_textarea").sendKeys(entity_date_format);
		getObject("ca_entity_timestamp_format_textarea").clear();
		getObject("ca_entity_timestamp_format_textarea").sendKeys(entity_timestamp_format);
		getObject("ca_todo_dueDate_timestamp_format_textarea").clear();
		getObject("ca_todo_dueDate_timestamp_format_textarea").sendKeys(todo_dueDate_timestamp_format);
		getObject("ca_volumeTrend_month_format_textarea").clear();
		getObject("ca_volumeTrend_month_format_textarea").sendKeys(volumeTrend_month_format);
		getObject("ca_excel_date_format_textarea").clear();
		getObject("ca_excel_date_format_textarea").sendKeys(excel_date_format);
		getObject("ca_slaPerformance_date_format_textarea").clear();
		getObject("ca_slaPerformance_date_format_textarea").sendKeys(slaPerformance_date_format);
		getObject("ca_email_date_format_textarea").clear();
		getObject("ca_email_date_format_textarea").sendKeys(email_date_format);
		getObject("ca_systemBccEmail_textarea").clear();
		getObject("ca_systemBccEmail_textarea").sendKeys(systemBccEmail);
		getObject("ca_bulkDefaultEmail_textarea").clear();
		getObject("ca_bulkDefaultEmail_textarea").sendKeys(bulkDefaultEmail);
		getObject("ca_systemEmail_textarea").clear();
		getObject("ca_systemEmail_textarea").sendKeys(systemEmail);
		getObject("ca_fromEmail_textarea").clear();
		getObject("ca_fromEmail_textarea").sendKeys(fromEmail);
		getObject("ca_userActivityEmailRecipient_textarea").clear();
		getObject("ca_userActivityEmailRecipient_textarea").sendKeys(userActivityEmailRecipient);
		getObject("ca_email_mail_imap_port_textarea").clear();
		getObject("ca_email_mail_imap_port_textarea").sendKeys(convertDoubleToIntegerInStringForm(email_mail_imap_port));
		getObject("ca_smtp_username_textarea").clear();
		getObject("ca_smtp_username_textarea").sendKeys(smtp_username);
		getObject("ca_smtp_password_textarea").clear();
		getObject("ca_smtp_password_textarea").sendKeys(smtp_password);
		getObject("ca_smtp_host_textarea").clear();
		getObject("ca_smtp_host_textarea").sendKeys(smtp_host);
		getObject("ca_smtp_port_textarea").clear();
		getObject("ca_smtp_port_textarea").sendKeys(convertDoubleToIntegerInStringForm(smtp_port));
		getObject("ca_mail_smtp_auth_textarea").clear();
		getObject("ca_mail_smtp_auth_textarea").sendKeys(mail_smtp_auth);
		getObject("ca_mail_smtp_connectiontimeout_textarea").clear();
		getObject("ca_mail_smtp_connectiontimeout_textarea").sendKeys(convertDoubleToIntegerInStringForm(mail_smtp_connectiontimeout));
		getObject("ca_mail_smtp_timeout_textarea").clear();
		getObject("ca_mail_smtp_timeout_textarea").sendKeys(convertDoubleToIntegerInStringForm(mail_smtp_timeout));
		getObject("ca_mail_smtp_starttls_enable_textarea").clear();
		getObject("ca_mail_smtp_starttls_enable_textarea").sendKeys(mail_smtp_starttls_enable);
		getObject("ca_mail_smtp_quitwait_textarea").clear();
		getObject("ca_mail_smtp_quitwait_textarea").sendKeys(mail_smtp_quitwait);

		getObject("ca_org_prop_save_butoon").click();
		
		getObject("ca_confirmation_box_button").click();	
		getObject("admin_tab_link").click();
	}
	
	public String convertDoubleToIntegerInStringForm(String data){
		data = Integer.valueOf((Double.valueOf(Double.parseDouble(data))).intValue()).toString();
		return data;
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
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
}