package userManagement;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class InvalidLoginAndRequiredFieldValidation_Action extends TestBase{
	
	@Test
	public void InvalidLoginAndRequiredFieldValidation() throws Exception{
		
		Reporter.log("Open Login Page");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		
		Reporter.log("Enter Invalid Email ID and Password then Click Signin then verify messages");
		getWebElement("Enotebook.login.username").sendKeys("123@login.com");
		getWebElement("Enotebook.login.password").sendKeys("admin");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(2000);
		String Message  = getWebElement("InvalidLoginMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='loginForm']/ul/li", Message);
		
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		String Message1  = getWebElement("LoginValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='loginInfo-error']", Message1);
		
		Reporter.log("Close the Browser");
		Thread.sleep(2000);
		driver.quit();
		
	}
}
