package stockroomUserManagement;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class StkRoomAdminMemberListFunctionality_Action extends TestBase{
	
	@Test
	public void StkRoomAdminMemberListFunctionality() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "StockroomUserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 0);
		String StockroomName = ExcelUtils.getCellData(1, 12);
		//String emailId = ExcelUtils.setCellData(title, RowNum, ColNum);
		
		Reporter.log("Login as MSAdmin");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Search for a Stockroom and navigate to Members List page");
		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys("dec23");
		
		Reporter.log("Verify Stockroom Lead and User functionality by changing the Role");
		Thread.sleep(3000);
		getWebElement("SelectMemberList").click();
		driver.getPageSource().contains("Stockroom User");
		Thread.sleep(3000);
		driver.getPageSource().contains("Stockroom Lead");
		Thread.sleep(5000);
		Library lib = new Library();
		lib.Select("StockroomUserRoleChange", 0);
		Thread.sleep(7000);
		lib.Select("StockroomLeadRoleChange", 1);
		Thread.sleep(7000);
		lib.Select("StockroomUserRoleChange", 1);
		Thread.sleep(7000);
		lib.Select("StockroomLeadRoleChange", 0);
		Thread.sleep(7000);
		getWebElement("StockroomUserStatusChange").click();
		Thread.sleep(7000);
		getWebElement("StockroomLeadStatusChange").click();
		Thread.sleep(7000);
		getWebElement("StockroomUserStatusChange").click();
		Thread.sleep(7000);
		getWebElement("StockroomLeadStatusChange").click();
		Thread.sleep(5000);
		getWebElement("DeleteStockroomUser").click();
		Thread.sleep(5000);
		getWebElement("StkroomAdminAddNewMembers").click();
		Thread.sleep(1000);
		getWebElement("EnterEmailId").sendKeys("stkroomuser23jan@mailinator.com");
		Thread.sleep(1000);
		lib.Select("SelectRole", 1);
		Thread.sleep(1000);
		getWebElement("InviteButton").click();
		Thread.sleep(5000);
		getWebElement("ReinviteMember").click();
		
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys("stkroomuser23jan@mailinator.com");
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		impliciteWait(5);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		driver.findElement(By.partialLinkText("Confirm to Join")).click();
		Thread.sleep(3000);
		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		Thread.sleep(3000);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
		getWebElement("JoinStockroomButton").click();
		
		Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
		
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Search for a Stockroom and navigate to Members List page");
		Thread.sleep(3000);
		getWebElement("LabMembersSearch").sendKeys("dec23");
		
		Reporter.log("Verify Stockroom Lead and User functionality by changing the Role");
		Thread.sleep(3000);
		getWebElement("SelectMemberList").click();
		Thread.sleep(2000);
		getWebElement("StockroomUserStatusChange").click();
		Thread.sleep(7000);
		getWebElement("StockroomUserStatusChange").click();
		Thread.sleep(2000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
