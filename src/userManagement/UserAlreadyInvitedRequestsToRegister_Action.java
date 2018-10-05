package userManagement;

import java.util.ArrayList;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class UserAlreadyInvitedRequestsToRegister_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void UserAlreadyInvitedRequestsToRegister() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 39);
		String FirstName = ExcelUtils.getCellData(1, 37);
		String LastName = ExcelUtils.getCellData(1, 38);
		String Password = ExcelUtils.getCellData(1, 40);
		
		Reporter.log("Open Application");
		init();
		impliciteWait(5);
		
		Reporter.log("Click on Home page Register");
		getWebElement("HomePageRegister").click();
		
		Reporter.log("Open Mailinator in New tab and create email ID");
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
	
		Reporter.log("Open Application in new Tab");
		Thread.sleep(15000);
		driver.switchTo().window(tabs.get(0));
		driver.get("https://ae1c-bioinfocloud-slb01/auth/");
		
		Reporter.log("Click on Home page Register");
		getWebElement("HomePageRegister").click();
		
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
		lib.Select("RegistrationCountry", 79);
		Thread.sleep(1000);
		getWebElement("RegistrationCaptcha").sendKeys("ab");
		Thread.sleep(1000);
		getWebElement("RegistrationTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegistrationAccept").click();
		Thread.sleep(2000);
		getWebElement("RegistrationCreateAccount").click();
		
		Reporter.log("Verify the Validation message for already Registered user");
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("Logon Id already exists !"));
		
		Reporter.log("Close Browser");
		Thread.sleep(3000);
		driver.quit();
		
		
	}
}
