package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class LabMembersErrorAndValidationMessage_Action extends TestBase{
	
	@Test
	public void LabMembersErrorAndValidationMessage() throws Exception{
		
		init();
		Reporter.log("Login to Application");
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Click Add New Member");
		Thread.sleep(1000);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Verify Validation and Error messages in Add New Member");
		Thread.sleep(3000);
		getWebElement("InviteButton").click();
		Thread.sleep(2000);
		String Message  = getWebElement("AddMemberInviteValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", Message);

		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("123@");
		Thread.sleep(1000);
		getWebElement("InviteButton").click();
		
		Thread.sleep(2000);
		String Message1  = getWebElement("AddMemberInviteErrorMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='addMemberForm:emailList:0:emailerror']", Message1);
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
	}
}
