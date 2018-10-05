package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ChangePassword_Action extends TestBase{

	@Test
	public void ChangePassword() throws Exception{
		
		Reporter.log("Login to Application with Specific User One");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtest2218@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Click on Profile Settings and Select Change Password");
		getWebElement("ProfileSettings").click();
		getWebElement("ChangePassword").click();
		
		Reporter.log("Enter the required field and click on Save");
		Thread.sleep(2000);
		getWebElement("CurrentPassword").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("NewPassword").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
		
		Thread.sleep(2000);
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		getWebElement("Logout").click();
		
		Reporter.log("Verify the Password change by logging to Application");
		Thread.sleep(2000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtest2218@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Revert Previous Password");
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		Thread.sleep(2000);
		getWebElement("ProfileSettings").click();
		Thread.sleep(2000);
		getWebElement("ChangePassword").click();
		Thread.sleep(2000);
		getWebElement("CurrentPassword").sendKeys("admin12345");
		Thread.sleep(1000);
		getWebElement("NewPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin123");
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
