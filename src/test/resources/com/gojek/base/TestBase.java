package test.resources.com.gojek.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import test.resources.com.gojek.util.Xls_Reader;

public class TestBase {
	
	

	public static boolean isInitialized = false;
	public static boolean isBrowserOpened = false;
	public static boolean isgojekLogin = false;
	

	public static Logger APP_LOGS = null;

	public static Properties CONFIG = null;
	public static Properties OR = null;

	public static Xls_Reader suiteXls = null;

	public static Xls_Reader flight_suite_xls = null;
	
	public static Xls_Reader signin_and_register_suite_xls = null;
	

	
		

	public static WebDriver driver = null;
	//public WebDriverWait wait_in_report = null;
	public static FluentWait<WebDriver> fluent_wait=null;
    

	static File path;
	static FirefoxBinary ffbinary;
	static FirefoxProfile ffprofile;

	public static void initialize() throws Exception 
	{
		System.out.println("Calling Test Base");
		if (!isInitialized) {
		

			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//com//gojek//config/config.properties");
			CONFIG.load(ip);

			OR = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//com//gojek//config/OR.properties");
			OR.load(ip);

			

			suiteXls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//gojek//xls//Suite.xlsx");
			signin_and_register_suite_xls=new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//gojek//xls//SignIn And Register Suite.xlsx");
			flight_suite_xls=new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//gojek//xls//Flight Suite.xlsx");
			
			isInitialized = true;
			
			}
		}

	// Opening a Browser
	public static void openBrowser() {
		if (!isBrowserOpened) {
			
			
			if (CONFIG.getProperty("browserType").equals("MOZILLA")) {
				path=new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				ffbinary = new FirefoxBinary(path);
				ffprofile = new FirefoxProfile();
				
				driver = new FirefoxDriver(ffbinary, ffprofile);
			} else if (CONFIG.getProperty("browserType").equals("IE")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if (CONFIG.getProperty("browserType").equals("CHROME")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
				driver = new ChromeDriver();
				}
			isBrowserOpened = true;
			driver.manage().window().maximize();
			}
		//wait_in_report = new WebDriverWait(driver, 30);
		fluent_wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS);
		System.out.println("Implicitly again wait applied for 1 seconds for check test waiting");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

	// Closing a Browser
	public void closeBrowser() {
		driver.quit();
		isInitialized = false;
		isBrowserOpened = false;
		isgojekLogin=false;
		}



	// Gets an Object with the given Xpath Key
	public static WebElement getObject(String xpathKey) {
		try {
			WebElement x = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
			return x;
			} catch (Throwable t) {
				
				return null;
				}
		}


	// Login to WebSite
	public static void gojekSiteNavigation(String site_url) {
		if (!isgojekLogin) {
			driver.get(CONFIG.getProperty("site_url"));
			}
		isgojekLogin = true;
		}






}
