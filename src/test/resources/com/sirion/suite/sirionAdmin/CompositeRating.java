package test.resources.com.sirion.suite.sirionAdmin;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
//import net.sf.saxon.sort.GroupStartingIterator;


public class CompositeRating extends TestSuiteBase{
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
	public void CompositeRating( String govScoreBelowExp, String govScoreMetMinExp, String supScoreBelowExp, String supScoreMetMinExp, String conManagementBelowExp,
	    String conManagementMetMinExp, String supPerformanceBelowExp, String supPerformanceMetMinExp, String finManagementBelowExp, String finManagementMetMinExp,
	    String relManagementBelowExp, String relManagementMetMinExp, String riskManagementBelowExp, String riskManagementMetMinExp) throws InterruptedException 
	
	{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no " +count);
		}
		
		openBrowser();
		driver.manage().window().maximize();
		//driver.get(CONFIG.getProperty("sirionAdminURL"));

		sirionAdminLogin(CONFIG.getProperty("sirionAdminURL"), CONFIG.getProperty("sirionAdminUsername"), CONFIG.getProperty("sirionAdminPassword")); 
		APP_LOGS.debug("Executing Sirion Admin Composite Rating Creation test");
		getObject("admin_tab_link").click();
		getObject("sa_composite_rating_link").click();
		
	    int govScoreBelowExp_integer=Double.valueOf(govScoreBelowExp).intValue();
	    String govScoreBelowExp_string = Integer.toString(govScoreBelowExp_integer);
	    
	       int govScoreMetMinExp_integer=Double.valueOf(govScoreMetMinExp).intValue();
	        String govScoreMetMinExp_string = Integer.toString(govScoreMetMinExp_integer);
	        
	        int supScoreBelowExp_integer=Double.valueOf(supScoreBelowExp).intValue();
	        String supScoreBelowExp_string = Integer.toString(supScoreBelowExp_integer);
	        
	        int supScoreMetMinExp_integer=Double.valueOf(supScoreMetMinExp).intValue();
	        String supScoreMetMinExp_string = Integer.toString(supScoreMetMinExp_integer);
	        
	        int conManagementBelowExp_integer=Double.valueOf(conManagementBelowExp).intValue();
	        String conManagementBelowExp_string = Integer.toString(conManagementBelowExp_integer);

	        int conManagementMetMinExp_integer=Double.valueOf(conManagementMetMinExp).intValue();
	        String conManagementMetMinExp_string = Integer.toString(conManagementMetMinExp_integer);
	        
	        int supPerformanceBelowExp_integer=Double.valueOf(supPerformanceBelowExp).intValue();
	        String supPerformanceBelowExp_string = Integer.toString(supPerformanceBelowExp_integer);
	        
	        int supPerformanceMetMinExp_integer=Double.valueOf(supPerformanceMetMinExp).intValue();
	        String supPerformanceMetMinExp_string = Integer.toString(supPerformanceMetMinExp_integer);
	        
	        int finManagementBelowExp_integer=Double.valueOf(finManagementBelowExp).intValue();
	        String finManagementBelowExp_string = Integer.toString(finManagementBelowExp_integer);
	        
	        int finManagementMetMinExp_integer=Double.valueOf(finManagementMetMinExp).intValue();
	        String finManagementMetMinExp_string = Integer.toString(finManagementMetMinExp_integer);
	        
	        int relManagementBelowExp_integer=Double.valueOf(relManagementBelowExp).intValue();
	        String relManagementBelowExp_string = Integer.toString(relManagementBelowExp_integer);
	        
	        int relManagementMetMinExp_integer=Double.valueOf(relManagementMetMinExp).intValue();
	        String relManagementMetMinExp_string = Integer.toString(relManagementMetMinExp_integer);
	        
	        int riskManagementBelowExp_integer=Double.valueOf(riskManagementBelowExp).intValue();
	        String riskManagementBelowExp_string = Integer.toString(riskManagementBelowExp_integer);
	        
	        int riskManagementMetMinExp_integer=Double.valueOf(riskManagementMetMinExp).intValue();
	        String riskManagementMetMinExp_string = Integer.toString(riskManagementMetMinExp_integer);
		
		
		getObject("sa_comp_rating_governance_score_below_expectation_textbox").clear();
		getObject("sa_comp_rating_governance_score_below_expectation_textbox").sendKeys(govScoreBelowExp_string);
	    getObject("sa_comp_rating_governance_score_met_minimum_expectation_textbox").clear();
	    getObject("sa_comp_rating_governance_score_met_minimum_expectation_textbox").sendKeys(govScoreMetMinExp_string);
	    
	    
	    getObject("sa_comp_rating_supplier_score_link").click();
	       getObject("sa_comp_rating_supplier_score_below_expectation_textbox").clear();
	        getObject("sa_comp_rating_supplier_score_below_expectation_textbox").sendKeys(supScoreBelowExp_string);
	        getObject("sa_comp_rating_supplier_score_met_minimum_expectation_textbox").clear();
	        getObject("sa_comp_rating_supplier_score_met_minimum_expectation_textbox").sendKeys(supScoreMetMinExp_string);
	    
	        getObject("sa_comp_rating_contract_management_link").click();
	        getObject("sa_comp_rating_contract_management_below_expectation_textbox").clear();
	        getObject("sa_comp_rating_contract_management_below_expectation_textbox").sendKeys(conManagementBelowExp_string);
	        getObject("sa_comp_rating_contract_management_met_minimum_expectation_textbox").clear();
	        getObject("sa_comp_rating_contract_management_met_minimum_expectation_textbox").sendKeys(conManagementMetMinExp_string);
	        
	        getObject("sa_comp_rating_supplier_performance_link").click();
	        getObject("sa_comp_rating_supplier_performance_below_expectation_textbox").clear();
	        getObject("sa_comp_rating_supplier_performance_below_expectation_textbox").sendKeys(supPerformanceBelowExp_string);
	        getObject("sa_comp_rating_supplier_performance_met_minimum_expectation_textbox").clear();
	        getObject("sa_comp_rating_supplier_performance_met_minimum_expectation_textbox").sendKeys(supPerformanceMetMinExp_string);
	        
	        getObject("sa_comp_rating_financial_management_link").click();
	           getObject("sa_comp_rating_financial_management_below_expectation_textbox").clear();
	            getObject("sa_comp_rating_financial_management_below_expectation_textbox").sendKeys(finManagementBelowExp_string);
	            getObject("sa_comp_rating_financial_management_met_minimum_expectation_textbox").clear();
	            getObject("sa_comp_rating_financial_management_met_minimum_expectation_textbox").sendKeys(finManagementMetMinExp_string);
	        
	            getObject("sa_comp_rating_relationship_management_link").click();
	        getObject("sa_comp_rating_relationship_management_below_expectation_textbox").clear();
	        getObject("sa_comp_rating_relationship_management_below_expectation_textbox").sendKeys(relManagementBelowExp_string);
	        getObject("sa_comp_rating_relationship_management_met_minimum_expectation_textbox").clear();
	        getObject("sa_comp_rating_relationship_management_met_minimum_expectation_textbox").sendKeys(relManagementMetMinExp_string);
	        
	        getObject("sa_comp_rating_risk_management_link").click();
	        getObject("sa_comp_rating_risk_management_below_expectation_textbox").clear();
	        getObject("sa_comp_rating_risk_management_below_expectation_textbox").sendKeys(riskManagementBelowExp_string);
	        getObject("sa_comp_rating_risk_management_met_minimum_expectation_textbox").clear();
	        getObject("sa_comp_rating_risk_management_met_minimum_expectation_textbox").sendKeys(riskManagementMetMinExp_string);

	        getObject("sa_comp_rating_update_button").click();
	        
	        APP_LOGS.debug("Sirion Admin Composite Rating Created successfully");
	        fail=false;

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
