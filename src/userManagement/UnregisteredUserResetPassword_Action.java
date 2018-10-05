package userManagement;

import junit.framework.Assert;

import org.testng.annotations.Test;

import testBase.TestBase;

public class UnregisteredUserResetPassword_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void UnregisteredUserResetPassword() throws Exception{

		init();
		getWebElement("Enotebook.clicklogin.username").click();
		Thread.sleep(2000);
		getWebElement("ForgotPassword").click();
		Thread.sleep(3000);
		getWebElement("EmailAddress").sendKeys("manager17113123@amail.club");
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
