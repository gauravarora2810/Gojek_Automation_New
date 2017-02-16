package test.resources.com.sirion.suite.common.listings;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Common Listing Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Common Listing Suite")) {
			APP_LOGS.debug("Skipped Common Listing Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Common Listing Suite set to NO. so skipping all tests in Common Listing Suite");
			}
		}

	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}