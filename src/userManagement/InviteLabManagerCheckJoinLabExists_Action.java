package userManagement;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class InviteLabManagerCheckJoinLabExists_Action extends TestBase{
	
	@Test
	public void InviteLabManagerCheckJoinLabExists() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 24);
		String FirstName = ExcelUtils.getCellData(1, 25);
		String LastName = ExcelUtils.getCellData(1, 26);
		
		Reporter.log("Login to Application with specific User Five");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("28feb2144@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Lab Members");
		Thread.sleep(5000);
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
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added Manager");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		Library lib = new Library();
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Register newly added Manager");
		Thread.sleep(20000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(15000);
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
		
		Reporter.log("Login to the registered EmailID and Logout");
		Thread.sleep(3000);
		getWebElement("Enotebook.login.username").sendKeys(EmailId);
		//getWebElement("Enotebook.login.username").sendKeys("newman3301@amail.club");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("DefaultLabName").click();
		Thread.sleep(5000);
		getWebElement("InviteCollaboratorsButton1").click();
		Thread.sleep(15000);
		getWebElement("GoToMyLabButton").click();
		Thread.sleep(15000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
		
		Reporter.log("Login to Application with Specific User Six");
		Thread.sleep(3000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("01mar1526@mailinator.com");
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
		
		Reporter.log("Invite the Existing Manager");
		Thread.sleep(3000);
		getWebElement("EnterEmailId").sendKeys(EmailId);
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to Nadamail and Confirm to Join");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(20000);
		getWebElement("InboxEmailID").click();
		Thread.sleep(8000);
		getWebElement("NadaMailListnew").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaConfirmToJoinButton").click();
		
		Reporter.log("Signin as Manager Login and Click on Join Lab");
		Thread.sleep(7000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
	    getWebElement("Enotebook.login.password").sendKeys("admin123");
	    Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id='switchLabForm:usersListTable']/div/div/div[2]/div/table/tbody/tr[2]/td[3]/div/a")).click();
		//getWebElement("JoinLabButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		
	}
}
