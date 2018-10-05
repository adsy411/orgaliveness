package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class EditLabValidationMessage_Action extends TestBase{
	
	@Test
	public void EditLabValidationMessage() throws Exception{
		
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to Lab Settings");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabSettings").click();
		
		Reporter.log("Clear Labname and Click on Save then verify Validation message");
		impliciteWait(5);
		getWebElement("EditLabNameicon").click();
		impliciteWait(5);
		getWebElement("EditLabName").clear();
		Thread.sleep(2000);
		getWebElement("SaveEditLabName").click();

		Thread.sleep(2000);
		String Message  = getWebElement("EditLabValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='labTitleError']", Message);
		
		Reporter.log("Click on Cancel in Edit Lab popup");
		Thread.sleep(2000);
		getWebElement("EditLabCloseButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
		
		
	}
}
