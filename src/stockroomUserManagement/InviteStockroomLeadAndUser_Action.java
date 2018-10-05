package stockroomUserManagement;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class InviteStockroomLeadAndUser_Action extends TestBase{
	
	@Test
	public void InviteStockroomLeadAndUser() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "StockroomUserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 14);
		String FirstName = ExcelUtils.getCellData(1, 15);
		String LastName = ExcelUtils.getCellData(1, 16);
		String MailIdCreation1 = ExcelUtils.getCellData(1, 18);
		String FirstName1 = ExcelUtils.getCellData(1, 19);
		String LastName1 = ExcelUtils.getCellData(1, 20);
		
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
		
		Reporter.log("Open 20minutemail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.20minutemail.com/");
	    Thread.sleep(3000);
	    getWebElement("CreateMailField").clear();
	    getWebElement("CreateMailField").sendKeys(MailIdCreation);
	    Thread.sleep(3000);
		getWebElement("CreateMailButton").click();
		Thread.sleep(5000);
		getWebElement("CopyEmailid").click();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added MS Admin");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(Keys.CONTROL + "V");
		Library lib = new Library();
		lib.Select("SelectRole", 0);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to 20minutemail and Register newly added MS Admin");
		Thread.sleep(8000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		getWebElement("EmailList").click();
		Thread.sleep(3000);
		getWebElement("RegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		impliciteWait(5);
		getWebElement("RegFirstName").sendKeys(FirstName);
		getWebElement("RegLastName").sendKeys(LastName);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
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
		
		Reporter.log("Click on User Settings and go to Admins");
		impliciteWait(5);
		driver.navigate().refresh();
		
		Reporter.log("Verify the First and Last Name");
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains(FirstName+" "+LastName));
		
		Thread.sleep(3000);
		getWebElement("StkroomAdminAddNewMembers").click();
		
		Reporter.log("Open 20minutemail in New tab and create new email ID");
		Thread.sleep(5000);
		/*((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());*/
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.20minutemail.com/");
	    Thread.sleep(3000);
	    getWebElement("CreateMailField").clear();
	    getWebElement("CreateMailField").sendKeys(MailIdCreation1);
	    Thread.sleep(3000);
		getWebElement("CreateMailButton").click();
		Thread.sleep(5000);
		getWebElement("CopyEmailid").click();
		
		Reporter.log("Navigate to Mconnected Lab and Invite the newly added MS Admin");
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(0));
		impliciteWait(5);
		getWebElement("EnterEmailId").sendKeys(Keys.CONTROL + "V");
		lib.Select("SelectRole", 1);
		Thread.sleep(2000);
		getWebElement("InviteButton").click();
		
		Reporter.log("Navigate to 20minutemail and Register newly added MS Admin");
		Thread.sleep(8000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		getWebElement("EmailList").click();
		Thread.sleep(3000);
		getWebElement("RegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		impliciteWait(5);
		getWebElement("RegFirstName").sendKeys(FirstName1);
		getWebElement("RegLastName").sendKeys(LastName1);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
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
		
		Reporter.log("Click on User Settings and go to Admins");
		impliciteWait(5);
		driver.navigate().refresh();
		
		Reporter.log("Verify the First and Last Name");
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains(FirstName1+" "+LastName1));
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
	}
}
