package test.resources.com.sirion.suite.dashboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	String dashboardCategoryTypeId;
	//String chart_id_outside;
	WebElement ele=null;
	 String runmodes[] = null;
	  static int count = -1;
	  // static boolean pass=false;
	  static boolean fail = false;
	  static boolean skip = false;
	  static boolean isTestPass = true;
	  Iterator<WebElement> iterator_obj=null;
	  Actions mouse_hover_on_download_icon;
	  Actions mouse_hover_on_select_classic_chart;

	  // Runmode of test case in a suite
	  @BeforeTest
	  public void checkTestSkip() {

	    if (!TestUtil.isTestCaseRunnable(dashboard_suite_xls, this.getClass().getSimpleName())) {
	      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
	      throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");
	    }
	    // load the runmodes off the tests
	    runmodes = TestUtil.getDataSetRunmodes(dashboard_suite_xls, this.getClass().getSimpleName());
	  }
	  @Test(dataProvider = "getTestData")
	public void dashboardTesting(String dashboardCategoryType, String dashboardID, String dashboardName, String dashboardDownloadGraph, String dashboardDownloadExcel) throws InterruptedException, Exception
	{
		
		  count++;
		    if (!runmodes[count].equalsIgnoreCase("Y")) {
		      skip = true;
		      throw new SkipException("Runmode for test set data set to no " + count);
		    }
	        APP_LOGS.debug("Executing Test Case Issue Audit Log Creation");
	        System.out.println("Dashboard Selected "+dashboardID);
	        openBrowser();
	      
			endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
			Thread.sleep(10000);
			mouse_hover_on_select_classic_chart=new Actions(driver);
			getObject("analytics_link").click();
			Thread.sleep(30000);
			dashboardCategoryTypeId=Integer.valueOf(
					(Double.valueOf(Double.parseDouble(dashboardCategoryType))).intValue())
					.toString();
			System.out.println("Gaurav Arora");
			System.out.println("dashboard category type is "+dashboardCategoryTypeId);
			if(dashboardCategoryTypeId.equalsIgnoreCase("2"))
			{
			driver.findElement(By.xpath(".//*[@id='priorityListModernView']/a/span")).click();
			System.out.println("Kumar");
			}
			else if(dashboardCategoryTypeId.equalsIgnoreCase("1"))
			{
				System.out.println("In classic chart first line");
				driver.findElement(By.xpath(".//*[@id='srsDashboardCharts']/ul/li[1]/a/span")).click();
				Thread.sleep(30000);
				mouse_hover_on_select_classic_chart.moveToElement(driver.findElement(By.xpath(".//*[@id='mainOL']/div[2]/div[1]/div/div[1]/ul/li[5]/a/span"))).clickAndHold().build().perform();
				System.out.println("In classic chart second line");
				Thread.sleep(5000);
				driver.findElement(By.xpath(".//*[@id='PriorityLink']")).click();
				System.out.println("classic charts");
		
			}
			
			List<WebElement> allOptions=driver.findElements(By.tagName("input"));
			//System.out.println(allOptions);

			    iterator_obj = allOptions.iterator();
			    while(iterator_obj.hasNext()) {
			         ele = iterator_obj.next();
			        if (ele.isSelected()) {
			            ele.click();
			        // do something in else perhaps
			        }
			    }
			
			chart_id=Integer.valueOf(
					(Double.valueOf(Double.parseDouble(dashboardID))).intValue())
					.toString();
			System.out.println(chart_id);
			
			
			System.out.println("Gaurav Arora hi new");   
			//System.out.println(chart_id_string);
			// chart_id=Integer.valueOf(dashboardID);
			//System.out.println("chart id is "+chart_id);
			
			 
			
			 Thread.sleep(2000);
			 driver.findElement(By.xpath(".//input[@id='"+chart_id+"']")).click();
	        
Thread.sleep(5000);
			driver.findElement(By.xpath(".//*[@id='savePreferences']")).click();
			System.out.println("Hello Gaurav");
			Thread.sleep(30000);
			
				
				
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='"+chart_id+"']")));
				driver.findElement(By.xpath(".//*[@id='"+chart_id+"']")).click();
				Thread.sleep(10000);
				if(driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[1]/a/span")).isDisplayed())
				{
				driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[1]/a/span")).click();
				}
				Thread.sleep(5000);
				driver.navigate().refresh();
				/*if(driver.findElement(By.xpath("//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")).isDisplayed())
				{
				driver.findElement(By.xpath("//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")).click();
				}*/
				Thread.sleep(30000);
				
			/*	if(driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id_outside+"']")).isDisplayed())
				{
					Actions mouse_hover_on_download_icon=new Actions(driver);
					mouse_hover_on_download_icon.moveToElement(driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id_outside+"']"))).build().perform();
					//driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id_outside+"']")).click();
					driver.findElement(By.xpath(".//*[@id='"+"exportChartImg"+chart_id_outside+"']")).click();
					driver.findElement(By.xpath(".//button/span[contains(.,'OK')]")).click();
					//driver.findElement(By.xpath(".//*[@id='exportChartImg+"+chart_id_outside+"']")).click();
				}*/
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id+"']")));
				if(driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id+"']")).isDisplayed())
				{
					System.out.println("In  excel download function first line");
					
					mouse_hover_on_download_icon.moveToElement(driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id+"']"))).click().build().perform();
					//driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id_outside+"']")).click();
					if(!dashboardDownloadExcel.equalsIgnoreCase(""))
					{
					if(driver.findElement(By.xpath(".//*[@id='"+"exportChart"+chart_id+"']")).isDisplayed())
					{
						driver.findElement(By.xpath(".//*[@id='"+"exportChart"+chart_id+"']")).click();
					driver.findElement(By.xpath(".//button/span[contains(.,'OK')]")).click();
					}
					Thread.sleep(10000);
					}
					System.out.println("In @ Test after excel download");
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id+"']")));
					
					if(driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id+"']")).isDisplayed())
					{
						System.out.println("In  chart download function first line");
					mouse_hover_on_download_icon.moveToElement(driver.findElement(By.xpath(".//*[@id='"+"db_"+chart_id+"']"))).click().build().perform();
					if(!dashboardDownloadGraph.equalsIgnoreCase(""))
					{
					if(driver.findElement(By.xpath(".//*[@id='"+"exportChartImg"+chart_id+"']")).isDisplayed())
					{
						driver.findElement(By.xpath(".//*[@id='"+"exportChartImg"+chart_id+"']")).click();
					driver.findElement(By.xpath(".//button/span[contains(.,'OK')]")).click();
					}
					}
					
					System.out.println("In  chart download function last line");
					}
					System.out.println("In @ Test after chart download");
					/*Robot robot = new Robot();	
			        robot.mouseMove(630, 420); // move mouse point to specific location	
			        robot.delay(1500);        // delay is to make code wait for mentioned milliseconds before executing next step	
			        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click	
			        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click	
			        robot.delay(1500);	
			        robot.keyPress(KeyEvent.VK_DOWN); // press keyboard arrow key to select Save radio button	
			        Thread.sleep(2000);	
			        robot.keyPress(KeyEvent.VK_ENTER);	*/
			       /* Thread.sleep(5000);
			        Alert myAlert = driver.switchTo().alert();
			        myAlert.accept();*/
			        System.out.println("In for loop last line");
				}
				Thread.sleep(10000);
			
				
				/*Thread.sleep(10000);
				driver.findElement(By.xpath(".//*[@id='mainOL']/div[2]/div[1]/div/div[1]/ul/li[4]/a/span")).click();
				Thread.sleep(10000);
				Actions select_chart=new Actions(driver);
				select_chart.moveToElement(driver.findElement(By.xpath(".//*[@id='mainOL']/div[2]/div[1]/div/div[1]/ul/li[5]/a/span"))).build().perform();
				Thread.sleep(5000);*/
				//driver.findElement(By.xpath(".//*[@id='PriorityLink']")).click();
				//Select chartlist=new Select(driver.findElement(By.xpath(".//*[@id='chartList']")));
				//List<WebElement> chartlist_array=chartlist.getOptions();
				
				
				
				/*if(!chart_id_outside.equalsIgnoreCase("11") || !chart_id_outside.equalsIgnoreCase("13"))
				{
				driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[3]/a/span")).click();
				}*/
				//Thread.sleep(5000);
				//driver.findElement(By.xpath(".//*[@id='chartContainerDiv']/div[1]/div/ul/li[2]/a/span")).click();
			
				
				//driver.findElement(By.xpath(".//*[@id='resetAttr']")).click();
				//driver.findElement(By.xpath(".//*[@id='Supplier']")).click();
				
				//driver.findElement(By.xpath(".//*[@id='applyAttributesAndSplit']")).click();
				
				//driver.findElement(By.className("ui-button-icon-primary ui-icon ui-icon-closethick")).click();
			
			
			System.out.println("Last line of @Test Annotation");
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
