package userManagement;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import bsh.org.objectweb.asm.Type;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabMemberInvitationFromOwner_Action extends TestBase{
	
	@Test
	public void LabMemberInvitationFromOwner() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 4);
		String FirstName = ExcelUtils.getCellData(1, 5);
		String LastName = ExcelUtils.getCellData(1, 6);
		
		Reporter.log("Login to Application with specific user Two");
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
		
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Open Nadamail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(MailIdCreation);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(2000);
		String EmailId = driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).getText();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added Member");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Member");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(8000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName);
		getWebElement("RegLastName").sendKeys(LastName);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("RegConfirmPassword").sendKeys("admin123");
		Thread.sleep(2000);
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		
		Reporter.log("Open Mconnected Lab and verify the First and Last Name");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		impliciteWait(5);
		Assert.assertTrue(driver.getPageSource().contains(FirstName+" "+LastName));
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}


}
