package test.resources.com.sirion.suite.common.reports;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Common Report Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Common Report Suite")) {
			APP_LOGS.debug("Skipped Common Suite Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Common Suite set to no. so skipping all tests in Common Report Suite");
			}
		}
	
	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}