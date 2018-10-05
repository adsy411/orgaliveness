package userManagement;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabManagerInvitationAsLabOwner_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void LabManagerInvitationAsLabOwner() throws Exception{
		
		Reporter.log("Login to Application with specific user Three");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("dt03aprilman@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		getWebElement("SwitchContext").click();
		
		Thread.sleep(3000);
		getWebElement("CreateNewLab").click();
		Thread.sleep(2000);
		getWebElement("CreateNewLabRequestButton").click();
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("Your request has been submitted and our customer support team will contact you soon."));
		Thread.sleep(2000);
		getWebElement("NewLabSuccessfulConfirmationOkButton").click();
		Thread.sleep(2000);
		getWebElement("SwitchContextCloseSymbol").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
