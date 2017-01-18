package test.resources.com.sirion.suite.sl;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.DatabaseListingColumnCount;
import test.resources.com.sirion.util.TestUtil;

public class SLListingSorting extends TestSuiteBase {
  String runmodes[] = null;
  static int count = -1;
  // static boolean pass=false;
  static boolean fail = true;
  static boolean skip = false;
  static boolean isTestPass = true;

  String numberOfEntries = null;
  String[] numberOfEntriesSplit = null;
  String numberOfSL;
  
  // Runmode of test case in a suite
  @BeforeTest
  public void checkTestSkip() {

    if (!TestUtil.isTestCaseRunnable(sl_suite_xls, this.getClass().getSimpleName())) {
      APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
      throw new SkipException("Skipping Test Case " + this.getClass().getSimpleName() + " as runmode set to NO");// reports
    }
    // load the runmodes off the tests
    runmodes = TestUtil.getDataSetRunmodes(sl_suite_xls, this.getClass().getSimpleName());
  }

  @Test (dataProvider = "getTestData")
	public void SLListing(String ListingEntity) throws InterruptedException, ClassNotFoundException, SQLException
			 {
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		APP_LOGS.debug("Executing Test Case SL Listing sorting");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));
		getObject("sl_quick_link").click(); 
//	new Select (getObject("sl_display_dropdown")).selectByIndex(3);
		
		driver.findElement(By.xpath(".//*[@id='cr_wrapper']/div[1]/button")).click();

 
	    Double temp_ListingEntity_double = Double.parseDouble(ListingEntity);
	    int temp_ListingEntity_int = temp_ListingEntity_double.intValue();
	    String ListingEntity_string = Integer.toString(temp_ListingEntity_int);
	  		
      DatabaseListingColumnCount count = new DatabaseListingColumnCount();
      
      String listingColumns =null;
      
		
      try {
        listingColumns =  count.DatabaseListing(ListingEntity_string);
   } catch (ClassNotFoundException e) {
       e.printStackTrace();
   } catch (SQLException e) {
       e.printStackTrace();
   }
      int listingColumns_int = Integer.parseInt(listingColumns);
      for (int i = 11; i <= listingColumns_int ;i ++){        
      driver.findElement(By.xpath(".//*[@id='data-ng-app']/div[26]/button["+i+"]")).click();      
      }
	
      driver.findElement(By.xpath(".//*[@id='tabs-inner-sec']/div[1]")).click();
      
    for (int i = 2; i <= listingColumns_int ;i ++){        
        driver.findElement(By.xpath(".//*[@id='cr_wrapper']/div[4]/div[1]/div/table/thead/tr/th["+i+"]")).click(); 
        Thread.sleep(5000);
        String slSorted=driver.findElement(By.xpath(".//*[@id='cr_wrapper']/div[4]/div[1]/div/table/thead/tr/th["+i+"]")).getAttribute("aria-sort");
        System.out.println("before -- "+slSorted);  
        Assert.assertEquals(slSorted, "ascending", "Column " +driver.findElement(By.xpath(".//*[@id='cr_wrapper']/div[4]/div[1]/div/table/thead/tr/th["+i+"]")).getText() + " is sorted ascending");
        System.out.println(slSorted);       
      }
      
 //     driver.findElement(By.xpath(".//*[@id='cr_wrapper']/div[4]/div[1]/div/table/thead/tr/th[2]")).click();  
//      String slSorted=driver.findElement(By.xpath(".//*[@id='cr_wrapper']/div[4]/div[1]/div/table/thead/tr/th[2]")).getAttribute("aria-sort");
     
//      System.out.println(slSorted);  
      
      
      
/*    List<WebElement> allElements = driver.findElements(By.className("dataTables_scrollBody"));

    System.out.println(allElements.size());
    int index=0;
    boolean itemPresent=false;
    for (WebElement element: allElements) {
        String[] arr=element.getText().split("\n");
        for(String ele:arr){
            index++;
              if(index%2==0){
                  
                  continue;
              }
             // System.out.println(index+" : "+ele+" ;;;;;;;;;;;;;;;;;;;");
              if (performanceType.equals("pppp")){
                  itemPresent=true;
                  break;
                            
              }
              if(itemPresent){
                  break;
              }*/
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
	       
		
//		String numberOfItems= driver.findElement(By.xpath(".//*[@id='data-ng-app']/div[26]/button[22]")).getAttribute("aria-sort");
		    
		    

		fail = false; //this executes if assertion passes
			 }
          
  @AfterMethod
  public void reportDataSetResult() {
    if (skip)
      TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
    else if (fail) {
      isTestPass = false;
      TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
    } else
      TestUtil.reportDataSetResult(sl_suite_xls, this.getClass().getSimpleName(), count + 2, "PASS");

    skip = false;
    fail = false;

  }

  @AfterTest
  public void reportTestResult() {
    if (isTestPass)
      TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls, this.getClass().getSimpleName()), "PASS");
    else
      TestUtil.reportDataSetResult(sl_suite_xls, "Test Cases", TestUtil.getRowNum(sl_suite_xls, this.getClass().getSimpleName()), "FAIL");

  }

  @DataProvider
  public Object[][] getTestData() {
    return TestUtil.getData(sl_suite_xls, this.getClass().getSimpleName());
  }

}
