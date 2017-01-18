package test.resources.com.sirion.suite.sl;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of SL Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "SL Suite")) {
			APP_LOGS.debug("Skipped SL Suite as the runmode was set to NO");
			throw new SkipException("Runmode of SL Suite set to no. So Skipping all tests in Suite");
			}
		}
	}
