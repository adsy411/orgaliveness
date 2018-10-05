package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class UserActivatedEmailVerification_Action extends TestBase{
	
	@SuppressWarnings("deprecation")
	@Test
	public void UserActivatedEmailVerification() throws Exception{
		
		Reporter.log("Login to Application with specific user4");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("Newuser2201@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Change the Member Status to Inactive then change to Active again");
		Thread.sleep(2000);
		getWebElement("StockroomUserStatusChange").click();
		Thread.sleep(5000);
		getWebElement("StockroomUserStatusChange").click();
		
		Reporter.log("Open Mailinator in new tab and verify User Activated Email Template");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("2201manager@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Your account is approved"));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
	}

}
