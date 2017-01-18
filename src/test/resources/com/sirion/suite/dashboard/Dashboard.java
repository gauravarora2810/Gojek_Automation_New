package test.resources.com.sirion.suite.dashboard;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;

public class Dashboard extends TestSuiteBase
{
	String chart_id;
	String chart_id_outside;
	 String runmodes[] = null;
	  static int count = -1;
	  // static boolean pass=false;
	  static boolean fail = false;
	  static boolean skip = false;
	  static boolean isTestPass = true;

	  // Runmode of test case in a suite
	  @BeforeTest
	  public void checkTestSkip() {

	    if (!TestUtil.isTestCaseRunnable(dashboard_suite_xls, this.getClass().getSimpleName())) {
	      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
	      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
	    }
	    // load the runmodes off the tests
	    runmodes = TestUtil.getDataSetRunmodes(dashboard_suite_xls, this.getClass().getSimpleName());
	  }
	  @Test(groups = "ActionCreation", dataProvider = "getTestData")
	public void dashboardTesting(String dashboardID) throws InterruptedException
	{
		
		  count++;
	        if(!runmodes[count].equalsIgnoreCase("Y")){
	            skip=true;
	            throw new SkipException("Runmode for test set data set to no "+count);
	        }
	        
	        APP_LOGS.debug("Executing Test Case Issue Audit Log Creation");
	        System.out.println(dashboardID);
	        openBrowser();
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			getObject("analytics_link").click();
			Thread.sleep(10000);
			driver.findElement(By.xpath(".//*[@id='mainOL']/div[2]/div[1]/div/div[1]/ul/li[4]/a/span")).click();
			Thread.sleep(10000);
			Actions select_chart=new Actions(driver);
			select_chart.moveToElement(driver.findElement(By.xpath(".//*[@id='mainOL']/div[2]/div[1]/div/div[1]/ul/li[5]/a/span"))).build().perform();
			Thread.sleep(5000);
			driver.findElement(By.xpath(".//*[@id='PriorityLink']")).click();
			//Select chartlist=new Select(driver.findElement(By.xpath(".//*[@id='chartList']")));
			//List<WebElement> chartlist_array=chartlist.getOptions();
			
			List<WebElement> allOptions=driver.findElements(By.tagName("input"));

			    Iterator<WebElement> iterator_obj = allOptions.iterator();
			    while(iterator_obj.hasNext()) {
			        WebElement ele = iterator_obj.next();
			        if (ele.isSelected()) {
			            ele.click();
			        // do something in else perhaps
			        }
			    }
			
			    
			Thread.sleep(20000);
			String[] select_chart_array=dashboardID.split(",");
			System.out.println(select_chart_array);
			for (int i=0 ; i <= select_chart_array.length-1 ; i++)
			{
			
				chart_id=select_chart_array[i];
				driver.findElement(By.xpath(".//input[@id='"+chart_id+"']")).click();
	        }

			driver.findElement(By.xpath(".//*[@id='savePreferences']")).click();
			System.out.println("Hello Gaurav");
			Thread.sleep(10000);
			for (int i=0 ; i <= select_chart_array.length-1 ; i++)
			{
				chart_id_outside=select_chart_array[i];
				System.out.println(chart_id_outside);
				driver.findElement(By.xpath(".//*[@id='"+chart_id_outside+"']/a/span")).click();
				if(!chart_id_outside.equalsIgnoreCase("11") || !chart_id_outside.equalsIgnoreCase("13"))
				{
				driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[3]/a/span")).click();
				}
				Thread.sleep(5000);
				driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[2]/a/span")).click();
				Thread.sleep(5000);
				//driver.findElement(By.xpath(".//*[@id='resetAttr']")).click();
				//driver.findElement(By.xpath(".//*[@id='Supplier']")).click();
				
				//driver.findElement(By.xpath(".//*[@id='applyAttributesAndSplit']")).click();
				
				
				driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[1]/a/span")).click();
				Thread.sleep(5000);
				//System.out.println(enduse);
				driver.navigate().refresh();
				//Thread.sleep(10000);
				Thread.sleep(5000);
				//driver.findElement(By.className("ui-button-icon-primary ui-icon ui-icon-closethick")).click();
			}
			
			
	}
	

	    @AfterMethod
	    public void reportDataSetResult(){
	        if(skip)
	            TestUtil.reportDataSetResult(dashboard_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
	        else if(fail){
	            isTestPass=false;
	            TestUtil.reportDataSetResult(dashboard_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
	        }
	        else
	            TestUtil.reportDataSetResult(dashboard_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
	        
	        skip=false;
	        fail=false;
	        

	    }
	    
	    @AfterTest
	    public void reportTestResult(){
	        if(isTestPass)
	            TestUtil.reportDataSetResult(dashboard_suite_xls, "Test Cases", TestUtil.getRowNum(dashboard_suite_xls,this.getClass().getSimpleName()), "PASS");
	        else
	            TestUtil.reportDataSetResult(dashboard_suite_xls, "Test Cases", TestUtil.getRowNum(dashboard_suite_xls,this.getClass().getSimpleName()), "FAIL");
	    
	    }
	    
	    @DataProvider
	    public Object[][] getTestData(){
	        return TestUtil.getData(dashboard_suite_xls, this.getClass().getSimpleName()) ;
	    }
	  
	
}
