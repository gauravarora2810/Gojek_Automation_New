package test.resources.com.sirion.suite.contractTemplate;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Contract Template Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Contract Template Suite")) {
			APP_LOGS.debug("Skipped Contract Template Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Contract Template Suite set to NO. So Skipping all tests in Suite");
		}
	}

	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
	}
}
