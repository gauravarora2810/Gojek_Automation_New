package test.resources.com.sirion.suite.sirionUserAdmin;

import test.resources.com.sirion.util.FullScreenCapture;

public class ClientAdminlogin extends ClientAdminCreation
{
	public void userlogin() throws InterruptedException{
		
	openBrowser();
	
	Thread.sleep(3000);
	
	endUserLogin(CONFIG.getProperty("enduserURL"),super.adminloginid, CONFIG.getProperty("enduserPassword"));
	
	
	System.out.println("   "+"Yess User's Home page is now visible");
		
		FullScreenCapture homepage = new FullScreenCapture();
		
		homepage.screenshot(super.adminloginid);
		
		getObject("sua_logout").click();
		

}
}
