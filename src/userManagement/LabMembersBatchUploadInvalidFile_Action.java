package userManagement;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabMembersBatchUploadInvalidFile_Action extends TestBase{
	
	@Test
	public void LabMembersBatchUploadInvalidFile() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 4);
		String FirstName = ExcelUtils.getCellData(1, 5);
		String LastName = ExcelUtils.getCellData(1, 6);
		
		Reporter.log("Login to Application with specific user2");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("09jan@mailinator.com");
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
		
		Reporter.log("Click Batchupload and upload Invalid File then verify the Error message");
		Thread.sleep(2000);
		getWebElement("BatchUpload").click();
		Thread.sleep(3000);
		getWebElement("FileUpload").click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\dprakash\\Desktop\\Files\\LabMembersBatchUploadInvalidFile.exe");

		Thread.sleep(3000);
		String Message  = getWebElement("InvalidFileTypeMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='addFileModal']/div/div[1]/div/div/ul/li[1]", Message);
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
			
	}
}
