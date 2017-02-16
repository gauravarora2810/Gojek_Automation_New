package test.resources.com.sirion.suite.cdr;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of CDR Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "CDR Suite")) {
			APP_LOGS.debug("Skipped CDR Suite as the runmode was set to NO");
			throw new SkipException("Runmode of CDR Suite set to NO. So Skipping all tests in CDR Suite");
			}
		}
	
	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}