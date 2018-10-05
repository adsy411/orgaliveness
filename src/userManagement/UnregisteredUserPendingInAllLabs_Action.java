package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import testBase.TestBase;

public class UnregisteredUserPendingInAllLabs_Action extends TestBase {
	
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
		
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Enter Email ID and Role then click on Invite");
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("manager1507@amail.club");
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
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
		
		Reporter.log("Verify the Validation message for Lab Manager in Pending Status");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getPageSource().contains("User is not allowed to login before email confirmation."));
		
		Reporter.log("Close Browser");
		Thread.sleep(5000);
		driver.quit();
		
		
	}
}
