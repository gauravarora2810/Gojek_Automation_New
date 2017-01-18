package test.resources.com.sirion.suite.action;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;

public class performance_vodafone extends TestSuiteBase

{
	
	 public static WebDriver driver = null;
	 //static File path=new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	 
	  /*static FirefoxBinary ffbinary=new FirefoxBinary(path);
	  static FirefoxProfile ffprofile=new FirefoxProfile();*/
	 public static void perfomance() throws Exception
	{
		/*driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		
	//	http://final.rimo.office/tblusers/update/1059
		
		driver.get("http://hdfc.bombardier.office:8080/login");
		
		driver.findElement(By.xpath("//input[@id='user']")).sendKeys("hdfc_admin1");
		
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin12345");*/
		
		//driver.findElement(By.xpath("//input[@class='login-arrow']")).click();
		for(int i=1062; i<=1258;i++)
		{
		//driver.navigate().to("http://hdfc.bombardier.office:8080/tblusers/update/"+i+"?_t=1474452444140");
		
			driver.navigate().to("http://final.rimo.office/tblusers/update/"+i);	
			
			Thread.sleep(5000);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='1390_read']")));
			driver.findElement(By.xpath(".//*[@id='1390_read']")).click();
			
			driver.findElement(By.xpath(".//*[@id='1390_write']")).click();
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='proceed']")));
			driver.findElement(By.xpath(".//*[@id='proceed']")).click();
			
			
		/*Select select=new Select(driver.findElement(By.xpath(".//*[@id='_userRoleGroups_id']")));
		List<WebElement> list_options_urg=select.getOptions();
		for(int j=0;j<list_options_urg.size();j++)
		{
			select.selectByIndex(j);
		}
		
		Select select_ac=new Select(driver.findElement(By.xpath(".//*[@id='_accessCriterias_id']")));
		List<WebElement> list_options_ac=select_ac.getOptions();
		System.out.println(list_options_ac.size());
		//Thread.sleep(5000);
		
		select_ac.selectByValue("1001");
		select_ac.selectByValue("1002");
		
		//Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='proceed']")).click();*/
		
		}
		
	}
	 
	 public static void main(String str[])
	 {
		 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		 driver=new ChromeDriver();
		
		 
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.manage().window().maximize();
			
		//	http://final.rimo.office/tblusers/update/1059
			
			driver.get("http://final.rimo.office/login");
			
			driver.findElement(By.xpath("//input[@id='user']")).sendKeys("final_admin1");
			
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin12345");
		 
			driver.findElement(By.xpath("//input[@class='login-arrow']")).click();
		 
		 try {
			performance_vodafone.perfomance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
