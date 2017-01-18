package test.resources.com.sirion.suite.obligation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


public class ObligationLinkage extends TestSuiteBase


{
	//WebElement datepicker_ui;
	 String actionTitle,  actionDescription,  actionType,  actionPriority,  actionResponsibility,
     actionGovernanceBody,  actionDeliveryCountries,  actionTimeZone,  actionCurrencies,  actionSupplierAccess,
     actionDependentEntity,  actionTier,  actionRequestedOnMonth,  actionRequestedOnDate,  actionRequestedOnYear,
     actionDueDateMonth,  actionDueDateDate,  actionDueDateYear,  actionFinancialImpact,
     actionSupName, actionSourceName, actionStatus, actionSource , actionFunction,  actionService,  actionRegion,  actionCountry;
	String runmodes[]=null;
    static int count=-1;
    //static boolean pass=false;
    static boolean fail=true;
    static boolean skip=false;
    static boolean isTestPass=true;
    static String actionObligationName;
    Object[][] getExcelDatas;
    int t =0;
    // Runmode of test case in a suite
    @BeforeTest
    public void checkTestSkip(){
        
        if(!TestUtil.isTestCaseRunnable(obligation_suite_xls,this.getClass().getSimpleName())){
            APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
            throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
        }
        // load the run modes off the tests
        runmodes=TestUtil.getDataSetRunmodes(obligation_suite_xls, this.getClass().getSimpleName());
    }
    
    
    @Test 
    public void ObligationLinkageTest()  throws InterruptedException, ClassNotFoundException, SQLException
             
            {
        // test the runmode of current dataset
        count++;
        if(!runmodes[count].equalsIgnoreCase("Y")){
            skip=true;
            throw new SkipException("Runmode for test set data set to no "+count);
        }
        
        APP_LOGS.debug("Executing Test Case Obligation Linkages");
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        if (!checkElementPresence("supplier_quick_link")) {
//          fail = true;
//          // return; // conditional
//        }
//        openBrowser();
//		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
//	    getObject("child_obligaiton_quick_link").click(); 
//	    getObject("obligaiton_link").click(); 
//	    getObject("ob_id_link").click(); //click on obligation id
//		Thread.sleep(5000);
//       
        //plus_button("ob_plus_button_link"); // web element for plus button on supplier page
//        plus_button("ob_plus_button_link"); // web element for plus button on obligation page
//        Thread.sleep(4000);
//        getObject("ac_create_link_from_ob").click(); // click Action create link 
        
       
		ActionCreation ac=new ActionCreation();
		
		try
		{
		ac.checkTestSkip();
		getExcelDatas=ac.getTestData();
		for(Object[] getExcelData: getExcelDatas ){
			ac.ActionCreation(getExcelData[0].toString(), getExcelData[1].toString(), getExcelData[2].toString(), getExcelData[3].toString(), getExcelData[4].toString(), getExcelData[5].toString(),
					getExcelData[6].toString(), getExcelData[7].toString(), getExcelData[8].toString(), getExcelData[9].toString(), getExcelData[10].toString(),
					getExcelData[11].toString(), getExcelData[12].toString(), getExcelData[13].toString(), getExcelData[14].toString(), getExcelData[15].toString(), getExcelData[16].toString(),
					getExcelData[17].toString(), getExcelData[18].toString(), getExcelData[19].toString(), getExcelData[20].toString(), getExcelData[21].toString(), getExcelData[22].toString(), getExcelData[23].toString(),
					getExcelData[24].toString(), getExcelData[25].toString(), getExcelData[26].toString(), getExcelData[27].toString());		
		actionObligationName=getExcelData[0].toString();
		System.out.println("Now I would print");
		System.out.println(actionObligationName);
		}
			
	System.out.println(actionObligationName);
	ac.reportDataSetResult();
	ac.reportTestResult();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		System.out.println("coming back to obligation linkage");
		System.out.println("Now search the created action in forward linkage");
		System.out.println("The Action id we have created is " + ac.action_id);
		getObject("child_obligaiton_quick_link").click(); 
		getObject("obligaiton_link").click();
		//new Select(driver.findElement(By.xpath(".//*[@id='cr_length']/label/select"))
		new Select(getObject("displayButton")).selectByVisibleText("100");
		
		driver.findElement(By.xpath("//*[@id='cr']/tbody/tr[@role='row']/td[contains(.,'"+actionObligationName +"')]/preceding-sibling::td[1]/a")).click();
		//driver.findElement(By.xpath(".//*[@id='kk']/div/div/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//a[contains(.,'LINKAGES')]")).click();
		driver.findElement(By.xpath("//a[contains(.,'Forward Citations')]")).click();
		//driver.findElement(By.xpath(".//*[@id='kk']/div/div/div[2]/div[3]/div/form/div/div/div[1]/ul/li[2]/a")).click();
		
		System.out.println("Hello reaching at point before 1452");
		WebElement wediv = driver.findElement(By.id("1452"));
		//WebElement we = wediv.findElement(By.tagName("table"));
		List<WebElement> rows = wediv.findElements(By.tagName("tr"));
		Iterator<WebElement> i = rows.iterator();
		//List<String> alias = new ArrayList<String>();  
		while(i.hasNext()){
		    WebElement row=i.next();
		    List<WebElement> columns= row.findElements(By.tagName("td"));
		    Iterator<WebElement> j = columns.iterator();
		    //int aa = 0;
		        while(j.hasNext()){
		            WebElement column = j.next();
		            //if(aa == 3) { alias.add(column.getText()); }
		            System.out.print(column.getText());
		            System.out.print("    |  ");
		           // aa++;
		        }System.out.println("");
		    }System.out.println("Table content is printed");
		    
			//WebElement wedivForAlias = driver.findElement(By.id("1452"));
		    List<WebElement> rowsForId = wediv.findElements(By.tagName("tr"));
			Iterator<WebElement> iter = rowsForId.iterator();
			List<String> idArray = new ArrayList<String>();  
			while(iter.hasNext()){
			    WebElement row=iter.next();
			    List<WebElement> columns= row.findElements(By.tagName("td"));
			    Iterator<WebElement> k = columns.iterator();
			    //int aa = 0;
			        while(k.hasNext()){
			            
			            WebElement idColumn = k.next();
			            idArray.add(idColumn.getText());
			            System.out.println(idArray);
			        }
			    }
		
		
		
          
          fail=false;               
        
    }
    
    @AfterMethod
    public void reportDataSetResult(){
        if(skip)
            TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
        else if(fail){
            isTestPass=false;
            TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
        }
        else
            TestUtil.reportDataSetResult(obligation_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
        
        skip=false;
        fail=false;
        

    }
    
    @AfterTest
    public void reportTestResult(){
        if(isTestPass)
            TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls,this.getClass().getSimpleName()), "PASS");
        else
            TestUtil.reportDataSetResult(obligation_suite_xls, "Test Cases", TestUtil.getRowNum(obligation_suite_xls,this.getClass().getSimpleName()), "FAIL");
    
    }
    
    @DataProvider
    public Object[][] getTestData() {
      return TestUtil.getData(obligation_suite_xls, this.getClass().getSimpleName());
    }
	
	
	
}
