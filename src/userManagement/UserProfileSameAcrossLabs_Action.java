package userManagement;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class UserProfileSameAcrossLabs_Action extends TestBase {
	
	@SuppressWarnings("deprecation")
	@Test
	public void UserProfileSameAcrossLabs() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("8mar1457@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Edit the required fileds and Save");
		Thread.sleep(7000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	Thread.sleep(3000);
    	getWebElement("UserProfileEditFirstName").sendKeys("1");
    	getWebElement("UserProfileEditLastName").sendKeys("1");
    	Thread.sleep(2000);
    	Library lib = new Library();
    	lib.Select("EditProfileCountry", 62);
    	Thread.sleep(2000);
    	getWebElement("UserProfileEditPhone").clear();
    	getWebElement("UserProfileEditPhone").sendKeys("111.111.1111");
    	Thread.sleep(2000);
    	lib.Select("UserProfileEditTimezone", 10);
    	Thread.sleep(2000);
    	getWebElement("EditProfileSave").click();
    	
    	Thread.sleep(15000);
    	getWebElement("EditProfileCancel").click();
    	
		Reporter.log("Click on User Settings and go to Lab Members");
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		getWebElement("SwitchContext").click();
		
		Reporter.log("Go to Newly created Lab");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='switchLabForm:usersListTable']/div[1]/div/div[2]/div[1]/table/tbody/tr[last()]/td[3]/div/a")).click();
		
		Reporter.log("Click on User Settings and go to My Profile");
		Thread.sleep(10000);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
		
		Reporter.log("Edit the required fileds and Save");
		Thread.sleep(7000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	Thread.sleep(2000);
    	Assert.assertTrue(driver.getPageSource().contains("Deepak1"));
    	Thread.sleep(2000);
    	Assert.assertTrue(driver.getPageSource().contains("8mar14571"));
    	Thread.sleep(2000);
    	Assert.assertTrue(driver.getPageSource().contains("(111) 111-1111"));
    	Thread.sleep(3000);
    	getWebElement("UserProfileEditFirstName").sendKeys(Keys.BACK_SPACE);
    	getWebElement("UserProfileEditLastName").sendKeys(Keys.BACK_SPACE);
    	Thread.sleep(5000);
    	lib.Select("EditProfileCountry", 77);
    	Thread.sleep(2000);
    	getWebElement("UserProfileEditPhone").clear();
    	Thread.sleep(2000);
    	lib.Select("UserProfileEditTimezone", 24);
    	Thread.sleep(2000);
    	getWebElement("EditProfileSave").click();
    	
    	Thread.sleep(8000);
    	getWebElement("EditProfileCancel").click();
    	
		Reporter.log("Click on User Settings and go to Lab Members");
		Thread.sleep(2000);
		getWebElement("UserSettings").click();
		getWebElement("SwitchContext").click();
		
		Thread.sleep(3000);
		getWebElement("DefaultLab").click();
		Thread.sleep(5000);
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();
		
	}
}
