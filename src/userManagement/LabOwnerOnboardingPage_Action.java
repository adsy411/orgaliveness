package userManagement;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class LabOwnerOnboardingPage_Action extends TestBase {
	
	@Test
	public void LabOwnerOnboardingPage() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("newreg1716@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Update First and Last Name from Setup Your Profile Tab then click Configure Your Lab Tab");
		Thread.sleep(5000);
		getWebElement("SetupYourProfileFirstName").clear();
		getWebElement("SetupYourProfileFirstName").sendKeys("Devtest1");
		Thread.sleep(1000);
		getWebElement("SetupYourProfileLastName").clear();
		getWebElement("SetupYourProfileLastName").sendKeys("Newreg17161");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabButton").click();
		
		Reporter.log("Update the required fields in Configure Your Lab Tab and click Tutorials Tips Tab");
		Thread.sleep(15000);
		getWebElement("ConfigureYourLabLabName").clear();
		getWebElement("ConfigureYourLabLabName").sendKeys("Newreg17161 Lab");
		Thread.sleep(1000);
		/*Library lib = new Library();
		lib.Select("ConfigureYourLabTimeZone", 24);
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabDesc").sendKeys("Desc");
		Thread.sleep(1000);*/
		getWebElement("InviteCollaboratorsButton").click();
		
		Reporter.log("Navigate to Configure Your Lab Tab and then to Setup Your Profile Tab");
		Thread.sleep(5000);
		getWebElement("ConfigureYourLabButton1").click();
		Thread.sleep(1000);
		getWebElement("SetUpYourProfileButton").click();
		
		Reporter.log("Reset First and Last Name from Setup Your Profile Tab then click Configure Your Lab Tab");
		Thread.sleep(3000);
		getWebElement("SetupYourProfileFirstName").clear();
		getWebElement("SetupYourProfileFirstName").sendKeys("Devtest");
		Thread.sleep(1000);
		getWebElement("SetupYourProfileLastName").clear();
		getWebElement("SetupYourProfileLastName").sendKeys("Newreg1716");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabButton").click();
		
		Reporter.log("Reset the required fields in Configure Your Lab Tab and click Tutorials Tips Tab");
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabLabName").clear();
		getWebElement("ConfigureYourLabLabName").sendKeys("Newreg1716 Lab");
		Thread.sleep(1000);
		/*Library lib = new Library();
		lib.Select("ConfigureYourLabTimeZone", 8);
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabDesc").clear();
		Thread.sleep(1000);*/
		getWebElement("InviteCollaboratorsButton").click();
		
		Reporter.log("Navigate to Configure Your Lab Tab and then to Setup Your Profile Tab");
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabButton1").click();
		Thread.sleep(1000);
		getWebElement("SetUpYourProfileButton").click();
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
	}
}
