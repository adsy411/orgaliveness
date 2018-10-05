package stockroomUserManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.reporters.EmailableReporter;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import userManagement.EmailNotificationSettings_Action;
import utills.Constants;
import utills.ExcelUtils;

public class AddStockroom_Action extends TestBase{
	
	@Test
	public void AddStockroom() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "StockroomUserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 0);
		String StockroomName = ExcelUtils.getCellData(1, 12);
		//String emailId = ExcelUtils.setCellData(title, RowNum, ColNum);
		
		Reporter.log("Login to Superuser");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestadmin@sial.com");
		getWebElement("Enotebook.login.password").sendKeys("sigma123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Admins");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		Thread.sleep(1000);
		getWebElement("UserSettingsAdmins").click();
		
		Reporter.log("Search for MS Admin user and copy the email ID then logout");
		getWebElement("StockroomSearch").sendKeys(MailIdCreation);
		Thread.sleep(3000);
		WebElement element = getWebElement("CopyEmail");
		String title = element.getAttribute("title");
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "StockroomUserManagement");
		ExcelUtils.setCellData(title, 1, 3, Constants.Path_TestData
				+ Constants.File_TestData,"test");
		
		/*Thread.sleep(5000);
		getWebElement("UserSettings").click();
		getWebElement("Logout").click();
		
		Reporter.log("Login with newly created MS Admin");
		Thread.sleep(5000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(title);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Create New Stockroom Name and Verify in Home page then Logout");
		Thread.sleep(5000);
		getWebElement("GoToStockrooms").click();
		impliciteWait(5);
		getWebElement("AddStockroomButton").click();
		impliciteWait(5);
		getWebElement("StockroomNameField").sendKeys(StockroomName);
		Thread.sleep(1000);
		getWebElement("StockroomCreateButton").click();
		Thread.sleep(3000);
		getWebElement("StockroomSearchField").sendKeys(StockroomName);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(StockroomName));
		
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		getWebElement("Logout").click();
		
		Reporter.log("Login to Superuser and Verify the Stockroom Name");
		Thread.sleep(5000);
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestadmin@sial.com");
		getWebElement("Enotebook.login.password").sendKeys("sigma123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Thread.sleep(5000);
		getWebElement("SelectLabName").click();
		
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		Thread.sleep(1000);
		getWebElement("UsersettingsStockroomList").click();
		impliciteWait(5);
		getWebElement("SuperuserStockroomSearchField").sendKeys(StockroomName);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(StockroomName));
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();*/
		
	}

}
