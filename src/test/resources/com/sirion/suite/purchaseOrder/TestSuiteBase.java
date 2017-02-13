package test.resources.com.sirion.suite.purchaseOrder;

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
		APP_LOGS.debug("Checking Runmode of Action Suite");
				if(!TestUtil.isSuiteRunnable(suiteXls, "PurchaseOrder Suite")){
			System.out.println("in suite runnable function");
			APP_LOGS.debug("Skipped Action Suite Suite as the runmode was set to NO");
			throw new SkipException("Runmode of Action Suite set to no. So Skipping all tests in Action Suite");
			
		}
		
	}
	
	@AfterSuite
	public void checkSuiteClosure()
	{
		closeBrowser();
	}
}
