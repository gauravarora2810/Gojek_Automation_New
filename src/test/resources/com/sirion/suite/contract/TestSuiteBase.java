package test.resources.com.sirion.suite.contract;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase{
    // check if the suite ex has to be skiped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of Contract Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Contract Suite")){
			APP_LOGS.debug("Skipped Contract Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Contract Suite set to no. So Skipping all tests in Suite");
		}
		
	}
	
	@AfterSuite
	public void checkSuiteClosure()
	{
		closeBrowser();
	}
}
