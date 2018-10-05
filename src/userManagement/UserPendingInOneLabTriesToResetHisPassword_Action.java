package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import testBase.TestBase;

public class UserPendingInOneLabTriesToResetHisPassword_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void UserPendingInOneLabTriesToResetHisPassword() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Enter Email ID and Role then click on Invite");
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("manager1607@amail.club");
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Logout from Application");
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(3000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Click Forgot Password from Login page");
		getWebElement("Enotebook.clicklogin.username").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
		
		Reporter.log("Enter Email Address and click on Submit");
		Thread.sleep(2000);
		getWebElement("EmailAddress").sendKeys("manager1607@amail.club");
		Thread.sleep(1000);
		getWebElement("SubmitButton").click();
		
		Reporter.log("Verify the Validation message for user pending in one Lab and tries to reset the Password");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getPageSource().contains("You cannot reset your password before validating your lab registration email."));
		
		Reporter.log("Close Browser");
		Thread.sleep(5000);
		driver.quit();
		
		
		
	}
}
