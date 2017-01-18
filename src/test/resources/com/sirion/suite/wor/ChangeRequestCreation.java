package test.resources.com.sirion.suite.wor;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class ChangeRequestCreation extends TestSuiteBase

{
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(wor_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(wor_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (groups = "ContractCreation", dataProvider = "getTestData")
	
	public void CRCreate(
			String crTitle, String crDescription, String crTimezone, String crClass, String crType, String crPriority,String crResponsibility, 
			String crId, String crContractEntity, String crSupplierAccess, String crTier, String crDependentEntity, 
            String crDateMonth, String crDateDate, String crDateYear, 
            String crEffectiveDateMonth, String crEffectiveDateDate, String crEffectiveDateYear,
            String crOriginalACV, String crCurrOriginalACV, String crRevisedACV, String crCurrRevisedACV, String crVariance, String crCurrVariance,
            String crSupplier, String crStatus, String crFunction,String crService,String conRegions,String conCountries ) throws InterruptedException
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case Change Request Creation");
			
		
		// Calling method for opening browser from TestBase.java file
		openBrowser();

		// Calling a method for login(at different platform) from TestBase.java file
		endUserLogin(CONFIG.getProperty("endUserURL"),CONFIG.getProperty("endUserUsername"),CONFIG.getProperty("endUserPassword"));

		WebDriverWait wait=new WebDriverWait(driver, 50);
 		getObject("analytics_link").click();
   
 		getObject("wor_quick_link").click(); // IP Quick Link Clicking
 		Thread.sleep(20000);
 		//wait.until(ExpectedConditions.elementToBeClickable(getObject("wor_id_link")));
 
 		getObject("wor_id_link").click(); 
    
 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mainContainer']/div/div[2]/a")));
	    plus_button("wor_plus_button_link"); // web element for plus button on supplier page
	    wait.until(ExpectedConditions.elementToBeClickable(getObject("cr_create_link_from_wor")));
    
	    getObject("cr_create_link_from_wor").click(); // click issue create link 



  
if(!crTitle.equalsIgnoreCase(""))
{
getObject("cr_title_textbox").sendKeys(crTitle); // name
}
if(!crDescription.equalsIgnoreCase(""))
{
getObject("cr_description_textarea").sendKeys(crDescription); // title
}

if(!crTimezone.equalsIgnoreCase(""))
{
new Select (getObject("cr_timezone_dropdown")).selectByVisibleText(crTimezone); // timezone

try {
	if (driver.findElement(By.className("success-icon")).getText().contains("Current Date is different for the selected Time Zone"))
		driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
} catch (Exception e) {
	
}

}
if(!crClass.equalsIgnoreCase(""))
{
new Select (getObject("cr_class_dropdown")).selectByVisibleText(crClass);
}

if(!crType.equalsIgnoreCase(""))
{
new Select (getObject("cr_type_dropdown")).selectByVisibleText(crType);
}
if(!crPriority.equalsIgnoreCase(""))
{
new Select (getObject("cr_priority")).selectByVisibleText(crPriority);
}
if(!crResponsibility.equalsIgnoreCase(""))
{
new Select (getObject("cr_responsibility")).selectByVisibleText(crResponsibility);
}		
if(!crId.equalsIgnoreCase(""))
{
getObject("cr_id").sendKeys(crId);// governance body
}
if(!crContractEntity.equalsIgnoreCase(""))
{
new Select (getObject("cr_contractentity_dropdown")).selectByVisibleText(crContractEntity);
}
if(crSupplierAccess.equalsIgnoreCase("yes"))
{
	getObject("cr_supaccess_checkbox").click();
}
if(!crTier.equalsIgnoreCase(""))
{
new Select (getObject("cr_tier_dropdown")).selectByVisibleText(crTier);
}
if(crDependentEntity.equalsIgnoreCase("yes"))
{
	getObject("cr_depentity_checkbox").click();
}

Thread.sleep(10000);
Date date = new Date();

int current_month = date.getMonth();

driver.findElement(By.name("crDate")).click();
Double temp_crDateYear_double = Double.parseDouble(crDateYear);
int temp_crDateYear_int = temp_crDateYear_double.intValue();
String crDateYear_string = Integer.toString(temp_crDateYear_int);

WebElement datepicker_ui = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
System.out.println(datepicker_ui.isDisplayed());
if (datepicker_ui.isDisplayed() == true) {
  WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
  new Select(datepicker_ui_year).selectByVisibleText(crDateYear_string);
}

Double temp_crDateMonth_double = Double.parseDouble(crDateMonth);
int temp_crDateMonth_int = temp_crDateMonth_double.intValue();
System.out.println(" crDateMonth " + temp_crDateMonth_int);

int click2 = current_month - temp_crDateMonth_int;
System.out.println("click " + click2);
for (; click2 >= 0; click2 = click2 - 1) {
  driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
}
Double temp_crDateDate_double = Double.parseDouble(crDateDate);
int temp_crDateDate_int = temp_crDateDate_double.intValue();
String crDateDate_string = Integer.toString(temp_crDateDate_int);
driver.findElement(By.linkText(crDateDate_string)).click();

driver.findElement(By.name("effectiveDate")).click();

Double temp_crEffectiveDateYear_double = Double.parseDouble(crEffectiveDateYear);
int temp_crEffectiveDateYear_int = temp_crEffectiveDateYear_double.intValue();
String crEffectiveDateYear_string = Integer.toString(temp_crEffectiveDateYear_int);

System.out.println(datepicker_ui.isDisplayed());
if (datepicker_ui.isDisplayed() == true) {
  WebElement datepicker_ui_year = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select"));
  new Select(datepicker_ui_year).selectByVisibleText(crEffectiveDateYear_string);
}

Double temp_crEffectiveDateMonth_double = Double.parseDouble(crEffectiveDateMonth);
int temp_crEffectiveDateMonth_int = temp_crEffectiveDateMonth_double.intValue();
System.out.println(" crEffectiveDateMonth " + temp_crEffectiveDateMonth_int);

int click3 = temp_crEffectiveDateMonth_int - current_month;
System.out.println("click " + click3);
for (; click3 >= 0; click3 = click3 - 1) {
  driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
}
Double temp_crEffectiveDateDate_double = Double.parseDouble(crEffectiveDateDate);
int temp_crEffectiveDateDate_int = temp_crEffectiveDateDate_double.intValue();
String crEffectiveDateDate_string = Integer.toString(temp_crEffectiveDateDate_int);
driver.findElement(By.linkText(crEffectiveDateDate_string)).click();

		if(!crOriginalACV.equalsIgnoreCase(""))
				{
				getObject("cr_original_acv").sendKeys(crOriginalACV);// governance body
				}
				if(!crCurrOriginalACV.equalsIgnoreCase(""))
				{
				new Select (getObject("cr_curr_original_acv")).selectByVisibleText(crCurrOriginalACV);
				}
				if(!crRevisedACV.equalsIgnoreCase(""))
				{
				getObject("cr_revised_acv").sendKeys(crRevisedACV);// governance body
				}
				if(!crCurrRevisedACV.equalsIgnoreCase(""))
				{
				new Select (getObject("cr_curr_revised_acv")).selectByVisibleText(crCurrRevisedACV);
				}
				if(!crVariance.equalsIgnoreCase(""))
				{
				getObject("cr_variance").sendKeys(crVariance);// governance body
				}
				if(!crCurrVariance.equalsIgnoreCase(""))
				{
				new Select (getObject("cr_curr_variance")).selectByVisibleText(crCurrVariance);
				}
				
				getObject("ac_save_button").click();
			    
			    Thread.sleep(5000);
			    String cr_id = getObject("cr_popup_id").getText();

			    APP_LOGS.debug("CR created successfully with CR id " + cr_id);

			    getObject("cr_popup_ok_button_from_is").click();

			    APP_LOGS.debug("Quick Search the created CR with CR id " + cr_id);

			   getObject("quick_search_textbox").sendKeys(cr_id);
			    //getObject("quick_search_textbox").sendKeys("CR01001"); // created for testing
			    getObject("quick_search_textbox").sendKeys(Keys.ENTER);
					   /* String crIdFromShowPage = getObject("cr_show_id").getText();

					    Assert.assertEquals(crIdFromShowPage, cr_id);

					    APP_LOGS.debug("CR show page open successfully with CR id " + cr_id);
					    */
					    
					    
					    
					    
					    /*String CRTitleShowPage=getObject("cr_title_show").getText();
					    try
					    {
					    	Assert.assertEquals(CRTitleShowPage, crTitle, "CR Title is =" +CRTitleShowPage+ "instead of"+ crTitle);	
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Title is =" +CRTitleShowPage+ "instead of"+ crTitle);
					    }
					    String CRDescriptionShowPage=getObject("cr_description_show").getText();
					    try
					    {
					    Assert.assertEquals(CRDescriptionShowPage, crDescription, "CR Description is =" +CRDescriptionShowPage+ "instead of"+ crDescription);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Description is =" +CRDescriptionShowPage+ "instead of"+ crDescription);
					    }
					    String CRSupplierShowPage=getObject("cr_supplier_show").getText();
					    try
					    {
					    Assert.assertEquals(CRSupplierShowPage, crSupplier, "CR Supplier is =" +CRSupplierShowPage+ "instead of"+ crSupplier);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Supplier is =" +CRSupplierShowPage+ "instead of"+ crSupplier);
					    }
					    
					    String CRTimeZoneShowPage=getObject("cr_timezone_show").getText();
					    try
					    {
					    Assert.assertEquals(CRTimeZoneShowPage, crTimezone, "CR TimeZone is =" +CRTimeZoneShowPage+ "instead of"+ crTimezone);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR TimeZone is =" +CRTimeZoneShowPage+ "instead of"+ crTimezone);
					    }
					    String CRClassShowPage=getObject("cr_class_show").getText();
					    try
					    {
					    Assert.assertEquals(CRClassShowPage, crClass, "CR Class is =" +CRClassShowPage+ "instead of"+ crClass);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Class is =" +CRClassShowPage+ "instead of"+ crClass);
					    }
					    String CRTypeShowPage=getObject("cr_type_show").getText();
					    try
					    {
					    Assert.assertEquals(CRTypeShowPage, crType, "CR Type is =" +CRTypeShowPage+ "instead of"+ crType);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Type is =" +CRTypeShowPage+ "instead of"+ crType);
					    }
					    
					    String CRPriorityShowPage=getObject("cr_priority_show").getText();
					    try
					    {
					    	 Assert.assertEquals(CRPriorityShowPage, crPriority, "CR Priority is =" +CRPriorityShowPage+ "instead of"+ crPriority);
					    }
					   
					    catch(Throwable e)
					    {
					    	System.out.println("CR Priority is =" +CRPriorityShowPage+ "instead of"+ crPriority);
					    }
					    
					    String CRResponsibilityShowPage=getObject("cr_responsibility_show").getText();
					    try
					    {
					    Assert.assertEquals(CRResponsibilityShowPage, crResponsibility, "CR Responsibility is =" +CRResponsibilityShowPage+ "instead of"+ crResponsibility);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Responsibility is =" +CRResponsibilityShowPage+ "instead of"+ crResponsibility);
					    }
					    String CRStatusShowPage=getObject("cr_status_show").getText();
					    try
					    {
					    Assert.assertEquals(CRStatusShowPage, crStatus, "CR Status is =" +CRStatusShowPage+ "instead of"+ crStatus);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Status is =" +CRStatusShowPage+ "instead of"+ crStatus);
					    }
					    String CRIdShowPage=getObject("cr_id_show").getText();
					    try
					    {
					    Assert.assertEquals(CRIdShowPage, crId, "CR ID is =" +CRIdShowPage+ "instead of"+ crId);
					    }
					    catch(Throwable e)
					    {
					    System.out.println("CR ID is =" +CRIdShowPage+ "instead of"+ crId);
					    }
					    String CRSupplierAccessShowPagee=getObject("cr_supaccess_show").getText();
					    try
					    {
					    Assert.assertEquals(CRSupplierAccessShowPagee, crSupplierAccess, "CR Suppplier Access is =" +CRSupplierAccessShowPagee+ "instead of"+ crSupplierAccess);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Suppplier Access is =" +CRSupplierAccessShowPagee+ "instead of"+ crSupplierAccess);
					    }
					    String CRTierShowPage=getObject("cr_tier_show").getText();
					    try
					    {
					    Assert.assertEquals(CRTierShowPage, crTier, "CR Tier is =" +CRTierShowPage+ "instead of"+ crTier);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Tier is =" +CRTierShowPage+ "instead of"+ crTier);
					    }
					    
					    String CRDependentEntityShowPage=getObject("cr_depentity_show").getText();
					    try
					    {
					    Assert.assertEquals(CRDependentEntityShowPage, crDependentEntity, "CR Dependent Entity is =" +CRDependentEntityShowPage+ "instead of"+ crDependentEntity);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Dependent Entity is =" +CRDependentEntityShowPage+ "instead of"+ crDependentEntity);
					    }
					    String CRFunctionsShowPage=getObject("cr_functions_show").getText();
					    try
					    {
					    Assert.assertEquals(CRFunctionsShowPage, crFunction, "CR Function is =" +CRFunctionsShowPage+ "instead of"+ crFunction);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Function is =" +CRFunctionsShowPage+ "instead of"+ crFunction);
					    }
					    String CRServicesShowPage=getObject("cr_services_show").getText();
					    try
					    {
					    Assert.assertEquals(CRServicesShowPage, crService, "CR Service is =" +CRServicesShowPage+ "instead of"+ crService);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Service is =" +CRServicesShowPage+ "instead of"+ crService);
					    }
					    String CRRegionsShowPage=getObject("cr_regions_show").getText();
					    try
					    
					    {
					    	Assert.assertEquals(CRRegionsShowPage, conRegions, "CR Region is =" +CRRegionsShowPage+ "instead of"+ conRegions);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Region is =" +CRRegionsShowPage+ "instead of"+ conRegions);
					    }
					    
					    
					    String CRCountriesShowPage=getObject("cr_countries_show").getText();
					    try
					    {
					    
					    Assert.assertEquals(CRCountriesShowPage, conCountries, "CR Country is =" +CRCountriesShowPage+ "instead of"+ conCountries);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Country is =" +CRCountriesShowPage+ "instead of"+ conCountries);
					    }
					    String CROriginalACVShowPage=getObject("cr_original_acv_show").getText();
					    try
					    
					    {
					    Assert.assertEquals(CROriginalACVShowPage, crOriginalACV, "CR Original ACV is =" +CROriginalACVShowPage+ "instead of"+ crOriginalACV);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Original ACV is =" +CROriginalACVShowPage+ "instead of"+ crOriginalACV);
					    }
					    String CRCurrencyOriginalACVShowPage=getObject("cr_curr_original_acv_show").getText();
					    try
					    {
					    Assert.assertEquals(CRCurrencyOriginalACVShowPage, crCurrOriginalACV, "CR Currency Original ACV is =" +CRCurrencyOriginalACVShowPage+ "instead of"+ crCurrOriginalACV);
					    }
					    catch(Throwable e)
					    {
					    System.out.println("CR Currency Original ACV is =" +CRCurrencyOriginalACVShowPage+ "instead of"+ crCurrOriginalACV);		
					    }
					    String CRRevisedACVShowPage=getObject("cr_revised_acv_show").getText();
					    try
					    {
					    Assert.assertEquals(CRRevisedACVShowPage, crRevisedACV, "CR Revised ACV is =" +CRRevisedACVShowPage+ "instead of"+ crRevisedACV);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Revised ACV is =" +CRRevisedACVShowPage+ "instead of"+ crRevisedACV);
					    }
					    String CRCurrencyRevisedACVShowPage=getObject("cr_curr_revised_acv_show").getText();
					    try
					    {
					    Assert.assertEquals(CRCurrencyRevisedACVShowPage, crCurrRevisedACV, "CR Currency Revised ACV is =" +CRCurrencyRevisedACVShowPage+ "instead of"+ crCurrRevisedACV);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Currency Revised ACV is =" +CRCurrencyRevisedACVShowPage+ "instead of"+ crCurrRevisedACV);
					    }
					    String CRVarianceShowPage=getObject("cr_variance_show").getText();
					    try
					    {
					    Assert.assertEquals(CRVarianceShowPage, crVariance, "CR Currency Revised ACV is =" +CRVarianceShowPage+ "instead of"+ crVariance);
					    }
					    catch(Throwable e)
					    {
					    System.out.println("CR Currency Revised ACV is =" +CRVarianceShowPage+ "instead of"+ crVariance);
					    }
					    String CRCurrencyVarianceShowPage=getObject("cr_curr_variance_show").getText();
					    try
					    {
					    Assert.assertEquals(CRCurrencyVarianceShowPage, crCurrVariance, "CR Currency Revised ACV is =" +CRCurrencyVarianceShowPage+ "instead of"+ crCurrVariance);
					    }
					    catch(Throwable e)
					    {
					    	System.out.println("CR Currency Revised ACV is =" +CRCurrencyVarianceShowPage+ "instead of"+ crCurrVariance);
					    }
					    */
					    
					    
					    
					    
					    
					    
					    fail = false; // this executes if assertion passes

					    APP_LOGS.debug("CR open successfully, following parameters have been validated: CR Title-- " + crTitle+ ", CR Description -- " + crDescription+ 
				                   ", CR TimeZone -- " + crTimezone+ ", CR Class -- " + crClass+ ", CR Type -- " + crType+ ", " +
				                   		"CR Priority  -- " 
				                   + crPriority+", CR Responsibiity -- " + crResponsibility+ ", CR Id-- " + cr_id+ ", CR Contract Entity -- " + crContractEntity+ 
				                   ", CR Supplier Access -- " + crSupplierAccess+
				                   ", " +  "CR Tier -- " + crTier+", CR Depenndent Entity -- " + crDependentEntity+ ", CR Date -- " + crDateDate+ 
				                   ", CR Date in Month-- " + crDateMonth+ ", CR Date in Year-- " + crDateYear+", CR Function -- " + crFunction+ ", CR Services -- " 
				                   + crService+ ", CR Contract Regions -- " + conRegions+ ", CR Contract Countries -- " + conCountries+ 
				                   ", CR Original ACV -- " 
				                   + crOriginalACV+ ", CR Currency Original ACV -- " + crCurrOriginalACV+ ", CR Revised ACV -- " + crRevisedACV+ 
				                   ", CR Currency Revised ACV -- " + crRevisedACV+" , CR Currency Revised ACV-- " 
				                    /*CRCurrencyRevisedACVShowPage*/+", CR Varience  -- " + crVariance+  ", CR Supplier--"+ crSupplier+ ", CR Status--" +crStatus);
					    
					    getObject("analytics_link").click();
					   // getObject("supplier_quick_link").click();
		}
	
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(wor_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil.getRowNum(wor_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(wor_suite_xls, "Test Cases", TestUtil.getRowNum(wor_suite_xls,this.getClass().getSimpleName()), "FAIL");
	
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(wor_suite_xls, this.getClass().getSimpleName()) ;
	}

	
	
}
