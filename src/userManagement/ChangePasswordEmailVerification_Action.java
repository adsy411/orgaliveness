package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ChangePasswordEmailVerification_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void ChangePassword() throws Exception{
		
		init();
		Reporter.log("Login to Application");
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Click on Profile Settings and Select Change Password");
		getWebElement("ProfileSettings").click();
		getWebElement("ChangePassword").click();
		
		Reporter.log("Enter the required field and click on Save");
		getWebElement("CurrentPassword").sendKeys("admin123");
		getWebElement("NewPassword").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin12345");
		Thread.sleep(2000);
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
		
		Reporter.log("Open Mailinator in new tab and verify Change Password Email Template");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		Thread.sleep(3000);
		getWebElement("MailinatorEmailField").sendKeys("devtestjan14@mailinator.com");
		Thread.sleep(5000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Your password has been changed"));
		
		Thread.sleep(5000);
		driver.switchTo().window(tabs.get(0));
		
		Thread.sleep(3000);
		Reporter.log("Click on Profile Settings and Select Change Password");
		getWebElement("ProfileSettings").click();
		
		Reporter.log("Enter the required field and click on Save");
		getWebElement("ChangePassword").click();
		getWebElement("CurrentPassword").sendKeys("admin12345");
		getWebElement("NewPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("ConfirmPassword").sendKeys("admin123");
		getWebElement("SavePassword").click();
		Thread.sleep(10000);
		getWebElement("ChangePasswordCancelButton").click();
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
		
	}

}
