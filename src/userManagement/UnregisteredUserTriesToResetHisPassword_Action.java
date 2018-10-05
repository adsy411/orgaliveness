package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class UnregisteredUserTriesToResetHisPassword_Action extends TestBase {

	@SuppressWarnings("deprecation")
	@Test
	public void UnregisteredUserTriesToResetHisPassword() throws Exception{
		
		Reporter.log("Click on Forgot Password from Login page");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
		
		Reporter.log("Enter Email Id and click on Submit");
		Thread.sleep(2000);
		getWebElement("EmailAddress").sendKeys("loxid1@amail.club");
		Thread.sleep(1000);
		getWebElement("SubmitButton").click();
		
		Reporter.log("Verify the Validation message for Unregistered user tries to reset the Password");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getPageSource().contains("Please enter valid email id."));
		
		Reporter.log("Close Browser");
		Thread.sleep(5000);
		driver.quit();
	
	}
}
