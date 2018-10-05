package stockroomUserManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class StockroomLeadInvitation_Action extends TestBase{
	
	@Test
	public void StockroomLeadInvitation() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "StockroomUserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 0);
		String StockroomName = ExcelUtils.getCellData(1, 12);
		
		Reporter.log("Login to Superuser");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		AddStockroom_Action addstockroom = new AddStockroom_Action();
		addstockroom.AddStockroom();
		getWebElement("Enotebook.login.username").sendKeys("devtestadmin@sial.com");
		getWebElement("Enotebook.login.password").sendKeys("sigma123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		
		
		
		
	}
}
