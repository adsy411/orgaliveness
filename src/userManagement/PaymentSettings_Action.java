package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class PaymentSettings_Action extends TestBase{
	
	@Test
    public void PaymentSettings() throws Exception {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String PI = ExcelUtils.getCellData(1, 12);
		String PO = ExcelUtils.getCellData(1, 13);
    	
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		impliciteWait(5);
		getWebElement("UserProfile").click();
    	
		Reporter.log("Click on Profile Settings and Select Payment Settings");
		Thread.sleep(5000);
		getWebElement("ProfileSettings").click();
		Thread.sleep(2000);
    	getWebElement("PaymentSettings").click();
    	
    	Reporter.log("Add New PI and PO and click on Save");
    	Thread.sleep(3000);
    	Library lib = new Library();
    	lib.Select("PIfield", 1);
    	Thread.sleep(2000);
    	getWebElement("PITextField").sendKeys(PI);
    	Thread.sleep(3000);
    	lib.Select("POfield", 1);
    	Thread.sleep(2000);
    	getWebElement("POTextField").sendKeys(PO);
    	Thread.sleep(2000);
    	getWebElement("SavePaymentSettings").click();
    	Thread.sleep(5000);
    	getWebElement("CancelPaymentSettings").click();
    	
    	Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
    	
	}
}
