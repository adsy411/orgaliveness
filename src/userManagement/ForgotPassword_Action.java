package userManagement;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class ForgotPassword_Action extends TestBase{
	
	@SuppressWarnings("deprecation")
	@Test
	public void ForgotPassword() throws Exception{
		
		Reporter.log("Click on Login page -> Forgot Password");
		init();
		Thread.sleep(3000);
		getWebElement("Enotebook.clicklogin.username").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
		
		Reporter.log("Enter Email Address and Click on Submit then verify the information message in Login page");
		impliciteWait(5);
		getWebElement("EmailAddress").sendKeys("devtest03april@mailinator.com");
		Thread.sleep(2000);
		getWebElement("ForgotPasswordCaptchafield").sendKeys("ab");
		Thread.sleep(2000);
		getWebElement("SubmitButton").click();
		
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Password reset instructions has sent to the email id that you provided. You will receive an email shortly for the same."));
		
		Thread.sleep(5000);
		driver.quit();
		
	}
}
