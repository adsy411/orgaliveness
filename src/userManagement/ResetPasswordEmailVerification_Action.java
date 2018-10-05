package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class ResetPasswordEmailVerification_Action extends TestBase{
	
	@SuppressWarnings("deprecation")
	@Test
	public void ResetPasswordEmailVerification() throws Exception{
	
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");
	
		String NewPassword = ExcelUtils.getCellData(1, 19);
		String ConfirmPassword = ExcelUtils.getCellData(1, 20);
	
		/*Reporter.log("Login to Application");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(sUserName);
		getWebElement("Enotebook.login.password").sendKeys(sPassword);
		getWebElement("Enotebook.login.loginButton").click();*/

		Reporter.log("Click on Forgot Password link");
		init();
		impliciteWait(5);
		getWebElement("HomePageLoginButton").click();
		Thread.sleep(3000);
		getWebElement("ForgotPassword").click();
		
		Reporter.log("Enter New Email Address and Click on Submit");
		impliciteWait(5);
		getWebElement("EmailAddress").sendKeys("Newuser2201@mailinator.com");
		getWebElement("SubmitButton").click();
		
		Reporter.log("Open Mailinator in new tab and verify Reset Password Email Template then Reset Password and login");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("Newuser2201@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("To reset your password, click on the button"));
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("For your security, click the link below within 24 hours to reset your password. If the link expires, just reset it again."));
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("If you don't want to reset your password, you can ignore this message - someone probably typed in your email address by mistake."));
		Thread.sleep(3000);
		driver.findElement(By.linkText("Reset password")).click();
		Thread.sleep(3000);
		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		Thread.sleep(3000);
		getWebElement("ResetPasswordNewPassword").sendKeys(NewPassword);
		Thread.sleep(1000);
		getWebElement("ResetPasswordConfirmPassword").sendKeys(ConfirmPassword);
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		driver.getPageSource().contains("Your password changed successfully. Please try to log in.");
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys("Newuser2201@mailinator.com");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys(NewPassword);
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Click on Profile Settings and Select Change Password");
		getWebElement("ProfileSettings").click();
		getWebElement("ChangePassword").click();
		
		Reporter.log("Enter the required field in Change Password popup and click on Save");
		getWebElement("CurrentPassword").sendKeys("admin1234");
		Thread.sleep(2000);
		getWebElement("NewPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
