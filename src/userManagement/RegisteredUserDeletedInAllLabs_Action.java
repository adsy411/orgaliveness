package userManagement;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class RegisteredUserDeletedInAllLabs_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void RegisteredUserDeletedInAllLabs() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		String EmailID = driver.findElement(By.xpath("//*[@id='memberForm:memberTable_data']/tr[2]/td[2]/span")).getText();

		Reporter.log("Delete Lab Manager from Lab Members page");
		Thread.sleep(3000);
		getWebElement("LabManagerDetele").click();
		Thread.sleep(1000);
		getWebElement("DeleteUser").click();
		
		Reporter.log("Logout from Application");
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(3000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Login to Application as Lab Manager");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(EmailID);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Verify the Validation Message for Deleted Lab Manager");
		Assert.assertTrue(driver.getPageSource().contains("Your lab does not exist. Do you want to create a new lab?"));
		
		Reporter.log("Navigate Backword and Login as Lab Owner");
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
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
		
		Reporter.log("Eneter the Deleted Lab Manager and Invite");
		Thread.sleep(2000);
		getWebElement("EnterEmailId").sendKeys(EmailID);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Open Nadamail in New tab and create email ID");
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
		getWebElement("AddEmailID").sendKeys("manager14mar");
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter Username and Password and Login");
		Thread.sleep(7000);
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click Join Lab");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='switchLabForm:usersListTable']/div/div/div[2]/div/table/tbody/tr/td[3]/div/a")).click();
		//getWebElement("JoinLabButton").click();
	    
		Reporter.log("Logout from Application");
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(3000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Login to Application as Lab Owner");
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Verify the Deleted Lab Manager is available in Lab Memebers page");
		Assert.assertTrue(driver.getPageSource().contains(EmailID));
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	    
		
	}
}
