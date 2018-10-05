package userManagement;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabManagerOnboardingPage_Action extends TestBase {
	
	@Test
	public void LabManagerOnboardingPage() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to Application as Lab Manager");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(MailIdCreation+"@amail.club");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Update First and Last Name from Setup Your Profile Tab then click Tutorials Tips Tab");
		Thread.sleep(5000);
		getWebElement("SetupYourProfileFirstName").clear();
		getWebElement("SetupYourProfileFirstName").sendKeys("Devtest1");
		Thread.sleep(1000);
		getWebElement("SetupYourProfileLastName").clear();
		getWebElement("SetupYourProfileLastName").sendKeys(MailIdCreation+"1");
		Thread.sleep(1000);
		getWebElement("InviteCollaboratorsButton1").click();
		
		Reporter.log("Reset First and Last Name from Setup Your Profile Tab then click Tutorials Tips Tab");
		Thread.sleep(10000);
		getWebElement("ConfigureYourLabButton1").click();
		Thread.sleep(1000);
		getWebElement("SetupYourProfileFirstName").clear();
		getWebElement("SetupYourProfileFirstName").sendKeys("Devtest");
		Thread.sleep(1000);
		getWebElement("SetupYourProfileLastName").clear();
		getWebElement("SetupYourProfileLastName").sendKeys(MailIdCreation);
		Thread.sleep(1000);
		getWebElement("InviteCollaboratorsButton1").click();
		
//		Reporter.log("Navigate to Setup Your Profile Tab");
//		Thread.sleep(2000);
//		getWebElement("SetupYourProfileTab").click();
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
