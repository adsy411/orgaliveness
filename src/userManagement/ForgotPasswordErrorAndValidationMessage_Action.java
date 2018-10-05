package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class ForgotPasswordErrorAndValidationMessage_Action extends TestBase{
	
	@Test
	public void ForgotPasswordErrorAndValidationMessage() throws Exception{
		
		Reporter.log("Click on Login page -> Forgot Password");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		Reporter.log("Enter Invalid Email ID and Password then Click Signin");
		getWebElement("Enotebook.login.username").sendKeys("123@login.com");
		getWebElement("Enotebook.login.password").sendKeys("admin");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
		
		Reporter.log("Enter Invalid Email Address and Click on Submit then verify the message");
		impliciteWait(5);
		getWebElement("EmailAddress").sendKeys("123login.com");
		getWebElement("SubmitButton").click();
		
		String Message  = getWebElement("ForgotPasswordInvalidEmailIDMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='resetForm:forgotpass-error']", Message);
		
		Thread.sleep(2000);
		getWebElement("SubmitButton").click();
		
		Thread.sleep(5000);
		String Message1  = getWebElement("ForgotPasswordCaptchaRequiredMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='resetForm:txtCaptchaId-error']", Message1);
		
		Reporter.log("Close the Browser");
		Thread.sleep(2000);
		driver.quit();

		
		
		
	}
}
