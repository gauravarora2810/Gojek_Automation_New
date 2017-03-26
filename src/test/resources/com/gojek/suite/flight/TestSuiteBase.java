package test.resources.com.gojek.suite.flight;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import test.resources.com.gojek.base.TestBase;
import test.resources.com.gojek.util.TestUtil;

public class TestSuiteBase extends TestBase

{
   
// check if the suite ex has to be skiped
@BeforeSuite
	public void checkSuiteSkip() throws Exception
	{	
		initialize();
		//APP_LOGS.debug("Checking Runmode of Flight Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Flight Suite")){
			APP_LOGS.debug("Skipped Flight Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Flight Suite set to no. So Skipping all tests in Flight Suite");
	}
		
}
	
@AfterSuite
	public void checkSuiteClosure()
	{
		closeBrowser();
	}
}
