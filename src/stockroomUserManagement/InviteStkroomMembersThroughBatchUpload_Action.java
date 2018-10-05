package stockroomUserManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class InviteStkroomMembersThroughBatchUpload_Action extends TestBase{
	
	@Test
	public void InviteStkroomMembersThroughBatchUpload() throws Exception{
		
		Reporter.log("Login as MSAdmin");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys("23Jan");
		Thread.sleep(3000);
		getWebElement("SelectMemberList").click();
		impliciteWait(5);
		getWebElement("StkroomAdminAddNewMembers").click();

		Reporter.log("Click Batchupload and upload the Lab Members");
		Thread.sleep(2000);
		getWebElement("BatchUpload").click();
		Thread.sleep(3000);
		getWebElement("FileUpload").click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\dprakash\\Desktop\\Files\\BatchUpload\\StkroomMembersBatchUpload.exe");
		Thread.sleep(8000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(3000);
		getWebElement("LabMemberDeleteIcon1").click();
		Thread.sleep(3000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
		
		
		
	}
}
