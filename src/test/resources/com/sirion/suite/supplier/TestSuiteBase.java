package test.resources.com.sirion.suite.supplier;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Supplier Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Supplier Suite")) {
			APP_LOGS.debug("Skipped Supplier Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Supplier Suite set to NO. So Skipping all tests in Supplier Suite");
			}
		}

	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}