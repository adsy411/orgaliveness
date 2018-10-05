package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class UserDeletedInAllLabsTriesToResetHisPassword_Action extends TestBase {
	
	@Test
	public void UserDeletedInAllLabsTriesToResetHisPassword() throws Exception{
		
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

		Thread.sleep(2000);
		getWebElement("LabManagerDeleteinPending").click();
		Thread.sleep(1000);
		getWebElement("DeleteUser").click();
		
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(3000);
		getWebElement("Enotebook.logout").click();

		getWebElement("Enotebook.clicklogin.username").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
		Thread.sleep(2000);
		getWebElement("EmailAddress").sendKeys("manager1607@amail.club");
		Thread.sleep(1000);
		getWebElement("SubmitButton").click();
		
		Thread.sleep(2000);
		//Assert.assertTrue(driver.getPageSource().contains("You cannot reset your password before validating your lab registration email."));
		
		Thread.sleep(5000);
		//driver.quit();
		
		
		
	}
}
