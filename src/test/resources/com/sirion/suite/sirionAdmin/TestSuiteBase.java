package test.resources.com.sirion.suite.sirionAdmin;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Sirion Admin Suite");
		
		if(!TestUtil.isSuiteRunnable(suiteXls,"Sirion Admin Suite")) {
			APP_LOGS.debug("Skipped Sirion Admin Suite as the Runmode was set to NO");
			throw new SkipException("Runmode of Sirion Admin Suite set to NO. So Skipping all tests in Sirion Admin Suite");
			}
		}

	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}
