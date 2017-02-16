package test.resources.com.sirion.suite.childSL;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Child SL Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Child SL Suite")) {
			APP_LOGS.debug("Skipped Child SL Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Child SL Suite set to NO. So Skipping all tests in Suite");
			}
		}

	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}