package userManagement;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class DeleteMemberAndInviteFromOwner_Action extends TestBase{
	
	@Test
	public void DeleteMemberAndInviteFromOwner() throws Exception{

		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String LastName = ExcelUtils.getCellData(1, 6);
		
		Reporter.log("Login to Application with specific User Two");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestmsadmin1dec@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();

		Reporter.log("Delete Lab Member then Click Add New Member and Enter the deleted mail ID");
		impliciteWait(5);
		getWebElement("LabMembersSearch").sendKeys(LastName);
		Thread.sleep(2000);
		String mailid = getWebElement("EmailTitle").getText();
		Thread.sleep(4000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(5000);
		getWebElement("DeleteUser").click();
		Thread.sleep(3000);
		getWebElement("AddNewMembersButton").click();
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys(mailid);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Click on the Pending Status to resend the email verification");
		Thread.sleep(3000);
		getWebElement("PendingStatus").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
	}
}
