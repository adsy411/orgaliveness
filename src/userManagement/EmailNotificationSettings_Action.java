package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class EmailNotificationSettings_Action extends TestBase{
	
	@Test
	public void EmailNotificationSettings() throws Exception{
		
		init();
		Reporter.log("Login to Application");
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Click on Profile Settings");
		Thread.sleep(3000);
		getWebElement("ProfileSettings").click();
		
		Reporter.log("Uncheck all the Notification and click on Save");
		getWebElement("EmailNotificationSettings").click();
		Thread.sleep(3000);
		getWebElement("ProdReqStaUpdate").click();
		getWebElement("ResearchPermissions").click();
		getWebElement("ResearchRevSub").click();
		getWebElement("UserProUpdate").click();
		getWebElement("DisposedMatStaUpdate").click();
		Thread.sleep(3000);
		getWebElement("EmailNotiSetSave").click();
		Thread.sleep(5000);
		getWebElement("EmailNotiSetCancel").click();
		
		Reporter.log("Click on Profile Settings");
		Thread.sleep(3000);
		getWebElement("ProfileSettings").click();
		
		Reporter.log("Check all the Notification and click on Save");
		getWebElement("EmailNotificationSettings").click();
		Thread.sleep(3000);
		getWebElement("ProdReqStaUpdate").click();
		getWebElement("ResearchPermissions").click();
		getWebElement("ResearchRevSub").click();
		getWebElement("UserProUpdate").click();
		getWebElement("DisposedMatStaUpdate").click();
		Thread.sleep(3000);
		getWebElement("EmailNotiSetSave").click();
		Thread.sleep(5000);
		getWebElement("EmailNotiSetCancel").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
	}
}
