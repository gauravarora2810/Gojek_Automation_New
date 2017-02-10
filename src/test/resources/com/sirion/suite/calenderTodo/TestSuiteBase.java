package test.resources.com.sirion.suite.calenderTodo;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import test.resources.com.sirion.base.TestBase;
import test.resources.com.sirion.util.TestUtil;

public class TestSuiteBase extends TestBase{
    // check if the suite ex has to be skiped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of Calendar Todo Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Calendar Todo Suite")){
			APP_LOGS.debug("Skipped Calendar Todo Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Calendar Todo Suite set to no. So Skipping all tests in Calendar Todo Suite");
		}
		
	}
}