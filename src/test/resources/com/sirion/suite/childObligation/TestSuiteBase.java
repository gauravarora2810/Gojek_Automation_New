package test.resources.com.sirion.suite.childObligation;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase{
    // check if the suite ex has to be skiped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of Child Obligation Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Child Obligation Suite")){
			APP_LOGS.debug("Skipped Child Obligation Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Child Obligation Suite set to no. So Skipping all tests in Suite");
		}
		
	}
}
