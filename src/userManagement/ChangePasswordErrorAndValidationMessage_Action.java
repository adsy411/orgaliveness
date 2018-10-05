package userManagement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ChangePasswordErrorAndValidationMessage_Action extends TestBase{
	
	@Test
	public void ChangePasswordErrorAndValidationMessage() throws Exception{
		
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Click on Profile Settings and Select Change Password");
		getWebElement("ProfileSettings").click();
		getWebElement("ChangePassword").click();
		
		Reporter.log("Click on Save and Verify Validation and Error messages in Change Password popup");
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		
		Thread.sleep(2000);
		String Message  = getWebElement("ChangePasswordCurrPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:currentPwd-error']", Message);
		
		Thread.sleep(2000);
		String Message1  = getWebElement("ChangePasswordNewPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:newPwd-error']", Message1);
		
		Thread.sleep(2000);
		String Message2  = getWebElement("ChangePasswordConPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:repeatPwd-error']", Message2);
		
		Thread.sleep(2000);
		getWebElement("NewPassword").sendKeys("123");
		
		Thread.sleep(2000);
		String Message3  = getWebElement("ChangePasswordNewPassValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='change-password-form:newPwd-error']", Message3);
		
		getWebElement("ChangePasswordCancelButton").click();
		
		Thread.sleep(2000);
		Reporter.log("Click on Profile Settings and Select Change Password");
		getWebElement("ProfileSettings").click();
		getWebElement("ChangePassword").click();
		
		Thread.sleep(1000);
		getWebElement("CurrentPassword").sendKeys("asmin123");
		Thread.sleep(1000);
		getWebElement("NewPassword").sendKeys("admin12345");
		Thread.sleep(1000);
		getWebElement("ConfirmPassword").sendKeys("admin12345");
		Thread.sleep(1000);
		getWebElement("SavePassword").click();
		Thread.sleep(8000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='change-password-form']/div/div[2]/div/div/div/ul/li[2]")).isDisplayed());
		//Assert.assertTrue(driver.getPageSource().contains("Warning!"));
		
		Thread.sleep(15000);
		getWebElement("CurrentPassword").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("NewPassword").sendKeys("asmin12345");
		Thread.sleep(1000);
		getWebElement("ConfirmPassword").sendKeys("afmin12345");
		Thread.sleep(1000);
		getWebElement("SavePassword").click();
		//Assert.assertEquals(actual, expected);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='change-password-form']/div/div[2]/div/div/div/ul/li[2]")).isDisplayed());
		Thread.sleep(1000);
		getWebElement("ChangePasswordCancelButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
	}
}
