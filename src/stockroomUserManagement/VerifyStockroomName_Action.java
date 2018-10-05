package stockroomUserManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class VerifyStockroomName_Action extends TestBase{
	
	@Test
	public void VerifyStockroomName() throws Exception{
		
		Reporter.log("Login as Stockroom Admin");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on Searched Stockroom");
		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys("23Jan");
		Thread.sleep(1000);
		getWebElement("FirstStockroomNameclick").click();
		
		Reporter.log("Navigate to Stockroom list from the Side Bar");
		Thread.sleep(3000);
		getWebElement("SideBarStkroomList").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
