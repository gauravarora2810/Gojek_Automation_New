package test.resources.com.sirion.base;

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
import org.openqa.selenium.NoSuchElementException;
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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import test.resources.com.sirion.util.ErrorUtil;
import test.resources.com.sirion.util.Xls_Reader;

public class TestBase {
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static boolean isInitialized = false;
	public static boolean isBrowserOpened = false;
	public static boolean isSirionAdminLogin = false;
	public static boolean isClientSetupAdminLogin = false;
	public static boolean isSirionUserAdminLogin = false;
	public static boolean isClientAdminLogin = false;
	public static boolean isEndUserLogin = false;

	public static Logger APP_LOGS = null;

	public static Properties CONFIG = null;
	public static Properties OR = null;

	public static Xls_Reader suiteXls = null;

	public static Xls_Reader sirion_admin_suite_xls = null;
	public static Xls_Reader client_setup_admin_suite_xls = null;
	public static Xls_Reader user_admin_suite_xls = null;
	public static Xls_Reader client_admin_suite_xls = null;

	public static Xls_Reader vendor_hierarchy_suite_xls = null;
	public static Xls_Reader supplier_suite_xls = null;
	public static Xls_Reader contract_suite_xls = null;
	public static Xls_Reader sl_suite_xls = null;
	public static Xls_Reader child_sl_suite_xls = null;
	public static Xls_Reader obligation_suite_xls = null;
	public static Xls_Reader child_obligation_suite_xls = null;
	public static Xls_Reader action_suite_xls = null;
	public static Xls_Reader issue_suite_xls = null;
	public static Xls_Reader cr_suite_xls = null;
	public static Xls_Reader int_suite_xls = null;
	public static Xls_Reader inv_suite_xls = null;
	public static Xls_Reader wor_suite_xls = null;
	public static Xls_Reader clause_suite_xls = null;
	public static Xls_Reader contract_template_suite_xls = null;
	public static Xls_Reader cdr_suite_xls = null;
	public static Xls_Reader po_suite_xls=null;

	public static Xls_Reader common_listing_suite_xls = null;
	public static Xls_Reader supplier_listing_suite_xls = null;
	public static Xls_Reader supplier_report_suite_xls = null;
	public static Xls_Reader contract_report_suite_xls = null;
	public static Xls_Reader sl_report_suite_xls = null;
	public static Xls_Reader child_sl_report_suite_xls = null;
	public static Xls_Reader obligation_report_suite_xls = null;
	public static Xls_Reader child_obligation_report_suite_xls = null;
	public static Xls_Reader action_report_suite_xls = null;
	public static Xls_Reader issues_report_suite_xls = null;
	public static Xls_Reader cr_listing_suite_xls = null;
	public static Xls_Reader cr_report_suite_xls = null;
	public static Xls_Reader interpretation_report_suite_xls = null;
	public static Xls_Reader invoices_report_suite_xls = null;
	public static Xls_Reader wor_report_suite_xls = null;
	public static Xls_Reader common_report_suite_xls = null;

	public static Xls_Reader dashboard_suite_xls = null;

	public static Xls_Reader suite_cart_xls = null;
	public static Xls_Reader suite_productDisplay_xls = null;
	public static Xls_Reader rc1_11_suite_xls = null;
	public static Xls_Reader governance_body_suite_xls=null;
	public static Xls_Reader calendar_suite_xls=null;
	public static Hashtable<String, String> sessionData = new Hashtable<String, String>();

	public static WebDriver driver = null;
	public WebDriverWait wait_in_report = null;

	File path = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");

	FirefoxBinary ffbinary;
	FirefoxProfile ffprofile;

	public static void initialize() throws Exception {
		System.out.println("Calling Test Base");
		if (!isInitialized) {
			APP_LOGS = Logger.getLogger("devpinoyLogger");
			APP_LOGS.debug("Loading Property Files");

			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//com//sirion//config/config.properties");
			CONFIG.load(ip);

			OR = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//com//sirion//config/OR.properties");
			OR.load(ip);

			APP_LOGS.debug("Loaded Property Files Successfully");
			APP_LOGS.debug("Loading XLS Files");

			suiteXls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Suite.xlsx");

			sirion_admin_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Sirion Admin Suite.xlsx");
			client_setup_admin_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Client Setup Admin Suite.xlsx");
			user_admin_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Sirion User Admin Suite.xlsx");

			client_admin_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Client Admin Suite.xlsx");
			calendar_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Calendar Todo Suite.xlsx");
			
			vendor_hierarchy_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Vendor Hierarchy Suite.xlsx");
			supplier_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Supplier Suite.xlsx");
			contract_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Contract Suite.xlsx");
			sl_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//SL Suite.xlsx");

			child_sl_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Child SL Suite.xlsx");
			obligation_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Obligation Suite.xlsx");
			child_obligation_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Child Obligation Suite.xlsx");
			action_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Action Suite.xlsx");
			issue_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Issue Suite.xlsx");
			cr_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//CR Suite.xlsx");
			int_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Interpretation Suite.xlsx");
			inv_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Invoice Suite.xlsx");
			wor_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//WOR Suite.xlsx");
			po_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//PO Suite.xlsx");
			governance_body_suite_xls=new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Governance Body Suite.xlsx");
			clause_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Clause Suite.xlsx");
			contract_template_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Contract Template Suite.xlsx");
			cdr_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//CDR Suite.xlsx");
			
			common_listing_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Common Listing Suite.xlsx");
			common_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Common Report Suite.xlsx");
		

/*			supplier_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Supplier Report Suite.xlsx");
			contract_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Contract Report Suite.xlsx");
			sl_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//ServiceLevel Report Suite.xlsx");
			child_sl_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//ChildServiceLevel Report Suite.xlsx");
			obligation_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//Obligation Report Suite.xlsx");
			child_obligation_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//ChildObligation Report Suite.xlsx");
			action_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//Action Report Suite.xlsx");
			issues_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//Issue Report Suite.xlsx");
			cr_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//ChangeRequest Report Suite.xlsx");
			interpretation_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//Interpretation Report Suite.xlsx");
			invoices_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//Invoice Report Suite.xlsx");
			wor_report_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//WorkOrderRequest Report Suite.xlsx");
			common_report_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//Common Report Suite.xlsx");

			dashboard_suite_xls = new Xls_Reader(System.getProperty("user.dir")	+ "//src//test//resources//com//sirion//xls//Dashboard Suite.xlsx");
			rc1_11_suite_xls = new Xls_Reader(System.getProperty("user.dir") + "//src//test//resources//com//sirion//xls//RC1_11 Suite.xlsx");
*/
			APP_LOGS.debug("Loaded XLS Files Successfully");
			isInitialized = true;
			System.out.println("at last oof function intiallized");
			}
		}

	// Opening a Browser
	public void openBrowser() {
		if (!isBrowserOpened) {
			
			
			if (CONFIG.getProperty("browserType").equals("MOZILLA")) {
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
		wait_in_report = new WebDriverWait(driver, 30);
		System.out.println("Implicitly again wait applied for 1 seconds for check test waiting");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	// Closing a Browser
	public void closeBrowser() {
		driver.quit();
		isInitialized = false;
		isBrowserOpened = false;
		isSirionAdminLogin=false;
		isClientSetupAdminLogin=false;
		isClientAdminLogin=false;
		isEndUserLogin=false;
		isSirionUserAdminLogin=false;
		}

	// Returns Element Presence
	public boolean checkElementPresence(String xpathKey) {
		int count = driver.findElements(By.xpath(OR.getProperty(xpathKey))).size();
		try {
			Assert.assertTrue(count > 0, "No Element Present");
			} catch (Throwable t) {
				ErrorUtil.addVerificationFailure(t);
				APP_LOGS.debug("No Element Present");
				return false;
				}
		return true;
		}

	// Gets an Object with the given Xpath Key
	public WebElement getObject(String xpathKey) {
		try {
			WebElement x = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
			return x;
			} catch (Throwable t) {
				ErrorUtil.addVerificationFailure(t);
				APP_LOGS.debug("Cannot Find Object with Key -- " + xpathKey);
				return null;
				}
		}

	// Hovers on an Object with the given Xpath Key
	public WebElement plus_button(String xpathKey) {
		try {
			Thread.sleep(5000);
			Actions action = new Actions(driver);
			WebElement menu = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
			action.moveToElement(menu).click().build().perform();
			} catch (Throwable t) {
				ErrorUtil.addVerificationFailure(t);
				APP_LOGS.debug("Cannot find Plus button with key -- " + xpathKey);
				return null;
				}
			return null;
			}

	// Login as Sirion Super Admin
	public void sirionAdminLogin(String sirionAdminURL, String sirionAdminUsername, String sirionAdminPassword) {
		if (!isSirionAdminLogin) {
			driver.get(CONFIG.getProperty("sirionAdminURL"));
			getObject("username").sendKeys(CONFIG.getProperty("sirionAdminUsername"));
			getObject("password").sendKeys(CONFIG.getProperty("sirionAdminPassword"));
			getObject("login_button").click();

			APP_LOGS.debug(CONFIG.getProperty("sirionAdminUsername") + " logged in Successfully");
			}
		isSirionAdminLogin = true;
		}

	// Login as Sirion Client Setup Admin
	public void clientSetupAdminLogin(String ClientSetupAdminURL, String ClientSetupAdminUserName, String ClientSetupAdminPassword) {
		if (!isClientSetupAdminLogin) {
			driver.get(CONFIG.getProperty("ClientSetupAdminURL"));
			getObject("username").sendKeys(CONFIG.getProperty("ClientSetupAdminUserName"));
			getObject("password").sendKeys(CONFIG.getProperty("ClientSetupAdminPassword"));
			getObject("login_button").click();

			APP_LOGS.debug(CONFIG.getProperty("ClientSetupAdminUserName") + " logged in Successfully");
			}
		isClientSetupAdminLogin = true;
		}

	// Login as Sirion User Admin
	public void sirionUserAdminLogin(String sirionUserAdminURL, String sirionUserAdminUsername, String sirionUserAdminPassword) {
		if (!isSirionUserAdminLogin) {
			driver.get(CONFIG.getProperty("sirionuserAdminURL"));
			getObject("username").sendKeys(CONFIG.getProperty("sirionuserAdminUsername"));
			getObject("password").sendKeys(CONFIG.getProperty("sirionuserAdminPassword"));
			getObject("login_button").click();

			APP_LOGS.debug(CONFIG.getProperty("sirionuserAdminUsername") + " logged in successfully");
			}
		isSirionUserAdminLogin = true;
		}

	// Login as Client Admin
	public void clientAdminLogin(String clientAdminURL, String clientAdminUsername, String clientAdminPassword) {
		if (!isClientAdminLogin) {
			driver.get(CONFIG.getProperty("clientAdminURL"));
			getObject("username").sendKeys(CONFIG.getProperty("clientAdminUsername"));
			getObject("password").sendKeys(CONFIG.getProperty("clientAdminPassword"));
			getObject("login_button").click();

			APP_LOGS.debug(CONFIG.getProperty("clientAdminUsername") + " logged in Successfully");
			}
		isClientAdminLogin = true;
		}

	// Login as Client User
	public void endUserLogin(String endUserURL, String endUserUsername, String endUserPassword) {
		if (!isEndUserLogin) {
			driver.get(CONFIG.getProperty("endUserURL"));
			getObject("username").sendKeys(CONFIG.getProperty("endUserUsername"));
			getObject("password").sendKeys(CONFIG.getProperty("endUserPassword"));
			getObject("login_button").click();

			APP_LOGS.debug(CONFIG.getProperty("endUserUsername") + " logged in Successfully");
			}
		isEndUserLogin = true;
		}

	// Connects to the given Database
	public Connection getDbConnection() throws ClassNotFoundException, SQLException {
		APP_LOGS.debug("Connecting to Database");
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONFIG.getProperty("DatabaseURL"), CONFIG.getProperty("DatabaseUsername"), CONFIG.getProperty("DatabasePassword"));

			APP_LOGS.debug("Connected to Database " + CONFIG.getProperty("DatabaseURL"));
			} catch (ClassNotFoundException ex) {
				} catch (SQLException ex) {
					}
		return con;
		}

	// Executes the given query on given Database
	public String queryResult(Connection con, String query) throws SQLException {
		String count_from_db = null;
		java.sql.Statement stmt = con.createStatement();

		APP_LOGS.debug("Executing Database Query ");
		java.sql.ResultSet resultset = stmt.executeQuery(query);

		System.out.println("Result is - " + resultset);

		int numcols = resultset.getMetaData().getColumnCount();

		List<List<String>> result = new ArrayList<>();

		while (resultset.next()) {
			List<String> row = new ArrayList<>(numcols);
			for (int i = 1; i <= numcols; i++) {
				row.add(resultset.getString(i));
				System.out.print(resultset.getString(i) + "\t");
				}
			result.add(row);

			System.out.print("\n");

			count_from_db = result.get(0).get(0);

			System.out.println("count from db " + count_from_db);

			APP_LOGS.debug("Count from DB " + count_from_db);
			}
		return count_from_db;
		}

	// Gets the Screenshot
	public void capturescreenshot(String filename) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\screenshots\\" + filename + ".jpg"));
		}
	public String convertDoubleToIntegerInStringForm(String data) {
        data = Integer.valueOf((Double.valueOf(Double.parseDouble(data))).intValue()).toString();
        return data;
 }

public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}


/*public WebElement fluentWait(final By locator) {
    Wait<WebDriver> webelement_waiting = new FluentWait<WebDriver>(driver)
            .withTimeout(30, TimeUnit.SECONDS)
            .pollingEvery(5, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class);

    WebElement foo = webelement_waiting.until(new Function<WebDriver, WebElement>() 
    		{
        public WebElement apply(WebDriver driver) 
        
        {
            return driver.findElement(locator);
        }
    });

    return  foo;
};
*/

}