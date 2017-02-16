package test.resources.com.sirion.suite.vendorHierarchy;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking Runmode of Vendor Hierarchy Suite");
		if (!TestUtil.isSuiteRunnable(suiteXls, "Vendor Hierarchy Suite")) {
			APP_LOGS.debug("Skipped Vendor Hierarchy Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Vendor Hierarchy Suite set to NO. So Skipping all tests in Vendor Hierarchy Suite");
			}
		}
	
	@AfterSuite
	public void checkSuiteClosure() {
		closeBrowser();
		}
	}
