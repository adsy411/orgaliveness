package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class UnregisteredUserDeletedInAllLabs_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void UnregisteredUserPendingInAllLabs() throws Exception{
		
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

		Reporter.log("Delete Lab Manager in Pending Status");
		Thread.sleep(2000);
		getWebElement("LabManagerDeleteinPending").click();
		Thread.sleep(1000);
		getWebElement("DeleteUser").click();
		
		Reporter.log("Logout from Application");
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(3000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Login to Application as Lab Manager");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("manager1507@amail.club");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Verify the Validation message for deleted Lab Manager");
		Assert.assertTrue(driver.getPageSource().contains("User is deleted from owner's lab. Please request for registration"));
		
		Reporter.log("Close Browser");
		Thread.sleep(5000);
		driver.quit();
		
		
		
		
	}
}
