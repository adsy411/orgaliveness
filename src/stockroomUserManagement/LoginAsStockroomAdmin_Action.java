package stockroomUserManagement;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import cucumber.api.java.en.Then;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LoginAsStockroomAdmin_Action extends TestBase{
	
	@Test
	public void LoginAsStockroomAdmin() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "StockroomUserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 0);
		String StockroomName = ExcelUtils.getCellData(1, 12);
		//String emailId = ExcelUtils.setCellData(title, RowNum, ColNum);
		
		Reporter.log("Login as Stockroom Admin");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("stkroomadmin@20email.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Verify the Stockroom List count");
		Thread.sleep(5000);
		String stkroomcount = getWebElement("StockroomCount").getText();
		System.out.println(stkroomcount);
		
		StringBuffer sb = new StringBuffer(stkroomcount);
		sb.delete(3, 19);
		System.out.println(sb);
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
