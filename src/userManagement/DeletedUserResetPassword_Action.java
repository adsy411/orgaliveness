package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class DeletedUserResetPassword_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void DeletedUserResetPassword() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("newreg1719@mailinator.com");
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
		
		Thread.sleep(3000);
		getWebElement("EnterEmailId").sendKeys("devtestmanager1719@amail.com");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys("devtestmanager1719@amail.com");
		Thread.sleep(5000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(5000);
		getWebElement("DeleteUser").click();
		
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
		
		Thread.sleep(3000);
		getWebElement("Enotebook.clicklogin.username").click();
		Thread.sleep(2000);
		getWebElement("ForgotPassword").click();
		Thread.sleep(3000);
		getWebElement("EmailAddress").sendKeys("devtestmanager1719@amail.com");
		Thread.sleep(2000);
		getWebElement("ForgotPasswordCaptchafield").sendKeys("ab");
		Thread.sleep(2000);
		getWebElement("SubmitButton").click();
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("You cannot reset your password as you are not active."));
		
		Thread.sleep(5000);
		driver.quit();
		
		
	}

}
