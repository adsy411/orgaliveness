package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class ExistingMemberInvitation_Action extends TestBase{
	
	@Test
	public void ExistingMemberInvitation() throws Exception{
		
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Invite existing Manager and Verify the alert message");
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("dt03aprilman@mailinator.com");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", "LogonId already exists");
		Thread.sleep(2000);
		getWebElement("CancelButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Invite existing Member and Verify the alert message");
		Thread.sleep(5000);
		getWebElement("AddNewMembersButton").click();
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("dt03aprilmem@mailinator.com");
		Thread.sleep(2000);
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", "LogonId already exists");
		Thread.sleep(2000);
		getWebElement("CancelButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
	}
}
