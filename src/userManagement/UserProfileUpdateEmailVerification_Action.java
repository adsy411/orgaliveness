package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class UserProfileUpdateEmailVerification_Action extends TestBase{
	
	@SuppressWarnings("deprecation")
	@Test
	public void UserProfileUpdateEmailVerification() throws Exception{
		
		Reporter.log("Login to Application with specific user4");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("Newuser2201@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		Thread.sleep(1000);
		getWebElement("UserProfile").click();
		
		Reporter.log("Edit the First Name and Save");
		Thread.sleep(5000);
		getWebElement("EditName").click();
		Thread.sleep(3000);
		getWebElement("UserEditFirstName").sendKeys("1");
		Thread.sleep(1000);
		getWebElement("SaveEditLabName").click();
		
		Reporter.log("Open Mailinator in new tab and verify User Profile Update Email Template");
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
		Assert.assertTrue(driver.getPageSource().contains(" User Profile has been Updated "));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
		
		Reporter.log("Click on User Settings and go to My Profile");
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		Thread.sleep(1000);
		getWebElement("UserProfile").click();
		
		Reporter.log("Edit the First Name same as before and Save");
		Thread.sleep(5000);
		getWebElement("EditName").click();
		Thread.sleep(3000);
		getWebElement("UserEditFirstName").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		getWebElement("SaveEditLabName").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}

}
