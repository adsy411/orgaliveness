package userManagement;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabOwnerRegistrationAndOnboarding_Action extends TestBase{
	
	@Test
	public void LabOwnerRegistrationAndOnboarding() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 30);
		String FirstName = ExcelUtils.getCellData(1, 28);
		String LastName = ExcelUtils.getCellData(1, 29);
		String Password = ExcelUtils.getCellData(1, 31);
		
		Reporter.log("Open Application");
		init();
		Thread.sleep(3000);
		
		Reporter.log("Click on Home page Register");
		driver.findElement(By.xpath("//*[@id='navbar']/ul/li[2]/a")).click();
		//getWebElement("HomePageRegister").click();
		Thread.sleep(2000);
		getWebElement("LoginRegisterTab").click();
		
		Reporter.log("Open Mailinator in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys(MailIdCreation);
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		String EmailId = driver.findElement(By.xpath("//*[@id='query_data']/div[4]")).getText();
		
		Reporter.log("Enter the required fields in Register page");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(3000);
		getWebElement("RegistrationFirstName").sendKeys(FirstName);
		Thread.sleep(1000);
		getWebElement("RegistrationLastName").sendKeys(LastName);
		Thread.sleep(1000);
		getWebElement("RegistrationEmail").sendKeys(EmailId);
		Thread.sleep(1000);
		getWebElement("RegistrationPassword").sendKeys(Password);
		Thread.sleep(1000);
		getWebElement("LabOwnerConfirmPassField").sendKeys(Password);
		Thread.sleep(1000);
		Library lib = new Library();
		lib.Select("RegistrationCountry", 79);
		Thread.sleep(1000);
		getWebElement("RegistrationCaptcha").sendKeys("ab");
		Thread.sleep(1000);
		getWebElement("RegistrationTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegistrationAccept").click();
		Thread.sleep(2000);
		getWebElement("RegistrationCreateAccount").click();
		
		Reporter.log("Navigate to Mailinator and Register newly added Manager");
		Thread.sleep(15000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		driver.findElement(By.linkText("Click Here To Access")).click();
		
		Reporter.log("Login with the New Registered User");
		Thread.sleep(7000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	    }
	    Thread.sleep(5000);
	    getWebElement("Enotebook.login.username").sendKeys(EmailId);
	    Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys(Password);
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
//		Thread.sleep(3000);
//		getWebElement("SetupYourProfilePageNextButton").click();
//		Thread.sleep(15000);
//		getWebElement("ConfigureYourLabNextButton").click();
//		Thread.sleep(8000);
//		getWebElement("TutorialTipsGotoMyLabButton").click();

		Reporter.log("Update First and Last Name from Setup Your Profile Tab then click Configure Your Lab Tab");
		Thread.sleep(5000);
		getWebElement("SetupYourProfileFirstName").clear();
		getWebElement("SetupYourProfileFirstName").sendKeys("Devtest1");
		Thread.sleep(1000);
		getWebElement("SetupYourProfileLastName").clear();
		getWebElement("SetupYourProfileLastName").sendKeys("Newreg17");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabButton").click();
		
		Reporter.log("Update the required fields in Configure Your Lab Tab and click Tutorials Tips Tab");
		Thread.sleep(10000);
		getWebElement("ConfigureYourLabLabName").clear();
		getWebElement("ConfigureYourLabLabName").sendKeys("Newreg17 Lab");
		Thread.sleep(1000);
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
		getWebElement("SetupYourProfileLastName").sendKeys(LastName);
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		getWebElement("ConfigureYourLabButton").click();
		
		Reporter.log("Reset the required fields in Configure Your Lab Tab and click Tutorials Tips Tab");
		Thread.sleep(5000);
		getWebElement("ConfigureYourLabLabName").clear();
		getWebElement("ConfigureYourLabLabName").sendKeys(LastName+ "Lab");
		Thread.sleep(1000);
		getWebElement("InviteCollaboratorsButton").click();
		
		Reporter.log("Navigate to Configure Your Lab Tab and then to Setup Your Profile Tab");
		Thread.sleep(2000);
		getWebElement("ConfigureYourLabButton1").click();
		Thread.sleep(2000);
		getWebElement("SetUpYourProfileButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
