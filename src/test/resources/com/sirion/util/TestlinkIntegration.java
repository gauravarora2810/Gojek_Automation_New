package test.resources.com.sirion.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TestlinkIntegration extends TestBase {

	private WebDriver driver;

	// Substitute your Dev Key Here
	public final static String DEV_KEY = CONFIG.getProperty("testlink_DEV_KEY");

	// Substitute your Server URL Here
	public final static String SERVER_URL = CONFIG.getProperty("testlink_SERVER_URL");

	// Substitute your project name Here
	public final static String PROJECT_NAME = CONFIG.getProperty("testlink_PROJECT_NAME");

	// Substitute your test plan Here
	public final static String PLAN_NAME = CONFIG.getProperty("testlink_PLAN_NAME");

	// Substitute your build name
	public final static String BUILD_NAME = CONFIG.getProperty("testlink_BUILD_NAME");

	@BeforeSuite
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
		}

	public static void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException {
		TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
		testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,	testCase, BUILD_NAME, exception, result);
		}
	}
