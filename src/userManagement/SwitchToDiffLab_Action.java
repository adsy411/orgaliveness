package userManagement;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class SwitchToDiffLab_Action extends TestBase{
	
	@SuppressWarnings("deprecation")
	@Test
    public void SwitchToDiffLab() throws Exception {
    	
		Reporter.log("Login to Application with specific user Two");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestmsadmin1dec@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to Switch Context");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		impliciteWait(5);
		getWebElement("SwitchContext").click();
		
		Reporter.log("Create New Lab then Go to Newly created Lab and Verify the Lab Name in User Settings");
		Thread.sleep(3000);
		getWebElement("CreateNewLab").click();
		Thread.sleep(2000);
		getWebElement("CreateNewLabYesButton").click();
		
		Thread.sleep(5000);
		String NewLab = getWebElement("NewLabSuccessfulMessage").getText();
		System.out.println(NewLab);
		
		StringBuffer sb = new StringBuffer(NewLab);
		sb.delete(19, 41);
		System.out.println(sb);
		
		Thread.sleep(20000);
		getWebElement("NewLabSuccessfulConfirmationOkButton").click();
		
		Reporter.log("Go to Newly created Lab");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='switchLabForm:usersListTable']/div[1]/div/div[2]/div[1]/table/tbody/tr[last()]/td[3]/div/a")).click();
	
		Reporter.log("Verify the Lab Name in User Settings");
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		
		Thread.sleep(5000);
		String VerifyLabName = getWebElement("VerifyLabNameFromUserSettings").getText();
		System.out.println(VerifyLabName);
		
		Assert.assertEquals(sb.toString(), VerifyLabName);
		getWebElement("UserSettings").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
