package test.resources.com.sirion.suite.governanceBody;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of GB Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Governance Body Suite")){
			APP_LOGS.debug("Skipped GB Suite as the runmode was set to NO");
			throw new SkipException("Runmode of GB Suite set to no. So Skipping all tests in GB Suite");
		}
		
	}
}
