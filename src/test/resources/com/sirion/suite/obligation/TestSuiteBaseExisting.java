package test.resources.com.sirion.suite.obligation;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBaseExisting extends TestBase{
    // check if the suite ex has to be skiped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of Obligation Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Obligation Suite")){
			APP_LOGS.debug("Skipped Obligation Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Obligation Suite set to no. So Skipping all tests in Suite");
		}
		
	}
}
