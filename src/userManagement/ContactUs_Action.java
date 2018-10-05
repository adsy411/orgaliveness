package userManagement;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class ContactUs_Action extends TestBase{

	@Test
	public void ContactUs() throws Exception{
		
		Reporter.log("Open Application");
		init();
		impliciteWait(5);
		
		Reporter.log("Click on Home page Contact Us");
		driver.findElement(By.xpath("//*[@id='navbar']/ul/li[3]/a")).click();
		//getWebElement("HomePageContactUsAuth").click();
		impliciteWait(5);
		
		Reporter.log("Enter the required fields and Click on Send");
		getWebElement("ContactUsEnterName").sendKeys("Test");
		Thread.sleep(2000);
		getWebElement("ContactUsEmailAddress").sendKeys("devtest03april@mailinator.com");
		getWebElement("ContactUsMessage").sendKeys("Test Message");
		Thread.sleep(3000);
		getWebElement("ContactUsSendButton").click();
		Thread.sleep(2000);
		getWebElement("CloseAuthentication").click();
		
		Reporter.log("Click on Login page Contact Us");
		Thread.sleep(3000);
		getWebElement("Enotebook.clicklogin.username").click();
		//getWebElement("HomePageLoginButton").click();
		impliciteWait(5);
		getWebElement("LoginPageContactUs").click();
		
		Reporter.log("Enter the required fields and Click on Send again");
		getWebElement("ContactUsEnterName").sendKeys("Test");
		Thread.sleep(2000);
		getWebElement("ContactUsEmailAddress").sendKeys("devtest03april@mailinator.com");
		getWebElement("ContactUsMessage").sendKeys("Test Message");
		Thread.sleep(3000);
		getWebElement("ContactUsSendButton").click();
		Thread.sleep(2000);
		getWebElement("CloseAuthentication").click();
		
		Reporter.log("Login to Application and click on Contact Us");
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys("devtest03april@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
		getWebElement("DashboardContactUs").click();
		
		Reporter.log("Enter the Feedback and click on Submit Feedback");
		impliciteWait(5);
		getWebElement("DescribeFeedback").sendKeys("Test Message");
		getWebElement("SendFeedbackButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}

}
