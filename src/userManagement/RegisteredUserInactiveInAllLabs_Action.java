package userManagement;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class RegisteredUserInactiveInAllLabs_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void RegisteredUserInactiveInAllLabs() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Change the Status of the Lab Manager to Inactive");
		Thread.sleep(3000);
		getWebElement("LabManagerStatusChange").click();
		String EmailID = driver.findElement(By.xpath("//*[@id='memberForm:memberTable_data']/tr[2]/td[2]/span")).getText();
		
		Reporter.log("Verify the Lab Name and Logout");
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		String LabName = driver.findElement(By.id("labNameInHeader")).getText();
		Thread.sleep(3000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Login as Lab Manager where the status is Inactive");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(EmailID);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Verify the Validation message for Inactive Lab Manager");
		Assert.assertTrue(driver.getPageSource().contains("Your lab does not exist. Do you want to create a new lab?"));
		
		Reporter.log("Navigate Backword and Login as Lab Owner");
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Change the Status of the Lab Manager to Active");
		Thread.sleep(3000);
		getWebElement("LabManagerStatusChange").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
		
	}
}
