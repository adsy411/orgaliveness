package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class EditNameValidationMessage_Action extends TestBase{
	
	@Test
    public void EditNameValidationMessage() throws Exception {
    	
		init();
		Reporter.log("Login to Application");
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		impliciteWait(5);
		getWebElement("UserProfile").click();
    	
		Reporter.log("Clear First and Last Name field and click on Save then Verify Validation message");
		Thread.sleep(5000);
    	getWebElement("EditName").click();
    	Thread.sleep(3000);
    	getWebElement("UserEditFirstName").clear();
    	impliciteWait(5);
    	getWebElement("UserEditLastName").clear();
    	impliciteWait(5);
    	getWebElement("EditNameSave").click();
    	
    	Thread.sleep(2000);
    	String Message  = getWebElement("EditNameFirstNameValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='fTitleError']", Message);
		
		Reporter.log("Enter some data on First Name and Click on Save then Verify Validation message");
		Thread.sleep(2000);
		getWebElement("UserEditFirstName").sendKeys("123");
		Thread.sleep(1000);
		getWebElement("EditNameSave").click();
		
		Thread.sleep(2000);
    	String Message1  = getWebElement("EditNameLastNameValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='lTitleError']", Message1);
		
		Reporter.log("Click on Cancel in Edit Username popup");
		Thread.sleep(2000);
		getWebElement("EditNameCancelButton").click();
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
    		
	}
}
