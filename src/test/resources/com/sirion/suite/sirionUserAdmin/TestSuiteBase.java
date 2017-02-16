package test.resources.com.sirion.suite.sirionUserAdmin;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of User Admin Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Sirion User Admin Suite")) {
			APP_LOGS.debug("Skipped User Admin Suite as the runmode was set to NO");
			throw new SkipException("Runmode of User Admin Suite set to NO. So Skipping all tests in Sirion User Admin Suite");
			}
		}
	}
