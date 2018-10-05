package stockroomUserManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class VerifyContactUs_Action extends TestBase{
	
	@Test
	public void VerifyContactUs() throws Exception{
		
		Reporter.log("Login as Stockroom Admin");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on Contact Us from the Side Bar");
		Thread.sleep(3000);
		getWebElement("SideBarContactUs").click();
		
		Reporter.log("Enter Feedback and Click on Submit");
		impliciteWait(5);
		getWebElement("DescribeFeedback").sendKeys("Test Feedback");
		Thread.sleep(1000);
		getWebElement("ContactUsMessage").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
}
