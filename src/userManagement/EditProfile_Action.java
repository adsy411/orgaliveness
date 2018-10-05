package userManagement;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;

public class EditProfile_Action extends TestBase{
	
	@Test
    public void EditProfile() throws Exception {
    	
		Reporter.log("Login to Application");
		init();
		LoginPage login = new LoginPage();
		login.loginToApplication();
		
		Reporter.log("Click on User Settings and go to My Profile");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("UserProfile").click();
    	
		Reporter.log("Edit the Username and Save then Verify the Username from My Profile page and Header Drop Down");
		Thread.sleep(5000);
    	getWebElement("EditName").click();
    	Thread.sleep(3000);
    	getWebElement("UserEditFirstName").sendKeys("1");
    	impliciteWait(5);
    	getWebElement("UserEditLastName").sendKeys("1");
    	impliciteWait(5);
    	getWebElement("EditNameSave").click();
    	Thread.sleep(5000);
    	String UserNameFromMyProfile = driver.findElement(By.id("user-name-popover:fullName")).getText();
    	
    	Thread.sleep(5000);
    	getWebElement("LogoutRightButton").click();
    	Thread.sleep(2000);
    	String UserNameFromHeaderDropDown = driver.findElement(By.id("header-drop-down-name")).getText();
    	
    	Thread.sleep(2000);
    	Assert.assertEquals(UserNameFromMyProfile, UserNameFromHeaderDropDown);
    	getWebElement("LogoutRightButton").click();
    	
    	Reporter.log("Edit the Profile and Save");
    	Thread.sleep(7000);
    	getWebElement("ProfileSettings").click();
    	Thread.sleep(3000);
    	getWebElement("EditProfile").click();
    	Thread.sleep(3000);
    	getWebElement("UserProfileEditFirstName").sendKeys(Keys.BACK_SPACE);
    	getWebElement("UserProfileEditLastName").sendKeys(Keys.BACK_SPACE);
    	Thread.sleep(2000);
    	getWebElement("UserProfileEditPhone").clear();
    	Thread.sleep(2000);
    	getWebElement("UserProfileEditPhone").sendKeys("111.111.1111");
    	Library lib = new Library();
    	lib.Select("UserProfileEditTimezone", 24);
    	impliciteWait(5);
    	getWebElement("EditProfileSave").click();
    	Thread.sleep(5000);
    	driver.getWindowHandle();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id='user-profile-form']/div[1]/h4")));
		Thread.sleep(2000);
    	getWebElement("EditProfileCancel").click();
    	
    	Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		login.Logout();
    	
    }
}