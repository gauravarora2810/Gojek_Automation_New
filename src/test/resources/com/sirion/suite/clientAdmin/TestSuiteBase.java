package test.resources.com.sirion.suite.clientAdmin;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Client Admin Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Client Admin Suite")) {
			APP_LOGS.debug("Skipped Client Admin Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Client Admin Suite set to NO. So Skipping all tests in Client Admin Suite");
			}
		}
	
	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}