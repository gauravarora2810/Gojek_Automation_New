package test.resources.com.sirion.suite.common.listings;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.resources.com.sirion.util.TestUtil;
import testlink.api.java.client.TestLinkAPIException;

public class ListingOpen extends TestSuiteBase {
	String result = null;
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	static WebDriverWait wait;

	@BeforeTest
	public void checkTestSkip() {
		if(!TestUtil.isTestCaseRunnable(common_listing_suite_xls,this.getClass().getSimpleName())) {
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as Runmode set to NO");
			}
		runmodes = TestUtil.getDataSetRunmodes(common_listing_suite_xls, this.getClass().getSimpleName());
		}
	
	@Test (dataProvider="getTestData")
	public void ListingOpenTest (String testCaseId, String entityName, String entityListingName) throws InterruptedException, TestLinkAPIException {
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")) {
			skip=true;
			throw new SkipException("Runmode for Test Data -- " +entityName +" set to NO " +count);
			}

		openBrowser();
		endUserLogin(CONFIG.getProperty("endUserURL"), CONFIG.getProperty("endUserUsername"), CONFIG.getProperty("endUserPassword"));		
		APP_LOGS.debug("Executing Common Listing Open Test -- "+entityName);
		wait = new WebDriverWait(driver, 60);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Analytics"))));

		driver.findElement(By.linkText("Analytics")).click();
		Thread.sleep(5000);

		if (entityName.equalsIgnoreCase("Vendor Hierarchy")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("vh_quick_link")));
			getObject("vh_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Supplier")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("suppliers_quick_link")));
			getObject("suppliers_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Contract")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("contracts_quick_link")));
			getObject("contracts_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Purchase Order")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("po_quick_link")));
			getObject("po_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Obligation")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("ob_quick_link")));
			getObject("ob_quick_link").click();

			driver.findElement(By.linkText("View Obligations")).click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}
		
		else if (entityName.equalsIgnoreCase("Child Obligation")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("ob_quick_link")));
			getObject("ob_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Service Level")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("sl_quick_link")));
			getObject("sl_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Child Service Level")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("sl_quick_link")));
			getObject("sl_quick_link").click();
			
			driver.findElement(By.linkText("View Child Service Levels")).click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Action")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("actions_quick_link")));
			getObject("actions_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Issue")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("issues_quick_link")));
			getObject("issues_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Invoice")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("inv_quick_link")));
			getObject("inv_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Change Request")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("cr_quick_link")));
			getObject("cr_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Interpretaion")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("ip_quick_link")));
			getObject("ip_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Work Order Request")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("wor_quick_link")));
			getObject("wor_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Governance Body")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("gb_quick_link")));
			getObject("gb_quick_link").click();
			
			driver.findElement(By.linkText("VIEW GOVERNANCE BODIES")).click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Governance Body Meeting")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("gb_quick_link")));
			getObject("gb_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}

		else if (entityName.equalsIgnoreCase("Contract Draft Request")) {
			wait.until(ExpectedConditions.elementToBeClickable(getObject("cdr_quick_link")));
			getObject("cdr_quick_link").click();
			Thread.sleep(5000);
/*			
			if (driver.findElements(By.id("exportXL")).size()>0) {
				driver.findElement(By.id("exportXL")).click();

				if (driver.findElements(By.id("alertdialog")).size()>0) {
					Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(.,'OK')]")));
					driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
					Thread.sleep(5000);
					}
				}
*/			}
	    Thread.sleep(5000);

	    String listingNameShowPage = getObject("entity_listing_page_name").getText();
	    Assert.assertEquals(listingNameShowPage, entityListingName, "Listing name is -- " +listingNameShowPage+ " instead of -- "+entityListingName);

		fail = false;
/*
		if (!fail)
			result= TestLinkAPIResults.TEST_PASSED;
	      else   
	    	  result= TestLinkAPIResults.TEST_FAILED;
	      TestlinkIntegration.updateTestLinkResult(testCaseId,"",result);
*/	      
	      driver.get(CONFIG.getProperty("endUserURL"));
	      }

	@AfterMethod
	public void reportDataSetResult() {
		if(skip)
			TestUtil.reportDataSetResult(common_listing_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail) {
			isTestPass=false;
			TestUtil.reportDataSetResult(common_listing_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
			}
		else
			TestUtil.reportDataSetResult(common_listing_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
		}
	
	@AfterTest
	public void reportTestResult() {
		if(isTestPass)
			TestUtil.reportDataSetResult(common_listing_suite_xls, "Test Cases", TestUtil.getRowNum(common_listing_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(common_listing_suite_xls, "Test Cases", TestUtil.getRowNum(common_listing_suite_xls,this.getClass().getSimpleName()), "FAIL");
		}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(common_listing_suite_xls, this.getClass().getSimpleName());
		}
	}