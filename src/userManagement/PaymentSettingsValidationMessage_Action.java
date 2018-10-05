package userManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class PaymentSettingsValidationMessage_Action extends TestBase{
	
	@Test
    public void PaymentSettingsValidationMessage() throws Exception {
		
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
    	
		Reporter.log("Click on Profile Settings and Select Payment Settings then verify the Validation messages");
		Thread.sleep(5000);
		getWebElement("ProfileSettings").click();
		Thread.sleep(2000);
    	getWebElement("PaymentSettings").click();

    	Thread.sleep(3000);
    	driver.getWindowHandle();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id='user-payment-info-form']/div[1]/h4")));
    	Library lib = new Library();
    	lib.Select("PIfield", 0);
    	Thread.sleep(1000);
    	lib.Select("POfield", 0);
    	Thread.sleep(1000);
    	getWebElement("SavePaymentSettings").click();
    	
    	Thread.sleep(2000);
		String Message  = getWebElement("PaymentSettingsPIValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='user-payment-info-form:pi-error']", Message);
		
		Thread.sleep(2000);
		String Message1  = getWebElement("PaymentSettingsPOValMess").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='user-payment-info-form:po-error']", Message1);
		
		Reporter.log("Click on Cancel in Payments settings popup");
		Thread.sleep(2000);
		getWebElement("CancelPaymentSettings").click();
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		login.Logout();
    	
	}
}
