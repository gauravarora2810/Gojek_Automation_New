package test.resources.com.sirion.suite.clientAdmin;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.*;



import test.resources.com.sirion.util.TestUtil;

public class PasswordPolicy extends TestSuiteBase 


{

	
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=true;
	static boolean skip=false;
	static boolean isTestPass=true;
	String ClientAdminPassword;
	String ClientAdminMainPassword;
	
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(client_admin_suite_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case "+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(client_admin_suite_xls, this.getClass().getSimpleName());
	}
	
	@Test (dataProvider="getTestData")
	public void PasswordPolicyTest(String PasswordNeverExpires, String PasswordExpiresInDays, String AdvancePasswordExpiryNotificationInDays, String TemporaryPasswordExpiresInHours, String EnforcePasswordHistory,
			String PastPasswordCount, String MinimumPasswordLength, String PasswordComplexityRequirement, String PasswordAnswerRequirement, String MaximumLoginInvalidAttempts, String UnlockAccount, 
			String LockoutEffectivePeriodInMinutes, String Enforce24HoursAsMinimumLifetimeOfPassword, String MaxFailedSecurityQuestionAttemptsAllowed, String MaximumIdleTimeInDays) throws Throwable
	{
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set -- " +PasswordNeverExpires +" to no " +count);
		}
		
		
		openBrowser();
		driver.manage().window().maximize();
		
		// creating ob performance type
		clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"), CONFIG.getProperty("clientAdminPassword")); 
//		ClientAdminPassword=
//		driver.findElement(By.xpath("")).clear();
//		driver.findElement(By.xpath("")).sendKeys("ted_admin1");
//		driver.findElement(By.xpath("")).clear();
//		driver.findElement(By.xpath("")).sendKeys();
//		driver.findElement(By.xpath("")).click();
		APP_LOGS.debug("Executing Client Admin Password Policy creation test -- " +PasswordNeverExpires);
		//String url_change_password_page="http:"+""+""+"//"+clientAdminURL+""+"/forceChangePasswordForm?_t=1801276022345824&dateFormatToShow=MM-dd-yyyy&dateFormatToPopulate=MM-dd-yyyy";
		if (driver.getCurrentUrl().contains("forceChangePasswordForm"))
		{
			String ClientAdminNewPassword=CONFIG.getProperty("clientAdminPassword")+""+1;
			ClientAdminMainPassword=ClientAdminNewPassword;
			Thread.sleep(5000);
			new Select(driver.findElement(By.xpath(".//*[@id='_questionId_id']"))).selectByVisibleText("What is your nick name?");
			driver.findElement(By.xpath(".//*[@id='_answer_id']")).sendKeys(CONFIG.getProperty("clientAdminUsername"));
			driver.findElement(By.xpath(".//*[@id='_currentPassword_1_id']")).sendKeys(CONFIG.getProperty("clientAdminPassword"));
			driver.findElement(By.xpath(".//*[@id='_newPassword_1_id']")).sendKeys(ClientAdminMainPassword);
			driver.findElement(By.xpath(".//*[@id='_repeatPassword_1_id']")).sendKeys(ClientAdminMainPassword);
			driver.findElement(By.xpath(".//*[@id='proceed']")).click();
			getObject("username").sendKeys(CONFIG.getProperty("clientAdminUsername"));
		    getObject("password").sendKeys(ClientAdminMainPassword);
		    getObject("login_button").click(); 
		   
		}
		try
		{
		if(driver.findElement(By.className("success-icon")).getText().contains("Your password will expire in"))
		{
			
			String ClientAdminNewPassword="Admin>123";
			ClientAdminMainPassword=ClientAdminNewPassword;
			driver.findElement(By.xpath(".//button[contains(.,'Change Password')]")).click();		
		//	driver.findElement(By.xpath(".//button[contains(.,'Remind me later')]")).click();		
			
			driver.findElement(By.xpath(".//*[@value='Change Password']")).click();
			driver.findElement(By.xpath(".//*[@id='_currentPassword_1_id']")).sendKeys(CONFIG.getProperty("clientAdminPassword"));
			driver.findElement(By.xpath(".//*[@id='_newPassword_1_id']")).sendKeys(ClientAdminMainPassword);
			driver.findElement(By.xpath(".//*[@id='_repeatPassword_1_id']")).sendKeys(ClientAdminMainPassword);
			driver.findElement(By.xpath(".//*[@id='proceed']")).click();
			clientAdminLogin(CONFIG.getProperty("clientAdminURL"), CONFIG.getProperty("clientAdminUsername"),ClientAdminMainPassword);
		}
		}
		catch(Exception e)
		{
			
		}
		
		getObject("admin_tab_link").click();
		getObject("ca_ppLink").click();
		driver.findElement(By.xpath(".//*[@id='basicInfo']/div[2]/input")).click();
		
		if(PasswordNeverExpires.equalsIgnoreCase("yes"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordNeverExpires_id']")).click();	
		}
		if(driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).isEnabled())
		{
		driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
		String PasswordExpiresInDays_string =PasswordExpiresInDays;
		
		try 
		{
		   Double PasswordExpiresInDays_double = Double.parseDouble(PasswordExpiresInDays);
           int PasswordExpiresInDays_int = PasswordExpiresInDays_double.intValue();
           PasswordExpiresInDays_string = Integer.toString(PasswordExpiresInDays_int);
           
        
		}   
		catch(Exception e) 
		{
			
		}


		driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys(PasswordExpiresInDays_string);
		driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
			
		
		try
		{
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 1000"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();	
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
			
		}
		
		else if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 7"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();	
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
		}
		
		else if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();	
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
		}
		
		else if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();	
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
		}
		else if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();	
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
		}
		else if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
		{
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();	
			driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
		}
		
		}
		catch(Exception e)
		{
			
		}
		}
		
		if(driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).isEnabled())
		{
			driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
			String AdvancePasswordExpiryNotificationInDays_string =AdvancePasswordExpiryNotificationInDays;
			
			try 
			{
			Double AdvancePasswordExpiryNotificationInDays_double = Double.parseDouble(AdvancePasswordExpiryNotificationInDays);
	        int AdvancePasswordExpiryNotificationInDays_int = AdvancePasswordExpiryNotificationInDays_double.intValue();
	        AdvancePasswordExpiryNotificationInDays_string = Integer.toString(AdvancePasswordExpiryNotificationInDays_int);
			}
			catch(Exception e)
			{
				
			}
			driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys(AdvancePasswordExpiryNotificationInDays_string);
			driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
			try
			{
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 30"))
			{
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 4"))
			{
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
			{
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
			{
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
			{
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
			{
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
			}
		
			}
			catch(Exception e)
			{
				
			}
		
		
		}
		
		driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
		String TemporaryPasswordExpiresInHours_string=TemporaryPasswordExpiresInHours;
		try
		{
		Double TemporaryPasswordExpiresInHours_double = Double.parseDouble(TemporaryPasswordExpiresInHours);
        int TemporaryPasswordExpiresInHours_int = TemporaryPasswordExpiresInHours_double.intValue();
        TemporaryPasswordExpiresInHours_string = Integer.toString(TemporaryPasswordExpiresInHours_int);
		}
		catch(Exception e)
		{
			
		}
        driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys(TemporaryPasswordExpiresInHours_string);
		driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
		try
		{
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 120"))
		{
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys("72");
			}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 4"))
		{
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys("72");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
		{
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys("72");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
		{
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys("72");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
		{
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys("72");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
		{
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_tempPasswordExpiresIn_id']")).sendKeys("72");
		}
		}
		catch(Exception e)
		{
			
		}
		
		
		
		if(EnforcePasswordHistory.equalsIgnoreCase("yes"))
		{
		driver.findElement(By.xpath(".//*[@id='_enforcePasswordHistory_id']")).click();
		
		}
		if(driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).isEnabled())
		{
			driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
			String PastPasswordCount_string=PastPasswordCount;
			try
			{
			Double PastPasswordCount_double = Double.parseDouble(PastPasswordCount);
	        int PastPasswordCount_int = PastPasswordCount_double.intValue();
	        PastPasswordCount_string = Integer.toString(PastPasswordCount_int);
			}
			catch(Exception e)
			{
				
			}
	        driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys(PastPasswordCount_string);
			driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
			try
			{
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 10"))
			{
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys("8");
				}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 5"))
			{
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys("8");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
			{
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys("8");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
			{
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys("8");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
			{
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys("8");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
			{
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_pastPasswordsCount_id']")).sendKeys("8");
			}
			}
			catch(Exception e)
			{
				
			}
		
		}
		driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
		String MinimumPasswordLength_string=MinimumPasswordLength;
		try
		{
		Double MinimumPasswordLength_double = Double.parseDouble(MinimumPasswordLength);
        int MinimumPasswordLength_int = MinimumPasswordLength_double.intValue();
        MinimumPasswordLength_string = Integer.toString(MinimumPasswordLength_int);
		}
		catch(Exception e)
		{
			
		}
        driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys(MinimumPasswordLength_string);
		driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
		try
		{
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 16"))
		{
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys("8");
			}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 8"))
		{
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys("8");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
		{
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys("8");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
		{
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys("8");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
		{
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys("8");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
		{
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_minPasswordLength_id']")).sendKeys("8");
		}
		}
		catch(Exception e)
		{
			
		}
		Thread.sleep(10000);
		Select dropdown_password_Complexity_Requirement=new Select(driver.findElement(By.xpath(".//*[@id='_passwordRegexRule_id']")));
		Thread.sleep(5000);
		dropdown_password_Complexity_Requirement.selectByVisibleText(PasswordComplexityRequirement);
		Select dropdown_password_Answer_Requirement=new Select(driver.findElement(By.xpath(".//*[@id='_securityAnswerConstraintRule_id']")));
		Thread.sleep(5000);
		dropdown_password_Answer_Requirement.selectByIndex(0);
		driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
		String MaximumLoginInvalidAttempts_string=MaximumLoginInvalidAttempts;
		try
		{
		Double MaximumLoginInvalidAttempts_double = Double.parseDouble(MaximumLoginInvalidAttempts);
        int MaximumLoginInvalidAttempts_int = MaximumLoginInvalidAttempts_double.intValue();
        MaximumLoginInvalidAttempts_string = Integer.toString(MaximumLoginInvalidAttempts_int);
		}
		catch(Exception e)
		{
			
		}
        driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys(MaximumLoginInvalidAttempts_string);
		driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
		try
		{
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 20"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys("5");
			}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 3"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys("5");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys("5");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys("5");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys("5");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxInvalidLoginAttempts_id']")).sendKeys("5");
		}
		
		}
		catch(Exception e)
		{
			
		}
		
		if(UnlockAccount.equalsIgnoreCase("yes"))
		{
		driver.findElement(By.xpath(".//*[@id='_unlockAccount_id']")).click();
		
		}
		if(driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).isEnabled())
		{
			driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
			String LockoutEffectivePeriodInMinutes_string=LockoutEffectivePeriodInMinutes;
			try
			{
			Double LockoutEffectivePeriodInMinutes_double = Double.parseDouble(LockoutEffectivePeriodInMinutes);
	        int LockoutEffectivePeriodInMinutes_int =LockoutEffectivePeriodInMinutes_double.intValue();
	        LockoutEffectivePeriodInMinutes_string = Integer.toString(LockoutEffectivePeriodInMinutes_int);
			// or
	        //Integer LockoutEffectivePeriodInMinutes_int=Integer.parseInt(LockoutEffectivePeriodInMinutes);
			//LockoutEffectivePeriodInMinutes_string=LockoutEffectivePeriodInMinutes_int.toString(); 
	        // or
	        //int LockoutEffectivePeriodInMinutes_int=Integer.parseInt(LockoutEffectivePeriodInMinutes);
			//LockoutEffectivePeriodInMinutes_string=Integer.toString(LockoutEffectivePeriodInMinutes_int);
			
			}
			catch(Exception e)
			{
				
			}
	        driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys(LockoutEffectivePeriodInMinutes_string);
			driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
			try
			{
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 60"))
			{
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys("15");
				}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 15"))
			{
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys("15");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
			{
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys("15");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
			{
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys("15");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
			{
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys("15");
			}
			
			if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
			{
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_lockoutEffectivePeriod_id']")).sendKeys("15");
			}
			}
			catch(Exception e)
			{
				
			}
			
			
		}
		driver.findElement(By.xpath(".//*[@id='_enforce24hoursAsMinimumLifetimeOfPassword_id']")).click();
		
		driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
		String MaxFailedSecurityQuestionAttemptsAllowed_string=MaxFailedSecurityQuestionAttemptsAllowed;
		try
		{
		Double MaxFailedSecurityQuestionAttemptsAllowed_double = Double.parseDouble(MaxFailedSecurityQuestionAttemptsAllowed);
        int MaxFailedSecurityQuestionAttemptsAllowed_int = MaxFailedSecurityQuestionAttemptsAllowed_double.intValue();
        MaxFailedSecurityQuestionAttemptsAllowed_string = Integer.toString(MaxFailedSecurityQuestionAttemptsAllowed_int);
		}
		catch(Exception e)
		{
			
		}
        driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys(MaxFailedSecurityQuestionAttemptsAllowed_string);
		driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
		try
		{
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 20"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys("6");
			}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 5"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys("6");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys("6");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys("6");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys("6");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxFailedSecurityQuestionAttemptsAllowed_id']")).sendKeys("6");
		}
		
		}
		catch(Exception e)
		{
			
		}
		
		
		driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
		String MaximumIdleTimeInDays_string=MaximumIdleTimeInDays;
		try
		{
		Double MaximumIdleTimeInDays_double = Double.parseDouble(MaximumIdleTimeInDays);
        int MaximumIdleTimeInDays_int = MaximumIdleTimeInDays_double.intValue();
        MaximumIdleTimeInDays_string = Integer.toString(MaximumIdleTimeInDays_int);
		}
		catch(Exception e)
		{
			
		}
        driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys(MaximumIdleTimeInDays_string);		
		driver.findElement(By.xpath(".//*[@id='contentWrap']")).click();
		try
		{
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Maximum value is 180"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("20");
			}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Minimum value is 7"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("20");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Invalid character"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("20");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only integer values allowed"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("20");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("Only number (Max length 18)"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("20");
		}
		
		if(driver.findElement(By.className("formErrorContent")).getText().equalsIgnoreCase("This field is required"))
		{
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
			driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("20");
		}
		
		}
		catch(Exception e)
		{
			
		}
		driver.findElement(By.xpath(".//*[@id='proceed']")).click();
		
		
		// first big condition
		try
		{
			if(driver.findElement(By.className("alertdialog-icon")).getText().equalsIgnoreCase("'Advanced Password Expiry notification' should always be less than 'Password expires in' value."))
			{
				System.out.println("reached to Advanced Password Expiry notificati");
				driver.findElement(By.xpath(".//button[contains(., 'OK')]")).click();
				driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
				System.out.println("Gaurav Test IT");
				driver.findElement(By.xpath(".//*[@id='proceed']")).click();
				try
				{
				if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
				{
					Thread.sleep(5000);
					new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
					driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
					driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
				}
				}
				catch(Exception e)
				{
					
				}
				try
				{
				if(driver.findElement(By.className("alertdialog-icon")).getText().equalsIgnoreCase("'Advanced Password Expiry notification' should always be less than 'Password expires in' value."))
				{
					driver.findElement(By.xpath(".//button[contains(., 'OK')]")).click();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
					driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
					System.out.println("Gaurav Test IT");
					driver.findElement(By.xpath(".//*[@id='proceed']")).click();
					try
					{
						if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
						{
							Thread.sleep(5000);
							new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
							driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
							driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
						}
					}
						catch(Exception e)
						{

							driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
							driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
							
						}
					
						
					
					
				}
				
				
				}
				catch(Exception e)
				{
					
				}
				
				try
				{
				if(driver.findElement(By.className("alertdialog-icon")).getText().equalsIgnoreCase("'Max Idle Duration (Days)' should always be less than 'Password expires in' value."))
				{
					driver.findElement(By.xpath(".//button[contains(., 'OK')]")).click();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
					driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("25");
					System.out.println("Gaurav Test IT");
					driver.findElement(By.xpath(".//*[@id='proceed']")).click();
					try
					{
						if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
						{
							Thread.sleep(5000);
							new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
							driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
							driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
							
							
						}
						
					}
						catch(Exception e)
						{
							driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
							driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
							
						}
					
						
							
							}
					
					
					
				
				
				}
				catch(Exception e)
				{
					
				}
				
			}
		}
		catch(Exception e)
		{
			
		}
				
			
		
		// Second big condition
		
		
		
		try
		{
			if(driver.findElement(By.className("alertdialog-icon")).getText().equalsIgnoreCase("'Max Idle Duration (Days)' should always be less than 'Password expires in' value."))
			{
				System.out.println("reached to Max Idle Duration (Days)");
				driver.findElement(By.xpath(".//button[contains(., 'OK')]")).click();
				driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
				driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
				driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("25");
				System.out.println("Gaurav Test IT");
				driver.findElement(By.xpath(".//*[@id='proceed']")).click();
				try
				{
				if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
				{
					new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
					driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
					driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
				}
				}
				catch(Exception e)
				{
				}
				
				
				try
				{
				if(driver.findElement(By.className("alertdialog-icon")).getText().equalsIgnoreCase("'Max Idle Duration (Days)' should always be less than 'Password expires in' value."))
				{
					driver.findElement(By.xpath(".//button[contains(., 'OK')]")).click();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
					driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_maxIdleTime_id']")).sendKeys("25");
					System.out.println("Gaurav Test IT");
					driver.findElement(By.xpath(".//*[@id='proceed']")).click();
					try
					{
						if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
						{
							new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
							driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
							driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
						}
						
					}	
						
					
					catch(Exception e)
					{
						driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
						driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
				
					}
				}
				}
				catch(Exception e)
				{
				}
				
				
				try
				{
				if(driver.findElement(By.className("alertdialog-icon")).getText().equalsIgnoreCase("'Advanced Password Expiry notification' should always be less than 'Password expires in' value."))
				{
					driver.findElement(By.xpath(".//button[contains(., 'OK')]")).click();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_passwordExpiresIn_id']")).sendKeys("45");
					driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).clear();
					driver.findElement(By.xpath(".//*[@id='_advancePasswordExpiryNotifications_id']")).sendKeys("30");
					System.out.println("Gaurav Test IT");
					driver.findElement(By.xpath(".//*[@id='proceed']")).click();
					try
					{
						if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
						{
							new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
							driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
							driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
						}
						
						
					}
					catch(Exception e)
					{
						driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
						driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
					
					}
				}
				}
				
				
				
				catch(Exception e)
				{
					driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
					driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
					
				}
			}
				}
				catch(Exception e)
				{
					
				}
			
			try
			{
		
			if(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']")).isDisplayed())
			{
				System.out.println("reached to passwordLengthorRegexChangeRule");
				Thread.sleep(5000);
				new Select(driver.findElement(By.xpath(".//*[@id='_passwordLengthorRegexChangeRule_id']"))).selectByIndex(1);
				driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
				driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
			}
			}
			
			
			
			catch(Exception e)
			{

			}
			
			
			try
			{
				System.out.println("reached to notifyUserOnPasswordPolicyChange");
				driver.findElement(By.xpath(".//*[@id='_notifyUserOnPasswordPolicyChange_id']")).click();
				driver.findElement(By.xpath(".//button[contains(.,'Submit')]")).click();
			}
			catch(Exception e)
			{
				
			}
			
			
			
			
			fail=false;
			
		}
		
	
		
		
		
		
		
	
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=true;
	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(client_admin_suite_xls, "Test Cases", TestUtil.getRowNum(client_admin_suite_xls,this.getClass().getSimpleName()), "FAIL");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(client_admin_suite_xls, this.getClass().getSimpleName()) ;
	}
	
	
	
}
