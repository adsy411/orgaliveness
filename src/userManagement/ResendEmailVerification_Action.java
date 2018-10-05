package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class ResendEmailVerification_Action extends TestBase {
	
	@Test
	public void ResendEmailVerification() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 46);
		
		Reporter.log("Login to Application with specific user3");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("Newuser2202@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();

		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Enter Required fields and Invite");
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys("1735manager@mailinator.com");
		Thread.sleep(1000);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(1000);
		getWebElement("InviteButton").click();
		Thread.sleep(8000);
		getWebElement("ResendEmail").click();
		
		Reporter.log("Open Mailinator in new tab and verify Invite New User Email Template");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		Thread.sleep(3000);
		getWebElement("MailinatorEmailField").sendKeys("1735manager@mailinator.com");
		Thread.sleep(5000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("You have been Invited by"));
		
		Thread.sleep(3000);
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
		
		Reporter.log("Delete the Invited Member");
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(2000);
		getWebElement("DeleteUser").click();
		//driver.findElement(By.id("deleteUserLink:j_idt361")).click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
	}

}
